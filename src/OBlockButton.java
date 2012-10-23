import java.util.List;
import java.util.Random;


public class OBlockButton extends OBlock {

    private final boolean a;

    protected OBlockButton(int j, int k, boolean flag) {
        super(j, k, OMaterial.q);
        b(true);
        a(OCreativeTabs.d);
        a = flag;
    }

    public OAxisAlignedBB e(OWorld oworld, int j, int k, int i1) {
        return null;
    }

    public int r_() {
        return a ? 30 : 20;
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean b_(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if ((paramInt4 == 2) && (paramOWorld.s(paramInt1, paramInt2, paramInt3 + 1))) {
            return true;
        }
        if ((paramInt4 == 3) && (paramOWorld.s(paramInt1, paramInt2, paramInt3 - 1))) {
            return true;
        }
        if ((paramInt4 == 4) && (paramOWorld.s(paramInt1 + 1, paramInt2, paramInt3))) {
            return true;
        }
        if ((paramInt4 == 5) && (paramOWorld.s(paramInt1 - 1, paramInt2, paramInt3))) {
            return true;
        }
        return false;
    }

    public boolean b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if (paramOWorld.s(paramInt1 - 1, paramInt2, paramInt3)) {
            return true;
        }
        if (paramOWorld.s(paramInt1 + 1, paramInt2, paramInt3)) {
            return true;
        }
        if (paramOWorld.s(paramInt1, paramInt2, paramInt3 - 1)) {
            return true;
        }
        if (paramOWorld.s(paramInt1, paramInt2, paramInt3 + 1)) {
            return true;
        }
        return false;
    }

    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3) {
        int i = paramOWorld.g(paramInt1, paramInt2, paramInt3);

        int j = i & 0x8;
        i &= 7;

        if ((paramInt4 == 2) && (paramOWorld.s(paramInt1, paramInt2, paramInt3 + 1))) {
            i = 4;
        } else if ((paramInt4 == 3) && (paramOWorld.s(paramInt1, paramInt2, paramInt3 - 1))) {
            i = 3;
        } else if ((paramInt4 == 4) && (paramOWorld.s(paramInt1 + 1, paramInt2, paramInt3))) {
            i = 2;
        } else if ((paramInt4 == 5) && (paramOWorld.s(paramInt1 - 1, paramInt2, paramInt3))) {
            i = 1;
        } else {
            i = l(paramOWorld, paramInt1, paramInt2, paramInt3);
        }
        paramOWorld.c(paramInt1, paramInt2, paramInt3, i + j);
    }

    private int l(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if (paramOWorld.s(paramInt1 - 1, paramInt2, paramInt3)) {
            return 1;
        }
        if (paramOWorld.s(paramInt1 + 1, paramInt2, paramInt3)) {
            return 2;
        }
        if (paramOWorld.s(paramInt1, paramInt2, paramInt3 - 1)) {
            return 3;
        }
        if (paramOWorld.s(paramInt1, paramInt2, paramInt3 + 1)) {
            return 4;
        }
        return 1;
    }

    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (n(paramOWorld, paramInt1, paramInt2, paramInt3)) {
            int i = paramOWorld.g(paramInt1, paramInt2, paramInt3) & 0x7;
            int j = 0;

            if ((!paramOWorld.s(paramInt1 - 1, paramInt2, paramInt3)) && (i == 1)) {
                j = 1;
            }
            if ((!paramOWorld.s(paramInt1 + 1, paramInt2, paramInt3)) && (i == 2)) {
                j = 1;
            }
            if ((!paramOWorld.s(paramInt1, paramInt2, paramInt3 - 1)) && (i == 3)) {
                j = 1;
            }
            if ((!paramOWorld.s(paramInt1, paramInt2, paramInt3 + 1)) && (i == 4)) {
                j = 1;
            }

            if (j != 0) {
                c(paramOWorld, paramInt1, paramInt2, paramInt3, paramOWorld.g(paramInt1, paramInt2, paramInt3), 0);
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
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

    public void a(OIBlockAccess oiBlockAccess, int i, int j, int k) {
        int toE = oiBlockAccess.g(i, j, k);
        e(toE);
    }

    public void a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {
    }

    private void e(int paramInt) {
        int i = paramInt & 0x7;
        int j = (paramInt & 0x8) > 0 ? 1 : 0;

        float f1 = 0.375F;
        float f2 = 0.625F;
        float f3 = 0.1875F;
        float f4 = 0.125F;
        if (j != 0) {
            f4 = 0.0625F;
        }

        if (i == 1) {
            a(0.0F, f1, 0.5F - f3, f4, f2, 0.5F + f3);
        } else if (i == 2) {
            a(1.0F - f4, f1, 0.5F - f3, 1.0F, f2, 0.5F + f3);
        } else if (i == 3) {
            a(0.5F - f3, f1, 0.0F, 0.5F + f3, f2, f4);
        } else if (i == 4) {
            a(0.5F - f3, f1, 1.0F - f4, 0.5F + f3, f2, 1.0F);
        }
    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer, int l, float f, float f1, float f2) {
        int i1 = oworld.g(i, j, k);
        int l1 = i1 & 7;
        int k1 = 8 - (i1 & 8);

        if (k1 == 0) {
            return true;
        }

        // CanaryMod: Allow button to provide power
        int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(oworld.world, cm, i, j, k), 0, 1);

        if (change == 0) {
            return true;
        } else {
            oworld.c(i, j, k, l1 + k1);
            oworld.d(i, j, k, i, j, k);
            oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, 0.6F);
            d(oworld, i, j, k, l1);
            oworld.a(i, j, k, cm, r_());
            return true;
        }
    }

    public void a(OWorld oworld, int j, int k, int i1, int j1, int k1) {
        if((k1 & 8) > 0)
        {
            int l1 = k1 & 7;
            d(oworld, j, k, i1, l1);
        }
        super.a(oworld, j, k, i1, j1, k1);
    }

    public boolean a(OIBlockAccess oiblockaccess, int j, int k, int i1, int j1) {
        return (oiblockaccess.g(j, k, i1) & 8) > 0;
    }

//    public boolean c(OWorld oworld, int i, int j, int k, int l) {
//        int i1 = oworld.g(i, j, k);
//
//        if ((i1 & 8) == 0) {
//            return false;
//        } else {
//            int j1 = i1 & 7;
//
//            return j1 == 5 && l == 1 ? true : (j1 == 4 && l == 2 ? true : (j1 == 3 && l == 3 ? true : (j1 == 2 && l == 4 ? true : j1 == 1 && l == 5)));
//        }
//    }

    public boolean b(OIBlockAccess oiblockaccess, int j, int k, int i1, int j1) {
        int k1 = oiblockaccess.g(j, k, i1);
        if ((k1 & 8) == 0) {
            return false;
        }
        int l1 = k1 & 7;
        if (l1 == 5 && j1 == 1) {
            return true;
        }
        if (l1 == 4 && j1 == 2) {
            return true;
        }
        if (l1 == 3 && j1 == 3) {
            return true;
        }
        if (l1 == 2 && j1 == 4) {
            return true;
        }
        return l1 == 1 && j1 == 5;
    }

    public boolean i() {
        return true;
    }

    public void b(OWorld oworld, int j, int k, int i1, Random random) {
        if (!oworld.J) {
            int j1 = oworld.g(j, k, i1);

            if ((j1 & 8) != 0) {

                // CanaryMod: Allow button to provide power
                int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, oworld.world.getBlockAt(j, k, i1), 1, 0);

                if (change > 0) {
                    return;
                }

                if(a) {
                    o(oworld, j, k, i1);
                } else {
                    oworld.c(j, k, i1, j1 & 7);
                    int k1 = j1 & 7;
                    d(oworld, j, k, i1, k1);
                    oworld.a((double) j + 0.5D, (double) k + 0.5D, (double) i1 + 0.5D, "random.click", 0.3F, 0.5F);
                    oworld.e(j, k, i1, j, k, i1);
                }
            }
        }
    }

    public void f() {
        float f1 = 0.1875F;
        float f2 = 0.125F;
        float f3 = 0.125F;
        a(0.5F - f1, 0.5F - f2, 0.5F - f3, 0.5F + f1, 0.5F + f2, 0.5F + f3);
    }

    public void a(OWorld oworld, int j, int k, int i1, OEntity oentity)
    {
        if(oworld.J)
            return;
        if(!a)
            return;
        if((oworld.g(j, k, i1) & 8) != 0)
        {
            return;
        } else
        {
            o(oworld, j, k, i1);
            return;
        }
    }

    private void o(OWorld oworld, int j, int k, int i1) {
        int j1 = oworld.g(j, k, i1);
        int k1 = j1 & 7;
        boolean flag = (j1 & 8) != 0;
        e(j1);
        List list = oworld.a(OEntityArrow, OAxisAlignedBB.a().a((double) j + ct, (double) k + cu, (double) i1 + cv, (double) j + cw, (double) k + cx, (double) i1 + cy));
        boolean flag1 = !list.isEmpty();
        if (flag1 && !flag) {
            oworld.c(j, k, i1, k1 | 8);
            d(oworld, j, k, i1, k1);
            oworld.e(j, k, i1, j, k, i1);
            oworld.a((double) j + 0.5D, (double) k + 0.5D, (double) i1 + 0.5D, "random.click", 0.3F, 0.6F);
        }
        if (!flag1 && flag) {
            oworld.c(j, k, i1, k1);
            d(oworld, j, k, i1, k1);
            oworld.e(j, k, i1, j, k, i1);
            oworld.a((double) j + 0.5D, (double) k + 0.5D, (double) i1 + 0.5D, "random.click", 0.3F, 0.5F);
        }
        if (flag1) {
            oworld.a(j, k, i1, cm, r_());
        }
    }

    private void d(OWorld oworld, int j, int k, int i1, int j1) {
        oworld.h(j, k, i1, cm);
        if (j1 == 1) {
            oworld.h(j - 1, k, i1, cm);
        } else if (j1 == 2) {
            oworld.h(j + 1, k, i1, cm);
        } else if (j1 == 3) {
            oworld.h(j, k, i1 - 1, cm);
        } else if (j1 == 4) {
            oworld.h(j, k, i1 + 1, cm);
        } else {
            oworld.h(j, k - 1, i1, cm);
        }
    }





}
