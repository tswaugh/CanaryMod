import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public abstract class OServerConfigurationManager {

    private static final SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    public static final Logger a = Logger.getLogger("Minecraft");
    private final OMinecraftServer f;
    public final List b = new ArrayList();
    private final OBanList g = new OBanList(new File("banned-players.txt"));
    private final OBanList h = new OBanList(new File("banned-ips.txt"));
    private Set i = new HashSet();
    private Set j = new HashSet();
    // private OIPlayerFileData k; // CanaryMod: multiworld
    private boolean l;
    protected int c;
    protected int d;
    private OEnumGameType m;
    private boolean n;
    private int o = 0;

    private Map<String, OIPlayerFileData> saveHandlers = new HashMap<String, OIPlayerFileData>(1);
    private Map<String, String> playerWorld = new HashMap<String, String>(1);

    public OServerConfigurationManager(OMinecraftServer ominecraftserver) {
        // CanaryMod: initialize
        etc.setServer(ominecraftserver);
        etc.getInstance().loadData();
        a.info("Note: your current classpath is: " + System.getProperty("java.class.path", "*UNKNOWN*"));
        if (!etc.getInstance().getTainted()) {
            a.info((etc.getInstance().isCrow() ? "CanaryMod Crow" : "CanaryMod") + " Build " + etc.getInstance().getVersionStr());
        } else {
            a.info("Tainted Build Information: " + etc.getInstance().getVersionStr());
        }

        this.f = ominecraftserver;
        this.g.a(false);
        this.h.a(false);
        this.c = 8;
    }

    public void a(OINetworkManager oinetworkmanager, OEntityPlayerMP oentityplayermp) {
        this.a(oentityplayermp);
        // CanaryMod: custom world. We don't need the playerWorld entry after this.
        oentityplayermp.a((OWorld) this.f.getWorld(this.playerWorld.remove(oentityplayermp.c_()), oentityplayermp.aq));
        oentityplayermp.c.a((OWorldServer) oentityplayermp.p);
        String s = "local";

        if (oinetworkmanager.c() != null) {
            s = oinetworkmanager.c().toString();
        }

        a.info(oentityplayermp.bR + "[" + s + "] logged in with entity id " + oentityplayermp.k + " at (" + oentityplayermp.t + ", " + oentityplayermp.u + ", " + oentityplayermp.v + ")");
        OWorldServer oworldserver = oentityplayermp.p(); // CanaryMod: get from entity itself
        OChunkCoordinates ochunkcoordinates = oworldserver.H();

        this.a(oentityplayermp, (OEntityPlayerMP) null, oworldserver);
        ONetServerHandler onetserverhandler = new ONetServerHandler(this.f, oinetworkmanager, oentityplayermp);

        onetserverhandler.b(new OPacket1Login(oentityplayermp.k, oworldserver.K().u(), oentityplayermp.c.b(), oworldserver.K().t(), oworldserver.u.h, oworldserver.s, oworldserver.O(), this.l()));
        onetserverhandler.b(new OPacket6SpawnPosition(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c));
        onetserverhandler.b(new OPacket202PlayerAbilities(oentityplayermp.cd));
        onetserverhandler.b(new OPacket16BlockItemSwitch(oentityplayermp.bJ.c));
        this.b(oentityplayermp, oworldserver);
        // CanaryMod - onPlayerConnect Hook
        HookParametersConnect hookResult = new HookParametersConnect(String.format(Colors.Yellow + "%s joined the game.", oentityplayermp.bR), true);

        hookResult = (HookParametersConnect) etc.getLoader().callHook(PluginLoader.Hook.PLAYER_CONNECT, oentityplayermp.getPlayer(), hookResult);
        if (!hookResult.isHidden()) {
            this.a((OPacket) (new OPacket3Chat(hookResult.getJoinMessage())));
        }

        // CanaryMod - Check if player is listed as muted, and mute him
        if (etc.getDataSource().isPlayerOnMuteList(oentityplayermp.getPlayer().getName())) {
            oentityplayermp.getPlayer().toggleMute();
        }
        // CanaryMod END

        this.c(oentityplayermp);
        onetserverhandler.a(oentityplayermp.t, oentityplayermp.u, oentityplayermp.v, oentityplayermp.z, oentityplayermp.A);
        this.f.ae().a(onetserverhandler);
        onetserverhandler.b(new OPacket4UpdateTime(oworldserver.F(), oworldserver.G()));
        if (this.f.Q().length() > 0) {
            oentityplayermp.a(this.f.Q(), this.f.S());
        }

        // CanaryMod: apply potion effects?
        if (hookResult.applyPotionsEffects()) {
            Iterator iterator = oentityplayermp.bz().iterator();

            while (iterator.hasNext()) {
                OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

                onetserverhandler.b(new OPacket41EntityEffect(oentityplayermp.k, opotioneffect));
            }
        }

        // CanaryMod: Handle login (send MOTD, send packet and set mode, and call hook)
        etc.getInstance().getMotd(oentityplayermp.getPlayer());
        etc.getLoader().callHook(PluginLoader.Hook.LOGIN, oentityplayermp.getPlayer());

        oentityplayermp.d_();
    }

    public void a(OWorldServer[] aoworldserver) {
        this.saveHandlers.put(aoworldserver[0].name, aoworldserver[0].J().e());
    }

    public void a(OEntityPlayerMP oentityplayermp, OWorldServer oworldserver) {
        OWorldServer oworldserver1 = oentityplayermp.p();

        if (oworldserver != null) {
            oworldserver.r().c(oentityplayermp);
        }

        oworldserver1.r().a(oentityplayermp);
        oworldserver1.b.c((int) oentityplayermp.t >> 4, (int) oentityplayermp.v >> 4);
    }

    public int a() {
        return OPlayerManager.a(this.o());
    }

    public void a(OEntityPlayerMP oentityplayermp) {
        ONBTTagCompound onbttagcompound = etc.getServer().getDefaultWorld().getWorld().K().i();

        if (oentityplayermp.c_().equals(this.f.H()) && onbttagcompound != null) {
            oentityplayermp.e(onbttagcompound);
        } else {
            this.saveHandlers.get(oentityplayermp.getPlayer().getWorld().getName()).b(oentityplayermp);
        }
    }

    protected void b(OEntityPlayerMP oentityplayermp) {
        this.saveHandlers.get(oentityplayermp.getPlayer().getWorld().getName()).a(oentityplayermp);
    }

    public void c(OEntityPlayerMP oentityplayermp) {
        // CanaryMod: Playername with color and Prefix
        PlayerlistEntry entry = oentityplayermp.getPlayer().getPlayerlistEntry(true);

        this.a((OPacket) (new OPacket201PlayerInfo(entry.getName(), entry.isShow(), 1000)));
        this.b.add(oentityplayermp);
        OWorldServer oworldserver = this.f.getWorld(oentityplayermp.p.name, oentityplayermp.aq);

        oworldserver.d(oentityplayermp);
        this.a(oentityplayermp, (OWorldServer) null);

        for (int i = 0; i < this.b.size(); ++i) {
            OEntityPlayerMP oentityplayermp1 = (OEntityPlayerMP) this.b.get(i);

            entry = oentityplayermp1.getPlayer().getPlayerlistEntry(true);
            oentityplayermp.a.b(new OPacket201PlayerInfo(entry.getName(), entry.isShow(), entry.getPing()));
        }
    }

    public void d(OEntityPlayerMP oentityplayermp) {
        oentityplayermp.p().r().d(oentityplayermp);
    }

    public void e(OEntityPlayerMP oentityplayermp) {
        this.b(oentityplayermp);
        OWorldServer oworldserver = oentityplayermp.p();

        oworldserver.e(oentityplayermp);
        oworldserver.r().c(oentityplayermp);
        this.b.remove(oentityplayermp);

        // CanaryMod: Player color and Prefix
        if (etc.getInstance().isPlayerList_enabled()) {
            PlayerlistEntry entry = oentityplayermp.getPlayer().getPlayerlistEntry(false);

            this.a(new OPacket201PlayerInfo(entry.getName(), entry.isShow(), entry.getPing()));
        }
    }

    public String a(SocketAddress socketaddress, String s) {
        if (!etc.getLoader().isLoaded()) {
            return "The server is not finished loading yet!";
        }

        // Move up IP
        String s2 = socketaddress.toString();

        s2 = s2.substring(s2.indexOf("/") + 1);
        s2 = s2.substring(0, s2.indexOf(":"));


        HookParametersLogincheck hook = (HookParametersLogincheck) etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, new HookParametersLogincheck(etc.getServer().getDefaultWorld().getName(), s, s2));

        if (hook.getKickReason() != null) {
            return hook.getKickReason();
        }

        // CanaryMod: Store for later usage.
        this.playerWorld.put(hook.getPlayerName(), hook.getWorldName());

        Player player = etc.getDataSource().getPlayer(s);

        if (etc.getDataSource().isOnBanList(s, s2)) {
            Ban ban = etc.getDataSource().getBan(s, s2);

            String msg = etc.getInstance().getDefaultBanMessage() + "\nReason: " + ban.getReason();
            if (ban.getTimestamp() != -1) {
                msg += "\nYour ban will be removed on " + e.format(new Date(ban.getTimestamp() * 1000));
            }

            return msg;
        } else if (this.g.a(s)) {
            OBanEntry obanentry = (OBanEntry) this.g.c().get(s);
            String s1 = etc.getInstance().getDefaultBanMessage() + "\nReason: " + obanentry.f();

            if (obanentry.d() != null) {
                s1 = s1 + "\nYour ban will be removed on " + e.format(obanentry.d());
            }

            return s1;
        } else if (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(s) || player.isAdmin())) {
            return etc.getInstance().getWhitelistMessage();
        } else {
            if (this.h.a(s2)) {
                OBanEntry obanentry1 = (OBanEntry) this.h.c().get(s2);
                String s3 = "Your IP address is banned from this server!\nReason: " + obanentry1.f();

                if (obanentry1.d() != null) {
                    s3 = s3 + "\nYour ban will be removed on " + e.format(obanentry1.d());
                }

                return s3;
            } else if (this.b.size() >= this.c  && !etc.getDataSource().isUserOnReserveList(s)) {
                return "The server is full!";
            } else {
                if (player.getIps() != null && !player.getIps()[0].equals("")) {
                    boolean ipallowed = false;

                    if (player.getIps() != null) {
                        for (String allowedip : player.getIps()) {
                            if (s2.equals(allowedip)) {
                                ipallowed = true;
                                break;
                            }
                        }
                    } else {
                        ipallowed = true;
                    }
                    if (!ipallowed) {
                        return "You are not allowed to log in from your current location";
                    }
                }
            }
        }
        return null;
    }

    public OEntityPlayerMP a(String s) {
        ArrayList arraylist = new ArrayList();

        OEntityPlayerMP oentityplayermp;

        for (int i = 0; i < this.b.size(); ++i) {
            oentityplayermp = (OEntityPlayerMP) this.b.get(i);
            if (oentityplayermp.bR.equalsIgnoreCase(s)) {
                arraylist.add(oentityplayermp);
            }
        }

        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            oentityplayermp = (OEntityPlayerMP) iterator.next();
            oentityplayermp.a.c("You logged in from another location");
        }

        // CanaryMod: make sure the world is loaded into memory.
        OWorldServer world = etc.getServer().loadWorld(this.playerWorld.get(s))[0].getWorld();

        Object object;

        if (this.f.M()) {
            object = new ODemoWorldManager(world);
        } else {
            object = new OItemInWorldManager(world);
        }

        return new OEntityPlayerMP(this.f, world, s, (OItemInWorldManager) object);
    }

    public OEntityPlayerMP a(OEntityPlayerMP oentityplayermp, int i, boolean flag) {
        return this.a(oentityplayermp, i, flag, null);
    }

    // CanaryMod: alias to set location when spawning
    public OEntityPlayerMP a(OEntityPlayerMP oentityplayermp, int i, boolean flag, Location location) {
        oentityplayermp.p().p().a(oentityplayermp);
        oentityplayermp.p().p().b(oentityplayermp);
        oentityplayermp.p().r().c(oentityplayermp);
        this.b.remove(oentityplayermp);
        this.f.getWorld(oentityplayermp.getPlayer().getWorld().getName(), oentityplayermp.aq).f(oentityplayermp);
        OChunkCoordinates ochunkcoordinates = oentityplayermp.bZ();
        boolean flag1 = oentityplayermp.ca();

        oentityplayermp.aq = i;
        OItemInWorldManager oiteminworldmanager;
        OWorldServer oworldserver = location != null ? location.getWorld().getWorld() : this.f.getWorld(oentityplayermp.p.name, oentityplayermp.ap);

        if (this.f.M()) {
            oiteminworldmanager = new ODemoWorldManager(oworldserver);
        } else {
            oiteminworldmanager = new OItemInWorldManager(oworldserver);
        }

        OEntityPlayerMP oentityplayermp1 = new OEntityPlayerMP(this.f, oworldserver, oentityplayermp.bR, oiteminworldmanager);
        oentityplayermp.getPlayer().moveTo(oentityplayermp1.getPlayer());

        oentityplayermp1.a = oentityplayermp.a;
        oentityplayermp1.a(oentityplayermp, flag);
        oentityplayermp1.k = oentityplayermp.k;
//        OWorldServer oworldserver = this.f.a(oentityplayermp.aq); //CanaryMod: Delete this i think? --somners

        this.a(oentityplayermp1, oentityplayermp, oworldserver);
        OChunkCoordinates ochunkcoordinates1;

        if (ochunkcoordinates != null) {
            ochunkcoordinates1 = OEntityPlayer.a((OWorld) oworldserver, ochunkcoordinates, flag1);
            if (ochunkcoordinates1 != null) {
                oentityplayermp1.b((double) ((float) ochunkcoordinates1.a + 0.5F), (double) ((float) ochunkcoordinates1.b + 0.1F), (double) ((float) ochunkcoordinates1.c + 0.5F), 0.0F, 0.0F);
                oentityplayermp1.a(ochunkcoordinates, flag1);
            } else {
                oentityplayermp1.a.b(new OPacket70GameEvent(0, 0));
            }
        }

        // CanaryMod set player location and angle if a spawn location is defined
        if (location != null) {
            oentityplayermp1.b(location.x, location.y, location.z, location.rotX, location.rotY);
        }

        oworldserver.b.c((int) oentityplayermp1.t >> 4, (int) oentityplayermp1.v >> 4);

        while (!oworldserver.a(oentityplayermp1, oentityplayermp1.D).isEmpty()) {
            oentityplayermp1.b(oentityplayermp1.t, oentityplayermp1.u + 1.0D, oentityplayermp1.v);
        }

        // Force chunk cache reload on the client
        oentityplayermp1.a.b(new OPacket9Respawn(oentityplayermp1.aq >= 0 ? -1 : 0, (byte) oworldserver.s, oworldserver.K().u(), oworldserver.O(), oentityplayermp1.c.b()));
        oentityplayermp1.a.b(new OPacket9Respawn(oentityplayermp1.aq, (byte) oworldserver.s, oworldserver.K().u(), oworldserver.O(), oentityplayermp1.c.b()));
        oentityplayermp1.a(oworldserver);
        oentityplayermp1.L = false;

        ochunkcoordinates1 = oworldserver.H();
        oentityplayermp1.a.a(oentityplayermp1.t, oentityplayermp1.u, oentityplayermp1.v, oentityplayermp1.z, oentityplayermp1.A);
        oentityplayermp1.a.b(new OPacket6SpawnPosition(ochunkcoordinates1.a, ochunkcoordinates1.b, ochunkcoordinates1.c));
        oentityplayermp1.a.b(new OPacket43Experience(oentityplayermp1.cg, oentityplayermp1.cf, oentityplayermp1.ce));
        this.b(oentityplayermp1, oworldserver);
        oworldserver.r().a(oentityplayermp1);
        oworldserver.d(oentityplayermp1);
        this.b.add(oentityplayermp1);
        oentityplayermp1.d_();
        etc.getLoader().callHook(PluginLoader.Hook.PLAYER_RESPAWN, oentityplayermp1.getPlayer(), location);
        return oentityplayermp1;
    }

    // CanaryMod: add flag to allow overriding portal creation
    public void a(OEntityPlayerMP oentityplayermp, int i) {
        this.sendPlayerToOtherDimension(oentityplayermp, i, true);
    }

    public void sendPlayerToOtherDimension(OEntityPlayerMP oentityplayermp, int i, boolean flag) {
        int j = oentityplayermp.aq;
        OWorldServer oworldserver = this.f.getWorld(oentityplayermp.p.name, oentityplayermp.aq);

        oentityplayermp.aq = i;
        OWorldServer oworldserver1 = this.f.getWorld(oentityplayermp.p.name, oentityplayermp.aq);

        oentityplayermp.a.b(new OPacket9Respawn(oentityplayermp.aq, (byte) oentityplayermp.p.s, oworldserver1.K().u(), oworldserver1.O(), oentityplayermp.c.b()));
        oworldserver.f(oentityplayermp);
        oentityplayermp.L = false;
        this.a(oentityplayermp, j, oworldserver, oworldserver1, flag);
        this.a(oentityplayermp, oworldserver);
        oentityplayermp.a.a(oentityplayermp.t, oentityplayermp.u, oentityplayermp.v, oentityplayermp.z, oentityplayermp.A);
        oentityplayermp.c.a(oworldserver1);
        this.b(oentityplayermp, oworldserver1);
        this.f(oentityplayermp);
        Iterator iterator = oentityplayermp.bz().iterator();

        while (iterator.hasNext()) {
            OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

            oentityplayermp.a.b(new OPacket41EntityEffect(oentityplayermp.k, opotioneffect));
        }
    }

    public void a(OEntity oentity, int i, OWorldServer oworldserver, OWorldServer oworldserver1) {
        this.a(oentity, i, oworldserver, oworldserver1, true);
    }

    public void a(OEntity oentity, int i, OWorldServer oworldserver, OWorldServer oworldserver1, boolean flag) {
        double d0 = oentity.t;
        double d1 = oentity.v;
        double d2 = 8.0D;
        double d3 = oentity.t;
        double d4 = oentity.u;
        double d5 = oentity.v;
        float f = oentity.z;

        oworldserver.D.a("moving");
        if (oentity.aq == -1) {
            d0 /= d2;
            d1 /= d2;
            oentity.b(d0, oentity.u, d1, oentity.z, oentity.A);
            if (oentity.S()) {
                oworldserver.a(oentity, false);
            }
        } else if (oentity.aq == 0) {
            d0 *= d2;
            d1 *= d2;
            oentity.b(d0, oentity.u, d1, oentity.z, oentity.A);
            if (oentity.S()) {
                oworldserver.a(oentity, false);
            }
        } else {
            OChunkCoordinates ochunkcoordinates;

            if (i == 1) {
                ochunkcoordinates = oworldserver1.H();
            } else {
                ochunkcoordinates = oworldserver1.l();
            }

            d0 = (double) ochunkcoordinates.a;
            oentity.u = (double) ochunkcoordinates.b;
            d1 = (double) ochunkcoordinates.c;
            oentity.b(d0, oentity.u, d1, 90.0F, 0.0F);
            if (oentity.S()) {
                oworldserver.a(oentity, false);
            }
        }

        oworldserver.D.b();
        if (i != 1) {
            oworldserver.D.a("placing");
            d0 = (double) OMathHelper.a((int) d0, -29999872, 29999872);
            d1 = (double) OMathHelper.a((int) d1, -29999872, 29999872);
            if (oentity.S()) {
                oworldserver1.d(oentity);
                oentity.b(d0, oentity.u, d1, oentity.z, oentity.A);
                oworldserver1.a(oentity, false);
                if (flag) {
                    oworldserver1.s().a(oentity, d3, d4, d5, f);
                }
            }

            oworldserver.D.b();
        }

        oentity.a((OWorld) oworldserver1);
    }

    public void b() {
        if (++this.o > etc.getInstance().getPlayerList_ticks()) {
            this.o = 0;
        }

        if (etc.getInstance().isPlayerList_autoupdate() && this.o < this.b.size()) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.b.get(this.o);
            PlayerlistEntry ple = oentityplayermp.getPlayer().getPlayerlistEntry(true);

            this.a((OPacket) (new OPacket201PlayerInfo(ple.getName(), ple.isShow(), ple.getPing())));
        }
    }

    public void a(OPacket opacket) {
        for (int i = 0; i < this.b.size(); ++i) {
            ((OEntityPlayerMP) this.b.get(i)).a.b(opacket);
        }
    }

    public void sendPacketToDimension(OPacket opacket, String world, int i) {
        for (int j = 0; j < this.b.size(); ++j) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.b.get(j);

            if (world.equals(oentityplayermp.p.name) && oentityplayermp.aq == i) {
                // TODO check: CanaryMod re-route time updates to world-specific entity trackers
                oentityplayermp.a.b(opacket);
            }
        }
    }

    @Deprecated
    public void a(OPacket opacket, int i) {
        throw new UnsupportedOperationException("OServerConfigurationManager"
                + ".a(OPacket, int) has been replaced by OServerConfiguration"
                + "Manager.sendPacketToDimension(OPacket, String, int).");
    }

    public String c() {
        String s = "";

        for (int i = 0; i < this.b.size(); ++i) {
            if (i > 0) {
                s = s + ", ";
            }

            s = s + ((OEntityPlayerMP) this.b.get(i)).bR;
        }

        return s;
    }

    public String[] d() {
        String[] astring = new String[this.b.size()];

        for (int i = 0; i < this.b.size(); ++i) {
            astring[i] = ((OEntityPlayerMP) this.b.get(i)).bR;
        }

        return astring;
    }

    public OBanList e() {
        return this.g;
    }

    public OBanList f() {
        return this.h;
    }

    public void b(String s) {
        this.i.add(s.toLowerCase());
    }

    public void c(String s) {
        this.i.remove(s.toLowerCase());
    }

    public boolean d(String s) {
        s = s.trim().toLowerCase();
        return !this.l || this.i.contains(s) || this.j.contains(s);
    }

    public boolean e(String s) {
        return this.i.contains(s.trim().toLowerCase()) || this.f.I() && etc.getServer().getDefaultWorld().getWorld().K().v() && this.f.H().equalsIgnoreCase(s) || this.n;
    }

    public OEntityPlayerMP f(String s) {
        Iterator iterator = this.b.iterator();

        OEntityPlayerMP oentityplayermp;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            oentityplayermp = (OEntityPlayerMP) iterator.next();
        } while (!oentityplayermp.bR.equalsIgnoreCase(s));

        return oentityplayermp;
    }

    public List a(OChunkCoordinates ochunkcoordinates, int i, int j, int k, int l, int i1, int j1) {
        if (this.b.isEmpty()) {
            return null;
        } else {
            Object object = new ArrayList();
            boolean flag = k < 0;
            int k1 = i * i;
            int l1 = j * j;

            k = OMathHelper.a(k);

            for (int i2 = 0; i2 < this.b.size(); ++i2) {
                OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.b.get(i2);

                if (ochunkcoordinates != null && (i > 0 || j > 0)) {
                    float f = ochunkcoordinates.e(oentityplayermp.b());

                    if (i > 0 && f < (float) k1 || j > 0 && f > (float) l1) {
                        continue;
                    }
                }

                if ((l == OEnumGameType.a.a() || l == oentityplayermp.c.b().a()) && (i1 <= 0 || oentityplayermp.ce >= i1) && oentityplayermp.ce <= j1) {
                    ((List) object).add(oentityplayermp);
                }
            }

            if (ochunkcoordinates != null) {
                Collections.sort((List) object, new OPlayerPositionComparator(ochunkcoordinates));
            }

            if (flag) {
                Collections.reverse((List) object);
            }

            if (k > 0) {
                object = ((List) object).subList(0, Math.min(k, ((List) object).size()));
            }

            return (List) object;
        }
    }

    // CanaryMod: change signature to include world name
    public void a(double d0, double d1, double d2, double d3, int i, OPacket opacket, String worldName) {
        this.a((OEntityPlayer) null, d0, d1, d2, d3, i, opacket, worldName);
    }

    // CanaryMod: change signature to include world name
    public void a(OEntityPlayer oentityplayer, double d0, double d1, double d2, double d3, int i, OPacket opacket, String worldName) {
        for (int j = 0; j < this.b.size(); ++j) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.b.get(j);

            if (oentityplayermp != oentityplayer && oentityplayermp.aq == i && oentityplayermp.p.name.equals(worldName)) {
                double d4 = d0 - oentityplayermp.t;
                double d5 = d1 - oentityplayermp.u;
                double d6 = d2 - oentityplayermp.v;

                if (d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3) {
                    oentityplayermp.a.b(opacket);
                }
            }
        }
    }

    public void g() {
        for (int i = 0; i < this.b.size(); ++i) {
            this.b((OEntityPlayerMP) this.b.get(i));
        }
    }

    public void h(String s) {
        this.j.add(s);
    }

    public void i(String s) {
        this.j.remove(s);
    }

    public Set h() {
        return this.j;
    }

    public Set i() {
        return this.i;
    }

    public void j() {}

    public void b(OEntityPlayerMP oentityplayermp, OWorldServer oworldserver) {
        oentityplayermp.a.b(new OPacket4UpdateTime(oworldserver.F(), oworldserver.G()));
        if (oworldserver.N()) {
            oentityplayermp.a.b(new OPacket70GameEvent(1, 0));
        }
    }

    public void f(OEntityPlayerMP oentityplayermp) {
        oentityplayermp.a(oentityplayermp.bK);
        oentityplayermp.m();
        oentityplayermp.a.b(new OPacket16BlockItemSwitch(oentityplayermp.bJ.c));
    }

    public int k() {
        return this.b.size();
    }

    public int l() {
        return this.c;
    }

    public String[] m() {
        return etc.getServer().getDefaultWorld().getWorld().J().e().f();
    }

    public boolean n() {
        return this.l;
    }

    public void a(boolean flag) {
        this.l = flag;
    }

    public List j(String s) {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) iterator.next();

            if (oentityplayermp.q().equals(s)) {
                arraylist.add(oentityplayermp);
            }
        }

        return arraylist;
    }

    public int o() {
        return this.d;
    }

    public OMinecraftServer p() {
        return this.f;
    }

    public ONBTTagCompound q() {
        return null;
    }

    private void a(OEntityPlayerMP oentityplayermp, OEntityPlayerMP oentityplayermp1, OWorld oworld) {
        if (oentityplayermp1 != null) {
            oentityplayermp.c.a(oentityplayermp1.c.b());
        } else if (this.m != null) {
            oentityplayermp.c.a(this.m);
        }

        oentityplayermp.c.b(oworld.K().r());
    }

    public void r() {
        while (!this.b.isEmpty()) {
            ((OEntityPlayerMP) this.b.get(0)).a.c("Server closed");
        }
    }
    
    public void k(String s) {
        this.f.f(s);
        this.a((OPacket) (new OPacket3Chat(s)));
    }

    public void k(String s) {
        // TODO check: move showDeathMessages here?
        this.f.f(s);
        this.a((OPacket) (new OPacket3Chat(s)));
    }

    /**
     * Returns the list of bans
     *
     * @return bans
     */
    public String getBans() {
        List<String> list = new ArrayList<String>(this.g.c().keySet());

        java.util.Collections.sort(list);
        StringBuilder builder = new StringBuilder();
        int len = 0;

        for (String name : list) {
            if (len > 0) {
                builder.append(", ");
            }
            builder.append(name);
            len++;
        }
        return builder.toString();
    }

    /**
     * Returns the list of IP bans
     *
     * @return ip bans
     */
    public String getIpBans() {
        List<String> list = new ArrayList<String>(this.h.c().keySet());

        java.util.Collections.sort(list);
        StringBuilder builder = new StringBuilder();
        int len = 0;

        for (String name : list) {
            if (len > 0) {
                builder.append(", ");
            }
            builder.append(name);
            len++;
        }
        return builder.toString();
    }

    /**
     * Returns player ban status
     * @param name player name
     * @return true if player is banned from server
     */
    public boolean isBanned(String name) {
        return this.g.a(name);
    }
}
