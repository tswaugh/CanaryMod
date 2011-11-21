import java.util.List;

public class OEntityLightningBolt extends OEntityWeatherEffect {

   private int b;
   public long a = 0L;
   private int c;


   public OEntityLightningBolt(OWorld var1, double var2, double var4, double var6) {
      super(var1);
      this.c(var2, var4, var6, 0.0F, 0.0F);
      this.b = 2;
      this.a = this.aH.nextLong();
      this.c = this.aH.nextInt(3) + 1;
      if(var1.v >= 2 && var1.a(OMathHelper.b(var2), OMathHelper.b(var4), OMathHelper.b(var6), 10)) {
         int var8 = OMathHelper.b(var2);
         int var9 = OMathHelper.b(var4);
         int var10 = OMathHelper.b(var6);
         if(var1.a(var8, var9, var10) == 0 && OBlock.ar.d(var1, var8, var9, var10)) {
            var1.e(var8, var9, var10, OBlock.ar.bM);
         }

         for(var8 = 0; var8 < 4; ++var8) {
            var9 = OMathHelper.b(var2) + this.aH.nextInt(3) - 1;
            var10 = OMathHelper.b(var4) + this.aH.nextInt(3) - 1;
            int var11 = OMathHelper.b(var6) + this.aH.nextInt(3) - 1;
            if(var1.a(var9, var10, var11) == 0 && OBlock.ar.d(var1, var9, var10, var11)) {
                // CanaryMod: Ignite hook
                Block block = X.world.getBlockAt(var9, var10, var11);
                block.setStatus(5); // lightning
                if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null))
                	var1.e(var9, var10, var11, OBlock.ar.bM);
            }
         }
      }

   }

   public void b() {
      super.b();
      if(this.b == 2) {
         this.X.a(this.ab, this.ac, this.ad, "ambient.weather.thunder", 10000.0F, 0.8F + this.aH.nextFloat() * 0.2F);
         this.X.a(this.ab, this.ac, this.ad, "random.explode", 2.0F, 0.5F + this.aH.nextFloat() * 0.2F);
      }

      --this.b;
      if(this.b < 0) {
         if(this.c == 0) {
            this.J();
         } else if(this.b < -this.aH.nextInt(10)) {
            --this.c;
            this.b = 1;
            this.a = this.aH.nextLong();
            if(this.X.a(OMathHelper.b(this.ab), OMathHelper.b(this.ac), OMathHelper.b(this.ad), 10)) {
               int var1 = OMathHelper.b(this.ab);
               int var2 = OMathHelper.b(this.ac);
               int var3 = OMathHelper.b(this.ad);
               if(this.X.a(var1, var2, var3) == 0 && OBlock.ar.d(this.X, var1, var2, var3)) {
            	   Block block = X.world.getBlockAt(var1, var2, var3);
                   block.setStatus(5); // lightning
                   if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null))
                	   this.X.e(var1, var2, var3, OBlock.ar.bM);
               }
            }
         }
      }

      if(this.b >= 0) {
         double var4 = 3.0D;
         List var8 = this.X.b((OEntity)this, OAxisAlignedBB.b(this.ab - var4, this.ac - var4, this.ad - var4, this.ab + var4, this.ac + 6.0D + var4, this.ad + var4));

         for(int var6 = 0; var6 < var8.size(); ++var6) {
            OEntity var7 = (OEntity)var8.get(var6);
            var7.a(this);
         }

         this.X.s = 2;
      }

   }

   protected void a() {}

   protected void b(ONBTTagCompound var1) {}

   protected void a(ONBTTagCompound var1) {}
}
