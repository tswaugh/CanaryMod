import java.util.List;


public class OEntityBoat extends OEntity {

    private int a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    // CanaryMod Start
    Boat boat = new Boat(this);

    // CanaryMod end

    public OEntityBoat(OWorld oworld) {
        super(oworld);
        this.bf = true;
        this.b(1.5F, 0.6F);
        this.bF = this.bH / 2.0F;
    }

    protected boolean g_() {
        return false;
    }

    protected void b() {
        this.bY.a(17, new Integer(0));
        this.bY.a(18, new Integer(1));
        this.bY.a(19, new Integer(0));
    }

    public OAxisAlignedBB b_(OEntity oentity) {
        return oentity.bw;
    }

    public OAxisAlignedBB h() {
        return this.bw;
    }

    public boolean e_() {
        return true;
    }

    public OEntityBoat(OWorld oworld, double d0, double d1, double d2) {
        this(oworld);
        this.c(d0, d1 + (double) this.bF, d2);
        this.bp = 0.0D;
        this.bq = 0.0D;
        this.br = 0.0D;
        this.bj = d0;
        this.bk = d1;
        this.bl = d2;
        
        // CanaryMod: Creation of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, boat);
    }

    public double x_() {
        return (double) this.bH * 0.0D - 0.30000001192092896D;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        // CanaryMod: Attack of the boat
        BaseEntity entity = null;
        
        if (odamagesource != null && odamagesource.a() != null) {
            entity = new BaseEntity(odamagesource.a());
        }
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, entity, i)) {
            return true;
        }
        
        if (!this.bi.F && !this.bE) {
            this.d(-this.m());
            this.c(10);
            this.b(this.k() + i * 10);
            this.aW();
            if (this.k() > 40) {
                if (this.bg != null) {
                    this.bg.b((OEntity) this);
                }

                int j;

                for (j = 0; j < 3; ++j) {
                    this.a(OBlock.x.bO, 1, 0.0F);
                }

                for (j = 0; j < 2; ++j) {
                    this.a(OItem.C.bP, 1, 0.0F);
                }

                this.X();
            }

            return true;
        } else {
            return true;
        }
    }

    public boolean o_() {
        return !this.bE;
    }

    public void F_() {
        super.F_();
        // CanaryMod: Update of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);
        
        double prevX = bj;
        double prevY = bk;
        double prevZ = bl;
        
        if (this.l() > 0) {
            this.c(this.l() - 1);
        }

        if (this.k() > 0) {
            this.b(this.k() - 1);
        }

        this.bj = this.bm;
        this.bk = this.bn;
        this.bl = this.bo;
        byte b0 = 5;
        double d0 = 0.0D;

        for (int i = 0; i < b0; ++i) {
            double d1 = this.bw.b + (this.bw.e - this.bw.b) * (double) (i + 0) / (double) b0 - 0.125D;
            double d2 = this.bw.b + (this.bw.e - this.bw.b) * (double) (i + 1) / (double) b0 - 0.125D;
            OAxisAlignedBB oaxisalignedbb = OAxisAlignedBB.b(this.bw.a, d1, this.bw.c, this.bw.d, d2, this.bw.f);

            if (this.bi.b(oaxisalignedbb, OMaterial.g)) {
                d0 += 1.0D / (double) b0;
            }
        }

        double d3 = Math.sqrt(this.bp * this.bp + this.br * this.br);
        double d4;
        double d5;

        if (d3 > 0.15D) {
            d4 = Math.cos((double) this.bs * 3.141592653589793D / 180.0D);
            d5 = Math.sin((double) this.bs * 3.141592653589793D / 180.0D);

            for (int j = 0; (double) j < 1.0D + d3 * 60.0D; ++j) {
                double d6 = (double) (this.bS.nextFloat() * 2.0F - 1.0F);
                double d7 = (double) (this.bS.nextInt(2) * 2 - 1) * 0.7D;
                double d8;
                double d9;

                if (this.bS.nextBoolean()) {
                    d8 = this.bm - d4 * d6 * 0.8D + d5 * d7;
                    d9 = this.bo - d5 * d6 * 0.8D - d4 * d7;
                    this.bi.a("splash", d8, this.bn - 0.125D, d9, this.bp, this.bq, this.br);
                } else {
                    d8 = this.bm + d4 + d5 * d6 * 0.7D;
                    d9 = this.bo + d5 - d4 * d6 * 0.7D;
                    this.bi.a("splash", d8, this.bn - 0.125D, d9, this.bp, this.bq, this.br);
                }
            }
        }

        double d10;
        double d11;

        if (this.bi.F) {
            if (this.a > 0) {
                d4 = this.bm + (this.b - this.bm) / (double) this.a;
                d5 = this.bn + (this.c - this.bn) / (double) this.a;
                d10 = this.bo + (this.d - this.bo) / (double) this.a;

                for (d11 = this.e - (double) this.bs; d11 < -180.0D; d11 += 360.0D) {
                    ;
                }

                while (d11 >= 180.0D) {
                    d11 -= 360.0D;
                }

                this.bs = (float) ((double) this.bs + d11 / (double) this.a);
                this.bt = (float) ((double) this.bt + (this.f - (double) this.bt) / (double) this.a);
                --this.a;
                this.c(d4, d5, d10);
                this.c(this.bs, this.bt);
            } else {
                d4 = this.bm + this.bp;
                d5 = this.bn + this.bq;
                d10 = this.bo + this.br;
                this.c(d4, d5, d10);
                if (this.bx) {
                    this.bp *= 0.5D;
                    this.bq *= 0.5D;
                    this.br *= 0.5D;
                }

                this.bp *= 0.9900000095367432D;
                this.bq *= 0.949999988079071D;
                this.br *= 0.9900000095367432D;
            }

        } else {
            if (d0 < 1.0D) {
                d4 = d0 * 2.0D - 1.0D;
                this.bq += 0.03999999910593033D * d4;
            } else {
                if (this.bq < 0.0D) {
                    this.bq /= 2.0D;
                }

                this.bq += 0.007000000216066837D;
            }

            if (this.bg != null) {
                this.bp += this.bg.bp * 0.2D;
                this.br += this.bg.br * 0.2D;
            }

            d4 = 0.4D;
            if (this.bp < -d4) {
                this.bp = -d4;
            }

            if (this.bp > d4) {
                this.bp = d4;
            }

            if (this.br < -d4) {
                this.br = -d4;
            }

            if (this.br > d4) {
                this.br = d4;
            }

            if (this.bx) {
                this.bp *= 0.5D;
                this.bq *= 0.5D;
                this.br *= 0.5D;
            }

            this.a(this.bp, this.bq, this.br);
            if (this.by && d3 > 0.2D) {
                if (!this.bi.F) {
                    this.X();

                    int k;

                    for (k = 0; k < 3; ++k) {
                        this.a(OBlock.x.bO, 1, 0.0F);
                    }

                    for (k = 0; k < 2; ++k) {
                        this.a(OItem.C.bP, 1, 0.0F);
                    }
                }
            } else {
                this.bp *= 0.9900000095367432D;
                this.bq *= 0.949999988079071D;
                this.br *= 0.9900000095367432D;
            }

            this.bt = 0.0F;
            d5 = (double) this.bs;
            d10 = this.bj - this.bm;
            d11 = this.bl - this.bo;
            if (d10 * d10 + d11 * d11 > 0.001D) {
                d5 = (double) ((float) (Math.atan2(d11, d10) * 180.0D / 3.141592653589793D));
            }

            double d12;

            for (d12 = d5 - (double) this.bs; d12 >= 180.0D; d12 -= 360.0D) {
                ;
            }

            while (d12 < -180.0D) {
                d12 += 360.0D;
            }

            if (d12 > 20.0D) {
                d12 = 20.0D;
            }

            if (d12 < -20.0D) {
                d12 = -20.0D;
            }

            this.bs = (float) ((double) this.bs + d12);
            this.c(this.bs, this.bt);
            
            // CanaryMod: Change of the boat
            if ((int) bj != (int) prevX || (int) bk != (int) prevY || (int) bl != (int) prevZ) {
                manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, boat, (int) bj, (int) bk, (int) bl);
            }
            
            List list = this.bi.b((OEntity) this, this.bw.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
            int l;

            if (list != null && list.size() > 0) {
                for (l = 0; l < list.size(); ++l) {
                    OEntity oentity = (OEntity) list.get(l);

                    if (oentity != this.bg && oentity.e_() && oentity instanceof OEntityBoat) {
                        oentity.k(this);
                    }
                }
            }

            for (l = 0; l < 4; ++l) {
                int i1 = OMathHelper.b(this.bm + ((double) (l % 2) - 0.5D) * 0.8D);
                int j1 = OMathHelper.b(this.bn);
                int k1 = OMathHelper.b(this.bo + ((double) (l / 2) - 0.5D) * 0.8D);

                if (this.bi.a(i1, j1, k1) == OBlock.aS.bO) {
                    this.bi.e(i1, j1, k1, 0);
                }
            }

            if (this.bg != null && this.bg.bE) {
                this.bg = null;
            }

        }
    }

    public void i_() {
        if (this.bg != null) {
            double d0 = Math.cos((double) this.bs * 3.141592653589793D / 180.0D) * 0.4D;
            double d1 = Math.sin((double) this.bs * 3.141592653589793D / 180.0D) * 0.4D;

            this.bg.c(this.bm + d0, this.bn + this.x_() + this.bg.W(), this.bo + d1);
        }
    }

    protected void b(ONBTTagCompound onbttagcompound) {}

    protected void a(ONBTTagCompound onbttagcompound) {}

    public boolean b(OEntityPlayer oentityplayer) {
        // CanaryMod: Entering the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, boat, oentityplayer.entity);
        
        if (this.bg != null && this.bg instanceof OEntityPlayer && this.bg != oentityplayer) {
            return true;
        } else {
            if (!this.bi.F) {
                oentityplayer.b((OEntity) this);
            }

            return true;
        }
    }

    public void b(int i) {
        this.bY.b(19, Integer.valueOf(i));
    }

    public int k() {
        return this.bY.c(19);
    }

    public void c(int i) {
        this.bY.b(17, Integer.valueOf(i));
    }

    public int l() {
        return this.bY.c(17);
    }

    public void d(int i) {
        this.bY.b(18, Integer.valueOf(i));
    }

    public int m() {
        return this.bY.c(18);
    }
}
