public class OEnchantmentDamage extends OEnchantment {

    //JarJar: begin fix
    private static final String[] A = new String[] { "all", "undead", "arthropods"};
    //JarJar: end fix
    private static final int[] B = new int[] { 1, 5, 5};
    private static final int[] C = new int[] { 11, 8, 8};
    private static final int[] D = new int[] { 20, 20, 20};
    public final int a;

    public OEnchantmentDamage(int i, int j, int k) {
        super(i, j, OEnumEnchantmentType.g);
        this.a = k;
    }

    public int a(int i) {
        return B[this.a] + (i - 1) * C[this.a];
    }

    public int b(int i) {
        return this.a(i) + D[this.a];
    }

    public int b() {
        return 5;
    }

    public int a(int i, OEntityLiving oentityliving) {
        return this.a == 0 ? i * 3 : (this.a == 1 && oentityliving.bz() == OEnumCreatureAttribute.b ? i * 4 : (this.a == 2 && oentityliving.bz() == OEnumCreatureAttribute.c ? i * 4 : 0));
    }

    public boolean a(OEnchantment oenchantment) {
        return !(oenchantment instanceof OEnchantmentDamage);
    }
}
