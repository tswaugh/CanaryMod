import java.util.Iterator;
import java.util.List;


public abstract class OEntityPlayer extends OEntityLiving {

    public OInventoryPlayer k = new OInventoryPlayer(this);
    public OContainer l;
    public OContainer m;
    protected OFoodStats n = new OFoodStats(this);
    protected int o = 0;
    public byte p = 0;
    public int q = 0;
    public float r;
    public float s;
    public boolean t = false;
    public int u = 0;
    public String v;
    public int w;
    public int x = 0;
    public double y;
    public double z;
    public double A;
    public double B;
    public double C;
    public double D;
    protected boolean E;
    public OChunkCoordinates F;
    private int a;
    public float G;
    public float H;
    private OChunkCoordinates b;
    private OChunkCoordinates c;
    public int I = 20;
    protected boolean J = false;
    public float K;
    public OPlayerCapabilities L = new OPlayerCapabilities();
    public int M;
    public int N;
    public float O;
    private OItemStack d;
    private int e;
    protected float P = 0.1F;
    protected float Q = 0.02F;
    public OEntityFishHook R = null;
    // CanaryMod start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    HumanEntity entity = new HumanEntity(this);

    // CanaryMod end

    public OEntityPlayer(OWorld oworld) {
        super(oworld);
        this.l = new OContainerPlayer(this.k, !oworld.F);
        this.m = this.l;
        this.bF = 1.62F;
        OChunkCoordinates ochunkcoordinates = oworld.p();

        this.c((double) ochunkcoordinates.a + 0.5D, (double) (ochunkcoordinates.b + 1), (double) ochunkcoordinates.c + 0.5D, 0.0F, 0.0F);
        this.ah = "humanoid";
        this.ag = 180.0F;
        this.bU = 20;
        this.ae = "/mob/char.png";
    }

    public int d() {
        return 20;
    }

    protected void b() {
        super.b();
        this.bY.a(16, Byte.valueOf((byte) 0));
        this.bY.a(17, Byte.valueOf((byte) 0));
    }

    public boolean M() {
        return this.d != null;
    }

    public void N() {
        if (this.d != null) {
            this.d.b(this.bi, this, this.e);
        }

        this.O();
    }

    public void O() {
        this.d = null;
        this.e = 0;
        if (!this.bi.F) {
            this.i(false);
        }

    }

    public boolean P() {
        return this.M() && OItem.d[this.d.c].d(this.d) == OEnumAction.d;
    }

    public void F_() {
        if (this.d != null) {
            OItemStack oitemstack = this.k.d();

            if (oitemstack != this.d) {
                this.O();
            } else {
                if (this.e <= 25 && this.e % 4 == 0) {
                    this.b(oitemstack, 5);
                }

                if (--this.e == 0 && !this.bi.F) {
                    this.K();
                }
            }
        }

        if (this.x > 0) {
            --this.x;
        }

        if (this.Z()) {
            ++this.a;
            if (this.a > 100) {
                this.a = 100;
            }

            if (!this.bi.F) {
                if (!this.G()) {
                    this.a(true, true, false);
                } else if (this.bi.e()) {
                    this.a(false, true, true);
                }
            }
        } else if (this.a > 0) {
            ++this.a;
            if (this.a >= 110) {
                this.a = 0;
            }
        }

        super.F_();
        if (!this.bi.F && this.m != null && !this.m.b(this)) {
            this.F();
            this.m = this.l;
        }

        if (this.L.b) {
            for (int i = 0; i < 8; ++i) {
                ;
            }
        }

        if (this.B_() && this.L.a) {
            this.aR();
        }

        this.y = this.B;
        this.z = this.C;
        this.A = this.D;
        double d0 = this.bm - this.B;
        double d1 = this.bn - this.C;
        double d2 = this.bo - this.D;
        double d3 = 10.0D;

        if (d0 > d3) {
            this.y = this.B = this.bm;
        }

        if (d2 > d3) {
            this.A = this.D = this.bo;
        }

        if (d1 > d3) {
            this.z = this.C = this.bn;
        }

        if (d0 < -d3) {
            this.y = this.B = this.bm;
        }

        if (d2 < -d3) {
            this.A = this.D = this.bo;
        }

        if (d1 < -d3) {
            this.z = this.C = this.bn;
        }

        this.B += d0 * 0.25D;
        this.D += d2 * 0.25D;
        this.C += d1 * 0.25D;
        this.a(OStatList.k, 1);
        if (this.bh == null) {
            this.c = null;
        }

        if (!this.bi.F) {
            this.n.a(this);
        }

    }

    protected void b(OItemStack oitemstack, int i) {
        if (oitemstack.m() == OEnumAction.c) {
            this.bi.a(this, "random.drink", 0.5F, this.bi.r.nextFloat() * 0.1F + 0.9F);
        }

        if (oitemstack.m() == OEnumAction.b) {
            for (int j = 0; j < i; ++j) {
                OVec3D ovec3d = OVec3D.b(((double) this.bS.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

                ovec3d.a(-this.bt * 3.1415927F / 180.0F);
                ovec3d.b(-this.bs * 3.1415927F / 180.0F);
                OVec3D ovec3d1 = OVec3D.b(((double) this.bS.nextFloat() - 0.5D) * 0.3D, (double) (-this.bS.nextFloat()) * 0.6D - 0.3D, 0.6D);

                ovec3d1.a(-this.bt * 3.1415927F / 180.0F);
                ovec3d1.b(-this.bs * 3.1415927F / 180.0F);
                ovec3d1 = ovec3d1.c(this.bm, this.bn + (double) this.B(), this.bo);
                this.bi.a("iconcrack_" + oitemstack.a().bP, ovec3d1.a, ovec3d1.b, ovec3d1.c, ovec3d.a, ovec3d.b + 0.05D, ovec3d.c);
            }

            this.bi.a(this, "random.eat", 0.5F + 0.5F * (float) this.bS.nextInt(2), (this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F);
        }

    }

    protected void K() {
        if (this.d != null) {
            this.b(this.d, 16);
            int i = this.d.a;
            OItemStack oitemstack = this.d.b(this.bi, this);

            if (oitemstack != this.d || oitemstack != null && oitemstack.a != i) {
                this.k.a[this.k.c] = oitemstack;
                if (oitemstack.a == 0) {
                    this.k.a[this.k.c] = null;
                }
            }

            this.O();
        }

    }

    protected boolean Q() {
        return this.aD() <= 0 || this.Z();
    }

    protected void F() {
        this.m = this.l;
    }

    public void R() {
        double d0 = this.bm;
        double d1 = this.bn;
        double d2 = this.bo;

        super.R();
        this.r = this.s;
        this.s = 0.0F;
        this.h(this.bm - d0, this.bn - d1, this.bo - d2);
    }

    private int E() {
        return this.a(OPotion.e) ? 6 - (1 + this.b(OPotion.e).c()) * 1 : (this.a(OPotion.f) ? 6 + (1 + this.b(OPotion.f).c()) * 2 : 6);
    }

    protected void d_() {
        int i = this.E();

        if (this.t) {
            ++this.u;
            if (this.u >= i) {
                this.u = 0;
                this.t = false;
            }
        } else {
            this.u = 0;
        }

        this.ao = (float) this.u / (float) i;
    }

    public void e() {
        if (this.o > 0) {
            --this.o;
        }
        
        // CanaryMod: adjust 'healing over time' independent of monster-spawn=true/false (nice notchup!)
        PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();

        if (this.bi.q == 0 && autoHeal == PluginLoader.HookResult.DEFAULT_ACTION || autoHeal == PluginLoader.HookResult.ALLOW_ACTION) {
            if (this.bi.q == 0 && this.aD() < this.d() && this.bT % 20 * 12 == 0) {
                this.d(1);
            }
        }

        this.k.i();
        this.r = this.s;
        super.e();
        this.al = this.P;
        this.am = this.Q;
        if (this.aZ()) {
            this.al = (float) ((double) this.al + (double) this.P * 0.3D);
            this.am = (float) ((double) this.am + (double) this.Q * 0.3D);
        }

        float f = OMathHelper.a(this.bp * this.bp + this.br * this.br);
        float f1 = (float) Math.atan(-this.bq * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.bx || this.aD() <= 0) {
            f = 0.0F;
        }

        if (this.bx || this.aD() <= 0) {
            f1 = 0.0F;
        }

        this.s += (f - this.s) * 0.4F;
        this.ay += (f1 - this.ay) * 0.8F;
        if (this.aD() > 0) {
            List list = this.bi.b((OEntity) this, this.bw.b(1.0D, 0.0D, 1.0D));

            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    OEntity oentity = (OEntity) list.get(i);

                    if (!oentity.bE) {
                        this.l(oentity);
                    }
                }
            }
        }

    }

    private void l(OEntity oentity) {
        oentity.a_(this);
    }

    public void a(ODamageSource odamagesource) {
        super.a(odamagesource);
        this.b(0.2F, 0.2F);
        this.c(this.bm, this.bn, this.bo);
        this.bq = 0.10000000149011612D;
        if (this.v.equals("Notch")) {
            this.a(new OItemStack(OItem.i, 1), true);
        }

        this.k.k();
        if (odamagesource != null) {
            this.bp = (double) (-OMathHelper.b((this.au + this.bs) * 3.1415927F / 180.0F) * 0.1F);
            this.br = (double) (-OMathHelper.a((this.au + this.bs) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.bp = this.br = 0.0D;
        }

        this.bF = 0.1F;
        this.a(OStatList.y, 1);
    }

    public void b(OEntity oentity, int i) {
        this.q += i;
        if (oentity instanceof OEntityPlayer) {
            this.a(OStatList.A, 1);
        } else {
            this.a(OStatList.z, 1);
        }

    }

    protected int b_(int i) {
        int j = OEnchantmentHelper.a(this.k);

        return j > 0 && this.bS.nextInt(j + 1) > 0 ? i : super.b_(i);
    }

    public OEntityItem S() {
        return this.a(this.k.a(this.k.c, 1), false);
    }

    public OEntityItem b(OItemStack oitemstack) {
        return this.a(oitemstack, false);
    }

    public OEntityItem a(OItemStack oitemstack, boolean flag) {
        if (oitemstack == null) {
            return null;
        } else {
            OEntityItem oentityitem = new OEntityItem(this.bi, this.bm, this.bn - 0.30000001192092896D + (double) this.B(), this.bo, oitemstack);

            oentityitem.c = 40;
            float f = 0.1F;
            float f1;

            if (flag) {
                f1 = this.bS.nextFloat() * 0.5F;
                float f2 = this.bS.nextFloat() * 3.1415927F * 2.0F;

                oentityitem.bp = (double) (-OMathHelper.a(f2) * f1);
                oentityitem.br = (double) (OMathHelper.b(f2) * f1);
                oentityitem.bq = 0.20000000298023224D;
            } else {
                f = 0.3F;
                oentityitem.bp = (double) (-OMathHelper.a(this.bs / 180.0F * 3.1415927F) * OMathHelper.b(this.bt / 180.0F * 3.1415927F) * f);
                oentityitem.br = (double) (OMathHelper.b(this.bs / 180.0F * 3.1415927F) * OMathHelper.b(this.bt / 180.0F * 3.1415927F) * f);
                oentityitem.bq = (double) (-OMathHelper.a(this.bt / 180.0F * 3.1415927F) * f + 0.1F);
                f = 0.02F;
                f1 = this.bS.nextFloat() * 3.1415927F * 2.0F;
                f *= this.bS.nextFloat();
                oentityitem.bp += Math.cos((double) f1) * (double) f;
                oentityitem.bq += (double) ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.1F);
                oentityitem.br += Math.sin((double) f1) * (double) f;
            }

            if (!(Boolean) manager.callHook(PluginLoader.Hook.ITEM_DROP, ((OEntityPlayerMP) this).getPlayer(), oentityitem.item)) {
                Item droppedItem = oentityitem.item.getItem();

                if (droppedItem.getAmount() < 0) {
                    droppedItem.setAmount(1);
                    droppedItem.update();
                }
                this.a(oentityitem);
                this.a(OStatList.v, 1);
                // return the item to the inventory.
            } else {
                return oentityitem;
            }
            return null;
        }
    }

    protected void a(OEntityItem oentityitem) {
        this.bi.b((OEntity) oentityitem);
    }

    public float a(OBlock oblock) {
        float f = this.k.a(oblock);
        float f1 = f;
        int i = OEnchantmentHelper.b(this.k);

        if (i > 0 && this.k.b(oblock)) {
            f1 = f + (float) (i * i + 1);
        }

        if (this.a(OPotion.e)) {
            f1 *= 1.0F + (float) (this.b(OPotion.e).c() + 1) * 0.2F;
        }

        if (this.a(OPotion.f)) {
            f1 *= 1.0F - (float) (this.b(OPotion.f).c() + 1) * 0.2F;
        }

        if (this.a(OMaterial.g) && !OEnchantmentHelper.g(this.k)) {
            f1 /= 5.0F;
        }

        if (!this.bx) {
            f1 /= 5.0F;
        }

        return f1;
    }

    public boolean b(OBlock oblock) {
        return this.k.b(oblock);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.n("Inventory");

        this.k.b(onbttaglist);
        this.w = onbttagcompound.f("Dimension");
        this.E = onbttagcompound.o("Sleeping");
        this.a = onbttagcompound.e("SleepTimer");
        this.O = onbttagcompound.h("XpP");
        this.M = onbttagcompound.f("XpLevel");
        this.N = onbttagcompound.f("XpTotal");
        if (this.E) {
            this.F = new OChunkCoordinates(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo));
            this.a(true, true, false);
        }

        if (onbttagcompound.c("SpawnX") && onbttagcompound.c("SpawnY") && onbttagcompound.c("SpawnZ")) {
            this.b = new OChunkCoordinates(onbttagcompound.f("SpawnX"), onbttagcompound.f("SpawnY"), onbttagcompound.f("SpawnZ"));
        }

        this.n.a(onbttagcompound);
        this.L.b(onbttagcompound);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Inventory", (ONBTBase) this.k.a(new ONBTTagList()));
        onbttagcompound.a("Dimension", this.w);
        onbttagcompound.a("Sleeping", this.E);
        onbttagcompound.a("SleepTimer", (short) this.a);
        onbttagcompound.a("XpP", this.O);
        onbttagcompound.a("XpLevel", this.M);
        onbttagcompound.a("XpTotal", this.N);
        if (this.b != null) {
            onbttagcompound.a("SpawnX", this.b.a);
            onbttagcompound.a("SpawnY", this.b.b);
            onbttagcompound.a("SpawnZ", this.b.c);
        }

        this.n.b(onbttagcompound);
        this.L.a(onbttagcompound);
    }

    public void a(OIInventory oiinventory) {}

    public void c(int i, int j, int k) {}

    public void b(int i, int j, int k) {}

    public void a(OEntity oentity, int i) {}

    public float B() {
        return 0.12F;
    }

    protected void A() {
        this.bF = 1.62F;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.L.a && !odamagesource.g()) {
            return false;
        } else {
            this.aV = 0;
            if (this.aD() <= 0) {
                return false;
            } else {
                if (this.Z() && !this.bi.F) {
                    this.a(true, true, false);
                }

                OEntity oentity = odamagesource.a();

                if (oentity instanceof OEntityMob || oentity instanceof OEntityArrow) {
                    if (this.bi.q == 0) {
                        i = 0;
                    }

                    if (this.bi.q == 1) {
                        i = i / 2 + 1;
                    }

                    if (this.bi.q == 3) {
                        i = i * 3 / 2;
                    }
                }

                if (i == 0) {
                    return false;
                } else {
                    OEntity oentity1 = oentity;

                    if (oentity instanceof OEntityArrow && ((OEntityArrow) oentity).c != null) {
                        oentity1 = ((OEntityArrow) oentity).c;
                    }

                    if (oentity1 instanceof OEntityLiving) {
                        this.a((OEntityLiving) oentity1, false);
                    }

                    this.a(OStatList.x, i);
                    return super.a(odamagesource, i);
                }
            }
        }
    }

    protected int b(ODamageSource odamagesource, int i) {
        int j = super.b(odamagesource, i);

        if (j <= 0) {
            return 0;
        } else {
            int k = OEnchantmentHelper.a(this.k, odamagesource);

            if (k > 20) {
                k = 20;
            }

            if (k > 0 && k <= 20) {
                int l = 25 - k;
                int i1 = j * l + this.ar;

                j = i1 / 25;
                this.ar = i1 % 25;
            }

            return j;
        }
    }

    protected boolean C() {
        return false;
    }

    protected void a(OEntityLiving oentityliving, boolean flag) {
        if (!(oentityliving instanceof OEntityCreeper) && !(oentityliving instanceof OEntityGhast)) {
            if (oentityliving instanceof OEntityWolf) {
                OEntityWolf oentitywolf = (OEntityWolf) oentityliving;

                if (oentitywolf.u_() && this.v.equals(oentitywolf.A())) {
                    return;
                }
            }

            if (!(oentityliving instanceof OEntityPlayer) || this.C()) {
                List list = this.bi.a(OEntityWolf.class, OAxisAlignedBB.b(this.bm, this.bn, this.bo, this.bm + 1.0D, this.bn + 1.0D, this.bo + 1.0D).b(16.0D, 4.0D, 16.0D));
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OEntity oentity = (OEntity) iterator.next();
                    OEntityWolf oentitywolf1 = (OEntityWolf) oentity;

                    if (oentitywolf1.u_() && oentitywolf1.I() == null && this.v.equals(oentitywolf1.A()) && (!flag || !oentitywolf1.v_())) {
                        oentitywolf1.c(false);
                        oentitywolf1.d(oentityliving);
                    }
                }

            }
        }
    }

    protected void f(int i) {
        this.k.e(i);
    }

    public int T() {
        return this.k.j();
    }

    protected void c(ODamageSource odamagesource, int i) {
        if (!odamagesource.e() && this.P()) {
            i = 1 + i >> 1;
        }

        i = this.d(odamagesource, i);
        i = this.b(odamagesource, i);
        this.c(odamagesource.f());
        this.ap -= i;
    }

    public void a(OTileEntityFurnace otileentityfurnace) {}

    public void a(OTileEntityDispenser otileentitydispenser) {}

    public void a(OTileEntitySign otileentitysign) {}

    public void a(OTileEntityBrewingStand otileentitybrewingstand) {}

    public void e(OEntity oentity) {
        if (!oentity.b(this)) {
            OItemStack oitemstack = this.U();
            PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.ENTITY_RIGHTCLICKED, ((OEntityPlayerMP) this).getPlayer(), oentity.entity, (oitemstack == null) ? null : new Item(oitemstack));

            if (res != PluginLoader.HookResult.PREVENT_ACTION) {
                // Normally when interact action is not defined on the interacted entity, false is returned, and the item stack is not used.
                // For example sheep can interact by shearing and cows by milking, and the item stack changes from this interaction if it returns true.
                // Players on the other hand won't interact normally, but if we want to update the item stack anyways, we will ALLOW the action.
                if (!oentity.b(this) || res == PluginLoader.HookResult.ALLOW_ACTION) {

                    if (oitemstack != null && oentity instanceof OEntityLiving) {
                        oitemstack.a((OEntityLiving) oentity);
                        if (oitemstack.a <= 0) {
                            oitemstack.a(this);
                            this.V();
                        }
                    }
                }
            }
        }
    }

    public OItemStack U() {
        return this.k.d();
    }

    public void V() {
        this.k.a(this.k.c, (OItemStack) null);
    }

    public double W() {
        return (double) (this.bF - 0.5F);
    }

    public void C_() {
        if (!this.t || this.u >= this.E() / 2 || this.u < 0) {
            this.u = -1;
            this.t = true;
        }

    }

    public void f(OEntity oentity) {
        if (oentity.k_()) {
            int i = this.k.a(oentity);

            if (this.a(OPotion.g)) {
                i += 3 << this.b(OPotion.g).c();
            }

            if (this.a(OPotion.t)) {
                i -= 2 << this.b(OPotion.t).c();
            }

            int j = 0;
            int k = 0;

            if (oentity instanceof OEntityLiving) {
                k = OEnchantmentHelper.a(this.k, (OEntityLiving) oentity);
                j += OEnchantmentHelper.b(this.k, (OEntityLiving) oentity);
            }

            if (this.aZ()) {
                ++j;
            }

            if (i > 0 || k > 0) {
                boolean flag = this.bK > 0.0F && !this.bx && !this.t() && !this.aU() && !this.a(OPotion.q) && this.bh == null && oentity instanceof OEntityLiving;

                if (flag) {
                    i += this.bS.nextInt(i / 2 + 2);
                }

                i += k;
                boolean flag1 = oentity.a(ODamageSource.b(this), i);

                if (flag1) {
                    if (j > 0) {
                        oentity.b_((double) (-OMathHelper.a(this.bs * 3.1415927F / 180.0F) * (float) j * 0.5F), 0.1D, (double) (OMathHelper.b(this.bs * 3.1415927F / 180.0F) * (float) j * 0.5F));
                        this.bp *= 0.6D;
                        this.br *= 0.6D;
                        this.h(false);
                    }

                    if (flag) {
                        this.c(oentity);
                    }

                    if (k > 0) {
                        this.d(oentity);
                    }

                    if (i >= 18) {
                        this.a((OStatBase) OAchievementList.E);
                    }

                    this.g(oentity);
                }

                OItemStack oitemstack = this.U();

                if (oitemstack != null && oentity instanceof OEntityLiving) {
                    oitemstack.a((OEntityLiving) oentity, this);
                    if (oitemstack.a <= 0) {
                        oitemstack.a(this);
                        this.V();
                    }
                }

                if (oentity instanceof OEntityLiving) {
                    if (oentity.aE()) {
                        this.a((OEntityLiving) oentity, true);
                    }

                    this.a(OStatList.w, i);
                    int l = OEnchantmentHelper.c(this.k, (OEntityLiving) oentity);

                    if (l > 0) {
                        oentity.i(l * 4);
                    }
                }

                this.c(0.3F);
            }

        }
    }

    public void c(OEntity oentity) {}

    public void d(OEntity oentity) {}

    public void a(OItemStack oitemstack) {}

    public void X() {
        super.X();
        this.l.a(this);
        if (this.m != null) {
            this.m.a(this);
        }

    }

    public boolean Y() {
        return !this.E && super.Y();
    }

    public OEnumStatus a(int i, int j, int k) {
        if (!this.bi.F) {
            if (this.Z() || !this.aE()) {
                return OEnumStatus.e;
            }

            if (!this.bi.t.d()) {
                return OEnumStatus.b;
            }

            if (this.bi.e()) {
                return OEnumStatus.c;
            }

            if (Math.abs(this.bm - (double) i) > 3.0D || Math.abs(this.bn - (double) j) > 2.0D || Math.abs(this.bo - (double) k) > 3.0D) {
                return OEnumStatus.d;
            }

            double d0 = 8.0D;
            double d1 = 5.0D;
            List list = this.bi.a(OEntityMob.class, OAxisAlignedBB.b((double) i - d0, (double) j - d1, (double) k - d0, (double) i + d0, (double) j + d1, (double) k + d0));

            if (!list.isEmpty()) {
                return OEnumStatus.f;
            }
        }

        this.b(0.2F, 0.2F);
        this.bF = 0.2F;
        if (this.bi.i(i, j, k)) {
            int l = this.bi.c(i, j, k);
            int i1 = OBlockBed.b(l);
            float f = 0.5F;
            float f1 = 0.5F;

            switch (i1) {
                case 0:
                    f1 = 0.9F;
                    break;

                case 1:
                    f = 0.1F;
                    break;

                case 2:
                    f1 = 0.1F;
                    break;

                case 3:
                    f = 0.9F;
            }

            this.c(i1);
            this.c((double) ((float) i + f), (double) ((float) j + 0.9375F), (double) ((float) k + f1));
        } else {
            this.c((double) ((float) i + 0.5F), (double) ((float) j + 0.9375F), (double) ((float) k + 0.5F));
        }

        this.E = true;
        this.a = 0;
        this.F = new OChunkCoordinates(i, j, k);
        this.bp = this.br = this.bq = 0.0D;
        if (!this.bi.F) {
            this.bi.t();
        }

        return OEnumStatus.a;
    }

    private void c(int i) {
        this.G = 0.0F;
        this.H = 0.0F;
        switch (i) {
            case 0:
                this.H = -1.8F;
                break;

            case 1:
                this.G = 1.8F;
                break;

            case 2:
                this.H = 1.8F;
                break;

            case 3:
                this.G = -1.8F;
        }

    }

    public void a(boolean flag, boolean flag1, boolean flag2) {
        this.b(0.6F, 1.8F);
        this.A();
        OChunkCoordinates ochunkcoordinates = this.F;
        OChunkCoordinates ochunkcoordinates1 = this.F;

        if (ochunkcoordinates != null && this.bi.a(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) == OBlock.S.bO) {
            OBlockBed.a(this.bi, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, false);
            ochunkcoordinates1 = OBlockBed.f(this.bi, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, 0);
            if (ochunkcoordinates1 == null) {
                ochunkcoordinates1 = new OChunkCoordinates(ochunkcoordinates.a, ochunkcoordinates.b + 1, ochunkcoordinates.c);
            }

            this.c((double) ((float) ochunkcoordinates1.a + 0.5F), (double) ((float) ochunkcoordinates1.b + this.bF + 0.1F), (double) ((float) ochunkcoordinates1.c + 0.5F));
        }

        this.E = false;
        if (!this.bi.F && flag1) {
            this.bi.t();
        }

        if (flag) {
            this.a = 0;
        } else {
            this.a = 100;
        }

        if (flag2) {
            this.a(this.F);
        }

    }

    private boolean G() {
        return this.bi.a(this.F.a, this.F.b, this.F.c) == OBlock.S.bO;
    }

    public static OChunkCoordinates a(OWorld oworld, OChunkCoordinates ochunkcoordinates) {
        OIChunkProvider oichunkprovider = oworld.q();

        oichunkprovider.c(ochunkcoordinates.a - 3 >> 4, ochunkcoordinates.c - 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a + 3 >> 4, ochunkcoordinates.c - 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a - 3 >> 4, ochunkcoordinates.c + 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a + 3 >> 4, ochunkcoordinates.c + 3 >> 4);
        if (oworld.a(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) != OBlock.S.bO) {
            return null;
        } else {
            OChunkCoordinates ochunkcoordinates1 = OBlockBed.f(oworld, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, 0);

            return ochunkcoordinates1;
        }
    }

    public boolean Z() {
        return this.E;
    }

    public boolean aa() {
        return this.E && this.a >= 100;
    }

    public void a(String s) {}

    public OChunkCoordinates ab() {
        return this.b;
    }

    public void a(OChunkCoordinates ochunkcoordinates) {
        if (ochunkcoordinates != null) {
            this.b = new OChunkCoordinates(ochunkcoordinates);
        } else {
            this.b = null;
        }

    }

    public void a(OStatBase ostatbase) {
        this.a(ostatbase, 1);
    }

    public void a(OStatBase ostatbase, int i) {}

    protected void ac() {
        super.ac();
        this.a(OStatList.u, 1);
        if (this.aZ()) {
            this.c(0.8F);
        } else {
            this.c(0.2F);
        }

    }

    public void a(float f, float f1) {
        double d0 = this.bm;
        double d1 = this.bn;
        double d2 = this.bo;

        if (this.L.b) {
            double d3 = this.bq;
            float f2 = this.am;

            this.am = 0.05F;
            super.a(f, f1);
            this.bq = d3 * 0.6D;
            this.am = f2;
        } else {
            super.a(f, f1);
        }

        this.b(this.bm - d0, this.bn - d1, this.bo - d2);
    }

    public void b(double d0, double d1, double d2) {
        if (this.bh == null) {
            int i;

            if (this.a(OMaterial.g)) {
                i = Math.round(OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.q, i);
                    this.c(0.015F * (float) i * 0.01F);
                }
            } else if (this.aU()) {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.m, i);
                    this.c(0.015F * (float) i * 0.01F);
                }
            } else if (this.t()) {
                if (d1 > 0.0D) {
                    this.a(OStatList.o, (int) Math.round(d1 * 100.0D));
                }
            } else if (this.bx) {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.l, i);
                    if (this.aZ()) {
                        this.c(0.099999994F * (float) i * 0.01F);
                    } else {
                        this.c(0.01F * (float) i * 0.01F);
                    }
                }
            } else {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 25) {
                    this.a(OStatList.p, i);
                }
            }

        }
    }

    private void h(double d0, double d1, double d2) {
        if (this.bh != null) {
            int i = Math.round(OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);

            if (i > 0) {
                if (this.bh instanceof OEntityMinecart) {
                    this.a(OStatList.r, i);
                    if (this.c == null) {
                        this.c = new OChunkCoordinates(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo));
                    } else if (this.c.b(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo)) >= 1000.0D) {
                        this.a((OStatBase) OAchievementList.q, 1);
                    }
                } else if (this.bh instanceof OEntityBoat) {
                    this.a(OStatList.s, i);
                } else if (this.bh instanceof OEntityPig) {
                    this.a(OStatList.t, i);
                }
            }
        }

    }

    protected void a(float f) {
        if (!this.L.c) {
            if (f >= 2.0F) {
                this.a(OStatList.n, (int) Math.round((double) f * 100.0D));
            }

            super.a(f);
        }
    }

    public void c(OEntityLiving oentityliving) {
        if (oentityliving instanceof OEntityMob) {
            this.a((OStatBase) OAchievementList.s);
        }

    }

    public void ad() {
        if (this.I > 0) {
            this.I = 10;
        } else {
            this.J = true;
        }
    }

    public void g(int i) {
        addXP(i);
    }
    
    public void addXP(int i) {
        int j = Integer.MAX_VALUE - this.N;

        if (i > j) {
            i = j;
        }
        
        this.q += i;
        this.O += (float) i / (float) this.ae();
        this.N += i;
        levelUp();
    }

    public void removeXP(int i) {
        this.q -= i;
        this.O -= (float) i / (float) this.ae();
        this.N -= i;
        levelUp();
    }

    public void setXP(int i) {
        this.q = i;
        this.O = (float) i / (float) this.ae();
        this.N = i;
        levelUp();
    }
    
    public void levelUp() {
        for (; this.O >= 1.0F; this.O /= (float) this.ae()) {
            this.O = (this.O - 1.0F) * (float) this.ae();
            this.H();
            
            manager.callHook(PluginLoader.Hook.LEVEL_UP, ((OEntityPlayerMP) this).getPlayer());
        }
    }

    public void e_(int i) {
        this.M -= i;
        if (this.M < 0) {
            this.M = 0;
        }

    }

    public int ae() {
        return 7 + (this.M * 7 >> 1);
    }

    private void H() {
        ++this.M;
    }

    public void c(float f) {
        if (!this.L.a) {
            if (!this.bi.F) {
                this.n.a(f);
            }

        }
    }

    public OFoodStats af() {
        return this.n;
    }

    public boolean b(boolean flag) {
        return (flag || this.n.b()) && !this.L.a;
    }

    public boolean ag() {
        return this.aD() > 0 && this.aD() < this.d();
    }

    public void a(OItemStack oitemstack, int i) {
        if (oitemstack != this.d) {
            this.d = oitemstack;
            this.e = i;
            if (!this.bi.F) {
                this.i(true);
            }

        }
    }

    public boolean d(int i, int j, int k) {
        return true;
    }

    protected int a(OEntityPlayer oentityplayer) {
        int i = this.M * 7;

        return i > 100 ? 100 : i;
    }

    protected boolean ah() {
        return true;
    }

    public String s() {
        return this.v;
    }

    public void e(int i) {}

    public void c(OEntityPlayer oentityplayer) {
        this.k.a(oentityplayer.k);
        this.ap = oentityplayer.ap;
        this.n = oentityplayer.n;
        this.M = oentityplayer.M;
        this.N = oentityplayer.N;
        this.O = oentityplayer.O;
        this.q = oentityplayer.q;
    }

    protected boolean g_() {
        return !this.L.b;
    }

    public void L() {}
}
