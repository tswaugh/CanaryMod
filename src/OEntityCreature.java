
public abstract class OEntityCreature extends OEntityLiving {

    private OPathEntity d;
    protected OEntity a;
    protected boolean b = false;
    protected int c = 0;

    public OEntityCreature(OWorld oworld) {
        super(oworld);
    }

    protected boolean i() {
        return false;
    }

    protected void be() {
        this.p.F.a("ai");
        if (this.c > 0) {
            --this.c;
        }

        this.b = this.i();
        float f = 16.0F;

        if (this.a == null) {
            this.a = this.k();
            if (this.a != null) {
                this.d = this.p.a(this, this.a, f, true, false, false, true);
            }
        } else if (this.a.S()) {
            float f1 = this.a.d((OEntity) this);

            if (this.l(this.a)) {
                this.a(this.a, f1);
            }
        } else {
            this.a = null;
        }

        this.p.F.b();
        if (!this.b && this.a != null && (this.d == null || this.Z.nextInt(20) == 0)) {
            this.d = this.p.a(this, this.a, f, true, false, false, true);
        } else if (!this.b && (this.d == null && this.Z.nextInt(180) == 0 || this.Z.nextInt(120) == 0 || this.c > 0) && this.bq < 100) {
            this.j();
        }

        int i = OMathHelper.c(this.D.b + 0.5D);
        boolean flag = this.H();
        boolean flag1 = this.J();

        this.A = 0.0F;
        if (this.d != null && this.Z.nextInt(100) != 0) {
            this.p.F.a("followpath");
            OVec3 ovec3 = this.d.a((OEntity) this);
            double d0 = (double) (this.N * 2.0F);

            while (ovec3 != null && ovec3.d(this.t, ovec3.b, this.v) < d0 * d0) {
                this.d.a();
                if (this.d.b()) {
                    ovec3 = null;
                    this.d = null;
                } else {
                    ovec3 = this.d.a((OEntity) this);
                }
            }

            this.bu = false;
            if (ovec3 != null) {
                double d1 = ovec3.a - this.t;
                double d2 = ovec3.c - this.v;
                double d3 = ovec3.b - (double) i;
                float f2 = (float) (Math.atan2(d2, d1) * 180.0D / 3.1415927410125732D) - 90.0F;
                float f3 = OMathHelper.g(f2 - this.z);

                this.bs = this.bw;
                if (f3 > 30.0F) {
                    f3 = 30.0F;
                }

                if (f3 < -30.0F) {
                    f3 = -30.0F;
                }

                this.z += f3;
                if (this.b && this.a != null) {
                    double d4 = this.a.t - this.t;
                    double d5 = this.a.v - this.v;
                    float f4 = this.z;

                    this.z = (float) (Math.atan2(d5, d4) * 180.0D / 3.1415927410125732D) - 90.0F;
                    f3 = (f4 - this.z + 90.0F) * 3.1415927F / 180.0F;
                    this.br = -OMathHelper.a(f3) * this.bs * 1.0F;
                    this.bs = OMathHelper.b(f3) * this.bs * 1.0F;
                }

                if (d3 > 0.0D) {
                    this.bu = true;
                }
            }

            if (this.a != null) {
                this.a(this.a, 30.0F, 30.0F);
            }

            if (this.F && !this.l()) {
                this.bu = true;
            }

            if (this.Z.nextFloat() < 0.8F && (flag || flag1)) {
                this.bu = true;
            }

            this.p.F.b();
        } else {
            super.be();
            this.d = null;
        }
    }

    protected void j() {
        this.p.F.a("stroll");
        boolean flag = false;
        int i = -1;
        int j = -1;
        int k = -1;
        float f = -99999.0F;

        for (int l = 0; l < 10; ++l) {
            int i1 = OMathHelper.c(this.t + (double) this.Z.nextInt(13) - 6.0D);
            int j1 = OMathHelper.c(this.u + (double) this.Z.nextInt(7) - 3.0D);
            int k1 = OMathHelper.c(this.v + (double) this.Z.nextInt(13) - 6.0D);
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
            this.d = this.p.a(this, i, j, k, 10.0F, true, false, false, true);
        }

        this.p.F.b();
    }

    protected void a(OEntity oentity, float f) {}

    public float a(int i, int j, int k) {
        return 0.0F;
    }

    protected OEntity k() {
        return null;
    }

    public boolean bi() {
        int i = OMathHelper.c(this.t);
        int j = OMathHelper.c(this.D.b);
        int k = OMathHelper.c(this.v);

        return super.bi() && this.a(i, j, k) >= 0.0F;
    }

    public boolean l() {
        return this.d != null;
    }

    public void a(OPathEntity opathentity) {
        this.d = opathentity;
    }

    public OEntity m() {
        return this.a;
    }

    public void b(OEntity oentity) {
        this.a = oentity;
    }

    protected float bs() {
        if (this.aV()) {
            return 1.0F;
        } else {
            float f = super.bs();

            if (this.c > 0) {
                f *= 2.0F;
            }

            return f;
        }
    }
}
