import java.util.Arrays;
import java.util.List;


public class OTileEntityBrewingStand extends OTileEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] a = new OItemStack[4];
    private int b;
    private int c;
    private int d;
    
    private String name = "container.brewing"; // CanaryMod

    public OTileEntityBrewingStand() {
        super();
    }

    public String e() {
        return name;
    }

    public int c() {
        return this.a.length;
    }

    public void q_() {
        if (this.b > 0) {
            --this.b;
            if (this.b == 0) {
                this.p();
                this.G_();
            } else if (!this.o()) {
                this.b = 0;
                this.G_();
            } else if (this.d != this.a[3].c) {
                this.b = 0;
                this.G_();
            }
        } else if (this.o()) {
            this.b = 400;
            this.d = this.a[3].c;
        }

        int i = this.n();

        if (i != this.c) {
            this.c = i;
            this.k.c(this.l, this.m, this.n, i);
        }

        super.q_();
    }

    public int i() {
        return this.b;
    }

    private boolean o() {
        if (this.a[3] != null && this.a[3].a > 0) {
            OItemStack oitemstack = this.a[3];

            if (!OItem.d[oitemstack.c].n()) {
                return false;
            } else {
                boolean flag = false;

                for (int i = 0; i < 3; ++i) {
                    if (this.a[i] != null && this.a[i].c == OItem.br.bP) {
                        int j = this.a[i].h();
                        int k = this.b(j, oitemstack);

                        if (!OItemPotion.c(j) && OItemPotion.c(k)) {
                            flag = true;
                            break;
                        }

                        List list = OItem.br.b(j);
                        List list1 = OItem.br.b(k);

                        if ((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null) && j != k) {
                            flag = true;
                            break;
                        }
                    }
                }

                return flag;
            }
        } else {
            return false;
        }
    }

    private void p() {
        if (this.o()) {
            OItemStack oitemstack = this.a[3];

            for (int i = 0; i < 3; ++i) {
                if (this.a[i] != null && this.a[i].c == OItem.br.bP) {
                    int j = this.a[i].h();
                    int k = this.b(j, oitemstack);
                    List list = OItem.br.b(j);
                    List list1 = OItem.br.b(k);

                    if ((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null)) {
                        if (j != k) {
                            this.a[i].b(k);
                        }
                    } else if (!OItemPotion.c(j) && OItemPotion.c(k)) {
                        this.a[i].b(k);
                    }
                }
            }

            if (OItem.d[oitemstack.c].k()) {
                this.a[3] = new OItemStack(OItem.d[oitemstack.c].j());
            } else {
                --this.a[3].a;
                if (this.a[3].a <= 0) {
                    this.a[3] = null;
                }
            }

        }
    }

    private int b(int i, OItemStack oitemstack) {
        return oitemstack == null ? i : (OItem.d[oitemstack.c].n() ? OPotionHelper.a(i, OItem.d[oitemstack.c].m()) : i);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.n("Items");

        this.a = new OItemStack[this.c()];

        for (int i = 0; i < onbttaglist.d(); ++i) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.a(i);
            byte b0 = onbttagcompound1.d("Slot");

            if (b0 >= 0 && b0 < this.a.length) {
                this.a[b0] = OItemStack.a(onbttagcompound1);
            }
        }

        this.b = onbttagcompound.e("BrewTime");
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("BrewTime", (short) this.b);
        ONBTTagList onbttaglist = new ONBTTagList();

        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

                onbttagcompound1.a("Slot", (byte) i);
                this.a[i].b(onbttagcompound1);
                onbttaglist.a((ONBTBase) onbttagcompound1);
            }
        }

        onbttagcompound.a("Items", (ONBTBase) onbttaglist);
    }

    public OItemStack g_(int i) {
        return i >= 0 && i < this.a.length ? this.a[i] : null;
    }

    public OItemStack a(int i, int j) {
        if (i >= 0 && i < this.a.length) {
            OItemStack oitemstack = this.a[i];

            this.a[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        if (i >= 0 && i < this.a.length) {
            OItemStack oitemstack = this.a[i];

            this.a[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        if (i >= 0 && i < this.a.length) {
            this.a[i] = oitemstack;
        }

    }

    public int a() {
        return 1;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.k.b(this.l, this.m, this.n) != this ? false : oentityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void f() {}

    public void g() {}

    public int n() {
        int i = 0;

        for (int j = 0; j < 3; ++j) {
            if (this.a[j] != null) {
                i |= 1 << j;
            }
        }

        return i;
    }

    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(a, getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        a = Arrays.copyOf(aoitemstack, getContentsSize());
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
