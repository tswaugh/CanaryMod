import java.util.Random;

public class OBlockCactus extends OBlock {

    protected OBlockCactus(int i, int j)
    {
        super(i, j, OMaterial.y);
        b(true);
        a(OCreativeTabs.c);
    }

    public void b(OWorld oworld, int i, int j, int k, Random random) {
        if (oworld.c(i, j + 1, k)) {
            int l;
            for(l = 1; oworld.a(i, j - l, k) == cm; l++);
            if(l < 3){
                int i1 = oworld.g(i, j, k);

                if (i1 == 15) {
                    oworld.e(i, j + 1, k, cm);
                    oworld.c(i, j, k, 0);
                } else {
                    oworld.c(i, j, k, i1 + 1);
                }
            }
        }

    }

    public OAxisAlignedBB e(OWorld oworld, int i, int j, int k) {
        float f = 0.0625F;

        return OAxisAlignedBB.a().a((float)i + f, j, (float)k + f, (float)(i + 1) - f, (float)(j + 1) - f, (float)(k + 1) - f);
    }

    public int a(int i) {
        return i == 1 ? cl - 1 : (i == 0 ? cl + 1 : cl);
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public int d() {
        return 13;
    }

    public boolean b(OWorld oworld, int i, int j, int k) {
        return !super.b(oworld, i, j, k) ? false : this.d(oworld, i, j, k);
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (!this.d(oworld, i, j, k)) {
            this.c(oworld, i, j, k, oworld.g(i, j, k), 0);
            oworld.e(i, j, k, 0);
        }

    }

    public boolean d(OWorld oworld, int i, int j, int k) {
        if (oworld.f(i - 1, j, k).a()) {
            return false;
        } else if (oworld.f(i + 1, j, k).a()) {
            return false;
        } else if (oworld.f(i, j, k - 1).a()) {
            return false;
        } else if (oworld.f(i, j, k + 1).a()) {
            return false;
        } else {
            int l = oworld.a(i, j - 1, k);

            return l == OBlock.aY.cm || l == OBlock.H.cm;
        }
    }

    public void a(OWorld oworld, int i, int j, int k, OEntity oentity) {
        // CanaryMod Damage hook: Cactus
        if (oentity instanceof OEntityLiving && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.CACTUS, null, new LivingEntity((OEntityLiving) oentity), 1)) {
            return;
        }
        oentity.a(ODamageSource.g, 1);
    }
}
