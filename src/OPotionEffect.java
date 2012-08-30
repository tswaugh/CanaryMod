
public class OPotionEffect {

    // CanaryMod made public
    public int a;
    public int b;
    public int c;
    public boolean permanent = false;
    public PotionEffect potionEffect = new PotionEffect(this);

    public OPotionEffect(int i, int j, int k) {
        super();
        this.a = i;
        this.b = j;
        this.c = k;
    }

    public OPotionEffect(OPotionEffect opotioneffect) {
        super();
        this.a = opotioneffect.a;
        this.b = opotioneffect.b;
        this.c = opotioneffect.c;
    }

    public void a(OPotionEffect opotioneffect) {
        if (this.a != opotioneffect.a) {
            System.err.println("This method should only be called for matching effects!");
        }

        if (opotioneffect.c > this.c) {
            this.c = opotioneffect.c;
            this.b = opotioneffect.b;
        } else if (opotioneffect.c == this.c && this.b < opotioneffect.b) {
            this.b = opotioneffect.b;
        }

    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public boolean a(OEntityLiving oentityliving) {
        if (this.b > 0) {
            if (OPotion.a[this.a].a(this.b, this.c)) {
                this.b(oentityliving);
            }

            this.e();
        }

        return this.b > 0;
    }

    private int e() {
        return this.permanent ? this.b : --this.b;
    }

    public void b(OEntityLiving oentityliving) {
        if (this.b > 0) {
            OPotion.a[this.a].a(oentityliving, this.c);
        }

    }

    public String d() {
        return OPotion.a[this.a].a();
    }

    public int hashCode() {
        return this.a;
    }

    public String toString() {
        String s = "";

        if (this.c() > 0) {
            s = this.d() + " x " + (this.c() + 1) + ", Duration: " + this.b();
        } else {
            s = this.d() + ", Duration: " + this.b();
        }

        return OPotion.a[this.a].i() ? "(" + s + ")" : s;
    }

    public boolean equals(Object object) {
        if (!(object instanceof OPotionEffect)) {
            return false;
        } else {
            OPotionEffect opotioneffect = (OPotionEffect) object;

            return this.a == opotioneffect.a && this.c == opotioneffect.c && this.b == opotioneffect.b;
        }
    }
}
