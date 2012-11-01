public enum OEnumEnchantmentType {

    a("all", 0), b("armor", 1), c("armor_feet", 2), d("armor_legs", 3), e("armor_torso", 4), f("armor_head", 5), g("weapon", 6), h("digger", 7), i("bow", 8);

    private static final OEnumEnchantmentType[] j = new OEnumEnchantmentType[] { a, b, c, d, e, f, g, h, i};

    private OEnumEnchantmentType(String s, int i) {}

    public boolean a(OItem oitem) {
        if (this == a) {
            return true;
        } else if (oitem instanceof OItemArmor) {
            if (this == b) {
                return true;
            } else {
                OItemArmor oitemarmor = (OItemArmor) oitem;

                return oitemarmor.a == 0 ? this == f : (oitemarmor.a == 2 ? this == d : (oitemarmor.a == 1 ? this == e : (oitemarmor.a == 3 ? this == c : false)));
            }
        } else {
            return oitem instanceof OItemSword ? this == g : (oitem instanceof OItemTool ? this == h : (oitem instanceof OItemBow ? this == i : false));
        }
    }
}
