
public class OEntityEnderman extends OEntityMob {

    private static boolean[] b = new boolean[256];
    public boolean a = false;
    private int g = 0;
    private int h = 0;
    // CanaryMod Start
    Enderman entity = new Enderman(this);

    // CanaryMod End

    public OEntityEnderman(OWorld var1) {
        super(var1);
        this.ac = "/mob/enderman.png";
        this.aY = 0.2F;
        this.c = 7;
        this.b(0.6F, 2.9F);
        this.bM = 1.0F;
    }

    public int c() {
        return 40;
    }

    protected void b() {
        super.b();
        this.bV.a(16, new Byte((byte) 0));
        this.bV.a(17, new Byte((byte) 0));
    }

    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("carried", (short) this.A());
        var1.a("carriedData", (short) this.B());
    }

    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.b(var1.e("carried"));
        this.c(var1.e("carriedData"));
    }

    protected OEntity k() {
        OEntityPlayer var1 = this.bf.b(this, 64.0D);

        if (var1 != null) {
            if (this.c(var1)) {
                if (this.h++ == 5) {
                    this.h = 0;
                    return var1;
                }
            } else {
                this.h = 0;
            }
        }

        return null;
    }

    public float a(float var1) {
        return super.a(var1);
    }

    private boolean c(OEntityPlayer var1) {
        OItemStack var2 = var1.k.b[3];

        if (var2 != null && var2.c == OBlock.bc.bO) {
            return false;
        } else {
            OVec3D var3 = var1.d(1.0F).b();
            OVec3D var4 = OVec3D.b(this.bj - var1.bj, this.bt.b + (double) (this.bE / 2.0F) - (var1.bk + (double) var1.x()), this.bl - var1.bl);
            double var5 = var4.c();

            var4 = var4.b();
            double var7 = var3.a(var4);

            return var7 > 1.0D - 0.025D / var5 ? var1.g(this) : false;
        }
    }

    public void d() {
        if (this.ay()) {
            this.a(ODamageSource.e, 1);
        }

        this.a = this.d != null;
        this.aY = this.d != null ? 6.5F : 0.3F;
        int var1;

        if (!this.bf.I) {
            int var2;
            int var3;
            int var4;

            if (this.A() == 0) {
                if (this.bP.nextInt(20) == 0) {
                    var1 = OMathHelper.b(this.bj - 2.0D + this.bP.nextDouble() * 4.0D);
                    var2 = OMathHelper.b(this.bk + this.bP.nextDouble() * 3.0D);
                    var3 = OMathHelper.b(this.bl - 2.0D + this.bP.nextDouble() * 4.0D);
                    var4 = this.bf.a(var1, var2, var3);
                    if (b[var4]) {
                        // CanaryMod onEndermanPickup
                        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_PICKUP, entity, new Block(var4, var1, var2, var3, this.bf.a(var1, var2, var3)))) {
                            this.b(this.bf.a(var1, var2, var3));
                            this.c(this.bf.c(var1, var2, var3));
                            this.bf.e(var1, var2, var3, 0);
                        }
                    }
                }
            } else if (this.bP.nextInt(2000) == 0) {
                var1 = OMathHelper.b(this.bj - 1.0D + this.bP.nextDouble() * 2.0D);
                var2 = OMathHelper.b(this.bk + this.bP.nextDouble() * 2.0D);
                var3 = OMathHelper.b(this.bl - 1.0D + this.bP.nextDouble() * 2.0D);
                var4 = this.bf.a(var1, var2, var3);
                int var5 = this.bf.a(var1, var2 - 1, var3);

                if (var4 == 0 && var5 > 0 && OBlock.m[var5].b()) {
                    // CanaryMod onEndermanDrop
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_DROP, entity, new Block(var5, var1, var2, var3, this.bf.a(var1, var2, var3)))) {
                        this.bf.b(var1, var2, var3, this.A(), this.B());
                        this.b(0);
                    }
                }
            }
        }

        for (var1 = 0; var1 < 2; ++var1) {
            this.bf.a("portal", this.bj + (this.bP.nextDouble() - 0.5D) * (double) this.bD, this.bk + this.bP.nextDouble() * (double) this.bE - 0.25D, this.bl + (this.bP.nextDouble() - 0.5D) * (double) this.bD, (this.bP.nextDouble() - 0.5D) * 2.0D, -this.bP.nextDouble(), (this.bP.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.bf.e() && !this.bf.I) {
            float var6 = this.a(1.0F);

            if (var6 > 0.5F && this.bf.j(OMathHelper.b(this.bj), OMathHelper.b(this.bk), OMathHelper.b(this.bl)) && this.bP.nextFloat() * 30.0F < (var6 - 0.4F) * 2.0F) {
                this.d = null;
                this.u_();
            }
        }

        if (this.ay()) {
            this.d = null;
            this.u_();
        }

        this.aW = false;
        if (this.d != null) {
            this.a(this.d, 100.0F, 100.0F);
        }

        if (!this.bf.I && this.aj()) {
            if (this.d != null) {
                if (this.d instanceof OEntityPlayer && this.c((OEntityPlayer) this.d)) {
                    this.aT = this.aU = 0.0F;
                    this.aY = 0.0F;
                    if (this.d.i(this) < 16.0D) {
                        this.u_();
                    }

                    this.g = 0;
                } else if (this.d.i(this) > 256.0D && this.g++ >= 30 && this.f(this.d)) {
                    this.g = 0;
                }
            } else {
                this.g = 0;
            }
        }

        super.d();
    }

    protected boolean u_() {
        double var1 = this.bj + (this.bP.nextDouble() - 0.5D) * 64.0D;
        double var3 = this.bk + (double) (this.bP.nextInt(64) - 32);
        double var5 = this.bl + (this.bP.nextDouble() - 0.5D) * 64.0D;

        return this.b(var1, var3, var5);
    }

    protected boolean f(OEntity var1) {
        OVec3D var2 = OVec3D.b(this.bj - var1.bj, this.bt.b + (double) (this.bE / 2.0F) - var1.bk + (double) var1.x(), this.bl - var1.bl);

        var2 = var2.b();
        double var3 = 16.0D;
        double var5 = this.bj + (this.bP.nextDouble() - 0.5D) * 8.0D - var2.a * var3;
        double var7 = this.bk + (double) (this.bP.nextInt(16) - 8) - var2.b * var3;
        double var9 = this.bl + (this.bP.nextDouble() - 0.5D) * 8.0D - var2.c * var3;

        return this.b(var5, var7, var9);
    }

    protected boolean b(double var1, double var3, double var5) {
        double var7 = this.bj;
        double var9 = this.bk;
        double var11 = this.bl;

        this.bj = var1;
        this.bk = var3;
        this.bl = var5;
        boolean var13 = false;
        int var14 = OMathHelper.b(this.bj);
        int var15 = OMathHelper.b(this.bk);
        int var16 = OMathHelper.b(this.bl);
        int var18;

        if (this.bf.g(var14, var15, var16)) {
            boolean var17 = false;

            while (!var17 && var15 > 0) {
                var18 = this.bf.a(var14, var15 - 1, var16);
                if (var18 != 0 && OBlock.m[var18].cb.c()) {
                    var17 = true;
                } else {
                    --this.bk;
                    --var15;
                }
            }

            if (var17) {
                this.c(this.bj, this.bk, this.bl);
                if (this.bf.a((OEntity) this, this.bt).size() == 0 && !this.bf.c(this.bt)) {
                    var13 = true;
                }
            }
        }

        if (!var13) {
            this.c(var7, var9, var11);
            return false;
        } else {
            short var30 = 128;

            for (var18 = 0; var18 < var30; ++var18) {
                double var19 = (double) var18 / ((double) var30 - 1.0D);
                float var21 = (this.bP.nextFloat() - 0.5F) * 0.2F;
                float var22 = (this.bP.nextFloat() - 0.5F) * 0.2F;
                float var23 = (this.bP.nextFloat() - 0.5F) * 0.2F;
                double var24 = var7 + (this.bj - var7) * var19 + (this.bP.nextDouble() - 0.5D) * (double) this.bD * 2.0D;
                double var26 = var9 + (this.bk - var9) * var19 + this.bP.nextDouble() * (double) this.bE;
                double var28 = var11 + (this.bl - var11) * var19 + (this.bP.nextDouble() - 0.5D) * (double) this.bD * 2.0D;

                this.bf.a("portal", var24, var26, var28, (double) var21, (double) var22, (double) var23);
            }

            this.bf.a(var7, var9, var11, "mob.endermen.portal", 1.0F, 1.0F);
            this.bf.a(this, "mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }

    protected String c_() {
        return "mob.endermen.idle";
    }

    protected String m() {
        return "mob.endermen.hit";
    }

    protected String n() {
        return "mob.endermen.death";
    }

    protected int e() {
        return OItem.bm.bM;
    }

    protected void a(boolean var1, int var2) {
        int var3 = this.e();

        if (var3 > 0) {
            int var4 = this.bP.nextInt(2 + var2);

            for (int var5 = 0; var5 < var4; ++var5) {
                this.b(var3, 1);
            }
        }

    }

    public void b(int var1) {
        this.bV.b(16, Byte.valueOf((byte) (var1 & 255)));
    }

    public int A() {
        return this.bV.a(16);
    }

    public void c(int var1) {
        this.bV.b(17, Byte.valueOf((byte) (var1 & 255)));
    }

    public int B() {
        return this.bV.a(17);
    }

    public boolean a(ODamageSource var1, int var2) {
        if (var1 instanceof OEntityDamageSourceIndirect) {
            for (int var3 = 0; var3 < 64; ++var3) {
                if (this.u_()) {
                    return true;
                }
            }

            return false;
        } else {
            return super.a(var1, var2);
        }
    }
   
    // CanaryMod start
    public static boolean canHoldItem(int blockID) {
        return b[blockID];
    }

    public static void setHoldable(int blockID, boolean holdable) {
        b[blockID] = holdable;
    }

    public static boolean getHoldable(int blockID) {
        return b[blockID];
    }

    // CanaryMod end

    static {
        b[OBlock.w.bO] = true;
        b[OBlock.x.bO] = true;
        b[OBlock.G.bO] = true;
        b[OBlock.H.bO] = true;
        b[OBlock.af.bO] = true;
        b[OBlock.ag.bO] = true;
        b[OBlock.ah.bO] = true;
        b[OBlock.ai.bO] = true;
        b[OBlock.ao.bO] = true;
        b[OBlock.aX.bO] = true;
        b[OBlock.aY.bO] = true;
        b[OBlock.bc.bO] = true;
        b[OBlock.bt.bO] = true;
        b[OBlock.bA.bO] = true;
    }
}
