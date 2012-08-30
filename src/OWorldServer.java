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
    private final OEntityTracker L;
    private final OPlayerManager M;
    private Set N;
    private TreeSet O;
    public OChunkProviderServer b;
    public boolean c = false;
    public boolean d;
    private boolean P;
    private int Q = 0;
    private OServerBlockEventList[] R = new OServerBlockEventList[] { new OServerBlockEventList((OServerBlockEvent) null), new OServerBlockEventList((OServerBlockEvent) null)};
    private int S = 0;
    private static final OWeightedRandomChestContent[] T = new OWeightedRandomChestContent[] { new OWeightedRandomChestContent(OItem.D.bT, 0, 1, 3, 10), new OWeightedRandomChestContent(OBlock.x.ca, 0, 1, 3, 10), new OWeightedRandomChestContent(OBlock.J.ca, 0, 1, 3, 10), new OWeightedRandomChestContent(OItem.y.bT, 0, 1, 1, 3), new OWeightedRandomChestContent(OItem.u.bT, 0, 1, 1, 5), new OWeightedRandomChestContent(OItem.x.bT, 0, 1, 1, 3), new OWeightedRandomChestContent(OItem.t.bT, 0, 1, 1, 5), new OWeightedRandomChestContent(OItem.j.bT, 0, 2, 3, 5), new OWeightedRandomChestContent(OItem.U.bT, 0, 2, 3, 3)};
    private OIntHashMap U;

    public OWorldServer(OMinecraftServer ominecraftserver, OISaveHandler oisavehandler, String s, int i, OWorldSettings oworldsettings, OProfiler oprofiler) {
        super(oisavehandler, s, oworldsettings, OWorldProvider.a(i), oprofiler);
        this.a = ominecraftserver;
        this.L = new OEntityTracker(this);
        this.M = new OPlayerManager(this, ominecraftserver.ab().o());
        if (this.U == null) {
            this.U = new OIntHashMap();
        }

        if (this.N == null) {
            this.N = new HashSet();
        }

        if (this.O == null) {
            this.O = new TreeSet();
        }
    }

    public void b() {
        super.b();
        if (this.H().s() && this.u < 3) {
            this.u = 3;
        }

        this.w.c.b();
        if (this.e()) {
            boolean flag = false;

            if (this.G && this.u >= 1) {
                ;
            }

            if (!flag) {
                long i = this.A.f() + 24000L;

                // CanaryMod: Time hook
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.TIME_CHANGE, world, i - i % 24000L)) {
                    this.A.b(i - i % 24000L);
                }
                this.d();
            }
        }

        this.F.a("mobSpawner");
        OSpawnerAnimals.a(this, this.G, this.H && this.A.f() % 400L == 0L);
        this.F.c("chunkSource");
        this.y.b();
        int j = this.a(1.0F);

        if (j != this.k) {
            this.k = j;
        }

        this.Q();
        // CanaryMod: Time hook
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.TIME_CHANGE, world, this.A.f() + 1L)) {
            this.A.b(this.A.f() + 1L);
        }
        this.F.c("tickPending");
        this.a(false);
        this.F.c("tickTiles");
        this.g();
        this.F.c("chunkMap");
        this.M.b();
        this.F.c("village");
        this.D.a();
        this.E.a();
        this.F.b();
        this.Q();
    }

    public OSpawnListEntry a(OEnumCreatureType oenumcreaturetype, int i, int j, int k) {
        List list = this.F().a(oenumcreaturetype, i, j, k);

        return list != null && !list.isEmpty() ? (OSpawnListEntry) OWeightedRandom.a(this.v, (Collection) list) : null;
    }

    public void c() {
        this.P = !this.i.isEmpty();
        Iterator iterator = this.i.iterator();

        while (iterator.hasNext()) {
            OEntityPlayer oentityplayer = (OEntityPlayer) iterator.next();

            if (!oentityplayer.bn()) {
                this.P = false;
                break;
            }
        }
    }

    protected void d() {
        this.P = false;
        Iterator iterator = this.i.iterator();

        while (iterator.hasNext()) {
            OEntityPlayer oentityplayer = (OEntityPlayer) iterator.next();

            if (oentityplayer.bn()) {
                oentityplayer.a(false, false, true);
            }
        }

        this.P();
    }

    private void P() {
        // CanaryMod: Weather hook
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.WEATHER_CHANGE, world, false)) {
            this.A.g(0);
            this.A.b(false);
        }
        // CanaryMod: Thunder hook
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.THUNDER_CHANGE, world, false)) {
            this.A.f(0);
            this.A.a(false);
        } // CanaryMod: diff visibility
    }

    public boolean e() {
        if (this.P && !this.K) {
            Iterator iterator = this.i.iterator();

            OEntityPlayer oentityplayer;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                oentityplayer = (OEntityPlayer) iterator.next();
            } while (oentityplayer.bH());

            return false;
        } else {
            return false;
        }
    }

    protected void g() {
        super.g();
        int i = 0;
        int j = 0;
        Iterator iterator = this.I.iterator();

        while (iterator.hasNext()) {
            OChunkCoordIntPair ochunkcoordintpair = (OChunkCoordIntPair) iterator.next();
            int k = ochunkcoordintpair.a * 16;
            int l = ochunkcoordintpair.b * 16;

            this.F.a("getChunk");
            OChunk ochunk = this.e(ochunkcoordintpair.a, ochunkcoordintpair.b);

            this.a(k, l, ochunk);
            this.F.c("tickChunk");
            ochunk.k();
            this.F.c("thunder");
            int i1;
            int j1;
            int k1;
            int l1;

            if (this.v.nextInt(100000) == 0 && this.J() && this.I()) {
                this.l = this.l * 3 + 1013904223;
                i1 = this.l >> 2;
                j1 = k + (i1 & 15);
                k1 = l + (i1 >> 8 & 15);
                l1 = this.g(j1, k1);
                if (this.B(j1, l1, k1)) {
                    this.c(new OEntityLightningBolt(this, (double) j1, (double) l1, (double) k1));
                    this.r = 2;
                }
            }

            this.F.c("iceandsnow");
            int i2;

            if (this.v.nextInt(16) == 0) {
                this.l = this.l * 3 + 1013904223;
                i1 = this.l >> 2;
                j1 = i1 & 15;
                k1 = i1 >> 8 & 15;
                l1 = this.g(j1 + k, k1 + l);
                if (this.v(j1 + k, l1 - 1, k1 + l)) {
                    this.e(j1 + k, l1 - 1, k1 + l, OBlock.aT.ca);
                }

                if (this.J() && this.w(j1 + k, l1, k1 + l)) {
                    this.e(j1 + k, l1, k1 + l, OBlock.aS.ca);
                }

                if (this.J()) {
                    OBiomeGenBase obiomegenbase = this.a(j1 + k, k1 + l);

                    if (obiomegenbase.d()) {
                        i2 = this.a(j1 + k, l1 - 1, k1 + l);
                        if (i2 != 0) {
                            OBlock.m[i2].f(this, j1 + k, l1 - 1, k1 + l);
                        }
                    }
                }
            }

            this.F.c("tickTiles");
            OExtendedBlockStorage[] aoextendedblockstorage = ochunk.i();

            j1 = aoextendedblockstorage.length;

            for (k1 = 0; k1 < j1; ++k1) {
                OExtendedBlockStorage oextendedblockstorage = aoextendedblockstorage[k1];

                if (oextendedblockstorage != null && oextendedblockstorage.b()) {
                    for (int j2 = 0; j2 < 3; ++j2) {
                        this.l = this.l * 3 + 1013904223;
                        i2 = this.l >> 2;
                        int k2 = i2 & 15;
                        int l2 = i2 >> 8 & 15;
                        int i3 = i2 >> 16 & 15;
                        int j3 = oextendedblockstorage.a(k2, i3, l2);

                        ++j;
                        OBlock oblock = OBlock.m[j3];

                        if (oblock != null && oblock.r()) {
                            ++i;
                            oblock.b(this, k2 + k, i3 + oextendedblockstorage.d(), l2 + l, this.v);
                        }
                    }
                }
            }

            this.F.b();
        }
    }

    public void a(int i, int j, int k, int l, int i1) {
        ONextTickListEntry onextticklistentry = new ONextTickListEntry(i, j, k, l);
        byte b0 = 8;

        if (this.e) {
            if (this.c(onextticklistentry.a - b0, onextticklistentry.b - b0, onextticklistentry.c - b0, onextticklistentry.a + b0, onextticklistentry.b + b0, onextticklistentry.c + b0)) {
                int j1 = this.a(onextticklistentry.a, onextticklistentry.b, onextticklistentry.c);

                if (j1 == onextticklistentry.d && j1 > 0) {
                    OBlock.m[j1].b(this, onextticklistentry.a, onextticklistentry.b, onextticklistentry.c, this.v);
                }
            }
        } else {
            if (this.c(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0)) {
                if (l > 0) {
                    onextticklistentry.a((long) i1 + this.A.f());
                }

                if (!this.N.contains(onextticklistentry)) {
                    this.N.add(onextticklistentry);
                    this.O.add(onextticklistentry);
                }
            }
        }
    }

    public void b(int i, int j, int k, int l, int i1) {
        ONextTickListEntry onextticklistentry = new ONextTickListEntry(i, j, k, l);

        if (l > 0) {
            onextticklistentry.a((long) i1 + this.A.f());
        }

        if (!this.N.contains(onextticklistentry)) {
            this.N.add(onextticklistentry);
            this.O.add(onextticklistentry);
        }
    }

    public void h() {
        if (this.i.isEmpty()) {
            if (this.Q++ >= 60) {
                return;
            }
        } else {
            this.Q = 0;
        }

        super.h();
    }

    public boolean a(boolean flag) {
        int i = this.O.size();

        if (i != this.N.size()) {
            throw new IllegalStateException("TickNextTick list out of synch");
        } else {
            if (i > 1000) {
                i = 1000;
            }

            for (int j = 0; j < i; ++j) {
                ONextTickListEntry onextticklistentry = (ONextTickListEntry) this.O.first();

                if (!flag && onextticklistentry.e > this.A.f()) {
                    break;
                }

                this.O.remove(onextticklistentry);
                this.N.remove(onextticklistentry);
                byte b0 = 8;

                if (this.c(onextticklistentry.a - b0, onextticklistentry.b - b0, onextticklistentry.c - b0, onextticklistentry.a + b0, onextticklistentry.b + b0, onextticklistentry.c + b0)) {
                    int k = this.a(onextticklistentry.a, onextticklistentry.b, onextticklistentry.c);

                    if (k == onextticklistentry.d && k > 0) {
                        OBlock.m[k].b(this, onextticklistentry.a, onextticklistentry.b, onextticklistentry.c, this.v);
                    }
                }
            }

            return !this.O.isEmpty();
        }
    }

    public List a(OChunk ochunk, boolean flag) {
        ArrayList arraylist = null;
        OChunkCoordIntPair ochunkcoordintpair = ochunk.l();
        int i = ochunkcoordintpair.a << 4;
        int j = i + 16;
        int k = ochunkcoordintpair.b << 4;
        int l = k + 16;
        Iterator iterator = this.O.iterator();

        while (iterator.hasNext()) {
            ONextTickListEntry onextticklistentry = (ONextTickListEntry) iterator.next();

            if (onextticklistentry.a >= i && onextticklistentry.a < j && onextticklistentry.c >= k && onextticklistentry.c < l) {
                if (flag) {
                    this.N.remove(onextticklistentry);
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
        if (!this.a.U() && (oentity instanceof OEntityAnimal || oentity instanceof OEntityWaterMob)) {
            oentity.y();
        }

        if (!this.a.V() && oentity instanceof OINpc) {
            oentity.y();
        }

        if (!(oentity.n instanceof OEntityPlayer)) {
            super.a(oentity, flag);
        }
    }

    public void b(OEntity oentity, boolean flag) {
        super.a(oentity, flag);
    }

    protected OIChunkProvider i() {
        OIChunkLoader oichunkloader = this.z.a(this.w);

        this.b = new OChunkProviderServer(this, oichunkloader, this.w.c());
        return this.b;
    }

    public List a(int i, int j, int k, int l, int i1, int j1) {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.h.iterator();

        while (iterator.hasNext()) {
            OTileEntity otileentity = (OTileEntity) iterator.next();

            if (otileentity.l >= i && otileentity.m >= j && otileentity.n >= k && otileentity.l < l && otileentity.m < i1 && otileentity.n < j1) {
                arraylist.add(otileentity);
            }
        }

        return arraylist;
    }

    public boolean a(OEntityPlayer oentityplayer, int i, int j, int k) {
        int l = OMathHelper.a(i - this.A.c());
        int i1 = OMathHelper.a(k - this.A.e());

        if (l > i1) {
            i1 = l;
        }

        return i1 > 16 || this.a.ab().e(oentityplayer.bJ) || this.a.H();
    }

    protected void a(OWorldSettings oworldsettings) {
        if (this.U == null) {
            this.U = new OIntHashMap();
        }

        if (this.N == null) {
            this.N = new HashSet();
        }

        if (this.O == null) {
            this.O = new TreeSet();
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
            this.A.a((int) point.x, (int) point.y, (int) point.z);
        } else if (!this.w.e()) {
            this.A.a(0, this.w.i(), 0);
        } else {
            this.B = true;
            OWorldChunkManager oworldchunkmanager = this.w.c;
            List list = oworldchunkmanager.a();
            Random random = new Random(this.C());
            OChunkPosition ochunkposition = oworldchunkmanager.a(0, 0, 256, list, random);
            int i = 0;
            int j = this.w.i();
            int k = 0;

            if (ochunkposition != null) {
                i = ochunkposition.a;
                k = ochunkposition.c;
            } else {
                System.out.println("Unable to find spawn biome");
            }

            int l = 0;

            while (!this.w.a(i, k)) {
                i += random.nextInt(64) - random.nextInt(64);
                k += random.nextInt(64) - random.nextInt(64);
                ++l;
                if (l == 1000) {
                    break;
                }
            }

            this.A.a(i, j, k);
            this.B = false;
            if (oworldsettings.c()) {
                this.j();
            }
        }
    }

    protected void j() {
        OWorldGeneratorBonusChest oworldgeneratorbonuschest = new OWorldGeneratorBonusChest(T, 10);

        for (int i = 0; i < 10; ++i) {
            int j = this.A.c() + this.v.nextInt(6) - this.v.nextInt(6);
            int k = this.A.e() + this.v.nextInt(6) - this.v.nextInt(6);
            int l = this.h(j, k) + 1;

            if (oworldgeneratorbonuschest.a(this, this.v, j, l, k)) {
                break;
            }
        }
    }

    public OChunkCoordinates k() {
        return this.w.h();
    }

    public void a(boolean flag, OIProgressUpdate oiprogressupdate) {
        if (this.y.c()) {
            if (oiprogressupdate != null) {
                oiprogressupdate.a("Saving level");
            }

            this.a();
            if (oiprogressupdate != null) {
                oiprogressupdate.c("Saving chunks");
            }

            this.y.a(flag, oiprogressupdate);
        }
    }

    protected void a() {
        this.B();
        this.z.a(this.A, this.a.ab().q());
        this.C.a();
    }

    protected void a(OEntity oentity) {
        super.a(oentity);
        this.U.a(oentity.k, oentity);
        OEntity[] aoentity = oentity.al();

        if (aoentity != null) {
            OEntity[] aoentity1 = aoentity;
            int i = aoentity.length;

            for (int j = 0; j < i; ++j) {
                OEntity oentity1 = aoentity1[j];

                this.U.a(oentity1.k, oentity1);
            }
        }
    }

    protected void b(OEntity oentity) {
        super.b(oentity);
        this.U.d(oentity.k);
        OEntity[] aoentity = oentity.al();

        if (aoentity != null) {
            OEntity[] aoentity1 = aoentity;
            int i = aoentity.length;

            for (int j = 0; j < i; ++j) {
                OEntity oentity1 = aoentity1[j];

                this.U.d(oentity1.k);
            }
        }

    }

    public OEntity a(int i) {
        return (OEntity) this.U.a(i);
    }

    public boolean c(OEntity oentity) {
        if (super.c(oentity)) {
            this.a.ab().a(oentity.t, oentity.u, oentity.v, 512.0D, this.w.g, new OPacket71Weather(oentity), this.name); // CanaryMod: multiworld
            return true;
        } else {
            return false;
        }
    }

    public void a(OEntity oentity, byte b0) {
        OPacket38EntityStatus opacket38entitystatus = new OPacket38EntityStatus(oentity.k, b0);

        this.o().b(oentity, opacket38entitystatus);
    }

    public OExplosion a(OEntity oentity, double d0, double d1, double d2, float f, boolean flag) {
        OExplosion oexplosion = new OExplosion(this, oentity, d0, d1, d2, f);

        oexplosion.a = flag;
        oexplosion.a();
        oexplosion.a(false);
        Iterator iterator = this.i.iterator();

        while (iterator.hasNext()) {
            OEntityPlayer oentityplayer = (OEntityPlayer) iterator.next();

            if (oentityplayer.e(d0, d1, d2) < 4096.0D) {
                ((OEntityPlayerMP) oentityplayer).a.b(new OPacket60Explosion(d0, d1, d2, f, oexplosion.g, (OVec3) oexplosion.b().get(oentityplayer)));
            }
        }

        return oexplosion;
    }

    public void b(int i, int j, int k, int l, int i1, int j1) {
        OBlockEventData oblockeventdata = new OBlockEventData(i, j, k, l, i1, j1);
        Iterator iterator = this.R[this.S].iterator();

        OBlockEventData oblockeventdata1;

        do {
            if (!iterator.hasNext()) {
                this.R[this.S].add(oblockeventdata);
                return;
            }

            oblockeventdata1 = (OBlockEventData) iterator.next();
        } while (!oblockeventdata1.equals(oblockeventdata));

    }

    private void Q() {
        while (!this.R[this.S].isEmpty()) {
            int i = this.S;

            this.S ^= 1;
            Iterator iterator = this.R[i].iterator();

            while (iterator.hasNext()) {
                OBlockEventData oblockeventdata = (OBlockEventData) iterator.next();

                if (this.a(oblockeventdata)) {
                    this.a.ab().a((double) oblockeventdata.a(), (double) oblockeventdata.b(), (double) oblockeventdata.c(), 64.0D, this.w.g, new OPacket54PlayNoteBlock(oblockeventdata.a(), oblockeventdata.b(), oblockeventdata.c(), oblockeventdata.f(), oblockeventdata.d(), oblockeventdata.e()), this.name); // CanaryMod: multiworld
                }
            }

            this.R[i].clear();
        }
    }

    private boolean a(OBlockEventData oblockeventdata) {
        int i = this.a(oblockeventdata.a(), oblockeventdata.b(), oblockeventdata.c());

        if (i == oblockeventdata.f()) {
            OBlock.m[i].b(this, oblockeventdata.a(), oblockeventdata.b(), oblockeventdata.c(), oblockeventdata.d(), oblockeventdata.e());
            return true;
        } else {
            return false;
        }
    }

    public void l() {
        this.z.a();
    }

    protected void m() {
        boolean flag = this.J();

        super.m();
        if (flag != this.J()) {
            if (flag) {
                this.a.ab().a((OPacket) (new OPacket70GameEvent(2, 0)));
            } else {
                this.a.ab().a((OPacket) (new OPacket70GameEvent(1, 0)));
            }
        }
    }

    public OMinecraftServer n() {
        return this.a;
    }

    public OEntityTracker o() {
        return this.L;
    }

    public void a(long i) {
        long j = i - this.A.f();

        ONextTickListEntry onextticklistentry;

        for (Iterator iterator = this.N.iterator(); iterator.hasNext(); onextticklistentry.e += j) {
            onextticklistentry = (ONextTickListEntry) iterator.next();
        }

        OBlock[] aoblock = OBlock.m;
        int k = aoblock.length;

        for (int l = 0; l < k; ++l) {
            OBlock oblock = aoblock[l];

            if (oblock != null) {
                oblock.a(this, j, i);
            }
        }

        this.b(i);
    }

    public OPlayerManager q() {
        return this.M;
    }
    
    /**
     * Get this worlds entity tracker to track and untrack players
     * @return
     */
    public EntityTracker getEntityTracker() {
        return this.o().getCanaryEntityTracker();
    }
}
