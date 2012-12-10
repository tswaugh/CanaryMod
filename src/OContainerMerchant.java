public class OContainerMerchant extends OContainer {

   private OIMerchant a;
   private OInventoryMerchant f;
   private final OWorld g;

   public OContainerMerchant(OInventoryPlayer var1, OIMerchant var2, OWorld var3) {
      this.a = var2;
      this.g = var3;
      this.f = new OInventoryMerchant(var1.d, var2);
      this.a(new OSlot(this.f, 0, 36, 53));
      this.a(new OSlot(this.f, 1, 62, 53));
      this.a((OSlot)(new OSlotMerchantResult(var1.d, var2, this.f, 2, 120, 53)));

      int var4;
      for(var4 = 0; var4 < 3; ++var4) {
         for(int var5 = 0; var5 < 9; ++var5) {
            this.a(new OSlot(var1, var5 + var4 * 9 + 9, 8 + var5 * 18, 84 + var4 * 18));
         }
      }

      for(var4 = 0; var4 < 9; ++var4) {
         this.a(new OSlot(var1, var4, 8 + var4 * 18, 142));
      }
   }

   public OInventoryMerchant d() {
      return this.f;
   }

   public void a(OICrafting var1) {
      super.a(var1);
   }

   public void b() {
      super.b();
   }

   public void a(OIInventory var1) {
      this.f.g();
      super.a(var1);
   }

   public void b(int var1) {
      this.f.c(var1);
   }

   public boolean a(OEntityPlayer var1) {
      return this.a.m_() == var1;
   }

   public OItemStack b(OEntityPlayer var1, int var2) {
      OItemStack var3 = null;
      OSlot var4 = (OSlot)this.c.get(var2);
      if(var4 != null && var4.d()) {
         OItemStack var5 = var4.c();
         var3 = var5.l();
         if(var2 == 2) {
            if(!this.a(var5, 3, 39, true)) {
               return null;
            }

            var4.a(var5, var3);
         } else if(var2 != 0 && var2 != 1) {
            if(var2 >= 3 && var2 < 30) {
               if(!this.a(var5, 30, 39, false)) {
                  return null;
               }
            } else if(var2 >= 30 && var2 < 39 && !this.a(var5, 3, 30, false)) {
               return null;
            }
         } else if(!this.a(var5, 3, 39, false)) {
            return null;
         }

         if(var5.a == 0) {
            var4.c((OItemStack)null);
         } else {
            var4.e();
         }

         if(var5.a == var3.a) {
            return null;
         }

         if(((OSlotMerchantResult) var4).a(var1, var5, true)) { //CanaryMod: tells the method the player held shift
        	 var3 = null;
         }
      }

      return var3;
   }

   public void b(OEntityPlayer var1) {
      super.b(var1);
      this.a.b_((OEntityPlayer)null);
      super.b(var1);
      if(!this.g.J) {
         OItemStack var2 = this.f.a_(0);
         if(var2 != null) {
            var1.c(var2);
         }

         var2 = this.f.a_(1);
         if(var2 != null) {
            var1.c(var2);
         }
      }
   }
}
