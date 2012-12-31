import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class OCommandHandler implements OICommandManager {

    private final Map a = new HashMap();
    private final Set b = new HashSet();

    public OCommandHandler() {}

    public void a(final OICommandSender oicommandsender, String s) { // CanaryMod: add final
        if (s.startsWith("/")) {
            s = s.substring(1);
        }

        String[] astring = s.split(" ");
        String s1 = astring[0];

        // CanaryMod start: Do our own parsing
        if (oicommandsender instanceof OMinecraftServer
                && etc.getInstance().parseConsoleCommand(s, (OMinecraftServer) oicommandsender)) {
            return;
        } else {
            MessageReceiver m;
            if (oicommandsender instanceof OEntityPlayerMP) {
                m = ((OEntityPlayerMP) oicommandsender).getPlayer();
            } else if (oicommandsender instanceof OTileEntityCommandBlock) {
                m = ((OTileEntityCommandBlock) oicommandsender).getComplexBlock();
            } else {
                m = new MessageReceiver() {

                    @Override
                    public String getName() {
                        return oicommandsender.c_();
                    }

                    @Override
                    public void notify(String message) {
                        oicommandsender.a(message);
                    }
                };
            }
            if (ServerConsoleCommands.parseServerConsoleCommand(m, s1, astring)) {
                return;
            }
        } // CanaryMod end

        astring = a(astring);
        OICommand oicommand = (OICommand) this.a.get(s1);
        int i = this.a(oicommand, astring);

        try {
            if (oicommand == null) {
                throw new OCommandNotFoundException();
            }

            if (oicommand.b(oicommandsender)) {
                if (i > -1) {
                    OEntityPlayerMP[] aoentityplayermp = OPlayerSelector.c(oicommandsender, astring[i]);
                    String s2 = astring[i];
                    OEntityPlayerMP[] aoentityplayermp1 = aoentityplayermp;
                    int j = aoentityplayermp.length;

                    for (int k = 0; k < j; ++k) {
                        OEntityPlayerMP oentityplayermp = aoentityplayermp1[k];

                        astring[i] = oentityplayermp.an();

                        try {
                            oicommand.b(oicommandsender, astring);
                        } catch (OPlayerNotFoundException oplayernotfoundexception) {
                            oicommandsender.a("\u00A7c" + oicommandsender.a(oplayernotfoundexception.getMessage(), oplayernotfoundexception.a()));
                        }
                    }

                    astring[i] = s2;
                } else {
                    oicommand.b(oicommandsender, astring);
                }
            } else {
                oicommandsender.a("\u00A7cYou do not have permission to use this command.");
            }
        } catch (OWrongUsageException owrongusageexception) {
            oicommandsender.a("\u00A7c" + oicommandsender.a("commands.generic.usage", new Object[] { oicommandsender.a(owrongusageexception.getMessage(), owrongusageexception.a())}));
        } catch (OCommandException ocommandexception) {
            oicommandsender.a("\u00A7c" + oicommandsender.a(ocommandexception.getMessage(), ocommandexception.a()));
        } catch (Throwable throwable) {
            oicommandsender.a("\u00A7c" + oicommandsender.a("commands.generic.exception", new Object[0]));
            throwable.printStackTrace();
        }
    }

    public OICommand a(OICommand oicommand) {
        List list = oicommand.b();

        this.a.put(oicommand.c(), oicommand);
        this.b.add(oicommand);
        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();
                OICommand oicommand1 = (OICommand) this.a.get(s);

                if (oicommand1 == null || !oicommand1.c().equals(s)) {
                    this.a.put(s, oicommand);
                }
            }
        }

        return oicommand;
    }

    private static String[] a(String[] astring) {
        String[] astring1 = new String[astring.length - 1];

        for (int i = 1; i < astring.length; ++i) {
            astring1[i - 1] = astring[i];
        }

        return astring1;
    }

    public List b(OICommandSender oicommandsender, String s) {
        String[] astring = s.split(" ", -1);
        String s1 = astring[0];

        if (astring.length == 1) {
            ArrayList arraylist = new ArrayList();
            Iterator iterator = this.a.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry entry = (Entry) iterator.next();

                if (OCommandBase.a(s1, (String) entry.getKey()) && ((OICommand) entry.getValue()).b(oicommandsender)) {
                    arraylist.add(entry.getKey());
                }
            }

            return arraylist;
        } else {
            if (astring.length > 1) {
                OICommand oicommand = (OICommand) this.a.get(s1);

                if (oicommand != null) {
                    return oicommand.a(oicommandsender, a(astring));
                }
            }

            return null;
        }
    }

    public List a(OICommandSender oicommandsender) {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            OICommand oicommand = (OICommand) iterator.next();

            if (oicommand.b(oicommandsender)) {
                arraylist.add(oicommand);
            }
        }

        return arraylist;
    }

    public Map a() {
        return this.a;
    }

    private int a(OICommand oicommand, String[] astring) {
        if (oicommand == null) {
            return -1;
        } else {
            for (int i = 0; i < astring.length; ++i) {
                if (oicommand.a(i) && OPlayerSelector.a(astring[i])) {
                    return i;
                }
            }

            return -1;
        }
    }
}
