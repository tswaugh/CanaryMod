import java.util.List;

public abstract class OEntityFireball extends OEntity {

    protected int e = -1; // CanaryMod: private to protected
    private int f = -1;
    private int g = -1;
    private int h = 0;
    private boolean i = false;
    public OEntityLiving a;
    private int j;
    private int at = 0;
    public double b;
    public double c;
    public double d;

    public OEntityFireball(OWorld oworld) {
        super(oworld);
        this.a(1.0F, 1.0F);
    }

    protected void a() {}

    public OEntityFireball(OWorld oworld, double d0, double d1, double d2, double d3, double d4, double d5) {
        super(oworld);
        this.a(1.0F, 1.0F);
        this.b(d0, d1, d2, this.z, this.A);
        this.b(d0, d1, d2);
        double d6 = (double) OMathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);

        this.b = d3 / d6 * 0.1D;
        this.c = d4 / d6 * 0.1D;
        this.d = d5 / d6 * 0.1D;
    }

    public OEntityFireball(OWorld oworld, OEntityLiving oentityliving, double d0, double d1, double d2) {
        super(oworld);
        this.a = oentityliving;
        this.a(1.0F, 1.0F);
        this.b(oentityliving.t, oentityliving.u, oentityliving.v, oentityliving.z, oentityliving.A);
        this.b(this.t, this.u, this.v);
        this.M = 0.0F;
        this.w = this.x = this.y = 0.0D;
        d0 += this.aa.nextGaussian() * 0.4D;
        d1 += this.aa.nextGaussian() * 0.4D;
        d2 += this.aa.nextGaussian() * 0.4D;
        double d3 = (double) OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        this.b = d0 / d3 * 0.1D;
        this.c = d1 / d3 * 0.1D;
        this.d = d2 / d3 * 0.1D;
    }

    public void j_() {
        if (!this.p.I && (this.a != null && this.a.L || !this.p.f((int) this.t, (int) this.u, (int) this.v))) {
            this.x();
        } else {
            super.j_();
            this.c(1);
            if (this.i) {
                int i = this.p.a(this.e, this.f, this.g);

                if (i == this.h) {
                    ++this.j;
                    if (this.j == 600) {
                        this.x();
                    }

                    return;
                }

                this.i = false;
                this.w *= (double) (this.aa.nextFloat() * 0.2F);
                this.x *= (double) (this.aa.nextFloat() * 0.2F);
                this.y *= (double) (this.aa.nextFloat() * 0.2F);
                this.j = 0;
                this.at = 0;
            } else {
                ++this.at;
            }

            OVec3 ovec3 = this.p.S().a(this.t, this.u, this.v);
            OVec3 ovec31 = this.p.S().a(this.t + this.w, this.u + this.x, this.v + this.y);
            OMovingObjectPosition omovingobjectposition = this.p.a(ovec3, ovec31);

            ovec3 = this.p.S().a(this.t, this.u, this.v);
            ovec31 = this.p.S().a(this.t + this.w, this.u + this.x, this.v + this.y);
            if (omovingobjectposition != null) {
                ovec31 = this.p.S().a(omovingobjectposition.f.c, omovingobjectposition.f.d, omovingobjectposition.f.e);
            }

            OEntity oentity = null;
            List list = this.p.b((OEntity) this, this.D.a(this.w, this.x, this.y).b(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;

            for (int j = 0; j < list.size(); ++j) {
                OEntity oentity1 = (OEntity) list.get(j);

                if (oentity1.L() && (!oentity1.i(this.a) || this.at >= 25)) {
                    float f = 0.3F;
                    OAxisAlignedBB oaxisalignedbb = oentity1.D.b((double) f, (double) f, (double) f);
                    OMovingObjectPosition omovingobjectposition1 = oaxisalignedbb.a(ovec3, ovec31);

                    if (omovingobjectposition1 != null) {
                        double d1 = ovec3.d(omovingobjectposition1.f);

                        if (d1 < d0 || d0 == 0.0D) {
                            oentity = oentity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (oentity != null) {
                omovingobjectposition = new OMovingObjectPosition(oentity);
            }

            if (omovingobjectposition != null) {
                this.a(omovingobjectposition);
            }

            this.t += this.w;
            this.u += this.x;
            this.v += this.y;
            float f1 = OMathHelper.a(this.w * this.w + this.y * this.y);

            this.z = (float) (Math.atan2(this.y, this.w) * 180.0D / 3.1415927410125732D) + 90.0F;

            for (this.A = (float) (Math.atan2((double) f1, this.x) * 180.0D / 3.1415927410125732D) - 90.0F; this.A - this.C < -180.0F; this.C -= 360.0F) {
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
            float f2 = this.c();

            if (this.H()) {
                for (int k = 0; k < 4; ++k) {
                    float f3 = 0.25F;

                    this.p.a("bubble", this.t - this.w * (double) f3, this.u - this.x * (double) f3, this.v - this.y * (double) f3, this.w, this.x, this.y);
                }

                f2 = 0.8F;
            }

            this.w += this.b;
            this.x += this.c;
            this.y += this.d;
            this.w *= (double) f2;
            this.x *= (double) f2;
            this.y *= (double) f2;
            this.p.a("smoke", this.t, this.u + 0.5D, this.v, 0.0D, 0.0D, 0.0D);
            this.b(this.t, this.u, this.v);
        }
    }

    protected float c() {
        return 0.95F;
    }

    protected abstract void a(OMovingObjectPosition omovingobjectposition);

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("xTile", (short) this.e);
        onbttagcompound.a("yTile", (short) this.f);
        onbttagcompound.a("zTile", (short) this.g);
        onbttagcompound.a("inTile", (byte) this.h);
        onbttagcompound.a("inGround", (byte) (this.i ? 1 : 0));
        onbttagcompound.a("direction", (ONBTBase) this.a(new double[] { this.w, this.x, this.y}));
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.e = onbttagcompound.d("xTile");
        this.f = onbttagcompound.d("yTile");
        this.g = onbttagcompound.d("zTile");
        this.h = onbttagcompound.c("inTile") & 255;
        this.i = onbttagcompound.c("inGround") == 1;
        if (onbttagcompound.b("direction")) {
            ONBTTagList onbttaglist = onbttagcompound.m("direction");

            this.w = ((ONBTTagDouble) onbttaglist.b(0)).a;
            this.x = ((ONBTTagDouble) onbttaglist.b(1)).a;
            this.y = ((ONBTTagDouble) onbttaglist.b(2)).a;
        } else {
            this.x();
        }
    }

    public boolean L() {
        return true;
    }

    public float Y() {
        return 1.0F;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.ar()) {
            return false;
        } else {
            this.K();
            if (odamagesource.g() != null) {
                OVec3 ovec3 = odamagesource.g().Z();

                if (ovec3 != null) {
                    this.w = ovec3.c;
                    this.x = ovec3.d;
                    this.y = ovec3.e;
                    this.b = this.w * 0.1D;
                    this.c = this.x * 0.1D;
                    this.d = this.y * 0.1D;
                }

                if (odamagesource.g() instanceof OEntityLiving) {
                    this.a = (OEntityLiving) odamagesource.g();
                }

                return true;
            } else {
                return false;
            }
        }
    }

    public float c(float f) {
        return 1.0F;
    }
}
