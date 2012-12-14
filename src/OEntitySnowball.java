
public class OEntitySnowball extends OEntityThrowable {

   public OEntitySnowball(OWorld var1) {
      super(var1);
   }

   public OEntitySnowball(OWorld var1, OEntityLiving var2) {
      super(var1, var2);
   }

   public OEntitySnowball(OWorld var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   protected void a(OMovingObjectPosition var1) {
      if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Snowball(this), var1.g == null ? null : var1.g.getEntity())) {
          if(var1.g != null) {
             byte var2 = 0;
             if(var1.g instanceof OEntityBlaze) {
                var2 = 3;
             }
    
             var1.g.a(ODamageSource.a((OEntity)this, this.h()), var2);
          }
    
          for(int var3 = 0; var3 < 8; ++var3) {
             this.p.a("snowballpoof", this.t, this.u, this.v, 0.0D, 0.0D, 0.0D);
          }
    
          if(!this.p.J) {
             this.x();
          }
      }
   }
}
