
public class OEntityLargeFireball extends OEntityFireball {

   public OEntityLargeFireball(OWorld var1) {
      super(var1);
   }

   public OEntityLargeFireball(OWorld var1, OEntityLiving var2, double var3, double var5, double var7) {
      super(var1, var2, var3, var5, var7);
   }

   protected void a(OMovingObjectPosition var1) {
      if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Projectile(this), var1.g == null ? null : var1.g.getEntity()) && !this.p.I) {
         if(var1.g != null) {
            var1.g.a(ODamageSource.a((OEntityFireball)this, this.a), 6);
         }

         this.p.a((OEntity)null, this.t, this.u, this.v, 1.0F, true, this.p.L().b("mobGriefing"));
         this.x();
      }

   }
}
