
public class OEntityEnderman extends OEntityMob {

   private static boolean[] f = new boolean[256];
   public boolean e = false;
   private int g = 0;
   private int h = 0;
   // CanaryMod Start
   Enderman entity = new Enderman(this);

   // CanaryMod End

   public OEntityEnderman(OWorld var1) {
      super(var1);
      this.be = "/mob/enderman.png";
      this.ca = 0.2F;
      this.d = 7;
      this.b(0.6F, 2.9F);
      this.aE = 1.0F;
   }

   public int e() {
      return 40;
   }

   protected void a() {
      super.a();
      this.aN.a(16, new Byte((byte)0));
      this.aN.a(17, new Byte((byte)0));
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      var1.a("carried", (short)this.M());
      var1.a("carriedData", (short)this.N());
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      this.b(var1.e("carried"));
      this.d(var1.e("carriedData"));
   }

   protected OEntity k() {
      OEntityPlayer var1 = this.X.b(this, 64.0D);
      if(var1 != null) {
         if(this.d(var1)) {
            if(this.h++ == 5) {
               this.h = 0;
               return var1;
            }
         } else {
            this.h = 0;
         }
      }

      return null;
   }

   public float a(float var1) {
      return super.a(var1);
   }

   private boolean d(OEntityPlayer var1) {
      OItemStack var2 = var1.k.b[3];
      if(var2 != null && var2.c == OBlock.ba.bM) {
         return false;
      } else {
         OVec3D var3 = var1.d(1.0F).b();
         OVec3D var4 = OVec3D.b(this.ab - var1.ab, this.al.b + (double)(this.aw / 2.0F) - (var1.ac + (double)var1.n()), this.ad - var1.ad);
         double var5 = var4.c();
         var4 = var4.b();
         double var7 = var3.a(var4);
         return var7 > 1.0D - 0.025D / var5?var1.i(this):false;
      }
   }

   public void f() {
      if(this.ab()) {
         this.a(ODamageSource.e, 1);
      }

      this.e = this.a != null;
      this.ca = this.a != null?6.5F:0.3F;
      int var1;
      if(!this.X.I) {
         int var2;
         int var3;
         int var4;
         if(this.M() == 0) {
            if(this.aH.nextInt(20) == 0) {
               var1 = OMathHelper.b(this.ab - 2.0D + this.aH.nextDouble() * 4.0D);
               var2 = OMathHelper.b(this.ac + this.aH.nextDouble() * 3.0D);
               var3 = OMathHelper.b(this.ad - 2.0D + this.aH.nextDouble() * 4.0D);
               var4 = this.X.a(var1, var2, var3);
               if(f[var4]) {
                   // CanaryMod onEndermanPickup
                   if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_PICKUP, entity, new Block(var4, var1, var2, var3, this.X.a(var1, var2, var3)))) {
                       this.b(this.X.a(var1, var2, var3));
                       this.d(this.X.e(var1, var2, var3));
                       this.X.e(var1, var2, var3, 0);
                   }
               }
            }
         } else if(this.aH.nextInt(2000) == 0) {
            var1 = OMathHelper.b(this.ab - 1.0D + this.aH.nextDouble() * 2.0D);
            var2 = OMathHelper.b(this.ac + this.aH.nextDouble() * 2.0D);
            var3 = OMathHelper.b(this.ad - 1.0D + this.aH.nextDouble() * 2.0D);
            var4 = this.X.a(var1, var2, var3);
            int var5 = this.X.a(var1, var2 - 1, var3);
            if(var4 == 0 && var5 > 0 && OBlock.k[var5].d()) {
                // CanaryMod onEndermanDrop
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_DROP, entity, new Block(var5, var1, var2, var3, this.X.a(var1, var2, var3)))) {
                	this.X.b(var1, var2, var3, this.M(), this.N());
                	this.b(0);
                }
            }
         }
      }

      for(var1 = 0; var1 < 2; ++var1) {
         this.X.a("portal", this.ab + (this.aH.nextDouble() - 0.5D) * (double)this.av, this.ac + this.aH.nextDouble() * (double)this.aw - 0.25D, this.ad + (this.aH.nextDouble() - 0.5D) * (double)this.av, (this.aH.nextDouble() - 0.5D) * 2.0D, -this.aH.nextDouble(), (this.aH.nextDouble() - 0.5D) * 2.0D);
      }

      if(this.X.e() && !this.X.I) {
         float var6 = this.a(1.0F);
         if(var6 > 0.5F && this.X.h(OMathHelper.b(this.ab), OMathHelper.b(this.ac), OMathHelper.b(this.ad)) && this.aH.nextFloat() * 30.0F < (var6 - 0.4F) * 2.0F) {
            this.a = null;
            this.H();
         }
      }

      if(this.ab()) {
         this.a = null;
         this.H();
      }

      this.bY = false;
      if(this.a != null) {
         this.a(this.a, 100.0F, 100.0F);
      }

      if(!this.X.I && this.af()) {
         if(this.a != null) {
            if(this.a instanceof OEntityPlayer && this.d((OEntityPlayer)this.a)) {
               this.bV = this.bW = 0.0F;
               this.ca = 0.0F;
               if(this.a.h(this) < 16.0D) {
                  this.H();
               }

               this.g = 0;
            } else if(this.a.h(this) > 256.0D && this.g++ >= 30 && this.j(this.a)) {
               this.g = 0;
            }
         } else {
            this.g = 0;
         }
      }

      super.f();
   }

   protected boolean H() {
      double var1 = this.ab + (this.aH.nextDouble() - 0.5D) * 64.0D;
      double var3 = this.ac + (double)(this.aH.nextInt(64) - 32);
      double var5 = this.ad + (this.aH.nextDouble() - 0.5D) * 64.0D;
      return this.d(var1, var3, var5);
   }

   protected boolean j(OEntity var1) {
      OVec3D var2 = OVec3D.b(this.ab - var1.ab, this.al.b + (double)(this.aw / 2.0F) - var1.ac + (double)var1.n(), this.ad - var1.ad);
      var2 = var2.b();
      double var3 = 16.0D;
      double var5 = this.ab + (this.aH.nextDouble() - 0.5D) * 8.0D - var2.a * var3;
      double var7 = this.ac + (double)(this.aH.nextInt(16) - 8) - var2.b * var3;
      double var9 = this.ad + (this.aH.nextDouble() - 0.5D) * 8.0D - var2.c * var3;
      return this.d(var5, var7, var9);
   }

   protected boolean d(double var1, double var3, double var5) {
      double var7 = this.ab;
      double var9 = this.ac;
      double var11 = this.ad;
      this.ab = var1;
      this.ac = var3;
      this.ad = var5;
      boolean var13 = false;
      int var14 = OMathHelper.b(this.ab);
      int var15 = OMathHelper.b(this.ac);
      int var16 = OMathHelper.b(this.ad);
      int var18;
      if(this.X.c(var14, var15, var16)) {
         boolean var17 = false;

         while(!var17 && var15 > 0) {
            var18 = this.X.a(var14, var15 - 1, var16);
            if(var18 != 0 && OBlock.k[var18].bZ.d()) {
               var17 = true;
            } else {
               --this.ac;
               --var15;
            }
         }

         if(var17) {
            this.e(this.ab, this.ac, this.ad);
            if(this.X.a((OEntity)this, this.al).size() == 0 && !this.X.c(this.al)) {
               var13 = true;
            }
         }
      }

      if(!var13) {
         this.e(var7, var9, var11);
         return false;
      } else {
         short var30 = 128;

         for(var18 = 0; var18 < var30; ++var18) {
            double var19 = (double)var18 / ((double)var30 - 1.0D);
            float var21 = (this.aH.nextFloat() - 0.5F) * 0.2F;
            float var22 = (this.aH.nextFloat() - 0.5F) * 0.2F;
            float var23 = (this.aH.nextFloat() - 0.5F) * 0.2F;
            double var24 = var7 + (this.ab - var7) * var19 + (this.aH.nextDouble() - 0.5D) * (double)this.av * 2.0D;
            double var26 = var9 + (this.ac - var9) * var19 + this.aH.nextDouble() * (double)this.aw;
            double var28 = var11 + (this.ad - var11) * var19 + (this.aH.nextDouble() - 0.5D) * (double)this.av * 2.0D;
            this.X.a("portal", var24, var26, var28, (double)var21, (double)var22, (double)var23);
         }

         this.X.a(var7, var9, var11, "mob.endermen.portal", 1.0F, 1.0F);
         this.X.a(this, "mob.endermen.portal", 1.0F, 1.0F);
         return true;
      }
   }

   protected String h() {
      return "mob.endermen.idle";
   }

   protected String i() {
      return "mob.endermen.hit";
   }

   protected String j() {
      return "mob.endermen.death";
   }

   protected int m() {
      return OItem.bm.bM;
   }

   protected void a(boolean var1, int var2) {
      int var3 = this.m();
      if(var3 > 0) {
         int var4 = this.aH.nextInt(2 + var2);

         for(int var5 = 0; var5 < var4; ++var5) {
            this.b(var3, 1);
         }
      }

   }

   public void b(int var1) {
      this.aN.b(16, Byte.valueOf((byte)(var1 & 255)));
   }

   public int M() {
      return this.aN.a(16);
   }

   public void d(int var1) {
      this.aN.b(17, Byte.valueOf((byte)(var1 & 255)));
   }

   public int N() {
      return this.aN.a(17);
   }

   public boolean a(ODamageSource var1, int var2) {
      if(var1 instanceof OEntityDamageSourceIndirect) {
         for(int var3 = 0; var3 < 64; ++var3) {
            if(this.H()) {
               return true;
            }
         }

         return false;
      } else {
         return super.a(var1, var2);
      }
   }
   
   // CanaryMod start
   public static boolean canHoldItem(int blockID) {
       return f[blockID];
   }

   public static void setHoldable(int blockID, boolean holdable) {
       f[blockID] = holdable;
   }

   public static boolean getHoldable(int blockID) {
       return f[blockID];
   }

   // CanaryMod end

   static {
      f[OBlock.u.bM] = true;
      f[OBlock.v.bM] = true;
      f[OBlock.E.bM] = true;
      f[OBlock.F.bM] = true;
      f[OBlock.ad.bM] = true;
      f[OBlock.ae.bM] = true;
      f[OBlock.af.bM] = true;
      f[OBlock.ag.bM] = true;
      f[OBlock.am.bM] = true;
      f[OBlock.aV.bM] = true;
      f[OBlock.aW.bM] = true;
      f[OBlock.ba.bM] = true;
      f[OBlock.br.bM] = true;
      f[OBlock.by.bM] = true;
   }
}
