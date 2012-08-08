import java.util.List;
import java.util.Random;


public class OBlockPressurePlate extends OBlock {

    private OEnumMobType a;

    protected OBlockPressurePlate(int i, int j, OEnumMobType oenummobtype, OMaterial omaterial) {
        super(i, j, omaterial);
        this.a = oenummobtype;
        this.a(true);
        float f = 0.0625F;

        this.a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    }

    public int d() {
        return 20;
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
    
    public boolean b(OIBlockAccess oiblockaccess, int i, int j, int k) {
        return true;
    }

    public boolean c(OWorld oworld, int i, int j, int k) {
        return oworld.e(i, j - 1, k) || oworld.a(i, j - 1, k) == OBlock.aZ.bO;
    }

    public void a(OWorld oworld, int i, int j, int k) {}

    public void a(OWorld oworld, int i, int j, int k, int l) {
        boolean flag = false;

        if (!oworld.e(i, j - 1, k) && oworld.a(i, j - 1, k) != OBlock.aZ.bO) {
            flag = true;
        }

        if (flag) {
            this.b(oworld, i, j, k, oworld.c(i, j, k), 0);
            oworld.e(i, j, k, 0);
        }

    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.F) {
            if (oworld.c(i, j, k) != 0) {
                this.g(oworld, i, j, k);
            }
        }
    }

    public void a(OWorld oworld, int i, int j, int k, OEntity oentity) {
        if (!oworld.F) {
            if (oworld.c(i, j, k) != 1) {
                this.g(oworld, i, j, k);
            }
        }
    }

    private void g(OWorld oworld, int i, int j, int k) {
        boolean flag = oworld.c(i, j, k) == 1;
        boolean flag1 = false;
        float f = 0.125F;
        List list = null;

        if (this.a == OEnumMobType.a) {
            list = oworld.b((OEntity) null, OAxisAlignedBB.b((double) ((float) i + f), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) j + 0.25D, (double) ((float) (k + 1) - f)));
        }

        if (this.a == OEnumMobType.b) {
            list = oworld.a(OEntityLiving.class, OAxisAlignedBB.b((double) ((float) i + f), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) j + 0.25D, (double) ((float) (k + 1) - f)));
        }

        if (this.a == OEnumMobType.c) {
            list = oworld.a(OEntityPlayer.class, OAxisAlignedBB.b((double) ((float) i + f), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) j + 0.25D, (double) ((float) (k + 1) - f)));
        }

        if (list.size() > 0) {
            flag1 = true;
        }
      
        // CanaryMod: Allow pressure plate interaction to power redstone
        if (flag1 != flag) {
            flag1 = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(oworld.world, bO, i, j, k), flag ? 1 : 0, flag1 ? 1 : 0) > 0;
        }

        if (flag1 && !flag) {
            oworld.c(i, j, k, 1);
            oworld.h(i, j, k, this.bO);
            oworld.h(i, j - 1, k, this.bO);
            oworld.b(i, j, k, i, j, k);
            oworld.a((double) i + 0.5D, (double) j + 0.1D, (double) k + 0.5D, "random.click", 0.3F, 0.6F);
        }

        if (!flag1 && flag) {
            oworld.c(i, j, k, 0);
            oworld.h(i, j, k, this.bO);
            oworld.h(i, j - 1, k, this.bO);
            oworld.b(i, j, k, i, j, k);
            oworld.a((double) i + 0.5D, (double) j + 0.1D, (double) k + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (flag1) {
            oworld.c(i, j, k, this.bO, this.d());
        }

    }

    public void d(OWorld oworld, int i, int j, int k) {
        int l = oworld.c(i, j, k);

        if (l > 0) {
            oworld.h(i, j, k, this.bO);
            oworld.h(i, j - 1, k, this.bO);
        }

        super.d(oworld, i, j, k);
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        boolean flag = oiblockaccess.c(i, j, k) == 1;
        float f = 0.0625F;

        if (flag) {
            this.a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else {
            this.a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }

    }

    public boolean a(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        return oiblockaccess.c(i, j, k) > 0;
    }

    public boolean d(OWorld oworld, int i, int j, int k, int l) {
        return oworld.c(i, j, k) == 0 ? false : l == 1;
    }

    public boolean e() {
        return true;
    }

    public void f() {
        float f = 0.5F;
        float f1 = 0.125F;
        float f2 = 0.5F;

        this.a(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
    }

    public int g() {
        return 1;
    }
}
