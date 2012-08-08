import java.util.List;
import java.util.Random;


public abstract class OEntity {

    private static int a = 0;
    public int bd;
    public double be;
    public boolean bf;
    public OEntity bg;
    public OEntity bh;
    public OWorld bi;
    public double bj;
    public double bk;
    public double bl;
    public double bm;
    public double bn;
    public double bo;
    public double bp;
    public double bq;
    public double br;
    public float bs;
    public float bt;
    public float bu;
    public float bv;
    public final OAxisAlignedBB bw;
    public boolean bx;
    public boolean by;
    public boolean bz;
    public boolean bA;
    public boolean bB;
    protected boolean bC;
    public boolean bD;
    public boolean bE;
    public float bF;
    public float bG;
    public float bH;
    public float bI;
    public float bJ;
    public float bK;
    private int b;
    public double bL;
    public double bM;
    public double bN;
    public float bO;
    public float bP;
    public boolean bQ;
    public float bR;
    protected Random bS;
    public int bT;
    public int bU;
    protected int c; // CanaryMod: private -> protected
    protected boolean bV;
    public int bW;
    private boolean d;
    protected boolean bX;
    protected ODataWatcher bY;
    private double e;
    private double f;
    public boolean bZ;
    public int ca;
    public int cb;
    public int cc;
    public boolean cd;
    public boolean ce;
    // CanaryMod Start
    BaseEntity entity = new BaseEntity(this);
    public static PluginLoader manager = etc.getLoader();

    // CanaryMod end
    public OEntity(OWorld oworld) {
        super();
        this.bd = a++;
        this.be = 1.0D;
        this.bf = false;
        this.bw = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.bx = false;
        this.bA = false;
        this.bB = false;
        this.bD = true;
        this.bE = false;
        this.bF = 0.0F;
        this.bG = 0.6F;
        this.bH = 1.8F;
        this.bI = 0.0F;
        this.bJ = 0.0F;
        this.bK = 0.0F;
        this.b = 1;
        this.bO = 0.0F;
        this.bP = 0.0F;
        this.bQ = false;
        this.bR = 0.0F;
        this.bS = new Random();
        this.bT = 0;
        this.bU = 1;
        this.c = 0;
        this.bV = false;
        this.bW = 0;
        this.d = true;
        this.bX = false;
        this.bY = new ODataWatcher();
        this.bZ = false;
        this.bi = oworld;
        this.c(0.0D, 0.0D, 0.0D);
        this.bY.a(0, Byte.valueOf((byte) 0));
        this.bY.a(1, Short.valueOf((short) 300));
        this.b();
    }

    protected abstract void b();

    public ODataWatcher aP() {
        return this.bY;
    }

    public boolean equals(Object object) {
        return object instanceof OEntity ? ((OEntity) object).bd == this.bd : false;
    }

    public int hashCode() {
        return this.bd;
    }

    public void X() {
        this.bE = true;
    }

    protected void b(float f, float f1) {
        this.bG = f;
        this.bH = f1;
    }

    protected void c(float f, float f1) {
        this.bs = f % 360.0F;
        this.bt = f1 % 360.0F;
    }

    public void c(double d0, double d1, double d2) {
        this.bm = d0;
        this.bn = d1;
        this.bo = d2;
        float f = this.bG / 2.0F;
        float f1 = this.bH;

        this.bw.c(d0 - (double) f, d1 - (double) this.bF + (double) this.bO, d2 - (double) f, d0 + (double) f, d1 - (double) this.bF + (double) this.bO + (double) f1, d2 + (double) f);
    }

    public void F_() {
        this.aA();
    }

    public void aA() {
        OProfiler.a("entityBaseTick");
        if (this.bh != null && this.bh.bE) {
            this.bh = null;
        }

        ++this.bT;
        this.bI = this.bJ;
        this.bj = this.bm;
        this.bk = this.bn;
        this.bl = this.bo;
        this.bv = this.bt;
        this.bu = this.bs;
        int i;

        if (this.aZ() && !this.aU()) {
            int j = OMathHelper.b(this.bm);
            int k = OMathHelper.b(this.bn - 0.20000000298023224D - (double) this.bF);

            i = OMathHelper.b(this.bo);
            int l = this.bi.a(j, k, i);

            if (l > 0) {
                this.bi.a("tilecrack_" + l, this.bm + ((double) this.bS.nextFloat() - 0.5D) * (double) this.bG, this.bw.b + 0.1D, this.bo + ((double) this.bS.nextFloat() - 0.5D) * (double) this.bG, -this.bp * 4.0D, 1.5D, -this.br * 4.0D);
            }
        }

        if (this.h_()) {
            if (!this.bV && !this.d) {
                float f = OMathHelper.a(this.bp * this.bp * 0.20000000298023224D + this.bq * this.bq + this.br * this.br * 0.20000000298023224D) * 0.2F;

                if (f > 1.0F) {
                    f = 1.0F;
                }

                this.bi.a(this, "random.splash", f, 1.0F + (this.bS.nextFloat() - this.bS.nextFloat()) * 0.4F);
                float f1 = (float) OMathHelper.b(this.bw.b);

                float f2;
                float f3;

                for (i = 0; (float) i < 1.0F + this.bG * 20.0F; ++i) {
                    f3 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG;
                    f2 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG;
                    this.bi.a("bubble", this.bm + (double) f3, (double) (f1 + 1.0F), this.bo + (double) f2, this.bp, this.bq - (double) (this.bS.nextFloat() * 0.2F), this.br);
                }

                for (i = 0; (float) i < 1.0F + this.bG * 20.0F; ++i) {
                    f3 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG;
                    f2 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG;
                    this.bi.a("splash", this.bm + (double) f3, (double) (f1 + 1.0F), this.bo + (double) f2, this.bp, this.bq, this.br);
                }
            }

            this.bK = 0.0F;
            this.bV = true;
            this.c = 0;
        } else {
            this.bV = false;
        }

        if (this.bi.F) {
            this.c = 0;
        } else if (this.c > 0) {
            if (this.bX) {
                this.c -= 4;
                if (this.c < 0) {
                    this.c = 0;
                }
            } else {
                if (this.c % 20 == 0) {
                    // CanaryMod Damage hook: Periodic burn damage
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE_TICK, null, entity, 1)) {
                        this.a(ODamageSource.c, 1);
                    }
                }

                --this.c;
            }
        }

        if (this.aV()) {
            this.aQ();
            this.bK *= 0.5F;
        }

        if (this.bn < -64.0D) {
            this.aI();
        }

        if (!this.bi.F) {
            this.a(0, this.c > 0);
            this.a(2, this.bh != null);
        }

        this.d = false;
        OProfiler.a();
    }

    protected void aQ() {
        if (!this.bX) {
            // CanaryMod Damage hook: Lava
            if (this instanceof OEntityLiving) {
                if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, entity, 4)) {
                    return;
                }
            }
            this.a(ODamageSource.d, 4);
            this.i(15);
        }

    }

    public void i(int i) {
        int j = i * 20;

        if (this.c < j) {
            this.c = j;
        }

    }

    public void aR() {
        this.c = 0;
    }

    protected void aI() {
        this.X();
    }

    public boolean d(double d0, double d1, double d2) {
        OAxisAlignedBB oaxisalignedbb = this.bw.c(d0, d1, d2);
        List list = this.bi.a(this, oaxisalignedbb);

        return list.size() > 0 ? false : !this.bi.c(oaxisalignedbb);
    }

    public void a(double d0, double d1, double d2) {
        if (this.bQ) {
            this.bw.d(d0, d1, d2);
            this.bm = (this.bw.a + this.bw.d) / 2.0D;
            this.bn = this.bw.b + (double) this.bF - (double) this.bO;
            this.bo = (this.bw.c + this.bw.f) / 2.0D;
        } else {
            OProfiler.a("move");
            this.bO *= 0.4F;
            double d3 = this.bm;
            double d4 = this.bo;

            if (this.bC) {
                this.bC = false;
                d0 *= 0.25D;
                d1 *= 0.05000000074505806D;
                d2 *= 0.25D;
                this.bp = 0.0D;
                this.bq = 0.0D;
                this.br = 0.0D;
            }

            double d5 = d0;
            double d6 = d1;
            double d7 = d2;
            OAxisAlignedBB oaxisalignedbb = this.bw.b();
            boolean flag = this.bx && this.aY() && this instanceof OEntityPlayer;

            if (flag) {
                double d8;

                for (d8 = 0.05D; d0 != 0.0D && this.bi.a(this, this.bw.c(d0, -1.0D, 0.0D)).size() == 0; d5 = d0) {
                    if (d0 < d8 && d0 >= -d8) {
                        d0 = 0.0D;
                    } else if (d0 > 0.0D) {
                        d0 -= d8;
                    } else {
                        d0 += d8;
                    }
                }

                for (; d2 != 0.0D && this.bi.a(this, this.bw.c(0.0D, -1.0D, d2)).size() == 0; d7 = d2) {
                    if (d2 < d8 && d2 >= -d8) {
                        d2 = 0.0D;
                    } else if (d2 > 0.0D) {
                        d2 -= d8;
                    } else {
                        d2 += d8;
                    }
                }

                while (d0 != 0.0D && d2 != 0.0D && this.bi.a(this, this.bw.c(d0, -1.0D, d2)).size() == 0) {
                    if (d0 < d8 && d0 >= -d8) {
                        d0 = 0.0D;
                    } else if (d0 > 0.0D) {
                        d0 -= d8;
                    } else {
                        d0 += d8;
                    }

                    if (d2 < d8 && d2 >= -d8) {
                        d2 = 0.0D;
                    } else if (d2 > 0.0D) {
                        d2 -= d8;
                    } else {
                        d2 += d8;
                    }

                    d5 = d0;
                    d7 = d2;
                }
            }

            List list = this.bi.a(this, this.bw.a(d0, d1, d2));

            for (int i = 0; i < list.size(); ++i) {
                d1 = ((OAxisAlignedBB) list.get(i)).b(this.bw, d1);
            }

            this.bw.d(0.0D, d1, 0.0D);
            if (!this.bD && d6 != d1) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            boolean flag1 = this.bx || d6 != d1 && d6 < 0.0D;

            int j;

            for (j = 0; j < list.size(); ++j) {
                d0 = ((OAxisAlignedBB) list.get(j)).a(this.bw, d0);
            }

            this.bw.d(d0, 0.0D, 0.0D);
            if (!this.bD && d5 != d0) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            for (j = 0; j < list.size(); ++j) {
                d2 = ((OAxisAlignedBB) list.get(j)).c(this.bw, d2);
            }

            this.bw.d(0.0D, 0.0D, d2);
            if (!this.bD && d7 != d2) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            double d9;
            double d10;
            int k;

            if (this.bP > 0.0F && flag1 && (flag || this.bO < 0.05F) && (d5 != d0 || d7 != d2)) {
                d9 = d0;
                d10 = d1;
                double d11 = d2;

                d0 = d5;
                d1 = (double) this.bP;
                d2 = d7;
                OAxisAlignedBB oaxisalignedbb1 = this.bw.b();

                this.bw.b(oaxisalignedbb);
                list = this.bi.a(this, this.bw.a(d5, d1, d7));

                for (k = 0; k < list.size(); ++k) {
                    d1 = ((OAxisAlignedBB) list.get(k)).b(this.bw, d1);
                }

                this.bw.d(0.0D, d1, 0.0D);
                if (!this.bD && d6 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (k = 0; k < list.size(); ++k) {
                    d0 = ((OAxisAlignedBB) list.get(k)).a(this.bw, d0);
                }

                this.bw.d(d0, 0.0D, 0.0D);
                if (!this.bD && d5 != d0) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (k = 0; k < list.size(); ++k) {
                    d2 = ((OAxisAlignedBB) list.get(k)).c(this.bw, d2);
                }

                this.bw.d(0.0D, 0.0D, d2);
                if (!this.bD && d7 != d2) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                if (!this.bD && d6 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                } else {
                    d1 = (double) (-this.bP);

                    for (k = 0; k < list.size(); ++k) {
                        d1 = ((OAxisAlignedBB) list.get(k)).b(this.bw, d1);
                    }

                    this.bw.d(0.0D, d1, 0.0D);
                }

                if (d9 * d9 + d11 * d11 >= d0 * d0 + d2 * d2) {
                    d0 = d9;
                    d1 = d10;
                    d2 = d11;
                    this.bw.b(oaxisalignedbb1);
                } else {
                    double d12 = this.bw.b - (double) ((int) this.bw.b);

                    if (d12 > 0.0D) {
                        this.bO = (float) ((double) this.bO + d12 + 0.01D);
                    }
                }
            }

            OProfiler.a();
            OProfiler.a("rest");
            this.bm = (this.bw.a + this.bw.d) / 2.0D;
            this.bn = this.bw.b + (double) this.bF - (double) this.bO;
            this.bo = (this.bw.c + this.bw.f) / 2.0D;
            this.by = d5 != d0 || d7 != d2;
            this.bz = d6 != d1;
            this.bx = d6 != d1 && d6 < 0.0D;
            this.bA = this.by || this.bz;
            this.a(d1, this.bx);
            if (d5 != d0) {
                this.bp = 0.0D;
            }

            if (d6 != d1) {
                this.bq = 0.0D;
            }

            if (d7 != d2) {
                this.br = 0.0D;
            }

            d9 = this.bm - d3;
            d10 = this.bo - d4;
            int l;
            int i1;
            int j1;

            if (this.g_() && !flag && this.bh == null) {
                this.bJ = (float) ((double) this.bJ + (double) OMathHelper.a(d9 * d9 + d10 * d10) * 0.6D);
                l = OMathHelper.b(this.bm);
                i1 = OMathHelper.b(this.bn - 0.20000000298023224D - (double) this.bF);
                j1 = OMathHelper.b(this.bo);
                k = this.bi.a(l, i1, j1);
                if (k == 0 && this.bi.a(l, i1 - 1, j1) == OBlock.aZ.bO) {
                    k = this.bi.a(l, i1 - 1, j1);
                }

                if (this.bJ > (float) this.b && k > 0) {
                    this.b = (int) this.bJ + 1;
                    this.a(l, i1, j1, k);
                    OBlock.m[k].b(this.bi, l, i1, j1, this);
                }
            }

            l = OMathHelper.b(this.bw.a + 0.001D);
            i1 = OMathHelper.b(this.bw.b + 0.001D);
            j1 = OMathHelper.b(this.bw.c + 0.001D);
            k = OMathHelper.b(this.bw.d - 0.001D);
            int k1 = OMathHelper.b(this.bw.e - 0.001D);
            int l1 = OMathHelper.b(this.bw.f - 0.001D);

            if (this.bi.a(l, i1, j1, k, k1, l1)) {
                for (int i2 = l; i2 <= k; ++i2) {
                    for (int j2 = i1; j2 <= k1; ++j2) {
                        for (int k2 = j1; k2 <= l1; ++k2) {
                            int l2 = this.bi.a(i2, j2, k2);

                            if (l2 > 0) {
                                OBlock.m[l2].a(this.bi, i2, j2, k2, this);
                            }
                        }
                    }
                }
            }

            boolean flag2 = this.aT();

            if (this.bi.d(this.bw.e(0.001D, 0.001D, 0.001D))) {
                this.a(1);
                if (!flag2) {
                    ++this.c;
                    if (this.c == 0) {
                        this.i(8);
                    }
                }
            } else if (this.c <= 0) {
                this.c = -this.bU;
            }

            if (flag2 && this.c > 0) {
                this.bi.a(this, "random.fizz", 0.7F, 1.6F + (this.bS.nextFloat() - this.bS.nextFloat()) * 0.4F);
                this.c = -this.bU;
            }

            OProfiler.a();
        }
    }

    protected void a(int i, int j, int k, int l) {
        OStepSound ostepsound = OBlock.m[l].cb;

        if (this.bi.a(i, j + 1, k) == OBlock.aS.bO) {
            ostepsound = OBlock.aS.cb;
            this.bi.a(this, ostepsound.c(), ostepsound.a() * 0.15F, ostepsound.b());
        } else if (!OBlock.m[l].cd.d()) {
            this.bi.a(this, ostepsound.c(), ostepsound.a() * 0.15F, ostepsound.b());
        }

    }

    protected boolean g_() {
        return true;
    }

    protected void a(double d0, boolean flag) {
        if (flag) {
            if (this.bK > 0.0F) {
                if (this instanceof OEntityLiving) {
                    int i = OMathHelper.b(this.bm);
                    int j = OMathHelper.b(this.bn - 0.20000000298023224D - (double) this.bF);
                    int k = OMathHelper.b(this.bo);
                    int l = this.bi.a(i, j, k);

                    if (l == 0 && this.bi.a(i, j - 1, k) == OBlock.aZ.bO) {
                        l = this.bi.a(i, j - 1, k);
                    }

                    if (l > 0) {
                        OBlock.m[l].a(this.bi, i, j, k, this, this.bK);
                    }
                }

                this.a(this.bK);
                this.bK = 0.0F;
            }
        } else if (d0 < 0.0D) {
            this.bK = (float) ((double) this.bK - d0);
        }

    }

    public OAxisAlignedBB h() {
        return null;
    }

    protected void a(int i) {
        if (!this.bX) {
            // CanaryMod Damage Hook: Fire
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE, null, entity, i)) {
                this.a(ODamageSource.b, i);
            }
        }

    }

    public final boolean aS() {
        return this.bX;
    }

    protected void a(float f) {
        if (this.bg != null) {
            this.bg.a(f);
        }

    }

    public boolean aT() {
        return this.bV || this.bi.y(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo));
    }

    public boolean aU() {
        return this.bV;
    }

    public boolean h_() {
        return this.bi.a(this.bw.b(0.0D, -0.4000000059604645D, 0.0D).e(0.001D, 0.001D, 0.001D), OMaterial.g, this);
    }

    public boolean a(OMaterial omaterial) {
        double d0 = this.bn + (double) this.B();
        int i = OMathHelper.b(this.bm);
        int j = OMathHelper.d((float) OMathHelper.b(d0));
        int k = OMathHelper.b(this.bo);
        int l = this.bi.a(i, j, k);

        if (l != 0 && OBlock.m[l].cd == omaterial) {
            float f = OBlockFluid.d(this.bi.c(i, j, k)) - 0.11111111F;
            float f1 = (float) (j + 1) - f;

            return d0 < (double) f1;
        } else {
            return false;
        }
    }

    public float B() {
        return 0.0F;
    }

    public boolean aV() {
        return this.bi.a(this.bw.b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), OMaterial.h);
    }

    public void a(float f, float f1, float f2) {
        float f3 = OMathHelper.c(f * f + f1 * f1);

        if (f3 >= 0.01F) {
            if (f3 < 1.0F) {
                f3 = 1.0F;
            }

            f3 = f2 / f3;
            f *= f3;
            f1 *= f3;
            float f4 = OMathHelper.a(this.bs * 3.1415927F / 180.0F);
            float f5 = OMathHelper.b(this.bs * 3.1415927F / 180.0F);

            this.bp += (double) (f * f5 - f1 * f4);
            this.br += (double) (f1 * f5 + f * f4);
        }
    }

    public float b(float f) {
        int i = OMathHelper.b(this.bm);
        int j = OMathHelper.b(this.bo);

        if (this.bi.i(i, 0, j)) {
            double d0 = (this.bw.e - this.bw.b) * 0.66D;
            int k = OMathHelper.b(this.bn - (double) this.bF + d0);

            return this.bi.p(i, k, j);
        } else {
            return 0.0F;
        }
    }

    public void a(OWorld oworld) {
        this.bi = oworld;
    }

    public void b(double d0, double d1, double d2, float f, float f1) {
        this.bj = this.bm = d0;
        this.bk = this.bn = d1;
        this.bl = this.bo = d2;
        this.bu = this.bs = f;
        this.bv = this.bt = f1;
        this.bO = 0.0F;
        double d3 = (double) (this.bu - f);

        if (d3 < -180.0D) {
            this.bu += 360.0F;
        }

        if (d3 >= 180.0D) {
            this.bu -= 360.0F;
        }

        this.c(this.bm, this.bn, this.bo);
        this.c(f, f1);
    }

    public void c(double d0, double d1, double d2, float f, float f1) {
        this.bL = this.bj = this.bm = d0;
        this.bM = this.bk = this.bn = d1 + (double) this.bF;
        this.bN = this.bl = this.bo = d2;
        this.bs = f;
        this.bt = f1;
        this.c(this.bm, this.bn, this.bo);
    }

    public float i(OEntity oentity) {
        float f = (float) (this.bm - oentity.bm);
        float f1 = (float) (this.bn - oentity.bn);
        float f2 = (float) (this.bo - oentity.bo);

        return OMathHelper.c(f * f + f1 * f1 + f2 * f2);
    }

    public double e(double d0, double d1, double d2) {
        double d3 = this.bm - d0;
        double d4 = this.bn - d1;
        double d5 = this.bo - d2;

        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public double f(double d0, double d1, double d2) {
        double d3 = this.bm - d0;
        double d4 = this.bn - d1;
        double d5 = this.bo - d2;

        return (double) OMathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);
    }

    public double j(OEntity oentity) {
        double d0 = this.bm - oentity.bm;
        double d1 = this.bn - oentity.bn;
        double d2 = this.bo - oentity.bo;

        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public void a_(OEntityPlayer oentityplayer) {}

    public void k(OEntity oentity) {
        if (oentity.bg != this && oentity.bh != this) {
            double d0 = oentity.bm - this.bm;
            double d1 = oentity.bo - this.bo;
            double d2 = OMathHelper.a(d0, d1);

            if (d2 >= 0.009999999776482582D) {
                d2 = (double) OMathHelper.a(d2);
                d0 /= d2;
                d1 /= d2;
                double d3 = 1.0D / d2;

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                d0 *= d3;
                d1 *= d3;
                d0 *= 0.05000000074505806D;
                d1 *= 0.05000000074505806D;
                d0 *= (double) (1.0F - this.bR);
                d1 *= (double) (1.0F - this.bR);
                this.b_(-d0, 0.0D, -d1);
                oentity.b_(d0, 0.0D, d1);
            }

        }
    }

    public void b_(double d0, double d1, double d2) {
        this.bp += d0;
        this.bq += d1;
        this.br += d2;
        this.ce = true;
    }

    protected void aW() {
        this.bB = true;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        this.aW();
        return false;
    }

    public boolean o_() {
        return false;
    }

    public boolean e_() {
        return false;
    }

    public void b(OEntity oentity, int i) {}

    public boolean c(ONBTTagCompound onbttagcompound) {
        String s = this.aX();

        if (!this.bE && s != null) {
            onbttagcompound.a("id", s);
            this.d(onbttagcompound);
            return true;
        } else {
            return false;
        }
    }

    public void d(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Pos", (ONBTBase) this.a(new double[] { this.bm, this.bn + (double) this.bO, this.bo}));
        onbttagcompound.a("Motion", (ONBTBase) this.a(new double[] { this.bp, this.bq, this.br}));
        onbttagcompound.a("Rotation", (ONBTBase) this.a(new float[] { this.bs, this.bt}));
        onbttagcompound.a("FallDistance", this.bK);
        onbttagcompound.a("Fire", (short) this.c);
        onbttagcompound.a("Air", (short) this.ba());
        onbttagcompound.a("OnGround", this.bx);
        this.b(onbttagcompound);
    }

    public void e(ONBTTagCompound onbttagcompound) {
        ONBTTagList onbttaglist = onbttagcompound.n("Pos");
        ONBTTagList onbttaglist1 = onbttagcompound.n("Motion");
        ONBTTagList onbttaglist2 = onbttagcompound.n("Rotation");

        this.bp = ((ONBTTagDouble) onbttaglist1.a(0)).a;
        this.bq = ((ONBTTagDouble) onbttaglist1.a(1)).a;
        this.br = ((ONBTTagDouble) onbttaglist1.a(2)).a;
        if (Math.abs(this.bp) > 10.0D) {
            this.bp = 0.0D;
        }

        if (Math.abs(this.bq) > 10.0D) {
            this.bq = 0.0D;
        }

        if (Math.abs(this.br) > 10.0D) {
            this.br = 0.0D;
        }

        this.bj = this.bL = this.bm = ((ONBTTagDouble) onbttaglist.a(0)).a;
        this.bk = this.bM = this.bn = ((ONBTTagDouble) onbttaglist.a(1)).a;
        this.bl = this.bN = this.bo = ((ONBTTagDouble) onbttaglist.a(2)).a;
        this.bu = this.bs = ((ONBTTagFloat) onbttaglist2.a(0)).a;
        this.bv = this.bt = ((ONBTTagFloat) onbttaglist2.a(1)).a;
        this.bK = onbttagcompound.h("FallDistance");
        this.c = onbttagcompound.e("Fire");
        this.k(onbttagcompound.e("Air"));
        this.bx = onbttagcompound.o("OnGround");
        this.c(this.bm, this.bn, this.bo);
        this.c(this.bs, this.bt);
        this.a(onbttagcompound);
    }

    protected final String aX() {
        return OEntityList.b(this);
    }

    protected abstract void a(ONBTTagCompound onbttagcompound);

    protected abstract void b(ONBTTagCompound onbttagcompound);

    protected ONBTTagList a(double... adouble) {
        ONBTTagList onbttaglist = new ONBTTagList();
        double[] adouble1 = adouble;
        int i = adouble.length;

        for (int j = 0; j < i; ++j) {
            double d0 = adouble1[j];

            onbttaglist.a((ONBTBase) (new ONBTTagDouble((String) null, d0)));
        }

        return onbttaglist;
    }

    protected ONBTTagList a(float... afloat) {
        ONBTTagList onbttaglist = new ONBTTagList();
        float[] afloat1 = afloat;
        int i = afloat.length;

        for (int j = 0; j < i; ++j) {
            float f = afloat1[j];

            onbttaglist.a((ONBTBase) (new ONBTTagFloat((String) null, f)));
        }

        return onbttaglist;
    }

    public OEntityItem b(int i, int j) {
        return this.a(i, j, 0.0F);
    }

    public OEntityItem a(int i, int j, float f) {
        return this.a(new OItemStack(i, j, 0), f);
    }

    public OEntityItem a(OItemStack oitemstack, float f) {
        OEntityItem oentityitem = new OEntityItem(this.bi, this.bm, this.bn + (double) f, this.bo, oitemstack);

        oentityitem.c = 10;
        this.bi.b((OEntity) oentityitem);
        return oentityitem;
    }

    public boolean aE() {
        return !this.bE;
    }

    public boolean Y() {
        for (int i = 0; i < 8; ++i) {
            float f = ((float) ((i >> 0) % 2) - 0.5F) * this.bG * 0.8F;
            float f1 = ((float) ((i >> 1) % 2) - 0.5F) * 0.1F;
            float f2 = ((float) ((i >> 2) % 2) - 0.5F) * this.bG * 0.8F;
            int j = OMathHelper.b(this.bm + (double) f);
            int k = OMathHelper.b(this.bn + (double) this.B() + (double) f1);
            int l = OMathHelper.b(this.bo + (double) f2);

            if (this.bi.e(j, k, l)) {
                return true;
            }
        }

        return false;
    }

    public boolean b(OEntityPlayer oentityplayer) {
        return false;
    }

    public OAxisAlignedBB b_(OEntity oentity) {
        return null;
    }

    public void R() {
        if (this.bh.bE) {
            this.bh = null;
        } else {
            this.bp = 0.0D;
            this.bq = 0.0D;
            this.br = 0.0D;
            this.F_();
            if (this.bh != null) {
                this.bh.i_();
                this.f += (double) (this.bh.bs - this.bh.bu);

                for (this.e += (double) (this.bh.bt - this.bh.bv); this.f >= 180.0D; this.f -= 360.0D) {
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

                double d0 = this.f * 0.5D;
                double d1 = this.e * 0.5D;
                float f = 10.0F;

                if (d0 > (double) f) {
                    d0 = (double) f;
                }

                if (d0 < (double) (-f)) {
                    d0 = (double) (-f);
                }

                if (d1 > (double) f) {
                    d1 = (double) f;
                }

                if (d1 < (double) (-f)) {
                    d1 = (double) (-f);
                }

                this.f -= d0;
                this.e -= d1;
                this.bs = (float) ((double) this.bs + d0);
                this.bt = (float) ((double) this.bt + d1);
            }
        }
    }

    public void i_() {
        this.bg.c(this.bm, this.bn + this.x_() + this.bg.W(), this.bo);
    }

    public double W() {
        return (double) this.bF;
    }

    public double x_() {
        return (double) this.bH * 0.75D;
    }

    public void b(OEntity oentity) {
        this.e = 0.0D;
        this.f = 0.0D;
        if (oentity == null) {
            if (this.bh != null) {
                this.c(this.bh.bm, this.bh.bw.b + (double) this.bh.bH, this.bh.bo, this.bs, this.bt);
                this.bh.bg = null;
            }

            this.bh = null;
        } else if (this.bh == oentity) {
            this.bh.bg = null;
            this.bh = null;
            this.c(oentity.bm, oentity.bw.b + (double) oentity.bH, oentity.bo, this.bs, this.bt);
        } else {
            if (this.bh != null) {
                this.bh.bg = null;
            }

            if (oentity.bg != null) {
                oentity.bg.bh = null;
            }

            this.bh = oentity;
            oentity.bg = this;
        }
    }

    public float j_() {
        return 0.1F;
    }

    public OVec3D aJ() {
        return null;
    }

    public void ad() {}

    public OItemStack[] y() {
        return null;
    }

    public boolean B_() {
        return this.c > 0 || this.j(0);
    }

    public boolean aY() {
        return this.j(1);
    }

    public void g(boolean flag) {
        this.a(1, flag);
    }

    public boolean aZ() {
        return this.j(3);
    }

    public void h(boolean flag) {
        this.a(3, flag);
    }

    public void i(boolean flag) {
        this.a(4, flag);
    }

    protected boolean j(int i) {
        return (this.bY.a(0) & 1 << i) != 0;
    }

    protected void a(int i, boolean flag) {
        byte b0 = this.bY.a(0);

        if (flag) {
            this.bY.b(0, Byte.valueOf((byte) (b0 | 1 << i)));
        } else {
            this.bY.b(0, Byte.valueOf((byte) (b0 & ~(1 << i))));
        }

    }

    public int ba() {
        return this.bY.b(1);
    }

    public void k(int i) {
        this.bY.b(1, Short.valueOf((short) i));
    }

    public void a(OEntityLightningBolt oentitylightningbolt) {
        // CanaryMod Damage Hook: Lightning
        if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LIGHTNING, null, entity, 5)) {
            return;
        }
        this.a(5);
        ++this.c;
        if (this.c == 0) {
            this.i(8);
        }

    }

    public void c(OEntityLiving oentityliving) {}

    protected boolean g(double d0, double d1, double d2) {
        int i = OMathHelper.b(d0);
        int j = OMathHelper.b(d1);
        int k = OMathHelper.b(d2);
        double d3 = d0 - (double) i;
        double d4 = d1 - (double) j;
        double d5 = d2 - (double) k;

        if (this.bi.e(i, j, k)) {
            boolean flag = !this.bi.e(i - 1, j, k);
            boolean flag1 = !this.bi.e(i + 1, j, k);
            boolean flag2 = !this.bi.e(i, j - 1, k);
            boolean flag3 = !this.bi.e(i, j + 1, k);
            boolean flag4 = !this.bi.e(i, j, k - 1);
            boolean flag5 = !this.bi.e(i, j, k + 1);
            byte b0 = -1;
            double d6 = 9999.0D;

            if (flag && d3 < d6) {
                d6 = d3;
                b0 = 0;
            }

            if (flag1 && 1.0D - d3 < d6) {
                d6 = 1.0D - d3;
                b0 = 1;
            }

            if (flag2 && d4 < d6) {
                d6 = d4;
                b0 = 2;
            }

            if (flag3 && 1.0D - d4 < d6) {
                d6 = 1.0D - d4;
                b0 = 3;
            }

            if (flag4 && d5 < d6) {
                d6 = d5;
                b0 = 4;
            }

            if (flag5 && 1.0D - d5 < d6) {
                d6 = 1.0D - d5;
                b0 = 5;
            }

            float f = this.bS.nextFloat() * 0.2F + 0.1F;

            if (b0 == 0) {
                this.bp = (double) (-f);
            }

            if (b0 == 1) {
                this.bp = (double) f;
            }

            if (b0 == 2) {
                this.bq = (double) (-f);
            }

            if (b0 == 3) {
                this.bq = (double) f;
            }

            if (b0 == 4) {
                this.br = (double) (-f);
            }

            if (b0 == 5) {
                this.br = (double) f;
            }

            return true;
        } else {
            return false;
        }
    }

    public void u() {
        this.bC = true;
        this.bK = 0.0F;
    }

    public String s() {
        String s = OEntityList.b(this);

        if (s == null) {
            s = "generic";
        }

        return OStatCollector.a("entity." + s + ".name");
    }

    public OEntity[] bb() {
        return null;
    }

    public boolean a_(OEntity oentity) {
        return this == oentity;
    }

    public float ar() {
        return 0.0F;
    }

    public boolean k_() {
        return true;
    }

}
