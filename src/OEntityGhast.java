
public class OEntityGhast extends OEntityFlying implements OIMob {

    public int a = 0;
    public double b;
    public double c;
    public double d;
    private OEntity g = null;
    private int h = 0;
    public int e = 0;
    public int f = 0;

    public OEntityGhast(OWorld oworld) {
        super(oworld);
        this.az = "/mob/ghast.png";
        this.a(4.0F, 4.0F);
        this.ae = true;
        this.aV = 5;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if ("fireball".equals(odamagesource.l()) && odamagesource.g() instanceof OEntityPlayer) {
            super.a(odamagesource, 1000);
            ((OEntityPlayer) odamagesource.g()).a((OStatBase) OAchievementList.y);
            return true;
        } else {
            return super.a(odamagesource, i);
        }
    }

    protected void a() {
        super.a();
        this.af.a(16, Byte.valueOf((byte) 0));
    }

    public int aM() {
        return 10;
    }

    public void h_() {
        super.h_();
        byte b0 = this.af.a(16);

        this.az = b0 == 1 ? "/mob/ghast_fire.png" : "/mob/ghast.png";
    }

    protected void be() {
        if (!this.p.K && this.p.u == 0) {
            this.y();
        }

        this.bb();
        this.e = this.f;
        double d0 = this.b - this.t;
        double d1 = this.c - this.u;
        double d2 = this.d - this.v;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D) {
            this.b = this.t + (double) ((this.Z.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.c = this.u + (double) ((this.Z.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.d = this.v + (double) ((this.Z.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.a-- <= 0) {
            this.a += this.Z.nextInt(5) + 2;
            d3 = (double) OMathHelper.a(d3);
            if (this.a(this.b, this.c, this.d, d3)) {
                this.w += d0 / d3 * 0.1D;
                this.x += d1 / d3 * 0.1D;
                this.y += d2 / d3 * 0.1D;
            } else {
                this.b = this.t;
                this.c = this.u;
                this.d = this.v;
            }
        }

        if (this.g != null && this.g.L) {
            this.g = null;
        }

        if (this.g == null || this.h-- <= 0) {
            OEntityPlayer oentityplayer = this.p.b(this, 100.0D);
            
            // CanaryMod: MOB_TARGET Hook for ghasts.
            if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentityplayer.entity.getPlayer(), this.entity)) {
                this.g = oentityplayer;
            }
            if (this.g != null) {
                this.h = 20;
            }
        }

        double d4 = 64.0D;

        if (this.g != null && this.g.e((OEntity) this) < d4 * d4) {
            double d5 = this.g.t - this.t;
            double d6 = this.g.D.b + (double) (this.g.O / 2.0F) - (this.u + (double) (this.O / 2.0F));
            double d7 = this.g.v - this.v;

            this.aq = this.z = -((float) Math.atan2(d5, d7)) * 180.0F / 3.1415927F;
            if (this.l(this.g)) {
                if (this.f == 10) {
                    this.p.a((OEntityPlayer) null, 1007, (int) this.t, (int) this.u, (int) this.v, 0);
                }

                ++this.f;
                if (this.f == 20) {
                    this.p.a((OEntityPlayer) null, 1008, (int) this.t, (int) this.u, (int) this.v, 0);
                    OEntityFireball oentityfireball = new OEntityFireball(this.p, this, d5, d6, d7);
                    double d8 = 4.0D;
                    OVec3 ovec3 = this.i(1.0F);

                    oentityfireball.t = this.t + ovec3.a * d8;
                    oentityfireball.u = this.u + (double) (this.O / 2.0F) + 0.5D;
                    oentityfireball.v = this.v + ovec3.c * d8;
                    this.p.d((OEntity) oentityfireball);
                    this.f = -40;
                }
            } else if (this.f > 0) {
                --this.f;
            }
        } else {
            this.aq = this.z = -((float) Math.atan2(this.w, this.y)) * 180.0F / 3.1415927F;
            if (this.f > 0) {
                --this.f;
            }
        }

        if (!this.p.K) {
            byte b0 = this.af.a(16);
            byte b1 = (byte) (this.f > 10 ? 1 : 0);

            if (b0 != b1) {
                this.af.b(16, Byte.valueOf(b1));
            }
        }

    }

    private boolean a(double d0, double d1, double d2, double d3) {
        double d4 = (this.b - this.t) / d3;
        double d5 = (this.c - this.u) / d3;
        double d6 = (this.d - this.v) / d3;
        OAxisAlignedBB oaxisalignedbb = this.D.c();

        for (int i = 1; (double) i < d3; ++i) {
            oaxisalignedbb.d(d4, d5, d6);
            if (!this.p.a((OEntity) this, oaxisalignedbb).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    protected String aQ() {
        return "mob.ghast.moan";
    }

    protected String aR() {
        return "mob.ghast.scream";
    }

    protected String aS() {
        return "mob.ghast.death";
    }

    protected int aT() {
        return OItem.M.bT;
    }

    protected void a(boolean flag, int i) {
        int j = this.Z.nextInt(2) + this.Z.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.bp.bT, 1);
        }

        j = this.Z.nextInt(3) + this.Z.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            this.b(OItem.M.bT, 1);
        }

    }

    protected float aP() {
        return 10.0F;
    }

    public boolean bi() {
        return this.Z.nextInt(20) == 0 && super.bi() && this.p.u > 0;
    }

    public int bl() {
        return 1;
    }
    
    public void setTarget(OEntity oentity) {
        this.g = oentity;
    }
    
    public OEntity getTarget() {
        return this.g;
    }
}
