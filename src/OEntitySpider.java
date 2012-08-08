
public class OEntitySpider extends OEntityMob {

    public OEntitySpider(OWorld oworld) {
        super(oworld);
        this.ae = "/mob/spider.png";
        this.b(1.4F, 0.9F);
        this.bb = 0.8F;
    }

    protected void b() {
        super.b();
        this.bY.a(16, new Byte((byte) 0));
    }

    public void e() {
        super.e();
    }

    public void F_() {
        super.F_();
        if (!this.bi.F) {
            this.a(this.by);
        }

    }

    public int d() {
        return 16;
    }

    public double x_() {
        return (double) this.bH * 0.75D - 0.5D;
    }

    protected boolean g_() {
        return false;
    }

    protected OEntity o() {
        float f = this.b(1.0F);

        double d0 = 16.0D;
        OEntityPlayer oentityplayer = this.bi.b(this, d0);

        if (f < 0.5F && oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentityplayer.entity.getPlayer(), entity)) {
            return oentityplayer;
        } else {
            return null;
        }
    }

    protected String i() {
        return "mob.spider";
    }

    protected String j() {
        return "mob.spider";
    }

    protected String k() {
        return "mob.spiderdeath";
    }

    protected void a(OEntity oentity, float f) {
        float f1 = this.b(1.0F);

        if (f1 > 0.5F && this.bS.nextInt(100) == 0) {
            this.d = null;
        } else {
            if (f > 2.0F && f < 6.0F && this.bS.nextInt(10) == 0) {
                if (this.bx) {
                    double d0 = oentity.bm - this.bm;
                    double d1 = oentity.bo - this.bo;
                    float f2 = OMathHelper.a(d0 * d0 + d1 * d1);

                    this.bp = d0 / (double) f2 * 0.5D * 0.800000011920929D + this.bp * 0.20000000298023224D;
                    this.br = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.br * 0.20000000298023224D;
                    this.bq = 0.4000000059604645D;
                }
            } else {
                super.a(oentity, f);
            }

        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
    }

    protected int f() {
        return OItem.J.bP;
    }

    protected void a(boolean flag, int i) {
        super.a(flag, i);
        if (flag && (this.bS.nextInt(3) == 0 || this.bS.nextInt(1 + i) > 0)) {
            this.b(OItem.bt.bP, 1);
        }

    }

    public boolean t() {
        return this.w();
    }

    public void u() {}

    public OEnumCreatureAttribute v() {
        return OEnumCreatureAttribute.c;
    }

    public boolean a(OPotionEffect opotioneffect) {
        return opotioneffect.a() == OPotion.u.H ? false : super.a(opotioneffect);
    }

    public boolean w() {
        return (this.bY.a(16) & 1) != 0;
    }

    public void a(boolean flag) {
        byte b0 = this.bY.a(16);

        if (flag) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.bY.b(16, Byte.valueOf(b0));
    }
}
