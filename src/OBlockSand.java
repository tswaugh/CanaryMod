import java.util.Random;


public class OBlockSand extends OBlock {

    public static boolean a = false;

    public OBlockSand(int i, int j) {
        super(i, j, OMaterial.o);
        this.a(OCreativeTabs.b);
    }

    public void g(OWorld oworld, int i, int j, int k) {
        // CanaryMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(oworld.world, this.ca, i, j, k), true)) {
            oworld.a(i, j, k, this.ca, this.p_());
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        // CanaryMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(oworld.world, this.ca, i, j, k), true)) {
            oworld.a(i, j, k, this.ca, this.p_());
        }
    }

    public void b(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.K) {
            this.l(oworld, i, j, k);
        }
    }

    private void l(OWorld oworld, int i, int j, int k) {
        if (e_(oworld, i, j - 1, k) && j >= 0) {
            byte b0 = 32;

            if (!a && oworld.c(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0)) {
                if (!oworld.K) {
                    OEntityFallingSand oentityfallingsand = new OEntityFallingSand(oworld, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this.ca);

                    oworld.d((OEntity) oentityfallingsand);
                }
            } else {
                oworld.e(i, j, k, 0);

                while (e_(oworld, i, j - 1, k) && j > 0) {
                    --j;
                }

                if (j > 0) {
                    oworld.e(i, j, k, this.ca);
                }
            }
        }

    }

    public int p_() {
        return 3;
    }

    public static boolean e_(OWorld oworld, int i, int j, int k) {
        int l = oworld.a(i, j, k);

        if (l == 0) {
            return true;
        } else if (l == OBlock.ar.ca) {
            return true;
        } else {
            OMaterial omaterial = OBlock.m[l].cp;

            return omaterial == OMaterial.g ? true : omaterial == OMaterial.h;
        }
    }

}
