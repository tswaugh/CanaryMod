public class OBehaviorBucketEmptyDispense extends OBehaviorDefaultDispenseItem {

    private final OBehaviorDefaultDispenseItem c;

    final OMinecraftServer b;

    public OBehaviorBucketEmptyDispense(OMinecraftServer ominecraftserver) {
        this.b = ominecraftserver;
        this.c = new OBehaviorDefaultDispenseItem();
    }

    public OItemStack b(OIBlockSource oiblocksource, OItemStack oitemstack) {
        OEnumFacing oenumfacing = OEnumFacing.a(oiblocksource.h());
        OWorld oworld = oiblocksource.k();
        int i = oiblocksource.d() + oenumfacing.c();
        int j = oiblocksource.e();
        int k = oiblocksource.f() + oenumfacing.e();
        OMaterial omaterial = oworld.g(i, j, k);
        int l = oworld.h(i, j, k);
        OItem oitem;

        if (OMaterial.h.equals(omaterial) && l == 0) {
            oitem = OItem.ax;
        } else {
            if (!OMaterial.i.equals(omaterial) || l != 0) {
                return super.b(oiblocksource, oitemstack);
            }

            oitem = OItem.ay;
        }

        oworld.e(i, j, k, 0);
        if (--oitemstack.a == 0) {
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) oiblocksource.j()), null)) {
                oitemstack.c = oitem.cj;
                oitemstack.a = 1;
            }
        } else if (((OTileEntityDispenser) oiblocksource.j()).a(new OItemStack(oitem)) < 0) {
            this.c.a(oiblocksource, new OItemStack(oitem));
        }

        return oitemstack;
    }
}
