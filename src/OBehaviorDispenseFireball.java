import java.util.Random;

public class OBehaviorDispenseFireball extends OBehaviorDefaultDispenseItem {

   // $FF: synthetic field
   final OMinecraftServer b;


   public OBehaviorDispenseFireball(OMinecraftServer var1) {
      this.b = var1;
   }

   public OItemStack b(OIBlockSource var1, OItemStack var2) {
      OEnumFacing var3 = OEnumFacing.a(var1.h());
      OIPosition var4 = OBlockDispenser.a(var1);
      double var5 = var4.a() + (double)((float)var3.c() * 0.3F);
      double var7 = var4.b();
      double var9 = var4.c() + (double)((float)var3.e() * 0.3F);
      OWorld var11 = var1.k();
      Random var12 = var11.u;
      double var13 = var12.nextGaussian() * 0.05D + (double)var3.c();
      double var15 = var12.nextGaussian() * 0.05D;
      double var17 = var12.nextGaussian() * 0.05D + (double)var3.e();
      OEntitySmallFireball fireball = new OEntitySmallFireball(var11, var5, var7, var9, var13, var15, var17);
      if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) var1.j()), new BaseEntity(fireball))) {
    	  var11.d((OEntity)(fireball));
    	  var2.a(1);
      }
      return var2;
   }

   protected void a(OIBlockSource var1) {
      var1.k().f(1009, var1.d(), var1.e(), var1.f(), 0);
   }
}
