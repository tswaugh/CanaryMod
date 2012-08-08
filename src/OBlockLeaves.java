import java.util.Random;


public class OBlockLeaves extends OBlockLeavesBase {

    private int c;
    int[] a;

    protected OBlockLeaves(int i, int j) {
        super(i, j, OMaterial.i, false);
        this.c = j;
        this.a(true);
    }

    public void d(OWorld oworld, int i, int j, int k) {
        byte b0 = 1;
        int l = b0 + 1;

        if (oworld.a(i - l, j - l, k - l, i + l, j + l, k + l)) {
            for (int i1 = -b0; i1 <= b0; ++i1) {
                for (int j1 = -b0; j1 <= b0; ++j1) {
                    for (int k1 = -b0; k1 <= b0; ++k1) {
                        int l1 = oworld.a(i + i1, j + j1, k + k1);

                        if (l1 == OBlock.K.bO) {
                            int i2 = oworld.c(i + i1, j + j1, k + k1);

                            oworld.d(i + i1, j + j1, k + k1, i2 | 8);
                        }
                    }
                }
            }
        }

    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.F) {
            int l = oworld.c(i, j, k);

            if ((l & 8) != 0 && (l & 4) == 0) {
                byte b0 = 4;
                int i1 = b0 + 1;
                byte b1 = 32;
                int j1 = b1 * b1;
                int k1 = b1 / 2;

                if (this.a == null) {
                    this.a = new int[b1 * b1 * b1];
                }

                int l1;

                if (oworld.a(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1)) {
                    int i2;
                    int j2;
                    int k2;

                    for (l1 = -b0; l1 <= b0; ++l1) {
                        for (i2 = -b0; i2 <= b0; ++i2) {
                            for (j2 = -b0; j2 <= b0; ++j2) {
                                k2 = oworld.a(i + l1, j + i2, k + j2);
                                if (k2 == OBlock.J.bO) {
                                    this.a[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
                                } else if (k2 == OBlock.K.bO) {
                                    this.a[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
                                } else {
                                    this.a[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
                                }
                            }
                        }
                    }

                    for (l1 = 1; l1 <= 4; ++l1) {
                        for (i2 = -b0; i2 <= b0; ++i2) {
                            for (j2 = -b0; j2 <= b0; ++j2) {
                                for (k2 = -b0; k2 <= b0; ++k2) {
                                    if (this.a[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1) {
                                        if (this.a[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2) {
                                            this.a[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.a[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2) {
                                            this.a[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.a[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2) {
                                            this.a[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.a[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2) {
                                            this.a[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.a[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2) {
                                            this.a[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
                                        }

                                        if (this.a[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2) {
                                            this.a[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                l1 = this.a[k1 * j1 + k1 * b1 + k1];
                if (l1 >= 0) {
                    oworld.d(i, j, k, l & -9);
                } else {
                    this.g(oworld, i, j, k);
                }
            }

        }
    }

    private void g(OWorld oworld, int i, int j, int k) {
        // CanaryMod: stop leaves from decaying
        World world = oworld.world;
        Block block = new Block(world, world.getBlockIdAt(i, j, k), i, j, k);

        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.LEAF_DECAY, block)) {
            this.b(oworld, i, j, k, oworld.c(i, j, k), 0);
            oworld.e(i, j, k, 0);
        }
    }

    public int a(Random random) {
        return random.nextInt(20) == 0 ? 1 : 0;
    }

    public int a(int i, Random random, int j) {
        return OBlock.y.bO;
    }

    public void a(OWorld oworld, int i, int j, int k, int l, float f, int i1) {
        if (!oworld.F) {
            byte b0 = 20;

            if ((l & 3) == 3) {
                b0 = 40;
            }

            if (oworld.r.nextInt(b0) == 0) {
                int j1 = this.a(l, oworld.r, i1);

                this.a(oworld, i, j, k, new OItemStack(j1, 1, this.c(l)));
            }

            if ((l & 3) == 0 && oworld.r.nextInt(200) == 0) {
                this.a(oworld, i, j, k, new OItemStack(OItem.i, 1, 0));
            }
        }

    }

    public void a(OWorld oworld, OEntityPlayer oentityplayer, int i, int j, int k, int l) {
        if (!oworld.F && oentityplayer.U() != null && oentityplayer.U().c == OItem.bd.bP) {
            oentityplayer.a(OStatList.C[this.bO], 1);
            this.a(oworld, i, j, k, new OItemStack(OBlock.K.bO, 1, l & 3));
        } else {
            super.a(oworld, oentityplayer, i, j, k, l);
        }

    }

    protected int c(int i) {
        return i & 3;
    }

    public boolean a() {
        return !this.b;
    }

    public int a(int i, int j) {
        return (j & 3) == 1 ? this.bN + 80 : ((j & 3) == 3 ? this.bN + 144 : this.bN);
    }

    public void b(OWorld oworld, int i, int j, int k, OEntity oentity) {
        super.b(oworld, i, j, k, oentity);
    }
}
