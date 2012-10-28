import java.util.List;

public class OCommandDifficulty extends OCommandBase {

    private static final String[] a = new String[] { "options.difficulty.peaceful", "options.difficulty.easy", "options.difficulty.normal", "options.difficulty.hard"};

    public OCommandDifficulty() {}

    public String c() {
        return "difficulty";
    }

    public int a() {
        return 2;
    }

    public String a(OICommandSender oicommandsender) {
        return oicommandsender.a("commands.difficulty.usage", new Object[0]);
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        if (astring.length > 0) {
            int i = this.d(oicommandsender, astring[0]);

            OMinecraftServer.D().c(i);
            String s = OStatCollector.a(a[i]);

            a(oicommandsender, 1, "commands.difficulty.success", new Object[] { s});
        } else {
            throw new OWrongUsageException("commands.difficulty.usage", new Object[0]);
        }
    }

    protected int d(OICommandSender oicommandsender, String s) {
        //JarJar: begin fix
        return !s.equalsIgnoreCase("peaceful") && !s.equalsIgnoreCase("p") ? (!s.equalsIgnoreCase("easy") && !s.equalsIgnoreCase("e") ? (!s.equalsIgnoreCase("normal") && !s.equalsIgnoreCase("n") ? (!s.equalsIgnoreCase("hard") && !s.equalsIgnoreCase("h") ? a(oicommandsender, s, 0, 3) : 3) : 2) : 1) : 0;
        //JarJar: end fix
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "peaceful", "easy", "normal", "hard"}) : null;
    }
}
