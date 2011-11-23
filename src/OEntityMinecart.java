import java.util.List;

public class OEntityMinecart extends OEntity implements OIInventory, Container<OItemStack> {

   private OItemStack[] d;
   private int e;
   private boolean f;
   public int a;
   public double b;
   public double c;
   private static final int[][][] g = new int[][][]{{{0, 0, -1}, {0, 0, 1}}, {{-1, 0, 0}, {1, 0, 0}}, {{-1, -1, 0}, {1, 0, 0}}, {{-1, 0, 0}, {1, -1, 0}}, {{0, 0, -1}, {0, -1, 1}}, {{0, -1, -1}, {0, 0, 1}}, {{0, 0, 1}, {1, 0, 0}}, {{0, 0, 1}, {-1, 0, 0}}, {{0, 0, -1}, {-1, 0, 0}}, {{0, 0, -1}, {1, 0, 0}}};
   private int h;
   private double i;
   private double j;
   private double k;
   private double l;
   private double m;
   // CanaryMod start
   private String name = "Minecart";
   Minecart cart = new Minecart(this);

   // CanaryMod end

   public OEntityMinecart(OWorld var1) {
      super(var1);
      this.d = new OItemStack[36];
      this.e = 0;
      this.f = false;
      this.U = true;
      this.b(0.98F, 0.7F);
      this.au = this.aw / 2.0F;
   }

   protected boolean c() {
      return false;
   }

   protected void a() {
      this.aN.a(16, new Byte((byte)0));
      this.aN.a(17, new Integer(0));
      this.aN.a(18, new Integer(1));
      this.aN.a(19, new Integer(0));
   }

   public OAxisAlignedBB c(OEntity var1) {
      return var1.al;
   }

   public OAxisAlignedBB g() {
      return null;
   }

   public boolean f_() {
      return true;
   }

   public OEntityMinecart(OWorld var1, double var2, double var4, double var6, int var8) {
      this(var1);
      this.e(var2, var4 + (double)this.au, var6);
      this.ae = 0.0D;
      this.af = 0.0D;
      this.ag = 0.0D;
      this.Y = var2;
      this.Z = var4;
      this.aa = var6;
      this.a = var8;
      // CanaryMod: Creation of the cart
      manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, cart);
   }

   public double i_() {
      return (double)this.aw * 0.0D - 0.30000001192092896D;
   }

   public boolean a(ODamageSource var1, int var2) {
       // CanaryMod: Attack of the cart
       BaseEntity entity = null;
       if (var1 != null && var1.a() != null) {
           entity = new BaseEntity(var1.a());
       }
       if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, cart, entity, var2))
           return true;

      if(!this.X.I && !this.at) {
         this.d(-this.q());
         this.b(10);
         this.ad();
         this.e_(this.o() + var2 * 10);
         if(this.o() > 40) {
            if(this.V != null) {
               this.V.a((OEntity)this);
            }

            this.J();
            this.a(OItem.ay.bM, 1, 0.0F);
            if(this.a == 1) {
               OEntityMinecart var3 = this;

               for(int var4 = 0; var4 < var3.d(); ++var4) {
                  OItemStack var5 = var3.c(var4);
                  if(var5 != null) {
                     float var6 = this.aH.nextFloat() * 0.8F + 0.1F;
                     float var7 = this.aH.nextFloat() * 0.8F + 0.1F;
                     float var8 = this.aH.nextFloat() * 0.8F + 0.1F;

                     while(var5.a > 0) {
                        int var9 = this.aH.nextInt(21) + 10;
                        if(var9 > var5.a) {
                           var9 = var5.a;
                        }

                        var5.a -= var9;
                        OEntityItem var10 = new OEntityItem(this.X, this.ab + (double)var6, this.ac + (double)var7, this.ad + (double)var8, new OItemStack(var5.c, var9, var5.h()));
                        float var11 = 0.05F;
                        var10.ae = (double)((float)this.aH.nextGaussian() * var11);
                        var10.af = (double)((float)this.aH.nextGaussian() * var11 + 0.2F);
                        var10.ag = (double)((float)this.aH.nextGaussian() * var11);
                        this.X.b((OEntity)var10);
                     }
                  }
               }

               this.a(OBlock.au.bM, 1, 0.0F);
            } else if(this.a == 2) {
               this.a(OBlock.aB.bM, 1, 0.0F);
            }
         }

         return true;
      } else {
         return true;
      }
   }

   public boolean y_() {
      return !this.at;
   }

   public void J() {
       // CanaryMod: Destruction of the cart
       manager.callHook(PluginLoader.Hook.VEHICLE_DESTROYED, cart);

      for(int var1 = 0; var1 < this.d(); ++var1) {
         OItemStack var2 = this.c(var1);
         if(var2 != null) {
            float var3 = this.aH.nextFloat() * 0.8F + 0.1F;
            float var4 = this.aH.nextFloat() * 0.8F + 0.1F;
            float var5 = this.aH.nextFloat() * 0.8F + 0.1F;

            while(var2.a > 0) {
               int var6 = this.aH.nextInt(21) + 10;
               if(var6 > var2.a) {
                  var6 = var2.a;
               }

               var2.a -= var6;
               OEntityItem var7 = new OEntityItem(this.X, this.ab + (double)var3, this.ac + (double)var4, this.ad + (double)var5, new OItemStack(var2.c, var6, var2.h()));
               float var8 = 0.05F;
               var7.ae = (double)((float)this.aH.nextGaussian() * var8);
               var7.af = (double)((float)this.aH.nextGaussian() * var8 + 0.2F);
               var7.ag = (double)((float)this.aH.nextGaussian() * var8);
               this.X.b((OEntity)var7);
            }
         }
      }

      super.J();
   }

   public void b() {
       // CanaryMod: Update of the cart
       manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, cart);

      if(this.p() > 0) {
         this.b(this.p() - 1);
      }

      double prevX = ab;
      double prevY = ac;
      double prevZ = ad;
      
      if(this.o() > 0) {
         this.e_(this.o() - 1);
      }

      if(this.m() && this.aH.nextInt(4) == 0) {
         this.X.a("largesmoke", this.ab, this.ac + 0.8D, this.ad, 0.0D, 0.0D, 0.0D);
      }

      if(this.X.I) {
         if(this.h > 0) {
            double var1 = this.ab + (this.i - this.ab) / (double)this.h;
            double var3 = this.ac + (this.j - this.ac) / (double)this.h;
            double var5 = this.ad + (this.k - this.ad) / (double)this.h;

            double var7;
            for(var7 = this.l - (double)this.ah; var7 < -180.0D; var7 += 360.0D) {
               ;
            }

            while(var7 >= 180.0D) {
               var7 -= 360.0D;
            }

            this.ah = (float)((double)this.ah + var7 / (double)this.h);
            this.ai = (float)((double)this.ai + (this.m - (double)this.ai) / (double)this.h);
            --this.h;
            this.e(var1, var3, var5);
            this.c(this.ah, this.ai);
         } else {
            this.e(this.ab, this.ac, this.ad);
            this.c(this.ah, this.ai);
         }

      } else {
         this.Y = this.ab;
         this.Z = this.ac;
         this.aa = this.ad;
         this.af -= 0.03999999910593033D;
         int var9 = OMathHelper.b(this.ab);
         int var10 = OMathHelper.b(this.ac);
         int var11 = OMathHelper.b(this.ad);
         // CanaryMod: Change of the cart
         if ((int) var9 != (int) prevX || (int) var10 != (int) prevY || (int) var11 != (int) prevZ)
             manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, var9, var10, var11);
         
         if(OBlockRail.c(this.X, var9, var10 - 1, var11)) {
            --var10;
         }

         double var12 = 0.4D;
         double var14 = 0.0078125D;
         int var16 = this.X.a(var9, var10, var11);
         if(OBlockRail.c(var16)) {
            OVec3D var17 = this.c(this.ab, this.ac, this.ad);
            int var18 = this.X.e(var9, var10, var11);
            this.ac = (double)var10;
            boolean var19 = false;
            boolean var20 = false;
            if(var16 == OBlock.T.bM) {
               var19 = (var18 & 8) != 0;
               var20 = !var19;
            }

            if(((OBlockRail)OBlock.k[var16]).q()) {
               var18 &= 7;
            }

            if(var18 >= 2 && var18 <= 5) {
               this.ac = (double)(var10 + 1);
            }

            if(var18 == 2) {
               this.ae -= var14;
            }

            if(var18 == 3) {
               this.ae += var14;
            }

            if(var18 == 4) {
               this.ag += var14;
            }

            if(var18 == 5) {
               this.ag -= var14;
            }

            int[][] var21 = g[var18];
            double var22 = (double)(var21[1][0] - var21[0][0]);
            double var24 = (double)(var21[1][2] - var21[0][2]);
            double var26 = Math.sqrt(var22 * var22 + var24 * var24);
            double var28 = this.ae * var22 + this.ag * var24;
            if(var28 < 0.0D) {
               var22 = -var22;
               var24 = -var24;
            }

            double var30 = Math.sqrt(this.ae * this.ae + this.ag * this.ag);
            this.ae = var30 * var22 / var26;
            this.ag = var30 * var24 / var26;
            double var32;
            if(var20) {
               var32 = Math.sqrt(this.ae * this.ae + this.ag * this.ag);
               if(var32 < 0.03D) {
                  this.ae *= 0.0D;
                  this.af *= 0.0D;
                  this.ag *= 0.0D;
               } else {
                  this.ae *= 0.5D;
                  this.af *= 0.0D;
                  this.ag *= 0.5D;
               }
            }

            var32 = 0.0D;
            double var34 = (double)var9 + 0.5D + (double)var21[0][0] * 0.5D;
            double var36 = (double)var11 + 0.5D + (double)var21[0][2] * 0.5D;
            double var38 = (double)var9 + 0.5D + (double)var21[1][0] * 0.5D;
            double var40 = (double)var11 + 0.5D + (double)var21[1][2] * 0.5D;
            var22 = var38 - var34;
            var24 = var40 - var36;
            double var42;
            double var46;
            double var44;
            if(var22 == 0.0D) {
               this.ab = (double)var9 + 0.5D;
               var32 = this.ad - (double)var11;
            } else if(var24 == 0.0D) {
               this.ad = (double)var11 + 0.5D;
               var32 = this.ab - (double)var9;
            } else {
               var42 = this.ab - var34;
               var44 = this.ad - var36;
               var46 = (var42 * var22 + var44 * var24) * 2.0D;
               var32 = var46;
            }

            this.ab = var34 + var22 * var32;
            this.ad = var36 + var24 * var32;
            this.e(this.ab, this.ac + (double)this.au, this.ad);
            var42 = this.ae;
            var44 = this.ag;
            if(this.V != null) {
               var42 *= 0.75D;
               var44 *= 0.75D;
            }

            if(var42 < -var12) {
               var42 = -var12;
            }

            if(var42 > var12) {
               var42 = var12;
            }

            if(var44 < -var12) {
               var44 = -var12;
            }

            if(var44 > var12) {
               var44 = var12;
            }

            this.a(var42, 0.0D, var44);
            if(var21[0][1] != 0 && OMathHelper.b(this.ab) - var9 == var21[0][0] && OMathHelper.b(this.ad) - var11 == var21[0][2]) {
               this.e(this.ab, this.ac + (double)var21[0][1], this.ad);
            } else if(var21[1][1] != 0 && OMathHelper.b(this.ab) - var9 == var21[1][0] && OMathHelper.b(this.ad) - var11 == var21[1][2]) {
               this.e(this.ab, this.ac + (double)var21[1][1], this.ad);
            }

            if(this.V != null) {
               this.ae *= 0.996999979019165D;
               this.af *= 0.0D;
               this.ag *= 0.996999979019165D;
            } else {
               if(this.a == 2) {
                  var46 = (double)OMathHelper.a(this.b * this.b + this.c * this.c);
                  if(var46 > 0.01D) {
                     this.b /= var46;
                     this.c /= var46;
                     double var48 = 0.04D;
                     this.ae *= 0.800000011920929D;
                     this.af *= 0.0D;
                     this.ag *= 0.800000011920929D;
                     this.ae += this.b * var48;
                     this.ag += this.c * var48;
                  } else {
                     this.ae *= 0.8999999761581421D;
                     this.af *= 0.0D;
                     this.ag *= 0.8999999761581421D;
                  }
               }

               this.ae *= 0.9599999785423279D;
               this.af *= 0.0D;
               this.ag *= 0.9599999785423279D;
            }

            OVec3D var50 = this.c(this.ab, this.ac, this.ad);
            if(var50 != null && var17 != null) {
               double var51 = (var17.b - var50.b) * 0.05D;
               var30 = Math.sqrt(this.ae * this.ae + this.ag * this.ag);
               if(var30 > 0.0D) {
                  this.ae = this.ae / var30 * (var30 + var51);
                  this.ag = this.ag / var30 * (var30 + var51);
               }

               this.e(this.ab, var50.b, this.ad);
            }

            int var53 = OMathHelper.b(this.ab);
            int var54 = OMathHelper.b(this.ad);
            if(var53 != var9 || var54 != var11) {
               var30 = Math.sqrt(this.ae * this.ae + this.ag * this.ag);
               this.ae = var30 * (double)(var53 - var9);
               this.ag = var30 * (double)(var54 - var11);
            }

            double var55;
            if(this.a == 2) {
               var55 = (double)OMathHelper.a(this.b * this.b + this.c * this.c);
               if(var55 > 0.01D && this.ae * this.ae + this.ag * this.ag > 0.001D) {
                  this.b /= var55;
                  this.c /= var55;
                  if(this.b * this.ae + this.c * this.ag < 0.0D) {
                     this.b = 0.0D;
                     this.c = 0.0D;
                  } else {
                     this.b = this.ae;
                     this.c = this.ag;
                  }
               }
            }

            if(var19) {
               var55 = Math.sqrt(this.ae * this.ae + this.ag * this.ag);
               if(var55 > 0.01D) {
                  double var57 = 0.06D;
                  this.ae += this.ae / var55 * var57;
                  this.ag += this.ag / var55 * var57;
               } else if(var18 == 1) {
                  if(this.X.o(var9 - 1, var10, var11)) {
                     this.ae = 0.02D;
                  } else if(this.X.o(var9 + 1, var10, var11)) {
                     this.ae = -0.02D;
                  }
               } else if(var18 == 0) {
                  if(this.X.o(var9, var10, var11 - 1)) {
                     this.ag = 0.02D;
                  } else if(this.X.o(var9, var10, var11 + 1)) {
                     this.ag = -0.02D;
                  }
               }
            }
         } else {
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
            if(!this.am) {
               this.ae *= 0.949999988079071D;
               this.af *= 0.949999988079071D;
               this.ag *= 0.949999988079071D;
            }
         }

         this.ai = 0.0F;
         double var59 = this.Y - this.ab;
         double var61 = this.aa - this.ad;
         if(var59 * var59 + var61 * var61 > 0.001D) {
            this.ah = (float)(Math.atan2(var61, var59) * 180.0D / 3.141592653589793D);
            if(this.f) {
               this.ah += 180.0F;
            }
         }

         double var63;
         for(var63 = (double)(this.ah - this.aj); var63 >= 180.0D; var63 -= 360.0D) {
            ;
         }

         while(var63 < -180.0D) {
            var63 += 360.0D;
         }

         if(var63 < -170.0D || var63 >= 170.0D) {
            this.ah += 180.0F;
            this.f = !this.f;
         }

         this.c(this.ah, this.ai);
         List var65 = this.X.b((OEntity)this, this.al.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
         if(var65 != null && var65.size() > 0) {
            for(int var66 = 0; var66 < var65.size(); ++var66) {
               OEntity var67 = (OEntity)var65.get(var66);
               if(var67 != this.V && var67.f_() && var67 instanceof OEntityMinecart) {
                  var67.b_(this);
               }
            }
         }

         if(this.V != null && this.V.at) {
            if(this.V.W == this) {
               this.V.W = null;
            }

            this.V = null;
         }

         if(this.e > 0) {
            --this.e;
         }

         if(this.e <= 0) {
            this.b = this.c = 0.0D;
         }

         this.a(this.e > 0);
      }
   }
   
   // CanaryMod: Store last position, avoids Hook spaming
   private int lastX = 0;
   private int lastY = 0;
   private int lastZ = 0;

   public OVec3D c(double var1, double var3, double var5) {
      int var7 = OMathHelper.b(var1);
      int var8 = OMathHelper.b(var3);
      int var9 = OMathHelper.b(var5);
      // CanaryMod: Change of the cart
      if ((int) var7 != (int) lastX || (int) var8 != (int) lastY || (int) var9 != (int) lastZ) {
          manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, var7, var8, var9);
          lastX = var7;
          lastY = var8;
          lastZ = var9;
      }

      if(OBlockRail.c(this.X, var7, var8 - 1, var9)) {
         --var8;
      }

      int var10 = this.X.a(var7, var8, var9);
      if(OBlockRail.c(var10)) {
         int var11 = this.X.e(var7, var8, var9);
         var3 = (double)var8;
         if(((OBlockRail)OBlock.k[var10]).q()) {
            var11 &= 7;
         }

         if(var11 >= 2 && var11 <= 5) {
            var3 = (double)(var8 + 1);
         }

         int[][] var12 = g[var11];
         double var13 = 0.0D;
         double var15 = (double)var7 + 0.5D + (double)var12[0][0] * 0.5D;
         double var17 = (double)var8 + 0.5D + (double)var12[0][1] * 0.5D;
         double var19 = (double)var9 + 0.5D + (double)var12[0][2] * 0.5D;
         double var21 = (double)var7 + 0.5D + (double)var12[1][0] * 0.5D;
         double var23 = (double)var8 + 0.5D + (double)var12[1][1] * 0.5D;
         double var25 = (double)var9 + 0.5D + (double)var12[1][2] * 0.5D;
         double var27 = var21 - var15;
         double var29 = (var23 - var17) * 2.0D;
         double var31 = var25 - var19;
         if(var27 == 0.0D) {
            var1 = (double)var7 + 0.5D;
            var13 = var5 - (double)var9;
         } else if(var31 == 0.0D) {
            var5 = (double)var9 + 0.5D;
            var13 = var1 - (double)var7;
         } else {
            double var33 = var1 - var15;
            double var35 = var5 - var19;
            double var37 = (var33 * var27 + var35 * var31) * 2.0D;
            var13 = var37;
         }

         var1 = var15 + var27 * var13;
         var3 = var17 + var29 * var13;
         var5 = var19 + var31 * var13;
         if(var29 < 0.0D) {
            ++var3;
         }

         if(var29 > 0.0D) {
            var3 += 0.5D;
         }

         return OVec3D.b(var1, var3, var5);
      } else {
         return null;
      }
   }

   protected void a(ONBTTagCompound var1) {
      var1.a("Type", this.a);
      if(this.a == 2) {
         var1.a("PushX", this.b);
         var1.a("PushZ", this.c);
         var1.a("Fuel", (short)this.e);
      } else if(this.a == 1) {
         ONBTTagList var2 = new ONBTTagList();

         for(int var3 = 0; var3 < this.d.length; ++var3) {
            if(this.d[var3] != null) {
               ONBTTagCompound var4 = new ONBTTagCompound();
               var4.a("Slot", (byte)var3);
               this.d[var3].b(var4);
               var2.a((ONBTBase)var4);
            }
         }

         var1.a("Items", (ONBTBase)var2);
      }

   }

   protected void b(ONBTTagCompound var1) {
      this.a = var1.f("Type");
      if(this.a == 2) {
         this.b = var1.i("PushX");
         this.c = var1.i("PushZ");
         this.e = var1.e("Fuel");
      } else if(this.a == 1) {
         ONBTTagList var2 = var1.m("Items");
         this.d = new OItemStack[this.d()];

         for(int var3 = 0; var3 < var2.d(); ++var3) {
            ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
            int var5 = var4.d("Slot") & 255;
            if(var5 >= 0 && var5 < this.d.length) {
               this.d[var5] = OItemStack.a(var4);
            }
         }
      }

   }

   public void b_(OEntity var1) {
      if(!this.X.I) {
         if(var1 != this.V) {
             // CanaryMod: Collision of a cart
             if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_COLLISION, cart, var1.entity))
                 return;
             
            if(var1 instanceof OEntityLiving && !(var1 instanceof OEntityPlayer) && this.a == 0 && this.ae * this.ae + this.ag * this.ag > 0.01D && this.V == null && var1.W == null) {
               var1.a((OEntity)this);
            }

            double var2 = var1.ab - this.ab;
            double var4 = var1.ad - this.ad;
            double var6 = var2 * var2 + var4 * var4;
            if(var6 >= 9.999999747378752E-5D) {
               var6 = (double)OMathHelper.a(var6);
               var2 /= var6;
               var4 /= var6;
               double var8 = 1.0D / var6;
               if(var8 > 1.0D) {
                  var8 = 1.0D;
               }

               var2 *= var8;
               var4 *= var8;
               var2 *= 0.10000000149011612D;
               var4 *= 0.10000000149011612D;
               var2 *= (double)(1.0F - this.aG);
               var4 *= (double)(1.0F - this.aG);
               var2 *= 0.5D;
               var4 *= 0.5D;
               if(var1 instanceof OEntityMinecart) {
                  double var10 = var1.ab - this.ab;
                  double var12 = var1.ad - this.ad;
                  OVec3D var14 = OVec3D.b(var10, 0.0D, var12).b();
                  OVec3D var15 = OVec3D.b((double)OMathHelper.b(this.ah * 3.1415927F / 180.0F), 0.0D, (double)OMathHelper.a(this.ah * 3.1415927F / 180.0F)).b();
                  double var16 = Math.abs(var14.a(var15));
                  if(var16 < 0.800000011920929D) {
                     return;
                  }

                  double var18 = var1.ae + this.ae;
                  double var20 = var1.ag + this.ag;
                  if(((OEntityMinecart)var1).a == 2 && this.a != 2) {
                     this.ae *= 0.20000000298023224D;
                     this.ag *= 0.20000000298023224D;
                     this.b(var1.ae - var2, 0.0D, var1.ag - var4);
                     var1.ae *= 0.949999988079071D;
                     var1.ag *= 0.949999988079071D;
                  } else if(((OEntityMinecart)var1).a != 2 && this.a == 2) {
                     var1.ae *= 0.20000000298023224D;
                     var1.ag *= 0.20000000298023224D;
                     var1.b(this.ae + var2, 0.0D, this.ag + var4);
                     this.ae *= 0.949999988079071D;
                     this.ag *= 0.949999988079071D;
                  } else {
                     var18 /= 2.0D;
                     var20 /= 2.0D;
                     this.ae *= 0.20000000298023224D;
                     this.ag *= 0.20000000298023224D;
                     this.b(var18 - var2, 0.0D, var20 - var4);
                     var1.ae *= 0.20000000298023224D;
                     var1.ag *= 0.20000000298023224D;
                     var1.b(var18 + var2, 0.0D, var20 + var4);
                  }
               } else {
                  this.b(-var2, 0.0D, -var4);
                  var1.b(var2 / 4.0D, 0.0D, var4 / 4.0D);
               }
            }

         }
      }
   }

   public int d() {
      return 27;
   }

   public OItemStack c(int var1) {
      return this.d[var1];
   }

   public OItemStack a(int var1, int var2) {
      if(this.d[var1] != null) {
         OItemStack var3;
         if(this.d[var1].a <= var2) {
            var3 = this.d[var1];
            this.d[var1] = null;
            return var3;
         } else {
            var3 = this.d[var1].a(var2);
            if(this.d[var1].a == 0) {
               this.d[var1] = null;
            }

            return var3;
         }
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      this.d[var1] = var2;
      if(var2 != null && var2.a > this.f()) {
         var2.a = this.f();
      }

   }

   public String e() {
      return "Minecart";
   }

   public int f() {
      return 64;
   }

   public void i() {}

   public boolean b(OEntityPlayer var1) {
       // CanaryMod: Entering the cart
       manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, cart, var1.entity);

      if(this.a == 0) {
         if(this.V != null && this.V instanceof OEntityPlayer && this.V != var1) {
            return true;
         }

         if(!this.X.I) {
            var1.a((OEntity)this);
         }
      } else if(this.a == 1) {
         if(!this.X.I) {
            var1.a((OIInventory)this);
         }
      } else if(this.a == 2) {
         OItemStack var2 = var1.k.a();
         if(var2 != null && var2.c == OItem.l.bM) {
            if(--var2.a == 0) {
               var1.k.a(var1.k.c, (OItemStack)null);
            }

            this.e += 3600;
         }

         this.b = this.ab - var1.ab;
         this.c = this.ad - var1.ad;
      }

      return true;
   }

   public boolean a_(OEntityPlayer var1) {
      return this.at?false:var1.h(this) <= 64.0D;
   }

   protected boolean m() {
      return (this.aN.a(16) & 1) != 0;
   }

   protected void a(boolean var1) {
      if(var1) {
         this.aN.b(16, Byte.valueOf((byte)(this.aN.a(16) | 1)));
      } else {
         this.aN.b(16, Byte.valueOf((byte)(this.aN.a(16) & -2)));
      }

   }

   public void k() {}

   public void z_() {}

   public void e_(int var1) {
      this.aN.b(19, Integer.valueOf(var1));
   }

   public int o() {
      return this.aN.c(19);
   }

   public void b(int var1) {
      this.aN.b(17, Integer.valueOf(var1));
   }

   public int p() {
      return this.aN.c(17);
   }

   public void d(int var1) {
      this.aN.b(18, Integer.valueOf(var1));
   }

   public int q() {
      return this.aN.c(18);
   }

   public OItemStack[] getContents() {
       return d;
   }
   
   public void setContents(OItemStack[] values) {
       d = values;
   }
   
   public OItemStack getContentsAt(int index) {
       return c(index);
   }
   
   public void setContentsAt(int index, OItemStack value) {
       a(index, value);
   }
   
   public int getContentsSize() {
       return d();
   }
   
   public String getName() {
       return name;
   }
   
   public void setName(String value) {
       name = value;
   }
}
