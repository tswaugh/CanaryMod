
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
        this.ae = "/mob/ghast.png";
        this.b(4.0F, 4.0F);
        this.bX = true;
        this.aA = 5;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if ("fireball".equals(odamagesource.l()) && odamagesource.a() instanceof OEntityPlayer) {
            super.a(odamagesource, 1000);
            ((OEntityPlayer) odamagesource.a()).a((OStatBase) OAchievementList.y);
            return true;
        } else {
            return super.a(odamagesource, i);
        }
    }

    protected void b() {
        super.b();
        this.bY.a(16, Byte.valueOf((byte) 0));
    }

    public int d() {
        return 10;
    }

    public void F_() {
        super.F_();
        byte b0 = this.bY.a(16);

        this.ae = b0 == 1 ? "/mob/ghast_fire.png" : "/mob/ghast.png";
    }

    protected void d_() {
        if (!this.bi.F && this.bi.q == 0) {
            this.X();
        }

        this.aG();
        this.e = this.f;
        double d0 = this.b - this.bm;
        double d1 = this.c - this.bn;
        double d2 = this.d - this.bo;
        double d3 = (double) OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        if (d3 < 1.0D || d3 > 60.0D) {
            this.b = this.bm + (double) ((this.bS.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.c = this.bn + (double) ((this.bS.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.d = this.bo + (double) ((this.bS.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.a-- <= 0) {
            this.a += this.bS.nextInt(5) + 2;
            if (this.a(this.b, this.c, this.d, d3)) {
                this.bp += d0 / d3 * 0.1D;
                this.bq += d1 / d3 * 0.1D;
                this.br += d2 / d3 * 0.1D;
            } else {
                this.b = this.bm;
                this.c = this.bn;
                this.d = this.bo;
            }
        }

        if (this.g != null && this.g.bE) {
            this.g = null;
        }

        if (this.g == null || this.h-- <= 0) {
            OEntityPlayer oentityplayer = this.bi.b(this, 100.0D);
            
            // CanaryMod: MOB_TARGET Hook for ghasts.
            if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentityplayer.entity.getPlayer(), entity)) {
                this.g = oentityplayer;
            }
            if (this.g != null) {
                this.h = 20;
            }
        }

        double d4 = 64.0D;

        if (this.g != null && this.g.j(this) < d4 * d4) {
            double d5 = this.g.bm - this.bm;
            double d6 = this.g.bw.b + (double) (this.g.bH / 2.0F) - (this.bn + (double) (this.bH / 2.0F));
            double d7 = this.g.bo - this.bo;

            this.V = this.bs = -((float) Math.atan2(d5, d7)) * 180.0F / 3.1415927F;
            if (this.h(this.g)) {
                if (this.f == 10) {
                    this.bi.a((OEntityPlayer) null, 1007, (int) this.bm, (int) this.bn, (int) this.bo, 0);
                }

                ++this.f;
                if (this.f == 20) {
                    this.bi.a((OEntityPlayer) null, 1008, (int) this.bm, (int) this.bn, (int) this.bo, 0);
                    OEntityFireball oentityfireball = new OEntityFireball(this.bi, this, d5, d6, d7);
                    double d8 = 4.0D;
                    OVec3D ovec3d = this.f(1.0F);

                    oentityfireball.bm = this.bm + ovec3d.a * d8;
                    oentityfireball.bn = this.bn + (double) (this.bH / 2.0F) + 0.5D;
                    oentityfireball.bo = this.bo + ovec3d.c * d8;
                    this.bi.b((OEntity) oentityfireball);
                    this.f = -40;
                }
            } else if (this.f > 0) {
                --this.f;
            }
        } else {
            this.V = this.bs = -((float) Math.atan2(this.bp, this.br)) * 180.0F / 3.1415927F;
            if (this.f > 0) {
                --this.f;
            }
        }

        if (!this.bi.F) {
            byte b0 = this.bY.a(16);
            byte b1 = (byte) (this.f > 10 ? 1 : 0);

            if (b0 != b1) {
                this.bY.b(16, Byte.valueOf(b1));
            }
        }

    }

    private boolean a(double d0, double d1, double d2, double d3) {
        double d4 = (this.b - this.bm) / d3;
        double d5 = (this.c - this.bn) / d3;
        double d6 = (this.d - this.bo) / d3;
        OAxisAlignedBB oaxisalignedbb = this.bw.b();

        for (int i = 1; (double) i < d3; ++i) {
            oaxisalignedbb.d(d4, d5, d6);
            if (this.bi.a((OEntity) this, oaxisalignedbb).size() > 0) {
                return false;
            }
        }

        return true;
    }

    protected String i() {
        return "mob.ghast.moan";
    }

    protected String j() {
        return "mob.ghast.scream";
    }

    protected String k() {
        return "mob.ghast.death";
    }

    protected int f() {
        return OItem.L.bP;
    }

    protected void a(boolean flag, int i) {
        int j = this.bS.nextInt(2) + this.bS.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.bo.bP, 1);
        }

        j = this.bS.nextInt(3) + this.bS.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            this.b(OItem.L.bP, 1);
        }

    }

    protected float p() {
        return 10.0F;
    }

    public boolean l() {
        return this.bS.nextInt(20) == 0 && super.l() && this.bi.q > 0;
    }

    public int q() {
        return 1;
    }
    
    public void setTarget(OEntity oentity) {
        this.g = oentity;
    }
    
    public OEntity getTarget() {
        return this.g;
    }
}
