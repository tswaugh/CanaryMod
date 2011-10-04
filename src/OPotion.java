
public class OPotion {

   public static final OPotion[] a = new OPotion[32];
   public static final OPotion b = null;
   public static final OPotion c = (new OPotion(1)).a("potion.moveSpeed");
   public static final OPotion d = (new OPotion(2)).a("potion.moveSlowdown");
   public static final OPotion e = (new OPotion(3)).a("potion.digSpeed");
   public static final OPotion f = (new OPotion(4)).a("potion.digSlowDown");
   public static final OPotion g = (new OPotion(5)).a("potion.damageBoost");
   public static final OPotion h = (new OPotionHealth(6)).a("potion.heal");
   public static final OPotion i = (new OPotionHealth(7)).a("potion.harm");
   public static final OPotion j = (new OPotion(8)).a("potion.jump");
   public static final OPotion k = (new OPotion(9)).a("potion.confusion");
   public static final OPotion l = (new OPotion(10)).a("potion.regeneration");
   public static final OPotion m = (new OPotion(11)).a("potion.resistance");
   public static final OPotion n = (new OPotion(12)).a("potion.fireResistance");
   public static final OPotion o = (new OPotion(13)).a("potion.waterBreathing");
   public static final OPotion p = (new OPotion(14)).a("potion.invisibility");
   public static final OPotion q = (new OPotion(15)).a("potion.blindness");
   public static final OPotion r = (new OPotion(16)).a("potion.nightVision");
   public static final OPotion s = (new OPotion(17)).a("potion.hunger");
   public static final OPotion t = (new OPotion(18)).a("potion.weakness");
   public static final OPotion u = (new OPotion(19)).a("potion.poison");
   public static final OPotion v = null;
   public static final OPotion w = null;
   public static final OPotion x = null;
   public static final OPotion y = null;
   public static final OPotion z = null;
   public static final OPotion A = null;
   public static final OPotion B = null;
   public static final OPotion C = null;
   public static final OPotion D = null;
   public static final OPotion E = null;
   public static final OPotion F = null;
   public static final OPotion G = null;
   public final int H;
   private String I = "";


   protected OPotion(int var1) {
      super();
      this.H = var1;
      a[var1] = this;
   }

   public void a(OEntityLiving var1, int var2) {
      if(this.H == l.H) {
         if(var1.an < 20) {
            var1.c(1);
         }
      } else if(this.H == u.H) {
         if(var1.an > 1) {
             // Canarymod: DAMAGE From Poison
             if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.POISON, null, var1.entity, 1))
                 var1.a(ODamageSource.l, 1);
         }
      } else if(this.H == s.H && var1 instanceof OEntityPlayer) {
         ((OEntityPlayer)var1).b(0.025F * (float)(var2 + 1));
      } else if(this.H == h.H) {
         var1.c(4 << var2);
      } else if(this.H == i.H) {
         // Canarymod: Àcall to DAMAGE on 1.9?
         var1.a(ODamageSource.l, 4 << var2);
      }

   }

   public boolean a(int var1, int var2) {
      if(this.H != l.H && this.H != u.H) {
         return this.H == s.H;
      } else {
         int var3 = 25 >> var2;
         return var3 > 0?var1 % var3 == 0:true;
      }
   }

   public OPotion a(String var1) {
      this.I = var1;
      return this;
   }

}
