public class OCommandServerSaveOn extends OCommandBase {

    public OCommandServerSaveOn() {}

    public String b() {
        return "save-on";
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        OMinecraftServer ominecraftserver = OMinecraftServer.C();

        for (OWorldServer[] level : ominecraftserver.worlds.values()) { // CanaryMod: multiworld
            for (int i = 0; i < level.length; ++i) {
                if (level[i] != null) {
                    OWorldServer oworldserver = level[i];

                    oworldserver.d = false;
                }
            }
        }

        a(oicommandsender, "commands.save.enabled", new Object[0]);
    }
}
