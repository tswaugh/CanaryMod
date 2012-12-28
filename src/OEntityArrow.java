import java.util.List;

public class OEntityArrow extends OEntity implements OIProjectile {

    private int d = -1;
    private int e = -1;
    private int f = -1;
    private int g = 0;
    private int h = 0;
    private boolean i = false;
    public int a = 0;
    public int b = 0;
    public OEntity c;
    private int j;
    private int at = 0;
    private double au = 2.0D;
    private int av;

    public OEntityArrow(OWorld oworld) {
        super(oworld);
        this.l = 10.0D;
        this.a(0.5F, 0.5F);
    }

    public OEntityArrow(OWorld oworld, double d0, double d1, double d2) {
        super(oworld);
        this.l = 10.0D;
        this.a(0.5F, 0.5F);
        this.b(d0, d1, d2);
        this.M = 0.0F;
    }

    public OEntityArrow(OWorld oworld, OEntityLiving oentityliving, OEntityLiving oentityliving1, float f, float f1) {
        super(oworld);
        this.l = 10.0D;
        this.c = oentityliving;
        if (oentityliving instanceof OEntityPlayer) {
            this.a = 1;
        }

        this.u = oentityliving.u + (double) oentityliving.e() - 0.10000000149011612D;
        double d0 = oentityliving1.t - oentityliving.t;
        double d1 = oentityliving1.u + (double) oentityliving1.e() - 0.699999988079071D - this.u;
        double d2 = oentityliving1.v - oentityliving.v;
        double d3 = (double) OMathHelper.a(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D) {
            float f2 = (float) (Math.atan2(d2, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
            float f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / 3.1415927410125732D));
            double d4 = d0 / d3;
            double d5 = d2 / d3;

            this.b(oentityliving.t + d4, this.u, oentityliving.v + d5, f2, f3);
            this.M = 0.0F;
            float f4 = (float) d3 * 0.2F;

            this.c(d0, d1 + (double) f4, d2, f, f1);
        }
    }

    public OEntityArrow(OWorld oworld, OEntityLiving oentityliving, float f) {
        super(oworld);
        this.l = 10.0D;
        this.c = oentityliving;
        if (oentityliving instanceof OEntityPlayer) {
            this.a = 1;
        }

        this.a(0.5F, 0.5F);
        this.b(oentityliving.t, oentityliving.u + (double) oentityliving.e(), oentityliving.v, oentityliving.z, oentityliving.A);
        this.t -= (double) (OMathHelper.b(this.z / 180.0F * 3.1415927F) * 0.16F);
        this.u -= 0.10000000149011612D;
        this.v -= (double) (OMathHelper.a(this.z / 180.0F * 3.1415927F) * 0.16F);
        this.b(this.t, this.u, this.v);
        this.M = 0.0F;
        this.w = (double) (-OMathHelper.a(this.z / 180.0F * 3.1415927F) * OMathHelper.b(this.A / 180.0F * 3.1415927F));
        this.y = (double) (OMathHelper.b(this.z / 180.0F * 3.1415927F) * OMathHelper.b(this.A / 180.0F * 3.1415927F));
        this.x = (double) (-OMathHelper.a(this.A / 180.0F * 3.1415927F));
        this.c(this.w, this.x, this.y, f * 1.5F, 1.0F);
    }

    protected void a() {
        this.ag.a(16, Byte.valueOf((byte) 0));
    }

    public void c(double d0, double d1, double d2, float f, float f1) {
        float f2 = OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        d0 /= (double) f2;
        d1 /= (double) f2;
        d2 /= (double) f2;
        d0 += this.aa.nextGaussian() * 0.007499999832361937D * (double) f1;
        d1 += this.aa.nextGaussian() * 0.007499999832361937D * (double) f1;
        d2 += this.aa.nextGaussian() * 0.007499999832361937D * (double) f1;
        d0 *= (double) f;
        d1 *= (double) f;
        d2 *= (double) f;
        this.w = d0;
        this.x = d1;
        this.y = d2;
        float f3 = OMathHelper.a(d0 * d0 + d2 * d2);

        this.B = this.z = (float) (Math.atan2(d0, d2) * 180.0D / 3.1415927410125732D);
        this.C = this.A = (float) (Math.atan2(d1, (double) f3) * 180.0D / 3.1415927410125732D);
        this.j = 0;
    }

    public void j_() {
        super.j_();
        if (this.C == 0.0F && this.B == 0.0F) {
            float f = OMathHelper.a(this.w * this.w + this.y * this.y);

            this.B = this.z = (float) (Math.atan2(this.w, this.y) * 180.0D / 3.1415927410125732D);
            this.C = this.A = (float) (Math.atan2(this.x, (double) f) * 180.0D / 3.1415927410125732D);
        }

        int i = this.p.a(this.d, this.e, this.f);

        if (i > 0) {
            OBlock.p[i].a(this.p, this.d, this.e, this.f);
            OAxisAlignedBB oaxisalignedbb = OBlock.p[i].e(this.p, this.d, this.e, this.f);

            if (oaxisalignedbb != null && oaxisalignedbb.a(this.p.S().a(this.t, this.u, this.v))) {
                this.i = true;
            }
        }

        if (this.b > 0) {
            --this.b;
        }

        if (this.i) {
            int j = this.p.a(this.d, this.e, this.f);
            int k = this.p.h(this.d, this.e, this.f);

            if (j == this.g && k == this.h) {
                ++this.j;
                if (this.j == 1200) {
                    this.x();
                }
            } else {
                this.i = false;
                this.w *= (double) (this.aa.nextFloat() * 0.2F);
                this.x *= (double) (this.aa.nextFloat() * 0.2F);
                this.y *= (double) (this.aa.nextFloat() * 0.2F);
                this.j = 0;
                this.at = 0;
            }
        } else {
            ++this.at;
            OVec3 ovec3 = this.p.S().a(this.t, this.u, this.v);
            OVec3 ovec31 = this.p.S().a(this.t + this.w, this.u + this.x, this.v + this.y);
            OMovingObjectPosition omovingobjectposition = this.p.a(ovec3, ovec31, false, true);

            ovec3 = this.p.S().a(this.t, this.u, this.v);
            ovec31 = this.p.S().a(this.t + this.w, this.u + this.x, this.v + this.y);
            if (omovingobjectposition != null) {
                ovec31 = this.p.S().a(omovingobjectposition.f.c, omovingobjectposition.f.d, omovingobjectposition.f.e);
            }

            OEntity oentity = null;
            List list = this.p.b((OEntity) this, this.D.a(this.w, this.x, this.y).b(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;

            int l;
            float f1;

            for (l = 0; l < list.size(); ++l) {
                OEntity oentity1 = (OEntity) list.get(l);

                if (oentity1.L() && (oentity1 != this.c || this.at >= 5)) {
                    f1 = 0.3F;
                    OAxisAlignedBB oaxisalignedbb1 = oentity1.D.b((double) f1, (double) f1, (double) f1);
                    OMovingObjectPosition omovingobjectposition1 = oaxisalignedbb1.a(ovec3, ovec31);

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

            float f2;
            float f3;

            if (omovingobjectposition != null) {
                if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Arrow(this), omovingobjectposition == null || omovingobjectposition.g == null ? null : omovingobjectposition.g.getEntity())) {
                    if (omovingobjectposition.g != null) {
                        f2 = OMathHelper.a(this.w * this.w + this.x * this.x + this.y * this.y);
                        int i1 = OMathHelper.f((double) f2 * this.au);

                        if (this.d()) {
                            i1 += this.aa.nextInt(i1 / 2 + 2);
                        }

                        ODamageSource odamagesource = null;

                        if (this.c == null) {
                            odamagesource = ODamageSource.a(this, this);
                        } else {
                            odamagesource = ODamageSource.a(this, this.c);
                        }

                        if (this.af() && !(omovingobjectposition.g instanceof OEntityEnderman)) {
                            omovingobjectposition.g.c(5);
                        }

                        if (omovingobjectposition.g.a(odamagesource, i1)) {
                            if (omovingobjectposition.g instanceof OEntityLiving) {
                                OEntityLiving oentityliving = (OEntityLiving) omovingobjectposition.g;

                                if (!this.p.I) {
                                    oentityliving.r(oentityliving.bJ() + 1);
                                }

                                if (this.av > 0) {
                                    f3 = OMathHelper.a(this.w * this.w + this.y * this.y);
                                    if (f3 > 0.0F) {
                                        omovingobjectposition.g.g(this.w * (double) this.av * 0.6000000238418579D / (double) f3, 0.1D, this.y * (double) this.av * 0.6000000238418579D / (double) f3);
                                    }
                                }

                                if (this.c != null) {
                                    OEnchantmentThorns.a(this.c, oentityliving, this.aa);
                                }

                                if (this.c != null && omovingobjectposition.g != this.c && omovingobjectposition.g instanceof OEntityPlayer && this.c instanceof OEntityPlayerMP) {
                                    ((OEntityPlayerMP) this.c).a.b(new OPacket70GameEvent(6, 0));
                                }
                            }

                            this.a("random.bowhit", 1.0F, 1.2F / (this.aa.nextFloat() * 0.2F + 0.9F));
                            if (!(omovingobjectposition.g instanceof OEntityEnderman)) {
                                this.x();
                            }
                        } else {
                            this.w *= -0.10000000149011612D;
                            this.x *= -0.10000000149011612D;
                            this.y *= -0.10000000149011612D;
                            this.z += 180.0F;
                            this.B += 180.0F;
                            this.at = 0;
                        }
                    }
                } else {
                    this.d = omovingobjectposition.b;
                    this.e = omovingobjectposition.c;
                    this.f = omovingobjectposition.d;
                    this.g = this.p.a(this.d, this.e, this.f);
                    this.h = this.p.h(this.d, this.e, this.f);
                    this.w = (double) ((float) (omovingobjectposition.f.c - this.t));
                    this.x = (double) ((float) (omovingobjectposition.f.d - this.u));
                    this.y = (double) ((float) (omovingobjectposition.f.e - this.v));
                    f2 = OMathHelper.a(this.w * this.w + this.x * this.x + this.y * this.y);
                    this.t -= this.w / (double) f2 * 0.05000000074505806D;
                    this.u -= this.x / (double) f2 * 0.05000000074505806D;
                    this.v -= this.y / (double) f2 * 0.05000000074505806D;
                    this.a("random.bowhit", 1.0F, 1.2F / (this.aa.nextFloat() * 0.2F + 0.9F));
                    this.i = true;
                    this.b = 7;
                    this.e(false);
                    if (this.g != 0) {
                        OBlock.p[this.g].a(this.p, this.d, this.e, this.f, (OEntity) this);
                    }
                }
            }

            if (this.d()) {
                for (l = 0; l < 4; ++l) {
                    this.p.a("crit", this.t + this.w * (double) l / 4.0D, this.u + this.x * (double) l / 4.0D, this.v + this.y * (double) l / 4.0D, -this.w, -this.x + 0.2D, -this.y);
                }
            }

            this.t += this.w;
            this.u += this.x;
            this.v += this.y;
            f2 = OMathHelper.a(this.w * this.w + this.y * this.y);
            this.z = (float) (Math.atan2(this.w, this.y) * 180.0D / 3.1415927410125732D);

            for (this.A = (float) (Math.atan2(this.x, (double) f2) * 180.0D / 3.1415927410125732D); this.A - this.C < -180.0F; this.C -= 360.0F) {
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
            float f4 = 0.99F;

            f1 = 0.05F;
            if (this.H()) {
                for (int j1 = 0; j1 < 4; ++j1) {
                    f3 = 0.25F;
                    this.p.a("bubble", this.t - this.w * (double) f3, this.u - this.x * (double) f3, this.v - this.y * (double) f3, this.w, this.x, this.y);
                }

                f4 = 0.8F;
            }

            this.w *= (double) f4;
            this.x *= (double) f4;
            this.y *= (double) f4;
            this.x -= (double) f1;
            this.b(this.t, this.u, this.v);
            this.D();
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("xTile", (short) this.d);
        onbttagcompound.a("yTile", (short) this.e);
        onbttagcompound.a("zTile", (short) this.f);
        onbttagcompound.a("inTile", (byte) this.g);
        onbttagcompound.a("inData", (byte) this.h);
        onbttagcompound.a("shake", (byte) this.b);
        onbttagcompound.a("inGround", (byte) (this.i ? 1 : 0));
        onbttagcompound.a("pickup", (byte) this.a);
        onbttagcompound.a("damage", this.au);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.d = onbttagcompound.d("xTile");
        this.e = onbttagcompound.d("yTile");
        this.f = onbttagcompound.d("zTile");
        this.g = onbttagcompound.c("inTile") & 255;
        this.h = onbttagcompound.c("inData") & 255;
        this.b = onbttagcompound.c("shake") & 255;
        this.i = onbttagcompound.c("inGround") == 1;
        if (onbttagcompound.b("damage")) {
            this.au = onbttagcompound.h("damage");
        }

        if (onbttagcompound.b("pickup")) {
            this.a = onbttagcompound.c("pickup");
        } else if (onbttagcompound.b("player")) {
            this.a = onbttagcompound.n("player") ? 1 : 0;
        }
    }

    public void c_(OEntityPlayer oentityplayer) {
        if (!this.p.I && this.i && this.b <= 0) {
            boolean flag = this.a == 1 || this.a == 2 && oentityplayer.cd.d;

            if (this.a == 1 && !oentityplayer.bJ.a(new OItemStack(OItem.l, 1))) {
                flag = false;
            }

            if (flag) {
                this.a("random.pop", 0.2F, ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                oentityplayer.a((OEntity) this, 1);
                this.x();
            }
        }
    }

    protected boolean f_() {
        return false;
    }

    public void b(double d0) {
        this.au = d0;
    }

    public double c() {
        return this.au;
    }

    public void a(int i) {
        this.av = i;
    }

    public boolean aq() {
        return false;
    }

    public void e(boolean flag) {
        byte b0 = this.ag.a(16);

        if (flag) {
            this.ag.b(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.ag.b(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    public boolean d() {
        byte b0 = this.ag.a(16);

        return (b0 & 1) != 0;
    }
}
