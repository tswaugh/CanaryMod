import java.util.List;

public class OEntityArrow extends OEntity implements OIProjectile {

   private int d = -1;
   private int e = -1;
   private int f = -1;
   private int g = 0;
   private int h = 0;
   private boolean i = false;
   public int a = 0;
   public int b = 0;
   public OEntity c;
   private int j;
   private int as = 0;
   private double at = 2.0D;
   private int au;


   public OEntityArrow(OWorld var1) {
      super(var1);
      this.a(0.5F, 0.5F);
   }

   public OEntityArrow(OWorld var1, double var2, double var4, double var6) {
      super(var1);
      this.a(0.5F, 0.5F);
      this.b(var2, var4, var6);
      this.M = 0.0F;
   }

   public OEntityArrow(OWorld var1, OEntityLiving var2, OEntityLiving var3, float var4, float var5) {
      super(var1);
      this.c = var2;
      if(var2 instanceof OEntityPlayer) {
         this.a = 1;
      }

      this.u = var2.u + (double)var2.e() - 0.10000000149011612D;
      double var6 = var3.t - var2.t;
      double var8 = var3.u + (double)var3.e() - 0.699999988079071D - this.u;
      double var10 = var3.v - var2.v;
      double var12 = (double)OMathHelper.a(var6 * var6 + var10 * var10);
      if(var12 >= 1.0E-7D) {
         float var14 = (float)(Math.atan2(var10, var6) * 180.0D / 3.1415927410125732D) - 90.0F;
         float var15 = (float)(-(Math.atan2(var8, var12) * 180.0D / 3.1415927410125732D));
         double var16 = var6 / var12;
         double var18 = var10 / var12;
         this.b(var2.t + var16, this.u, var2.v + var18, var14, var15);
         this.M = 0.0F;
         float var20 = (float)var12 * 0.2F;
         this.c(var6, var8 + (double)var20, var10, var4, var5);
      }
   }

   public OEntityArrow(OWorld var1, OEntityLiving var2, float var3) {
      super(var1);
      this.c = var2;
      if(var2 instanceof OEntityPlayer) {
         this.a = 1;
      }

      this.a(0.5F, 0.5F);
      this.b(var2.t, var2.u + (double)var2.e(), var2.v, var2.z, var2.A);
      this.t -= (double)(OMathHelper.b(this.z / 180.0F * 3.1415927F) * 0.16F);
      this.u -= 0.10000000149011612D;
      this.v -= (double)(OMathHelper.a(this.z / 180.0F * 3.1415927F) * 0.16F);
      this.b(this.t, this.u, this.v);
      this.M = 0.0F;
      this.w = (double)(-OMathHelper.a(this.z / 180.0F * 3.1415927F) * OMathHelper.b(this.A / 180.0F * 3.1415927F));
      this.y = (double)(OMathHelper.b(this.z / 180.0F * 3.1415927F) * OMathHelper.b(this.A / 180.0F * 3.1415927F));
      this.x = (double)(-OMathHelper.a(this.A / 180.0F * 3.1415927F));
      this.c(this.w, this.x, this.y, var3 * 1.5F, 1.0F);
   }

   protected void a() {
      this.ag.a(16, Byte.valueOf((byte)0));
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
      this.j = 0;
   }

   public void j_() {
      super.j_();
      if(this.C == 0.0F && this.B == 0.0F) {
         float var1 = OMathHelper.a(this.w * this.w + this.y * this.y);
         this.B = this.z = (float)(Math.atan2(this.w, this.y) * 180.0D / 3.1415927410125732D);
         this.C = this.A = (float)(Math.atan2(this.x, (double)var1) * 180.0D / 3.1415927410125732D);
      }

      int var16 = this.p.a(this.d, this.e, this.f);
      if(var16 > 0) {
         OBlock.p[var16].a(this.p, this.d, this.e, this.f);
         OAxisAlignedBB var2 = OBlock.p[var16].e(this.p, this.d, this.e, this.f);
         if(var2 != null && var2.a(this.p.S().a(this.t, this.u, this.v))) {
            this.i = true;
         }
      }

      if(this.b > 0) {
         --this.b;
      }

      if(this.i) {
         int var18 = this.p.a(this.d, this.e, this.f);
         int var19 = this.p.h(this.d, this.e, this.f);
         if(var18 == this.g && var19 == this.h) {
            ++this.j;
            if(this.j == 1200) {
               this.x();
            }

         } else {
            this.i = false;
            this.w *= (double)(this.aa.nextFloat() * 0.2F);
            this.x *= (double)(this.aa.nextFloat() * 0.2F);
            this.y *= (double)(this.aa.nextFloat() * 0.2F);
            this.j = 0;
            this.as = 0;
         }
      } else {
         ++this.as;
         OVec3 var17 = this.p.S().a(this.t, this.u, this.v);
         OVec3 var3 = this.p.S().a(this.t + this.w, this.u + this.x, this.v + this.y);
         OMovingObjectPosition var4 = this.p.a(var17, var3, false, true);
         var17 = this.p.S().a(this.t, this.u, this.v);
         var3 = this.p.S().a(this.t + this.w, this.u + this.x, this.v + this.y);
         if(var4 != null) {
            var3 = this.p.S().a(var4.f.c, var4.f.d, var4.f.e);
         }

         OEntity var5 = null;
         List var6 = this.p.b((OEntity)this, this.D.a(this.w, this.x, this.y).b(1.0D, 1.0D, 1.0D));
         double var7 = 0.0D;

         int var9;
         float var11;
         for(var9 = 0; var9 < var6.size(); ++var9) {
            OEntity var10 = (OEntity)var6.get(var9);
            if(var10.L() && (var10 != this.c || this.as >= 5)) {
               var11 = 0.3F;
               OAxisAlignedBB var12 = var10.D.b((double)var11, (double)var11, (double)var11);
               OMovingObjectPosition var13 = var12.a(var17, var3);
               if(var13 != null) {
                  double var14 = var17.d(var13.f);
                  if(var14 < var7 || var7 == 0.0D) {
                     var5 = var10;
                     var7 = var14;
                  }
               }
            }
         }

         if(var5 != null) {
            var4 = new OMovingObjectPosition(var5);
         }

         float var20;
         if(var4 != null) {
            if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Arrow(this), var4 == null || var4.g == null ? null : var4.g.getEntity())) {
                if(var4.g != null) {
                   var20 = OMathHelper.a(this.w * this.w + this.x * this.x + this.y * this.y);
                   int var23 = OMathHelper.f((double)var20 * this.at);
                   if(this.d()) {
                      var23 += this.aa.nextInt(var23 / 2 + 2);
                   }
    
                   ODamageSource var21 = null;
                   if(this.c == null) {
                      var21 = ODamageSource.a(this, this);
                   } else {
                      var21 = ODamageSource.a(this, this.c);
                   }
    
                   if(this.af()) {
                      var4.g.c(5);
                   }
    
                   if(var4.g.a(var21, var23)) {
                      if(var4.g instanceof OEntityLiving) {
                         if(!this.p.J) {
                            OEntityLiving var24 = (OEntityLiving)var4.g;
                            var24.r(var24.bJ() + 1);
                         }
    
                         if(this.au > 0) {
                            float var25 = OMathHelper.a(this.w * this.w + this.y * this.y);
                            if(var25 > 0.0F) {
                               var4.g.g(this.w * (double)this.au * 0.6000000238418579D / (double)var25, 0.1D, this.y * (double)this.au * 0.6000000238418579D / (double)var25);
                            }
                         }
                      }
    
                      this.a("random.bowhit", 1.0F, 1.2F / (this.aa.nextFloat() * 0.2F + 0.9F));
                      this.x();
                   } else {
                      this.w *= -0.10000000149011612D;
                      this.x *= -0.10000000149011612D;
                      this.y *= -0.10000000149011612D;
                      this.z += 180.0F;
                      this.B += 180.0F;
                      this.as = 0;
                   }
                } else {
                   this.d = var4.b;
                   this.e = var4.c;
                   this.f = var4.d;
                   this.g = this.p.a(this.d, this.e, this.f);
                   this.h = this.p.h(this.d, this.e, this.f);
                   this.w = (double)((float)(var4.f.c - this.t));
                   this.x = (double)((float)(var4.f.d - this.u));
                   this.y = (double)((float)(var4.f.e - this.v));
                   var20 = OMathHelper.a(this.w * this.w + this.x * this.x + this.y * this.y);
                   this.t -= this.w / (double)var20 * 0.05000000074505806D;
                   this.u -= this.x / (double)var20 * 0.05000000074505806D;
                   this.v -= this.y / (double)var20 * 0.05000000074505806D;
                   this.a("random.bowhit", 1.0F, 1.2F / (this.aa.nextFloat() * 0.2F + 0.9F));
                   this.i = true;
                   this.b = 7;
                   this.e(false);
                   if(this.g != 0) {
                      OBlock.p[this.g].a(this.p, this.d, this.e, this.f, (OEntity)this);
                   }
                }
            }
         }

         if(this.d()) {
            for(var9 = 0; var9 < 4; ++var9) {
               this.p.a("crit", this.t + this.w * (double)var9 / 4.0D, this.u + this.x * (double)var9 / 4.0D, this.v + this.y * (double)var9 / 4.0D, -this.w, -this.x + 0.2D, -this.y);
            }
         }

         this.t += this.w;
         this.u += this.x;
         this.v += this.y;
         var20 = OMathHelper.a(this.w * this.w + this.y * this.y);
         this.z = (float)(Math.atan2(this.w, this.y) * 180.0D / 3.1415927410125732D);

         for(this.A = (float)(Math.atan2(this.x, (double)var20) * 180.0D / 3.1415927410125732D); this.A - this.C < -180.0F; this.C -= 360.0F) {
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
         float var22 = 0.99F;
         var11 = 0.05F;
         if(this.H()) {
            for(int var26 = 0; var26 < 4; ++var26) {
               float var27 = 0.25F;
               this.p.a("bubble", this.t - this.w * (double)var27, this.u - this.x * (double)var27, this.v - this.y * (double)var27, this.w, this.x, this.y);
            }

            var22 = 0.8F;
         }

         this.w *= (double)var22;
         this.x *= (double)var22;
         this.y *= (double)var22;
         this.x -= (double)var11;
         this.b(this.t, this.u, this.v);
         this.D();
      }
   }

   public void b(ONBTTagCompound var1) {
      var1.a("xTile", (short)this.d);
      var1.a("yTile", (short)this.e);
      var1.a("zTile", (short)this.f);
      var1.a("inTile", (byte)this.g);
      var1.a("inData", (byte)this.h);
      var1.a("shake", (byte)this.b);
      var1.a("inGround", (byte)(this.i?1:0));
      var1.a("pickup", (byte)this.a);
      var1.a("damage", this.at);
   }

   public void a(ONBTTagCompound var1) {
      this.d = var1.d("xTile");
      this.e = var1.d("yTile");
      this.f = var1.d("zTile");
      this.g = var1.c("inTile") & 255;
      this.h = var1.c("inData") & 255;
      this.b = var1.c("shake") & 255;
      this.i = var1.c("inGround") == 1;
      if(var1.b("damage")) {
         this.at = var1.h("damage");
      }

      if(var1.b("pickup")) {
         this.a = var1.c("pickup");
      } else if(var1.b("player")) {
         this.a = var1.n("player")?1:0;
      }

   }

   public void c_(OEntityPlayer var1) {
      if(!this.p.J && this.i && this.b <= 0) {
         boolean var2 = this.a == 1 || this.a == 2 && var1.cc.d;
         if(this.a == 1 && !var1.bI.a(new OItemStack(OItem.l, 1))) {
            var2 = false;
         }

         if(var2) {
            this.a("random.pop", 0.2F, ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            var1.a((OEntity)this, 1);
            this.x();
         }

      }
   }

   protected boolean f_() {
      return false;
   }

   public void b(double var1) {
      this.at = var1;
   }

   public double c() {
      return this.at;
   }

   public void a(int var1) {
      this.au = var1;
   }

   public boolean aq() {
      return false;
   }

   public void e(boolean var1) {
      byte var2 = this.ag.a(16);
      if(var1) {
         this.ag.b(16, Byte.valueOf((byte)(var2 | 1)));
      } else {
         this.ag.b(16, Byte.valueOf((byte)(var2 & -2)));
      }

   }

   public boolean d() {
      byte var1 = this.ag.a(16);
      return (var1 & 1) != 0;
   }
}
