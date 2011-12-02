import java.util.List;
import java.util.Random;


public abstract class OEntity {

    private static int a = 0;
    public int ba;
    public double bb;
    public boolean bc;
    public OEntity bd;
    public OEntity be;
    public OWorld bf;
    public double bg;
    public double bh;
    public double bi;
    public double bj;
    public double bk;
    public double bl;
    public double bm;
    public double bn;
    public double bo;
    public float bp;
    public float bq;
    public float br;
    public float bs;
    public final OAxisAlignedBB bt;
    public boolean bu;
    public boolean bv;
    public boolean bw;
    public boolean bx;
    public boolean by;
    protected boolean bz;
    public boolean bA;
    public boolean bB;
    public float bC;
    public float bD;
    public float bE;
    public float bF;
    public float bG;
    public float bH;
    private int b;
    public double bI;
    public double bJ;
    public double bK;
    public float bL;
    public float bM;
    public boolean bN;
    public float bO;
    protected Random bP;
    public int bQ;
    public int bR;
    protected int c; // CanaryMod: private -> protected
    protected boolean bS;
    public int bT;
    private boolean d;
    protected boolean bU;
    protected ODataWatcher bV;
    private double e;
    private double f;
    public boolean bW;
    public int bX;
    public int bY;
    public int bZ;
    public boolean ca;
    public boolean cb;
    // CanaryMod Start
    BaseEntity entity = new BaseEntity(this);
    public static PluginLoader manager = etc.getLoader();

    // CanaryMod end

    public OEntity(OWorld var1) {
        super();
        this.ba = a++;
        this.bb = 1.0D;
        this.bc = false;
        this.bt = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.bu = false;
        this.bx = false;
        this.by = false;
        this.bA = true;
        this.bB = false;
        this.bC = 0.0F;
        this.bD = 0.6F;
        this.bE = 1.8F;
        this.bF = 0.0F;
        this.bG = 0.0F;
        this.bH = 0.0F;
        this.b = 1;
        this.bL = 0.0F;
        this.bM = 0.0F;
        this.bN = false;
        this.bO = 0.0F;
        this.bP = new Random();
        this.bQ = 0;
        this.bR = 1;
        this.c = 0;
        this.bS = false;
        this.bT = 0;
        this.d = true;
        this.bU = false;
        this.bV = new ODataWatcher();
        this.bW = false;
        this.bf = var1;
        this.c(0.0D, 0.0D, 0.0D);
        this.bV.a(0, Byte.valueOf((byte) 0));
        this.bV.a(1, Short.valueOf((short) 300));
        this.b();
    }

    protected abstract void b();

    public ODataWatcher au() {
        return this.bV;
    }

    public boolean equals(Object var1) {
        return var1 instanceof OEntity ? ((OEntity) var1).ba == this.ba : false;
    }

    public int hashCode() {
        return this.ba;
    }

    public void S() {
        this.bB = true;
    }

    protected void b(float var1, float var2) {
        this.bD = var1;
        this.bE = var2;
    }

    protected void c(float var1, float var2) {
        this.bp = var1 % 360.0F;
        this.bq = var2 % 360.0F;
    }

    public void c(double var1, double var3, double var5) {
        this.bj = var1;
        this.bk = var3;
        this.bl = var5;
        float var7 = this.bD / 2.0F;
        float var8 = this.bE;

        this.bt.c(var1 - (double) var7, var3 - (double) this.bC + (double) this.bL, var5 - (double) var7, var1 + (double) var7, var3 - (double) this.bC + (double) this.bL + (double) var8, var5 + (double) var7);
    }

    public void w_() {
        this.af();
    }

    public void af() {
        OProfiler.a("entityBaseTick");
        if (this.be != null && this.be.bB) {
            this.be = null;
        }

        ++this.bQ;
        this.bF = this.bG;
        this.bg = this.bj;
        this.bh = this.bk;
        this.bi = this.bl;
        this.bs = this.bq;
        this.br = this.bp;
        int var3;

        if (this.aE()) {
            int var1 = OMathHelper.b(this.bj);
            int var2 = OMathHelper.b(this.bk - 0.20000000298023224D - (double) this.bC);

            var3 = OMathHelper.b(this.bl);
            int var4 = this.bf.a(var1, var2, var3);

            if (var4 > 0) {
                this.bf.a("tilecrack_" + var4, this.bj + ((double) this.bP.nextFloat() - 0.5D) * (double) this.bD, this.bt.b + 0.1D, this.bl + ((double) this.bP.nextFloat() - 0.5D) * (double) this.bD, -this.bm * 4.0D, 1.5D, -this.bo * 4.0D);
            }
        }

        if (this.i_()) {
            if (!this.bS && !this.d) {
                float var6 = OMathHelper.a(this.bm * this.bm * 0.20000000298023224D + this.bn * this.bn + this.bo * this.bo * 0.20000000298023224D) * 0.2F;

                if (var6 > 1.0F) {
                    var6 = 1.0F;
                }

                this.bf.a(this, "random.splash", var6, 1.0F + (this.bP.nextFloat() - this.bP.nextFloat()) * 0.4F);
                float var7 = (float) OMathHelper.b(this.bt.b);

                float var5;
                float var8;

                for (var3 = 0; (float) var3 < 1.0F + this.bD * 20.0F; ++var3) {
                    var8 = (this.bP.nextFloat() * 2.0F - 1.0F) * this.bD;
                    var5 = (this.bP.nextFloat() * 2.0F - 1.0F) * this.bD;
                    this.bf.a("bubble", this.bj + (double) var8, (double) (var7 + 1.0F), this.bl + (double) var5, this.bm, this.bn - (double) (this.bP.nextFloat() * 0.2F), this.bo);
                }

                for (var3 = 0; (float) var3 < 1.0F + this.bD * 20.0F; ++var3) {
                    var8 = (this.bP.nextFloat() * 2.0F - 1.0F) * this.bD;
                    var5 = (this.bP.nextFloat() * 2.0F - 1.0F) * this.bD;
                    this.bf.a("splash", this.bj + (double) var8, (double) (var7 + 1.0F), this.bl + (double) var5, this.bm, this.bn, this.bo);
                }
            }

            this.bH = 0.0F;
            this.bS = true;
            this.c = 0;
        } else {
            this.bS = false;
        }

        if (this.bf.I) {
            this.c = 0;
        } else if (this.c > 0) {
            if (this.bU) {
                this.c -= 4;
                if (this.c < 0) {
                    this.c = 0;
                }
            } else {
                if (this.c % 20 == 0) {
                    // CanaryMod Damage hook: Periodic burn damage
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE_TICK, null, entity, 1)) {
                        this.a(ODamageSource.b, 1);
                    }
                }

                --this.c;
            }
        }

        if (this.aA()) {
            this.av();
            this.bH *= 0.5F;
        }

        if (this.bk < -64.0D) {
            this.ao();
        }

        if (!this.bf.I) {
            this.a(0, this.c > 0);
            this.a(2, this.be != null);
        }

        this.d = false;
        OProfiler.a();
    }

    protected void av() {
        if (!this.bU) {
            // CanaryMod Damage hook: Lava
            if (this instanceof OEntityLiving) {
                if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, entity, 4)) {
                    return;
                }
            }
            this.a(ODamageSource.c, 4);
            this.j(15);
        }

    }

    public void j(int var1) {
        int var2 = var1 * 20;

        if (this.c < var2) {
            this.c = var2;
        }

    }

    public void aw() {
        this.c = 0;
    }

    protected void ao() {
        this.S();
    }

    public boolean d(double var1, double var3, double var5) {
        OAxisAlignedBB var7 = this.bt.c(var1, var3, var5);
        List var8 = this.bf.a(this, var7);

        return var8.size() > 0 ? false : !this.bf.c(var7);
    }

    public void a(double var1, double var3, double var5) {
        if (this.bN) {
            this.bt.d(var1, var3, var5);
            this.bj = (this.bt.a + this.bt.d) / 2.0D;
            this.bk = this.bt.b + (double) this.bC - (double) this.bL;
            this.bl = (this.bt.c + this.bt.f) / 2.0D;
        } else {
            OProfiler.a("move");
            this.bL *= 0.4F;
            double var7 = this.bj;
            double var9 = this.bl;

            if (this.bz) {
                this.bz = false;
                var1 *= 0.25D;
                var3 *= 0.05000000074505806D;
                var5 *= 0.25D;
                this.bm = 0.0D;
                this.bn = 0.0D;
                this.bo = 0.0D;
            }

            double var11 = var1;
            double var13 = var3;
            double var15 = var5;
            OAxisAlignedBB var17 = this.bt.b();
            boolean var18 = this.bu && this.aD();

            if (var18) {
                double var19;

                for (var19 = 0.05D; var1 != 0.0D && this.bf.a(this, this.bt.c(var1, -1.0D, 0.0D)).size() == 0; var11 = var1) {
                    if (var1 < var19 && var1 >= -var19) {
                        var1 = 0.0D;
                    } else if (var1 > 0.0D) {
                        var1 -= var19;
                    } else {
                        var1 += var19;
                    }
                }

                for (; var5 != 0.0D && this.bf.a(this, this.bt.c(0.0D, -1.0D, var5)).size() == 0; var15 = var5) {
                    if (var5 < var19 && var5 >= -var19) {
                        var5 = 0.0D;
                    } else if (var5 > 0.0D) {
                        var5 -= var19;
                    } else {
                        var5 += var19;
                    }
                }
            }

            List var21 = this.bf.a(this, this.bt.a(var1, var3, var5));

            for (int var22 = 0; var22 < var21.size(); ++var22) {
                var3 = ((OAxisAlignedBB) var21.get(var22)).b(this.bt, var3);
            }

            this.bt.d(0.0D, var3, 0.0D);
            if (!this.bA && var13 != var3) {
                var5 = 0.0D;
                var3 = 0.0D;
                var1 = 0.0D;
            }

            boolean var42 = this.bu || var13 != var3 && var13 < 0.0D;

            int var23;

            for (var23 = 0; var23 < var21.size(); ++var23) {
                var1 = ((OAxisAlignedBB) var21.get(var23)).a(this.bt, var1);
            }

            this.bt.d(var1, 0.0D, 0.0D);
            if (!this.bA && var11 != var1) {
                var5 = 0.0D;
                var3 = 0.0D;
                var1 = 0.0D;
            }

            for (var23 = 0; var23 < var21.size(); ++var23) {
                var5 = ((OAxisAlignedBB) var21.get(var23)).c(this.bt, var5);
            }

            this.bt.d(0.0D, 0.0D, var5);
            if (!this.bA && var15 != var5) {
                var5 = 0.0D;
                var3 = 0.0D;
                var1 = 0.0D;
            }

            double var24;
            double var26;
            int var31;

            if (this.bM > 0.0F && var42 && (var18 || this.bL < 0.05F) && (var11 != var1 || var15 != var5)) {
                var24 = var1;
                var26 = var3;
                double var28 = var5;

                var1 = var11;
                var3 = (double) this.bM;
                var5 = var15;
                OAxisAlignedBB var30 = this.bt.b();

                this.bt.b(var17);
                var21 = this.bf.a(this, this.bt.a(var11, var3, var15));

                for (var31 = 0; var31 < var21.size(); ++var31) {
                    var3 = ((OAxisAlignedBB) var21.get(var31)).b(this.bt, var3);
                }

                this.bt.d(0.0D, var3, 0.0D);
                if (!this.bA && var13 != var3) {
                    var5 = 0.0D;
                    var3 = 0.0D;
                    var1 = 0.0D;
                }

                for (var31 = 0; var31 < var21.size(); ++var31) {
                    var1 = ((OAxisAlignedBB) var21.get(var31)).a(this.bt, var1);
                }

                this.bt.d(var1, 0.0D, 0.0D);
                if (!this.bA && var11 != var1) {
                    var5 = 0.0D;
                    var3 = 0.0D;
                    var1 = 0.0D;
                }

                for (var31 = 0; var31 < var21.size(); ++var31) {
                    var5 = ((OAxisAlignedBB) var21.get(var31)).c(this.bt, var5);
                }

                this.bt.d(0.0D, 0.0D, var5);
                if (!this.bA && var15 != var5) {
                    var5 = 0.0D;
                    var3 = 0.0D;
                    var1 = 0.0D;
                }

                if (!this.bA && var13 != var3) {
                    var5 = 0.0D;
                    var3 = 0.0D;
                    var1 = 0.0D;
                } else {
                    var3 = (double) (-this.bM);

                    for (var31 = 0; var31 < var21.size(); ++var31) {
                        var3 = ((OAxisAlignedBB) var21.get(var31)).b(this.bt, var3);
                    }

                    this.bt.d(0.0D, var3, 0.0D);
                }

                if (var24 * var24 + var28 * var28 >= var1 * var1 + var5 * var5) {
                    var1 = var24;
                    var3 = var26;
                    var5 = var28;
                    this.bt.b(var30);
                } else {
                    double var32 = this.bt.b - (double) ((int) this.bt.b);

                    if (var32 > 0.0D) {
                        this.bL = (float) ((double) this.bL + var32 + 0.01D);
                    }
                }
            }

            OProfiler.a();
            OProfiler.a("rest");
            this.bj = (this.bt.a + this.bt.d) / 2.0D;
            this.bk = this.bt.b + (double) this.bC - (double) this.bL;
            this.bl = (this.bt.c + this.bt.f) / 2.0D;
            this.bv = var11 != var1 || var15 != var5;
            this.bw = var13 != var3;
            this.bu = var13 != var3 && var13 < 0.0D;
            this.bx = this.bv || this.bw;
            this.a(var3, this.bu);
            if (var11 != var1) {
                this.bm = 0.0D;
            }

            if (var13 != var3) {
                this.bn = 0.0D;
            }

            if (var15 != var5) {
                this.bo = 0.0D;
            }

            var24 = this.bj - var7;
            var26 = this.bl - var9;
            int var34;
            int var35;
            int var43;

            if (this.g_() && !var18 && this.be == null) {
                this.bG = (float) ((double) this.bG + (double) OMathHelper.a(var24 * var24 + var26 * var26) * 0.6D);
                var34 = OMathHelper.b(this.bj);
                var35 = OMathHelper.b(this.bk - 0.20000000298023224D - (double) this.bC);
                var43 = OMathHelper.b(this.bl);
                var31 = this.bf.a(var34, var35, var43);
                if (var31 == 0 && this.bf.a(var34, var35 - 1, var43) == OBlock.bb.bO) {
                    var31 = this.bf.a(var34, var35 - 1, var43);
                }

                if (this.bG > (float) this.b && var31 > 0) {
                    this.b = (int) this.bG + 1;
                    this.a(var34, var35, var43, var31);
                    OBlock.m[var31].b(this.bf, var34, var35, var43, this);
                }
            }

            var34 = OMathHelper.b(this.bt.a + 0.001D);
            var35 = OMathHelper.b(this.bt.b + 0.001D);
            var43 = OMathHelper.b(this.bt.c + 0.001D);
            var31 = OMathHelper.b(this.bt.d - 0.001D);
            int var36 = OMathHelper.b(this.bt.e - 0.001D);
            int var37 = OMathHelper.b(this.bt.f - 0.001D);

            if (this.bf.a(var34, var35, var43, var31, var36, var37)) {
                for (int var38 = var34; var38 <= var31; ++var38) {
                    for (int var39 = var35; var39 <= var36; ++var39) {
                        for (int var40 = var43; var40 <= var37; ++var40) {
                            int var41 = this.bf.a(var38, var39, var40);

                            if (var41 > 0) {
                                OBlock.m[var41].a(this.bf, var38, var39, var40, this);
                            }
                        }
                    }
                }
            }

            boolean var44 = this.ay();

            if (this.bf.d(this.bt.e(0.001D, 0.001D, 0.001D))) {
                this.a(1);
                if (!var44) {
                    ++this.c;
                    if (this.c == 0) {
                        this.j(8);
                    }
                }
            } else if (this.c <= 0) {
                this.c = -this.bR;
            }

            if (var44 && this.c > 0) {
                this.bf.a(this, "random.fizz", 0.7F, 1.6F + (this.bP.nextFloat() - this.bP.nextFloat()) * 0.4F);
                this.c = -this.bR;
            }

            OProfiler.a();
        }
    }

    protected void a(int var1, int var2, int var3, int var4) {
        OStepSound var5 = OBlock.m[var4].bZ;

        if (this.bf.a(var1, var2 + 1, var3) == OBlock.aU.bO) {
            var5 = OBlock.aU.bZ;
            this.bf.a(this, var5.c(), var5.a() * 0.15F, var5.b());
        } else if (!OBlock.m[var4].cb.d()) {
            this.bf.a(this, var5.c(), var5.a() * 0.15F, var5.b());
        }

    }

    protected boolean g_() {
        return true;
    }

    protected void a(double var1, boolean var3) {
        if (var3) {
            if (this.bH > 0.0F) {
                this.b(this.bH);
                this.bH = 0.0F;
            }
        } else if (var1 < 0.0D) {
            this.bH = (float) ((double) this.bH - var1);
        }

    }

    public OAxisAlignedBB h_() {
        return null;
    }

    protected void a(int var1) {
        if (!this.bU) {
            // CanaryMod Damage Hook: Fire
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE, null, entity, var1)) {
                this.a(ODamageSource.a, var1);
            }
        }

    }

    public final boolean ax() {
        return this.bU;
    }

    protected void b(float var1) {
        if (this.bd != null) {
            this.bd.b(var1);
        }

    }

    public boolean ay() {
        return this.bS || this.bf.v(OMathHelper.b(this.bj), OMathHelper.b(this.bk), OMathHelper.b(this.bl));
    }

    public boolean az() {
        return this.bS;
    }

    public boolean i_() {
        return this.bf.a(this.bt.b(0.0D, -0.4000000059604645D, 0.0D).e(0.001D, 0.001D, 0.001D), OMaterial.g, this);
    }

    public boolean a(OMaterial var1) {
        double var2 = this.bk + (double) this.x();
        int var4 = OMathHelper.b(this.bj);
        int var5 = OMathHelper.d((float) OMathHelper.b(var2));
        int var6 = OMathHelper.b(this.bl);
        int var7 = this.bf.a(var4, var5, var6);

        if (var7 != 0 && OBlock.m[var7].cb == var1) {
            float var8 = OBlockFluid.d(this.bf.c(var4, var5, var6)) - 0.11111111F;
            float var9 = (float) (var5 + 1) - var8;

            return var2 < (double) var9;
        } else {
            return false;
        }
    }

    public float x() {
        return 0.0F;
    }

    public boolean aA() {
        return this.bf.a(this.bt.b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), OMaterial.h);
    }

    public void a(float var1, float var2, float var3) {
        float var4 = OMathHelper.c(var1 * var1 + var2 * var2);

        if (var4 >= 0.01F) {
            if (var4 < 1.0F) {
                var4 = 1.0F;
            }

            var4 = var3 / var4;
            var1 *= var4;
            var2 *= var4;
            float var5 = OMathHelper.a(this.bp * 3.1415927F / 180.0F);
            float var6 = OMathHelper.b(this.bp * 3.1415927F / 180.0F);

            this.bm += (double) (var1 * var6 - var2 * var5);
            this.bo += (double) (var2 * var6 + var1 * var5);
        }
    }

    public float a(float var1) {
        int var2 = OMathHelper.b(this.bj);
        int var3 = OMathHelper.b(this.bl);

        if (this.bf.g(var2, this.bf.c / 2, var3)) {
            double var4 = (this.bt.e - this.bt.b) * 0.66D;
            int var6 = OMathHelper.b(this.bk - (double) this.bC + var4);

            return this.bf.m(var2, var6, var3);
        } else {
            return 0.0F;
        }
    }

    public void a(OWorld var1) {
        this.bf = var1;
    }

    public void b(double var1, double var3, double var5, float var7, float var8) {
        this.bg = this.bj = var1;
        this.bh = this.bk = var3;
        this.bi = this.bl = var5;
        this.br = this.bp = var7;
        this.bs = this.bq = var8;
        this.bL = 0.0F;
        double var9 = (double) (this.br - var7);

        if (var9 < -180.0D) {
            this.br += 360.0F;
        }

        if (var9 >= 180.0D) {
            this.br -= 360.0F;
        }

        this.c(this.bj, this.bk, this.bl);
        this.c(var7, var8);
    }

    public void c(double var1, double var3, double var5, float var7, float var8) {
        this.bI = this.bg = this.bj = var1;
        this.bJ = this.bh = this.bk = var3 + (double) this.bC;
        this.bK = this.bi = this.bl = var5;
        this.bp = var7;
        this.bq = var8;
        this.c(this.bj, this.bk, this.bl);
    }

    public float h(OEntity var1) {
        float var2 = (float) (this.bj - var1.bj);
        float var3 = (float) (this.bk - var1.bk);
        float var4 = (float) (this.bl - var1.bl);

        return OMathHelper.c(var2 * var2 + var3 * var3 + var4 * var4);
    }

    public double e(double var1, double var3, double var5) {
        double var7 = this.bj - var1;
        double var9 = this.bk - var3;
        double var11 = this.bl - var5;

        return var7 * var7 + var9 * var9 + var11 * var11;
    }

    public double f(double var1, double var3, double var5) {
        double var7 = this.bj - var1;
        double var9 = this.bk - var3;
        double var11 = this.bl - var5;

        return (double) OMathHelper.a(var7 * var7 + var9 * var9 + var11 * var11);
    }

    public double i(OEntity var1) {
        double var2 = this.bj - var1.bj;
        double var4 = this.bk - var1.bk;
        double var6 = this.bl - var1.bl;

        return var2 * var2 + var4 * var4 + var6 * var6;
    }

    public void a_(OEntityPlayer var1) {}

    public void j(OEntity var1) {
        if (var1.bd != this && var1.be != this) {
            double var2 = var1.bj - this.bj;
            double var4 = var1.bl - this.bl;
            double var6 = OMathHelper.a(var2, var4);

            if (var6 >= 0.009999999776482582D) {
                var6 = (double) OMathHelper.a(var6);
                var2 /= var6;
                var4 /= var6;
                double var8 = 1.0D / var6;

                if (var8 > 1.0D) {
                    var8 = 1.0D;
                }

                var2 *= var8;
                var4 *= var8;
                var2 *= 0.05000000074505806D;
                var4 *= 0.05000000074505806D;
                var2 *= (double) (1.0F - this.bO);
                var4 *= (double) (1.0F - this.bO);
                this.b_(-var2, 0.0D, -var4);
                var1.b_(var2, 0.0D, var4);
            }

        }
    }

    public void b_(double var1, double var3, double var5) {
        this.bm += var1;
        this.bn += var3;
        this.bo += var5;
        this.cb = true;
    }

    protected void aB() {
        this.by = true;
    }

    public boolean a(ODamageSource var1, int var2) {
        this.aB();
        return false;
    }

    public boolean e_() {
        return false;
    }

    public boolean f_() {
        return false;
    }

    public void b(OEntity var1, int var2) {}

    public boolean c(ONBTTagCompound var1) {
        String var2 = this.aC();

        if (!this.bB && var2 != null) {
            var1.a("id", var2);
            this.d(var1);
            return true;
        } else {
            return false;
        }
    }

    public void d(ONBTTagCompound var1) {
        var1.a("Pos", (ONBTBase) this.a(new double[] { this.bj, this.bk + (double) this.bL, this.bl}));
        var1.a("Motion", (ONBTBase) this.a(new double[] { this.bm, this.bn, this.bo}));
        var1.a("Rotation", (ONBTBase) this.a(new float[] { this.bp, this.bq}));
        var1.a("FallDistance", this.bH);
        var1.a("Fire", (short) this.c);
        var1.a("Air", (short) this.aF());
        var1.a("OnGround", this.bu);
        this.b(var1);
    }

    public void e(ONBTTagCompound var1) {
        ONBTTagList var2 = var1.m("Pos");
        ONBTTagList var3 = var1.m("Motion");
        ONBTTagList var4 = var1.m("Rotation");

        this.bm = ((ONBTTagDouble) var3.a(0)).a;
        this.bn = ((ONBTTagDouble) var3.a(1)).a;
        this.bo = ((ONBTTagDouble) var3.a(2)).a;
        if (Math.abs(this.bm) > 10.0D) {
            this.bm = 0.0D;
        }

        if (Math.abs(this.bn) > 10.0D) {
            this.bn = 0.0D;
        }

        if (Math.abs(this.bo) > 10.0D) {
            this.bo = 0.0D;
        }

        this.bg = this.bI = this.bj = ((ONBTTagDouble) var2.a(0)).a;
        this.bh = this.bJ = this.bk = ((ONBTTagDouble) var2.a(1)).a;
        this.bi = this.bK = this.bl = ((ONBTTagDouble) var2.a(2)).a;
        this.br = this.bp = ((ONBTTagFloat) var4.a(0)).a;
        this.bs = this.bq = ((ONBTTagFloat) var4.a(1)).a;
        this.bH = var1.h("FallDistance");
        this.c = var1.e("Fire");
        this.l(var1.e("Air"));
        this.bu = var1.n("OnGround");
        this.c(this.bj, this.bk, this.bl);
        this.c(this.bp, this.bq);
        this.a(var1);
    }

    protected final String aC() {
        return OEntityList.b(this);
    }

    protected abstract void a(ONBTTagCompound var1);

    protected abstract void b(ONBTTagCompound var1);

    protected ONBTTagList a(double... var1) {
        ONBTTagList var2 = new ONBTTagList();
        double[] var3 = var1;
        int var4 = var1.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            double var6 = var3[var5];

            var2.a((ONBTBase) (new ONBTTagDouble((String) null, var6)));
        }

        return var2;
    }

    protected ONBTTagList a(float... var1) {
        ONBTTagList var2 = new ONBTTagList();
        float[] var3 = var1;
        int var4 = var1.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            float var6 = var3[var5];

            var2.a((ONBTBase) (new ONBTTagFloat((String) null, var6)));
        }

        return var2;
    }

    public OEntityItem b(int var1, int var2) {
        return this.a(var1, var2, 0.0F);
    }

    public OEntityItem a(int var1, int var2, float var3) {
        return this.a(new OItemStack(var1, var2, 0), var3);
    }

    public OEntityItem a(OItemStack var1, float var2) {
        OEntityItem var3 = new OEntityItem(this.bf, this.bj, this.bk + (double) var2, this.bl, var1);

        var3.c = 10;
        this.bf.b((OEntity) var3);
        return var3;
    }

    public boolean aj() {
        return !this.bB;
    }

    public boolean T() {
        for (int var1 = 0; var1 < 8; ++var1) {
            float var2 = ((float) ((var1 >> 0) % 2) - 0.5F) * this.bD * 0.8F;
            float var3 = ((float) ((var1 >> 1) % 2) - 0.5F) * 0.1F;
            float var4 = ((float) ((var1 >> 2) % 2) - 0.5F) * this.bD * 0.8F;
            int var5 = OMathHelper.b(this.bj + (double) var2);
            int var6 = OMathHelper.b(this.bk + (double) this.x() + (double) var3);
            int var7 = OMathHelper.b(this.bl + (double) var4);

            if (this.bf.e(var5, var6, var7)) {
                return true;
            }
        }

        return false;
    }

    public boolean b(OEntityPlayer var1) {
        return false;
    }

    public OAxisAlignedBB a_(OEntity var1) {
        return null;
    }

    public void M() {
        if (this.be.bB) {
            this.be = null;
        } else {
            this.bm = 0.0D;
            this.bn = 0.0D;
            this.bo = 0.0D;
            this.w_();
            if (this.be != null) {
                this.be.i();
                this.f += (double) (this.be.bp - this.be.br);

                for (this.e += (double) (this.be.bq - this.be.bs); this.f >= 180.0D; this.f -= 360.0D) {
                    ;
                }

                while (this.f < -180.0D) {
                    this.f += 360.0D;
                }

                while (this.e >= 180.0D) {
                    this.e -= 360.0D;
                }

                while (this.e < -180.0D) {
                    this.e += 360.0D;
                }

                double var1 = this.f * 0.5D;
                double var3 = this.e * 0.5D;
                float var5 = 10.0F;

                if (var1 > (double) var5) {
                    var1 = (double) var5;
                }

                if (var1 < (double) (-var5)) {
                    var1 = (double) (-var5);
                }

                if (var3 > (double) var5) {
                    var3 = (double) var5;
                }

                if (var3 < (double) (-var5)) {
                    var3 = (double) (-var5);
                }

                this.f -= var1;
                this.e -= var3;
                this.bp = (float) ((double) this.bp + var1);
                this.bq = (float) ((double) this.bq + var3);
            }
        }
    }

    public void i() {
        this.bd.c(this.bj, this.bk + this.q() + this.bd.R(), this.bl);
    }

    public double R() {
        return (double) this.bC;
    }

    public double q() {
        return (double) this.bE * 0.75D;
    }

    public void b(OEntity var1) {
        this.e = 0.0D;
        this.f = 0.0D;
        if (var1 == null) {
            if (this.be != null) {
                this.c(this.be.bj, this.be.bt.b + (double) this.be.bE, this.be.bl, this.bp, this.bq);
                this.be.bd = null;
            }

            this.be = null;
        } else if (this.be == var1) {
            this.be.bd = null;
            this.be = null;
            this.c(var1.bj, var1.bt.b + (double) var1.bE, var1.bl, this.bp, this.bq);
        } else {
            if (this.be != null) {
                this.be.bd = null;
            }

            if (var1.bd != null) {
                var1.bd.be = null;
            }

            this.be = var1;
            var1.bd = this;
        }
    }

    public float j_() {
        return 0.1F;
    }

    public OVec3D ap() {
        return null;
    }

    public void Y() {}

    public OItemStack[] v() {
        return null;
    }

    public boolean z() {
        return this.c > 0 || this.k(0);
    }

    public boolean aD() {
        return this.k(1);
    }

    public void e(boolean var1) {
        this.a(1, var1);
    }

    public boolean aE() {
        return this.k(3);
    }

    public void f(boolean var1) {
        this.a(3, var1);
    }

    public void g(boolean var1) {
        this.a(4, var1);
    }

    protected boolean k(int var1) {
        return (this.bV.a(0) & 1 << var1) != 0;
    }

    protected void a(int var1, boolean var2) {
        byte var3 = this.bV.a(0);

        if (var2) {
            this.bV.b(0, Byte.valueOf((byte) (var3 | 1 << var1)));
        } else {
            this.bV.b(0, Byte.valueOf((byte) (var3 & ~(1 << var1))));
        }

    }

    public int aF() {
        return this.bV.b(1);
    }

    public void l(int var1) {
        this.bV.b(1, Short.valueOf((short) var1));
    }

    public void a(OEntityLightningBolt var1) {
        // CanaryMod Damage Hook: Lightning
        if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LIGHTNING, null, entity, 5)) {
            return;
        }
        this.a(5);
        ++this.c;
        if (this.c == 0) {
            this.j(8);
        }
    }

    public void a(OEntityLiving var1) {}

    protected boolean g(double var1, double var3, double var5) {
        int var7 = OMathHelper.b(var1);
        int var8 = OMathHelper.b(var3);
        int var9 = OMathHelper.b(var5);
        double var10 = var1 - (double) var7;
        double var12 = var3 - (double) var8;
        double var14 = var5 - (double) var9;

        if (this.bf.e(var7, var8, var9)) {
            boolean var16 = !this.bf.e(var7 - 1, var8, var9);
            boolean var17 = !this.bf.e(var7 + 1, var8, var9);
            boolean var18 = !this.bf.e(var7, var8 - 1, var9);
            boolean var19 = !this.bf.e(var7, var8 + 1, var9);
            boolean var20 = !this.bf.e(var7, var8, var9 - 1);
            boolean var21 = !this.bf.e(var7, var8, var9 + 1);
            byte var22 = -1;
            double var23 = 9999.0D;

            if (var16 && var10 < var23) {
                var23 = var10;
                var22 = 0;
            }

            if (var17 && 1.0D - var10 < var23) {
                var23 = 1.0D - var10;
                var22 = 1;
            }

            if (var18 && var12 < var23) {
                var23 = var12;
                var22 = 2;
            }

            if (var19 && 1.0D - var12 < var23) {
                var23 = 1.0D - var12;
                var22 = 3;
            }

            if (var20 && var14 < var23) {
                var23 = var14;
                var22 = 4;
            }

            if (var21 && 1.0D - var14 < var23) {
                var23 = 1.0D - var14;
                var22 = 5;
            }

            float var25 = this.bP.nextFloat() * 0.2F + 0.1F;

            if (var22 == 0) {
                this.bm = (double) (-var25);
            }

            if (var22 == 1) {
                this.bm = (double) var25;
            }

            if (var22 == 2) {
                this.bn = (double) (-var25);
            }

            if (var22 == 3) {
                this.bn = (double) var25;
            }

            if (var22 == 4) {
                this.bo = (double) (-var25);
            }

            if (var22 == 5) {
                this.bo = (double) var25;
            }

            return true;
        } else {
            return false;
        }
    }

    public void s() {
        this.bz = true;
    }

    public String ad() {
        String var1 = OEntityList.b(this);

        if (var1 == null) {
            var1 = "generic";
        }

        return OStatCollector.a("entity." + var1 + ".name");
    }

    public OEntity[] aG() {
        return null;
    }

    public boolean a(OEntity var1) {
        return this == var1;
    }

}
