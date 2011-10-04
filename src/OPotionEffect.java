
public class OPotionEffect {

   // CanaryMod made public
   public int a;
   public int b;
   public int c;
   public PotionEffect potionEffect = new PotionEffect(this);


   public OPotionEffect(int var1, int var2, int var3) {
      super();
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }

   public void a(OPotionEffect var1) {
      if(this.a != var1.a) {
         System.err.println("This method should only be called for matching effects!");
      }

      if(var1.c >= this.c) {
         this.c = var1.c;
         this.b = var1.b;
      }

   }

   public int a() {
      return this.a;
   }

   public int b() {
      return this.b;
   }

   public int c() {
      return this.c;
   }

   public boolean a(OEntityLiving var1) {
      if(this.b > 0) {
         if(OPotion.a[this.a].a(this.b, this.c)) {
            this.b(var1);
         }

         this.d();
      }

      return this.b > 0;
   }

   private int d() {
      return --this.b;
   }

   public void b(OEntityLiving var1) {
      if(this.b > 0) {
         OPotion.a[this.a].a(var1, this.c);
      }

   }

   public int hashCode() {
      return this.a;
   }
}
