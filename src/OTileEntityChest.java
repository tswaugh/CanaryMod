import java.util.Arrays;


public class OTileEntityChest extends OTileEntity implements OIInventory, Container<OItemStack> {

    public OItemStack[] i = new OItemStack[36];
    public boolean a = false;
    public OTileEntityChest b;
    public OTileEntityChest c;
    public OTileEntityChest d;
    public OTileEntityChest e;
    public float f;
    public float g;
    public int h;
    private int j;
    private String name = "container.chest"; // CanaryMod

    public OTileEntityChest() {
        super();
    }

    public int c() {
        return 27;
    }

    public OItemStack g_(int i) {
        return this.i[i];
    }

    public OItemStack a(int i, int j) {
        if (this.i[i] != null) {
            OItemStack oitemstack;

            if (this.i[i].a <= j) {
                oitemstack = this.i[i];
                this.i[i] = null;
                this.G_();
                return oitemstack;
            } else {
                oitemstack = this.i[i].a(j);
                if (this.i[i].a == 0) {
                    this.i[i] = null;
                }

                this.G_();
                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        if (this.i[i] != null) {
            OItemStack oitemstack = this.i[i];

            this.i[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        this.i[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.a()) {
            oitemstack.a = this.a();
        }

        this.G_();
    }

    public String e() {
        return name;
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.n("Items");

        this.i = new OItemStack[this.c()];

        for (int i = 0; i < onbttaglist.d(); ++i) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.a(i);
            int j = onbttagcompound1.d("Slot") & 255;

            if (j >= 0 && j < this.i.length) {
                this.i[j] = OItemStack.a(onbttagcompound1);
            }
        }

    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        ONBTTagList onbttaglist = new ONBTTagList();

        for (int i = 0; i < this.i.length; ++i) {
            if (this.i[i] != null) {
                ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

                onbttagcompound1.a("Slot", (byte) i);
                this.i[i].b(onbttagcompound1);
                onbttaglist.a((ONBTBase) onbttagcompound1);
            }
        }

        onbttagcompound.a("Items", (ONBTBase) onbttaglist);
    }

    public int a() {
        return 64;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.k.b(this.l, this.m, this.n) != this ? false : oentityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void h() {
        super.h();
        this.a = false;
    }

    public void i() {
        if (!this.a) {
            this.a = true;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            if (this.k.a(this.l - 1, this.m, this.n) == OBlock.au.bO) {
                this.d = (OTileEntityChest) this.k.b(this.l - 1, this.m, this.n);
            }

            if (this.k.a(this.l + 1, this.m, this.n) == OBlock.au.bO) {
                this.c = (OTileEntityChest) this.k.b(this.l + 1, this.m, this.n);
            }

            if (this.k.a(this.l, this.m, this.n - 1) == OBlock.au.bO) {
                this.b = (OTileEntityChest) this.k.b(this.l, this.m, this.n - 1);
            }

            if (this.k.a(this.l, this.m, this.n + 1) == OBlock.au.bO) {
                this.e = (OTileEntityChest) this.k.b(this.l, this.m, this.n + 1);
            }

            if (this.b != null) {
                this.b.h();
            }

            if (this.e != null) {
                this.e.h();
            }

            if (this.c != null) {
                this.c.h();
            }

            if (this.d != null) {
                this.d.h();
            }

        }
    }

    public void q_() {
        super.q_();
        this.i();
        if (++this.j % 20 * 4 == 0) {
            this.k.e(this.l, this.m, this.n, 1, this.h);
        }

        this.g = this.f;
        float f = 0.1F;
        double d0;

        if (this.h > 0 && this.f == 0.0F && this.b == null && this.d == null) {
            double d1 = (double) this.l + 0.5D;

            d0 = (double) this.n + 0.5D;
            if (this.e != null) {
                d0 += 0.5D;
            }

            if (this.c != null) {
                d1 += 0.5D;
            }

            this.k.a(d1, (double) this.m + 0.5D, d0, "random.chestopen", 0.5F, this.k.r.nextFloat() * 0.1F + 0.9F);
        }

        if (this.h == 0 && this.f > 0.0F || this.h > 0 && this.f < 1.0F) {
            float f1 = this.f;

            if (this.h > 0) {
                this.f += f;
            } else {
                this.f -= f;
            }

            if (this.f > 1.0F) {
                this.f = 1.0F;
            }

            float f2 = 0.5F;

            if (this.f < f2 && f1 >= f2 && this.b == null && this.d == null) {
                d0 = (double) this.l + 0.5D;
                double d2 = (double) this.n + 0.5D;

                if (this.e != null) {
                    d2 += 0.5D;
                }

                if (this.c != null) {
                    d0 += 0.5D;
                }

                this.k.a(d0, (double) this.m + 0.5D, d2, "random.chestclosed", 0.5F, this.k.r.nextFloat() * 0.1F + 0.9F);
            }

            if (this.f < 0.0F) {
                this.f = 0.0F;
            }
        }

    }

    public void b(int i, int j) {
        if (i == 1) {
            this.h = j;
        }

    }

    public void f() {
        ++this.h;
        this.k.e(this.l, this.m, this.n, 1, this.h);
    }

    public void g() {
        --this.h;
        this.k.e(this.l, this.m, this.n, 1, this.h);
    }

    public void j() {
        this.h();
        this.i();
        super.j();
    }
   
    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(i, getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        i = Arrays.copyOf(aoitemstack, getContentsSize());
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
