
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
        this(oblock.ca, i, 0);
    }

    public OItemStack(OBlock oblock, int i, int j) {
        this(oblock.ca, i, j);
    }

    public OItemStack(OItem oitem) {
        this(oitem.bT, 1, 0);
    }

    public OItemStack(OItem oitem, int i) {
        this(oitem.bT, i, 0);
    }

    public OItemStack(OItem oitem, int i, int j) {
        this(oitem.bT, i, j);
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
        return oitemstack.b() != null ? oitemstack : null;
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

    public OItem b() {
        return OItem.e[this.c];
    }

    public boolean a(OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        boolean flag = this.b().a(this, oentityplayer, oworld, i, j, k, l, f, f1, f2);

        if (flag) {
            oentityplayer.a(OStatList.E[this.c], 1);
        }

        return flag;
    }

    public float a(OBlock oblock) {
        return this.b().a(this, oblock);
    }

    public OItemStack a(OWorld oworld, OEntityPlayer oentityplayer) {
        return this.b().a(this, oworld, oentityplayer);
    }

    public OItemStack b(OWorld oworld, OEntityPlayer oentityplayer) {
        return this.b().b(this, oworld, oentityplayer);
    }

    public ONBTTagCompound b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("id", (short) this.c);
        onbttagcompound.a("Count", (byte) this.a);
        onbttagcompound.a("Damage", (short) this.e);
        if (this.d != null) {
            onbttagcompound.a("tag", (ONBTBase) this.d);
        }

        return onbttagcompound;
    }

    public void c(ONBTTagCompound onbttagcompound) {
        this.c = onbttagcompound.d("id");
        this.a = onbttagcompound.c("Count");
        this.e = onbttagcompound.d("Damage");
        if (onbttagcompound.b("tag")) {
            this.d = onbttagcompound.l("tag");
        }

    }

    public int d() {
        return this.b().j();
    }

    public boolean e() {
        return this.d() > 1 && (!this.f() || !this.h());
    }

    public boolean f() {
        return OItem.e[this.c].l() > 0;
    }

    public boolean g() {
        return OItem.e[this.c].k();
    }

    public boolean h() {
        return this.f() && this.e > 0;
    }

    public int i() {
        return this.e;
    }

    public int j() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public int k() {
        return OItem.e[this.c].l();
    }

    public void a(int i, OEntityLiving oentityliving) {
        if (this.f()) {
            if (i > 0 && oentityliving instanceof OEntityPlayer) {
                int j = OEnchantmentHelper.c(((OEntityPlayer) oentityliving).by);

                if (j > 0 && oentityliving.p.v.nextInt(j + 1) > 0) {
                    return;
                }
            }

            if (!(oentityliving instanceof OEntityPlayer) || !((OEntityPlayer) oentityliving).bZ.d) {
                this.e += i;
            }

            if (this.e > this.k()) {
                oentityliving.a(this);
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
        boolean flag = OItem.e[this.c].a(this, oentityliving, (OEntityLiving) oentityplayer);

        if (flag) {
            oentityplayer.a(OStatList.E[this.c], 1);
        }

    }

    public void a(OWorld oworld, int i, int j, int k, int l, OEntityPlayer oentityplayer) {
        boolean flag = OItem.e[this.c].a(this, oworld, i, j, k, l, oentityplayer);

        if (flag) {
            oentityplayer.a(OStatList.E[this.c], 1);
        }

    }

    public int a(OEntity oentity) {
        return OItem.e[this.c].a(oentity);
    }

    public boolean b(OBlock oblock) {
        return OItem.e[this.c].a(oblock);
    }

    public boolean a(OEntityLiving oentityliving) {
        return OItem.e[this.c].a(this, oentityliving);
    }

    public OItemStack l() {
        OItemStack oitemstack = new OItemStack(this.c, this.a, this.e);

        if (this.d != null) {
            oitemstack.d = (ONBTTagCompound) this.d.b();
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

    public String a() {
        return OItem.e[this.c].c(this);
    }

    public static OItemStack b(OItemStack oitemstack) {
        return oitemstack == null ? null : oitemstack.l();
    }

    public String toString() {
        return this.a + "x" + OItem.e[this.c].a() + "@" + this.e;
    }

    public void a(OWorld oworld, OEntity oentity, int i, boolean flag) {
        if (this.b > 0) {
            --this.b;
        }

        OItem.e[this.c].a(this, oworld, oentity, i, flag);
    }

    public void a(OWorld oworld, OEntityPlayer oentityplayer, int i) {
        oentityplayer.a(OStatList.D[this.c], i);
        OItem.e[this.c].d(this, oworld, oentityplayer);
    }

    public boolean c(OItemStack oitemstack) {
        return this.c == oitemstack.c && this.a == oitemstack.a && this.e == oitemstack.e;
    }

    public int m() {
        return this.b().a(this);
    }

    public OEnumAction n() {
        return this.b().b(this);
    }

    public void b(OWorld oworld, OEntityPlayer oentityplayer, int i) {
        this.b().a(this, oworld, oentityplayer, i);
    }

    public boolean o() {
        return this.d != null;
    }

    public ONBTTagCompound p() {
        return this.d;
    }

    public ONBTTagList q() {
        return this.d == null ? null : (ONBTTagList) this.d.a("ench");
    }

    public void d(ONBTTagCompound onbttagcompound) {
        this.d = onbttagcompound;
    }

    public boolean u() {
        return !this.b().k(this) ? false : !this.v();
    }

    public void a(OEnchantment oenchantment, int i) {
        if (this.d == null) {
            this.d(new ONBTTagCompound());
        }

        if (!this.d.b("ench")) {
            this.d.a("ench", (ONBTBase) (new ONBTTagList("ench")));
        }

        ONBTTagList onbttaglist = (ONBTTagList) this.d.a("ench");
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        onbttagcompound.a("id", (short) oenchantment.x);
        onbttagcompound.a("lvl", (short) ((byte) i));
        onbttaglist.a((ONBTBase) onbttagcompound);
    }

    public boolean v() {
        return this.d != null && this.d.b("ench");
    }
}
