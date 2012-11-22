
public class OBehaviorBucketEmptyDispense extends OBehaviorDefaultDispenseItem {

   private final OBehaviorDefaultDispenseItem c;
   // $FF: synthetic field
   final OMinecraftServer b;


   public OBehaviorBucketEmptyDispense(OMinecraftServer var1) {
      this.b = var1;
      this.c = new OBehaviorDefaultDispenseItem();
   }

   public OItemStack b(OIBlockSource var1, OItemStack var2) {
      OEnumFacing var3 = OEnumFacing.a(var1.h());
      OWorld var4 = var1.k();
      int var5 = var1.d() + var3.c();
      int var6 = var1.e();
      int var7 = var1.f() + var3.e();
      OMaterial var8 = var4.g(var5, var6, var7);
      int var9 = var4.h(var5, var6, var7);
      OItem var10;
      if(OMaterial.h.equals(var8) && var9 == 0) {
         var10 = OItem.ax;
      } else {
         if(!OMaterial.i.equals(var8) || var9 != 0) {
            return super.b(var1, var2);
         }

         var10 = OItem.ay;
      }

      var4.e(var5, var6, var7, 0);
      if(--var2.a == 0) {
    	  if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) var1.j()), null)) {
    		  var2.c = var10.cg;
    		  var2.a = 1;
    	  }
      } else if(((OTileEntityDispenser)var1.j()).a(new OItemStack(var10)) < 0) {
         this.c.a(var1, new OItemStack(var10));
      }

      return var2;
   }
}
