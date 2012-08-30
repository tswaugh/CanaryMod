import java.util.Iterator;
import java.util.List;
import java.util.Random;


public abstract class OEntity {

    private static int a = 0;
    public int k;
    public double l;
    public boolean m;
    public OEntity n;
    public OEntity o;
    public OWorld p;
    public double q;
    public double r;
    public double s;
    public double t;
    public double u;
    public double v;
    public double w;
    public double x;
    public double y;
    public float z;
    public float A;
    public float B;
    public float C;
    public final OAxisAlignedBB D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    protected boolean J;
    public boolean K;
    public boolean L;
    public float M;
    public float N;
    public float O;
    public float P;
    public float Q;
    public float R;
    private int b;
    public double S;
    public double T;
    public double U;
    public float V;
    public float W;
    public boolean X;
    public float Y;
    protected Random Z;
    public int aa;
    public int ab;
    protected int c; // CanaryMod: private -> protected
    protected boolean ac;
    public int ad;
    private boolean d;
    protected boolean ae;
    protected ODataWatcher af;
    private double e;
    private double f;
    public boolean ag;
    public int ah;
    public int ai;
    public int aj;
    public boolean ak;
    public boolean al;
    public OEnumEntitySize am;
    
    // CanaryMod Start
    BaseEntity entity = new BaseEntity(this);
    public static PluginLoader manager = etc.getLoader();
    // CanaryMod end

    public OEntity(OWorld oworld) {
        this.k = a++;
        this.l = 1.0D;
        this.m = false;
        this.D = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.E = false;
        this.H = false;
        this.I = false;
        this.K = true;
        this.L = false;
        this.M = 0.0F;
        this.N = 0.6F;
        this.O = 1.8F;
        this.P = 0.0F;
        this.Q = 0.0F;
        this.R = 0.0F;
        this.b = 1;
        this.V = 0.0F;
        this.W = 0.0F;
        this.X = false;
        this.Y = 0.0F;
        this.Z = new Random();
        this.aa = 0;
        this.ab = 1;
        this.c = 0;
        this.ac = false;
        this.ad = 0;
        this.d = true;
        this.ae = false;
        this.af = new ODataWatcher();
        this.ag = false;
        this.am = OEnumEntitySize.b;
        this.p = oworld;
        this.b(0.0D, 0.0D, 0.0D);
        this.af.a(0, Byte.valueOf((byte) 0));
        this.af.a(1, Short.valueOf((short) 300));
        this.a();
    }

    protected abstract void a();

    public ODataWatcher w() {
        return this.af;
    }

    public boolean equals(Object object) {
        return object instanceof OEntity ? ((OEntity) object).k == this.k : false;
    }

    public int hashCode() {
        return this.k;
    }

    public void y() {
        this.L = true;
    }

    protected void a(float f, float f1) {
        this.N = f;
        this.O = f1;
        float f2 = f % 2.0F;

        if ((double) f2 < 0.375D) {
            this.am = OEnumEntitySize.a;
        } else if ((double) f2 < 0.75D) {
            this.am = OEnumEntitySize.b;
        } else if ((double) f2 < 1.0D) {
            this.am = OEnumEntitySize.c;
        } else if ((double) f2 < 1.375D) {
            this.am = OEnumEntitySize.d;
        } else if ((double) f2 < 1.75D) {
            this.am = OEnumEntitySize.e;
        } else {
            this.am = OEnumEntitySize.f;
        }
    }

    protected void b(float f, float f1) {
        this.z = f % 360.0F;
        this.A = f1 % 360.0F;
    }

    public void b(double d0, double d1, double d2) {
        this.t = d0;
        this.u = d1;
        this.v = d2;
        float f = this.N / 2.0F;
        float f1 = this.O;

        this.D.b(d0 - (double) f, d1 - (double) this.M + (double) this.V, d2 - (double) f, d0 + (double) f, d1 - (double) this.M + (double) this.V + (double) f1, d2 + (double) f);
    }

    public void h_() {
        this.z();
    }

    public void z() {
        this.p.F.a("entityBaseTick");
        if (this.o != null && this.o.L) {
            this.o = null;
        }

        ++this.aa;
        this.P = this.Q;
        this.q = this.t;
        this.r = this.u;
        this.s = this.v;
        this.C = this.A;
        this.B = this.z;
        int i;

        if (this.ag() && !this.H()) {
            int j = OMathHelper.c(this.t);
            int k = OMathHelper.c(this.u - 0.20000000298023224D - (double) this.M);

            i = OMathHelper.c(this.v);
            int l = this.p.a(j, k, i);

            if (l > 0) {
                this.p.a("tilecrack_" + l, this.t + ((double) this.Z.nextFloat() - 0.5D) * (double) this.N, this.D.b + 0.1D, this.v + ((double) this.Z.nextFloat() - 0.5D) * (double) this.N, -this.w * 4.0D, 1.5D, -this.y * 4.0D);
            }
        }

        if (this.I()) {
            if (!this.ac && !this.d) {
                float f = OMathHelper.a(this.w * this.w * 0.20000000298023224D + this.x * this.x + this.y * this.y * 0.20000000298023224D) * 0.2F;

                if (f > 1.0F) {
                    f = 1.0F;
                }

                this.p.a(this, "random.splash", f, 1.0F + (this.Z.nextFloat() - this.Z.nextFloat()) * 0.4F);
                float f1 = (float) OMathHelper.c(this.D.b);

                float f2;
                float f3;

                for (i = 0; (float) i < 1.0F + this.N * 20.0F; ++i) {
                    f3 = (this.Z.nextFloat() * 2.0F - 1.0F) * this.N;
                    f2 = (this.Z.nextFloat() * 2.0F - 1.0F) * this.N;
                    this.p.a("bubble", this.t + (double) f3, (double) (f1 + 1.0F), this.v + (double) f2, this.w, this.x - (double) (this.Z.nextFloat() * 0.2F), this.y);
                }

                for (i = 0; (float) i < 1.0F + this.N * 20.0F; ++i) {
                    f3 = (this.Z.nextFloat() * 2.0F - 1.0F) * this.N;
                    f2 = (this.Z.nextFloat() * 2.0F - 1.0F) * this.N;
                    this.p.a("splash", this.t + (double) f3, (double) (f1 + 1.0F), this.v + (double) f2, this.w, this.x, this.y);
                }
            }

            this.R = 0.0F;
            this.ac = true;
            this.c = 0;
        } else {
            this.ac = false;
        }

        if (this.p.K) {
            this.c = 0;
        } else if (this.c > 0) {
            if (this.ae) {
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

        if (this.J()) {
            this.A();
            this.R *= 0.5F;
        }

        if (this.u < -64.0D) {
            this.C();
        }

        if (!this.p.K) {
            this.a(0, this.c > 0);
            this.a(2, this.o != null);
        }

        this.d = false;
        this.p.F.b();
    }

    protected void A() {
        if (!this.ae) {
            // CanaryMod Damage hook: Lava
            if (this instanceof OEntityLiving) {
                if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, entity, 4)) {
                    return;
                }
            }
            this.a(ODamageSource.c, 4);
            this.d(15);
        }

    }

    public void d(int i) {
        int j = i * 20;

        if (this.c < j) {
            this.c = j;
        }

    }

    public void B() {
        this.c = 0;
    }

    protected void C() {
        this.y();
    }

    public boolean c(double d0, double d1, double d2) {
        OAxisAlignedBB oaxisalignedbb = this.D.c(d0, d1, d2);
        List list = this.p.a(this, oaxisalignedbb);

        return !list.isEmpty() ? false : !this.p.d(oaxisalignedbb);
    }

    public void d(double d0, double d1, double d2) {
        if (this.X) {
            this.D.d(d0, d1, d2);
            this.t = (this.D.a + this.D.d) / 2.0D;
            this.u = this.D.b + (double) this.M - (double) this.V;
            this.v = (this.D.c + this.D.f) / 2.0D;
        } else {
            this.p.F.a("move");
            this.V *= 0.4F;
            double d3 = this.t;
            double d4 = this.v;

            if (this.J) {
                this.J = false;
                d0 *= 0.25D;
                d1 *= 0.05000000074505806D;
                d2 *= 0.25D;
                this.w = 0.0D;
                this.x = 0.0D;
                this.y = 0.0D;
            }

            double d5 = d0;
            double d6 = d1;
            double d7 = d2;
            OAxisAlignedBB oaxisalignedbb = this.D.c();
            boolean flag = this.E && this.af() && this instanceof OEntityPlayer;

            if (flag) {
                double d8;

                for (d8 = 0.05D; d0 != 0.0D && this.p.a(this, this.D.c(d0, -1.0D, 0.0D)).isEmpty(); d5 = d0) {
                    if (d0 < d8 && d0 >= -d8) {
                        d0 = 0.0D;
                    } else if (d0 > 0.0D) {
                        d0 -= d8;
                    } else {
                        d0 += d8;
                    }
                }

                for (; d2 != 0.0D && this.p.a(this, this.D.c(0.0D, -1.0D, d2)).isEmpty(); d7 = d2) {
                    if (d2 < d8 && d2 >= -d8) {
                        d2 = 0.0D;
                    } else if (d2 > 0.0D) {
                        d2 -= d8;
                    } else {
                        d2 += d8;
                    }
                }

                while (d0 != 0.0D && d2 != 0.0D && this.p.a(this, this.D.c(d0, -1.0D, d2)).isEmpty()) {
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

            List list = this.p.a(this, this.D.a(d0, d1, d2));

            OAxisAlignedBB oaxisalignedbb1;

            for (Iterator iterator = list.iterator(); iterator.hasNext(); d1 = oaxisalignedbb1.b(this.D, d1)) {
                oaxisalignedbb1 = (OAxisAlignedBB) iterator.next();
            }

            this.D.d(0.0D, d1, 0.0D);
            if (!this.K && d6 != d1) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            boolean flag1 = this.E || d6 != d1 && d6 < 0.0D;

            OAxisAlignedBB oaxisalignedbb2;
            Iterator iterator1;

            for (iterator1 = list.iterator(); iterator1.hasNext(); d0 = oaxisalignedbb2.a(this.D, d0)) {
                oaxisalignedbb2 = (OAxisAlignedBB) iterator1.next();
            }

            this.D.d(d0, 0.0D, 0.0D);
            if (!this.K && d5 != d0) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            for (iterator1 = list.iterator(); iterator1.hasNext(); d2 = oaxisalignedbb2.c(this.D, d2)) {
                oaxisalignedbb2 = (OAxisAlignedBB) iterator1.next();
            }

            this.D.d(0.0D, 0.0D, d2);
            if (!this.K && d7 != d2) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            double d9;
            double d10;

            if (this.W > 0.0F && flag1 && (flag || this.V < 0.05F) && (d5 != d0 || d7 != d2)) {
                d9 = d0;
                d10 = d1;
                double d11 = d2;

                d0 = d5;
                d1 = (double) this.W;
                d2 = d7;
                OAxisAlignedBB oaxisalignedbb3 = this.D.c();

                this.D.c(oaxisalignedbb);
                list = this.p.a(this, this.D.a(d5, d1, d7));

                Iterator iterator2;
                OAxisAlignedBB oaxisalignedbb4;

                for (iterator2 = list.iterator(); iterator2.hasNext(); d1 = oaxisalignedbb4.b(this.D, d1)) {
                    oaxisalignedbb4 = (OAxisAlignedBB) iterator2.next();
                }

                this.D.d(0.0D, d1, 0.0D);
                if (!this.K && d6 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (iterator2 = list.iterator(); iterator2.hasNext(); d0 = oaxisalignedbb4.a(this.D, d0)) {
                    oaxisalignedbb4 = (OAxisAlignedBB) iterator2.next();
                }

                this.D.d(d0, 0.0D, 0.0D);
                if (!this.K && d5 != d0) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (iterator2 = list.iterator(); iterator2.hasNext(); d2 = oaxisalignedbb4.c(this.D, d2)) {
                    oaxisalignedbb4 = (OAxisAlignedBB) iterator2.next();
                }

                this.D.d(0.0D, 0.0D, d2);
                if (!this.K && d7 != d2) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                if (!this.K && d6 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                } else {
                    d1 = (double) (-this.W);

                    for (iterator2 = list.iterator(); iterator2.hasNext(); d1 = oaxisalignedbb4.b(this.D, d1)) {
                        oaxisalignedbb4 = (OAxisAlignedBB) iterator2.next();
                    }

                    this.D.d(0.0D, d1, 0.0D);
                }

                if (d9 * d9 + d11 * d11 >= d0 * d0 + d2 * d2) {
                    d0 = d9;
                    d1 = d10;
                    d2 = d11;
                    this.D.c(oaxisalignedbb3);
                } else {
                    double d12 = this.D.b - (double) ((int) this.D.b);

                    if (d12 > 0.0D) {
                        this.V = (float) ((double) this.V + d12 + 0.01D);
                    }
                }
            }

            this.p.F.b();
            this.p.F.a("rest");
            this.t = (this.D.a + this.D.d) / 2.0D;
            this.u = this.D.b + (double) this.M - (double) this.V;
            this.v = (this.D.c + this.D.f) / 2.0D;
            this.F = d5 != d0 || d7 != d2;
            this.G = d6 != d1;
            this.E = d6 != d1 && d6 < 0.0D;
            this.H = this.F || this.G;
            this.a(d1, this.E);
            if (d5 != d0) {
                this.w = 0.0D;
            }

            if (d6 != d1) {
                this.x = 0.0D;
            }

            if (d7 != d2) {
                this.y = 0.0D;
            }

            d9 = this.t - d3;
            d10 = this.v - d4;
            if (this.e_() && !flag && this.o == null) {
                this.Q = (float) ((double) this.Q + (double) OMathHelper.a(d9 * d9 + d10 * d10) * 0.6D);
                int i = OMathHelper.c(this.t);
                int j = OMathHelper.c(this.u - 0.20000000298023224D - (double) this.M);
                int k = OMathHelper.c(this.v);
                int l = this.p.a(i, j, k);

                if (l == 0 && this.p.a(i, j - 1, k) == OBlock.aZ.ca) {
                    l = this.p.a(i, j - 1, k);
                }

                if (this.Q > (float) this.b && l > 0) {
                    this.b = (int) this.Q + 1;
                    this.a(i, j, k, l);
                    OBlock.m[l].b(this.p, i, j, k, this);
                }
            }

            this.D();
            boolean flag2 = this.G();

            if (this.p.e(this.D.e(0.001D, 0.001D, 0.001D))) {
                this.e(1);
                if (!flag2) {
                    ++this.c;
                    if (this.c == 0) {
                        this.d(8);
                    }
                }
            } else if (this.c <= 0) {
                this.c = -this.ab;
            }

            if (flag2 && this.c > 0) {
                this.p.a(this, "random.fizz", 0.7F, 1.6F + (this.Z.nextFloat() - this.Z.nextFloat()) * 0.4F);
                this.c = -this.ab;
            }

            this.p.F.b();
        }
    }

    protected void D() {
        int i = OMathHelper.c(this.D.a + 0.001D);
        int j = OMathHelper.c(this.D.b + 0.001D);
        int k = OMathHelper.c(this.D.c + 0.001D);
        int l = OMathHelper.c(this.D.d - 0.001D);
        int i1 = OMathHelper.c(this.D.e - 0.001D);
        int j1 = OMathHelper.c(this.D.f - 0.001D);

        if (this.p.c(i, j, k, l, i1, j1)) {
            for (int k1 = i; k1 <= l; ++k1) {
                for (int l1 = j; l1 <= i1; ++l1) {
                    for (int i2 = k; i2 <= j1; ++i2) {
                        int j2 = this.p.a(k1, l1, i2);

                        if (j2 > 0) {
                            OBlock.m[j2].a(this.p, k1, l1, i2, this);
                        }
                    }
                }
            }
        }
    }

    protected void a(int i, int j, int k, int l) {
        OStepSound ostepsound = OBlock.m[l].cn;

        if (this.p.a(i, j + 1, k) == OBlock.aS.ca) {
            ostepsound = OBlock.aS.cn;
            this.p.a(this, ostepsound.d(), ostepsound.b() * 0.15F, ostepsound.c());
        } else if (!OBlock.m[l].cp.d()) {
            this.p.a(this, ostepsound.d(), ostepsound.b() * 0.15F, ostepsound.c());
        }

    }

    protected boolean e_() {
        return true;
    }

    protected void a(double d0, boolean flag) {
        if (flag) {
            if (this.R > 0.0F) {
                if (this instanceof OEntityLiving) {
                    int i = OMathHelper.c(this.t);
                    int j = OMathHelper.c(this.u - 0.20000000298023224D - (double) this.M);
                    int k = OMathHelper.c(this.v);
                    int l = this.p.a(i, j, k);

                    if (l == 0 && this.p.a(i, j - 1, k) == OBlock.aZ.ca) {
                        l = this.p.a(i, j - 1, k);
                    }

                    if (l > 0) {
                        OBlock.m[l].a(this.p, i, j, k, this, this.R);
                    }
                }

                this.a(this.R);
                this.R = 0.0F;
            }
        } else if (d0 < 0.0D) {
            this.R = (float) ((double) this.R - d0);
        }

    }

    public OAxisAlignedBB E() {
        return null;
    }

    protected void e(int i) {
        if (!this.ae) {
            // CanaryMod Damage Hook: Fire
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE, null, entity, i)) {
                this.a(ODamageSource.a, i);
            }
        }
    }

    public final boolean F() {
        return this.ae;
    }

    protected void a(float f) {
        if (this.n != null) {
            this.n.a(f);
        }

    }

    public boolean G() {
        return this.ac || this.p.B(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
    }

    public boolean H() {
        return this.ac;
    }

    public boolean I() {
        return this.p.a(this.D.b(0.0D, -0.4000000059604645D, 0.0D).e(0.001D, 0.001D, 0.001D), OMaterial.g, this);
    }

    public boolean a(OMaterial omaterial) {
        double d0 = this.u + (double) this.e();
        int i = OMathHelper.c(this.t);
        int j = OMathHelper.d((float) OMathHelper.c(d0));
        int k = OMathHelper.c(this.v);
        int l = this.p.a(i, j, k);

        if (l != 0 && OBlock.m[l].cp == omaterial) {
            float f = OBlockFluid.d(this.p.g(i, j, k)) - 0.11111111F;
            float f1 = (float) (j + 1) - f;

            return d0 < (double) f1;
        } else {
            return false;
        }
    }

    public float e() {
        return 0.0F;
    }

    public boolean J() {
        return this.p.a(this.D.b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), OMaterial.h);
    }

    public void a(float f, float f1, float f2) {
        float f3 = f * f + f1 * f1;

        if (f3 >= 1.0E-4F) {
            f3 = OMathHelper.c(f3);
            if (f3 < 1.0F) {
                f3 = 1.0F;
            }

            f3 = f2 / f3;
            f *= f3;
            f1 *= f3;
            float f4 = OMathHelper.a(this.z * 3.1415927F / 180.0F);
            float f5 = OMathHelper.b(this.z * 3.1415927F / 180.0F);

            this.w += (double) (f * f5 - f1 * f4);
            this.y += (double) (f1 * f5 + f * f4);
        }
    }

    public float c(float f) {
        int i = OMathHelper.c(this.t);
        int j = OMathHelper.c(this.v);

        if (this.p.e(i, 0, j)) {
            double d0 = (this.D.e - this.D.b) * 0.66D;
            int k = OMathHelper.c(this.u - (double) this.M + d0);

            return this.p.o(i, k, j);
        } else {
            return 0.0F;
        }
    }

    public void a(OWorld oworld) {
        this.p = oworld;
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
        this.q = this.t = d0;
        this.r = this.u = d1;
        this.s = this.v = d2;
        this.B = this.z = f;
        this.C = this.A = f1;
        this.V = 0.0F;
        double d3 = (double) (this.B - f);

        if (d3 < -180.0D) {
            this.B += 360.0F;
        }

        if (d3 >= 180.0D) {
            this.B -= 360.0F;
        }

        this.b(this.t, this.u, this.v);
        this.b(f, f1);
    }

    public void b(double d0, double d1, double d2, float f, float f1) {
        this.S = this.q = this.t = d0;
        this.T = this.r = this.u = d1 + (double) this.M;
        this.U = this.s = this.v = d2;
        this.z = f;
        this.A = f1;
        this.b(this.t, this.u, this.v);
    }

    public float d(OEntity oentity) {
        float f = (float) (this.t - oentity.t);
        float f1 = (float) (this.u - oentity.u);
        float f2 = (float) (this.v - oentity.v);

        return OMathHelper.c(f * f + f1 * f1 + f2 * f2);
    }

    public double e(double d0, double d1, double d2) {
        double d3 = this.t - d0;
        double d4 = this.u - d1;
        double d5 = this.v - d2;

        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public double f(double d0, double d1, double d2) {
        double d3 = this.t - d0;
        double d4 = this.u - d1;
        double d5 = this.v - d2;

        return (double) OMathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);
    }

    public double e(OEntity oentity) {
        double d0 = this.t - oentity.t;
        double d1 = this.u - oentity.u;
        double d2 = this.v - oentity.v;

        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public void b_(OEntityPlayer oentityplayer) {}

    public void f(OEntity oentity) {
        if (oentity.n != this && oentity.o != this) {
            double d0 = oentity.t - this.t;
            double d1 = oentity.v - this.v;
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
                d0 *= (double) (1.0F - this.Y);
                d1 *= (double) (1.0F - this.Y);
                this.g(-d0, 0.0D, -d1);
                oentity.g(d0, 0.0D, d1);
            }

        }
    }

    public void g(double d0, double d1, double d2) {
        this.w += d0;
        this.x += d1;
        this.y += d2;
        this.al = true;
    }

    protected void K() {
        this.I = true;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        this.K();
        return false;
    }

    public boolean L() {
        return false;
    }

    public boolean M() {
        return false;
    }

    public void c(OEntity oentity, int i) {}

    public boolean c(ONBTTagCompound onbttagcompound) {
        String s = this.Q();

        if (!this.L && s != null) {
            onbttagcompound.a("id", s);
            this.d(onbttagcompound);
            return true;
        } else {
            return false;
        }
    }

    public void d(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Pos", (ONBTBase) this.a(new double[] { this.t, this.u + (double) this.V, this.v}));
        onbttagcompound.a("Motion", (ONBTBase) this.a(new double[] { this.w, this.x, this.y}));
        onbttagcompound.a("Rotation", (ONBTBase) this.a(new float[] { this.z, this.A}));
        onbttagcompound.a("FallDistance", this.R);
        onbttagcompound.a("Fire", (short) this.c);
        onbttagcompound.a("Air", (short) this.ai());
        onbttagcompound.a("OnGround", this.E);
        this.b(onbttagcompound);
    }

    public void e(ONBTTagCompound onbttagcompound) {
        ONBTTagList onbttaglist = onbttagcompound.m("Pos");
        ONBTTagList onbttaglist1 = onbttagcompound.m("Motion");
        ONBTTagList onbttaglist2 = onbttagcompound.m("Rotation");

        this.w = ((ONBTTagDouble) onbttaglist1.b(0)).a;
        this.x = ((ONBTTagDouble) onbttaglist1.b(1)).a;
        this.y = ((ONBTTagDouble) onbttaglist1.b(2)).a;
        if (Math.abs(this.w) > 10.0D) {
            this.w = 0.0D;
        }

        if (Math.abs(this.x) > 10.0D) {
            this.x = 0.0D;
        }

        if (Math.abs(this.y) > 10.0D) {
            this.y = 0.0D;
        }

        this.q = this.S = this.t = ((ONBTTagDouble) onbttaglist.b(0)).a;
        this.r = this.T = this.u = ((ONBTTagDouble) onbttaglist.b(1)).a;
        this.s = this.U = this.v = ((ONBTTagDouble) onbttaglist.b(2)).a;
        this.B = this.z = ((ONBTTagFloat) onbttaglist2.b(0)).a;
        this.C = this.A = ((ONBTTagFloat) onbttaglist2.b(1)).a;
        this.R = onbttagcompound.g("FallDistance");
        this.c = onbttagcompound.d("Fire");
        this.g(onbttagcompound.d("Air"));
        this.E = onbttagcompound.n("OnGround");
        this.b(this.t, this.u, this.v);
        this.b(this.z, this.A);
        this.a(onbttagcompound);
    }

    protected final String Q() {
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
        OEntityItem oentityitem = new OEntityItem(this.p, this.t, this.u + (double) f, this.v, oitemstack);

        oentityitem.c = 10;
        this.p.d((OEntity) oentityitem);
        return oentityitem;
    }

    public boolean S() {
        return !this.L;
    }

    public boolean T() {
        for (int i = 0; i < 8; ++i) {
            float f = ((float) ((i >> 0) % 2) - 0.5F) * this.N * 0.8F;
            float f1 = ((float) ((i >> 1) % 2) - 0.5F) * 0.1F;
            float f2 = ((float) ((i >> 2) % 2) - 0.5F) * this.N * 0.8F;
            int j = OMathHelper.c(this.t + (double) f);
            int k = OMathHelper.c(this.u + (double) this.e() + (double) f1);
            int l = OMathHelper.c(this.v + (double) f2);

            if (this.p.s(j, k, l)) {
                return true;
            }
        }

        return false;
    }

    public boolean c(OEntityPlayer oentityplayer) {
        return false;
    }

    public OAxisAlignedBB g(OEntity oentity) {
        return null;
    }

    public void U() {
        if (this.o.L) {
            this.o = null;
        } else {
            this.w = 0.0D;
            this.x = 0.0D;
            this.y = 0.0D;
            this.h_();
            if (this.o != null) {
                this.o.V();
                this.f += (double) (this.o.z - this.o.B);

                for (this.e += (double) (this.o.A - this.o.C); this.f >= 180.0D; this.f -= 360.0D) {
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
                this.z = (float) ((double) this.z + d0);
                this.A = (float) ((double) this.A + d1);
            }
        }
    }

    public void V() {
        if (!(this.n instanceof OEntityPlayer) || !((OEntityPlayer) this.n).bF()) {
            this.n.S = this.n.t;
            this.n.T = this.n.u;
            this.n.U = this.n.v;
        }

        this.n.b(this.t, this.u + this.X() + this.n.W(), this.v);
    }

    public double W() {
        return (double) this.M;
    }

    public double X() {
        return (double) this.O * 0.75D;
    }

    public void a(OEntity oentity) {
        this.e = 0.0D;
        this.f = 0.0D;
        if (oentity == null) {
            if (this.o != null) {
                this.b(this.o.t, this.o.D.b + (double) this.o.O, this.o.v, this.z, this.A);
                this.o.n = null;
            }

            this.o = null;
        } else if (this.o == oentity) {
            this.h(oentity);
            this.o.n = null;
            this.o = null;
        } else {
            if (this.o != null) {
                this.o.n = null;
            }

            if (oentity.n != null) {
                oentity.n.o = null;
            }

            this.o = oentity;
            oentity.n = this;
        }
    }

    public void h(OEntity oentity) {
        double d0 = oentity.t;
        double d1 = oentity.D.b + (double) oentity.O;
        double d2 = oentity.v;

        for (double d3 = -1.5D; d3 < 2.0D; ++d3) {
            for (double d4 = -1.5D; d4 < 2.0D; ++d4) {
                if (d3 != 0.0D || d4 != 0.0D) {
                    int i = (int) (this.t + d3);
                    int j = (int) (this.v + d4);
                    OAxisAlignedBB oaxisalignedbb = this.D.c(d3, 1.0D, d4);

                    if (this.p.a(oaxisalignedbb).isEmpty()) {
                        if (this.p.t(i, (int) this.u, j)) {
                            this.b(this.t + d3, this.u + 1.0D, this.v + d4, this.z, this.A);
                            return;
                        }

                        if (this.p.t(i, (int) this.u - 1, j) || this.p.f(i, (int) this.u - 1, j) == OMaterial.g) {
                            d0 = this.t + d3;
                            d1 = this.u + 1.0D;
                            d2 = this.v + d4;
                        }
                    }
                }
            }
        }

        this.b(d0, d1, d2, this.z, this.A);
    }

    public float Y() {
        return 0.1F;
    }

    public OVec3 Z() {
        return null;
    }

    public void aa() {}

    public OItemStack[] c() {
        return null;
    }

    public boolean ad() {
        return this.c > 0 || this.f(0);
    }

    public boolean af() {
        return this.f(1);
    }

    public void a(boolean flag) {
        this.a(1, flag);
    }

    public boolean ag() {
        return this.f(3);
    }

    public void b(boolean flag) {
        this.a(3, flag);
    }

    public void c(boolean flag) {
        this.a(4, flag);
    }

    protected boolean f(int i) {
        return (this.af.a(0) & 1 << i) != 0;
    }

    protected void a(int i, boolean flag) {
        byte b0 = this.af.a(0);

        if (flag) {
            this.af.b(0, Byte.valueOf((byte) (b0 | 1 << i)));
        } else {
            this.af.b(0, Byte.valueOf((byte) (b0 & ~(1 << i))));
        }

    }

    public int ai() {
        return this.af.b(1);
    }

    public void g(int i) {
        this.af.b(1, Short.valueOf((short) i));
    }

    public void a(OEntityLightningBolt oentitylightningbolt) {
        // CanaryMod Damage Hook: Lightning
        if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LIGHTNING, null, entity, 5)) {
            return;
        }
        this.e(5);
        ++this.c;
        if (this.c == 0) {
            this.d(8);
        }

    }

    public void a(OEntityLiving oentityliving) {}

    protected boolean i(double d0, double d1, double d2) {
        int i = OMathHelper.c(d0);
        int j = OMathHelper.c(d1);
        int k = OMathHelper.c(d2);
        double d3 = d0 - (double) i;
        double d4 = d1 - (double) j;
        double d5 = d2 - (double) k;

        if (this.p.s(i, j, k)) {
            boolean flag = !this.p.s(i - 1, j, k);
            boolean flag1 = !this.p.s(i + 1, j, k);
            boolean flag2 = !this.p.s(i, j - 1, k);
            boolean flag3 = !this.p.s(i, j + 1, k);
            boolean flag4 = !this.p.s(i, j, k - 1);
            boolean flag5 = !this.p.s(i, j, k + 1);
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

            float f = this.Z.nextFloat() * 0.2F + 0.1F;

            if (b0 == 0) {
                this.w = (double) (-f);
            }

            if (b0 == 1) {
                this.w = (double) f;
            }

            if (b0 == 2) {
                this.x = (double) (-f);
            }

            if (b0 == 3) {
                this.x = (double) f;
            }

            if (b0 == 4) {
                this.y = (double) (-f);
            }

            if (b0 == 5) {
                this.y = (double) f;
            }

            return true;
        } else {
            return false;
        }
    }

    public void aj() {
        this.J = true;
        this.R = 0.0F;
    }

    public String ak() {
        String s = OEntityList.b(this);

        if (s == null) {
            s = "generic";
        }

        return OStatCollector.a("entity." + s + ".name");
    }

    public OEntity[] al() {
        return null;
    }

    public boolean i(OEntity oentity) {
        return this == oentity;
    }

    public float am() {
        return 0.0F;
    }

    public boolean an() {
        return true;
    }

    public String toString() {
        return String.format("%s[\'%s\'/%d, l=\'%s\', x=%.2f, y=%.2f, z=%.2f]", new Object[] { this.getClass().getSimpleName(), this.ak(), Integer.valueOf(this.k), this.p == null ? "~NULL~" : this.p.H().j(), Double.valueOf(this.t), Double.valueOf(this.u), Double.valueOf(this.v)});
    }
}
