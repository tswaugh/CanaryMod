
final class OEnchantmentModifierLiving implements OIEnchantmentModifier {

    public int a;
    public OEntityLiving b;

    private OEnchantmentModifierLiving() {
        super();
    }

    public void a(OEnchantment oenchantment, int i) {
        this.a += oenchantment.a(i, this.b);
    }

    // $FF: synthetic method
    OEnchantmentModifierLiving(OEmpty1 oempty1) {
        this();
    }
}
