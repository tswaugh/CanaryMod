import java.util.ArrayList;
import java.util.Iterator;

public class OEntityFallingSand extends OEntity {

    public int a;
    public int b;
    public int c;
    public boolean d;
    private boolean e;
    private boolean f;
    private int g;
    private float h;

    public OEntityFallingSand(OWorld oworld) {
        super(oworld);
        this.c = 0;
        this.d = true;
        this.e = false;
        this.f = false;
        this.g = 20;
        this.h = 2.0F;
    }

    public OEntityFallingSand(OWorld oworld, double d0, double d1, double d2, int i) {
        this(oworld, d0, d1, d2, i, 0);
    }

    public OEntityFallingSand(OWorld oworld, double d0, double d1, double d2, int i, int j) {
        super(oworld);
        this.c = 0;
        this.d = true;
        this.e = false;
        this.f = false;
        this.g = 20;
        this.h = 2.0F;
        this.a = i;
        this.b = j;
        this.m = true;
        this.a(0.98F, 0.98F);
        this.M = this.O / 2.0F;
        this.b(d0, d1, d2);
        this.w = 0.0D;
        this.x = 0.0D;
        this.y = 0.0D;
        this.q = d0;
        this.r = d1;
        this.s = d2;
    }

    protected boolean f_() {
        return false;
    }

    protected void a() {}

    public boolean L() {
        return !this.L;
    }

    public void j_() {
        if (this.a == 0) {
            this.x();
        } else {
            this.q = this.t;
            this.r = this.u;
            this.s = this.v;
            ++this.c;
            this.x -= 0.03999999910593033D;
            this.d(this.w, this.x, this.y);
            this.w *= 0.9800000190734863D;
            this.x *= 0.9800000190734863D;
            this.y *= 0.9800000190734863D;
            if (!this.p.J) {
                int i = OMathHelper.c(this.t);
                int j = OMathHelper.c(this.u);
                int k = OMathHelper.c(this.v);

                if (this.c == 1) {
                    if (this.c == 1 && this.p.a(i, j, k) == this.a) {
                        this.p.e(i, j, k, 0);
                    } else {
                        this.x();
                    }
                }

                if (this.E) {
                    this.w *= 0.699999988079071D;
                    this.y *= 0.699999988079071D;
                    this.x *= -0.5D;
                    if (this.p.a(i, j, k) != OBlock.af.cm) {
                        this.x();
                        if (!this.e && this.p.a(this.a, i, j, k, true, 1, (OEntity) null) && !OBlockSand.a_(this.p, i, j - 1, k) && this.p.d(i, j, k, this.a, this.b)) {
                            if (OBlock.p[this.a] instanceof OBlockSand) {
                                ((OBlockSand) OBlock.p[this.a]).a_(this.p, i, j, k, this.b);
                            }
                        } else if (this.d && !this.e) {
                            this.a(new OItemStack(this.a, 1, OBlock.p[this.a].b(this.b)), 0.0F);
                        }
                    }
                } else if (this.c > 100 && !this.p.J && (j < 1 || j > 256) || this.c > 600) {
                    if (this.d) {
                        this.a(new OItemStack(this.a, 1, OBlock.p[this.a].b(this.b)), 0.0F);
                    }

                    this.x();
                }
            }
        }
    }

    protected void a(float f) {
        if (this.f) {
            int i = OMathHelper.f(f - 1.0F);

            if (i > 0) {
                ArrayList arraylist = new ArrayList(this.p.b((OEntity) this, this.D));
                ODamageSource odamagesource = this.a == OBlock.ck.cm ? ODamageSource.o : ODamageSource.p;
                Iterator iterator = arraylist.iterator();

                while (iterator.hasNext()) {
                    OEntity oentity = (OEntity) iterator.next();

                    if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.fromDamageSource(odamagesource), new BaseEntity(this), oentity.getEntity())) {
                        oentity.a(odamagesource, Math.min(OMathHelper.d((float) i * this.h), this.g));
                    }
                }

                if (this.a == OBlock.ck.cm && (double) this.aa.nextFloat() < 0.05000000074505806D + (double) i * 0.05D) {
                    int j = this.b >> 2;
                    int k = this.b & 3;

                    ++j;
                    if (j > 2) {
                        this.e = true;
                    } else {
                        this.b = k | j << 2;
                    }
                }
            }
        }
    }

    protected void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Tile", (byte) this.a);
        onbttagcompound.a("Data", (byte) this.b);
        onbttagcompound.a("Time", (byte) this.c);
        onbttagcompound.a("DropItem", this.d);
        onbttagcompound.a("HurtEntities", this.f);
        onbttagcompound.a("FallHurtAmount", this.h);
        onbttagcompound.a("FallHurtMax", this.g);
    }

    protected void a(ONBTTagCompound onbttagcompound) {
        this.a = onbttagcompound.c("Tile") & 255;
        this.b = onbttagcompound.c("Data") & 255;
        this.c = onbttagcompound.c("Time") & 255;
        if (onbttagcompound.b("HurtEntities")) {
            this.f = onbttagcompound.n("HurtEntities");
            this.h = onbttagcompound.g("FallHurtAmount");
            this.g = onbttagcompound.e("FallHurtMax");
        } else if (this.a == OBlock.ck.cm) {
            this.f = true;
        }

        if (onbttagcompound.b("DropItem")) {
            this.d = onbttagcompound.n("DropItem");
        }

        if (this.a == 0) {
            this.a = OBlock.H.cm;
        }
    }

    public void e(boolean flag) {
        this.f = flag;
    }
}
