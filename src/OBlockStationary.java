import java.util.Random;


public class OBlockStationary extends OBlockFluid {

    protected OBlockStationary(int i, OMaterial omaterial) {
        super(i, omaterial);
        this.b(false);
        if (omaterial == OMaterial.h) {
            this.b(true);
        }

    }

    public boolean c(OIBlockAccess oiblockaccess, int i, int j, int k) {
        return this.cp != OMaterial.h;
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        super.a(oworld, i, j, k, l);
        if (oworld.a(i, j, k) == this.ca) {
            this.l(oworld, i, j, k);
        }

    }

    private void l(OWorld oworld, int i, int j, int k) {
        int l = oworld.g(i, j, k);

        oworld.t = true;
        oworld.c(i, j, k, this.ca - 1, l);
        oworld.d(i, j, k, i, j, k);
        oworld.a(i, j, k, this.ca - 1, this.p_());
        oworld.t = false;
    }

    public void b(OWorld oworld, int i, int j, int k, Random random) {
        if (this.cp == OMaterial.h) {
            int l = random.nextInt(3);

            // CanaryMod: prevent lava from putting something on fire.
            World world = oworld.world;
            Block block = new Block(world, world.getBlockIdAt(i, j, k), i, j, k);

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
                    if (this.n(oworld, i - 1, j, k) || this.n(oworld, i + 1, j, k) || this.n(oworld, i, j, k - 1) || this.n(oworld, i, j, k + 1) || this.n(oworld, i, j - 1, k) || this.n(oworld, i, j + 1, k)) {
                        oworld.e(i, j, k, OBlock.ar.ca);
                        return;
                    }
                } else if (OBlock.m[j1].cp.c()) {
                    return;
                }
            }

            if (l == 0) {
                i1 = i;
                j1 = k;

                for (int k1 = 0; k1 < 3; ++k1) {
                    i = i1 + random.nextInt(3) - 1;
                    k = j1 + random.nextInt(3) - 1;
                    if (oworld.c(i, j + 1, k) && this.n(oworld, i, j, k)) {
                        oworld.e(i, j + 1, k, OBlock.ar.ca);
                    }
                }
            }
        }

    }

    private boolean n(OWorld oworld, int i, int j, int k) {
        return oworld.f(i, j, k).h();
    }
}
