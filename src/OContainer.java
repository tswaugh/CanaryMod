
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class OContainer {

    public List d = new ArrayList();
    public List e = new ArrayList();
    public int f = 0;
    private short a = 0;
    protected List g = new ArrayList();
    private Set b = new HashSet();
    // CanaryMod: inventory - Used to know which inventory was passed to this container GUI.
    private Inventory inventory;

    public OContainer() {
        super();
    }

    protected void a(OSlot oslot) {
        oslot.c = this.e.size();
        this.e.add(oslot);
        this.d.add((Object) null);
    }

    public void a(OICrafting oicrafting) {
        if (this.g.contains(oicrafting)) {
            throw new IllegalArgumentException("Listener already listening");
        } else {
            this.g.add(oicrafting);
            oicrafting.a(this, this.b());
            this.a();
        }
    }

    public List b() {
        ArrayList arraylist = new ArrayList();

        for (int i = 0; i < this.e.size(); ++i) {
            arraylist.add(((OSlot) this.e.get(i)).b());
        }

        return arraylist;
    }

    public void a() {
        for (int i = 0; i < this.e.size(); ++i) {
            OItemStack oitemstack = ((OSlot) this.e.get(i)).b();
            OItemStack oitemstack1 = (OItemStack) this.d.get(i);

            if (!OItemStack.b(oitemstack1, oitemstack)) {
                oitemstack1 = oitemstack == null ? null : oitemstack.j();
                this.d.set(i, oitemstack1);

                for (int j = 0; j < this.g.size(); ++j) {
                    ((OICrafting) this.g.get(j)).a(this, i, oitemstack1);
                }
            }
        }

    }

    public boolean a(OEntityPlayer oentityplayer, int i) {
        return false;
    }

    public OSlot a(OIInventory oiinventory, int i) {
        for (int j = 0; j < this.e.size(); ++j) {
            OSlot oslot = (OSlot) this.e.get(j);

            if (oslot.a(oiinventory, i)) {
                return oslot;
            }
        }

        return null;
    }

    public OSlot b(int i) {
        return (OSlot) this.e.get(i);
    }

    public OItemStack a(int i) {
        OSlot oslot = (OSlot) this.e.get(i);

        return oslot != null ? oslot.b() : null;
    }

    public OItemStack a(int i, int j, boolean flag, OEntityPlayer oentityplayer) {
        OItemStack oitemstack = null;

        if (j > 1) {
            return null;
        } else {
            if (j == 0 || j == 1) {
                OInventoryPlayer oinventoryplayer = oentityplayer.k;

                if (i == -999) {
                    if (oinventoryplayer.l() != null && i == -999) {
                        if (j == 0) {
                            oentityplayer.b(oinventoryplayer.l());
                            oinventoryplayer.b((OItemStack) null);
                        }

                        if (j == 1) {
                            oentityplayer.b(oinventoryplayer.l().a(1));
                            if (oinventoryplayer.l().a == 0) {
                                oinventoryplayer.b((OItemStack) null);
                            }
                        }
                    }
                } else if (flag) {
                    OItemStack oitemstack1 = this.a(i);

                    if (oitemstack1 != null) {
                        int k = oitemstack1.c;

                        oitemstack = oitemstack1.j();
                        OSlot oslot = (OSlot) this.e.get(i);

                        if (oslot != null && oslot.b() != null && oslot.b().c == k) {
                            this.b(i, j, flag, oentityplayer);
                        }
                    }
                } else {
                    if (i < 0) {
                        return null;
                    }

                    OSlot oslot1 = (OSlot) this.e.get(i);

                    if (oslot1 != null) {
                        oslot1.d();
                        OItemStack oitemstack2 = oslot1.b();
                        OItemStack oitemstack3 = oinventoryplayer.l();

                        if (oitemstack2 != null) {
                            oitemstack = oitemstack2.j();
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

                            oslot1.c(oinventoryplayer.l());
                        } else if (oslot1.a(oitemstack3)) {
                            if (oitemstack2.c == oitemstack3.c && (!oitemstack2.e() || oitemstack2.h() == oitemstack3.h()) && OItemStack.a(oitemstack2, oitemstack3)) {
                                l = j == 0 ? oitemstack3.a : 1;
                                if (l > oslot1.a() - oitemstack2.a) {
                                    l = oslot1.a() - oitemstack2.a;
                                }

                                if (l > oitemstack3.b() - oitemstack2.a) {
                                    l = oitemstack3.b() - oitemstack2.a;
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
                        } else if (oitemstack2.c == oitemstack3.c && oitemstack3.b() > 1 && (!oitemstack2.e() || oitemstack2.h() == oitemstack3.h()) && OItemStack.a(oitemstack2, oitemstack3)) {
                            l = oitemstack2.a;
                            if (l > 0 && l + oitemstack3.a <= oitemstack3.b()) {
                                oitemstack3.a += l;
                                oitemstack2 = oslot1.a(l);
                                if (oitemstack2.a == 0) {
                                    oslot1.d((OItemStack) null);
                                }

                                oslot1.c(oinventoryplayer.l());
                            }
                        }
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

        OInventoryPlayer oinventoryplayer = oentityplayer.k;

        if (oinventoryplayer.l() != null) {
            oentityplayer.b(oinventoryplayer.l());
            oinventoryplayer.b((OItemStack) null);
        }
    }

    public void a(OIInventory oiinventory) {
        this.a();
    }

    public void a(int i, OItemStack oitemstack) {
        this.b(i).d(oitemstack);
    }

    public boolean c(OEntityPlayer oentityplayer) {
        return !this.b.contains(oentityplayer);
    }

    public void a(OEntityPlayer oentityplayer, boolean flag) {
        if (flag) {
            this.b.remove(oentityplayer);
        } else {
            this.b.add(oentityplayer);
        }

    }

    public abstract boolean b(OEntityPlayer oentityplayer);

    protected boolean a(OItemStack oitemstack, int i, int j, boolean flag) {
        boolean flag1 = false;
        int k = i;

        if (flag) {
            k = j - 1;
        }

        OSlot oslot;
        OItemStack oitemstack1;

        if (oitemstack.c()) {
            while (oitemstack.a > 0 && (!flag && k < j || flag && k >= i)) {
                oslot = (OSlot) this.e.get(k);
                oitemstack1 = oslot.b();
                if (oitemstack1 != null && oitemstack1.c == oitemstack.c && (!oitemstack.e() || oitemstack.h() == oitemstack1.h()) && OItemStack.a(oitemstack, oitemstack1)) {
                    int l = oitemstack1.a + oitemstack.a;

                    if (l <= oitemstack.b()) {
                        oitemstack.a = 0;
                        oitemstack1.a = l;
                        oslot.d();
                        flag1 = true;
                    } else if (oitemstack1.a < oitemstack.b()) {
                        oitemstack.a -= oitemstack.b() - oitemstack1.a;
                        oitemstack1.a = oitemstack.b();
                        oslot.d();
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
                oslot = (OSlot) this.e.get(k);
                oitemstack1 = oslot.b();
                if (oitemstack1 == null) {
                    oslot.d(oitemstack.j());
                    oslot.d();
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
