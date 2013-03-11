public class OEntityEnderPearl extends OEntityThrowable {

    public OEntityEnderPearl(OWorld oworld) {
        super(oworld);
    }

    public OEntityEnderPearl(OWorld oworld, OEntityLiving oentityliving) {
        super(oworld, oentityliving);
    }

    protected void a(OMovingObjectPosition omovingobjectposition) {
        if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Projectile(this), omovingobjectposition.g == null ? null : omovingobjectposition.g.getEntity())) {
            if (omovingobjectposition.g != null) {
                omovingobjectposition.g.a(ODamageSource.a((OEntity) this, this.h()), 0);
            }
            // CanaryMod start - Fix enderpearl dupe bug
            Player p = null;

            if (this.h() instanceof OEntityPlayerMP) {
                p = new Player((OEntityPlayerMP) this.h());
            }
            if ((p != null) && !(etc.getServer().getPlayerList().contains(p))) {
                this.x(); // kill this entity
                return;
            }
            // CanaryMod end

            for (int i = 0; i < 32; ++i) {
            this.q.a("portal", this.u, this.v + this.ab.nextDouble() * 2.0D, this.w, this.ab.nextGaussian(), 0.0D, this.ab.nextGaussian());
            }

        if (!this.q.I) {
                if (this.h() != null && this.h() instanceof OEntityPlayerMP) {
                    OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.h();

                if (!oentityplayermp.a.b && oentityplayermp.q == this.q) {
                    this.h().a(this.u, this.v, this.w);
                    this.h().T = 0.0F;
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENDERPEARL, new BaseEntity(this), ((OEntityPlayerMP) h()).getPlayer(), 5)) {

                            this.h().a(ODamageSource.h, 5);
                        } //
                    }
                }

                this.w();
            }
        }
    }
}
