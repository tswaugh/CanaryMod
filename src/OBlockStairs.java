import java.util.ArrayList;
import java.util.Random;

public class OBlockStairs extends OBlock {

   private OBlock a;


   protected OBlockStairs(int var1, OBlock var2) {
      super(var1, var2.bL, var2.bZ);
      this.a = var2;
      this.c(var2.bN);
      this.b(var2.bO / 3.0F);
      this.a(var2.bX);
      this.f(255);
   }

   public void a(OIBlockAccess var1, int var2, int var3, int var4) {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public OAxisAlignedBB a(OWorld var1, int var2, int var3, int var4) {
      return super.a(var1, var2, var3, var4);
   }

   public boolean a() {
      return false;
   }

   public boolean d() {
      return false;
   }

   public int b() {
      return 10;
   }

   public void a(OWorld var1, int var2, int var3, int var4, OAxisAlignedBB var5, ArrayList var6) {
      int var7 = var1.e(var2, var3, var4);
      if(var7 == 0) {
         this.a(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
         super.a(var1, var2, var3, var4, var5, var6);
         this.a(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         super.a(var1, var2, var3, var4, var5, var6);
      } else if(var7 == 1) {
         this.a(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
         super.a(var1, var2, var3, var4, var5, var6);
         this.a(0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
         super.a(var1, var2, var3, var4, var5, var6);
      } else if(var7 == 2) {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F);
         super.a(var1, var2, var3, var4, var5, var6);
         this.a(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
         super.a(var1, var2, var3, var4, var5, var6);
      } else if(var7 == 3) {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
         super.a(var1, var2, var3, var4, var5, var6);
         this.a(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
         super.a(var1, var2, var3, var4, var5, var6);
      }

      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public void b(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
      this.a.b(var1, var2, var3, var4, var5);
   }

   public void c(OWorld var1, int var2, int var3, int var4, int var5) {
      this.a.c(var1, var2, var3, var4, var5);
   }

   public float a(OEntity var1) {
      return this.a.a(var1);
   }

   public int a(int var1, int var2) {
      return this.a.a(var1, 0);
   }

   public int a(int var1) {
      return this.a.a(var1, 0);
   }

   public int f() {
      return this.a.f();
   }

   public void a(OWorld var1, int var2, int var3, int var4, OEntity var5, OVec3D var6) {
      this.a.a(var1, var2, var3, var4, var5, var6);
   }

   public boolean h() {
      return this.a.h();
   }

   public boolean a(int var1, boolean var2) {
      return this.a.a(var1, var2);
   }

   public boolean d(OWorld var1, int var2, int var3, int var4) {
      return this.a.d(var1, var2, var3, var4);
   }

   public void b(OWorld var1, int var2, int var3, int var4) {
      this.a(var1, var2, var3, var4, 0);
      this.a.b(var1, var2, var3, var4);
   }

   public void e(OWorld var1, int var2, int var3, int var4) {
      this.a.e(var1, var2, var3, var4);
   }

   public void b(OWorld var1, int var2, int var3, int var4, OEntity var5) {
      this.a.b(var1, var2, var3, var4, var5);
   }

   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      this.a.a(var1, var2, var3, var4, var5);
   }

   public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
      return this.a.a(var1, var2, var3, var4, var5);
   }

   public void g(OWorld var1, int var2, int var3, int var4) {
      this.a.g(var1, var2, var3, var4);
   }

   public void a(OWorld var1, int var2, int var3, int var4, OEntityLiving var5) {
      int var6 = OMathHelper.b((double)(var5.ah * 4.0F / 360.0F) + 0.5D) & 3;
      if(var6 == 0) {
         var1.c(var2, var3, var4, 2);
      }

      if(var6 == 1) {
         var1.c(var2, var3, var4, 1);
      }

      if(var6 == 2) {
         var1.c(var2, var3, var4, 3);
      }

      if(var6 == 3) {
         var1.c(var2, var3, var4, 0);
      }

   }
}
