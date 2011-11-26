
public abstract class OEntityMob extends OEntityCreature implements OIMob {

    protected int c = 2;
    
    // CanaryMod start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    protected LivingEntity entity = new LivingEntity(this);
    // CanaryMod end
    
    public OEntityMob(OWorld var1) {
        super(var1);
        this.az = 5;
    }

    public void d() {
        float var1 = this.a(1.0F);

        if (var1 > 0.5F) {
            this.aS += 2;
        }

        super.d();
    }

    public void w_() {
        super.w_();
        if (!this.bf.I && this.bf.v == 0) {
            this.S();
        }

    }

    protected OEntity k() {
        OEntityPlayer var1 = this.bf.b(this, 16.0D);

        return var1 != null && this.g(var1) && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) var1.entity.getPlayer(), entity) ? var1 : null; // CanaryMod: MOB_TARGET hook
    }

    public boolean a(ODamageSource var1, int var2) {
        if (super.a(var1, var2)) {
            OEntity var3 = var1.a();

            if (this.bd != var3 && this.be != var3) {
                if (var3 != this) {
                    // CanaryMod start - MOB_TARGET hook
                    if (var3 instanceof OEntityPlayer && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) var3.entity.getPlayer(), entity)) {
                        this.d = var3;
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

    protected boolean d(OEntity var1) {
        int var2 = this.c;

        if (this.a(OPotion.g)) {
            var2 += 3 << this.b(OPotion.g).c();
        }

        if (this.a(OPotion.t)) {
            var2 -= 2 << this.b(OPotion.t).c();
        }

        return var1.a(ODamageSource.a((OEntityLiving) this), var2);
    }

    protected void a(OEntity var1, float var2) {
        if (this.av <= 0 && var2 < 2.0F && var1.bt.e > this.bt.b && var1.bt.b < this.bt.e) {
            this.av = 20;
            this.d(var1);
        }

    }

    protected float a(int var1, int var2, int var3) {
        return 0.5F - this.bf.m(var1, var2, var3);
    }

    public void b(ONBTTagCompound var1) {
        super.b(var1);
    }

    public void a(ONBTTagCompound var1) {
        super.a(var1);
    }

    protected boolean y() {
        int var1 = OMathHelper.b(this.bj);
        int var2 = OMathHelper.b(this.bt.b);
        int var3 = OMathHelper.b(this.bl);

        if (this.bf.a(OEnumSkyBlock.a, var1, var2, var3) > this.bP.nextInt(32)) {
            return false;
        } else {
            int var4 = this.bf.l(var1, var2, var3);

            if (this.bf.v()) {
                int var5 = this.bf.k;

                this.bf.k = 10;
                var4 = this.bf.l(var1, var2, var3);
                this.bf.k = var5;
            }

            return var4 <= this.bP.nextInt(8);
        }
    }

    public boolean g() {
        return this.y() && super.g();
    }
}
