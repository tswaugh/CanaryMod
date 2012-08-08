import java.util.ArrayList;
import java.util.Random;


public class OBlockStairs extends OBlock {

    private OBlock a;

    protected OBlockStairs(int i, OBlock oblock) {
        super(i, oblock.bN, oblock.cd);
        this.a = oblock;
        this.c(oblock.bP);
        this.b(oblock.bQ / 3.0F);
        this.a(oblock.cb);
        this.f(255);
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public OAxisAlignedBB e(OWorld oworld, int i, int j, int k) {
        return super.e(oworld, i, j, k);
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int c() {
        return 10;
    }

    public void a(OWorld oworld, int i, int j, int k, OAxisAlignedBB oaxisalignedbb, ArrayList arraylist) {
        int l = oworld.c(i, j, k);
        int i1 = l & 3;
        float f = 0.0F;
        float f1 = 0.5F;
        float f2 = 0.5F;
        float f3 = 1.0F;

        if ((l & 4) != 0) {
            f = 0.5F;
            f1 = 1.0F;
            f2 = 0.0F;
            f3 = 0.5F;
        }

        this.a(0.0F, f, 0.0F, 1.0F, f1, 1.0F);
        super.a(oworld, i, j, k, oaxisalignedbb, arraylist);
        if (i1 == 0) {
            this.a(0.5F, f2, 0.0F, 1.0F, f3, 1.0F);
            super.a(oworld, i, j, k, oaxisalignedbb, arraylist);
        } else if (i1 == 1) {
            this.a(0.0F, f2, 0.0F, 0.5F, f3, 1.0F);
            super.a(oworld, i, j, k, oaxisalignedbb, arraylist);
        } else if (i1 == 2) {
            this.a(0.0F, f2, 0.5F, 1.0F, f3, 1.0F);
            super.a(oworld, i, j, k, oaxisalignedbb, arraylist);
        } else if (i1 == 3) {
            this.a(0.0F, f2, 0.0F, 1.0F, f3, 0.5F);
            super.a(oworld, i, j, k, oaxisalignedbb, arraylist);
        }

        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void b(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {
        this.a.b(oworld, i, j, k, oentityplayer);
    }

    public void c(OWorld oworld, int i, int j, int k, int l) {
        this.a.c(oworld, i, j, k, l);
    }

    public float a(OEntity oentity) {
        return this.a.a(oentity);
    }

    public int a(int i, int j) {
        return this.a.a(i, 0);
    }

    public int a(int i) {
        return this.a.a(i, 0);
    }

    public int d() {
        return this.a.d();
    }

    public void a(OWorld oworld, int i, int j, int k, OEntity oentity, OVec3D ovec3d) {
        this.a.a(oworld, i, j, k, oentity, ovec3d);
    }

    public boolean E_() {
        return this.a.E_();
    }

    public boolean a(int i, boolean flag) {
        return this.a.a(i, flag);
    }

    public boolean c(OWorld oworld, int i, int j, int k) {
        return this.a.c(oworld, i, j, k);
    }

    public void a(OWorld oworld, int i, int j, int k) {
        this.a(oworld, i, j, k, 0);
        this.a.a(oworld, i, j, k);
    }

    public void d(OWorld oworld, int i, int j, int k) {
        this.a.d(oworld, i, j, k);
    }

    public void b(OWorld oworld, int i, int j, int k, OEntity oentity) {
        this.a.b(oworld, i, j, k, oentity);
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        this.a.a(oworld, i, j, k, random);
    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {
        return this.a.a(oworld, i, j, k, oentityplayer);
    }

    public void a_(OWorld oworld, int i, int j, int k) {
        this.a.a_(oworld, i, j, k);
    }

    public void a(OWorld oworld, int i, int j, int k, OEntityLiving oentityliving) {
        int l = OMathHelper.b((double) (oentityliving.bs * 4.0F / 360.0F) + 0.5D) & 3;
        int i1 = oworld.c(i, j, k) & 4;

        if (l == 0) {
            oworld.c(i, j, k, 2 | i1);
        }

        if (l == 1) {
            oworld.c(i, j, k, 1 | i1);
        }

        if (l == 2) {
            oworld.c(i, j, k, 3 | i1);
        }

        if (l == 3) {
            oworld.c(i, j, k, 0 | i1);
        }

    }

    public void e(OWorld oworld, int i, int j, int k, int l) {
        if (l == 0) {
            int i1 = oworld.c(i, j, k);

            oworld.c(i, j, k, i1 | 4);
        }

    }
}
