import java.util.List;

public class OCommandXP extends OCommandBase {

    public OCommandXP() {}

    public String c() {
        //JarJar: begin fix
        return "xp";
        //JarJar: end fix
    }

    public int a() {
        return 2;
    }

    public String a(OICommandSender oicommandsender) {
        return oicommandsender.a("commands.xp.usage", new Object[0]);
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        if (astring.length <= 0) {
            throw new OWrongUsageException("commands.xp.usage", new Object[0]);
        } else {
            String s = astring[0];
            //JarJar: begin fix
            boolean flag = s.endsWith("l") || s.endsWith("L");
            //JarJar: end fix
            if (flag && s.length() > 1) {
                s = s.substring(0, s.length() - 1);
            }

            int i = a(oicommandsender, s);
            boolean flag1 = i < 0;

            if (flag1) {
                i *= -1;
            }

            OEntityPlayerMP oentityplayermp;

            if (astring.length > 1) {
                oentityplayermp = c(oicommandsender, astring[1]);
            } else {
                oentityplayermp = c(oicommandsender);
            }

            if (flag) {
                if (flag1) {
                    oentityplayermp.a(-i);
                    a(oicommandsender, "commands.xp.success.negative.levels", new Object[] { Integer.valueOf(i), oentityplayermp.an()});
                } else {
                    oentityplayermp.a(i);
                    a(oicommandsender, "commands.xp.success.levels", new Object[] { Integer.valueOf(i), oentityplayermp.an()});
                }
            } else {
                if (flag1) {
                    throw new OWrongUsageException("commands.xp.failure.widthdrawXp", new Object[0]);
                }

                oentityplayermp.t(i);
                a(oicommandsender, "commands.xp.success", new Object[] { Integer.valueOf(i), oentityplayermp.an()});
            }
        }
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        return astring.length == 2 ? a(astring, this.d()) : null;
    }

    protected String[] d() {
        return OMinecraftServer.D().A();
    }

    public boolean a(int i) {
        return i == 1;
    }
}
