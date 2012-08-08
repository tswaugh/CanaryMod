
public class OEntityEnderman extends OEntityMob {

    private static boolean[] b = new boolean[256];
    public boolean a = false;
    private int g = 0;
    private int h = 0;
    // CanaryMod Start
    Enderman entity = new Enderman(this);

    // CanaryMod End

    public OEntityEnderman(OWorld oworld) {
        super(oworld);
        this.ae = "/mob/enderman.png";
        this.bb = 0.2F;
        this.c = 7;
        this.b(0.6F, 2.9F);
        this.bP = 1.0F;
    }

    public int d() {
        return 40;
    }

    protected void b() {
        super.b();
        this.bY.a(16, new Byte((byte) 0));
        this.bY.a(17, new Byte((byte) 0));
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("carried", (short) this.A());
        onbttagcompound.a("carriedData", (short) this.E());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.c(onbttagcompound.e("carried"));
        this.e(onbttagcompound.e("carriedData"));
    }

    protected OEntity o() {
        OEntityPlayer oentityplayer = this.bi.b(this, 64.0D);

        if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentityplayer.entity.getPlayer(), entity)) { // CanaryMod - MOB_TARGET
            if (this.c(oentityplayer)) {
                if (this.h++ == 5) {
                    this.h = 0;
                    return oentityplayer;
                }
            } else {
                this.h = 0;
            }
        }

        return null;
    }

    public float b(float f) {
        return super.b(f);
    }

    private boolean c(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.k.b[3];

        if (oitemstack != null && oitemstack.c == OBlock.ba.bO) {
            return false;
        } else {
            OVec3D ovec3d = oentityplayer.f(1.0F).b();
            OVec3D ovec3d1 = OVec3D.b(this.bm - oentityplayer.bm, this.bw.b + (double) (this.bH / 2.0F) - (oentityplayer.bn + (double) oentityplayer.B()), this.bo - oentityplayer.bo);
            double d0 = ovec3d1.c();

            ovec3d1 = ovec3d1.b();
            double d1 = ovec3d.a(ovec3d1);

            return d1 > 1.0D - 0.025D / d0 ? oentityplayer.h(this) : false;
        }
    }

    public void e() {
        if (this.aT()) {
            this.a(ODamageSource.f, 1);
        }

        this.a = this.d != null;
        this.bb = this.d != null ? 6.5F : 0.3F;
        int i;

        if (!this.bi.F) {
            int j;
            int k;
            int l;

            if (this.A() == 0) {
                if (this.bS.nextInt(20) == 0) {
                    i = OMathHelper.b(this.bm - 2.0D + this.bS.nextDouble() * 4.0D);
                    j = OMathHelper.b(this.bn + this.bS.nextDouble() * 3.0D);
                    k = OMathHelper.b(this.bo - 2.0D + this.bS.nextDouble() * 4.0D);
                    l = this.bi.a(i, j, k);
                    if (b[l]) {
                        // CanaryMod onEndermanPickup
                        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_PICKUP, entity, new Block(l, i, j, k, this.bi.a(i, j, k)))) {
                            this.c(this.bi.a(i, j, k));
                            this.e(this.bi.c(i, j, k));
                            this.bi.e(i, j, k, 0);
                        }
                    }
                }
            } else if (this.bS.nextInt(2000) == 0) {
                i = OMathHelper.b(this.bm - 1.0D + this.bS.nextDouble() * 2.0D);
                j = OMathHelper.b(this.bn + this.bS.nextDouble() * 2.0D);
                k = OMathHelper.b(this.bo - 1.0D + this.bS.nextDouble() * 2.0D);
                l = this.bi.a(i, j, k);
                int i1 = this.bi.a(i, j - 1, k);

                if (l == 0 && i1 > 0 && OBlock.m[i1].b()) {
                    // CanaryMod onEndermanDrop
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_DROP, entity, new Block(i1, i, j, k, this.bi.a(i, j, k)))) {
                        this.bi.b(i, j, k, this.A(), this.E());
                        this.c(0);
                    }
                }
            }
        }

        for (i = 0; i < 2; ++i) {
            this.bi.a("portal", this.bm + (this.bS.nextDouble() - 0.5D) * (double) this.bG, this.bn + this.bS.nextDouble() * (double) this.bH - 0.25D, this.bo + (this.bS.nextDouble() - 0.5D) * (double) this.bG, (this.bS.nextDouble() - 0.5D) * 2.0D, -this.bS.nextDouble(), (this.bS.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.bi.e() && !this.bi.F) {
            float f = this.b(1.0F);

            if (f > 0.5F && this.bi.l(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo)) && this.bS.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.d = null;
                this.x();
            }
        }

        if (this.aT()) {
            this.d = null;
            this.x();
        }

        this.aZ = false;
        if (this.d != null) {
            this.a(this.d, 100.0F, 100.0F);
        }

        if (!this.bi.F && this.aE()) {
            if (this.d != null) {
                if (this.d instanceof OEntityPlayer && this.c((OEntityPlayer) this.d)) {
                    this.aW = this.aX = 0.0F;
                    this.bb = 0.0F;
                    if (this.d.j(this) < 16.0D) {
                        this.x();
                    }

                    this.g = 0;
                } else if (this.d.j(this) > 256.0D && this.g++ >= 30 && this.e(this.d)) {
                    this.g = 0;
                }
            } else {
                this.g = 0;
            }
        }

        super.e();
    }

    protected boolean x() {
        double d0 = this.bm + (this.bS.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.bn + (double) (this.bS.nextInt(64) - 32);
        double d2 = this.bo + (this.bS.nextDouble() - 0.5D) * 64.0D;

        return this.b(d0, d1, d2);
    }

    protected boolean e(OEntity oentity) {
        OVec3D ovec3d = OVec3D.b(this.bm - oentity.bm, this.bw.b + (double) (this.bH / 2.0F) - oentity.bn + (double) oentity.B(), this.bo - oentity.bo);

        ovec3d = ovec3d.b();
        double d0 = 16.0D;
        double d1 = this.bm + (this.bS.nextDouble() - 0.5D) * 8.0D - ovec3d.a * d0;
        double d2 = this.bn + (double) (this.bS.nextInt(16) - 8) - ovec3d.b * d0;
        double d3 = this.bo + (this.bS.nextDouble() - 0.5D) * 8.0D - ovec3d.c * d0;

        return this.b(d1, d2, d3);
    }

    protected boolean b(double d0, double d1, double d2) {
        double d3 = this.bm;
        double d4 = this.bn;
        double d5 = this.bo;

        this.bm = d0;
        this.bn = d1;
        this.bo = d2;
        boolean flag = false;
        int i = OMathHelper.b(this.bm);
        int j = OMathHelper.b(this.bn);
        int k = OMathHelper.b(this.bo);
        int l;

        if (this.bi.i(i, j, k)) {
            boolean flag1 = false;

            while (!flag1 && j > 0) {
                l = this.bi.a(i, j - 1, k);
                if (l != 0 && OBlock.m[l].cd.c()) {
                    flag1 = true;
                } else {
                    --this.bn;
                    --j;
                }
            }

            if (flag1) {
                this.c(this.bm, this.bn, this.bo);
                if (this.bi.a((OEntity) this, this.bw).size() == 0 && !this.bi.c(this.bw)) {
                    flag = true;
                }
            }
        }

        if (!flag) {
            this.c(d3, d4, d5);
            return false;
        } else {
            short short1 = 128;

            for (l = 0; l < short1; ++l) {
                double d6 = (double) l / ((double) short1 - 1.0D);
                float f = (this.bS.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.bS.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.bS.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.bm - d3) * d6 + (this.bS.nextDouble() - 0.5D) * (double) this.bG * 2.0D;
                double d8 = d4 + (this.bn - d4) * d6 + this.bS.nextDouble() * (double) this.bH;
                double d9 = d5 + (this.bo - d5) * d6 + (this.bS.nextDouble() - 0.5D) * (double) this.bG * 2.0D;

                this.bi.a("portal", d7, d8, d9, (double) f, (double) f1, (double) f2);
            }

            this.bi.a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.bi.a(this, "mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }

    protected String i() {
        return "mob.endermen.idle";
    }

    protected String j() {
        return "mob.endermen.hit";
    }

    protected String k() {
        return "mob.endermen.death";
    }

    protected int f() {
        return OItem.bm.bP;
    }

    protected void a(boolean flag, int i) {
        int j = this.f();

        if (j > 0) {
            int k = this.bS.nextInt(2 + i);

            for (int l = 0; l < k; ++l) {
                this.b(j, 1);
            }
        }

    }

    public void c(int i) {
        this.bY.b(16, Byte.valueOf((byte) (i & 255)));
    }

    public int A() {
        return this.bY.a(16);
    }

    public void e(int i) {
        this.bY.b(17, Byte.valueOf((byte) (i & 255)));
    }

    public int E() {
        return this.bY.a(17);
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (odamagesource instanceof OEntityDamageSourceIndirect) {
            for (int j = 0; j < 64; ++j) {
                if (this.x()) {
                    return true;
                }
            }

            return false;
        } else {
            return super.a(odamagesource, i);
        }
    }
    
    // CanaryMod start
    public static boolean canHoldItem(int i) {
        return b[i];
    }

    public static void setHoldable(int i, boolean flag) {
        b[i] = flag;
    }

    public static boolean getHoldable(int i) {
        return b[i];
    }

    // CanaryMod end

    static {
        b[OBlock.u.bO] = true;
        b[OBlock.v.bO] = true;
        b[OBlock.E.bO] = true;
        b[OBlock.F.bO] = true;
        b[OBlock.ad.bO] = true;
        b[OBlock.ae.bO] = true;
        b[OBlock.af.bO] = true;
        b[OBlock.ag.bO] = true;
        b[OBlock.am.bO] = true;
        b[OBlock.aV.bO] = true;
        b[OBlock.aW.bO] = true;
        b[OBlock.ba.bO] = true;
        b[OBlock.br.bO] = true;
        b[OBlock.by.bO] = true;
    }
}
