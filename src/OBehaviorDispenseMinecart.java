
public class OBehaviorDispenseMinecart extends OBehaviorDefaultDispenseItem {

   private final OBehaviorDefaultDispenseItem c;
   // $FF: synthetic field
   final OMinecraftServer b;


   public OBehaviorDispenseMinecart(OMinecraftServer var1) {
      this.b = var1;
      this.c = new OBehaviorDefaultDispenseItem();
   }

   public OItemStack b(OIBlockSource var1, OItemStack var2) {
      OEnumFacing var3 = OEnumFacing.a(var1.h());
      OWorld var4 = var1.k();
      double var5 = var1.a() + (double)((float)var3.c() * 1.125F);
      double var7 = var1.b();
      double var9 = var1.c() + (double)((float)var3.e() * 1.125F);
      int var11 = var1.d() + var3.c();
      int var12 = var1.e();
      int var13 = var1.f() + var3.e();
      int var14 = var4.a(var11, var12, var13);
      double var15;
      if(OBlockRail.d(var14)) {
         var15 = 0.0D;
      } else {
         if(var14 != 0 || !OBlockRail.d(var4.a(var11, var12 - 1, var13))) {
            return this.c.a(var1, var2);
         }

         var15 = -1.0D;
      }

      OEntityMinecart var17 = new OEntityMinecart(var4, var5, var7 + var15, var9, ((OItemMinecart)var2.b()).a);
      if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) var1.j()), new Minecart(var17))) {
    	  var4.d((OEntity)var17);
    	  var2.a(1);
      }
      return var2;
   }

   protected void a(OIBlockSource var1) {
      var1.k().f(1000, var1.d(), var1.e(), var1.f(), 0);
   }
}
