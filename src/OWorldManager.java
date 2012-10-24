import java.util.Iterator;

public class OWorldManager implements OIWorldAccess {

    private OMinecraftServer a;
    public OWorldServer b; // CanaryMod private -> public

    public OWorldManager(OMinecraftServer ominecraftserver, OWorldServer oworldserver) {
        super();
        this.a = ominecraftserver;
        this.b = oworldserver;
    }

    public void a(String s, double d0, double d1, double d2, double d3, double d4, double d5) {}

    public void a(OEntity oentity) {
        this.b.p().a(oentity);
    }

    public void b(OEntity oentity) {
        this.b.p().b(oentity);
    }

    public void a(String s, double d0, double d1, double d2, float f, float f1) {
<<<<<<<
        this.a.ab().a(d0, d1, d2, f > 1.0F ? (double) (16.0F * f) : 16.0D, this.b.w.g, new OPacket62LevelSound(s, d0, d1, d2, f, f1), this.b.name);
|||||||
        this.a.ab().a(d0, d1, d2, f > 1.0F ? (double) (16.0F * f) : 16.0D, this.b.w.g, new OPacket62LevelSound(s, d0, d1, d2, f, f1));
=======
        this.a.ad().a(d0, d1, d2, f > 1.0F ? (double) (16.0F * f) : 16.0D, this.b.v.h, new OPacket62LevelSound(s, d0, d1, d2, f, f1));
>>>>>>>
    }

    public void a(int i, int j, int k, int l, int i1, int j1) {}

    public void a(int i, int j, int k) {
        this.b.r().a(i, j, k);
    }

    public void b(int i, int j, int k) {}

    public void a(String s, int i, int j, int k) {}

    public void a(OEntityPlayer oentityplayer, int i, int j, int k, int l, int i1) {
<<<<<<<
        this.a.ab().a(oentityplayer, (double) j, (double) k, (double) l, 64.0D, this.b.w.g, new OPacket61DoorChange(i, j, k, l, i1), this.b.name);
|||||||
        this.a.ab().a(oentityplayer, (double) j, (double) k, (double) l, 64.0D, this.b.w.g, new OPacket61DoorChange(i, j, k, l, i1));
=======
        this.a.ad().a(oentityplayer, (double) j, (double) k, (double) l, 64.0D, this.b.v.h, new OPacket61DoorChange(i, j, k, l, i1, false));
>>>>>>>
    }

    public void a(int i, int j, int k, int l, int i1) {
        this.a.ad().a((OPacket) (new OPacket61DoorChange(i, j, k, l, i1, true)));
    }

    public void b(int i, int j, int k, int l, int i1) {
        Iterator iterator = this.a.ad().b.iterator();

        while (iterator.hasNext()) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) iterator.next();

            if (oentityplayermp != null && oentityplayermp.p == this.b && oentityplayermp.k != i) {
                double d0 = (double) j - oentityplayermp.t;
                double d1 = (double) k - oentityplayermp.u;
                double d2 = (double) l - oentityplayermp.v;

                if (d0 * d0 + d1 * d1 + d2 * d2 < 1024.0D) {
                    oentityplayermp.a.b(new OPacket55BlockDestroy(i, j, k, l, i1));
                }
            }
        }
    }
}
