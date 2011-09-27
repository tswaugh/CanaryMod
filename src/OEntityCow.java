
public class OEntityCow extends OEntityAnimal {

   public OEntityCow(OWorld var1) {
      super(var1);
      this.ab = "/mob/cow.png";
      this.b(0.9F, 1.3F);
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
   }

   protected String h() {
      return "mob.cow";
   }

   protected String i() {
      return "mob.cowhurt";
   }

   protected String j() {
      return "mob.cowhurt";
   }

   protected float l() {
      return 0.4F;
   }

   protected int k() {
      return OItem.aD.bo;
   }

   protected void a(boolean var1) {
      int var2 = this.bL.nextInt(3);

      int var3;
      for(var3 = 0; var3 < var2; ++var3) {
         this.b(OItem.aD.bo, 1);
      }

      var2 = this.bL.nextInt(3) + 1;

      for(var3 = 0; var3 < var2; ++var3) {
         if(this.bO > 0) {
            this.b(OItem.bh.bo, 1);
         } else {
            this.b(OItem.bg.bo, 1);
         }
      }

   }

   public boolean b(OEntityPlayer var1) {
      OItemStack var2 = var1.j.b();
      if(var2 != null && var2.c == OItem.au.bo) {
         // CanaryMod hook: onCowMilk
         if (!(Boolean)etc.getLoader().callHook(PluginLoader.Hook.COW_MILK, ((OEntityPlayerMP) var1).getPlayer(), new Mob(this)))
         {
            var1.j.a(var1.j.c, new OItemStack(OItem.aE));
            return true;
         }
         return false;
      } else {
         return false;
      }
   }
}
