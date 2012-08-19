import java.util.List;


public class OEntityPigZombie extends OEntityZombie {

    private int a = 0;
    private int b = 0;
    private static final OItemStack g = new OItemStack(OItem.F, 1);

    public OEntityPigZombie(OWorld oworld) {
        super(oworld);
        this.ae = "/mob/pigzombie.png";
        this.bb = 0.5F;
        this.c = 5;
        this.bX = true;
    }

    protected boolean c_() {
        return false;
    }

    public void F_() {
        this.bb = this.d != null ? 0.95F : 0.5F;
        if (this.b > 0 && --this.b == 0) {
            this.bi.a(this, "mob.zombiepig.zpigangry", this.p() * 2.0F, ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.F_();
    }

    public boolean l() {
        return this.bi.q > 0 && this.bi.a(this.bw) && this.bi.a((OEntity) this, this.bw).size() == 0 && !this.bi.c(this.bw);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Anger", (short) this.a);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a = onbttagcompound.e("Anger");
    }

    protected OEntity o() {
        return this.a == 0 ? null : super.o();
    }

    public void e() {
        super.e();
    }

    public boolean a(ODamageSource odamagesource, int i) {
        OEntity oentity = odamagesource.a();

        if (oentity instanceof OEntityPlayer) {
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentity.entity.getPlayer(), this.entity)) { // CanaryMod: MOB_TARGET

                List list = this.bi.b((OEntity) this, this.bw.b(32.0D, 32.0D, 32.0D));

                for (int j = 0; j < list.size(); ++j) {
                    OEntity oentity1 = (OEntity) list.get(j);

                    if (oentity1 instanceof OEntityPigZombie) {
                        OEntityPigZombie oentitypigzombie = (OEntityPigZombie) oentity1;

                        oentitypigzombie.e(oentity);
                    }
                }

                this.e(oentity);
            }
        }

        return super.a(odamagesource, i);
    }

    private void e(OEntity oentity) {
        this.d = oentity;
        this.a = 400 + this.bS.nextInt(400);
        this.b = this.bS.nextInt(40);
    }

    protected String i() {
        return "mob.zombiepig.zpig";
    }

    protected String j() {
        return "mob.zombiepig.zpighurt";
    }

    protected String k() {
        return "mob.zombiepig.zpigdeath";
    }

    protected void a(boolean flag, int i) {
        int j = this.bS.nextInt(2 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.bl.bP, 1);
        }

        j = this.bS.nextInt(2 + i);

        for (k = 0; k < j; ++k) {
            this.b(OItem.bp.bP, 1);
        }

    }

    protected void b(int i) {
        if (i > 0) {
            OItemStack oitemstack = new OItemStack(OItem.F);

            OEnchantmentHelper.a(this.bS, oitemstack, 5);
            this.a(oitemstack, 0.0F);
        } else {
            int j = this.bS.nextInt(3);

            if (j == 0) {
                this.b(OItem.o.bP, 1);
            } else if (j == 1) {
                this.b(OItem.F.bP, 1);
            } else if (j == 2) {
                this.b(OItem.ak.bP, 1);
            }
        }

    }

    protected int f() {
        return OItem.bl.bP;
    }

}
