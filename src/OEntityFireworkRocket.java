public class OEntityFireworkRocket extends OEntity {

    private int a;
    protected int b; //CanaryMod: private -> protected

    public OEntityFireworkRocket(OWorld oworld) {
        super(oworld);
        this.a(0.25F, 0.25F);
    }

    protected void a() {
        this.ag.a(8, new OItemStack(0, 0, 0));
    }

    public OEntityFireworkRocket(OWorld oworld, double d0, double d1, double d2, OItemStack oitemstack) {
        super(oworld);
        
        // CanaryMod: Adding null check to not spawn null firework
        if(oitemstack.d == null) this.x();
        // CanaryMod: End
        
        this.a = 0;
        this.a(0.25F, 0.25F);
        this.b(d0, d1, d2);
        this.M = 0.0F;
        int i = 1;

        if (oitemstack != null && oitemstack.o()) {
            this.ag.b(8, oitemstack);
            ONBTTagCompound onbttagcompound = oitemstack.p();
            ONBTTagCompound onbttagcompound1 = onbttagcompound.l("Fireworks");

            if (onbttagcompound1 != null) {
                i += onbttagcompound1.c("Flight");
            }
        }

        this.w = this.aa.nextGaussian() * 0.001D;
        this.y = this.aa.nextGaussian() * 0.001D;
        this.x = 0.05D;
        this.b = 10 * i + this.aa.nextInt(6) + this.aa.nextInt(7);
    }

    public void j_() {
        this.T = this.t;
        this.U = this.u;
        this.V = this.v;
        super.j_();
        this.w *= 1.15D;
        this.y *= 1.15D;
        this.x += 0.04D;
        this.d(this.w, this.x, this.y);
        float f = OMathHelper.a(this.w * this.w + this.y * this.y);

        this.z = (float) (Math.atan2(this.w, this.y) * 180.0D / 3.1415927410125732D);

        for (this.A = (float) (Math.atan2(this.x, (double) f) * 180.0D / 3.1415927410125732D); this.A - this.C < -180.0F; this.C -= 360.0F) {
            ;
        }

        while (this.A - this.C >= 180.0F) {
            this.C += 360.0F;
        }

        while (this.z - this.B < -180.0F) {
            this.B -= 360.0F;
        }

        while (this.z - this.B >= 180.0F) {
            this.B += 360.0F;
        }

        this.A = this.C + (this.A - this.C) * 0.2F;
        this.z = this.B + (this.z - this.B) * 0.2F;
        if (this.a == 0) {
            this.p.a((OEntity) this, "fireworks.launch", 3.0F, 1.0F);
        }

        ++this.a;
        if (this.p.I && this.a % 2 < 2) {
            this.p.a("fireworksSpark", this.t, this.u - 0.3D, this.v, this.aa.nextGaussian() * 0.05D, -this.x * 0.5D, this.aa.nextGaussian() * 0.05D);
        }

        if (!this.p.I && this.a > this.b) {
            this.p.a(this, (byte) 17);
            this.x();
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Life", this.a);
        onbttagcompound.a("LifeTime", this.b);
        OItemStack oitemstack = this.ag.f(8);

        if (oitemstack != null) {
            ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

            oitemstack.b(onbttagcompound1);
            onbttagcompound.a("FireworksItem", onbttagcompound1);
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.a = onbttagcompound.e("Life");
        this.b = onbttagcompound.e("LifeTime");
        ONBTTagCompound onbttagcompound1 = onbttagcompound.l("FireworksItem");

        if (onbttagcompound1 != null) {
            OItemStack oitemstack = OItemStack.a(onbttagcompound1);

            if (oitemstack != null) {
                this.ag.b(8, oitemstack);
            }
        }
    }

    public float c(float f) {
        return super.c(f);
    }

    public boolean aq() {
        return false;
    }
}
