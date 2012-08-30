
public class OEntityOcelot extends OEntityTameable {

    private OEntityAITempt e;

    public OEntityOcelot(OWorld oworld) {
        super(oworld);
        this.az = "/mob/ozelot.png";
        this.a(0.6F, 0.8F);
        this.as().a(true);
        this.bg.a(1, new OEntityAISwimming(this));
        this.bg.a(2, this.d);
        this.bg.a(3, this.e = new OEntityAITempt(this, 0.18F, OItem.aU.bT, true));
        this.bg.a(4, new OEntityAIAvoidEntity(this, OEntityPlayer.class, 16.0F, 0.23F, 0.4F));
        this.bg.a(5, new OEntityAIFollowOwner(this, 0.3F, 10.0F, 5.0F));
        this.bg.a(6, new OEntityAIOcelotSit(this, 0.4F));
        this.bg.a(7, new OEntityAILeapAtTarget(this, 0.3F));
        this.bg.a(8, new OEntityAIOcelotAttack(this));
        this.bg.a(9, new OEntityAIMate(this, 0.23F));
        this.bg.a(10, new OEntityAIWander(this, 0.23F));
        this.bg.a(11, new OEntityAIWatchClosest(this, OEntityPlayer.class, 10.0F));
        this.bh.a(1, new OEntityAITargetNonTamed(this, OEntityChicken.class, 14.0F, 750, false));
    }

    protected void a() {
        super.a();
        this.af.a(18, Byte.valueOf((byte) 0));
    }

    public void bd() {
        if (this.aq().a()) {
            float f = this.aq().b();

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

    protected boolean ba() {
        return !this.n();
    }

    public boolean aV() {
        return true;
    }

    public int aM() {
        return 10;
    }

    protected void a(float f) {}

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("CatType", this.u());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.b(onbttagcompound.e("CatType"));
    }

    protected String aQ() {
        return this.n() ? (this.s() ? "mob.cat.purr" : (this.Z.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
    }

    protected String aR() {
        return "mob.cat.hitt";
    }

    protected String aS() {
        return "mob.cat.hitt";
    }

    protected float aP() {
        return 0.4F;
    }

    protected int aT() {
        return OItem.aF.bT;
    }

    public boolean k(OEntity oentity) {
        return oentity.a(ODamageSource.a((OEntityLiving) this), 3);
    }

    public boolean a(ODamageSource odamagesource, int i) {
        this.d.a(false);
        return super.a(odamagesource, i);
    }

    protected void a(boolean flag, int i) {}

    public boolean c(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.by.g();

        if (this.n()) {
            if (oentityplayer.bJ.equalsIgnoreCase(this.p()) && !this.p.K && !this.b(oitemstack)) {
                this.d.a(!this.o());
            }
        } else if (this.e.f() && oitemstack != null && oitemstack.c == OItem.aU.bT && oentityplayer.e(this) < 9.0D) {
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
                    this.b(1 + this.p.v.nextInt(3));
                    this.a(oentityplayer.bJ);
                    this.e(true);
                    this.d.a(true);
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

    public OEntityAnimal a(OEntityAnimal oentityanimal) {
        OEntityOcelot oentityocelot = new OEntityOcelot(this.p);

        if (this.n()) {
            oentityocelot.a(this.p());
            oentityocelot.f(true);
            oentityocelot.b(this.u());
        }

        return oentityocelot;
    }

    public boolean b(OItemStack oitemstack) {
        return oitemstack != null && oitemstack.c == OItem.aU.bT;
    }

    public boolean b(OEntityAnimal oentityanimal) {
        if (oentityanimal == this) {
            return false;
        } else if (!this.n()) {
            return false;
        } else if (!(oentityanimal instanceof OEntityOcelot)) {
            return false;
        } else {
            OEntityOcelot oentityocelot = (OEntityOcelot) oentityanimal;

            return !oentityocelot.n() ? false : this.s() && oentityocelot.s();
        }
    }

    public int u() {
        return this.af.a(18);
    }

    public void b(int i) {
        this.af.b(18, Byte.valueOf((byte) i));
    }

    public boolean bi() {
        if (this.p.v.nextInt(3) == 0) {
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

                if (l == OBlock.u.ca || l == OBlock.K.ca) {
                    return true;
                }
            }

            return false;
        }
    }

    public String ak() {
        return this.n() ? "entity.Cat.name" : super.ak();
    }
}
