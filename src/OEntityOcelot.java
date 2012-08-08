
public class OEntityOcelot extends OEntityTamable {

    private OEntityAITempt b;

    public OEntityOcelot(OWorld oworld) {
        super(oworld);
        this.ae = "/mob/ozelot.png";
        this.b(0.6F, 0.8F);
        this.al().a(true);
        this.aL.a(1, new OEntityAISwimming(this));
        this.aL.a(2, this.a);
        this.aL.a(3, this.b = new OEntityAITempt(this, 0.18F, OItem.aT.bP, true));
        this.aL.a(4, new OEntityAIAvoidEntity(this, OEntityPlayer.class, 16.0F, 0.23F, 0.4F));
        this.aL.a(5, new OEntityAICatBehavior(this, 0.4F));
        this.aL.a(6, new OEntityAIFollowOwner(this, 0.3F, 10.0F, 5.0F));
        this.aL.a(7, new OEntityAILeapAtTarget(this, 0.3F));
        this.aL.a(8, new OEntityAIOcelotAttack(this));
        this.aL.a(9, new OEntityAIMate(this, 0.23F));
        this.aL.a(10, new OEntityAIWander(this, 0.23F));
        this.aL.a(11, new OEntityAIWatchClosest(this, OEntityPlayer.class, 10.0F));
        this.aM.a(1, new OEntityAITargetNonTamed(this, OEntityChicken.class, 14.0F, 750, false));
    }

    protected void b() {
        super.b();
        this.bY.a(18, Byte.valueOf((byte) 0));
    }

    public void g() {
        if (!this.aj().a()) {
            this.g(false);
            this.h(false);
        } else {
            float f = this.aj().b();

            if (f == 0.18F) {
                this.g(true);
                this.h(false);
            } else if (f == 0.4F) {
                this.g(false);
                this.h(true);
            } else {
                this.g(false);
                this.h(false);
            }
        }

    }

    protected boolean n() {
        return !this.u_();
    }

    public boolean c_() {
        return true;
    }

    public int d() {
        return 10;
    }

    protected void a(float f) {}

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("CatType", this.r());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.c_(onbttagcompound.f("CatType"));
    }

    protected String i() {
        return this.u_() ? (this.r_() ? "mob.cat.purr" : (this.bS.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
    }

    protected String j() {
        return "mob.cat.hitt";
    }

    protected String k() {
        return "mob.cat.hitt";
    }

    protected float p() {
        return 0.4F;
    }

    protected int f() {
        return OItem.aE.bP;
    }

    public boolean a(OEntity oentity) {
        return oentity.a(ODamageSource.a((OEntityLiving) this), 3);
    }

    public boolean a(ODamageSource odamagesource, int i) {
        this.a.a(false);
        return super.a(odamagesource, i);
    }

    protected void a(boolean flag, int i) {}

    public boolean b(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.k.d();

        if (!this.u_()) {
            if (this.b.f() && oitemstack != null && oitemstack.c == OItem.aT.bP && oentityplayer.j(this) < 9.0D) {
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
                        this.c_(1 + this.bi.r.nextInt(3));
                        this.a(oentityplayer.v);
                        this.a(true);
                        this.a.a(true);
                        this.bi.a(this, (byte) 7);
                    } else {
                        this.a(false);
                        this.bi.a(this, (byte) 6);
                    }
                }
            }

            return true;
        } else {
            if (oentityplayer.v.equalsIgnoreCase(this.A()) && !this.bi.F && !this.a(oitemstack)) {
                this.a.a(!this.v_());
            }

            return super.b(oentityplayer);
        }
    }

    public OEntityAnimal a(OEntityAnimal oentityanimal) {
        OEntityOcelot oentityocelot = new OEntityOcelot(this.bi);

        if (this.u_()) {
            oentityocelot.a(this.A());
            oentityocelot.b(true);
            oentityocelot.c_(this.r());
        }

        return oentityocelot;
    }

    public boolean a(OItemStack oitemstack) {
        return oitemstack != null && oitemstack.c == OItem.aT.bP;
    }

    public boolean b(OEntityAnimal oentityanimal) {
        if (oentityanimal == this) {
            return false;
        } else if (!this.u_()) {
            return false;
        } else if (!(oentityanimal instanceof OEntityOcelot)) {
            return false;
        } else {
            OEntityOcelot oentityocelot = (OEntityOcelot) oentityanimal;

            return !oentityocelot.u_() ? false : this.r_() && oentityocelot.r_();
        }
    }

    public int r() {
        return this.bY.a(18);
    }

    public void c_(int i) {
        this.bY.b(18, Byte.valueOf((byte) i));
    }

    public boolean l() {
        if (this.bi.r.nextInt(3) == 0) {
            return false;
        } else {
            if (this.bi.a(this.bw) && this.bi.a((OEntity) this, this.bw).size() == 0 && !this.bi.c(this.bw)) {
                int i = OMathHelper.b(this.bm);
                int j = OMathHelper.b(this.bw.b);
                int k = OMathHelper.b(this.bo);

                if (j < 63) {
                    return false;
                }

                int l = this.bi.a(i, j - 1, k);

                if (l == OBlock.u.bO || l == OBlock.K.bO) {
                    return true;
                }
            }

            return false;
        }
    }

    public String s() {
        return this.u_() ? "entity.Cat.name" : super.s();
    }
}
