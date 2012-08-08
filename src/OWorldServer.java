import java.util.ArrayList;
import java.util.List;


public class OWorldServer extends OWorld {

    public OChunkProviderServer G;
    public boolean H = false;
    public boolean I;
    private OMinecraftServer J;
    private OIntHashMap K;

    public OWorldServer(OMinecraftServer ominecraftserver, OISaveHandler oisavehandler, String s, int i, OWorldSettings oworldsettings) {
        super(oisavehandler, s, oworldsettings, OWorldProvider.a(i));
        this.J = ominecraftserver;
        if (this.K == null) {
            this.K = new OIntHashMap();
        }

    }

    public void a(OEntity oentity, boolean flag) {
        if (!this.J.o && (oentity instanceof OEntityAnimal || oentity instanceof OEntityWaterMob)) {
            oentity.X();
        }

        if (!this.J.p && oentity instanceof OINpc) {
            oentity.X();
        }

        if (oentity.bg == null || !(oentity.bg instanceof OEntityPlayer)) {
            super.a(oentity, flag);
        }

    }

    public void b(OEntity oentity, boolean flag) {
        super.a(oentity, flag);
    }

    protected OIChunkProvider b() {
        OIChunkLoader oichunkloader = this.w.a(this.t);

        this.G = new OChunkProviderServer(this, oichunkloader, this.t.b());
        return this.G;
    }

    public List c(int i, int j, int k, int l, int i1, int j1) {
        ArrayList arraylist = new ArrayList();

        for (int k1 = 0; k1 < this.c.size(); ++k1) {
            OTileEntity otileentity = (OTileEntity) this.c.get(k1);

            if (otileentity.l >= i && otileentity.m >= j && otileentity.n >= k && otileentity.l < l && otileentity.m < i1 && otileentity.n < j1) {
                arraylist.add(otileentity);
            }
        }

        return arraylist;
    }

    public boolean a(OEntityPlayer oentityplayer, int i, int j, int k) {
        int l = OMathHelper.a(i - this.x.c());
        int i1 = OMathHelper.a(k - this.x.e());

        if (l > i1) {
            i1 = l;
        }

        return i1 > 16 || this.J.h.h(oentityplayer.v);
    }

    protected void c() {
        if (this.K == null) {
            this.K = new OIntHashMap();
        }

        super.c();
    }

    protected void c(OEntity oentity) {
        super.c(oentity);
        this.K.a(oentity.bd, oentity);
        OEntity[] aoentity = oentity.bb();

        if (aoentity != null) {
            for (int i = 0; i < aoentity.length; ++i) {
                this.K.a(aoentity[i].bd, aoentity[i]);
            }
        }

    }

    protected void d(OEntity oentity) {
        super.d(oentity);
        this.K.d(oentity.bd);
        OEntity[] aoentity = oentity.bb();

        if (aoentity != null) {
            for (int i = 0; i < aoentity.length; ++i) {
                this.K.d(aoentity[i].bd);
            }
        }

    }

    public OEntity a(int i) {
        return (OEntity) this.K.a(i);
    }

    public boolean a(OEntity oentity) {
        if (super.a(oentity)) {
            this.J.h.a(oentity.bm, oentity.bn, oentity.bo, 512.0D, this.t.g, new OPacket71Weather(oentity));
            return true;
        } else {
            return false;
        }
    }

    public void a(OEntity oentity, byte b0) {
        OPacket38EntityStatus opacket38entitystatus = new OPacket38EntityStatus(oentity.bd, b0);

        this.getEntityTracker().sendPacketToPlayersAndEntity(oentity, opacket38entitystatus);
    }

    public OExplosion a(OEntity oentity, double d0, double d1, double d2, float f, boolean flag) {
        OExplosion oexplosion = new OExplosion(this, oentity, d0, d1, d2, f);

        oexplosion.a = flag;
        oexplosion.a();
        oexplosion.a(false);
        this.J.h.a(d0, d1, d2, 64.0D, this.t.g, new OPacket60Explosion(d0, d1, d2, f, oexplosion.g));
        return oexplosion;
    }

    public void e(int i, int j, int k, int l, int i1) {
        super.e(i, j, k, l, i1);
        this.J.h.a((double) i, (double) j, (double) k, 64.0D, this.t.g, new OPacket54PlayNoteBlock(i, j, k, l, i1));
    }

    public void A() {
        this.w.e();
    }

    protected void i() {
        boolean flag = this.x();

        super.i();
        if (flag != this.x()) {
            if (flag) {
                this.J.h.a((OPacket) (new OPacket70Bed(2, 0)));
            } else {
                this.J.h.a((OPacket) (new OPacket70Bed(1, 0)));
            }
        }

    }
}
