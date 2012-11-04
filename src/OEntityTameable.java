public abstract class OEntityTameable extends OEntityAnimal {

    protected OEntityAISit d = new OEntityAISit(this);

    private TamableEntity tamableEntity = new TamableEntity(this);

    public OEntityTameable(OWorld oworld) {
        super(oworld);
    }

    protected void a() {
        super.a();
        this.ag.a(16, Byte.valueOf((byte) 0));
        this.ag.a(17, "");
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        if (this.o() == null) {
            onbttagcompound.a("Owner", "");
        } else {
            onbttagcompound.a("Owner", this.o());
        }

        onbttagcompound.a("Sitting", this.n());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        String s = onbttagcompound.i("Owner");

        if (s.length() > 0) {
            this.a(s);
            this.g(true);
        }

        this.d.a(onbttagcompound.n("Sitting"));
        this.h(onbttagcompound.n("Sitting"));
    }

    protected void f(boolean flag) {
        String s = "heart";

        if (!flag) {
            s = "smoke";
        }

        for (int i = 0; i < 7; ++i) {
            double d0 = this.aa.nextGaussian() * 0.02D;
            double d1 = this.aa.nextGaussian() * 0.02D;
            double d2 = this.aa.nextGaussian() * 0.02D;

            this.p.a(s, this.t + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N, this.u + 0.5D + (double) (this.aa.nextFloat() * this.O), this.v + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N, d0, d1, d2);
        }
    }

    public boolean m() {
        return (this.ag.a(16) & 4) != 0;
    }

    public void g(boolean flag) {
        byte b0 = this.ag.a(16);

        if (flag) {
            this.ag.b(16, Byte.valueOf((byte) (b0 | 4)));
        } else {
            this.ag.b(16, Byte.valueOf((byte) (b0 & -5)));
        }
    }

    public boolean n() {
        return (this.ag.a(16) & 1) != 0;
    }

    public void h(boolean flag) {
        byte b0 = this.ag.a(16);

        if (flag) {
            this.ag.b(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.ag.b(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    public String o() {
        return this.ag.e(17);
    }

    public void a(String s) {
        this.ag.b(17, s);
    }

    public OEntityLiving p() {
        return this.p.a(this.o());
    }

    public OEntityAISit q() {
        return this.d;
    }

    @Override
    public BaseEntity getEntity() {
        return tamableEntity;
    }
}
