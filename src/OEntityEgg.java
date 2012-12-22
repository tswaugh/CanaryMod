
public class OEntityEgg extends OEntityThrowable {

   public OEntityEgg(OWorld var1) {
      super(var1);
   }

   public OEntityEgg(OWorld var1, OEntityLiving var2) {
      super(var1, var2);
   }

   public OEntityEgg(OWorld var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   protected void a(OMovingObjectPosition var1) {
      if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Egg(this), var1.g == null ? null : var1.g.getEntity())) {
          if(var1.g != null) {
             var1.g.a(ODamageSource.a((OEntity)this, this.h()), 0);
          }

          if(!this.p.I && this.aa.nextInt(8) == 0) {
             byte var2 = 1;
             if(this.aa.nextInt(32) == 0) {
                var2 = 4;
             }

             for(int var3 = 0; var3 < var2; ++var3) {
                OEntityChicken var4 = new OEntityChicken(this.p);
                var4.a(-24000);
                var4.b(this.t, this.u, this.v, this.z, 0.0F);
                this.p.d((OEntity)var4);
             }
          }

          for(int var5 = 0; var5 < 8; ++var5) {
             this.p.a("snowballpoof", this.t, this.u, this.v, 0.0D, 0.0D, 0.0D);
          }

          if(!this.p.I) {
             this.x();
          }
      }
   }
}
