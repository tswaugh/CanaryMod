
public class OFoodStats {

   // CanaryMod: made fields public
   public int a = 20;
   public float b = 5.0F;
   public float c;
   public int d = 0;
   private int e = 20;

   public OFoodStats() {
      super();
   }

   public void a(int var1, float var2) {
      this.a = Math.min(var1 + this.a, 20);
      this.b = Math.min(this.b + (float)var1 * var2 * 2.0F, (float)this.a);
   }

   public void a(OItemFood var1) {
      this.a(var1.k(), var1.l());
   }

   public void a(OEntityPlayer var1) {
      int var2 = var1.bb.v;
      this.e = this.a;
      if(this.c > 4.0F) {
         this.c -= 4.0F;
         if(this.b > 0.0F) {
            // CanaryMod: Calls onFoodExhaustionChange
            float newLevel = Math.max(this.b - 1.0F, 0.0F);
            float oldLevel = this.b;
            this.b = (Float) etc.getLoader().callHook(PluginLoader.Hook.FOODEXHAUSTION_CHANGE, ((OEntityPlayerMP)var1).getPlayer(), oldLevel, newLevel);
         } else if(var2 > 0) {
            // CanaryMod: Calls onFoodLevelChange
            int newLevel = Math.max(this.a -1, 0);
            int oldLevel = this.a;
            this.a = (Integer) etc.getLoader().callHook(PluginLoader.Hook.FOODLEVEL_CHANGE, ((OEntityPlayerMP)var1).getPlayer(), oldLevel, newLevel);
         }
      }

      if(this.a >= 18 && var1.W()) {
         ++this.d;
         if(this.d >= 80) {
            var1.c(1);
            this.d = 0;
         }
      } else if(this.a <= 0) {
         ++this.d;
         if(this.d >= 80) {
            if(var1.an > 10 || var2 >= 3 || var1.an > 1 && var2 >= 2) {
                // CanaryMod: DAMAGE From starvation
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.STARVATION, null, ((OEntityPlayerMP) var1).getPlayer(), 1))
                    var1.a(ODamageSource.f, 1);
            }

            this.d = 0;
         }
      } else {
         this.d = 0;
      }

   }

   public void a(ONBTTagCompound var1) {
      if(var1.b("foodLevel")) {
         this.a = var1.e("foodLevel");
         this.d = var1.e("foodTickTimer");
         this.b = var1.g("foodSaturationLevel");
         this.c = var1.g("foodExhaustionLevel");
      }

   }

   public void b(ONBTTagCompound var1) {
      var1.a("foodLevel", this.a);
      var1.a("foodTickTimer", this.d);
      var1.a("foodSaturationLevel", this.b);
      var1.a("foodExhaustionLevel", this.c);
   }

   public int a() {
      return this.a;
   }

   public boolean b() {
      return this.a < 20;
   }

   public void a(float var1) {
      this.c = Math.min(this.c + var1, 40.0F);
   }

   public float c() {
      return this.b;
   }
}
