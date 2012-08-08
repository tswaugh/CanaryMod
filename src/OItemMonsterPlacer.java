
public class OItemMonsterPlacer extends OItem {

    public OItemMonsterPlacer(int i) {
        super(i);
        this.a(true);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        if (oworld.F || oitemstack.h() < 50 || (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), this.getBlockInfo(oworld, i, j, k, l), null, new Item(oitemstack))) {
            return true;
        } else {
            int i1 = oworld.a(i, j, k);

            i += OFacing.b[l];
            j += OFacing.c[l];
            k += OFacing.d[l];
            double d0 = 0.0D;

            if (l == 1 && i1 == OBlock.aZ.bO || i1 == OBlock.bB.bO) {
                d0 = 0.5D;
            }

            if (a(oworld, oitemstack.h(), (double) i + 0.5D, (double) j + d0, (double) k + 0.5D) && !oentityplayer.L.d) {
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
                oentity.c(d0, d1, d2, oworld.r.nextFloat() * 360.0F, 0.0F);
                oworld.b(oentity);
                ((OEntityLiving) oentity).az();
            }

            return oentity != null;
        }
    }
}
