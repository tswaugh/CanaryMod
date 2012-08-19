
public abstract class OEntityCreature extends OEntityLiving {

    private OPathEntity a;
    protected OEntity d;
    protected boolean e = false;
    protected int f = 0;

    public OEntityCreature(OWorld oworld) {
        super(oworld);
    }

    protected boolean F() {
        return false;
    }

    protected void d_() {
        OProfiler.a("ai"); // CanaryMod jarjar fix
        if (this.f > 0) {
            --this.f;
        }

        this.e = this.F();
        float f = 16.0F;

        if (this.d == null) {
            this.d = this.o();
            if (this.d != null) {
                this.a = this.bi.a(this, this.d, f, true, false, false, true);
            }
        } else if (!this.d.aE()) {
            this.d = null;
        } else {
            float f1 = this.d.i(this);

            if (this.h(this.d)) {
                this.a(this.d, f1);
            } else {
                this.b(this.d, f1);
            }
        }

        OProfiler.a();
        if (!this.e && this.d != null && (this.a == null || this.bS.nextInt(20) == 0)) {
            this.a = this.bi.a(this, this.d, f, true, false, false, true);
        } else if (!this.e && (this.a == null && this.bS.nextInt(180) == 0 || this.bS.nextInt(120) == 0 || this.f > 0) && this.aV < 100) {
            this.G();
        }

        int i = OMathHelper.b(this.bw.b + 0.5D);
        boolean flag = this.aU();
        boolean flag1 = this.aV();

        this.bt = 0.0F;
        if (this.a != null && this.bS.nextInt(100) != 0) {
            OProfiler.a("followpath");
            OVec3D ovec3d = this.a.a((OEntity) this);
            double d0 = (double) (this.bG * 2.0F);

            while (ovec3d != null && ovec3d.d(this.bm, ovec3d.b, this.bo) < d0 * d0) {
                this.a.a();
                if (this.a.b()) {
                    ovec3d = null;
                    this.a = null;
                } else {
                    ovec3d = this.a.a((OEntity) this);
                }
            }

            this.aZ = false;
            if (ovec3d != null) {
                double d1 = ovec3d.a - this.bm;
                double d2 = ovec3d.c - this.bo;
                double d3 = ovec3d.b - (double) i;
                float f2 = (float) (Math.atan2(d2, d1) * 180.0D / 3.1415927410125732D) - 90.0F;
                float f3 = f2 - this.bs;

                for (this.aX = this.bb; f3 < -180.0F; f3 += 360.0F) {
                    ;
                }

                while (f3 >= 180.0F) {
                    f3 -= 360.0F;
                }

                if (f3 > 30.0F) {
                    f3 = 30.0F;
                }

                if (f3 < -30.0F) {
                    f3 = -30.0F;
                }

                this.bs += f3;
                if (this.e && this.d != null) {
                    double d4 = this.d.bm - this.bm;
                    double d5 = this.d.bo - this.bo;
                    float f4 = this.bs;

                    this.bs = (float) (Math.atan2(d5, d4) * 180.0D / 3.1415927410125732D) - 90.0F;
                    f3 = (f4 - this.bs + 90.0F) * 3.1415927F / 180.0F;
                    this.aW = -OMathHelper.a(f3) * this.aX * 1.0F;
                    this.aX = OMathHelper.b(f3) * this.aX * 1.0F;
                }

                if (d3 > 0.0D) {
                    this.aZ = true;
                }
            }

            if (this.d != null) {
                this.a(this.d, 30.0F, 30.0F);
            }

            if (this.by && !this.H()) {
                this.aZ = true;
            }

            if (this.bS.nextFloat() < 0.8F && (flag || flag1)) {
                this.aZ = true;
            }

            OProfiler.a();
        } else {
            super.d_();
            this.a = null;
        }
    }

    protected void G() {
        OProfiler.a("stroll");
        boolean flag = false;
        int i = -1;
        int j = -1;
        int k = -1;
        float f = -99999.0F;

        for (int l = 0; l < 10; ++l) {
            int i1 = OMathHelper.b(this.bm + (double) this.bS.nextInt(13) - 6.0D);
            int j1 = OMathHelper.b(this.bn + (double) this.bS.nextInt(7) - 3.0D);
            int k1 = OMathHelper.b(this.bo + (double) this.bS.nextInt(13) - 6.0D);
            float f1 = this.a(i1, j1, k1);

            if (f1 > f) {
                f = f1;
                i = i1;
                j = j1;
                k = k1;
                flag = true;
            }
        }

        if (flag) {
            this.a = this.bi.a(this, i, j, k, 10.0F, true, false, false, true);
        }

        OProfiler.a();
    }

    protected void a(OEntity oentity, float f) {}

    protected void b(OEntity oentity, float f) {}

    public float a(int i, int j, int k) {
        return 0.0F;
    }

    protected OEntity o() {
        return null;
    }

    public boolean l() {
        int i = OMathHelper.b(this.bm);
        int j = OMathHelper.b(this.bw.b);
        int k = OMathHelper.b(this.bo);

        return super.l() && this.a(i, j, k) >= 0.0F;
    }

    public boolean H() {
        return this.a != null;
    }

    public void a(OPathEntity opathentity) {
        this.a = opathentity;
    }

    public OEntity I() {
        return this.d;
    }

    public void d(OEntity oentity) {
        this.d = oentity;
    }

    protected float J() {
        if (this.c_()) {
            return 1.0F;
        } else {
            float f = super.J();

            if (this.f > 0) {
                f *= 2.0F;
            }

            return f;
        }
    }
}
