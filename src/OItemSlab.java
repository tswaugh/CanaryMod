
public class OItemSlab extends OItemBlock {

    public OItemSlab(int i) {
        super(i);
        this.f(0);
        this.a(true);
    }

    public int a(int i) {
        return i;
    }

    public String a(OItemStack oitemstack) {
        int i = oitemstack.h();

        if (i < 0 || i >= OBlockStep.a.length) {
            i = 0;
        }

        return super.b() + "." + OBlockStep.a[i];
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, this.getBlockInfo(oworld, i, j, k, l), new Item(oitemstack))) {
            return true;
        }
            
        if (oitemstack.a == 0) {
            return false;
        } else if (!oentityplayer.d(i, j, k)) {
            return false;
        } else {
            int i1 = oworld.a(i, j, k);
            int j1 = oworld.c(i, j, k);
            int k1 = j1 & 7;
            boolean flag = (j1 & 8) != 0;

            if ((l == 1 && !flag || l == 0 && flag) && i1 == OBlock.ak.bO && k1 == oitemstack.h()) {
                if (oworld.a(OBlock.aj.e(oworld, i, j, k)) && oworld.b(i, j, k, OBlock.aj.bO, k1)) {
                    oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), OBlock.aj.cb.c(), (OBlock.aj.cb.a() + 1.0F) / 2.0F, OBlock.aj.cb.b() * 0.8F);
                    --oitemstack.a;
                }

                return true;
            } else {
                return b(oitemstack, oentityplayer, oworld, i, j, k, l) ? true : super.a(oitemstack, oentityplayer, oworld, i, j, k, l);
            }
        }
    }

    private static boolean b(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
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

        int i1 = oworld.a(i, j, k);
        int j1 = oworld.c(i, j, k);
        int k1 = j1 & 7;

        if (i1 == OBlock.ak.bO && k1 == oitemstack.h()) {
            if (oworld.a(OBlock.aj.e(oworld, i, j, k)) && oworld.b(i, j, k, OBlock.aj.bO, k1)) {
                oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), OBlock.aj.cb.c(), (OBlock.aj.cb.a() + 1.0F) / 2.0F, OBlock.aj.cb.b() * 0.8F);
                --oitemstack.a;
            }

            return true;
        } else {
            return false;
        }
    }
}
