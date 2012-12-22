
public class OEntityExpBottle extends OEntityThrowable {

   public OEntityExpBottle(OWorld var1) {
      super(var1);
   }

   public OEntityExpBottle(OWorld var1, OEntityLiving var2) {
      super(var1, var2);
   }

   public OEntityExpBottle(OWorld var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
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

   protected void a(OMovingObjectPosition var1) {
      if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Projectile(this), var1.g == null ? null : var1.g.getEntity()) && !this.p.I) {
         this.p.f(2002, (int)Math.round(this.t), (int)Math.round(this.u), (int)Math.round(this.v), 0);
         int var2 = 3 + this.p.t.nextInt(5) + this.p.t.nextInt(5);

         while(var2 > 0) {
            int var3 = OEntityXPOrb.a(var2);
            var2 -= var3;
            this.p.d((OEntity)(new OEntityXPOrb(this.p, this.t, this.u, this.v, var3)));
         }

         this.x();
      }

   }
}
