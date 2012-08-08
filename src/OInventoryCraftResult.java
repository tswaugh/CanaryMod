
public class OInventoryCraftResult implements OIInventory, Container<OItemStack> {

    private OItemStack[] a = new OItemStack[1];
    // CanaryMod
    private String name = "Result";

    public OInventoryCraftResult() {
        super();
    }

    public int c() {
        return 1;
    }

    public OItemStack g_(int i) {
        return this.a[i];
    }

    public String e() {
        return name;
    }

    public OItemStack a(int i, int j) {
        if (this.a[i] != null) {
            OItemStack oitemstack = this.a[i];

            this.a[i] = null;
            return oitemstack;
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

    public void a(int i, OItemStack oitemstack) {
        this.a[i] = oitemstack;
    }

    public int a() {
        return 64;
    }

    public void G_() {}

    public boolean a(OEntityPlayer oentityplayer) {
        return true;
    }

    public void f() {}

    public void g() {}
    
    @Override
    public OItemStack[] getContents() {
        int size = getContentsSize();
        OItemStack[] result = new OItemStack[size];

        for (int i = 0; i < size; i++) {
            result[i] = getContentsAt(i);
        }
        return result;
    }

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        int size = getContentsSize();

        for (int i = 0; i < size; i++) {
            setContentsAt(i, aoitemstack[i]);
        }
    }

    @Override
    public OItemStack getContentsAt(int i) {
        return this.g_(i);
    }

    @Override
    public void setContentsAt(int i, OItemStack oitemstack) {
        this.a(i, oitemstack);
    }

    @Override
    public int getContentsSize() {
        return this.c();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String s) {
        this.name = s;
    }
}
