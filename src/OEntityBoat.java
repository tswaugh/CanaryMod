import java.util.List;

public class OEntityBoat extends OEntity {

    private boolean a;
    private double b;
    private int c;
    private double d;
    private double e;
    private double f;
    private double g;
    private double h;

    // CanaryMod Start
    Boat boat = new Boat(this);
    // CanaryMod end

    public OEntityBoat(OWorld oworld) {
        super(oworld);
        this.a = true;
        this.b = 0.07D;
        this.m = true;
        this.a(1.5F, 0.6F);
        this.N = this.P / 2.0F;
    }

    protected boolean f_() {
        return false;
    }

    protected void a() {
        this.ah.a(17, new Integer(0));
        this.ah.a(18, new Integer(1));
        this.ah.a(19, new Integer(0));
    }

    public OAxisAlignedBB g(OEntity oentity) {
        return oentity.E;
    }

    public OAxisAlignedBB D() {
        return this.E;
    }

    public boolean L() {
        return true;
    }

    public OEntityBoat(OWorld oworld, double d0, double d1, double d2) {
        this(oworld);
        this.b(d0, d1 + (double) this.N, d2);
        this.x = 0.0D;
        this.y = 0.0D;
        this.z = 0.0D;
        this.r = d0;
        this.s = d1;
        this.t = d2;

        // CanaryMod: Creation of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, boat);

    }

    public double W() {
        return (double) this.P * 0.0D - 0.30000001192092896D;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        // CanaryMod: Attack of the boat
        BaseEntity entity = null;

        if (odamagesource != null && odamagesource.i() != null) {
            entity = new BaseEntity(odamagesource.i());
        }
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, entity, i)) {
            return true;
        }


        if (this.aq()) {
            return false;
        } else if (!this.q.I && !this.M) {
            this.h(-this.h());
            this.b(10);
            this.a(this.d() + i * 10);
            this.J();
            boolean flag = odamagesource.i() instanceof OEntityPlayer && ((OEntityPlayer) odamagesource.i()).ce.d;

            if (flag || this.d() > 40) {
                if (this.n != null) {
                    this.n.a((OEntity) this);
                }

                if (!flag) {
                    this.a(OItem.aF.cp, 1, 0.0F);
                }

                this.w();
            }

            return true;
        } else {
            return true;
        }
    }

    public boolean K() {
        return !this.M;
    }

    public void l_() {
        super.l_();
        // CanaryMod: Update of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);

        double prevX = this.r;
        double prevY = this.s;
        double prevZ = this.t;

        if (this.g() > 0) {
            this.b(this.g() - 1);
        }

        if (this.d() > 0) {
            this.a(this.d() - 1);
        }

        this.r = this.u;
        this.s = this.v;
        this.t = this.w;
        byte b0 = 5;
        double d0 = 0.0D;

        for (int i = 0; i < b0; ++i) {
            double d1 = this.E.b + (this.E.e - this.E.b) * (double) (i + 0) / (double) b0 - 0.125D;
            double d2 = this.E.b + (this.E.e - this.E.b) * (double) (i + 1) / (double) b0 - 0.125D;
            OAxisAlignedBB oaxisalignedbb = OAxisAlignedBB.a().a(this.E.a, d1, this.E.c, this.E.d, d2, this.E.f);

            if (this.q.b(oaxisalignedbb, OMaterial.h)) {
                d0 += 1.0D / (double) b0;
            }
        }

        double d3 = Math.sqrt(this.x * this.x + this.z * this.z);
        double d4;
        double d5;

        if (d3 > 0.26249999999999996D) {
            d4 = Math.cos((double) this.A * 3.141592653589793D / 180.0D);
            d5 = Math.sin((double) this.A * 3.141592653589793D / 180.0D);

            for (int j = 0; (double) j < 1.0D + d3 * 60.0D; ++j) {
                double d6 = (double) (this.ab.nextFloat() * 2.0F - 1.0F);
                double d7 = (double) (this.ab.nextInt(2) * 2 - 1) * 0.7D;
                double d8;
                double d9;

                if (this.ab.nextBoolean()) {
                    d8 = this.u - d4 * d6 * 0.8D + d5 * d7;
                    d9 = this.w - d5 * d6 * 0.8D - d4 * d7;
                    this.q.a("splash", d8, this.v - 0.125D, d9, this.x, this.y, this.z);
                } else {
                    d8 = this.u + d4 + d5 * d6 * 0.7D;
                    d9 = this.w + d5 - d4 * d6 * 0.7D;
                    this.q.a("splash", d8, this.v - 0.125D, d9, this.x, this.y, this.z);
                }
            }
        }

        double d10;
        double d11;

        if (this.q.I && this.a) {
            if (this.c > 0) {
                d4 = this.u + (this.d - this.u) / (double) this.c;
                d5 = this.v + (this.e - this.v) / (double) this.c;
                d10 = this.w + (this.f - this.w) / (double) this.c;
                d11 = OMathHelper.g(this.g - (double) this.A);
                this.A = (float) ((double) this.A + d11 / (double) this.c);
                this.B = (float) ((double) this.B + (this.h - (double) this.B) / (double) this.c);
                --this.c;
                this.b(d4, d5, d10);
                this.b(this.A, this.B);
            } else {
                d4 = this.u + this.x;
                d5 = this.v + this.y;
                d10 = this.w + this.z;
                this.b(d4, d5, d10);
                if (this.F) {
                    this.x *= 0.5D;
                    this.y *= 0.5D;
                    this.z *= 0.5D;
                }

                this.x *= 0.9900000095367432D;
                this.y *= 0.949999988079071D;
                this.z *= 0.9900000095367432D;
            }
        } else {
            if (d0 < 1.0D) {
                d4 = d0 * 2.0D - 1.0D;
                this.y += 0.03999999910593033D * d4;
            } else {
                if (this.y < 0.0D) {
                    this.y /= 2.0D;
                }

                this.y += 0.007000000216066837D;
            }

            if (this.n != null) {
                this.x += this.n.x * this.b;
                this.z += this.n.z * this.b;
            }

            d4 = Math.sqrt(this.x * this.x + this.z * this.z);
            if (d4 > 0.35D) {
                d5 = 0.35D / d4;
                this.x *= d5;
                this.z *= d5;
                d4 = 0.35D;
            }

            if (d4 > d3 && this.b < 0.35D) {
                this.b += (0.35D - this.b) / 35.0D;
                if (this.b > 0.35D) {
                    this.b = 0.35D;
                }
            } else {
                this.b -= (this.b - 0.07D) / 35.0D;
                if (this.b < 0.07D) {
                    this.b = 0.07D;
                }
            }

            if (this.F) {
                this.x *= 0.5D;
                this.y *= 0.5D;
                this.z *= 0.5D;
            }

            this.d(this.x, this.y, this.z);
            if (this.G && d3 > 0.2D) {
                if (!this.q.I) {
                    this.w();

                    int k;

                    for (k = 0; k < 3; ++k) {
                        this.a(OBlock.B.cz, 1, 0.0F);
                    }

                    for (k = 0; k < 2; ++k) {
                        this.a(OItem.E.cp, 1, 0.0F);
                    }
                }
            } else {
                this.x *= 0.9900000095367432D;
                this.y *= 0.949999988079071D;
                this.z *= 0.9900000095367432D;
            }

            this.B = 0.0F;
            d5 = (double) this.A;
            d10 = this.r - this.u;
            d11 = this.t - this.w;
            if (d10 * d10 + d11 * d11 > 0.001D) {
                d5 = (double) ((float) (Math.atan2(d11, d10) * 180.0D / 3.141592653589793D));
            }

            double d12 = OMathHelper.g(d5 - (double) this.A);

            if (d12 > 20.0D) {
                d12 = 20.0D;
            }

            if (d12 < -20.0D) {
                d12 = -20.0D;
            }

            this.A = (float) ((double) this.A + d12);
            this.b(this.A, this.B);
            if (!this.q.I) {
                if ((int) this.u != (int) prevX || (int) this.v != (int) prevY || (int) this.w != (int) prevZ) {
                    manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, boat, (int) this.t, (int) this.s, (int) this.t);
                }

                List list = this.q.b((OEntity) this, this.E.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
                int l;

                if (list != null && !list.isEmpty()) {
                    for (l = 0; l < list.size(); ++l) {
                        OEntity oentity = (OEntity) list.get(l);

                        if (oentity != this.n && oentity.L() && oentity instanceof OEntityBoat) {
                            oentity.f((OEntity) this);
                        }
                    }
                }

                for (l = 0; l < 4; ++l) {
                    int i1 = OMathHelper.c(this.u + ((double) (l % 2) - 0.5D) * 0.8D);
                    int j1 = OMathHelper.c(this.w + ((double) (l / 2) - 0.5D) * 0.8D);

                    for (int k1 = 0; k1 < 2; ++k1) {
                        int l1 = OMathHelper.c(this.v) + k1;
                        int i2 = this.q.a(i1, l1, j1);

                        if (i2 == OBlock.aW.cz) {
                            this.q.i(i1, l1, j1);
                        } else if (i2 == OBlock.bD.cz) {
                            this.q.a(i1, l1, j1, true);
                        }
                    }
                }

                if (this.n != null && this.n.M) {
                    this.n = null;
                }
            }
        }
    }

    public void U() {
        if (this.n != null) {
            double d0 = Math.cos((double) this.A * 3.141592653589793D / 180.0D) * 0.4D;
            double d1 = Math.sin((double) this.A * 3.141592653589793D / 180.0D) * 0.4D;

            this.n.b(this.u + d0, this.v + this.W() + this.n.V(), this.w + d1);
        }
    }

    protected void b(ONBTTagCompound onbttagcompound) {}

    protected void a(ONBTTagCompound onbttagcompound) {}

    public boolean a_(OEntityPlayer oentityplayer) {
        // CanaryMod: Entering the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, boat, oentityplayer.entity);

        if (this.n != null && this.n instanceof OEntityPlayer && this.n != oentityplayer) {
            return true;
        } else {
            if (!this.q.I) {
                oentityplayer.a((OEntity) this);
            }

            return true;
        }
    }

    public void a(int i) {
        this.ah.b(19, Integer.valueOf(i));
    }

    public int d() {
        return this.ah.c(19);
    }

    public void b(int i) {
        this.ah.b(17, Integer.valueOf(i));
    }

    public int g() {
        return this.ah.c(17);
    }

    public void h(int i) {
        this.ah.b(18, Integer.valueOf(i));
    }

    public int h() {
        return this.ah.c(18);
    }

    @Override
    public Boat getEntity() {
        return boat;
    } //
}
