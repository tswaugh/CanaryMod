
public class OBehaviorBucketFullDispense extends OBehaviorDefaultDispenseItem {

   private final OBehaviorDefaultDispenseItem c;
   // $FF: synthetic field
   final OMinecraftServer b;


   public OBehaviorBucketFullDispense(OMinecraftServer var1) {
      this.b = var1;
      this.c = new OBehaviorDefaultDispenseItem();
   }

   public OItemStack b(OIBlockSource var1, OItemStack var2) {
      OItemBucket var3 = (OItemBucket)var2.b();
      int var4 = var1.d();
      int var5 = var1.e();
      int var6 = var1.f();
      OEnumFacing var7 = OEnumFacing.a(var1.h());
      if(var3.a(var1.k(), (double)var4, (double)var5, (double)var6, var4 + var7.c(), var5, var6 + var7.e())) {
          if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) var1.j()), null)) {
              var2.c = OItem.aw.cg;
              var2.a = 1;
          }
         return var2;
      } else {
         return this.c.a(var1, var2);
      }
   }
}
