public class OTileEntityCommandBlock extends OTileEntity implements OICommandSender {

    private String a = "";
    protected String prefix = "@"; // CanaryMod: allows us to set this block's text prefix
    private final CommandBlock block = new CommandBlock(this); // CanaryMod

    public OTileEntityCommandBlock() {}

    public void b(String s) {
        this.a = s;
        this.d();
    }

    public void a(OWorld oworld) {
        if (!oworld.I) {
            OMinecraftServer ominecraftserver = OMinecraftServer.D();

            if (ominecraftserver != null && ominecraftserver.Z()) {
                OICommandManager oicommandmanager = ominecraftserver.E();

                if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMMAND_BLOCK_COMMAND, new CommandBlock(this), a.split(" "))) {
                    oicommandmanager.a(this, this.a);
                }
            }
        }
    }

    public String c_() {
        return prefix; // CanaryMod: allows us to set this block's text prefix
    }

    public void a(String s) {}

    public boolean a(int i, String s) {
        return i <= 2;
    }

    public String a(String s, Object... aobject) {
        return s;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Command", this.a);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a = onbttagcompound.i("Command");
    }

    public OChunkCoordinates b() {
        return new OChunkCoordinates(this.l, this.m, this.n);
    }

    public OPacket l() {
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        this.b(onbttagcompound);
        return new OPacket132TileEntityData(this.l, this.m, this.n, 2, onbttagcompound);
    }

    public String getCommand() { // CanaryMod: allows us to access the command stored
        return this.a;
    }

    @Override
    public CommandBlock getComplexBlock() {
        return block;
    }
}
