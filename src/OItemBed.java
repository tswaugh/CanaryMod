
public class OItemBed extends OItem {

    public OItemBed(int i) {
        super(i);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        // CanaryMod: store the block that was clicked
        Block blockClicked = new Block(oworld.world, oworld.world.getBlockIdAt(i, j, k), i, j, k);

        blockClicked.setFaceClicked(Block.Face.fromId(l));
        
        if (l != 1) {
            return false;
        } else {
            ++j;
            OBlockBed oblockbed = (OBlockBed) OBlock.S;
            int i1 = OMathHelper.b((double) (oentityplayer.bs * 4.0F / 360.0F) + 0.5D) & 3;
            byte b0 = 0;
            byte b1 = 0;

            if (i1 == 0) {
                b1 = 1;
            }

            if (i1 == 1) {
                b0 = -1;
            }

            if (i1 == 2) {
                b1 = -1;
            }

            if (i1 == 3) {
                b0 = 1;
            }
            
            // CanaryMod: onItemUse hook
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), new Block(oworld.world, OBlock.S.bO, i, j, k), blockClicked, new Item(oitemstack))) {
                return false;
            }

            if (oentityplayer.d(i, j, k) && oentityplayer.d(i + b0, j, k + b1)) {
                if (oworld.g(i, j, k) && oworld.g(i + b0, j, k + b1) && oworld.e(i, j - 1, k) && oworld.e(i + b0, j - 1, k + b1)) {
                    oworld.b(i, j, k, oblockbed.bO, i1);
                    if (oworld.a(i, j, k) == oblockbed.bO) {
                        oworld.b(i + b0, j, k + b1, oblockbed.bO, i1 + 8);
                    }

                    --oitemstack.a;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
