public class OSlotMerchantResult extends OSlot {

   private final OInventoryMerchant a;
   private OEntityPlayer b;
   private int c;
   private final OIMerchant d;

   public OSlotMerchantResult(OEntityPlayer var1, OIMerchant var2, OInventoryMerchant var3, int var4, int var5, int var6) {
      super(var3, var4, var5, var6);
      this.b = var1;
      this.d = var2;
      this.a = var3;
   }

   public boolean a(OItemStack var1) {
      return false;
   }

   public OItemStack a(int var1) {
      if(this.d()) {
         this.c += Math.min(var1, this.c().a);
      }

      return super.a(var1);
   }

   protected void a(OItemStack var1, int var2) {
      this.c += var2;
      this.b(var1);
   }

   protected void b(OItemStack var1) {
      var1.a(this.b.p, this.b, this.c);
      this.c = 0;
   }
   
   public void a(OEntityPlayer var1, OItemStack var2) { //CanaryMod: so we don't break stuff
       a(var1, var2, false);
   }

   public boolean a(OEntityPlayer var1, OItemStack var2, boolean heldShift) { //CanaryMod: added parameter heldShift
       OMerchantRecipe var3 = this.a.h();
       if((Boolean) etc.getLoader().callHook(PluginLoader.Hook.VILLAGER_TRADE, new Object[] {((OEntityPlayerMP) var1).getPlayer(), new Villager((OEntityVillager) d), new VillagerTrade(var3)})) {
           if(heldShift) {
               ((OEntityPlayerMP) var1).getPlayer().getInventory().removeItemOverStacks(new Item(var3.d()));
           } else {
               var1.bI.b((OItemStack) null);
           }
           return true;
       }
      this.b(var2);
      if(var3 != null) {
         OItemStack var4 = this.a.a(0);
         OItemStack var5 = this.a.a(1);
         if(this.a(var3, var4, var5) || this.a(var3, var5, var4)) {
            if(var4 != null && var4.a <= 0) {
               var4 = null;
            }

            if(var5 != null && var5.a <= 0) {
               var5 = null;
            }

            this.a.a(0, var4);
            this.a.a(1, var5);
            this.d.a(var3);
         }
      }
      return false;
   }

   private boolean a(OMerchantRecipe var1, OItemStack var2, OItemStack var3) {
      OItemStack var4 = var1.a();
      OItemStack var5 = var1.b();
      if(var2 != null && var2.c == var4.c) {
         if(var5 != null && var3 != null && var5.c == var3.c) {
            var2.a -= var4.a;
            var3.a -= var5.a;
            return true;
         }

         if(var5 == null && var3 == null) {
            var2.a -= var4.a;
            return true;
         }
      }

      return false;
   }
}
