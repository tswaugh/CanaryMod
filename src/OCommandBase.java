import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class OCommandBase implements OICommand {

    private static OIAdminCommand a = null;

    public OCommandBase() {}

    public String a(OICommandSender oicommandsender) {
        return "/" + this.b();
    }

    public List a() {
        return null;
    }

    public boolean b(OICommandSender oicommandsender) {
        return oicommandsender.b(this.b());
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        return null;
    }

    public static int a(OICommandSender oicommandsender, String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException numberformatexception) {
            throw new ONumberInvalidException("commands.generic.num.invalid", new Object[] { s});
        }
    }

    public static int a(OICommandSender oicommandsender, String s, int i) {
        return a(oicommandsender, s, i, Integer.MAX_VALUE);
    }

    public static int a(OICommandSender oicommandsender, String s, int i, int j) {
        int k = a(oicommandsender, s);

        if (k < i) {
            throw new ONumberInvalidException("commands.generic.num.tooSmall", new Object[] { Integer.valueOf(k), Integer.valueOf(i)});
        } else if (k > j) {
            throw new ONumberInvalidException("commands.generic.num.tooBig", new Object[] { Integer.valueOf(k), Integer.valueOf(j)});
        } else {
            return k;
        }
    }

    public static OEntityPlayer c(OICommandSender oicommandsender) {
        if (oicommandsender instanceof OEntityPlayer) {
            return (OEntityPlayer) oicommandsender;
        } else {
            throw new OPlayerNotFoundException("You must specify which player you wish to perform this action on.", new Object[0]);
        }
    }

    public static String a(String[] astring, int i) {
        StringBuilder stringbuilder = new StringBuilder();

        for (int j = i; j < astring.length; ++j) {
            if (j > i) {
                stringbuilder.append(" ");
            }

            stringbuilder.append(astring[j]);
        }

        return stringbuilder.toString();
    }

    public static String a(Object[] aobject) {
        StringBuilder stringbuilder = new StringBuilder();

        for (int i = 0; i < aobject.length; ++i) {
            String s = aobject[i].toString();

            if (i > 0) {
                if (i == aobject.length - 1) {
                    stringbuilder.append(" and ");
                } else {
                    stringbuilder.append(", ");
                }
            }

            stringbuilder.append(s);
        }

        return stringbuilder.toString();
    }

    public static boolean a(String s, String s1) {
        return s1.regionMatches(true, 0, s, 0, s.length());
    }

    public static List a(String[] astring, String... astring1) {
        String s = astring[astring.length - 1];
        ArrayList arraylist = new ArrayList();
        String[] astring2 = astring;
        int i = astring1.length;

        for (int j = 0; j < i; ++j) {
            String s1 = astring2[j];

            if (a(s, s1)) {
                arraylist.add(s1);
            }
        }

        return arraylist;
    }

    public static List a(String[] astring, Iterable iterable) {
        String s = astring[astring.length - 1];
        ArrayList arraylist = new ArrayList();
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            String s1 = (String) iterator.next();

            if (a(s, s1)) {
                arraylist.add(s1);
            }
        }

        return arraylist;
    }

    public static void a(OICommandSender oicommandsender, String s, Object... aobject) {
        a(oicommandsender, 0, s, aobject);
    }

    public static void a(OICommandSender oicommandsender, int i, String s, Object... aobject) {
        if (a != null) {
            a.a(oicommandsender, i, s, aobject);
        }
    }

    public static void a(OIAdminCommand oiadmincommand) {
        a = oiadmincommand;
    }

    public int a(OICommand oicommand) {
        return this.b().compareTo(oicommand.b());
    }

    public int compareTo(Object object) {
        return this.a((OICommand) object);
    }
}
