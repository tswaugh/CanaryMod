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

        OEntity ent = OItemMonsterPlacer.a(oiblocksource.k(), oitemstack.j(), d0, d1, d2, false); // CanaryMod: don't spawn entity yet
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) oiblocksource.j()), ent.entity)) {
            oiblocksource.k().d(ent); // CanaryMod: now we spawn the entity
            oitemstack.a(1);
        }
        return oitemstack;
    }
}
