
public class OEntityEnderman extends OEntityMob {

    private static boolean[] d = new boolean[256];
    private int e = 0;
    private int g = 0;
    
    // CanaryMod Start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    Enderman entity = new Enderman(this);
    // CanaryMod End

    public OEntityEnderman(OWorld oworld) {
        super(oworld);
        this.az = "/mob/enderman.png";
        this.bw = 0.2F;
        this.f = 7;
        this.a(0.6F, 2.9F);
        this.W = 1.0F;
    }

    public int aM() {
        return 40;
    }

    protected void a() {
        super.a();
        this.af.a(16, new Byte((byte) 0));
        this.af.a(17, new Byte((byte) 0));
        this.af.a(18, new Byte((byte) 0));
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("carried", (short) this.p());
        onbttagcompound.a("carriedData", (short) this.q());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a(onbttagcompound.d("carried"));
        this.b(onbttagcompound.d("carriedData"));
    }

    protected OEntity k() {
        OEntityPlayer oentityplayer = this.p.b(this, 64.0D);

        if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentityplayer.entity.getPlayer(), entity)) {
            if (this.d(oentityplayer)) {
                if (this.g++ == 5) {
                    this.g = 0;
                    this.e(true);
                    return oentityplayer;
                }
            } else {
                this.g = 0;
            }
        }

        return null;
    }

    private boolean d(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.by.b[3];

        if (oitemstack != null && oitemstack.c == OBlock.ba.ca) {
            return false;
        } else {
            OVec3 ovec3 = oentityplayer.i(1.0F).b();
            OVec3 ovec31 = OVec3.a().a(this.t - oentityplayer.t, this.D.b + (double) (this.O / 2.0F) - (oentityplayer.u + (double) oentityplayer.e()), this.v - oentityplayer.v);
            double d0 = ovec31.c();

            ovec31 = ovec31.b();
            double d1 = ovec3.b(ovec31);

            return d1 > 1.0D - 0.025D / d0 ? oentityplayer.l(this) : false;
        }
    }

    public void d() {
        if (this.G()) {
            this.a(ODamageSource.e, 1);
        }

        this.bw = this.a != null ? 6.5F : 0.3F;
        int i;

        if (!this.p.K) {
            int j;
            int k;
            int l;

            if (this.p() == 0) {
                if (this.Z.nextInt(20) == 0) {
                    i = OMathHelper.c(this.t - 2.0D + this.Z.nextDouble() * 4.0D);
                    j = OMathHelper.c(this.u + this.Z.nextDouble() * 3.0D);
                    k = OMathHelper.c(this.v - 2.0D + this.Z.nextDouble() * 4.0D);
                    l = this.p.a(i, j, k);
                    if (d[l] && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_PICKUP, entity, new Block(entity.getWorld(), l, i, j, k, entity.getWorld().getBlockIdAt(i, j, k)))) {
                        this.a(this.p.a(i, j, k));
                        this.b(this.p.g(i, j, k));
                        this.p.e(i, j, k, 0);
                    }
                }
            } else if (this.Z.nextInt(2000) == 0) {
                i = OMathHelper.c(this.t - 1.0D + this.Z.nextDouble() * 2.0D);
                j = OMathHelper.c(this.u + this.Z.nextDouble() * 2.0D);
                k = OMathHelper.c(this.v - 1.0D + this.Z.nextDouble() * 2.0D);
                l = this.p.a(i, j, k);
                int i1 = this.p.a(i, j - 1, k);

                if (l == 0 && i1 > 0 && OBlock.m[i1].c() && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_DROP, entity, new Block(entity.getWorld(), i1, i, j, k, entity.getWorld().getBlockIdAt(i, j, k)))) {
                    this.p.d(i, j, k, this.p(), this.q());
                    this.a(0);
                }
            }
        }

        for (i = 0; i < 2; ++i) {
            this.p.a("portal", this.t + (this.Z.nextDouble() - 0.5D) * (double) this.N, this.u + this.Z.nextDouble() * (double) this.O - 0.25D, this.v + (this.Z.nextDouble() - 0.5D) * (double) this.N, (this.Z.nextDouble() - 0.5D) * 2.0D, -this.Z.nextDouble(), (this.Z.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.p.s() && !this.p.K) {
            float f = this.c(1.0F);

            if (f > 0.5F && this.p.j(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v)) && this.Z.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.a = null;
                this.e(false);
                this.n();
            }
        }

        if (this.G()) {
            this.a = null;
            this.e(false);
            this.n();
        }

        this.bu = false;
        if (this.a != null) {
            this.a(this.a, 100.0F, 100.0F);
        }

        if (!this.p.K && this.S()) {
            if (this.a != null) {
                if (this.a instanceof OEntityPlayer && this.d((OEntityPlayer) this.a)) {
                    this.br = this.bs = 0.0F;
                    this.bw = 0.0F;
                    if (this.a.e((OEntity) this) < 16.0D) {
                        this.n();
                    }

                    this.e = 0;
                } else if (this.a.e((OEntity) this) > 256.0D && this.e++ >= 30 && this.c(this.a)) {
                    this.e = 0;
                }
            } else {
                this.e(false);
                this.e = 0;
            }
        }

        super.d();
    }

    protected boolean n() {
        double d0 = this.t + (this.Z.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.u + (double) (this.Z.nextInt(64) - 32);
        double d2 = this.v + (this.Z.nextDouble() - 0.5D) * 64.0D;

        return this.j(d0, d1, d2);
    }

    protected boolean c(OEntity oentity) {
        OVec3 ovec3 = OVec3.a().a(this.t - oentity.t, this.D.b + (double) (this.O / 2.0F) - oentity.u + (double) oentity.e(), this.v - oentity.v);

        ovec3 = ovec3.b();
        double d0 = 16.0D;
        double d1 = this.t + (this.Z.nextDouble() - 0.5D) * 8.0D - ovec3.a * d0;
        double d2 = this.u + (double) (this.Z.nextInt(16) - 8) - ovec3.b * d0;
        double d3 = this.v + (this.Z.nextDouble() - 0.5D) * 8.0D - ovec3.c * d0;

        return this.j(d1, d2, d3);
    }

    protected boolean j(double d0, double d1, double d2) {
        double d3 = this.t;
        double d4 = this.u;
        double d5 = this.v;

        this.t = d0;
        this.u = d1;
        this.v = d2;
        boolean flag = false;
        int i = OMathHelper.c(this.t);
        int j = OMathHelper.c(this.u);
        int k = OMathHelper.c(this.v);
        int l;

        if (this.p.e(i, j, k)) {
            boolean flag1 = false;

            while (!flag1 && j > 0) {
                l = this.p.a(i, j - 1, k);
                if (l != 0 && OBlock.m[l].cp.c()) {
                    flag1 = true;
                } else {
                    --this.u;
                    --j;
                }
            }

            if (flag1) {
                this.b(this.t, this.u, this.v);
                if (this.p.a((OEntity) this, this.D).isEmpty() && !this.p.d(this.D)) {
                    flag = true;
                }
            }
        }

        if (!flag) {
            this.b(d3, d4, d5);
            return false;
        } else {
            short short1 = 128;

            for (l = 0; l < short1; ++l) {
                double d6 = (double) l / ((double) short1 - 1.0D);
                float f = (this.Z.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.Z.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.Z.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.t - d3) * d6 + (this.Z.nextDouble() - 0.5D) * (double) this.N * 2.0D;
                double d8 = d4 + (this.u - d4) * d6 + this.Z.nextDouble() * (double) this.O;
                double d9 = d5 + (this.v - d5) * d6 + (this.Z.nextDouble() - 0.5D) * (double) this.N * 2.0D;

                this.p.a("portal", d7, d8, d9, (double) f, (double) f1, (double) f2);
            }

            this.p.a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.p.a(this, "mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }

    protected String aQ() {
        return "mob.endermen.idle";
    }

    protected String aR() {
        return "mob.endermen.hit";
    }

    protected String aS() {
        return "mob.endermen.death";
    }

    protected int aT() {
        return OItem.bn.bT;
    }

    protected void a(boolean flag, int i) {
        int j = this.aT();

        if (j > 0) {
            int k = this.Z.nextInt(2 + i);

            for (int l = 0; l < k; ++l) {
                this.b(j, 1);
            }
        }
    }

    public void a(int i) {
        this.af.b(16, Byte.valueOf((byte) (i & 255)));
    }

    public int p() {
        return this.af.a(16);
    }

    public void b(int i) {
        this.af.b(17, Byte.valueOf((byte) (i & 255)));
    }

    public int q() {
        return this.af.a(17);
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (odamagesource instanceof OEntityDamageSourceIndirect) {
            for (int j = 0; j < 64; ++j) {
                if (this.n()) {
                    return true;
                }
            }

            return false;
        } else {
            if (odamagesource.g() instanceof OEntityPlayer) {
                this.e(true);
            }

            return super.a(odamagesource, i);
        }
    }
    
    public void e(boolean flag) {
        this.af.b(18, Byte.valueOf((byte) (flag ? 1 : 0)));
    }

    // CanaryMod start
    public static boolean canHoldItem(int i) {
        return d[i];
    }

    public static void setHoldable(int i, boolean flag) {
        d[i] = flag;
    }

    public static boolean getHoldable(int i) {
        return d[i];
    }
    // CanaryMod end

    static {
        d[OBlock.u.ca] = true;
        d[OBlock.v.ca] = true;
        d[OBlock.E.ca] = true;
        d[OBlock.F.ca] = true;
        d[OBlock.ad.ca] = true;
        d[OBlock.ae.ca] = true;
        d[OBlock.af.ca] = true;
        d[OBlock.ag.ca] = true;
        d[OBlock.am.ca] = true;
        d[OBlock.aV.ca] = true;
        d[OBlock.aW.ca] = true;
        d[OBlock.ba.ca] = true;
        d[OBlock.br.ca] = true;
        d[OBlock.by.ca] = true;
    }
}
