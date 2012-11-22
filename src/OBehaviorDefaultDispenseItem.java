
public class OBehaviorDefaultDispenseItem implements OIBehaviorDispenseItem {

   public final OItemStack a(OIBlockSource var1, OItemStack var2) {
      OItemStack var3 = this.b(var1, var2);
      this.a(var1);
      this.a(var1, OEnumFacing.a(var1.h()));
      return var3;
   }

   protected OItemStack b(OIBlockSource var1, OItemStack var2) {
	   if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) var1.j()), null)) {
		   OEnumFacing var3 = OEnumFacing.a(var1.h());
		   OIPosition var4 = OBlockDispenser.a(var1);
		   OItemStack var5 = var2.a(1);
		   a(var1.k(), var5, 6, var3, var4);
	   }
	   return var2;
   }

   public static void a(OWorld var0, OItemStack var1, int var2, OEnumFacing var3, OIPosition var4) {
      double var5 = var4.a();
      double var7 = var4.b();
      double var9 = var4.c();
      OEntityItem var11 = new OEntityItem(var0, var5, var7 - 0.3D, var9, var1);
      double var12 = var0.u.nextDouble() * 0.1D + 0.2D;
      var11.w = (double)var3.c() * var12;
      var11.x = 0.20000000298023224D;
      var11.y = (double)var3.e() * var12;
      var11.w += var0.u.nextGaussian() * 0.007499999832361937D * (double)var2;
      var11.x += var0.u.nextGaussian() * 0.007499999832361937D * (double)var2;
      var11.y += var0.u.nextGaussian() * 0.007499999832361937D * (double)var2;
      var0.d((OEntity)var11);
   }

   protected void a(OIBlockSource var1) {
      var1.k().f(1000, var1.d(), var1.e(), var1.f(), 0);
   }

   protected void a(OIBlockSource var1, OEnumFacing var2) {
      var1.k().f(2000, var1.d(), var1.e(), var1.f(), this.a(var2));
   }

   private int a(OEnumFacing var1) {
      return var1.c() + 1 + (var1.e() + 1) * 3;
   }
}
