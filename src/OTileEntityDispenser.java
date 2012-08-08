import java.util.Arrays;
import java.util.Random;


public class OTileEntityDispenser extends OTileEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] a = new OItemStack[9];
    private Random b = new Random();
    private String name = "container.dispenser";

    public OTileEntityDispenser() {
        super();
    }

    public int c() {
        return 9;
    }

    public OItemStack g_(int i) {
        return this.a[i];
    }

    public OItemStack a(int i, int j) {
        if (this.a[i] != null) {
            OItemStack oitemstack;

            if (this.a[i].a <= j) {
                oitemstack = this.a[i];
                this.a[i] = null;
                this.G_();
                return oitemstack;
            } else {
                oitemstack = this.a[i].a(j);
                if (this.a[i].a == 0) {
                    this.a[i] = null;
                }

                this.G_();
                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        if (this.a[i] != null) {
            OItemStack oitemstack = this.a[i];

            this.a[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public OItemStack p_() {
        int i = -1;
        int j = 1;

        for (int k = 0; k < this.a.length; ++k) {
            if (this.a[k] != null && this.b.nextInt(j++) == 0) {
                i = k;
            }
        }

        if (i >= 0) {
            return this.a(i, 1);
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        this.a[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.a()) {
            oitemstack.a = this.a();
        }

        this.G_();
    }

    public String e() {
        return "Trap";
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.n("Items");

        this.a = new OItemStack[this.c()];

        for (int i = 0; i < onbttaglist.d(); ++i) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.a(i);
            int j = onbttagcompound1.d("Slot") & 255;

            if (j >= 0 && j < this.a.length) {
                this.a[j] = OItemStack.a(onbttagcompound1);
            }
        }

    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
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

    public int a() {
        return 64;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.k.b(this.l, this.m, this.n) != this ? false : oentityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void f() {}

    public void g() {}
   
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
