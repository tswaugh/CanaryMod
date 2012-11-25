import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class OEntityLiving extends OEntity {

    private static final float[] b = new float[] { 0.0F, 0.0F, 0.005F, 0.01F};
    private static final float[] c = new float[] { 0.0F, 0.0F, 0.05F, 0.1F};
    private static final float[] d = new float[] { 0.0F, 0.0F, 0.005F, 0.02F};
    public static final float[] as = new float[] { 0.0F, 0.01F, 0.07F, 0.2F};
    public int at = 20;
    public float au;
    public float av;
    public float aw = 0.0F;
    public float ax = 0.0F;
    public float ay = 0.0F;
    public float az = 0.0F;
    protected float aA;
    protected float aB;
    protected float aC;
    protected float aD;
    protected boolean aE = true;
    protected String aF = "/mob/char.png";
    protected boolean aG = true;
    protected float aH = 0.0F;
    protected String aI = null;
    protected float aJ = 1.0F;
    protected int aK = 0;
    protected float aL = 0.0F;
    public float aM = 0.1F;
    public float aN = 0.02F;
    public float aO;
    public float aP;
    protected int aQ = this.aT(); //CanaryMod: health variable
    public int aR;
    protected int aS;
    public int aT;
    public int aU;
    public int aV;
    public float aW = 0.0F;
    public int aX = 0;
    public int aY = 0;
    public float aZ;
    public float ba;
    protected boolean bb = false;
    protected int bc;
    public int bd = -1;
    public float be = (float) (Math.random() * 0.8999999761581421D + 0.10000000149011612D);
    public float bf;
    public float bg;
    public float bh;
    protected OEntityPlayer bi = null;
    protected int bj = 0;
    private OEntityLiving e = null;
    private int f = 0;
    private OEntityLiving g = null;
    public int bk = 0;
    protected HashMap bl = new HashMap();
    private boolean h = true;
    private int i;
    private OEntityLookHelper j;
    private OEntityMoveHelper bI;
    private OEntityJumpHelper bJ;
    private OEntityBodyHelper bK;
    private OPathNavigate bL;
    protected final OEntityAITasks bm;
    protected final OEntityAITasks bn;
    private OEntityLiving bM;
    private OEntitySenses bN;
    private float bO;
    private OChunkCoordinates bP = new OChunkCoordinates(0, 0, 0);
    private float bQ = -1.0F;
    private OItemStack[] bR = new OItemStack[5];
    protected float[] bo = new float[5];
    private OItemStack[] bS = new OItemStack[5];
    public boolean bp = false;
    public int bq = 0;
    protected boolean br = false;
    protected boolean bT = false; //CanaryMod: private -> protected
    protected int bs;
    protected double bt;
    protected double bu;
    protected double bv;
    protected double bw;
    protected double bx;
    float by = 0.0F;
    protected int bz = 0;
    protected int bA = 0;
    protected float bB;
    protected float bC;
    protected float bD;
    protected boolean bE = false;
    protected float bF = 0.0F;
    protected float bG = 0.7F;
    private int bU = 0;
    private OEntity bV;
    protected int bH = 0;

    // CanaryMod Start
    LivingEntity entity = new LivingEntity(this);
    protected MobSpawner spawner = null;
    public int maxHealth = 0;
    // CanaryMod end

    public OEntityLiving(OWorld oworld) {
        super(oworld);
        this.m = true;
        this.bm = new OEntityAITasks(oworld != null && oworld.E != null ? oworld.E : null);
        this.bn = new OEntityAITasks(oworld != null && oworld.E != null ? oworld.E : null);
        this.j = new OEntityLookHelper(this);
        this.bI = new OEntityMoveHelper(this);
        this.bJ = new OEntityJumpHelper(this);
        this.bK = new OEntityBodyHelper(this);
        this.bL = new OPathNavigate(this, oworld, 16.0F);
        this.bN = new OEntitySenses(this);
        this.av = (float) (Math.random() + 1.0D) * 0.01F;
        this.b(this.t, this.u, this.v);
        this.au = (float) Math.random() * 12398.0F;
        this.z = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.ay = this.z;

        for (int i = 0; i < this.bo.length; ++i) {
            this.bo[i] = 0.05F;
        }

        this.X = 0.5F;
    }

    public OEntityLookHelper aw() {
        return this.j;
    }

    public OEntityMoveHelper ax() {
        return this.bI;
    }

    public OEntityJumpHelper ay() {
        return this.bJ;
    }

    public OPathNavigate az() {
        return this.bL;
    }

    public OEntitySenses aA() {
        return this.bN;
    }

    public Random aB() {
        return this.aa;
    }

    public OEntityLiving aC() {
        return this.e;
    }

    public OEntityLiving aD() {
        return this.g;
    }

    public void l(OEntity oentity) {
        if (oentity instanceof OEntityLiving) {
            this.g = (OEntityLiving) oentity;
        }
    }

    public int aE() {
        return this.bA;
    }

    public float ap() {
        return this.ay;
    }

    public float aF() {
        return this.bO;
    }

    public void e(float f) {
        this.bO = f;
        this.f(f);
    }

    public boolean m(OEntity oentity) {
        this.l(oentity);
        return false;
    }

    public OEntityLiving aG() {
        return this.bM;
    }

    public void b(OEntityLiving oentityliving) {
        // CanaryMod start: MOB_TARGET hook
        if ((Boolean) manager.callHook(PluginLoader.Hook.MOB_TARGET, oentityliving.getEntity(), this.getEntity())) {
            return;
        } // CanaryMod end
        this.bM = oentityliving;
    }

    public boolean a(Class oclass) {
        return OEntityCreeper.class != oclass && OEntityGhast.class != oclass;
    }

    public void aH() {}

    protected void a(double d0, boolean flag) {
        if (!this.H()) {
            this.I();
        }

        if (flag && this.S > 0.0F) {
            int i = OMathHelper.c(this.t);
            int j = OMathHelper.c(this.u - 0.20000000298023224D - (double) this.M);
            int k = OMathHelper.c(this.v);
            int l = this.p.a(i, j, k);

            if (l == 0) {
                int i1 = this.p.e(i, j - 1, k);

                if (i1 == 11 || i1 == 32 || i1 == 21) {
                    l = this.p.a(i, j - 1, k);
                }
            }

            if (l > 0) {
                OBlock.p[l].a(this.p, i, j, k, this, this.S);
            }
        }

        super.a(d0, flag);
    }

    public boolean aI() {
        return this.e(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
    }

    public boolean e(int i, int j, int k) {
        return this.bQ == -1.0F ? true : this.bP.e(i, j, k) < this.bQ * this.bQ;
    }

    public void b(int i, int j, int k, int l) {
        this.bP.b(i, j, k);
        this.bQ = (float) l;
    }

    public OChunkCoordinates aJ() {
        return this.bP;
    }

    public float aK() {
        return this.bQ;
    }

    public void aL() {
        this.bQ = -1.0F;
    }

    public boolean aM() {
        return this.bQ != -1.0F;
    }

    public void c(OEntityLiving oentityliving) {
        this.e = oentityliving;
        this.f = this.e != null ? 60 : 0;
    }

    protected void a() {
        this.ag.a(8, Integer.valueOf(this.i));
        this.ag.a(9, Byte.valueOf((byte) 0));
        this.ag.a(10, Byte.valueOf((byte) 0));
    }

    public boolean n(OEntity oentity) {
        return this.p.a(this.p.S().a(this.t, this.u + (double) this.e(), this.v), this.p.S().a(oentity.t, oentity.u + (double) oentity.e(), oentity.v)) == null;
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

    public int aN() {
        return 80;
    }

    public void aO() {
        String s = this.aY();

        if (s != null) {
            this.a(s, this.aX(), this.aV());
        }
    }

    public void y() {
        this.aO = this.aP;
        super.y();
        this.p.E.a("mobBaseTick");
        if (this.S() && this.aa.nextInt(1000) < this.aT++) {
            this.aT = -this.aN();
            this.aO();
        }

        if (this.S() && this.T()) {
            // CanaryMod Damage hook: Suffocation
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.SUFFOCATION, null, this.entity, 1)) {
                this.a(ODamageSource.d, 1);
            }
        }

        if (this.F() || this.p.J) {
            this.B();
        }

        if (this.S() && this.a(OMaterial.h) && !this.bc() && !this.bl.containsKey(Integer.valueOf(OPotion.o.H))) {
            this.f(this.g(this.al()));
            if (this.al() == -20) {
                this.f(0);

                // CanaryMod Damage hook: Drowning
                if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.WATER, null, entity, 2)) {
                    for (int i = 0; i < 8; ++i) {
                        float f = this.aa.nextFloat() - this.aa.nextFloat();
                        float f1 = this.aa.nextFloat() - this.aa.nextFloat();
                        float f2 = this.aa.nextFloat() - this.aa.nextFloat();

                        this.p.a("bubble", this.t + (double) f, this.u + (double) f1, this.v + (double) f2, this.w, this.x, this.y);
                    }

                    this.a(ODamageSource.e, 2);
                }
            }

            this.B();
        } else {
            this.f(300);
        }

        this.aZ = this.ba;
        if (this.aY > 0) {
            --this.aY;
        }

        if (this.aU > 0) {
            --this.aU;
        }

        if (this.ae > 0) {
            --this.ae;
        }

        if (this.aQ <= 0) {
            this.aP();
        }

        if (this.bj > 0) {
            --this.bj;
        } else {
            this.bi = null;
        }

        if (this.g != null && !this.g.S()) {
            this.g = null;
        }

        if (this.e != null) {
            if (!this.e.S()) {
                this.c((OEntityLiving) null);
            } else if (this.f > 0) {
                --this.f;
            } else {
                this.c((OEntityLiving) null);
            }
        }

        this.bx();
        this.aD = this.aC;
        this.ax = this.aw;
        this.az = this.ay;
        this.B = this.z;
        this.C = this.A;
        this.p.E.b();
    }

    protected void aP() {
        ++this.aX;
        if (this.aX == 20) {
            int i;

            if (!this.p.J && (this.bj > 0 || this.aQ()) && !this.h_()) {
                i = this.c(this.bi);

                while (i > 0) {
                    int j = OEntityXPOrb.a(i);

                    i -= j;
                    this.p.d((OEntity) (new OEntityXPOrb(this.p, this.t, this.u, this.v, j)));
                }
            }

            this.x();

            for (i = 0; i < 20; ++i) {
                double d0 = this.aa.nextGaussian() * 0.02D;
                double d1 = this.aa.nextGaussian() * 0.02D;
                double d2 = this.aa.nextGaussian() * 0.02D;

                this.p.a("explode", this.t + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N, this.u + (double) (this.aa.nextFloat() * this.O), this.v + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N, d0, d1, d2);
            }
        }
    }

    protected int g(int i) {
        int j = OEnchantmentHelper.b(this);

        return j > 0 && this.aa.nextInt(j + 1) > 0 ? i : i - 1;
    }

    protected int c(OEntityPlayer oentityplayer) {
        return this.bc;
    }

    protected boolean aQ() {
        return false;
    }

    public void aR() {
        for (int i = 0; i < 20; ++i) {
            double d0 = this.aa.nextGaussian() * 0.02D;
            double d1 = this.aa.nextGaussian() * 0.02D;
            double d2 = this.aa.nextGaussian() * 0.02D;
            double d3 = 10.0D;

            this.p.a("explode", this.t + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N - d0 * d3, this.u + (double) (this.aa.nextFloat() * this.O) - d1 * d3, this.v + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N - d2 * d3, d0, d1, d2);
        }
    }

    public void U() {
        super.U();
        this.aA = this.aB;
        this.aB = 0.0F;
        this.S = 0.0F;
    }

    public void j_() {
        super.j_();
        if (!this.p.J) {
            int i;

            for (i = 0; i < 5; ++i) {
                OItemStack oitemstack = this.p(i);

                if (!OItemStack.b(oitemstack, this.bS[i])) {
                    ((OWorldServer) this.p).p().a((OEntity) this, (OPacket) (new OPacket5PlayerInventory(this.k, i, oitemstack)));
                    this.bS[i] = oitemstack == null ? null : oitemstack.l();
                }
            }

            i = this.bJ();
            if (i > 0) {
                if (this.bk <= 0) {
                    this.bk = 20 * (30 - i);
                }

                --this.bk;
                if (this.bk <= 0) {
                    this.r(i - 1);
                }
            }
        }

        this.c();
        double d0 = this.t - this.q;
        double d1 = this.v - this.s;
        float f = (float) (d0 * d0 + d1 * d1);
        float f1 = this.aw;
        float f2 = 0.0F;

        this.aA = this.aB;
        float f3 = 0.0F;

        if (f > 0.0025000002F) {
            f3 = 1.0F;
            f2 = (float) Math.sqrt((double) f) * 3.0F;
            f1 = (float) Math.atan2(d1, d0) * 180.0F / 3.1415927F - 90.0F;
        }

        if (this.aP > 0.0F) {
            f1 = this.z;
        }

        if (!this.E) {
            f3 = 0.0F;
        }

        this.aB += (f3 - this.aB) * 0.3F;
        this.p.E.a("headTurn");
        if (this.be()) {
            this.bK.a();
        } else {
            float f4 = OMathHelper.g(f1 - this.aw);

            this.aw += f4 * 0.3F;
            float f5 = OMathHelper.g(this.z - this.aw);
            boolean flag = f5 < -90.0F || f5 >= 90.0F;

            if (f5 < -75.0F) {
                f5 = -75.0F;
            }

            if (f5 >= 75.0F) {
                f5 = 75.0F;
            }

            this.aw = this.z - f5;
            if (f5 * f5 > 2500.0F) {
                this.aw += f5 * 0.2F;
            }

            if (flag) {
                f2 *= -1.0F;
            }
        }

        this.p.E.b();
        this.p.E.a("rangeChecks");

        while (this.z - this.B < -180.0F) {
            this.B -= 360.0F;
        }

        while (this.z - this.B >= 180.0F) {
            this.B += 360.0F;
        }

        while (this.aw - this.ax < -180.0F) {
            this.ax -= 360.0F;
        }

        while (this.aw - this.ax >= 180.0F) {
            this.ax += 360.0F;
        }

        while (this.A - this.C < -180.0F) {
            this.C -= 360.0F;
        }

        while (this.A - this.C >= 180.0F) {
            this.C += 360.0F;
        }

        while (this.ay - this.az < -180.0F) {
            this.az -= 360.0F;
        }

        while (this.ay - this.az >= 180.0F) {
            this.az += 360.0F;
        }

        this.p.E.b();
        this.aC += f2;
    }

    public void i(int i) {
        if (this.aQ > 0) {
            this.aQ += i;
            if (this.aQ > this.aT()) {
                this.aQ = this.aT();
            }

            this.ae = this.at / 2;
        }
    }

    public abstract int aT();

    public int aU() {
        return this.aQ;
    }

    public void j(int i) {
        this.aQ = i;
        if (i > this.aT()) {
            i = this.aT();
        }
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.ar()) {
            return false;
        } else if (this.p.J) {
            return false;
        } else {
            this.bA = 0;
            if (this.aQ <= 0) {
                return false;
            } else if (odamagesource.k() && this.a(OPotion.n)) {
                return false;
            } else {
                if ((odamagesource == ODamageSource.o || odamagesource == ODamageSource.p) && this.p(4) != null) {
                    i = (int) ((float) i * 0.55F);
                }

                this.bg = 1.5F;

                LivingEntity attacker = null;

                if ((odamagesource != null) && odamagesource instanceof OEntityDamageSource && ((OEntityDamageSource)odamagesource).g() instanceof OEntityLiving) {
                    OEntityLiving ent = (OEntityLiving) ((OEntityDamageSource)odamagesource).g();
                    attacker = ent.getEntity();
                }
                if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.ATTACK, attacker, this.entity, i)) {
                    if (this instanceof OEntityCreature) {
                        ((OEntityCreature) this).c = 0;
                    }
                    return false;
                }
                boolean flag = true;

                if ((float) this.ae > (float) this.at / 2.0F) {
                    if (i <= this.bz) {
                        return false;
                    }

                    if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, this.entity, i - this.bz)) {
                        return false;
                    }
                    this.d(odamagesource, i - this.bz);
                    this.bz = i;
                    flag = false;
                } else {
                    if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, this.entity, i)) {
                        return false;
                    }
                    this.bz = i;
                    this.aR = this.aQ;
                    this.ae = this.at;
                    this.d(odamagesource, i);
                    this.aU = this.aV = 10;
                }

                this.aW = 0.0F;
                OEntity oentity = odamagesource.g();

                if (oentity != null) {
                    if (oentity instanceof OEntityLiving) {
                        this.c((OEntityLiving) oentity);
                    }

                    if (oentity instanceof OEntityPlayer) {
                        this.bj = 60;
                        this.bi = (OEntityPlayer) oentity;
                    } else if (oentity instanceof OEntityWolf) {
                        OEntityWolf oentitywolf = (OEntityWolf) oentity;

                        if (oentitywolf.m()) {
                            this.bj = 60;
                            this.bi = null;
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

                        this.aW = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - this.z;
                        this.a(oentity, i, d0, d1);
                    } else {
                        this.aW = (float) ((int) (Math.random() * 2.0D) * 180);
                    }
                }

                if (this.aQ <= 0) {
                    if (flag) {
                        this.a(this.ba(), this.aX(), this.aV());
                    }

                    this.a(odamagesource);
                } else if (flag) {
                    this.a(this.aZ(), this.aX(), this.aV());
                }

                return true;
            }
        }
    }

    protected float aV() {
        return this.h_() ? (this.aa.nextFloat() - this.aa.nextFloat()) * 0.2F + 1.5F : (this.aa.nextFloat() - this.aa.nextFloat()) * 0.2F + 1.0F;
    }

    public int aW() {
        int i = 0;
        OItemStack[] aoitemstack = this.ae();
        int j = aoitemstack.length;

        for (int k = 0; k < j; ++k) {
            OItemStack oitemstack = aoitemstack[k];

            if (oitemstack != null && oitemstack.b() instanceof OItemArmor) {
                int l = ((OItemArmor) oitemstack.b()).b;

                i += l;
            }
        }

        return i;
    }

    protected void k(int i) {}

    protected int b(ODamageSource odamagesource, int i) {
        if (!odamagesource.c()) {
            int j = 25 - this.aW();
            int k = i * j + this.aS;

            this.k(i);
            i = k / 25;
            this.aS = k % 25;
        }

        return i;
    }

    protected int c(ODamageSource odamagesource, int i) {
        if (this.a(OPotion.m)) {
            int j = (this.b(OPotion.m).c() + 1) * 5;
            int k = 25 - j;
            int l = i * k + this.aS;

            i = l / 25;
            this.aS = l % 25;
        }

        return i;
    }

    protected void d(ODamageSource odamagesource, int i) {
        if (!this.ar()) {
            i = this.b(odamagesource, i);
            i = this.c(odamagesource, i);
            this.aQ -= i;
        }
    }

    protected float aX() {
        return 1.0F;
    }

    protected String aY() {
        return null;
    }

    protected String aZ() {
        return "damage.hit";
    }

    protected String ba() {
        return "damage.hit";
    }

    public void a(OEntity oentity, int i, double d0, double d1) {
        this.am = true;
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
        // CanaryMod: call DEATH hook
        manager.callHook(PluginLoader.Hook.DEATH, this.getEntity());

        OEntity oentity = odamagesource.g();

        if (this.aK >= 0 && oentity != null) {
            oentity.c(this, this.aK);
        }

        if (oentity != null) {
            oentity.a(this);
        }

        this.bb = true;
        if (!this.p.J) {
            int i = 0;

            if (oentity instanceof OEntityPlayer) {
                i = OEnchantmentHelper.g((OEntityLiving) oentity);
            }

            if (!this.h_() && this.p.L().b("doMobLoot")) {
                this.a(this.bj > 0, i);
                this.b(this.bj > 0, i);
                if (this.bj > 0) {
                    int j = this.aa.nextInt(200) - i;

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
        int j = this.bb();

        if (j > 0) {
            int k = this.aa.nextInt(3);

            if (i > 0) {
                k += this.aa.nextInt(i + 1);
            }

            for (int l = 0; l < k; ++l) {
                this.b(j, 1);
            }
        }
    }

    protected int bb() {
        return 0;
    }

    protected void a(float f) {
        super.a(f);
        int i = OMathHelper.f(f - 3.0F);

        if (i > 0) {
            // CanaryMod Damage hook: Falling
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, entity, i)) {
                if (i > 4) {
                    this.a("damage.fallbig", 1.0F, 1.0F);
                } else {
                    this.a("damage.fallsmall", 1.0F, 1.0F);
                }

                this.a(ODamageSource.h, i);
            }
            int j = this.p.a(OMathHelper.c(this.t), OMathHelper.c(this.u - 0.20000000298023224D - (double) this.M), OMathHelper.c(this.v));

            if (j > 0) {
                OStepSound ostepsound = OBlock.p[j].cz;

                this.a(ostepsound.e(), ostepsound.c() * 0.5F, ostepsound.d() * 0.75F);
            }
        }
    }

    public void e(float f, float f1) {
        double d0;

        if (this.H() && (!(this instanceof OEntityPlayer) || !((OEntityPlayer) this).cc.b)) {
            d0 = this.u;
            this.a(f, f1, this.be() ? 0.04F : 0.02F);
            this.d(this.w, this.x, this.y);
            this.w *= 0.800000011920929D;
            this.x *= 0.800000011920929D;
            this.y *= 0.800000011920929D;
            this.x -= 0.02D;
            if (this.F && this.c(this.w, this.x + 0.6000000238418579D - this.u + d0, this.y)) {
                this.x = 0.30000001192092896D;
            }
        } else if (this.J() && (!(this instanceof OEntityPlayer) || !((OEntityPlayer) this).cc.b)) {
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
                    f2 = OBlock.p[i].cC * 0.91F;
                }
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            float f4;

            if (this.E) {
                if (this.be()) {
                    f4 = this.aF();
                } else {
                    f4 = this.aM;
                }

                f4 *= f3;
            } else {
                f4 = this.aN;
            }

            this.a(f, f1, f4);
            f2 = 0.91F;
            if (this.E) {
                f2 = 0.54600006F;
                int j = this.p.a(OMathHelper.c(this.t), OMathHelper.c(this.D.b) - 1, OMathHelper.c(this.v));

                if (j > 0) {
                    f2 = OBlock.p[j].cC * 0.91F;
                }
            }

            if (this.g_()) {
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

                this.S = 0.0F;
                if (this.x < -0.15D) {
                    this.x = -0.15D;
                }

                boolean flag = this.ah() && this instanceof OEntityPlayer;

                if (flag && this.x < 0.0D) {
                    this.x = 0.0D;
                }
            }

            this.d(this.w, this.x, this.y);
            if (this.F && this.g_()) {
                this.x = 0.2D;
            }

            if (this.p.J && (!this.p.f((int) this.t, 0, (int) this.v) || !this.p.d((int) this.t, (int) this.v).d)) {
                if (this.u > 0.0D) {
                    this.x = -0.1D;
                } else {
                    this.x = 0.0D;
                }
            } else {
                this.x -= 0.08D;
            }

            this.x *= 0.9800000190734863D;
            this.w *= (double) f2;
            this.y *= (double) f2;
        }

        this.bf = this.bg;
        d0 = this.t - this.q;
        double d1 = this.v - this.s;
        float f6 = OMathHelper.a(d0 * d0 + d1 * d1) * 4.0F;

        if (f6 > 1.0F) {
            f6 = 1.0F;
        }

        this.bg += (f6 - this.bg) * 0.4F;
        this.bh += this.bg;
    }

    public boolean g_() {
        int i = OMathHelper.c(this.t);
        int j = OMathHelper.c(this.D.b);
        int k = OMathHelper.c(this.v);
        int l = this.p.a(i, j, k);

        return l == OBlock.aI.cm || l == OBlock.bx.cm;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        if (this.aQ < -32768) {
            this.aQ = -32768;
        }

        onbttagcompound.a("Health", (short) this.aQ);
        onbttagcompound.a("HurtTime", (short) this.aU);
        onbttagcompound.a("DeathTime", (short) this.aX);
        onbttagcompound.a("AttackTime", (short) this.aY);
        onbttagcompound.a("CanPickUpLoot", this.br);
        onbttagcompound.a("PersistenceRequired", this.bT);
        ONBTTagList onbttaglist = new ONBTTagList();

        for (int i = 0; i < this.bR.length; ++i) {
            ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

            if (this.bR[i] != null) {
                this.bR[i].b(onbttagcompound1);
            }

                onbttaglist.a((ONBTBase) onbttagcompound1);
            }

        onbttagcompound.a("Equipment", (ONBTBase) onbttaglist);
        ONBTTagList onbttaglist1;

        if (!this.bl.isEmpty()) {
            onbttaglist1 = new ONBTTagList();
            Iterator iterator = this.bl.values().iterator();

            while (iterator.hasNext()) {
                OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

                onbttaglist1.a((ONBTBase) opotioneffect.a(new ONBTTagCompound()));
            }

            onbttagcompound.a("ActiveEffects", (ONBTBase) onbttaglist1);
        }

        onbttaglist1 = new ONBTTagList();

        for (int j = 0; j < this.bo.length; ++j) {
            onbttaglist1.a((ONBTBase) (new ONBTTagFloat(j + "", this.bo[j])));
        }

        onbttagcompound.a("DropChances", (ONBTBase) onbttaglist1);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.aQ = onbttagcompound.d("Health");
        if (!onbttagcompound.b("Health")) {
            this.aQ = this.aT();
        }

        this.aU = onbttagcompound.d("HurtTime");
        this.aX = onbttagcompound.d("DeathTime");
        this.aY = onbttagcompound.d("AttackTime");
        this.br = onbttagcompound.n("CanPickUpLoot");
        this.bT = onbttagcompound.n("PersistenceRequired");
        ONBTTagList onbttaglist;
        int i;

        if (onbttagcompound.b("Equipment")) {
            onbttaglist = onbttagcompound.m("Equipment");

            for (i = 0; i < this.bR.length; ++i) {
                this.bR[i] = OItemStack.a((ONBTTagCompound) onbttaglist.b(i));
            }
        }

        if (onbttagcompound.b("ActiveEffects")) {
            onbttaglist = onbttagcompound.m("ActiveEffects");

            for (i = 0; i < onbttaglist.c(); ++i) {
                ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
                OPotionEffect opotioneffect = OPotionEffect.b(onbttagcompound1);

                this.bl.put(Integer.valueOf(opotioneffect.a()), opotioneffect);
            }
        }

        if (onbttagcompound.b("DropChances")) {
            onbttaglist = onbttagcompound.m("DropChances");

            for (i = 0; i < onbttaglist.c(); ++i) {
                this.bo[i] = ((ONBTTagFloat) onbttaglist.b(i)).a;
            }
        }
    }

    public boolean S() {
        return !this.L && this.aQ > 0;
    }

    public boolean bc() {
        return false;
    }

    public void f(float f) {
        this.bC = f;
    }

    public void e(boolean flag) {
        this.bE = flag;
    }

    public void c() {
        if (this.bU > 0) {
            --this.bU;
        }

        if (this.bs > 0) {
            double d0 = this.t + (this.bt - this.t) / (double) this.bs;
            double d1 = this.u + (this.bu - this.u) / (double) this.bs;
            double d2 = this.v + (this.bv - this.v) / (double) this.bs;
            double d3 = OMathHelper.g(this.bw - (double) this.z);

            this.z = (float) ((double) this.z + d3 / (double) this.bs);
            this.A = (float) ((double) this.A + (this.bx - (double) this.A) / (double) this.bs);
            --this.bs;
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

        this.p.E.a("ai");
        if (this.bg()) {
            this.bE = false;
            this.bB = 0.0F;
            this.bC = 0.0F;
            this.bD = 0.0F;
        } else if (this.bf()) {
            if (this.be()) {
                this.p.E.a("newAi");
                this.bl();
                this.p.E.b();
            } else {
                this.p.E.a("oldAi");
                this.bn();
                this.p.E.b();
                this.ay = this.z;
            }
        }

        this.p.E.b();
        this.p.E.a("jump");
        if (this.bE) {
            if (!this.H() && !this.J()) {
                if (this.E && this.bU == 0) {
                    this.bi();
                    this.bU = 10;
                }
            } else {
                this.x += 0.03999999910593033D;
            }
        } else {
            this.bU = 0;
        }

        this.p.E.b();
        this.p.E.a("travel");
        this.bB *= 0.98F;
        this.bC *= 0.98F;
        this.bD *= 0.9F;
        float f = this.aM;

        this.aM *= this.bB();
        this.e(this.bB, this.bC);
        this.aM = f;
        this.p.E.b();
        this.p.E.a("push");
        if (!this.p.J) {
            this.bd();
        }

        this.p.E.b();
        this.p.E.a("looting");
        if (!this.p.J && this.br && !this.bb && this.p.L().b("mobGriefing")) {
            List list = this.p.a(OEntityItem.class, this.D.b(1.0D, 0.0D, 1.0D));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                OEntityItem oentityitem = (OEntityItem) iterator.next();

                if (!oentityitem.L && oentityitem.a != null) {
                    OItemStack oitemstack = oentityitem.a;
                    int i = b(oitemstack);

                    if (i > -1) {
                        boolean flag = true;
                        OItemStack oitemstack1 = this.p(i);

                        if (oitemstack1 != null) {
                            if (i == 0) {
                                if (oitemstack.b() instanceof OItemSword && !(oitemstack1.b() instanceof OItemSword)) {
                                    flag = true;
                                } else if (oitemstack.b() instanceof OItemSword && oitemstack1.b() instanceof OItemSword) {
                                    OItemSword oitemsword = (OItemSword) oitemstack.b();
                                    OItemSword oitemsword1 = (OItemSword) oitemstack1.b();

                                    if (oitemsword.g() == oitemsword1.g()) {
                                        flag = oitemstack.j() > oitemstack1.j() || oitemstack.o() && !oitemstack1.o();
                                    } else {
                                        flag = oitemsword.g() > oitemsword1.g();
                                    }
                                } else {
                                    flag = false;
                                }
                            } else if (oitemstack.b() instanceof OItemArmor && !(oitemstack1.b() instanceof OItemArmor)) {
                                flag = true;
                            } else if (oitemstack.b() instanceof OItemArmor && oitemstack1.b() instanceof OItemArmor) {
                                OItemArmor oitemarmor = (OItemArmor) oitemstack.b();
                                OItemArmor oitemarmor1 = (OItemArmor) oitemstack1.b();

                                if (oitemarmor.b == oitemarmor1.b) {
                                    flag = oitemstack.j() > oitemstack1.j() || oitemstack.o() && !oitemstack1.o();
                                } else {
                                    flag = oitemarmor.b > oitemarmor1.b;
                                }
                            } else {
                                flag = false;
                            }
                        }

                        if (flag) {
                            if (oitemstack1 != null && this.aa.nextFloat() - 0.1F < this.bo[i]) {
                                this.a(oitemstack1, 0.0F);
                            }

                            this.b(i, oitemstack);
                            this.bo[i] = 2.0F;
                            this.bT = true;
                            this.a((OEntity) oentityitem, 1);
                            oentityitem.x();
                        }
                    }
                }
            }
        }

        this.p.E.b();
    }

    protected void bd() {
        List list = this.p.b((OEntity) this, this.D.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                OEntity oentity = (OEntity) list.get(i);

                if (oentity.M()) {
                    this.o(oentity);
                }
            }
        }
    }

    protected void o(OEntity oentity) {
        oentity.f(this);
    }

    protected boolean be() {
        return false;
    }

    protected boolean bf() {
        return !this.p.J;
    }

    protected boolean bg() {
        return this.aQ <= 0;
    }

    public boolean bh() {
        return false;
    }

    protected void bi() {
        this.x = 0.41999998688697815D;
        if (this.a(OPotion.j)) {
            this.x += (double) ((float) (this.b(OPotion.j).c() + 1) * 0.1F);
        }

        if (this.ai()) {
            float f = this.z * 0.017453292F;

            this.w -= (double) (OMathHelper.a(f) * 0.2F);
            this.y += (double) (OMathHelper.b(f) * 0.2F);
        }

        this.am = true;
    }

    protected boolean bj() {
        return true;
    }

    protected void bk() {
        if (!this.bT) {
            OEntityPlayer oentityplayer = this.p.a(this, -1.0D);

            if (oentityplayer != null) {
                double d0 = oentityplayer.t - this.t;
                double d1 = oentityplayer.u - this.u;
                double d2 = oentityplayer.v - this.v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.bj() && d3 > 16384.0D) {
                    if (!(Boolean) OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
                        this.x();
                    }
                }

                if (this.bA > 600 && this.aa.nextInt(800) == 0 && d3 > 1024.0D && this.bj()) {
                    // CanaryMod hook onEntityDespawn
                    if (!(Boolean) OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
                        this.x();
                    } else {
                        this.bA = 0;
                    }
                } else if (d3 < 1024.0D) {
                    this.bA = 0;
                }
            }
        }
    }

    protected void bl() {
        ++this.bA;
        this.p.E.a("checkDespawn");
        this.bk();
        this.p.E.b();
        this.p.E.a("sensing");
        this.bN.a();
        this.p.E.b();
        this.p.E.a("targetSelector");
        this.bn.a();
        this.p.E.b();
        this.p.E.a("goalSelector");
        this.bm.a();
        this.p.E.b();
        this.p.E.a("navigation");
        this.bL.e();
        this.p.E.b();
        this.p.E.a("mob tick");
        this.bm();
        this.p.E.b();
        this.p.E.a("controls");
        this.p.E.a("move");
        this.bI.c();
        this.p.E.c("look");
        this.j.a();
        this.p.E.c("jump");
        this.bJ.b();
        this.p.E.b();
        this.p.E.b();
    }

    protected void bm() {}

    protected void bn() {
        ++this.bA;
        this.bk();
        this.bB = 0.0F;
        this.bC = 0.0F;
        float f = 8.0F;

        if (this.aa.nextFloat() < 0.02F) {
            OEntityPlayer oentityplayer = this.p.a(this, (double) f);

            if (oentityplayer != null) {
                this.bV = oentityplayer;
                this.bH = 10 + this.aa.nextInt(20);
            } else {
                this.bD = (this.aa.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.bV != null) {
            this.a(this.bV, 10.0F, (float) this.bp());
            if (this.bH-- <= 0 || this.bV.L || this.bV.e((OEntity) this) > (double) (f * f)) {
                this.bV = null;
            }
        } else {
            if (this.aa.nextFloat() < 0.05F) {
                this.bD = (this.aa.nextFloat() - 0.5F) * 20.0F;
            }

            this.z += this.bD;
            this.A = this.bF;
        }

        boolean flag = this.H();
        boolean flag1 = this.J();

        if (flag || flag1) {
            this.bE = this.aa.nextFloat() < 0.8F;
        }
    }

    protected void bo() {
        int i = this.h();

        if (this.bp) {
            ++this.bq;
            if (this.bq >= i) {
                this.bq = 0;
                this.bp = false;
            }
        } else {
            this.bq = 0;
        }

        this.aP = (float) this.bq / (float) i;
    }

    public int bp() {
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

    public boolean bs() {
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
            return this.p.S().a((double) (f2 * f3), (double) f4, (double) (f1 * f3));
        } else {
            f1 = this.C + (this.A - this.C) * f;
            f2 = this.B + (this.z - this.B) * f;
            f3 = OMathHelper.b(-f2 * 0.017453292F - 3.1415927F);
            f4 = OMathHelper.a(-f2 * 0.017453292F - 3.1415927F);
            float f5 = -OMathHelper.b(-f1 * 0.017453292F);
            float f6 = OMathHelper.a(-f1 * 0.017453292F);

            return this.p.S().a((double) (f4 * f5), (double) f6, (double) (f3 * f5));
        }
    }

    public int bv() {
        return 4;
    }

    public boolean bw() {
        return false;
    }

    protected void bx() {
        Iterator iterator = ((HashMap) this.bl.clone()).keySet().iterator(); //CanaryMod: hashmap is cloned to prevent concurrent modification exceptions

        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            OPotionEffect opotioneffect = (OPotionEffect) this.bl.get(integer);

            if (!opotioneffect.a(this)) {
                if (!this.p.J) {
                    this.bl.remove(integer); //CanaryMod: changed from interator.remove() to coincide with cloning
                    this.c(opotioneffect);
                }
            } else if (opotioneffect.b() % 600 == 0) {
                this.b(opotioneffect);
            }
        }

        int i;

        if (this.h) {
            if (!this.p.J) {
                if (this.bl.isEmpty()) {
                    this.ag.b(9, Byte.valueOf((byte) 0));
                    this.ag.b(8, Integer.valueOf(0));
                    this.c(false);
                } else {
                    i = OPotionHelper.a(this.bl.values());
                    this.ag.b(9, Byte.valueOf((byte) (OPotionHelper.b(this.bl.values()) ? 1 : 0)));
                    this.ag.b(8, Integer.valueOf(i));
                    this.c(this.m(OPotion.p.H));
                }
            }

            this.h = false;
        }

        i = this.ag.c(8);
        boolean flag = this.ag.a(9) > 0;

        if (i > 0) {
            boolean flag1 = false;

            if (!this.aj()) {
                flag1 = this.aa.nextBoolean();
            } else {
                flag1 = this.aa.nextInt(15) == 0;
            }

            if (flag) {
                flag1 &= this.aa.nextInt(5) == 0;
            }

            if (flag1 && i > 0) {
                double d0 = (double) (i >> 16 & 255) / 255.0D;
                double d1 = (double) (i >> 8 & 255) / 255.0D;
                double d2 = (double) (i >> 0 & 255) / 255.0D;

                this.p.a(flag ? "mobSpellAmbient" : "mobSpell", this.t + (this.aa.nextDouble() - 0.5D) * (double) this.N, this.u + this.aa.nextDouble() * (double) this.O - (double) this.M, this.v + (this.aa.nextDouble() - 0.5D) * (double) this.N, d0, d1, d2);
            }
        }
    }

    public void by() {
        Iterator iterator = this.bl.keySet().iterator();

        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            OPotionEffect opotioneffect = (OPotionEffect) this.bl.get(integer);

            if (!this.p.J) {
                iterator.remove();
                this.c(opotioneffect);
            }
        }
    }

    public Collection bz() {
        return this.bl.values();
    }

    public boolean m(int i) {
        return this.bl.containsKey(Integer.valueOf(i));
    }

    public boolean a(OPotion opotion) {
        return this.bl.containsKey(Integer.valueOf(opotion.H));
    }

    public OPotionEffect b(OPotion opotion) {
        return (OPotionEffect) this.bl.get(Integer.valueOf(opotion.H));
    }

    public void d(OPotionEffect opotioneffect) {
        // CanaryMod - POTION_EFFECT HOOK
        PotionEffect pe = (PotionEffect) etc.getLoader().callHook(PluginLoader.Hook.POTION_EFFECT, this.entity, opotioneffect.potionEffect);

        if (pe == null) {
            return;
        }
        opotioneffect = pe.potionEffect;
        if (this.e(opotioneffect)) {
            if (this.bl.containsKey(Integer.valueOf(opotioneffect.a()))) {
                ((OPotionEffect) this.bl.get(Integer.valueOf(opotioneffect.a()))).a(opotioneffect);
                this.b((OPotionEffect) this.bl.get(Integer.valueOf(opotioneffect.a())));
            } else {
                this.bl.put(Integer.valueOf(opotioneffect.a()), opotioneffect);
                this.a(opotioneffect);
            }
        }
    }

    public boolean e(OPotionEffect opotioneffect) {
        if (this.bC() == OEnumCreatureAttribute.b) {
            int i = opotioneffect.a();

            if (i == OPotion.l.H || i == OPotion.u.H) {
                return false;
            }
        }

        return true;
    }

    public boolean bA() {
        return this.bC() == OEnumCreatureAttribute.b;
    }

    public void o(int i) {
        OPotionEffect opotioneffect = (OPotionEffect) this.bl.remove(Integer.valueOf(i));

        if (opotioneffect != null) {
            this.c(opotioneffect);
        }
    }

    protected void a(OPotionEffect opotioneffect) {
        this.h = true;
    }

    protected void b(OPotionEffect opotioneffect) {
        this.h = true;
    }

    protected void c(OPotionEffect opotioneffect) {
        etc.getLoader().callHook(PluginLoader.Hook.POTION_EFFECTFINISHED, this.entity, opotioneffect.potionEffect);
        this.h = true;
    }

    public float bB() {
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

    public boolean h_() {
        return false;
    }

    public OEnumCreatureAttribute bC() {
        return OEnumCreatureAttribute.a;
    }

    public void a(OItemStack oitemstack) {
        this.a("random.break", 0.8F, 0.8F + this.p.u.nextFloat() * 0.4F);

        for (int i = 0; i < 5; ++i) {
            OVec3 ovec3 = this.p.S().a(((double) this.aa.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

            ovec3.a(-this.A * 3.1415927F / 180.0F);
            ovec3.b(-this.z * 3.1415927F / 180.0F);
            OVec3 ovec31 = this.p.S().a(((double) this.aa.nextFloat() - 0.5D) * 0.3D, (double) (-this.aa.nextFloat()) * 0.6D - 0.3D, 0.6D);

            ovec31.a(-this.A * 3.1415927F / 180.0F);
            ovec31.b(-this.z * 3.1415927F / 180.0F);
            ovec31 = ovec31.c(this.t, this.u + (double) this.e(), this.v);
            this.p.a("iconcrack_" + oitemstack.b().cg, ovec31.c, ovec31.d, ovec31.e, ovec3.c, ovec3.d + 0.05D, ovec3.e);
        }
    }

    public int as() {
        if (this.aG() == null) {
            return 3;
        } else {
            int i = (int) ((float) this.aQ - (float) this.aT() * 0.33F);

            i -= (3 - this.p.t) * 4;
            if (i < 0) {
                i = 0;
            }

            return i + 3;
        }
    }

    public OItemStack bD() {
        return this.bR[0];
    }

    public OItemStack p(int i) {
        return this.bR[i];
    }

    public OItemStack q(int i) {
        return this.bR[i + 1];
    }

    public void b(int i, OItemStack oitemstack) {
        this.bR[i] = oitemstack;
    }

    public OItemStack[] ae() {
        return this.bR;
    }

    protected void b(boolean flag, int i) {
        for (int j = 0; j < this.ae().length; ++j) {
            OItemStack oitemstack = this.p(j);
            boolean flag1 = this.bo[j] > 1.0F;

            if (oitemstack != null && (flag || flag1) && this.aa.nextFloat() - (float) i * 0.01F < this.bo[j]) {
                if (!flag1 && oitemstack.f()) {
                    int k = Math.max(oitemstack.k() - 25, 1);
                    int l = oitemstack.k() - this.aa.nextInt(this.aa.nextInt(k) + 1);

                    if (l > k) {
                        l = k;
                    }

                    if (l < 1) {
                        l = 1;
                    }

                    oitemstack.b(l);
                }

                this.a(oitemstack, 0.0F);
            }
        }
    }

    protected void bE() {
        if (this.aa.nextFloat() < d[this.p.t]) {
            int i = this.aa.nextInt(2);
            float f = this.p.t == 3 ? 0.1F : 0.25F;

            if (this.aa.nextFloat() < 0.07F) {
                ++i;
            }

            if (this.aa.nextFloat() < 0.07F) {
                ++i;
            }

            if (this.aa.nextFloat() < 0.07F) {
                ++i;
            }

            for (int j = 3; j >= 0; --j) {
                OItemStack oitemstack = this.q(j);

                if (j < 3 && this.aa.nextFloat() < f) {
                    break;
                }

                if (oitemstack == null) {
                    OItem oitem = a(j + 1, i);

                    if (oitem != null) {
                        this.b(j + 1, new OItemStack(oitem));
                    }
                }
            }
        }
    }

    public void a(OEntity oentity, int i) {
        if (!oentity.L && !this.p.J) {
            OEntityTracker oentitytracker = ((OWorldServer) this.p).p();

            if (oentity instanceof OEntityItem) {
                oentitytracker.a(oentity, (OPacket) (new OPacket22Collect(oentity.k, this.k)));
            }

            if (oentity instanceof OEntityArrow) {
                oentitytracker.a(oentity, (OPacket) (new OPacket22Collect(oentity.k, this.k)));
            }

            if (oentity instanceof OEntityXPOrb) {
                oentitytracker.a(oentity, (OPacket) (new OPacket22Collect(oentity.k, this.k)));
            }
        }
    }

    public static int b(OItemStack oitemstack) {
        if (oitemstack.c != OBlock.bd.cm && oitemstack.c != OItem.bQ.cg) {
            if (oitemstack.b() instanceof OItemArmor) {
                switch (((OItemArmor) oitemstack.b()).a) {
                    case 0:
                        return 4;

                    case 1:
                        return 3;

                    case 2:
                        return 2;

                    case 3:
                        return 1;
                }
            }

            return 0;
        } else {
            return 4;
        }
    }

    public static OItem a(int i, int j) {
        switch (i) {
            case 4:
                if (j == 0) {
                    return OItem.V;
                } else if (j == 1) {
                    return OItem.al;
                } else if (j == 2) {
                    return OItem.Z;
                } else if (j == 3) {
                    return OItem.ad;
                } else if (j == 4) {
                    return OItem.ah;
                }

            case 3:
                if (j == 0) {
                    return OItem.W;
                } else if (j == 1) {
                    return OItem.am;
                } else if (j == 2) {
                    return OItem.aa;
                } else if (j == 3) {
                    return OItem.ae;
                } else if (j == 4) {
                    return OItem.ai;
                }

            case 2:
                if (j == 0) {
                    return OItem.X;
                } else if (j == 1) {
                    return OItem.an;
                } else if (j == 2) {
                    return OItem.ab;
                } else if (j == 3) {
                    return OItem.af;
                } else if (j == 4) {
                    return OItem.aj;
                }

            case 1:
                if (j == 0) {
                    return OItem.Y;
                } else if (j == 1) {
                    return OItem.ao;
                } else if (j == 2) {
                    return OItem.ac;
                } else if (j == 3) {
                    return OItem.ag;
                } else if (j == 4) {
                    return OItem.ak;
                }

            default:
                return null;
        }
    }

    protected void bF() {
        if (this.bD() != null && this.aa.nextFloat() < b[this.p.t]) {
            OEnchantmentHelper.a(this.aa, this.bD(), 5);
        }

        for (int i = 0; i < 4; ++i) {
            OItemStack oitemstack = this.q(i);

            if (oitemstack != null && this.aa.nextFloat() < c[this.p.t]) {
                OEnchantmentHelper.a(this.aa, oitemstack, 5);
            }
        }
    }

    public void bG() {}

    private int h() {
        return this.a(OPotion.e) ? 6 - (1 + this.b(OPotion.e).c()) * 1 : (this.a(OPotion.f) ? 6 + (1 + this.b(OPotion.f).c()) * 2 : 6);
    }

    public void bH() {
        if (!this.bp || this.bq >= this.h() / 2 || this.bq < 0) {
            this.bq = -1;
            this.bp = true;
            if (this.p instanceof OWorldServer) {
                ((OWorldServer) this.p).p().a((OEntity) this, (OPacket) (new OPacket18Animation(this, 1)));
            }
        }
    }

    public boolean bI() {
        return false;
    }

    public final int bJ() {
        return this.ag.a(10);
    }

    public final void r(int i) {
        this.ag.b(10, Byte.valueOf((byte) i));
    }

    // CanaryMod start: add getEntity
    @Override
    public LivingEntity getEntity() {
        return entity;
    } // CanaryMod end
}
