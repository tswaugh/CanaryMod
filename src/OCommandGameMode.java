import java.util.List;

public class OCommandGameMode extends OCommandBase {

    public OCommandGameMode() {}

    public String c() {
        return "gamemode";
    }

    public int a() {
        return 2;
    }

    public String a(OICommandSender oicommandsender) {
        return oicommandsender.a("commands.gamemode.usage", new Object[0]);
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        if (astring.length > 0) {
            OEnumGameType oenumgametype = this.d(oicommandsender, astring[0]);
            OEntityPlayerMP oentityplayermp = astring.length >= 2 ? c(oicommandsender, astring[1]) : c(oicommandsender);

            oentityplayermp.a(oenumgametype);
            String s = OStatCollector.a("gameMode." + oenumgametype.b());

            if (oentityplayermp != oicommandsender) {
                a(oicommandsender, 1, "commands.gamemode.success.other", new Object[] { oentityplayermp.an(), s});
            } else {
                a(oicommandsender, 1, "commands.gamemode.success.self", new Object[] { s});
            }
        } else {
            throw new OWrongUsageException("commands.gamemode.usage", new Object[0]);
        }
    }

    protected OEnumGameType d(OICommandSender oicommandsender, String s) {
        //JarJar: begin fix
        return !s.equalsIgnoreCase(OEnumGameType.b.b()) && !s.equalsIgnoreCase("s") ? (!s.equalsIgnoreCase(OEnumGameType.c.b()) && !s.equalsIgnoreCase("c") ? (!s.equalsIgnoreCase(OEnumGameType.d.b()) && !s.equalsIgnoreCase("a") ? OWorldSettings.a(a(oicommandsender, s, 0, OEnumGameType.values().length - 2)) : OEnumGameType.d) : OEnumGameType.c) : OEnumGameType.b;
        //JarJar: end fix
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "survival", "creative", "adventure"}) : (astring.length == 2 ? a(astring, this.d()) : null);
    }

    protected String[] d() {
        return OMinecraftServer.D().A();
    }

    public boolean a(int i) {
        return i == 1;
    }
}
