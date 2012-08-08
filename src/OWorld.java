import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class OWorld implements OIBlockAccess {

    public boolean a = false;
    public List<OEntity> b = new ArrayList<OEntity>();
    private List G = new ArrayList();
    private TreeSet<ONextTickListEntry> H = new TreeSet<ONextTickListEntry>();
    private Set<ONextTickListEntry> I = new HashSet<ONextTickListEntry>();
    public List<OTileEntity> c = new ArrayList<OTileEntity>();
    private List<OTileEntity> J = new ArrayList<OTileEntity>();
    private List<OTileEntity> K = new ArrayList<OTileEntity>();
    public List<OEntityPlayer> d = new ArrayList<OEntityPlayer>();
    public List<OEntity> e = new ArrayList<OEntity>();
    private long L = 16777215L;
    public int f = 0;
    protected int g = (new Random()).nextInt();
    protected final int h = 1013904223;
    protected float i;
    protected float j;
    protected float k;
    protected float l;
    protected int m = 0;
    public int n = 0;
    public boolean o = false;
    private long M = System.currentTimeMillis();
    protected int p = 40;
    public int q;
    public Random r = new Random();
    public boolean s = false;
    public final OWorldProvider t;
    protected List<OIWorldAccess> u = new ArrayList<OIWorldAccess>();
    protected OIChunkProvider v;
    protected final OISaveHandler w;
    protected OWorldInfo x;
    public boolean y;
    private boolean N;
    public OMapStorage z;
    public final OVillageCollection A = new OVillageCollection(this);
    private final OVillageSiege O = new OVillageSiege(this);
    private ArrayList<OAxisAlignedBB> P = new ArrayList<OAxisAlignedBB>();
    private boolean Q;
    protected boolean B = true;
    protected boolean C = true;
    protected Set<OChunkCoordIntPair> D = new HashSet<OChunkCoordIntPair>();
    private int R;
    int[] E;
    private List S;
    public boolean F;
    
    // CanaryMod
    public final World world = new World((OWorldServer) this);
    boolean loadedpreload = false;
    public final String name;
    private OEntityTracker entityTracker; // Reference for multiworld

    public OBiomeGenBase a(int i, int j) {
        if (this.i(i, 0, j)) {
            OChunk ochunk = this.c(i, j);

            if (ochunk != null) {
                return ochunk.a(i & 15, j & 15, this.t.c);
            }
        }

        return this.t.c.a(i, j);
    }

    public OWorldChunkManager a() {
        return this.t.c;
    }

    public OWorld(OISaveHandler oisavehandler, String s, OWorldSettings oworldsettings, OWorldProvider oworldprovider) {
        super();
        this.R = this.r.nextInt(12000);
        this.E = new int['\u8000'];
        this.S = new ArrayList();
        this.F = false;
        this.w = oisavehandler;
        this.z = new OMapStorage(oisavehandler);
        this.x = oisavehandler.c();
        this.s = this.x == null;
        if (oworldprovider != null) {
            this.t = oworldprovider;
        } else if (this.x != null && this.x.g() != 0) {
            this.t = OWorldProvider.a(this.x.g());
        } else {
            this.t = OWorldProvider.a(0);
        }

        boolean flag = false;

        if (this.x == null) {
            this.x = new OWorldInfo(oworldsettings, s);
            flag = true;
        } else {
            this.x.a(s);
        }

        this.t.a(this);
        this.v = this.b();
        if (flag) {
            this.c();
        }

        this.g();
        this.B();
        
        this.name = s; // CanaryMod: store world name in an accessible place.
    }

    protected OIChunkProvider b() {
        OIChunkLoader oichunkloader = this.w.a(this.t);

        return new OChunkProvider(this, oichunkloader, this.t.b());
    }

    protected void c() {
        // CanaryMod: load preload plugins once!
        if (!loadedpreload) {
            etc.getLoader().loadPreloadPlugins();
            loadedpreload = true;
        }
        // CanaryMod onSpawnpointCreate hook
        Location point = (Location) etc.getLoader().callHook(PluginLoader.Hook.SPAWNPOINT_CREATE, world);
    
        if (!this.t.c()) {
            this.x.a(0, this.t.f(), 0);
        } else {
            this.y = true;
            OWorldChunkManager oworldchunkmanager = this.t.c;
            List list = oworldchunkmanager.a();
            Random random = new Random(this.n());
            OChunkPosition ochunkposition = oworldchunkmanager.a(0, 0, 256, list, random);
            int i = 0;
            int j = this.t.f();
            int k = 0;

            if (ochunkposition != null) {
                i = ochunkposition.a;
                k = ochunkposition.c;
            } else {
                System.out.println("Unable to find spawn biome");
            }

            int l = 0;

            while (!this.t.a(i, k)) {
                i += random.nextInt(64) - random.nextInt(64);
                k += random.nextInt(64) - random.nextInt(64);
                ++l;
                if (l == 1000) {
                    break;
                }
            }

            this.x.a(i, j, k);
            this.y = false;
        }
    }

    public OChunkCoordinates d() {
        return this.t.e();
    }

    public int b(int i, int j) {
        int k;

        for (k = 63; !this.g(i, k + 1, j); ++k) {
            ;
        }

        return this.a(i, k, j);
    }

    public void a(boolean flag, OIProgressUpdate oiprogressupdate) {
        if (this.v.b()) {
            if (oiprogressupdate != null) {
                oiprogressupdate.a("Saving level");
            }

            this.A();
            if (oiprogressupdate != null) {
                oiprogressupdate.b("Saving chunks");
            }

            this.v.a(flag, oiprogressupdate);
        }
    }

    private void A() {
        this.m();
        this.w.a(this.x, this.d);
        this.z.a();
    }

    public int a(int i, int j, int k) {
        return i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000 ? (j < 0 ? 0 : (j >= 256 ? 0 : this.d(i >> 4, k >> 4).a(i & 15, j, k & 15))) : 0;
    }

    public int f(int i, int j, int k) {
        return i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000 ? (j < 0 ? 0 : (j >= 256 ? 0 : this.d(i >> 4, k >> 4).b(i & 15, j, k & 15))) : 0;
    }

    public boolean g(int i, int j, int k) {
        return this.a(i, j, k) == 0;
    }

    public boolean h(int i, int j, int k) {
        int l = this.a(i, j, k);

        return OBlock.m[l] != null && OBlock.m[l].o();
    }

    public boolean i(int i, int j, int k) {
        return j >= 0 && j < 256 ? this.h(i >> 4, k >> 4) : false;
    }

    public boolean a(int i, int j, int k, int l) {
        return this.a(i - l, j - l, k - l, i + l, j + l, k + l);
    }

    public boolean a(int i, int j, int k, int l, int i1, int j1) {
        if (i1 >= 0 && j < 256) {
            i >>= 4;
            k >>= 4;
            l >>= 4;
            j1 >>= 4;

            for (int k1 = i; k1 <= l; ++k1) {
                for (int l1 = k; l1 <= j1; ++l1) {
                    if (!this.h(k1, l1)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private boolean h(int i, int j) {
        return (this.v != null ? this.v.a(i, j) : false);
    }

    public OChunk c(int i, int j) {
        return this.d(i >> 4, j >> 4);
    }

    public OChunk d(int i, int j) {
        return this.v.b(i, j);
    }

    public boolean a(int i, int j, int k, int l, int i1) {

        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            if (j < 0) {
                return false;
            } else if (j >= 256) {
                return false;
            } else {
                boolean flag = false;
                OChunk ochunk = this.d(i >> 4, k >> 4);
                // CanaryMod: Get Block Info
                Block block = this.world.getBlockAt(i, j, k);

                // CanaryMod ignore if new block is air
                if (l == 0) {
                    flag = ochunk.a(i & 15, j, k & 15, l, i1);
                } else if (!(Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_UPDATE, block, l)) {
                    flag = ochunk.a(i & 15, j, k & 15, l, i1);
                }
                OProfiler.a("checkLight");
                this.v(i, j, k);
                OProfiler.a();
                return flag;
            }
        } else {
            return false;
        }
    }

    public boolean b(int i, int j, int k, int l) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            if (j < 0) {
                return false;
            } else if (j >= 256) {
                return false;
            } else {
                OChunk ochunk = this.d(i >> 4, k >> 4);
                // CanaryMod Block Updates
                // Get Block Info
                Block block = this.world.getBlockAt(i, j, k);
                // ignore if new block is air
                boolean flag = false;

                if (l == 0) {
                    flag = ochunk.a(i & 15, j, k & 15, l);
                } else if (!(Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_UPDATE, block, l)) {
                    flag = ochunk.a(i & 15, j, k & 15, l);
                }
                OProfiler.a("checkLight");
                this.v(i, j, k);
                OProfiler.a();
                return flag;
            }
        } else {
            return false;
        }
    }

    public OMaterial d(int i, int j, int k) {
        int l = this.a(i, j, k);

        return l == 0 ? OMaterial.a : OBlock.m[l].cd;
    }

    public int c(int i, int j, int k) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            if (j < 0) {
                return 0;
            } else if (j >= 256) {
                return 0;
            } else {
                OChunk ochunk = this.d(i >> 4, k >> 4);

                i &= 15;
                k &= 15;
                return ochunk.c(i, j, k);
            }
        } else {
            return 0;
        }
    }

    public void c(int i, int j, int k, int l) {
        if (this.d(i, j, k, l)) {
            int i1 = this.a(i, j, k);

            if (OBlock.r[i1 & 4095]) {
                this.f(i, j, k, i1);
            } else {
                this.h(i, j, k, i1);
            }
        }

    }

    public boolean d(int i, int j, int k, int l) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            if (j < 0) {
                return false;
            } else if (j >= 256) {
                return false;
            } else {
                OChunk ochunk = this.d(i >> 4, k >> 4);

                i &= 15;
                k &= 15;
                return ochunk.b(i, j, k, l);
            }
        } else {
            return false;
        }
    }

    public boolean e(int i, int j, int k, int l) {
        if (this.b(i, j, k, l)) {
            this.f(i, j, k, l);
            return true;
        } else {
            return false;
        }
    }

    public boolean b(int i, int j, int k, int l, int i1) {
        if (this.a(i, j, k, l, i1)) {
            this.f(i, j, k, l);
            return true;
        } else {
            return false;
        }
    }

    public void j(int i, int j, int k) {
        for (int l = 0; l < this.u.size(); ++l) {
            this.u.get(l).a(i, j, k);
        }

    }

    public void f(int i, int j, int k, int l) {
        this.j(i, j, k);
        this.h(i, j, k, l);
    }

    public void g(int i, int j, int k, int l) {
        int i1;

        if (k > l) {
            i1 = l;
            l = k;
            k = i1;
        }

        if (!this.t.e) {
            for (i1 = k; i1 <= l; ++i1) {
                this.b(OEnumSkyBlock.a, i, i1, j);
            }
        }

        this.b(i, k, j, i, l, j);
    }

    public void k(int i, int j, int k) {
        for (int l = 0; l < this.u.size(); ++l) {
            this.u.get(l).a(i, j, k, i, j, k);
        }

    }

    public void b(int i, int j, int k, int l, int i1, int j1) {
        for (int k1 = 0; k1 < this.u.size(); ++k1) {
            this.u.get(k1).a(i, j, k, l, i1, j1);
        }

    }

    public void h(int i, int j, int k, int l) {
        this.k(i - 1, j, k, l);
        this.k(i + 1, j, k, l);
        this.k(i, j - 1, k, l);
        this.k(i, j + 1, k, l);
        this.k(i, j, k - 1, l);
        this.k(i, j, k + 1, l);
    }

    private void k(int i, int j, int k, int l) {
        if (!this.o && !this.F) {
            OBlock oblock = OBlock.m[this.a(i, j, k)];

            if (oblock != null) {
                oblock.a(this, i, j, k, l);
            }

        }
    }

    public boolean l(int i, int j, int k) {
        return this.d(i >> 4, k >> 4).d(i & 15, j, k & 15);
    }

    public int m(int i, int j, int k) {
        if (j < 0) {
            return 0;
        } else {
            if (j >= 256) {
                j = 255;
            }

            return this.d(i >> 4, k >> 4).c(i & 15, j, k & 15, 0);
        }
    }

    public int n(int i, int j, int k) {
        return this.a(i, j, k, true);
    }

    public int a(int i, int j, int k, boolean flag) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            if (flag) {
                int l = this.a(i, j, k);

                if (l == OBlock.ak.bO || l == OBlock.aA.bO || l == OBlock.aH.bO || l == OBlock.at.bO) {
                    int i1 = this.a(i, j + 1, k, false);
                    int j1 = this.a(i + 1, j, k, false);
                    int k1 = this.a(i - 1, j, k, false);
                    int l1 = this.a(i, j, k + 1, false);
                    int i2 = this.a(i, j, k - 1, false);

                    if (j1 > i1) {
                        i1 = j1;
                    }

                    if (k1 > i1) {
                        i1 = k1;
                    }

                    if (l1 > i1) {
                        i1 = l1;
                    }

                    if (i2 > i1) {
                        i1 = i2;
                    }

                    return i1;
                }
            }

            if (j < 0) {
                return 0;
            } else {
                if (j >= 256) {
                    j = 255;
                }

                OChunk ochunk = this.d(i >> 4, k >> 4);

                i &= 15;
                k &= 15;
                return ochunk.c(i, j, k, this.f);
            }
        } else {
            return 15;
        }
    }

    public int e(int i, int j) {
        if (i >= -30000000 && j >= -30000000 && i < 30000000 && j < 30000000) {
            if (!this.h(i >> 4, j >> 4)) {
                return 0;
            } else {
                OChunk ochunk = this.d(i >> 4, j >> 4);

                return ochunk.b(i & 15, j & 15);
            }
        } else {
            return 0;
        }
    }

    public int a(OEnumSkyBlock oenumskyblock, int i, int j, int k) {
        if (j < 0) {
            j = 0;
        }

        if (j >= 256) {
            j = 255;
        }

        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            int l = i >> 4;
            int i1 = k >> 4;

            if (!this.h(l, i1)) {
                return oenumskyblock.c;
            } else {
                OChunk ochunk = this.d(l, i1);

                return ochunk.a(oenumskyblock, i & 15, j, k & 15);
            }
        } else {
            return oenumskyblock.c;
        }
    }

    public void a(OEnumSkyBlock oenumskyblock, int i, int j, int k, int l) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            if (j >= 0) {
                if (j < 256) {
                    if (this.h(i >> 4, k >> 4)) {
                        OChunk ochunk = this.d(i >> 4, k >> 4);

                        ochunk.a(oenumskyblock, i & 15, j, k & 15, l);
                        etc.getLoader().callHook(PluginLoader.Hook.LIGHT_CHANGE, i, j, k, l);
                        for (int i1 = 0; i1 < this.u.size(); ++i1) {
                            this.u.get(i1).b(i, j, k);
                        }

                    }
                }
            }
        }
    }

    public void o(int i, int j, int k) {
        for (int l = 0; l < this.u.size(); ++l) {
            this.u.get(l).b(i, j, k);
        }

    }

    public float p(int i, int j, int k) {
        return this.t.f[this.n(i, j, k)];
    }

    public boolean e() {
        return this.f < 4;
    }

    public OMovingObjectPosition a(OVec3D ovec3d, OVec3D ovec3d1) {
        return this.a(ovec3d, ovec3d1, false, false);
    }

    public OMovingObjectPosition a(OVec3D ovec3d, OVec3D ovec3d1, boolean flag) {
        return this.a(ovec3d, ovec3d1, flag, false);
    }

    public OMovingObjectPosition a(OVec3D ovec3d, OVec3D ovec3d1, boolean flag, boolean flag1) {
        if (!Double.isNaN(ovec3d.a) && !Double.isNaN(ovec3d.b) && !Double.isNaN(ovec3d.c)) {
            if (!Double.isNaN(ovec3d1.a) && !Double.isNaN(ovec3d1.b) && !Double.isNaN(ovec3d1.c)) {
                int i = OMathHelper.b(ovec3d1.a);
                int j = OMathHelper.b(ovec3d1.b);
                int k = OMathHelper.b(ovec3d1.c);
                int l = OMathHelper.b(ovec3d.a);
                int i1 = OMathHelper.b(ovec3d.b);
                int j1 = OMathHelper.b(ovec3d.c);
                int k1 = this.a(l, i1, j1);
                int l1 = this.c(l, i1, j1);
                OBlock oblock = OBlock.m[k1];

                if ((!flag1 || oblock == null || oblock.e(this, l, i1, j1) != null) && k1 > 0 && oblock.a(l1, flag)) {
                    OMovingObjectPosition omovingobjectposition = oblock.a(this, l, i1, j1, ovec3d, ovec3d1);

                    if (omovingobjectposition != null) {
                        return omovingobjectposition;
                    }
                }

                k1 = 200;

                while (k1-- >= 0) {
                    if (Double.isNaN(ovec3d.a) || Double.isNaN(ovec3d.b) || Double.isNaN(ovec3d.c)) {
                        return null;
                    }

                    if (l == i && i1 == j && j1 == k) {
                        return null;
                    }

                    boolean flag2 = true;
                    boolean flag3 = true;
                    boolean flag4 = true;
                    double d0 = 999.0D;
                    double d1 = 999.0D;
                    double d2 = 999.0D;

                    if (i > l) {
                        d0 = (double) l + 1.0D;
                    } else if (i < l) {
                        d0 = (double) l + 0.0D;
                    } else {
                        flag2 = false;
                    }

                    if (j > i1) {
                        d1 = (double) i1 + 1.0D;
                    } else if (j < i1) {
                        d1 = (double) i1 + 0.0D;
                    } else {
                        flag3 = false;
                    }

                    if (k > j1) {
                        d2 = (double) j1 + 1.0D;
                    } else if (k < j1) {
                        d2 = (double) j1 + 0.0D;
                    } else {
                        flag4 = false;
                    }

                    double d3 = 999.0D;
                    double d4 = 999.0D;
                    double d5 = 999.0D;
                    double d6 = ovec3d1.a - ovec3d.a;
                    double d7 = ovec3d1.b - ovec3d.b;
                    double d8 = ovec3d1.c - ovec3d.c;

                    if (flag2) {
                        d3 = (d0 - ovec3d.a) / d6;
                    }

                    if (flag3) {
                        d4 = (d1 - ovec3d.b) / d7;
                    }

                    if (flag4) {
                        d5 = (d2 - ovec3d.c) / d8;
                    }

                    boolean flag5 = false;
                    byte b0;

                    if (d3 < d4 && d3 < d5) {
                        if (i > l) {
                            b0 = 4;
                        } else {
                            b0 = 5;
                        }

                        ovec3d.a = d0;
                        ovec3d.b += d7 * d3;
                        ovec3d.c += d8 * d3;
                    } else if (d4 < d5) {
                        if (j > i1) {
                            b0 = 0;
                        } else {
                            b0 = 1;
                        }

                        ovec3d.a += d6 * d4;
                        ovec3d.b = d1;
                        ovec3d.c += d8 * d4;
                    } else {
                        if (k > j1) {
                            b0 = 2;
                        } else {
                            b0 = 3;
                        }

                        ovec3d.a += d6 * d5;
                        ovec3d.b += d7 * d5;
                        ovec3d.c = d2;
                    }

                    OVec3D ovec3d2 = OVec3D.b(ovec3d.a, ovec3d.b, ovec3d.c);

                    l = (int) (ovec3d2.a = (double) OMathHelper.b(ovec3d.a));
                    if (b0 == 5) {
                        --l;
                        ++ovec3d2.a;
                    }

                    i1 = (int) (ovec3d2.b = (double) OMathHelper.b(ovec3d.b));
                    if (b0 == 1) {
                        --i1;
                        ++ovec3d2.b;
                    }

                    j1 = (int) (ovec3d2.c = (double) OMathHelper.b(ovec3d.c));
                    if (b0 == 3) {
                        --j1;
                        ++ovec3d2.c;
                    }

                    int i2 = this.a(l, i1, j1);
                    int j2 = this.c(l, i1, j1);
                    OBlock oblock1 = OBlock.m[i2];

                    if ((!flag1 || oblock1 == null || oblock1.e(this, l, i1, j1) != null) && i2 > 0 && oblock1.a(j2, flag)) {
                        OMovingObjectPosition omovingobjectposition1 = oblock1.a(this, l, i1, j1, ovec3d, ovec3d1);

                        if (omovingobjectposition1 != null) {
                            return omovingobjectposition1;
                        }
                    }
                }

                return null;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void a(OEntity oentity, String s, float f, float f1) {
        for (int i = 0; i < this.u.size(); ++i) {
            this.u.get(i).a(s, oentity.bm, oentity.bn - (double) oentity.bF, oentity.bo, f, f1);
        }

    }

    public void a(double d0, double d1, double d2, String s, float f, float f1) {
        for (int i = 0; i < this.u.size(); ++i) {
            this.u.get(i).a(s, d0, d1, d2, f, f1);
        }

    }

    public void a(String s, int i, int j, int k) {
        for (int l = 0; l < this.u.size(); ++l) {
            this.u.get(l).a(s, i, j, k);
        }

    }

    public void a(String s, double d0, double d1, double d2, double d3, double d4, double d5) {
        for (int i = 0; i < this.u.size(); ++i) {
            this.u.get(i).a(s, d0, d1, d2, d3, d4, d5);
        }

    }

    public boolean a(OEntity oentity) {
        this.e.add(oentity);
        return true;
    }

    public boolean b(OEntity oentity) {
        // CanaryMod: mob spawn hook
        if (oentity instanceof OEntityLiving && !(oentity instanceof OEntityPlayer)) {
            if ((etc.getInstance().getMobSpawnRate() < 100 && etc.getInstance().getMobSpawnRate() > 0 && etc.getInstance().getMobSpawnRate() <= r.nextInt(101)) || etc.getInstance().getMobSpawnRate() <= 0 || (Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob((OEntityLiving) oentity)))) {
                return false;
            }
        }
        
        int i = OMathHelper.b(oentity.bm / 16.0D);
        int j = OMathHelper.b(oentity.bo / 16.0D);
        boolean flag = false;

        if (oentity instanceof OEntityPlayer) {
            flag = true;
        }

        if (!flag && !this.h(i, j)) {
            return false;
        } else {
            if (oentity instanceof OEntityPlayer) {
                OEntityPlayer oentityplayer = (OEntityPlayer) oentity;

                this.d.add(oentityplayer);
                this.t();
            }

            this.d(i, j).a(oentity);
            this.b.add(oentity);
            this.c(oentity);
            return true;
        }
    }

    protected void c(OEntity oentity) {
        for (int i = 0; i < this.u.size(); ++i) {
            this.u.get(i).a(oentity);
        }

    }

    protected void d(OEntity oentity) {
        for (int i = 0; i < this.u.size(); ++i) {
            this.u.get(i).b(oentity);
        }

    }

    public void e(OEntity oentity) {
        if (oentity.bg != null) {
            oentity.bg.b((OEntity) null);
        }

        if (oentity.bh != null) {
            oentity.b((OEntity) null);
        }

        oentity.X();
        if (oentity instanceof OEntityPlayer) {
            this.d.remove((OEntityPlayer) oentity);
            this.t();
        }

    }

    public void f(OEntity oentity) {
        oentity.X(); 
        if (oentity instanceof OEntityPlayer) {
            this.d.remove((OEntityPlayer) oentity);
            this.t();
        }

        int i = oentity.ca;
        int j = oentity.cc;

        if (oentity.bZ && this.h(i, j)) {
            this.d(i, j).b(oentity);
        }

        this.b.remove(oentity);
        this.d(oentity);
    }

    public void a(OIWorldAccess oiworldaccess) {
        this.u.add(oiworldaccess);
    }

    public List<OAxisAlignedBB> a(OEntity oentity, OAxisAlignedBB oaxisalignedbb) {
        this.P.clear();
        int i = OMathHelper.b(oaxisalignedbb.a);
        int j = OMathHelper.b(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.b(oaxisalignedbb.b);
        int l = OMathHelper.b(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.b(oaxisalignedbb.c);
        int j1 = OMathHelper.b(oaxisalignedbb.f + 1.0D);

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = i1; l1 < j1; ++l1) {
                if (this.i(k1, 64, l1)) {
                    for (int i2 = k - 1; i2 < l; ++i2) {
                        OBlock oblock = OBlock.m[this.a(k1, i2, l1)];

                        if (oblock != null) {
                            oblock.a(this, k1, i2, l1, oaxisalignedbb, this.P);
                        }
                    }
                }
            }
        }

        double d0 = 0.25D;
        List list = this.b(oentity, oaxisalignedbb.b(d0, d0, d0));

        // CanaryMod: Implemented fix via M4411K4 VEHICLE_COLLISION hook
        Minecart minecart = null;

        if (oentity instanceof OEntityMinecart) {
            minecart = ((OEntityMinecart) oentity).cart;
        }
        
        for (int j2 = 0; j2 < list.size(); ++j2) {
            OEntity oentity = (OEntity) list.get(j2);
            
            OAxisAlignedBB oaxisalignedbb1 = oentity.h();

            if (oaxisalignedbb1 != null && oaxisalignedbb1.a(oaxisalignedbb)) {

                // this collided with a boat
                if (minecart != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_COLLISION, minecart, oentity.entity)) { // Canary
                    continue;
                } // Canary
                
                this.P.add(oaxisalignedbb1);
            }

            // appears to only be for boats and minecarts currently. Everything else returns null
            oaxisalignedbb1 = oentity.b_(oentity); // originally: oaxisalignedbb1 = oentity.b_((OEntity) list.get(j2));
            if (oaxisalignedbb1 != null && oaxisalignedbb1.a(oaxisalignedbb)) {

                // this collided with entity
                if (minecart != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_COLLISION, minecart, oentity.entity)) { // Canary
                    continue;
                } // Canary
                
                this.P.add(oaxisalignedbb1);
            }
        }
        // CanaryMod: End
        
        return this.P;
    }

    public int a(float f) {
        float f1 = this.b(f);
        float f2 = 1.0F - (OMathHelper.b(f1 * 3.1415927F * 2.0F) * 2.0F + 0.5F);

        if (f2 < 0.0F) {
            f2 = 0.0F;
        }

        if (f2 > 1.0F) {
            f2 = 1.0F;
        }

        f2 = 1.0F - f2;
        f2 = (float) ((double) f2 * (1.0D - (double) (this.d(f) * 5.0F) / 16.0D));
        f2 = (float) ((double) f2 * (1.0D - (double) (this.c(f) * 5.0F) / 16.0D));
        f2 = 1.0F - f2;
        return (int) (f2 * 11.0F);
    }

    public float b(float f) {
        return this.t.a(this.x.f(), f);
    }

    public int f(int i, int j) {
        return this.c(i, j).d(i & 15, j & 15);
    }

    public int g(int i, int j) {
        OChunk ochunk = this.c(i, j);
        int k = ochunk.g() + 16;

        i &= 15;

        for (j &= 15; k > 0; --k) {
            int l = ochunk.a(i, k, j);

            if (l != 0 && OBlock.m[l].cd.c() && OBlock.m[l].cd != OMaterial.i) {
                return k + 1;
            }
        }

        return -1;
    }

    public void c(int i, int j, int k, int l, int i1) {
        ONextTickListEntry onextticklistentry = new ONextTickListEntry(i, j, k, l);
        byte b0 = 8;

        if (this.a) {
            if (this.a(onextticklistentry.a - b0, onextticklistentry.b - b0, onextticklistentry.c - b0, onextticklistentry.a + b0, onextticklistentry.b + b0, onextticklistentry.c + b0)) {
                int j1 = this.a(onextticklistentry.a, onextticklistentry.b, onextticklistentry.c);

                if (j1 == onextticklistentry.d && j1 > 0) {
                    OBlock.m[j1].a(this, onextticklistentry.a, onextticklistentry.b, onextticklistentry.c, this.r);
                }
            }

        } else {
            if (this.a(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0)) {
                if (l > 0) {
                    onextticklistentry.a((long) i1 + this.x.f());
                }

                if (!this.I.contains(onextticklistentry)) {
                    this.I.add(onextticklistentry);
                    this.H.add(onextticklistentry);
                }
            }

        }
    }

    public void d(int i, int j, int k, int l, int i1) {
        ONextTickListEntry onextticklistentry = new ONextTickListEntry(i, j, k, l);

        if (l > 0) {
            onextticklistentry.a((long) i1 + this.x.f());
        }

        if (!this.I.contains(onextticklistentry)) {
            this.I.add(onextticklistentry);
            this.H.add(onextticklistentry);
        }

    }

    public void f() {
        OProfiler.a("entities");
        OProfiler.a("global");

        int i;
        OEntity oentity;

        for (i = 0; i < this.e.size(); ++i) {
            oentity = this.e.get(i);
            oentity.F_();
            if (oentity.bE) {
                this.e.remove(i--);
            }
        }

        OProfiler.b("remove");
        this.b.removeAll(this.G);

        int j;
        int k;

        for (i = 0; i < this.G.size(); ++i) {
            oentity = (OEntity) this.G.get(i);
            j = oentity.ca;
            k = oentity.cc;
            if (oentity.bZ && this.h(j, k)) {
                this.d(j, k).b(oentity);
            }
        }

        for (i = 0; i < this.G.size(); ++i) {
            this.d((OEntity) this.G.get(i));
        }

        this.G.clear();
        OProfiler.b("regular");

        for (i = 0; i < this.b.size(); ++i) {
            oentity = this.b.get(i);
            if (oentity.bh != null) {
                if (!oentity.bh.bE && oentity.bh.bg == oentity) {
                    continue;
                }

                oentity.bh.bg = null;
                oentity.bh = null;
            }

            if (!oentity.bE) {
                this.g(oentity);
            }

            OProfiler.a("remove");
            if (oentity.bE) {
                j = oentity.ca;
                k = oentity.cc;
                if (oentity.bZ && this.h(j, k)) {
                    this.d(j, k).b(oentity);
                }

                this.b.remove(i--);
                this.d(oentity);
            }

            OProfiler.a();
        }

        OProfiler.b("tileEntities");
        this.Q = true;
        Iterator<OTileEntity> i0 = this.c.iterator();

        while (i0.hasNext()) {
            OTileEntity otileentity = i0.next();

            if (!otileentity.l() && otileentity.k != null && this.i(otileentity.l, otileentity.m, otileentity.n)) {
                otileentity.q_();
            }

            if (otileentity.l()) {
                i0.remove();
                if (this.h(otileentity.l >> 4, otileentity.n >> 4)) {
                    OChunk ochunk = this.d(otileentity.l >> 4, otileentity.n >> 4);

                    if (ochunk != null) {
                        ochunk.f(otileentity.l & 15, otileentity.m, otileentity.n & 15);
                    }
                }
            }
        }

        this.Q = false;
        if (!this.K.isEmpty()) {
            this.c.removeAll(this.K);
            this.K.clear();
        }

        OProfiler.b("pendingTileEntities");
        if (!this.J.isEmpty()) {
            Iterator<OTileEntity> var6 = this.J.iterator();

            while (var6.hasNext()) {
                OTileEntity otileentity1 = var6.next();

                if (!otileentity1.l()) {
                    if (!this.c.contains(otileentity1)) {
                        this.c.add(otileentity1);
                    }

                    if (this.h(otileentity1.l >> 4, otileentity1.n >> 4)) {
                        OChunk ochunk1 = this.d(otileentity1.l >> 4, otileentity1.n >> 4);

                        if (ochunk1 != null) {
                            ochunk1.a(otileentity1.l & 15, otileentity1.m, otileentity1.n & 15, otileentity1);
                        }
                    }

                    this.j(otileentity1.l, otileentity1.m, otileentity1.n);
                }
            }

            this.J.clear();
        }

        OProfiler.a();
        OProfiler.a();
    }

    public void a(Collection collection) {
        if (this.Q) {
            this.J.addAll(collection);
        } else {
            this.c.addAll(collection);
        }

    }

    public void g(OEntity oentity) {
        this.a(oentity, true);
    }

    public void a(OEntity oentity, boolean flag) {
        int i = OMathHelper.b(oentity.bm);
        int j = OMathHelper.b(oentity.bo);
        byte b0 = 32;

        if (!flag || this.a(i - b0, 0, j - b0, i + b0, 0, j + b0)) {
            oentity.bL = oentity.bm;
            oentity.bM = oentity.bn;
            oentity.bN = oentity.bo;
            oentity.bu = oentity.bs;
            oentity.bv = oentity.bt;
            if (flag && oentity.bZ) {
                if (oentity.bh != null) {
                    oentity.R();
                } else {
                    oentity.F_();
                }
            }

            OProfiler.a("chunkCheck");
            if (Double.isNaN(oentity.bm) || Double.isInfinite(oentity.bm)) {
                oentity.bm = oentity.bL;
            }

            if (Double.isNaN(oentity.bn) || Double.isInfinite(oentity.bn)) {
                oentity.bn = oentity.bM;
            }

            if (Double.isNaN(oentity.bo) || Double.isInfinite(oentity.bo)) {
                oentity.bo = oentity.bN;
            }

            if (Double.isNaN((double) oentity.bt) || Double.isInfinite((double) oentity.bt)) {
                oentity.bt = oentity.bv;
            }

            if (Double.isNaN((double) oentity.bs) || Double.isInfinite((double) oentity.bs)) {
                oentity.bs = oentity.bu;
            }

            int k = OMathHelper.b(oentity.bm / 16.0D);
            int l = OMathHelper.b(oentity.bn / 16.0D);
            int i1 = OMathHelper.b(oentity.bo / 16.0D);

            if (!oentity.bZ || oentity.ca != k || oentity.cb != l || oentity.cc != i1) {
                if (oentity.bZ && this.h(oentity.ca, oentity.cc)) {
                    this.d(oentity.ca, oentity.cc).a(oentity, oentity.cb);
                }

                if (this.h(k, i1)) {
                    oentity.bZ = true;
                    this.d(k, i1).a(oentity);
                } else {
                    oentity.bZ = false;
                }
            }

            OProfiler.a();
            if (flag && oentity.bZ && oentity.bg != null) {
                if (!oentity.bg.bE && oentity.bg.bh == oentity) {
                    this.g(oentity.bg);
                } else {
                    oentity.bg.bh = null;
                    oentity.bg = null;
                }
            }

        }
    }

    public boolean a(OAxisAlignedBB oaxisalignedbb) {
        List list = this.b((OEntity) null, oaxisalignedbb);

        for (int i = 0; i < list.size(); ++i) {
            OEntity oentity = (OEntity) list.get(i);

            if (!oentity.bE && oentity.bf) {
                return false;
            }
        }

        return true;
    }

    public boolean b(OAxisAlignedBB oaxisalignedbb) {
        int i = OMathHelper.b(oaxisalignedbb.a);
        int j = OMathHelper.b(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.b(oaxisalignedbb.b);
        int l = OMathHelper.b(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.b(oaxisalignedbb.c);
        int j1 = OMathHelper.b(oaxisalignedbb.f + 1.0D);

        if (oaxisalignedbb.a < 0.0D) {
            --i;
        }

        if (oaxisalignedbb.b < 0.0D) {
            --k;
        }

        if (oaxisalignedbb.c < 0.0D) {
            --i1;
        }

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    OBlock oblock = OBlock.m[this.a(k1, l1, i2)];

                    if (oblock != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean c(OAxisAlignedBB oaxisalignedbb) {
        int i = OMathHelper.b(oaxisalignedbb.a);
        int j = OMathHelper.b(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.b(oaxisalignedbb.b);
        int l = OMathHelper.b(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.b(oaxisalignedbb.c);
        int j1 = OMathHelper.b(oaxisalignedbb.f + 1.0D);

        if (oaxisalignedbb.a < 0.0D) {
            --i;
        }

        if (oaxisalignedbb.b < 0.0D) {
            --k;
        }

        if (oaxisalignedbb.c < 0.0D) {
            --i1;
        }

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    OBlock oblock = OBlock.m[this.a(k1, l1, i2)];

                    if (oblock != null && oblock.cd.d()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean d(OAxisAlignedBB oaxisalignedbb) {
        int i = OMathHelper.b(oaxisalignedbb.a);
        int j = OMathHelper.b(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.b(oaxisalignedbb.b);
        int l = OMathHelper.b(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.b(oaxisalignedbb.c);
        int j1 = OMathHelper.b(oaxisalignedbb.f + 1.0D);

        if (this.a(i, k, i1, j, l, j1)) {
            for (int k1 = i; k1 < j; ++k1) {
                for (int l1 = k; l1 < l; ++l1) {
                    for (int i2 = i1; i2 < j1; ++i2) {
                        int j2 = this.a(k1, l1, i2);

                        if (j2 == OBlock.ar.bO || j2 == OBlock.C.bO || j2 == OBlock.D.bO) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean a(OAxisAlignedBB oaxisalignedbb, OMaterial omaterial, OEntity oentity) {
        int i = OMathHelper.b(oaxisalignedbb.a);
        int j = OMathHelper.b(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.b(oaxisalignedbb.b);
        int l = OMathHelper.b(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.b(oaxisalignedbb.c);
        int j1 = OMathHelper.b(oaxisalignedbb.f + 1.0D);

        if (!this.a(i, k, i1, j, l, j1)) {
            return false;
        } else {
            boolean flag = false;
            OVec3D ovec3d = OVec3D.b(0.0D, 0.0D, 0.0D);

            for (int k1 = i; k1 < j; ++k1) {
                for (int l1 = k; l1 < l; ++l1) {
                    for (int i2 = i1; i2 < j1; ++i2) {
                        OBlock oblock = OBlock.m[this.a(k1, l1, i2)];

                        if (oblock != null && oblock.cd == omaterial) {
                            double d0 = (double) ((float) (l1 + 1) - OBlockFluid.d(this.c(k1, l1, i2)));

                            if ((double) l >= d0) {
                                flag = true;
                                oblock.a(this, k1, l1, i2, oentity, ovec3d);
                            }
                        }
                    }
                }
            }

            if (ovec3d.c() > 0.0D) {
                ovec3d = ovec3d.b();
                double d1 = 0.014D;

                oentity.bp += ovec3d.a * d1;
                oentity.bq += ovec3d.b * d1;
                oentity.br += ovec3d.c * d1;
            }

            return flag;
        }
    }

    public boolean a(OAxisAlignedBB oaxisalignedbb, OMaterial omaterial) {
        int i = OMathHelper.b(oaxisalignedbb.a);
        int j = OMathHelper.b(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.b(oaxisalignedbb.b);
        int l = OMathHelper.b(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.b(oaxisalignedbb.c);
        int j1 = OMathHelper.b(oaxisalignedbb.f + 1.0D);

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    OBlock oblock = OBlock.m[this.a(k1, l1, i2)];

                    if (oblock != null && oblock.cd == omaterial) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean b(OAxisAlignedBB oaxisalignedbb, OMaterial omaterial) {
        int i = OMathHelper.b(oaxisalignedbb.a);
        int j = OMathHelper.b(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.b(oaxisalignedbb.b);
        int l = OMathHelper.b(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.b(oaxisalignedbb.c);
        int j1 = OMathHelper.b(oaxisalignedbb.f + 1.0D);

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    OBlock oblock = OBlock.m[this.a(k1, l1, i2)];

                    if (oblock != null && oblock.cd == omaterial) {
                        int j2 = this.c(k1, l1, i2);
                        double d0 = (double) (l1 + 1);

                        if (j2 < 8) {
                            d0 = (double) (l1 + 1) - (double) j2 / 8.0D;
                        }

                        if (d0 >= oaxisalignedbb.b) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public OExplosion a(OEntity oentity, double d0, double d1, double d2, float f) {
        return this.a(oentity, d0, d1, d2, f, false);
    }

    public OExplosion a(OEntity oentity, double d0, double d1, double d2, float f, boolean flag) {
        OExplosion oexplosion = new OExplosion(this, oentity, d0, d1, d2, f);

        oexplosion.a = flag;
        oexplosion.a();
        oexplosion.a(true);
        return oexplosion;
    }

    public float a(OVec3D ovec3d, OAxisAlignedBB oaxisalignedbb) {
        double d0 = 1.0D / ((oaxisalignedbb.d - oaxisalignedbb.a) * 2.0D + 1.0D);
        double d1 = 1.0D / ((oaxisalignedbb.e - oaxisalignedbb.b) * 2.0D + 1.0D);
        double d2 = 1.0D / ((oaxisalignedbb.f - oaxisalignedbb.c) * 2.0D + 1.0D);
        int i = 0;
        int j = 0;

        for (float f = 0.0F; f <= 1.0F; f = (float) ((double) f + d0)) {
            for (float f1 = 0.0F; f1 <= 1.0F; f1 = (float) ((double) f1 + d1)) {
                for (float f2 = 0.0F; f2 <= 1.0F; f2 = (float) ((double) f2 + d2)) {
                    double d3 = oaxisalignedbb.a + (oaxisalignedbb.d - oaxisalignedbb.a) * (double) f;
                    double d4 = oaxisalignedbb.b + (oaxisalignedbb.e - oaxisalignedbb.b) * (double) f1;
                    double d5 = oaxisalignedbb.c + (oaxisalignedbb.f - oaxisalignedbb.c) * (double) f2;

                    if (this.a(OVec3D.b(d3, d4, d5), ovec3d) == null) {
                        ++i;
                    }

                    ++j;
                }
            }
        }

        return (float) i / (float) j;
    }

    public boolean a(OEntityPlayer oentityplayer, int i, int j, int k, int l) {
        if (l == 0) {
            --j;
        }

        if (l == 1) {
            ++j;
        }

        if (l == 2) {
            --k;
        }

        if (l == 3) {
            ++k;
        }

        if (l == 4) {
            --i;
        }

        if (l == 5) {
            ++i;
        }

        if (this.a(i, j, k) == OBlock.ar.bO) {
            this.a(oentityplayer, 1004, i, j, k, 0);
            this.e(i, j, k, 0);
            return true;
        } else {
            return false;
        }
    }

    public OTileEntity b(int i, int j, int k) {
        if (j >= 256) {
            return null;
        } else {
            OChunk ochunk = this.d(i >> 4, k >> 4);

            if (ochunk == null) {
                return null;
            } else {
                OTileEntity otileentity = ochunk.e(i & 15, j, k & 15);

                if (otileentity == null) {
                    Iterator<OTileEntity> var6 = this.J.iterator();

                    while (var6.hasNext()) {
                        OTileEntity otileentity1 = var6.next();

                        if (!otileentity1.l() && otileentity1.l == i && otileentity1.m == j && otileentity1.n == k) {
                            otileentity = otileentity1;
                            break;
                        }
                    }
                }

                return otileentity;
            }
        }
    }

    public void a(int i, int j, int k, OTileEntity otileentity) {
        if (otileentity != null && !otileentity.l()) {
            if (this.Q) {
                otileentity.l = i;
                otileentity.m = j;
                otileentity.n = k;
                this.J.add(otileentity);
            } else {
                this.c.add(otileentity);
                OChunk ochunk = this.d(i >> 4, k >> 4);

                if (ochunk != null) {
                    ochunk.a(i & 15, j, k & 15, otileentity);
                }
            }
        }

    }

    public void q(int i, int j, int k) {
        OTileEntity otileentity = this.b(i, j, k);

        if (otileentity != null && this.Q) {
            otileentity.j();
            this.J.remove(otileentity);
        } else {
            if (otileentity != null) {
                this.J.remove(otileentity);
                this.c.remove(otileentity);
            }

            OChunk ochunk = this.d(i >> 4, k >> 4);

            if (ochunk != null) {
                ochunk.f(i & 15, j, k & 15);
            }
        }

    }

    public void a(OTileEntity otileentity) {
        this.K.add(otileentity);
    }

    public boolean r(int i, int j, int k) {
        OBlock oblock = OBlock.m[this.a(i, j, k)];

        return oblock == null ? false : oblock.a();
    }

    public boolean e(int i, int j, int k) {
        return OBlock.g(this.a(i, j, k));
    }

    public boolean b(int i, int j, int k, boolean flag) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            OChunk ochunk = this.v.b(i >> 4, k >> 4);

            if (ochunk != null && !ochunk.f()) {
                OBlock oblock = OBlock.m[this.a(i, j, k)];

                return oblock == null ? false : oblock.cd.j() && oblock.b();
            } else {
                return flag;
            }
        } else {
            return flag;
        }
    }

    public void g() {
        int i = this.a(1.0F);

        if (i != this.f) {
            this.f = i;
        }

    }

    public void a(boolean flag, boolean flag1) {
        this.B = flag;
        this.C = flag1;
    }

    public void h() {
        if (this.s().o() && this.q < 3) {
            this.q = 3;
        }

        this.t.c.b();
        this.i();
        long i;

        if (this.v()) {
            boolean flag = false;

            if (this.B && this.q >= 1) {
                ;
            }

            if (!flag) {
                i = this.x.f() + 24000L;
                this.x.a(i - i % 24000L);
                this.u();
            }
        }

        OProfiler.a("mobSpawner");
        OSpawnerAnimals.a(this, this.B, this.C && this.x.f() % 400L == 0L);
        OProfiler.b("chunkSource");
        this.v.a();
        int j = this.a(1.0F);

        if (j != this.f) {
            this.f = j;
        }

        i = this.x.f() + 1L;
        if (i % (long) this.p == 0L) {
            OProfiler.b("save");
            this.a(false, (OIProgressUpdate) null);
        }
        
        // CanaryMod: Time hook
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.TIME_CHANGE, world, i)) {
            this.x.a(i);
        }

        OProfiler.b("tickPending");
        this.a(false);
        OProfiler.b("tickTiles");
        this.l();
        OProfiler.b("village");
        this.A.a();
        this.O.a();
        OProfiler.a();
    }

    private void B() {
        if (this.x.k()) {
            this.j = 1.0F;
            if (this.x.i()) {
                this.l = 1.0F;
            }
        }

    }

    protected void i() {
        if (!this.t.e) {
            if (this.m > 0) {
                --this.m;
            }

            int i = this.x.j();

            if (i <= 0) {
                if (this.x.i()) {
                    this.x.b(this.r.nextInt(12000) + 3600);
                } else {
                    this.x.b(this.r.nextInt(168000) + 12000);
                }
            } else {
                --i;
                this.x.b(i);
                if (i <= 0) {
                    // CanaryMod: Thunder hook
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.THUNDER_CHANGE, world, !this.x.i())) {
                        this.x.a(!this.x.i());
                    }
                }
            }

            int j = this.x.l();

            if (j <= 0) {
                if (this.x.k()) {
                    this.x.c(this.r.nextInt(12000) + 12000);
                } else {
                    this.x.c(this.r.nextInt(168000) + 12000);
                }
            } else {
                --j;
                this.x.c(j);
                if (j <= 0) {
                    // CanaryMod: Thunder hook
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.WEATHER_CHANGE, world, !this.x.k())) {
                        this.x.b(!this.x.k());
                    }
                }
            }

            this.i = this.j;
            if (this.x.k()) {
                this.j = (float) ((double) this.j + 0.01D);
            } else {
                this.j = (float) ((double) this.j - 0.01D);
            }

            if (this.j < 0.0F) {
                this.j = 0.0F;
            }

            if (this.j > 1.0F) {
                this.j = 1.0F;
            }

            this.k = this.l;
            if (this.x.i()) {
                this.l = (float) ((double) this.l + 0.01D);
            } else {
                this.l = (float) ((double) this.l - 0.01D);
            }

            if (this.l < 0.0F) {
                this.l = 0.0F;
            }

            if (this.l > 1.0F) {
                this.l = 1.0F;
            }

        }
    }

    private void C() {
        // CanaryMod: Weather hook
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.WEATHER_CHANGE, world, !this.x.k())) {
            this.x.c(0);
            this.x.b(false);
        }
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.THUNDER_CHANGE, world, !this.x.i())) {
            this.x.b(0);
            this.x.a(false);
        }
    }

    public void j() {
        this.x.c(1);
    }

    protected void k() {
        this.D.clear();
        OProfiler.a("buildList");

        int i;
        OEntityPlayer oentityplayer;
        int j;
        int k;

        for (i = 0; i < this.d.size(); ++i) {
            oentityplayer = this.d.get(i);
            j = OMathHelper.b(oentityplayer.bm / 16.0D);
            k = OMathHelper.b(oentityplayer.bo / 16.0D);
            byte b0 = 7;

            for (int l = -b0; l <= b0; ++l) {
                for (int i1 = -b0; i1 <= b0; ++i1) {
                    this.D.add(new OChunkCoordIntPair(l + j, i1 + k));
                }
            }
        }

        OProfiler.a();
        if (this.R > 0) {
            --this.R;
        }

        OProfiler.a("playerCheckLight");
        if (!this.d.isEmpty()) {
            i = this.r.nextInt(this.d.size());
            oentityplayer = this.d.get(i);
            j = OMathHelper.b(oentityplayer.bm) + this.r.nextInt(11) - 5;
            k = OMathHelper.b(oentityplayer.bn) + this.r.nextInt(11) - 5;
            int j1 = OMathHelper.b(oentityplayer.bo) + this.r.nextInt(11) - 5;

            this.v(j, k, j1);
        }

        OProfiler.a();
    }

    protected void a(int i, int j, OChunk ochunk) {
        OProfiler.b("tickChunk");
        ochunk.j();
        OProfiler.b("moodSound");
        if (this.R == 0) {
            this.g = this.g * 3 + 1013904223;
            int k = this.g >> 2;
            int l = k & 15;
            int i1 = k >> 8 & 15;
            int j1 = k >> 16 & 127;
            int k1 = ochunk.a(l, j1, i1);

            l += i;
            i1 += j;
            if (k1 == 0 && this.m(l, j1, i1) <= this.r.nextInt(8) && this.a(OEnumSkyBlock.a, l, j1, i1) <= 0) {
                OEntityPlayer oentityplayer = this.a((double) l + 0.5D, (double) j1 + 0.5D, (double) i1 + 0.5D, 8.0D);

                if (oentityplayer != null && oentityplayer.e((double) l + 0.5D, (double) j1 + 0.5D, (double) i1 + 0.5D) > 4.0D) {
                    this.a((double) l + 0.5D, (double) j1 + 0.5D, (double) i1 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + this.r.nextFloat() * 0.2F);
                    this.R = this.r.nextInt(12000) + 6000;
                }
            }
        }

        OProfiler.b("checkLight");
        ochunk.n();
    }

    protected void l() {
        this.k();
        int i = 0;
        int j = 0;
        Iterator<OChunkCoordIntPair> var3 = this.D.iterator();

        while (var3.hasNext()) {
            OChunkCoordIntPair ochunkcoordintpair = var3.next();
            int k = ochunkcoordintpair.a * 16;
            int l = ochunkcoordintpair.b * 16;

            OProfiler.a("getChunk");
            OChunk ochunk = this.d(ochunkcoordintpair.a, ochunkcoordintpair.b);

            this.a(k, l, ochunk);
            OProfiler.b("thunder");
            int i1;
            int j1;
            int k1;
            int l1;

            if (this.r.nextInt(100000) == 0 && this.x() && this.w()) {
                this.g = this.g * 3 + 1013904223;
                i1 = this.g >> 2;
                j1 = k + (i1 & 15);
                k1 = l + (i1 >> 8 & 15);
                l1 = this.f(j1, k1);
                if (this.y(j1, l1, k1)) {
                    this.a((OEntity) (new OEntityLightningBolt(this, (double) j1, (double) l1, (double) k1)));
                    this.m = 2;
                }
            }

            OProfiler.b("iceandsnow");
            if (this.r.nextInt(16) == 0) {
                this.g = this.g * 3 + 1013904223;
                i1 = this.g >> 2;
                j1 = i1 & 15;
                k1 = i1 >> 8 & 15;
                l1 = this.f(j1 + k, k1 + l);
                if (this.t(j1 + k, l1 - 1, k1 + l)) {
                    this.e(j1 + k, l1 - 1, k1 + l, OBlock.aT.bO);
                }

                if (this.x() && this.u(j1 + k, l1, k1 + l)) {
                    this.e(j1 + k, l1, k1 + l, OBlock.aS.bO);
                }
            }

            OProfiler.b("tickTiles");
            OExtendedBlockStorage[] aoextendedblockstorage = ochunk.h();

            j1 = aoextendedblockstorage.length;

            for (k1 = 0; k1 < j1; ++k1) {
                OExtendedBlockStorage oextendedblockstorage = aoextendedblockstorage[k1];

                if (oextendedblockstorage != null && oextendedblockstorage.b()) {
                    for (int i2 = 0; i2 < 3; ++i2) {
                        this.g = this.g * 3 + 1013904223;
                        int j2 = this.g >> 2;
                        int k2 = j2 & 15;
                        int l2 = j2 >> 8 & 15;
                        int i3 = j2 >> 16 & 15;
                        int j3 = oextendedblockstorage.a(k2, i3, l2);

                        ++j;
                        OBlock oblock = OBlock.m[j3];

                        if (oblock != null && oblock.n()) {
                            ++i;
                            oblock.a(this, k2 + k, i3 + oextendedblockstorage.c(), l2 + l, this.r);
                        }
                    }
                }
            }

            OProfiler.a();
        }

    }

    public boolean s(int i, int j, int k) {
        return this.c(i, j, k, false);
    }

    public boolean t(int i, int j, int k) {
        return this.c(i, j, k, true);
    }

    public boolean c(int i, int j, int k, boolean flag) {
        OBiomeGenBase obiomegenbase = this.a(i, k);
        float f = obiomegenbase.i();

        if (f > 0.15F) {
            return false;
        } else {
            if (j >= 0 && j < 256 && this.a(OEnumSkyBlock.b, i, j, k) < 10) {
                int l = this.a(i, j, k);

                if ((l == OBlock.B.bO || l == OBlock.A.bO) && this.c(i, j, k) == 0) {
                    if (!flag) {
                        return true;
                    }

                    boolean flag1 = true;

                    if (flag1 && this.d(i - 1, j, k) != OMaterial.g) {
                        flag1 = false;
                    }

                    if (flag1 && this.d(i + 1, j, k) != OMaterial.g) {
                        flag1 = false;
                    }

                    if (flag1 && this.d(i, j, k - 1) != OMaterial.g) {
                        flag1 = false;
                    }

                    if (flag1 && this.d(i, j, k + 1) != OMaterial.g) {
                        flag1 = false;
                    }

                    if (!flag1) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public boolean u(int i, int j, int k) {
        OBiomeGenBase obiomegenbase = this.a(i, k);
        float f = obiomegenbase.i();

        if (f > 0.15F) {
            return false;
        } else {
            if (j >= 0 && j < 256 && this.a(OEnumSkyBlock.b, i, j, k) < 10) {
                int l = this.a(i, j - 1, k);
                int i1 = this.a(i, j, k);

                if (i1 == 0 && OBlock.aS.c(this, i, j, k) && l != 0 && l != OBlock.aT.bO && OBlock.m[l].cd.c()) {
                    return true;
                }
            }

            return false;
        }
    }

    public void v(int i, int j, int k) {
        if (!this.t.e) {
            this.b(OEnumSkyBlock.a, i, j, k);
        }

        this.b(OEnumSkyBlock.b, i, j, k);
    }

    private int c(int i, int j, int k, int l, int i1, int j1) {
        int k1 = 0;

        if (this.l(j, k, l)) {
            k1 = 15;
        } else {
            if (j1 == 0) {
                j1 = 1;
            }

            int l1 = this.a(OEnumSkyBlock.a, j - 1, k, l) - j1;
            int i2 = this.a(OEnumSkyBlock.a, j + 1, k, l) - j1;
            int j2 = this.a(OEnumSkyBlock.a, j, k - 1, l) - j1;
            int k2 = this.a(OEnumSkyBlock.a, j, k + 1, l) - j1;
            int l2 = this.a(OEnumSkyBlock.a, j, k, l - 1) - j1;
            int i3 = this.a(OEnumSkyBlock.a, j, k, l + 1) - j1;

            if (l1 > k1) {
                k1 = l1;
            }

            if (i2 > k1) {
                k1 = i2;
            }

            if (j2 > k1) {
                k1 = j2;
            }

            if (k2 > k1) {
                k1 = k2;
            }

            if (l2 > k1) {
                k1 = l2;
            }

            if (i3 > k1) {
                k1 = i3;
            }
        }

        return k1;
    }

    private int d(int i, int j, int k, int l, int i1, int j1) {
        int k1 = OBlock.q[i1];
        int l1 = this.a(OEnumSkyBlock.b, j - 1, k, l) - j1;
        int i2 = this.a(OEnumSkyBlock.b, j + 1, k, l) - j1;
        int j2 = this.a(OEnumSkyBlock.b, j, k - 1, l) - j1;
        int k2 = this.a(OEnumSkyBlock.b, j, k + 1, l) - j1;
        int l2 = this.a(OEnumSkyBlock.b, j, k, l - 1) - j1;
        int i3 = this.a(OEnumSkyBlock.b, j, k, l + 1) - j1;

        if (l1 > k1) {
            k1 = l1;
        }

        if (i2 > k1) {
            k1 = i2;
        }

        if (j2 > k1) {
            k1 = j2;
        }

        if (k2 > k1) {
            k1 = k2;
        }

        if (l2 > k1) {
            k1 = l2;
        }

        if (i3 > k1) {
            k1 = i3;
        }

        return k1;
    }

    public void b(OEnumSkyBlock oenumskyblock, int i, int j, int k) {
        if (this.a(i, j, k, 17)) {
            int l = 0;
            int i1 = 0;

            OProfiler.a("getBrightness");
            int j1 = this.a(oenumskyblock, i, j, k);
            boolean flag = false;
            int k1 = this.a(i, j, k);
            int l1 = this.f(i, j, k);

            if (l1 == 0) {
                l1 = 1;
            }

            boolean flag1 = false;
            int i2;

            if (oenumskyblock == OEnumSkyBlock.a) {
                i2 = this.c(j1, i, j, k, k1, l1);
            } else {
                i2 = this.d(j1, i, j, k, k1, l1);
            }

            int j2;
            int k2;
            int l2;
            int i3;
            int j3;
            int k3;

            if (i2 > j1) {
                this.E[i1++] = 133152;
            } else if (i2 < j1) {
                if (oenumskyblock != OEnumSkyBlock.b) {
                    ;
                }

                this.E[i1++] = 133152 + (j1 << 18);

                while (l < i1) {
                    j2 = this.E[l++];
                    k1 = (j2 & 63) - 32 + i;
                    l1 = (j2 >> 6 & 63) - 32 + j;
                    i2 = (j2 >> 12 & 63) - 32 + k;
                    k2 = j2 >> 18 & 15;
                    l2 = this.a(oenumskyblock, k1, l1, i2);
                    if (l2 == k2) {
                        this.a(oenumskyblock, k1, l1, i2, 0);
                        if (k2 > 0) {
                            i3 = k1 - i;
                            k3 = l1 - j;
                            j3 = i2 - k;
                            if (i3 < 0) {
                                i3 = -i3;
                            }

                            if (k3 < 0) {
                                k3 = -k3;
                            }

                            if (j3 < 0) {
                                j3 = -j3;
                            }

                            if (i3 + k3 + j3 < 17) {
                                for (int l3 = 0; l3 < 6; ++l3) {
                                    int i4 = l3 % 2 * 2 - 1;
                                    int j4 = k1 + l3 / 2 % 3 / 2 * i4;
                                    int k4 = l1 + (l3 / 2 + 1) % 3 / 2 * i4;
                                    int l4 = i2 + (l3 / 2 + 2) % 3 / 2 * i4;

                                    l2 = this.a(oenumskyblock, j4, k4, l4);
                                    int i5 = OBlock.o[this.a(j4, k4, l4)];

                                    if (i5 == 0) {
                                        i5 = 1;
                                    }

                                    if (l2 == k2 - i5 && i1 < this.E.length) {
                                        this.E[i1++] = j4 - i + 32 + (k4 - j + 32 << 6) + (l4 - k + 32 << 12) + (k2 - i5 << 18);
                                    }
                                }
                            }
                        }
                    }
                }

                l = 0;
            }

            OProfiler.a();
            OProfiler.a("tcp < tcc");

            while (l < i1) {
                j1 = this.E[l++];
                int j5 = (j1 & 63) - 32 + i;

                j2 = (j1 >> 6 & 63) - 32 + j;
                k1 = (j1 >> 12 & 63) - 32 + k;
                l1 = this.a(oenumskyblock, j5, j2, k1);
                i2 = this.a(j5, j2, k1);
                k2 = OBlock.o[i2];
                if (k2 == 0) {
                    k2 = 1;
                }

                boolean flag2 = false;

                if (oenumskyblock == OEnumSkyBlock.a) {
                    l2 = this.c(l1, j5, j2, k1, i2, k2);
                } else {
                    l2 = this.d(l1, j5, j2, k1, i2, k2);
                }

                if (l2 != l1) {
                    this.a(oenumskyblock, j5, j2, k1, l2);
                    if (l2 > l1) {
                        i3 = j5 - i;
                        k3 = j2 - j;
                        j3 = k1 - k;
                        if (i3 < 0) {
                            i3 = -i3;
                        }

                        if (k3 < 0) {
                            k3 = -k3;
                        }

                        if (j3 < 0) {
                            j3 = -j3;
                        }

                        if (i3 + k3 + j3 < 17 && i1 < this.E.length - 6) {
                            if (this.a(oenumskyblock, j5 - 1, j2, k1) < l2) {
                                this.E[i1++] = j5 - 1 - i + 32 + (j2 - j + 32 << 6) + (k1 - k + 32 << 12);
                            }

                            if (this.a(oenumskyblock, j5 + 1, j2, k1) < l2) {
                                this.E[i1++] = j5 + 1 - i + 32 + (j2 - j + 32 << 6) + (k1 - k + 32 << 12);
                            }

                            if (this.a(oenumskyblock, j5, j2 - 1, k1) < l2) {
                                this.E[i1++] = j5 - i + 32 + (j2 - 1 - j + 32 << 6) + (k1 - k + 32 << 12);
                            }

                            if (this.a(oenumskyblock, j5, j2 + 1, k1) < l2) {
                                this.E[i1++] = j5 - i + 32 + (j2 + 1 - j + 32 << 6) + (k1 - k + 32 << 12);
                            }

                            if (this.a(oenumskyblock, j5, j2, k1 - 1) < l2) {
                                this.E[i1++] = j5 - i + 32 + (j2 - j + 32 << 6) + (k1 - 1 - k + 32 << 12);
                            }

                            if (this.a(oenumskyblock, j5, j2, k1 + 1) < l2) {
                                this.E[i1++] = j5 - i + 32 + (j2 - j + 32 << 6) + (k1 + 1 - k + 32 << 12);
                            }
                        }
                    }
                }
            }

            OProfiler.a();
        }
    }

    public boolean a(boolean flag) {
        int i = this.H.size();

        if (i != this.I.size()) {
            throw new IllegalStateException("TickNextTick list out of synch");
        } else {
            if (i > 1000) {
                i = 1000;
            }

            for (int j = 0; j < i; ++j) {
                ONextTickListEntry onextticklistentry = this.H.first();

                if (!flag && onextticklistentry.e > this.x.f()) {
                    break;
                }

                this.H.remove(onextticklistentry);
                this.I.remove(onextticklistentry);
                byte b0 = 8;

                if (this.a(onextticklistentry.a - b0, onextticklistentry.b - b0, onextticklistentry.c - b0, onextticklistentry.a + b0, onextticklistentry.b + b0, onextticklistentry.c + b0)) {
                    int k = this.a(onextticklistentry.a, onextticklistentry.b, onextticklistentry.c);

                    if (k == onextticklistentry.d && k > 0) {
                        OBlock.m[k].a(this, onextticklistentry.a, onextticklistentry.b, onextticklistentry.c, this.r);
                    }
                }
            }

            return this.H.size() != 0;
        }
    }

    public List<ONextTickListEntry> a(OChunk ochunk, boolean flag) {
        ArrayList<ONextTickListEntry> var3 = null;
        OChunkCoordIntPair ochunkcoordintpair = ochunk.k();
        int i = ochunkcoordintpair.a << 4;
        int j = i + 16;
        int k = ochunkcoordintpair.b << 4;
        int l = k + 16;
        Iterator<ONextTickListEntry> var9 = this.I.iterator();

        while (var9.hasNext()) {
            ONextTickListEntry onextticklistentry = var9.next();

            if (onextticklistentry.a >= i && onextticklistentry.a < j && onextticklistentry.c >= k && onextticklistentry.c < l) {
                if (flag) {
                    this.H.remove(onextticklistentry);
                    var9.remove();
                }

                if (var3 == null) {
                    var3 = new ArrayList<ONextTickListEntry>();
                }

                var3.add(onextticklistentry);
            }
        }

        return var3;
    }

    public List b(OEntity oentity, OAxisAlignedBB oaxisalignedbb) {
        this.S.clear();
        int i = OMathHelper.b((oaxisalignedbb.a - 2.0D) / 16.0D);
        int j = OMathHelper.b((oaxisalignedbb.d + 2.0D) / 16.0D);
        int k = OMathHelper.b((oaxisalignedbb.c - 2.0D) / 16.0D);
        int l = OMathHelper.b((oaxisalignedbb.f + 2.0D) / 16.0D);

        for (int i1 = i; i1 <= j; ++i1) {
            for (int j1 = k; j1 <= l; ++j1) {
                if (this.h(i1, j1)) {
                    this.d(i1, j1).a(oentity, oaxisalignedbb, this.S);
                }
            }
        }

        return this.S;
    }

    public List a(Class oclass, OAxisAlignedBB oaxisalignedbb) {
        int i = OMathHelper.b((oaxisalignedbb.a - 2.0D) / 16.0D);
        int j = OMathHelper.b((oaxisalignedbb.d + 2.0D) / 16.0D);
        int k = OMathHelper.b((oaxisalignedbb.c - 2.0D) / 16.0D);
        int l = OMathHelper.b((oaxisalignedbb.f + 2.0D) / 16.0D);
        ArrayList arraylist = new ArrayList();

        for (int i1 = i; i1 <= j; ++i1) {
            for (int j1 = k; j1 <= l; ++j1) {
                if (this.h(i1, j1)) {
                    this.d(i1, j1).a(oclass, oaxisalignedbb, arraylist);
                }
            }
        }

        return arraylist;
    }

    public OEntity a(Class oclass, OAxisAlignedBB oaxisalignedbb, OEntity oentity) {
        List list = this.a(oclass, oaxisalignedbb);
        OEntity oentity1 = null;
        double d0 = Double.MAX_VALUE;
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            OEntity oentity2 = (OEntity) iterator.next();

            if (oentity2 != oentity) {
                double d1 = oentity.j(oentity2);

                if (d1 <= d0) {
                    oentity1 = oentity2;
                    d0 = d1;
                }
            }
        }

        return oentity1;
    }

    public void b(int i, int j, int k, OTileEntity otileentity) {
        if (this.i(i, j, k)) {
            this.c(i, k).e();
        }

        for (int l = 0; l < this.u.size(); ++l) {
            this.u.get(l).a(i, j, k, otileentity);
        }

    }

    public int a(Class oclass) {
        int i = 0;

        for (int j = 0; j < this.b.size(); ++j) {
            OEntity oentity = this.b.get(j);

            if (oclass.isAssignableFrom(oentity.getClass())) {
                ++i;
            }
        }

        return i;
    }

    public void a(List list) {
        this.b.addAll(list);

        for (int i = 0; i < list.size(); ++i) {
            this.c((OEntity) list.get(i));
        }

    }

    public void b(List list) {
        this.G.addAll(list);
    }

    public boolean a(int i, int j, int k, int l, boolean flag, int i1) {
        int j1 = this.a(j, k, l);
        OBlock oblock = OBlock.m[j1];
        OBlock oblock1 = OBlock.m[i];
        OAxisAlignedBB oaxisalignedbb = oblock1.e(this, j, k, l);

        if (flag) {
            oaxisalignedbb = null;
        }

        if (oaxisalignedbb != null && !this.a(oaxisalignedbb)) {
            return false;
        } else {
            if (oblock != null && (oblock == OBlock.A || oblock == OBlock.B || oblock == OBlock.C || oblock == OBlock.D || oblock == OBlock.ar || oblock.cd.i())) {
                oblock = null;
            }

            return i > 0 && oblock == null && oblock1.b(this, j, k, l, i1);
        }
    }

    public OPathEntity a(OEntity oentity, OEntity oentity1, float f, boolean flag, boolean flag1, boolean flag2, boolean flag3) {
        OProfiler.a("pathfind");
        int i = OMathHelper.b(oentity.bm);
        int j = OMathHelper.b(oentity.bn + 1.0D);
        int k = OMathHelper.b(oentity.bo);
        int l = (int) (f + 16.0F);
        int i1 = i - l;
        int j1 = j - l;
        int k1 = k - l;
        int l1 = i + l;
        int i2 = j + l;
        int j2 = k + l;
        OChunkCache ochunkcache = new OChunkCache(this, i1, j1, k1, l1, i2, j2);
        OPathEntity opathentity = (new OPathFinder(ochunkcache, flag, flag1, flag2, flag3)).a(oentity, oentity1, f);

        OProfiler.a();
        return opathentity;
    }

    public OPathEntity a(OEntity oentity, int i, int j, int k, float f, boolean flag, boolean flag1, boolean flag2, boolean flag3) {
        OProfiler.a("pathfind");
        int l = OMathHelper.b(oentity.bm);
        int i1 = OMathHelper.b(oentity.bn);
        int j1 = OMathHelper.b(oentity.bo);
        int k1 = (int) (f + 8.0F);
        int l1 = l - k1;
        int i2 = i1 - k1;
        int j2 = j1 - k1;
        int k2 = l + k1;
        int l2 = i1 + k1;
        int i3 = j1 + k1;
        OChunkCache ochunkcache = new OChunkCache(this, l1, i2, j2, k2, l2, i3);
        OPathEntity opathentity = (new OPathFinder(ochunkcache, flag, flag1, flag2, flag3)).a(oentity, i, j, k, f);

        OProfiler.a();
        return opathentity;
    }

    public boolean i(int i, int j, int k, int l) {
        int i1 = this.a(i, j, k);

        return i1 == 0 ? false : OBlock.m[i1].d(this, i, j, k, l);
    }

    public boolean w(int i, int j, int k) {
        return this.i(i, j - 1, k, 0) ? true : (this.i(i, j + 1, k, 1) ? true : (this.i(i, j, k - 1, 2) ? true : (this.i(i, j, k + 1, 3) ? true : (this.i(i - 1, j, k, 4) ? true : this.i(i + 1, j, k, 5)))));
    }

    public boolean j(int i, int j, int k, int l) {
        if (this.e(i, j, k)) {
            return this.w(i, j, k);
        } else {
            int i1 = this.a(i, j, k);

            return i1 == 0 ? false : OBlock.m[i1].a((OIBlockAccess) this, i, j, k, l);
        }
    }

    public boolean x(int i, int j, int k) {
        return this.j(i, j - 1, k, 0) ? true : (this.j(i, j + 1, k, 1) ? true : (this.j(i, j, k - 1, 2) ? true : (this.j(i, j, k + 1, 3) ? true : (this.j(i - 1, j, k, 4) ? true : this.j(i + 1, j, k, 5)))));
    }

    public OEntityPlayer a(OEntity oentity, double d0) {
        return this.a(oentity.bm, oentity.bn, oentity.bo, d0);
    }

    public OEntityPlayer a(double d0, double d1, double d2, double d3) {
        double d4 = -1.0D;
        OEntityPlayer oentityplayer = null;

        for (int i = 0; i < this.d.size(); ++i) {
            OEntityPlayer oentityplayer1 = this.d.get(i);
            double d5 = oentityplayer1.e(d0, d1, d2);

            if ((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1.0D || d5 < d4)) {
                d4 = d5;
                oentityplayer = oentityplayer1;
            }
        }

        return oentityplayer;
    }

    public OEntityPlayer a(double d0, double d1, double d2) {
        double d3 = -1.0D;
        OEntityPlayer oentityplayer = null;

        for (int i = 0; i < this.d.size(); ++i) {
            OEntityPlayer oentityplayer1 = this.d.get(i);
            double d4 = oentityplayer1.e(d0, oentityplayer1.bn, d1);

            if ((d2 < 0.0D || d4 < d2 * d2) && (d3 == -1.0D || d4 < d3)) {
                d3 = d4;
                oentityplayer = oentityplayer1;
            }
        }

        return oentityplayer;
    }

    public OEntityPlayer b(OEntity oentity, double d0) {
        return this.b(oentity.bm, oentity.bn, oentity.bo, d0);
    }

    public OEntityPlayer b(double d0, double d1, double d2, double d3) {
        double d4 = -1.0D;
        OEntityPlayer oentityplayer = null;

        for (int i = 0; i < this.d.size(); ++i) {
            OEntityPlayer oentityplayer1 = this.d.get(i);

            if (!oentityplayer1.L.a) {
                double d5 = oentityplayer1.e(d0, d1, d2);

                if ((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1.0D || d5 < d4)) {
                    d4 = d5;
                    oentityplayer = oentityplayer1;
                }
            }
        }

        return oentityplayer;
    }

    public OEntityPlayer a(String s) {
        for (int i = 0; i < this.d.size(); ++i) {
            if (s.equals(this.d.get(i).v)) {
                return this.d.get(i);
            }
        }

        return null;
    }

    public void m() {
        this.w.b();
    }

    public void a(long i) {
        this.x.a(i);
    }

    public void b(long i) {
        long j = i - this.x.f();

        ONextTickListEntry onextticklistentry;

        for (Iterator<ONextTickListEntry> var5 = this.I.iterator(); var5.hasNext(); onextticklistentry.e += j) {
            onextticklistentry = var5.next();
        }

        this.a(i);
    }

    public long n() {
        return this.x.b();
    }

    public long o() {
        return this.x.f();
    }

    public OChunkCoordinates p() {
        return new OChunkCoordinates(this.x.c(), this.x.d(), this.x.e());
    }

    public boolean a(OEntityPlayer oentityplayer, int i, int j, int k) {
        return true;
    }

    public void a(OEntity oentity, byte b0) {}

    public OIChunkProvider q() {
        return this.v;
    }

    public void e(int i, int j, int k, int l, int i1) {
        int j1 = this.a(i, j, k);

        if (j1 > 0) {
            OBlock.m[j1].a(this, i, j, k, l, i1);
        }

    }

    public OISaveHandler r() {
        return this.w;
    }

    public OWorldInfo s() {
        return this.x;
    }

    public void t() {
        this.N = !this.d.isEmpty();
        Iterator<OEntityPlayer> var1 = this.d.iterator();

        while (var1.hasNext()) {
            OEntityPlayer oentityplayer = var1.next();

            if (!oentityplayer.Z()) {
                this.N = false;
                break;
            }
        }

    }

    protected void u() {
        this.N = false;
        Iterator<OEntityPlayer> var1 = this.d.iterator();

        while (var1.hasNext()) {
            OEntityPlayer oentityplayer = var1.next();

            if (oentityplayer.Z()) {
                oentityplayer.a(false, false, true);
            }
        }

        this.C();
    }

    public boolean v() {
        if (this.N && !this.F) {
            Iterator<OEntityPlayer> var1 = this.d.iterator();

            OEntityPlayer oentityplayer;

            do {
                if (!var1.hasNext()) {
                    return true;
                }

                oentityplayer = var1.next();
            } while (oentityplayer.aa());

            return false;
        } else {
            return false;
        }
    }

    public float c(float f) {
        return (this.k + (this.l - this.k) * f) * this.d(f);
    }

    public float d(float f) {
        return this.i + (this.j - this.i) * f;
    }

    public boolean w() {
        return (double) this.c(1.0F) > 0.9D;
    }

    public boolean x() {
        return (double) this.d(1.0F) > 0.2D;
    }

    public boolean y(int i, int j, int k) {
        if (!this.x()) {
            return false;
        } else if (!this.l(i, j, k)) {
            return false;
        } else if (this.f(i, k) > j) {
            return false;
        } else {
            OBiomeGenBase obiomegenbase = this.a(i, k);

            return obiomegenbase.c() ? false : obiomegenbase.d();
        }
    }

    public boolean z(int i, int j, int k) {
        OBiomeGenBase obiomegenbase = this.a(i, k);

        return obiomegenbase.e();
    }

    public void a(String s, OWorldSavedData oworldsaveddata) {
        this.z.a(s, oworldsaveddata);
    }

    public OWorldSavedData a(Class oclass, String s) {
        return this.z.a(oclass, s);
    }

    public int b(String s) {
        return this.z.a(s);
    }

    public void f(int i, int j, int k, int l, int i1) {
        this.a((OEntityPlayer) null, i, j, k, l, i1);
    }

    public void a(OEntityPlayer oentityplayer, int i, int j, int k, int l, int i1) {
        for (int j1 = 0; j1 < this.u.size(); ++j1) {
            this.u.get(j1).a(oentityplayer, i, j, k, l, i1);
        }

    }

    public int y() {
        return 256;
    }

    public Random A(int i, int j, int k) {
        long l = (long) i * 341873128712L + (long) j * 132897987541L + this.s().b() + (long) k;

        this.r.setSeed(l);
        return this.r;
    }

    public boolean z() {
        return false;
    }

    public OSpawnListEntry a(OEnumCreatureType oenumcreaturetype, int i, int j, int k) {
        List list = this.q().a(oenumcreaturetype, i, j, k);

        return list != null && !list.isEmpty() ? (OSpawnListEntry) OWeightedRandom.a(this.r, (Collection) list) : null;
    }

    public OChunkPosition b(String s, int i, int j, int k) {
        return this.q().a(this, s, i, j, k);
    }

    /**
     * Get this worlds entity tracker to track and untrack players
     * @return
     */
    public EntityTracker getEntityTracker() {
        return this.entityTracker.getCanaryEntityTracker();
    }
    
    protected void setEntityTracker(OEntityTracker oentitytracker) {
        this.oentitytracker = oentitytracker;
    }
}
