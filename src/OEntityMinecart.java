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

    public OEntityMinecart(OWorld oworld) {
        super(oworld);
        this.d = new OItemStack[36];
        this.e = 0;
        this.f = false;
        this.bf = true;
        this.b(0.98F, 0.7F);
        this.bF = this.bH / 2.0F;
    }

    protected boolean g_() {
        return false;
    }

    protected void b() {
        this.bY.a(16, new Byte((byte) 0));
        this.bY.a(17, new Integer(0));
        this.bY.a(18, new Integer(1));
        this.bY.a(19, new Integer(0));
    }

    public OAxisAlignedBB b_(OEntity oentity) {
        return oentity.bw;
    }

    public OAxisAlignedBB h() {
        return null;
    }

    public boolean e_() {
        return true;
    }

    public OEntityMinecart(OWorld oworld, double d0, double d1, double d2, int i) {
        this(oworld);
        this.c(d0, d1 + (double) this.bF, d2);
        this.bp = 0.0D;
        this.bq = 0.0D;
        this.br = 0.0D;
        this.bj = d0;
        this.bk = d1;
        this.bl = d2;
        this.a = i;
        // CanaryMod: Creation of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, cart);
    }

    public double x_() {
        return (double) this.bH * 0.0D - 0.30000001192092896D;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        // CanaryMod: Attack of the cart
        BaseEntity entity = null;

        if (odamagesource != null && odamagesource.a() != null) {
            entity = new BaseEntity(odamagesource.a());
        }
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, cart, entity, i)) {
            return true;
        }

        if (!this.bi.F && !this.bE) {
            this.e(-this.n());
            this.d(10);
            this.aW();
            this.c(this.l() + i * 10);
            if (this.l() > 40) {
                if (this.bg != null) {
                    this.bg.b((OEntity) this);
                }

                this.X();
                this.a(OItem.ay.bP, 1, 0.0F);
                if (this.a == 1) {
                    OEntityMinecart oentityminecart = this;

                    for (int j = 0; j < oentityminecart.c(); ++j) {
                        OItemStack oitemstack = oentityminecart.g_(j);

                        if (oitemstack != null) {
                            float f = this.bS.nextFloat() * 0.8F + 0.1F;
                            float f1 = this.bS.nextFloat() * 0.8F + 0.1F;
                            float f2 = this.bS.nextFloat() * 0.8F + 0.1F;

                            while (oitemstack.a > 0) {
                                int k = this.bS.nextInt(21) + 10;

                                if (k > oitemstack.a) {
                                    k = oitemstack.a;
                                }

                                oitemstack.a -= k;
                                OEntityItem oentityitem = new OEntityItem(this.bi, this.bm + (double) f, this.bn + (double) f1, this.bo + (double) f2, new OItemStack(oitemstack.c, k, oitemstack.h()));
                                float f3 = 0.05F;

                                oentityitem.bp = (double) ((float) this.bS.nextGaussian() * f3);
                                oentityitem.bq = (double) ((float) this.bS.nextGaussian() * f3 + 0.2F);
                                oentityitem.br = (double) ((float) this.bS.nextGaussian() * f3);
                                this.bi.b((OEntity) oentityitem);
                            }
                        }
                    }

                    this.a(OBlock.au.bO, 1, 0.0F);
                } else if (this.a == 2) {
                    this.a(OBlock.aB.bO, 1, 0.0F);
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public boolean o_() {
        return !this.bE;
    }

    public void X() {
        // CanaryMod: Destruction of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_DESTROYED, cart);
        for (int i = 0; i < this.c(); ++i) {
            OItemStack oitemstack = this.g_(i);

            if (oitemstack != null) {
                float f = this.bS.nextFloat() * 0.8F + 0.1F;
                float f1 = this.bS.nextFloat() * 0.8F + 0.1F;
                float f2 = this.bS.nextFloat() * 0.8F + 0.1F;

                while (oitemstack.a > 0) {
                    int j = this.bS.nextInt(21) + 10;

                    if (j > oitemstack.a) {
                        j = oitemstack.a;
                    }

                    oitemstack.a -= j;
                    OEntityItem oentityitem = new OEntityItem(this.bi, this.bm + (double) f, this.bn + (double) f1, this.bo + (double) f2, new OItemStack(oitemstack.c, j, oitemstack.h()));
                    
                    if (oitemstack.n()) {
                        oentityitem.a.d((ONBTTagCompound) oitemstack.o().b());
                    }
                    
                    float f3 = 0.05F;

                    oentityitem.bp = (double) ((float) this.bS.nextGaussian() * f3);
                    oentityitem.bq = (double) ((float) this.bS.nextGaussian() * f3 + 0.2F);
                    oentityitem.br = (double) ((float) this.bS.nextGaussian() * f3);
                    this.bi.b((OEntity) oentityitem);
                }
            }
        }

        super.X();
    }

    public void F_() {
        // CanaryMod: Update of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, cart);
        
        if (this.m() > 0) {
            this.d(this.m() - 1);
        }
        
        double prevX = bj;
        double prevY = bk;
        double prevZ = bl;

        if (this.l() > 0) {
            this.c(this.l() - 1);
        }
        
        if (this.bn < -64.0D) {
            this.aI();
        }

        if (this.k() && this.bS.nextInt(4) == 0) {
            this.bi.a("largesmoke", this.bm, this.bn + 0.8D, this.bo, 0.0D, 0.0D, 0.0D);
        }

        if (this.bi.F) {
            if (this.h > 0) {
                double d0 = this.bm + (this.i - this.bm) / (double) this.h;
                double d1 = this.bn + (this.j - this.bn) / (double) this.h;
                double d2 = this.bo + (this.k - this.bo) / (double) this.h;

                double d3;

                for (d3 = this.l - (double) this.bs; d3 < -180.0D; d3 += 360.0D) {
                    ;
                }

                while (d3 >= 180.0D) {
                    d3 -= 360.0D;
                }

                this.bs = (float) ((double) this.bs + d3 / (double) this.h);
                this.bt = (float) ((double) this.bt + (this.m - (double) this.bt) / (double) this.h);
                --this.h;
                this.c(d0, d1, d2);
                this.c(this.bs, this.bt);
            } else {
                this.c(this.bm, this.bn, this.bo);
                this.c(this.bs, this.bt);
            }

        } else {
            this.bj = this.bm;
            this.bk = this.bn;
            this.bl = this.bo;
            this.bq -= 0.03999999910593033D;
            int i = OMathHelper.b(this.bm);
            int j = OMathHelper.b(this.bn);
            int k = OMathHelper.b(this.bo);
            
            // CanaryMod: Change of the cart
            if ((int) i != (int) prevX || (int) j != (int) prevY || (int) k != (int) prevZ) {
                manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, i, j, k);
            }

            if (OBlockRail.g(this.bi, i, j - 1, k)) {
                --j;
            }

            double d4 = 0.4D;
            double d5 = 0.0078125D;
            int l = this.bi.a(i, j, k);

            if (OBlockRail.d(l)) {
                OVec3D ovec3d = this.h(this.bm, this.bn, this.bo);
                int i1 = this.bi.c(i, j, k);

                this.bn = (double) j;
                boolean flag = false;
                boolean flag1 = false;

                if (l == OBlock.T.bO) {
                    flag = (i1 & 8) != 0;
                    flag1 = !flag;
                }

                if (((OBlockRail) OBlock.m[l]).i()) {
                    i1 &= 7;
                }

                if (i1 >= 2 && i1 <= 5) {
                    this.bn = (double) (j + 1);
                }

                if (i1 == 2) {
                    this.bp -= d5;
                }

                if (i1 == 3) {
                    this.bp += d5;
                }

                if (i1 == 4) {
                    this.br += d5;
                }

                if (i1 == 5) {
                    this.br -= d5;
                }

                int[][] aint = g[i1];
                double d6 = (double) (aint[1][0] - aint[0][0]);
                double d7 = (double) (aint[1][2] - aint[0][2]);
                double d8 = Math.sqrt(d6 * d6 + d7 * d7);
                double d9 = this.bp * d6 + this.br * d7;

                if (d9 < 0.0D) {
                    d6 = -d6;
                    d7 = -d7;
                }

                double d10 = Math.sqrt(this.bp * this.bp + this.br * this.br);

                this.bp = d10 * d6 / d8;
                this.br = d10 * d7 / d8;
                double d11;

                if (flag1) {
                    d11 = Math.sqrt(this.bp * this.bp + this.br * this.br);
                    if (d11 < 0.03D) {
                        this.bp *= 0.0D;
                        this.bq *= 0.0D;
                        this.br *= 0.0D;
                    } else {
                        this.bp *= 0.5D;
                        this.bq *= 0.0D;
                        this.br *= 0.5D;
                    }
                }

                d11 = 0.0D;
                double d12 = (double) i + 0.5D + (double) aint[0][0] * 0.5D;
                double d13 = (double) k + 0.5D + (double) aint[0][2] * 0.5D;
                double d14 = (double) i + 0.5D + (double) aint[1][0] * 0.5D;
                double d15 = (double) k + 0.5D + (double) aint[1][2] * 0.5D;

                d6 = d14 - d12;
                d7 = d15 - d13;
                double d16;
                double d17;
                double d18;

                if (d6 == 0.0D) {
                    this.bm = (double) i + 0.5D;
                    d11 = this.bo - (double) k;
                } else if (d7 == 0.0D) {
                    this.bo = (double) k + 0.5D;
                    d11 = this.bm - (double) i;
                } else {
                    d16 = this.bm - d12;
                    d18 = this.bo - d13;
                    d17 = (d16 * d6 + d18 * d7) * 2.0D;
                    d11 = d17;
                }

                this.bm = d12 + d6 * d11;
                this.bo = d13 + d7 * d11;
                this.c(this.bm, this.bn + (double) this.bF, this.bo);
                d16 = this.bp;
                d18 = this.br;
                if (this.bg != null) {
                    d16 *= 0.75D;
                    d18 *= 0.75D;
                }

                if (d16 < -d4) {
                    d16 = -d4;
                }

                if (d16 > d4) {
                    d16 = d4;
                }

                if (d18 < -d4) {
                    d18 = -d4;
                }

                if (d18 > d4) {
                    d18 = d4;
                }

                this.a(d16, 0.0D, d18);
                if (aint[0][1] != 0 && OMathHelper.b(this.bm) - i == aint[0][0] && OMathHelper.b(this.bo) - k == aint[0][2]) {
                    this.c(this.bm, this.bn + (double) aint[0][1], this.bo);
                } else if (aint[1][1] != 0 && OMathHelper.b(this.bm) - i == aint[1][0] && OMathHelper.b(this.bo) - k == aint[1][2]) {
                    this.c(this.bm, this.bn + (double) aint[1][1], this.bo);
                }

                if (this.bg != null) {
                    this.bp *= 0.996999979019165D;
                    this.bq *= 0.0D;
                    this.br *= 0.996999979019165D;
                } else {
                    if (this.a == 2) {
                        d17 = (double) OMathHelper.a(this.b * this.b + this.c * this.c);
                        if (d17 > 0.01D) {
                            this.b /= d17;
                            this.c /= d17;
                            double d19 = 0.04D;

                            this.bp *= 0.800000011920929D;
                            this.bq *= 0.0D;
                            this.br *= 0.800000011920929D;
                            this.bp += this.b * d19;
                            this.br += this.c * d19;
                        } else {
                            this.bp *= 0.8999999761581421D;
                            this.bq *= 0.0D;
                            this.br *= 0.8999999761581421D;
                        }
                    }

                    this.bp *= 0.9599999785423279D;
                    this.bq *= 0.0D;
                    this.br *= 0.9599999785423279D;
                }

                OVec3D ovec3d1 = this.h(this.bm, this.bn, this.bo);

                if (ovec3d1 != null && ovec3d != null) {
                    double d20 = (ovec3d.b - ovec3d1.b) * 0.05D;

                    d10 = Math.sqrt(this.bp * this.bp + this.br * this.br);
                    if (d10 > 0.0D) {
                        this.bp = this.bp / d10 * (d10 + d20);
                        this.br = this.br / d10 * (d10 + d20);
                    }

                    this.c(this.bm, ovec3d1.b, this.bo);
                }

                int j1 = OMathHelper.b(this.bm);
                int k1 = OMathHelper.b(this.bo);

                if (j1 != i || k1 != k) {
                    d10 = Math.sqrt(this.bp * this.bp + this.br * this.br);
                    this.bp = d10 * (double) (j1 - i);
                    this.br = d10 * (double) (k1 - k);
                }

                double d21;

                if (this.a == 2) {
                    d21 = (double) OMathHelper.a(this.b * this.b + this.c * this.c);
                    if (d21 > 0.01D && this.bp * this.bp + this.br * this.br > 0.001D) {
                        this.b /= d21;
                        this.c /= d21;
                        if (this.b * this.bp + this.c * this.br < 0.0D) {
                            this.b = 0.0D;
                            this.c = 0.0D;
                        } else {
                            this.b = this.bp;
                            this.c = this.br;
                        }
                    }
                }

                if (flag) {
                    d21 = Math.sqrt(this.bp * this.bp + this.br * this.br);
                    if (d21 > 0.01D) {
                        double d22 = 0.06D;

                        this.bp += this.bp / d21 * d22;
                        this.br += this.br / d21 * d22;
                    } else if (i1 == 1) {
                        if (this.bi.e(i - 1, j, k)) {
                            this.bp = 0.02D;
                        } else if (this.bi.e(i + 1, j, k)) {
                            this.bp = -0.02D;
                        }
                    } else if (i1 == 0) {
                        if (this.bi.e(i, j, k - 1)) {
                            this.br = 0.02D;
                        } else if (this.bi.e(i, j, k + 1)) {
                            this.br = -0.02D;
                        }
                    }
                }
            } else {
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
                if (!this.bx) {
                    this.bp *= 0.949999988079071D;
                    this.bq *= 0.949999988079071D;
                    this.br *= 0.949999988079071D;
                }
            }

            this.bt = 0.0F;
            double d23 = this.bj - this.bm;
            double d24 = this.bl - this.bo;

            if (d23 * d23 + d24 * d24 > 0.001D) {
                this.bs = (float) (Math.atan2(d24, d23) * 180.0D / 3.141592653589793D);
                if (this.f) {
                    this.bs += 180.0F;
                }
            }

            double d25;

            for (d25 = (double) (this.bs - this.bu); d25 >= 180.0D; d25 -= 360.0D) {
                ;
            }

            while (d25 < -180.0D) {
                d25 += 360.0D;
            }

            if (d25 < -170.0D || d25 >= 170.0D) {
                this.bs += 180.0F;
                this.f = !this.f;
            }

            this.c(this.bs, this.bt);
            List list = this.bi.b((OEntity) this, this.bw.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (list != null && list.size() > 0) {
                for (int l1 = 0; l1 < list.size(); ++l1) {
                    OEntity oentity = (OEntity) list.get(l1);

                    if (oentity != this.bg && oentity.e_() && oentity instanceof OEntityMinecart) {
                        oentity.k(this);
                    }
                }
            }

            if (this.bg != null && this.bg.bE) {
                if (this.bg.bh == this) {
                    this.bg.bh = null;
                }

                this.bg = null;
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

    public OVec3D h(double d0, double d1, double d2) {
        int i = OMathHelper.b(d0);
        int j = OMathHelper.b(d1);
        int k = OMathHelper.b(d2);
        
        // CanaryMod: Change of the cart
        if ((int) i != (int) lastX || (int) j != (int) lastY || (int) k != (int) lastZ) {
            manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, i, j, k);
            lastX = i;
            lastY = j;
            lastZ = k;
        }

        if (OBlockRail.g(this.bi, i, j - 1, k)) {
            --j;
        }

        int l = this.bi.a(i, j, k);

        if (OBlockRail.d(l)) {
            int i1 = this.bi.c(i, j, k);

            d1 = (double) j;
            if (((OBlockRail) OBlock.m[l]).i()) {
                i1 &= 7;
            }

            if (i1 >= 2 && i1 <= 5) {
                d1 = (double) (j + 1);
            }

            int[][] aint = g[i1];
            double d3 = 0.0D;
            double d4 = (double) i + 0.5D + (double) aint[0][0] * 0.5D;
            double d5 = (double) j + 0.5D + (double) aint[0][1] * 0.5D;
            double d6 = (double) k + 0.5D + (double) aint[0][2] * 0.5D;
            double d7 = (double) i + 0.5D + (double) aint[1][0] * 0.5D;
            double d8 = (double) j + 0.5D + (double) aint[1][1] * 0.5D;
            double d9 = (double) k + 0.5D + (double) aint[1][2] * 0.5D;
            double d10 = d7 - d4;
            double d11 = (d8 - d5) * 2.0D;
            double d12 = d9 - d6;

            if (d10 == 0.0D) {
                d0 = (double) i + 0.5D;
                d3 = d2 - (double) k;
            } else if (d12 == 0.0D) {
                d2 = (double) k + 0.5D;
                d3 = d0 - (double) i;
            } else {
                double d13 = d0 - d4;
                double d14 = d2 - d6;
                double d15 = (d13 * d10 + d14 * d12) * 2.0D;

                d3 = d15;
            }

            d0 = d4 + d10 * d3;
            d1 = d5 + d11 * d3;
            d2 = d6 + d12 * d3;
            if (d11 < 0.0D) {
                ++d1;
            }

            if (d11 > 0.0D) {
                d1 += 0.5D;
            }

            return OVec3D.b(d0, d1, d2);
        } else {
            return null;
        }
    }

    protected void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Type", this.a);
        if (this.a == 2) {
            onbttagcompound.a("PushX", this.b);
            onbttagcompound.a("PushZ", this.c);
            onbttagcompound.a("Fuel", (short) this.e);
        } else if (this.a == 1) {
            ONBTTagList onbttaglist = new ONBTTagList();

            for (int i = 0; i < this.d.length; ++i) {
                if (this.d[i] != null) {
                    ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

                    onbttagcompound1.a("Slot", (byte) i);
                    this.d[i].b(onbttagcompound1);
                    onbttaglist.a((ONBTBase) onbttagcompound1);
                }
            }

            onbttagcompound.a("Items", (ONBTBase) onbttaglist);
        }

    }

    protected void a(ONBTTagCompound onbttagcompound) {
        this.a = onbttagcompound.f("Type");
        if (this.a == 2) {
            this.b = onbttagcompound.i("PushX");
            this.c = onbttagcompound.i("PushZ");
            this.e = onbttagcompound.e("Fuel");
        } else if (this.a == 1) {
            ONBTTagList onbttaglist = onbttagcompound.n("Items");

            this.d = new OItemStack[this.c()];

            for (int i = 0; i < onbttaglist.d(); ++i) {
                ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.a(i);
                int j = onbttagcompound1.d("Slot") & 255;

                if (j >= 0 && j < this.d.length) {
                    this.d[j] = OItemStack.a(onbttagcompound1);
                }
            }
        }

    }

    public void k(OEntity oentity) {
        if (!this.bi.F) {
            if (oentity != this.bg) {
                // CanaryMod: Collision of a cart
                if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_COLLISION, cart, oentity.entity)) {
                    return;
                }
                
                if (oentity instanceof OEntityLiving && !(oentity instanceof OEntityPlayer) && !(oentity instanceof OEntityIronGolem) && this.a == 0 && this.bp * this.bp + this.br * this.br > 0.01D && this.bg == null && oentity.bh == null) {
                    oentity.b((OEntity) this);
                }

                double d0 = oentity.bm - this.bm;
                double d1 = oentity.bo - this.bo;
                double d2 = d0 * d0 + d1 * d1;

                if (d2 >= 9.999999747378752E-5D) {
                    d2 = (double) OMathHelper.a(d2);
                    d0 /= d2;
                    d1 /= d2;
                    double d3 = 1.0D / d2;

                    if (d3 > 1.0D) {
                        d3 = 1.0D;
                    }

                    d0 *= d3;
                    d1 *= d3;
                    d0 *= 0.10000000149011612D;
                    d1 *= 0.10000000149011612D;
                    d0 *= (double) (1.0F - this.bR);
                    d1 *= (double) (1.0F - this.bR);
                    d0 *= 0.5D;
                    d1 *= 0.5D;
                    if (oentity instanceof OEntityMinecart) {
                        double d4 = oentity.bm - this.bm;
                        double d5 = oentity.bo - this.bo;
                        OVec3D ovec3d = OVec3D.b(d4, 0.0D, d5).b();
                        OVec3D ovec3d1 = OVec3D.b((double) OMathHelper.b(this.bs * 3.1415927F / 180.0F), 0.0D, (double) OMathHelper.a(this.bs * 3.1415927F / 180.0F)).b();
                        double d6 = Math.abs(ovec3d.a(ovec3d1));

                        if (d6 < 0.800000011920929D) {
                            return;
                        }

                        double d7 = oentity.bp + this.bp;
                        double d8 = oentity.br + this.br;

                        if (((OEntityMinecart) oentity).a == 2 && this.a != 2) {
                            this.bp *= 0.20000000298023224D;
                            this.br *= 0.20000000298023224D;
                            this.b_(oentity.bp - d0, 0.0D, oentity.br - d1);
                            oentity.bp *= 0.949999988079071D;
                            oentity.br *= 0.949999988079071D;
                        } else if (((OEntityMinecart) oentity).a != 2 && this.a == 2) {
                            oentity.bp *= 0.20000000298023224D;
                            oentity.br *= 0.20000000298023224D;
                            oentity.b_(this.bp + d0, 0.0D, this.br + d1);
                            this.bp *= 0.949999988079071D;
                            this.br *= 0.949999988079071D;
                        } else {
                            d7 /= 2.0D;
                            d8 /= 2.0D;
                            this.bp *= 0.20000000298023224D;
                            this.br *= 0.20000000298023224D;
                            this.b_(d7 - d0, 0.0D, d8 - d1);
                            oentity.bp *= 0.20000000298023224D;
                            oentity.br *= 0.20000000298023224D;
                            oentity.b_(d7 + d0, 0.0D, d8 + d1);
                        }
                    } else {
                        this.b_(-d0, 0.0D, -d1);
                        oentity.b_(d0 / 4.0D, 0.0D, d1 / 4.0D);
                    }
                }

            }
        }
    }

    public int c() {
        return 27;
    }

    public OItemStack g_(int i) {
        return this.d[i];
    }

    public OItemStack a(int i, int j) {
        if (this.d[i] != null) {
            OItemStack oitemstack;

            if (this.d[i].a <= j) {
                oitemstack = this.d[i];
                this.d[i] = null;
                return oitemstack;
            } else {
                oitemstack = this.d[i].a(j);
                if (this.d[i].a == 0) {
                    this.d[i] = null;
                }

                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        if (this.d[i] != null) {
            OItemStack oitemstack = this.d[i];

            this.d[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        this.d[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.a()) {
            oitemstack.a = this.a();
        }

    }

    public String e() {
        return "container.minecart";
    }

    public int a() {
        return 64;
    }

    public void G_() {}

    public boolean b(OEntityPlayer oentityplayer) {
        // CanaryMod: Entering the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, cart, oentityplayer.entity);
        
        if (this.a == 0) {
            if (this.bg != null && this.bg instanceof OEntityPlayer && this.bg != oentityplayer) {
                return true;
            }

            if (!this.bi.F) {
                oentityplayer.b((OEntity) this);
            }
        } else if (this.a == 1) {
            if (!this.bi.F) {
                oentityplayer.a((OIInventory) this);
            }
        } else if (this.a == 2) {
            OItemStack oitemstack = oentityplayer.k.d();

            if (oitemstack != null && oitemstack.c == OItem.l.bP) {
                if (--oitemstack.a == 0) {
                    oentityplayer.k.a(oentityplayer.k.c, (OItemStack) null);
                }

                this.e += 3600;
            }

            this.b = this.bm - oentityplayer.bm;
            this.c = this.bo - oentityplayer.bo;
        }

        return true;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.bE ? false : oentityplayer.j(this) <= 64.0D;
    }

    protected boolean k() {
        return (this.bY.a(16) & 1) != 0;
    }

    protected void a(boolean flag) {
        if (flag) {
            this.bY.b(16, Byte.valueOf((byte) (this.bY.a(16) | 1)));
        } else {
            this.bY.b(16, Byte.valueOf((byte) (this.bY.a(16) & -2)));
        }

    }

    public void f() {}

    public void g() {}

    public void c(int i) {
        this.bY.b(19, Integer.valueOf(i));
    }

    public int l() {
        return this.bY.c(19);
    }

    public void d(int i) {
        this.bY.b(17, Integer.valueOf(i));
    }

    public int m() {
        return this.bY.c(17);
    }

    public void e(int i) {
        this.bY.b(18, Integer.valueOf(i));
    }

    public int n() {
        return this.bY.c(18);
    }
    
    public OItemStack[] getContents() {
        return d;
    }

    public void setContents(OItemStack[] aoitemstack) {
        d = aoitemstack;
    }

    public OItemStack getContentsAt(int i) {
        return g_(i);
    }

    public void setContentsAt(int i, OItemStack oitemstack) {
        a(i, oitemstack);
    }

    public int getContentsSize() {
        return c();
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

}
