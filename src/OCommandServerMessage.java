import java.util.Arrays;
import java.util.List;

public class OCommandServerMessage extends OCommandBase {

    public OCommandServerMessage() {}

    public List b() {
        //JarJar: begin fix
        return Arrays.asList(new String[] { "w", "msg"});
        //JarJar: end fix
    }

    public String c() {
        return "tell";
    }

    public int a() {
        return 0;
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        if (astring.length < 2) {
            throw new OWrongUsageException("commands.message.usage", new Object[0]);
        } else {
            OEntityPlayerMP oentityplayermp = c(oicommandsender, astring[0]);

            if (oentityplayermp == null) {
                throw new OPlayerNotFoundException();
            } else if (oentityplayermp == oicommandsender) {
                throw new OPlayerNotFoundException("commands.message.sameTarget", new Object[0]);
            } else {
                String s = a(oicommandsender, astring, 1, !(oicommandsender instanceof OEntityPlayer));

                oentityplayermp.a("\u00A77\u00A7o" + oentityplayermp.a("commands.message.display.incoming", new Object[] { oicommandsender.c_(), s}));
                oicommandsender.a("\u00A77\u00A7o" + oicommandsender.a("commands.message.display.outgoing", new Object[] { oentityplayermp.c_(), s}));
            }
        }
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        return a(astring, OMinecraftServer.D().A());
    }

    public boolean a(int i) {
        return i == 0;
    }
}
