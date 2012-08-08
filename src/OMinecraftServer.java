import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * CANARYMOD OLD SERVER
 */
public class OMinecraftServer implements Runnable, OICommandListener, OIServer {

    public static Logger a = Logger.getLogger("Minecraft");
    public static HashMap<String, Integer> b = new HashMap<String, Integer>();
    private String y;
    private int z;
    public ONetworkListenThread c;
    public OPropertyManager d;
    // public OWorldServer[] e;
    public long[] f = new long[100];
    // public long[][] g;
    public OServerConfigurationManager h;
    private OConsoleCommandHandler A;
    private boolean B = true;
    public boolean i = false;
    int j = 0;
    public String k;
    public int l;
    private List<OIUpdatePlayerListBox> C = new ArrayList<OIUpdatePlayerListBox>();
    private List<OServerCommand> D = Collections.synchronizedList(new ArrayList<OServerCommand>());
    // public OEntityTracker[] m = new OEntityTracker[3];
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public String s;
    public int t;
    private long E;
    private long F;
    private long G;
    private long H;
    public long[] u = new long[100];
    public long[] v = new long[100];
    public long[] w = new long[100];
    public long[] x = new long[100];
    private ORConThreadQuery I;
    private ORConThreadMain J;

    // CanaryMod start: Multiworld \o/
    public Map<String, OWorldServer[]> worlds = new HashMap<String, OWorldServer[]>(1);
    public Map<String, long[][]> worldTickNanos = new HashMap<String, long[][]>(1);
    // CanaryMod end

    public OMinecraftServer() {
        super();
        new OThreadSleepForever(this);
    }

    private boolean s() throws IOException {
        this.A = new OConsoleCommandHandler(this);
        OThreadCommandReader othreadcommandreader = new OThreadCommandReader(this);

        othreadcommandreader.setDaemon(true);
        othreadcommandreader.start();
        OConsoleLogManager.a();
        a.info("Starting minecraft server version 1.2.5");
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            a.warning("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }

        a.info("Loading properties");
        this.d = new OPropertyManager(new File("server.properties"));
        this.y = this.d.a("server-ip", "");
        this.n = this.d.a("online-mode", true);
        this.o = this.d.a("spawn-animals", true);
        this.p = this.d.a("spawn-npcs", true);
        this.q = this.d.a("pvp", true);
        this.r = this.d.a("allow-flight", false);
        this.s = this.d.a("motd", "A Minecraft Server");
        this.s.replace('\u00a7', '$');
        InetAddress inetaddress = null;

        if (this.y.length() > 0) {
            inetaddress = InetAddress.getByName(this.y);
        }

        this.z = this.d.a("server-port", 25565);
        a.info("Starting Minecraft server on " + (this.y.length() == 0 ? "*" : this.y) + ":" + this.z);

        try {
            this.c = new ONetworkListenThread(this, inetaddress, this.z);
        } catch (IOException ioexception) {
            a.warning("**** FAILED TO BIND TO PORT!");
            a.log(Level.WARNING, "The exception was: " + ioexception.toString());
            a.warning("Perhaps a server is already running on that port?");
            return false;
        }

        if (!this.n) {
            a.warning("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
            a.warning("The server will make no attempt to authenticate usernames. Beware.");
            a.warning("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
            a.warning("To change this, set \"online-mode\" to \"true\" in the server.settings file.");
        }

        this.h = new OServerConfigurationManager(this);
        long i = System.nanoTime();
        String s = this.d.a("level-name", "world");
        String s1 = this.d.a("level-seed", "");
        String s2 = this.d.a("level-type", "DEFAULT");
        long j = (new Random()).nextLong();

        if (s1.length() > 0) {
            try {
                long k = Long.parseLong(s1);

                if (k != 0L) {
                    j = k;
                }
            } catch (NumberFormatException numberformatexception) {
                j = (long) s1.hashCode();
            }
        }

        OEnumWorldType oenumworldtype = OEnumWorldType.a(s2);

        if (oenumworldtype == null) {
            oenumworldtype = OEnumWorldType.b;
        }

        this.t = this.d.a("max-build-height", 256);
        this.t = (this.t + 8) / 16 * 16;
        this.t = OMathHelper.a(this.t, 64, 256);
        this.d.a("max-build-height", (Object) Integer.valueOf(this.t));
        a.info("Preparing level \"" + s + "\"");
        this.a(new OAnvilSaveConverter(new File(".")), s, j, oenumworldtype);
        long l = System.nanoTime() - i;
        String s3 = String.format("%.3fs", new Object[] { Double.valueOf((double) l / 1.0E9D)});

        a.info("Done (" + s3 + ")! For help, type \"help\" or \"?\"");
        if (this.d.a("enable-query", false)) {
            a.info("Starting GS4 status listener");
            this.I = new ORConThreadQuery(this);
            this.I.a();
        }

        if (this.d.a("enable-rcon", false)) {
            a.info("Starting remote control listener");
            this.J = new ORConThreadMain(this);
            this.J.a();
        }

        return true;
    }

    protected void loadWorld(String s, long i, World.Type world.type) {
        this.a(new OAnvilSaveConverter(new File(".")), s, i, world.type.getNative());
    }

    private void a(OISaveFormat oisaveformat, String s, long i, OEnumWorldType oenumworldtype) {
        if (oisaveformat.a(s)) {
            a.info("Converting map!");
            oisaveformat.a(s, new OConvertProgressUpdater(this));
        }

        OWorldServer[] toLoad = new OWorldServer[3];

        this.worlds.put(s, toLoad);
        this.worldTickNanos.put(s, new long[toLoad.length][100]);
        int j = this.d.a("gamemode", 0);

        j = OWorldSettings.a(j);
        a.info("Default game type: " + j);
        boolean flag = this.d.a("generate-structures", true);
        OWorldSettings oworldsettings = new OWorldSettings(i, j, flag, false, oenumworldtype);
        OAnvilSaveHandler oanvilsavehandler = new OAnvilSaveHandler(new File("."), s, true);

        for (int k = 0; k < toLoad.length; ++k) {
            byte b0 = 0;

            if (k == 1) {
                b0 = -1;
            }

            if (k == 2) {
                b0 = 1;
            }

            if (k == 0) {
                toLoad[k] = new OWorldServer(this, oanvilsavehandler, s, b0, oworldsettings);
            } else {
                toLoad[k] = new OWorldServerMulti(this, oanvilsavehandler, s, b0, oworldsettings, toLoad[0]);
            }

            this.h.newWorld(s);
            toLoad[k].a(new OWorldManager(this, toLoad[k]));
            toLoad[k].setEntityTracker(new OEntityTracker(this, b0, s));
            toLoad[k].q = this.d.a("difficulty", 1);
            toLoad[k].a(this.d.a("spawn-monsters", true), this.o);
            toLoad[k].s().d(j);
            this.h.a(toLoad);
        }

        short short1 = 196;
        long l = System.currentTimeMillis();

        for (int i1 = 0; i1 < 1; ++i1) {
            a.info("Preparing start region for level " + i1);
            OWorldServer oworldserver = toLoad[i1];
            OChunkCoordinates ochunkcoordinates = oworldserver.p();

            for (int j1 = -short1; j1 <= short1 && this.B; j1 += 16) {
                for (int k1 = -short1; k1 <= short1 && this.B; k1 += 16) {
                    long l1 = System.currentTimeMillis();

                    if (l1 < l) {
                        l = l1;
                    }

                    if (l1 > l + 1000L) {
                        int i2 = (short1 * 2 + 1) * (short1 * 2 + 1);
                        int j2 = (j1 + short1) * (short1 * 2 + 1) + k1 + 1;

                        this.b("Preparing spawn area", j2 * 100 / i2);
                        l = l1;
                    }

                    oworldserver.G.c(ochunkcoordinates.a + j1 >> 4, ochunkcoordinates.c + k1 >> 4);

                    while (oworldserver.z() && this.B) {
                        ;
                    }
                }
            }
        }

        this.t();
    }

    private void b(String s, int i) {
        this.k = s;
        this.l = i;
        a.info(s + ": " + i + "%");
    }

    private void t() {
        this.k = null;
        this.l = 0;
    }

    private void u(OWorldServer oworldserver) {
        a.info("Saving chunks for level " + oworldserver.t.g);

        oworldserver.a(true, (OIProgressUpdate) null);
        oworldserver.A();
    }

    private void v() {
        a.info("Stopping server");
        if (this.h != null) {
            this.h.g();
        }

        for (Map.Entry<String, OWorldServer[]> entrySet : worlds.entrySet()) {
            a.info("Saving chunks for world " + entrySet.getKey());
            OWorldServer[] level = entrySet.getValue();

            for (int i = 0; i < level.length; ++i) {
                OWorldServer oworldserver = level[i];

                if (oworldserver != null) {
                    this.u(oworldserver);
                }
            }
        }
    }

    public void a() {
        this.B = false;
    }

    public void run() {
        boolean k9 = false;

        label595: {
            try {
                k9 = true;
                if (this.s()) {
                    long i = System.currentTimeMillis();

                    for (long j = 0L; this.B; Thread.sleep(1L)) {
                        long k = System.currentTimeMillis();
                        long l = k - i;

                        if (l > 2000L) {
                            a.warning("Can\'t keep up! Did the system time change, or is the server overloaded?");
                            l = 2000L;
                        }

                        if (l < 0L) {
                            a.warning("Time ran backwards! Did the system time change?");
                            l = 0L;
                        }

                        j += l;
                        i = k;
                        // CanaryMod start: multiworld sleeping
                        boolean allSleeping = true;

                        for (OWorldServer[] level : this.worlds.values()) {
                            allSleeping &= level[0].v();
                        }
                        // CanaryMod end
                        
                        if (allSleeping) { // CanaryMod: multiworld sleeping
                            this.w();
                            j = 0L;
                        } else {
                            while (j > 50L) {
                                j -= 50L;
                                this.w();
                            }
                        }
                    }

                    k9 = false;
                } else {
                    while (this.B) {
                        this.b();

                        try {
                            Thread.sleep(10L);
                        } catch (InterruptedException interruptedexception) {
                            interruptedexception.printStackTrace();
                        }
                    }

                    k9 = false;
                }
                break label595;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                a.log(Level.SEVERE, "Unexpected exception", throwable);

                while (true) {
                    if (!this.B) {
                        k9 = false;
                        break;
                    }

                    this.b();

                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException interruptedexception1) {
                        interruptedexception1.printStackTrace();
                    }
                }
            } finally {
                if (k9) {
                    boolean flag1 = false;

                    label523: {
                        label522: {
                            try {
                                flag1 = true;
                                this.v();
                                this.i = true;
                                flag1 = false;
                                break label522;
                            } catch (Throwable throwable1) {
                                throwable1.printStackTrace();
                                flag1 = false;
                            } finally {
                                if (flag1) {
                                    System.exit(0);
                                }
                            }

                            System.exit(0);
                            break label523;
                        }

                        System.exit(0);
                    }

                }
            }

            boolean flag2 = false;

            label596: {
                try {
                    flag2 = true;
                    this.v();
                    this.i = true;
                    flag2 = false;
                    break label596;
                } catch (Throwable throwable2) {
                    throwable2.printStackTrace();
                    flag2 = false;
                } finally {
                    if (flag2) {
                        System.exit(0);
                    }
                }

                System.exit(0);
                return;
            }

            System.exit(0);
            return;
        }

        boolean flag3 = false;

        label597: {
            try {
                flag3 = true;
                this.v();
                this.i = true;
                flag3 = false;
                break label597;
            } catch (Throwable throwable3) {
                throwable3.printStackTrace();
                flag3 = false;
            } finally {
                if (flag3) {
                    System.exit(0);
                }
            }

            System.exit(0);
            return;
        }

        System.exit(0);
    }

    private void w() {
        long i = System.nanoTime();
        ArrayList<String> var3 = new ArrayList<String>();
        Iterator<String> var4 = b.keySet().iterator();

        while (var4.hasNext()) {
            String s = var4.next();
            int j = b.get(s).intValue();

            if (j > 0) {
                b.put(s, Integer.valueOf(j - 1));
            } else {
                var3.add(s);
            }
        }

        int k;

        for (k = 0; k < var3.size(); ++k) {
            b.remove(var3.get(k));
        }

        OAxisAlignedBB.a();
        OVec3D.a();
        ++this.j;

        for (Map.Entry<String, OWorldServer[]> entry : this.worlds.entrySet()) {
            OWorldServer[] level = entry.getValue();

            for (k = 0; k < level.length; ++k) {
                long l = System.nanoTime();

                if (k == 0 || this.d.a("allow-nether", true)) {
                    OWorldServer oworldserver = level[k];

                    if (this.j % 20 == 0) {
                        // this.h.a((OPacket) (new
                        // OPacket4UpdateTime(oworldserver.o())), oworldserver.t.g);
                        // }
                        // this.h.a((OPacket) (new
                        // OPacket4UpdateTime(oworldserver.o())), oworldserver.t.g);
                        for (int i1 = 0; i1 < h.b.size(); ++i1) {
                            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) h.b.get(i1);

                            if (oentityplayermp.bi.hashCode() == oworldserver.hashCode()) {
                                oentityplayermp.a.b((OPacket) (new OPacket4UpdateTime(oworldserver.o())));
                            }
                        }
                    }

                    oworldserver.h();

                    while (true) {
                        if (!oworldserver.z()) {
                            oworldserver.f();
                            break;
                        }
                    }
                }

                this.worldTickNanos.get(entry.getKey())[k][this.j % 100] = System.nanoTime() - l;
            }
        }

        this.c.a();
        this.h.b();

        for (OWorldServer[] aows : this.worlds.values()) {
            for (k = 0; k < aows.length; ++k) {
                aows[k].getEntityTracker().updateTrackedEntities();
            }
        }

        for (k = 0; k < this.C.size(); ++k) {
            this.C.get(k).a();
        }

        try {
            this.b();
        } catch (Exception exception) {
            a.log(Level.WARNING, "Unexpected exception while parsing console command", exception);
        }

        this.f[this.j % 100] = System.nanoTime() - i;
        this.u[this.j % 100] = OPacket.n - this.E;
        this.E = OPacket.n;
        this.v[this.j % 100] = OPacket.o - this.F;
        this.F = OPacket.o;
        this.w[this.j % 100] = OPacket.l - this.G;
        this.G = OPacket.l;
        this.x[this.j % 100] = OPacket.m - this.H;
        this.H = OPacket.m;
    }

    public void a(String s, OICommandListener oicommandlistener) {
        this.D.add(new OServerCommand(s, oicommandlistener));
    }

    public void b() {
        while (this.D.size() > 0) {
            OServerCommand oservercommand = this.D.remove(0);

            this.A.a(oservercommand);
        }

    }

    public void a(OIUpdatePlayerListBox oiupdateplayerlistbox) {
        this.C.add(oiupdateplayerlistbox);
    }

    public static void main(String[] astring) {
        OStatList.a();

        try {
            OMinecraftServer ominecraftserver = new OMinecraftServer();

            if (!GraphicsEnvironment.isHeadless() && (astring.length <= 0 || !astring[0].equals("nogui"))) {
                OServerGUI.a(ominecraftserver);
            }

            (new OThreadServerApplication("Server thread", ominecraftserver)).start();
        } catch (Exception exception) {
            a.log(Level.SEVERE, "Failed to start the minecraft server", exception);
        }

    }

    public File a(String s) {
        return new File(s);
    }

    public void b(String s) {
        a.info(s);
    }

    public void c(String s) {
        a.warning(s);
    }

    public String d() {
        return "CONSOLE";
    }

    public OWorldServer a(int i) {
        throw new UnsupportedOperationException("OMinecraftServer.a(int) has" + " been replaced by OMinecraftServer.getWorld(String, int).");
    }
    
    public OWorldServer getWorld(String s, int i) {
        int index = i == 0 ? 0 : i == -1 ? 1 : 2;

        return this.worlds.get(s)[index];
    }

    public OEntityTracker b(int i) {
        throw new UnsupportedOperationException("OMinecraftServer.b(int) has" + " been replaced by OWorld.getEntityTracker()");
    }

    public int a(String s, int i) {
        return this.d.a(s, i);
    }

    public String a(String s, String s1) {
        return this.d.a(s, s1);
    }

    public void a(String s, Object object) {
        this.d.a(s, object);
    }

    public void c() {
        this.d.b();
    }

    public String e() {
        File file1 = this.d.c();

        return file1 != null ? file1.getAbsolutePath() : "No settings file";
    }

    public String f() {
        return this.y;
    }

    public int g() {
        return this.z;
    }

    public String h() {
        return this.s;
    }

    public String i() {
        return "1.2.5";
    }

    public int j() {
        return this.h.j();
    }

    public int k() {
        return this.h.k();
    }

    public String[] l() {
        return this.h.d();
    }

    public String m() {
        return this.d.a("level-name", "world");
    }

    public String n() {
        // CanaryMod: return our own stuff for RCon
        etc e = etc.getInstance();

        return (e.isCrow() ? "Crow " : "CanaryMod ") + e.getVersionStr() + ": " + etc.getLoader().getPluginList();
    }

    public void o() {}

    public String d(String s) {
        ORConConsoleSource.a.a();
        this.A.a(new OServerCommand(s, ORConConsoleSource.a));
        return ORConConsoleSource.a.b();
    }

    public boolean p() {
        return false;
    }

    public void e(String s) {
        a.log(Level.SEVERE, s);
    }

    public void f(String s) {
        if (this.p()) {
            a.log(Level.INFO, s);
        }

    }

    public String[] q() {
        return (String[]) this.h.f().toArray(new String[0]);
    }

    public String[] r() {
        return (String[]) this.h.e().toArray(new String[0]);
    }

    public String getServerModName() {
        return "CanaryMod";
    }

    // $FF: synthetic method
    public static boolean a(OMinecraftServer ominecraftserver) {
        return ominecraftserver.B;
    }

}
