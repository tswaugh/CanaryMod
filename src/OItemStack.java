
public final class OItemStack {

    public int a;
    public int b;
    public int c;
    public ONBTTagCompound d;
    private int e;

    public OItemStack(OBlock oblock) {
        this(oblock, 1);
    }

    public OItemStack(OBlock oblock, int i) {
        this(oblock.bO, i, 0);
    }

    public OItemStack(OBlock oblock, int i, int j) {
        this(oblock.bO, i, j);
    }

    public OItemStack(OItem oitem) {
        this(oitem.bP, 1, 0);
    }

    public OItemStack(OItem oitem, int i) {
        this(oitem.bP, i, 0);
    }

    public OItemStack(OItem oitem, int i, int j) {
        this(oitem.bP, i, j);
    }

    public OItemStack(int i, int j, int k) {
        super();
        this.a = 0;
        this.c = i;
        this.a = j;
        this.e = k;
    }

    public static OItemStack a(ONBTTagCompound onbttagcompound) {
        OItemStack oitemstack = new OItemStack();

        oitemstack.c(onbttagcompound);
        return oitemstack.a() != null ? oitemstack : null;
    }

    private OItemStack() {
        super();
        this.a = 0;
    }

    public OItemStack a(int i) {
        OItemStack oitemstack = new OItemStack(this.c, i, this.e);

        if (this.d != null) {
            oitemstack.d = (ONBTTagCompound) this.d.b();
        }

        this.a -= i;
        return oitemstack;
    }

    public OItem a() {
        return OItem.d[this.c];
    }

    public boolean a(OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        boolean flag = this.a().a(this, oentityplayer, oworld, i, j, k, l);

        if (flag) {
            oentityplayer.a(OStatList.E[this.c], 1);
        }

        return flag;
    }

    public float a(OBlock oblock) {
        return this.a().a(this, oblock);
    }

    public OItemStack a(OWorld oworld, OEntityPlayer oentityplayer) {
        return this.a().a(this, oworld, oentityplayer);
    }

    public OItemStack b(OWorld oworld, OEntityPlayer oentityplayer) {
        return this.a().b(this, oworld, oentityplayer);
    }

    public ONBTTagCompound b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("id", (short) this.c); // CanaryMod: fix jarjar
        onbttagcompound.a("Count", (byte) this.a);
        onbttagcompound.a("Damage", (short) this.e);
        if (this.d != null) {
            onbttagcompound.a("tag", (ONBTBase) this.d);
        }

        return onbttagcompound;
    }

    public void c(ONBTTagCompound onbttagcompound) {
        this.c = onbttagcompound.e("id"); // CanaryMod: fix jarjar
        this.a = onbttagcompound.d("Count");
        this.e = onbttagcompound.e("Damage");
        if (onbttagcompound.c("tag")) {
            this.d = onbttagcompound.m("tag");
        }

    }

    public int b() {
        return this.a().d();
    }

    public boolean c() {
        return this.b() > 1 && (!this.d() || !this.f());
    }

    public boolean d() {
        return OItem.d[this.c].f() > 0;
    }

    public boolean e() {
        return OItem.d[this.c].e();
    }

    public boolean f() {
        return this.d() && this.e > 0;
    }

    public int g() {
        return this.e;
    }

    public int h() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public int i() {
        return OItem.d[this.c].f();
    }

    public void a(int i, OEntityLiving oentityliving) {
        if (this.d()) {
            if (i > 0 && oentityliving instanceof OEntityPlayer) {
                int j = OEnchantmentHelper.c(((OEntityPlayer) oentityliving).k);

                if (j > 0 && oentityliving.bi.r.nextInt(j + 1) > 0) {
                    return;
                }
            }

            this.e += i;
            if (this.e > this.i()) {
                oentityliving.c(this);
                if (oentityliving instanceof OEntityPlayer) {
                    ((OEntityPlayer) oentityliving).a(OStatList.F[this.c], 1);
                }

                --this.a;
                if (this.a < 0) {
                    this.a = 0;
                }

                this.e = 0;
            }

        }
    }

    public void a(OEntityLiving oentityliving, OEntityPlayer oentityplayer) {
        boolean flag = OItem.d[this.c].a(this, oentityliving, (OEntityLiving) oentityplayer);

        if (flag) {
            oentityplayer.a(OStatList.E[this.c], 1);
        }

    }

    public void a(int i, int j, int k, int l, OEntityPlayer oentityplayer) {
        boolean flag = OItem.d[this.c].a(this, i, j, k, l, oentityplayer);

        if (flag) {
            oentityplayer.a(OStatList.E[this.c], 1);
        }

    }

    public int a(OEntity oentity) {
        return OItem.d[this.c].a(oentity);
    }

    public boolean b(OBlock oblock) {
        return OItem.d[this.c].a(oblock);
    }

    public void a(OEntityPlayer oentityplayer) {}

    public void a(OEntityLiving oentityliving) {
        OItem.d[this.c].a(this, oentityliving);
    }

    public OItemStack j() {
        OItemStack oitemstack = new OItemStack(this.c, this.a, this.e);

        if (this.d != null) {
            oitemstack.d = (ONBTTagCompound) this.d.b();
            if (!oitemstack.d.equals(this.d)) {
                return oitemstack;
            }
        }

        return oitemstack;
    }

    public static boolean a(OItemStack oitemstack, OItemStack oitemstack1) {
        return oitemstack == null && oitemstack1 == null ? true : (oitemstack != null && oitemstack1 != null ? (oitemstack.d == null && oitemstack1.d != null ? false : oitemstack.d == null || oitemstack.d.equals(oitemstack1.d)) : false);
    }

    public static boolean b(OItemStack oitemstack, OItemStack oitemstack1) {
        return oitemstack == null && oitemstack1 == null ? true : (oitemstack != null && oitemstack1 != null ? oitemstack.d(oitemstack1) : false);
    }

    private boolean d(OItemStack oitemstack) {
        return this.a != oitemstack.a ? false : (this.c != oitemstack.c ? false : (this.e != oitemstack.e ? false : (this.d == null && oitemstack.d != null ? false : this.d == null || this.d.equals(oitemstack.d))));
    }

    public boolean a(OItemStack oitemstack) {
        return this.c == oitemstack.c && this.e == oitemstack.e;
    }

    public String k() {
        return OItem.d[this.c].a(this);
    }

    public static OItemStack b(OItemStack oitemstack) {
        return oitemstack == null ? null : oitemstack.j();
    }

    public String toString() {
        return this.a + "x" + OItem.d[this.c].b() + "@" + this.e; // CanaryMod: fix jarjar
    }

    public void a(OWorld oworld, OEntity oentity, int i, boolean flag) {
        if (this.b > 0) {
            --this.b;
        }

        OItem.d[this.c].a(this, oworld, oentity, i, flag);
    }

    public void a(OWorld oworld, OEntityPlayer oentityplayer, int i) {
        oentityplayer.a(OStatList.D[this.c], i);
        OItem.d[this.c].d(this, oworld, oentityplayer);
    }

    public boolean c(OItemStack oitemstack) {
        return this.c == oitemstack.c && this.a == oitemstack.a && this.e == oitemstack.e;
    }

    public int l() {
        return this.a().c(this);
    }

    public OEnumAction m() {
        return this.a().d(this);
    }

    public void b(OWorld oworld, OEntityPlayer oentityplayer, int i) {
        this.a().a(this, oworld, oentityplayer, i);
    }

    public boolean n() {
        return this.d != null;
    }

    public ONBTTagCompound o() {
        return this.d;
    }

    public ONBTTagList p() {
        return this.d == null ? null : (ONBTTagList) this.d.b("ench");
    }

    public void d(ONBTTagCompound onbttagcompound) {
        this.d = onbttagcompound;
    }

    public boolean q() {
        return !this.a().f(this) ? false : !this.r();
    }

    public void a(OEnchantment oenchantment, int i) {
        if (this.d == null) {
            this.d(new ONBTTagCompound());
        }

        if (!this.d.c("ench")) {
            this.d.a("ench", (ONBTBase) (new ONBTTagList("ench")));
        }

        ONBTTagList onbttaglist = (ONBTTagList) this.d.b("ench");
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        onbttagcompound.a("id", (short) oenchantment.x); // CanaryMod: fix jarjar
        onbttagcompound.a("lvl", (short) ((byte) i));
        onbttaglist.a((ONBTBase) onbttagcompound);
    }

    public boolean r() {
        return this.d != null && this.d.c("ench");
    }
}
