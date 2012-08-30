
public class OItemBlock extends OItem {

    private int a;

    public OItemBlock(int i) {
        super(i);
        this.a = i + 256;
        this.c(OBlock.m[i + 256].a(2));
    }

    public int f() {
        return this.a;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        // CanaryMod: Bail if we have nothing of the items in hand
        if (oitemstack.a == 0) {
            return false;
        }
        // CanaryMod: Store blockInfo of the one we clicked
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
        
        // CanaryMod: Store faceClicked (must be here to have the 'snow' special case).
        blockClicked.setFaceClicked(Block.Face.fromId(l));
        // CanaryMod: And the block we're about to place
        Block blockPlaced = new Block(oworld.world, this.a, i, j, k);

        if (oitemstack.a == 0) {
            return false;
        } else if (!oentityplayer.e(i, j, k)) {
            return false;
        } else if (j == 255 && OBlock.m[this.a].cp.a()) {
            return false;
        } else if (oworld.a(this.a, i, j, k, false, l, oentityplayer) // CanaryMod: prevent unwanted blocks from getting placed.
                && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack))) {
            OBlock oblock = OBlock.m[this.a];

            if (oworld.d(i, j, k, this.a, this.b(oitemstack.j()))) {
                if (oworld.a(i, j, k) == this.a) {
                    OBlock.m[this.a].a(oworld, i, j, k, l, f, f1, f2);
                    OBlock.m[this.a].a(oworld, i, j, k, (OEntityLiving) oentityplayer);
                }

                oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), oblock.cn.d(), (oblock.cn.b() + 1.0F) / 2.0F, oblock.cn.c() * 0.8F);
                --oitemstack.a;
            }

            return true;
        } else {
            return false;
        }
    }

    public String c(OItemStack oitemstack) {
        return OBlock.m[this.a].a();
    }

    public String a() {
        return OBlock.m[this.a].a();
    }
}
