import java.util.ArrayList;
import java.util.List;

public class OCommandServerOp extends OCommandBase {

    public OCommandServerOp() {}

    public String c() {
        //JarJar: begin fix
        return "op";
        //JarJar: end fix
    }

    public int a() {
        return 3;
    }

    public String a(OICommandSender oicommandsender) {
        return oicommandsender.a("commands.op.usage", new Object[0]);
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        if (astring.length == 1 && astring[0].length() > 0) {
            OMinecraftServer.D().ad().b(astring[0]);
            a(oicommandsender, "commands.op.success", new Object[] { astring[0]});
        } else {
            throw new OWrongUsageException("commands.op.usage", new Object[0]);
        }
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        if (astring.length == 1) {
            String s = astring[astring.length - 1];
            ArrayList arraylist = new ArrayList();
            String[] astring1 = OMinecraftServer.D().A();
            int i = astring1.length;

            for (int j = 0; j < i; ++j) {
                String s1 = astring1[j];

                if (!OMinecraftServer.D().ad().e(s1) && a(s, s1)) {
                    arraylist.add(s1);
                }
            }

            return arraylist;
        } else {
            return null;
        }
    }
}
