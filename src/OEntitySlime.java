public class OEntitySlime extends OEntityLiving implements OIMob {

    public float b;
    public float c;
    public float d;
    private int e = 0;

    public OEntitySlime(OWorld oworld) {
        super(oworld);
        this.aG = "/mob/slime.png";
        int i = 1 << this.aa.nextInt(3);

        this.M = 0.0F;
        this.e = this.aa.nextInt(20) + 10;
        this.a(i);
    }

    protected void a() {
        super.a();
        this.ag.a(16, new Byte((byte) 1));
    }

    protected void a(int i) {
        this.ag.b(16, new Byte((byte) i));
        this.a(0.6F * (float) i, 0.6F * (float) i);
        this.b(this.t, this.u, this.v);
        this.j(this.aT());
        this.bd = i;
    }

    public int aT() {
        int i = this.p();
        return this.maxHealth == 0 ? (i * i) : this.maxHealth;
    }

    public int p() {
        return this.ag.a(16);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Size", this.p() - 1);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a(onbttagcompound.e("Size") + 1);
    }

    protected String h() {
        return "slime";
    }

    protected String n() {
        return "mob.slime." + (this.p() > 1 ? "big" : "small");
    }

    public void j_() {
        if (!this.p.I && this.p.s == 0 && this.p() > 0) {
            this.L = true;
        }

        this.c += (this.b - this.c) * 0.5F;
        this.d = this.c;
        boolean flag = this.E;

        super.j_();
        int i;

        if (this.E && !flag) {
            i = this.p();

            for (int j = 0; j < i * 8; ++j) {
                float f = this.aa.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.aa.nextFloat() * 0.5F + 0.5F;
                float f2 = OMathHelper.a(f) * (float) i * 0.5F * f1;
                float f3 = OMathHelper.b(f) * (float) i * 0.5F * f1;

                this.p.a(this.h(), this.t + (double) f2, this.D.b, this.v + (double) f3, 0.0D, 0.0D, 0.0D);
            }

            if (this.o()) {
                this.a(this.n(), this.aX(), ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.b = -0.5F;
        } else if (!this.E && flag) {
            this.b = 1.0F;
        }

        this.k();
        if (this.p.I) {
            i = this.p();
            this.a(0.6F * (float) i, 0.6F * (float) i);
        }
    }

    protected void bn() {
        this.bk();
        OEntityPlayer oentityplayer = this.p.b(this, 16.0D);

        if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentityplayer.getEntity(), this.getEntity())) { // CanaryMod - MOB_TARGET
            this.a(oentityplayer, 10.0F, 20.0F);
        }

        if (this.E && this.e-- <= 0) {
            this.e = this.j();
            if (oentityplayer != null) {
                this.e /= 3;
            }

            this.bF = true;
            if (this.q()) {
                this.a(this.n(), this.aX(), ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.bC = 1.0F - this.aa.nextFloat() * 2.0F;
            this.bD = (float) (1 * this.p());
        } else {
            this.bF = false;
            if (this.E) {
                this.bC = this.bD = 0.0F;
            }
        }
    }

    protected void k() {
        this.b *= 0.6F;
    }

    protected int j() {
        return this.aa.nextInt(20) + 10;
    }

    protected OEntitySlime i() {
        return new OEntitySlime(this.p);
    }

    public void x() {
        int i = this.p();

        if (!this.p.I && i > 1 && this.aU() <= 0) {
            int j = 2 + this.aa.nextInt(3);

            for (int k = 0; k < j; ++k) {
                float f = ((float) (k % 2) - 0.5F) * (float) i / 4.0F;
                float f1 = ((float) (k / 2) - 0.5F) * (float) i / 4.0F;
                OEntitySlime oentityslime = this.i();

                oentityslime.a(i / 2);
                oentityslime.b(this.t + (double) f, this.u + 0.5D, this.v + (double) f1, this.aa.nextFloat() * 360.0F, 0.0F);
                this.p.d((OEntity) oentityslime);
            }
        }

        super.x();
    }

    public void c_(OEntityPlayer oentityplayer) {
        if (this.l()) {
            int i = this.p();

            if (this.n(oentityplayer) && this.e(oentityplayer) < 0.6D * (double) i * 0.6D * (double) i && oentityplayer.a(ODamageSource.a((OEntityLiving) this), this.m())) {
                this.a("mob.attack", 1.0F, (this.aa.nextFloat() - this.aa.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    protected boolean l() {
        return this.p() > 1;
    }

    protected int m() {
        return this.p();
    }

    protected String aZ() {
        return "mob.slime." + (this.p() > 1 ? "big" : "small");
    }

    protected String ba() {
        return "mob.slime." + (this.p() > 1 ? "big" : "small");
    }

    protected int bb() {
        return this.p() == 1 ? OItem.aM.cj : 0;
    }

    public boolean bs() {
        OChunk ochunk = this.p.d(OMathHelper.c(this.t), OMathHelper.c(this.v));

        if (this.p.K().u() == OWorldType.c && this.aa.nextInt(4) != 1) {
            return false;
        } else {
            if (this.p() == 1 || this.p.s > 0) {
                if (this.p.a(OMathHelper.c(this.t), OMathHelper.c(this.v)) == OBiomeGenBase.h && this.u > 50.0D && this.u < 70.0D && this.p.m(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v)) <= this.aa.nextInt(8)) {
                    return super.bs();
                }

                if (this.aa.nextInt(10) == 0 && ochunk.a(987234911L).nextInt(10) == 0 && this.u < 40.0D) {
                    return super.bs();
                }
            }

            return false;
        }
    }

    protected float aX() {
        return 0.4F * (float) this.p();
    }

    public int bp() {
        return 0;
    }

    protected boolean q() {
        return this.p() > 0;
    }

    protected boolean o() {
        return this.p() > 2;
    }
}
