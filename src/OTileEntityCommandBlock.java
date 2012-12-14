
public class OTileEntityCommandBlock extends OTileEntity implements OICommandSender {

   private String a = "";
   protected String prefix = "@"; //CanaryMod: allows us to set this block's text prefix


   public void b(String var1) {
      this.a = var1;
      this.d();
   }

   public void a(OWorld var1) {
      if(!var1.J) {
         OMinecraftServer var2 = OMinecraftServer.D();
         if(var2 != null && var2.Z()) {
            OICommandManager var3 = var2.E();
            var3.a(this, this.a);
         }

      }
   }

   public String c_() {
      return prefix; //CanaryMod: allows us to set this block's text prefix
   }

   public void a(String var1) {}

   public boolean a(int var1, String var2) {
      return var1 <= 2;
   }

   public String a(String var1, Object ... var2) {
      return var1;
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("Command", this.a);
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      this.a = var1.i("Command");
   }

   public OChunkCoordinates b() {
      return new OChunkCoordinates(this.l, this.m, this.n);
   }

   public OPacket l() {
      ONBTTagCompound var1 = new ONBTTagCompound();
      this.b(var1);
      return new OPacket132TileEntityData(this.l, this.m, this.n, 2, var1);
   }

   public String getCommand() { //CanaryMod: allows us to access the command stored
       return this.a;
   }
}
