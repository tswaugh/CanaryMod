import java.util.Iterator;
import java.util.List;

public abstract class OEntityPlayer extends OEntityLiving implements OICommandSender {

    public OInventoryPlayer by = new OInventoryPlayer(this);
    private OInventoryEnderChest a = new OInventoryEnderChest();
    public OContainer bz;
    public OContainer bA;
    protected OFoodStats bB = new OFoodStats(this);
    protected int bC = 0;
    public byte bD = 0;
    public int bE = 0;
    public float bF;
    public float bG;
    public boolean bH = false;
    public int bI = 0;
    public String bJ;
    public int bK;
    public int bL = 0;
    public double bM;
    public double bN;
    public double bO;
    public double bP;
    public double bQ;
    public double bR;
    protected boolean bS;
    public OChunkCoordinates bT;
    private int b;
    public float bU;
    public float bV;
    private OChunkCoordinates c;
    private OChunkCoordinates d;
    public int bW = 20;
    protected boolean bX = false;
    public float bY;
    public OPlayerCapabilities bZ = new OPlayerCapabilities();
    public int ca;
    public int cb;
    public float cc;
    private OItemStack e;
    private int f;
    protected float cd = 0.1F;
    protected float ce = 0.02F;
    public OEntityFishHook cf = null;

    // CanaryMod start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    HumanEntity entity = new HumanEntity(this);
    // CanaryMod end

    public OEntityPlayer(OWorld oworld) {
        super(oworld);
        this.bz = new OContainerPlayer(this.by, !oworld.K);
        this.bA = this.bz;
        this.M = 1.62F;
        OChunkCoordinates ochunkcoordinates = oworld.E();

        this.b((double) ochunkcoordinates.a + 0.5D, (double) (ochunkcoordinates.b + 1), (double) ochunkcoordinates.c + 0.5D, 0.0F, 0.0F);
        this.aC = "humanoid";
        this.aB = 180.0F;
        this.ab = 20;
        this.az = "/mob/char.png";
    }

    public int aM() {
        return 20;
    }

    protected void a() {
        super.a();
        this.af.a(16, Byte.valueOf((byte) 0));
        this.af.a(17, Byte.valueOf((byte) 0));
    }

    public boolean bw() {
        return this.e != null;
    }

    public void by() {
        if (this.e != null) {
            this.e.b(this.p, this, this.f);
        }

        this.bz();
    }

    public void bz() {
        this.e = null;
        this.f = 0;
        if (!this.p.K) {
            this.c(false);
        }

    }

    public boolean aY() {
        return this.bw() && OItem.e[this.e.c].b(this.e) == OEnumAction.d;
    }

    public void h_() {
        if (this.e != null) {
            OItemStack oitemstack = this.by.g();

            if (oitemstack == this.e) {
                if (this.f <= 25 && this.f % 4 == 0) {
                    this.c(oitemstack, 5);
                }

                if (--this.f == 0 && !this.p.K) {
                    this.o();
                }
            } else {
                this.bz();
            }
        }

        if (this.bL > 0) {
            --this.bL;
        }

        if (this.bn()) {
            ++this.b;
            if (this.b > 100) {
                this.b = 100;
            }

            if (!this.p.K) {
                if (!this.l()) {
                    this.a(true, true, false);
                } else if (this.p.s()) {
                    this.a(false, true, true);
                }
            }
        } else if (this.b > 0) {
            ++this.b;
            if (this.b >= 110) {
                this.b = 0;
            }
        }

        super.h_();
        if (!this.p.K && this.bA != null && !this.bA.c(this)) {
            this.j();
            this.bA = this.bz;
        }

        if (this.ad() && this.bZ.a) {
            this.B();
        }

        this.bM = this.bP;
        this.bN = this.bQ;
        this.bO = this.bR;
        double d0 = this.t - this.bP;
        double d1 = this.u - this.bQ;
        double d2 = this.v - this.bR;
        double d3 = 10.0D;

        if (d0 > d3) {
            this.bM = this.bP = this.t;
        }

        if (d2 > d3) {
            this.bO = this.bR = this.v;
        }

        if (d1 > d3) {
            this.bN = this.bQ = this.u;
        }

        if (d0 < -d3) {
            this.bM = this.bP = this.t;
        }

        if (d2 < -d3) {
            this.bO = this.bR = this.v;
        }

        if (d1 < -d3) {
            this.bN = this.bQ = this.u;
        }

        this.bP += d0 * 0.25D;
        this.bR += d2 * 0.25D;
        this.bQ += d1 * 0.25D;
        this.a(OStatList.k, 1);
        if (this.o == null) {
            this.d = null;
        }

        if (!this.p.K) {
            this.bB.a(this);
        }

    }

    protected void c(OItemStack oitemstack, int i) {
        if (oitemstack.n() == OEnumAction.c) {
            this.p.a(this, "random.drink", 0.5F, this.p.v.nextFloat() * 0.1F + 0.9F);
        }

        if (oitemstack.n() == OEnumAction.b) {
            for (int j = 0; j < i; ++j) {
                OVec3 ovec3 = OVec3.a().a(((double) this.Z.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

                ovec3.a(-this.A * 3.1415927F / 180.0F);
                ovec3.b(-this.z * 3.1415927F / 180.0F);
                OVec3 ovec31 = OVec3.a().a(((double) this.Z.nextFloat() - 0.5D) * 0.3D, (double) (-this.Z.nextFloat()) * 0.6D - 0.3D, 0.6D);

                ovec31.a(-this.A * 3.1415927F / 180.0F);
                ovec31.b(-this.z * 3.1415927F / 180.0F);
                ovec31 = ovec31.c(this.t, this.u + (double) this.e(), this.v);
                this.p.a("iconcrack_" + oitemstack.b().bT, ovec31.a, ovec31.b, ovec31.c, ovec3.a, ovec3.b + 0.05D, ovec3.c);
            }

            this.p.a(this, "random.eat", 0.5F + 0.5F * (float) this.Z.nextInt(2), (this.Z.nextFloat() - this.Z.nextFloat()) * 0.2F + 1.0F);
        }

    }

    protected void o() {
        if (this.e != null) {
            this.c(this.e, 16);
            int i = this.e.a;
            OItemStack oitemstack = this.e.b(this.p, this);

            if (oitemstack != this.e || oitemstack != null && oitemstack.a != i) {
                this.by.a[this.by.c] = oitemstack;
                if (oitemstack.a == 0) {
                    this.by.a[this.by.c] = null;
                }
            }

            this.bz();
        }

    }

    protected boolean aX() {
        return this.aN() <= 0 || this.bn();
    }

    protected void j() {
        this.bA = this.bz;
    }

    public void U() {
        double d0 = this.t;
        double d1 = this.u;
        double d2 = this.v;

        super.U();
        this.bF = this.bG;
        this.bG = 0.0F;
        this.k(this.t - d0, this.u - d1, this.v - d2);
    }

    private int k() {
        return this.a(OPotion.e) ? 6 - (1 + this.b(OPotion.e).c()) * 1 : (this.a(OPotion.f) ? 6 + (1 + this.b(OPotion.f).c()) * 2 : 6);
    }

    protected void be() {
        int i = this.k();

        if (this.bH) {
            ++this.bI;
            if (this.bI >= i) {
                this.bI = 0;
                this.bH = false;
            }
        } else {
            this.bI = 0;
        }

        this.aJ = (float) this.bI / (float) i;
    }

    public void d() {
        if (this.bC > 0) {
            --this.bC;
        }

        // CanaryMod: adjust 'healing over time' independent of monster-spawn=true/false (nice notchup!)
        PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();

        if (this.p.u == 0 && autoHeal == PluginLoader.HookResult.DEFAULT_ACTION || autoHeal == PluginLoader.HookResult.ALLOW_ACTION) {
            if (this.aN() < this.aM() && this.aa % 20 * 12 == 0) {
                this.i(1);
            }
        }

        this.by.k();
        this.bF = this.bG;
        super.d();
        this.aG = this.bZ.b();
        this.aH = this.ce;
        if (this.ag()) {
            this.aG = (float) ((double) this.aG + (double) this.bZ.b() * 0.3D);
            this.aH = (float) ((double) this.aH + (double) this.ce * 0.3D);
        }

        float f = OMathHelper.a(this.w * this.w + this.y * this.y);
        float f1 = (float) Math.atan(-this.x * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.E || this.aN() <= 0) {
            f = 0.0F;
        }

        if (this.E || this.aN() <= 0) {
            f1 = 0.0F;
        }

        this.bG += (f - this.bG) * 0.4F;
        this.aT += (f1 - this.aT) * 0.8F;
        if (this.aN() > 0) {
            List list = this.p.b((OEntity) this, this.D.b(1.0D, 0.0D, 1.0D));

            if (list != null) {
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OEntity oentity = (OEntity) iterator.next();

                    if (!oentity.L) {
                        this.o(oentity);
                    }
                }
            }
        }

    }

    private void o(OEntity oentity) {
        oentity.b_(this);
    }

    public void a(ODamageSource odamagesource) {
        super.a(odamagesource);
        this.a(0.2F, 0.2F);
        this.b(this.t, this.u, this.v);
        this.x = 0.10000000149011612D;
        if (this.bJ.equals("Notch")) {
            this.a(new OItemStack(OItem.j, 1), true);
        }

        this.by.m();
        if (odamagesource != null) {
            this.w = (double) (-OMathHelper.b((this.aP + this.z) * 3.1415927F / 180.0F) * 0.1F);
            this.y = (double) (-OMathHelper.a((this.aP + this.z) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.w = this.y = 0.0D;
        }

        this.M = 0.1F;
        this.a(OStatList.y, 1);
    }

    public void c(OEntity oentity, int i) {
        this.bE += i;
        if (oentity instanceof OEntityPlayer) {
            this.a(OStatList.A, 1);
        } else {
            this.a(OStatList.z, 1);
        }

    }

    protected int h(int i) {
        int j = OEnchantmentHelper.a(this.by);

        return j > 0 && this.Z.nextInt(j + 1) > 0 ? i : super.h(i);
    }

    public OEntityItem bB() {
        return this.a(this.by.a(this.by.c, 1), false);
    }

    public OEntityItem b(OItemStack oitemstack) {
        return this.a(oitemstack, false);
    }

    public OEntityItem a(OItemStack oitemstack, boolean flag) {
        if (oitemstack == null) {
            return null;
        } else {
            OEntityItem oentityitem = new OEntityItem(this.p, this.t, this.u - 0.30000001192092896D + (double) this.e(), this.v, oitemstack);

            oentityitem.c = 40;
            float f = 0.1F;
            float f1;

            if (flag) {
                f1 = this.Z.nextFloat() * 0.5F;
                float f2 = this.Z.nextFloat() * 3.1415927F * 2.0F;

                oentityitem.w = (double) (-OMathHelper.a(f2) * f1);
                oentityitem.y = (double) (OMathHelper.b(f2) * f1);
                oentityitem.x = 0.20000000298023224D;
            } else {
                f = 0.3F;
                oentityitem.w = (double) (-OMathHelper.a(this.z / 180.0F * 3.1415927F) * OMathHelper.b(this.A / 180.0F * 3.1415927F) * f);
                oentityitem.y = (double) (OMathHelper.b(this.z / 180.0F * 3.1415927F) * OMathHelper.b(this.A / 180.0F * 3.1415927F) * f);
                oentityitem.x = (double) (-OMathHelper.a(this.A / 180.0F * 3.1415927F) * f + 0.1F);
                f = 0.02F;
                f1 = this.Z.nextFloat() * 3.1415927F * 2.0F;
                f *= this.Z.nextFloat();
                oentityitem.w += Math.cos((double) f1) * (double) f;
                oentityitem.x += (double) ((this.Z.nextFloat() - this.Z.nextFloat()) * 0.1F);
                oentityitem.y += Math.sin((double) f1) * (double) f;
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
        this.p.d((OEntity) oentityitem);
    }

    public float a(OBlock oblock) {
        float f = this.by.a(oblock);
        int i = OEnchantmentHelper.b(this.by);

        if (i > 0 && this.by.b(oblock)) {
            f += (float) (i * i + 1);
        }

        if (this.a(OPotion.e)) {
            f *= 1.0F + (float) (this.b(OPotion.e).c() + 1) * 0.2F;
        }

        if (this.a(OPotion.f)) {
            f *= 1.0F - (float) (this.b(OPotion.f).c() + 1) * 0.2F;
        }

        if (this.a(OMaterial.g) && !OEnchantmentHelper.g(this.by)) {
            f /= 5.0F;
        }

        if (!this.E) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(OBlock oblock) {
        return this.by.b(oblock);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Inventory");

        this.by.b(onbttaglist);
        this.bK = onbttagcompound.e("Dimension");
        this.bS = onbttagcompound.n("Sleeping");
        this.b = onbttagcompound.d("SleepTimer");
        this.cc = onbttagcompound.g("XpP");
        this.ca = onbttagcompound.e("XpLevel");
        this.cb = onbttagcompound.e("XpTotal");
        if (this.bS) {
            this.bT = new OChunkCoordinates(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
            this.a(true, true, false);
        }

        if (onbttagcompound.b("SpawnX") && onbttagcompound.b("SpawnY") && onbttagcompound.b("SpawnZ")) {
            this.c = new OChunkCoordinates(onbttagcompound.e("SpawnX"), onbttagcompound.e("SpawnY"), onbttagcompound.e("SpawnZ"));
        }

        this.bB.a(onbttagcompound);
        this.bZ.b(onbttagcompound);
        if (onbttagcompound.b("EnderItems")) {
            ONBTTagList onbttaglist1 = onbttagcompound.m("EnderItems");

            this.a.a(onbttaglist1);
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Inventory", (ONBTBase) this.by.a(new ONBTTagList()));
        onbttagcompound.a("Dimension", this.bK);
        onbttagcompound.a("Sleeping", this.bS);
        onbttagcompound.a("SleepTimer", (short) this.b);
        onbttagcompound.a("XpP", this.cc);
        onbttagcompound.a("XpLevel", this.ca);
        onbttagcompound.a("XpTotal", this.cb);
        if (this.c != null) {
            onbttagcompound.a("SpawnX", this.c.a);
            onbttagcompound.a("SpawnY", this.c.b);
            onbttagcompound.a("SpawnZ", this.c.c);
        }

        this.bB.b(onbttagcompound);
        this.bZ.a(onbttagcompound);
        onbttagcompound.a("EnderItems", (ONBTBase) this.a.g());
    }

    public void a(OIInventory oiinventory) {}

    public void c(int i, int j, int k) {}

    public void b(int i, int j, int k) {}

    public void a(OEntity oentity, int i) {}

    public float e() {
        return 0.12F;
    }

    protected void d_() {
        this.M = 1.62F;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.bZ.a && !odamagesource.e()) {
            return false;
        } else {
            this.bq = 0;
            if (this.aN() <= 0) {
                return false;
            } else {
                if (this.bn() && !this.p.K) {
                    this.a(true, true, false);
                }

                OEntity oentity = odamagesource.g();

                if (odamagesource.n()) {
                    if (this.p.u == 0) {
                        i = 0;
                    }

                    if (this.p.u == 1) {
                        i = i / 2 + 1;
                    }

                    if (this.p.u == 3) {
                        i = i * 3 / 2;
                    }
                }

                if (i == 0) {
                    return false;
                } else {
                    OEntity oentity1 = odamagesource.g();

                    if (oentity1 instanceof OEntityArrow && ((OEntityArrow) oentity1).c != null) {
                        oentity1 = ((OEntityArrow) oentity1).c;
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

    protected int c(ODamageSource odamagesource, int i) {
        int j = super.c(odamagesource, i);

        if (j <= 0) {
            return 0;
        } else {
            int k = OEnchantmentHelper.a(this.by, odamagesource);

            if (k > 20) {
                k = 20;
            }

            if (k > 0 && k <= 20) {
                int l = 25 - k;
                int i1 = j * l + this.aM;

                j = i1 / 25;
                this.aM = i1 % 25;
            }

            return j;
        }
    }

    protected boolean h() {
        return false;
    }

    protected void a(OEntityLiving oentityliving, boolean flag) {
        if (!(oentityliving instanceof OEntityCreeper) && !(oentityliving instanceof OEntityGhast)) {
            if (oentityliving instanceof OEntityWolf) {
                OEntityWolf oentitywolf = (OEntityWolf) oentityliving;

                if (oentitywolf.n() && this.bJ.equals(oentitywolf.p())) {
                    return;
                }
            }

            if (!(oentityliving instanceof OEntityPlayer) || this.h()) {
                List list = this.p.a(OEntityWolf.class, OAxisAlignedBB.a().a(this.t, this.u, this.v, this.t + 1.0D, this.u + 1.0D, this.v + 1.0D).b(16.0D, 4.0D, 16.0D));
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OEntityWolf oentitywolf1 = (OEntityWolf) iterator.next();

                    if (oentitywolf1.n() && oentitywolf1.m() == null && this.bJ.equals(oentitywolf1.p()) && (!flag || !oentitywolf1.o())) {
                        oentitywolf1.g(false);
                        oentitywolf1.b((OEntity) oentityliving);
                    }
                }

            }
        }
    }

    protected void k(int i) {
        this.by.g(i);
    }

    public int aO() {
        return this.by.l();
    }

    protected void d(ODamageSource odamagesource, int i) {
        if (!odamagesource.c() && this.aY()) {
            i = 1 + i >> 1;
        }

        i = this.b(odamagesource, i);
        i = this.c(odamagesource, i);
        this.j(odamagesource.d());
        this.aK -= i;
    }

    public void a(OTileEntityFurnace otileentityfurnace) {}

    public void a(OTileEntityDispenser otileentitydispenser) {}

    public void a(OTileEntitySign otileentitysign) {}

    public void a(OTileEntityBrewingStand otileentitybrewingstand) {}

    public void a(OIMerchant oimerchant) {}

    public void c(OItemStack oitemstack) {}

    public boolean m(OEntity oentity) {
        if (oentity.c(this)) {
            return true;
        } else {
            OItemStack oitemstack = this.bC();
            PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.ENTITY_RIGHTCLICKED, ((OEntityPlayerMP) this).getPlayer(), oentity.entity, (oitemstack == null) ? null : new Item(oitemstack));

            if (res != PluginLoader.HookResult.PREVENT_ACTION) {
                // Normally when interact action is not defined on the interacted entity, false is returned, and the item stack is not used.
                // For example sheep can interact by shearing and cows by milking, and the item stack changes from this interaction if it returns true.
                // Players on the other hand won't interact normally, but if we want to update the item stack anyways, we will ALLOW the action.
                if (res == PluginLoader.HookResult.ALLOW_ACTION) {

                    if (oitemstack != null && oentity instanceof OEntityLiving) {
                        if (this.bZ.d) {
                            oitemstack = oitemstack.l();
                        }

                        if (oitemstack.a((OEntityLiving) oentity)) {
                            if (oitemstack.a <= 0 && !this.bZ.d) {
                                this.bD();
                            }

                            return true;
                        }
                    }
                }

            }
            return false;
        }
    }

    public OItemStack bC() {
        return this.by.g();
    }

    public void bD() {
        this.by.a(this.by.c, (OItemStack) null);
    }

    public double W() {
        return (double) (this.M - 0.5F);
    }

    public void i() {
        if (!this.bH || this.bI >= this.k() / 2 || this.bI < 0) {
            this.bI = -1;
            this.bH = true;
        }

    }

    public void n(OEntity oentity) {
        if (oentity.an()) {
            int i = this.by.a(oentity);

            if (this.a(OPotion.g)) {
                i += 3 << this.b(OPotion.g).c();
            }

            if (this.a(OPotion.t)) {
                i -= 2 << this.b(OPotion.t).c();
            }

            int j = 0;
            int k = 0;

            if (oentity instanceof OEntityLiving) {
                k = OEnchantmentHelper.a(this.by, (OEntityLiving) oentity);
                j += OEnchantmentHelper.b(this.by, (OEntityLiving) oentity);
            }

            if (this.ag()) {
                ++j;
            }

            if (i > 0 || k > 0) {
                boolean flag = this.R > 0.0F && !this.E && !this.f_() && !this.H() && !this.a(OPotion.q) && this.o == null && oentity instanceof OEntityLiving;

                if (flag) {
                    i += this.Z.nextInt(i / 2 + 2);
                }

                i += k;
                boolean flag1 = oentity.a(ODamageSource.a(this), i);

                if (flag1) {
                    if (j > 0) {
                        oentity.g((double) (-OMathHelper.a(this.z * 3.1415927F / 180.0F) * (float) j * 0.5F), 0.1D, (double) (OMathHelper.b(this.z * 3.1415927F / 180.0F) * (float) j * 0.5F));
                        this.w *= 0.6D;
                        this.y *= 0.6D;
                        this.b(false);
                    }

                    if (flag) {
                        this.b(oentity);
                    }

                    if (k > 0) {
                        this.c(oentity);
                    }

                    if (i >= 18) {
                        this.a((OStatBase) OAchievementList.E);
                    }

                    this.j(oentity);
                }

                OItemStack oitemstack = this.bC();

                if (oitemstack != null && oentity instanceof OEntityLiving) {
                    oitemstack.a((OEntityLiving) oentity, this);
                    if (oitemstack.a <= 0) {
                        this.bD();
                    }
                }

                if (oentity instanceof OEntityLiving) {
                    if (oentity.S()) {
                        this.a((OEntityLiving) oentity, true);
                    }

                    this.a(OStatList.w, i);
                    int l = OEnchantmentHelper.c(this.by, (OEntityLiving) oentity);

                    if (l > 0) {
                        oentity.d(l * 4);
                    }
                }

                this.j(0.3F);
            }

        }
    }

    public void b(OEntity oentity) {}

    public void c(OEntity oentity) {}

    public void y() {
        super.y();
        this.bz.a(this);
        if (this.bA != null) {
            this.bA.a(this);
        }

    }

    public boolean T() {
        return !this.bS && super.T();
    }

    public boolean bF() {
        return false;
    }

    public OEnumStatus a(int i, int j, int k) {
        if (!this.p.K) {
            if (this.bn() || !this.S()) {
                return OEnumStatus.e;
            }

            if (!this.p.w.d()) {
                return OEnumStatus.b;
            }

            if (this.p.s()) {
                return OEnumStatus.c;
            }

            if (Math.abs(this.t - (double) i) > 3.0D || Math.abs(this.u - (double) j) > 2.0D || Math.abs(this.v - (double) k) > 3.0D) {
                return OEnumStatus.d;
            }

            double d0 = 8.0D;
            double d1 = 5.0D;
            List list = this.p.a(OEntityMob.class, OAxisAlignedBB.a().a((double) i - d0, (double) j - d1, (double) k - d0, (double) i + d0, (double) j + d1, (double) k + d0));

            if (!list.isEmpty()) {
                return OEnumStatus.f;
            }
        }

        this.a(0.2F, 0.2F);
        this.M = 0.2F;
        if (this.p.e(i, j, k)) {
            int l = this.p.g(i, j, k);
            int i1 = OBlockBed.d(l);
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

            this.b(i1);
            this.b((double) ((float) i + f), (double) ((float) j + 0.9375F), (double) ((float) k + f1));
        } else {
            this.b((double) ((float) i + 0.5F), (double) ((float) j + 0.9375F), (double) ((float) k + 0.5F));
        }

        this.bS = true;
        this.b = 0;
        this.bT = new OChunkCoordinates(i, j, k);
        this.w = this.y = this.x = 0.0D;
        if (!this.p.K) {
            this.p.c();
        }

        return OEnumStatus.a;
    }

    private void b(int i) {
        this.bU = 0.0F;
        this.bV = 0.0F;
        switch (i) {
            case 0:
            this.bV = -1.8F;
                break;

            case 1:
            this.bU = 1.8F;
                break;

            case 2:
            this.bV = 1.8F;
                break;

            case 3:
            this.bU = -1.8F;
        }

    }

    public void a(boolean flag, boolean flag1, boolean flag2) {
        this.a(0.6F, 1.8F);
        this.d_();
        OChunkCoordinates ochunkcoordinates = this.bT;
        OChunkCoordinates ochunkcoordinates1 = this.bT;

        if (ochunkcoordinates != null && this.p.a(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) == OBlock.S.ca) {
            OBlockBed.a(this.p, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, false);
            ochunkcoordinates1 = OBlockBed.b(this.p, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, 0);
            if (ochunkcoordinates1 == null) {
                ochunkcoordinates1 = new OChunkCoordinates(ochunkcoordinates.a, ochunkcoordinates.b + 1, ochunkcoordinates.c);
            }

            this.b((double) ((float) ochunkcoordinates1.a + 0.5F), (double) ((float) ochunkcoordinates1.b + this.M + 0.1F), (double) ((float) ochunkcoordinates1.c + 0.5F));
        }

        this.bS = false;
        if (!this.p.K && flag1) {
            this.p.c();
        }

        if (flag) {
            this.b = 0;
        } else {
            this.b = 100;
        }

        if (flag2) {
            this.a(this.bT);
        }

    }

    private boolean l() {
        return this.p.a(this.bT.a, this.bT.b, this.bT.c) == OBlock.S.ca;
    }

    public static OChunkCoordinates a(OWorld oworld, OChunkCoordinates ochunkcoordinates) {
        OIChunkProvider oichunkprovider = oworld.F();

        oichunkprovider.c(ochunkcoordinates.a - 3 >> 4, ochunkcoordinates.c - 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a + 3 >> 4, ochunkcoordinates.c - 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a - 3 >> 4, ochunkcoordinates.c + 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a + 3 >> 4, ochunkcoordinates.c + 3 >> 4);
        if (oworld.a(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) != OBlock.S.ca) {
            return null;
        } else {
            OChunkCoordinates ochunkcoordinates1 = OBlockBed.b(oworld, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, 0);

            return ochunkcoordinates1;
        }
    }

    public boolean bn() {
        return this.bS;
    }

    public boolean bH() {
        return this.bS && this.b >= 100;
    }

    public void c(String s) {}

    public OChunkCoordinates bJ() {
        return this.c;
    }

    public void a(OChunkCoordinates ochunkcoordinates) {
        if (ochunkcoordinates != null) {
            this.c = new OChunkCoordinates(ochunkcoordinates);
        } else {
            this.c = null;
        }

    }

    public void a(OStatBase ostatbase) {
        this.a(ostatbase, 1);
    }

    public void a(OStatBase ostatbase, int i) {}

    protected void aZ() {
        super.aZ();
        this.a(OStatList.u, 1);
        if (this.ag()) {
            this.j(0.8F);
        } else {
            this.j(0.2F);
        }

    }

    public void e(float f, float f1) {
        double d0 = this.t;
        double d1 = this.u;
        double d2 = this.v;

        if (this.bZ.b && this.o == null) {
            double d3 = this.x;
            float f2 = this.aH;

            this.aH = this.bZ.a();
            super.e(f, f1);
            this.x = d3 * 0.6D;
            this.aH = f2;
        } else {
            super.e(f, f1);
        }

        this.j(this.t - d0, this.u - d1, this.v - d2);
    }

    public void j(double d0, double d1, double d2) {
        if (this.o == null) {
            int i;

            if (this.a(OMaterial.g)) {
                i = Math.round(OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.q, i);
                    this.j(0.015F * (float) i * 0.01F);
                }
            } else if (this.H()) {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.m, i);
                    this.j(0.015F * (float) i * 0.01F);
                }
            } else if (this.f_()) {
                if (d1 > 0.0D) {
                    this.a(OStatList.o, (int) Math.round(d1 * 100.0D));
                }
            } else if (this.E) {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.l, i);
                    if (this.ag()) {
                        this.j(0.099999994F * (float) i * 0.01F);
                    } else {
                        this.j(0.01F * (float) i * 0.01F);
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

    private void k(double d0, double d1, double d2) {
        if (this.o != null) {
            int i = Math.round(OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);

            if (i > 0) {
                if (this.o instanceof OEntityMinecart) {
                    this.a(OStatList.r, i);
                    if (this.d == null) {
                        this.d = new OChunkCoordinates(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
                    } else if ((double) this.d.e(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v)) >= 1000000.0D) {
                        this.a((OStatBase) OAchievementList.q, 1);
                    }
                } else if (this.o instanceof OEntityBoat) {
                    this.a(OStatList.s, i);
                } else if (this.o instanceof OEntityPig) {
                    this.a(OStatList.t, i);
                }
            }
        }

    }

    protected void a(float f) {
        if (!this.bZ.c) {
            if (f >= 2.0F) {
                this.a(OStatList.n, (int) Math.round((double) f * 100.0D));
            }

            super.a(f);
        }
    }

    public void a(OEntityLiving oentityliving) {
        if (oentityliving instanceof OEntityMob) {
            this.a((OStatBase) OAchievementList.s);
        }

    }

    public void aa() {
        if (this.bW > 0) {
            this.bW = 10;
        } else {
            this.bX = true;
        }
    }

    public void a(int i) {
        if(!(Boolean) manager.callHook(PluginLoader.Hook.EXPERIENCE_CHANGE, etc.getDataSource().getPlayer(bJ), cb, cb)){
            this.ca -= i;
            if (this.ca < 0) {
                this.ca = 0;
            }
        }
    }

    public void q(int i) {
        addXP(i);
    }

    public void addXP(int i) {
        int j = Integer.MAX_VALUE - this.cb;

        if (i > j) {
            i = j;
        }

        this.bE += i;
        this.cc += (float) i / (float) this.bK();
        this.cb += i;
        levelUp(i);
    }

    public void removeXP(int i) {
        this.bE -= i;
        this.cc -= (float) i / (float) this.bK();
        this.cb -= i;
        levelUp(i);
    }

    public void setXP(int i) {
        this.bE = i;
        this.cc = (float) i / (float) this.bK();
        this.cb = i;
        levelUp(i);
    }

    public void levelUp(int i) {
        // CanaryMod: Make sure levels are right, even when removing XP.
    	int oldLevel = this.ca;
   		for (; this.cc >= 1.0F; this.cc /= (float) this.bK()) {
   			this.cc = (this.cc - 1.0F) * (float) this.bK();
   			this.m();
   		}
    	if (this.ca > oldLevel) {
			manager.callHook(PluginLoader.Hook.LEVEL_UP, ((OEntityPlayerMP) this).getPlayer());
		}
    }

    public int bK() {
    	if(etc.getInstance().isOldExperience()) {
    		return 7 + (this.ca * 7 >> 1);
    	}
   		return this.ca >= 30 ? 62 + (this.ca - 30) * 7 : (this.ca >= 15 ? 17 + (this.ca - 15) * 3 : 17);
    }

    private void m() {
        ++this.ca;
    }

    public void j(float f) {
        if (!this.bZ.a) {
            if (!this.p.K) {
                this.bB.a(f);
            }

        }
    }

    public OFoodStats bL() {
        return this.bB;
    }

    public boolean e(boolean flag) {
        return (flag || this.bB.c()) && !this.bZ.a;
    }

    public boolean bM() {
        return this.aN() > 0 && this.aN() < this.aM();
    }

    public void a(OItemStack oitemstack, int i) {
        if (oitemstack != this.e) {
            this.e = oitemstack;
            this.f = i;
            if (!this.p.K) {
                this.c(true);
            }

        }
    }

    public boolean e(int i, int j, int k) {
        return this.bZ.e;
    }

    protected int a(OEntityPlayer oentityplayer) {
        int i = this.ca * 7;

        return i > 100 ? 100 : i;
    }

    protected boolean aJ() {
        return true;
    }

    public String ak() {
        return this.bJ;
    }

    public void c(int i) {}

    public void a(OEntityPlayer oentityplayer, boolean flag) {
        if (flag) {
            this.by.b(oentityplayer.by);
            this.aK = oentityplayer.aK;
            this.bB = oentityplayer.bB;
            this.ca = oentityplayer.ca;
            this.cb = oentityplayer.cb;
            this.cc = oentityplayer.cc;
            this.bE = oentityplayer.bE;
        }

        this.a = oentityplayer.a;
    }

    protected boolean e_() {
        return !this.bZ.b;
    }

    public void p() {}

    public void a(OEnumGameType oenumgametype) {}

    public String c_() {
        return this.bJ;
    }

    public OStringTranslate t() {
        return OStringTranslate.a();
    }

    public String a(String s, Object... aobject) {
        return this.t().a(s, aobject);
    }

    public OInventoryEnderChest bN() {
        return this.a;
    }
}
