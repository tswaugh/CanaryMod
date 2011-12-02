import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public abstract class OEntityLiving extends OEntity {

    public int S = 20;
    public float T;
    public float U;
    public float V = 0.0F;
    public float W = 0.0F;
    protected float X;
    protected float Y;
    protected float Z;
    protected float aa;
    protected boolean ab = true;
    protected String ac = "/mob/char.png";
    protected boolean ad = true;
    protected float ae = 0.0F;
    protected String af = null;
    protected float ag = 1.0F;
    protected int ah = 0;
    protected float ai = 0.0F;
    public boolean aj = false;
    public float ak = 0.1F;
    public float al = 0.02F;
    public float am;
    public float an;
    protected int ao = this.c();
    public int ap;
    protected int aq;
    private int a;
    public int ar;
    public int as;
    public float at = 0.0F;
    public int au = 0;
    public int av = 0;
    public float aw;
    public float ax;
    protected boolean ay = false;
    protected int az;
    public int aA = -1;
    public float aB = (float) (Math.random() * 0.8999999761581421D + 0.10000000149011612D);
    public float aC;
    public float aD;
    public float aE;
    protected OEntityPlayer aF = null;
    protected int aG = 0;
    public int aH = 0;
    public int aI = 0;
    protected HashMap aJ = new HashMap();
    private boolean b = true;
    private int c;
    protected int aK;
    protected double aL;
    protected double aM;
    protected double aN;
    protected double aO;
    protected double aP;
    float aQ = 0.0F;
    protected int aR = 0;
    protected int aS = 0;
    protected float aT;
    protected float aU;
    protected float aV;
    protected boolean aW = false;
    protected float aX = 0.0F;
    protected float aY = 0.7F;
    private int d = 0;
    private OEntity e;
    protected int aZ = 0;
    
    // CanaryMod Start
    LivingEntity entity = new LivingEntity(this);
    protected MobSpawner spawner = null;
    // CanaryMod end

    public OEntityLiving(OWorld var1) {
        super(var1);
        this.bc = true;
        this.U = (float) (Math.random() + 1.0D) * 0.01F;
        this.c(this.bj, this.bk, this.bl);
        this.T = (float) Math.random() * 12398.0F;
        this.bp = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.bM = 0.5F;
    }

    protected void b() {
        this.bV.a(8, Integer.valueOf(this.c));
    }

    public boolean g(OEntity var1) {
        return this.bf.a(OVec3D.b(this.bj, this.bk + (double) this.x(), this.bl), OVec3D.b(var1.bj, var1.bk + (double) var1.x(), var1.bl)) == null;
    }

    public boolean e_() {
        return !this.bB;
    }

    public boolean f_() {
        return !this.bB;
    }

    public float x() {
        return this.bE * 0.85F;
    }

    public int h() {
        return 80;
    }

    public void ae() {
        String var1 = this.c_();

        if (var1 != null) {
            this.bf.a(this, var1, this.o(), this.w());
        }

    }
    
    public void af() {
        this.am = this.an;
        super.af();
        OProfiler.a("mobBaseTick");
        if (this.bP.nextInt(1000) < this.a++) {
            this.a = -this.h();
            this.ae();
        }

        if (this.aj() && this.T()) {
            // CanaryMod Damage hook: Suffocation
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.SUFFOCATION, null, entity, 1)) {
                this.a(ODamageSource.d, 1);
            }
        }

        if (this.ax() || this.bf.I) {
            this.aw();
        }

        if (this.aj() && this.a(OMaterial.g) && !this.f() && !this.aJ.containsKey(Integer.valueOf(OPotion.o.H))) {
            this.l(this.f(this.aF()));
            if (this.aF() == -20) {
                this.l(0);

                for (int var1 = 0; var1 < 8; ++var1) {
                    float var2 = this.bP.nextFloat() - this.bP.nextFloat();
                    float var3 = this.bP.nextFloat() - this.bP.nextFloat();
                    float var4 = this.bP.nextFloat() - this.bP.nextFloat();

                    this.bf.a("bubble", this.bj + (double) var2, this.bk + (double) var3, this.bl + (double) var4, this.bm, this.bn, this.bo);
                }
                // CanaryMod Damage hook: Drowning
                if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.WATER, null, entity, 2)) {
                    this.a(ODamageSource.e, 2);
                }

            }

            this.aw();
        } else {
            this.l(300);
        }

        this.aw = this.ax;
        if (this.av > 0) {
            --this.av;
        }

        if (this.ar > 0) {
            --this.ar;
        }

        if (this.bT > 0) {
            --this.bT;
        }

        if (this.ao <= 0) {
            this.ag();
        }

        if (this.aG > 0) {
            --this.aG;
        } else {
            this.aF = null;
        }

        this.aq();
        this.aa = this.Z;
        this.W = this.V;
        this.br = this.bp;
        this.bs = this.bq;
        OProfiler.a();
    }

    protected void ag() {
        ++this.au;
        if (this.au == 20) {
            int var1;

            if (!this.bf.I && (this.aG > 0 || this.ac()) && !this.l()) {
                var1 = this.a(this.aF);

                while (var1 > 0) {
                    int var2 = OEntityXPOrb.b(var1);

                    var1 -= var2;
                    this.bf.b((OEntity) (new OEntityXPOrb(this.bf, this.bj, this.bk, this.bl, var2)));
                }
            }

            this.an();
            this.S();

            for (var1 = 0; var1 < 20; ++var1) {
                double var3 = this.bP.nextGaussian() * 0.02D;
                double var5 = this.bP.nextGaussian() * 0.02D;
                double var7 = this.bP.nextGaussian() * 0.02D;

                this.bf.a("explode", this.bj + (double) (this.bP.nextFloat() * this.bD * 2.0F) - (double) this.bD, this.bk + (double) (this.bP.nextFloat() * this.bE), this.bl + (double) (this.bP.nextFloat() * this.bD * 2.0F) - (double) this.bD, var3, var5, var7);
            }
        }

    }

    protected int f(int var1) {
        return var1 - 1;
    }

    protected int a(OEntityPlayer var1) {
        return this.az;
    }

    protected boolean ac() {
        return false;
    }

    public void ah() {
        for (int var1 = 0; var1 < 20; ++var1) {
            double var2 = this.bP.nextGaussian() * 0.02D;
            double var4 = this.bP.nextGaussian() * 0.02D;
            double var6 = this.bP.nextGaussian() * 0.02D;
            double var8 = 10.0D;

            this.bf.a("explode", this.bj + (double) (this.bP.nextFloat() * this.bD * 2.0F) - (double) this.bD - var2 * var8, this.bk + (double) (this.bP.nextFloat() * this.bE) - var4 * var8, this.bl + (double) (this.bP.nextFloat() * this.bD * 2.0F) - (double) this.bD - var6 * var8, var2, var4, var6);
        }

    }

    public void M() {
        super.M();
        this.X = this.Y;
        this.Y = 0.0F;
        this.bH = 0.0F;
    }

    public void w_() {
        super.w_();
        if (this.aH > 0) {
            if (this.aI <= 0) {
                this.aI = 60;
            }

            --this.aI;
            if (this.aI <= 0) {
                --this.aH;
            }
        }

        this.d();
        double var1 = this.bj - this.bg;
        double var3 = this.bl - this.bi;
        float var5 = OMathHelper.a(var1 * var1 + var3 * var3);
        float var6 = this.V;
        float var7 = 0.0F;

        this.X = this.Y;
        float var8 = 0.0F;

        if (var5 > 0.05F) {
            var8 = 1.0F;
            var7 = var5 * 3.0F;
            var6 = (float) Math.atan2(var3, var1) * 180.0F / 3.1415927F - 90.0F;
        }

        if (this.an > 0.0F) {
            var6 = this.bp;
        }

        if (!this.bu) {
            var8 = 0.0F;
        }

        this.Y += (var8 - this.Y) * 0.3F;

        float var9;

        for (var9 = var6 - this.V; var9 < -180.0F; var9 += 360.0F) {
            ;
        }

        while (var9 >= 180.0F) {
            var9 -= 360.0F;
        }

        this.V += var9 * 0.3F;

        float var10;

        for (var10 = this.bp - this.V; var10 < -180.0F; var10 += 360.0F) {
            ;
        }

        while (var10 >= 180.0F) {
            var10 -= 360.0F;
        }

        boolean var11 = var10 < -90.0F || var10 >= 90.0F;

        if (var10 < -75.0F) {
            var10 = -75.0F;
        }

        if (var10 >= 75.0F) {
            var10 = 75.0F;
        }

        this.V = this.bp - var10;
        if (var10 * var10 > 2500.0F) {
            this.V += var10 * 0.2F;
        }

        if (var11) {
            var7 *= -1.0F;
        }

        while (this.bp - this.br < -180.0F) {
            this.br -= 360.0F;
        }

        while (this.bp - this.br >= 180.0F) {
            this.br += 360.0F;
        }

        while (this.V - this.W < -180.0F) {
            this.W -= 360.0F;
        }

        while (this.V - this.W >= 180.0F) {
            this.W += 360.0F;
        }

        while (this.bq - this.bs < -180.0F) {
            this.bs -= 360.0F;
        }

        while (this.bq - this.bs >= 180.0F) {
            this.bs += 360.0F;
        }

        this.Z += var7;
    }

    protected void b(float var1, float var2) {
        super.b(var1, var2);
    }

    public void d(int var1) {
        if (this.ao > 0) {
            this.ao += var1;
            if (this.ao > this.c()) {
                this.ao = this.c();
            }

            this.bT = this.S / 2;
        }
    }

    public abstract int c();

    public int ai() {
        return this.ao;
    }

    public void i(int var1) {
        this.ao = var1;
        if (var1 > this.c()) {
            var1 = this.c();
        }

    }

    public boolean a(ODamageSource var1, int var2) {
        if (this.bf.I) {
            return false;
        } else {
            this.aS = 0;
            if (this.ao <= 0) {
                return false;
            } else if (var1.k() && this.a(OPotion.n)) {
                return false;
            } else {
                this.aD = 1.5F;
            
                // CanaryMod damage entities.
                LivingEntity attacker = null;

                if (var1 != null && var1 instanceof OEntityDamageSource && ((OEntityDamageSource) var1).a() instanceof OEntityLiving) {
                    OEntity o = ((OEntityDamageSource) var1).a();

                    attacker = new LivingEntity((OEntityLiving) o);
                }

                // CanaryMod attack by entity, but it might not do damage!
                if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.ATTACK, attacker, entity, var2)) {
                    if (this instanceof OEntityCreature) {
                        ((OEntityCreature) this).f = 0;
                    }
                    return false;
                }
                
                boolean var3 = true;

                if ((float) this.bT > (float) this.S / 2.0F) {
                    if (var2 <= this.aR) {
                        return false;
                    }
               
                    // CanaryMod: partial damage
                    if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, var2 - bT)) {
                        return false;
                    }

                    this.c(var1, var2 - this.aR);
                    this.aR = var2;
                    var3 = false;
                } else {
                    // CanaryMod: full damage
                    if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, var2)) {
                        return false;
                    }
                    
                    this.aR = var2;
                    this.ap = this.ao;
                    this.bT = this.S;
                    this.c(var1, var2);
                    this.ar = this.as = 10;
                }

                this.at = 0.0F;
                OEntity var4 = var1.a();

                if (var4 != null) {
                    if (var4 instanceof OEntityPlayer) {
                        this.aG = 60;
                        this.aF = (OEntityPlayer) var4;
                    } else if (var4 instanceof OEntityWolf) {
                        OEntityWolf var5 = (OEntityWolf) var4;

                        if (var5.B()) {
                            this.aG = 60;
                            this.aF = null;
                        }
                    }
                }

                if (var3) {
                    this.bf.a(this, (byte) 2);
                    this.aB();
                    if (var4 != null) {
                        double var6 = var4.bj - this.bj;

                        double var8;

                        for (var8 = var4.bl - this.bl; var6 * var6 + var8 * var8 < 1.0E-4D; var8 = (Math.random() - Math.random()) * 0.01D) {
                            var6 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.at = (float) (Math.atan2(var8, var6) * 180.0D / 3.1415927410125732D) - this.bp;
                        this.a(var4, var2, var6, var8);
                    } else {
                        this.at = (float) ((int) (Math.random() * 2.0D) * 180);
                    }
                }

                if (this.ao <= 0) {
                    if (var3) {
                        this.bf.a(this, this.n(), this.o(), this.w());
                    }

                    this.a(var1);
                } else if (var3) {
                    this.bf.a(this, this.m(), this.o(), this.w());
                }

                return true;
            }
        }
    }

    private float w() {
        return this.l() ? (this.bP.nextFloat() - this.bP.nextFloat()) * 0.2F + 1.5F : (this.bP.nextFloat() - this.bP.nextFloat()) * 0.2F + 1.0F;
    }

    protected int O() {
        return 0;
    }

    protected void g(int var1) {}

    protected int d(ODamageSource var1, int var2) {
        if (!var1.d()) {
            int var3 = 25 - this.O();
            int var4 = var2 * var3 + this.aq;

            this.g(var2);
            var2 = var4 / 25;
            this.aq = var4 % 25;
        }

        return var2;
    }

    protected int b(ODamageSource var1, int var2) {
        if (this.a(OPotion.m)) {
            int var3 = (this.b(OPotion.m).c() + 1) * 5;
            int var4 = 25 - var3;
            int var5 = var2 * var4 + this.aq;

            var2 = var5 / 25;
            this.aq = var5 % 25;
        }

        return var2;
    }

    protected void c(ODamageSource var1, int var2) {
        var2 = this.d(var1, var2);
        var2 = this.b(var1, var2);
        this.ao -= var2;
    }

    protected float o() {
        return 1.0F;
    }

    protected String c_() {
        return null;
    }

    protected String m() {
        return "damage.hurtflesh";
    }

    protected String n() {
        return "damage.hurtflesh";
    }

    public void a(OEntity var1, int var2, double var3, double var5) {
        this.cb = true;
        float var7 = OMathHelper.a(var3 * var3 + var5 * var5);
        float var8 = 0.4F;

        this.bm /= 2.0D;
        this.bn /= 2.0D;
        this.bo /= 2.0D;
        this.bm -= var3 / (double) var7 * (double) var8;
        this.bn += 0.4000000059604645D;
        this.bo -= var5 / (double) var7 * (double) var8;
        if (this.bn > 0.4000000059604645D) {
            this.bn = 0.4000000059604645D;
        }

    }

    public void a(ODamageSource var1) {
        OEntity var2 = var1.a();

        if (this.ah >= 0 && var2 != null) {
            var2.b(this, this.ah);
        }

        if (var2 != null) {
            var2.a(this);
        }

        this.ay = true;
        if (!this.bf.I) {
            int var3 = 0;

            if (var2 instanceof OEntityPlayer) {
                var3 = OEnchantmentHelper.f(((OEntityPlayer) var2).k);
            }

            if (!this.l()) {
                this.a(this.aG > 0, var3);
            }
        }

        this.bf.a(this, (byte) 3);
    }

    protected void a(boolean var1, int var2) {
        int var3 = this.e();

        if (var3 > 0) {
            int var4 = this.bP.nextInt(3);

            if (var2 > 0) {
                var4 += this.bP.nextInt(var2 + 1);
            }

            for (int var5 = 0; var5 < var4; ++var5) {
                this.b(var3, 1);
            }
        }

    }

    protected int e() {
        return 0;
    }

    protected void b(float var1) {
        super.b(var1);
        int var2 = (int) Math.ceil((double) (var1 - 3.0F));

        if (var2 > 0) {
            // CanaryMod Damage hook: Falling
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, entity, var2)) {
                if (var2 > 4) {
                    this.bf.a(this, "damage.fallbig", 1.0F, 1.0F);
                } else {
                    this.bf.a(this, "damage.fallsmall", 1.0F, 1.0F);
                }
                this.a(ODamageSource.h, var2);
            }
            int var3 = this.bf.a(OMathHelper.b(this.bj), OMathHelper.b(this.bk - 0.20000000298023224D - (double) this.bC), OMathHelper.b(this.bl));

            if (var3 > 0) {
                OStepSound var4 = OBlock.m[var3].bZ;

                this.bf.a(this, var4.c(), var4.a() * 0.5F, var4.b() * 0.75F);
            }
        }

    }

    public void a(float var1, float var2) {
        double var3;

        if (this.az()) {
            var3 = this.bk;
            this.a(var1, var2, 0.02F);
            this.a(this.bm, this.bn, this.bo);
            this.bm *= 0.800000011920929D;
            this.bn *= 0.800000011920929D;
            this.bo *= 0.800000011920929D;
            this.bn -= 0.02D;
            if (this.bv && this.d(this.bm, this.bn + 0.6000000238418579D - this.bk + var3, this.bo)) {
                this.bn = 0.30000001192092896D;
            }
        } else if (this.aA()) {
            var3 = this.bk;
            this.a(var1, var2, 0.02F);
            this.a(this.bm, this.bn, this.bo);
            this.bm *= 0.5D;
            this.bn *= 0.5D;
            this.bo *= 0.5D;
            this.bn -= 0.02D;
            if (this.bv && this.d(this.bm, this.bn + 0.6000000238418579D - this.bk + var3, this.bo)) {
                this.bn = 0.30000001192092896D;
            }
        } else {
            float var5 = 0.91F;

            if (this.bu) {
                var5 = 0.54600006F;
                int var6 = this.bf.a(OMathHelper.b(this.bj), OMathHelper.b(this.bt.b) - 1, OMathHelper.b(this.bl));

                if (var6 > 0) {
                    var5 = OBlock.m[var6].cc * 0.91F;
                }
            }

            float var12 = 0.16277136F / (var5 * var5 * var5);
            float var7 = this.bu ? this.ak * var12 : this.al;

            this.a(var1, var2, var7);
            var5 = 0.91F;
            if (this.bu) {
                var5 = 0.54600006F;
                int var8 = this.bf.a(OMathHelper.b(this.bj), OMathHelper.b(this.bt.b) - 1, OMathHelper.b(this.bl));

                if (var8 > 0) {
                    var5 = OBlock.m[var8].cc * 0.91F;
                }
            }

            if (this.r()) {
                float var13 = 0.15F;

                if (this.bm < (double) (-var13)) {
                    this.bm = (double) (-var13);
                }

                if (this.bm > (double) var13) {
                    this.bm = (double) var13;
                }

                if (this.bo < (double) (-var13)) {
                    this.bo = (double) (-var13);
                }

                if (this.bo > (double) var13) {
                    this.bo = (double) var13;
                }

                this.bH = 0.0F;
                if (this.bn < -0.15D) {
                    this.bn = -0.15D;
                }

                if (this.aD() && this.bn < 0.0D) {
                    this.bn = 0.0D;
                }
            }

            this.a(this.bm, this.bn, this.bo);
            if (this.bv && this.r()) {
                this.bn = 0.2D;
            }

            this.bn -= 0.08D;
            this.bn *= 0.9800000190734863D;
            this.bm *= (double) var5;
            this.bo *= (double) var5;
        }

        this.aC = this.aD;
        var3 = this.bj - this.bg;
        double var9 = this.bl - this.bi;
        float var11 = OMathHelper.a(var3 * var3 + var9 * var9) * 4.0F;

        if (var11 > 1.0F) {
            var11 = 1.0F;
        }

        this.aD += (var11 - this.aD) * 0.4F;
        this.aE += this.aD;
    }

    public boolean r() {
        int var1 = OMathHelper.b(this.bj);
        int var2 = OMathHelper.b(this.bt.b);
        int var3 = OMathHelper.b(this.bl);

        return this.bf.a(var1, var2, var3) == OBlock.aH.bO;
    }

    public void b(ONBTTagCompound var1) {
        var1.a("Health", (short) this.ao);
        var1.a("HurtTime", (short) this.ar);
        var1.a("DeathTime", (short) this.au);
        var1.a("AttackTime", (short) this.av);
        if (!this.aJ.isEmpty()) {
            ONBTTagList var2 = new ONBTTagList();
            Iterator var3 = this.aJ.values().iterator();

            while (var3.hasNext()) {
                OPotionEffect var4 = (OPotionEffect) var3.next();
                ONBTTagCompound var5 = new ONBTTagCompound();

                var5.a("Id", (byte) var4.a());
                var5.a("Amplifier", (byte) var4.c());
                var5.a("Duration", var4.b());
                var2.a((ONBTBase) var5);
            }

            var1.a("ActiveEffects", (ONBTBase) var2);
        }

    }

    public void a(ONBTTagCompound var1) {
        this.ao = var1.e("Health");
        if (!var1.c("Health")) {
            this.ao = this.c();
        }

        this.ar = var1.e("HurtTime");
        this.au = var1.e("DeathTime");
        this.av = var1.e("AttackTime");
        if (var1.c("ActiveEffects")) {
            ONBTTagList var2 = var1.m("ActiveEffects");

            for (int var3 = 0; var3 < var2.d(); ++var3) {
                ONBTTagCompound var4 = (ONBTTagCompound) var2.a(var3);
                byte var5 = var4.d("Id");
                byte var6 = var4.d("Amplifier");
                int var7 = var4.f("Duration");

                this.aJ.put(Integer.valueOf(var5), new OPotionEffect(var5, var7, var6));
            }
        }

    }

    public boolean aj() {
        return !this.bB && this.ao > 0;
    }

    public boolean f() {
        return false;
    }

    public void d() {
        if (this.d > 0) {
            --this.d;
        }

        if (this.aK > 0) {
            double var1 = this.bj + (this.aL - this.bj) / (double) this.aK;
            double var3 = this.bk + (this.aM - this.bk) / (double) this.aK;
            double var5 = this.bl + (this.aN - this.bl) / (double) this.aK;

            double var7;

            for (var7 = this.aO - (double) this.bp; var7 < -180.0D; var7 += 360.0D) {
                ;
            }

            while (var7 >= 180.0D) {
                var7 -= 360.0D;
            }

            this.bp = (float) ((double) this.bp + var7 / (double) this.aK);
            this.bq = (float) ((double) this.bq + (this.aP - (double) this.bq) / (double) this.aK);
            --this.aK;
            this.c(var1, var3, var5);
            this.c(this.bp, this.bq);
            List var9 = this.bf.a((OEntity) this, this.bt.e(0.03125D, 0.0D, 0.03125D));

            if (var9.size() > 0) {
                double var10 = 0.0D;

                for (int var12 = 0; var12 < var9.size(); ++var12) {
                    OAxisAlignedBB var13 = (OAxisAlignedBB) var9.get(var12);

                    if (var13.e > var10) {
                        var10 = var13.e;
                    }
                }

                var3 += var10 - this.bt.b;
                this.c(var1, var3, var5);
            }
        }

        OProfiler.a("OBlockTNT");
        if (this.L()) {
            this.aW = false;
            this.aT = 0.0F;
            this.aU = 0.0F;
            this.aV = 0.0F;
        } else if (!this.aj) {
            this.m_();
        }

        OProfiler.a();
        boolean var14 = this.az();
        boolean var15 = this.aA();

        if (this.aW) {
            if (var14) {
                this.bn += 0.03999999910593033D;
            } else if (var15) {
                this.bn += 0.03999999910593033D;
            } else if (this.bu && this.d == 0) {
                this.X();
                this.d = 10;
            }
        } else {
            this.d = 0;
        }

        this.aT *= 0.98F;
        this.aU *= 0.98F;
        this.aV *= 0.9F;
        float var16 = this.ak;

        this.ak *= this.F();
        this.a(this.aT, this.aU);
        this.ak = var16;
        OProfiler.a("push");
        List var17 = this.bf.b((OEntity) this, this.bt.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

        if (var17 != null && var17.size() > 0) {
            for (int var18 = 0; var18 < var17.size(); ++var18) {
                OEntity var19 = (OEntity) var17.get(var18);

                if (var19.f_()) {
                    var19.j(this);
                }
            }
        }

        OProfiler.a();
    }

    protected boolean L() {
        return this.ao <= 0;
    }

    public boolean K() {
        return false;
    }

    protected void X() {
        this.bn = 0.41999998688697815D;
        if (this.a(OPotion.j)) {
            this.bn += (double) ((float) (this.b(OPotion.j).c() + 1) * 0.1F);
        }

        if (this.aE()) {
            float var1 = this.bp * 0.017453292F;

            this.bm -= (double) (OMathHelper.a(var1) * 0.2F);
            this.bo += (double) (OMathHelper.b(var1) * 0.2F);
        }

        this.cb = true;
    }

    protected boolean d_() {
        return true;
    }

    protected void ak() {
        OEntityPlayer var1 = this.bf.a(this, -1.0D);

        if (var1 != null) {
            double var2 = var1.bj - this.bj;
            double var4 = var1.bk - this.bk;
            double var6 = var1.bl - this.bl;
            double var8 = var2 * var2 + var4 * var4 + var6 * var6;

            if (this.d_() && var8 > 16384.0D) {
                // CanaryMod hook onEntityDespawn
                if (!(Boolean) OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
                    this.S();
                }
            }

            if (this.aS > 600 && this.bP.nextInt(800) == 0 && this.d_()) {
                if (var8 < 1024.0D) {
                    this.aS = 0;
                } else {
                    // CanaryMod hook onEntityDespawn
                    if (!(Boolean) OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
                        this.S();
                    } else {
                        this.aS = 0;
                    }
                }
            }
        }

    }

    protected void m_() {
        ++this.aS;
        OEntityPlayer var1 = this.bf.a(this, -1.0D);

        this.ak();
        this.aT = 0.0F;
        this.aU = 0.0F;
        float var2 = 8.0F;

        if (this.bP.nextFloat() < 0.02F) {
            var1 = this.bf.a(this, (double) var2);
            if (var1 != null) {
                this.e = var1;
                this.aZ = 10 + this.bP.nextInt(20);
            } else {
                this.aV = (this.bP.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.e != null) {
            this.a(this.e, 10.0F, (float) this.q_());
            if (this.aZ-- <= 0 || this.e.bB || this.e.i(this) > (double) (var2 * var2)) {
                this.e = null;
            }
        } else {
            if (this.bP.nextFloat() < 0.05F) {
                this.aV = (this.bP.nextFloat() - 0.5F) * 20.0F;
            }

            this.bp += this.aV;
            this.bq = this.aX;
        }

        boolean var3 = this.az();
        boolean var4 = this.aA();

        if (var3 || var4) {
            this.aW = this.bP.nextFloat() < 0.8F;
        }

    }

    protected int q_() {
        return 40;
    }

    public void a(OEntity var1, float var2, float var3) {
        double var4 = var1.bj - this.bj;
        double var6 = var1.bl - this.bl;
        double var9;

        if (var1 instanceof OEntityLiving) {
            OEntityLiving var8 = (OEntityLiving) var1;

            var9 = this.bk + (double) this.x() - (var8.bk + (double) var8.x());
        } else {
            var9 = (var1.bt.b + var1.bt.e) / 2.0D - (this.bk + (double) this.x());
        }

        double var11 = (double) OMathHelper.a(var4 * var4 + var6 * var6);
        float var13 = (float) (Math.atan2(var6, var4) * 180.0D / 3.1415927410125732D) - 90.0F;
        float var14 = (float) (-(Math.atan2(var9, var11) * 180.0D / 3.1415927410125732D));

        this.bq = -this.b(this.bq, var14, var3);
        this.bp = this.b(this.bp, var13, var2);
    }

    public boolean al() {
        return this.e != null;
    }

    public OEntity am() {
        return this.e;
    }

    private float b(float var1, float var2, float var3) {
        float var4;

        for (var4 = var2 - var1; var4 < -180.0F; var4 += 360.0F) {
            ;
        }

        while (var4 >= 180.0F) {
            var4 -= 360.0F;
        }

        if (var4 > var3) {
            var4 = var3;
        }

        if (var4 < -var3) {
            var4 = -var3;
        }

        return var1 + var4;
    }

    public void an() {}

    public boolean g() {
        return this.bf.a(this.bt) && this.bf.a((OEntity) this, this.bt).size() == 0 && !this.bf.c(this.bt);
    }

    protected void ao() {
        this.a(ODamageSource.i, 4);
    }

    public OVec3D ap() {
        return this.d(1.0F);
    }

    public OVec3D d(float var1) {
        float var2;
        float var3;
        float var4;
        float var5;

        if (var1 == 1.0F) {
            var2 = OMathHelper.b(-this.bp * 0.017453292F - 3.1415927F);
            var3 = OMathHelper.a(-this.bp * 0.017453292F - 3.1415927F);
            var4 = -OMathHelper.b(-this.bq * 0.017453292F);
            var5 = OMathHelper.a(-this.bq * 0.017453292F);
            return OVec3D.b((double) (var3 * var4), (double) var5, (double) (var2 * var4));
        } else {
            var2 = this.bs + (this.bq - this.bs) * var1;
            var3 = this.br + (this.bp - this.br) * var1;
            var4 = OMathHelper.b(-var3 * 0.017453292F - 3.1415927F);
            var5 = OMathHelper.a(-var3 * 0.017453292F - 3.1415927F);
            float var6 = -OMathHelper.b(-var2 * 0.017453292F);
            float var7 = OMathHelper.a(-var2 * 0.017453292F);

            return OVec3D.b((double) (var5 * var6), (double) var7, (double) (var4 * var6));
        }
    }

    public int p() {
        return 4;
    }

    public boolean U() {
        return false;
    }

    protected void aq() {
        Iterator var1 = this.aJ.keySet().iterator();

        while (var1.hasNext()) {
            Integer var2 = (Integer) var1.next();
            OPotionEffect var3 = (OPotionEffect) this.aJ.get(var2);

            if (!var3.a(this) && !this.bf.I) {
                var1.remove();
                this.d(var3);
            }
        }

        int var10;

        if (this.b) {
            if (!this.bf.I) {
                if (!this.aJ.isEmpty()) {
                    var10 = OPotionHelper.a(this.aJ.values());
                    this.bV.b(8, Integer.valueOf(var10));
                } else {
                    this.bV.b(8, Integer.valueOf(0));
                }
            }

            this.b = false;
        }

        if (this.bP.nextBoolean()) {
            var10 = this.bV.c(8);
            if (var10 > 0) {
                double var4 = (double) (var10 >> 16 & 255) / 255.0D;
                double var6 = (double) (var10 >> 8 & 255) / 255.0D;
                double var8 = (double) (var10 >> 0 & 255) / 255.0D;

                this.bf.a("mobSpell", this.bj + (this.bP.nextDouble() - 0.5D) * (double) this.bD, this.bk + this.bP.nextDouble() * (double) this.bE - (double) this.bC, this.bl + (this.bP.nextDouble() - 0.5D) * (double) this.bD, var4, var6, var8);
            }
        }

    }

    public void ar() {
        Iterator var1 = this.aJ.keySet().iterator();

        while (var1.hasNext()) {
            Integer var2 = (Integer) var1.next();
            OPotionEffect var3 = (OPotionEffect) this.aJ.get(var2);

            if (!this.bf.I) {
                var1.remove();
                this.d(var3);
            }
        }

    }

    public Collection as() {
        return this.aJ.values();
    }

    public boolean a(OPotion var1) {
        return this.aJ.containsKey(Integer.valueOf(var1.H));
    }

    public OPotionEffect b(OPotion var1) {
        return (OPotionEffect) this.aJ.get(Integer.valueOf(var1.H));
    }

    public void e(OPotionEffect var1) {
        // CanaryMod - POTION_EFFECT HOOK
        PotionEffect pe = (PotionEffect) etc.getLoader().callHook(PluginLoader.Hook.POTION_EFFECT, this.entity, var1.potionEffect);

        if (pe == null) {
            return;
        }
        var1 = pe.potionEffect;
        if (this.a(var1)) {
            if (this.aJ.containsKey(Integer.valueOf(var1.a()))) {
                ((OPotionEffect) this.aJ.get(Integer.valueOf(var1.a()))).a(var1);
                this.c((OPotionEffect) this.aJ.get(Integer.valueOf(var1.a())));
            } else {
                this.aJ.put(Integer.valueOf(var1.a()), var1);
                this.b(var1);
            }
        }
    }

    public boolean a(OPotionEffect var1) {
        if (this.t() == OEnumCreatureAttribute.b) {
            int var2 = var1.a();

            if (var2 == OPotion.l.H || var2 == OPotion.u.H) {
                return false;
            }
        }

        return true;
    }

    public boolean at() {
        return this.t() == OEnumCreatureAttribute.b;
    }

    protected void b(OPotionEffect var1) {
        this.b = true;
    }

    protected void c(OPotionEffect var1) {
        this.b = true;
    }

    protected void d(OPotionEffect var1) {
        this.b = true;
    }

    protected float F() {
        float var1 = 1.0F;

        if (this.a(OPotion.c)) {
            var1 *= 1.0F + 0.2F * (float) (this.b(OPotion.c).c() + 1);
        }

        if (this.a(OPotion.d)) {
            var1 *= 1.0F - 0.15F * (float) (this.b(OPotion.d).c() + 1);
        }

        return var1;
    }

    public void a_(double var1, double var3, double var5) {
        this.c(var1, var3, var5, this.bp, this.bq);
    }

    public boolean l() {
        return false;
    }

    public OEnumCreatureAttribute t() {
        return OEnumCreatureAttribute.a;
    }

    public void c(OItemStack var1) {
        this.bf.a(this, "random.break", 0.8F, 0.8F + this.bf.w.nextFloat() * 0.4F);

        for (int var2 = 0; var2 < 5; ++var2) {
            OVec3D var3 = OVec3D.b(((double) this.bP.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

            var3.a(-this.bq * 3.1415927F / 180.0F);
            var3.b(-this.bp * 3.1415927F / 180.0F);
            OVec3D var4 = OVec3D.b(((double) this.bP.nextFloat() - 0.5D) * 0.3D, (double) (-this.bP.nextFloat()) * 0.6D - 0.3D, 0.6D);

            var4.a(-this.bq * 3.1415927F / 180.0F);
            var4.b(-this.bp * 3.1415927F / 180.0F);
            var4 = var4.c(this.bj, this.bk + (double) this.x(), this.bl);
            this.bf.a("iconcrack_" + var1.a().bM, var4.a, var4.b, var4.c, var3.a, var3.b + 0.05D, var3.c);
        }

    }
}
