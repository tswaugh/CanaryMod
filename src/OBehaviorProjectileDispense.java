
public abstract class OBehaviorProjectileDispense extends OBehaviorDefaultDispenseItem {

   public OItemStack b(OIBlockSource var1, OItemStack var2) {
      OWorld var3 = var1.k();
      OIPosition var4 = OBlockDispenser.a(var1);
      OEnumFacing var5 = OEnumFacing.a(var1.h());
      OIProjectile var6 = this.a(var3, var4);
      if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) var1.j()), new BaseEntity((OEntity) var6))) {
          var6.c((double)var5.c(), 0.10000000149011612D, (double)var5.e(), this.b(), this.a());
          var3.d((OEntity)var6);
          var2.a(1);
      }
      return var2;
   }

   protected void a(OIBlockSource var1) {
       var1.k().f(1002, var1.d(), var1.e(), var1.f(), 0);
   }

   protected abstract OIProjectile a(OWorld var1, OIPosition var2);

   protected float a() {
      return 6.0F;
   }

   protected float b() {
      return 1.1F;
   }
}
