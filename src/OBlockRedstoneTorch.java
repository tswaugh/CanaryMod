import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class OBlockRedstoneTorch extends OBlockTorch {

    private boolean a = false;
    private static List b = new ArrayList();

    public int a(int i, int j) {
        return i == 1 ? OBlock.av.a(i, j) : super.a(i, j);
    }

    private boolean a(OWorld oworld, int i, int j, int k, boolean flag) {
        if (flag) {
            b.add(new ORedstoneUpdateInfo(i, j, k, oworld.o()));
        }

        int l = 0;

        for (int i1 = 0; i1 < b.size(); ++i1) {
            ORedstoneUpdateInfo oredstoneupdateinfo = (ORedstoneUpdateInfo) b.get(i1);

            if (oredstoneupdateinfo.a == i && oredstoneupdateinfo.b == j && oredstoneupdateinfo.c == k) {
                ++l;
                if (l >= 8) {
                    return true;
                }
            }
        }

        return false;
    }

    protected OBlockRedstoneTorch(int i, int j, boolean flag) {
        super(i, j);
        this.a = flag;
        this.a(true);
    }

    public int d() {
        return 2;
    }

    public void a(OWorld oworld, int i, int j, int k) {
        if (oworld.c(i, j, k) == 0) {
            super.a(oworld, i, j, k);
        }

        if (this.a) {
            oworld.h(i, j - 1, k, this.bO);
            oworld.h(i, j + 1, k, this.bO);
            oworld.h(i - 1, j, k, this.bO);
            oworld.h(i + 1, j, k, this.bO);
            oworld.h(i, j, k - 1, this.bO);
            oworld.h(i, j, k + 1, this.bO);
        }

    }

    public void d(OWorld oworld, int i, int j, int k) {
        if (this.a) {
            oworld.h(i, j - 1, k, this.bO);
            oworld.h(i, j + 1, k, this.bO);
            oworld.h(i - 1, j, k, this.bO);
            oworld.h(i + 1, j, k, this.bO);
            oworld.h(i, j, k - 1, this.bO);
            oworld.h(i, j, k + 1, this.bO);
        }

    }

    public boolean a(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        if (!this.a) {
            return false;
        } else {
            int i1 = oiblockaccess.c(i, j, k);

            return i1 == 5 && l == 1 ? false : (i1 == 3 && l == 3 ? false : (i1 == 4 && l == 2 ? false : (i1 == 1 && l == 5 ? false : i1 != 2 || l != 4)));
        }
    }

    private boolean g(OWorld oworld, int i, int j, int k) {
        int l = oworld.c(i, j, k);

        return l == 5 && oworld.j(i, j - 1, k, 0) ? true : (l == 3 && oworld.j(i, j, k - 1, 2) ? true : (l == 4 && oworld.j(i, j, k + 1, 3) ? true : (l == 1 && oworld.j(i - 1, j, k, 4) ? true : l == 2 && oworld.j(i + 1, j, k, 5))));
    }
	
    // Skye's attempt at making sure redstone torches stay lit forever.
    private int counter = 0;

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        boolean flag = this.g(oworld, i, j, k);

        while (b.size() > 0 && oworld.o() - ((ORedstoneUpdateInfo) b.get(0)).d > 60L) {
            b.remove(0);
        }

        if (this.a) {
            if (flag) {
                oworld.b(i, j, k, OBlock.aP.bO, oworld.c(i, j, k));
                // CanaryMod: Allow redstone torches to provide power
                int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(oworld.world, bO, i, j, k), 1, 0);
				
                if (current == 0) {
                    if (this.a(oworld, i, j, k, true)) {
                        oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), "random.fizz", 0.5F, 2.6F + (oworld.r.nextFloat() - oworld.r.nextFloat()) * 0.8F);
	
                        for (int l = 0; l < 5; ++l) {
                            double d0 = (double) i + random.nextDouble() * 0.6D + 0.2D;
                            double d1 = (double) j + random.nextDouble() * 0.6D + 0.2D;
                            double d2 = (double) k + random.nextDouble() * 0.6D + 0.2D;
		
                            oworld.a("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
                        }
                    }
                }
            }
        } else if (!flag && !this.a(oworld, i, j, k, false)) {
            oworld.b(i, j, k, OBlock.aQ.bO, oworld.c(i, j, k));
        }

    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        super.a(oworld, i, j, k, l);
        oworld.c(i, j, k, this.bO, this.d());
    }

    public boolean d(OWorld oworld, int i, int j, int k, int l) {
        return l == 0 ? this.a((OIBlockAccess) oworld, i, j, k, l) : false;
    }

    public int a(int i, Random random, int j) {
        return OBlock.aQ.bO;
    }

    public boolean e() {
        return true;
    }

}
