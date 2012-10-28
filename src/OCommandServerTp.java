import java.util.List;

public class OCommandServerTp extends OCommandBase {

    public OCommandServerTp() {}

    public String c() {
        //JarJar: begin fix
        return "tp";
        //JarJar: end fix
    }

    public int a() {
        return 2;
    }

    public String a(OICommandSender oicommandsender) {
        return oicommandsender.a("commands.tp.usage", new Object[0]);
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        if (astring.length < 1) {
            throw new OWrongUsageException("commands.tp.usage", new Object[0]);
        } else {
            OEntityPlayerMP oentityplayermp;

            if (astring.length != 2 && astring.length != 4) {
                oentityplayermp = c(oicommandsender);
            } else {
                oentityplayermp = c(oicommandsender, astring[0]);
                if (oentityplayermp == null) {
                    throw new OPlayerNotFoundException();
                }
            }

            if (astring.length != 3 && astring.length != 4) {
                if (astring.length == 1 || astring.length == 2) {
                    OEntityPlayerMP oentityplayermp1 = c(oicommandsender, astring[astring.length - 1]);

                    if (oentityplayermp1 == null) {
                        throw new OPlayerNotFoundException();
                    }

                    oentityplayermp.a.a(oentityplayermp1.t, oentityplayermp1.u, oentityplayermp1.v, oentityplayermp1.z, oentityplayermp1.A);
                    a(oicommandsender, "commands.tp.success", new Object[] { oentityplayermp.an(), oentityplayermp1.an()});
                }
            } else if (oentityplayermp.p != null) {
                int i = astring.length - 3;
                double d0 = this.a(oicommandsender, oentityplayermp.t, astring[i++]);
                double d1 = this.a(oicommandsender, oentityplayermp.u, astring[i++], 0, 0);
                double d2 = this.a(oicommandsender, oentityplayermp.v, astring[i++]);

                oentityplayermp.a(d0, d1, d2);
                a(oicommandsender, "commands.tp.success.coordinates", new Object[] { oentityplayermp.an(), Double.valueOf(d0), Double.valueOf(d1), Double.valueOf(d2)});
            }
        }
    }

    private double a(OICommandSender oicommandsender, double d0, String s) {
        return this.a(oicommandsender, d0, s, -30000000, 30000000);
    }

    private double a(OICommandSender oicommandsender, double d0, String s, int i, int j) {
        boolean flag = s.startsWith("~");
        double d1 = flag ? d0 : 0.0D;

        if (!flag || s.length() > 1) {
            boolean flag1 = s.contains(".");

            if (flag) {
                s = s.substring(1);
            }

            d1 += b(oicommandsender, s);
            if (!flag1 && !flag) {
                d1 += 0.5D;
            }
        }

        if (i != 0 || j != 0) {
            if (d1 < (double) i) {
                throw new ONumberInvalidException("commands.generic.double.tooSmall", new Object[] { Double.valueOf(d1), Integer.valueOf(i)});
            }

            if (d1 > (double) j) {
                throw new ONumberInvalidException("commands.generic.double.tooBig", new Object[] { Double.valueOf(d1), Integer.valueOf(j)});
            }
        }

        return d1;
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        return astring.length != 1 && astring.length != 2 ? null : a(astring, OMinecraftServer.D().A());
    }

    public boolean a(int i) {
        return i == 0;
    }
}
