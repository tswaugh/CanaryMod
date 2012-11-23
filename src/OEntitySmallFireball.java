
public class OEntitySmallFireball extends OEntityFireball {

   public OEntitySmallFireball(OWorld var1) {
      super(var1);
      this.a(0.3125F, 0.3125F);
   }

   public OEntitySmallFireball(OWorld var1, OEntityLiving var2, double var3, double var5, double var7) {
      super(var1, var2, var3, var5, var7);
      this.a(0.3125F, 0.3125F);
   }

   public OEntitySmallFireball(OWorld var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.a(0.3125F, 0.3125F);
   }

   protected void a(OMovingObjectPosition var1) {
      if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Projectile(this), var1.g == null ? null : var1.g.getEntity()) && !this.p.J) {
         if(var1.g != null) {
            if(!var1.g.F() && var1.g.a(ODamageSource.a((OEntityFireball)this, this.a), 5)) {
               var1.g.c(5);
            }
         } else {
            int var2 = var1.b;
            int var3 = var1.c;
            int var4 = var1.d;
            switch(var1.e) {
            case 0:
               --var3;
               break;
            case 1:
               ++var3;
               break;
            case 2:
               --var4;
               break;
            case 3:
               ++var4;
               break;
            case 4:
               --var2;
               break;
            case 5:
               ++var2;
            }

            if(this.p.c(var2, var3, var4)) {
               this.p.e(var2, var3, var4, OBlock.au.cm);
            }
         }

         this.x();
      }

   }

   public boolean L() {
      return false;
   }

   public boolean a(ODamageSource var1, int var2) {
      return false;
   }
}
