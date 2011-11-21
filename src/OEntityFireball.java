import java.util.List;

public class OEntityFireball extends OEntity {

   private int e = -1;
   private int f = -1;
   private int g = -1;
   private int h = 0;
   private boolean i = false;
   public OEntityLiving a;
   private int j;
   private int k = 0;
   public double b;
   public double c;
   public double d;


   public OEntityFireball(OWorld var1) {
      super(var1);
      this.b(1.0F, 1.0F);
   }

   protected void a() {}

   public OEntityFireball(OWorld var1, OEntityLiving var2, double var3, double var5, double var7) {
      super(var1);
      this.a = var2;
      this.b(1.0F, 1.0F);
      this.c(var2.ab, var2.ac, var2.ad, var2.ah, var2.ai);
      this.e(this.ab, this.ac, this.ad);
      this.au = 0.0F;
      this.ae = this.af = this.ag = 0.0D;
      var3 += this.aH.nextGaussian() * 0.4D;
      var5 += this.aH.nextGaussian() * 0.4D;
      var7 += this.aH.nextGaussian() * 0.4D;
      double var9 = (double)OMathHelper.a(var3 * var3 + var5 * var5 + var7 * var7);
      this.b = var3 / var9 * 0.1D;
      this.c = var5 / var9 * 0.1D;
      this.d = var7 / var9 * 0.1D;
   }

   public void b() {
      super.b();
      this.h(1);
      if(!this.X.I && (this.a == null || this.a.at)) {
         this.J();
      }

      if(this.i) {
         int var1 = this.X.a(this.e, this.f, this.g);
         if(var1 == this.h) {
            ++this.j;
            if(this.j == 1200) {
               this.J();
            }

            return;
         }

         this.i = false;
         this.ae *= (double)(this.aH.nextFloat() * 0.2F);
         this.af *= (double)(this.aH.nextFloat() * 0.2F);
         this.ag *= (double)(this.aH.nextFloat() * 0.2F);
         this.j = 0;
         this.k = 0;
      } else {
         ++this.k;
      }

      OVec3D var15 = OVec3D.b(this.ab, this.ac, this.ad);
      OVec3D var2 = OVec3D.b(this.ab + this.ae, this.ac + this.af, this.ad + this.ag);
      OMovingObjectPosition var3 = this.X.a(var15, var2);
      var15 = OVec3D.b(this.ab, this.ac, this.ad);
      var2 = OVec3D.b(this.ab + this.ae, this.ac + this.af, this.ad + this.ag);
      if(var3 != null) {
         var2 = OVec3D.b(var3.f.a, var3.f.b, var3.f.c);
      }

      OEntity var4 = null;
      List var5 = this.X.b((OEntity)this, this.al.a(this.ae, this.af, this.ag).b(1.0D, 1.0D, 1.0D));
      double var6 = 0.0D;

      for(int var8 = 0; var8 < var5.size(); ++var8) {
         OEntity var9 = (OEntity)var5.get(var8);
         if(var9.y_() && (!var9.c_(this.a) || this.k >= 25)) {
            float var10 = 0.3F;
            OAxisAlignedBB var11 = var9.al.b((double)var10, (double)var10, (double)var10);
            OMovingObjectPosition var12 = var11.a(var15, var2);
            if(var12 != null) {
               double var13 = var15.b(var12.f);
               if(var13 < var6 || var6 == 0.0D) {
                  var4 = var9;
                  var6 = var13;
               }
            }
         }
      }

      if(var4 != null) {
         var3 = new OMovingObjectPosition(var4);
      }

      if(var3 != null) {
         this.a(var3);
      }

      this.ab += this.ae;
      this.ac += this.af;
      this.ad += this.ag;
      float var16 = OMathHelper.a(this.ae * this.ae + this.ag * this.ag);
      this.ah = (float)(Math.atan2(this.ae, this.ag) * 180.0D / 3.1415927410125732D);

      for(this.ai = (float)(Math.atan2(this.af, (double)var16) * 180.0D / 3.1415927410125732D); this.ai - this.ak < -180.0F; this.ak -= 360.0F) {
         ;
      }

      while(this.ai - this.ak >= 180.0F) {
         this.ak += 360.0F;
      }

      while(this.ah - this.aj < -180.0F) {
         this.aj -= 360.0F;
      }

      while(this.ah - this.aj >= 180.0F) {
         this.aj += 360.0F;
      }

      this.ai = this.ak + (this.ai - this.ak) * 0.2F;
      this.ah = this.aj + (this.ah - this.aj) * 0.2F;
      float var17 = 0.95F;
      if(this.l_()) {
         for(int var19 = 0; var19 < 4; ++var19) {
            float var18 = 0.25F;
            this.X.a("bubble", this.ab - this.ae * (double)var18, this.ac - this.af * (double)var18, this.ad - this.ag * (double)var18, this.ae, this.af, this.ag);
         }

         var17 = 0.8F;
      }

      this.ae += this.b;
      this.af += this.c;
      this.ag += this.d;
      this.ae *= (double)var17;
      this.af *= (double)var17;
      this.ag *= (double)var17;
      this.X.a("smoke", this.ab, this.ac + 0.5D, this.ad, 0.0D, 0.0D, 0.0D);
      this.e(this.ab, this.ac, this.ad);
   }

   protected void a(OMovingObjectPosition var1) {
      if(!this.X.I) {
         if(var1.g != null && var1.g.a(ODamageSource.a(this, this.a), 4)) {
            ;
         }

         this.X.a((OEntity)null, this.ab, this.ac, this.ad, 1.0F, true);
         this.J();
      }

   }

   public void a(ONBTTagCompound var1) {
      var1.a("xTile", (short)this.e);
      var1.a("yTile", (short)this.f);
      var1.a("zTile", (short)this.g);
      var1.a("inTile", (byte)this.h);
      var1.a("inGround", (byte)(this.i?1:0));
   }

   public void b(ONBTTagCompound var1) {
      this.e = var1.e("xTile");
      this.f = var1.e("yTile");
      this.g = var1.e("zTile");
      this.h = var1.d("inTile") & 255;
      this.i = var1.d("inGround") == 1;
   }

   public boolean y_() {
      return true;
   }

   public float j_() {
      return 1.0F;
   }

   public boolean a(ODamageSource var1, int var2) {
      this.ad();
      if(var1.a() != null) {
         OVec3D var3 = var1.a().ah();
         if(var3 != null) {
            this.ae = var3.a;
            this.af = var3.b;
            this.ag = var3.c;
            this.b = this.ae * 0.1D;
            this.c = this.af * 0.1D;
            this.d = this.ag * 0.1D;
         }

         if(var1.a() instanceof OEntityLiving) {
            this.a = (OEntityLiving)var1.a();
         }

         return true;
      } else {
         return false;
      }
   }

   public float a(float var1) {
      return 1.0F;
   }
}
