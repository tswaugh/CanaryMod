import java.util.List;

public class OCommandServerEmote extends OCommandBase {

    public OCommandServerEmote() {}

    public String c() {
        //JarJar: begin fix
        return "me";
        //JarJar: end fix
    }

    public int a() {
        return 0;
    }

    public String a(OICommandSender oicommandsender) {
        return oicommandsender.a("commands.me.usage", new Object[0]);
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        if (astring.length > 0) {
            String s = a(oicommandsender, astring, 0);

            OMinecraftServer.D().ad().a((OPacket) (new OPacket3Chat("* " + oicommandsender.c_() + " " + s)));
        } else {
            throw new OWrongUsageException("commands.me.usage", new Object[0]);
        }
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        return a(astring, OMinecraftServer.D().A());
    }
}
