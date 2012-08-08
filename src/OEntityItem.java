
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
        this.b(0.25F, 0.25F);
        this.bF = this.bH / 2.0F;
        this.c(d0, d1, d2);
        this.a = oitemstack;
        this.bs = (float) (Math.random() * 360.0D);
        this.bp = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.bq = 0.20000000298023224D;
        this.br = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
    }

    protected boolean g_() {
        return false;
    }

    public OEntityItem(OWorld oworld) {
        super(oworld);
        this.b(0.25F, 0.25F);
        this.bF = this.bH / 2.0F;
    }

    protected void b() {}

    public void F_() {
        super.F_();
        if (this.c > 0) {
            --this.c;
        }
        
        // CanaryMod start
        boolean tmpTouchesGround = this.bx;

        // CanaryMod end

        this.bj = this.bm;
        this.bk = this.bn;
        this.bl = this.bo;
        this.bq -= 0.03999999910593033D;
        if (this.bi.d(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo)) == OMaterial.h) {
            this.bq = 0.20000000298023224D;
            this.bp = (double) ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F);
            this.br = (double) ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F);
            this.bi.a(this, "random.fizz", 0.4F, 2.0F + this.bS.nextFloat() * 0.4F);
        }

        this.g(this.bm, (this.bw.b + this.bw.e) / 2.0D, this.bo);
        this.a(this.bp, this.bq, this.br);
        float f = 0.98F;

        if (this.bx) {
            f = 0.58800006F;
            int i = this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bw.b) - 1, OMathHelper.b(this.bo));

            if (i > 0) {
                f = OBlock.m[i].ce * 0.98F;
            }
            
            // CanaryMod start
            // It does touch the ground now, but didn't in last tick
            if (!tmpTouchesGround) {
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_TOUCH_GROUND, item)) {
                    this.X(); // kill the item
                }
            }
            // CanaryMod end
        }

        this.bp *= (double) f;
        this.bq *= 0.9800000190734863D;
        this.br *= (double) f;
        if (this.bx) {
            this.bq *= -0.5D;
        }

        ++this.b;
        if (this.b >= 6000) {
            // CanaryMod onEntityDespawn
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENTITY_DESPAWN, item)) {
                this.X();
            } else {
                this.b = 0;
            }
        }

    }

    public void k() {
        this.b = 4800;
    }

    public boolean h_() {
        return this.bi.a(this.bw, OMaterial.g, this);
    }

    protected void a(int i) {
        this.a(ODamageSource.b, i);
    }

    public boolean a(ODamageSource odamagesource, int i) {
        this.aW();
        this.e -= i;
        if (this.e <= 0) {
            this.X();
        }

        return false;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Health", (short) ((byte) this.e));
        onbttagcompound.a("Age", (short) this.b);
        onbttagcompound.a("Item", this.a.b(new ONBTTagCompound()));
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.e = onbttagcompound.e("Health") & 255;
        this.b = onbttagcompound.e("Age");
        ONBTTagCompound onbttagcompound1 = onbttagcompound.m("Item");

        this.a = OItemStack.a(onbttagcompound1);
        if (this.a == null) {
            this.X();
        }

    }

    public void a_(OEntityPlayer oentityplayer) {
        if (!this.bi.F) {
            int i = this.a.a;

            // CanaryMod: First simulate the pickup and call the hooks
            if (this.c == 0 && oentityplayer.k.canPickup(this)) {
                if (oentityplayer.k.a(this.a)) {
                    if (this.a.c == OBlock.J.bO) {
                        oentityplayer.a((OStatBase) OAchievementList.g);
                    }

                    if (this.a.c == OItem.aE.bP) {
                        oentityplayer.a((OStatBase) OAchievementList.t);
                    }

                    if (this.a.c == OItem.m.bP) {
                        oentityplayer.a((OStatBase) OAchievementList.w);
                    }

                    if (this.a.c == OItem.bn.bP) {
                        oentityplayer.a((OStatBase) OAchievementList.z);
                    }

                    this.bi.a(this, "random.pop", 0.2F, ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    oentityplayer.a((OEntity) this, i);
                    if (this.a.a <= 0) {
                        this.X();
                    }
                }
            }

        }
    }

    public String s() {
        return OStatCollector.a("item." + this.a.k());
    }

    public boolean k_() {
        return false;
    }
}
