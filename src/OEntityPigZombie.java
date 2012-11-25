import java.util.List;

public class OEntityPigZombie extends OEntityZombie {

    private int d = 0;
    private int e = 0;

    public OEntityPigZombie(OWorld oworld) {
        super(oworld);
        this.aF = "/mob/pigzombie.png";
        this.bG = 0.5F;
        this.af = true;
    }

    protected boolean be() {
        return false;
    }

    public void j_() {
        this.bG = this.a_ != null ? 0.95F : 0.5F;
        if (this.e > 0 && --this.e == 0) {
            this.a("mob.zombiepig.zpigangry", this.aX() * 2.0F, ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.j_();
    }

    public boolean bs() {
        return this.p.t > 0 && this.p.b(this.D) && this.p.a((OEntity) this, this.D).isEmpty() && !this.p.d(this.D);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Anger", (short) this.d);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.d = onbttagcompound.d("Anger");
    }

    protected OEntity j() {
        return this.d == 0 ? null : super.j();
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.ar()) {
            return false;
        } else {
            OEntity oentity = odamagesource.g();

            if (oentity instanceof OEntityPlayer) {
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentity.getEntity(), this.getEntity())) { // CanaryMod: MOB_TARGET
                    List list = this.p.b((OEntity) this, this.D.b(32.0D, 32.0D, 32.0D));

                    for (int j = 0; j < list.size(); ++j) {
                        OEntity oentity1 = (OEntity) list.get(j);

                        if (oentity1 instanceof OEntityPigZombie) {
                            OEntityPigZombie oentitypigzombie = (OEntityPigZombie) oentity1;

                            oentitypigzombie.p(oentity);
                        }
                    }

                    this.p(oentity);
                } //
            }

            return super.a(odamagesource, i);
        }
    }

    private void p(OEntity oentity) {
        this.a_ = oentity;
        this.d = 400 + this.aa.nextInt(400);
        this.e = this.aa.nextInt(40);
    }

    protected String aY() {
        return "mob.zombiepig.zpig";
    }

    protected String aZ() {
        return "mob.zombiepig.zpighurt";
    }

    protected String ba() {
        return "mob.zombiepig.zpigdeath";
    }

    protected void a(boolean flag, int i) {
        int j = this.aa.nextInt(2 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.bm.cg, 1);
        }

        j = this.aa.nextInt(2 + i);

        for (k = 0; k < j; ++k) {
            this.b(OItem.bq.cg, 1);
        }
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return false;
    }

    protected void l(int i) {
        this.b(OItem.p.cg, 1);
    }

    protected int bb() {
        return OItem.bm.cg;
    }

    protected void bE() {
        this.b(0, new OItemStack(OItem.G));
    }

    public void bG() {
        super.bG();
        this.g(false);
    }

    public int c(OEntity oentity) {
        OItemStack oitemstack = this.bD();
        int i = 5;

        if (oitemstack != null) {
            i += oitemstack.a((OEntity) this);
        }

        return i;
    }
}
