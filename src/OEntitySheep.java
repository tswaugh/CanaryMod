import java.util.Random;

public class OEntitySheep extends OEntityAnimal {

    private final OInventoryCrafting e = new OInventoryCrafting(new OContainerSheep(this), 2, 1);
    public static final float[][] d = new float[][] { { 1.0F, 1.0F, 1.0F}, { 0.85F, 0.5F, 0.2F}, { 0.7F, 0.3F, 0.85F}, { 0.4F, 0.6F, 0.85F}, { 0.9F, 0.9F, 0.2F}, { 0.5F, 0.8F, 0.1F}, { 0.95F, 0.5F, 0.65F}, { 0.3F, 0.3F, 0.3F}, { 0.6F, 0.6F, 0.6F}, { 0.3F, 0.5F, 0.6F}, { 0.5F, 0.25F, 0.7F}, { 0.2F, 0.3F, 0.7F}, { 0.4F, 0.3F, 0.2F}, { 0.4F, 0.5F, 0.2F}, { 0.6F, 0.2F, 0.2F}, { 0.1F, 0.1F, 0.1F}};
    private int f;
    private OEntityAIEatGrass g = new OEntityAIEatGrass(this);

    private Sheep sheep = new Sheep(this); // CanaryMod: one sheep per sheep

    public OEntitySheep(OWorld oworld) {
        super(oworld);
        this.aF = "/mob/sheep.png";
        this.a(0.9F, 1.3F);
        float f = 0.23F;

        this.az().a(true);
        this.bm.a(0, new OEntityAISwimming(this));
        this.bm.a(1, new OEntityAIPanic(this, 0.38F));
        this.bm.a(2, new OEntityAIMate(this, f));
        this.bm.a(3, new OEntityAITempt(this, 0.25F, OItem.T.cg, false));
        this.bm.a(4, new OEntityAIFollowParent(this, 0.25F));
        this.bm.a(5, this.g);
        this.bm.a(6, new OEntityAIWander(this, f));
        this.bm.a(7, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.bm.a(8, new OEntityAILookIdle(this));
        this.e.a(0, new OItemStack(OItem.aW, 1, 0));
        this.e.a(1, new OItemStack(OItem.aW, 1, 0));
    }

    protected boolean be() {
        return true;
    }

    protected void bl() {
        this.f = this.g.f();
        super.bl();
    }

    public void c() {
        if (this.p.J) {
            this.f = Math.max(0, this.f - 1);
        }

        super.c();
    }

    public int aT() {
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

    protected int bb() {
        return OBlock.ae.cm;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bI.g();

        if (oitemstack != null && oitemstack.c == OItem.be.cg && !this.n() && !this.h_()) {
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
            this.a("mob.sheep.shear", 1.0F, 1.0F);
        }

        return super.a(oentityplayer);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Sheared", this.n());
        onbttagcompound.a("Color", (byte) this.m());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.f(onbttagcompound.n("Sheared"));
        this.s(onbttagcompound.c("Color"));
    }

    protected String aY() {
        return "mob.sheep.say";
    }

    protected String aZ() {
        return "mob.sheep.say";
    }

    protected String ba() {
        return "mob.sheep.say";
    }

    protected void a(int i, int j, int k, int l) {
        this.a("mob.sheep.step", 0.15F, 1.0F);
    }

    public int m() {
        return this.ag.a(16) & 15;
    }

    public void s(int i) {
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

    public OEntitySheep b(OEntityAgeable oentityageable) {
        OEntitySheep oentitysheep = (OEntitySheep) oentityageable;
        OEntitySheep oentitysheep1 = new OEntitySheep(this.p);
        int i = this.a(this, oentitysheep);

        oentitysheep1.s(15 - i);
        return oentitysheep1;
    }

    public void aH() {
        this.f(false);
        if (this.h_()) {
            int i = this.b() + 1200;

            if (i > 0) {
                i = 0;
            }

            this.a(i);
        }
    }

    public void bG() {
        this.s(a(this.p.u));
    }

    private int a(OEntityAnimal oentityanimal, OEntityAnimal oentityanimal1) {
        int i = this.b(oentityanimal);
        int j = this.b(oentityanimal1);

        this.e.a(0).b(i);
        this.e.a(1).b(j);
        OItemStack oitemstack = OCraftingManager.a().a(this.e, ((OEntitySheep) oentityanimal).p);
        int k;

        if (oitemstack != null && oitemstack.b().cg == OItem.aW.cg) {
            k = oitemstack.j();
        } else {
            k = this.p.u.nextBoolean() ? i : j;
        }

        return k;
    }

    private int b(OEntityAnimal oentityanimal) {
        return 15 - ((OEntitySheep) oentityanimal).m();
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }

    @Override
    public Sheep getEntity() {
        return sheep;
    }
}
