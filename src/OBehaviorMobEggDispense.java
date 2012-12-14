
public class OBehaviorMobEggDispense extends OBehaviorDefaultDispenseItem {

   // $FF: synthetic field
   final OMinecraftServer b;


   public OBehaviorMobEggDispense(OMinecraftServer var1) {
      this.b = var1;
   }

   public OItemStack b(OIBlockSource var1, OItemStack var2) {
      OEnumFacing var3 = OEnumFacing.a(var1.h());
      double var4 = var1.a() + (double)var3.c();
      double var6 = (double)((float)var1.e() + 0.2F);
      double var8 = var1.c() + (double)var3.e();
      OEntity entity = OItemMonsterPlacer.a(var1.k(), var2.j(), var4, var6, var8, false);
      if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) var1.j()), new BaseEntity(entity))) {
          entity.p.d(entity);
          var2.a(1);
      }
      return var2;
   }

   protected void a(OIBlockSource var1) {
      var1.k().f(1002, var1.d(), var1.e(), var1.f(), 0);
   }
}
