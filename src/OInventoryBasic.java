import java.util.Arrays;
import java.util.List;

public class OInventoryBasic implements OIInventory, Container<OItemStack> {

    private String a;
    private int b;
    protected OItemStack[] c; //CanaryMod: private -> protected
    private List d;

    public OInventoryBasic(String s, int i) {
        this.a = s;
        this.b = i;
        this.c = new OItemStack[i];
    }

    public OItemStack a(int i) {
        return this.c[i];
    }

    public OItemStack a(int i, int j) {
        if (this.c[i] != null) {
            OItemStack oitemstack;

            if (this.c[i].a <= j) {
                oitemstack = this.c[i];
                this.c[i] = null;
                this.d();
                return oitemstack;
            } else {
                oitemstack = this.c[i].a(j);
                if (this.c[i].a == 0) {
                    this.c[i] = null;
                }

                this.d();
                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack a_(int i) {
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
        if (oitemstack != null && oitemstack.a > this.c()) {
            oitemstack.a = this.c();
        }

        this.d();
    }

    public int k_() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public int c() {
        return 64;
    }

    public void d() {
        if (this.d != null) {
            for (int i = 0; i < this.d.size(); ++i) {
                ((OIInvBasic) this.d.get(i)).a(this);
            }
        }
    }

    public boolean a_(OEntityPlayer oentityplayer) {
        return true;
    }

    public void l_() {}

    public void f() {}

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
        return this.a(i);
    }

    @Override
    public void setContentsAt(int i, OItemStack oitemstack) {
        this.a(i, oitemstack);
    }

    @Override
    public int getContentsSize() {
        return this.k_();
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
