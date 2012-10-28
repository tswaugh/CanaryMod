public class OEnchantmentProtection extends OEnchantment {

    private static final String[] A = new String[] { "all", "fire", "fall", "explosion", "projectile"};
    private static final int[] B = new int[] { 1, 10, 5, 5, 3};
    private static final int[] C = new int[] { 11, 8, 6, 8, 6};
    private static final int[] D = new int[] { 20, 12, 10, 12, 15};
    public final int a;

    public OEnchantmentProtection(int i, int j, int k) {
        super(i, j, OEnumEnchantmentType.b);
        this.a = k;
        if (k == 2) {
            this.y = OEnumEnchantmentType.c;
        }
    }

    public int a(int i) {
        return B[this.a] + (i - 1) * C[this.a];
    }

    public int b(int i) {
        return this.a(i) + D[this.a];
    }

    public int b() {
        return 4;
    }

    public int a(int i, ODamageSource odamagesource) {
        if (odamagesource.e()) {
            return 0;
        } else {
            int j = (6 + i * i) / 2;

            return this.a == 0 ? j : (this.a == 1 && odamagesource.k() ? j : (this.a == 2 && odamagesource == ODamageSource.h ? j * 2 : ((this.a != 3 || odamagesource != ODamageSource.k) && odamagesource != ODamageSource.l ? (this.a == 4 && odamagesource.a() ? j : 0) : j)));
        }
    }

    public boolean a(OEnchantment oenchantment) {
        if (oenchantment instanceof OEnchantmentProtection) {
            OEnchantmentProtection oenchantmentprotection = (OEnchantmentProtection) oenchantment;

            return oenchantmentprotection.a == this.a ? false : this.a == 2 || oenchantmentprotection.a == 2;
        } else {
            return super.a(oenchantment);
        }
    }
}
