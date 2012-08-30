
public abstract class OEntityMob extends OEntityCreature implements OIMob {

    protected int f = 2;
    
    // CanaryMod start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    protected LivingEntity entity = new LivingEntity(this);
    // CanaryMod end

    public OEntityMob(OWorld oworld) {
        super(oworld);
        this.aV = 5;
    }

    public void d() {
        float f = this.c(1.0F);

        if (f > 0.5F) {
            this.bq += 2;
        }

        super.d();
    }

    public void h_() {
        super.h_();
        if (!this.p.K && this.p.u == 0) {
            this.y();
        }

    }

    protected OEntity k() {
        OEntityPlayer oentityplayer = this.p.b(this, 16.0D);

        return oentityplayer != null && this.l(oentityplayer) && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentityplayer.entity.getPlayer(), this.entity) ? oentityplayer : null;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (super.a(odamagesource, i)) {
            OEntity oentity = odamagesource.g();

            if (this.n != oentity && this.o != oentity) {
                if (oentity != this) {
                    // CanaryMod start - MOB_TARGET hook
                    if (oentity instanceof OEntityPlayer && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentity.entity.getPlayer(), this.entity)) {
                        this.a = oentity;
                    }
                    // CanaryMod end
                }

                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean k(OEntity oentity) {
        int i = this.f;

        if (this.a(OPotion.g)) {
            i += 3 << this.b(OPotion.g).c();
        }

        if (this.a(OPotion.t)) {
            i -= 2 << this.b(OPotion.t).c();
        }

        return oentity.a(ODamageSource.a((OEntityLiving) this), i);
    }

    protected void a(OEntity oentity, float f) {
        if (this.aR <= 0 && f < 2.0F && oentity.D.e > this.D.b && oentity.D.b < this.D.e) {
            this.aR = 20;
            this.k(oentity);
        }

    }

    public float a(int i, int j, int k) {
        return 0.5F - this.p.o(i, j, k);
    }

    protected boolean o() {
        int i = OMathHelper.c(this.t);
        int j = OMathHelper.c(this.D.b);
        int k = OMathHelper.c(this.v);

        if (this.p.b(OEnumSkyBlock.a, i, j, k) > this.Z.nextInt(32)) {
            return false;
        } else {
            int l = this.p.l(i, j, k);

            if (this.p.I()) {
                int i1 = this.p.k;

                this.p.k = 10;
                l = this.p.l(i, j, k);
                this.p.k = i1;
            }

            return l <= this.Z.nextInt(8);
        }
    }

    public boolean bi() {
        return this.o() && super.bi();
    }
}
