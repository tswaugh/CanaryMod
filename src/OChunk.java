import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class OChunk {

    public static boolean a;
    private OExtendedBlockStorage[] q;
    private byte[] r;
    public int[] b;
    public boolean[] c;
    public boolean d;
    public OWorld e;
    public int[] f;
    public final int g;
    public final int h;
    private boolean s;
    public Map i;
    public List[] j;
    public boolean k;
    public boolean l;
    public boolean m;
    public long n;
    public boolean o;
    private int t;
    boolean p;
    // CanaryMod
    public final Chunk chunk = new Chunk(this);

    public OChunk(OWorld oworld, int i, int j) {
        super();
        this.q = new OExtendedBlockStorage[16];
        this.r = new byte[256];
        this.b = new int[256];
        this.c = new boolean[256];
        this.s = false;
        this.i = new HashMap();
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = 0L;
        this.o = false;
        this.t = 4096;
        this.p = false;
        this.j = new List[16];
        this.e = oworld;
        this.g = i;
        this.h = j;
        this.f = new int[256];

        for (int k = 0; k < this.j.length; ++k) {
            this.j[k] = new ArrayList();
        }

        Arrays.fill(this.b, -999);
        Arrays.fill(this.r, (byte) -1);
    }

    public OChunk(OWorld oworld, byte[] abyte, int i, int j) {
        this(oworld, i, j);
        int k = abyte.length / 256;

        for (int l = 0; l < 16; ++l) {
            for (int i1 = 0; i1 < 16; ++i1) {
                for (int j1 = 0; j1 < k; ++j1) {
                    byte b0 = abyte[l << 11 | i1 << 7 | j1];

                    if (b0 != 0) {
                        int k1 = j1 >> 4;

                        if (this.q[k1] == null) {
                            this.q[k1] = new OExtendedBlockStorage(k1 << 4);
                        }

                        this.q[k1].a(l, j1 & 15, i1, b0);
                    }
                }
            }
        }

    }

    public boolean a(int i, int j) {
        return i == this.g && j == this.h;
    }

    public int b(int i, int j) {
        return this.f[j << 4 | i];
    }

    public int g() {
        for (int i = this.q.length - 1; i >= 0; --i) {
            if (this.q[i] != null) {
                return this.q[i].c();
            }
        }

        return 0;
    }

    public OExtendedBlockStorage[] h() {
        return this.q;
    }

    public void a() {
        int i = this.g();

        int j;
        int k;

        for (j = 0; j < 16; ++j) {
            k = 0;

            while (k < 16) {
                this.b[j + (k << 4)] = -999;
                int l = i + 16 - 1;

                while (true) {
                    if (l > 0) {
                        if (this.b(j, l - 1, k) == 0) {
                            --l;
                            continue;
                        }

                        this.f[k << 4 | j] = l;
                    }

                    if (!this.e.t.e) {
                        l = 15;
                        int i1 = i + 16 - 1;

                        do {
                            l -= this.b(j, i1, k);
                            if (l > 0) {
                                OExtendedBlockStorage oextendedblockstorage = this.q[i1 >> 4];

                                if (oextendedblockstorage != null) {
                                    oextendedblockstorage.c(j, i1 & 15, k, l);
                                    this.e.o((this.g << 4) + j, i1, (this.h << 4) + k);
                                }
                            }

                            --i1;
                        } while (i1 > 0 && l > 0);
                    }

                    ++k;
                    break;
                }
            }
        }

        this.l = true;

        for (j = 0; j < 16; ++j) {
            for (k = 0; k < 16; ++k) {
                this.e(j, k);
            }
        }

    }

    public void b() {}

    private void e(int i, int j) {
        this.c[i + j * 16] = true;
        this.s = true;
    }

    private void o() {
        OProfiler.a("recheckGaps");
        if (this.e.a(this.g * 16 + 8, 0, this.h * 16 + 8, 16)) {
            for (int i = 0; i < 16; ++i) {
                for (int j = 0; j < 16; ++j) {
                    if (this.c[i + j * 16]) {
                        this.c[i + j * 16] = false;
                        int k = this.b(i, j);
                        int l = this.g * 16 + i;
                        int i1 = this.h * 16 + j;
                        int j1 = this.e.e(l - 1, i1);
                        int k1 = this.e.e(l + 1, i1);
                        int l1 = this.e.e(l, i1 - 1);
                        int i2 = this.e.e(l, i1 + 1);

                        if (k1 < j1) {
                            j1 = k1;
                        }

                        if (l1 < j1) {
                            j1 = l1;
                        }

                        if (i2 < j1) {
                            j1 = i2;
                        }

                        this.g(l, i1, j1);
                        this.g(l - 1, i1, k);
                        this.g(l + 1, i1, k);
                        this.g(l, i1 - 1, k);
                        this.g(l, i1 + 1, k);
                    }
                }
            }

            this.s = false;
        }

        OProfiler.a();
    }

    private void g(int i, int j, int k) {
        int l = this.e.e(i, j);

        if (l > k) {
            this.d(i, j, k, l + 1);
        } else if (l < k) {
            this.d(i, j, l, k + 1);
        }

    }

    private void d(int i, int j, int k, int l) {
        if (l > k && this.e.a(i, 0, j, 16)) {
            for (int i1 = k; i1 < l; ++i1) {
                this.e.b(OEnumSkyBlock.a, i, i1, j);
            }

            this.l = true;
        }

    }

    private void h(int i, int j, int k) {
        int l = this.f[k << 4 | i] & 255;
        int i1 = l;

        if (j > l) {
            i1 = j;
        }

        while (i1 > 0 && this.b(i, i1 - 1, k) == 0) {
            --i1;
        }

        if (i1 != l) {
            this.e.g(i, k, i1, l);
            this.f[k << 4 | i] = i1;
            int j1 = this.g * 16 + i;
            int k1 = this.h * 16 + k;
            int l1;
            int i2;

            if (!this.e.t.e) {
                OExtendedBlockStorage oextendedblockstorage;

                if (i1 < l) {
                    for (l1 = i1; l1 < l; ++l1) {
                        oextendedblockstorage = this.q[l1 >> 4];
                        if (oextendedblockstorage != null) {
                            oextendedblockstorage.c(i, l1 & 15, k, 15);
                            this.e.o((this.g << 4) + i, l1, (this.h << 4) + k);
                        }
                    }
                } else {
                    for (l1 = l; l1 < i1; ++l1) {
                        // CanaryMod start: Catch corrupt index info
                        if (l1 >> 4 < 0 || l1 >> 4 >= 16) {
                            ONetServerHandler.a.warning("Invalid chunk info array index: " + (l1 >> 4));
                            ONetServerHandler.a.warning("l: " + l + ", i1: " + i1);
                            ONetServerHandler.a.warning("Chunk location: " + j1 + ", " + k1);
                            l1 = 0;
                        }
                        // CanaryMod end
                        oextendedblockstorage = this.q[l1 >> 4];
                        if (oextendedblockstorage != null) {
                            oextendedblockstorage.c(i, l1 & 15, k, 0);
                            this.e.o((this.g << 4) + i, l1, (this.h << 4) + k);
                        }
                    }
                }

                l1 = 15;

                while (i1 > 0 && l1 > 0) {
                    --i1;
                    i2 = this.b(i, i1, k);
                    if (i2 == 0) {
                        i2 = 1;
                    }

                    l1 -= i2;
                    if (l1 < 0) {
                        l1 = 0;
                    }

                    OExtendedBlockStorage oextendedblockstorage1 = this.q[i1 >> 4];

                    if (oextendedblockstorage1 != null) {
                        oextendedblockstorage1.c(i, i1 & 15, k, l1);
                    }
                }
            }

            l1 = this.f[k << 4 | i];
            i2 = l;
            int j2 = l1;

            if (l1 < l) {
                i2 = l1;
                j2 = l;
            }

            if (!this.e.t.e) {
                this.d(j1 - 1, k1, i2, j2);
                this.d(j1 + 1, k1, i2, j2);
                this.d(j1, k1 - 1, i2, j2);
                this.d(j1, k1 + 1, i2, j2);
                this.d(j1, k1, i2, j2);
            }

            this.l = true;
        }
    }

    public int b(int i, int j, int k) {
        return OBlock.o[this.a(i, j, k)];
    }

    public int a(int i, int j, int k) {
        if (j >> 4 >= this.q.length) {
            return 0;
        } else {
            OExtendedBlockStorage oextendedblockstorage = this.q[j >> 4];

            return oextendedblockstorage != null ? oextendedblockstorage.a(i, j & 15, k) : 0;
        }
    }

    public int c(int i, int j, int k) {
        if (j >> 4 >= this.q.length) {
            return 0;
        } else {
            OExtendedBlockStorage oextendedblockstorage = this.q[j >> 4];

            return oextendedblockstorage != null ? oextendedblockstorage.b(i, j & 15, k) : 0;
        }
    }

    public boolean a(int i, int j, int k, int l) {
        return this.a(i, j, k, l, 0);
    }

    public boolean a(int i, int j, int k, int l, int i1) {
        return a(i, j, k, l, i1, true);
    }
   
    public boolean a(int i, int j, int k, int l, int i1, boolean flag) {
        int j1 = k << 4 | i;

        if (j >= this.b[j1] - 1) {
            this.b[j1] = -999;
        }

        int k1 = this.f[j1];
        int l1 = this.a(i, j, k);

        if (l1 == l && this.c(i, j, k) == i1) {
            return false;
        } else {
            int portalPointX = this.g * 16 + i;
            int portalPointZ = this.h * 16 + k;

            if (flag == true) {
                int portalPointY = j;
                // CanaryMod check if removed block is portal block
                int portalId = Block.Type.Portal.getType();

                if (chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ) == portalId) {
                    // These will be equal 1 if the portal is defined on their axis and 0 if not.
                    int portalXOffset = (chunk.getWorld().getBlockIdAt(portalPointX - 1, portalPointY, portalPointZ) == portalId || chunk.getWorld().getBlockIdAt(portalPointX + 1, portalPointY, portalPointZ) == portalId) ? 1 : 0;
                    int portalZOffset = (chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ - 1) == portalId || chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ + 1) == portalId) ? 1 : 0;

                    // If the portal is either x aligned or z aligned but not both (has neighbor portal in x or z plane but not both)
                    if (portalXOffset != portalZOffset) {
                        // Get the edge of the portal.
                        int portalX = portalPointX - ((chunk.getWorld().getBlockIdAt(portalPointX - 1, portalPointY, portalPointZ) == portalId) ? 1 : 0);
                        int portalZ = portalPointZ - ((chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ - 1) == portalId) ? 1 : 0);
                        int portalY = j;

                        while (chunk.getWorld().getBlockIdAt(portalX, ++portalY, portalZ) == portalId) {
                            ;
                        }
                        portalY -= 1;
                        // Scan the portal and see if its still all there (2x3 formation)
                        boolean completePortal = true;
                        Block[][] portalBlocks = new Block[3][2];

                        for (int i1 = 0; i1 < 3 && completePortal == true; i1 += 1) {
                            for (int j1 = 0; j1 < 2 && completePortal == true; j1 += 1) {
                                portalBlocks[i1][j1] = chunk.getWorld().getBlockAt(portalX + j1 * portalXOffset, portalY - i1, portalZ + j1 * portalZOffset);
                                if (portalBlocks[i1][j1].getType() != portalId) {
                                    completePortal = false;
                                }
                            }
                        }
                        if (completePortal == true) {
                            // CanaryMod hook onPortalDestroy
                            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.PORTAL_DESTROY, (Object) portalBlocks)) {
                                // Hook returned true = don't destroy the portal.
                                // in that case we need to reconstruct the portal's frame to make the portal valid.
                                // Problem is we don't want to reconstruct it right away because more blocks might be deleted (for example on explosion).
                                // In order to avoid spamming the hook for each destroyed block, I'm queuing the reconstruction of the portal instead.
                                etc.getServer().addToServerQueue(new PortalReconstructJob(chunk.getWorld(), portalX, portalY, portalZ, (portalXOffset == 1)));
                            }
                        }
                    }
                }
            }
		
            OExtendedBlockStorage oextendedblockstorage = this.q[j >> 4];
            boolean flag1 = false;

            if (oextendedblockstorage == null) {
                if (l == 0) {
                    return false;
                }

                oextendedblockstorage = this.q[j >> 4] = new OExtendedBlockStorage(j >> 4 << 4);
                flag1 = j >= k1;
            }

            oextendedblockstorage.a(i, j & 15, k, l);
            int i2 = this.g * 16 + i;
            int j2 = this.h * 16 + k;

            if (l1 != 0) {
                if (!this.e.F) {
                    OBlock.m[l1].d(this.e, i2, j, j2);
                } else if (OBlock.m[l1] instanceof OBlockContainer && l1 != l) {
                    this.e.q(i2, j, j2);
                }
            }

            if (oextendedblockstorage.a(i, j & 15, k) != l) {
                return false;
            } else {
                oextendedblockstorage.b(i, j & 15, k, i1);
                if (flag1) {
                    this.a();
                } else {
                    if (OBlock.o[l & 4095] > 0) {
                        if (j >= k1) {
                            this.h(i, j + 1, k);
                        }
                    } else if (j == k1 - 1) {
                        this.h(i, j, k);
                    }

                    this.e(i, k);
                }

                OTileEntity otileentity;

                if (l != 0) {
                    if (!this.e.F) {
                        OBlock.m[l].a(this.e, i2, j, j2);
                    }

                    if (OBlock.m[l] instanceof OBlockContainer) {
                        otileentity = this.e(i, j, k);
                        if (otileentity == null) {
                            otileentity = ((OBlockContainer) OBlock.m[l]).a_();
                            this.e.a(i2, j, j2, otileentity);
                        }

                        if (otileentity != null) {
                            otileentity.h();
                        }
                    }
                } else if (l1 > 0 && OBlock.m[l1] instanceof OBlockContainer) {
                    otileentity = this.e(i, j, k);

                    if (otileentity != null) {
                        otileentity.h();
                    }
                }

                this.l = true;
                return true;
            }
        }
    }

    public boolean b(int i, int j, int k, int l) {
        return b(i, j, k, l, true);
    }
   
    public boolean b(int i, int j, int k, int l, boolean flag) {
        OExtendedBlockStorage oextendedblockstorage = this.q[j >> 4];

        if (oextendedblockstorage == null) {
            return false;
        } else {
            int i1 = this.g * 16 + i;
            int j1 = this.h * 16 + k;

            if (flag == true) {
                int portalPointX = i1;
                int portalPointY = j;
                int portalPointZ = j1;
                // CanaryMod check if removed block is portal block
                int portalId = Block.Type.Portal.getType();

                if (chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ) == portalId) {
                    // These will be equal 1 if the portal is defined on theirn axis and 0 if not.
                    int portalXOffset = (chunk.getWorld().getBlockIdAt(portalPointX - 1, portalPointY, portalPointZ) == portalId || chunk.getWorld().getBlockIdAt(portalPointX + 1, portalPointY, portalPointZ) == portalId) ? 1 : 0;
                    int portalZOffset = (chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ - 1) == portalId || chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ + 1) == portalId) ? 1 : 0;

                    // If the portal is either x aligned or z aligned but not both (has neighbor portal in x or z plane but not both)
                    if (portalXOffset != portalZOffset) {
                        // Get the edge of the portal.
                        int portalX = portalPointX - ((chunk.getWorld().getBlockIdAt(portalPointX - 1, portalPointY, portalPointZ) == portalId) ? 1 : 0);
                        int portalZ = portalPointZ - ((chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ - 1) == portalId) ? 1 : 0);
                        int portalY = j;

                        while (chunk.getWorld().getBlockIdAt(portalX, ++portalY, portalZ) == portalId) {
                            ;
                        }
                        portalY -= 1;
                        // Scan the portal and see if its still all there (2x3 formation)
                        boolean completePortal = true;
                        Block[][] portalBlocks = new Block[3][2];

                        for (int i1 = 0; i1 < 3 && completePortal == true; i1 += 1) {
                            for (int j1 = 0; j1 < 2 && completePortal == true; j1 += 1) {
                                portalBlocks[i1][j1] = chunk.getWorld().getBlockAt(portalX + j1 * portalXOffset, portalY - i1, portalZ + j1 * portalZOffset);
                                if (portalBlocks[i1][j1].getType() != portalId) {
                                    completePortal = false;
                                }
                            }
                        }
                        if (completePortal == true) {
                            // CanaryMod hook onPortalDestroy
                            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.PORTAL_DESTROY, (Object) portalBlocks)) {
                                // Hook returned true = don't destroy the portal.
                                // in that case we need to reconstruct the portal's frame to make the portal valid.
                                // Problem is we don't want to reconstruct it right away because more blocks might be deleted (for example on explosion).
                                // In order to avoid spamming the hook for each destroyed block, I'm queuing the reconstruction of the portal instead.
                                etc.getServer().addToServerQueue(new PortalReconstructJob(chunk.getWorld(), portalX, portalY, portalZ, (portalXOffset == 1)));
                            }
                        }
                    }
                }
            }
			
            int k1 = oextendedblockstorage.b(i, j & 15, k);

            if (k1 == l) {
                return false;
            } else {
                this.l = true;
                oextendedblockstorage.b(i, j & 15, k, l);
                int l1 = oextendedblockstorage.a(i, j & 15, k);

                if (l1 > 0 && OBlock.m[l1] instanceof OBlockContainer) {
                    OTileEntity otileentity = this.e(i, j, k);

                    if (otileentity != null) {
                        otileentity.h();
                        otileentity.p = l;
                    }
                }

                return true;
            }
        }
    }

    public int a(OEnumSkyBlock oenumskyblock, int i, int j, int k) {
        OExtendedBlockStorage oextendedblockstorage = this.q[j >> 4];

        return oextendedblockstorage == null ? oenumskyblock.c : (oenumskyblock == OEnumSkyBlock.a ? oextendedblockstorage.c(i, j & 15, k) : (oenumskyblock == OEnumSkyBlock.b ? oextendedblockstorage.d(i, j & 15, k) : oenumskyblock.c));
    }

    public void a(OEnumSkyBlock oenumskyblock, int i, int j, int k, int l) {
        OExtendedBlockStorage oextendedblockstorage = this.q[j >> 4];

        if (oextendedblockstorage == null) {
            oextendedblockstorage = this.q[j >> 4] = new OExtendedBlockStorage(j >> 4 << 4);
            this.a();
        }

        this.l = true;
        if (oenumskyblock == OEnumSkyBlock.a) {
            if (!this.e.t.e) {
                oextendedblockstorage.c(i, j & 15, k, l);
            }
        } else {
            if (oenumskyblock != OEnumSkyBlock.b) {
                return;
            }

            oextendedblockstorage.d(i, j & 15, k, l);
        }

    }

    public int c(int i, int j, int k, int l) {
        OExtendedBlockStorage oextendedblockstorage = this.q[j >> 4];

        if (oextendedblockstorage == null) {
            return !this.e.t.e && l < OEnumSkyBlock.a.c ? OEnumSkyBlock.a.c - l : 0;
        } else {
            int i1 = this.e.t.e ? 0 : oextendedblockstorage.c(i, j & 15, k);

            if (i1 > 0) {
                a = true;
            }

            i1 -= l;
            int j1 = oextendedblockstorage.d(i, j & 15, k);

            if (j1 > i1) {
                i1 = j1;
            }

            return i1;
        }
    }

    public void a(OEntity oentity) {
        this.m = true;
        int i = OMathHelper.b(oentity.bm / 16.0D);
        int j = OMathHelper.b(oentity.bo / 16.0D);

        if (i != this.g || j != this.h) {
            System.out.println("Wrong location! " + oentity + " Destorying Entity...");
            // Thread.dumpStack(); //CanaryMod: Disabled stack dump...
            oentity.X(); // CanaryMod: Destroy the entity instead of keeping it around to cause more troubles
            return;
        }

        int k = OMathHelper.b(oentity.bn / 16.0D);

        if (k < 0) {
            k = 0;
        }

        if (k >= this.j.length) {
            k = this.j.length - 1;
        }

        oentity.bZ = true;
        oentity.ca = this.g;
        oentity.cb = k;
        oentity.cc = this.h;
        this.j[k].add(oentity);
    }

    public void b(OEntity oentity) {
        this.a(oentity, oentity.cb);
    }

    public void a(OEntity oentity, int i) {
        if (i < 0) {
            i = 0;
        }

        if (i >= this.j.length) {
            i = this.j.length - 1;
        }

        this.j[i].remove(oentity);
    }

    public boolean d(int i, int j, int k) {
        return j >= this.f[k << 4 | i];
    }

    public OTileEntity e(int i, int j, int k) {
        OChunkPosition ochunkposition = new OChunkPosition(i, j, k);
        OTileEntity otileentity = (OTileEntity) this.i.get(ochunkposition);

        if (otileentity == null) {
            int l = this.a(i, j, k);

            if (l <= 0 || !OBlock.m[l].o()) {
                return null;
            }

            if (otileentity == null) {
                otileentity = ((OBlockContainer) OBlock.m[l]).a_();
                this.e.a(this.g * 16 + i, j, this.h * 16 + k, otileentity);
            }

            otileentity = (OTileEntity) this.i.get(ochunkposition);
        }

        if (otileentity != null && otileentity.l()) {
            this.i.remove(ochunkposition);
            return null;
        } else {
            return otileentity;
        }
    }

    public void a(OTileEntity otileentity) {
        int i = otileentity.l - this.g * 16;
        int j = otileentity.m;
        int k = otileentity.n - this.h * 16;

        this.a(i, j, k, otileentity);
        if (this.d) {
            this.e.c.add(otileentity);
        }

    }

    public void a(int i, int j, int k, OTileEntity otileentity) {
        OChunkPosition ochunkposition = new OChunkPosition(i, j, k);

        otileentity.k = this.e;
        otileentity.l = this.g * 16 + i;
        otileentity.m = j;
        otileentity.n = this.h * 16 + k;
        if (this.a(i, j, k) != 0 && OBlock.m[this.a(i, j, k)] instanceof OBlockContainer) {
            otileentity.m();
            this.i.put(ochunkposition, otileentity);
        }
    }

    public void f(int i, int j, int k) {
        OChunkPosition ochunkposition = new OChunkPosition(i, j, k);

        if (this.d) {
            OTileEntity otileentity = (OTileEntity) this.i.remove(ochunkposition);

            if (otileentity != null) {
                otileentity.j();
            }
        }

    }

    public void c() {
        this.d = true;
        this.e.a(this.i.values());

        for (int i = 0; i < this.j.length; ++i) {
            this.e.a(this.j[i]);
        }

    }

    public void d() {
        this.d = false;
        Iterator iterator = this.i.values().iterator();

        while (iterator.hasNext()) {
            OTileEntity otileentity = (OTileEntity) iterator.next();

            this.e.a(otileentity);
        }

        for (int i = 0; i < this.j.length; ++i) {
            this.e.b(this.j[i]);
        }

    }

    public void e() {
        this.l = true;
    }

    public void a(OEntity oentity, OAxisAlignedBB oaxisalignedbb, List list) {
        int i = OMathHelper.b((oaxisalignedbb.b - 2.0D) / 16.0D);
        int j = OMathHelper.b((oaxisalignedbb.e + 2.0D) / 16.0D);

        if (i < 0) {
            i = 0;
        }

        if (j >= this.j.length) {
            j = this.j.length - 1;
        }

        for (int k = i; k <= j; ++k) {
            List list1 = this.j[k];

            for (int l = 0; l < list1.size(); ++l) {
                OEntity oentity1 = (OEntity) list1.get(l);

                if (oentity1 != oentity && oentity1.bw.a(oaxisalignedbb)) {
                    list.add(oentity1);
                    OEntity[] aoentity = oentity1.bb();

                    if (aoentity != null) {
                        for (int i1 = 0; i1 < aoentity.length; ++i1) {
                            oentity1 = aoentity[i1];
                            if (oentity1 != oentity && oentity1.bw.a(oaxisalignedbb)) {
                                list.add(oentity1);
                            }
                        }
                    }
                }
            }
        }

    }

    public void a(Class oclass, OAxisAlignedBB oaxisalignedbb, List list) {
        int i = OMathHelper.b((oaxisalignedbb.b - 2.0D) / 16.0D);
        int j = OMathHelper.b((oaxisalignedbb.e + 2.0D) / 16.0D);

        if (i < 0) {
            i = 0;
        } else if (i >= this.j.length) {
            i = this.j.length - 1;
        }

        if (j >= this.j.length) {
            j = this.j.length - 1;
        } else if (j < 0) {
            j = 0;
        }

        for (int k = i; k <= j; ++k) {
            List list1 = this.j[k];

            for (int l = 0; l < list1.size(); ++l) {
                OEntity oentity = (OEntity) list1.get(l);

                if (oclass.isAssignableFrom(oentity.getClass()) && oentity.bw.a(oaxisalignedbb)) {
                    list.add(oentity);
                }
            }
        }

    }

    public boolean a(boolean flag) {
        if (flag) {
            if (this.m && this.e.o() != this.n) {
                return true;
            }
        } else if (this.m && this.e.o() >= this.n + 600L) {
            return true;
        }

        return this.l;
    }

    public Random a(long i) {
        return new Random(this.e.n() + (long) (this.g * this.g * 4987142) + (long) (this.g * 5947611) + (long) (this.h * this.h) * 4392871L + (long) (this.h * 389711) ^ i);
    }

    public boolean f() {
        return false;
    }

    public void i() {
        OExtendedBlockStorage[] aoextendedblockstorage = this.q;
        int i = aoextendedblockstorage.length;

        for (int j = 0; j < i; ++j) {
            OExtendedBlockStorage oextendedblockstorage = aoextendedblockstorage[j];

            if (oextendedblockstorage != null) {
                oextendedblockstorage.e();
            }
        }

    }

    public void a(OIChunkProvider oichunkprovider, OIChunkProvider oichunkprovider1, int i, int j) {
        if (!this.k && oichunkprovider.a(i + 1, j + 1) && oichunkprovider.a(i, j + 1) && oichunkprovider.a(i + 1, j)) {
            oichunkprovider.a(oichunkprovider1, i, j);
        }

        if (oichunkprovider.a(i - 1, j) && !oichunkprovider.b(i - 1, j).k && oichunkprovider.a(i - 1, j + 1) && oichunkprovider.a(i, j + 1) && oichunkprovider.a(i - 1, j + 1)) {
            oichunkprovider.a(oichunkprovider1, i - 1, j);
        }

        if (oichunkprovider.a(i, j - 1) && !oichunkprovider.b(i, j - 1).k && oichunkprovider.a(i + 1, j - 1) && oichunkprovider.a(i + 1, j - 1) && oichunkprovider.a(i + 1, j)) {
            oichunkprovider.a(oichunkprovider1, i, j - 1);
        }

        if (oichunkprovider.a(i - 1, j - 1) && !oichunkprovider.b(i - 1, j - 1).k && oichunkprovider.a(i, j - 1) && oichunkprovider.a(i - 1, j)) {
            oichunkprovider.a(oichunkprovider1, i - 1, j - 1);
        }

    }

    public int d(int i, int j) {
        int k = i | j << 4;
        int l = this.b[k];

        if (l == -999) {
            int i1 = this.g() + 15;

            l = -1;

            while (i1 > 0 && l == -1) {
                int j1 = this.a(i, i1, j);
                OMaterial omaterial = j1 == 0 ? OMaterial.a : OBlock.m[j1].cd;

                if (!omaterial.c() && !omaterial.d()) {
                    --i1;
                } else {
                    l = i1 + 1;
                }
            }

            this.b[k] = l;
        }

        return l;
    }

    public void j() {
        if (this.s && !this.e.t.e) {
            this.o();
        }

    }

    public OChunkCoordIntPair k() {
        return new OChunkCoordIntPair(this.g, this.h);
    }

    public boolean c(int i, int j) {
        if (i < 0) {
            i = 0;
        }

        if (j >= 256) {
            j = 255;
        }

        for (int k = i; k <= j; k += 16) {
            OExtendedBlockStorage oextendedblockstorage = this.q[k >> 4];

            if (oextendedblockstorage != null && !oextendedblockstorage.a()) {
                return false;
            }
        }

        return true;
    }

    public void a(OExtendedBlockStorage[] aoextendedblockstorage) {
        this.q = aoextendedblockstorage;
    }

    public OBiomeGenBase a(int i, int j, OWorldChunkManager oworldchunkmanager) {
        int k = this.r[j << 4 | i] & 255;

        if (k == 255) {
            OBiomeGenBase obiomegenbase = oworldchunkmanager.a((this.g << 4) + i, (this.h << 4) + j);

            k = obiomegenbase.M;
            this.r[j << 4 | i] = (byte) (k & 255);
        }

        return OBiomeGenBase.a[k] == null ? OBiomeGenBase.c : OBiomeGenBase.a[k];
    }

    public byte[] l() {
        return this.r;
    }

    public void a(byte[] abyte) {
        this.r = abyte;
    }

    public void m() {
        this.t = 0;
    }

    public void n() {
        for (int i = 0; i < 8; ++i) {
            if (this.t >= 4096) {
                return;
            }

            int j = this.t % 16;
            int k = this.t / 16 % 16;
            int l = this.t / 256;

            ++this.t;
            int i1 = (this.g << 4) + k;
            int j1 = (this.h << 4) + l;

            for (int k1 = 0; k1 < 16; ++k1) {
                int l1 = (j << 4) + k1;

                if (this.q[j] == null && (k1 == 0 || k1 == 15 || k == 0 || k == 15 || l == 0 || l == 15) || this.q[j] != null && this.q[j].a(k, k1, l) == 0) {
                    if (OBlock.q[this.e.a(i1, l1 - 1, j1)] > 0) {
                        this.e.v(i1, l1 - 1, j1);
                    }

                    if (OBlock.q[this.e.a(i1, l1 + 1, j1)] > 0) {
                        this.e.v(i1, l1 + 1, j1);
                    }

                    if (OBlock.q[this.e.a(i1 - 1, l1, j1)] > 0) {
                        this.e.v(i1 - 1, l1, j1);
                    }

                    if (OBlock.q[this.e.a(i1 + 1, l1, j1)] > 0) {
                        this.e.v(i1 + 1, l1, j1);
                    }

                    if (OBlock.q[this.e.a(i1, l1, j1 - 1)] > 0) {
                        this.e.v(i1, l1, j1 - 1);
                    }

                    if (OBlock.q[this.e.a(i1, l1, j1 + 1)] > 0) {
                        this.e.v(i1, l1, j1 + 1);
                    }

                    this.e.v(i1, l1, j1);
                }
            }
        }

    }
}
