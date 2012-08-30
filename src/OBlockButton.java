import java.util.Random;


public class OBlockButton extends OBlock {

    protected OBlockButton(int i, int j) {
        super(i, j, OMaterial.p);
        this.b(true);
        this.a(OCreativeTabs.d);
    }

    public OAxisAlignedBB e(OWorld oworld, int i, int j, int k) {
        return null;
    }

    public int p_() {
        return 20;
    }

    public boolean d() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public boolean a_(OWorld oworld, int i, int j, int k, int l) {
        return l == 2 && oworld.s(i, j, k + 1) ? true : (l == 3 && oworld.s(i, j, k - 1) ? true : (l == 4 && oworld.s(i + 1, j, k) ? true : l == 5 && oworld.s(i - 1, j, k)));
    }

    public boolean b(OWorld oworld, int i, int j, int k) {
        return oworld.s(i - 1, j, k) ? true : (oworld.s(i + 1, j, k) ? true : (oworld.s(i, j, k - 1) ? true : oworld.s(i, j, k + 1)));
    }

    public void a(OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        int i1 = oworld.g(i, j, k);
        int j1 = i1 & 8;

        i1 &= 7;
        if (l == 2 && oworld.s(i, j, k + 1)) {
            i1 = 4;
        } else if (l == 3 && oworld.s(i, j, k - 1)) {
            i1 = 3;
        } else if (l == 4 && oworld.s(i + 1, j, k)) {
            i1 = 2;
        } else if (l == 5 && oworld.s(i - 1, j, k)) {
            i1 = 1;
        } else {
            i1 = this.l(oworld, i, j, k);
        }

        oworld.c(i, j, k, i1 + j1);
    }

    private int l(OWorld oworld, int i, int j, int k) {
        return oworld.s(i - 1, j, k) ? 1 : (oworld.s(i + 1, j, k) ? 2 : (oworld.s(i, j, k - 1) ? 3 : (oworld.s(i, j, k + 1) ? 4 : 1)));
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (this.n(oworld, i, j, k)) {
            int i1 = oworld.g(i, j, k) & 7;
            boolean flag = false;

            if (!oworld.s(i - 1, j, k) && i1 == 1) {
                flag = true;
            }

            if (!oworld.s(i + 1, j, k) && i1 == 2) {
                flag = true;
            }

            if (!oworld.s(i, j, k - 1) && i1 == 3) {
                flag = true;
            }

            if (!oworld.s(i, j, k + 1) && i1 == 4) {
                flag = true;
            }

            if (flag) {
                this.c(oworld, i, j, k, oworld.g(i, j, k), 0);
                oworld.e(i, j, k, 0);
            }
        }

    }

    private boolean n(OWorld oworld, int i, int j, int k) {
        if (!this.b(oworld, i, j, k)) {
            this.c(oworld, i, j, k, oworld.g(i, j, k), 0);
            oworld.e(i, j, k, 0);
            return false;
        } else {
            return true;
        }
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        int l = oiblockaccess.g(i, j, k);
        int i1 = l & 7;
        boolean flag = (l & 8) > 0;
        float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.1875F;
        float f3 = 0.125F;

        if (flag) {
            f3 = 0.0625F;
        }

        if (i1 == 1) {
            this.a(0.0F, f, 0.5F - f2, f3, f1, 0.5F + f2);
        } else if (i1 == 2) {
            this.a(1.0F - f3, f, 0.5F - f2, 1.0F, f1, 0.5F + f2);
        } else if (i1 == 3) {
            this.a(0.5F - f2, f, 0.0F, 0.5F + f2, f1, f3);
        } else if (i1 == 4) {
            this.a(0.5F - f2, f, 1.0F - f3, 0.5F + f2, f1, 1.0F);
        }

    }

    public void a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {
        this.a(oworld, i, j, k, oentityplayer, 0, 0.0F, 0.0F, 0.0F);
    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer, int l, float f, float f1, float f2) {
        int i1 = oworld.g(i, j, k);
        int j1 = i1 & 7;
        int k1 = 8 - (i1 & 8);

        if (k1 == 0) {
            return true;
        }
      
        // CanaryMod: Allow button to provide power
        int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(oworld.world, this.ca, i, j, k), 0, 1);

        if (change == 0) {
            return true;
        } else {
            oworld.c(i, j, k, j1 + k1);
            oworld.d(i, j, k, i, j, k);
            oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, 0.6F);
            oworld.h(i, j, k, this.ca);
            if (j1 == 1) {
                oworld.h(i - 1, j, k, this.ca);
            } else if (j1 == 2) {
                oworld.h(i + 1, j, k, this.ca);
            } else if (j1 == 3) {
                oworld.h(i, j, k - 1, this.ca);
            } else if (j1 == 4) {
                oworld.h(i, j, k + 1, this.ca);
            } else {
                oworld.h(i, j - 1, k, this.ca);
            }

            oworld.a(i, j, k, this.ca, this.p_());
            return true;
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        if ((i1 & 8) > 0) {
            oworld.h(i, j, k, this.ca);
            int j1 = i1 & 7;

            if (j1 == 1) {
                oworld.h(i - 1, j, k, this.ca);
            } else if (j1 == 2) {
                oworld.h(i + 1, j, k, this.ca);
            } else if (j1 == 3) {
                oworld.h(i, j, k - 1, this.ca);
            } else if (j1 == 4) {
                oworld.h(i, j, k + 1, this.ca);
            } else {
                oworld.h(i, j - 1, k, this.ca);
            }
        }

        super.a(oworld, i, j, k, l, i1);
    }

    public boolean a(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        return (oiblockaccess.g(i, j, k) & 8) > 0;
    }

    public boolean c(OWorld oworld, int i, int j, int k, int l) {
        int i1 = oworld.g(i, j, k);

        if ((i1 & 8) == 0) {
            return false;
        } else {
            int j1 = i1 & 7;

            return j1 == 5 && l == 1 ? true : (j1 == 4 && l == 2 ? true : (j1 == 3 && l == 3 ? true : (j1 == 2 && l == 4 ? true : j1 == 1 && l == 5)));
        }
    }

    public boolean i() {
        return true;
    }

    public void b(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.K) {
            int l = oworld.g(i, j, k);

            if ((l & 8) != 0) {
        	 
                // CanaryMod: Allow button to provide power
                int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, oworld.world.getBlockAt(i, j, k), 1, 0);

                if (change > 0) {
                    return;
                }
        	 
                oworld.c(i, j, k, l & 7);
                oworld.h(i, j, k, this.ca);
                int i1 = l & 7;

                if (i1 == 1) {
                    oworld.h(i - 1, j, k, this.ca);
                } else if (i1 == 2) {
                    oworld.h(i + 1, j, k, this.ca);
                } else if (i1 == 3) {
                    oworld.h(i, j, k - 1, this.ca);
                } else if (i1 == 4) {
                    oworld.h(i, j, k + 1, this.ca);
                } else {
                    oworld.h(i, j - 1, k, this.ca);
                }

                oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, 0.5F);
                oworld.d(i, j, k, i, j, k);
            }
        }
    }

    public void f() {
        float f = 0.1875F;
        float f1 = 0.125F;
        float f2 = 0.125F;

        this.a(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
    }
}
