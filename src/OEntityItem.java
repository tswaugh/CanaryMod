
public class OEntityItem extends OEntity {

    public OItemStack a;
    private int e;
    public int b = 0;
    public int c;
    private int f = 5;
    public float d = (float) (Math.random() * 3.141592653589793D * 2.0D);
    // CanaryMod Start
    ItemEntity item = new ItemEntity(this);

    // CanaryMod End

    public OEntityItem(OWorld var1, double var2, double var4, double var6, OItemStack var8) {
        super(var1);
        this.b(0.25F, 0.25F);
        this.bC = this.bE / 2.0F;
        this.c(var2, var4, var6);
        this.a = var8;
        this.bp = (float) (Math.random() * 360.0D);
        this.bm = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.bn = 0.20000000298023224D;
        this.bo = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
    }

    protected boolean g_() {
        return false;
    }

    public OEntityItem(OWorld var1) {
        super(var1);
        this.b(0.25F, 0.25F);
        this.bC = this.bE / 2.0F;
    }

    protected void b() {}

    public void w_() {
        super.w_();
        if (this.c > 0) {
            --this.c;
        }

        this.bg = this.bj;
        this.bh = this.bk;
        this.bi = this.bl;
        this.bn -= 0.03999999910593033D;
        if (this.bf.d(OMathHelper.b(this.bj), OMathHelper.b(this.bk), OMathHelper.b(this.bl)) == OMaterial.h) {
            this.bn = 0.20000000298023224D;
            this.bm = (double) ((this.bP.nextFloat() - this.bP.nextFloat()) * 0.2F);
            this.bo = (double) ((this.bP.nextFloat() - this.bP.nextFloat()) * 0.2F);
            this.bf.a(this, "random.fizz", 0.4F, 2.0F + this.bP.nextFloat() * 0.4F);
        }

        this.g(this.bj, (this.bt.b + this.bt.e) / 2.0D, this.bl);
        this.a(this.bm, this.bn, this.bo);
        float var1 = 0.98F;

        if (this.bu) {
            var1 = 0.58800006F;
            int var2 = this.bf.a(OMathHelper.b(this.bj), OMathHelper.b(this.bt.b) - 1, OMathHelper.b(this.bl));

            if (var2 > 0) {
                var1 = OBlock.m[var2].cc * 0.98F;
            }
        }

        this.bm *= (double) var1;
        this.bn *= 0.9800000190734863D;
        this.bo *= (double) var1;
        if (this.bu) {
            this.bn *= -0.5D;
        }

        ++this.e;
        ++this.b;
        if (this.b >= 6000) {
            // CanaryMod onEntityDespawn
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENTITY_DESPAWN, item)) {
                this.S();
            } else {
                this.b = 0;
            }
        }

    }

    public boolean i_() {
        return this.bf.a(this.bt, OMaterial.g, this);
    }

    protected void a(int var1) {
        this.a(ODamageSource.a, var1);
    }

    public boolean a(ODamageSource var1, int var2) {
        this.aB();
        this.f -= var2;
        if (this.f <= 0) {
            this.S();
        }

        return false;
    }

    public void b(ONBTTagCompound var1) {
        var1.a("Health", (short) ((byte) this.f));
        var1.a("Age", (short) this.b);
        var1.a("Item", this.a.b(new ONBTTagCompound()));
    }

    public void a(ONBTTagCompound var1) {
        this.f = var1.e("Health") & 255;
        this.b = var1.e("Age");
        ONBTTagCompound var2 = var1.l("Item");

        this.a = OItemStack.a(var2);
        if (this.a == null) {
            this.S();
        }

    }

    public void a_(OEntityPlayer var1) {
        if (!this.bf.I) {
            int var2 = this.a.a;

            // CanaryMod: First simulate the pickup and call the hooks
            if (this.c == 0 && var1.k.canPickup(this)) {
                if (var1.k.a(this.a)) {
                    if (this.a.c == OBlock.L.bO) {
                        var1.a((OStatBase) OAchievementList.g);
                    }
	
                    if (this.a.c == OItem.aE.bM) {
                        var1.a((OStatBase) OAchievementList.t);
                    }
	
                    if (this.a.c == OItem.m.bM) {
                        var1.a((OStatBase) OAchievementList.w);
                    }
	
                    if (this.a.c == OItem.bn.bM) {
                        var1.a((OStatBase) OAchievementList.z);
                    }

                    this.bf.a(this, "random.pop", 0.2F, ((this.bP.nextFloat() - this.bP.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    var1.a((OEntity) this, var2);
                    if (this.a.a <= 0) {
                        this.S();
                    }
                }
            }
        }
    }

    public String ad() {
        return OStatCollector.a("item." + this.a.k());
    }
}
