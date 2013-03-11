import java.util.Arrays;
import java.util.List;

public class OTileEntityBrewingStand extends OTileEntity implements OISidedInventory, Container<OItemStack> {

    private OItemStack[] a = new OItemStack[4];
    private int b;
    private int c;
    private int d;
    private String e;

    private final BrewingStand stand = new BrewingStand(this); // CanaryMod

    public OTileEntityBrewingStand() {}

    public String b() {
        return this.c() ? this.e : "container.brewing";
    }

    public boolean c() {
        return this.e != null && this.e.length() > 0;
    }

    public void a(String s) {
        this.e = s;
    }

    public int j_() {
        return this.a.length;
    }

    public void h() {
        if (this.b > 0) {
            --this.b;
            if (this.b == 0) {
                this.u();
                this.k_();
            } else if (!this.l()) {
                this.b = 0;
                this.k_();
            } else if (this.d != this.a[3].c) {
                this.b = 0;
                this.k_();
            }
        } else if (this.l()) {
            this.b = 400;
            this.d = this.a[3].c;
        }

        int i = this.j();

        if (i != this.c) {
            this.c = i;
            this.k.b(this.l, this.m, this.n, i, 2);
        }

        super.h();
    }

    public int x_() {
        return this.b;
    }

    private boolean l() {
        if (this.a[3] != null && this.a[3].a > 0) {
            OItemStack oitemstack = this.a[3];

            if (!OItem.f[oitemstack.c].w()) {
                return false;
            } else {
                boolean flag = false;

                for (int i = 0; i < 3; ++i) {
                    if (this.a[i] != null && this.a[i].c == OItem.bt.cp) {
                        int j = this.a[i].k();
                        int k = this.c(j, oitemstack);

                        if (!OItemPotion.f(j) && OItemPotion.f(k)) {
                            flag = true;
                            break;
                        }

                        List list = OItem.bt.c(j);
                        List list1 = OItem.bt.c(k);

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

    private void u() {
        if (this.l()) {
            OItemStack oitemstack = this.a[3];

            for (int i = 0; i < 3; ++i) {
                if (this.a[i] != null && this.a[i].c == OItem.bt.cp) {
                    int j = this.a[i].k();
                    int k = this.c(j, oitemstack);
                    List list = OItem.bt.c(j);
                    List list1 = OItem.bt.c(k);

                    if ((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null)) {
                        if (j != k) {
                            this.a[i].b(k);
                        }
                    } else if (!OItemPotion.f(j) && OItemPotion.f(k)) {
                        this.a[i].b(k);
                    }
                }
            }

            if (OItem.f[oitemstack.c].t()) {
                this.a[3] = new OItemStack(OItem.f[oitemstack.c].s());
            } else {
                --this.a[3].a;
                if (this.a[3].a <= 0) {
                    this.a[3] = null;
                }
            }
        }
    }

    private int c(int i, OItemStack oitemstack) {
        return oitemstack == null ? i : (OItem.f[oitemstack.c].w() ? OPotionHelper.a(i, OItem.f[oitemstack.c].v()) : i);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Items");

        this.a = new OItemStack[this.j_()];

        for (int i = 0; i < onbttaglist.c(); ++i) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
            byte b0 = onbttagcompound1.c("Slot");

            if (b0 >= 0 && b0 < this.a.length) {
                this.a[b0] = OItemStack.a(onbttagcompound1);
            }
        }

        this.b = onbttagcompound.d("BrewTime");
        if (onbttagcompound.b("CustomName")) {
            this.e = onbttagcompound.i("CustomName");
        }
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
        if (this.c()) {
            onbttagcompound.a("CustomName", this.e);
        }
    }

    public OItemStack a(int i) {
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

    public int d() {
        return 64;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.k.r(this.l, this.m, this.n) != this ? false : oentityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i, OItemStack oitemstack) {
        return i == 3 ? OItem.f[oitemstack.c].w() : oitemstack.c == OItem.bt.cp || oitemstack.c == OItem.bu.cp;
    }

    public int j() {
        int i = 0;

        for (int j = 0; j < 3; ++j) {
            if (this.a[j] != null) {
                i |= 1 << j;
            }
        }

        return i;
    }

    public int c(int i) {
        return i == 1 ? 3 : 0;
    }

    public int d(int i) {
        return i == 1 ? 1 : 3;
    }

    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(this.a, this.getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        this.a = Arrays.copyOf(aoitemstack, this.getContentsSize());
    }

    @Override
    public OItemStack getContentsAt(int i) {
        return this.a(i);
    }

    @Override
    public void setContentsAt(int i, OItemStack oitemstack) {
        this.a(i, oitemstack);
    }

    @Override
    public int getContentsSize() {
        return this.j_();
    }

    @Override
    public String getName() {
        return this.b();
    }

    @Override
    public void setName(String s) {
        this.a(s);
    }

    @Override
    public BrewingStand getComplexBlock() {
        return stand;
    }
}
