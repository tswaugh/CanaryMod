import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class OContainerEnchantment extends OContainer {

    public OIInventory a = new OSlotEnchantmentTable(this, "Enchant", 1);
    private OWorld h;
    private int i;
    private int j;
    private int k;
    private Random l = new Random();
    public long b;
    public int[] c = new int[3];

    public OContainerEnchantment(OInventoryPlayer oinventoryplayer, OWorld oworld, int i, int j, int k) {
        super();
        this.h = oworld;
        this.i = i;
        this.j = j;
        this.k = k;
        this.a((OSlot) (new OSlotEnchantment(this, this.a, 0, 25, 47)));

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

    public void a(OICrafting oicrafting) {
        super.a(oicrafting);
        oicrafting.a(this, 0, this.c[0]);
        oicrafting.a(this, 1, this.c[1]);
        oicrafting.a(this, 2, this.c[2]);
    }

    public void a() {
        super.a();

        for (int i = 0; i < this.g.size(); ++i) {
            OICrafting oicrafting = (OICrafting) this.g.get(i);

            oicrafting.a(this, 0, this.c[0]);
            oicrafting.a(this, 1, this.c[1]);
            oicrafting.a(this, 2, this.c[2]);
        }

    }

    public void a(OIInventory oiinventory) {
        if (oiinventory == this.a) {
            OItemStack oitemstack = oiinventory.g_(0);
            int i;

            if (oitemstack != null && oitemstack.q()) {
                this.b = this.l.nextLong();
                if (!this.h.F) {
                    i = 0;

                    int j;

                    for (j = -1; j <= 1; ++j) {
                        for (int k = -1; k <= 1; ++k) {
                            if ((j != 0 || k != 0) && this.h.g(this.i + k, this.j, this.k + j) && this.h.g(this.i + k, this.j + 1, this.k + j)) {
                                if (this.h.a(this.i + k * 2, this.j, this.k + j * 2) == OBlock.an.bO) {
                                    ++i;
                                }

                                if (this.h.a(this.i + k * 2, this.j + 1, this.k + j * 2) == OBlock.an.bO) {
                                    ++i;
                                }

                                if (k != 0 && j != 0) {
                                    if (this.h.a(this.i + k * 2, this.j, this.k + j) == OBlock.an.bO) {
                                        ++i;
                                    }

                                    if (this.h.a(this.i + k * 2, this.j + 1, this.k + j) == OBlock.an.bO) {
                                        ++i;
                                    }

                                    if (this.h.a(this.i + k, this.j, this.k + j * 2) == OBlock.an.bO) {
                                        ++i;
                                    }

                                    if (this.h.a(this.i + k, this.j + 1, this.k + j * 2) == OBlock.an.bO) {
                                        ++i;
                                    }
                                }
                            }
                        }
                    }

                    for (j = 0; j < 3; ++j) {
                        this.c[j] = OEnchantmentHelper.a(this.l, j, i, oitemstack);
                    }

                    this.a();
                }
            } else {
                for (i = 0; i < 3; ++i) {
                    this.c[i] = 0;
                }
            }
        }

    }

    public boolean a(OEntityPlayer oentityplayer, int i) {
        OItemStack oitemstack = this.a.g_(0);

        if (this.c[i] > 0 && oitemstack != null && (oentityplayer.M >= this.c[i] || oentityplayer.L.d)) {
            if (!this.h.F) {
                List list = OEnchantmentHelper.b(this.l, oitemstack, this.c[i]);

                if (list != null) {
                    // CanaryMod hook: onEnchant
                    HookParametersEnchant enchantParameters = (HookParametersEnchant) etc.getLoader().callHook(PluginLoader.Hook.ENCHANT, new HookParametersEnchant(((OEntityPlayerMP) oentityplayer).getPlayer(), oitemstack.c, list));

                    if (!enchantParameters.isCanceled() && enchantParameters.isValid(false)) {
                        List<Enchantment> enchantments = enchantParameters.getEnchantments();

                        list = new ArrayList();
                        for (Enchantment enchantment : enchantments) {
                            list.add(new OEnchantmentData(enchantment.getEnchantment(), enchantment.getLevel()));
                        }
						
                        oentityplayer.e_(this.c[i]);
                        Iterator iterator = list.iterator();

                        while (iterator.hasNext()) {
                            OEnchantmentData oenchantmentdata = (OEnchantmentData) iterator.next();
	
                            oitemstack.a(oenchantmentdata.a, oenchantmentdata.b);
                        }
						
                        this.a(this.a);
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public void a(OEntityPlayer oentityplayer) {
        super.a(oentityplayer);
        if (!this.h.F) {
            OItemStack oitemstack = this.a.b(0);

            if (oitemstack != null) {
                oentityplayer.b(oitemstack);
            }

        }
    }

    public boolean b(OEntityPlayer oentityplayer) {
        return this.h.a(this.i, this.j, this.k) != OBlock.bE.bO ? false : oentityplayer.e((double) this.i + 0.5D, (double) this.j + 0.5D, (double) this.k + 0.5D) <= 64.0D;
    }

    public OItemStack a(int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.e.get(i);

        if (oslot != null && oslot.c()) {
            OItemStack oitemstack1 = oslot.b();

            oitemstack = oitemstack1.j();
            if (i != 0) {
                return null;
            }

            if (!this.a(oitemstack1, 1, 37, true)) {
                return null;
            }

            if (oitemstack1.a == 0) {
                oslot.d((OItemStack) null);
            } else {
                oslot.d();
            }

            if (oitemstack1.a == oitemstack.a) {
                return null;
            }

            oslot.c(oitemstack1);
        }

        return oitemstack;
    }
}
