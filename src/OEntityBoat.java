import java.util.List;

public class OEntityBoat extends OEntity {

   private int a;
   private double b;
   private double c;
   private double d;
   private double e;
   private double f;
   // CanaryMod Start
   Boat boat = new Boat(this);

   // CanaryMod end

   public OEntityBoat(OWorld var1) {
      super(var1);
      this.U = true;
      this.b(1.5F, 0.6F);
      this.au = this.aw / 2.0F;
   }

   protected boolean c() {
      return false;
   }

   protected void a() {
      this.aN.a(17, new Integer(0));
      this.aN.a(18, new Integer(1));
      this.aN.a(19, new Integer(0));
   }

   public OAxisAlignedBB c(OEntity var1) {
      return var1.al;
   }

   public OAxisAlignedBB g() {
      return this.al;
   }

   public boolean f_() {
      return true;
   }

   public OEntityBoat(OWorld var1, double var2, double var4, double var6) {
      this(var1);
      this.e(var2, var4 + (double)this.au, var6);
      this.ae = 0.0D;
      this.af = 0.0D;
      this.ag = 0.0D;
      this.Y = var2;
      this.Z = var4;
      this.aa = var6;
      
      // CanaryMod: Creation of the boat
      manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, boat);
   }

   public double i_() {
      return (double)this.aw * 0.0D - 0.30000001192092896D;
   }

   public boolean a(ODamageSource var1, int var2) {
       // CanaryMod: Attack of the boat
       BaseEntity entity = null;
       if (var1 != null && var1.a() != null) {
           entity = new BaseEntity(var1.a());
       }
       if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, entity, var2))
           return true;

      if(!this.X.I && !this.at) {
         this.d(-this.m());
         this.c(10);
         this.b(this.i() + var2 * 10);
         this.ad();
         if(this.i() > 40) {
            if(this.V != null) {
               this.V.a((OEntity)this);
            }

            int var3;
            for(var3 = 0; var3 < 3; ++var3) {
               this.a(OBlock.x.bM, 1, 0.0F);
            }

            for(var3 = 0; var3 < 2; ++var3) {
               this.a(OItem.C.bM, 1, 0.0F);
            }

            this.J();
         }

         return true;
      } else {
         return true;
      }
   }

   public boolean y_() {
      return !this.at;
   }

   public void b() {
      super.b();
      // CanaryMod: Update of the boat
      manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);
      
      double prevX = ab;
      double prevY = ac;
      double prevZ = ad;
      
      if(this.k() > 0) {
         this.c(this.k() - 1);
      }

      if(this.i() > 0) {
         this.b(this.i() - 1);
      }

      this.Y = this.ab;
      this.Z = this.ac;
      this.aa = this.ad;
      byte var1 = 5;
      double var2 = 0.0D;

      for(int var4 = 0; var4 < var1; ++var4) {
         double var5 = this.al.b + (this.al.e - this.al.b) * (double)(var4 + 0) / (double)var1 - 0.125D;
         double var7 = this.al.b + (this.al.e - this.al.b) * (double)(var4 + 1) / (double)var1 - 0.125D;
         OAxisAlignedBB var9 = OAxisAlignedBB.b(this.al.a, var5, this.al.c, this.al.d, var7, this.al.f);
         if(this.X.b(var9, OMaterial.g)) {
            var2 += 1.0D / (double)var1;
         }
      }

      double var10 = Math.sqrt(this.ae * this.ae + this.ag * this.ag);
      double var12;
      double var14;
      if(var10 > 0.15D) {
         var12 = Math.cos((double)this.ah * 3.141592653589793D / 180.0D);
         var14 = Math.sin((double)this.ah * 3.141592653589793D / 180.0D);

         for(int var16 = 0; (double)var16 < 1.0D + var10 * 60.0D; ++var16) {
            double var17 = (double)(this.aH.nextFloat() * 2.0F - 1.0F);
            double var19 = (double)(this.aH.nextInt(2) * 2 - 1) * 0.7D;
            double var21;
            double var23;
            if(this.aH.nextBoolean()) {
               var21 = this.ab - var12 * var17 * 0.8D + var14 * var19;
               var23 = this.ad - var14 * var17 * 0.8D - var12 * var19;
               this.X.a("splash", var21, this.ac - 0.125D, var23, this.ae, this.af, this.ag);
            } else {
               var21 = this.ab + var12 + var14 * var17 * 0.7D;
               var23 = this.ad + var14 - var12 * var17 * 0.7D;
               this.X.a("splash", var21, this.ac - 0.125D, var23, this.ae, this.af, this.ag);
            }
         }
      }

      double var25;
      double var27;
      if(this.X.I) {
         if(this.a > 0) {
            var12 = this.ab + (this.b - this.ab) / (double)this.a;
            var14 = this.ac + (this.c - this.ac) / (double)this.a;
            var25 = this.ad + (this.d - this.ad) / (double)this.a;

            for(var27 = this.e - (double)this.ah; var27 < -180.0D; var27 += 360.0D) {
               ;
            }

            while(var27 >= 180.0D) {
               var27 -= 360.0D;
            }

            this.ah = (float)((double)this.ah + var27 / (double)this.a);
            this.ai = (float)((double)this.ai + (this.f - (double)this.ai) / (double)this.a);
            --this.a;
            this.e(var12, var14, var25);
            this.c(this.ah, this.ai);
         } else {
            var12 = this.ab + this.ae;
            var14 = this.ac + this.af;
            var25 = this.ad + this.ag;
            this.e(var12, var14, var25);
            if(this.am) {
               this.ae *= 0.5D;
               this.af *= 0.5D;
               this.ag *= 0.5D;
            }

            this.ae *= 0.9900000095367432D;
            this.af *= 0.949999988079071D;
            this.ag *= 0.9900000095367432D;
         }

      } else {
         if(var2 < 1.0D) {
            var12 = var2 * 2.0D - 1.0D;
            this.af += 0.03999999910593033D * var12;
         } else {
            if(this.af < 0.0D) {
               this.af /= 2.0D;
            }

            this.af += 0.007000000216066837D;
         }

         if(this.V != null) {
            this.ae += this.V.ae * 0.2D;
            this.ag += this.V.ag * 0.2D;
         }

         var12 = 0.4D;
         if(this.ae < -var12) {
            this.ae = -var12;
         }

         if(this.ae > var12) {
            this.ae = var12;
         }

         if(this.ag < -var12) {
            this.ag = -var12;
         }

         if(this.ag > var12) {
            this.ag = var12;
         }

         if(this.am) {
            this.ae *= 0.5D;
            this.af *= 0.5D;
            this.ag *= 0.5D;
         }

         this.a(this.ae, this.af, this.ag);
         if(this.an && var10 > 0.2D) {
            if(!this.X.I) {
               this.J();

               int var29;
               for(var29 = 0; var29 < 3; ++var29) {
                  this.a(OBlock.x.bM, 1, 0.0F);
               }

               for(var29 = 0; var29 < 2; ++var29) {
                  this.a(OItem.C.bM, 1, 0.0F);
               }
            }
         } else {
            this.ae *= 0.9900000095367432D;
            this.af *= 0.949999988079071D;
            this.ag *= 0.9900000095367432D;
         }

         this.ai = 0.0F;
         var14 = (double)this.ah;
         var25 = this.Y - this.ab;
         var27 = this.aa - this.ad;
         if(var25 * var25 + var27 * var27 > 0.001D) {
            var14 = (double)((float)(Math.atan2(var27, var25) * 180.0D / 3.141592653589793D));
         }

         double var30;
         for(var30 = var14 - (double)this.ah; var30 >= 180.0D; var30 -= 360.0D) {
            ;
         }

         while(var30 < -180.0D) {
            var30 += 360.0D;
         }

         if(var30 > 20.0D) {
            var30 = 20.0D;
         }

         if(var30 < -20.0D) {
            var30 = -20.0D;
         }

         this.ah = (float)((double)this.ah + var30);
         this.c(this.ah, this.ai);
         
         // CanaryMod: Change of the boat
         if ((int) ab != (int) prevX || (int) ac != (int) prevY || (int) ad != (int) prevZ)
             manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, boat, (int) ab, (int) ac, (int) ad);
         
         List var32 = this.X.b((OEntity)this, this.al.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
         int var33;
         if(var32 != null && var32.size() > 0) {
            for(var33 = 0; var33 < var32.size(); ++var33) {
               OEntity var34 = (OEntity)var32.get(var33);
               if(var34 != this.V && var34.f_() && var34 instanceof OEntityBoat) {
                  var34.b_(this);
               }
            }
         }

         for(var33 = 0; var33 < 4; ++var33) {
            int var37 = OMathHelper.b(this.ab + ((double)(var33 % 2) - 0.5D) * 0.8D);
            int var35 = OMathHelper.b(this.ac);
            int var36 = OMathHelper.b(this.ad + ((double)(var33 / 2) - 0.5D) * 0.8D);
            if(this.X.a(var37, var35, var36) == OBlock.aS.bM) {
               this.X.e(var37, var35, var36, 0);
            }
         }

         if(this.V != null && this.V.at) {
            this.V = null;
         }

      }
   }

   public void ag() {
      if(this.V != null) {
         double var1 = Math.cos((double)this.ah * 3.141592653589793D / 180.0D) * 0.4D;
         double var3 = Math.sin((double)this.ah * 3.141592653589793D / 180.0D) * 0.4D;
         this.V.e(this.ab + var1, this.ac + this.i_() + this.V.I(), this.ad + var3);
      }
   }

   protected void a(ONBTTagCompound var1) {}

   protected void b(ONBTTagCompound var1) {}

   public boolean b(OEntityPlayer var1) {
       // CanaryMod: Entering the boat
       manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, boat, var1.entity);

      if(this.V != null && this.V instanceof OEntityPlayer && this.V != var1) {
         return true;
      } else {
         if(!this.X.I) {
            var1.a((OEntity)this);
         }

         return true;
      }
   }

   public void b(int var1) {
      this.aN.b(19, Integer.valueOf(var1));
   }

   public int i() {
      return this.aN.c(19);
   }

   public void c(int var1) {
      this.aN.b(17, Integer.valueOf(var1));
   }

   public int k() {
      return this.aN.c(17);
   }

   public void d(int var1) {
      this.aN.b(18, Integer.valueOf(var1));
   }

   public int m() {
      return this.aN.c(18);
   }
}
