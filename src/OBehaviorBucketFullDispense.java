public class OBehaviorBucketFullDispense extends OBehaviorDefaultDispenseItem {

    private final OBehaviorDefaultDispenseItem c;

    final OMinecraftServer b;

    public OBehaviorBucketFullDispense(OMinecraftServer ominecraftserver) {
        this.b = ominecraftserver;
        this.c = new OBehaviorDefaultDispenseItem();
    }

    public OItemStack b(OIBlockSource oiblocksource, OItemStack oitemstack) {
        OItemBucket oitembucket = (OItemBucket) oitemstack.b();
        int i = oiblocksource.d();
        int j = oiblocksource.e();
        int k = oiblocksource.f();
        OEnumFacing oenumfacing = OEnumFacing.a(oiblocksource.h());

        if (oitembucket.a(oiblocksource.k(), (double) i, (double) j, (double) k, i + oenumfacing.c(), j, k + oenumfacing.e())) {
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) oiblocksource.j()), null)) {
                oitemstack.c = OItem.aw.cj;
                oitemstack.a = 1;
            }
            return oitemstack;
        } else {
            return this.c.a(oiblocksource, oitemstack);
        }
    }
}
