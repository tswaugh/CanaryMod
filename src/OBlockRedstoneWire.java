import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class OBlockRedstoneWire extends OBlock {

    private boolean a = true;
    private Set b = new HashSet();

    public OBlockRedstoneWire(int i, int j) {
        super(i, j, OMaterial.p);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    public int a(int i, int j) {
        return this.bN;
    }

    public OAxisAlignedBB e(OWorld oworld, int i, int j, int k) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int c() {
        return 5;
    }

    public boolean c(OWorld oworld, int i, int j, int k) {
        return oworld.e(i, j - 1, k) || oworld.a(i, j - 1, k) == OBlock.bd.bO;
    }

    private void g(OWorld oworld, int i, int j, int k) {
        this.a(oworld, i, j, k, i, j, k);
        ArrayList arraylist = new ArrayList(this.b);

        this.b.clear();

        for (int l = 0; l < arraylist.size(); ++l) {
            OChunkPosition ochunkposition = (OChunkPosition) arraylist.get(l);

            oworld.h(ochunkposition.a, ochunkposition.b, ochunkposition.c, this.bO);
        }

    }

    private void a(OWorld oworld, int i, int j, int k, int l, int i1, int j1) {
        int k1 = oworld.c(i, j, k);
        int l1 = 0;

        this.a = false;
        boolean flag = oworld.x(i, j, k);

        this.a = true;
        int i2;
        int j2;
        int k2;

        if (flag) {
            l1 = 15;
        } else {
            for (i2 = 0; i2 < 4; ++i2) {
                j2 = i;
                k2 = k;
                if (i2 == 0) {
                    j2 = i - 1;
                }

                if (i2 == 1) {
                    ++j2;
                }

                if (i2 == 2) {
                    k2 = k - 1;
                }

                if (i2 == 3) {
                    ++k2;
                }

                if (j2 != l || j != i1 || k2 != j1) {
                    l1 = this.f(oworld, j2, j, k2, l1);
                }

                if (oworld.e(j2, j, k2) && !oworld.e(i, j + 1, k)) {
                    if (j2 != l || j + 1 != i1 || k2 != j1) {
                        l1 = this.f(oworld, j2, j + 1, k2, l1);
                    }
                } else if (!oworld.e(j2, j, k2) && (j2 != l || j - 1 != i1 || k2 != j1)) {
                    l1 = this.f(oworld, j2, j - 1, k2, l1);
                }
            }

            if (l1 > 0) {
                --l1;
            } else {
                l1 = 0;
            }
        }
		
        // CanaryMod: Allow redstone wire current changes
        if (k1 != l1) {
            l1 = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(oworld.world, bO, i, j, k), k1, l1);
        }

        if (k1 != l1) {
            oworld.o = true;
            oworld.c(i, j, k, l1);
            oworld.b(i, j, k, i, j, k);
            oworld.o = false;

            for (i2 = 0; i2 < 4; ++i2) {
                j2 = i;
                k2 = k;
                int l2 = j - 1;

                if (i2 == 0) {
                    j2 = i - 1;
                }

                if (i2 == 1) {
                    ++j2;
                }

                if (i2 == 2) {
                    k2 = k - 1;
                }

                if (i2 == 3) {
                    ++k2;
                }

                if (oworld.e(j2, j, k2)) {
                    l2 += 2;
                }

                boolean flag1 = false;
                int i3 = this.f(oworld, j2, j, k2, -1);

                l1 = oworld.c(i, j, k);
                if (l1 > 0) {
                    --l1;
                }

                if (i3 >= 0 && i3 != l1) {
                    this.a(oworld, j2, j, k2, i, j, k);
                }

                i3 = this.f(oworld, j2, l2, k2, -1);
                l1 = oworld.c(i, j, k);
                if (l1 > 0) {
                    --l1;
                }

                if (i3 >= 0 && i3 != l1) {
                    this.a(oworld, j2, l2, k2, i, j, k);
                }
            }

            if (k1 < l1 || l1 == 0) {
                this.b.add(new OChunkPosition(i, j, k));
                this.b.add(new OChunkPosition(i - 1, j, k));
                this.b.add(new OChunkPosition(i + 1, j, k));
                this.b.add(new OChunkPosition(i, j - 1, k));
                this.b.add(new OChunkPosition(i, j + 1, k));
                this.b.add(new OChunkPosition(i, j, k - 1));
                this.b.add(new OChunkPosition(i, j, k + 1));
            }
        }

    }

    private void h(OWorld oworld, int i, int j, int k) {
        if (oworld.a(i, j, k) == this.bO) {
            oworld.h(i, j, k, this.bO);
            oworld.h(i - 1, j, k, this.bO);
            oworld.h(i + 1, j, k, this.bO);
            oworld.h(i, j, k - 1, this.bO);
            oworld.h(i, j, k + 1, this.bO);
            oworld.h(i, j - 1, k, this.bO);
            oworld.h(i, j + 1, k, this.bO);
        }
    }

    public void a(OWorld oworld, int i, int j, int k) {
        super.a(oworld, i, j, k);
        if (!oworld.F) {
            this.g(oworld, i, j, k);
            oworld.h(i, j + 1, k, this.bO);
            oworld.h(i, j - 1, k, this.bO);
            this.h(oworld, i - 1, j, k);
            this.h(oworld, i + 1, j, k);
            this.h(oworld, i, j, k - 1);
            this.h(oworld, i, j, k + 1);
            if (oworld.e(i - 1, j, k)) {
                this.h(oworld, i - 1, j + 1, k);
            } else {
                this.h(oworld, i - 1, j - 1, k);
            }

            if (oworld.e(i + 1, j, k)) {
                this.h(oworld, i + 1, j + 1, k);
            } else {
                this.h(oworld, i + 1, j - 1, k);
            }

            if (oworld.e(i, j, k - 1)) {
                this.h(oworld, i, j + 1, k - 1);
            } else {
                this.h(oworld, i, j - 1, k - 1);
            }

            if (oworld.e(i, j, k + 1)) {
                this.h(oworld, i, j + 1, k + 1);
            } else {
                this.h(oworld, i, j - 1, k + 1);
            }

        }
    }

    public void d(OWorld oworld, int i, int j, int k) {
        super.d(oworld, i, j, k);
        if (!oworld.F) {
            oworld.h(i, j + 1, k, this.bO);
            oworld.h(i, j - 1, k, this.bO);
            oworld.h(i + 1, j, k, this.bO);
            oworld.h(i - 1, j, k, this.bO);
            oworld.h(i, j, k + 1, this.bO);
            oworld.h(i, j, k - 1, this.bO);
            this.g(oworld, i, j, k);
            this.h(oworld, i - 1, j, k);
            this.h(oworld, i + 1, j, k);
            this.h(oworld, i, j, k - 1);
            this.h(oworld, i, j, k + 1);
            if (oworld.e(i - 1, j, k)) {
                this.h(oworld, i - 1, j + 1, k);
            } else {
                this.h(oworld, i - 1, j - 1, k);
            }

            if (oworld.e(i + 1, j, k)) {
                this.h(oworld, i + 1, j + 1, k);
            } else {
                this.h(oworld, i + 1, j - 1, k);
            }

            if (oworld.e(i, j, k - 1)) {
                this.h(oworld, i, j + 1, k - 1);
            } else {
                this.h(oworld, i, j - 1, k - 1);
            }

            if (oworld.e(i, j, k + 1)) {
                this.h(oworld, i, j + 1, k + 1);
            } else {
                this.h(oworld, i, j - 1, k + 1);
            }

        }
    }

    private int f(OWorld oworld, int i, int j, int k, int l) {
        if (oworld.a(i, j, k) != this.bO) {
            return l;
        } else {
            int i1 = oworld.c(i, j, k);

            return i1 > l ? i1 : l;
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (!oworld.F) {
            int i1 = oworld.c(i, j, k);
            boolean flag = this.c(oworld, i, j, k);

            if (!flag) {
                this.b(oworld, i, j, k, i1, 0);
                oworld.e(i, j, k, 0);
            } else {
                this.g(oworld, i, j, k);
            }

            super.a(oworld, i, j, k, l);
        }
    }

    public int a(int i, Random random, int j) {
        return OItem.aB.bP;
    }

    public boolean d(OWorld oworld, int i, int j, int k, int l) {
        return !this.a ? false : this.a((OIBlockAccess) oworld, i, j, k, l);
    }

    public boolean a(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        if (!this.a) {
            return false;
        } else if (oiblockaccess.c(i, j, k) == 0) {
            return false;
        } else if (l == 1) {
            return true;
        } else {
            boolean flag = d(oiblockaccess, i - 1, j, k, 1) || !oiblockaccess.e(i - 1, j, k) && d(oiblockaccess, i - 1, j - 1, k, -1);
            boolean flag1 = d(oiblockaccess, i + 1, j, k, 3) || !oiblockaccess.e(i + 1, j, k) && d(oiblockaccess, i + 1, j - 1, k, -1);
            boolean flag2 = d(oiblockaccess, i, j, k - 1, 2) || !oiblockaccess.e(i, j, k - 1) && d(oiblockaccess, i, j - 1, k - 1, -1);
            boolean flag3 = d(oiblockaccess, i, j, k + 1, 0) || !oiblockaccess.e(i, j, k + 1) && d(oiblockaccess, i, j - 1, k + 1, -1);

            if (!oiblockaccess.e(i, j + 1, k)) {
                if (oiblockaccess.e(i - 1, j, k) && d(oiblockaccess, i - 1, j + 1, k, -1)) {
                    flag = true;
                }

                if (oiblockaccess.e(i + 1, j, k) && d(oiblockaccess, i + 1, j + 1, k, -1)) {
                    flag1 = true;
                }

                if (oiblockaccess.e(i, j, k - 1) && d(oiblockaccess, i, j + 1, k - 1, -1)) {
                    flag2 = true;
                }

                if (oiblockaccess.e(i, j, k + 1) && d(oiblockaccess, i, j + 1, k + 1, -1)) {
                    flag3 = true;
                }
            }

            return !flag2 && !flag1 && !flag && !flag3 && l >= 2 && l <= 5 ? true : (l == 2 && flag2 && !flag && !flag1 ? true : (l == 3 && flag3 && !flag && !flag1 ? true : (l == 4 && flag && !flag2 && !flag3 ? true : l == 5 && flag1 && !flag2 && !flag3)));
        }
    }

    public boolean e() {
        return this.a;
    }

    public static boolean c(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        int i1 = oiblockaccess.a(i, j, k);

        if (i1 == OBlock.av.bO) {
            return true;
        } else if (i1 == 0) {
            return false;
        } else if (i1 != OBlock.bh.bO && i1 != OBlock.bi.bO) {
            return OBlock.m[i1].e() && l != -1;
        } else {
            int j1 = oiblockaccess.c(i, j, k);

            return l == (j1 & 3) || l == ODirection.e[j1 & 3];
        }
    }

    public static boolean d(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        if (c(oiblockaccess, i, j, k, l)) {
            return true;
        } else {
            int i1 = oiblockaccess.a(i, j, k);

            if (i1 == OBlock.bi.bO) {
                int j1 = oiblockaccess.c(i, j, k);

                return l == (j1 & 3);
            } else {
                return false;
            }
        }
    }
}
