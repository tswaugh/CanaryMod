import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;


public class OConsoleCommandHandler {

    private static Logger a = Logger.getLogger("Minecraft");
    private OMinecraftServer b;

    public OConsoleCommandHandler(OMinecraftServer ominecraftserver) {
        super();
        this.b = ominecraftserver;
    }

    public synchronized void a(OServerCommand oservercommand) {
        String s = oservercommand.a;
        String[] astring = s.split(" ");
        String s1 = astring[0];
        String s2 = s.substring(s1.length()).trim();
        OICommandListener oicommandlistener = oservercommand.b;
        String s3 = oicommandlistener.d();
        OServerConfigurationManager oserverconfigurationmanager = this.b.h;

        if (!s1.equalsIgnoreCase("help") && !s1.equalsIgnoreCase("?")) {
            if (s1.equalsIgnoreCase("list")) {
                oicommandlistener.b("Connected players: " + oserverconfigurationmanager.c());
            } else if (s1.equalsIgnoreCase("stop")) {
                this.a(s3, "Stopping the server..");
                this.b.a();
            } else {
                int i;
                OWorldServer oworldserver;

                if (s1.equalsIgnoreCase("save-all")) {
                    this.a(s3, "Forcing save..");
                    if (oserverconfigurationmanager != null) {
                        oserverconfigurationmanager.g();
                    }
                    
                    for (OWorldServer[] level : this.b.worlds.values()) { // CanaryMod: multiworld
                        for (i = 0; i < level.length; ++i) {
                            oworldserver = level[i];
                            boolean flag = oworldserver.I;

                            oworldserver.I = false;
                            oworldserver.a(true, (OIProgressUpdate) null);
                            oworldserver.I = flag;
                        }
                    }

                    this.a(s3, "Save complete.");
                } else if (s1.equalsIgnoreCase("save-off")) {
                    this.a(s3, "Disabling level saving..");

                    for (OWorldServer[] level : this.b.worlds.values()) { // CanaryMod: multiworld
                        for (i = 0; i < level.length; ++i) {
                            oworldserver = level[i];
                            oworldserver.I = true;
                        }
                    }
                } else if (s1.equalsIgnoreCase("save-on")) {
                    this.a(s3, "Enabling level saving..");

                    for (OWorldServer[] level : this.b.worlds.values()) { // CanaryMod: multiworld
                        for (i = 0; i < level.length; ++i) {
                            oworldserver = level[i];
                            oworldserver.I = false;
                        }
                    }
                } else if (s1.equalsIgnoreCase("op")) {
                    oserverconfigurationmanager.e(s2);
                    this.a(s1, "Opping " + s2);
                    oserverconfigurationmanager.a(s2, "\u00a7eYou are now op!");
                } else if (s1.equalsIgnoreCase("deop")) {
                    oserverconfigurationmanager.f(s2);
                    oserverconfigurationmanager.a(s2, "\u00a7eYou are no longer op!");
                    this.a(s3, "De-opping " + s2);
                } else if (s1.equalsIgnoreCase("ban-ip")) {
                    oserverconfigurationmanager.c(s2);
                    this.a(s3, "Banning ip " + s2);
                } else if (s1.equalsIgnoreCase("pardon-ip")) {
                    oserverconfigurationmanager.d(s2);
                    this.a(s3, "Pardoning ip " + s2);
                } else {
                    OEntityPlayerMP oentityplayermp;

                    if (s1.equalsIgnoreCase("ban")) {
                        oserverconfigurationmanager.a(s2);
                        this.a(s1, "Banning " + s2);
                        oentityplayermp = oserverconfigurationmanager.i(s2);
                        if (oentityplayermp != null) {
                            oentityplayermp.a.a("Banned by admin");
                        }
                    } else if (s1.equalsIgnoreCase("pardon")) {
                        oserverconfigurationmanager.b(s2);
                        this.a(s1, "Pardoning " + s2);
                    } else {
                        String s4;
                        int j;

                        if (s1.equalsIgnoreCase("kick")) {
                            s4 = s2;
                            oentityplayermp = null;

                            for (j = 0; j < oserverconfigurationmanager.b.size(); ++j) {
                                OEntityPlayerMP oentityplayermp1 = (OEntityPlayerMP) oserverconfigurationmanager.b.get(j);

                                if (oentityplayermp1.v.equalsIgnoreCase(s2)) {
                                    oentityplayermp = oentityplayermp1;
                                }
                            }

                            if (oentityplayermp != null) {
                                oentityplayermp.a.a("Kicked by admin");
                                this.a(s1, "Kicking " + oentityplayermp.v);
                            } else {
                                oicommandlistener.b("Can\'t find user " + s4 + ". No kick.");
                            }
                        } else if (s1.equalsIgnoreCase("tp")) {
                            if (astring.length == 3) {
                                OEntityPlayerMP oentityplayermp2 = oserverconfigurationmanager.i(astring[1]);

                                oentityplayermp = oserverconfigurationmanager.i(astring[2]);
                                if (oentityplayermp2 == null) {
                                    oicommandlistener.b("Can\'t find user " + astring[1] + ". No tp.");
                                } else if (oentityplayermp == null) {
                                    oicommandlistener.b("Can\'t find user " + astring[2] + ". No tp.");
                                } else if (oentityplayermp2.w != oentityplayermp.w) {
                                    oicommandlistener.b("User " + astring[1] + " and " + astring[2] + " are in different dimensions. No tp.");
                                } else {
                                    oentityplayermp2.a.a(oentityplayermp.bm, oentityplayermp.bn, oentityplayermp.bo, oentityplayermp.bs, oentityplayermp.bt, oentityplayermp.w, oentityplayermp.bi.name);
                                    this.a(s3, "Teleporting " + astring[1] + " to " + astring[2] + ".");
                                }
                            } else {
                                oicommandlistener.b("Syntax error, please provice a source and a target.");
                            }
                        } else if (s1.equalsIgnoreCase("give")) {
                            if (astring.length != 3 && astring.length != 4 && astring.length != 5) {
                                return;
                            }

                            s4 = astring[1];
                            oentityplayermp = oserverconfigurationmanager.i(s4);
                            if (oentityplayermp != null) {
                                try {
                                    j = Integer.parseInt(astring[2]);
                                    if (OItem.d[j] != null) {
                                        this.a(s3, "Giving " + oentityplayermp.v + " some " + j);
                                        int k = 1;
                                        int l = 0;

                                        if (astring.length > 3) {
                                            k = this.a(astring[3], 1);
                                        }

                                        if (astring.length > 4) {
                                            l = this.a(astring[4], 1);
                                        }

                                        if (k < 1) {
                                            k = 1;
                                        }

                                        if (k > 64) {
                                            k = 64;
                                        }

                                        oentityplayermp.b(new OItemStack(j, k, l));
                                    } else {
                                        oicommandlistener.b("There\'s no item with id " + j);
                                    }
                                } catch (NumberFormatException numberformatexception) {
                                    oicommandlistener.b("There\'s no item with id " + astring[2]);
                                }
                            } else {
                                oicommandlistener.b("Can\'t find user " + s4);
                            }
                        } else if (s1.equalsIgnoreCase("xp")) {
                            if (astring.length != 3) {
                                return;
                            }

                            s4 = astring[1];
                            oentityplayermp = oserverconfigurationmanager.i(s4);
                            if (oentityplayermp != null) {
                                try {
                                    j = Integer.parseInt(astring[2]);
                                    j = j > 5000 ? 5000 : j;
                                    this.a(s3, "Giving " + j + " orbs to " + oentityplayermp.v);
                                    oentityplayermp.g(j);
                                } catch (NumberFormatException numberformatexception1) {
                                    oicommandlistener.b("Invalid orb count: " + astring[2]);
                                }
                            } else {
                                oicommandlistener.b("Can\'t find user " + s4);
                            }
                        } else if (s1.equalsIgnoreCase("gamemode")) {
                            if (astring.length != 3) {
                                return;
                            }

                            s4 = astring[1];
                            oentityplayermp = oserverconfigurationmanager.i(s4);
                            if (oentityplayermp != null) {
                                try {
                                    j = Integer.parseInt(astring[2]);
                                    j = OWorldSettings.a(j);
                                    if (oentityplayermp.c.a() != j) {
                                        this.a(s3, "Setting " + oentityplayermp.v + " to game mode " + j);
                                        oentityplayermp.c.a(j);
                                        oentityplayermp.a.b((OPacket) (new OPacket70Bed(3, j)));
                                    } else {
                                        this.a(s3, oentityplayermp.v + " already has game mode " + j);
                                    }
                                } catch (NumberFormatException numberformatexception2) {
                                    oicommandlistener.b("There\'s no game mode with id " + astring[2]);
                                }
                            } else {
                                oicommandlistener.b("Can\'t find user " + s4);
                            }
                            // CanaryMod: We have our own time command
                            /* } else if(s1.equalsIgnoreCase("time")) {
                             if(astring.length != 3) {
                             return;
                             }

                             s4 = astring[1];

                             try {
                             int i1 = Integer.parseInt(astring[2]);
                             OWorldServer oworldserver1;
                             if("add".equalsIgnoreCase(s4)) {
                             for(j = 0; j < this.b.e.length; ++j) {
                             oworldserver1 = this.b.e[j];
                             oworldserver1.b(oworldserver1.o() + (long)i1);
                             }

                             this.a(s3, "Added " + i1 + " to time");
                             } else if("set".equalsIgnoreCase(s4)) {
                             for(j = 0; j < this.b.e.length; ++j) {
                             oworldserver1 = this.b.e[j];
                             oworldserver1.b((long)i1);
                             }

                             this.a(s3, "Set time to " + i1);
                             } else {
                             oicommandlistener.b("Unknown method, use either \"add\" or \"set\"");
                             }
                             } catch (NumberFormatException numberformatexception3) {
                             oicommandlistener.b("Unable to convert time value, " + astring[2]);
                             }*/
                        } else if (s1.equalsIgnoreCase("say") && s2.length() > 0) {
                            a.info("[" + s3 + "] " + s2);
                            oserverconfigurationmanager.a((OPacket) (new OPacket3Chat("\u00a7d[Server] " + s2)));
                        } else if (s1.equalsIgnoreCase("tell")) {
                            if (astring.length >= 3) {
                                s = s.substring(s.indexOf(" ")).trim();
                                s = s.substring(s.indexOf(" ")).trim();
                                a.info("[" + s3 + "->" + astring[1] + "] " + s);
                                s = "\u00a77" + s3 + " whispers " + s;
                                a.info(s);
                                if (!oserverconfigurationmanager.a(astring[1], (OPacket) (new OPacket3Chat(s)))) {
                                    oicommandlistener.b("There\'s no player by that name online.");
                                }
                            }
                        } else if (s1.equalsIgnoreCase("whitelist")) {
                            this.a(s3, s, oicommandlistener);
                            // CanaryMod: We have our own weather command
                            /* } else if(s1.equalsIgnoreCase("toggledownfall")) {
                             this.b.e[0].j();
                             oicommandlistener.b("Toggling rain and snow, hold on...");*/
                        } else if (s1.equalsIgnoreCase("banlist")) {
                            if (astring.length == 2) {
                                if (astring[1].equals("ips")) {
                                    oicommandlistener.b("IP Ban list:" + this.a(this.b.q(), ", "));
                                }
                            } else {
                                oicommandlistener.b("Ban list:" + this.a(this.b.r(), ", "));
                            }
                        } else {
                            a.info("Unknown console command. Type \"help\" for help.");
                        }
                    }
                }
            }
        } else {
            this.a(oicommandlistener);
        }

    }

    private void a(String s, String s1, OICommandListener oicommandlistener) {
        String[] astring = s1.split(" ");

        if (astring.length >= 2) {
            String s2 = astring[1].toLowerCase();

            if ("on".equals(s2)) {
                this.a(s, "Turned on white-listing");
                this.b.d.b("white-list", true);
            } else if ("off".equals(s2)) {
                this.a(s, "Turned off white-listing");
                this.b.d.b("white-list", false);
            } else if ("list".equals(s2)) {
                Set set = this.b.h.h();
                String s3 = "";

                String s4;

                for (Iterator iterator = set.iterator(); iterator.hasNext(); s3 = s3 + s4 + " ") {
                    s4 = (String) iterator.next();
                }

                oicommandlistener.b("White-listed players: " + s3);
            } else {
                String s5;

                if ("add".equals(s2) && astring.length == 3) {
                    s5 = astring[2].toLowerCase();
                    this.b.h.k(s5);
                    this.a(s, "Added " + s5 + " to white-list");
                } else if ("remove".equals(s2) && astring.length == 3) {
                    s5 = astring[2].toLowerCase();
                    this.b.h.l(s5);
                    this.a(s, "Removed " + s5 + " from white-list");
                } else if ("reload".equals(s2)) {
                    this.b.h.i();
                    this.a(s, "Reloaded white-list from file");
                }
            }

        }
    }

    private void a(OICommandListener oicommandlistener) {
        oicommandlistener.b("To run the server without a gui, start it like this:");
        oicommandlistener.b("   java -Xmx1024M -Xms1024M -jar minecraft_server.jar nogui");
        oicommandlistener.b("Console commands:");
        oicommandlistener.b("   help  or  ?               shows this message");
        oicommandlistener.b("   kick <player>             removes a player from the server");
        oicommandlistener.b("   ban <player>              bans a player from the server");
        oicommandlistener.b("   pardon <player>           pardons a banned player so that they can connect again");
        oicommandlistener.b("   ban-ip <ip>               bans an IP address from the server");
        oicommandlistener.b("   pardon-ip <ip>            pardons a banned IP address so that they can connect again");
        oicommandlistener.b("   op <player>               turns a player into an op");
        oicommandlistener.b("   deop <player>             removes op status from a player");
        oicommandlistener.b("   tp <player1> <player2>    moves one player to the same location as another player");
        oicommandlistener.b("   give <player> <id> [num]  gives a player a resource");
        oicommandlistener.b("   tell <player> <message>   sends a private message to a player");
        oicommandlistener.b("   stop                      gracefully stops the server");
        oicommandlistener.b("   save-all                  forces a server-wide level save");
        oicommandlistener.b("   save-off                  disables terrain saving (useful for backup scripts)");
        oicommandlistener.b("   save-on                   re-enables terrain saving");
        oicommandlistener.b("   list                      lists all currently connected players");
        oicommandlistener.b("   say <message>             broadcasts a message to all players");
        oicommandlistener.b("   time <add|set> <amount>   adds to or sets the world time (0-24000)");
        oicommandlistener.b("   gamemode <player> <mode>  sets player\'s game mode (0 or 1)");
        oicommandlistener.b("   xp <player> <amount>      gives the player the amount of xp (0-5000)");
    }

    private void a(String s, String s1) {
        String s2 = s + ": " + s1;

        this.b.h.j("\u00a77(" + s2 + ")");
        a.info(s2);
    }

    private int a(String s, int i) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException numberformatexception) {
            return i;
        }
    }

    private String a(String[] astring, String s) {
        int i = astring.length;

        if (0 == i) {
            return "";
        } else {
            StringBuilder stringbuilder = new StringBuilder();

            stringbuilder.append(astring[0]);

            for (int j = 1; j < i; ++j) {
                stringbuilder.append(s).append(astring[j]);
            }

            return stringbuilder.toString();
        }
    }

}
