
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
        if (this.b.name.equals(oentity.bi.name)) {
            this.b.getEntityTracker().trackEntity(oentity);
        }
    }

    public void b(OEntity oentity) {
        if (this.b.name.equals(oentity.bi.name)) {
            this.b.getEntityTracker().untrackEntity(oentity);
        }
    }

    public void a(String s, double d0, double d1, double d2, float f, float f1) {}

    public void a(int i, int j, int k, int l, int i1, int j1) {}

    public void a(int i, int j, int k) {
        this.a.h.markBlockNeedsUpdate(i, j, k, this.b.t.g, this.b.name);
    }

    public void b(int i, int j, int k) {}

    public void a(String s, int i, int j, int k) {}

    public void a(int i, int j, int k, OTileEntity otileentity) {
        this.a.h.a(i, j, k, otileentity);
    }

    public void a(OEntityPlayer oentityplayer, int i, int j, int k, int l, int i1) {
        // canatyMod: Fix for block particle spawning cross-worlds
        this.a.h.a(oentityplayer, (double) j, (double) k, (double) l, 64.0D, this.b.t.g, new OPacket61DoorChange(i, j, k, l, i1), b.name);
    }
}
