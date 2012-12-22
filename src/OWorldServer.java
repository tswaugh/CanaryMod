import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class OWorldServer extends OWorld {

    private final OMinecraftServer a;
    private final OEntityTracker J;
    private final OPlayerManager K;
    private Set L;
    private TreeSet M;
    public OChunkProviderServer b;
    public boolean c;
    private boolean N;
    private int O = 0;
    private final OTeleporter P;
    private OServerBlockEventList[] Q = new OServerBlockEventList[] { new OServerBlockEventList((OServerBlockEvent) null), new OServerBlockEventList((OServerBlockEvent) null)};
    private int R = 0;
    private static final OWeightedRandomChestContent[] S = new OWeightedRandomChestContent[] { new OWeightedRandomChestContent(OItem.D.cj, 0, 1, 3, 10), new OWeightedRandomChestContent(OBlock.A.cm, 0, 1, 3, 10), new OWeightedRandomChestContent(OBlock.M.cm, 0, 1, 3, 10), new OWeightedRandomChestContent(OItem.y.cj, 0, 1, 1, 3), new OWeightedRandomChestContent(OItem.u.cj, 0, 1, 1, 5), new OWeightedRandomChestContent(OItem.x.cj, 0, 1, 1, 3), new OWeightedRandomChestContent(OItem.t.cj, 0, 1, 1, 5), new OWeightedRandomChestContent(OItem.j.cj, 0, 2, 3, 5), new OWeightedRandomChestContent(OItem.U.cj, 0, 2, 3, 3)};
    private OIntHashMap T;

    public OWorldServer(OMinecraftServer ominecraftserver, OISaveHandler oisavehandler, String s, int i, OWorldSettings oworldsettings, OProfiler oprofiler) {
        super(oisavehandler, s, oworldsettings, OWorldProvider.a(i), oprofiler);
        this.a = ominecraftserver;
        this.J = new OEntityTracker(this);
        this.K = new OPlayerManager(this, ominecraftserver.ad().o());
        if (this.T == null) {
            this.T = new OIntHashMap();
        }

        if (this.L == null) {
            this.L = new HashSet();
        }

        if (this.M == null) {
            this.M = new TreeSet();
        }

        this.P = new OTeleporter(this);
    }

    public void b() {
        super.b();
        if (this.K().t() && this.s < 3) {
            this.s = 3;
        }

        this.u.d.b();
        if (this.e()) {
            boolean flag = false;

            if (this.E && this.s >= 1) {
                ;
            }

            if (!flag) {
                long i = this.y.g() + 24000L;

                // CanaryMod: Time hook
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.TIME_CHANGE, world, i - i % 24000L)) {
                    this.y.c(i - i % 24000L);
                }

                this.d();
            }
        }

        this.D.a("mobSpawner");
        if (this.L().b("doMobSpawning")) {
            OSpawnerAnimals.a(this, this.E, this.F, this.y.f() % 400L == 0L);
        }

        this.D.c("chunkSource");
        this.w.b();
        int j = this.a(1.0F);

        if (j != this.j) {
            this.j = j;
        }

        this.V();
        // CanaryMod: Time hook
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.TIME_CHANGE, world, this.y.f() + 1L)) {
            this.y.b(this.y.f() + 1L);
            this.y.c(this.y.g() + 1L);
        }
        this.D.c("tickPending");
        this.a(false);
        this.D.c("tickTiles");
        this.g();
        this.D.c("chunkMap");
        this.K.b();
        this.D.c("village");
        this.B.a();
        this.C.a();
        this.D.c("portalForcer");
        this.P.a(this.F());
        this.D.b();
        this.V();
    }

    public OSpawnListEntry a(OEnumCreatureType oenumcreaturetype, int i, int j, int k) {
        List list = this.I().a(oenumcreaturetype, i, j, k);

        return list != null && !list.isEmpty() ? (OSpawnListEntry) OWeightedRandom.a(this.t, (Collection) list) : null;
    }

    public void c() {
        this.N = !this.h.isEmpty();
        Iterator iterator = this.h.iterator();

        while (iterator.hasNext()) {
            OEntityPlayer oentityplayer = (OEntityPlayer) iterator.next();

            if (!oentityplayer.bw()) {
                this.N = false;
                break;
            }
        }
    }

    protected void d() {
        this.N = false;
        Iterator iterator = this.h.iterator();

        while (iterator.hasNext()) {
            OEntityPlayer oentityplayer = (OEntityPlayer) iterator.next();

            if (oentityplayer.bw()) {
                oentityplayer.a(false, false, true);
            }
        }

        this.U();
    }

    private void U() {
        // CanaryMod: Weather hook
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.WEATHER_CHANGE, world, false)) {
            this.y.g(0);
            this.y.b(false);
        }
        // CanaryMod: Thunder hook
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.THUNDER_CHANGE, world, false)) {
            this.y.f(0);
            this.y.a(false);
        } // CanaryMod: diff visibility
    }

    public boolean e() {
        if (this.N && !this.I) {
            Iterator iterator = this.h.iterator();

            OEntityPlayer oentityplayer;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                oentityplayer = (OEntityPlayer) iterator.next();
            } while (oentityplayer.bX());

            return false;
        } else {
            return false;
        }
    }

    protected void g() {
        super.g();
        int i = 0;
        int j = 0;
        Iterator iterator = this.G.iterator();

        while (iterator.hasNext()) {
            OChunkCoordIntPair ochunkcoordintpair = (OChunkCoordIntPair) iterator.next();
            int k = ochunkcoordintpair.a * 16;
            int l = ochunkcoordintpair.b * 16;

            this.D.a("getChunk");
            OChunk ochunk = this.e(ochunkcoordintpair.a, ochunkcoordintpair.b);

            this.a(k, l, ochunk);
            this.D.c("tickChunk");
            ochunk.k();
            this.D.c("thunder");
            int i1;
            int j1;
            int k1;
            int l1;

            if (this.t.nextInt(100000) == 0 && this.N() && this.M()) {
                this.k = this.k * 3 + 1013904223;
                i1 = this.k >> 2;
                j1 = k + (i1 & 15);
                k1 = l + (i1 >> 8 & 15);
                l1 = this.h(j1, k1);
                if (this.D(j1, l1, k1)) {
                    this.c(new OEntityLightningBolt(this, (double) j1, (double) l1, (double) k1));
                }
            }

            this.D.c("iceandsnow");
            int i2;

            if (this.t.nextInt(16) == 0) {
                this.k = this.k * 3 + 1013904223;
                i1 = this.k >> 2;
                j1 = i1 & 15;
                k1 = i1 >> 8 & 15;
                l1 = this.h(j1 + k, k1 + l);
                if (this.x(j1 + k, l1 - 1, k1 + l)) {
                    this.e(j1 + k, l1 - 1, k1 + l, OBlock.aW.cm);
                }

                if (this.N() && this.y(j1 + k, l1, k1 + l)) {
                    this.e(j1 + k, l1, k1 + l, OBlock.aV.cm);
                }

                if (this.N()) {
                    OBiomeGenBase obiomegenbase = this.a(j1 + k, k1 + l);

                    if (obiomegenbase.d()) {
                        i2 = this.a(j1 + k, l1 - 1, k1 + l);
                        if (i2 != 0) {
                            OBlock.p[i2].f(this, j1 + k, l1 - 1, k1 + l);
                        }
                    }
                }
            }

            this.D.c("tickTiles");
            OExtendedBlockStorage[] aoextendedblockstorage = ochunk.i();

            j1 = aoextendedblockstorage.length;

            for (k1 = 0; k1 < j1; ++k1) {
                OExtendedBlockStorage oextendedblockstorage = aoextendedblockstorage[k1];

                if (oextendedblockstorage != null && oextendedblockstorage.b()) {
                    for (int j2 = 0; j2 < 3; ++j2) {
                        this.k = this.k * 3 + 1013904223;
                        i2 = this.k >> 2;
                        int k2 = i2 & 15;
                        int l2 = i2 >> 8 & 15;
                        int i3 = i2 >> 16 & 15;
                        int j3 = oextendedblockstorage.a(k2, i3, l2);

                        ++j;
                        OBlock oblock = OBlock.p[j3];

                        if (oblock != null && oblock.t()) {
                            ++i;
                            oblock.b(this, k2 + k, i3 + oextendedblockstorage.d(), l2 + l, this.t);
                        }
                    }
                }
            }

            this.D.b();
        }
    }

    public void a(int i, int j, int k, int l, int i1) {
        this.a(i, j, k, l, i1, 0);
    }

    public void a(int i, int j, int k, int l, int i1, int j1) {
        ONextTickListEntry onextticklistentry = new ONextTickListEntry(i, j, k, l);
        byte b0 = 8;

        if (this.d && l > 0) {
            if (OBlock.p[l].l()) {
                if (this.d(onextticklistentry.a - b0, onextticklistentry.b - b0, onextticklistentry.c - b0, onextticklistentry.a + b0, onextticklistentry.b + b0, onextticklistentry.c + b0)) {
                    int k1 = this.a(onextticklistentry.a, onextticklistentry.b, onextticklistentry.c);

                    if (k1 == onextticklistentry.d && k1 > 0) {
                        OBlock.p[k1].b(this, onextticklistentry.a, onextticklistentry.b, onextticklistentry.c, this.t);
                    }
                }

                return;
            }

            i1 = 1;
        }

        if (this.d(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0)) {
            if (l > 0) {
                onextticklistentry.a((long) i1 + this.y.f());
                onextticklistentry.a(j1);
            }

            if (!this.L.contains(onextticklistentry)) {
                this.L.add(onextticklistentry);
                this.M.add(onextticklistentry);
            }
        }
    }

    public void b(int i, int j, int k, int l, int i1) {
        ONextTickListEntry onextticklistentry = new ONextTickListEntry(i, j, k, l);

        if (l > 0) {
            onextticklistentry.a((long) i1 + this.y.f());
        }

        if (!this.L.contains(onextticklistentry)) {
            this.L.add(onextticklistentry);
            this.M.add(onextticklistentry);
        }
    }

    public void h() {
        if (this.h.isEmpty()) {
            if (this.O++ >= 1200) {
                return;
            }
        } else {
            this.i();
        }

        super.h();
    }

    public void i() {
        this.O = 0;
    }

    public boolean a(boolean flag) {
        int i = this.M.size();

        if (i != this.L.size()) {
            throw new IllegalStateException("TickNextTick list out of synch");
        } else {
            if (i > 1000) {
                i = 1000;
            }

            for (int j = 0; j < i; ++j) {
                ONextTickListEntry onextticklistentry = (ONextTickListEntry) this.M.first();

                if (!flag && onextticklistentry.e > this.y.f()) {
                    break;
                }

                this.M.remove(onextticklistentry);
                this.L.remove(onextticklistentry);
                byte b0 = 8;

                if (this.d(onextticklistentry.a - b0, onextticklistentry.b - b0, onextticklistentry.c - b0, onextticklistentry.a + b0, onextticklistentry.b + b0, onextticklistentry.c + b0)) {
                    int k = this.a(onextticklistentry.a, onextticklistentry.b, onextticklistentry.c);

                    if (k == onextticklistentry.d && k > 0) {
                        try {
                            OBlock.p[k].b(this, onextticklistentry.a, onextticklistentry.b, onextticklistentry.c, this.t);
                        } catch (Throwable throwable) {
                            OCrashReport ocrashreport = OCrashReport.a(throwable, "Exception while ticking a block");
                            OCrashReportCategory ocrashreportcategory = ocrashreport.a("Block being ticked");

                            int l;

                            try {
                                l = this.h(onextticklistentry.a, onextticklistentry.b, onextticklistentry.c);
                            } catch (Throwable throwable1) {
                                l = -1;
                            }

                            OCrashReportCategory.a(ocrashreportcategory, onextticklistentry.a, onextticklistentry.b, onextticklistentry.c, k, l);
                            throw new OReportedException(ocrashreport);
                        }
                    }
                }
            }

            return !this.M.isEmpty();
        }
    }

    public List a(OChunk ochunk, boolean flag) {
        ArrayList arraylist = null;
        OChunkCoordIntPair ochunkcoordintpair = ochunk.l();
        int i = ochunkcoordintpair.a << 4;
        int j = i + 16;
        int k = ochunkcoordintpair.b << 4;
        int l = k + 16;
        Iterator iterator = this.M.iterator();

        while (iterator.hasNext()) {
            ONextTickListEntry onextticklistentry = (ONextTickListEntry) iterator.next();

            if (onextticklistentry.a >= i && onextticklistentry.a < j && onextticklistentry.c >= k && onextticklistentry.c < l) {
                if (flag) {
                    this.L.remove(onextticklistentry);
                    iterator.remove();
                }

                if (arraylist == null) {
                    arraylist = new ArrayList();
                }

                arraylist.add(onextticklistentry);
            }
        }

        return arraylist;
    }

    public void a(OEntity oentity, boolean flag) {
        if (!this.a.V() && (oentity instanceof OEntityAnimal || oentity instanceof OEntityWaterMob)) {
            oentity.x();
        }

        if (!this.a.W() && oentity instanceof OINpc) {
            oentity.x();
        }

        if (!(oentity.n instanceof OEntityPlayer)) {
            super.a(oentity, flag);
        }
    }

    public void b(OEntity oentity, boolean flag) {
        super.a(oentity, flag);
    }

    protected OIChunkProvider j() {
        OIChunkLoader oichunkloader = this.x.a(this.u);

        this.b = new OChunkProviderServer(this, oichunkloader, this.u.c());
        return this.b;
    }

    public List b(int i, int j, int k, int l, int i1, int j1) {
        ArrayList arraylist = new ArrayList();

        for (int k1 = 0; k1 < this.g.size(); ++k1) {
            OTileEntity otileentity = (OTileEntity) this.g.get(k1);

            if (otileentity.l >= i && otileentity.m >= j && otileentity.n >= k && otileentity.l < l && otileentity.m < i1 && otileentity.n < j1) {
                arraylist.add(otileentity);
            }
        }

        return arraylist;
    }

    public boolean a(OEntityPlayer oentityplayer, int i, int j, int k) {
        int l = OMathHelper.a(i - this.y.c());
        int i1 = OMathHelper.a(k - this.y.e());

        if (l > i1) {
            i1 = l;
        }

        return i1 > 16 || this.a.ad().e(oentityplayer.bR) || this.a.I();
    }

    protected void a(OWorldSettings oworldsettings) {
        if (this.T == null) {
            this.T = new OIntHashMap();
        }

        if (this.L == null) {
            this.L = new HashSet();
        }

        if (this.M == null) {
            this.M = new TreeSet();
        }

        this.b(oworldsettings);
        super.a(oworldsettings);
    }

    protected void b(OWorldSettings oworldsettings) {
        // CanaryMod: load preload plugins once!
        if (!loadedpreload) {
            etc.getLoader().loadPreloadPlugins();
            loadedpreload = true;
        }
        // CanaryMod onSpawnpointCreate hook
        Location point = (Location) etc.getLoader().callHook(PluginLoader.Hook.SPAWNPOINT_CREATE, world);

        if (point != null) {
            this.y.a((int) point.x, (int) point.y, (int) point.z);
        } else if (!this.u.e()) {
            this.y.a(0, this.u.i(), 0);
        } else {
            this.z = true;
            OWorldChunkManager oworldchunkmanager = this.u.d;
            List list = oworldchunkmanager.a();
            Random random = new Random(this.E());
            OChunkPosition ochunkposition = oworldchunkmanager.a(0, 0, 256, list, random);
            int i = 0;
            int j = this.u.i();
            int k = 0;

            if (ochunkposition != null) {
                i = ochunkposition.a;
                k = ochunkposition.c;
            } else {
                System.out.println("Unable to find spawn biome");
            }

            int l = 0;

            while (!this.u.a(i, k)) {
                i += random.nextInt(64) - random.nextInt(64);
                k += random.nextInt(64) - random.nextInt(64);
                ++l;
                if (l == 1000) {
                    break;
                }
            }

            this.y.a(i, j, k);
            this.z = false;
            if (oworldsettings.c()) {
                this.k();
            }
        }
    }

    protected void k() {
        OWorldGeneratorBonusChest oworldgeneratorbonuschest = new OWorldGeneratorBonusChest(S, 10);

        for (int i = 0; i < 10; ++i) {
            int j = this.y.c() + this.t.nextInt(6) - this.t.nextInt(6);
            int k = this.y.e() + this.t.nextInt(6) - this.t.nextInt(6);
            int l = this.i(j, k) + 1;

            if (oworldgeneratorbonuschest.a(this, this.t, j, l, k)) {
                break;
            }
        }
    }

    public OChunkCoordinates l() {
        return this.u.h();
    }

    public void a(boolean flag, OIProgressUpdate oiprogressupdate) {
        if (this.w.c()) {
            if (oiprogressupdate != null) {
                oiprogressupdate.a("Saving level");
            }

            this.a();
            if (oiprogressupdate != null) {
                oiprogressupdate.c("Saving chunks");
            }

            this.w.a(flag, oiprogressupdate);
        }
    }

    protected void a() {
        this.D();
        this.x.a(this.y, this.a.ad().q());
        this.A.a();
    }

    protected void a(OEntity oentity) {
        super.a(oentity);
        this.T.a(oentity.k, oentity);
        OEntity[] aoentity = oentity.ao();

        if (aoentity != null) {
            for (int i = 0; i < aoentity.length; ++i) {
                this.T.a(aoentity[i].k, aoentity[i]);
            }
        }
    }

    protected void b(OEntity oentity) {
        super.b(oentity);
        this.T.d(oentity.k);
        OEntity[] aoentity = oentity.ao();

        if (aoentity != null) {
            for (int i = 0; i < aoentity.length; ++i) {
                this.T.d(aoentity[i].k);
            }
        }
    }

    public OEntity a(int i) {
        return (OEntity) this.T.a(i);
    }

    public boolean c(OEntity oentity) {
        if (super.c(oentity)) {
            this.a.ad().a(oentity.t, oentity.u, oentity.v, 512.0D, this.u.h, new OPacket71Weather(oentity), this.name); // CanaryMod: multiworld
            return true;
        } else {
            return false;
        }
    }

    public void a(OEntity oentity, byte b0) {
        OPacket38EntityStatus opacket38entitystatus = new OPacket38EntityStatus(oentity.k, b0);

        this.p().b(oentity, opacket38entitystatus);
    }

    public OExplosion a(OEntity oentity, double d0, double d1, double d2, float f, boolean flag, boolean flag1) {
        OExplosion oexplosion = new OExplosion(this, oentity, d0, d1, d2, f);

        oexplosion.a = flag;
        oexplosion.b = flag1;
        oexplosion.a();
        oexplosion.a(false);
        if (!flag1) {
            oexplosion.h.clear();
        }

        Iterator iterator = this.h.iterator();

        while (iterator.hasNext()) {
            OEntityPlayer oentityplayer = (OEntityPlayer) iterator.next();

            if (oentityplayer.e(d0, d1, d2) < 4096.0D) {
                ((OEntityPlayerMP) oentityplayer).a.b(new OPacket60Explosion(d0, d1, d2, f, oexplosion.h, (OVec3) oexplosion.b().get(oentityplayer)));
            }
        }

        return oexplosion;
    }

    public void c(int i, int j, int k, int l, int i1, int j1) {
        OBlockEventData oblockeventdata = new OBlockEventData(i, j, k, l, i1, j1);
        Iterator iterator = this.Q[this.R].iterator();

        OBlockEventData oblockeventdata1;

        do {
            if (!iterator.hasNext()) {
                this.Q[this.R].add(oblockeventdata);
                return;
            }

            oblockeventdata1 = (OBlockEventData) iterator.next();
        } while (!oblockeventdata1.equals(oblockeventdata));

    }

    private void V() {
        while (!this.Q[this.R].isEmpty()) {
            int i = this.R;

            this.R ^= 1;
            Iterator iterator = this.Q[i].iterator();

            while (iterator.hasNext()) {
                OBlockEventData oblockeventdata = (OBlockEventData) iterator.next();

                if (this.a(oblockeventdata)) {
                    this.a.ad().a((double) oblockeventdata.a(), (double) oblockeventdata.b(), (double) oblockeventdata.c(), 64.0D, this.u.h, new OPacket54PlayNoteBlock(oblockeventdata.a(), oblockeventdata.b(), oblockeventdata.c(), oblockeventdata.f(), oblockeventdata.d(), oblockeventdata.e()), this.name); // CanaryMod: multiworld);
                }
            }

            this.Q[i].clear();
        }
    }

    private boolean a(OBlockEventData oblockeventdata) {
        int i = this.a(oblockeventdata.a(), oblockeventdata.b(), oblockeventdata.c());

        if (i == oblockeventdata.f()) {
            OBlock.p[i].b(this, oblockeventdata.a(), oblockeventdata.b(), oblockeventdata.c(), oblockeventdata.d(), oblockeventdata.e());
            return true;
        } else {
            return false;
        }
    }

    public void m() {
        this.x.a();
    }

    protected void n() {
        boolean flag = this.N();

        super.n();
        if (flag != this.N()) {
            if (flag) {
                this.a.ad().a((OPacket) (new OPacket70GameEvent(2, 0)));
            } else {
                this.a.ad().a((OPacket) (new OPacket70GameEvent(1, 0)));
            }
        }
    }

    public OMinecraftServer o() {
        return this.a;
    }

    public OEntityTracker p() {
        return this.J;
    }

    public OPlayerManager r() {
        return this.K;
    }

    public OTeleporter s() {
        return this.P;
    }

    /**
     * Get this worlds entity tracker to track and untrack players
     * @return
     */
    public EntityTracker getEntityTracker() {
        return this.p().getCanaryEntityTracker();
    }
}
