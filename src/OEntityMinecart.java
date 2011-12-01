import java.util.List;


public class OEntityMinecart extends OEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] d;
    private int e;
    private boolean f;
    public int a;
    public double b;
    public double c;
    private static final int[][][] g = new int[][][] { { { 0, 0, -1}, { 0, 0, 1}}, { { -1, 0, 0}, { 1, 0, 0}}, { { -1, -1, 0}, { 1, 0, 0}}, { { -1, 0, 0}, { 1, -1, 0}}, { { 0, 0, -1}, { 0, -1, 1}}, { { 0, -1, -1}, { 0, 0, 1}}, { { 0, 0, 1}, { 1, 0, 0}}, { { 0, 0, 1}, { -1, 0, 0}}, { { 0, 0, -1}, { -1, 0, 0}}, { { 0, 0, -1}, { 1, 0, 0}}};
    private int h;
    private double i;
    private double j;
    private double k;
    private double l;
    private double m;
    // CanaryMod start
    private String name = "Minecart";
    Minecart cart = new Minecart(this);

    // CanaryMod end

    public OEntityMinecart(OWorld var1) {
        super(var1);
        this.d = new OItemStack[36];
        this.e = 0;
        this.f = false;
        this.bc = true;
        this.b(0.98F, 0.7F);
        this.bC = this.bE / 2.0F;
    }

    protected boolean g_() {
        return false;
    }

    protected void b() {
        this.bV.a(16, new Byte((byte) 0));
        this.bV.a(17, new Integer(0));
        this.bV.a(18, new Integer(1));
        this.bV.a(19, new Integer(0));
    }

    public OAxisAlignedBB a_(OEntity var1) {
        return var1.bt;
    }

    public OAxisAlignedBB h_() {
        return null;
    }

    public boolean f_() {
        return true;
    }

    public OEntityMinecart(OWorld var1, double var2, double var4, double var6, int var8) {
        this(var1);
        this.c(var2, var4 + (double) this.bC, var6);
        this.bm = 0.0D;
        this.bn = 0.0D;
        this.bo = 0.0D;
        this.bg = var2;
        this.bh = var4;
        this.bi = var6;
        this.a = var8;
        // CanaryMod: Creation of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, cart);
    }

    public double q() {
        return (double) this.bE * 0.0D - 0.30000001192092896D;
    }

    public boolean a(ODamageSource var1, int var2) {
        // CanaryMod: Attack of the cart
        BaseEntity entity = null;

        if (var1 != null && var1.a() != null) {
            entity = new BaseEntity(var1.a());
        }
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, cart, entity, var2)) {
            return true;
        }
        
        if (!this.bf.I && !this.bB) {
            this.d(-this.m());
            this.c(10);
            this.aB();
            this.b(this.k() + var2 * 10);
            if (this.k() > 40) {
                if (this.bd != null) {
                    this.bd.b((OEntity) this);
                }

                this.S();
                this.a(OItem.ay.bM, 1, 0.0F);
                if (this.a == 1) {
                    OEntityMinecart var3 = this;

                    for (int var4 = 0; var4 < var3.c(); ++var4) {
                        OItemStack var5 = var3.c_(var4);

                        if (var5 != null) {
                            float var6 = this.bP.nextFloat() * 0.8F + 0.1F;
                            float var7 = this.bP.nextFloat() * 0.8F + 0.1F;
                            float var8 = this.bP.nextFloat() * 0.8F + 0.1F;

                            while (var5.a > 0) {
                                int var9 = this.bP.nextInt(21) + 10;

                                if (var9 > var5.a) {
                                    var9 = var5.a;
                                }

                                var5.a -= var9;
                                OEntityItem var10 = new OEntityItem(this.bf, this.bj + (double) var6, this.bk + (double) var7, this.bl + (double) var8, new OItemStack(var5.c, var9, var5.h()));
                                float var11 = 0.05F;

                                var10.bm = (double) ((float) this.bP.nextGaussian() * var11);
                                var10.bn = (double) ((float) this.bP.nextGaussian() * var11 + 0.2F);
                                var10.bo = (double) ((float) this.bP.nextGaussian() * var11);
                                this.bf.b((OEntity) var10);
                            }
                        }
                    }

                    this.a(OBlock.aw.bO, 1, 0.0F);
                } else if (this.a == 2) {
                    this.a(OBlock.aD.bO, 1, 0.0F);
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public boolean e_() {
        return !this.bB;
    }

    public void S() {
        // CanaryMod: Destruction of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_DESTROYED, cart);
        
        for (int var1 = 0; var1 < this.c(); ++var1) {
            OItemStack var2 = this.c_(var1);

            if (var2 != null) {
                float var3 = this.bP.nextFloat() * 0.8F + 0.1F;
                float var4 = this.bP.nextFloat() * 0.8F + 0.1F;
                float var5 = this.bP.nextFloat() * 0.8F + 0.1F;

                while (var2.a > 0) {
                    int var6 = this.bP.nextInt(21) + 10;

                    if (var6 > var2.a) {
                        var6 = var2.a;
                    }

                    var2.a -= var6;
                    OEntityItem var7 = new OEntityItem(this.bf, this.bj + (double) var3, this.bk + (double) var4, this.bl + (double) var5, new OItemStack(var2.c, var6, var2.h()));
                    float var8 = 0.05F;

                    var7.bm = (double) ((float) this.bP.nextGaussian() * var8);
                    var7.bn = (double) ((float) this.bP.nextGaussian() * var8 + 0.2F);
                    var7.bo = (double) ((float) this.bP.nextGaussian() * var8);
                    this.bf.b((OEntity) var7);
                }
            }
        }

        super.S();
    }

    public void w_() {
        // CanaryMod: Update of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, cart);

        if (this.l() > 0) {
            this.c(this.l() - 1);
        }

        double prevX = bj;
        double prevY = bk;
        double prevZ = bl;
      
        if (this.k() > 0) {
            this.b(this.k() - 1);
        }

        if (this.j() && this.bP.nextInt(4) == 0) {
            this.bf.a("largesmoke", this.bj, this.bk + 0.8D, this.bl, 0.0D, 0.0D, 0.0D);
        }

        if (this.bf.I) {
            if (this.h > 0) {
                double var1 = this.bj + (this.i - this.bj) / (double) this.h;
                double var3 = this.bk + (this.j - this.bk) / (double) this.h;
                double var5 = this.bl + (this.k - this.bl) / (double) this.h;

                double var7;

                for (var7 = this.l - (double) this.bp; var7 < -180.0D; var7 += 360.0D) {
                    ;
                }

                while (var7 >= 180.0D) {
                    var7 -= 360.0D;
                }

                this.bp = (float) ((double) this.bp + var7 / (double) this.h);
                this.bq = (float) ((double) this.bq + (this.m - (double) this.bq) / (double) this.h);
                --this.h;
                this.c(var1, var3, var5);
                this.c(this.bp, this.bq);
            } else {
                this.c(this.bj, this.bk, this.bl);
                this.c(this.bp, this.bq);
            }

        } else {
            this.bg = this.bj;
            this.bh = this.bk;
            this.bi = this.bl;
            this.bn -= 0.03999999910593033D;
            int var9 = OMathHelper.b(this.bj);
            int var10 = OMathHelper.b(this.bk);
            int var11 = OMathHelper.b(this.bl);

            // CanaryMod: Change of the cart
            if ((int) var9 != (int) prevX || (int) var10 != (int) prevY || (int) var11 != (int) prevZ) {
                manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, var9, var10, var11);
            }
         
            if (OBlockRail.g(this.bf, var9, var10 - 1, var11)) {
                --var10;
            }

            double var12 = 0.4D;
            double var14 = 0.0078125D;
            int var16 = this.bf.a(var9, var10, var11);

            if (OBlockRail.d(var16)) {
                OVec3D var17 = this.h(this.bj, this.bk, this.bl);
                int var18 = this.bf.c(var9, var10, var11);

                this.bk = (double) var10;
                boolean var19 = false;
                boolean var20 = false;

                if (var16 == OBlock.V.bO) {
                    var19 = (var18 & 8) != 0;
                    var20 = !var19;
                }

                if (((OBlockRail) OBlock.m[var16]).h()) {
                    var18 &= 7;
                }

                if (var18 >= 2 && var18 <= 5) {
                    this.bk = (double) (var10 + 1);
                }

                if (var18 == 2) {
                    this.bm -= var14;
                }

                if (var18 == 3) {
                    this.bm += var14;
                }

                if (var18 == 4) {
                    this.bo += var14;
                }

                if (var18 == 5) {
                    this.bo -= var14;
                }

                int[][] var21 = g[var18];
                double var22 = (double) (var21[1][0] - var21[0][0]);
                double var24 = (double) (var21[1][2] - var21[0][2]);
                double var26 = Math.sqrt(var22 * var22 + var24 * var24);
                double var28 = this.bm * var22 + this.bo * var24;

                if (var28 < 0.0D) {
                    var22 = -var22;
                    var24 = -var24;
                }

                double var30 = Math.sqrt(this.bm * this.bm + this.bo * this.bo);

                this.bm = var30 * var22 / var26;
                this.bo = var30 * var24 / var26;
                double var32;

                if (var20) {
                    var32 = Math.sqrt(this.bm * this.bm + this.bo * this.bo);
                    if (var32 < 0.03D) {
                        this.bm *= 0.0D;
                        this.bn *= 0.0D;
                        this.bo *= 0.0D;
                    } else {
                        this.bm *= 0.5D;
                        this.bn *= 0.0D;
                        this.bo *= 0.5D;
                    }
                }

                var32 = 0.0D;
                double var34 = (double) var9 + 0.5D + (double) var21[0][0] * 0.5D;
                double var36 = (double) var11 + 0.5D + (double) var21[0][2] * 0.5D;
                double var38 = (double) var9 + 0.5D + (double) var21[1][0] * 0.5D;
                double var40 = (double) var11 + 0.5D + (double) var21[1][2] * 0.5D;

                var22 = var38 - var34;
                var24 = var40 - var36;
                double var42;
                double var46;
                double var44;

                if (var22 == 0.0D) {
                    this.bj = (double) var9 + 0.5D;
                    var32 = this.bl - (double) var11;
                } else if (var24 == 0.0D) {
                    this.bl = (double) var11 + 0.5D;
                    var32 = this.bj - (double) var9;
                } else {
                    var42 = this.bj - var34;
                    var44 = this.bl - var36;
                    var46 = (var42 * var22 + var44 * var24) * 2.0D;
                    var32 = var46;
                }

                this.bj = var34 + var22 * var32;
                this.bk = var36 + var24 * var32;
                this.e(this.bj, this.bk + (double) this.bC, this.bl);
                var42 = this.bm;
                var44 = this.bo;
                if (this.bd != null) {
                    var42 *= 0.75D;
                    var44 *= 0.75D;
                }

                if (var42 < -var12) {
                    var42 = -var12;
                }

                if (var42 > var12) {
                    var42 = var12;
                }

                if (var44 < -var12) {
                    var44 = -var12;
                }

                if (var44 > var12) {
                    var44 = var12;
                }

                this.a(var42, 0.0D, var44);
                if (var21[0][1] != 0 && OMathHelper.b(this.bj) - var9 == var21[0][0] && OMathHelper.b(this.bl) - var11 == var21[0][2]) {
                    this.c(this.bj, this.bk + (double) var21[0][1], this.bl);
                } else if (var21[1][1] != 0 && OMathHelper.b(this.bj) - var9 == var21[1][0] && OMathHelper.b(this.bl) - var11 == var21[1][2]) {
                    this.c(this.bj, this.bk + (double) var21[1][1], this.bl);
                }

                if (this.bd != null) {
                    this.bm *= 0.996999979019165D;
                    this.bn *= 0.0D;
                    this.bo *= 0.996999979019165D;
                } else {
                    if (this.a == 2) {
                        var46 = (double) OMathHelper.a(this.b * this.b + this.c * this.c);
                        if (var46 > 0.01D) {
                            this.b /= var46;
                            this.c /= var46;
                            double var48 = 0.04D;

                            this.bm *= 0.800000011920929D;
                            this.bn *= 0.0D;
                            this.bo *= 0.800000011920929D;
                            this.bm += this.b * var48;
                            this.bo += this.c * var48;
                        } else {
                            this.bm *= 0.8999999761581421D;
                            this.bn *= 0.0D;
                            this.bo *= 0.8999999761581421D;
                        }
                    }

                    this.bm *= 0.9599999785423279D;
                    this.bn *= 0.0D;
                    this.bo *= 0.9599999785423279D;
                }

                OVec3D var50 = this.h(this.bj, this.bk, this.bl);

                if (var50 != null && var17 != null) {
                    double var51 = (var17.b - var50.b) * 0.05D;

                    var30 = Math.sqrt(this.bm * this.bm + this.bo * this.bo);
                    if (var30 > 0.0D) {
                        this.bm = this.bm / var30 * (var30 + var51);
                        this.bo = this.bo / var30 * (var30 + var51);
                    }

                    this.c(this.bj, var50.b, this.bl);
                }

                int var53 = OMathHelper.b(this.bj);
                int var54 = OMathHelper.b(this.bl);

                if (var53 != var9 || var54 != var11) {
                    var30 = Math.sqrt(this.bm * this.bm + this.bo * this.bo);
                    this.bm = var30 * (double) (var53 - var9);
                    this.bo = var30 * (double) (var54 - var11);
                }

                double var55;

                if (this.a == 2) {
                    var55 = (double) OMathHelper.a(this.b * this.b + this.c * this.c);
                    if (var55 > 0.01D && this.bm * this.bm + this.bo * this.bo > 0.001D) {
                        this.b /= var55;
                        this.c /= var55;
                        if (this.b * this.bm + this.c * this.bo < 0.0D) {
                            this.b = 0.0D;
                            this.c = 0.0D;
                        } else {
                            this.b = this.bm;
                            this.c = this.bo;
                        }
                    }
                }

                if (var19) {
                    var55 = Math.sqrt(this.bm * this.bm + this.bo * this.bo);
                    if (var55 > 0.01D) {
                        double var57 = 0.06D;

                        this.bm += this.bm / var55 * var57;
                        this.bo += this.bo / var55 * var57;
                    } else if (var18 == 1) {
                        if (this.bf.e(var9 - 1, var10, var11)) {
                            this.bm = 0.02D;
                        } else if (this.bf.e(var9 + 1, var10, var11)) {
                            this.bm = -0.02D;
                        }
                    } else if (var18 == 0) {
                        if (this.bf.e(var9, var10, var11 - 1)) {
                            this.bo = 0.02D;
                        } else if (this.bf.e(var9, var10, var11 + 1)) {
                            this.bo = -0.02D;
                        }
                    }
                }
            } else {
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
                if (!this.bu) {
                    this.bm *= 0.949999988079071D;
                    this.bn *= 0.949999988079071D;
                    this.bo *= 0.949999988079071D;
                }
            }

            this.bq = 0.0F;
            double var59 = this.bg - this.bj;
            double var61 = this.bi - this.bl;

            if (var59 * var59 + var61 * var61 > 0.001D) {
                this.bp = (float) (Math.atan2(var61, var59) * 180.0D / 3.141592653589793D);
                if (this.f) {
                    this.bp += 180.0F;
                }
            }

            double var63;

            for (var63 = (double) (this.bp - this.br); var63 >= 180.0D; var63 -= 360.0D) {
                ;
            }

            while (var63 < -180.0D) {
                var63 += 360.0D;
            }

            if (var63 < -170.0D || var63 >= 170.0D) {
                this.bp += 180.0F;
                this.f = !this.f;
            }

            this.c(this.bp, this.bq);
            List var65 = this.bf.b((OEntity) this, this.bt.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (var65 != null && var65.size() > 0) {
                for (int var66 = 0; var66 < var65.size(); ++var66) {
                    OEntity var67 = (OEntity) var65.get(var66);

                    if (var67 != this.bd && var67.f_() && var67 instanceof OEntityMinecart) {
                        var67.j(this);
                    }
                }
            }

            if (this.bd != null && this.bd.bB) {
                if (this.bd.be == this) {
                    this.bd.be = null;
                }

                this.bd = null;
            }

            if (this.e > 0) {
                --this.e;
            }

            if (this.e <= 0) {
                this.b = this.c = 0.0D;
            }

            this.a(this.e > 0);
        }
    }
   
    // CanaryMod: Store last position, avoids Hook spaming
    private int lastX = 0;
    private int lastY = 0;
    private int lastZ = 0;

    public OVec3D h(double var1, double var3, double var5) {
        int var7 = OMathHelper.b(var1);
        int var8 = OMathHelper.b(var3);
        int var9 = OMathHelper.b(var5);

        // CanaryMod: Change of the cart
        if ((int) var7 != (int) lastX || (int) var8 != (int) lastY || (int) var9 != (int) lastZ) {
            manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, var7, var8, var9);
            lastX = var7;
            lastY = var8;
            lastZ = var9;
        }

        if (OBlockRail.g(this.bf, var7, var8 - 1, var9)) {
            --var8;
        }

        int var10 = this.bf.a(var7, var8, var9);

        if (OBlockRail.d(var10)) {
            int var11 = this.bf.c(var7, var8, var9);

            var3 = (double) var8;
            if (((OBlockRail) OBlock.m[var10]).h()) {
                var11 &= 7;
            }

            if (var11 >= 2 && var11 <= 5) {
                var3 = (double) (var8 + 1);
            }

            int[][] var12 = g[var11];
            double var13 = 0.0D;
            double var15 = (double) var7 + 0.5D + (double) var12[0][0] * 0.5D;
            double var17 = (double) var8 + 0.5D + (double) var12[0][1] * 0.5D;
            double var19 = (double) var9 + 0.5D + (double) var12[0][2] * 0.5D;
            double var21 = (double) var7 + 0.5D + (double) var12[1][0] * 0.5D;
            double var23 = (double) var8 + 0.5D + (double) var12[1][1] * 0.5D;
            double var25 = (double) var9 + 0.5D + (double) var12[1][2] * 0.5D;
            double var27 = var21 - var15;
            double var29 = (var23 - var17) * 2.0D;
            double var31 = var25 - var19;

            if (var27 == 0.0D) {
                var1 = (double) var7 + 0.5D;
                var13 = var5 - (double) var9;
            } else if (var31 == 0.0D) {
                var5 = (double) var9 + 0.5D;
                var13 = var1 - (double) var7;
            } else {
                double var33 = var1 - var15;
                double var35 = var5 - var19;
                double var37 = (var33 * var27 + var35 * var31) * 2.0D;

                var13 = var37;
            }

            var1 = var15 + var27 * var13;
            var3 = var17 + var29 * var13;
            var5 = var19 + var31 * var13;
            if (var29 < 0.0D) {
                ++var3;
            }

            if (var29 > 0.0D) {
                var3 += 0.5D;
            }

            return OVec3D.b(var1, var3, var5);
        } else {
            return null;
        }
    }

    protected void b(ONBTTagCompound var1) {
        var1.a("Type", this.a);
        if (this.a == 2) {
            var1.a("PushX", this.b);
            var1.a("PushZ", this.c);
            var1.a("Fuel", (short) this.e);
        } else if (this.a == 1) {
            ONBTTagList var2 = new ONBTTagList();

            for (int var3 = 0; var3 < this.d.length; ++var3) {
                if (this.d[var3] != null) {
                    ONBTTagCompound var4 = new ONBTTagCompound();

                    var4.a("Slot", (byte) var3);
                    this.d[var3].b(var4);
                    var2.a((ONBTBase) var4);
                }
            }

            var1.a("Items", (ONBTBase) var2);
        }

    }

    protected void a(ONBTTagCompound var1) {
        this.a = var1.f("Type");
        if (this.a == 2) {
            this.b = var1.i("PushX");
            this.c = var1.i("PushZ");
            this.e = var1.e("Fuel");
        } else if (this.a == 1) {
            ONBTTagList var2 = var1.m("Items");

            this.d = new OItemStack[this.c()];

            for (int var3 = 0; var3 < var2.d(); ++var3) {
                ONBTTagCompound var4 = (ONBTTagCompound) var2.a(var3);
                int var5 = var4.d("Slot") & 255;

                if (var5 >= 0 && var5 < this.d.length) {
                    this.d[var5] = OItemStack.a(var4);
                }
            }
        }

    }

    public void j(OEntity var1) {
        if (!this.bf.I) {
            if (var1 != this.bd) {
                // CanaryMod: Collision of a cart
                if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_COLLISION, cart, var1.entity)) {
                    return;
                }
             
                if (var1 instanceof OEntityLiving && !(var1 instanceof OEntityPlayer) && this.a == 0 && this.bm * this.bm + this.bo * this.bo > 0.01D && this.bd == null && var1.be == null) {
                    var1.b((OEntity) this);
                }

                double var2 = var1.bj - this.bj;
                double var4 = var1.bl - this.bl;
                double var6 = var2 * var2 + var4 * var4;

                if (var6 >= 9.999999747378752E-5D) {
                    var6 = (double) OMathHelper.a(var6);
                    var2 /= var6;
                    var4 /= var6;
                    double var8 = 1.0D / var6;

                    if (var8 > 1.0D) {
                        var8 = 1.0D;
                    }

                    var2 *= var8;
                    var4 *= var8;
                    var2 *= 0.10000000149011612D;
                    var4 *= 0.10000000149011612D;
                    var2 *= (double) (1.0F - this.bO);
                    var4 *= (double) (1.0F - this.bO);
                    var2 *= 0.5D;
                    var4 *= 0.5D;
                    if (var1 instanceof OEntityMinecart) {
                        double var10 = var1.bj - this.bj;
                        double var12 = var1.bl - this.bl;
                        OVec3D var14 = OVec3D.b(var10, 0.0D, var12).b();
                        OVec3D var15 = OVec3D.b((double) OMathHelper.b(this.bp * 3.1415927F / 180.0F), 0.0D, (double) OMathHelper.a(this.bp * 3.1415927F / 180.0F)).b();
                        double var16 = Math.abs(var14.a(var15));

                        if (var16 < 0.800000011920929D) {
                            return;
                        }

                        double var18 = var1.bm + this.bm;
                        double var20 = var1.bo + this.bo;

                        if (((OEntityMinecart) var1).a == 2 && this.a != 2) {
                            this.bm *= 0.20000000298023224D;
                            this.bo *= 0.20000000298023224D;
                            this.b_(var1.bm - var2, 0.0D, var1.bo - var4);
                            var1.bm *= 0.949999988079071D;
                            var1.bo *= 0.949999988079071D;
                        } else if (((OEntityMinecart) var1).a != 2 && this.a == 2) {
                            var1.bm *= 0.20000000298023224D;
                            var1.bo *= 0.20000000298023224D;
                            var1.b_(this.bm + var2, 0.0D, this.bo + var4);
                            this.bm *= 0.949999988079071D;
                            this.bo *= 0.949999988079071D;
                        } else {
                            var18 /= 2.0D;
                            var20 /= 2.0D;
                            this.bm *= 0.20000000298023224D;
                            this.bo *= 0.20000000298023224D;
                            this.b_(var18 - var2, 0.0D, var20 - var4);
                            var1.bm *= 0.20000000298023224D;
                            var1.bo *= 0.20000000298023224D;
                            var1.b_(var18 + var2, 0.0D, var20 + var4);
                        }
                    } else {
                        this.b_(-var2, 0.0D, -var4);
                        var1.b_(var2 / 4.0D, 0.0D, var4 / 4.0D);
                    }
                }

            }
        }
    }

    public int c() {
        return 27;
    }

    public OItemStack c_(int var1) {
        return this.d[var1];
    }

    public OItemStack a(int var1, int var2) {
        if (this.d[var1] != null) {
            OItemStack var3;

            if (this.d[var1].a <= var2) {
                var3 = this.d[var1];
                this.d[var1] = null;
                return var3;
            } else {
                var3 = this.d[var1].a(var2);
                if (this.d[var1].a == 0) {
                    this.d[var1] = null;
                }

                return var3;
            }
        } else {
            return null;
        }
    }

    public void a(int var1, OItemStack var2) {
        this.d[var1] = var2;
        if (var2 != null && var2.a > this.a()) {
            var2.a = this.a();
        }

    }

    public String e() {
        return "Minecart";
    }

    public int a() {
        return 64;
    }

    public void x_() {}

    public boolean b(OEntityPlayer var1) {
        // CanaryMod: Entering the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, cart, var1.entity);

        if (this.a == 0) {
            if (this.bd != null && this.bd instanceof OEntityPlayer && this.bd != var1) {
                return true;
            }

            if (!this.bf.I) {
                var1.b((OEntity) this);
            }
        } else if (this.a == 1) {
            if (!this.bf.I) {
                var1.a((OIInventory) this);
            }
        } else if (this.a == 2) {
            OItemStack var2 = var1.k.d();

            if (var2 != null && var2.c == OItem.l.bM) {
                if (--var2.a == 0) {
                    var1.k.a(var1.k.c, (OItemStack) null);
                }

                this.e += 3600;
            }

            this.b = this.bj - var1.bj;
            this.c = this.bl - var1.bl;
        }

        return true;
    }

    public boolean a(OEntityPlayer var1) {
        return this.bB ? false : var1.i(this) <= 64.0D;
    }

    protected boolean j() {
        return (this.bV.a(16) & 1) != 0;
    }

    protected void a(boolean var1) {
        if (var1) {
            this.bV.b(16, Byte.valueOf((byte) (this.bV.a(16) | 1)));
        } else {
            this.bV.b(16, Byte.valueOf((byte) (this.bV.a(16) & -2)));
        }

    }

    public void f() {}

    public void g() {}

    public void b(int var1) {
        this.bV.b(19, Integer.valueOf(var1));
    }

    public int k() {
        return this.bV.c(19);
    }

    public void c(int var1) {
        this.bV.b(17, Integer.valueOf(var1));
    }

    public int l() {
        return this.bV.c(17);
    }

    public void d(int var1) {
        this.bV.b(18, Integer.valueOf(var1));
    }

    public int m() {
        return this.bV.c(18);
    }

    public OItemStack[] getContents() {
        return d;
    }
   
    public void setContents(OItemStack[] values) {
        d = values;
    }
   
    public OItemStack getContentsAt(int index) {
        return c_(index);
    }
   
    public void setContentsAt(int index, OItemStack value) {
        a(index, value);
    }
   
    public int getContentsSize() {
        return c();
    }
   
    public String getName() {
        return name;
    }
   
    public void setName(String value) {
        name = value;
    }
}
