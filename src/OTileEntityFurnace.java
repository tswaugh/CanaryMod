import java.util.Arrays;


public class OTileEntityFurnace extends OTileEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] d = new OItemStack[3];
    public int a = 0;
    public int b = 0;
    public int c = 0;
    private String name = "container.furnace"; // CanaryMod

    public OTileEntityFurnace() {
        super();
    }

    public int c() {
        return this.d.length;
    }

    public OItemStack g_(int i) {
        return this.d[i];
    }

    public OItemStack a(int i, int j) {
        if (this.d[i] != null) {
            OItemStack oitemstack;

            if (this.d[i].a <= j) {
                oitemstack = this.d[i];
                this.d[i] = null;
                return oitemstack;
            } else {
                oitemstack = this.d[i].a(j);
                if (this.d[i].a == 0) {
                    this.d[i] = null;
                }

                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        if (this.d[i] != null) {
            OItemStack oitemstack = this.d[i];

            this.d[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        this.d[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.a()) {
            oitemstack.a = this.a();
        }

    }

    public String e() {
        return name;
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.n("Items");

        this.d = new OItemStack[this.c()];

        for (int i = 0; i < onbttaglist.d(); ++i) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.a(i);
            byte b0 = onbttagcompound1.d("Slot");

            if (b0 >= 0 && b0 < this.d.length) {
                this.d[b0] = OItemStack.a(onbttagcompound1);
            }
        }

        this.a = onbttagcompound.e("BurnTime");
        this.c = onbttagcompound.e("CookTime");
        this.b = a(this.d[1]);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("BurnTime", (short) this.a);
        onbttagcompound.a("CookTime", (short) this.c);
        ONBTTagList onbttaglist = new ONBTTagList();

        for (int i = 0; i < this.d.length; ++i) {
            if (this.d[i] != null) {
                ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

                onbttagcompound1.a("Slot", (byte) i);
                this.d[i].b(onbttagcompound1);
                onbttaglist.a((ONBTBase) onbttagcompound1);
            }
        }

        onbttagcompound.a("Items", (ONBTBase) onbttaglist);
    }

    public int a() {
        return 64;
    }

    public boolean i() {
        return this.a > 0;
    }

    public void q_() {
        boolean flag = this.a > 0;
        boolean flag1 = false;

        if (this.a > 0) {
            --this.a;
        }

        if (!this.k.F) {
            if (this.a == 0 && this.o()) {
                this.b = this.a = a(this.d[1]);
                if (this.a > 0) {
                    flag1 = true;
                    if (this.d[1] != null) {
                        --this.d[1].a;
                        if (this.d[1].a == 0) {
                            this.d[1] = null;
                        }
                    }
                }
            }

            if (this.i() && this.o()) {
                ++this.c;
                if (this.c == 200) {
                    this.c = 0;
                    this.n();
                    flag1 = true;
                }
            } else {
                this.c = 0;
            }

            if (flag != this.a > 0) {
                flag1 = true;
                OBlockFurnace.a(this.a > 0, this.k, this.l, this.m, this.n);
            }
        }

        if (flag1) {
            this.G_();
        }

    }

    private boolean o() {
        if (this.d[0] == null) {
            return false;
        } else {
            OItemStack oitemstack = OFurnaceRecipes.a().a(this.d[0].a().bP);

            return oitemstack == null ? false : (this.d[2] == null ? true : (!this.d[2].a(oitemstack) ? false : (this.d[2].a < this.a() && this.d[2].a < this.d[2].b() ? true : this.d[2].a < oitemstack.b())));
        }
    }

    public void n() {
        if (this.o()) {
            OItemStack oitemstack = OFurnaceRecipes.a().a(this.d[0].a().bP);

            if (this.d[2] == null) {
                this.d[2] = oitemstack.j();
            } else if (this.d[2].c == oitemstack.c) {
                ++this.d[2].a;
            }

            --this.d[0].a;
            if (this.d[0].a <= 0) {
                this.d[0] = null;
            }

        }
    }

    public static int a(OItemStack oitemstack) {
        if (oitemstack == null) {
            return 0;
        } else {
            int i = oitemstack.a().bP;

            return i < 256 && OBlock.m[i].cd == OMaterial.d ? 300 : (i == OItem.C.bP ? 100 : (i == OItem.l.bP ? 1600 : (i == OItem.ax.bP ? 20000 : (i == OBlock.y.bO ? 100 : (i == OItem.bn.bP ? 2400 : 0)))));
        }
    }

    public static boolean b(OItemStack oitemstack) {
        return a(oitemstack) > 0;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.k.b(this.l, this.m, this.n) != this ? false : oentityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void f() {}

    public void g() {}

    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(d, getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        d = Arrays.copyOf(aoitemstack, getContentsSize());
    }

    @Override
    public OItemStack getContentsAt(int i) {
        return g_(i);
    }

    @Override
    public void setContentsAt(int i, OItemStack oitemstack) {
        a(i, oitemstack);
    }

    @Override
    public int getContentsSize() {
        return c();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String s) {
        name = s;
    }

}
