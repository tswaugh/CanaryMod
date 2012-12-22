public class OEntityExpBottle extends OEntityThrowable {

    public OEntityExpBottle(OWorld oworld) {
        super(oworld);
    }

    public OEntityExpBottle(OWorld oworld, OEntityLiving oentityliving) {
        super(oworld, oentityliving);
    }

    public OEntityExpBottle(OWorld oworld, double d0, double d1, double d2) {
        super(oworld, d0, d1, d2);
    }

    protected float g() {
        return 0.07F;
    }

    protected float c() {
        return 0.7F;
    }

    protected float d() {
        return -20.0F;
    }

    protected void a(OMovingObjectPosition omovingobjectposition) {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Projectile(this), omovingobjectposition.g == null ? null : omovingobjectposition.g.getEntity()) && !this.p.I) {
            this.p.f(2002, (int) Math.round(this.t), (int) Math.round(this.u), (int) Math.round(this.v), 0);
            int i = 3 + this.p.t.nextInt(5) + this.p.t.nextInt(5);

            while (i > 0) {
                int j = OEntityXPOrb.a(i);

                i -= j;
                this.p.d((OEntity) (new OEntityXPOrb(this.p, this.t, this.u, this.v, j)));
            }

            this.x();
        }
    }
}
