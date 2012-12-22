public class OEntityEgg extends OEntityThrowable {

    public OEntityEgg(OWorld oworld) {
        super(oworld);
    }

    public OEntityEgg(OWorld oworld, OEntityLiving oentityliving) {
        super(oworld, oentityliving);
    }

    public OEntityEgg(OWorld oworld, double d0, double d1, double d2) {
        super(oworld, d0, d1, d2);
    }

    protected void a(OMovingObjectPosition omovingobjectposition) {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Egg(this), omovingobjectposition.g == null ? null : omovingobjectposition.g.getEntity())) {
            if (omovingobjectposition.g != null) {
                omovingobjectposition.g.a(ODamageSource.a((OEntity) this, this.h()), 0);
            }

        if (!this.p.I && this.aa.nextInt(8) == 0) {
                byte b0 = 1;

                if (this.aa.nextInt(32) == 0) {
                    b0 = 4;
                }

                for (int i = 0; i < b0; ++i) {
                    OEntityChicken oentitychicken = new OEntityChicken(this.p);

                    oentitychicken.a(-24000);
                    oentitychicken.b(this.t, this.u, this.v, this.z, 0.0F);
                    this.p.d((OEntity) oentitychicken);
                }
            }

            for (int j = 0; j < 8; ++j) {
                this.p.a("snowballpoof", this.t, this.u, this.v, 0.0D, 0.0D, 0.0D);
            }

        if (!this.p.I) {
                this.x();
            }
        }
    }
}
