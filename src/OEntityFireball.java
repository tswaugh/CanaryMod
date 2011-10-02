import java.util.List;

public class OEntityFireball extends OEntity {

   private int f = -1;
   private int g = -1;
   private int h = -1;
   private int i = 0;
   private boolean j = false;
   public int a = 0;
   public OEntityLiving b;
   private int k;
   private int l = 0;
   public double c;
   public double d;
   public double e;


   public OEntityFireball(OWorld var1) {
      super(var1);
      this.b(1.0F, 1.0F);
   }

   protected void b() {}

   public OEntityFireball(OWorld var1, OEntityLiving var2, double var3, double var5, double var7) {
      super(var1);
      this.b = var2;
      this.b(1.0F, 1.0F);
      this.c(var2.bf, var2.bg, var2.bh, var2.bl, var2.bm);
      this.c(this.bf, this.bg, this.bh);
      this.by = 0.0F;
      this.bi = this.bj = this.bk = 0.0D;
      var3 += this.bL.nextGaussian() * 0.4D;
      var5 += this.bL.nextGaussian() * 0.4D;
      var7 += this.bL.nextGaussian() * 0.4D;
      double var9 = (double)OMathHelper.a(var3 * var3 + var5 * var5 + var7 * var7);
      this.c = var3 / var9 * 0.1D;
      this.d = var5 / var9 * 0.1D;
      this.e = var7 / var9 * 0.1D;
   }

   public void s_() {
      super.s_();
      this.bO = 10;
      if(this.a > 0) {
         --this.a;
      }

      if(this.j) {
         int var1 = this.bb.a(this.f, this.g, this.h);
         if(var1 == this.i) {
            ++this.k;
            if(this.k == 1200) {
               this.N();
            }

            return;
         }

         this.j = false;
         this.bi *= (double)(this.bL.nextFloat() * 0.2F);
         this.bj *= (double)(this.bL.nextFloat() * 0.2F);
         this.bk *= (double)(this.bL.nextFloat() * 0.2F);
         this.k = 0;
         this.l = 0;
      } else {
         ++this.l;
      }

      OVec3D var15 = OVec3D.b(this.bf, this.bg, this.bh);
      OVec3D var2 = OVec3D.b(this.bf + this.bi, this.bg + this.bj, this.bh + this.bk);
      OMovingObjectPosition var3 = this.bb.a(var15, var2);
      var15 = OVec3D.b(this.bf, this.bg, this.bh);
      var2 = OVec3D.b(this.bf + this.bi, this.bg + this.bj, this.bh + this.bk);
      if(var3 != null) {
         var2 = OVec3D.b(var3.f.a, var3.f.b, var3.f.c);
      }

      OEntity var4 = null;
      List var5 = this.bb.b((OEntity)this, this.bp.a(this.bi, this.bj, this.bk).b(1.0D, 1.0D, 1.0D));
      double var6 = 0.0D;

      for(int var8 = 0; var8 < var5.size(); ++var8) {
         OEntity var9 = (OEntity)var5.get(var8);
         if(var9.r_() && (var9 != this.b || this.l >= 25)) {
            float var10 = 0.3F;
            OAxisAlignedBB var11 = var9.bp.b((double)var10, (double)var10, (double)var10);
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
         if(!this.bb.I) {
            if(var3.g != null && var3.g.a(ODamageSource.a(this, this.b), 0)) {
               ;
            }

            this.bb.a((OEntity)this, this.bf, this.bg, this.bh, 1.0F, true);
         }

         this.N();
      }

      this.bf += this.bi;
      this.bg += this.bj;
      this.bh += this.bk;
      float var16 = OMathHelper.a(this.bi * this.bi + this.bk * this.bk);
      this.bl = (float)(Math.atan2(this.bi, this.bk) * 180.0D / 3.1415927410125732D);

      for(this.bm = (float)(Math.atan2(this.bj, (double)var16) * 180.0D / 3.1415927410125732D); this.bm - this.bo < -180.0F; this.bo -= 360.0F) {
         ;
      }

      while(this.bm - this.bo >= 180.0F) {
         this.bo += 360.0F;
      }

      while(this.bl - this.bn < -180.0F) {
         this.bn -= 360.0F;
      }

      while(this.bl - this.bn >= 180.0F) {
         this.bn += 360.0F;
      }

      this.bm = this.bo + (this.bm - this.bo) * 0.2F;
      this.bl = this.bn + (this.bl - this.bn) * 0.2F;
      float var17 = 0.95F;
      if(this.ao()) {
         for(int var19 = 0; var19 < 4; ++var19) {
            float var18 = 0.25F;
            this.bb.a("bubble", this.bf - this.bi * (double)var18, this.bg - this.bj * (double)var18, this.bh - this.bk * (double)var18, this.bi, this.bj, this.bk);
         }

         var17 = 0.8F;
      }

      this.bi += this.c;
      this.bj += this.d;
      this.bk += this.e;
      this.bi *= (double)var17;
      this.bj *= (double)var17;
      this.bk *= (double)var17;
      this.bb.a("smoke", this.bf, this.bg + 0.5D, this.bh, 0.0D, 0.0D, 0.0D);
      this.c(this.bf, this.bg, this.bh);
   }

   public void b(ONBTTagCompound var1) {
      var1.a("xTile", (short)this.f);
      var1.a("yTile", (short)this.g);
      var1.a("zTile", (short)this.h);
      var1.a("inTile", (byte)this.i);
      var1.a("shake", (byte)this.a);
      var1.a("inGround", (byte)(this.j?1:0));
   }

   public void a(ONBTTagCompound var1) {
      this.f = var1.d("xTile");
      this.g = var1.d("yTile");
      this.h = var1.d("zTile");
      this.i = var1.c("inTile") & 255;
      this.a = var1.c("shake") & 255;
      this.j = var1.c("inGround") == 1;
   }

   public boolean r_() {
      return true;
   }

   public boolean a(ODamageSource var1, int var2) {
      this.aq();
      if(var1.a() != null) {
         OVec3D var3 = var1.a().ai();
         if(var3 != null) {
            this.bi = var3.a;
            this.bj = var3.b;
            this.bk = var3.c;
            this.c = this.bi * 0.1D;
            this.d = this.bj * 0.1D;
            this.e = this.bk * 0.1D;
         }

         return true;
      } else {
         return false;
      }
   }
}
