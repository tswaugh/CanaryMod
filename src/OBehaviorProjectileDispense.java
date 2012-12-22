public abstract class OBehaviorProjectileDispense extends OBehaviorDefaultDispenseItem {

    public OBehaviorProjectileDispense() {}

    public OItemStack b(OIBlockSource oiblocksource, OItemStack oitemstack) {
        OWorld oworld = oiblocksource.k();
        OIPosition oiposition = OBlockDispenser.a(oiblocksource);
        OEnumFacing oenumfacing = OEnumFacing.a(oiblocksource.h());
        OIProjectile oiprojectile = this.a(oworld, oiposition);

        oiprojectile.c((double) oenumfacing.c(), 0.10000000149011612D, (double) oenumfacing.e(), this.b(), this.a());
        oworld.d((OEntity) oiprojectile);
        oitemstack.a(1);
        return oitemstack;
    }

    protected void a(OIBlockSource oiblocksource) {
        oiblocksource.k().f(1002, oiblocksource.d(), oiblocksource.e(), oiblocksource.f(), 0);
    }

    protected abstract OIProjectile a(OWorld oworld, OIPosition oiposition);

    protected float a() {
        return 6.0F;
    }

    protected float b() {
        return 1.1F;
    }
}
