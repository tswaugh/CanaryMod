
final class OEnchantmentModifierDamage implements OIEnchantmentModifier {

    public int a;
    public ODamageSource b;

    private OEnchantmentModifierDamage() {
        super();
    }

    public void a(OEnchantment oenchantment, int i) {
        this.a += oenchantment.a(i, this.b);
    }

    // $FF: synthetic method
    OEnchantmentModifierDamage(OEmpty1 oempty1) {
        this();
    }
}
