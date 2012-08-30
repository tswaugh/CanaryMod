
public class OEntitySlime extends OEntityLiving implements OIMob {

    public float a;
    public float b;
    public float c;
    private int d = 0;

    public OEntitySlime(OWorld oworld) {
        super(oworld);
        this.az = "/mob/slime.png";
        int i = 1 << this.Z.nextInt(3);

        this.M = 0.0F;
        this.d = this.Z.nextInt(20) + 10;
        this.a(i);
    }

    protected void a() {
        super.a();
        this.af.a(16, new Byte((byte) 1));
    }

    public void a(int i) {
        this.af.b(16, new Byte((byte) i));
        this.a(0.6F * (float) i, 0.6F * (float) i);
        this.b(this.t, this.u, this.v);
        this.j(this.aM());
        this.aV = i;
    }

    public int aM() {
        int i = this.q();

        return i * i;
    }

    public int q() {
        return this.af.a(16);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Size", this.q() - 1);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a(onbttagcompound.e("Size") + 1);
    }

    protected String i() {
        return "slime";
    }

    protected String o() {
        return "mob.slime";
    }

    public void h_() {
        if (!this.p.K && this.p.u == 0 && this.q() > 0) {
            this.L = true;
        }

        this.b += (this.a - this.b) * 0.5F;
        this.c = this.b;
        boolean flag = this.E;

        super.h_();
        if (this.E && !flag) {
            int i = this.q();

            for (int j = 0; j < i * 8; ++j) {
                float f = this.Z.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.Z.nextFloat() * 0.5F + 0.5F;
                float f2 = OMathHelper.a(f) * (float) i * 0.5F * f1;
                float f3 = OMathHelper.b(f) * (float) i * 0.5F * f1;

                this.p.a(this.i(), this.t + (double) f2, this.D.b, this.v + (double) f3, 0.0D, 0.0D, 0.0D);
            }

            if (this.p()) {
                this.p.a(this, this.o(), this.aP(), ((this.Z.nextFloat() - this.Z.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.a = -0.5F;
        } else if (!this.E && flag) {
            this.a = 1.0F;
        }

        this.l();
    }

    protected void be() {
        this.bb();
        OEntityPlayer oentityplayer = this.p.b(this, 16.0D);

        if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentityplayer.entity.getPlayer(), this.entity)) { // CanaryMod - MOB_TARGET
            this.a(oentityplayer, 10.0F, 20.0F);
        }

        if (this.E && this.d-- <= 0) {
            this.d = this.k();
            if (oentityplayer != null) {
                this.d /= 3;
            }

            this.bu = true;
            if (this.r()) {
                this.p.a(this, this.o(), this.aP(), ((this.Z.nextFloat() - this.Z.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.br = 1.0F - this.Z.nextFloat() * 2.0F;
            this.bs = (float) (1 * this.q());
        } else {
            this.bu = false;
            if (this.E) {
                this.br = this.bs = 0.0F;
            }
        }

    }

    protected void l() {
        this.a *= 0.6F;
    }

    protected int k() {
        return this.Z.nextInt(20) + 10;
    }

    protected OEntitySlime j() {
        return new OEntitySlime(this.p);
    }

    public void y() {
        int i = this.q();

        if (!this.p.K && i > 1 && this.aN() <= 0) {
            int j = 2 + this.Z.nextInt(3);

            for (int k = 0; k < j; ++k) {
                float f = ((float) (k % 2) - 0.5F) * (float) i / 4.0F;
                float f1 = ((float) (k / 2) - 0.5F) * (float) i / 4.0F;
                OEntitySlime oentityslime = this.j();

                oentityslime.a(i / 2);
                oentityslime.b(this.t + (double) f, this.u + 0.5D, this.v + (double) f1, this.Z.nextFloat() * 360.0F, 0.0F);
                this.p.d((OEntity) oentityslime);
            }
        }

        super.y();
    }

    public void b_(OEntityPlayer oentityplayer) {
        if (this.m()) {
            int i = this.q();

            if (this.l(oentityplayer) && this.e(oentityplayer) < 0.6D * (double) i * 0.6D * (double) i && oentityplayer.a(ODamageSource.a((OEntityLiving) this), this.n())) {
                this.p.a(this, "mob.slimeattack", 1.0F, (this.Z.nextFloat() - this.Z.nextFloat()) * 0.2F + 1.0F);
            }
        }

    }

    protected boolean m() {
        return this.q() > 1;
    }

    protected int n() {
        return this.q();
    }

    protected String aR() {
        return "mob.slime";
    }

    protected String aS() {
        return "mob.slime";
    }

    protected int aT() {
        return this.q() == 1 ? OItem.aM.bT : 0;
    }

    public boolean bi() {
        OChunk ochunk = this.p.d(OMathHelper.c(this.t), OMathHelper.c(this.v));

        return this.p.H().t() == OWorldType.c && this.Z.nextInt(4) != 1 ? false : ((this.q() == 1 || this.p.u > 0) && this.Z.nextInt(10) == 0 && ochunk.a(987234911L).nextInt(10) == 0 && this.u < 40.0D ? super.bi() : false);
    }

    protected float aP() {
        return 0.4F * (float) this.q();
    }

    public int bf() {
        return 0;
    }

    protected boolean r() {
        return this.q() > 1;
    }

    protected boolean p() {
        return this.q() > 2;
    }
}
