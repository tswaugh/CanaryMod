import java.util.List;


public class OEntityFireball extends OEntity {

    private int e = -1;
    private int f = -1;
    private int g = -1;
    private int h = 0;
    private boolean i = false;
    public OEntityLiving a;
    private int j;
    private int k = 0;
    public double b;
    public double c;
    public double d;

    public OEntityFireball(OWorld var1) {
        super(var1);
        this.b(1.0F, 1.0F);
    }

    protected void b() {}

    public OEntityFireball(OWorld var1, OEntityLiving var2, double var3, double var5, double var7) {
        super(var1);
        this.a = var2;
        this.b(1.0F, 1.0F);
        this.c(var2.bj, var2.bk, var2.bl, var2.bp, var2.bq);
        this.c(this.bj, this.bk, this.bl);
        this.bC = 0.0F;
        this.bm = this.bn = this.bo = 0.0D;
        var3 += this.bP.nextGaussian() * 0.4D;
        var5 += this.bP.nextGaussian() * 0.4D;
        var7 += this.bP.nextGaussian() * 0.4D;
        double var9 = (double) OMathHelper.a(var3 * var3 + var5 * var5 + var7 * var7);

        this.b = var3 / var9 * 0.1D;
        this.c = var5 / var9 * 0.1D;
        this.d = var7 / var9 * 0.1D;
    }

    public void w_() {
        super.w_();
        this.j(1);
        if (!this.bf.I && (this.a == null || this.a.bB)) {
            this.S();
        }

        if (this.i) {
            int var1 = this.bf.a(this.e, this.f, this.g);

            if (var1 == this.h) {
                ++this.j;
                if (this.j == 1200) {
                    this.S();
                }

                return;
            }

            this.i = false;
            this.bm *= (double) (this.bP.nextFloat() * 0.2F);
            this.bn *= (double) (this.bP.nextFloat() * 0.2F);
            this.bo *= (double) (this.bP.nextFloat() * 0.2F);
            this.j = 0;
            this.k = 0;
        } else {
            ++this.k;
        }

        OVec3D var15 = OVec3D.b(this.bj, this.bk, this.bl);
        OVec3D var2 = OVec3D.b(this.bj + this.bm, this.bk + this.bn, this.bl + this.bo);
        OMovingObjectPosition var3 = this.bf.a(var15, var2);

        var15 = OVec3D.b(this.bj, this.bk, this.bl);
        var2 = OVec3D.b(this.bj + this.bm, this.bk + this.bn, this.bl + this.bo);
        if (var3 != null) {
            var2 = OVec3D.b(var3.f.a, var3.f.b, var3.f.c);
        }

        OEntity var4 = null;
        List var5 = this.bf.b((OEntity) this, this.bt.a(this.bm, this.bn, this.bo).b(1.0D, 1.0D, 1.0D));
        double var6 = 0.0D;

        for (int var8 = 0; var8 < var5.size(); ++var8) {
            OEntity var9 = (OEntity) var5.get(var8);

            if (var9.e_() && (!var9.a((OEntity) this.a) || this.k >= 25)) {
                float var10 = 0.3F;
                OAxisAlignedBB var11 = var9.bt.b((double) var10, (double) var10, (double) var10);
                OMovingObjectPosition var12 = var11.a(var15, var2);

                if (var12 != null) {
                    double var13 = var15.b(var12.f);

                    if (var13 < var6 || var6 == 0.0D) {
                        var4 = var9;
                        var6 = var13;
                    }
                }
            }
        }

        if (var4 != null) {
            var3 = new OMovingObjectPosition(var4);
        }

        if (var3 != null) {
            this.a(var3);
        }

        this.bj += this.bm;
        this.bk += this.bn;
        this.bl += this.bo;
        float var16 = OMathHelper.a(this.bm * this.bm + this.bo * this.bo);

        this.bp = (float) (Math.atan2(this.bm, this.bo) * 180.0D / 3.1415927410125732D);

        for (this.bq = (float) (Math.atan2(this.bn, (double) var16) * 180.0D / 3.1415927410125732D); this.bq - this.bs < -180.0F; this.bs -= 360.0F) {
            ;
        }

        while (this.bq - this.bs >= 180.0F) {
            this.bs += 360.0F;
        }

        while (this.bp - this.br < -180.0F) {
            this.br -= 360.0F;
        }

        while (this.bp - this.br >= 180.0F) {
            this.br += 360.0F;
        }

        this.bq = this.bs + (this.bq - this.bs) * 0.2F;
        this.bp = this.br + (this.bp - this.br) * 0.2F;
        float var17 = 0.95F;

        if (this.az()) {
            for (int var19 = 0; var19 < 4; ++var19) {
                float var18 = 0.25F;

                this.bf.a("bubble", this.bj - this.bm * (double) var18, this.bk - this.bn * (double) var18, this.bl - this.bo * (double) var18, this.bm, this.bn, this.bo);
            }

            var17 = 0.8F;
        }

        this.bm += this.b;
        this.bn += this.c;
        this.bo += this.d;
        this.bm *= (double) var17;
        this.bn *= (double) var17;
        this.bo *= (double) var17;
        this.bf.a("smoke", this.bj, this.bk + 0.5D, this.bl, 0.0D, 0.0D, 0.0D);
        this.c(this.bj, this.bk, this.bl);
    }

    protected void a(OMovingObjectPosition var1) {
        if (!this.bf.I) {
            if (var1.g != null && var1.g.a(ODamageSource.a(this, this.a), 4)) {
                ;
            }

            this.bf.a((OEntity) null, this.bj, this.bk, this.bl, 1.0F, true);
            this.S();
        }

    }

    public void b(ONBTTagCompound var1) {
        var1.a("xTile", (short) this.e);
        var1.a("yTile", (short) this.f);
        var1.a("zTile", (short) this.g);
        var1.a("inTile", (byte) this.h);
        var1.a("inGround", (byte) (this.i ? 1 : 0));
    }

    public void a(ONBTTagCompound var1) {
        this.e = var1.e("xTile");
        this.f = var1.e("yTile");
        this.g = var1.e("zTile");
        this.h = var1.d("inTile") & 255;
        this.i = var1.d("inGround") == 1;
    }

    public boolean e_() {
        return true;
    }

    public float j_() {
        return 1.0F;
    }

    public boolean a(ODamageSource var1, int var2) {
        this.aB();
        if (var1.a() != null) {
            OVec3D var3 = var1.a().ap();

            if (var3 != null) {
                this.bm = var3.a;
                this.bn = var3.b;
                this.bo = var3.c;
                this.b = this.bm * 0.1D;
                this.c = this.bn * 0.1D;
                this.d = this.bo * 0.1D;
            }

            if (var1.a() instanceof OEntityLiving) {
                this.a = (OEntityLiving) var1.a();
            }

            return true;
        } else {
            return false;
        }
    }

    public float a(float var1) {
        return 1.0F;
    }
}
