
public abstract class OEntityMob extends OEntityCreature implements OIMob {

    protected int c = 2;
    
    // CanaryMod start
    protected LivingEntity entity = new LivingEntity(this);
    // CanaryMod end

    public OEntityMob(OWorld oworld) {
        super(oworld);
        this.aA = 5;
    }

    public void e() {
        float f = this.b(1.0F);

        if (f > 0.5F) {
            this.aV += 2;
        }

        super.e();
    }

    public void F_() {
        super.F_();
        if (!this.bi.F && this.bi.q == 0) {
            this.X();
        }

    }

    protected OEntity o() {
        OEntityPlayer oentityplayer = this.bi.b(this, 16.0D);

        return (oentityplayer != null && this.h(oentityplayer) && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentityplayer.entity.getPlayer(), entity)) ? oentityplayer : null; // CanaryMod: MOB_TARGET
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (super.a(odamagesource, i)) {
            OEntity oentity = odamagesource.a();

            if (this.bg != oentity && this.bh != oentity) {
                if (oentity != this) {
                    // CanaryMod start - MOB_TARGET hook
                    if (oentity instanceof OEntityPlayer && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentity.entity.getPlayer(), entity)) {
                        this.d = oentity;
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

    public boolean a(OEntity oentity) {
        int i = this.c;

        if (this.a(OPotion.g)) {
            i += 3 << this.b(OPotion.g).c();
        }

        if (this.a(OPotion.t)) {
            i -= 2 << this.b(OPotion.t).c();
        }

        return oentity.a(ODamageSource.a((OEntityLiving) this), i);
    }

    protected void a(OEntity oentity, float f) {
        if (this.aw <= 0 && f < 2.0F && oentity.bw.e > this.bw.b && oentity.bw.b < this.bw.e) {
            this.aw = 20;
            this.a(oentity);
        }

    }

    public float a(int i, int j, int k) {
        return 0.5F - this.bi.p(i, j, k);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
    }

    protected boolean C() {
        int i = OMathHelper.b(this.bm);
        int j = OMathHelper.b(this.bw.b);
        int k = OMathHelper.b(this.bo);

        if (this.bi.a(OEnumSkyBlock.a, i, j, k) > this.bS.nextInt(32)) {
            return false;
        } else {
            int l = this.bi.n(i, j, k);

            if (this.bi.w()) {
                int i1 = this.bi.f;

                this.bi.f = 10;
                l = this.bi.n(i, j, k);
                this.bi.f = i1;
            }

            return l <= this.bS.nextInt(8);
        }
    }

    public boolean l() {
        return this.C() && super.l();
    }
}
