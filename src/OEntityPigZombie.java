import java.util.Iterator;
import java.util.List;


public class OEntityPigZombie extends OEntityZombie {

    private int d = 0;
    private int e = 0;
    private static final OItemStack g = new OItemStack(OItem.G, 1);

    public OEntityPigZombie(OWorld oworld) {
        super(oworld);
        this.az = "/mob/pigzombie.png";
        this.bw = 0.5F;
        this.f = 5;
        this.ae = true;
    }

    protected boolean aV() {
        return false;
    }

    public void h_() {
        this.bw = this.a != null ? 0.95F : 0.5F;
        if (this.e > 0 && --this.e == 0) {
            this.p.a(this, "mob.zombiepig.zpigangry", this.aP() * 2.0F, ((this.Z.nextFloat() - this.Z.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.h_();
    }

    public boolean bi() {
        return this.p.u > 0 && this.p.b(this.D) && this.p.a((OEntity) this, this.D).isEmpty() && !this.p.d(this.D);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Anger", (short) this.d);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.d = onbttagcompound.d("Anger");
    }

    protected OEntity k() {
        return this.d == 0 ? null : super.k();
    }

    public boolean a(ODamageSource odamagesource, int i) {
        OEntity oentity = odamagesource.g();

        if (oentity instanceof OEntityPlayer) {
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentity.entity.getPlayer(), this.entity)) { // CanaryMod: MOB_TARGET

                List list = this.p.b((OEntity) this, this.D.b(32.0D, 32.0D, 32.0D));
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OEntity oentity1 = (OEntity) iterator.next();

                    if (oentity1 instanceof OEntityPigZombie) {
                        OEntityPigZombie oentitypigzombie = (OEntityPigZombie) oentity1;

                    oentitypigzombie.c(oentity);
                    }
                }

                this.c(oentity);
            }
        }

        return super.a(odamagesource, i);
    }

    private void c(OEntity oentity) {
        this.a = oentity;
        this.d = 400 + this.Z.nextInt(400);
        this.e = this.Z.nextInt(40);
    }

    protected String aQ() {
        return "mob.zombiepig.zpig";
    }

    protected String aR() {
        return "mob.zombiepig.zpighurt";
    }

    protected String aS() {
        return "mob.zombiepig.zpigdeath";
    }

    protected void a(boolean flag, int i) {
        int j = this.Z.nextInt(2 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.bm.bT, 1);
        }

        j = this.Z.nextInt(2 + i);

        for (k = 0; k < j; ++k) {
            this.b(OItem.bq.bT, 1);
        }

    }

    protected void l(int i) {
        if (i > 0) {
            OItemStack oitemstack = new OItemStack(OItem.G);

            OEnchantmentHelper.a(this.Z, oitemstack, 5);
            this.a(oitemstack, 0.0F);
        } else {
            int j = this.Z.nextInt(3);

            if (j == 0) {
                this.b(OItem.p.bT, 1);
            } else if (j == 1) {
                this.b(OItem.G.bT, 1);
            } else if (j == 2) {
                this.b(OItem.al.bT, 1);
            }
        }

    }

    protected int aT() {
        return OItem.bm.bT;
    }

}
