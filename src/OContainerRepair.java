import java.util.Iterator;
import java.util.Map;

public class OContainerRepair extends OContainer {

    private OIInventory f = new OInventoryCraftResult();
    private OIInventory g = new OInventoryRepair(this, "Repair", 2);
    private OWorld h;
    private int i;
    private int j;
    private int k;
    public int a = 0;
    private int l = 0;
    private String m;
    private final OEntityPlayer n;

    public OContainerRepair(OInventoryPlayer oinventoryplayer, OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {
        this.h = oworld;
        this.i = i;
        this.j = j;
        this.k = k;
        this.n = oentityplayer;
        this.a(new OSlot(this.g, 0, 27, 47));
        this.a(new OSlot(this.g, 1, 76, 47));
        this.a((OSlot) (new OSlotRepair(this, this.f, 2, 134, 47, oworld, i, j, k)));

        int l;

        for (l = 0; l < 3; ++l) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.a(new OSlot(oinventoryplayer, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (l = 0; l < 9; ++l) {
            this.a(new OSlot(oinventoryplayer, l, 8 + l * 18, 142));
        }
    }

    public void a(OIInventory oiinventory) {
        super.a(oiinventory);
        if (oiinventory == this.g) {
            this.d();
        }
    }

    public void d() {
        OItemStack oitemstack = this.g.a(0);

        this.a = 0;
        int i = 0;
        byte b0 = 0;
        int j = 0;

        if (oitemstack == null) {
            this.f.a(0, (OItemStack) null);
            this.a = 0;
        } else {
            OItemStack oitemstack1 = oitemstack.l();
            OItemStack oitemstack2 = this.g.a(1);
            Map map = OEnchantmentHelper.a(oitemstack1);
            int k = b0 + oitemstack.A() + (oitemstack2 == null ? 0 : oitemstack2.A());

            this.l = 0;
            int l;
            int i1;
            int j1;
            int k1;
            OEnchantment oenchantment;
            Iterator iterator;

            if (oitemstack2 != null) {
                if (oitemstack1.f() && OItem.e[oitemstack1.c].a(oitemstack, oitemstack2)) {
                    l = Math.min(oitemstack1.i(), oitemstack1.k() / 4);
                    if (l <= 0) {
                        this.f.a(0, (OItemStack) null);
                        this.a = 0;
                        return;
                    }

                    for (i1 = 0; l > 0 && i1 < oitemstack2.a; ++i1) {
                        j1 = oitemstack1.i() - l;
                        oitemstack1.b(j1);
                        i += Math.max(1, l / 100) + map.size();
                        l = Math.min(oitemstack1.i(), oitemstack1.k() / 4);
                    }

                    this.l = i1;
                } else {
                    if (oitemstack1.c != oitemstack2.c || !oitemstack1.f()) {
                        this.f.a(0, (OItemStack) null);
                        this.a = 0;
                        return;
                    }

                    if (oitemstack1.f()) {
                        l = oitemstack.k() - oitemstack.i();
                        i1 = oitemstack2.k() - oitemstack2.i();
                        j1 = i1 + oitemstack1.k() * 12 / 100;
                        int l1 = l + j1;

                        k1 = oitemstack1.k() - l1;
                        if (k1 < 0) {
                            k1 = 0;
                        }

                        if (k1 < oitemstack1.j()) {
                            oitemstack1.b(k1);
                            i += Math.max(1, j1 / 100);
                        }
                    }

                    Map map1 = OEnchantmentHelper.a(oitemstack2);

                    iterator = map1.keySet().iterator();

                    while (iterator.hasNext()) {
                        j1 = ((Integer) iterator.next()).intValue();
                        oenchantment = OEnchantment.b[j1];
                        k1 = map.containsKey(Integer.valueOf(j1)) ? ((Integer) map.get(Integer.valueOf(j1))).intValue() : 0;
                        int i2 = ((Integer) map1.get(Integer.valueOf(j1))).intValue();
                        int j2;

                        if (k1 == i2) {
                            ++i2;
                            j2 = i2;
                        } else {
                            j2 = Math.max(i2, k1);
                        }

                        i2 = j2;
                        int k2 = i2 - k1;
                        boolean flag = true;
                        Iterator iterator1 = map.keySet().iterator();

                        while (iterator1.hasNext()) {
                            int l2 = ((Integer) iterator1.next()).intValue();

                            if (l2 != j1 && !oenchantment.a(OEnchantment.b[l2])) {
                                flag = false;
                                i += k2;
                            }
                        }

                        if (flag) {
                            if (i2 > oenchantment.b()) {
                                i2 = oenchantment.b();
                            }

                            map.put(Integer.valueOf(j1), Integer.valueOf(i2));
                            byte b1 = 0;

                            switch (oenchantment.c()) {
                                case 1:
                                    b1 = 8;
                                    break;

                                case 2:
                                    b1 = 4;

                                case 3:
                                case 4:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                default:
                                    break;

                                case 5:
                                    b1 = 2;
                                    break;

                                case 10:
                                    b1 = 1;
                            }

                            i += b1 * k2;
                        }
                    }
                }
            }

            if (this.m != null && !this.m.equalsIgnoreCase(oitemstack.r()) && this.m.length() > 0) {
                j = oitemstack.f() ? 7 : oitemstack.a * 5;
                i += j;
                if (oitemstack.s()) {
                    k += j / 2;
                }

                oitemstack1.c(this.m);
            }

            l = 0;

            byte b2;

            for (iterator = map.keySet().iterator(); iterator.hasNext(); k += l + k1 * b2) {
                j1 = ((Integer) iterator.next()).intValue();
                oenchantment = OEnchantment.b[j1];
                k1 = ((Integer) map.get(Integer.valueOf(j1))).intValue();
                b2 = 0;
                ++l;
                switch (oenchantment.c()) {
                    case 1:
                        b2 = 8;
                        break;

                    case 2:
                        b2 = 4;

                    case 3:
                    case 4:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    default:
                        break;

                    case 5:
                        b2 = 2;
                        break;

                    case 10:
                        b2 = 1;
                }
            }

            this.a = k + i;
            if (i <= 0) {
                oitemstack1 = null;
            }

            if (j == i && j > 0 && this.a >= 40) {
                // System.out.println("Naming an item only, cost too high; giving discount to cap cost to 39 levels"); // CanaryMod: disable debug message
                this.a = 39;
            }

            if (this.a >= 40 && !this.n.cc.d) {
                oitemstack1 = null;
            }

            if (oitemstack1 != null) {
                i1 = oitemstack1.A();
                if (oitemstack2 != null && i1 < oitemstack2.A()) {
                    i1 = oitemstack2.A();
                }

                if (oitemstack1.s()) {
                    i1 -= 9;
                }

                if (i1 < 0) {
                    i1 = 0;
                }

                i1 += 2;
                oitemstack1.c(i1);
                OEnchantmentHelper.a(map, oitemstack1);
            }

            this.f.a(0, oitemstack1);
            this.b();
        }
    }

    public void a(OICrafting oicrafting) {
        super.a(oicrafting);
        oicrafting.a(this, 0, this.a);
    }

    public void b(OEntityPlayer oentityplayer) {
        super.b(oentityplayer);
        if (!this.h.J) {
            for (int i = 0; i < this.g.k_(); ++i) {
                OItemStack oitemstack = this.g.a_(i);

                if (oitemstack != null) {
                    oentityplayer.c(oitemstack);
                }
            }
        }
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.h.a(this.i, this.j, this.k) != OBlock.ck.cm ? false : oentityplayer.e((double) this.i + 0.5D, (double) this.j + 0.5D, (double) this.k + 0.5D) <= 64.0D;
    }

    public OItemStack b(OEntityPlayer oentityplayer, int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.c.get(i);

        if (oslot != null && oslot.d()) {
            OItemStack oitemstack1 = oslot.c();

            oitemstack = oitemstack1.l();
            if (i == 2) {
                if (!this.a(oitemstack1, 3, 39, true)) {
                    return null;
                }

                oslot.a(oitemstack1, oitemstack);
            } else if (i != 0 && i != 1) {
                if (i >= 3 && i < 39 && !this.a(oitemstack1, 0, 2, false)) {
                    return null;
                }
            } else if (!this.a(oitemstack1, 3, 39, false)) {
                return null;
            }

            if (oitemstack1.a == 0) {
                oslot.c((OItemStack) null);
            } else {
                oslot.e();
            }

            if (oitemstack1.a == oitemstack.a) {
                return null;
            }

            oslot.a(oentityplayer, oitemstack1);
        }

        return oitemstack;
    }

    public void a(String s) {
        this.m = s;
        if (this.a(2).d()) {
            this.a(2).c().c(this.m);
        }

        this.d();
    }

    static OIInventory a(OContainerRepair ocontainerrepair) {
        return ocontainerrepair.g;
    }

    static int b(OContainerRepair ocontainerrepair) {
        return ocontainerrepair.l;
    }

    // CanaryMod start
    public String getToolName() {
        return this.m;
    }

    OEntityPlayer getPlayer() {
        return this.n;
    }

    OInventoryCraftResult getCraftResult() {
        return (OInventoryCraftResult) this.f;
    }
    // CanaryMod end
}
