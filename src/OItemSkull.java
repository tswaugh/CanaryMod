public class OItemSkull extends OItem {

    private static final String[] a = new String[] { "skeleton", "wither", "zombie", "char", "creeper"};
    private static final int[] b = new int[] { 224, 225, 226, 227, 228};

    public OItemSkull(int i) {
        super(i);
        this.a(OCreativeTabs.c);
        this.e(0);
        this.a(true);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        if (l == 0) {
            return false;
        } else if (!oworld.g(i, j, k).a()) {
            return false;
        } else {
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

            if (!oentityplayer.a(i, j, k, l, oitemstack)) {
                return false;
            } else if (!OBlock.cj.b(oworld, i, j, k)) {
                return false;
            } else {
                oworld.d(i, j, k, OBlock.cj.cm, l);
                int i1 = 0;

                if (l == 1) {
                    i1 = OMathHelper.c((double) (oentityplayer.z * 16.0F / 360.0F) + 0.5D) & 15;
                }

                OTileEntity otileentity = oworld.q(i, j, k);

                if (otileentity != null && otileentity instanceof OTileEntitySkull) {
                    String s = "";

                    if (oitemstack.o() && oitemstack.p().b("SkullOwner")) {
                        s = oitemstack.p().i("SkullOwner");
                    }

                    ((OTileEntitySkull) otileentity).a(oitemstack.j(), s);
                    ((OTileEntitySkull) otileentity).a(i1);
                    ((OBlockSkull) OBlock.cj).a(oworld, i, j, k, (OTileEntitySkull) otileentity);
                }

                --oitemstack.a;
                return true;
            }
        }
    }

    public int a(int i) {
        return i;
    }

    public String d(OItemStack oitemstack) {
        int i = oitemstack.j();

        if (i < 0 || i >= a.length) {
            i = 0;
        }

        return super.a() + "." + a[i];
    }

    public String l(OItemStack oitemstack) {
        return oitemstack.j() == 3 && oitemstack.o() && oitemstack.p().b("SkullOwner") ? OStatCollector.a("item.skull.player.name", new Object[] { oitemstack.p().i("SkullOwner")}) : super.l(oitemstack);
    }
}
