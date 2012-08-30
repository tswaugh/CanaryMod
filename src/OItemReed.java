
public class OItemReed extends OItem {

    private int a;

    public OItemReed(int i, OBlock oblock) {
        super(i);
        this.a = oblock.ca;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        // CanaryMod: Store blockClicked
        Block blockClicked = this.getBlockInfo(oworld, i, j, k, l);
        int i1 = oworld.a(i, j, k);

        if (i1 == OBlock.aS.ca) {
            l = 1;
        } else if (i1 != OBlock.bu.ca && i1 != OBlock.X.ca && i1 != OBlock.Y.ca) {
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

        if (!oentityplayer.e(i, j, k)) {
            return false;
        } else if (oitemstack.a == 0) {
            return false;
        } else {
            if (oworld.a(this.a, i, j, k, false, l, (OEntity) null)) {
                // CanaryMod: Reed placement
                Block blockPlaced = new Block(oworld.world, this.a, i, j, k);
                
                Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(oitemstack))) {
                    return false;
                }

                OBlock oblock = OBlock.m[this.a];

                if (oworld.e(i, j, k, this.a)) {
                    if (oworld.a(i, j, k) == this.a) {
                        OBlock.m[this.a].a(oworld, i, j, k, l, f, f1, f2);
                        OBlock.m[this.a].a(oworld, i, j, k, (OEntityLiving) oentityplayer);
                    }

                    oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), oblock.cn.d(), (oblock.cn.b() + 1.0F) / 2.0F, oblock.cn.c() * 0.8F);
                    --oitemstack.a;
                }
            }

            return true;
        }
    }
}
