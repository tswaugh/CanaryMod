import java.util.Random;


public class OBlockFlowing extends OBlockFluid {

    int a = 0;
    boolean[] b = new boolean[4];
    int[] c = new int[4];

    protected OBlockFlowing(int i, OMaterial omaterial) {
        super(i, omaterial);
    }

    private void i(OWorld oworld, int i, int j, int k) {
        int l = oworld.c(i, j, k);

        oworld.a(i, j, k, this.bO + 1, l);
        oworld.b(i, j, k, i, j, k);
        oworld.j(i, j, k);
    }

    public boolean b(OIBlockAccess oiblockaccess, int i, int j, int k) {
        return this.cd != OMaterial.h;
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        // CanaryMod: Store originating block
        World world = oworld.world;
        Block blockFrom = new Block(world, bO, i, j, k);
		
        int l = this.g(oworld, i, j, k);
        byte b0 = 1;

        if (this.cd == OMaterial.h && !oworld.t.d) {
            b0 = 2;
        }

        boolean flag = true;
        int i1;

        if (l > 0) {
            byte b1 = -100;

            this.a = 0;
            int j1 = this.f(oworld, i - 1, j, k, b1);

            j1 = this.f(oworld, i + 1, j, k, j1);
            j1 = this.f(oworld, i, j, k - 1, j1);
            j1 = this.f(oworld, i, j, k + 1, j1);
            i1 = j1 + b0;
            if (i1 >= 8 || j1 < 0) {
                i1 = -1;
            }

            if (this.g(oworld, i, j + 1, k) >= 0) {
                int k1 = this.g(oworld, i, j + 1, k);

                if (k1 >= 8) {
                    i1 = k1;
                } else {
                    i1 = k1 + 8;
                }
            }

            if (this.a >= 2 && this.cd == OMaterial.g) {
                if (oworld.d(i, j - 1, k).a()) {
                    i1 = 0;
                } else if (oworld.d(i, j - 1, k) == this.cd && oworld.c(i, j, k) == 0) {
                    i1 = 0;
                }
            }

            if (this.cd == OMaterial.h && l < 8 && i1 < 8 && i1 > l && random.nextInt(4) != 0) {
                i1 = l;
                flag = false;
            }

            if (i1 != l) {
                l = i1;
                if (i1 < 0) {
                    oworld.e(i, j, k, 0);
                } else {
                    oworld.c(i, j, k, i1);
                    oworld.c(i, j, k, this.bO, this.d());
                    oworld.h(i, j, k, this.bO);
                }
            } else if (flag) {
                this.i(oworld, i, j, k);
            }
        } else {
            this.i(oworld, i, j, k);
        }

        if (this.l(oworld, i, j - 1, k)) {
            if (this.cd == OMaterial.h && oworld.d(i, j - 1, k) == OMaterial.g) {
                oworld.e(i, j - 1, k, OBlock.t.bO);
                this.h(oworld, i, j - 1, k);
                return;
            }
            // CanaryMod: downwards flow.
            Block blockTo = new Block(world, 0, i, j - 1, k);

            if (!((Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[] { blockFrom, blockTo })).booleanValue()) {
                if (l >= 8) {
                    oworld.b(i, j - 1, k, this.bO, l);
                } else {
                    oworld.b(i, j - 1, k, this.bO, l + 8);
                }
            }
        } else if (l >= 0 && (l == 0 || this.k(oworld, i, j - 1, k))) {
            boolean[] aboolean = this.j(oworld, i, j, k);

            i1 = l + b0;
            if (l >= 8) {
                i1 = 1;
            }

            if (i1 >= 8) {
                return;
            }

            // CanaryMod: sidewards flow.
            if (aboolean[0]) {
                Block blockTo = new Block(world, 0, i - 1, j, k);

                if (!((Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[] { blockFrom, blockTo })).booleanValue()) {
                    this.g(oworld, i - 1, j, k, i1);
                }
            }

            if (aboolean[1]) {
                Block blockTo = new Block(world, 0, i + 1, j, k);

                if (!((Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[] { blockFrom, blockTo })).booleanValue()) {
                    this.g(oworld, i + 1, j, k, i1);
                }
            }

            if (aboolean[2]) {
                Block blockTo = new Block(world, 0, i, j, k - 1);

                if (!((Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[] { blockFrom, blockTo })).booleanValue()) {
                    this.g(oworld, i, j, k - 1, i1);
                }
            }

            if (aboolean[3]) {
                Block blockTo = new Block(world, 0, i, j, k + 1);

                if (!((Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[] { blockFrom, blockTo })).booleanValue()) {
                    this.g(oworld, i, j, k + 1, i1);
                }
            }
        }

    }

    private void g(OWorld oworld, int i, int j, int k, int l) {
        if (this.l(oworld, i, j, k)) {
            int i1 = oworld.a(i, j, k);

            if (i1 > 0) {
                if (this.cd == OMaterial.h) {
                    this.h(oworld, i, j, k);
                } else {
                    OBlock.m[i1].b(oworld, i, j, k, oworld.c(i, j, k), 0);
                }
            }

            oworld.b(i, j, k, this.bO, l);
        }

    }

    private int c(OWorld oworld, int i, int j, int k, int l, int i1) {
        int j1 = 1000;

        for (int k1 = 0; k1 < 4; ++k1) {
            if ((k1 != 0 || i1 != 1) && (k1 != 1 || i1 != 0) && (k1 != 2 || i1 != 3) && (k1 != 3 || i1 != 2)) {
                int l1 = i;
                int i2 = k;

                if (k1 == 0) {
                    l1 = i - 1;
                }

                if (k1 == 1) {
                    ++l1;
                }

                if (k1 == 2) {
                    i2 = k - 1;
                }

                if (k1 == 3) {
                    ++i2;
                }

                if (!this.k(oworld, l1, j, i2) && (oworld.d(l1, j, i2) != this.cd || oworld.c(l1, j, i2) != 0)) {
                    if (!this.k(oworld, l1, j - 1, i2)) {
                        return l;
                    }

                    if (l < 4) {
                        int j2 = this.c(oworld, l1, j, i2, l + 1, k1);

                        if (j2 < j1) {
                            j1 = j2;
                        }
                    }
                }
            }
        }

        return j1;
    }

    private boolean[] j(OWorld oworld, int i, int j, int k) {
        int l;
        int i1;

        for (l = 0; l < 4; ++l) {
            this.c[l] = 1000;
            i1 = i;
            int j1 = k;

            if (l == 0) {
                i1 = i - 1;
            }

            if (l == 1) {
                ++i1;
            }

            if (l == 2) {
                j1 = k - 1;
            }

            if (l == 3) {
                ++j1;
            }

            if (!this.k(oworld, i1, j, j1) && (oworld.d(i1, j, j1) != this.cd || oworld.c(i1, j, j1) != 0)) {
                if (!this.k(oworld, i1, j - 1, j1)) {
                    this.c[l] = 0;
                } else {
                    this.c[l] = this.c(oworld, i1, j, j1, 1, l);
                }
            }
        }

        l = this.c[0];

        for (i1 = 1; i1 < 4; ++i1) {
            if (this.c[i1] < l) {
                l = this.c[i1];
            }
        }

        for (i1 = 0; i1 < 4; ++i1) {
            this.b[i1] = this.c[i1] == l;
        }

        return this.b;
    }

    private boolean k(OWorld oworld, int i, int j, int k) {
        int l = oworld.a(i, j, k);

        if (l != OBlock.aE.bO && l != OBlock.aL.bO && l != OBlock.aD.bO && l != OBlock.aF.bO && l != OBlock.aX.bO) {
            if (l == 0) {
                return false;
            } else {
                OMaterial omaterial = OBlock.m[l].cd;

                return omaterial == OMaterial.B ? true : omaterial.c();
            }
        } else {
            return true;
        }
    }

    protected int f(OWorld oworld, int i, int j, int k, int l) {
        int i1 = this.g(oworld, i, j, k);

        if (i1 < 0) {
            return l;
        } else {
            if (i1 == 0) {
                ++this.a;
            }

            if (i1 >= 8) {
                i1 = 0;
            }

            return l >= 0 && i1 >= l ? l : i1;
        }
    }

    private boolean l(OWorld oworld, int i, int j, int k) {
        // CanaryMod: See if this liquid can destroy this block.
        Block block = new Block(oworld.world, oworld.a(i, j, k), i, j, k);
        PluginLoader.HookResult ret = (PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.LIQUID_DESTROY, this.bO, block);

        if (ret == PluginLoader.HookResult.PREVENT_ACTION) {
            return false;
        }
        if (ret == PluginLoader.HookResult.ALLOW_ACTION) {
            return true;
        }
        OMaterial omaterial = oworld.d(i, j, k);

        return omaterial == this.cd ? false : (omaterial == OMaterial.h ? false : !this.k(oworld, i, j, k));
    }

    public void a(OWorld oworld, int i, int j, int k) {
        super.a(oworld, i, j, k);
        if (oworld.a(i, j, k) == this.bO) {
            oworld.c(i, j, k, this.bO, this.d());
        }

    }
}
