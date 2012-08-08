
public class OEntityWolf extends OEntityTamable {

    private boolean b = false;
    private float c;
    private float g;
    private boolean h;
    private boolean i;
    private float j;
    private float k;

    public OEntityWolf(OWorld oworld) {
        super(oworld);
        this.ae = "/mob/wolf.png";
        this.b(0.6F, 0.8F);
        this.bb = 0.3F;
        this.al().a(true);
        this.aL.a(1, new OEntityAISwimming(this));
        this.aL.a(2, this.a);
        this.aL.a(3, new OEntityAILeapAtTarget(this, 0.4F));
        this.aL.a(4, new OEntityAIAttackOnCollide(this, this.bb, true));
        this.aL.a(5, new OEntityAIFollowOwner(this, this.bb, 10.0F, 2.0F));
        this.aL.a(6, new OEntityAIMate(this, this.bb));
        this.aL.a(7, new OEntityAIWander(this, this.bb));
        this.aL.a(8, new OEntityAIBeg(this, 8.0F));
        this.aL.a(9, new OEntityAIWatchClosest(this, OEntityPlayer.class, 8.0F));
        this.aL.a(9, new OEntityAILookIdle(this));
        this.aM.a(1, new OEntityAIOwnerHurtByTarget(this));
        this.aM.a(2, new OEntityAIOwnerHurtTarget(this));
        this.aM.a(3, new OEntityAIHurtByTarget(this, true));
        this.aM.a(4, new OEntityAITargetNonTamed(this, OEntitySheep.class, 16.0F, 200, false));
    }

    public boolean c_() {
        return true;
    }

    public void b(OEntityLiving oentityliving) {
        super.b(oentityliving);
        if (oentityliving instanceof OEntityPlayer) {
            this.d(true);
        }

    }

    protected void g() {
        this.bY.b(18, Integer.valueOf(this.aD()));
    }

    public int d() {
        return this.u_() ? 20 : 8;
    }

    protected void b() {
        super.b();
        this.bY.a(18, new Integer(this.aD()));
    }

    protected boolean g_() {
        return false;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Angry", this.E());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.d(onbttagcompound.o("Angry"));
    }

    protected boolean n() {
        return this.E();
    }

    protected String i() {
        return this.E() ? "mob.wolf.growl" : (this.bS.nextInt(3) == 0 ? (this.u_() && this.bY.c(18) < 10 ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String j() {
        return "mob.wolf.hurt";
    }

    protected String k() {
        return "mob.wolf.death";
    }

    protected float p() {
        return 0.4F;
    }

    protected int f() {
        return -1;
    }

    public void e() {
        super.e();
        if (!this.bi.F && this.h && !this.i && !this.H() && this.bx) {
            this.i = true;
            this.j = 0.0F;
            this.k = 0.0F;
            this.bi.a(this, (byte) 8);
        }

    }

    public void F_() {
        super.F_();
        this.g = this.c;
        if (this.b) {
            this.c += (1.0F - this.c) * 0.4F;
        } else {
            this.c += (0.0F - this.c) * 0.4F;
        }

        if (this.b) {
            this.bc = 10;
        }

        if (this.aT()) {
            this.h = true;
            this.i = false;
            this.j = 0.0F;
            this.k = 0.0F;
        } else if ((this.h || this.i) && this.i) {
            if (this.j == 0.0F) {
                this.bi.a(this, "mob.wolf.shake", this.p(), (this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F);
            }

            this.k = this.j;
            this.j += 0.05F;
            if (this.k >= 2.0F) {
                this.h = false;
                this.i = false;
                this.k = 0.0F;
                this.j = 0.0F;
            }

            if (this.j > 0.4F) {
                float f = (float) this.bw.b;
                int i = (int) (OMathHelper.a((this.j - 0.4F) * 3.1415927F) * 7.0F);

                for (int j = 0; j < i; ++j) {
                    float f1 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG * 0.5F;
                    float f2 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG * 0.5F;

                    this.bi.a("splash", this.bm + (double) f1, (double) (f + 0.8F), this.bo + (double) f2, this.bp, this.bq, this.br);
                }
            }
        }

    }

    public float B() {
        return this.bH * 0.8F;
    }

    public int D() {
        return this.v_() ? 20 : super.D();
    }

    public boolean a(ODamageSource odamagesource, int i) {
        OEntity oentity = odamagesource.a();

        this.a.a(false);
        if (oentity != null && !(oentity instanceof OEntityPlayer) && !(oentity instanceof OEntityArrow)) {
            i = (i + 1) / 2;
        }

        return super.a(odamagesource, i);
    }

    public boolean a(OEntity oentity) {
        int i = this.u_() ? 4 : 2;

        return oentity.a(ODamageSource.a((OEntityLiving) this), i);
    }

    public boolean b(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.k.d();

        if (!this.u_()) {
            if (oitemstack != null && oitemstack.c == OItem.aW.bP && !this.E()) {
                --oitemstack.a;
                if (oitemstack.a <= 0) {
                    oentityplayer.k.a(oentityplayer.k.c, (OItemStack) null);
                }

                if (!this.bi.F) {
                    // CanaryMod hook: onTame
                    // randomize the tame result. if its 0 - tame success.
                    int tameResult = this.bS.nextInt(3);
                    // Call hook
                    PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.TAME, manager.getServer().getPlayer(oentityplayer.v), new Mob(this), tameResult == 0);

                    // if taming succeeded normally (tameResult == 0) or plugin hook result is allow (force taming)
                    if (tameResult == 0 && res == PluginLoader.HookResult.DEFAULT_ACTION || res == PluginLoader.HookResult.ALLOW_ACTION) {
                        this.b(true);
                        this.a((OPathEntity) null);
                        this.b((OEntityLiving) null);
                        this.a.a(true);
                        this.h(20);
                        this.a(oentityplayer.v);
                        this.a(true);
                        this.bi.a(this, (byte) 7);
                    } else {
                        this.a(false);
                        this.bi.a(this, (byte) 6);
                    }
                }

                return true;
            }
        } else {
            if (oitemstack != null && OItem.d[oitemstack.c] instanceof OItemFood) {
                OItemFood oitemfood = (OItemFood) OItem.d[oitemstack.c];

                if (oitemfood.q() && this.bY.c(18) < 20) {
                    --oitemstack.a;
                    this.d(oitemfood.o());
                    if (oitemstack.a <= 0) {
                        oentityplayer.k.a(oentityplayer.k.c, (OItemStack) null);
                    }

                    return true;
                }
            }

            if (oentityplayer.v.equalsIgnoreCase(this.A()) && !this.bi.F && !this.a(oitemstack)) {
                this.a.a(!this.v_());
                this.aZ = false;
                this.a((OPathEntity) null);
            }
        }

        return super.b(oentityplayer);
    }

    public boolean a(OItemStack oitemstack) {
        return oitemstack == null ? false : (!(OItem.d[oitemstack.c] instanceof OItemFood) ? false : ((OItemFood) OItem.d[oitemstack.c]).q());
    }

    public int q() {
        return 8;
    }

    public boolean E() {
        return (this.bY.a(16) & 2) != 0;
    }

    public void d(boolean flag) {
        byte b0 = this.bY.a(16);

        if (flag) {
            this.bY.b(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.bY.b(16, Byte.valueOf((byte) (b0 & -3)));
        }

    }

    public OEntityAnimal a(OEntityAnimal oentityanimal) {
        OEntityWolf oentitywolf = new OEntityWolf(this.bi);

        oentitywolf.a(this.A());
        oentitywolf.b(true);
        return oentitywolf;
    }

    public void e(boolean flag) {
        this.b = flag;
    }

    public boolean b(OEntityAnimal oentityanimal) {
        if (oentityanimal == this) {
            return false;
        } else if (!this.u_()) {
            return false;
        } else if (!(oentityanimal instanceof OEntityWolf)) {
            return false;
        } else {
            OEntityWolf oentitywolf = (OEntityWolf) oentityanimal;

            return !oentitywolf.u_() ? false : (oentitywolf.v_() ? false : this.r_() && oentitywolf.r_());
        }
    }
}
