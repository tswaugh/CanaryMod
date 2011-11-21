
public class OInventoryCraftResult implements OIInventory {

   private OItemStack[] a = new OItemStack[1];
   // CanaryMod
   private String name = "Result";

   public OInventoryCraftResult() {
      super();
   }

   public int d() {
      return 1;
   }

   public OItemStack c(int var1) {
      return this.a[var1];
   }

   public String e() {
      return this.name;
   }

   public OItemStack a(int var1, int var2) {
      if(this.a[var1] != null) {
         OItemStack var3 = this.a[var1];
         this.a[var1] = null;
         return var3;
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      this.a[var1] = var2;
   }

   public int f() {
      return 64;
   }

   public void i() {}

   public boolean a_(OEntityPlayer var1) {
      return true;
   }

   public void k() {}

   public void z_() {}
}
