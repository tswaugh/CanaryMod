import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class OEntityPlayerMP extends OEntityPlayer implements OICrafting {

    private OStringTranslate cg = new OStringTranslate("en_US");
    public ONetServerHandler a;
    public OMinecraftServer b;
    public OItemInWorldManager c;
    public double d;
    public double e;
    public final List f = new LinkedList();
    public final List g = new LinkedList();
    private int ch = -99999999;
    private int ci = -99999999;
    private boolean cj = true;
    private int ck = -99999999;
    private int cl = 60;
    private int cm = 0;
    private int cn = 0;
    private boolean co = true;
    private OItemStack[] cp = new OItemStack[] { null, null, null, null, null};
    private int cq = 0;
    public boolean h;
    public int i;
    public boolean j = false;
    // CanaryMod: Player storage
    private Player player;

    public OEntityPlayerMP(OMinecraftServer ominecraftserver, OWorld oworld, String s, OItemInWorldManager oiteminworldmanager) {
        super(oworld);
        oiteminworldmanager.b = this;
        this.c = oiteminworldmanager;
        this.cm = ominecraftserver.ab().o();
        OChunkCoordinates ochunkcoordinates = oworld.E();
        int i = ochunkcoordinates.a;
        int j = ochunkcoordinates.c;
        int k = ochunkcoordinates.b;

        if (!oworld.w.e && oworld.H().q() != OEnumGameType.d) {
            i += this.Z.nextInt(20) - 10;
            k = oworld.h(i, j);
            j += this.Z.nextInt(20) - 10;
        }

        this.b((double) i + 0.5D, (double) k, (double) j + 0.5D, 0.0F, 0.0F);
        this.b = ominecraftserver;
        this.W = 0.0F;
        this.bJ = s;
        this.M = 0.0F;
        
        // CanaryMod: Store player
        player = etc.getDataSource().getPlayer(s);
        player.setUser(this);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        if (onbttagcompound.b("playerGameType")) {
            this.c.a(OEnumGameType.a(onbttagcompound.e("playerGameType")));
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("playerGameType", this.c.b().a());
    }

    public void a(int i) {
        super.a(i);
        this.ck = -1;
    }

    public void b() {
        this.bA.a((OICrafting) this);
    }

    public OItemStack[] c() {
        return this.cp;
    }

    protected void d_() {
        this.M = 0.0F;
    }

    public float e() {
        return 1.62F;
    }

    public void h_() {
        this.c.a();
        --this.cl;
        this.bA.b();

        int i;

        for (i = 0; i < 5; ++i) {
            OItemStack oitemstack = this.b(i);

            if (oitemstack != this.cp[i]) {
                this.q().o().a(this, new OPacket5PlayerInventory(this.k, i, oitemstack));
                this.cp[i] = oitemstack;
            }
        }

        if (!this.f.isEmpty()) {
            ArrayList arraylist = new ArrayList();
            Iterator iterator = this.f.iterator();
            ArrayList arraylist1 = new ArrayList();

            while (iterator.hasNext() && arraylist.size() < 5) {
                OChunkCoordIntPair ochunkcoordintpair = (OChunkCoordIntPair) iterator.next();

                iterator.remove();
                if (ochunkcoordintpair != null && this.p.e(ochunkcoordintpair.a << 4, 0, ochunkcoordintpair.b << 4)) {
                    arraylist.add(this.p.e(ochunkcoordintpair.a, ochunkcoordintpair.b));
                    arraylist1.addAll(((OWorldServer) this.p).a(ochunkcoordintpair.a * 16, 0, ochunkcoordintpair.b * 16, ochunkcoordintpair.a * 16 + 16, 256, ochunkcoordintpair.b * 16 + 16));
                }
            }

            if (!arraylist.isEmpty()) {
                this.a.b(new OPacket56MapChunks(arraylist));
                Iterator iterator1 = arraylist1.iterator();

                while (iterator1.hasNext()) {
                    OTileEntity otileentity = (OTileEntity) iterator1.next();

                    this.a(otileentity);
                }
            }
        }

        if (!this.g.isEmpty()) {
            i = Math.min(this.g.size(), 127);
            int[] aint = new int[i];
            Iterator iterator2 = this.g.iterator();
            int j = 0;

            while (iterator2.hasNext() && j < i) {
                aint[j++] = ((Integer) iterator2.next()).intValue();
                iterator2.remove();
            }

            this.a.b(new OPacket29DestroyEntity(aint));
        }
    }

    public void g() {
        super.h_();

        for (int i = 0; i < this.by.i_(); ++i) {
            OItemStack oitemstack = this.by.a(i);

            if (oitemstack != null && OItem.e[oitemstack.c].m_() && this.a.e() <= 2) {
                OPacket opacket = ((OItemMapBase) OItem.e[oitemstack.c]).c(oitemstack, this.p, this);

                if (opacket != null) {
                    this.a.b(opacket);
                }
            }
        }

        if (this.bX) {
            if (this.b.r()) {
                if (this.bA != this.bz) {
                    this.j();
                }

                if (this.o != null) {
                    this.a(this.o);
                } else {
                    this.bY += 0.0125F;
                    if (this.bY >= 1.0F) {
                        this.bY = 1.0F;
                        this.bW = 10;
                        boolean flag = false;
                        byte b0;

                        if (this.bK == -1) {
                            b0 = 0;
                        } else {
                            b0 = -1;
                        }
                        
                        // CanaryMod onPortalUse
                        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PORTAL_USE, player, player.getWorld())) {
                            this.b.ab().a(this, b0);
                            this.ck = -1;
                            this.ch = -1;
                            this.ci = -1;
                            this.a((OStatBase) OAchievementList.x);
                        }
                    }
                }

                this.bX = false;
            }
        } else {
            if (this.bY > 0.0F) {
                this.bY -= 0.05F;
            }

            if (this.bY < 0.0F) {
                this.bY = 0.0F;
            }
        }

        if (this.bW > 0) {
            --this.bW;
        }

        // CanaryMod: Update the health
        if (this.aN() != this.ch) {
            // updates your health when it is changed.
            if (!etc.getInstance().isHealthEnabled()) {
                this.aK = this.aM();
                this.aU = false;
            } else if ((Boolean) manager.callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), ch, aK)) {
                this.aK = this.ch;
            }
        }
        
        if (this.aN() != this.ch || this.ci != this.bB.a() || this.bB.e() == 0.0F != this.cj) {
            this.a.b(new OPacket8UpdateHealth(this.aN(), this.bB.a(), this.bB.e()));
            this.ch = this.aN();
            this.ci = this.bB.a();
            this.cj = this.bB.e() == 0.0F;
        }
            
        // CanaryMod: Update experience
        if (this.cb != this.ck) {
            // updates your experience when it is changed.
            if (!etc.getInstance().isExpEnabled()) {
                this.cb = 0;
                this.ca = 0;
            } else if ((Boolean) manager.callHook(PluginLoader.Hook.EXPERIENCE_CHANGE, getPlayer(), this.ck, cb)) {
                this.cb = this.ck;
            }
        }
    
        if (this.cb != this.ck) {
            this.ck = this.cb;
            this.a.b(new OPacket43Experience(this.cc, this.cb, this.ca));
        }
    }

    public OItemStack b(int i) {
        return i == 0 ? this.by.g() : this.by.b[i - 1];
    }

    public void a(ODamageSource odamagesource) {
        if (etc.getInstance().deathMessages) {
            this.b.ab().a((OPacket) (new OPacket3Chat(odamagesource.b(this))));
        }
        this.by.m();
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.cl > 0) {
            return false;
        } else {
            if (!this.b.W() && odamagesource instanceof OEntityDamageSource) {
                OEntity oentity = odamagesource.g();

                if (oentity instanceof OEntityPlayer) {
                    return false;
                }

                if (oentity instanceof OEntityArrow) {
                    OEntityArrow oentityarrow = (OEntityArrow) oentity;

                    if (oentityarrow.c instanceof OEntityPlayer) {
                        return false;
                    }
                }
            }

            return super.a(odamagesource, i);
        }
    }

    protected boolean h() {
        return this.b.W();
    }

    public void c(int i) {
        if (this.bK == 1 && i == 1) {
            this.a((OStatBase) OAchievementList.C);
            this.p.e((OEntity) this);
            this.j = true;
            this.a.b(new OPacket70GameEvent(4, 0));
        } else {
            this.a((OStatBase) OAchievementList.B);
            OChunkCoordinates ochunkcoordinates = this.b.getWorld(this.p.name, i).k();

            if (ochunkcoordinates != null) {
                this.a.a((double) ochunkcoordinates.a, (double) ochunkcoordinates.b, (double) ochunkcoordinates.c, 0.0F, 0.0F);
            }

            this.b.ab().a(this, 1);
            this.ck = -1;
            this.ch = -1;
            this.ci = -1;
        }

    }

    private void a(OTileEntity otileentity) {
        if (otileentity != null) {
            // CanaryMod: Let plugins know we're showing a sign
            if (otileentity instanceof OTileEntitySign) {
                Sign sign = new Sign((OTileEntitySign) otileentity);

                manager.callHook(PluginLoader.Hook.SIGN_SHOW, getPlayer(), sign);
            }
            OPacket opacket = otileentity.e();

            if (opacket != null) {
                this.a.b(opacket);
            }
        }
    }

    public void a(OEntity oentity, int i) {
        if (!oentity.L) {
            OEntityTracker oentitytracker = this.q().o();

            if (oentity instanceof OEntityItem) {
                oentitytracker.a(oentity, new OPacket22Collect(oentity.k, this.k));
            }

            if (oentity instanceof OEntityArrow) {
                oentitytracker.a(oentity, new OPacket22Collect(oentity.k, this.k));
            }

            if (oentity instanceof OEntityXPOrb) {
                oentitytracker.a(oentity, new OPacket22Collect(oentity.k, this.k));
            }
        }

        super.a(oentity, i);
        this.bA.b();
    }

    public void i() {
        if (!this.bH) {
            this.bI = -1;
            this.bH = true;
            this.q().o().a(this, new OPacket18Animation(this, 1));
        }
    }

    public OEnumStatus a(int i, int j, int k) {
        OEnumStatus oenumstatus = super.a(i, j, k);

        if (oenumstatus == OEnumStatus.a) {
            OPacket17Sleep opacket17sleep = new OPacket17Sleep(this, 0, i, j, k);
            
            this.q().o().a(this, opacket17sleep);
            this.a.a(this.t, this.u, this.v, this.z, this.A);
            this.a.b(opacket17sleep);
        }

        return oenumstatus;
    }

    public void a(boolean flag, boolean flag1, boolean flag2) {
        if (this.bn()) {
            this.q().o().b(this, new OPacket18Animation(this, 3));
        }

        super.a(flag, flag1, flag2);
        if (this.a != null) {
            this.a.a(this.t, this.u, this.v, this.z, this.A);
        }
    }

    public void a(OEntity oentity) {
        super.a(oentity);
        this.a.b(new OPacket39AttachEntity(this, this.o));
        this.a.a(this.t, this.u, this.v, this.z, this.A);
    }

    protected void a(double d0, boolean flag) {}

    public void b(double d0, boolean flag) {
        super.a(d0, flag);
    }

    private void bO() {
        this.cq = this.cq % 100 + 1;
    }

    public void b(int i, int j, int k) {
        // CanaryMod: Check if we can open this
        OContainerWorkbench container = new OContainerWorkbench(this.by, this.p, i, j, k);
        Inventory inv = new Workbench(container);

        container.setInventory(inv);
        String name = "Crafting";
        
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }
        
        if (inv != null) {
            name = inv.getName();
        }
    
        this.bO();
        this.a.b(new OPacket100OpenWindow(this.cq, 1, name, 9));
        this.bA = container;
        this.bA.c = this.cq;
        this.bA.a((OICrafting) this);
    }

    public void c(int i, int j, int k) {
        // CanaryMod: Check if we can open this
        OContainerEnchantment container = new OContainerEnchantment(this.by, this.p, i, j, k);
        Inventory inv = new EnchantmentTable(container);

        container.setInventory(inv);
        String name = "Enchant";
        
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }
        
        if (inv != null) {
            name = inv.getName();
        }
        
        this.bO();
        this.a.b(new OPacket100OpenWindow(this.cq, 4, name, 9));
        this.bA = container;
        this.bA.c = this.cq;
        this.bA.a((OICrafting) this);
    }

    public void a(OIInventory oiinventory) {
        // CanaryMod: Check if we can open this
        Inventory inv = null;
        String name = oiinventory.b();

        HookParametersOpenInventory openInventoryParameters = null;

        if (oiinventory instanceof OTileEntityChest) {
            inv = new Chest((OTileEntityChest) oiinventory);
            openInventoryParameters = new HookParametersOpenInventory(getPlayer(), inv, false);
            if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, openInventoryParameters)) {
                return;
            }
        } else if (oiinventory instanceof OInventoryLargeChest) {
            inv = new DoubleChest((OInventoryLargeChest) oiinventory);
            openInventoryParameters = new HookParametersOpenInventory(getPlayer(), inv, false);
            if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, openInventoryParameters)) {
                return;
            }
        }

        if (inv != null) {
            name = inv.getName();
        }

        if (this.bA != this.bz) {
            this.j();
        }

        this.bO();
        this.a.b(new OPacket100OpenWindow(this.cq, 0, name, oiinventory.i_()));
        // CanaryMod: Check if openend the chest in silence mode.
        this.bA = new OContainerChest(this.by, oiinventory, (openInventoryParameters == null) ? false : openInventoryParameters.isSilenced());
        this.bA.setInventory(inv);
        // CanaryMod end
        this.bA.c = this.cq;
        this.bA.a((OICrafting) this);
    }

    public void a(OTileEntityFurnace otileentityfurnace) {
        // CanaryMod: Check if we can open this
        Inventory inv = new Furnace(otileentityfurnace);
        String name = otileentityfurnace.getName();
        
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }
        
        if (inv != null) {
            name = inv.getName();
        }
        
        this.bO();
        this.a.b(new OPacket100OpenWindow(this.cq, 2, name, otileentityfurnace.i_()));
        this.bA = new OContainerFurnace(this.by, otileentityfurnace);
        // CanaryMod: Set the inventory for the GUI
        this.bA.setInventory(inv);
        this.bA.c = this.cq;
        this.bA.a((OICrafting) this);
    }

    public void a(OTileEntityDispenser otileentitydispenser) {
        // CanaryMod: Check if we can open this
        Inventory inv = new Dispenser(otileentitydispenser);
        String name = otileentitydispenser.getName();
        
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }
        
        if (inv != null) {
            name = inv.getName();
        }
        
        this.bO();
        this.a.b(new OPacket100OpenWindow(this.cq, 3, name, otileentitydispenser.i_()));
        this.bA = new OContainerDispenser(this.by, otileentitydispenser);
        // CanaryMod: Set the inventory for the GUI
        this.bA.setInventory(inv);
        this.bA.c = this.cq;
        this.bA.a((OICrafting) this);
    }

    public void a(OTileEntityBrewingStand otileentitybrewingstand) {
        // CanaryMod: Check if we can open this
        Inventory inv = new BrewingStand(otileentitybrewingstand);
        String name = otileentitybrewingstand.getName();
        
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }
        
        if (inv != null) {
            name = inv.getName();
        }
        
        this.bO();
        this.a.b(new OPacket100OpenWindow(this.cq, 5, name, otileentitybrewingstand.i_()));
        this.bA = new OContainerBrewingStand(this.by, otileentitybrewingstand);
        // CanaryMod: Set the inventory for the GUI
        this.bA.setInventory(inv);
        this.bA.c = this.cq;
        this.bA.a((OICrafting) this);
    }

    public void a(OIMerchant oimerchant) {
        this.bO();
        this.bA = new OContainerMerchant(this.by, oimerchant, this.p);
        this.bA.c = this.cq;
        this.bA.a((OICrafting) this);
        OInventoryMerchant oinventorymerchant = ((OContainerMerchant) this.bA).d();

        this.a.b(new OPacket100OpenWindow(this.cq, 6, oinventorymerchant.b(), oinventorymerchant.i_()));
        OMerchantRecipeList omerchantrecipelist = oimerchant.b(this);

        if (omerchantrecipelist != null) {
            try {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);

                dataoutputstream.writeInt(this.cq);
                omerchantrecipelist.a(dataoutputstream);
                this.a.b(new OPacket250CustomPayload("MC|TrList", bytearrayoutputstream.toByteArray()));
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    public void a(OContainer ocontainer, int i, OItemStack oitemstack) {
        if (!(ocontainer.a(i) instanceof OSlotCrafting)) {
            if (!this.h) {
                this.a.b(new OPacket103SetSlot(ocontainer.c, i, oitemstack));
            }
        }
    }

    public void a(OContainer ocontainer) {
        this.a(ocontainer, ocontainer.a());
    }

    public void a(OContainer ocontainer, List list) {
        this.a.b(new OPacket104WindowItems(ocontainer.c, list));
        this.a.b(new OPacket103SetSlot(-1, -1, this.by.o()));
    }

    public void a(OContainer ocontainer, int i, int j) {
        this.a.b(new OPacket105UpdateProgressbar(ocontainer.c, i, j));
    }

    public void j() {
        this.a.b(new OPacket101CloseWindow(this.bA.c));
        this.l();
    }

    public void k() {
        if (!this.h) {
            this.a.b(new OPacket103SetSlot(-1, -1, this.by.o()));
        }
    }

    public void l() {
        this.bA.a((OEntityPlayer) this);
        this.bA = this.bz;
    }

    public void a(OStatBase ostatbase, int i) {
        if (ostatbase != null) {
            if (!ostatbase.f) {
                while (i > 100) {
                    this.a.b(new OPacket200Statistic(ostatbase.e, 100));
                    i -= 100;
                }

                this.a.b(new OPacket200Statistic(ostatbase.e, i));
            }
        }
    }

    public void m() {
        if (this.o != null) {
            this.a(this.o);
        }

        if (this.n != null) {
            this.n.a((OEntity) this);
        }

        if (this.bS) {
            this.a(true, false, false);
        }
    }

    public void n() {
        this.ch = -99999999;
    }

    public void c(String s) {
        OStringTranslate ostringtranslate = OStringTranslate.a();
        String s1 = ostringtranslate.b(s);

        this.a.b(new OPacket3Chat(s1));
    }

    protected void o() {
        this.a.b(new OPacket38EntityStatus(this.k, (byte) 9));
        super.o();
    }

    public void a(OItemStack oitemstack, int i) {
        super.a(oitemstack, i);
        // CanaryMod: Call EAT Hook
        if (oitemstack != null && oitemstack.b() != null && oitemstack.b().b(oitemstack) == OEnumAction.b) {
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.EAT, ((OEntityPlayerMP) this).getPlayer(), new Item(oitemstack))) {
                super.a(oitemstack, i);
                this.q().o().b(this, new OPacket18Animation(this, 5));
            } else {
                this.a.b((OPacket) (new OPacket38EntityStatus(this.an, (byte) 9)));
                this.getPlayer().updateLevels();
                this.getPlayer().updateInventory();
            }
        } else { // CanaryMod: Bow, or block action
            super.a(oitemstack, i);
        }

    }

    protected void a(OPotionEffect opotioneffect) {
        super.a(opotioneffect);
        this.a.b(new OPacket41EntityEffect(this.k, opotioneffect));
    }

    protected void b(OPotionEffect opotioneffect) {
        super.b(opotioneffect);
        this.a.b(new OPacket41EntityEffect(this.k, opotioneffect));
    }

    protected void c(OPotionEffect opotioneffect) {
        super.c(opotioneffect);
        this.a.b(new OPacket42RemoveEntityEffect(this.k, opotioneffect));
    }

    public void a(double d0, double d1, double d2) {
        this.a.a(d0, d1, d2, this.z, this.A);
    }

    public void b(OEntity oentity) {
        this.q().o().b(this, new OPacket18Animation(oentity, 6));
    }

    public void c(OEntity oentity) {
        this.q().o().b(this, new OPacket18Animation(oentity, 7));
    }

    public void p() {
        if (this.a != null) {
            this.a.b(new OPacket202PlayerAbilities(this.bZ));
        }
    }

    public OWorldServer q() {
        return (OWorldServer) this.p;
    }

    public void a(OEnumGameType oenumgametype) {
        this.c.a(oenumgametype);
        this.a.b(new OPacket70GameEvent(3, oenumgametype.a()));
    }
    
    public void a(String s) {
        this.a.b(new OPacket3Chat(s));
    }

    public boolean b(String s) {
        // TODO: check this.
        return "seed".equals(s) && !this.b.S() ? true : (!"tell".equals(s) && !"help".equals(s) && !"me".equals(s) ? this.b.ab().e(this.bJ) : true);
    }

    public String r() {
        String s = this.a.b.c().toString();

        s = s.substring(s.indexOf("/") + 1);
        s = s.substring(0, s.indexOf(":"));
        return s;
    }

    public void a(OPacket204ClientInfo opacket204clientinfo) {
        if (this.cg.b().containsKey(opacket204clientinfo.d())) {
            this.cg.a(opacket204clientinfo.d());
        }

        int i = 256 >> opacket204clientinfo.f();

        if (i > 3 && i < 15) {
            this.cm = i;
        }

        this.cn = opacket204clientinfo.g();
        this.co = opacket204clientinfo.h();
        if (this.b.H() && this.b.G().equals(this.bJ)) {
            this.b.c(opacket204clientinfo.i());
        }
    }

    public OStringTranslate t() {
        return this.cg;
    }

    public int v() {
        return this.cn;
    }

    public void a(String s, int i) {
        String s1 = s + "" + i;

        this.a.b(new OPacket250CustomPayload("MC|TPack", s1.getBytes()));
    }
    
    public Player getPlayer() {
        return player;
    }

    /**
     * Reloads the player
     */
    public void reloadPlayer() {
        player = etc.getDataSource().getPlayer(this.bJ);
        player.setUser(this);
    }
}
