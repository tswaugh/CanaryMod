public class OCommandServerSaveAll extends OCommandBase {

    public OCommandServerSaveAll() {}

    public String b() {
        return "save-all";
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        OMinecraftServer ominecraftserver = OMinecraftServer.C();

        oicommandsender.a(oicommandsender.a("commands.save.start", new Object[0]));
        if (ominecraftserver.ab() != null) {
            ominecraftserver.ab().g();
        }

        for (OWorldServer[] level: ominecraftserver.worlds.values()) { // CanaryMod: multiworld
            for (int i = 0; i < level.length; ++i) {
                if (level[i] != null) {
                    OWorldServer oworldserver = level[i];
                    boolean flag = oworldserver.d;

                    oworldserver.d = false;
                    oworldserver.a(true, (OIProgressUpdate) null);
                    oworldserver.d = flag;
                }
            }
        }

        a(oicommandsender, "commands.save.success", new Object[0]);
    }
}
