import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.*;
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

    public void a(ONetworkManager onetworkmanager, OEntityPlayerMP oentityplayermp) {
        this.a(oentityplayermp);
        // CanaryMod: custom world. We don't need the playerWorld entry after this.
        oentityplayermp.a((OWorld) this.f.getWorld(this.playerWorld.remove(oentityplayermp.c_()), oentityplayermp.bK));
        oentityplayermp.c.a((OWorldServer) oentityplayermp.p);
        String s = "local";

        if (onetworkmanager.c() != null) {
            s = onetworkmanager.c().toString();
        }

        a.info(oentityplayermp.bJ + "[" + s + "] logged in with entity id " + oentityplayermp.k + " at (" + oentityplayermp.t + ", " + oentityplayermp.u + ", " + oentityplayermp.v + " in world " + oentityplayermp.p.name + ", dimension " + oentityplayermp.bK + ")");
        OWorldServer oworldserver = oentityplayermp.q(); // CanaryMod: get from entity itself
        OChunkCoordinates ochunkcoordinates = oworldserver.E();

        this.a(oentityplayermp, (OEntityPlayerMP) null, oworldserver);
        ONetServerHandler onetserverhandler = new ONetServerHandler(this.f, onetworkmanager, oentityplayermp);

        onetserverhandler.b(new OPacket1Login(oentityplayermp.k, oworldserver.H().t(), oentityplayermp.c.b(), oworldserver.H().s(), oworldserver.w.g, oworldserver.u, oworldserver.K(), this.l()));
        onetserverhandler.b(new OPacket6SpawnPosition(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c));
        onetserverhandler.b(new OPacket202PlayerAbilities(oentityplayermp.bZ));
        this.b(oentityplayermp, oworldserver);
        // CanaryMod - onPlayerConnect Hook
        HookParametersConnect hookResult = new HookParametersConnect(String.format(Colors.Yellow + "%s joined the game.", oentityplayermp.v), true);

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
        this.f.ac().a(onetserverhandler);
        onetserverhandler.b(new OPacket4UpdateTime(oworldserver.D()));
        if (this.f.P().length() > 0) {
            oentityplayermp.a(this.f.P(), this.f.R());
        }

        // CanaryMod: apply potion effects?
        if (hookResult.applyPotionsEffects()) {
            Iterator iterator = oentityplayermp.bq().iterator();

            while (iterator.hasNext()) {
                OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

                onetserverhandler.b(new OPacket41EntityEffect(oentityplayermp.k, opotioneffect));
            }
        }

        oentityplayermp.b();
    }

    public void a(OWorldServer[] aoworldserver) {
        this.saveHandlers.put(aoworldserver[0].name, aoworldserver[0].G().e());
    }

    public void a(OEntityPlayerMP oentityplayermp, OWorldServer oworldserver) {
        OWorldServer oworldserver1 = oentityplayermp.q();

        if (oworldserver != null) {
            oworldserver.q().c(oentityplayermp);
        }

        oworldserver1.q().a(oentityplayermp);
        oworldserver1.b.c((int) oentityplayermp.t >> 4, (int) oentityplayermp.v >> 4);
    }

    public int a() {
        return OPlayerManager.a(this.o());
    }

    public void a(OEntityPlayerMP oentityplayermp) {
        ONBTTagCompound onbttagcompound = etc.getServer().getDefaultWorld().getWorld().H().h();

        if (oentityplayermp.c_().equals(this.f.G()) && onbttagcompound != null) {
            oentityplayermp.e(onbttagcompound);
        } else {
            this.saveHandlers.get(oentityplayermp.getPlayer().getWorld().getName()).b(oentityplayermp);
        }
    }

    protected void b(OEntityPlayerMP oentityplayermp) {
        this.saveHandlers.get(oentityplayermp.getPlayer().getWorld().getName()).a(oentityplayermp);
    }

    public void c(OEntityPlayerMP oentityplayermp) {
        this.a((OPacket) (new OPacket201PlayerInfo(oentityplayermp.bJ, true, 1000)));
        this.b.add(oentityplayermp);
        OWorldServer oworldserver = this.f.getWorld(oentityplayermp.p.name, oentityplayermp.bK);

        while (!oworldserver.a(oentityplayermp, oentityplayermp.D).isEmpty()) {
            oentityplayermp.b(oentityplayermp.t, oentityplayermp.u + 1.0D, oentityplayermp.v);
        }

        oworldserver.d(oentityplayermp);
        this.a(oentityplayermp, (OWorldServer) null);
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            OEntityPlayerMP oentityplayermp1 = (OEntityPlayerMP) iterator.next();

            oentityplayermp.a.b(new OPacket201PlayerInfo(oentityplayermp1.bJ, true, oentityplayermp1.i));
        }
    }

    public void d(OEntityPlayerMP oentityplayermp) {
        oentityplayermp.q().q().d(oentityplayermp);
    }

    public void e(OEntityPlayerMP oentityplayermp) {
        this.b(oentityplayermp);
        OWorldServer oworldserver = oentityplayermp.q();

        oworldserver.e(oentityplayermp);
        oworldserver.q().c(oentityplayermp);
        this.b.remove(oentityplayermp);
        this.a((OPacket) (new OPacket201PlayerInfo(oentityplayermp.bJ, false, 9999)));
    }

    public String a(SocketAddress socketaddress, String s) {
        if (!etc.getLoader().isLoaded()) {
            return "The server is not finished loading yet!";
        }

        // Move up IP
        String s2 = socketaddress.toString();

        s2 = s2.substring(s2.indexOf("/") + 1);
        s2 = s2.substring(0, s2.indexOf(":"));


        HookParametersLogincheck hook = (HookParametersLogincheck) etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, new HookParametersLogincheck(f.getWorld(f.I(), 0).name, s, s2));

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
        Iterator iterator = this.b.iterator();

        OEntityPlayerMP oentityplayermp;

        while (iterator.hasNext()) {
            oentityplayermp = (OEntityPlayerMP) iterator.next();
            if (oentityplayermp.bJ.equalsIgnoreCase(s)) {
                arraylist.add(oentityplayermp);
            }
        }

        iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            oentityplayermp = (OEntityPlayerMP) iterator.next();
            oentityplayermp.a.c("You logged in from another location");
        }
        
        OWorldServer world = this.f.getWorld(this.playerWorld.get(s), 0);

        Object object;

        if (this.f.L()) {
            object = new ODemoWorldManager(world);
        } else {
            object = new OItemInWorldManager(world);
        }

        // CanaryMod: custom world
        return new OEntityPlayerMP(this.f, world, s, (OItemInWorldManager) object);
    }

    public OEntityPlayerMP a(OEntityPlayerMP oentityplayermp, int i, boolean flag) {
        return this.a(oentityplayermp, i, flag, null);
    }

    // CanaryMod: alias to set location when spawning
    public OEntityPlayerMP a(OEntityPlayerMP oentityplayermp, int i, boolean flag, Location location) {
        oentityplayermp.q().o().a(oentityplayermp);
        oentityplayermp.q().o().b(oentityplayermp);
        oentityplayermp.q().q().c(oentityplayermp);
        this.b.remove(oentityplayermp);
        this.f.a(oentityplayermp.bK).f(oentityplayermp);
        OChunkCoordinates ochunkcoordinates = oentityplayermp.bJ();

        oentityplayermp.bK = i;
        Object object;

        if (this.f.L()) {
            object = new ODemoWorldManager(this.f.a(oentityplayermp.bK));
        } else {
            object = new OItemInWorldManager(this.f.a(oentityplayermp.bK));
        }

        OEntityPlayerMP oentityplayermp1 = new OEntityPlayerMP(this.f, this.f.a(oentityplayermp.bK), oentityplayermp.bJ, (OItemInWorldManager) object);

        oentityplayermp1.a(oentityplayermp, flag);
        oentityplayermp1.k = oentityplayermp.k;
        oentityplayermp1.a = oentityplayermp.a;
        OWorldServer oworldserver = this.f.a(oentityplayermp.bK);

        this.a(oentityplayermp1, oentityplayermp, oworldserver);
        OChunkCoordinates ochunkcoordinates1;

        if (ochunkcoordinates != null) {
            ochunkcoordinates1 = OEntityPlayer.a((OWorld) this.f.a(oentityplayermp.bK), ochunkcoordinates);
            if (ochunkcoordinates1 != null) {
                oentityplayermp1.b((double) ((float) ochunkcoordinates1.a + 0.5F), (double) ((float) ochunkcoordinates1.b + 0.1F), (double) ((float) ochunkcoordinates1.c + 0.5F), 0.0F, 0.0F);
                oentityplayermp1.a(ochunkcoordinates);
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

        oentityplayermp1.a.b(new OPacket9Respawn(oentityplayermp1.bK, (byte) oentityplayermp1.p.u, oentityplayermp1.p.H().t(), oentityplayermp1.p.K(), oentityplayermp1.c.b()));
        ochunkcoordinates1 = oworldserver.E();
        oentityplayermp1.a.a(oentityplayermp1.t, oentityplayermp1.u, oentityplayermp1.v, oentityplayermp1.z, oentityplayermp1.A);
        oentityplayermp1.a.b(new OPacket6SpawnPosition(ochunkcoordinates1.a, ochunkcoordinates1.b, ochunkcoordinates1.c));
        this.b(oentityplayermp1, oworldserver);
        oworldserver.q().a(oentityplayermp1);
        oworldserver.d(oentityplayermp1);
        this.b.add(oentityplayermp1);
        oentityplayermp1.b();
        return oentityplayermp1;
    }

    public void a(OEntityPlayerMP oentityplayermp, int i) {
        this.sendPlayerToOtherDimension(oentityplayermp, i, true);
    }

    public void sendPlayerToOtherDimension(OEntityPlayerMP oentityplayermp, int i, boolean flag) {
        int j = oentityplayermp.bK;
        OWorldServer oworldserver = this.f.getWorld(oentityplayermp.p.name, oentityplayermp.bK);

        oentityplayermp.bK = i;
        OWorldServer oworldserver1 = this.f.getWorld(oentityplayermp.p.name, oentityplayermp.bK);

        oentityplayermp.a.b(new OPacket9Respawn(oentityplayermp.bK, (byte) oentityplayermp.p.u, oworldserver1.H().t(), oworldserver1.K(), oentityplayermp.c.b()));
        oworldserver.f(oentityplayermp);
        oentityplayermp.L = false;
        double d0 = oentityplayermp.t;
        double d1 = oentityplayermp.v;
        double d2 = 8.0D;

        if (oentityplayermp.bK == -1) {
            d0 /= d2;
            d1 /= d2;
            oentityplayermp.b(d0, oentityplayermp.u, d1, oentityplayermp.z, oentityplayermp.A);
            if (oentityplayermp.S()) {
                oworldserver.a((OEntity) oentityplayermp, false);
            }
        } else if (oentityplayermp.bK == 0) {
            d0 *= d2;
            d1 *= d2;
            oentityplayermp.b(d0, oentityplayermp.u, d1, oentityplayermp.z, oentityplayermp.A);
            if (oentityplayermp.S()) {
                oworldserver.a((OEntity) oentityplayermp, false);
            }
        } else {
            OChunkCoordinates ochunkcoordinates = oworldserver1.k();

            d0 = (double) ochunkcoordinates.a;
            oentityplayermp.u = (double) ochunkcoordinates.b;
            d1 = (double) ochunkcoordinates.c;
            oentityplayermp.b(d0, oentityplayermp.u, d1, 90.0F, 0.0F);
            if (oentityplayermp.S()) {
                oworldserver.a((OEntity) oentityplayermp, false);
            }
        }

        if (j != 1) {
            d0 = (double) OMathHelper.a((int) d0, -29999872, 29999872);
            d1 = (double) OMathHelper.a((int) d1, -29999872, 29999872);
            if (oentityplayermp.S()) {
                oworldserver1.d(oentityplayermp);
                oentityplayermp.b(d0, oentityplayermp.u, d1, oentityplayermp.z, oentityplayermp.A);
                oworldserver1.a((OEntity) oentityplayermp, false);
                if (flag) {
                    (new OTeleporter()).a(oworldserver1, oentityplayermp);
                }
            }
        }

        oentityplayermp.a((OWorld) oworldserver1);
        this.a(oentityplayermp, oworldserver);
        oentityplayermp.a.a(oentityplayermp.t, oentityplayermp.u, oentityplayermp.v, oentityplayermp.z, oentityplayermp.A);
        oentityplayermp.c.a(oworldserver1);
        this.b(oentityplayermp, oworldserver1);
        this.f(oentityplayermp);
        Iterator iterator = oentityplayermp.bq().iterator();

        while (iterator.hasNext()) {
            OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

            oentityplayermp.a.b(new OPacket41EntityEffect(oentityplayermp.k, opotioneffect));
        }
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
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) iterator.next();

            if (world.equals(oentityplayermp.p.name) && oentityplayermp.bK == i) {
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

            s = s + ((OEntityPlayerMP) this.b.get(i)).bJ;
        }

        return s;
    }

    public String[] d() {
        String[] astring = new String[this.b.size()];

        for (int i = 0; i < this.b.size(); ++i) {
            astring[i] = ((OEntityPlayerMP) this.b.get(i)).bJ;
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
        return this.i.contains(s.trim().toLowerCase()) || this.f.H() && etc.getServer().getDefaultWorld().getWorld().H().u() && this.f.G().equalsIgnoreCase(s) || this.n;
    }

    public OEntityPlayerMP f(String s) {
        Iterator iterator = this.b.iterator();

        OEntityPlayerMP oentityplayermp;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            oentityplayermp = (OEntityPlayerMP) iterator.next();
        } while (!oentityplayermp.bJ.equalsIgnoreCase(s));

        return oentityplayermp;
    }

    // CanaryMod: change signature to include world name
    public void a(double d0, double d1, double d2, double d3, int i, OPacket opacket, String worldName) {
        this.a((OEntityPlayer) null, d0, d1, d2, d3, i, opacket, worldName);
    }

    // CanaryMod: change signature to include world name
    public void a(OEntityPlayer oentityplayer, double d0, double d1, double d2, double d3, int i, OPacket opacket, String worldName) {
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) iterator.next();

            if (oentityplayermp != oentityplayer && oentityplayermp.bK == i && oentityplayermp.p.name.equals(worldName)) {
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
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) iterator.next();

            this.b(oentityplayermp);
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
        oentityplayermp.a.b(new OPacket4UpdateTime(oworldserver.D()));
        if (oworldserver.J()) {
            oentityplayermp.a.b(new OPacket70GameEvent(1, 0));
        }
    }

    public void f(OEntityPlayerMP oentityplayermp) {
        oentityplayermp.a(oentityplayermp.bz);
        oentityplayermp.n();
    }

    public int k() {
        return this.b.size();
    }

    public int l() {
        return this.c;
    }

    public String[] m() {
        return etc.getServer().getDefaultWorld().getWorld().G().e().f();
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

            if (oentityplayermp.r().equals(s)) {
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

        oentityplayermp.c.b(oworld.H().q());
    }

    public void r() {
        while (!this.b.isEmpty()) {
            ((OEntityPlayerMP) this.b.get(0)).a.c("Server closed");
        }
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
