
public class OItemMonsterPlacer extends OItem {

    public OItemMonsterPlacer(int i) {
        super(i);
        this.a(true);
        this.a(OCreativeTabs.f);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        // CanaryMod: deny hackish eggs, call onItemUse
        if (oworld.K || oitemstack.j() < 50 || oitemstack.j() >= 200 || (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), this.getBlockInfo(oworld, i, j, k, l), null, new Item(oitemstack))) {
            return true;
        } else {
            int i1 = oworld.a(i, j, k);

            i += OFacing.b[l];
            j += OFacing.c[l];
            k += OFacing.d[l];
            double d0 = 0.0D;

            if (l == 1 && i1 == OBlock.aZ.ca || i1 == OBlock.bB.ca) {
                d0 = 0.5D;
            }

            if (a(oworld, oitemstack.j(), (double) i + 0.5D, (double) j + d0, (double) k + 0.5D) && !oentityplayer.bZ.d) {
                --oitemstack.a;
            }

            return true;
        }
    }

    public static boolean a(OWorld oworld, int i, double d0, double d1, double d2) {
        if (!OEntityList.a.containsKey(Integer.valueOf(i))) {
            return false;
        } else {
            OEntity oentity = OEntityList.a(i, oworld);

            if (oentity != null) {
                oentity.b(d0, d1, d2, oworld.v.nextFloat() * 360.0F, 0.0F);
                if (oentity instanceof OEntityVillager) {
                    OEntityVillager oentityvillager = (OEntityVillager) oentity;

                    oentityvillager.b(oentityvillager.au().nextInt(5));
                    oworld.d((OEntity) oentityvillager);
                    return true;
                }

                oworld.d(oentity);
                ((OEntityLiving) oentity).aH();
            }

            return oentity != null;
        }
    }
}
