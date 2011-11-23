
public class OItemReed extends OItem {

   private int a;


   public OItemReed(int var1, OBlock var2) {
      super(var1);
      this.a = var2.bM;
   }

   public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
       // CanaryMod: Store blockClicked
       int clicked = var3.a(var4, var5, var6);
       Block blockClicked = new Block(var3.world, clicked, var4, var5, var6);

      int var8 = var3.a(var4, var5, var6);
      if(var8 == OBlock.aS.bM) {
         var7 = 0;
      } else if(var8 != OBlock.bu.bM) {
         if(var7 == 0) {
            --var5;
         }

         if(var7 == 1) {
            ++var5;
         }

         if(var7 == 2) {
            --var6;
         }

         if(var7 == 3) {
            ++var6;
         }

         if(var7 == 4) {
            --var4;
         }

         if(var7 == 5) {
            ++var4;
         }
      }

      if(!var2.d(var4, var5, var6)) {
         return false;
      } else if(var1.a == 0) {
         return false;
      } else {
         if(var3.a(this.a, var4, var5, var6, false, var7)) {
             // CanaryMod: Reed placement
             Block blockPlaced = new Block(var3.world, var3.a(var4, var5, var6), var4, var5, var6);
             blockClicked.setFaceClicked(Block.Face.fromId(var7));
             Player player = ((OEntityPlayerMP) var2).getPlayer();

             if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(var1)))
                 return false;

            OBlock var9 = OBlock.k[this.a];
            if(var3.e(var4, var5, var6, this.a)) {
               if(var3.a(var4, var5, var6) == this.a) {
                  OBlock.k[this.a].b(var3, var4, var5, var6, var7);
                  OBlock.k[this.a].a(var3, var4, var5, var6, (OEntityLiving)var2);
               }

               var3.a((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), var9.bX.c(), (var9.bX.a() + 1.0F) / 2.0F, var9.bX.b() * 0.8F);
               --var1.a;
            }
         }

         return true;
      }
   }
}
