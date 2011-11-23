import java.util.List;
import java.util.Random;

public abstract class OEntity {

   private static int a = 0;
   public int S;
   public double T;
   public boolean U;
   public OEntity V;
   public OEntity W;
   public OWorld X;
   public double Y;
   public double Z;
   public double aa;
   public double ab;
   public double ac;
   public double ad;
   public double ae;
   public double af;
   public double ag;
   public float ah;
   public float ai;
   public float aj;
   public float ak;
   public final OAxisAlignedBB al;
   public boolean am;
   public boolean an;
   public boolean ao;
   public boolean ap;
   public boolean aq;
   protected boolean ar;
   public boolean as;
   public boolean at;
   public float au;
   public float av;
   public float aw;
   public float ax;
   public float ay;
   public float az;
   private int b;
   public double aA;
   public double aB;
   public double aC;
   public float aD;
   public float aE;
   public boolean aF;
   public float aG;
   protected Random aH;
   public int aI;
   public int aJ;
   public int c;
   protected boolean aK;
   public int aL;
   private boolean d;
   protected boolean aM;
   protected ODataWatcher aN;
   private double e;
   private double f;
   public boolean aO;
   public int aP;
   public int aQ;
   public int aR;
   public boolean aS;
   public boolean aT;
   // CanaryMod Start
   BaseEntity entity = new BaseEntity(this);
   public static PluginLoader manager = etc.getLoader();

   // CanaryMod end

   public OEntity(OWorld var1) {
      super();
      this.S = a++;
      this.T = 1.0D;
      this.U = false;
      this.al = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      this.am = false;
      this.ap = false;
      this.aq = false;
      this.as = true;
      this.at = false;
      this.au = 0.0F;
      this.av = 0.6F;
      this.aw = 1.8F;
      this.ax = 0.0F;
      this.ay = 0.0F;
      this.az = 0.0F;
      this.b = 1;
      this.aD = 0.0F;
      this.aE = 0.0F;
      this.aF = false;
      this.aG = 0.0F;
      this.aH = new Random();
      this.aI = 0;
      this.aJ = 1;
      this.c = 0;
      this.aK = false;
      this.aL = 0;
      this.d = true;
      this.aM = false;
      this.aN = new ODataWatcher();
      this.aO = false;
      this.X = var1;
      this.e(0.0D, 0.0D, 0.0D);
      this.aN.a(0, Byte.valueOf((byte)0));
      this.aN.a(1, Short.valueOf((short)300));
      this.a();
   }

   protected abstract void a();

   public ODataWatcher V() {
      return this.aN;
   }

   public boolean equals(Object var1) {
      return var1 instanceof OEntity?((OEntity)var1).S == this.S:false;
   }

   public int hashCode() {
      return this.S;
   }

   public void J() {
      this.at = true;
   }

   protected void b(float var1, float var2) {
      this.av = var1;
      this.aw = var2;
   }

   protected void c(float var1, float var2) {
      this.ah = var1 % 360.0F;
      this.ai = var2 % 360.0F;
   }

   public void e(double var1, double var3, double var5) {
      this.ab = var1;
      this.ac = var3;
      this.ad = var5;
      float var7 = this.av / 2.0F;
      float var8 = this.aw;
      this.al.c(var1 - (double)var7, var3 - (double)this.au + (double)this.aD, var5 - (double)var7, var1 + (double)var7, var3 - (double)this.au + (double)this.aD + (double)var8, var5 + (double)var7);
   }

   public void b() {
      this.W();
   }

   public void W() {
      OProfiler.a("entityBaseTick");
      if(this.W != null && this.W.at) {
         this.W = null;
      }

      ++this.aI;
      this.ax = this.ay;
      this.Y = this.ab;
      this.Z = this.ac;
      this.aa = this.ad;
      this.ak = this.ai;
      this.aj = this.ah;
      int var3;
      if(this.ak()) {
         int var1 = OMathHelper.b(this.ab);
         int var2 = OMathHelper.b(this.ac - 0.20000000298023224D - (double)this.au);
         var3 = OMathHelper.b(this.ad);
         int var4 = this.X.a(var1, var2, var3);
         if(var4 > 0) {
            this.X.a("tilecrack_" + var4, this.ab + ((double)this.aH.nextFloat() - 0.5D) * (double)this.av, this.al.b + 0.1D, this.ad + ((double)this.aH.nextFloat() - 0.5D) * (double)this.av, -this.ae * 4.0D, 1.5D, -this.ag * 4.0D);
         }
      }

      if(this.e_()) {
         if(!this.aK && !this.d) {
            float var6 = OMathHelper.a(this.ae * this.ae * 0.20000000298023224D + this.af * this.af + this.ag * this.ag * 0.20000000298023224D) * 0.2F;
            if(var6 > 1.0F) {
               var6 = 1.0F;
            }

            this.X.a(this, "random.splash", var6, 1.0F + (this.aH.nextFloat() - this.aH.nextFloat()) * 0.4F);
            float var7 = (float)OMathHelper.b(this.al.b);

            float var5;
            float var8;
            for(var3 = 0; (float)var3 < 1.0F + this.av * 20.0F; ++var3) {
               var8 = (this.aH.nextFloat() * 2.0F - 1.0F) * this.av;
               var5 = (this.aH.nextFloat() * 2.0F - 1.0F) * this.av;
               this.X.a("bubble", this.ab + (double)var8, (double)(var7 + 1.0F), this.ad + (double)var5, this.ae, this.af - (double)(this.aH.nextFloat() * 0.2F), this.ag);
            }

            for(var3 = 0; (float)var3 < 1.0F + this.av * 20.0F; ++var3) {
               var8 = (this.aH.nextFloat() * 2.0F - 1.0F) * this.av;
               var5 = (this.aH.nextFloat() * 2.0F - 1.0F) * this.av;
               this.X.a("splash", this.ab + (double)var8, (double)(var7 + 1.0F), this.ad + (double)var5, this.ae, this.af, this.ag);
            }
         }

         this.az = 0.0F;
         this.aK = true;
         this.c = 0;
      } else {
         this.aK = false;
      }

      if(this.X.I) {
         this.c = 0;
      } else if(this.c > 0) {
         if(this.aM) {
            this.c -= 4;
            if(this.c < 0) {
               this.c = 0;
            }
         } else {
            if(this.c % 20 == 0) {
                // CanaryMod Damage hook: Periodic burn damage
                if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE_TICK, null, entity, 1))
                	this.a(ODamageSource.b, 1);
            }

            --this.c;
         }
      }

      if(this.ac()) {
         this.X();
         this.az *= 0.5F;
      }

      if(this.ac < -64.0D) {
         this.Z();
      }

      if(!this.X.I) {
         this.a(0, this.c > 0);
         this.a(2, this.W != null);
      }

      this.d = false;
      OProfiler.a();
   }

   protected void X() {
      if(!this.aM) {
    	  //CanaryMod Damage hook: Lava
    	  if (this instanceof OEntityLiving)
    		  if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, entity, 4))
    			  return;
    	  this.a(ODamageSource.c, 4);
    	  this.h(15);
      }

   }

   public void h(int var1) {
      int var2 = var1 * 20;
      if(this.c < var2) {
         this.c = var2;
      }

   }

   public void Y() {
      this.c = 0;
   }

   protected void Z() {
      this.J();
   }

   public boolean f(double var1, double var3, double var5) {
      OAxisAlignedBB var7 = this.al.c(var1, var3, var5);
      List var8 = this.X.a(this, var7);
      return var8.size() > 0?false:!this.X.c(var7);
   }

   public void a(double var1, double var3, double var5) {
      if(this.aF) {
         this.al.d(var1, var3, var5);
         this.ab = (this.al.a + this.al.d) / 2.0D;
         this.ac = this.al.b + (double)this.au - (double)this.aD;
         this.ad = (this.al.c + this.al.f) / 2.0D;
      } else {
         OProfiler.a("move");
         this.aD *= 0.4F;
         double var7 = this.ab;
         double var9 = this.ad;
         if(this.ar) {
            this.ar = false;
            var1 *= 0.25D;
            var3 *= 0.05000000074505806D;
            var5 *= 0.25D;
            this.ae = 0.0D;
            this.af = 0.0D;
            this.ag = 0.0D;
         }

         double var11 = var1;
         double var13 = var3;
         double var15 = var5;
         OAxisAlignedBB var17 = this.al.b();
         boolean var18 = this.am && this.aj();
         if(var18) {
            double var19;
            for(var19 = 0.05D; var1 != 0.0D && this.X.a(this, this.al.c(var1, -1.0D, 0.0D)).size() == 0; var11 = var1) {
               if(var1 < var19 && var1 >= -var19) {
                  var1 = 0.0D;
               } else if(var1 > 0.0D) {
                  var1 -= var19;
               } else {
                  var1 += var19;
               }
            }

            for(; var5 != 0.0D && this.X.a(this, this.al.c(0.0D, -1.0D, var5)).size() == 0; var15 = var5) {
               if(var5 < var19 && var5 >= -var19) {
                  var5 = 0.0D;
               } else if(var5 > 0.0D) {
                  var5 -= var19;
               } else {
                  var5 += var19;
               }
            }
         }

         List var21 = this.X.a(this, this.al.a(var1, var3, var5));

         for(int var22 = 0; var22 < var21.size(); ++var22) {
            var3 = ((OAxisAlignedBB)var21.get(var22)).b(this.al, var3);
         }

         this.al.d(0.0D, var3, 0.0D);
         if(!this.as && var13 != var3) {
            var5 = 0.0D;
            var3 = 0.0D;
            var1 = 0.0D;
         }

         boolean var42 = this.am || var13 != var3 && var13 < 0.0D;

         int var23;
         for(var23 = 0; var23 < var21.size(); ++var23) {
            var1 = ((OAxisAlignedBB)var21.get(var23)).a(this.al, var1);
         }

         this.al.d(var1, 0.0D, 0.0D);
         if(!this.as && var11 != var1) {
            var5 = 0.0D;
            var3 = 0.0D;
            var1 = 0.0D;
         }

         for(var23 = 0; var23 < var21.size(); ++var23) {
            var5 = ((OAxisAlignedBB)var21.get(var23)).c(this.al, var5);
         }

         this.al.d(0.0D, 0.0D, var5);
         if(!this.as && var15 != var5) {
            var5 = 0.0D;
            var3 = 0.0D;
            var1 = 0.0D;
         }

         double var24;
         double var26;
         int var31;
         if(this.aE > 0.0F && var42 && (var18 || this.aD < 0.05F) && (var11 != var1 || var15 != var5)) {
            var24 = var1;
            var26 = var3;
            double var28 = var5;
            var1 = var11;
            var3 = (double)this.aE;
            var5 = var15;
            OAxisAlignedBB var30 = this.al.b();
            this.al.b(var17);
            var21 = this.X.a(this, this.al.a(var11, var3, var15));

            for(var31 = 0; var31 < var21.size(); ++var31) {
               var3 = ((OAxisAlignedBB)var21.get(var31)).b(this.al, var3);
            }

            this.al.d(0.0D, var3, 0.0D);
            if(!this.as && var13 != var3) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            }

            for(var31 = 0; var31 < var21.size(); ++var31) {
               var1 = ((OAxisAlignedBB)var21.get(var31)).a(this.al, var1);
            }

            this.al.d(var1, 0.0D, 0.0D);
            if(!this.as && var11 != var1) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            }

            for(var31 = 0; var31 < var21.size(); ++var31) {
               var5 = ((OAxisAlignedBB)var21.get(var31)).c(this.al, var5);
            }

            this.al.d(0.0D, 0.0D, var5);
            if(!this.as && var15 != var5) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            }

            if(!this.as && var13 != var3) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            } else {
               var3 = (double)(-this.aE);

               for(var31 = 0; var31 < var21.size(); ++var31) {
                  var3 = ((OAxisAlignedBB)var21.get(var31)).b(this.al, var3);
               }

               this.al.d(0.0D, var3, 0.0D);
            }

            if(var24 * var24 + var28 * var28 >= var1 * var1 + var5 * var5) {
               var1 = var24;
               var3 = var26;
               var5 = var28;
               this.al.b(var30);
            } else {
               double var32 = this.al.b - (double)((int)this.al.b);
               if(var32 > 0.0D) {
                  this.aD = (float)((double)this.aD + var32 + 0.01D);
               }
            }
         }

         OProfiler.a();
         OProfiler.a("rest");
         this.ab = (this.al.a + this.al.d) / 2.0D;
         this.ac = this.al.b + (double)this.au - (double)this.aD;
         this.ad = (this.al.c + this.al.f) / 2.0D;
         this.an = var11 != var1 || var15 != var5;
         this.ao = var13 != var3;
         this.am = var13 != var3 && var13 < 0.0D;
         this.ap = this.an || this.ao;
         this.a(var3, this.am);
         if(var11 != var1) {
            this.ae = 0.0D;
         }

         if(var13 != var3) {
            this.af = 0.0D;
         }

         if(var15 != var5) {
            this.ag = 0.0D;
         }

         var24 = this.ab - var7;
         var26 = this.ad - var9;
         int var34;
         int var35;
         int var43;
         if(this.c() && !var18 && this.W == null) {
            this.ay = (float)((double)this.ay + (double)OMathHelper.a(var24 * var24 + var26 * var26) * 0.6D);
            var34 = OMathHelper.b(this.ab);
            var35 = OMathHelper.b(this.ac - 0.20000000298023224D - (double)this.au);
            var43 = OMathHelper.b(this.ad);
            var31 = this.X.a(var34, var35, var43);
            if(var31 == 0 && this.X.a(var34, var35 - 1, var43) == OBlock.aZ.bM) {
               var31 = this.X.a(var34, var35 - 1, var43);
            }

            if(this.ay > (float)this.b && var31 > 0) {
               this.b = (int)this.ay + 1;
               this.a(var34, var35, var43, var31);
               OBlock.k[var31].b(this.X, var34, var35, var43, this);
            }
         }

         var34 = OMathHelper.b(this.al.a + 0.001D);
         var35 = OMathHelper.b(this.al.b + 0.001D);
         var43 = OMathHelper.b(this.al.c + 0.001D);
         var31 = OMathHelper.b(this.al.d - 0.001D);
         int var36 = OMathHelper.b(this.al.e - 0.001D);
         int var37 = OMathHelper.b(this.al.f - 0.001D);
         if(this.X.a(var34, var35, var43, var31, var36, var37)) {
            for(int var38 = var34; var38 <= var31; ++var38) {
               for(int var39 = var35; var39 <= var36; ++var39) {
                  for(int var40 = var43; var40 <= var37; ++var40) {
                     int var41 = this.X.a(var38, var39, var40);
                     if(var41 > 0) {
                        OBlock.k[var41].a(this.X, var38, var39, var40, this);
                     }
                  }
               }
            }
         }

         boolean var44 = this.ab();
         if(this.X.d(this.al.e(0.001D, 0.001D, 0.001D))) {
            this.a(1);
            if(!var44) {
               ++this.c;
               if(this.c == 0) {
                  this.h(8);
               }
            }
         } else if(this.c <= 0) {
            this.c = -this.aJ;
         }

         if(var44 && this.c > 0) {
            this.X.a(this, "random.fizz", 0.7F, 1.6F + (this.aH.nextFloat() - this.aH.nextFloat()) * 0.4F);
            this.c = -this.aJ;
         }

         OProfiler.a();
      }
   }

   protected void a(int var1, int var2, int var3, int var4) {
      OStepSound var5 = OBlock.k[var4].bX;
      if(this.X.a(var1, var2 + 1, var3) == OBlock.aS.bM) {
         var5 = OBlock.aS.bX;
         this.X.a(this, var5.c(), var5.a() * 0.15F, var5.b());
      } else if(!OBlock.k[var4].bZ.a()) {
         this.X.a(this, var5.c(), var5.a() * 0.15F, var5.b());
      }

   }

   protected boolean c() {
      return true;
   }

   protected void a(double var1, boolean var3) {
      if(var3) {
         if(this.az > 0.0F) {
            this.b(this.az);
            this.az = 0.0F;
         }
      } else if(var1 < 0.0D) {
         this.az = (float)((double)this.az - var1);
      }

   }

   public OAxisAlignedBB g() {
      return null;
   }

   protected void a(int var1) {
      if(!this.aM) {
          // CanaryMod Damage Hook: Fire
          if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE, null, entity, var1))
              this.a(ODamageSource.a, var1);
      }

   }

   public final boolean aa() {
      return this.aM;
   }

   protected void b(float var1) {
      if(this.V != null) {
         this.V.b(var1);
      }

   }

   public boolean ab() {
      return this.aK || this.X.v(OMathHelper.b(this.ab), OMathHelper.b(this.ac), OMathHelper.b(this.ad));
   }

   public boolean l_() {
      return this.aK;
   }

   public boolean e_() {
      return this.X.a(this.al.b(0.0D, -0.4000000059604645D, 0.0D).e(0.001D, 0.001D, 0.001D), OMaterial.g, this);
   }

   public boolean a(OMaterial var1) {
      double var2 = this.ac + (double)this.n();
      int var4 = OMathHelper.b(this.ab);
      int var5 = OMathHelper.d((float)OMathHelper.b(var2));
      int var6 = OMathHelper.b(this.ad);
      int var7 = this.X.a(var4, var5, var6);
      if(var7 != 0 && OBlock.k[var7].bZ == var1) {
         float var8 = OBlockFluid.c(this.X.e(var4, var5, var6)) - 0.11111111F;
         float var9 = (float)(var5 + 1) - var8;
         return var2 < (double)var9;
      } else {
         return false;
      }
   }

   public float n() {
      return 0.0F;
   }

   public boolean ac() {
      return this.X.a(this.al.b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), OMaterial.h);
   }

   public void a(float var1, float var2, float var3) {
      float var4 = OMathHelper.c(var1 * var1 + var2 * var2);
      if(var4 >= 0.01F) {
         if(var4 < 1.0F) {
            var4 = 1.0F;
         }

         var4 = var3 / var4;
         var1 *= var4;
         var2 *= var4;
         float var5 = OMathHelper.a(this.ah * 3.1415927F / 180.0F);
         float var6 = OMathHelper.b(this.ah * 3.1415927F / 180.0F);
         this.ae += (double)(var1 * var6 - var2 * var5);
         this.ag += (double)(var2 * var6 + var1 * var5);
      }
   }

   public float a(float var1) {
      int var2 = OMathHelper.b(this.ab);
      int var3 = OMathHelper.b(this.ad);
      if(this.X.c(var2, this.X.c / 2, var3)) {
         double var4 = (this.al.e - this.al.b) * 0.66D;
         int var6 = OMathHelper.b(this.ac - (double)this.au + var4);
         return this.X.k(var2, var6, var3);
      } else {
         return 0.0F;
      }
   }

   public void a(OWorld var1) {
      this.X = var1;
   }

   public void b(double var1, double var3, double var5, float var7, float var8) {
      this.Y = this.ab = var1;
      this.Z = this.ac = var3;
      this.aa = this.ad = var5;
      this.aj = this.ah = var7;
      this.ak = this.ai = var8;
      this.aD = 0.0F;
      double var9 = (double)(this.aj - var7);
      if(var9 < -180.0D) {
         this.aj += 360.0F;
      }

      if(var9 >= 180.0D) {
         this.aj -= 360.0F;
      }

      this.e(this.ab, this.ac, this.ad);
      this.c(var7, var8);
   }

   public void c(double var1, double var3, double var5, float var7, float var8) {
      this.aA = this.Y = this.ab = var1;
      this.aB = this.Z = this.ac = var3 + (double)this.au;
      this.aC = this.aa = this.ad = var5;
      this.ah = var7;
      this.ai = var8;
      this.e(this.ab, this.ac, this.ad);
   }

   public float g(OEntity var1) {
      float var2 = (float)(this.ab - var1.ab);
      float var3 = (float)(this.ac - var1.ac);
      float var4 = (float)(this.ad - var1.ad);
      return OMathHelper.c(var2 * var2 + var3 * var3 + var4 * var4);
   }

   public double g(double var1, double var3, double var5) {
      double var7 = this.ab - var1;
      double var9 = this.ac - var3;
      double var11 = this.ad - var5;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   public double h(double var1, double var3, double var5) {
      double var7 = this.ab - var1;
      double var9 = this.ac - var3;
      double var11 = this.ad - var5;
      return (double)OMathHelper.a(var7 * var7 + var9 * var9 + var11 * var11);
   }

   public double h(OEntity var1) {
      double var2 = this.ab - var1.ab;
      double var4 = this.ac - var1.ac;
      double var6 = this.ad - var1.ad;
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public void a(OEntityPlayer var1) {}

   public void b_(OEntity var1) {
      if(var1.V != this && var1.W != this) {
         double var2 = var1.ab - this.ab;
         double var4 = var1.ad - this.ad;
         double var6 = OMathHelper.a(var2, var4);
         if(var6 >= 0.009999999776482582D) {
            var6 = (double)OMathHelper.a(var6);
            var2 /= var6;
            var4 /= var6;
            double var8 = 1.0D / var6;
            if(var8 > 1.0D) {
               var8 = 1.0D;
            }

            var2 *= var8;
            var4 *= var8;
            var2 *= 0.05000000074505806D;
            var4 *= 0.05000000074505806D;
            var2 *= (double)(1.0F - this.aG);
            var4 *= (double)(1.0F - this.aG);
            this.b(-var2, 0.0D, -var4);
            var1.b(var2, 0.0D, var4);
         }

      }
   }

   public void b(double var1, double var3, double var5) {
      this.ae += var1;
      this.af += var3;
      this.ag += var5;
      this.aT = true;
   }

   protected void ad() {
      this.aq = true;
   }

   public boolean a(ODamageSource var1, int var2) {
      this.ad();
      return false;
   }

   public boolean y_() {
      return false;
   }

   public boolean f_() {
      return false;
   }

   public void b(OEntity var1, int var2) {}

   public boolean c(ONBTTagCompound var1) {
      String var2 = this.ae();
      if(!this.at && var2 != null) {
         var1.a("id", var2);
         this.d(var1);
         return true;
      } else {
         return false;
      }
   }

   public void d(ONBTTagCompound var1) {
      var1.a("Pos", (ONBTBase)this.a(new double[]{this.ab, this.ac + (double)this.aD, this.ad}));
      var1.a("Motion", (ONBTBase)this.a(new double[]{this.ae, this.af, this.ag}));
      var1.a("Rotation", (ONBTBase)this.a(new float[]{this.ah, this.ai}));
      var1.a("FallDistance", this.az);
      var1.a("Fire", (short)this.c);
      var1.a("Air", (short)this.al());
      var1.a("OnGround", this.am);
      this.a(var1);
   }

   public void e(ONBTTagCompound var1) {
      ONBTTagList var2 = var1.m("Pos");
      ONBTTagList var3 = var1.m("Motion");
      ONBTTagList var4 = var1.m("Rotation");
      this.ae = ((ONBTTagDouble)var3.a(0)).a;
      this.af = ((ONBTTagDouble)var3.a(1)).a;
      this.ag = ((ONBTTagDouble)var3.a(2)).a;
      if(Math.abs(this.ae) > 10.0D) {
         this.ae = 0.0D;
      }

      if(Math.abs(this.af) > 10.0D) {
         this.af = 0.0D;
      }

      if(Math.abs(this.ag) > 10.0D) {
         this.ag = 0.0D;
      }

      this.Y = this.aA = this.ab = ((ONBTTagDouble)var2.a(0)).a;
      this.Z = this.aB = this.ac = ((ONBTTagDouble)var2.a(1)).a;
      this.aa = this.aC = this.ad = ((ONBTTagDouble)var2.a(2)).a;
      this.aj = this.ah = ((ONBTTagFloat)var4.a(0)).a;
      this.ak = this.ai = ((ONBTTagFloat)var4.a(1)).a;
      this.az = var1.h("FallDistance");
      this.c = var1.e("Fire");
      this.j(var1.e("Air"));
      this.am = var1.n("OnGround");
      this.e(this.ab, this.ac, this.ad);
      this.c(this.ah, this.ai);
      this.b(var1);
   }

   protected final String ae() {
      return OEntityList.b(this);
   }

   protected abstract void b(ONBTTagCompound var1);

   protected abstract void a(ONBTTagCompound var1);

   protected ONBTTagList a(double ... var1) {
      ONBTTagList var2 = new ONBTTagList();
      double[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         double var6 = var3[var5];
         var2.a((ONBTBase)(new ONBTTagDouble((String)null, var6)));
      }

      return var2;
   }

   protected ONBTTagList a(float ... var1) {
      ONBTTagList var2 = new ONBTTagList();
      float[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         float var6 = var3[var5];
         var2.a((ONBTBase)(new ONBTTagFloat((String)null, var6)));
      }

      return var2;
   }

   public OEntityItem b(int var1, int var2) {
      return this.a(var1, var2, 0.0F);
   }

   public OEntityItem a(int var1, int var2, float var3) {
      return this.a(new OItemStack(var1, var2, 0), var3);
   }

   public OEntityItem a(OItemStack var1, float var2) {
      OEntityItem var3 = new OEntityItem(this.X, this.ab, this.ac + (double)var2, this.ad, var1);
      var3.c = 10;
      this.X.b((OEntity)var3);
      return var3;
   }

   public boolean af() {
      return !this.at;
   }

   public boolean K() {
      for(int var1 = 0; var1 < 8; ++var1) {
         float var2 = ((float)((var1 >> 0) % 2) - 0.5F) * this.av * 0.8F;
         float var3 = ((float)((var1 >> 1) % 2) - 0.5F) * 0.1F;
         float var4 = ((float)((var1 >> 2) % 2) - 0.5F) * this.av * 0.8F;
         int var5 = OMathHelper.b(this.ab + (double)var2);
         int var6 = OMathHelper.b(this.ac + (double)this.n() + (double)var3);
         int var7 = OMathHelper.b(this.ad + (double)var4);
         if(this.X.o(var5, var6, var7)) {
            return true;
         }
      }

      return false;
   }

   public boolean b(OEntityPlayer var1) {
      return false;
   }

   public OAxisAlignedBB c(OEntity var1) {
      return null;
   }

   public void C() {
      if(this.W.at) {
         this.W = null;
      } else {
         this.ae = 0.0D;
         this.af = 0.0D;
         this.ag = 0.0D;
         this.b();
         if(this.W != null) {
            this.W.ag();
            this.f += (double)(this.W.ah - this.W.aj);

            for(this.e += (double)(this.W.ai - this.W.ak); this.f >= 180.0D; this.f -= 360.0D) {
               ;
            }

            while(this.f < -180.0D) {
               this.f += 360.0D;
            }

            while(this.e >= 180.0D) {
               this.e -= 360.0D;
            }

            while(this.e < -180.0D) {
               this.e += 360.0D;
            }

            double var1 = this.f * 0.5D;
            double var3 = this.e * 0.5D;
            float var5 = 10.0F;
            if(var1 > (double)var5) {
               var1 = (double)var5;
            }

            if(var1 < (double)(-var5)) {
               var1 = (double)(-var5);
            }

            if(var3 > (double)var5) {
               var3 = (double)var5;
            }

            if(var3 < (double)(-var5)) {
               var3 = (double)(-var5);
            }

            this.f -= var1;
            this.e -= var3;
            this.ah = (float)((double)this.ah + var1);
            this.ai = (float)((double)this.ai + var3);
         }
      }
   }

   public void ag() {
      this.V.e(this.ab, this.ac + this.i_() + this.V.I(), this.ad);
   }

   public double I() {
      return (double)this.au;
   }

   public double i_() {
      return (double)this.aw * 0.75D;
   }

   public void a(OEntity var1) {
      this.e = 0.0D;
      this.f = 0.0D;
      if(var1 == null) {
         if(this.W != null) {
            this.c(this.W.ab, this.W.al.b + (double)this.W.aw, this.W.ad, this.ah, this.ai);
            this.W.V = null;
         }

         this.W = null;
      } else if(this.W == var1) {
         this.W.V = null;
         this.W = null;
         this.c(var1.ab, var1.al.b + (double)var1.aw, var1.ad, this.ah, this.ai);
      } else {
         if(this.W != null) {
            this.W.V = null;
         }

         if(var1.V != null) {
            var1.V.W = null;
         }

         this.W = var1;
         var1.V = this;
      }
   }

   public float j_() {
      return 0.1F;
   }

   public OVec3D ah() {
      return null;
   }

   public void P() {}

   public OItemStack[] l() {
      return null;
   }

   public boolean ai() {
      return this.c > 0 || this.i(0);
   }

   public boolean aj() {
      return this.i(1);
   }

   public void c(boolean var1) {
      this.a(1, var1);
   }

   public boolean ak() {
      return this.i(3);
   }

   public void d(boolean var1) {
      this.a(3, var1);
   }

   public void e(boolean var1) {
      this.a(4, var1);
   }

   protected boolean i(int var1) {
      return (this.aN.a(0) & 1 << var1) != 0;
   }

   protected void a(int var1, boolean var2) {
      byte var3 = this.aN.a(0);
      if(var2) {
         this.aN.b(0, Byte.valueOf((byte)(var3 | 1 << var1)));
      } else {
         this.aN.b(0, Byte.valueOf((byte)(var3 & ~(1 << var1))));
      }

   }

   public int al() {
      return this.aN.b(1);
   }

   public void j(int var1) {
      this.aN.b(1, Short.valueOf((short)var1));
   }

   public void a(OEntityLightningBolt var1) {
	   // CanaryMod Damage Hook: Lightning
       if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LIGHTNING, null, entity, 5))
           return;
		this.a(5);
		++this.c;
		if(this.c == 0) {
		   this.h(8);
		}
   }

   public void a(OEntityLiving var1) {}

   protected boolean i(double var1, double var3, double var5) {
      int var7 = OMathHelper.b(var1);
      int var8 = OMathHelper.b(var3);
      int var9 = OMathHelper.b(var5);
      double var10 = var1 - (double)var7;
      double var12 = var3 - (double)var8;
      double var14 = var5 - (double)var9;
      if(this.X.o(var7, var8, var9)) {
         boolean var16 = !this.X.o(var7 - 1, var8, var9);
         boolean var17 = !this.X.o(var7 + 1, var8, var9);
         boolean var18 = !this.X.o(var7, var8 - 1, var9);
         boolean var19 = !this.X.o(var7, var8 + 1, var9);
         boolean var20 = !this.X.o(var7, var8, var9 - 1);
         boolean var21 = !this.X.o(var7, var8, var9 + 1);
         byte var22 = -1;
         double var23 = 9999.0D;
         if(var16 && var10 < var23) {
            var23 = var10;
            var22 = 0;
         }

         if(var17 && 1.0D - var10 < var23) {
            var23 = 1.0D - var10;
            var22 = 1;
         }

         if(var18 && var12 < var23) {
            var23 = var12;
            var22 = 2;
         }

         if(var19 && 1.0D - var12 < var23) {
            var23 = 1.0D - var12;
            var22 = 3;
         }

         if(var20 && var14 < var23) {
            var23 = var14;
            var22 = 4;
         }

         if(var21 && 1.0D - var14 < var23) {
            var23 = 1.0D - var14;
            var22 = 5;
         }

         float var25 = this.aH.nextFloat() * 0.2F + 0.1F;
         if(var22 == 0) {
            this.ae = (double)(-var25);
         }

         if(var22 == 1) {
            this.ae = (double)var25;
         }

         if(var22 == 2) {
            this.af = (double)(-var25);
         }

         if(var22 == 3) {
            this.af = (double)var25;
         }

         if(var22 == 4) {
            this.ag = (double)(-var25);
         }

         if(var22 == 5) {
            this.ag = (double)var25;
         }

         return true;
      } else {
         return false;
      }
   }

   public void A_() {
      this.ar = true;
   }

   public String U() {
      String var1 = OEntityList.b(this);
      if(var1 == null) {
         var1 = "generic";
      }

      return OStatCollector.a("entity." + var1 + ".name");
   }

   public OEntity[] am() {
      return null;
   }

   public boolean c_(OEntity var1) {
      return this == var1;
   }

}
