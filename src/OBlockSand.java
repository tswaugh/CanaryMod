import java.util.Random;


public class OBlockSand extends OBlock {

    public static boolean a = false;

    public OBlockSand(int i, int j) {
        super(i, j, OMaterial.o);
    }

    public void a(OWorld oworld, int i, int j, int k) {
        // CanaryMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(oworld.world, bO, i, j, k), true)) {
            oworld.c(i, j, k, this.bO, this.d());
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        // CanaryMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(oworld.world, bO, i, j, k), true)) {
            oworld.c(i, j, k, this.bO, this.d());
        }
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        this.h(oworld, i, j, k);
    }

    private void h(OWorld oworld, int i, int j, int k) {
        if (g(oworld, i, j - 1, k) && j >= 0) {
            byte b0 = 32;

            if (!a && oworld.a(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0)) {
                if (!oworld.F) {
                    OEntityFallingSand oentityfallingsand = new OEntityFallingSand(oworld, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this.bO);

                    oworld.b((OEntity) oentityfallingsand);
                }
            } else {
                oworld.e(i, j, k, 0);

                while (g(oworld, i, j - 1, k) && j > 0) {
                    --j;
                }

                if (j > 0) {
                    oworld.e(i, j, k, this.bO);
                }
            }
        }

    }

    public int d() {
        return 3;
    }

    public static boolean g(OWorld oworld, int i, int j, int k) {
        int l = oworld.a(i, j, k);

        if (l == 0) {
            return true;
        } else if (l == OBlock.ar.bO) {
            return true;
        } else {
            OMaterial omaterial = OBlock.m[l].cd;

            return omaterial == OMaterial.g ? true : omaterial == OMaterial.h;
        }
    }

}
