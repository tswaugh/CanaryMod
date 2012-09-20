import java.util.Random;


public class OBlockFire extends OBlock {

    private int[] a = new int[256];
    private int[] b = new int[256];

    protected OBlockFire(int i, int j) {
        super(i, j, OMaterial.n);
        this.b(true);
    }

    public void r_() {
        this.a(OBlock.x.ca, 5, 20);
        this.a(OBlock.bN.ca, 5, 20);
        this.a(OBlock.bO.ca, 5, 20);
        this.a(OBlock.aZ.ca, 5, 20);
        this.a(OBlock.at.ca, 5, 20);
        this.a(OBlock.bX.ca, 5, 20);
        this.a(OBlock.bW.ca, 5, 20);
        this.a(OBlock.bY.ca, 5, 20);
        this.a(OBlock.J.ca, 5, 5);
        this.a(OBlock.K.ca, 30, 60);
        this.a(OBlock.an.ca, 30, 20);
        this.a(OBlock.am.ca, 15, 100);
        this.a(OBlock.X.ca, 60, 100);
        this.a(OBlock.ab.ca, 30, 60);
        this.a(OBlock.bu.ca, 15, 100);
    }

    private void a(int i, int j, int k) {
        this.a[i] = j;
        this.b[i] = k;
    }

    public OAxisAlignedBB e(OWorld oworld, int i, int j, int k) {
        return null;
    }

    public boolean d() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public int b() {
        return 3;
    }

    public int a(Random random) {
        return 0;
    }

    public int p_() {
        return 30;
    }

    public void b(OWorld oworld, int i, int j, int k, Random random) {
        boolean flag = oworld.a(i, j - 1, k) == OBlock.bb.ca;

        if (oworld.w instanceof OWorldProviderEnd && oworld.a(i, j - 1, k) == OBlock.z.ca) {
            flag = true;
        }

        if (!this.b(oworld, i, j, k)) {
            oworld.e(i, j, k, 0);
        }

        if (!flag && oworld.J() && (oworld.B(i, j, k) || oworld.B(i - 1, j, k) || oworld.B(i + 1, j, k) || oworld.B(i, j, k - 1) || oworld.B(i, j, k + 1))) {
            oworld.e(i, j, k, 0);
        } else {
            int l = oworld.g(i, j, k);

            if (l < 15) {
                oworld.d(i, j, k, l + random.nextInt(3) / 2);
            }

            oworld.a(i, j, k, this.ca, this.p_() + random.nextInt(10));
            if (!flag && !this.l(oworld, i, j, k)) {
                if (!oworld.t(i, j - 1, k) || l > 3) {
                    oworld.e(i, j, k, 0);
                }
            } else if (!flag && !this.d(oworld, i, j - 1, k) && l == 15 && random.nextInt(4) == 0) {
                oworld.e(i, j, k, 0);
            } else {
                boolean flag1 = oworld.C(i, j, k);
                byte b0 = 0;

                if (flag1) {
                    b0 = -50;
                }

                this.a(oworld, i + 1, j, k, 300 + b0, random, l);
                this.a(oworld, i - 1, j, k, 300 + b0, random, l);
                this.a(oworld, i, j - 1, k, 250 + b0, random, l);
                this.a(oworld, i, j + 1, k, 250 + b0, random, l);
                this.a(oworld, i, j, k - 1, 300 + b0, random, l);
                this.a(oworld, i, j, k + 1, 300 + b0, random, l);

                for (int i1 = i - 1; i1 <= i + 1; ++i1) {
                    for (int j1 = k - 1; j1 <= k + 1; ++j1) {
                        for (int k1 = j - 1; k1 <= j + 4; ++k1) {
                            if (i1 != i || k1 != j || j1 != k) {
                                int l1 = 100;

                                if (k1 > j + 1) {
                                    l1 += (k1 - (j + 1)) * 100;
                                }

                                int i2 = this.n(oworld, i1, k1, j1);

                                if (i2 > 0) {
                                    int j2 = (i2 + 40) / (l + 30);

                                    if (flag1) {
                                        j2 /= 2;
                                    }

                                    if (j2 > 0 && random.nextInt(l1) <= j2 && (!oworld.J() || !oworld.B(i1, k1, j1)) && !oworld.B(i1 - 1, k1, k) && !oworld.B(i1 + 1, k1, j1) && !oworld.B(i1, k1, j1 - 1) && !oworld.B(i1, k1, j1 + 1)) {
                                        int k2 = l + random.nextInt(5) / 4;

                                        if (k2 > 15) {
                                            k2 = 15;
                                        }

                                        // CanaryMod: dynamic spreading of fire.
                                        // avg call amount per placed block of fire ~ 4
                                        Block block = new Block(oworld.world, oworld.world.getBlockIdAt(i1, k1, j1), i1, k1, j1);

                                        block.setStatus(3);
                                        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                                            oworld.d(i1, k1, j1, this.ca, k2);
                                        }

                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    private void a(OWorld oworld, int i, int j, int k, int l, Random random, int i1) {
        int j1 = this.b[oworld.a(i, j, k)];

        if (random.nextInt(l) < j1) {
            boolean flag = oworld.a(i, j, k) == OBlock.am.ca;

            if (random.nextInt(i1 + 10) < 5 && !oworld.B(i, j, k)) {
                int k1 = i1 + random.nextInt(5) / 4;

                if (k1 > 15) {
                    k1 = 15;
                }

                // CanaryMod: VERY SLOW dynamic spreading of fire.
                Block block = new Block(oworld.world, oworld.world.getBlockIdAt(i, j, k), i, j, k);

                block.setStatus(3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                    oworld.d(i, j, k, this.ca, k1);
                }
            } else {
                // CanaryMod: fire destroying a block.
                Block block = new Block(oworld.world, oworld.a(i, j, k), i, j, k);

                block.setStatus(4);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                    oworld.e(i, j, k, 0);
                }
            }

            if (flag) {
                OBlock.am.d(oworld, i, j, k, 1);
            }
        }

    }

    private boolean l(OWorld oworld, int i, int j, int k) {
        return this.d((OIBlockAccess) oworld, i + 1, j, k) ? true : (this.d((OIBlockAccess) oworld, i - 1, j, k) ? true : (this.d((OIBlockAccess) oworld, i, j - 1, k) ? true : (this.d((OIBlockAccess) oworld, i, j + 1, k) ? true : (this.d((OIBlockAccess) oworld, i, j, k - 1) ? true : this.d((OIBlockAccess) oworld, i, j, k + 1)))));
    }

    private int n(OWorld oworld, int i, int j, int k) {
        byte b0 = 0;

        if (!oworld.c(i, j, k)) {
            return 0;
        } else {
            int l = this.e(oworld, i + 1, j, k, b0);

            l = this.e(oworld, i - 1, j, k, l);
            l = this.e(oworld, i, j - 1, k, l);
            l = this.e(oworld, i, j + 1, k, l);
            l = this.e(oworld, i, j, k - 1, l);
            l = this.e(oworld, i, j, k + 1, l);
            return l;
        }
    }

    public boolean l() {
        return false;
    }

    public boolean d(OIBlockAccess oiblockaccess, int i, int j, int k) {
        return this.a[oiblockaccess.a(i, j, k)] > 0;
    }

    public int e(OWorld oworld, int i, int j, int k, int l) {
        int i1 = this.a[oworld.a(i, j, k)];

        return i1 > l ? i1 : l;
    }

    public boolean b(OWorld oworld, int i, int j, int k) {
        return oworld.t(i, j - 1, k) || this.l(oworld, i, j, k);
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (!oworld.t(i, j - 1, k) && !this.l(oworld, i, j, k)) {
            oworld.e(i, j, k, 0);
        }
    }

    public void g(OWorld oworld, int i, int j, int k) {
        if (oworld.w.g > 0 || oworld.a(i, j - 1, k) != OBlock.ap.ca || !OBlock.be.i_(oworld, i, j, k)) {
            if (!oworld.t(i, j - 1, k) && !this.l(oworld, i, j, k)) {
                oworld.e(i, j, k, 0);
            } else {
                oworld.a(i, j, k, this.ca, this.p_() + oworld.v.nextInt(10));
            }
        }
    }
}
