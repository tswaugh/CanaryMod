
public class OItemBlock extends OItem {

    private int a;

    public OItemBlock(int i) {
        super(i);
        this.a = i + 256;
        this.d(OBlock.m[i + 256].a(2));
    }

    public int a() {
        return this.a;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        // CanaryMod: Bail if we have nothing of the items in hand
        if (oitemstack.a == 0) {
            return false;
        }
        // CanaryMod: Store blockInfo of the one we clicked
        int blockClickedId = oworld.a(i, j, k);
        Block blockClicked = new Block(oworld.world, blockClickedId, i, j, k);
        
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
        
        // CanaryMod: Store faceClicked (must be here to have the 'snow' special case).
        blockClicked.setFaceClicked(Block.Face.fromId(l));
        // CanaryMod: And the block we're about to place
        Block blockPlaced = new Block(oworld.world, a, i, j, k);

        if (oitemstack.a == 0) {
            return false;
        } else if (!oentityplayer.d(i, j, k)) {
            return false;
        } else if (j == 255 && OBlock.m[this.a].cd.a()) {
            return false;
        } else if (oworld.a(this.a, i, j, k, false, l) // CanaryMod: prevent unwanted blocks from getting placed.
                && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack))) {
            OBlock oblock = OBlock.m[this.a];

            if (oworld.b(i, j, k, this.a, this.a(oitemstack.h()))) {
                if (oworld.a(i, j, k) == this.a) {
                    OBlock.m[this.a].e(oworld, i, j, k, l);
                    OBlock.m[this.a].a(oworld, i, j, k, (OEntityLiving) oentityplayer);
                }

                oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), oblock.cb.c(), (oblock.cb.a() + 1.0F) / 2.0F, oblock.cb.b() * 0.8F);
                --oitemstack.a;
            }

            return true;
        } else {
            return false;
        }
    }

    public String a(OItemStack oitemstack) {
        return OBlock.m[this.a].q();
    }

    public String b() {
        return OBlock.m[this.a].q();
    }
}
