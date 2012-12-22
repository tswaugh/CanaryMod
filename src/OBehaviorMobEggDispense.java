public class OBehaviorMobEggDispense extends OBehaviorDefaultDispenseItem {

    final OMinecraftServer b;

    public OBehaviorMobEggDispense(OMinecraftServer ominecraftserver) {
        this.b = ominecraftserver;
    }

    public OItemStack b(OIBlockSource oiblocksource, OItemStack oitemstack) {
        OEnumFacing oenumfacing = OEnumFacing.a(oiblocksource.h());
        double d0 = oiblocksource.a() + (double) oenumfacing.c();
        double d1 = (double) ((float) oiblocksource.e() + 0.2F);
        double d2 = oiblocksource.c() + (double) oenumfacing.e();

        OItemMonsterPlacer.a(oiblocksource.k(), oitemstack.j(), d0, d1, d2);
        oitemstack.a(1);
        return oitemstack;
    }

    protected void a(OIBlockSource oiblocksource) {
        oiblocksource.k().f(1002, oiblocksource.d(), oiblocksource.e(), oiblocksource.f(), 0);
    }
}
