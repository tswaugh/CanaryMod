
public class OItemDoor extends OItem {

    private OMaterial a;

    public OItemDoor(int i, OMaterial omaterial) {
        super(i);
        this.a = omaterial;
        this.bQ = 1;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        if (l != 1) {
            return false;
        } else {
            ++j;
            OBlock oblock;

            if (this.a == OMaterial.d) {
                oblock = OBlock.aE;
            } else {
                oblock = OBlock.aL;
            }

            if (oentityplayer.d(i, j, k) && oentityplayer.d(i, j + 1, k)) {
                if (!oblock.c(oworld, i, j, k)) {
                    return false;
                } else {
                    // CanaryMod hook: onItemUse
                    Block blockClicked = new Block(oworld.world, oworld.world.getBlockIdAt(i, j, k), i, j, k);

                    blockClicked.setFaceClicked(Block.Face.fromId(l));
                    Block blockPlaced = new Block(oworld.world, oblock.bO, i, j, k);

                    // Call the hook
                    if (oentityplayer instanceof OEntityPlayerMP) {
                        Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(oitemstack))) {
                            return false;
                        }
                    }
                    
                    int i1 = OMathHelper.b((double) ((oentityplayer.bs + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;

                    a(oworld, i, j, k, i1, oblock);
                    --oitemstack.a;
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public static void a(OWorld oworld, int i, int j, int k, int l, OBlock oblock) {
        byte b0 = 0;
        byte b1 = 0;

        if (l == 0) {
            b1 = 1;
        }

        if (l == 1) {
            b0 = -1;
        }

        if (l == 2) {
            b1 = -1;
        }

        if (l == 3) {
            b0 = 1;
        }

        int i1 = (oworld.e(i - b0, j, k - b1) ? 1 : 0) + (oworld.e(i - b0, j + 1, k - b1) ? 1 : 0);
        int j1 = (oworld.e(i + b0, j, k + b1) ? 1 : 0) + (oworld.e(i + b0, j + 1, k + b1) ? 1 : 0);
        boolean flag = oworld.a(i - b0, j, k - b1) == oblock.bO || oworld.a(i - b0, j + 1, k - b1) == oblock.bO;
        boolean flag1 = oworld.a(i + b0, j, k + b1) == oblock.bO || oworld.a(i + b0, j + 1, k + b1) == oblock.bO;
        boolean flag2 = false;

        if (flag && !flag1) {
            flag2 = true;
        } else if (j1 > i1) {
            flag2 = true;
        }

        oworld.o = true;
        oworld.b(i, j, k, oblock.bO, l);
        oworld.b(i, j + 1, k, oblock.bO, 8 | (flag2 ? 1 : 0));
        oworld.o = false;
        oworld.h(i, j, k, oblock.bO);
        oworld.h(i, j + 1, k, oblock.bO);
    }
}
