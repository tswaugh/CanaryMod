import java.util.List;

public abstract class OEntityThrowable extends OEntity implements OIProjectile {

   private int c = -1;
   private int d = -1;
   private int e = -1;
   private int f = 0;
   protected boolean a = false;
   public int b = 0;
   private OEntityLiving g;
   private String h = null;
   private int i;
   private int j = 0;


   public OEntityThrowable(OWorld var1) {
      super(var1);
      this.a(0.25F, 0.25F);
   }

   protected void a() {}

   public OEntityThrowable(OWorld var1, OEntityLiving var2) {
      super(var1);
      this.g = var2;
      this.a(0.25F, 0.25F);
      this.b(var2.t, var2.u + (double)var2.e(), var2.v, var2.z, var2.A);
      this.t -= (double)(OMathHelper.b(this.z / 180.0F * 3.1415927F) * 0.16F);
      this.u -= 0.10000000149011612D;
      this.v -= (double)(OMathHelper.a(this.z / 180.0F * 3.1415927F) * 0.16F);
      this.b(this.t, this.u, this.v);
      this.M = 0.0F;
      float var3 = 0.4F;
      this.w = (double)(-OMathHelper.a(this.z / 180.0F * 3.1415927F) * OMathHelper.b(this.A / 180.0F * 3.1415927F) * var3);
      this.y = (double)(OMathHelper.b(this.z / 180.0F * 3.1415927F) * OMathHelper.b(this.A / 180.0F * 3.1415927F) * var3);
      this.x = (double)(-OMathHelper.a((this.A + this.d()) / 180.0F * 3.1415927F) * var3);
      this.c(this.w, this.x, this.y, this.c(), 1.0F);
   }

   public OEntityThrowable(OWorld var1, double var2, double var4, double var6) {
      super(var1);
      this.i = 0;
      this.a(0.25F, 0.25F);
      this.b(var2, var4, var6);
      this.M = 0.0F;
   }

   protected float c() {
      return 1.5F;
   }

   protected float d() {
      return 0.0F;
   }

   public void c(double var1, double var3, double var5, float var7, float var8) {
      float var9 = OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5);
      var1 /= (double)var9;
      var3 /= (double)var9;
      var5 /= (double)var9;
      var1 += this.aa.nextGaussian() * 0.007499999832361937D * (double)var8;
      var3 += this.aa.nextGaussian() * 0.007499999832361937D * (double)var8;
      var5 += this.aa.nextGaussian() * 0.007499999832361937D * (double)var8;
      var1 *= (double)var7;
      var3 *= (double)var7;
      var5 *= (double)var7;
      this.w = var1;
      this.x = var3;
      this.y = var5;
      float var10 = OMathHelper.a(var1 * var1 + var5 * var5);
      this.B = this.z = (float)(Math.atan2(var1, var5) * 180.0D / 3.1415927410125732D);
      this.C = this.A = (float)(Math.atan2(var3, (double)var10) * 180.0D / 3.1415927410125732D);
      this.i = 0;
   }

   public void j_() {
      this.T = this.t;
      this.U = this.u;
      this.V = this.v;
      super.j_();
      if(this.b > 0) {
         --this.b;
      }

      if(this.a) {
         int var1 = this.p.a(this.c, this.d, this.e);
         if(var1 == this.f) {
            ++this.i;
            if(this.i == 1200) {
               this.x();
            }

            return;
         }

         this.a = false;
         this.w *= (double)(this.aa.nextFloat() * 0.2F);
         this.x *= (double)(this.aa.nextFloat() * 0.2F);
         this.y *= (double)(this.aa.nextFloat() * 0.2F);
         this.i = 0;
         this.j = 0;
      } else {
         ++this.j;
      }

      OVec3 var18 = this.p.S().a(this.t, this.u, this.v);
      OVec3 var2 = this.p.S().a(this.t + this.w, this.u + this.x, this.v + this.y);
      OMovingObjectPosition var3 = this.p.a(var18, var2);
      var18 = this.p.S().a(this.t, this.u, this.v);
      var2 = this.p.S().a(this.t + this.w, this.u + this.x, this.v + this.y);
      if(var3 != null) {
         var2 = this.p.S().a(var3.f.c, var3.f.d, var3.f.e);
      }

      if(!this.p.J) {
         OEntity var4 = null;
         List var5 = this.p.b((OEntity)this, this.D.a(this.w, this.x, this.y).b(1.0D, 1.0D, 1.0D));
         double var6 = 0.0D;
         OEntityLiving var8 = this.h();

         for(int var9 = 0; var9 < var5.size(); ++var9) {
            OEntity var10 = (OEntity)var5.get(var9);
            if(var10.L() && (var10 != var8 || this.j >= 5)) {
               float var11 = 0.3F;
               OAxisAlignedBB var12 = var10.D.b((double)var11, (double)var11, (double)var11);
               OMovingObjectPosition var13 = var12.a(var18, var2);
               if(var13 != null) {
                  double var14 = var18.d(var13.f);
                  if(var14 < var6 || var6 == 0.0D) {
                     var4 = var10;
                     var6 = var14;
                  }
               }
            }
         }

         if(var4 != null) {
            var3 = new OMovingObjectPosition(var4);
         }
      }

      if(var3 != null) {
         if(var3.a == OEnumMovingObjectType.a && this.p.a(var3.b, var3.c, var3.d) == OBlock.bh.cm) {
            this.aa();
         } else {
            this.a(var3);
         }
      }

      this.t += this.w;
      this.u += this.x;
      this.v += this.y;
      float var19 = OMathHelper.a(this.w * this.w + this.y * this.y);
      this.z = (float)(Math.atan2(this.w, this.y) * 180.0D / 3.1415927410125732D);

      for(this.A = (float)(Math.atan2(this.x, (double)var19) * 180.0D / 3.1415927410125732D); this.A - this.C < -180.0F; this.C -= 360.0F) {
         ;
      }

      while(this.A - this.C >= 180.0F) {
         this.C += 360.0F;
      }

      while(this.z - this.B < -180.0F) {
         this.B -= 360.0F;
      }

      while(this.z - this.B >= 180.0F) {
         this.B += 360.0F;
      }

      this.A = this.C + (this.A - this.C) * 0.2F;
      this.z = this.B + (this.z - this.B) * 0.2F;
      float var20 = 0.99F;
      float var16 = this.g();
      if(this.H()) {
         for(int var17 = 0; var17 < 4; ++var17) {
            float var21 = 0.25F;
            this.p.a("bubble", this.t - this.w * (double)var21, this.u - this.x * (double)var21, this.v - this.y * (double)var21, this.w, this.x, this.y);
         }

         var20 = 0.8F;
      }

      this.w *= (double)var20;
      this.x *= (double)var20;
      this.y *= (double)var20;
      this.x -= (double)var16;
      this.b(this.t, this.u, this.v);
   }

   protected float g() {
      return 0.03F;
   }

   protected abstract void a(OMovingObjectPosition var1);

   public void b(ONBTTagCompound var1) {
      var1.a("xTile", (short)this.c);
      var1.a("yTile", (short)this.d);
      var1.a("zTile", (short)this.e);
      var1.a("inTile", (byte)this.f);
      var1.a("shake", (byte)this.b);
      var1.a("inGround", (byte)(this.a?1:0));
      if((this.h == null || this.h.length() == 0) && this.g != null && this.g instanceof OEntityPlayer) {
         this.h = this.g.an();
      }

      var1.a("ownerName", this.h == null?"":this.h);
   }

   public void a(ONBTTagCompound var1) {
      this.c = var1.d("xTile");
      this.d = var1.d("yTile");
      this.e = var1.d("zTile");
      this.f = var1.c("inTile") & 255;
      this.b = var1.c("shake") & 255;
      this.a = var1.c("inGround") == 1;
      this.h = var1.i("ownerName");
      if(this.h != null && this.h.length() == 0) {
         this.h = null;
      }

   }

   public OEntityLiving h() {
      if(this.g == null && this.h != null && this.h.length() > 0) {
         this.g = this.p.a(this.h);
      }

      return this.g;
   }

   public void setShooter(OEntityLiving shooter) { //CanaryMod: method for setting the shooter
       g = shooter;
       if(shooter instanceof OEntityPlayer) {
           h = ((OEntityPlayer) shooter).getEntity().getName();
       }
   }
}
