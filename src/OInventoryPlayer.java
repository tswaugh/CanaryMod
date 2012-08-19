
public class OInventoryPlayer implements OIInventory, Container<OItemStack> {

    public OItemStack[] a = new OItemStack[36];
    public OItemStack[] b = new OItemStack[4];
    public int c = 0;
    public OEntityPlayer d;
    private OItemStack f;
    public boolean e = false;
    // CanaryMod
    private String name = "Inventory";

    public OInventoryPlayer(OEntityPlayer oentityplayer) {
        super();
        this.d = oentityplayer;
    }

    public OItemStack d() {
        return this.c < 9 && this.c >= 0 ? this.a[this.c] : null;
    }

    public static int h() {
        return 9;
    }

    private int f(int i) {
        for (int j = 0; j < this.a.length; ++j) {
            if (this.a[j] != null && this.a[j].c == i) {
                return j;
            }
        }

        return -1;
    }

    private int d(OItemStack oitemstack) {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null && this.a[i].c == oitemstack.c && this.a[i].c() && this.a[i].a < this.a[i].b() && this.a[i].a < this.a() && (!this.a[i].e() || this.a[i].h() == oitemstack.h()) && OItemStack.a(this.a[i], oitemstack)) {
                return i;
            }
        }

        return -1;
    }

    private int m() {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] == null) {
                return i;
            }
        }

        return -1;
    }

    private int e(OItemStack oitemstack) {
        int i = oitemstack.c;
        int j = oitemstack.a;
        int k;

        if (oitemstack.b() == 1) {
            k = this.m();
            if (k < 0) {
                return j;
            } else {
                if (this.a[k] == null) {
                    this.a[k] = OItemStack.b(oitemstack);
                }

                return 0;
            }
        } else {
            k = this.d(oitemstack);
            if (k < 0) {
                k = this.m();
            }

            if (k < 0) {
                return j;
            } else {
                if (this.a[k] == null) {
                    this.a[k] = new OItemStack(i, 0, oitemstack.h());
                    if (oitemstack.n()) {
                        this.a[k].d((ONBTTagCompound) oitemstack.o().b());
                    }
                }

                int l = j;

                if (j > this.a[k].b() - this.a[k].a) {
                    l = this.a[k].b() - this.a[k].a;
                }

                if (l > this.a() - this.a[k].a) {
                    l = this.a() - this.a[k].a;
                }

                if (l == 0) {
                    return j;
                } else {
                    j -= l;
                    this.a[k].a += l;
                    this.a[k].b = 5;
                    return j;
                }
            }
        }
    }

    public void i() {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                this.a[i].a(this.d.bi, this.d, i, this.c == i);
            }
        }

    }

    public boolean c(int i) {
        int j = this.f(i);

        if (j < 0) {
            return false;
        } else {
            if (--this.a[j].a <= 0) {
                this.a[j] = null;
            }

            return true;
        }
    }

    public boolean d(int i) {
        int j = this.f(i);

        return j >= 0;
    }
    
    // CanaryMod: Simulate Pickup (Its the same as a(OItemStack) but without
    // altering the inventory
    public boolean canPickup(OEntityItem oentityitem) {
        OItemStack oitemstack = oentityitem.a;
        int i;

        if (oitemstack.f()) {
            i = this.m();
            if (i >= 0) {
                return !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, ((OEntityPlayerMP) d).getPlayer(), oentityitem.item);
            } else if (this.d.L.d) {
                return true;
            } else {
                return false;
            }
        } else {
            int slot = 0;
            int left = oitemstack.a;
            
            do {
                OItemStack oitemstack1 = this.a[slot];
                int delta = 0;
                
                if (oitemstack1 == null) {
                    delta = Math.min(64, left);
                } else if (oitemstack1.a < 64 && oitemstack.c == oitemstack1.c && oitemstack.d() == oitemstack1.d()) {
                    delta = Math.min(64 - oitemstack.a, left);
                }
                left -= delta;
                slot++;
            } while (left > 0 && slot < 36);
            if (oitemstack.a - left > 0) {
                return !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, ((OEntityPlayerMP) d).getPlayer(), oentityitem.item);
            } else {
                return false;
            }
        }
    }

    public boolean a(OItemStack oitemstack) {
        int i;

        if (oitemstack.f()) {
            i = this.m();
            if (i >= 0) {
                this.a[i] = OItemStack.b(oitemstack);
                this.a[i].b = 5;
                oitemstack.a = 0;
                return true;
            } else if (this.d.L.d) {
                oitemstack.a = 0;
                return true;
            } else {
                return false;
            }
        } else {
            do {
                i = oitemstack.a;
                oitemstack.a = this.e(oitemstack);
            } while (oitemstack.a > 0 && oitemstack.a < i);

            if (oitemstack.a == i && this.d.L.d) {
                oitemstack.a = 0;
                return true;
            } else {
                return oitemstack.a < i;
            }
        }
    }

    public OItemStack a(int i, int j) {
        OItemStack[] aoitemstack = this.a;

        if (i >= this.a.length) {
            aoitemstack = this.b;
            i -= this.a.length;
        }

        if (aoitemstack[i] != null) {
            OItemStack oitemstack;

            if (aoitemstack[i].a <= j) {
                oitemstack = aoitemstack[i];
                aoitemstack[i] = null;
                return oitemstack;
            } else {
                oitemstack = aoitemstack[i].a(j);
                if (aoitemstack[i].a == 0) {
                    aoitemstack[i] = null;
                }

                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        OItemStack[] aoitemstack = this.a;

        if (i >= this.a.length) {
            aoitemstack = this.b;
            i -= this.a.length;
        }

        if (aoitemstack[i] != null) {
            OItemStack oitemstack = aoitemstack[i];

            aoitemstack[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        OItemStack[] aoitemstack = this.a;

        if (i >= aoitemstack.length) {
            i -= aoitemstack.length;
            aoitemstack = this.b;
        }

        aoitemstack[i] = oitemstack;
    }

    public float a(OBlock oblock) {
        float f = 1.0F;

        if (this.a[this.c] != null) {
            f *= this.a[this.c].a(oblock);
        }

        return f;
    }

    public ONBTTagList a(ONBTTagList onbttaglist) {
        int i;
        ONBTTagCompound onbttagcompound;

        for (i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                onbttagcompound = new ONBTTagCompound();
                onbttagcompound.a("Slot", (byte) i);
                this.a[i].b(onbttagcompound);
                onbttaglist.a((ONBTBase) onbttagcompound);
            }
        }

        for (i = 0; i < this.b.length; ++i) {
            if (this.b[i] != null) {
                onbttagcompound = new ONBTTagCompound();
                onbttagcompound.a("Slot", (byte) (i + 100));
                this.b[i].b(onbttagcompound);
                onbttaglist.a((ONBTBase) onbttagcompound);
            }
        }

        return onbttaglist;
    }

    public void b(ONBTTagList onbttaglist) {
        this.a = new OItemStack[36];
        this.b = new OItemStack[4];

        for (int i = 0; i < onbttaglist.d(); ++i) {
            ONBTTagCompound onbttagcompound = (ONBTTagCompound) onbttaglist.a(i);
            int j = onbttagcompound.d("Slot") & 255;
            OItemStack oitemstack = OItemStack.a(onbttagcompound);

            if (oitemstack != null) {
                if (j >= 0 && j < this.a.length) {
                    this.a[j] = oitemstack;
                }

                if (j >= 100 && j < this.b.length + 100) {
                    this.b[j - 100] = oitemstack;
                }
            }
        }

    }

    public int c() {
        return this.a.length + 4;
    }

    public OItemStack g_(int i) {
        OItemStack[] aoitemstack = this.a;

        if (i >= aoitemstack.length) {
            i -= aoitemstack.length;
            aoitemstack = this.b;
        }

        return aoitemstack[i];
    }

    public String e() {
        return "container.inventory";
    }

    public int a() {
        return 64;
    }

    public int a(OEntity oentity) {
        OItemStack oitemstack = this.g_(this.c);

        return oitemstack != null ? oitemstack.a(oentity) : 1;
    }

    public boolean b(OBlock oblock) {
        if (oblock.cd.k()) {
            return true;
        } else {
            OItemStack oitemstack = this.g_(this.c);

            return oitemstack != null ? oitemstack.b(oblock) : false;
        }
    }

    public int j() {
        int i = 0;

        for (int j = 0; j < this.b.length; ++j) {
            if (this.b[j] != null && this.b[j].a() instanceof OItemArmor) {
                int k = ((OItemArmor) this.b[j].a()).b;

                i += k;
            }
        }

        return i;
    }

    public void e(int i) {
        i /= 4;
        if (i < 1) {
            i = 1;
        }

        for (int j = 0; j < this.b.length; ++j) {
            if (this.b[j] != null && this.b[j].a() instanceof OItemArmor) {
                this.b[j].a(i, this.d);
                if (this.b[j].a == 0) {
                    this.b[j].a(this.d);
                    this.b[j] = null;
                }
            }
        }

    }

    public void k() {
        int i;

        for (i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                this.d.a(this.a[i], true);
                this.a[i] = null;
            }
        }

        for (i = 0; i < this.b.length; ++i) {
            if (this.b[i] != null) {
                this.d.a(this.b[i], true);
                this.b[i] = null;
            }
        }

    }

    public void G_() {
        this.e = true;
    }

    public void b(OItemStack oitemstack) {
        this.f = oitemstack;
        this.d.a(oitemstack);
    }

    public OItemStack l() {
        return this.f;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.d.bE ? false : oentityplayer.j(this.d) <= 64.0D;
    }

    public boolean c(OItemStack oitemstack) {
        int i;

        for (i = 0; i < this.b.length; ++i) {
            if (this.b[i] != null && this.b[i].c(oitemstack)) {
                return true;
            }
        }

        for (i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null && this.a[i].c(oitemstack)) {
                return true;
            }
        }

        return false;
    }

    public void f() {}

    public void g() {}
    
    @Override
    public OItemStack[] getContents() {
        return a;
    }
   
    @Override
    public void setContents(OItemStack[] aoitemstack) {
        a = aoitemstack;
    }

    @Override
    public OItemStack getContentsAt(int i) {
        return g_(i);
    }

    @Override
    public void setContentsAt(int i, OItemStack oitemstack) {
        a(i, oitemstack);
    }

    @Override
    public int getContentsSize() {
        return c();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String s) {
        name = s;
    }

    public void a(OInventoryPlayer oinventoryplayer) {
        int i;

        for (i = 0; i < this.a.length; ++i) {
            this.a[i] = OItemStack.b(oinventoryplayer.a[i]);
        }

        for (i = 0; i < this.b.length; ++i) {
            this.b[i] = OItemStack.b(oinventoryplayer.b[i]);
        }

    }
}
