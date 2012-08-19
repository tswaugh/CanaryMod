
public class OEntitySlime extends OEntityLiving implements OIMob {

    public float a;
    public float b;
    public float c;
    private int d = 0;

    // CanaryMod start
    protected LivingEntity entity = new LivingEntity(this);
    // CanaryMod end

    public OEntitySlime(OWorld oworld) {
        super(oworld);
        this.ae = "/mob/slime.png";
        int i = 1 << this.bS.nextInt(3);

        this.bF = 0.0F;
        this.d = this.bS.nextInt(20) + 10;
        this.c(i);
    }

    protected void b() {
        super.b();
        this.bY.a(16, new Byte((byte) 1));
    }

    public void c(int i) {
        this.bY.b(16, new Byte((byte) i));
        this.b(0.6F * (float) i, 0.6F * (float) i);
        this.c(this.bm, this.bn, this.bo);
        this.h(this.d());
        this.aA = i;
    }

    public int d() {
        int i = this.L();

        return i * i;
    }

    public int L() {
        return this.bY.a(16);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Size", this.L() - 1);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.c(onbttagcompound.f("Size") + 1);
    }

    protected String A() {
        return "slime";
    }

    protected String I() {
        return "mob.slime";
    }

    public void F_() {
        if (!this.bi.F && this.bi.q == 0 && this.L() > 0) {
            this.bE = true;
        }

        this.b += (this.a - this.b) * 0.5F;
        this.c = this.b;
        boolean flag = this.bx;

        super.F_();
        if (this.bx && !flag) {
            int i = this.L();

            for (int j = 0; j < i * 8; ++j) {
                float f = this.bS.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.bS.nextFloat() * 0.5F + 0.5F;
                float f2 = OMathHelper.a(f) * (float) i * 0.5F * f1;
                float f3 = OMathHelper.b(f) * (float) i * 0.5F * f1;

                this.bi.a(this.A(), this.bm + (double) f2, this.bw.b, this.bo + (double) f3, 0.0D, 0.0D, 0.0D);
            }

            if (this.K()) {
                this.bi.a(this, this.I(), this.p(), ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.a = -0.5F;
        }

        this.F();
    }

    protected void d_() {
        this.aG();
        OEntityPlayer oentityplayer = this.bi.b(this, 16.0D);

        if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentityplayer.entity.getPlayer(), entity)) { // CanaryMod - MOB_TARGET
            this.a(oentityplayer, 10.0F, 20.0F);
        }

        if (this.bx && this.d-- <= 0) {
            this.d = this.E();
            if (oentityplayer != null) {
                this.d /= 3;
            }

            this.aZ = true;
            if (this.M()) {
                this.bi.a(this, this.I(), this.p(), ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.a = 1.0F;
            this.aW = 1.0F - this.bS.nextFloat() * 2.0F;
            this.aX = (float) (1 * this.L());
        } else {
            this.aZ = false;
            if (this.bx) {
                this.aW = this.aX = 0.0F;
            }
        }

    }

    protected void F() {
        this.a *= 0.6F;
    }

    protected int E() {
        return this.bS.nextInt(20) + 10;
    }

    protected OEntitySlime C() {
        return new OEntitySlime(this.bi);
    }

    public void X() {
        int i = this.L();

        if (!this.bi.F && i > 1 && this.aD() <= 0) {
            int j = 2 + this.bS.nextInt(3);

            for (int k = 0; k < j; ++k) {
                float f = ((float) (k % 2) - 0.5F) * (float) i / 4.0F;
                float f1 = ((float) (k / 2) - 0.5F) * (float) i / 4.0F;
                OEntitySlime oentityslime = this.C();

                oentityslime.c(i / 2);
                oentityslime.c(this.bm + (double) f, this.bn + 0.5D, this.bo + (double) f1, this.bS.nextFloat() * 360.0F, 0.0F);
                this.bi.b((OEntity) oentityslime);
            }
        }

        super.X();
    }

    public void a_(OEntityPlayer oentityplayer) {
        if (this.G()) {
            int i = this.L();

            if (this.h(oentityplayer) && (double) this.i(oentityplayer) < 0.6D * (double) i && oentityplayer.a(ODamageSource.a((OEntityLiving) this), this.H())) {
                this.bi.a(this, "mob.slimeattack", 1.0F, (this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F);
            }
        }

    }

    protected boolean G() {
        return this.L() > 1;
    }

    protected int H() {
        return this.L();
    }

    protected String j() {
        return "mob.slime";
    }

    protected String k() {
        return "mob.slime";
    }

    protected int f() {
        return this.L() == 1 ? OItem.aL.bP : 0;
    }

    public boolean l() {
        OChunk ochunk = this.bi.c(OMathHelper.b(this.bm), OMathHelper.b(this.bo));

        return (this.L() == 1 || this.bi.q > 0) && this.bS.nextInt(10) == 0 && ochunk.a(987234911L).nextInt(10) == 0 && this.bn < 40.0D ? super.l() : false;
    }

    protected float p() {
        return 0.4F * (float) this.L();
    }

    public int D() {
        return 0;
    }

    protected boolean M() {
        return this.L() > 1;
    }

    protected boolean K() {
        return this.L() > 2;
    }
}
