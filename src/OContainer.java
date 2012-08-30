
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public abstract class OContainer {

    public List a = new ArrayList();
    public List b = new ArrayList();
    public int c = 0;
    private short e = 0;
    protected List d = new ArrayList();
    private Set f = new HashSet();
    // CanaryMod: inventory - Used to know which inventory was passed to this container GUI.
    private Inventory inventory;

    public OContainer() {
        super();
    }

    protected OSlot a(OSlot oslot) {
        oslot.d = this.b.size();
        this.b.add(oslot);
        this.a.add(null);
        return oslot;
    }

    public void a(OICrafting oicrafting) {
        if (this.d.contains(oicrafting)) {
            throw new IllegalArgumentException("Listener already listening");
        } else {
            this.d.add(oicrafting);
            oicrafting.a(this, this.a());
            this.b();
        }
    }

    public List a() {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            OSlot oslot = (OSlot) iterator.next();

            arraylist.add(oslot.c());
        }

        return arraylist;
    }

    public void b() {
        for (int i = 0; i < this.b.size(); ++i) {
            OItemStack oitemstack = ((OSlot) this.b.get(i)).c();
            OItemStack oitemstack1 = (OItemStack) this.a.get(i);

            if (!OItemStack.b(oitemstack1, oitemstack)) {
                oitemstack1 = oitemstack == null ? null : oitemstack.l();
                this.a.set(i, oitemstack1);
                Iterator iterator = this.d.iterator();

                while (iterator.hasNext()) {
                    OICrafting oicrafting = (OICrafting) iterator.next();

                    oicrafting.a(this, i, oitemstack1);
                }
            }
        }

    }

    public boolean a(OEntityPlayer oentityplayer, int i) {
        return false;
    }

    public OSlot a(OIInventory oiinventory, int i) {
        Iterator iterator = this.b.iterator();

        OSlot oslot;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            oslot = (OSlot) iterator.next();
        } while (!oslot.a(oiinventory, i));

        return oslot;
    }

    public OSlot a(int i) {
        return (OSlot) this.b.get(i);
    }

    public OItemStack b(int i) {
        OSlot oslot = (OSlot) this.b.get(i);

        return oslot != null ? oslot.c() : null;
    }

    public OItemStack a(int i, int j, boolean flag, OEntityPlayer oentityplayer) {
        OItemStack oitemstack = null;

        if (j > 1) {
            return null;
        } else {
            if (j == 0 || j == 1) {
                OInventoryPlayer oinventoryplayer = oentityplayer.by;

                if (i == -999) {
                    if (oinventoryplayer.o() != null && i == -999) {
                        if (j == 0) {
                            oentityplayer.b(oinventoryplayer.o());
                            oinventoryplayer.b((OItemStack) null);
                        }

                        if (j == 1) {
                            oentityplayer.b(oinventoryplayer.o().a(1));
                            if (oinventoryplayer.o().a == 0) {
                                oinventoryplayer.b((OItemStack) null);
                            }
                        }
                    }
                } else if (flag) {
                    OItemStack oitemstack1 = this.b(i);

                    if (oitemstack1 != null) {
                        int k = oitemstack1.c;

                        oitemstack = oitemstack1.l();
                        OSlot oslot = (OSlot) this.b.get(i);

                        if (oslot != null && oslot.c() != null && oslot.c().c == k) {
                            this.b(i, j, flag, oentityplayer);
                        }
                    }
                } else {
                    if (i < 0) {
                        return null;
                    }

                    OSlot oslot1 = (OSlot) this.b.get(i);

                    if (oslot1 != null) {
                        OItemStack oitemstack2 = oslot1.c();
                        OItemStack oitemstack3 = oinventoryplayer.o();

                        if (oitemstack2 != null) {
                            oitemstack = oitemstack2.l();
                        }

                        int l;

                        if (oitemstack2 == null) {
                            if (oitemstack3 != null && oslot1.a(oitemstack3)) {
                                l = j == 0 ? oitemstack3.a : 1;
                                if (l > oslot1.a()) {
                                    l = oslot1.a();
                                }

                                oslot1.d(oitemstack3.a(l));
                                if (oitemstack3.a == 0) {
                                    oinventoryplayer.b((OItemStack) null);
                                }
                            }
                        } else if (oitemstack3 == null) {
                            l = j == 0 ? oitemstack2.a : (oitemstack2.a + 1) / 2;
                            OItemStack oitemstack4 = oslot1.a(l);

                            oinventoryplayer.b(oitemstack4);
                            if (oitemstack2.a == 0) {
                                oslot1.d((OItemStack) null);
                            }

                            oslot1.b(oinventoryplayer.o());
                        } else if (oslot1.a(oitemstack3)) {
                            if (oitemstack2.c == oitemstack3.c && (!oitemstack2.g() || oitemstack2.j() == oitemstack3.j()) && OItemStack.a(oitemstack2, oitemstack3)) {
                                l = j == 0 ? oitemstack3.a : 1;
                                if (l > oslot1.a() - oitemstack2.a) {
                                    l = oslot1.a() - oitemstack2.a;
                                }

                                if (l > oitemstack3.d() - oitemstack2.a) {
                                    l = oitemstack3.d() - oitemstack2.a;
                                }

                                oitemstack3.a(l);
                                if (oitemstack3.a == 0) {
                                    oinventoryplayer.b((OItemStack) null);
                                }

                                oitemstack2.a += l;
                            } else if (oitemstack3.a <= oslot1.a()) {
                                oslot1.d(oitemstack3);
                                oinventoryplayer.b(oitemstack2);
                            }
                        } else if (oitemstack2.c == oitemstack3.c && oitemstack3.d() > 1 && (!oitemstack2.g() || oitemstack2.j() == oitemstack3.j()) && OItemStack.a(oitemstack2, oitemstack3)) {
                            l = oitemstack2.a;
                            if (l > 0 && l + oitemstack3.a <= oitemstack3.d()) {
                                oitemstack3.a += l;
                                oitemstack2 = oslot1.a(l);
                                if (oitemstack2.a == 0) {
                                    oslot1.d((OItemStack) null);
                                }

                                oslot1.b(oinventoryplayer.o());
                            }
                        }

                        oslot1.e();
                    }
                }
            }

            return oitemstack;
        }
    }

    protected void b(int i, int j, boolean flag, OEntityPlayer oentityplayer) {
        this.a(i, j, flag, oentityplayer);
    }

    public void a(OEntityPlayer oentityplayer) {
        // CanaryMod: onCloseInventory
        if (oentityplayer instanceof OEntityPlayerMP) {
            HookParametersCloseInventory closeInventoryParameters = new HookParametersCloseInventory(((OEntityPlayerMP) oentityplayer).getPlayer(), this.inventory, false);

            etc.getLoader().callHook(PluginLoader.Hook.CLOSE_INVENTORY, closeInventoryParameters);
        }

        OInventoryPlayer oinventoryplayer = oentityplayer.by;

        if (oinventoryplayer.o() != null) {
            oentityplayer.b(oinventoryplayer.o());
            oinventoryplayer.b((OItemStack) null);
        }
    }

    public void a(OIInventory oiinventory) {
        this.b();
    }

    public void a(int i, OItemStack oitemstack) {
        this.a(i).d(oitemstack);
    }

    public boolean b(OEntityPlayer oentityplayer) {
        return !this.f.contains(oentityplayer);
    }

    public void a(OEntityPlayer oentityplayer, boolean flag) {
        if (flag) {
            this.f.remove(oentityplayer);
        } else {
            this.f.add(oentityplayer);
        }

    }

    public abstract boolean c(OEntityPlayer oentityplayer);

    protected boolean a(OItemStack oitemstack, int i, int j, boolean flag) {
        boolean flag1 = false;
        int k = i;

        if (flag) {
            k = j - 1;
        }

        OSlot oslot;
        OItemStack oitemstack1;

        if (oitemstack.e()) {
            while (oitemstack.a > 0 && (!flag && k < j || flag && k >= i)) {
                oslot = (OSlot) this.b.get(k);
                oitemstack1 = oslot.c();
                if (oitemstack1 != null && oitemstack1.c == oitemstack.c && (!oitemstack.g() || oitemstack.j() == oitemstack1.j()) && OItemStack.a(oitemstack, oitemstack1)) {
                    int l = oitemstack1.a + oitemstack.a;

                    if (l <= oitemstack.d()) {
                        oitemstack.a = 0;
                        oitemstack1.a = l;
                        oslot.e();
                        flag1 = true;
                    } else if (oitemstack1.a < oitemstack.d()) {
                        oitemstack.a -= oitemstack.d() - oitemstack1.a;
                        oitemstack1.a = oitemstack.d();
                        oslot.e();
                        flag1 = true;
                    }
                }

                if (flag) {
                    --k;
                } else {
                    ++k;
                }
            }
        }

        if (oitemstack.a > 0) {
            if (flag) {
                k = j - 1;
            } else {
                k = i;
            }

            while (!flag && k < j || flag && k >= i) {
                oslot = (OSlot) this.b.get(k);
                oitemstack1 = oslot.c();
                if (oitemstack1 == null) {
                    oslot.d(oitemstack.l());
                    oslot.e();
                    oitemstack.a = 0;
                    flag1 = true;
                    break;
                }

                if (flag) {
                    --k;
                } else {
                    ++k;
                }
            }
        }

        return flag1;
    }

    // CanaryMod: get and set inventory passed to the GUI.
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
