public class OBehaviorDispenseMinecart extends OBehaviorDefaultDispenseItem {

    private final OBehaviorDefaultDispenseItem c;

    final OMinecraftServer b;

    public OBehaviorDispenseMinecart(OMinecraftServer ominecraftserver) {
        this.b = ominecraftserver;
        this.c = new OBehaviorDefaultDispenseItem();
    }

    public OItemStack b(OIBlockSource oiblocksource, OItemStack oitemstack) {
        OEnumFacing oenumfacing = OEnumFacing.a(oiblocksource.h());
        OWorld oworld = oiblocksource.k();
        double d0 = oiblocksource.a() + (double) ((float) oenumfacing.c() * 1.125F);
        double d1 = oiblocksource.b();
        double d2 = oiblocksource.c() + (double) ((float) oenumfacing.e() * 1.125F);
        int i = oiblocksource.d() + oenumfacing.c();
        int j = oiblocksource.e();
        int k = oiblocksource.f() + oenumfacing.e();
        int l = oworld.a(i, j, k);
        double d3;

        if (OBlockRail.e(l)) {
            d3 = 0.0D;
        } else {
            if (l != 0 || !OBlockRail.e(oworld.a(i, j - 1, k))) {
                return this.c.a(oiblocksource, oitemstack);
            }

            d3 = -1.0D;
        }

        OEntityMinecart oentityminecart = new OEntityMinecart(oworld, d0, d1 + d3, d2, ((OItemMinecart) oitemstack.b()).a);

        oworld.d((OEntity) oentityminecart);
        oitemstack.a(1);
        return oitemstack;
    }

    protected void a(OIBlockSource oiblocksource) {
        oiblocksource.k().f(1000, oiblocksource.d(), oiblocksource.e(), oiblocksource.f(), 0);
    }
}
