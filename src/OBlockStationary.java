import java.util.Random;


public class OBlockStationary extends OBlockFluid {

    protected OBlockStationary(int i, OMaterial omaterial) {
        super(i, omaterial);
        this.a(false);
        if (omaterial == OMaterial.h) {
            this.a(true);
        }

    }

    public boolean b(OIBlockAccess oiblockaccess, int i, int j, int k) {
        return this.cd != OMaterial.h;
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        super.a(oworld, i, j, k, l);
        if (oworld.a(i, j, k) == this.bO) {
            this.i(oworld, i, j, k);
        }

    }

    private void i(OWorld oworld, int i, int j, int k) {
        int l = oworld.c(i, j, k);

        oworld.o = true;
        oworld.a(i, j, k, this.bO - 1, l);
        oworld.b(i, j, k, i, j, k);
        oworld.c(i, j, k, this.bO - 1, this.d());
        oworld.o = false;
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (this.cd == OMaterial.h) {
            int l = random.nextInt(3);

            // CanaryMod: prevent lava from putting something on fire.
            Block block = new Block(oworld.world, oworld.a(i, j, k), i, j, k);

            block.setStatus(1);
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                return;
            }
			
            int i1;
            int j1;

            for (i1 = 0; i1 < l; ++i1) {
                i += random.nextInt(3) - 1;
                ++j;
                k += random.nextInt(3) - 1;
                j1 = oworld.a(i, j, k);
                if (j1 == 0) {
                    if (this.j(oworld, i - 1, j, k) || this.j(oworld, i + 1, j, k) || this.j(oworld, i, j, k - 1) || this.j(oworld, i, j, k + 1) || this.j(oworld, i, j - 1, k) || this.j(oworld, i, j + 1, k)) {
                        oworld.e(i, j, k, OBlock.ar.bO);
                        return;
                    }
                } else if (OBlock.m[j1].cd.c()) {
                    return;
                }
            }

            if (l == 0) {
                i1 = i;
                j1 = k;

                for (int k1 = 0; k1 < 3; ++k1) {
                    i = i1 + random.nextInt(3) - 1;
                    k = j1 + random.nextInt(3) - 1;
                    if (oworld.g(i, j + 1, k) && this.j(oworld, i, j, k)) {
                        oworld.e(i, j + 1, k, OBlock.ar.bO);
                    }
                }
            }
        }

    }

    private boolean j(OWorld oworld, int i, int j, int k) {
        return oworld.d(i, j, k).g();
    }
}
