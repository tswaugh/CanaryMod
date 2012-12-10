public class OMerchantRecipe {

   protected OItemStack a; //CanaryMod: private -> protected
   protected OItemStack b; //CanaryMod: private -> protected
   protected OItemStack c; //CanaryMod: private -> protected
   private int d;
   private int e;

   public OMerchantRecipe(ONBTTagCompound var1) {
      this.a(var1);
   }

   public OMerchantRecipe(OItemStack var1, OItemStack var2, OItemStack var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.e = 7;
   }

   public OMerchantRecipe(OItemStack var1, OItemStack var2) {
      this(var1, (OItemStack)null, var2);
   }

   public OMerchantRecipe(OItemStack var1, OItem var2) {
      this(var1, new OItemStack(var2));
   }

   public OItemStack a() {
      return this.a;
   }

   public OItemStack b() {
      return this.b;
   }

   public boolean c() {
      return this.b != null;
   }

   public OItemStack d() {
      return this.c;
   }

   public boolean a(OMerchantRecipe var1) {
      return this.a.c == var1.a.c && this.c.c == var1.c.c?this.b == null && var1.b == null || this.b != null && var1.b != null && this.b.c == var1.b.c:false;
   }

   public boolean b(OMerchantRecipe var1) {
      return this.a(var1) && (this.a.a < var1.a.a || this.b != null && this.b.a < var1.b.a);
   }

   public void f() {
      ++this.d;
   }

   public void a(int var1) {
      this.e += var1;
   }

   public boolean g() {
      return this.d >= this.e;
   }

   public void a(ONBTTagCompound var1) {
      ONBTTagCompound var2 = var1.l("buy");
      this.a = OItemStack.a(var2);
      ONBTTagCompound var3 = var1.l("sell");
      this.c = OItemStack.a(var3);
      if(var1.b("buyB")) {
         this.b = OItemStack.a(var1.l("buyB"));
      }

      if(var1.b("uses")) {
         this.d = var1.e("uses");
      }

      if(var1.b("maxUses")) {
         this.e = var1.e("maxUses");
      } else {
         this.e = 7;
      }
   }

   public ONBTTagCompound i() {
      ONBTTagCompound var1 = new ONBTTagCompound();
      var1.a("buy", this.a.b(new ONBTTagCompound("buy")));
      var1.a("sell", this.c.b(new ONBTTagCompound("sell")));
      if(this.b != null) {
         var1.a("buyB", this.b.b(new ONBTTagCompound("buyB")));
      }

      var1.a("uses", this.d);
      var1.a("maxUses", this.e);
      return var1;
   }
}
