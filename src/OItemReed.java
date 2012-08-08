
public class OItemReed extends OItem {

    private int a;

    public OItemReed(int i, OBlock oblock) {
        super(i);
        this.a = oblock.bO;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        // CanaryMod: Store blockClicked
        int clicked = oworld.a(i, j, k);
        Block blockClicked = new Block(oworld.world, clicked, i, j, k);
        
        int i1 = oworld.a(i, j, k);

        if (i1 == OBlock.aS.bO) {
            l = 1;
        } else if (i1 != OBlock.bu.bO && i1 != OBlock.X.bO && i1 != OBlock.Y.bO) {
            if (l == 0) {
                --j;
            }

            if (l == 1) {
                ++j;
            }

            if (l == 2) {
                --k;
            }

            if (l == 3) {
                ++k;
            }

            if (l == 4) {
                --i;
            }

            if (l == 5) {
                ++i;
            }
        }

        if (!oentityplayer.d(i, j, k)) {
            return false;
        } else if (oitemstack.a == 0) {
            return false;
        } else {
            if (oworld.a(this.a, i, j, k, false, l)) {
                // CanaryMod: Reed placement
                Block blockPlaced = new Block(oworld.world, this.a, i, j, k);

                blockClicked.setFaceClicked(Block.Face.fromId(l));
                Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(oitemstack))) {
                    return false;
                }
                
                OBlock oblock = OBlock.m[this.a];

                if (oworld.e(i, j, k, this.a)) {
                    if (oworld.a(i, j, k) == this.a) {
                        OBlock.m[this.a].e(oworld, i, j, k, l);
                        OBlock.m[this.a].a(oworld, i, j, k, (OEntityLiving) oentityplayer);
                    }

                    oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), oblock.cb.c(), (oblock.cb.a() + 1.0F) / 2.0F, oblock.cb.b() * 0.8F);
                    --oitemstack.a;
                }
            }

            return true;
        }
    }
}
