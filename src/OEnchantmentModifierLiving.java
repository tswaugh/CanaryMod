
final class OEnchantmentModifierLiving implements OIEnchantmentModifier {

    public int a;
    public OEntityLiving b;

    private OEnchantmentModifierLiving() {}

    public void a(OEnchantment oenchantment, int i) {
        this.a += oenchantment.a(i, this.b);
    }

    OEnchantmentModifierLiving(OEmpty3 oempty3) {
        this();
    }
}
