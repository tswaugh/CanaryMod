import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class OTileEntity {

   private static Map a = new HashMap();
   private static Map b = new HashMap();
   protected OWorld k;
   public int l;
   public int m;
   public int n;
   protected boolean o;
   public int p = -1;
   public OBlock q;
   NBTTagCompound metadata = new NBTTagCompound("Canary");


   private static void a(Class var0, String var1) {
      if(a.containsKey(var1)) {
         throw new IllegalArgumentException("Duplicate id: " + var1);
      } else {
         a.put(var1, var0);
         b.put(var0, var1);
      }
   }

   public void b(OWorld var1) {
      this.k = var1;
   }

   public boolean o() {
      return this.k != null;
   }

   public void a(ONBTTagCompound var1) {
      this.l = var1.e("x");
      this.m = var1.e("y");
      this.n = var1.e("z");
      if(var1.b("Canary")) {
          this.metadata = new NBTTagCompound(var1.l("Canary"));
      }
   }

   public void b(ONBTTagCompound var1) {
      String var2 = (String)b.get(this.getClass());
      if(var2 == null) {
         throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
      } else {
         var1.a("id", var2);
         var1.a("x", this.l);
         var1.a("y", this.m);
         var1.a("z", this.n);
         var1.a("Canary", metadata.getBaseTag());
      }
   }

   public void g() {}

   public static OTileEntity c(ONBTTagCompound var0) {
      OTileEntity var1 = null;

      try {
         Class var2 = (Class)a.get(var0.i("id"));
         if(var2 != null) {
            var1 = (OTileEntity)var2.newInstance();
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      if(var1 != null) {
         var1.a(var0);
      } else {
         System.out.println("Skipping TileEntity with id " + var0.i("id"));
      }

      return var1;
   }

   public int p() {
      if(this.p == -1) {
         this.p = this.k.h(this.l, this.m, this.n);
      }

      return this.p;
   }

   public void d() {
      if(this.k != null) {
         this.p = this.k.h(this.l, this.m, this.n);
         this.k.b(this.l, this.m, this.n, this);
      }

   }

   public OBlock q() {
      if(this.q == null) {
         this.q = OBlock.p[this.k.a(this.l, this.m, this.n)];
      }

      return this.q;
   }

   public OPacket l() {
      return null;
   }

   public boolean r() {
      return this.o;
   }

   public void w_() {
      this.o = true;
   }

   public void s() {
      this.o = false;
   }

   public void b(int var1, int var2) {}

   public void h() {
      this.q = null;
      this.p = -1;
   }

   public void a(OCrashReportCategory var1) {
      var1.a("Name", (Callable)(new OCallableTileEntityName(this)));
      OCrashReportCategory.a(var1, this.l, this.m, this.n, this.q.cm, this.p);
   }

   // $FF: synthetic method
   static Map t() {
      return b;
   }

   static {
      a(OTileEntityFurnace.class, "Furnace");
      a(OTileEntityChest.class, "Chest");
      a(OTileEntityEnderChest.class, "EnderChest");
      a(OTileEntityRecordPlayer.class, "RecordPlayer");
      a(OTileEntityDispenser.class, "Trap");
      a(OTileEntitySign.class, "Sign");
      a(OTileEntityMobSpawner.class, "MobSpawner");
      a(OTileEntityNote.class, "Music");
      a(OTileEntityPiston.class, "Piston");
      a(OTileEntityBrewingStand.class, "Cauldron");
      a(OTileEntityEnchantmentTable.class, "EnchantTable");
      a(OTileEntityEndPortal.class, "Airportal");
      a(OTileEntityCommandBlock.class, "Control");
      a(OTileEntityBeacon.class, "Beacon");
      a(OTileEntitySkull.class, "Skull");
   }
}
