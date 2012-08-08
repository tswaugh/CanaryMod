import java.util.Arrays;
import java.util.List;


public class OInventoryBasic implements OIInventory, Container<OItemStack> {

    private String a;
    private int b;
    private OItemStack[] c;
    private List d;

    public OInventoryBasic(String s, int i) {
        super();
        this.a = s;
        this.b = i;
        this.c = new OItemStack[i];
    }

    public OItemStack g_(int i) {
        return this.c[i];
    }

    public OItemStack a(int i, int j) {
        if (this.c[i] != null) {
            OItemStack oitemstack;

            if (this.c[i].a <= j) {
                oitemstack = this.c[i];
                this.c[i] = null;
                this.G_();
                return oitemstack;
            } else {
                oitemstack = this.c[i].a(j);
                if (this.c[i].a == 0) {
                    this.c[i] = null;
                }

                this.G_();
                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        if (this.c[i] != null) {
            OItemStack oitemstack = this.c[i];

            this.c[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        this.c[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.a()) {
            oitemstack.a = this.a();
        }

        this.G_();
    }

    public int c() {
        return this.b;
    }

    public String e() {
        return this.a;
    }

    public int a() {
        return 64;
    }

    public void G_() {
        if (this.d != null) {
            for (int i = 0; i < this.d.size(); ++i) {
                ((OIInvBasic) this.d.get(i)).a(this);
            }
        }

    }

    public boolean a(OEntityPlayer oentityplayer) {
        return true;
    }

    public void f() {}

    public void g() {}
    
    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(this.c, getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        this.c = Arrays.copyOf(aoitemstack, getContentsSize());
    }

    @Override
    public OItemStack getContentsAt(int i) {
        return this.g_(i);
    }

    @Override
    public void setContentsAt(int i, OItemStack oitemstack) {
        this.a(b, oitemstack);
    }

    @Override
    public int getContentsSize() {
        return this.c();
    }

    @Override
    public String getName() {
        return this.a;
    }

    @Override
    public void setName(String s) {
        this.a = s;
    }
}
