import java.util.ArrayList;
import java.util.List;


public class OPlayerManager {

    public List a = new ArrayList();
    private OLongHashMap b = new OLongHashMap();
    private List c = new ArrayList();
    private OMinecraftServer d;
    private int e;
    private int f;
    private final int[][] g = new int[][] { { 1, 0}, { 0, 1}, { -1, 0}, { 0, -1}};
    
    // CanaryMod: store world name
    private final String worldName;
    
    private PlayerManager playerManager; // CanaryMod player manager wrap

    public OPlayerManager(OMinecraftServer ominecraftserver, int i, int j, String s) {
        super();
        if (j > 15) {
            throw new IllegalArgumentException("Too big view radius!");
        } else if (j < 3) {
            throw new IllegalArgumentException("Too small view radius!");
        } else {
            this.f = j;
            this.d = ominecraftserver;
            this.e = i;
        }
        
        this.s = s;
        this.playerManager = new PlayerManager(this);
    }
    
    public PlayerManager getCanaryPlayerManager() {
        return playerManager;
    }

    public OWorldServer a() {
        return this.d.getWorld(this.worldName, this.e);
    }

    public void b() {
        for (int i = 0; i < this.c.size(); ++i) {
            ((OPlayerInstance) this.c.get(i)).a();
        }

        this.c.clear();
        if (this.a.isEmpty()) {
            OWorldServer oworldserver = this.d.getWorld(this.worldName, this.e);
            OWorldProvider oworldprovider = oworldserver.t;

            if (!oworldprovider.c()) {
                oworldserver.G.c();
            }
        }

    }

    private OPlayerInstance a(int i, int j, boolean flag) {
        long k = (long) i + 2147483647L | (long) j + 2147483647L << 32;
        OPlayerInstance oplayerinstance = (OPlayerInstance) this.b.a(k);

        if (oplayerinstance == null && flag) {
            oplayerinstance = new OPlayerInstance(this, i, j);
            this.b.a(k, oplayerinstance);
        }

        return oplayerinstance;
    }

    public void a(int i, int j, int k) {
        int l = i >> 4;
        int i1 = k >> 4;
        OPlayerInstance oplayerinstance = this.a(l, i1, false);

        if (oplayerinstance != null) {
            oplayerinstance.a(i & 15, j, k & 15);
        }

    }

    public void a(OEntityPlayerMP oentityplayermp) {
        int i = (int) oentityplayermp.bm >> 4;
        int j = (int) oentityplayermp.bo >> 4;

        oentityplayermp.d = oentityplayermp.bm;
        oentityplayermp.e = oentityplayermp.bo;
        int k = 0;
        int l = this.f;
        int i1 = 0;
        int j1 = 0;

        this.a(i, j, true).a(oentityplayermp);

        int k1;

        for (k1 = 1; k1 <= l * 2; ++k1) {
            for (int l1 = 0; l1 < 2; ++l1) {
                int[] aint = this.g[k++ % 4];

                for (int i2 = 0; i2 < k1; ++i2) {
                    i1 += aint[0];
                    j1 += aint[1];
                    this.a(i + i1, j + j1, true).a(oentityplayermp);
                }
            }
        }

        k %= 4;

        for (k1 = 0; k1 < l * 2; ++k1) {
            i1 += this.g[k][0];
            j1 += this.g[k][1];
            this.a(i + i1, j + j1, true).a(oentityplayermp);
        }

        this.a.add(oentityplayermp);
    }

    public void b(OEntityPlayerMP oentityplayermp) {
        int i = (int) oentityplayermp.d >> 4;
        int j = (int) oentityplayermp.e >> 4;

        for (int k = i - this.f; k <= i + this.f; ++k) {
            for (int l = j - this.f; l <= j + this.f; ++l) {
                OPlayerInstance oplayerinstance = this.a(k, l, false);

                if (oplayerinstance != null) {
                    oplayerinstance.b(oentityplayermp);
                }
            }
        }

        this.a.remove(oentityplayermp);
    }

    private boolean a(int i, int j, int k, int l) {
        int i1 = i - k;
        int j1 = j - l;

        return i1 >= -this.f && i1 <= this.f ? j1 >= -this.f && j1 <= this.f : false;
    }

    public void c(OEntityPlayerMP oentityplayermp) {
        int i = (int) oentityplayermp.bm >> 4;
        int j = (int) oentityplayermp.bo >> 4;
        double d0 = oentityplayermp.d - oentityplayermp.bm;
        double d1 = oentityplayermp.e - oentityplayermp.bo;
        double d2 = d0 * d0 + d1 * d1;

        if (d2 >= 64.0D) {
            int k = (int) oentityplayermp.d >> 4;
            int l = (int) oentityplayermp.e >> 4;
            int i1 = i - k;
            int j1 = j - l;

            if (i1 != 0 || j1 != 0) {
                // CanaryMod speed up teleporting.
                if (i1 > f || i1 < -f || j1 > f || j1 < -f) {
                    b(oentityplayermp);
                    a(oentityplayermp);
                    return;
                }

                for (int k1 = i - this.f; k1 <= i + this.f; ++k1) {
                    for (int l1 = j - this.f; l1 <= j + this.f; ++l1) {
                        if (!this.a(k1, l1, k, l)) {
                            this.a(k1, l1, true).a(oentityplayermp);
                        }

                        if (!this.a(k1 - i1, l1 - j1, i, j)) {
                            OPlayerInstance oplayerinstance = this.a(k1 - i1, l1 - j1, false);

                            if (oplayerinstance != null) {
                                oplayerinstance.b(oentityplayermp);
                            }
                        }
                    }
                }

                oentityplayermp.d = oentityplayermp.bm;
                oentityplayermp.e = oentityplayermp.bo;
            }
        }
    }

    public int c() {
        return this.f * 16 - 16;
    }

    // $FF: synthetic method
    static OLongHashMap a(OPlayerManager oplayermanager) {
        return oplayermanager.b;
    }

    // $FF: synthetic method
    static List b(OPlayerManager oplayermanager) {
        return oplayermanager.c;
    }
   
    // CanaryMod: bring back old "send packet to chunk" method from alpha
    public void sendPacketToChunk(OPacket opacket, int i, int j, int k) {
        // Get chunk coordinates
        int chunkx = i >> 4;
        int chunkz = k >> 4;
        // Get the chunk
        OPlayerInstance localOPlayerInstance = a(chunkx, chunkz, false);

        // if chunk != null, send packet
        if (localOPlayerInstance != null) {
            localOPlayerInstance.a(opacket);
        }
    }

}
