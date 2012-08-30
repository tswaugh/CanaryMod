
import java.util.Iterator;

public class OEntityItem extends OEntity {

    public OItemStack a;
    public int b = 0;
    public int c;
    private int e = 5;
    public float d = (float) (Math.random() * 3.141592653589793D * 2.0D);
    // CanaryMod Start
    ItemEntity item = new ItemEntity(this);

    // CanaryMod End

    public OEntityItem(OWorld oworld, double d0, double d1, double d2, OItemStack oitemstack) {
        super(oworld);
        this.a(0.25F, 0.25F);
        this.M = this.O / 2.0F;
        this.b(d0, d1, d2);
        this.a = oitemstack;
        this.z = (float) (Math.random() * 360.0D);
        this.w = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.x = 0.20000000298023224D;
        this.y = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
    }

    protected boolean e_() {
        return false;
    }

    public OEntityItem(OWorld oworld) {
        super(oworld);
        this.a(0.25F, 0.25F);
        this.M = this.O / 2.0F;
    }

    protected void a() {}

    public void h_() {
        super.h_();
        if (this.c > 0) {
            --this.c;
        }
        
        // CanaryMod start
        boolean tmpTouchesGround = this.E;
        // CanaryMod end

        this.q = this.t;
        this.r = this.u;
        this.s = this.v;
        this.x -= 0.03999999910593033D;
        this.i(this.t, (this.D.b + this.D.e) / 2.0D, this.v);
        this.d(this.w, this.x, this.y);
        boolean flag = (int) this.q != (int) this.t || (int) this.r != (int) this.u || (int) this.s != (int) this.v;

        if (flag) {
            if (this.p.f(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v)) == OMaterial.h) {
                this.x = 0.20000000298023224D;
                this.w = (double) ((this.Z.nextFloat() - this.Z.nextFloat()) * 0.2F);
                this.y = (double) ((this.Z.nextFloat() - this.Z.nextFloat()) * 0.2F);
                this.p.a(this, "random.fizz", 0.4F, 2.0F + this.Z.nextFloat() * 0.4F);
            }

            if (!this.p.K) {
                Iterator iterator = this.p.a(OEntityItem.class, this.D.b(0.5D, 0.0D, 0.5D)).iterator();

                while (iterator.hasNext()) {
                    OEntityItem oentityitem = (OEntityItem) iterator.next();

                    this.a(oentityitem);
                }
            }
        }

        float f = 0.98F;

        if (this.E) {
            f = 0.58800006F;
            int i = this.p.a(OMathHelper.c(this.t), OMathHelper.c(this.D.b) - 1, OMathHelper.c(this.v));

            if (i > 0) {
                f = OBlock.m[i].cq * 0.98F;
            }
            
            // CanaryMod start
            // It does touch the ground now, but didn't in last tick
            if (!tmpTouchesGround) {
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_TOUCH_GROUND, item)) {
                    this.y(); // kill the item
                }
            }
            // CanaryMod end
        }

        this.w *= (double) f;
        this.x *= 0.9800000190734863D;
        this.y *= (double) f;
        if (this.E) {
            this.x *= -0.5D;
        }

        ++this.b;
        if (this.b >= 6000) {
            // CanaryMod onEntityDespawn
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENTITY_DESPAWN, item)) {
                this.y();
            } else {
                this.b = 0;
            }
        }
    }

    public boolean a(OEntityItem oentityitem) {
        if (oentityitem == this) {
            return false;
        } else if (oentityitem.S() && this.S()) {
            if (oentityitem.a.b() != this.a.b()) {
                return false;
            } else if (oentityitem.a.b().k() && oentityitem.a.j() != this.a.j()) {
                return false;
            } else if (oentityitem.a.a < this.a.a) {
                return oentityitem.a(this);
            } else if (oentityitem.a.a + this.a.a > oentityitem.a.d()) {
                return false;
            } else {
                oentityitem.a.a += this.a.a;
                oentityitem.c = Math.max(oentityitem.c, this.c);
                oentityitem.b = Math.min(oentityitem.b, this.b);
                this.y();
                return true;
            }
        } else {
            return false;
        }

    }

    public void d() {
        this.b = 4800;
    }

    public boolean I() {
        return this.p.a(this.D, OMaterial.g, this);
    }

    protected void e(int i) {
        this.a(ODamageSource.a, i);
    }

    public boolean a(ODamageSource odamagesource, int i) {
        this.K();
        this.e -= i;
        if (this.e <= 0) {
            this.y();
        }

        return false;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Health", (short) ((byte) this.e));
        onbttagcompound.a("Age", (short) this.b);
        if (this.a != null) {
            onbttagcompound.a("Item", this.a.b(new ONBTTagCompound()));
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.e = onbttagcompound.d("Health") & 255;
        this.b = onbttagcompound.d("Age");
        ONBTTagCompound onbttagcompound1 = onbttagcompound.l("Item");

        this.a = OItemStack.a(onbttagcompound1);
        if (this.a == null) {
            this.y();
        }

    }

    public void b_(OEntityPlayer oentityplayer) {
        if (!this.p.K) {
            int i = this.a.a;

            // CanaryMod: First simulate the pickup and call the hooks
            if (this.c == 0 && oentityplayer.by.canPickup(this)) {
                if (oentityplayer.by.a(this.a)) {
                    if (this.a.c == OBlock.J.ca) {
                        oentityplayer.a((OStatBase) OAchievementList.g);
                    }

                    if (this.a.c == OItem.aF.bT) {
                        oentityplayer.a((OStatBase) OAchievementList.t);
                    }

                    if (this.a.c == OItem.n.bT) {
                        oentityplayer.a((OStatBase) OAchievementList.w);
                    }

                    if (this.a.c == OItem.bo.bT) {
                        oentityplayer.a((OStatBase) OAchievementList.z);
                    }

                    this.p.a(this, "random.pop", 0.2F, ((this.Z.nextFloat() - this.Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    oentityplayer.a((OEntity) this, i);
                    if (this.a.a <= 0) {
                        this.y();
                    }
                }
            }

        }
    }

    public String ak() {
        return OStatCollector.a("item." + this.a.a());
    }

    public boolean an() {
        return false;
    }
}
