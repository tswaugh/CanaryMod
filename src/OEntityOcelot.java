public class OEntityOcelot extends OEntityTameable {

    private OEntityAITempt e;
    private Ocelot ocelot = new Ocelot(this); // CanaryMod: one ocelot per ocelot

    public OEntityOcelot(OWorld oworld) {
        super(oworld);
        this.aG = "/mob/ozelot.png";
        this.a(0.6F, 0.8F);
        this.az().a(true);
        this.bn.a(1, new OEntityAISwimming(this));
        this.bn.a(2, this.d);
        this.bn.a(3, this.e = new OEntityAITempt(this, 0.18F, OItem.aU.cj, true));
        this.bn.a(4, new OEntityAIAvoidEntity(this, OEntityPlayer.class, 16.0F, 0.23F, 0.4F));
        this.bn.a(5, new OEntityAIFollowOwner(this, 0.3F, 10.0F, 5.0F));
        this.bn.a(6, new OEntityAIOcelotSit(this, 0.4F));
        this.bn.a(7, new OEntityAILeapAtTarget(this, 0.3F));
        this.bn.a(8, new OEntityAIOcelotAttack(this));
        this.bn.a(9, new OEntityAIMate(this, 0.23F));
        this.bn.a(10, new OEntityAIWander(this, 0.23F));
        this.bn.a(11, new OEntityAIWatchClosest(this, OEntityPlayer.class, 10.0F));
        this.bo.a(1, new OEntityAITargetNonTamed(this, OEntityChicken.class, 14.0F, 750, false));
    }

    protected void a() {
        super.a();
        this.ag.a(18, Byte.valueOf((byte) 0));
    }

    public void bm() {
        if (this.ax().a()) {
            float f = this.ax().b();

            if (f == 0.18F) {
                this.a(true);
                this.b(false);
            } else if (f == 0.4F) {
                this.a(false);
                this.b(true);
            } else {
                this.a(false);
                this.b(false);
            }
        } else {
            this.a(false);
            this.b(false);
        }
    }

    protected boolean bj() {
        return !this.m();
    }

    public boolean be() {
        return true;
    }

    public int aT() {
        //CanaryMod: set max health here, but check for uninitialized value.
        return this.maxHealth == 0 ? 10 : this.maxHealth;
    }

    protected void a(float f) {}

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("CatType", this.t());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.s(onbttagcompound.e("CatType"));
    }

    protected String aY() {
        return this.m() ? (this.r() ? "mob.cat.purr" : (this.aa.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
    }

    protected String aZ() {
        return "mob.cat.hitt";
    }

    protected String ba() {
        return "mob.cat.hitt";
    }

    protected float aX() {
        return 0.4F;
    }

    protected int bb() {
        return OItem.aF.cj;
    }

    public boolean m(OEntity oentity) {
        return oentity.a(ODamageSource.a((OEntityLiving) this), 3);
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.ar()) {
            return false;
        } else {
            this.d.a(false);
            return super.a(odamagesource, i);
        }
    }

    protected void a(boolean flag, int i) {}

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bJ.g();

        if (this.m()) {
            if (oentityplayer.bR.equalsIgnoreCase(this.o()) && !this.p.I && !this.c(oitemstack)) {
                this.d.a(!this.n());
            }
        } else if (this.e.f() && oitemstack != null && oitemstack.c == OItem.aU.cj && oentityplayer.e(this) < 9.0D) {
            if (!oentityplayer.cd.d) {
                --oitemstack.a;
            }

            if (oitemstack.a <= 0) {
                oentityplayer.bJ.a(oentityplayer.bJ.c, (OItemStack) null);
            }

            if (!this.p.I) {
                // CanaryMod hook: onTame
                // randomize the tame result. if its 0 - tame success.
                int tameResult = this.aa.nextInt(3);
                // Call hook
                PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.TAME, oentityplayer.entity.getPlayer(), new Mob(this), tameResult == 0);

                // if taming succeeded normally (tameResult == 0) or plugin hook result is allow (force taming)
                if (tameResult == 0 && res == PluginLoader.HookResult.DEFAULT_ACTION || res == PluginLoader.HookResult.ALLOW_ACTION) {
                    this.g(true);
                    this.s(1 + this.p.t.nextInt(3));
                    this.a(oentityplayer.bR);
                    this.f(true);
                    this.d.a(true);
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

    public OEntityOcelot b(OEntityAgeable oentityageable) {
        OEntityOcelot oentityocelot = new OEntityOcelot(this.p);

        if (this.m()) {
            oentityocelot.a(this.o());
            oentityocelot.g(true);
            oentityocelot.s(this.t());
        }

        return oentityocelot;
    }

    public boolean c(OItemStack oitemstack) {
        return oitemstack != null && oitemstack.c == OItem.aU.cj;
    }

    public boolean a(OEntityAnimal oentityanimal) {
        if (oentityanimal == this) {
            return false;
        } else if (!this.m()) {
            return false;
        } else if (!(oentityanimal instanceof OEntityOcelot)) {
            return false;
        } else {
            OEntityOcelot oentityocelot = (OEntityOcelot) oentityanimal;

            return !oentityocelot.m() ? false : this.r() && oentityocelot.r();
        }
    }

    public int t() {
        return this.ag.a(18);
    }

    public void s(int i) {
        this.ag.b(18, Byte.valueOf((byte) i));
    }

    public boolean bs() {
        if (this.p.t.nextInt(3) == 0) {
            return false;
        } else {
            if (this.p.b(this.D) && this.p.a((OEntity) this, this.D).isEmpty() && !this.p.d(this.D)) {
                int i = OMathHelper.c(this.t);
                int j = OMathHelper.c(this.D.b);
                int k = OMathHelper.c(this.v);

                if (j < 63) {
                    return false;
                }

                int l = this.p.a(i, j - 1, k);

                if (l == OBlock.x.cm || l == OBlock.N.cm) {
                    return true;
                }
            }

            return false;
        }
    }

    public String an() {
        return this.m() ? "entity.Cat.name" : super.an();
    }

    public void bG() {
        if (this.p.t.nextInt(7) == 0) {
            for (int i = 0; i < 2; ++i) {
                OEntityOcelot oentityocelot = new OEntityOcelot(this.p);

                oentityocelot.b(this.t, this.u, this.v, this.z, 0.0F);
                oentityocelot.a(-24000);
                this.p.d((OEntity) oentityocelot);
            }
        }
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }

    @Override
    public Ocelot getEntity() {
        return ocelot;
    }
}
