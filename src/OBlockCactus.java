import java.util.Random;


public class OBlockCactus extends OBlock {

    protected OBlockCactus(int i, int j) {
        super(i, j, OMaterial.x);
        this.a(true);
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (oworld.g(i, j + 1, k)) {
            int l;

            for (l = 1; oworld.a(i, j - l, k) == this.bO; ++l) {
                ;
            }

            if (l < 3) {
                int i1 = oworld.c(i, j, k);

                if (i1 == 15) {
                    oworld.e(i, j + 1, k, this.bO);
                    oworld.c(i, j, k, 0);
                } else {
                    oworld.c(i, j, k, i1 + 1);
                }
            }
        }

    }

    public OAxisAlignedBB e(OWorld oworld, int i, int j, int k) {
        float f = 0.0625F;

        return OAxisAlignedBB.b((double) ((float) i + f), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) ((float) (j + 1) - f), (double) ((float) (k + 1) - f));
    }

    public int a(int i) {
        return i == 1 ? this.bN - 1 : (i == 0 ? this.bN + 1 : this.bN);
    }

    public boolean b() {
        return false;
    }

    public boolean a() {
        return false;
    }

    public int c() {
        return 13;
    }

    public boolean c(OWorld oworld, int i, int j, int k) {
        return !super.c(oworld, i, j, k) ? false : this.f(oworld, i, j, k);
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (!this.f(oworld, i, j, k)) {
            this.b(oworld, i, j, k, oworld.c(i, j, k), 0);
            oworld.e(i, j, k, 0);
        }

    }

    public boolean f(OWorld oworld, int i, int j, int k) {
        if (oworld.d(i - 1, j, k).a()) {
            return false;
        } else if (oworld.d(i + 1, j, k).a()) {
            return false;
        } else if (oworld.d(i, j, k - 1).a()) {
            return false;
        } else if (oworld.d(i, j, k + 1).a()) {
            return false;
        } else {
            int l = oworld.a(i, j - 1, k);

            return l == OBlock.aV.bO || l == OBlock.E.bO;
        }
    }

    public void a(OWorld oworld, int i, int j, int k, OEntity oentity) {
        // CanaryMod Damage hook: Cactus
        if (oentity instanceof OEntityLiving && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.CACTUS, null, new LivingEntity((OEntityLiving) oentity), 1)) {
            return;
        }
        oentity.a(ODamageSource.h, 1);
    }
}
