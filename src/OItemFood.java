
public class OItemFood extends OItem {

   public final int a;
   private final int bt;
   private final float bu;
   private final boolean bv;
   private boolean bw;
   private int bx;
   private int by;
   private int bz;
   private float bA;


   public OItemFood(int var1, int var2, float var3, boolean var4) {
      super(var1);
      this.a = 32;
      this.bt = var2;
      this.bv = var4;
      this.bu = var3;
   }

   public OItemFood(int var1, int var2, boolean var3) {
      this(var1, var2, 0.6F, var3);
   }

   public OItemStack b(OItemStack var1, OWorld var2, OEntityPlayer var3) {
      --var1.a;
      
      // CanaryMod FOODLEVEL_CHANGE HOOK
      int oldLevel = var3.V().a;
      int newLevel = Math.min(this.k()+oldLevel,20);
      int retLevel = (Integer) etc.getLoader().callHook(PluginLoader.Hook.FOODLEVEL_CHANGE, ((OEntityPlayerMP)var3).getPlayer(), oldLevel, newLevel);
      if (retLevel != oldLevel) {
          var3.V().a(retLevel);
      } 
      
      // Client has increased food level, update it to the modified one
      if (retLevel != newLevel) {
          ((OEntityPlayerMP)var3).getPlayer().updateLevels(); 
      }
      // CanaryMod END

      if(!var2.I && this.bx > 0 && var2.w.nextFloat() < this.bA) {
         var3.d(new OPotionEffect(this.bx, this.by * 20, this.bz));
      }

      return var1;
   }

   public int c(OItemStack var1) {
      return 32;
   }

   public OEnumAction b(OItemStack var1) {
      return OEnumAction.b;
   }

   public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
      if(var3.c(this.bw)) {
         var3.a(var1, this.c(var1));
      } 

      return var1;
   }

   public int k() {
      return this.bt;
   }

   public float l() {
      return this.bu;
   }

   public boolean m() {
      return this.bv;
   }

   public OItemFood a(int var1, int var2, int var3, float var4) {
      this.bx = var1;
      this.by = var2;
      this.bz = var3;
      this.bA = var4;
      return this;
   }

   public OItemFood n() {
      this.bw = true;
      return this;
   }

   public OItem a(String var1) {
      return super.a(var1);
   }
}
