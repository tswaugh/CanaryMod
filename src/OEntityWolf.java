public class OEntityWolf extends OEntityTameable {

    private float e;
    private float f;
    private boolean g;
    private boolean h;
    private float i;
    private float j;

    public OEntityWolf(OWorld oworld) {
        super(oworld);
        this.az = "/mob/wolf.png";
        this.a(0.6F, 0.8F);
        this.bw = 0.3F;
        this.as().a(true);
        this.bg.a(1, new OEntityAISwimming(this));
        this.bg.a(2, this.d);
        this.bg.a(3, new OEntityAILeapAtTarget(this, 0.4F));
        this.bg.a(4, new OEntityAIAttackOnCollide(this, this.bw, true));
        this.bg.a(5, new OEntityAIFollowOwner(this, this.bw, 10.0F, 2.0F));
        this.bg.a(6, new OEntityAIMate(this, this.bw));
        this.bg.a(7, new OEntityAIWander(this, this.bw));
        this.bg.a(8, new OEntityAIBeg(this, 8.0F));
        this.bg.a(9, new OEntityAIWatchClosest(this, OEntityPlayer.class, 8.0F));
        this.bg.a(9, new OEntityAILookIdle(this));
        this.bh.a(1, new OEntityAIOwnerHurtByTarget(this));
        this.bh.a(2, new OEntityAIOwnerHurtTarget(this));
        this.bh.a(3, new OEntityAIHurtByTarget(this, true));
        this.bh.a(4, new OEntityAITargetNonTamed(this, OEntitySheep.class, 16.0F, 200, false));
    }

    public boolean aV() {
        return true;
    }

    public void b(OEntityLiving oentityliving) {
        super.b(oentityliving);
        if (oentityliving instanceof OEntityPlayer) {
            this.h(true);
        }
    }

    protected void bd() {
        this.af.b(18, Integer.valueOf(this.aN()));
    }

    public int aM() {
        return this.n() ? 20 : 8;
    }

    protected void a() {
        super.a();
        this.af.a(18, new Integer(this.aN()));
        this.af.a(19, new Byte((byte) 0));
    }

    protected boolean e_() {
        return false;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Angry", this.bu());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.h(onbttagcompound.n("Angry"));
    }

    protected boolean ba() {
        return this.bu();
    }

    protected String aQ() {
        return this.bu() ? "mob.wolf.growl" : (this.Z.nextInt(3) == 0 ? (this.n() && this.af.c(18) < 10 ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String aR() {
        return "mob.wolf.hurt";
    }

    protected String aS() {
        return "mob.wolf.death";
    }

    protected float aP() {
        return 0.4F;
    }

    protected int aT() {
        return -1;
    }

    public void d() {
        super.d();
        if (!this.p.K && this.g && !this.h && !this.l() && this.E) {
            this.h = true;
            this.i = 0.0F;
            this.j = 0.0F;
            this.p.a(this, (byte) 8);
        }
    }

    public void h_() {
        super.h_();
        this.f = this.e;
        if (this.bv()) {
            this.e += (1.0F - this.e) * 0.4F;
        } else {
            this.e += (0.0F - this.e) * 0.4F;
        }

        if (this.bv()) {
            this.bx = 10;
        }

        if (this.G()) {
            this.g = true;
            this.h = false;
            this.i = 0.0F;
            this.j = 0.0F;
        } else if ((this.g || this.h) && this.h) {
            if (this.i == 0.0F) {
                this.p.a(this, "mob.wolf.shake", this.aP(), (this.Z.nextFloat() - this.Z.nextFloat()) * 0.2F + 1.0F);
            }

            this.j = this.i;
            this.i += 0.05F;
            if (this.j >= 2.0F) {
                this.g = false;
                this.h = false;
                this.j = 0.0F;
                this.i = 0.0F;
            }

            if (this.i > 0.4F) {
                float f = (float) this.D.b;
                int i = (int) (OMathHelper.a((this.i - 0.4F) * 3.1415927F) * 7.0F);

                for (int j = 0; j < i; ++j) {
                    float f1 = (this.Z.nextFloat() * 2.0F - 1.0F) * this.N * 0.5F;
                    float f2 = (this.Z.nextFloat() * 2.0F - 1.0F) * this.N * 0.5F;

                    this.p.a("splash", this.t + (double) f1, (double) (f + 0.8F), this.v + (double) f2, this.w, this.x, this.y);
                }
            }
        }
    }

    public float e() {
        return this.O * 0.8F;
    }

    public int bf() {
        return this.o() ? 20 : super.bf();
    }

    public boolean a(ODamageSource odamagesource, int i) {
        OEntity oentity = odamagesource.g();

        this.d.a(false);
        if (oentity != null && !(oentity instanceof OEntityPlayer) && !(oentity instanceof OEntityArrow)) {
            i = (i + 1) / 2;
        }

        return super.a(odamagesource, i);
    }

    public boolean k(OEntity oentity) {
        int i = this.n() ? 4 : 2;

        return oentity.a(ODamageSource.a((OEntityLiving) this), i);
    }

    public boolean c(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.by.g();

        if (this.n()) {
            if (oitemstack != null && OItem.e[oitemstack.c] instanceof OItemFood) {
                OItemFood oitemfood = (OItemFood) OItem.e[oitemstack.c];

                if (oitemfood.h() && this.af.c(18) < 20) {
                    if (!oentityplayer.bZ.d) {
                        --oitemstack.a;
                    }

                    this.i(oitemfood.f());
                    if (oitemstack.a <= 0) {
                        oentityplayer.by.a(oentityplayer.by.c, (OItemStack) null);
                    }

                    return true;
                }
            }

            if (oentityplayer.bJ.equalsIgnoreCase(this.p()) && !this.p.K && !this.b(oitemstack)) {
                this.d.a(!this.o());
                this.bu = false;
                this.a((OPathEntity) null);
            }
        } else if (oitemstack != null && oitemstack.c == OItem.aX.bT && !this.bu()) {
            if (!oentityplayer.bZ.d) {
                --oitemstack.a;
            }

            if (oitemstack.a <= 0) {
                oentityplayer.by.a(oentityplayer.by.c, (OItemStack) null);
            }

            if (!this.p.K) {
                // CanaryMod hook: onTame
                // randomize the tame result. if its 0 - tame success.
                int tameResult = this.Z.nextInt(3);
                // Call hook
                PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.TAME, oentityplayer.entity.getPlayer(), new Mob(this), tameResult == 0);
                
                // if taming succeeded normally (tameResult == 0) or plugin hook result is allow (force taming)
                if (tameResult == 0 && res == PluginLoader.HookResult.DEFAULT_ACTION || res == PluginLoader.HookResult.ALLOW_ACTION) {
                    this.f(true);
                    this.a((OPathEntity) null);
                    this.b((OEntityLiving) null);
                    this.d.a(true);
                    this.j(20);
                    this.a(oentityplayer.bJ);
                    this.e(true);
                    this.p.a(this, (byte) 7);
                } else {
                    this.e(false);
                    this.p.a(this, (byte) 6);
                }
            }

            return true;
        }

        return super.c(oentityplayer);
    }

    public boolean b(OItemStack oitemstack) {
        return oitemstack == null ? false : (!(OItem.e[oitemstack.c] instanceof OItemFood) ? false : ((OItemFood) OItem.e[oitemstack.c]).h());
    }

    public int bl() {
        return 8;
    }

    public boolean bu() {
        return (this.af.a(16) & 2) != 0;
    }

    public void h(boolean flag) {
        byte b0 = this.af.a(16);

        if (flag) {
            this.af.b(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.af.b(16, Byte.valueOf((byte) (b0 & -3)));
        }
    }

    public OEntityAnimal a(OEntityAnimal oentityanimal) {
        OEntityWolf oentitywolf = new OEntityWolf(this.p);

        oentitywolf.a(this.p());
        oentitywolf.f(true);
        return oentitywolf;
    }

    public void i(boolean flag) {
        byte b0 = this.af.a(19);

        if (flag) {
            this.af.b(19, Byte.valueOf((byte) 1));
        } else {
            this.af.b(19, Byte.valueOf((byte) 0));
        }
    }

    public boolean b(OEntityAnimal oentityanimal) {
        if (oentityanimal == this) {
            return false;
        } else if (!this.n()) {
            return false;
        } else if (!(oentityanimal instanceof OEntityWolf)) {
            return false;
        } else {
            OEntityWolf oentitywolf = (OEntityWolf) oentityanimal;

            return !oentitywolf.n() ? false : (oentitywolf.o() ? false : this.s() && oentitywolf.s());
        }
    }

    public boolean bv() {
        return this.af.a(19) == 1;
    }
}
