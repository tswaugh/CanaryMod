import java.util.Iterator;
import java.util.List;

public abstract class OEntityPlayer extends OEntityLiving implements OICommandSender {

    public OInventoryPlayer bJ = new OInventoryPlayer(this);
    private OInventoryEnderChest a = new OInventoryEnderChest();
    public OContainer bK;
    public OContainer bL;
    protected OFoodStats bM = new OFoodStats(this); // CanaryMod: pass 'this'
    protected int bN = 0;
    public byte bO = 0;
    public float bP;
    public float bQ;
    public String bR;
    public int bS = 0;
    public double bT;
    public double bU;
    public double bV;
    public double bW;
    public double bX;
    public double bY;
    protected boolean bZ;
    public OChunkCoordinates ca;
    private int b;
    public float cb;
    public float cc;
    private OChunkCoordinates c;
    private boolean d;
    private OChunkCoordinates e;
    public OPlayerCapabilities cd = new OPlayerCapabilities();
    public int ce;
    public int cf;
    public float cg;
    private OItemStack f;
    private int g;
    protected float ch = 0.1F;
    protected float ci = 0.02F;
    private int h = 0;
    public OEntityFishHook cj = null;
    // CanaryMod start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    HumanEntity entity = new HumanEntity(this);
    private String displayName; // CanaryMod: custom display names
    // CanaryMod end

    public OEntityPlayer(OWorld oworld) {
        super(oworld);
        this.bK = new OContainerPlayer(this.bJ, !oworld.I, this);
        this.bL = this.bK;
        this.M = 1.62F;
        OChunkCoordinates ochunkcoordinates = oworld.H();

        this.b((double) ochunkcoordinates.a + 0.5D, (double) (ochunkcoordinates.b + 1), (double) ochunkcoordinates.c + 0.5D, 0.0F, 0.0F);
        this.aJ = "humanoid";
        this.aI = 180.0F;
        this.ac = 20;
        this.aG = "/mob/char.png";
    }

    public int aT() {
        //CanaryMod: set max health here, but check for uninitialized value.
        return this.maxHealth == 0 ? 20 : this.maxHealth;
    }

    protected void a() {
        super.a();
        this.ag.a(16, Byte.valueOf((byte) 0));
        this.ag.a(17, Byte.valueOf((byte) 0));
        this.ag.a(18, Integer.valueOf(0));
    }

    public boolean bM() {
        return this.f != null;
    }

    public void bO() {
        if (this.f != null) {
            this.f.b(this.p, this, this.g);
        }

        this.bP();
    }

    public void bP() {
        this.f = null;
        this.g = 0;
        if (!this.p.I) {
            this.d(false);
        }
    }

    public boolean bh() {
        return this.bM() && OItem.e[this.f.c].b_(this.f) == OEnumAction.d;
    }

    public void j_() {
        if (this.f != null) {
            OItemStack oitemstack = this.bJ.g();

            if (oitemstack == this.f) {
                if (this.g <= 25 && this.g % 4 == 0) {
                    this.c(oitemstack, 5);
                }

                if (--this.g == 0 && !this.p.I) {
                    this.n();
                }
            } else {
                this.bP();
            }
        }

        if (this.bS > 0) {
            --this.bS;
        }

        if (this.bw()) {
            ++this.b;
            if (this.b > 100) {
                this.b = 100;
            }

            if (!this.p.I) {
                if (!this.j()) {
                    this.a(true, true, false);
                } else if (this.p.u()) {
                    this.a(false, true, true);
                }
            }
        } else if (this.b > 0) {
            ++this.b;
            if (this.b >= 110) {
                this.b = 0;
            }
        }

        super.j_();
        if (!this.p.I && this.bL != null && !this.bL.a(this)) {
            this.i();
            this.bL = this.bK;
        }

        if (this.af() && this.cd.a) {
            this.B();
        }

        this.bT = this.bW;
        this.bU = this.bX;
        this.bV = this.bY;
        double d0 = this.t - this.bW;
        double d1 = this.u - this.bX;
        double d2 = this.v - this.bY;
        double d3 = 10.0D;

        if (d0 > d3) {
            this.bT = this.bW = this.t;
        }

        if (d2 > d3) {
            this.bV = this.bY = this.v;
        }

        if (d1 > d3) {
            this.bU = this.bX = this.u;
        }

        if (d0 < -d3) {
            this.bT = this.bW = this.t;
        }

        if (d2 < -d3) {
            this.bV = this.bY = this.v;
        }

        if (d1 < -d3) {
            this.bU = this.bX = this.u;
        }

        this.bW += d0 * 0.25D;
        this.bY += d2 * 0.25D;
        this.bX += d1 * 0.25D;
        this.a(OStatList.k, 1);
        if (this.o == null) {
            this.e = null;
        }

        if (!this.p.I) {
            this.bM.a(this);
        }
    }

    public int z() {
        return this.cd.a ? 0 : 80;
    }

    public int ab() {
        return 10;
    }

    public void a(String s, float f, float f1) {
        this.p.a(this, s, f, f1);
    }

    protected void c(OItemStack oitemstack, int i) {
        if (oitemstack.n() == OEnumAction.c) {
            this.a("random.drink", 0.5F, this.p.t.nextFloat() * 0.1F + 0.9F);
        }

        if (oitemstack.n() == OEnumAction.b) {
            for (int j = 0; j < i; ++j) {
                OVec3 ovec3 = this.p.S().a(((double) this.aa.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

                ovec3.a(-this.A * 3.1415927F / 180.0F);
                ovec3.b(-this.z * 3.1415927F / 180.0F);
                OVec3 ovec31 = this.p.S().a(((double) this.aa.nextFloat() - 0.5D) * 0.3D, (double) (-this.aa.nextFloat()) * 0.6D - 0.3D, 0.6D);

                ovec31.a(-this.A * 3.1415927F / 180.0F);
                ovec31.b(-this.z * 3.1415927F / 180.0F);
                ovec31 = ovec31.c(this.t, this.u + (double) this.e(), this.v);
                this.p.a("iconcrack_" + oitemstack.b().cj, ovec31.c, ovec31.d, ovec31.e, ovec3.c, ovec3.d + 0.05D, ovec3.e);
            }

            this.a("random.eat", 0.5F + 0.5F * (float) this.aa.nextInt(2), (this.aa.nextFloat() - this.aa.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected void n() {
        if (this.f != null) {
            this.c(this.f, 16);
            int i = this.f.a;
            OItemStack oitemstack = this.f.b(this.p, this);

            if (oitemstack != this.f || oitemstack != null && oitemstack.a != i) {
                this.bJ.a[this.bJ.c] = oitemstack;
                if (oitemstack.a == 0) {
                    this.bJ.a[this.bJ.c] = null;
                }
            }

            this.bP();
        }
    }

    protected boolean bg() {
        return this.aU() <= 0 || this.bw();
    }

    protected void i() {
        this.bL = this.bK;
    }

    public void U() {
        double d0 = this.t;
        double d1 = this.u;
        double d2 = this.v;
        float f = this.z;
        float f1 = this.A;

        super.U();
        this.bP = this.bQ;
        this.bQ = 0.0F;
        this.k(this.t - d0, this.u - d1, this.v - d2);
        if (this.o instanceof OEntityPig) {
            this.A = f1;
            this.z = f;
            this.ax = ((OEntityPig) this.o).ax;
        }
    }

    protected void bn() {
        this.bo();
    }

    public void c() {
        if (this.bN > 0) {
            --this.bN;
        }

        // CanaryMod: adjust 'healing over time' independent of monster-spawn=true/false (nice notchup!)
        PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();

        if (this.p.s == 0 && autoHeal == PluginLoader.HookResult.DEFAULT_ACTION || autoHeal == PluginLoader.HookResult.ALLOW_ACTION) {
            if (this.aU() < this.aT() && this.ab % 20 * 12 == 0) {
                this.i(1);
            }
        }

        this.bJ.j();
        this.bP = this.bQ;
        super.c();
        this.aN = this.cd.b();
        this.aO = this.ci;
        if (this.ai()) {
            this.aN = (float) ((double) this.aN + (double) this.cd.b() * 0.3D);
            this.aO = (float) ((double) this.aO + (double) this.ci * 0.3D);
        }

        float f = OMathHelper.a(this.w * this.w + this.y * this.y);
        float f1 = (float) Math.atan(-this.x * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.E || this.aU() <= 0) {
            f = 0.0F;
        }

        if (this.E || this.aU() <= 0) {
            f1 = 0.0F;
        }

        this.bQ += (f - this.bQ) * 0.4F;
        this.bb += (f1 - this.bb) * 0.8F;
        if (this.aU() > 0) {
            List list = this.p.b((OEntity) this, this.D.b(1.0D, 0.5D, 1.0D));

            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    OEntity oentity = (OEntity) list.get(i);

                    if (!oentity.L) {
                        this.r(oentity);
                    }
                }
            }
        }
    }

    private void r(OEntity oentity) {
        oentity.c_(this);
    }

    public int bQ() {
        return this.ag.c(18);
    }

    public void s(int i) {
        this.ag.b(18, Integer.valueOf(i));
    }

    public void t(int i) {
        int j = this.bQ();

        this.ag.b(18, Integer.valueOf(j + i));
    }

    public void a(ODamageSource odamagesource) {
        super.a(odamagesource);
        this.a(0.2F, 0.2F);
        this.b(this.t, this.u, this.v);
        this.x = 0.10000000149011612D;
        if (this.bR.equals("Notch")) {
            this.a(new OItemStack(OItem.j, 1), true);
        }

        if (!this.p.L().b("keepInventory")) {
            this.bJ.l();
        }

        if (odamagesource != null) {
            this.w = (double) (-OMathHelper.b((this.aX + this.z) * 3.1415927F / 180.0F) * 0.1F);
            this.y = (double) (-OMathHelper.a((this.aX + this.z) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.w = this.y = 0.0D;
        }

        this.M = 0.1F;
        this.a(OStatList.y, 1);
    }

    public void c(OEntity oentity, int i) {
        this.t(i);
        if (oentity instanceof OEntityPlayer) {
            this.a(OStatList.A, 1);
        } else {
            this.a(OStatList.z, 1);
        }
    }

    public OEntityItem f(boolean flag) {
        return this.a(this.bJ.a(this.bJ.c, flag && this.bJ.g() != null ? this.bJ.g().a : 1), false);
    }

    public OEntityItem c(OItemStack oitemstack) {
        return this.a(oitemstack, false);
    }

    public OEntityItem a(OItemStack oitemstack, boolean flag) {
        if (oitemstack == null) {
            return null;
        } else {
            OEntityItem oentityitem = new OEntityItem(this.p, this.t, this.u - 0.30000001192092896D + (double) this.e(), this.v, oitemstack);

            oentityitem.b = 40;
            float f = 0.1F;
            float f1;

            if (flag) {
                f1 = this.aa.nextFloat() * 0.5F;
                float f2 = this.aa.nextFloat() * 3.1415927F * 2.0F;

                oentityitem.w = (double) (-OMathHelper.a(f2) * f1);
                oentityitem.y = (double) (OMathHelper.b(f2) * f1);
                oentityitem.x = 0.20000000298023224D;
            } else {
                f = 0.3F;
                oentityitem.w = (double) (-OMathHelper.a(this.z / 180.0F * 3.1415927F) * OMathHelper.b(this.A / 180.0F * 3.1415927F) * f);
                oentityitem.y = (double) (OMathHelper.b(this.z / 180.0F * 3.1415927F) * OMathHelper.b(this.A / 180.0F * 3.1415927F) * f);
                oentityitem.x = (double) (-OMathHelper.a(this.A / 180.0F * 3.1415927F) * f + 0.1F);
                f = 0.02F;
                f1 = this.aa.nextFloat() * 3.1415927F * 2.0F;
                f *= this.aa.nextFloat();
                oentityitem.w += Math.cos((double) f1) * (double) f;
                oentityitem.x += (double) ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.1F);
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

                return oentityitem;
            } else { // return the item to the inventory.
                return null;
            }
        }
    }

    protected void a(OEntityItem oentityitem) {
        this.p.d((OEntity) oentityitem);
    }

    public float a(OBlock oblock) {
        float f = this.bJ.a(oblock);
        int i = OEnchantmentHelper.c(this);
        OItemStack oitemstack = this.bJ.g();

        if (i > 0 && oitemstack != null) {
            float f1 = (float) (i * i + 1);

            if (!oitemstack.b(oblock) && f <= 1.0F) {
                f += f1 * 0.08F;
            } else {
                f += f1;
            }
        }

        if (this.a(OPotion.e)) {
            f *= 1.0F + (float) (this.b(OPotion.e).c() + 1) * 0.2F;
        }

        if (this.a(OPotion.f)) {
            f *= 1.0F - (float) (this.b(OPotion.f).c() + 1) * 0.2F;
        }

        if (this.a(OMaterial.h) && !OEnchantmentHelper.h(this)) {
            f /= 5.0F;
        }

        if (!this.E) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(OBlock oblock) {
        return this.bJ.b(oblock);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Inventory");

        this.bJ.b(onbttaglist);
        this.bJ.c = onbttagcompound.e("SelectedItemSlot");
        this.bZ = onbttagcompound.n("Sleeping");
        this.b = onbttagcompound.d("SleepTimer");
        this.cg = onbttagcompound.g("XpP");
        this.ce = onbttagcompound.e("XpLevel");
        this.cf = onbttagcompound.e("XpTotal");
        this.s(onbttagcompound.e("Score"));
        if (this.bZ) {
            this.ca = new OChunkCoordinates(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
            this.a(true, true, false);
        }

        if (onbttagcompound.b("SpawnX") && onbttagcompound.b("SpawnY") && onbttagcompound.b("SpawnZ")) {
            this.c = new OChunkCoordinates(onbttagcompound.e("SpawnX"), onbttagcompound.e("SpawnY"), onbttagcompound.e("SpawnZ"));
            this.d = onbttagcompound.n("SpawnForced");
        }

        this.bM.a(onbttagcompound);
        this.cd.b(onbttagcompound);
        if (onbttagcompound.b("EnderItems")) {
            ONBTTagList onbttaglist1 = onbttagcompound.m("EnderItems");

            this.a.a(onbttaglist1);
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Inventory", (ONBTBase) this.bJ.a(new ONBTTagList()));
        onbttagcompound.a("SelectedItemSlot", this.bJ.c);
        onbttagcompound.a("Sleeping", this.bZ);
        onbttagcompound.a("SleepTimer", (short) this.b);
        onbttagcompound.a("XpP", this.cg);
        onbttagcompound.a("XpLevel", this.ce);
        onbttagcompound.a("XpTotal", this.cf);
        onbttagcompound.a("Score", this.bQ());
        if (this.c != null) {
            onbttagcompound.a("SpawnX", this.c.a);
            onbttagcompound.a("SpawnY", this.c.b);
            onbttagcompound.a("SpawnZ", this.c.c);
            onbttagcompound.a("SpawnForced", this.d);
        }

        this.bM.b(onbttagcompound);
        this.cd.a(onbttagcompound);
        onbttagcompound.a("EnderItems", (ONBTBase) this.a.g());
    }

    public void a(OIInventory oiinventory) {}

    public void c(int i, int j, int k) {}

    public void d(int i, int j, int k) {}

    public void b(int i, int j, int k) {}

    public float e() {
        return 0.12F;
    }

    protected void e_() {
        this.M = 1.62F;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.ar()) {
            return false;
        } else if (this.cd.a && !odamagesource.e()) {
            return false;
        } else {
            this.bB = 0;
            if (this.aU() <= 0) {
                return false;
            } else {
                if (this.bw() && !this.p.I) {
                    this.a(true, true, false);
                }

                if (odamagesource.n()) {
                    if (this.p.s == 0) {
                        i = 0;
                    }

                    if (this.p.s == 1) {
                        i = i / 2 + 1;
                    }

                    if (this.p.s == 3) {
                        i = i * 3 / 2;
                    }
                }

                if (i == 0) {
                    return false;
                } else {
                    OEntity oentity = odamagesource.g();

                    if (oentity instanceof OEntityArrow && ((OEntityArrow) oentity).c != null) {
                        oentity = ((OEntityArrow) oentity).c;
                    }

                    if (oentity instanceof OEntityLiving) {
                        this.a((OEntityLiving) oentity, false);
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
            int k = OEnchantmentHelper.a(this.bJ.b, odamagesource);

            if (k > 20) {
                k = 20;
            }

            if (k > 0 && k <= 20) {
                int l = 25 - k;
                int i1 = j * l + this.aT;

                j = i1 / 25;
                this.aT = i1 % 25;
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

                if (oentitywolf.m() && this.bR.equals(oentitywolf.o())) {
                    return;
                }
            }

            if (!(oentityliving instanceof OEntityPlayer) || this.h()) {
                List list = this.p.a(OEntityWolf.class, OAxisAlignedBB.a().a(this.t, this.u, this.v, this.t + 1.0D, this.u + 1.0D, this.v + 1.0D).b(16.0D, 4.0D, 16.0D));
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OEntityWolf oentitywolf1 = (OEntityWolf) iterator.next();

                    if (oentitywolf1.m() && oentitywolf1.l() == null && this.bR.equals(oentitywolf1.o()) && (!flag || !oentitywolf1.n())) {
                        oentitywolf1.h(false);
                        oentitywolf1.b((OEntity) oentityliving);
                    }
                }
            }
        }
    }

    protected void k(int i) {
        this.bJ.g(i);
    }

    public int aW() {
        return this.bJ.k();
    }

    public float bR() {
        int i = 0;
        OItemStack[] aoitemstack = this.bJ.b;
        int j = aoitemstack.length;

        for (int k = 0; k < j; ++k) {
            OItemStack oitemstack = aoitemstack[k];

            if (oitemstack != null) {
                ++i;
            }
        }

        return (float) i / (float) this.bJ.b.length;
    }

    protected void d(ODamageSource odamagesource, int i) {
        if (!this.ar()) {
            if (!odamagesource.c() && this.bh()) {
                i = 1 + i >> 1;
            }

            i = this.b(odamagesource, i);
            i = this.c(odamagesource, i);
            this.j(odamagesource.d());
            this.aR -= i;
        }
    }

    public void a(OTileEntityFurnace otileentityfurnace) {}

    public void a(OTileEntityDispenser otileentitydispenser) {}

    public void a(OTileEntity otileentity) {}

    public void a(OTileEntityBrewingStand otileentitybrewingstand) {}

    public void a(OTileEntityBeacon otileentitybeacon) {}

    public void a(OIMerchant oimerchant) {}

    public void d(OItemStack oitemstack) {}

    public boolean p(OEntity oentity) {
        if (oentity.a(this)) {
            return true;
        } else {
            OItemStack oitemstack = this.bS();
            PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.ENTITY_RIGHTCLICKED, ((OEntityPlayerMP) this).getPlayer(), oentity.entity, (oitemstack == null) ? null : new Item(oitemstack));

            if (res == PluginLoader.HookResult.ALLOW_ACTION) {
                return true;
            } else if (res == PluginLoader.HookResult.DEFAULT_ACTION) {
                // Normally when interact action is not defined on the interacted entity, false is returned, and the item stack is not used.
                // For example sheep can interact by shearing and cows by milking, and the item stack changes from this interaction if it returns true.
                // Players on the other hand won't interact normally, but if we want to update the item stack anyways, we will ALLOW the action.
                if (oitemstack != null && oentity instanceof OEntityLiving) {
                    if (this.cd.d) {
                        oitemstack = oitemstack.l();
                    }

                    if (oitemstack.a((OEntityLiving) oentity)) {
                        if (oitemstack.a <= 0 && !this.cd.d) {
                            this.bT();
                        }

                        return true;
                    }
                }
            }

            return false;
        }
    }

    public OItemStack bS() {
        return this.bJ.g();
    }

    public void bT() {
        this.bJ.a(this.bJ.c, (OItemStack) null);
    }

    public double W() {
        return (double) (this.M - 0.5F);
    }

    public void q(OEntity oentity) {
        if (oentity.aq()) {
            if (!oentity.j(this)) {
                int i = this.bJ.a(oentity);

                if (this.a(OPotion.g)) {
                    i += 3 << this.b(OPotion.g).c();
                }

                if (this.a(OPotion.t)) {
                    i -= 2 << this.b(OPotion.t).c();
                }

                int j = 0;
                int k = 0;

                if (oentity instanceof OEntityLiving) {
                    k = OEnchantmentHelper.a((OEntityLiving) this, (OEntityLiving) oentity);
                    j += OEnchantmentHelper.b(this, (OEntityLiving) oentity);
                }

                if (this.ai()) {
                    ++j;
                }

                if (i > 0 || k > 0) {
                    boolean flag = this.S > 0.0F && !this.E && !this.g_() && !this.H() && !this.a(OPotion.q) && this.o == null && oentity instanceof OEntityLiving;

                    if (flag) {
                        i += this.aa.nextInt(i / 2 + 2);
                    }

                    i += k;
                    boolean flag1 = false;
                    int l = OEnchantmentHelper.a((OEntityLiving) this);

                    if (oentity instanceof OEntityLiving && l > 0 && !oentity.af()) {
                        flag1 = true;
                        oentity.c(1);
                    }

                    boolean flag2 = oentity.a(ODamageSource.a(this), i);

                    if (flag2) {
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

                        this.l(oentity);
                        if (oentity instanceof OEntityLiving) {
                            OEnchantmentThorns.a(this, (OEntityLiving) oentity, this.aa);
                        }
                    }

                    OItemStack oitemstack = this.bS();

                    if (oitemstack != null && oentity instanceof OEntityLiving) {
                        oitemstack.a((OEntityLiving) oentity, this);
                        if (oitemstack.a <= 0) {
                            this.bT();
                        }
                    }

                    if (oentity instanceof OEntityLiving) {
                        if (oentity.S()) {
                            this.a((OEntityLiving) oentity, true);
                        }

                        this.a(OStatList.w, i);
                        if (l > 0 && flag2) {
                            oentity.c(l * 4);
                        } else if (flag1) {
                            oentity.B();
                        }
                    }

                    this.j(0.3F);
                }
            }
        }
    }

    public void b(OEntity oentity) {}

    public void c(OEntity oentity) {}

    public void x() {
        super.x();
        this.bK.b(this);
        if (this.bL != null) {
            this.bL.b(this);
        }
    }

    public boolean T() {
        return !this.bZ && super.T();
    }

    public boolean bV() {
        return false;
    }

    public OEnumStatus a(int i, int j, int k) {
        if (!this.p.I) {
            if (this.bw() || !this.S()) {
                return OEnumStatus.e;
            }

            if (!this.p.u.d()) {
                return OEnumStatus.b;
            }

            if (this.p.u()) {
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
        if (this.p.f(i, j, k)) {
            int l = this.p.h(i, j, k);
            int i1 = OBlockBed.e(l);
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

            this.x(i1);
            this.b((double) ((float) i + f), (double) ((float) j + 0.9375F), (double) ((float) k + f1));
        } else {
            this.b((double) ((float) i + 0.5F), (double) ((float) j + 0.9375F), (double) ((float) k + 0.5F));
        }

        this.bZ = true;
        this.b = 0;
        this.ca = new OChunkCoordinates(i, j, k);
        this.w = this.y = this.x = 0.0D;
        if (!this.p.I) {
            this.p.c();
        }

        return OEnumStatus.a;
    }

    private void x(int i) {
        this.cb = 0.0F;
        this.cc = 0.0F;
        switch (i) {
            case 0:
                this.cc = -1.8F;
                break;

            case 1:
                this.cb = 1.8F;
                break;

            case 2:
                this.cc = 1.8F;
                break;

            case 3:
                this.cb = -1.8F;
        }
    }

    public void a(boolean flag, boolean flag1, boolean flag2) {
        this.a(0.6F, 1.8F);
        this.e_();
        OChunkCoordinates ochunkcoordinates = this.ca;
        OChunkCoordinates ochunkcoordinates1 = this.ca;

        if (ochunkcoordinates != null && this.p.a(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) == OBlock.V.cm) {
            OBlockBed.a(this.p, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, false);
            ochunkcoordinates1 = OBlockBed.b(this.p, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, 0);
            if (ochunkcoordinates1 == null) {
                ochunkcoordinates1 = new OChunkCoordinates(ochunkcoordinates.a, ochunkcoordinates.b + 1, ochunkcoordinates.c);
            }

            this.b((double) ((float) ochunkcoordinates1.a + 0.5F), (double) ((float) ochunkcoordinates1.b + this.M + 0.1F), (double) ((float) ochunkcoordinates1.c + 0.5F));
        }

        this.bZ = false;
        if (!this.p.I && flag1) {
            this.p.c();
        }

        if (flag) {
            this.b = 0;
        } else {
            this.b = 100;
        }

        if (flag2) {
            this.a(this.ca, false);
        }
    }

    private boolean j() {
        return this.p.a(this.ca.a, this.ca.b, this.ca.c) == OBlock.V.cm;
    }

    public static OChunkCoordinates a(OWorld oworld, OChunkCoordinates ochunkcoordinates, boolean flag) {
        OIChunkProvider oichunkprovider = oworld.I();

        oichunkprovider.c(ochunkcoordinates.a - 3 >> 4, ochunkcoordinates.c - 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a + 3 >> 4, ochunkcoordinates.c - 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a - 3 >> 4, ochunkcoordinates.c + 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a + 3 >> 4, ochunkcoordinates.c + 3 >> 4);
        if (oworld.a(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) == OBlock.V.cm) {
            OChunkCoordinates ochunkcoordinates1 = OBlockBed.b(oworld, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, 0);

            return ochunkcoordinates1;
        } else {
            OMaterial omaterial = oworld.g(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c);
            OMaterial omaterial1 = oworld.g(ochunkcoordinates.a, ochunkcoordinates.b + 1, ochunkcoordinates.c);
            boolean flag1 = !omaterial.a() && !omaterial.d();
            boolean flag2 = !omaterial1.a() && !omaterial1.d();

            return flag && flag1 && flag2 ? ochunkcoordinates : null;
        }
    }

    public boolean bw() {
        return this.bZ;
    }

    public boolean bX() {
        return this.bZ && this.b >= 100;
    }

    protected void b(int i, boolean flag) {
        byte b0 = this.ag.a(16);

        if (flag) {
            this.ag.b(16, Byte.valueOf((byte) (b0 | 1 << i)));
        } else {
            this.ag.b(16, Byte.valueOf((byte) (b0 & ~(1 << i))));
        }
    }

    public void b(String s) {}

    public OChunkCoordinates bZ() {
        return this.c;
    }

    public boolean ca() {
        return this.d;
    }

    public void a(OChunkCoordinates ochunkcoordinates, boolean flag) {
        if (ochunkcoordinates != null) {
            this.c = new OChunkCoordinates(ochunkcoordinates);
            this.d = flag;
        } else {
            this.c = null;
            this.d = false;
        }
    }

    public void a(OStatBase ostatbase) {
        this.a(ostatbase, 1);
    }

    public void a(OStatBase ostatbase, int i) {}

    protected void bi() {
        super.bi();
        this.a(OStatList.u, 1);
        if (this.ai()) {
            this.j(0.8F);
        } else {
            this.j(0.2F);
        }
    }

    public void e(float f, float f1) {
        double d0 = this.t;
        double d1 = this.u;
        double d2 = this.v;

        if (this.cd.b && this.o == null) {
            double d3 = this.x;
            float f2 = this.aO;

            this.aO = this.cd.a();
            super.e(f, f1);
            this.x = d3 * 0.6D;
            this.aO = f2;
        } else {
            super.e(f, f1);
        }

        this.j(this.t - d0, this.u - d1, this.v - d2);
    }

    public void j(double d0, double d1, double d2) {
        if (this.o == null) {
            int i;

            if (this.a(OMaterial.h)) {
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
            } else if (this.g_()) {
                if (d1 > 0.0D) {
                    this.a(OStatList.o, (int) Math.round(d1 * 100.0D));
                }
            } else if (this.E) {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.l, i);
                    if (this.ai()) {
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
                    if (this.e == null) {
                        this.e = new OChunkCoordinates(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
                    } else if ((double) this.e.e(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v)) >= 1000000.0D) {
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
        if (!this.cd.c) {
            if (f >= 2.0F) {
                this.a(OStatList.n, (int) Math.round((double) f * 100.0D));
            }

            super.a(f);
        }
    }

    public void a(OEntityLiving oentityliving) {
        if (oentityliving instanceof OIMob) {
            this.a((OStatBase) OAchievementList.s);
        }
    }

    public void am() {
        if (!this.cd.b) {
            super.am();
        }
    }

    public OItemStack q(int i) {
        return this.bJ.f(i);
    }

    protected void bE() {}

    protected void bF() {}

    public void w(int i) {
        this.t(i);
        int j = Integer.MAX_VALUE - this.cf;

        if (i > j) {
            i = j;
        }

        this.cg += (float) i / (float) this.cb();

        for (this.cf += i; this.cg >= 1.0F; this.cg /= (float) this.cb()) {
            this.cg = (this.cg - 1.0F) * (float) this.cb();
            this.a(1);
        }
    }

    // CanaryMod start - custom XP methods
    public void removeXP(int i) {
        if (i > this.cf) { // Don't go below 0
            i = this.cf;
        }

        this.cf -= (float) i / (float) this.cb();

        // Inverse of for loop in this.t(int)
        for (this.cf -= i; this.cg < 0.0F; this.cg = this.cg / this.cb() + 1.0F) {
            this.cg *= this.cb();
            this.a(-1);
        }
    }

    public void setXP(int i) {
        if (i < this.ce) {
            this.removeXP(this.ce - i);
        } else {
            this.t(i - this.ce);
        }
    }

    public void recalculateXP() {
        this.cg = this.cf / (float) this.cb();
        this.ce = 0;

        while (this.cg >= 1.0F) {
            this.cg = (this.cg - 1.0F) * this.cb();
            this.ce++;
            this.cg /= this.cb();
        }

        if (this instanceof OEntityPlayerMP) {
            ((OEntityPlayerMP) this).getEntity().updateLevels();
        }
    } // CanaryMod end - custom XP methods

    public void a(int i) {
        manager.callHook(PluginLoader.Hook.LEVEL_UP, ((OEntityPlayerMP) this).getPlayer());
        this.ce += i;
        if (this.ce < 0) {
            this.ce = 0;
            this.cg = 0.0F;
            this.cf = 0;
        }

        if (i > 0 && this.ce % 5 == 0 && (float) this.h < (float) this.ab - 100.0F) {
            float f = this.ce > 30 ? 1.0F : (float) this.ce / 30.0F;

            this.p.a((OEntity) this, "random.levelup", f * 0.75F, 1.0F);
            this.h = this.ab;
        }
    }

    public int cb() {
        // CanaryMod: Old experience option
        if(etc.getInstance().isOldExperience()) {
            return 7 + (this.ce * 7 >> 1);
        } // CanaryMod: End
        return this.ce >= 30 ? 62 + (this.ce - 30) * 7 : (this.ce >= 15 ? 17 + (this.ce - 15) * 3 : 17);
    }

    public void j(float f) {
        if (!this.cd.a) {
            if (!this.p.I) {
                this.bM.a(f);
            }
        }
    }

    public OFoodStats cc() {
        return this.bM;
    }

    public boolean g(boolean flag) {
        return (flag || this.bM.c()) && !this.cd.a;
    }

    public boolean cd() {
        return this.aU() > 0 && this.aU() < this.aT();
    }

    public void a(OItemStack oitemstack, int i) {
        if (oitemstack != this.f) {
            this.f = oitemstack;
            this.g = i;
            if (!this.p.I) {
                this.d(true);
            }
        }
    }

    public boolean f(int i, int j, int k) {
        if (this.cd.e) {
            return true;
        } else {
            int l = this.p.a(i, j, k);

            if (l > 0) {
                OBlock oblock = OBlock.p[l];

                if (oblock.cB.q()) {
                    return true;
                }

                if (this.bS() != null) {
                    OItemStack oitemstack = this.bS();

                    if (oitemstack.b(oblock) || oitemstack.a(oblock) > 1.0F) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public boolean a(int i, int j, int k, int l, OItemStack oitemstack) {
        return this.cd.e ? true : (oitemstack != null ? oitemstack.x() : false);
    }

    protected int c(OEntityPlayer oentityplayer) {
        if (this.p.L().b("keepInventory")) {
            return 0;
        } else {
            int i = this.ce * 7;

            return i > 100 ? 100 : i;
        }
    }

    protected boolean aQ() {
        return true;
    }

    public String an() {
        return this.bR;
    }

    public void a(OEntityPlayer oentityplayer, boolean flag) {
        if (flag) {
            this.bJ.b(oentityplayer.bJ);
            this.aR = oentityplayer.aR;
            this.bM = oentityplayer.bM;
            this.ce = oentityplayer.ce;
            this.cf = oentityplayer.cf;
            this.cg = oentityplayer.cg;
            this.s(oentityplayer.bQ());
            this.ar = oentityplayer.ar;
        } else if (this.p.L().b("keepInventory")) {
            this.bJ.b(oentityplayer.bJ);
            this.ce = oentityplayer.ce;
            this.cf = oentityplayer.cf;
            this.cg = oentityplayer.cg;
            this.s(oentityplayer.bQ());
        }

        this.a = oentityplayer.a;
    }

    protected boolean f_() {
        return !this.cd.b;
    }

    public void o() {}

    public void a(OEnumGameType oenumgametype) {}

    public String c_() {
        return this.bR;
    }

    public OStringTranslate s() {
        return OStringTranslate.a();
    }

    public String a(String s, Object... aobject) {
        return this.s().a(s, aobject);
    }

    public OInventoryEnderChest ce() {
        return this.a;
    }

    public OItemStack p(int i) {
        return i == 0 ? this.bJ.g() : this.bJ.b[i - 1];
    }

    public OItemStack bD() {
        return this.bJ.g();
    }

    public void b(int i, OItemStack oitemstack) {
        this.bJ.b[i] = oitemstack;
    }

    public OItemStack[] ae() {
        return this.bJ.b;
    }

    @Override
    public HumanEntity getEntity() {
        return entity;
    } //

    // CanaryMod start
    public String getDisplayName() {
        return displayName == null ? this.bR : displayName;
    }

    public void setDisplayName(String name) {
        this.displayName = name;
    }
    // CanaryMod end
}
