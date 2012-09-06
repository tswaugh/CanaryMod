public class OCommandServerSaveOff extends OCommandBase {

    public OCommandServerSaveOff() {}

    public String b() {
        return "save-off";
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        OMinecraftServer ominecraftserver = OMinecraftServer.C();

        for (OWorldServer[] level : ominecraftserver.worlds.values()) { // CanaryMod: multiworld 
            for (int i = 0; i < level.length; ++i) {
                if (level[i] != null) {
                    OWorldServer oworldserver = level[i];

                    oworldserver.d = true;
                }
            }
        }

        a(oicommandsender, "commands.save.disabled", new Object[0]);
    }
}
