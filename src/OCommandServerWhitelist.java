import java.util.ArrayList;
import java.util.List;

public class OCommandServerWhitelist extends OCommandBase {

    public OCommandServerWhitelist() {}

    public String c() {
        return "whitelist";
    }

    public int a() {
        return 3;
    }

    public String a(OICommandSender oicommandsender) {
        return oicommandsender.a("commands.whitelist.usage", new Object[0]);
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        if (astring.length >= 1) {
            //JarJar: begin fix
            if (astring[0].equals("on")) {
                OMinecraftServer.D().ad().a(true);
                //JarJar: end fix
                a(oicommandsender, "commands.whitelist.enabled", new Object[0]);
                return;
            }

            if (astring[0].equals("off")) {
                OMinecraftServer.D().ad().a(false);
                a(oicommandsender, "commands.whitelist.disabled", new Object[0]);
                return;
            }

            if (astring[0].equals("list")) {
                oicommandsender.a(oicommandsender.a("commands.whitelist.list", new Object[] { Integer.valueOf(OMinecraftServer.D().ad().h().size()), Integer.valueOf(OMinecraftServer.D().ad().m().length)}));
                oicommandsender.a(a(OMinecraftServer.D().ad().h().toArray(new String[0])));
                return;
            }
            //JarJar: begin fix
            if (astring[0].equals("add")) {
                //JarJar: end fix
                if (astring.length < 2) {
                    throw new OWrongUsageException("commands.whitelist.add.usage", new Object[0]);
                }

                OMinecraftServer.D().ad().h(astring[1]);
                a(oicommandsender, "commands.whitelist.add.success", new Object[] { astring[1]});
                return;
            }

            if (astring[0].equals("remove")) {
                if (astring.length < 2) {
                    throw new OWrongUsageException("commands.whitelist.remove.usage", new Object[0]);
                }

                OMinecraftServer.D().ad().i(astring[1]);
                a(oicommandsender, "commands.whitelist.remove.success", new Object[] { astring[1]});
                return;
            }

            if (astring[0].equals("reload")) {
                OMinecraftServer.D().ad().j();
                a(oicommandsender, "commands.whitelist.reloaded", new Object[0]);
                return;
            }
        }

        throw new OWrongUsageException("commands.whitelist.usage", new Object[0]);
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        if (astring.length == 1) {
            //JarJar: begin fix
            return a(astring, new String[] { "on", "off", "list", "add", "remove", "reload"});
            //JarJar: end fix
        } else {
            if (astring.length == 2) {
                //JarJar: begin fix
                if (astring[0].equals("add")) {
                    //JarJar: end fix
                    String[] astring1 = OMinecraftServer.D().ad().m();
                    ArrayList arraylist = new ArrayList();
                    String s = astring[astring.length - 1];
                    String[] astring2 = astring1;
                    int i = astring1.length;

                    for (int j = 0; j < i; ++j) {
                        String s1 = astring2[j];

                        if (a(s, s1) && !OMinecraftServer.D().ad().h().contains(s1)) {
                            arraylist.add(s1);
                        }
                    }

                    return arraylist;
                }

                if (astring[0].equals("remove")) {
                    return a(astring, OMinecraftServer.D().ad().h());
                }
            }

            return null;
        }
    }
}
