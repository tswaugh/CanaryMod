
public class OEntityGhast extends OEntityFlying implements OIMob {

    public int a = 0;
    public double b;
    public double c;
    public double d;
    private OEntity g = null;
    private int h = 0;
    public int e = 0;
    public int f = 0;

    public OEntityGhast(OWorld var1) {
        super(var1);
        this.ac = "/mob/ghast.png";
        this.b(4.0F, 4.0F);
        this.bU = true;
        this.az = 5;
    }

    public boolean a(ODamageSource var1, int var2) {
        if ("fireball".equals(var1.l()) && var1.a() instanceof OEntityPlayer) {
            super.a(var1, 1000);
            ((OEntityPlayer) var1.a()).a((OStatBase) OAchievementList.y);
            return true;
        } else {
            return super.a(var1, var2);
        }
    }

    protected void b() {
        super.b();
        this.bV.a(16, Byte.valueOf((byte) 0));
    }

    public int c() {
        return 10;
    }

    public void w_() {
        super.w_();
        byte var1 = this.bV.a(16);

        this.ac = var1 == 1 ? "/mob/ghast_fire.png" : "/mob/ghast.png";
    }

    protected void m_() {
        if (!this.bf.I && this.bf.v == 0) {
            this.S();
        }

        this.ak();
        this.e = this.f;
        double var1 = this.b - this.bj;
        double var3 = this.c - this.bk;
        double var5 = this.d - this.bl;
        double var7 = (double) OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5);

        if (var7 < 1.0D || var7 > 60.0D) {
            this.b = this.bj + (double) ((this.bP.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.c = this.bk + (double) ((this.bP.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.d = this.bl + (double) ((this.bP.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.a-- <= 0) {
            this.a += this.bP.nextInt(5) + 2;
            if (this.a(this.b, this.c, this.d, var7)) {
                this.bm += var1 / var7 * 0.1D;
                this.bn += var3 / var7 * 0.1D;
                this.bo += var5 / var7 * 0.1D;
            } else {
                this.b = this.bj;
                this.c = this.bk;
                this.d = this.bl;
            }
        }

        if (this.g != null && this.g.bB) {
            this.g = null;
        }

        if (this.g == null || this.h-- <= 0) {
            OEntityPlayer var8 = this.bf.b(this, 100.0D);
            // CanaryMod: MOB_TARGET Hook for ghasts.
            if(var8 != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) var8.entity.getPlayer(), entity))
            this.g = var8;
            if (this.g != null) {
                this.h = 20;
            }
        }

        double var9 = 64.0D;

        if (this.g != null && this.g.i(this) < var9 * var9) {
            double var11 = this.g.bj - this.bj;
            double var13 = this.g.bt.b + (double) (this.g.bE / 2.0F) - (this.bk + (double) (this.bE / 2.0F));
            double var15 = this.g.bl - this.bl;

            this.V = this.bp = -((float) Math.atan2(var11, var15)) * 180.0F / 3.1415927F;
            if (this.g(this.g)) {
                if (this.f == 10) {
                    this.bf.a((OEntityPlayer) null, 1007, (int) this.bj, (int) this.bk, (int) this.bl, 0);
                }

                ++this.f;
                if (this.f == 20) {
                    this.bf.a((OEntityPlayer) null, 1008, (int) this.bj, (int) this.bk, (int) this.bl, 0);
                    OEntityFireball var17 = new OEntityFireball(this.bf, this, var11, var13, var15);
                    double var18 = 4.0D;
                    OVec3D var20 = this.d(1.0F);

                    var17.bj = this.bj + var20.a * var18;
                    var17.bk = this.bk + (double) (this.bE / 2.0F) + 0.5D;
                    var17.bl = this.bl + var20.c * var18;
                    this.bf.b((OEntity) var17);
                    this.f = -40;
                }
            } else if (this.f > 0) {
                --this.f;
            }
        } else {
            this.V = this.bp = -((float) Math.atan2(this.bm, this.bo)) * 180.0F / 3.1415927F;
            if (this.f > 0) {
                --this.f;
            }
        }

        if (!this.bf.I) {
            byte var21 = this.bV.a(16);
            byte var22 = (byte) (this.f > 10 ? 1 : 0);

            if (var21 != var22) {
                this.bV.b(16, Byte.valueOf(var22));
            }
        }

    }

    private boolean a(double var1, double var3, double var5, double var7) {
        double var9 = (this.b - this.bj) / var7;
        double var11 = (this.c - this.bk) / var7;
        double var13 = (this.d - this.bl) / var7;
        OAxisAlignedBB var15 = this.bt.b();

        for (int var16 = 1; (double) var16 < var7; ++var16) {
            var15.d(var9, var11, var13);
            if (this.bf.a((OEntity) this, var15).size() > 0) {
                return false;
            }
        }

        return true;
    }

    protected String c_() {
        return "mob.ghast.moan";
    }

    protected String m() {
        return "mob.ghast.scream";
    }

    protected String n() {
        return "mob.ghast.death";
    }

    protected int e() {
        return OItem.L.bM;
    }

    protected void a(boolean var1, int var2) {
        int var3 = this.bP.nextInt(2) + this.bP.nextInt(1 + var2);

        int var4;

        for (var4 = 0; var4 < var3; ++var4) {
            this.b(OItem.bo.bM, 1);
        }

        var3 = this.bP.nextInt(3) + this.bP.nextInt(1 + var2);

        for (var4 = 0; var4 < var3; ++var4) {
            this.b(OItem.L.bM, 1);
        }

    }

    protected float o() {
        return 10.0F;
    }

    public boolean g() {
        return this.bP.nextInt(20) == 0 && super.g() && this.bf.v > 0;
    }

    public int p() {
        return 1;
    }
    
    public void setTarget(OEntity var1){
        this.g = var1;
    }
    
    public OEntity getTarget(){
        return this.g;
    }
}
