import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public abstract class OEntityLiving extends OEntity {

    public int S = 20;
    public float T;
    public float U;
    public float V = 0.0F;
    public float W = 0.0F;
    public float X = 0.0F;
    public float Y = 0.0F;
    protected float Z;
    protected float aa;
    protected float ab;
    protected float ac;
    protected boolean ad = true;
    protected String ae = "/mob/char.png";
    protected boolean af = true;
    protected float ag = 0.0F;
    protected String ah = null;
    protected float ai = 1.0F;
    protected int aj = 0;
    protected float ak = 0.0F;
    public float al = 0.1F;
    public float am = 0.02F;
    public float an;
    public float ao;
    protected int ap = this.d();
    public int aq;
    protected int ar;
    private int a;
    public int as;
    public int at;
    public float au = 0.0F;
    public int av = 0;
    public int aw = 0;
    public float ax;
    public float ay;
    protected boolean az = false;
    protected int aA;
    public int aB = -1;
    public float aC = (float) (Math.random() * 0.8999999761581421D + 0.10000000149011612D);
    public float aD;
    public float aE;
    public float aF;
    protected OEntityPlayer aG = null;
    protected int aH = 0;
    private OEntityLiving b = null;
    private int c = 0;
    private OEntityLiving d = null;
    public int aI = 0;
    public int aJ = 0;
    protected HashMap aK = new HashMap();
    private boolean e = true;
    private int f;
    private OEntityLookHelper g;
    private OEntityMoveHelper h;
    private OEntityJumpHelper i;
    private OEntityBodyHelper j;
    private OPathNavigate k;
    protected OEntityAITasks aL = new OEntityAITasks();
    protected OEntityAITasks aM = new OEntityAITasks();
    private OEntityLiving l;
    private OEntitySenses m;
    private float n;
    private OChunkCoordinates o = new OChunkCoordinates(0, 0, 0);
    private float p = -1.0F;
    protected int aN;
    protected double aO;
    protected double aP;
    protected double aQ;
    protected double aR;
    protected double aS;
    float aT = 0.0F;
    protected int aU = 0;
    protected int aV = 0;
    protected float aW;
    protected float aX;
    protected float aY;
    protected boolean aZ = false;
    protected float ba = 0.0F;
    protected float bb = 0.7F;
    private int q = 0;
    private OEntity r;
    protected int bc = 0;

    // CanaryMod Start
    LivingEntity entity = new LivingEntity(this);
    protected MobSpawner spawner = null;

    // CanaryMod end
    public OEntityLiving(OWorld oworld) {
        super(oworld);
        this.bf = true;
        this.g = new OEntityLookHelper(this);
        this.h = new OEntityMoveHelper(this);
        this.i = new OEntityJumpHelper(this);
        this.j = new OEntityBodyHelper(this);
        this.k = new OPathNavigate(this, oworld, 16.0F);
        this.m = new OEntitySenses(this);
        this.U = (float) (Math.random() + 1.0D) * 0.01F;
        this.c(this.bm, this.bn, this.bo);
        this.T = (float) Math.random() * 12398.0F;
        this.bs = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.X = this.bs;
        this.bP = 0.5F;
    }

    public OEntityLookHelper ai() {
        return this.g;
    }

    public OEntityMoveHelper aj() {
        return this.h;
    }

    public OEntityJumpHelper ak() {
        return this.i;
    }

    public OPathNavigate al() {
        return this.k;
    }

    public OEntitySenses am() {
        return this.m;
    }

    public Random an() {
        return this.bS;
    }

    public OEntityLiving ao() {
        return this.b;
    }

    public OEntityLiving ap() {
        return this.d;
    }

    public void g(OEntity oentity) {
        if (oentity instanceof OEntityLiving) {
            this.d = (OEntityLiving) oentity;
        }

    }

    public int aq() {
        return this.aV;
    }

    public float ar() {
        return this.X;
    }

    public float as() {
        return this.n;
    }

    public void d(float f) {
        this.n = f;
        this.e(f);
    }

    public boolean a(OEntity oentity) {
        this.g(oentity);
        return false;
    }

    public OEntityLiving at() {
        return this.l;
    }

    public void b(OEntityLiving oentityliving) {
        this.l = oentityliving;
    }

    public boolean a(Class oclass) {
        return OEntityCreeper.class != oclass && OEntityGhast.class != oclass;
    }

    public void z() {}

    public boolean au() {
        return this.e(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo));
    }

    public boolean e(int i, int j, int k) {
        return this.p == -1.0F ? true : this.o.c(i, j, k) < this.p * this.p;
    }

    public void b(int i, int j, int k, int l) {
        this.o.a(i, j, k);
        this.p = (float) l;
    }

    public OChunkCoordinates av() {
        return this.o;
    }

    public float aw() {
        return this.p;
    }

    public void ax() {
        this.p = -1.0F;
    }

    public boolean ay() {
        return this.p != -1.0F;
    }

    public void a(OEntityLiving oentityliving) {
        this.b = oentityliving;
        this.c = this.b != null ? 60 : 0;
    }

    protected void b() {
        this.bY.a(8, Integer.valueOf(this.f));
    }

    public boolean h(OEntity oentity) {
        return this.bi.a(OVec3D.b(this.bm, this.bn + (double) this.B(), this.bo), OVec3D.b(oentity.bm, oentity.bn + (double) oentity.B(), oentity.bo)) == null;
    }

    public boolean o_() {
        return !this.bE;
    }

    public boolean e_() {
        return !this.bE;
    }

    public float B() {
        return this.bH * 0.85F;
    }

    public int m() {
        return 80;
    }

    public void az() {
        String s = this.i();

        if (s != null) {
            this.bi.a(this, s, this.p(), this.A());
        }

    }

    public void aA() {
        this.an = this.ao;
        super.aA();
        OProfiler.a("mobBaseTick");
        if (this.aE() && this.bS.nextInt(1000) < this.a++) {
            this.a = -this.m();
            this.az();
        }

        if (this.aE() && this.Y()) {
            // CanaryMod Damage hook: Suffocation
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.SUFFOCATION, null, entity, 1)) {
                this.a(ODamageSource.e, 1);

            }
        }

        if (this.aS() || this.bi.F) {
            this.aR();
        }

        if (this.aE() && this.a(OMaterial.g) && !this.f_() && !this.aK.containsKey(Integer.valueOf(OPotion.o.H))) {
            this.k(this.b_(this.ba()));
            if (this.ba() == -20) {
                this.k(0);
                // CanaryMod Damage hook: Drowning
                if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.WATER, null, entity, 2)) {
                    for (int i = 0; i < 8; ++i) {
                        float f = this.bS.nextFloat() - this.bS.nextFloat();
                        float f1 = this.bS.nextFloat() - this.bS.nextFloat();
                        float f2 = this.bS.nextFloat() - this.bS.nextFloat();

                        this.bi.a("bubble", this.bm + (double) f, this.bn + (double) f1, this.bo + (double) f2, this.bp, this.bq, this.br);
                    }

                    this.a(ODamageSource.f, 2);
                }
            }

            this.aR();
        } else {
            this.k(300);
        }

        this.ax = this.ay;
        if (this.aw > 0) {
            --this.aw;
        }

        if (this.as > 0) {
            --this.as;
        }

        if (this.bW > 0) {
            --this.bW;
        }

        if (this.ap <= 0) {
            this.aB();
        }

        if (this.aH > 0) {
            --this.aH;
        } else {
            this.aG = null;
        }

        if (this.d != null && !this.d.aE()) {
            this.d = null;
        }

        if (this.b != null) {
            if (!this.b.aE()) {
                this.a((OEntityLiving) null);
            } else if (this.c > 0) {
                --this.c;
            } else {
                this.a((OEntityLiving) null);
            }
        }

        this.aK();
        this.ac = this.ab;
        this.W = this.V;
        this.Y = this.X;
        this.bu = this.bs;
        this.bv = this.bt;
        OProfiler.a();
    }

    protected void aB() {
        ++this.av;
        if (this.av == 20) {
            int i;

            if (!this.bi.F && (this.aH > 0 || this.ah()) && !this.aO()) {
                i = this.a(this.aG);

                while (i > 0) {
                    int j = OEntityXPOrb.b(i);

                    i -= j;
                    this.bi.b((OEntity) (new OEntityXPOrb(this.bi, this.bm, this.bn, this.bo, j)));
                }
            }

            this.aH();
            this.X();

            for (i = 0; i < 20; ++i) {
                double d0 = this.bS.nextGaussian() * 0.02D;
                double d1 = this.bS.nextGaussian() * 0.02D;
                double d2 = this.bS.nextGaussian() * 0.02D;

                this.bi.a("explode", this.bm + (double) (this.bS.nextFloat() * this.bG * 2.0F) - (double) this.bG, this.bn + (double) (this.bS.nextFloat() * this.bH), this.bo + (double) (this.bS.nextFloat() * this.bG * 2.0F) - (double) this.bG, d0, d1, d2);
            }
        }

    }

    protected int b_(int i) {
        return i - 1;
    }

    protected int a(OEntityPlayer oentityplayer) {
        return this.aA;
    }

    protected boolean ah() {
        return false;
    }

    public void aC() {
        for (int i = 0; i < 20; ++i) {
            double d0 = this.bS.nextGaussian() * 0.02D;
            double d1 = this.bS.nextGaussian() * 0.02D;
            double d2 = this.bS.nextGaussian() * 0.02D;
            double d3 = 10.0D;

            this.bi.a("explode", this.bm + (double) (this.bS.nextFloat() * this.bG * 2.0F) - (double) this.bG - d0 * d3, this.bn + (double) (this.bS.nextFloat() * this.bH) - d1 * d3, this.bo + (double) (this.bS.nextFloat() * this.bG * 2.0F) - (double) this.bG - d2 * d3, d0, d1, d2);
        }

    }

    public void R() {
        super.R();
        this.Z = this.aa;
        this.aa = 0.0F;
        this.bK = 0.0F;
    }

    public void F_() {
        super.F_();
        if (this.aI > 0) {
            if (this.aJ <= 0) {
                this.aJ = 60;
            }

            --this.aJ;
            if (this.aJ <= 0) {
                --this.aI;
            }
        }

        this.e();
        double d0 = this.bm - this.bj;
        double d1 = this.bo - this.bl;
        float f = OMathHelper.a(d0 * d0 + d1 * d1);
        float f1 = this.V;
        float f2 = 0.0F;

        this.Z = this.aa;
        float f3 = 0.0F;

        if (f > 0.05F) {
            f3 = 1.0F;
            f2 = f * 3.0F;
            f1 = (float) Math.atan2(d1, d0) * 180.0F / 3.1415927F - 90.0F;
        }

        if (this.ao > 0.0F) {
            f1 = this.bs;
        }

        if (!this.bx) {
            f3 = 0.0F;
        }

        this.aa += (f3 - this.aa) * 0.3F;
        if (this.c_()) {
            this.j.a();
        } else {
            float f4;

            for (f4 = f1 - this.V; f4 < -180.0F; f4 += 360.0F) {
                ;
            }

            while (f4 >= 180.0F) {
                f4 -= 360.0F;
            }

            this.V += f4 * 0.3F;

            float f5;

            for (f5 = this.bs - this.V; f5 < -180.0F; f5 += 360.0F) {
                ;
            }

            while (f5 >= 180.0F) {
                f5 -= 360.0F;
            }

            boolean flag = f5 < -90.0F || f5 >= 90.0F;

            if (f5 < -75.0F) {
                f5 = -75.0F;
            }

            if (f5 >= 75.0F) {
                f5 = 75.0F;
            }

            this.V = this.bs - f5;
            if (f5 * f5 > 2500.0F) {
                this.V += f5 * 0.2F;
            }

            if (flag) {
                f2 *= -1.0F;
            }
        }

        while (this.bs - this.bu < -180.0F) {
            this.bu -= 360.0F;
        }

        while (this.bs - this.bu >= 180.0F) {
            this.bu += 360.0F;
        }

        while (this.V - this.W < -180.0F) {
            this.W -= 360.0F;
        }

        while (this.V - this.W >= 180.0F) {
            this.W += 360.0F;
        }

        while (this.bt - this.bv < -180.0F) {
            this.bv -= 360.0F;
        }

        while (this.bt - this.bv >= 180.0F) {
            this.bv += 360.0F;
        }

        while (this.X - this.Y < -180.0F) {
            this.Y -= 360.0F;
        }

        while (this.X - this.Y >= 180.0F) {
            this.Y += 360.0F;
        }

        this.ab += f2;
    }

    protected void b(float f, float f1) {
        super.b(f, f1);
    }

    public void d(int i) {
        if (this.ap > 0) {
            this.ap += i;
            if (this.ap > this.d()) {
                this.ap = this.d();
            }

            this.bW = this.S / 2;
        }
    }

    public abstract int d();

    public int aD() {
        return this.ap;
    }

    public void h(int i) {
        this.ap = i;
        if (i > this.d()) {
            i = this.d();
        }

    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.bi.F) {
            return false;
        } else {
            this.aV = 0;
            if (this.ap <= 0) {
                return false;
            } else if (odamagesource.k() && this.a(OPotion.n)) {
                return false;
            } else {
                this.aE = 1.5F;

                // CanaryMod damage entities.
                LivingEntity attacker = null;

                if (odamagesource != null && odamagesource instanceof OEntityDamageSource && ((OEntityDamageSource) odamagesource).a() instanceof OEntityLiving) {
                    OEntity o = ((OEntityDamageSource) odamagesource).a();

                    attacker = new LivingEntity((OEntityLiving) o);
                }

                // CanaryMod attack by entity, but it might not do damage!
                if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.ATTACK, attacker, entity, i)) {
                    if (this instanceof OEntityCreature) {
                        ((OEntityCreature) this).f = 0;
                    }
                    return false;
                }
                boolean flag = true;

                if ((float) this.bW > (float) this.S / 2.0F) {
                    if (i <= this.aU) {
                        return false;
                    }

                    // CanaryMod: partial damage
                    if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, i - bW)) {
                        return false;
                    }
                    this.c(odamagesource, i - this.aU);
                    this.aU = i;
                    flag = false;
                } else {
                    // CanaryMod: full damage
                    if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, i)) {
                        return false;
                    }
                    this.aU = i;
                    this.aq = this.ap;
                    this.bW = this.S;
                    this.c(odamagesource, i);
                    this.as = this.at = 10;
                }

                this.au = 0.0F;
                OEntity oentity = odamagesource.a();

                if (oentity != null) {
                    if (oentity instanceof OEntityLiving) {
                        this.a((OEntityLiving) oentity);
                    }

                    if (oentity instanceof OEntityPlayer) {
                        this.aH = 60;
                        this.aG = (OEntityPlayer) oentity;
                    } else if (oentity instanceof OEntityWolf) {
                        OEntityWolf oentitywolf = (OEntityWolf) oentity;

                        if (oentitywolf.u_()) {
                            this.aH = 60;
                            this.aG = null;
                        }
                    }
                }

                if (flag) {
                    this.bi.a(this, (byte) 2);
                    this.aW();
                    if (oentity != null) {
                        double d0 = oentity.bm - this.bm;

                        double d1;

                        for (d1 = oentity.bo - this.bo; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                            d0 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.au = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - this.bs;
                        this.a(oentity, i, d0, d1);
                    } else {
                        this.au = (float) ((int) (Math.random() * 2.0D) * 180);
                    }
                }

                if (this.ap <= 0) {
                    if (flag) {
                        this.bi.a(this, this.k(), this.p(), this.A());
                    }

                    this.a(odamagesource);
                } else if (flag) {
                    this.bi.a(this, this.j(), this.p(), this.A());
                }

                return true;
            }
        }
    }

    private float A() {
        return this.aO() ? (this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.5F : (this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F;
    }

    public int T() {
        return 0;
    }

    protected void f(int i) {}

    protected int d(ODamageSource odamagesource, int i) {
        if (!odamagesource.e()) {
            int j = 25 - this.T();
            int k = i * j + this.ar;

            this.f(i);
            i = k / 25;
            this.ar = k % 25;
        }

        return i;
    }

    protected int b(ODamageSource odamagesource, int i) {
        if (this.a(OPotion.m)) {
            int j = (this.b(OPotion.m).c() + 1) * 5;
            int k = 25 - j;
            int l = i * k + this.ar;

            i = l / 25;
            this.ar = l % 25;
        }

        return i;
    }

    protected void c(ODamageSource odamagesource, int i) {
        i = this.d(odamagesource, i);
        i = this.b(odamagesource, i);
        this.ap -= i;
    }

    protected float p() {
        return 1.0F;
    }

    protected String i() {
        return null;
    }

    protected String j() {
        return "damage.hurtflesh";
    }

    protected String k() {
        return "damage.hurtflesh";
    }

    public void a(OEntity oentity, int i, double d0, double d1) {
        this.ce = true;
        float f = OMathHelper.a(d0 * d0 + d1 * d1);
        float f1 = 0.4F;

        this.bp /= 2.0D;
        this.bq /= 2.0D;
        this.br /= 2.0D;
        this.bp -= d0 / (double) f * (double) f1;
        this.bq += (double) f1;
        this.br -= d1 / (double) f * (double) f1;
        if (this.bq > 0.4000000059604645D) {
            this.bq = 0.4000000059604645D;
        }

    }

    public void a(ODamageSource odamagesource) {
        OEntity oentity = odamagesource.a();

        if (this.aj >= 0 && oentity != null) {
            oentity.b(this, this.aj);
        }

        if (oentity != null) {
            oentity.c(this);
        }

        this.az = true;
        if (!this.bi.F) {
            int i = 0;

            if (oentity instanceof OEntityPlayer) {
                i = OEnchantmentHelper.f(((OEntityPlayer) oentity).k);
            }

            if (!this.aO()) {
                this.a(this.aH > 0, i);
                if (this.aH > 0) {
                    int j = this.bS.nextInt(200) - i;

                    if (j < 5) {
                        this.b(j <= 0 ? 1 : 0);
                    }
                }
            }
        }

        this.bi.a(this, (byte) 3);
    }

    protected void b(int i) {}

    protected void a(boolean flag, int i) {
        int j = this.f();

        if (j > 0) {
            int k = this.bS.nextInt(3);

            if (i > 0) {
                k += this.bS.nextInt(i + 1);
            }

            for (int l = 0; l < k; ++l) {
                this.b(j, 1);
            }
        }

    }

    protected int f() {
        return 0;
    }

    protected void a(float f) {
        super.a(f);
        int i = (int) Math.ceil((double) (f - 3.0F));

        if (i > 0) {
            // CanaryMod Damage hook: Falling
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, entity, i)) {
                if (i > 4) {
                    this.bi.a(this, "damage.fallbig", 1.0F, 1.0F);
                } else {
                    this.bi.a(this, "damage.fallsmall", 1.0F, 1.0F);
                }

                this.a(ODamageSource.i, i);
            }
            int j = this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bn - 0.20000000298023224D - (double) this.bF), OMathHelper.b(this.bo));

            if (j > 0) {
                OStepSound ostepsound = OBlock.m[j].cb;

                this.bi.a(this, ostepsound.c(), ostepsound.a() * 0.5F, ostepsound.b() * 0.75F);
            }
        }

    }

    public void a(float f, float f1) {
        double d0;

        if (this.aU()) {
            d0 = this.bn;
            this.a(f, f1, this.c_() ? 0.04F : 0.02F);
            this.a(this.bp, this.bq, this.br);
            this.bp *= 0.800000011920929D;
            this.bq *= 0.800000011920929D;
            this.br *= 0.800000011920929D;
            this.bq -= 0.02D;
            if (this.by && this.d(this.bp, this.bq + 0.6000000238418579D - this.bn + d0, this.br)) {
                this.bq = 0.30000001192092896D;
            }
        } else if (this.aV()) {
            d0 = this.bn;
            this.a(f, f1, 0.02F);
            this.a(this.bp, this.bq, this.br);
            this.bp *= 0.5D;
            this.bq *= 0.5D;
            this.br *= 0.5D;
            this.bq -= 0.02D;
            if (this.by && this.d(this.bp, this.bq + 0.6000000238418579D - this.bn + d0, this.br)) {
                this.bq = 0.30000001192092896D;
            }
        } else {
            float f2 = 0.91F;

            if (this.bx) {
                f2 = 0.54600006F;
                int i = this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bw.b) - 1, OMathHelper.b(this.bo));

                if (i > 0) {
                    f2 = OBlock.m[i].ce * 0.91F;
                }
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            float f4;

            if (this.bx) {
                if (this.c_()) {
                    f4 = this.as();
                } else {
                    f4 = this.al;
                }

                f4 *= f3;
            } else {
                f4 = this.am;
            }

            this.a(f, f1, f4);
            f2 = 0.91F;
            if (this.bx) {
                f2 = 0.54600006F;
                int j = this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bw.b) - 1, OMathHelper.b(this.bo));

                if (j > 0) {
                    f2 = OBlock.m[j].ce * 0.91F;
                }
            }

            if (this.t()) {
                float f5 = 0.15F;

                if (this.bp < (double) (-f5)) {
                    this.bp = (double) (-f5);
                }

                if (this.bp > (double) f5) {
                    this.bp = (double) f5;
                }

                if (this.br < (double) (-f5)) {
                    this.br = (double) (-f5);
                }

                if (this.br > (double) f5) {
                    this.br = (double) f5;
                }

                this.bK = 0.0F;
                if (this.bq < -0.15D) {
                    this.bq = -0.15D;
                }

                boolean flag = this.aY() && this instanceof OEntityPlayer;

                if (flag && this.bq < 0.0D) {
                    this.bq = 0.0D;
                }
            }

            this.a(this.bp, this.bq, this.br);
            if (this.by && this.t()) {
                this.bq = 0.2D;
            }

            this.bq -= 0.08D;
            this.bq *= 0.9800000190734863D;
            this.bp *= (double) f2;
            this.br *= (double) f2;
        }

        this.aD = this.aE;
        d0 = this.bm - this.bj;
        double d1 = this.bo - this.bl;
        float f6 = OMathHelper.a(d0 * d0 + d1 * d1) * 4.0F;

        if (f6 > 1.0F) {
            f6 = 1.0F;
        }

        this.aE += (f6 - this.aE) * 0.4F;
        this.aF += this.aE;
    }

    public boolean t() {
        int i = OMathHelper.b(this.bm);
        int j = OMathHelper.b(this.bw.b);
        int k = OMathHelper.b(this.bo);
        int l = this.bi.a(i, j, k);

        return l == OBlock.aF.bO || l == OBlock.bu.bO;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Health", (short) this.ap);
        onbttagcompound.a("HurtTime", (short) this.as);
        onbttagcompound.a("DeathTime", (short) this.av);
        onbttagcompound.a("AttackTime", (short) this.aw);
        if (!this.aK.isEmpty()) {
            ONBTTagList onbttaglist = new ONBTTagList();
            Iterator iterator = this.aK.values().iterator();

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
        if (this.ap < -32768) {
            this.ap = -32768;
        }

        this.ap = onbttagcompound.e("Health");
        if (!onbttagcompound.c("Health")) {
            this.ap = this.d();
        }

        this.as = onbttagcompound.e("HurtTime");
        this.av = onbttagcompound.e("DeathTime");
        this.aw = onbttagcompound.e("AttackTime");
        if (onbttagcompound.c("ActiveEffects")) {
            ONBTTagList onbttaglist = onbttagcompound.n("ActiveEffects");

            for (int i = 0; i < onbttaglist.d(); ++i) {
                ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.a(i);
                byte b0 = onbttagcompound1.d("Id");
                byte b1 = onbttagcompound1.d("Amplifier");
                int j = onbttagcompound1.f("Duration");

                this.aK.put(Integer.valueOf(b0), new OPotionEffect(b0, j, b1));
            }
        }

    }

    public boolean aE() {
        return !this.bE && this.ap > 0;
    }

    public boolean f_() {
        return false;
    }

    public void e(float f) {
        this.aX = f;
    }

    public void f(boolean flag) {
        this.aZ = flag;
    }

    public void e() {
        if (this.q > 0) {
            --this.q;
        }

        if (this.aN > 0) {
            double d0 = this.bm + (this.aO - this.bm) / (double) this.aN;
            double d1 = this.bn + (this.aP - this.bn) / (double) this.aN;
            double d2 = this.bo + (this.aQ - this.bo) / (double) this.aN;

            double d3;

            for (d3 = this.aR - (double) this.bs; d3 < -180.0D; d3 += 360.0D) {
                ;
            }

            while (d3 >= 180.0D) {
                d3 -= 360.0D;
            }

            this.bs = (float) ((double) this.bs + d3 / (double) this.aN);
            this.bt = (float) ((double) this.bt + (this.aS - (double) this.bt) / (double) this.aN);
            --this.aN;
            this.c(d0, d1, d2);
            this.c(this.bs, this.bt);
            List list = this.bi.a((OEntity) this, this.bw.e(0.03125D, 0.0D, 0.03125D));

            if (list.size() > 0) {
                double d4 = 0.0D;

                for (int i = 0; i < list.size(); ++i) {
                    OAxisAlignedBB oaxisalignedbb = (OAxisAlignedBB) list.get(i);

                    if (oaxisalignedbb.e > d4) {
                        d4 = oaxisalignedbb.e;
                    }
                }

                d1 += d4 - this.bw.b;
                this.c(d0, d1, d2);
            }
        }

        OProfiler.a("ai");
        if (this.Q()) {
            this.aZ = false;
            this.aW = 0.0F;
            this.aX = 0.0F;
            this.aY = 0.0F;
        } else if (this.aF()) {
            if (this.c_()) {
                OProfiler.a("newAi");
                this.z_();
                OProfiler.a();
            } else {
                OProfiler.a("oldAi");
                this.d_();
                OProfiler.a();
                this.X = this.bs;
            }
        }

        OProfiler.a();
        boolean flag = this.aU();
        boolean flag1 = this.aV();

        if (this.aZ) {
            if (flag) {
                this.bq += 0.03999999910593033D;
            } else if (flag1) {
                this.bq += 0.03999999910593033D;
            } else if (this.bx && this.q == 0) {
                this.ac();
                this.q = 10;
            }
        } else {
            this.q = 0;
        }

        this.aW *= 0.98F;
        this.aX *= 0.98F;
        this.aY *= 0.9F;
        float f = this.al;

        this.al *= this.J();
        this.a(this.aW, this.aX);
        this.al = f;
        OProfiler.a("push");
        List list1 = this.bi.b((OEntity) this, this.bw.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

        if (list1 != null && list1.size() > 0) {
            for (int j = 0; j < list1.size(); ++j) {
                OEntity oentity = (OEntity) list1.get(j);

                if (oentity.e_()) {
                    oentity.k(this);
                }
            }
        }

        OProfiler.a();
    }

    protected boolean c_() {
        return false;
    }

    protected boolean aF() {
        return !this.bi.F;
    }

    protected boolean Q() {
        return this.ap <= 0;
    }

    public boolean P() {
        return false;
    }

    protected void ac() {
        this.bq = 0.41999998688697815D;
        if (this.a(OPotion.j)) {
            this.bq += (double) ((float) (this.b(OPotion.j).c() + 1) * 0.1F);
        }

        if (this.aZ()) {
            float f = this.bs * 0.017453292F;

            this.bp -= (double) (OMathHelper.a(f) * 0.2F);
            this.br += (double) (OMathHelper.b(f) * 0.2F);
        }

        this.ce = true;
    }

    protected boolean n() {
        return true;
    }

    protected void aG() {
        OEntityPlayer oentityplayer = this.bi.a(this, -1.0D);

        if (oentityplayer != null) {
            double d0 = oentityplayer.bm - this.bm;
            double d1 = oentityplayer.bn - this.bn;
            double d2 = oentityplayer.bo - this.bo;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;

            if (this.n() && d3 > 16384.0D) {
                // CanaryMod hook onEntityDespawn
                if (!(Boolean) OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
                    this.X();
                }
            }

            if (this.aV > 600 && this.bS.nextInt(800) == 0 && d3 > 1024.0D && this.n()) {
                // CanaryMod hook onEntityDespawn
                if (!(Boolean) OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
                    this.X();
                } else {
                    this.aV = 0;
                }
            } else if (d3 < 1024.0D) {
                this.aV = 0;
            }
        }

    }

    protected void z_() {
        ++this.aV;
        OProfiler.a("checkDespawn");
        this.aG();
        OProfiler.a();
        OProfiler.a("sensing");
        this.m.a();
        OProfiler.a();
        OProfiler.a("targetSelector");
        this.aM.a();
        OProfiler.a();
        OProfiler.a("goalSelector");
        this.aL.a();
        OProfiler.a();
        OProfiler.a("navigation");
        this.k.d();
        OProfiler.a();
        OProfiler.a("mob tick");
        this.g();
        OProfiler.a();
        OProfiler.a("controls");
        this.h.c();
        this.g.a();
        this.i.b();
        OProfiler.a();
    }

    protected void g() {}

    protected void d_() {
        ++this.aV;
        this.aG();
        this.aW = 0.0F;
        this.aX = 0.0F;
        float f = 8.0F;

        if (this.bS.nextFloat() < 0.02F) {
            OEntityPlayer oentityplayer = this.bi.a(this, (double) f);

            if (oentityplayer != null) {
                this.r = oentityplayer;
                this.bc = 10 + this.bS.nextInt(20);
            } else {
                this.aY = (this.bS.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.r != null) {
            this.a(this.r, 10.0F, (float) this.D());
            if (this.bc-- <= 0 || this.r.bE || this.r.j(this) > (double) (f * f)) {
                this.r = null;
            }
        } else {
            if (this.bS.nextFloat() < 0.05F) {
                this.aY = (this.bS.nextFloat() - 0.5F) * 20.0F;
            }

            this.bs += this.aY;
            this.bt = this.ba;
        }

        boolean flag = this.aU();
        boolean flag1 = this.aV();

        if (flag || flag1) {
            this.aZ = this.bS.nextFloat() < 0.8F;
        }

    }

    public int D() {
        return 40;
    }

    public void a(OEntity oentity, float f, float f1) {
        double d0 = oentity.bm - this.bm;
        double d1 = oentity.bo - this.bo;
        double d2;

        if (oentity instanceof OEntityLiving) {
            OEntityLiving oentityliving = (OEntityLiving) oentity;

            d2 = this.bn + (double) this.B() - (oentityliving.bn + (double) oentityliving.B());
        } else {
            d2 = (oentity.bw.b + oentity.bw.e) / 2.0D - (this.bn + (double) this.B());
        }

        double d3 = (double) OMathHelper.a(d0 * d0 + d1 * d1);
        float f2 = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
        float f3 = (float) (-(Math.atan2(d2, d3) * 180.0D / 3.1415927410125732D));

        this.bt = -this.b(this.bt, f3, f1);
        this.bs = this.b(this.bs, f2, f);
    }

    private float b(float f, float f1, float f2) {
        float f3;

        for (f3 = f1 - f; f3 < -180.0F; f3 += 360.0F) {
            ;
        }

        while (f3 >= 180.0F) {
            f3 -= 360.0F;
        }

        if (f3 > f2) {
            f3 = f2;
        }

        if (f3 < -f2) {
            f3 = -f2;
        }

        return f + f3;
    }

    public void aH() {
        LivingEntity entity = new LivingEntity(this);

        manager.callHook(PluginLoader.Hook.DEATH, entity);

    }

    public boolean l() {
        return this.bi.a(this.bw) && this.bi.a((OEntity) this, this.bw).size() == 0 && !this.bi.c(this.bw);
    }

    protected void aI() {
        this.a(ODamageSource.j, 4);
    }

    public OVec3D aJ() {
        return this.f(1.0F);
    }

    public OVec3D f(float f) {
        float f1;
        float f2;
        float f3;
        float f4;

        if (f == 1.0F) {
            f1 = OMathHelper.b(-this.bs * 0.017453292F - 3.1415927F);
            f2 = OMathHelper.a(-this.bs * 0.017453292F - 3.1415927F);
            f3 = -OMathHelper.b(-this.bt * 0.017453292F);
            f4 = OMathHelper.a(-this.bt * 0.017453292F);
            return OVec3D.b((double) (f2 * f3), (double) f4, (double) (f1 * f3));
        } else {
            f1 = this.bv + (this.bt - this.bv) * f;
            f2 = this.bu + (this.bs - this.bu) * f;
            f3 = OMathHelper.b(-f2 * 0.017453292F - 3.1415927F);
            f4 = OMathHelper.a(-f2 * 0.017453292F - 3.1415927F);
            float f5 = -OMathHelper.b(-f1 * 0.017453292F);
            float f6 = OMathHelper.a(-f1 * 0.017453292F);

            return OVec3D.b((double) (f4 * f5), (double) f6, (double) (f3 * f5));
        }
    }

    public int q() {
        return 4;
    }

    public boolean Z() {
        return false;
    }

    protected void aK() {
        Iterator iterator = this.aK.keySet().iterator();

        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            OPotionEffect opotioneffect = (OPotionEffect) this.aK.get(integer);

            if (!opotioneffect.a(this) && !this.bi.F) {
                iterator.remove();
                this.d(opotioneffect);
            }
        }

        int i;

        if (this.e) {
            if (!this.bi.F) {
                if (!this.aK.isEmpty()) {
                    i = OPotionHelper.a(this.aK.values());
                    this.bY.b(8, Integer.valueOf(i));
                } else {
                    this.bY.b(8, Integer.valueOf(0));
                }
            }

            this.e = false;
        }

        if (this.bS.nextBoolean()) {
            i = this.bY.c(8);
            if (i > 0) {
                double d0 = (double) (i >> 16 & 255) / 255.0D;
                double d1 = (double) (i >> 8 & 255) / 255.0D;
                double d2 = (double) (i >> 0 & 255) / 255.0D;

                this.bi.a("mobSpell", this.bm + (this.bS.nextDouble() - 0.5D) * (double) this.bG, this.bn + this.bS.nextDouble() * (double) this.bH - (double) this.bF, this.bo + (this.bS.nextDouble() - 0.5D) * (double) this.bG, d0, d1, d2);
            }
        }

    }

    public void aL() {
        Iterator iterator = this.aK.keySet().iterator();

        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            OPotionEffect opotioneffect = (OPotionEffect) this.aK.get(integer);

            if (!this.bi.F) {
                iterator.remove();
                this.d(opotioneffect);
            }
        }

    }

    public Collection aM() {
        return this.aK.values();
    }

    public boolean a(OPotion opotion) {
        return this.aK.containsKey(Integer.valueOf(opotion.H));
    }

    public OPotionEffect b(OPotion opotion) {
        return (OPotionEffect) this.aK.get(Integer.valueOf(opotion.H));
    }

    public void e(OPotionEffect opotioneffect) {
        // CanaryMod - POTION_EFFECT HOOK
        PotionEffect pe = (PotionEffect) etc.getLoader().callHook(PluginLoader.Hook.POTION_EFFECT, this.entity, opotioneffect.potionEffect);

        if (pe == null) {
            return;
        }
        opotioneffect = pe.potionEffect;
        if (this.a(opotioneffect)) {
            if (this.aK.containsKey(Integer.valueOf(opotioneffect.a()))) {
                ((OPotionEffect) this.aK.get(Integer.valueOf(opotioneffect.a()))).a(opotioneffect);
                this.c((OPotionEffect) this.aK.get(Integer.valueOf(opotioneffect.a())));
            } else {
                this.aK.put(Integer.valueOf(opotioneffect.a()), opotioneffect);
                this.b(opotioneffect);
            }

        }
    }

    public boolean a(OPotionEffect opotioneffect) {
        if (this.v() == OEnumCreatureAttribute.b) {
            int i = opotioneffect.a();

            if (i == OPotion.l.H || i == OPotion.u.H) {
                return false;
            }
        }

        return true;
    }

    public boolean aN() {
        return this.v() == OEnumCreatureAttribute.b;
    }

    protected void b(OPotionEffect opotioneffect) {
        this.e = true;
    }

    protected void c(OPotionEffect opotioneffect) {
        this.e = true;
    }

    protected void d(OPotionEffect opotioneffect) {
        this.e = true;
    }

    protected float J() {
        float f = 1.0F;

        if (this.a(OPotion.c)) {
            f *= 1.0F + 0.2F * (float) (this.b(OPotion.c).c() + 1);
        }

        if (this.a(OPotion.d)) {
            f *= 1.0F - 0.15F * (float) (this.b(OPotion.d).c() + 1);
        }

        return f;
    }

    public void a_(double d0, double d1, double d2) {
        this.c(d0, d1, d2, this.bs, this.bt);
    }

    public boolean aO() {
        return false;
    }

    public OEnumCreatureAttribute v() {
        return OEnumCreatureAttribute.a;
    }

    public void c(OItemStack oitemstack) {
        this.bi.a(this, "random.break", 0.8F, 0.8F + this.bi.r.nextFloat() * 0.4F);

        for (int i = 0; i < 5; ++i) {
            OVec3D ovec3d = OVec3D.b(((double) this.bS.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

            ovec3d.a(-this.bt * 3.1415927F / 180.0F);
            ovec3d.b(-this.bs * 3.1415927F / 180.0F);
            OVec3D ovec3d1 = OVec3D.b(((double) this.bS.nextFloat() - 0.5D) * 0.3D, (double) (-this.bS.nextFloat()) * 0.6D - 0.3D, 0.6D);

            ovec3d1.a(-this.bt * 3.1415927F / 180.0F);
            ovec3d1.b(-this.bs * 3.1415927F / 180.0F);
            ovec3d1 = ovec3d1.c(this.bm, this.bn + (double) this.B(), this.bo);
            this.bi.a("iconcrack_" + oitemstack.a().bP, ovec3d1.a, ovec3d1.b, ovec3d1.c, ovec3d.a, ovec3d.b + 0.05D, ovec3d.c);
        }

    }
}
