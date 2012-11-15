public class OItemMonsterPlacer extends OItem {

    public OItemMonsterPlacer(int i) {
        super(i);
        this.a(true);
        this.a(OCreativeTabs.f);
    }

    public String j(OItemStack oitemstack) {
        String s = ("" + OStatCollector.a(this.a() + ".name")).trim();
        String s1 = OEntityList.b(oitemstack.j());

        if (s1 != null) {
            s = s + " " + OStatCollector.a("entity." + s1 + ".name");
        }

        return s;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        // CanaryMod: call onItemUse
        if (oworld.J || (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), this.getBlockInfo(oworld, i, j, k, l), null, new Item(oitemstack))) {
            return true;
        } else {
            int i1 = oworld.a(i, j, k);

            i += OFacing.b[l];
            j += OFacing.c[l];
            k += OFacing.d[l];
            double d0 = 0.0D;

            if (l == 1 && OBlock.p[i1] != null && OBlock.p[i1].d() == 11) {
                d0 = 0.5D;
            }

            if (a(oworld, oitemstack.j(), (double) i + 0.5D, (double) j + d0, (double) k + 0.5D) != null && !oentityplayer.cc.d) {
                --oitemstack.a;
            }

            return true;
        }
    }

    public static OEntity a(OWorld oworld, int i, double d0, double d1, double d2) {
        if (!OEntityList.a.containsKey(Integer.valueOf(i))) {
            return null;
        } else {
            OEntity oentity = null;

            for (int j = 0; j < 1; ++j) {
                oentity = OEntityList.a(i, oworld);
                if (oentity != null) {
                    oentity.b(d0, d1, d2, oworld.u.nextFloat() * 360.0F, 0.0F);
                    ((OEntityLiving) oentity).bG();
                    oworld.d(oentity);
                    ((OEntityLiving) oentity).aO();
                }
            }

            return oentity;
        }
    }
}
