import java.util.HashMap;
import java.util.Map;


public class OTileEntity {

    private static Map a = new HashMap();
    private static Map b = new HashMap();
    public OWorld k;
    public int l;
    public int m;
    public int n;
    protected boolean o;
    public int p = -1;
    public OBlock q;

    public OTileEntity() {
        super();
    }

    private static void a(Class oclass, String s) {
        if (b.containsKey(s)) {
            throw new IllegalArgumentException("Duplicate id: " + s);
        } else {
            a.put(s, oclass);
            b.put(oclass, s);
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.l = onbttagcompound.f("x");
        this.m = onbttagcompound.f("y");
        this.n = onbttagcompound.f("z");
    }

    public void b(ONBTTagCompound onbttagcompound) {
        String s = (String) b.get(this.getClass());

        if (s == null) {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        } else {
            onbttagcompound.a("id", s);
            onbttagcompound.a("x", this.l);
            onbttagcompound.a("y", this.m);
            onbttagcompound.a("z", this.n);
        }
    }

    public void q_() {}

    public static OTileEntity c(ONBTTagCompound onbttagcompound) {
        OTileEntity otileentity = null;

        try {
            Class oclass = (Class) a.get(onbttagcompound.j("id"));

            if (oclass != null) {
                otileentity = (OTileEntity) oclass.newInstance();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (otileentity != null) {
            otileentity.a(onbttagcompound);
        } else {
            System.out.println("Skipping TileEntity with id " + onbttagcompound.j("id"));
        }

        return otileentity;
    }

    public int k() {
        if (this.p == -1) {
            this.p = this.k.c(this.l, this.m, this.n);
        }

        return this.p;
    }

    public void G_() {
        if (this.k != null) {
            this.p = this.k.c(this.l, this.m, this.n);
            this.k.b(this.l, this.m, this.n, this);
        }

    }

    public OPacket d() {
        return null;
    }

    public boolean l() {
        return this.o;
    }

    public void j() {
        this.o = true;
    }

    public void m() {
        this.o = false;
    }

    public void b(int i, int j) {}

    public void h() {
        this.q = null;
        this.p = -1;
    }

    static {
        a(OTileEntityFurnace.class, "Furnace");
        a(OTileEntityChest.class, "Chest");
        a(OTileEntityRecordPlayer.class, "RecordPlayer");
        a(OTileEntityDispenser.class, "Trap");
        a(OTileEntitySign.class, "Sign");
        a(OTileEntityMobSpawner.class, "MobSpawner");
        a(OTileEntityNote.class, "Music");
        a(OTileEntityPiston.class, "Piston");
        a(OTileEntityBrewingStand.class, "Cauldron");
        a(OTileEntityEnchantmentTable.class, "EnchantTable");
        a(OTileEntityEndPortal.class, "Airportal");
    }
}
