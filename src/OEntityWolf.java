public class OEntityWolf extends OEntityTameable {

    private float e;
    private float f;
    private boolean g;
    private boolean h;
    private float i;
    private float j;

    private Wolf wolf = new Wolf(this);

    public OEntityWolf(OWorld oworld) {
        super(oworld);
        this.aF = "/mob/wolf.png";
        this.a(0.6F, 0.8F);
        this.bG = 0.3F;
        this.az().a(true);
        this.bm.a(1, new OEntityAISwimming(this));
        this.bm.a(2, this.d);
        this.bm.a(3, new OEntityAILeapAtTarget(this, 0.4F));
        this.bm.a(4, new OEntityAIAttackOnCollide(this, this.bG, true));
        this.bm.a(5, new OEntityAIFollowOwner(this, this.bG, 10.0F, 2.0F));
        this.bm.a(6, new OEntityAIMate(this, this.bG));
        this.bm.a(7, new OEntityAIWander(this, this.bG));
        this.bm.a(8, new OEntityAIBeg(this, 8.0F));
        this.bm.a(9, new OEntityAIWatchClosest(this, OEntityPlayer.class, 8.0F));
        this.bm.a(9, new OEntityAILookIdle(this));
        this.bn.a(1, new OEntityAIOwnerHurtByTarget(this));
        this.bn.a(2, new OEntityAIOwnerHurtTarget(this));
        this.bn.a(3, new OEntityAIHurtByTarget(this, true));
        this.bn.a(4, new OEntityAITargetNonTamed(this, OEntitySheep.class, 16.0F, 200, false));
    }

    public boolean be() {
        return true;
    }

    public void b(OEntityLiving oentityliving) {
        super.b(oentityliving);
        if (oentityliving instanceof OEntityPlayer) {
            this.i(true);
        }
    }

    protected void bm() {
        this.ag.b(18, Integer.valueOf(this.aU()));
    }

    public int aT() {
        //CanaryMod: set max health here, but check for uninitialized value.
        return this.maxHealth == 0 ? (this.m() ? 20 : 8) : this.maxHealth;
    }

    protected void a() {
        super.a();
        this.ag.a(18, new Integer(this.aU()));
        this.ag.a(19, new Byte((byte) 0));
        this.ag.a(20, new Byte((byte) OBlockCloth.e_(1)));
    }

    protected void a(int i, int j, int k, int l) {
        this.a("mob.wolf.step", 0.15F, 1.0F);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Angry", this.bK());
        onbttagcompound.a("CollarColor", (byte) this.bL());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.i(onbttagcompound.n("Angry"));
        if (onbttagcompound.b("CollarColor")) {
            this.s(onbttagcompound.c("CollarColor"));
        }
    }

    protected boolean bj() {
        return this.bK();
    }

    protected String aY() {
        return this.bK() ? "mob.wolf.growl" : (this.aa.nextInt(3) == 0 ? (this.m() && this.ag.c(18) < 10 ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String aZ() {
        return "mob.wolf.hurt";
    }

    protected String ba() {
        return "mob.wolf.death";
    }

    protected float aX() {
        return 0.4F;
    }

    protected int bb() {
        return -1;
    }

    public void c() {
        super.c();
        if (!this.p.J && this.g && !this.h && !this.k() && this.E) {
            this.h = true;
            this.i = 0.0F;
            this.j = 0.0F;
            this.p.a(this, (byte) 8);
        }
    }

    public void j_() {
        super.j_();
        this.f = this.e;
        if (this.bM()) {
            this.e += (1.0F - this.e) * 0.4F;
        } else {
            this.e += (0.0F - this.e) * 0.4F;
        }

        if (this.bM()) {
            this.bH = 10;
        }

        if (this.G()) {
            this.g = true;
            this.h = false;
            this.i = 0.0F;
            this.j = 0.0F;
        } else if ((this.g || this.h) && this.h) {
            if (this.i == 0.0F) {
                this.a("mob.wolf.shake", this.aX(), (this.aa.nextFloat() - this.aa.nextFloat()) * 0.2F + 1.0F);
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
                    float f1 = (this.aa.nextFloat() * 2.0F - 1.0F) * this.N * 0.5F;
                    float f2 = (this.aa.nextFloat() * 2.0F - 1.0F) * this.N * 0.5F;

                    this.p.a("splash", this.t + (double) f1, (double) (f + 0.8F), this.v + (double) f2, this.w, this.x, this.y);
                }
            }
        }
    }

    public float e() {
        return this.O * 0.8F;
    }

    public int bp() {
        return this.n() ? 20 : super.bp();
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.ar()) {
            return false;
        } else {
            OEntity oentity = odamagesource.g();

            this.d.a(false);
            if (oentity != null && !(oentity instanceof OEntityPlayer) && !(oentity instanceof OEntityArrow)) {
                i = (i + 1) / 2;
            }

            return super.a(odamagesource, i);
        }
    }

    public boolean m(OEntity oentity) {
        int i = this.m() ? 4 : 2;

        return oentity.a(ODamageSource.a((OEntityLiving) this), i);
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bI.g();

        if (this.m()) {
            if (oitemstack != null) {
                if (OItem.e[oitemstack.c] instanceof OItemFood) {
                    OItemFood oitemfood = (OItemFood) OItem.e[oitemstack.c];

                    if (oitemfood.i() && this.ag.c(18) < 20) {
                        if (!oentityplayer.cc.d) {
                            --oitemstack.a;
                        }

                        this.i(oitemfood.g());
                        if (oitemstack.a <= 0) {
                            oentityplayer.bI.a(oentityplayer.bI.c, (OItemStack) null);
                        }

                        return true;
                    }
                } else if (oitemstack.c == OItem.aW.cg) {
                    int i = OBlockCloth.e_(oitemstack.j());

                    if (i != this.bL()) {
                        this.s(i);
                        if (!oentityplayer.cc.d && --oitemstack.a <= 0) {
                            oentityplayer.bI.a(oentityplayer.bI.c, (OItemStack) null);
                        }

                        return true;
                    }
                }
            }

            if (oentityplayer.bQ.equalsIgnoreCase(this.o()) && !this.p.J && !this.c(oitemstack)) {
                this.d.a(!this.n());
                this.bE = false;
                this.a((OPathEntity) null);
            }
        } else if (oitemstack != null && oitemstack.c == OItem.aX.cg && !this.bK()) {
            if (!oentityplayer.cc.d) {
                --oitemstack.a;
            }

            if (oitemstack.a <= 0) {
                oentityplayer.bI.a(oentityplayer.bI.c, (OItemStack) null);
            }

            if (!this.p.J) {
                // CanaryMod hook: onTame
                // randomize the tame result. if its 0 - tame success.
                int tameResult = this.aa.nextInt(3);
                // Call hook
                PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.TAME, oentityplayer.entity.getPlayer(), new Mob(this), tameResult == 0);

                // if taming succeeded normally (tameResult == 0) or plugin hook result is allow (force taming)
                if (tameResult == 0 && res == PluginLoader.HookResult.DEFAULT_ACTION || res == PluginLoader.HookResult.ALLOW_ACTION) {
                    this.g(true);
                    this.a((OPathEntity) null);
                    this.b((OEntityLiving) null);
                    this.d.a(true);
                    this.j(20);
                    this.a(oentityplayer.bQ);
                    this.f(true);
                    this.p.a(this, (byte) 7);
                } else {
                    this.f(false);
                    this.p.a(this, (byte) 6);
                }
            }

            return true;
        }

        return super.a(oentityplayer);
    }

    public boolean c(OItemStack oitemstack) {
        return oitemstack == null ? false : (!(OItem.e[oitemstack.c] instanceof OItemFood) ? false : ((OItemFood) OItem.e[oitemstack.c]).i());
    }

    public int bv() {
        return 8;
    }

    public boolean bK() {
        return (this.ag.a(16) & 2) != 0;
    }

    public void i(boolean flag) {
        byte b0 = this.ag.a(16);

        if (flag) {
            this.ag.b(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.ag.b(16, Byte.valueOf((byte) (b0 & -3)));
        }
    }

    public int bL() {
        return this.ag.a(20) & 15;
    }

    public void s(int i) {
        this.ag.b(20, Byte.valueOf((byte) (i & 15)));
    }

    public OEntityWolf b(OEntityAgeable oentityageable) {
        OEntityWolf oentitywolf = new OEntityWolf(this.p);

        oentitywolf.a(this.o());
        oentitywolf.g(true);
        return oentitywolf;
    }

    public void j(boolean flag) {
        byte b0 = this.ag.a(19);

        if (flag) {
            this.ag.b(19, Byte.valueOf((byte) 1));
        } else {
            this.ag.b(19, Byte.valueOf((byte) 0));
        }
    }

    public boolean a(OEntityAnimal oentityanimal) {
        if (oentityanimal == this) {
            return false;
        } else if (!this.m()) {
            return false;
        } else if (!(oentityanimal instanceof OEntityWolf)) {
            return false;
        } else {
            OEntityWolf oentitywolf = (OEntityWolf) oentityanimal;

            return !oentitywolf.m() ? false : (oentitywolf.n() ? false : this.r() && oentitywolf.r());
        }
    }

    public boolean bM() {
        return this.ag.a(19) == 1;
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }

    @Override
    public Wolf getEntity() {
        return wolf;
    } //
}
