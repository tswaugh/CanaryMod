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

    public OEntityBoat(OWorld var1) {
        super(var1);
        this.bc = true;
        this.b(1.5F, 0.6F);
        this.bC = this.bE / 2.0F;
    }

    protected boolean g_() {
        return false;
    }

    protected void b() {
        this.bV.a(17, new Integer(0));
        this.bV.a(18, new Integer(1));
        this.bV.a(19, new Integer(0));
    }

    public OAxisAlignedBB a_(OEntity var1) {
        return var1.bt;
    }

    public OAxisAlignedBB h_() {
        return this.bt;
    }

    public boolean f_() {
        return true;
    }

    public OEntityBoat(OWorld var1, double var2, double var4, double var6) {
        this(var1);
        this.c(var2, var4 + (double) this.bC, var6);
        this.bm = 0.0D;
        this.bn = 0.0D;
        this.bo = 0.0D;
        this.bg = var2;
        this.bh = var4;
        this.bi = var6;
      
        // CanaryMod: Creation of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, boat);
    }

    public double q() {
        return (double) this.bE * 0.0D - 0.30000001192092896D;
    }

    public boolean a(ODamageSource var1, int var2) {
        // CanaryMod: Attack of the boat
        BaseEntity entity = null;

        if (var1 != null && var1.a() != null) {
            entity = new BaseEntity(var1.a());
        }
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, entity, var2)) {
            return true;
        }

        if (!this.bf.I && !this.bB) {
            this.d(-this.l());
            this.c(10);
            this.b(this.j() + var2 * 10);
            this.aB();
            if (this.j() > 40) {
                if (this.bd != null) {
                    this.bd.b((OEntity) this);
                }

                int var3;

                for (var3 = 0; var3 < 3; ++var3) {
                    this.a(OBlock.z.bO, 1, 0.0F);
                }

                for (var3 = 0; var3 < 2; ++var3) {
                    this.a(OItem.C.bM, 1, 0.0F);
                }

                this.S();
            }

            return true;
        } else {
            return true;
        }
    }

    public boolean e_() {
        return !this.bB;
    }

    public void w_() {
        super.w_();
        // CanaryMod: Update of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);
      
        double prevX = bj;
        double prevY = bk;
        double prevZ = bl;
        if (this.k() > 0) {
            this.c(this.k() - 1);
        }

        if (this.j() > 0) {
            this.b(this.j() - 1);
        }

        this.bg = this.bj;
        this.bh = this.bk;
        this.bi = this.bl;
        byte var1 = 5;
        double var2 = 0.0D;

        for (int var4 = 0; var4 < var1; ++var4) {
            double var5 = this.bt.b + (this.bt.e - this.bt.b) * (double) (var4 + 0) / (double) var1 - 0.125D;
            double var7 = this.bt.b + (this.bt.e - this.bt.b) * (double) (var4 + 1) / (double) var1 - 0.125D;
            OAxisAlignedBB var9 = OAxisAlignedBB.b(this.bt.a, var5, this.bt.c, this.bt.d, var7, this.bt.f);

            if (this.bf.b(var9, OMaterial.g)) {
                var2 += 1.0D / (double) var1;
            }
        }

        double var10 = Math.sqrt(this.bm * this.bm + this.bo * this.bo);
        double var12;
        double var14;

        if (var10 > 0.15D) {
            var12 = Math.cos((double) this.bp * 3.141592653589793D / 180.0D);
            var14 = Math.sin((double) this.bp * 3.141592653589793D / 180.0D);

            for (int var16 = 0; (double) var16 < 1.0D + var10 * 60.0D; ++var16) {
                double var17 = (double) (this.bP.nextFloat() * 2.0F - 1.0F);
                double var19 = (double) (this.bP.nextInt(2) * 2 - 1) * 0.7D;
                double var21;
                double var23;

                if (this.bP.nextBoolean()) {
                    var21 = this.bj - var12 * var17 * 0.8D + var14 * var19;
                    var23 = this.bl - var14 * var17 * 0.8D - var12 * var19;
                    this.bf.a("splash", var21, this.bk - 0.125D, var23, this.bm, this.bn, this.bo);
                } else {
                    var21 = this.bj + var12 + var14 * var17 * 0.7D;
                    var23 = this.bl + var14 - var12 * var17 * 0.7D;
                    this.bf.a("splash", var21, this.bk - 0.125D, var23, this.bm, this.bn, this.bo);
                }
            }
        }

        double var25;
        double var27;

        if (this.bf.I) {
            if (this.a > 0) {
                var12 = this.bj + (this.b - this.bj) / (double) this.a;
                var14 = this.bk + (this.c - this.bk) / (double) this.a;
                var25 = this.bl + (this.d - this.bl) / (double) this.a;

                for (var27 = this.e - (double) this.bp; var27 < -180.0D; var27 += 360.0D) {
                    ;
                }

                while (var27 >= 180.0D) {
                    var27 -= 360.0D;
                }

                this.bp = (float) ((double) this.bp + var27 / (double) this.a);
                this.bq = (float) ((double) this.bq + (this.f - (double) this.bq) / (double) this.a);
                --this.a;
                this.c(var12, var14, var25);
                this.c(this.bp, this.bq);
            } else {
                var12 = this.bj + this.bm;
                var14 = this.bk + this.bn;
                var25 = this.bl + this.bo;
                this.c(var12, var14, var25);
                if (this.bu) {
                    this.bm *= 0.5D;
                    this.bn *= 0.5D;
                    this.bo *= 0.5D;
                }

                this.bm *= 0.9900000095367432D;
                this.bn *= 0.949999988079071D;
                this.bo *= 0.9900000095367432D;
            }

        } else {
            if (var2 < 1.0D) {
                var12 = var2 * 2.0D - 1.0D;
                this.bn += 0.03999999910593033D * var12;
            } else {
                if (this.bn < 0.0D) {
                    this.bn /= 2.0D;
                }

                this.bn += 0.007000000216066837D;
            }

            if (this.bd != null) {
                this.bm += this.bd.bm * 0.2D;
                this.bo += this.bd.bo * 0.2D;
            }

            var12 = 0.4D;
            if (this.bm < -var12) {
                this.bm = -var12;
            }

            if (this.bm > var12) {
                this.bm = var12;
            }

            if (this.bo < -var12) {
                this.bo = -var12;
            }

            if (this.bo > var12) {
                this.bo = var12;
            }

            if (this.bu) {
                this.bm *= 0.5D;
                this.bn *= 0.5D;
                this.bo *= 0.5D;
            }

            this.a(this.bm, this.bn, this.bo);
            if (this.bv && var10 > 0.2D) {
                if (!this.bf.I) {
                    this.S();

                    int var29;

                    for (var29 = 0; var29 < 3; ++var29) {
                        this.a(OBlock.z.bO, 1, 0.0F);
                    }

                    for (var29 = 0; var29 < 2; ++var29) {
                        this.a(OItem.C.bM, 1, 0.0F);
                    }
                }
            } else {
                this.bm *= 0.9900000095367432D;
                this.bn *= 0.949999988079071D;
                this.bo *= 0.9900000095367432D;
            }

            this.bq = 0.0F;
            var14 = (double) this.bp;
            var25 = this.bg - this.bj;
            var27 = this.bi - this.bl;
            if (var25 * var25 + var27 * var27 > 0.001D) {
                var14 = (double) ((float) (Math.atan2(var27, var25) * 180.0D / 3.141592653589793D));
            }

            double var30;

            for (var30 = var14 - (double) this.bp; var30 >= 180.0D; var30 -= 360.0D) {
                ;
            }

            while (var30 < -180.0D) {
                var30 += 360.0D;
            }

            if (var30 > 20.0D) {
                var30 = 20.0D;
            }

            if (var30 < -20.0D) {
                var30 = -20.0D;
            }

            this.bp = (float) ((double) this.bp + var30);
            this.c(this.bp, this.bq);
         
            // CanaryMod: Change of the boat
            if ((int) bj != (int) prevX || (int) bk != (int) prevY || (int) bl != (int) prevZ) {
                manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, boat, (int) bj, (int) bk, (int) bl);
            }
            
            List var32 = this.bf.b((OEntity) this, this.bt.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
            int var33;

            if (var32 != null && var32.size() > 0) {
                for (var33 = 0; var33 < var32.size(); ++var33) {
                    OEntity var34 = (OEntity) var32.get(var33);

                    if (var34 != this.bd && var34.f_() && var34 instanceof OEntityBoat) {
                        var34.j(this);
                    }
                }
            }

            for (var33 = 0; var33 < 4; ++var33) {
                int var37 = OMathHelper.b(this.bj + ((double) (var33 % 2) - 0.5D) * 0.8D);
                int var35 = OMathHelper.b(this.bk);
                int var36 = OMathHelper.b(this.bl + ((double) (var33 / 2) - 0.5D) * 0.8D);

                if (this.bf.a(var37, var35, var36) == OBlock.aU.bO) {
                    this.bf.e(var37, var35, var36, 0);
                }
            }

            if (this.bd != null && this.bd.bB) {
                this.bd = null;
            }

        }
    }

    public void i() {
        if (this.bd != null) {
            double var1 = Math.cos((double) this.bp * 3.141592653589793D / 180.0D) * 0.4D;
            double var3 = Math.sin((double) this.bp * 3.141592653589793D / 180.0D) * 0.4D;

            this.bd.c(this.bj + var1, this.bk + this.q() + this.bd.R(), this.bl + var3);
        }
    }

    protected void b(ONBTTagCompound var1) {}

    protected void a(ONBTTagCompound var1) {}

    public boolean b(OEntityPlayer var1) {
        // CanaryMod: Entering the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, boat, var1.entity);

        if (this.bd != null && this.bd instanceof OEntityPlayer && this.bd != var1) {
            return true;
        } else {
            if (!this.bf.I) {
                var1.b((OEntity) this);
            }

            return true;
        }
    }

    public void b(int var1) {
        this.bV.b(19, Integer.valueOf(var1));
    }

    public int j() {
        return this.bV.c(19);
    }

    public void c(int var1) {
        this.bV.b(17, Integer.valueOf(var1));
    }

    public int k() {
        return this.bV.c(17);
    }

    public void d(int var1) {
        this.bV.b(18, Integer.valueOf(var1));
    }

    public int l() {
        return this.bV.c(18);
    }
}
