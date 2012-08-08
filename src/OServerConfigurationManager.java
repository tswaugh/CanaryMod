import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;


public class OServerConfigurationManager {

    public static Logger a = Logger.getLogger("Minecraft");
    public List b = new ArrayList();
    private OMinecraftServer c;
    // private OPlayerManager[] d = new OPlayerManager[3]; // CanaryMod: multiworld
    private int e;
    private Set f = new HashSet();
    private Set g = new HashSet();
    private Set h = new HashSet();
    private Set i = new HashSet();
    private File j;
    private File k;
    private File l;
    // private File m; //CanaryMod: disable Notchian whitelist
    // private OIPlayerFileData n; // CanaryMod: multiworld
    private boolean o;
    private int p = 0;
    
    private Map<String, OPlayerManager[]> managers = new HashMap<String, OPlayerManager[]>(1);
    private Map<String, OIPlayerFileData> saveHandlers = new HashMap<String, OIPlayerFileData>(1);
    private int viewDistance;

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
        
        this.c = ominecraftserver;
        this.j = ominecraftserver.a("banned-players.txt");
        this.k = ominecraftserver.a("banned-ips.txt");
        this.l = ominecraftserver.a("ops.txt");
        // this.m = ominecraftserver.a("white-list.txt"); //CanaryMod: disable Notchian whitelist
        this.viewDistance = ominecraftserver.d.a("view-distance", 10);
        
        this.e = ominecraftserver.d.a("max-players", 20);
        this.o = ominecraftserver.d.a("white-list", false);
        this.l();
        this.n();
        this.p();
        // this.r();
        this.m();
        this.o();
        this.q();
        // this.s();
    }

    public void a(OWorldServer[] aoworldserver) {
        this.saveHandlers.put(aoworldserver[0].name, aoworldserver[0].r().d());
    }

    public void a(OEntityPlayerMP oentityplayermp) {
        OPlayerManager[] mgrs = this.managers.get(oentityplayermp.bi.name);

        mgrs[0].b(oentityplayermp);
        mgrs[1].b(oentityplayermp);
        mgrs[2].b(oentityplayermp);
        this.getManager(oentityplayermp.bi.name, oentityplayermp.w).removePlayer(oentityplayermp);
        this.getManager(oentityplayermp.bi.name, oentityplayermp.w).addPlayer(oentityplayermp);
        OWorldServer oworldserver = this.c.getWorld(oentityplayermp.bi.name, oentityplayermp.w);

        oworldserver.G.c((int) oentityplayermp.bm >> 4, (int) oentityplayermp.bo >> 4);
    }

    public int a() {
        throw new UnsupportedOperationException("OServerConfigurationManager" + ".a() has been replaced by OServerConfigurationManager" + ".getMaxTrackingDistance(String).");
    }
    
    public int getMaxTrackingDistance(String s) {
        return this.managers.get(s)[0].c();
    }

    private OPlayerManager a(int i) {
        throw new UnsupportedOperationException("OServerConfigurationManager.a" + "(int) has been replaced by OServerConfigurationManager.get" + "Manager(String, int).");
    }
    
    public PlayerManager getManager(String s, int i) {
        int index = i == -1 ? 1 : (i == 0 ? 0 : 2);

        return this.managers.get(s)[index].getCanaryPlayerManager();
    }

    public void b(OEntityPlayerMP oentityplayermp) {
        this.saveHandlers.get(oentityplayermp.bi.name).b(oentityplayermp);
    }

    public void c(OEntityPlayerMP oentityplayermp) {
        // CanaryMod: Playername with color and Prefix
        PlayerlistEntry entry = oentityplayermp.getPlayer().getPlayerlistEntry(true);

        this.a(new OPacket201PlayerInfo(entry.getName(), entry.isShow(), 1000));
        this.b.add(oentityplayermp);
        OWorldServer oworldserver = this.c.getWorld(oentityplayermp.bi.name, oentityplayermp.w);

        oworldserver.G.c((int) oentityplayermp.bm >> 4, (int) oentityplayermp.bo >> 4);

        while (oworldserver.a(oentityplayermp, oentityplayermp.bw).size() != 0) {
            oentityplayermp.c(oentityplayermp.bm, oentityplayermp.bn + 1.0D, oentityplayermp.bo);
        }

        oworldserver.b(oentityplayermp);
        this.getManager(oworldserver.name, oentityplayermp.w).addPlayer(oentityplayermp);
        this.u();

        for (int i = 0; i < this.b.size(); ++i) {
            OEntityPlayerMP oentityplayermp1 = (OEntityPlayerMP) this.b.get(i);

            entry = oentityplayermp1.getPlayer().getPlayerlistEntry(true);
            oentityplayermp.a.b(new OPacket201PlayerInfo(entry.getName(), entry.isShow(), entry.getPing()));
        }

        // CanaryMod: Handle login (send MOTD, send packet and set mode, and call hook)
        if (oentityplayermp.getPlayer().getMode()) {
            oentityplayermp.getPlayer().setCreativeMode(1);
        }
        etc.getInstance().getMotd(oentityplayermp.getPlayer());
        etc.getLoader().callHook(PluginLoader.Hook.LOGIN, oentityplayermp.getPlayer());
        oentityplayermp.getPlayer().refreshCreativeMode();
    }

    public void d(OEntityPlayerMP oentityplayermp) {
        this.getManager(oentityplayermp.bi.name, oentityplayermp.w).updateMountedMovingPlayer(oentityplayermp);
    }

    public void e(OEntityPlayerMP oentityplayermp) {
        this.saveHandlers.get(oentityplayermp.bi.name).a(oentityplayermp); // save player
        this.c.getWorld(oentityplayermp.bi.name, oentityplayermp.w).e(oentityplayermp);
        this.b.remove(oentityplayermp);
        this.getManager(oentityplayermp.bi.name, oentityplayermp.w).removePlayer(oentityplayermp);
        // CanaryMod: Player color and Prefix
        if (etc.getInstance().isPlayerList_enabled()) {
            PlayerlistEntry entry = oentityplayermp.getPlayer().getPlayerlistEntry(false);

            this.a(new OPacket201PlayerInfo(entry.getName(), entry.isShow(), entry.getPing()));
        }
    }

    public OEntityPlayerMP a(ONetLoginHandler onetloginhandler, String s) {
        // TODO: add reasons, expire tempbans
        if (!etc.getLoader().isLoaded()) {
            onetloginhandler.a("The server is not finished loading yet!");
        }
        
        String ip = onetloginhandler.b.c().toString(); // IP, move up!

        ip = ip.substring(ip.indexOf("/") + 1);
        ip = ip.substring(0, ip.lastIndexOf(":"));
        // CanaryMod: Moved the loginchecks hook up.
        // Plugins should get priority over system settings anyways, this also allows setting of custom world spawns

        HookParametersLogincheck hook = (HookParametersLogincheck) etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, new Object[] { new HookParametersLogincheck(c.getWorld(c.m(), 0).name, s, ip)});

        if (hook.getKickReason() != null) {
            onetloginhandler.a(hook.getKickReason());
            return null; // return and spare the rest. 
        }
        // CanaryMod before anything happens with a player instance, make sure the world we want is loaded into memory
        // or else we'll run into crazy NPEs and subsequent "epic failure"
        World[] worlds = etc.getServer().loadWorld(hook.getWorldName());
        
        // CanaryMod: whole section below is modified to handle whitelists and bans etc
        
        // There, now we can put the player into the proper world before he joins. Fancy
        OEntityPlayerMP temp = new OEntityPlayerMP(c, worlds[0].getWorld(), s, new OItemInWorldManager(worlds[0].getWorld()));
        
        // CanaryMod: set the world already.
        // Usually this is called in ONetLoginhandler.b(OPacketLogin)
        // However, we lost recollection of the world by then and need to set it here instead!
        temp.a((OWorld) worlds[0].getWorld());
        Player player = temp.getPlayer();
        
        if (etc.getDataSource().isOnBanList(s, ip)) {
            Ban ban = etc.getDataSource().getBan(s, ip);

            onetloginhandler.a(ban.getReason());
            return null;
        } else if (this.f.contains(s.trim().toLowerCase())) {
            onetloginhandler.a(etc.getInstance().getDefaultBanMessage());
            return null;
        } else if ((etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(s) || player.isAdmin()))) {
            onetloginhandler.a(etc.getInstance().getWhitelistMessage());
            return null;
        } else {
            if (this.g.contains(ip)) {
                onetloginhandler.a("Your IP address is banned from this server!");
                return null;
            } else if (this.b.size() >= this.e && !etc.getDataSource().isUserOnReserveList(s)) {
                onetloginhandler.a("The server is full!");
                return null;
            } else {
                if (player.getIps() != null && !player.getIps()[0].equals("")) {
                    boolean ipallowed = false;

                    if (player.getIps() != null) {
                        for (String allowedip : player.getIps()) {
                            if (ip.equals(allowedip)) {
                                ipallowed = true;
                                break;
                            }
                        }
                    } else {
                        ipallowed = true;
                    }
                    if (!ipallowed) {
                        onetloginhandler.a("You are not allowed to log in from your current location");
                        return null;
                    }
                }

                for (int i = 0; i < this.b.size(); ++i) {
                    OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.b.get(i);

                    if (oentityplayermp.v.equalsIgnoreCase(s)) {
                        if (etc.getInstance().isAltLocLoginAllowed()) {
                            oentityplayermp.a.a("You logged in from another location");
                        } else {
                            onetloginhandler.a("You are already logged in from another location");
                            return null;
                        }
                    }
                }

                // return new OEntityPlayerMP(this.c, this.c.a(0), s, new OItemInWorldManager(this.c.a(0)));
            }
        }
        // Object obj = etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, s, ip);
        //
        // if (obj instanceof String) {
        // String result = (String) obj;
        //
        // if (result != null && !result.equals("")) {
        // onetloginhandler.a(result);
        // return null;
        // }
        // }
        return temp;
    }
    
    // CanaryMod alias to set location when respawning.
    public OEntityPlayerMP a(OEntityPlayerMP oentityplayermp, int i, boolean flag) {
        return a(oentityplayermp, i, flag, null);
    }

    public OEntityPlayerMP a(OEntityPlayerMP oentityplayermp, int i, boolean flag, Location location) {
        oentityplayermp.bi.getEntityTracker().untrackPlayerSymmetrics(oentityplayermp);
        oentityplayermp.bi.getEntityTracker().untrackEntity(oentityplayermp);
        this.b.remove(oentityplayermp);
        oentityplayermp.bi.world.removePlayerFromWorld(oentityplayermp.getPlayer()); // Calls despawn method in OWorld
        this.getManager(oentityplayermp.bi.name, oentityplayermp.w).removePlayer(oentityplayermp);
        OChunkCoordinates ochunkcoordinates = oentityplayermp.ab();

        oentityplayermp.w = i;
        OEntityPlayerMP oentityplayermp1 = new OEntityPlayerMP(this.c, oentityplayermp.bi, oentityplayermp.v, new OItemInWorldManager(oentityplayermp.bi));

        if (flag) {
            oentityplayermp1.c((OEntityPlayer) oentityplayermp);
        }

        oentityplayermp1.bd = oentityplayermp.bd;
        // Handle setting of serverhandlers
        oentityplayermp1.a = oentityplayermp.a;
        oentityplayermp1.a.setPlayer(oentityplayermp1); // Override player entity
        OWorldServer oworldserver = (OWorldServer) oentityplayermp.bi;

        oentityplayermp1.c.a(oentityplayermp.c.a());
        oentityplayermp1.c.b(oworldserver.s().m());
        if (ochunkcoordinates != null) {
            OChunkCoordinates ochunkcoordinates1 = OEntityPlayer.a(oentityplayermp.bi, ochunkcoordinates);

            if (ochunkcoordinates1 != null) {
                oentityplayermp1.c((double) ((float) ochunkcoordinates1.a + 0.5F), (double) ((float) ochunkcoordinates1.b + 0.1F), (double) ((float) ochunkcoordinates1.c + 0.5F), 0.0F, 0.0F);
                oentityplayermp1.a(ochunkcoordinates);
            } else {
                oentityplayermp1.a.b((OPacket) (new OPacket70Bed(0, 0)));
            }
        }
        
        // CanaryMod set player location and angle if a spawn location is defined
        if (location != null) {
            oentityplayermp1.c((double) location.x, (double) location.y, (double) location.z, 0.0F, 0.0F);
        }

        oworldserver.G.c((int) oentityplayermp1.bm >> 4, (int) oentityplayermp1.bo >> 4);

        while (oworldserver.a(oentityplayermp1, oentityplayermp1.bw).size() != 0) {
            oentityplayermp1.c(oentityplayermp1.bm, oentityplayermp1.bn + 1.0D, oentityplayermp1.bo);
        }

        oentityplayermp1.a.b((OPacket) (new OPacket9Respawn(oentityplayermp1.w, (byte) oentityplayermp1.bi.q, oentityplayermp1.bi.s().p(), oentityplayermp1.bi.y(), oentityplayermp1.c.a())));
        oentityplayermp1.a.a(oentityplayermp1.bm, oentityplayermp1.bn, oentityplayermp1.bo, oentityplayermp1.bs, oentityplayermp1.bt, oentityplayermp1.w, oentityplayermp1.bi.name);
        this.a(oentityplayermp1, oworldserver);
        
        this.b.add(oentityplayermp1); 
        // oentityplayermp1.bi.getEntityTracker().trackEntity(oentityplayermp1); //no need to add player to tracker
        this.getManager(oentityplayermp1.bi.name, oentityplayermp1.w).addPlayer(oentityplayermp1);
        oentityplayermp1.bi.world.addPlayerToWorld(oentityplayermp1.getPlayer());
        oentityplayermp1.x();
        oentityplayermp1.E();
        return oentityplayermp1;
    }
    
    // CanaryMod alias to normally create portals when players are switching worlds.
    public void a(OEntityPlayerMP oentityplayermp, int i) {
        sendPlayerToOtherDimension(oentityplayermp, i, true);
    }

    // CanaryMod used to be a(OEntityPlayerMP var1, int var2) to teleport player to other dimensions.
    // Added createPortal option to cancel portal creation if not needed.
    public void sendPlayerToOtherDimension(OEntityPlayerMP oentityplayermp, int i, boolean flag) {
        int j = oentityplayermp.w;
        OWorldServer oworldserver = this.c.getWorld(oentityplayermp.bi.name, oentityplayermp.w);

        oentityplayermp.w = i;
        OWorldServer oworldserver1 = this.c.getWorld(oentityplayermp.bi.name, oentityplayermp.w);

        oentityplayermp.a.b((OPacket) (new OPacket9Respawn(oentityplayermp.w, (byte) oentityplayermp.bi.q, oworldserver1.s().p(), oworldserver1.y(), oentityplayermp.c.a())));
        oworldserver.f(oentityplayermp);
        oentityplayermp.bE = false;
        double d0 = oentityplayermp.bm;
        double d1 = oentityplayermp.bo;
        double d2 = 8.0D;

        if (oentityplayermp.w == -1) {
            d0 /= d2;
            d1 /= d2;
            oentityplayermp.c(d0, oentityplayermp.bn, d1, oentityplayermp.bs, oentityplayermp.bt);
            if (oentityplayermp.aE()) {
                oworldserver.a(oentityplayermp, false);
            }
        } else if (oentityplayermp.w == 0) {
            d0 *= d2;
            d1 *= d2;
            oentityplayermp.c(d0, oentityplayermp.bn, d1, oentityplayermp.bs, oentityplayermp.bt);
            if (oentityplayermp.aE()) {
                oworldserver.a(oentityplayermp, false);
            }
        } else {
            OChunkCoordinates ochunkcoordinates = oworldserver1.d();

            d0 = (double) ochunkcoordinates.a;
            oentityplayermp.bn = (double) ochunkcoordinates.b;
            d1 = (double) ochunkcoordinates.c;
            oentityplayermp.c(d0, oentityplayermp.bn, d1, 90.0F, 0.0F);
            if (oentityplayermp.aE()) {
                oworldserver.a(oentityplayermp, false);
            }
        }

        if (j != 1 && oentityplayermp.aE()) {
            oworldserver1.b(oentityplayermp);
            oentityplayermp.c(d0, oentityplayermp.bn, d1, oentityplayermp.bs, oentityplayermp.bt);
            oworldserver1.a(oentityplayermp, false);
            // CanaryMod - don't create portal if we are not using a portal to teleport.
            if (flag) {
                oworldserver1.G.a = true;
                (new OTeleporter()).a(oworldserver1, oentityplayermp);
                oworldserver1.G.a = false;
            }
        }

        this.a(oentityplayermp);
        oentityplayermp.a.a(oentityplayermp.bm, oentityplayermp.bn, oentityplayermp.bo, oentityplayermp.bs, oentityplayermp.bt, oentityplayermp.w, oentityplayermp.bi.name);
        oentityplayermp.a((OWorld) oworldserver1);
        oentityplayermp.c.a(oworldserver1);
        this.a(oentityplayermp, oworldserver1);
        this.f(oentityplayermp);
    }

    public void b() {
        if ((etc.getInstance().isPlayerList_autoupdate()) && (this.p-- <= 0)) {
            for (int i = 0; i < this.b.size(); i++) {
                OEntityPlayerMP j = (OEntityPlayerMP) this.b.get(i);
                PlayerlistEntry entry = j.getPlayer().getPlayerlistEntry(true);

                a(new OPacket201PlayerInfo(entry.getName(), entry.isShow(), entry.getPing()));
            }
            this.p = etc.getInstance().getPlayerList_ticks();
        }
        
        for (OPlayerManager[] mgrs : managers.values()) {
            for (int j = 0; j < mgrs.length; ++j) {
                mgrs[j].b();
            }
        }

    }

    public void a(int i, int j, int k, int l) {
        throw new UnsupportedOperationException("OServerConfigurationManager" + ".a(int, int, int, int) has been replaced by OServer" + "ConfigurationManager.markBlockNeedsUpdate(int, int, int, int," + " String).");
    }
    
    public void markBlockNeedsUpdate(int i, int j, int k, int l, String s) {
        this.getManager(s, l).markBlockNeedsUpdate(i, j, k);
    }

    /**
     * Send packet to all
     * @param var1 packet XXX
     */
    public void a(OPacket opacket) {
        for (int i = 0; i < this.b.size(); ++i) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.b.get(i);

            oentityplayermp.a.b(opacket);
        }

    }

    /**
     * Send packet to guys in dimension
     * @param var1 packet
     * @param var2 dimension id
     */
    public void a(OPacket opacket, int i) {
        for (int j = 0; j < this.b.size(); ++j) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.b.get(j);

            if (oentityplayermp.w == i) {
                // CanaryMod re-route time updates to world-specific entity trackers
                oentityplayermp.bi.getEntityTracker().sendPacketToPlayersAndEntity(oentityplayermp, opacket);
                // oentityplayermp.a.b(opacket);
            }
        }

    }

    public String c() {
        String s = "";

        for (int i = 0; i < this.b.size(); ++i) {
            if (i > 0) {
                s = s + ", ";
            }

            s = s + ((OEntityPlayerMP) this.b.get(i)).v;
        }

        return s;
    }

    public String[] d() {
        String[] astring = new String[this.b.size()];

        for (int i = 0; i < this.b.size(); ++i) {
            astring[i] = ((OEntityPlayerMP) this.b.get(i)).v;
        }

        return astring;
    }

    public void a(String s) {
        this.f.add(s.toLowerCase());
        this.m();
    }

    public void b(String s) {
        this.f.remove(s.toLowerCase());
        this.m();
    }

    private void l() {
        try {
            this.f.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.j));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.f.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load ban list: " + exception);
        }

    }

    private void m() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.j, false));
            Iterator iterator = this.f.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            a.warning("Failed to save ban list: " + exception);
        }

    }

    public Set e() {
        return this.f;
    }

    public Set f() {
        return this.g;
    }

    public void c(String s) {
        this.g.add(s.toLowerCase());
        this.o();
    }

    public void d(String s) {
        this.g.remove(s.toLowerCase());
        this.o();
    }

    private void n() {
        try {
            this.g.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.k));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.g.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load ip ban list: " + exception);
        }

    }

    private void o() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.k, false));
            Iterator iterator = this.g.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            a.warning("Failed to save ip ban list: " + exception);
        }

    }

    public void e(String s) {
        this.h.add(s.toLowerCase());
        this.q();
    }

    public void f(String s) {
        this.h.remove(s.toLowerCase());
        this.q();
    }

    private void p() {
        try {
            this.h.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.l));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.h.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load operators list: " + exception);
        }

    }

    private void q() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.l, false));
            Iterator iterator = this.h.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            a.warning("Failed to save operators list: " + exception);
        }

    }

    private void r() {// CanaryMod: Disable Notchian Whitelist
        /* try {
         this.i.clear();
         BufferedReader var1 = new BufferedReader(new FileReader(this.m));
         String var2 = "";

         while ((var2 = var1.readLine()) != null) {
         this.i.add(var2.trim().toLowerCase());
         }

         var1.close();
         } catch (Exception var3) {
         a.warning("Failed to load white-list: " + var3);
         }*/}

    private void s() {// CanaryMod: Disable Notchian Whitelist
        /* try {
         PrintWriter var1 = new PrintWriter(new FileWriter(this.m, false));
         Iterator var2 = this.i.iterator();

         while (var2.hasNext()) {
         String var3 = (String) var2.next();

         var1.println(var3);
         }

         var1.close();
         } catch (Exception var4) {
         a.warning("Failed to save white-list: " + var4);
         }*/}

    public boolean g(String s) {
        s = s.trim().toLowerCase();
        return !this.o || this.h.contains(s) || this.i.contains(s);
    }

    public boolean h(String s) {
        return this.h.contains(s.trim().toLowerCase());
    }

    public OEntityPlayerMP i(String s) {
        for (int i = 0; i < this.b.size(); ++i) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.b.get(i);

            if (oentityplayermp.v.equalsIgnoreCase(s)) {
                return oentityplayermp;
            }
        }

        return null;
    }

    public void a(String s, String s1) {
        OEntityPlayerMP oentityplayermp = this.i(s);

        if (oentityplayermp != null) {
            oentityplayermp.a.b((OPacket) (new OPacket3Chat(s1)));
        }

    }

    public void a(double d0, double d1, double d2, double d3, int i, OPacket opacket) {
        this.a((OEntityPlayer) null, d0, d1, d2, d3, i, opacket, null);
    }

    public void a(OEntityPlayer oentityplayer, double d0, double d1, double d2, double d3, int i, OPacket opacket, String s) {
        if (s == null) {
            for (int k = 0; k < this.b.size(); ++k) {
                OEntityPlayerMP oentityplayermp1 = (OEntityPlayerMP) this.b.get(k);

                if (oentityplayermp1 != oentityplayer && oentityplayermp1.w == i) {
                    double d7 = d0 - oentityplayermp1.bm;
                    double d8 = d1 - oentityplayermp1.bn;
                    double d9 = d2 - oentityplayermp1.bo;

                    if (d7 * d7 + d8 * d8 + d9 * d9 < d3 * d3) {
                        oentityplayermp1.a.b(opacket);
                    }
                }
            }
        } else {
            for (int k = 0; k < this.b.size(); ++k) {
                OEntityPlayerMP oentityplayermp1 = (OEntityPlayerMP) this.b.get(k);

                if (oentityplayermp1 != oentityplayer && oentityplayermp1.w == i && s.equals(oentityplayermp1.bi.name)) {
                    double d7 = d0 - oentityplayermp1.bm;
                    double d8 = d1 - oentityplayermp1.bn;
                    double d9 = d2 - oentityplayermp1.bo;

                    if (d7 * d7 + d8 * d8 + d9 * d9 < d3 * d3) {
                        oentityplayermp1.a.b(opacket);
                    }
                }
            }
        }
        
    }

    public void j(String s) {
        OPacket3Chat opacket3chat = new OPacket3Chat(s);

        for (int i = 0; i < this.b.size(); ++i) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.b.get(i);

            if (this.h(oentityplayermp.v)) {
                oentityplayermp.a.b((OPacket) opacket3chat);
            }
        }

    }

    public boolean a(String s, OPacket opacket) {
        OEntityPlayerMP oentityplayermp = this.i(s);

        if (oentityplayermp != null) {
            oentityplayermp.a.b(opacket);
            return true;
        } else {
            return false;
        }
    }

    public void g() {
        for (int i = 0; i < this.b.size(); ++i) {
            // CanaryMod: Store in a temp variable so we can get the save handler
            OEntityPlayer oep = (OEntityPlayer) this.b.get(i);

            this.saveHandlers.get(oep.bi.name).a(oep);
        }

    }

    public void a(int i, int j, int k, OTileEntity otileentity) {}

    public void k(String s) {
        this.i.add(s);
        // this.s(); CanaryMod - Disable Notchian whitelist
    }

    public void l(String s) {
        this.i.remove(s);
        // this.s(); CanaryMod - Disable Notchian whitelist
    }

    public Set h() {
        return this.i;
    }

    public void i() {// this.r(); CanaryMod - Disable Notchian whitelist
    }

    public void a(OEntityPlayerMP oentityplayermp, OWorldServer oworldserver) {
        oentityplayermp.a.b((OPacket) (new OPacket4UpdateTime(oworldserver.o())));
        if (oworldserver.x()) {
            oentityplayermp.a.b((OPacket) (new OPacket70Bed(1, 0)));
        }

    }

    public void f(OEntityPlayerMP oentityplayermp) {
        oentityplayermp.a(oentityplayermp.l);
        oentityplayermp.J();
    }

    public int j() {
        return this.b.size();
    }

    public int k() {
        return this.e;
    }

    public String[] t() {
        // CanaryMod get the default world from Canary world list instead.
        return etc.getServer().getDefaultWorld().getWorld().r().d().g();
        // return this.c.e[0].r().d().g();
    }

    private void u() {
        OPlayerUsageSnooper oplayerusagesnooper = new OPlayerUsageSnooper("server");

        oplayerusagesnooper.a("version", this.c.i());
        oplayerusagesnooper.a("os_name", System.getProperty("os.name"));
        oplayerusagesnooper.a("os_version", System.getProperty("os.version"));
        oplayerusagesnooper.a("os_architecture", System.getProperty("os.arch"));
        oplayerusagesnooper.a("memory_total", Long.valueOf(Runtime.getRuntime().totalMemory()));
        oplayerusagesnooper.a("memory_max", Long.valueOf(Runtime.getRuntime().maxMemory()));
        oplayerusagesnooper.a("memory_free", Long.valueOf(Runtime.getRuntime().freeMemory()));
        oplayerusagesnooper.a("java_version", System.getProperty("java.version"));
        oplayerusagesnooper.a("cpu_cores", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
        oplayerusagesnooper.a("players_current", Integer.valueOf(this.j()));
        oplayerusagesnooper.a("players_max", Integer.valueOf(this.k()));
        oplayerusagesnooper.a("players_seen", Integer.valueOf(this.t().length));
        oplayerusagesnooper.a("uses_auth", Boolean.valueOf(this.c.n));
        oplayerusagesnooper.a("server_brand", this.c.getServerModName());
        oplayerusagesnooper.a();
    }
    
    /**
     * Returns the list of bans
     * 
     * @return bans
     */
    public String getBans() {
        List<String> list = new ArrayList<String>(f);

        java.util.Collections.sort(list);
        StringBuilder builder = new StringBuilder();       
        int l = 0;

        for (String o : list) {
            if (l > 0) {
                builder.append(", ");
            }
            builder.append(o);
            l++;
        }
        return builder.toString();
    }

    /**
     * Returns the list of IP bans
     * 
     * @return ip bans
     */
    public String getIpBans() {
        StringBuilder builder = new StringBuilder();
        int l = 0;

        for (Object o : g) {
            if (l > 0) {
                builder.append(", ");
            }
            builder.append(o);
            l++;
        }
        return builder.toString();
    }

    /**
     * Returns player ban status
     * @param name player name
     * @return true if player is banned from server
     */
    public boolean isBanned(String s) {
        return this.f.contains(s.toLowerCase());
    }
    
    protected void newWorld(String s) {
        OPlayerManager[] toPut = new OPlayerManager[3];

        toPut[0] = new OPlayerManager(this.c, 0, this.viewDistance, s);
        toPut[1] = new OPlayerManager(this.c, -1, this.viewDistance, s);
        toPut[2] = new OPlayerManager(this.c, 1, this.viewDistance, s);
        this.managers.put(s, toPut);
    }
}
