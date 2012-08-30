import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public abstract class OEntityLiving extends OEntity {

    public int an = 20;
    public float ao;
    public float ap;
    public float aq = 0.0F;
    public float ar = 0.0F;
    public float as = 0.0F;
    public float at = 0.0F;
    protected float au;
    protected float av;
    protected float aw;
    protected float ax;
    protected boolean ay = true;
    protected String az = "/mob/char.png";
    protected boolean aA = true;
    protected float aB = 0.0F;
    protected String aC = null;
    protected float aD = 1.0F;
    protected int aE = 0;
    protected float aF = 0.0F;
    public float aG = 0.1F;
    public float aH = 0.02F;
    public float aI;
    public float aJ;
    protected int aK = this.aM();
    public int aL;
    protected int aM;
    private int a;
    public int aN;
    public int aO;
    public float aP = 0.0F;
    public int aQ = 0;
    public int aR = 0;
    public float aS;
    public float aT;
    protected boolean aU = false;
    protected int aV;
    public int aW = -1;
    public float aX = (float) (Math.random() * 0.8999999761581421D + 0.10000000149011612D);
    public float aY;
    public float aZ;
    public float ba;
    protected OEntityPlayer bb = null;
    protected int bc = 0;
    private OEntityLiving b = null;
    private int c = 0;
    private OEntityLiving d = null;
    public int bd = 0;
    public int be = 0;
    protected HashMap bf = new HashMap();
    private boolean e = true;
    private int f;
    private OEntityLookHelper g;
    private OEntityMoveHelper h;
    private OEntityJumpHelper i;
    private OEntityBodyHelper j;
    private OPathNavigate by;
    protected final OEntityAITasks bg;
    protected final OEntityAITasks bh;
    private OEntityLiving bz;
    private OEntitySenses bA;
    private float bB;
    private OChunkCoordinates bC = new OChunkCoordinates(0, 0, 0);
    private float bD = -1.0F;
    protected int bi;
    protected double bj;
    protected double bk;
    protected double bl;
    protected double bm;
    protected double bn;
    float bo = 0.0F;
    protected int bp = 0;
    protected int bq = 0;
    protected float br;
    protected float bs;
    protected float bt;
    protected boolean bu = false;
    protected float bv = 0.0F;
    protected float bw = 0.7F;
    private int bE = 0;
    private OEntity bF;
    protected int bx = 0;

    // CanaryMod Start
    LivingEntity entity = new LivingEntity(this);
    protected MobSpawner spawner = null;
    // CanaryMod end
    
    public OEntityLiving(OWorld oworld) {
        super(oworld);
        this.m = true;
        this.bg = new OEntityAITasks(oworld != null && oworld.F != null ? oworld.F : null);
        this.bh = new OEntityAITasks(oworld != null && oworld.F != null ? oworld.F : null);
        this.g = new OEntityLookHelper(this);
        this.h = new OEntityMoveHelper(this);
        this.i = new OEntityJumpHelper(this);
        this.j = new OEntityBodyHelper(this);
        this.by = new OPathNavigate(this, oworld, 16.0F);
        this.bA = new OEntitySenses(this);
        this.ap = (float) (Math.random() + 1.0D) * 0.01F;
        this.b(this.t, this.u, this.v);
        this.ao = (float) Math.random() * 12398.0F;
        this.z = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.as = this.z;
        this.W = 0.5F;
    }

    public OEntityLookHelper ap() {
        return this.g;
    }

    public OEntityMoveHelper aq() {
        return this.h;
    }

    public OEntityJumpHelper ar() {
        return this.i;
    }

    public OPathNavigate as() {
        return this.by;
    }

    public OEntitySenses at() {
        return this.bA;
    }

    public Random au() {
        return this.Z;
    }

    public OEntityLiving av() {
        return this.b;
    }

    public OEntityLiving aw() {
        return this.d;
    }

    public void j(OEntity oentity) {
        if (oentity instanceof OEntityLiving) {
            this.d = (OEntityLiving) oentity;
        }

    }

    public int ax() {
        return this.bq;
    }

    public float am() {
        return this.as;
    }

    public float ay() {
        return this.bB;
    }

    public void e(float f) {
        this.bB = f;
        this.f(f);
    }

    public boolean k(OEntity oentity) {
        this.j(oentity);
        return false;
    }

    public OEntityLiving az() {
        return this.bz;
    }

    public void b(OEntityLiving oentityliving) {
        this.bz = oentityliving;
    }

    public boolean a(Class oclass) {
        return OEntityCreeper.class != oclass && OEntityGhast.class != oclass;
    }

    public void aA() {}

    public boolean aB() {
        return this.d(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
    }

    public boolean d(int i, int j, int k) {
        return this.bD == -1.0F ? true : this.bC.e(i, j, k) < this.bD * this.bD;
    }

    public void b(int i, int j, int k, int l) {
        this.bC.b(i, j, k);
        this.bD = (float) l;
    }

    public OChunkCoordinates aC() {
        return this.bC;
    }

    public float aD() {
        return this.bD;
    }

    public void aE() {
        this.bD = -1.0F;
    }

    public boolean aF() {
        return this.bD != -1.0F;
    }

    public void c(OEntityLiving oentityliving) {
        this.b = oentityliving;
        this.c = this.b != null ? 60 : 0;
    }

    protected void a() {
        this.af.a(8, Integer.valueOf(this.f));
    }

    public boolean l(OEntity oentity) {
        return this.p.a(OVec3.a().a(this.t, this.u + (double) this.e(), this.v), OVec3.a().a(oentity.t, oentity.u + (double) oentity.e(), oentity.v)) == null;
    }

    public boolean L() {
        return !this.L;
    }

    public boolean M() {
        return !this.L;
    }

    public float e() {
        return this.O * 0.85F;
    }

    public int aG() {
        return 80;
    }

    public void aH() {
        String s = this.aQ();

        if (s != null) {
            this.p.a(this, s, this.aP(), this.i());
        }

    }

    public void z() {
        this.aI = this.aJ;
        super.z();
        this.p.F.a("mobBaseTick");
        if (this.S() && this.Z.nextInt(1000) < this.a++) {
            this.a = -this.aG();
            this.aH();
        }

        if (this.S() && this.T()) {
            // CanaryMod Damage hook: Suffocation
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.SUFFOCATION, null, this.entity, 1)) {
                this.a(ODamageSource.d, 1);
            }
        }

        if (this.F() || this.p.K) {
            this.B();
        }

        if (this.S() && this.a(OMaterial.g) && !this.aU() && !this.bf.containsKey(Integer.valueOf(OPotion.o.H))) {
            this.g(this.h(this.ai()));
            if (this.ai() == -20) {
                this.g(0);
                // CanaryMod Damage hook: Drowning
                if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.WATER, null, entity, 2)) {
                    for (int i = 0; i < 8; ++i) {
                    float f = this.Z.nextFloat() - this.Z.nextFloat();
                    float f1 = this.Z.nextFloat() - this.Z.nextFloat();
                    float f2 = this.Z.nextFloat() - this.Z.nextFloat();

                    this.p.a("bubble", this.t + (double) f, this.u + (double) f1, this.v + (double) f2, this.w, this.x, this.y);
                    }

                    this.a(ODamageSource.e, 2);
                }
            }

            this.B();
        } else {
            this.g(300);
        }

        this.aS = this.aT;
        if (this.aR > 0) {
            --this.aR;
        }

        if (this.aN > 0) {
            --this.aN;
        }

        if (this.ad > 0) {
            --this.ad;
        }

        if (this.aK <= 0) {
            this.aI();
        }

        if (this.bc > 0) {
            --this.bc;
        } else {
            this.bb = null;
        }

        if (this.d != null && !this.d.S()) {
            this.d = null;
        }

        if (this.b != null) {
            if (!this.b.S()) {
                this.c((OEntityLiving) null);
            } else if (this.c > 0) {
                --this.c;
            } else {
                this.c((OEntityLiving) null);
            }
        }

        this.bo();
        this.ax = this.aw;
        this.ar = this.aq;
        this.at = this.as;
        this.B = this.z;
        this.C = this.A;
        this.p.F.b();
    }

    protected void aI() {
        ++this.aQ;
        if (this.aQ == 20) {
            int i;

            if (!this.p.K && (this.bc > 0 || this.aJ()) && !this.g_()) {
                i = this.a(this.bb);

                while (i > 0) {
                    int j = OEntityXPOrb.a(i);

                    i -= j;
                    this.p.d((OEntity) (new OEntityXPOrb(this.p, this.t, this.u, this.v, j)));
                }
            }

            this.y();

            for (i = 0; i < 20; ++i) {
                double d0 = this.Z.nextGaussian() * 0.02D;
                double d1 = this.Z.nextGaussian() * 0.02D;
                double d2 = this.Z.nextGaussian() * 0.02D;

                this.p.a("explode", this.t + (double) (this.Z.nextFloat() * this.N * 2.0F) - (double) this.N, this.u + (double) (this.Z.nextFloat() * this.O), this.v + (double) (this.Z.nextFloat() * this.N * 2.0F) - (double) this.N, d0, d1, d2);
            }
        }

    }

    protected int h(int i) {
        return i - 1;
    }

    protected int a(OEntityPlayer oentityplayer) {
        return this.aV;
    }

    protected boolean aJ() {
        return false;
    }

    public void aK() {
        for (int i = 0; i < 20; ++i) {
            double d0 = this.Z.nextGaussian() * 0.02D;
            double d1 = this.Z.nextGaussian() * 0.02D;
            double d2 = this.Z.nextGaussian() * 0.02D;
            double d3 = 10.0D;

            this.p.a("explode", this.t + (double) (this.Z.nextFloat() * this.N * 2.0F) - (double) this.N - d0 * d3, this.u + (double) (this.Z.nextFloat() * this.O) - d1 * d3, this.v + (double) (this.Z.nextFloat() * this.N * 2.0F) - (double) this.N - d2 * d3, d0, d1, d2);
        }

    }

    public void U() {
        super.U();
        this.au = this.av;
        this.av = 0.0F;
        this.R = 0.0F;
    }

    public void h_() {
        super.h_();
        if (this.bd > 0) {
            if (this.be <= 0) {
                this.be = 60;
            }

            --this.be;
            if (this.be <= 0) {
                --this.bd;
            }
        }

        this.d();
        double d0 = this.t - this.q;
        double d1 = this.v - this.s;
        float f = (float) (d0 * d0 + d1 * d1);
        float f1 = this.aq;
        float f2 = 0.0F;

        this.au = this.av;
        float f3 = 0.0F;

        if (f > 0.0025000002F) {
            f3 = 1.0F;
            f2 = (float) Math.sqrt((double) f) * 3.0F;
            f1 = (float) Math.atan2(d1, d0) * 180.0F / 3.1415927F - 90.0F;
        }

        if (this.aJ > 0.0F) {
            f1 = this.z;
        }

        if (!this.E) {
            f3 = 0.0F;
        }

        this.av += (f3 - this.av) * 0.3F;
        this.p.F.a("headTurn");
        if (this.aV()) {
            this.j.a();
        } else {
            float f4 = OMathHelper.g(f1 - this.aq);

            this.aq += f4 * 0.3F;
            float f5 = OMathHelper.g(this.z - this.aq);
            boolean flag = f5 < -90.0F || f5 >= 90.0F;

            if (f5 < -75.0F) {
                f5 = -75.0F;
            }

            if (f5 >= 75.0F) {
                f5 = 75.0F;
            }

            this.aq = this.z - f5;
            if (f5 * f5 > 2500.0F) {
                this.aq += f5 * 0.2F;
            }

            if (flag) {
                f2 *= -1.0F;
            }
        }

        this.p.F.b();
        this.p.F.a("rangeChecks");

        while (this.z - this.B < -180.0F) {
            this.B -= 360.0F;
        }

        while (this.z - this.B >= 180.0F) {
            this.B += 360.0F;
        }

        while (this.aq - this.ar < -180.0F) {
            this.ar -= 360.0F;
        }

        while (this.aq - this.ar >= 180.0F) {
            this.ar += 360.0F;
        }

        while (this.A - this.C < -180.0F) {
            this.C -= 360.0F;
        }

        while (this.A - this.C >= 180.0F) {
            this.C += 360.0F;
        }

        while (this.as - this.at < -180.0F) {
            this.at -= 360.0F;
        }

        while (this.as - this.at >= 180.0F) {
            this.at += 360.0F;
        }

        this.p.F.b();
        this.aw += f2;
    }

    public void i(int i) {
        if (this.aK > 0) {
            this.aK += i;
            if (this.aK > this.aM()) {
                this.aK = this.aM();
            }

            this.ad = this.an / 2;
        }
    }

    public abstract int aM();

    public int aN() {
        return this.aK;
    }

    public void j(int i) {
        this.aK = i;
        if (i > this.aM()) {
            i = this.aM();
        }

    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.p.K) {
            return false;
        } else {
            this.bq = 0;
            if (this.aK <= 0) {
                return false;
            } else if (odamagesource.k() && this.a(OPotion.n)) {
                return false;
            } else {
                this.aZ = 1.5F;

                // CanaryMod damage entities.
                LivingEntity attacker = null;

                if (odamagesource != null && odamagesource instanceof OEntityDamageSource && ((OEntityDamageSource) odamagesource).g() instanceof OEntityLiving) {
                    OEntity ent = ((OEntityDamageSource) odamagesource).g();

                    attacker = new LivingEntity((OEntityLiving) ent);
                }

                // CanaryMod attack by entity, but it might not do damage!
                if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.ATTACK, attacker, entity, i)) {
                    if (this instanceof OEntityCreature) {
                        ((OEntityCreature) this).c = 0;
                    }
                    return false;
                }
                boolean flag = true;

                if ((float) this.ad > (float) this.an / 2.0F) {
                    if (i <= this.bp) {
                        return false;
                    }

                    // CanaryMod: partial damage
                    if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, i - this.bp)) {
                        return false;
                    }
                    this.d(odamagesource, i - this.bp);
                    this.bp = i;
                    flag = false;
                } else {
                    // CanaryMod: full damage
                    if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, i)) {
                        return false;
                    }
                    this.bp = i;
                    this.aL = this.aK;
                    this.ad = this.an;
                    this.d(odamagesource, i);
                    this.aN = this.aO = 10;
                }

                this.aP = 0.0F;
                OEntity oentity = odamagesource.g();

                if (oentity != null) {
                    if (oentity instanceof OEntityLiving) {
                        this.c((OEntityLiving) oentity);
                    }

                    if (oentity instanceof OEntityPlayer) {
                        this.bc = 60;
                        this.bb = (OEntityPlayer) oentity;
                    } else if (oentity instanceof OEntityWolf) {
                        OEntityWolf oentitywolf = (OEntityWolf) oentity;

                        if (oentitywolf.n()) {
                            this.bc = 60;
                            this.bb = null;
                        }
                    }
                }

                if (flag) {
                    this.p.a(this, (byte) 2);
                    if (odamagesource != ODamageSource.e && odamagesource != ODamageSource.l) {
                        this.K();
                    }

                    if (oentity != null) {
                        double d0 = oentity.t - this.t;

                        double d1;

                        for (d1 = oentity.v - this.v; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                            d0 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.aP = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - this.z;
                        this.a(oentity, i, d0, d1);
                    } else {
                        this.aP = (float) ((int) (Math.random() * 2.0D) * 180);
                    }
                }

                if (this.aK <= 0) {
                    if (flag) {
                        this.p.a(this, this.aS(), this.aP(), this.i());
                    }

                    this.a(odamagesource);
                } else if (flag) {
                    this.p.a(this, this.aR(), this.aP(), this.i());
                }

                return true;
            }
        }
    }

    private float i() {
        return this.g_() ? (this.Z.nextFloat() - this.Z.nextFloat()) * 0.2F + 1.5F : (this.Z.nextFloat() - this.Z.nextFloat()) * 0.2F + 1.0F;
    }

    public int aO() {
        return 0;
    }

    protected void k(int i) {}

    protected int b(ODamageSource odamagesource, int i) {
        if (!odamagesource.c()) {
            int j = 25 - this.aO();
            int k = i * j + this.aM;

            this.k(i);
            i = k / 25;
            this.aM = k % 25;
        }

        return i;
    }

    protected int c(ODamageSource odamagesource, int i) {
        if (this.a(OPotion.m)) {
            int j = (this.b(OPotion.m).c() + 1) * 5;
            int k = 25 - j;
            int l = i * k + this.aM;

            i = l / 25;
            this.aM = l % 25;
        }

        return i;
    }

    protected void d(ODamageSource odamagesource, int i) {
        i = this.b(odamagesource, i);
        i = this.c(odamagesource, i);
        this.aK -= i;
    }

    protected float aP() {
        return 1.0F;
    }

    protected String aQ() {
        return null;
    }

    protected String aR() {
        return "damage.hurtflesh";
    }

    protected String aS() {
        return "damage.hurtflesh";
    }

    public void a(OEntity oentity, int i, double d0, double d1) {
        this.al = true;
        float f = OMathHelper.a(d0 * d0 + d1 * d1);
        float f1 = 0.4F;

        this.w /= 2.0D;
        this.x /= 2.0D;
        this.y /= 2.0D;
        this.w -= d0 / (double) f * (double) f1;
        this.x += (double) f1;
        this.y -= d1 / (double) f * (double) f1;
        if (this.x > 0.4000000059604645D) {
            this.x = 0.4000000059604645D;
        }

    }

    public void a(ODamageSource odamagesource) {
        OEntity oentity = odamagesource.g();

        if (this.aE >= 0 && oentity != null) {
            oentity.c(this, this.aE);
        }

        if (oentity != null) {
            oentity.a(this);
        }

        this.aU = true;
        if (!this.p.K) {
            int i = 0;

            if (oentity instanceof OEntityPlayer) {
                i = OEnchantmentHelper.f(((OEntityPlayer) oentity).by);
            }

            if (!this.g_()) {
                this.a(this.bc > 0, i);
                if (this.bc > 0) {
                    int j = this.Z.nextInt(200) - i;

                    if (j < 5) {
                        this.l(j <= 0 ? 1 : 0);
                    }
                }
            }
        }

        this.p.a(this, (byte) 3);
    }

    protected void l(int i) {}

    protected void a(boolean flag, int i) {
        int j = this.aT();

        if (j > 0) {
            int k = this.Z.nextInt(3);

            if (i > 0) {
                k += this.Z.nextInt(i + 1);
            }

            for (int l = 0; l < k; ++l) {
                this.b(j, 1);
            }
        }

    }

    protected int aT() {
        return 0;
    }

    protected void a(float f) {
        super.a(f);
        int i = OMathHelper.f(f - 3.0F);

        if (i > 0) {
            // CanaryMod Damage hook: Falling
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, entity, i)) {
                if (i > 4) {
                    this.p.a(this, "damage.fallbig", 1.0F, 1.0F);
                } else {
                    this.p.a(this, "damage.fallsmall", 1.0F, 1.0F);
                }

                this.a(ODamageSource.h, i);
            }
            int j = this.p.a(OMathHelper.c(this.t), OMathHelper.c(this.u - 0.20000000298023224D - (double) this.M), OMathHelper.c(this.v));

            if (j > 0) {
                OStepSound ostepsound = OBlock.m[j].cn;

                this.p.a(this, ostepsound.d(), ostepsound.b() * 0.5F, ostepsound.c() * 0.75F);
            }
        }

    }

    public void e(float f, float f1) {
        double d0;

        if (this.H() && (!(this instanceof OEntityPlayer) || !((OEntityPlayer) this).bZ.b)) {
            d0 = this.u;
            this.a(f, f1, this.aV() ? 0.04F : 0.02F);
            this.d(this.w, this.x, this.y);
            this.w *= 0.800000011920929D;
            this.x *= 0.800000011920929D;
            this.y *= 0.800000011920929D;
            this.x -= 0.02D;
            if (this.F && this.c(this.w, this.x + 0.6000000238418579D - this.u + d0, this.y)) {
                this.x = 0.30000001192092896D;
            }
        } else if (this.J() && (!(this instanceof OEntityPlayer) || !((OEntityPlayer) this).bZ.b)) {
            d0 = this.u;
            this.a(f, f1, 0.02F);
            this.d(this.w, this.x, this.y);
            this.w *= 0.5D;
            this.x *= 0.5D;
            this.y *= 0.5D;
            this.x -= 0.02D;
            if (this.F && this.c(this.w, this.x + 0.6000000238418579D - this.u + d0, this.y)) {
                this.x = 0.30000001192092896D;
            }
        } else {
            float f2 = 0.91F;

            if (this.E) {
                f2 = 0.54600006F;
                int i = this.p.a(OMathHelper.c(this.t), OMathHelper.c(this.D.b) - 1, OMathHelper.c(this.v));

                if (i > 0) {
                    f2 = OBlock.m[i].cq * 0.91F;
                }
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            float f4;

            if (this.E) {
                if (this.aV()) {
                    f4 = this.ay();
                } else {
                    f4 = this.aG;
                }

                f4 *= f3;
            } else {
                f4 = this.aH;
            }

            this.a(f, f1, f4);
            f2 = 0.91F;
            if (this.E) {
                f2 = 0.54600006F;
                int j = this.p.a(OMathHelper.c(this.t), OMathHelper.c(this.D.b) - 1, OMathHelper.c(this.v));

                if (j > 0) {
                    f2 = OBlock.m[j].cq * 0.91F;
                }
            }

            if (this.f_()) {
                float f5 = 0.15F;

                if (this.w < (double) (-f5)) {
                    this.w = (double) (-f5);
                }

                if (this.w > (double) f5) {
                    this.w = (double) f5;
                }

                if (this.y < (double) (-f5)) {
                    this.y = (double) (-f5);
                }

                if (this.y > (double) f5) {
                    this.y = (double) f5;
                }

                this.R = 0.0F;
                if (this.x < -0.15D) {
                    this.x = -0.15D;
                }

                boolean flag = this.af() && this instanceof OEntityPlayer;

                if (flag && this.x < 0.0D) {
                    this.x = 0.0D;
                }
            }

            this.d(this.w, this.x, this.y);
            if (this.F && this.f_()) {
                this.x = 0.2D;
            }

            this.x -= 0.08D;
            this.x *= 0.9800000190734863D;
            this.w *= (double) f2;
            this.y *= (double) f2;
        }

        this.aY = this.aZ;
        d0 = this.t - this.q;
        double d1 = this.v - this.s;
        float f6 = OMathHelper.a(d0 * d0 + d1 * d1) * 4.0F;

        if (f6 > 1.0F) {
            f6 = 1.0F;
        }

        this.aZ += (f6 - this.aZ) * 0.4F;
        this.ba += this.aZ;
    }

    public boolean f_() {
        int i = OMathHelper.c(this.t);
        int j = OMathHelper.c(this.D.b);
        int k = OMathHelper.c(this.v);
        int l = this.p.a(i, j, k);

        return l == OBlock.aF.ca || l == OBlock.bu.ca;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Health", (short) this.aK);
        onbttagcompound.a("HurtTime", (short) this.aN);
        onbttagcompound.a("DeathTime", (short) this.aQ);
        onbttagcompound.a("AttackTime", (short) this.aR);
        if (!this.bf.isEmpty()) {
            ONBTTagList onbttaglist = new ONBTTagList();
            Iterator iterator = this.bf.values().iterator();

            while (iterator.hasNext()) {
                OPotionEffect opotioneffect = (OPotionEffect) iterator.next();
                ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

                onbttagcompound1.a("Id", (byte) opotioneffect.a());
                onbttagcompound1.a("Amplifier", (byte) opotioneffect.c());
                onbttagcompound1.a("Duration", opotioneffect.b());
                onbttaglist.a((ONBTBase) onbttagcompound1);
            }

            onbttagcompound.a("ActiveEffects", (ONBTBase) onbttaglist);
        }

    }

    public void a(ONBTTagCompound onbttagcompound) {
        if (this.aK < -32768) {
            this.aK = -32768;
        }

        this.aK = onbttagcompound.d("Health");
        if (!onbttagcompound.b("Health")) {
            this.aK = this.aM();
        }

        this.aN = onbttagcompound.d("HurtTime");
        this.aQ = onbttagcompound.d("DeathTime");
        this.aR = onbttagcompound.d("AttackTime");
        if (onbttagcompound.b("ActiveEffects")) {
            ONBTTagList onbttaglist = onbttagcompound.m("ActiveEffects");

            for (int i = 0; i < onbttaglist.c(); ++i) {
                ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
                byte b0 = onbttagcompound1.c("Id");
                byte b1 = onbttagcompound1.c("Amplifier");
                int j = onbttagcompound1.e("Duration");

                this.bf.put(Integer.valueOf(b0), new OPotionEffect(b0, j, b1));
            }
        }

    }

    public boolean S() {
        return !this.L && this.aK > 0;
    }

    public boolean aU() {
        return false;
    }

    public void f(float f) {
        this.bs = f;
    }

    public void d(boolean flag) {
        this.bu = flag;
    }

    public void d() {
        if (this.bE > 0) {
            --this.bE;
        }

        if (this.bi > 0) {
            double d0 = this.t + (this.bj - this.t) / (double) this.bi;
            double d1 = this.u + (this.bk - this.u) / (double) this.bi;
            double d2 = this.v + (this.bl - this.v) / (double) this.bi;
            double d3 = OMathHelper.g(this.bm - (double) this.z);

            this.z = (float) ((double) this.z + d3 / (double) this.bi);
            this.A = (float) ((double) this.A + (this.bn - (double) this.A) / (double) this.bi);
            --this.bi;
            this.b(d0, d1, d2);
            this.b(this.z, this.A);
        }

        if (Math.abs(this.w) < 0.005D) {
            this.w = 0.0D;
        }

        if (Math.abs(this.x) < 0.005D) {
            this.x = 0.0D;
        }

        if (Math.abs(this.y) < 0.005D) {
            this.y = 0.0D;
        }

        this.p.F.a("ai");
        if (this.aX()) {
            this.bu = false;
            this.br = 0.0F;
            this.bs = 0.0F;
            this.bt = 0.0F;
        } else if (this.aW()) {
            if (this.aV()) {
                this.p.F.a("newAi");
                this.bc();
                this.p.F.b();
            } else {
                this.p.F.a("oldAi");
                this.be();
                this.p.F.b();
                this.as = this.z;
            }
        }

        this.p.F.b();
        this.p.F.a("jump");
        if (this.bu) {
            if (!this.H() && !this.J()) {
                if (this.E && this.bE == 0) {
                    this.aZ();
                    this.bE = 10;
                }
            } else {
                this.x += 0.03999999910593033D;
            }
        } else {
            this.bE = 0;
        }

        this.p.F.b();
        this.p.F.a("travel");
        this.br *= 0.98F;
        this.bs *= 0.98F;
        this.bt *= 0.9F;
        float f = this.aG;

        this.aG *= this.bs();
        this.e(this.br, this.bs);
        this.aG = f;
        this.p.F.b();
        this.p.F.a("push");
        if (!this.p.K) {
            List list = this.p.b((OEntity) this, this.D.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (list != null && !list.isEmpty()) {
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OEntity oentity = (OEntity) iterator.next();

                    if (oentity.M()) {
                        oentity.f(this);
                    }
                }
            }
        }

        this.p.F.b();
    }

    protected boolean aV() {
        return false;
    }

    protected boolean aW() {
        return !this.p.K;
    }

    protected boolean aX() {
        return this.aK <= 0;
    }

    public boolean aY() {
        return false;
    }

    protected void aZ() {
        this.x = 0.41999998688697815D;
        if (this.a(OPotion.j)) {
            this.x += (double) ((float) (this.b(OPotion.j).c() + 1) * 0.1F);
        }

        if (this.ag()) {
            float f = this.z * 0.017453292F;

            this.w -= (double) (OMathHelper.a(f) * 0.2F);
            this.y += (double) (OMathHelper.b(f) * 0.2F);
        }

        this.al = true;
    }

    protected boolean ba() {
        return true;
    }

    protected void bb() {
        OEntityPlayer oentityplayer = this.p.a(this, -1.0D);

        if (oentityplayer != null) {
            double d0 = oentityplayer.t - this.t;
            double d1 = oentityplayer.u - this.u;
            double d2 = oentityplayer.v - this.v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;

            if (this.ba() && d3 > 16384.0D) {
                // CanaryMod hook onEntityDespawn
                if (!(Boolean) OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
                    this.y();
                }
            }

            if (this.bq > 600 && this.Z.nextInt(800) == 0 && d3 > 1024.0D && this.ba()) {
                // CanaryMod hook onEntityDespawn
                if (!(Boolean) OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
                    this.y();
                } else {
                    this.bq = 0;
                }
            } else if (d3 < 1024.0D) {
                this.bq = 0;
            }
        }

    }

    protected void bc() {
        ++this.bq;
        this.p.F.a("checkDespawn");
        this.bb();
        this.p.F.b();
        this.p.F.a("sensing");
        this.bA.a();
        this.p.F.b();
        this.p.F.a("targetSelector");
        this.bh.a();
        this.p.F.b();
        this.p.F.a("goalSelector");
        this.bg.a();
        this.p.F.b();
        this.p.F.a("navigation");
        this.by.e();
        this.p.F.b();
        this.p.F.a("mob tick");
        this.bd();
        this.p.F.b();
        this.p.F.a("controls");
        this.p.F.a("move");
        this.h.c();
        this.p.F.c("look");
        this.g.a();
        this.p.F.c("jump");
        this.i.b();
        this.p.F.b();
        this.p.F.b();
    }

    protected void bd() {}

    protected void be() {
        ++this.bq;
        this.bb();
        this.br = 0.0F;
        this.bs = 0.0F;
        float f = 8.0F;

        if (this.Z.nextFloat() < 0.02F) {
            OEntityPlayer oentityplayer = this.p.a(this, (double) f);

            if (oentityplayer != null) {
                this.bF = oentityplayer;
                this.bx = 10 + this.Z.nextInt(20);
            } else {
                this.bt = (this.Z.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.bF != null) {
            this.a(this.bF, 10.0F, (float) this.bf());
            if (this.bx-- <= 0 || this.bF.L || this.bF.e((OEntity) this) > (double) (f * f)) {
                this.bF = null;
            }
        } else {
            if (this.Z.nextFloat() < 0.05F) {
                this.bt = (this.Z.nextFloat() - 0.5F) * 20.0F;
            }

            this.z += this.bt;
            this.A = this.bv;
        }

        boolean flag = this.H();
        boolean flag1 = this.J();

        if (flag || flag1) {
            this.bu = this.Z.nextFloat() < 0.8F;
        }

    }

    public int bf() {
        return 40;
    }

    public void a(OEntity oentity, float f, float f1) {
        double d0 = oentity.t - this.t;
        double d1 = oentity.v - this.v;
        double d2;

        if (oentity instanceof OEntityLiving) {
            OEntityLiving oentityliving = (OEntityLiving) oentity;

            d2 = this.u + (double) this.e() - (oentityliving.u + (double) oentityliving.e());
        } else {
            d2 = (oentity.D.b + oentity.D.e) / 2.0D - (this.u + (double) this.e());
        }

        double d3 = (double) OMathHelper.a(d0 * d0 + d1 * d1);
        float f2 = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
        float f3 = (float) (-(Math.atan2(d2, d3) * 180.0D / 3.1415927410125732D));

        this.A = -this.b(this.A, f3, f1);
        this.z = this.b(this.z, f2, f);
    }

    private float b(float f, float f1, float f2) {
        float f3 = OMathHelper.g(f1 - f);

        if (f3 > f2) {
            f3 = f2;
        }

        if (f3 < -f2) {
            f3 = -f2;
        }

        return f + f3;
    }

    public boolean bi() {
        return this.p.b(this.D) && this.p.a((OEntity) this, this.D).isEmpty() && !this.p.d(this.D);
    }

    protected void C() {
        this.a(ODamageSource.i, 4);
    }

    public OVec3 Z() {
        return this.i(1.0F);
    }

    public OVec3 i(float f) {
        float f1;
        float f2;
        float f3;
        float f4;

        if (f == 1.0F) {
            f1 = OMathHelper.b(-this.z * 0.017453292F - 3.1415927F);
            f2 = OMathHelper.a(-this.z * 0.017453292F - 3.1415927F);
            f3 = -OMathHelper.b(-this.A * 0.017453292F);
            f4 = OMathHelper.a(-this.A * 0.017453292F);
            return OVec3.a().a((double) (f2 * f3), (double) f4, (double) (f1 * f3));
        } else {
            f1 = this.C + (this.A - this.C) * f;
            f2 = this.B + (this.z - this.B) * f;
            f3 = OMathHelper.b(-f2 * 0.017453292F - 3.1415927F);
            f4 = OMathHelper.a(-f2 * 0.017453292F - 3.1415927F);
            float f5 = -OMathHelper.b(-f1 * 0.017453292F);
            float f6 = OMathHelper.a(-f1 * 0.017453292F);

            return OVec3.a().a((double) (f4 * f5), (double) f6, (double) (f3 * f5));
        }
    }

    public int bl() {
        return 4;
    }

    public boolean bn() {
        return false;
    }

    protected void bo() {
        Iterator iterator = this.bf.keySet().iterator();

        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            OPotionEffect opotioneffect = (OPotionEffect) this.bf.get(integer);

            if (!opotioneffect.a(this) && !this.p.K) {
                iterator.remove();
                this.c(opotioneffect);
            }
        }

        int i;

        if (this.e) {
            if (!this.p.K) {
                if (this.bf.isEmpty()) {
                    this.af.b(8, Integer.valueOf(0));
                } else {
                    i = OPotionHelper.a(this.bf.values());
                    this.af.b(8, Integer.valueOf(i));
                }
            }

            this.e = false;
        }

        if (this.Z.nextBoolean()) {
            i = this.af.c(8);
            if (i > 0) {
                double d0 = (double) (i >> 16 & 255) / 255.0D;
                double d1 = (double) (i >> 8 & 255) / 255.0D;
                double d2 = (double) (i >> 0 & 255) / 255.0D;

                this.p.a("mobSpell", this.t + (this.Z.nextDouble() - 0.5D) * (double) this.N, this.u + this.Z.nextDouble() * (double) this.O - (double) this.M, this.v + (this.Z.nextDouble() - 0.5D) * (double) this.N, d0, d1, d2);
            }
        }

    }

    public void bp() {
        Iterator iterator = this.bf.keySet().iterator();

        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            OPotionEffect opotioneffect = (OPotionEffect) this.bf.get(integer);

            if (!this.p.K) {
                iterator.remove();
                this.c(opotioneffect);
            }
        }

    }

    public Collection bq() {
        return this.bf.values();
    }

    public boolean a(OPotion opotion) {
        return this.bf.containsKey(Integer.valueOf(opotion.H));
    }

    public OPotionEffect b(OPotion opotion) {
        return (OPotionEffect) this.bf.get(Integer.valueOf(opotion.H));
    }

    public void d(OPotionEffect opotioneffect) {
        // CanaryMod - POTION_EFFECT HOOK
        PotionEffect pe = (PotionEffect) etc.getLoader().callHook(PluginLoader.Hook.POTION_EFFECT, this.entity, opotioneffect.potionEffect);

        if (pe == null) {
            return;
        }
        opotioneffect = pe.potionEffect;
        if (this.e(opotioneffect)) {
            if (this.bf.containsKey(Integer.valueOf(opotioneffect.a()))) {
                ((OPotionEffect) this.bf.get(Integer.valueOf(opotioneffect.a()))).a(opotioneffect);
                this.b((OPotionEffect) this.bf.get(Integer.valueOf(opotioneffect.a())));
            } else {
                this.bf.put(Integer.valueOf(opotioneffect.a()), opotioneffect);
                this.a(opotioneffect);
            }

        }
    }

    public boolean e(OPotionEffect opotioneffect) {
        if (this.bt() == OEnumCreatureAttribute.b) {
            int i = opotioneffect.a();

            if (i == OPotion.l.H || i == OPotion.u.H) {
                return false;
            }
        }

        return true;
    }

    public boolean br() {
        return this.bt() == OEnumCreatureAttribute.b;
    }

    protected void a(OPotionEffect opotioneffect) {
        this.e = true;
    }

    protected void b(OPotionEffect opotioneffect) {
        this.e = true;
    }

    protected void c(OPotionEffect opotioneffect) {
        this.e = true;
    }

    protected float bs() {
        float f = 1.0F;

        if (this.a(OPotion.c)) {
            f *= 1.0F + 0.2F * (float) (this.b(OPotion.c).c() + 1);
        }

        if (this.a(OPotion.d)) {
            f *= 1.0F - 0.15F * (float) (this.b(OPotion.d).c() + 1);
        }

        return f;
    }

    public void a(double d0, double d1, double d2) {
        this.b(d0, d1, d2, this.z, this.A);
    }

    public boolean g_() {
        return false;
    }

    public OEnumCreatureAttribute bt() {
        return OEnumCreatureAttribute.a;
    }

    public void a(OItemStack oitemstack) {
        this.p.a(this, "random.break", 0.8F, 0.8F + this.p.v.nextFloat() * 0.4F);

        for (int i = 0; i < 5; ++i) {
            OVec3 ovec3 = OVec3.a().a(((double) this.Z.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

            ovec3.a(-this.A * 3.1415927F / 180.0F);
            ovec3.b(-this.z * 3.1415927F / 180.0F);
            OVec3 ovec31 = OVec3.a().a(((double) this.Z.nextFloat() - 0.5D) * 0.3D, (double) (-this.Z.nextFloat()) * 0.6D - 0.3D, 0.6D);

            ovec31.a(-this.A * 3.1415927F / 180.0F);
            ovec31.b(-this.z * 3.1415927F / 180.0F);
            ovec31 = ovec31.c(this.t, this.u + (double) this.e(), this.v);
            this.p.a("iconcrack_" + oitemstack.b().bT, ovec31.a, ovec31.b, ovec31.c, ovec3.a, ovec3.b + 0.05D, ovec3.c);
        }

    }
}
