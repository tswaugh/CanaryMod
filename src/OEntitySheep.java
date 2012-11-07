import java.util.Random;

public class OEntitySheep extends OEntityAnimal {

    public static final float[][] d = new float[][] { { 1.0F, 1.0F, 1.0F}, { 0.95F, 0.7F, 0.2F}, { 0.9F, 0.5F, 0.85F}, { 0.6F, 0.7F, 0.95F}, { 0.9F, 0.9F, 0.2F}, { 0.5F, 0.8F, 0.1F}, { 0.95F, 0.7F, 0.8F}, { 0.3F, 0.3F, 0.3F}, { 0.6F, 0.6F, 0.6F}, { 0.3F, 0.6F, 0.7F}, { 0.7F, 0.4F, 0.9F}, { 0.2F, 0.4F, 0.8F}, { 0.5F, 0.4F, 0.3F}, { 0.4F, 0.5F, 0.2F}, { 0.8F, 0.3F, 0.3F}, { 0.1F, 0.1F, 0.1F}};
    private int e;
    private OEntityAIEatGrass f = new OEntityAIEatGrass(this);

    private Sheep sheep = new Sheep(this); // CanaryMod: one sheep per sheep

    public OEntitySheep(OWorld oworld) {
        super(oworld);
        this.aF = "/mob/sheep.png";
        this.a(0.9F, 1.3F);
        float f = 0.23F;

        this.ay().a(true);
        this.bn.a(0, new OEntityAISwimming(this));
        this.bn.a(1, new OEntityAIPanic(this, 0.38F));
        this.bn.a(2, new OEntityAIMate(this, f));
        this.bn.a(3, new OEntityAITempt(this, 0.25F, OItem.T.cf, false));
        this.bn.a(4, new OEntityAIFollowParent(this, 0.25F));
        this.bn.a(5, this.f);
        this.bn.a(6, new OEntityAIWander(this, f));
        this.bn.a(7, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.bn.a(8, new OEntityAILookIdle(this));
    }

    protected boolean bb() {
        return true;
    }

    protected void bi() {
        this.e = this.f.f();
        super.bi();
    }

    public void c() {
        if (this.p.J) {
            this.e = Math.max(0, this.e - 1);
        }

        super.c();
    }

    public int aS() {
        return 8;
    }

    protected void a() {
        super.a();
        this.ag.a(16, new Byte((byte) 0));
    }

    protected void a(boolean flag, int i) {
        if (!this.n()) {
            this.a(new OItemStack(OBlock.ae.cm, 1, this.m()), 0.0F);
        }
    }

    protected int aZ() {
        return OBlock.ae.cm;
    }

    public boolean c(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bK.g();

        if (oitemstack != null && oitemstack.c == OItem.be.cf && !this.n() && !this.h_()) {
            if (!this.p.J) {
                this.f(true);
                int i = 1 + this.aa.nextInt(3);

                for (int j = 0; j < i; ++j) {
                    OEntityItem oentityitem = this.a(new OItemStack(OBlock.ae.cm, 1, this.m()), 1.0F);

                    oentityitem.x += (double) (this.aa.nextFloat() * 0.05F);
                    oentityitem.w += (double) ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.1F);
                    oentityitem.y += (double) ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.1F);
                }
            }

            oitemstack.a(1, oentityplayer);
            this.p.a(this, "mob.sheep.shear", 1.0F, 1.0F);
        }

        return super.c(oentityplayer);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Sheared", this.n());
        onbttagcompound.a("Color", (byte) this.m());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.f(onbttagcompound.n("Sheared"));
        this.r(onbttagcompound.c("Color"));
    }

    protected String aW() {
        return "mob.sheep.say";
    }

    protected String aX() {
        return "mob.sheep.say";
    }

    protected String aY() {
        return "mob.sheep.say";
    }

    protected void a(int i, int j, int k, int l) {
        this.p.a(this, "mob.sheep.step", 0.15F, 1.0F);
    }

    public int m() {
        return this.ag.a(16) & 15;
    }

    public void r(int i) {
        byte b0 = this.ag.a(16);

        this.ag.b(16, Byte.valueOf((byte) (b0 & 240 | i & 15)));
    }

    public boolean n() {
        return (this.ag.a(16) & 16) != 0;
    }

    public void f(boolean flag) {
        byte b0 = this.ag.a(16);

        if (flag) {
            this.ag.b(16, Byte.valueOf((byte) (b0 | 16)));
        } else {
            this.ag.b(16, Byte.valueOf((byte) (b0 & -17)));
        }
    }

    public static int a(Random random) {
        int i = random.nextInt(100);

        return i < 5 ? 15 : (i < 10 ? 7 : (i < 15 ? 8 : (i < 18 ? 12 : (random.nextInt(500) == 0 ? 6 : 0))));
    }

    public OEntityAnimal a(OEntityAnimal oentityanimal) {
        OEntitySheep oentitysheep = (OEntitySheep) oentityanimal;
        OEntitySheep oentitysheep1 = new OEntitySheep(this.p);

        if (this.aa.nextBoolean()) {
            oentitysheep1.r(this.m());
        } else {
            oentitysheep1.r(oentitysheep.m());
        }

        return oentitysheep1;
    }

    public void aG() {
        this.f(false);
        if (this.h_()) {
            int i = this.b() + 1200;

            if (i > 0) {
                i = 0;
            }

            this.a(i);
        }
    }

    public void bD() {
        this.r(a(this.p.u));
    }

    @Override
    public Sheep getEntity() {
        return sheep;
    }
}
