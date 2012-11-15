import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class OEntityPlayerMP extends OEntityPlayer implements OICrafting {

    private OStringTranslate cj = new OStringTranslate("en_US");
    public ONetServerHandler a;
    public OMinecraftServer b;
    public OItemInWorldManager c;
    public double d;
    public double e;
    public final List f = new LinkedList();
    public final List g = new LinkedList();
    private int ck = -99999999; // CanaryMod: last health client was set to
    private int cl = -99999999;
    private boolean cm = true;
    private int cn = -99999999;
    private int co = 60;
    private int cp = 0;
    private int cq = 0;
    private boolean cr = true;
    private int cs = 0;
    public boolean h;
    public int i;
    public boolean j = false;
    // CanaryMod: Player storage
    private Player player;

    public OEntityPlayerMP(OMinecraftServer ominecraftserver, OWorld oworld, String s, OItemInWorldManager oiteminworldmanager) {
        super(oworld);
        oiteminworldmanager.b = this;
        this.c = oiteminworldmanager;
        this.cp = ominecraftserver.ad().o();
        OChunkCoordinates ochunkcoordinates = oworld.H();
        int i = ochunkcoordinates.a;
        int j = ochunkcoordinates.c;
        int k = ochunkcoordinates.b;

        if (!oworld.v.f && oworld.K().r() != OEnumGameType.d) {
            int l = Math.max(5, ominecraftserver.ak() - 6);

            i += this.aa.nextInt(l * 2) - l;
            j += this.aa.nextInt(l * 2) - l;
            k = oworld.i(i, j);
        }

        this.b((double) i + 0.5D, (double) k, (double) j + 0.5D, 0.0F, 0.0F);
        this.b = ominecraftserver;
        this.X = 0.0F;
        this.bQ = s;
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
        this.cn = -1;
    }

    public void d_() {
        this.bK.a((OICrafting) this);
    }

    protected void e_() {
        this.M = 0.0F;
    }

    public float e() {
        return 1.62F;
    }

    public void j_() {
        this.c.a();
        --this.co;
        this.bK.b();

        while (!this.g.isEmpty()) {
            int i = Math.min(this.g.size(), 127);
            int[] aint = new int[i];
            Iterator iterator = this.g.iterator();
            int j = 0;

            while (iterator.hasNext() && j < i) {
                aint[j++] = ((Integer) iterator.next()).intValue();
                iterator.remove();
            }

            this.a.b(new OPacket29DestroyEntity(aint));
        }

        if (!this.f.isEmpty()) {
            ArrayList arraylist = new ArrayList();
            Iterator iterator1 = this.f.iterator();
            ArrayList arraylist1 = new ArrayList();

            while (iterator1.hasNext() && arraylist.size() < 5) {
                OChunkCoordIntPair ochunkcoordintpair = (OChunkCoordIntPair) iterator1.next();

                iterator1.remove();
                if (ochunkcoordintpair != null && this.p.f(ochunkcoordintpair.a << 4, 0, ochunkcoordintpair.b << 4)) {
                    arraylist.add(this.p.e(ochunkcoordintpair.a, ochunkcoordintpair.b));
                    arraylist1.addAll(((OWorldServer) this.p).b(ochunkcoordintpair.a * 16, 0, ochunkcoordintpair.b * 16, ochunkcoordintpair.a * 16 + 16, 256, ochunkcoordintpair.b * 16 + 16));
                }
            }

            if (!arraylist.isEmpty()) {
                this.a.b(new OPacket56MapChunks(arraylist));
                Iterator iterator2 = arraylist1.iterator();

                while (iterator2.hasNext()) {
                    OTileEntity otileentity = (OTileEntity) iterator2.next();

                    this.b(otileentity);
                }

                iterator2 = arraylist.iterator();

                while (iterator2.hasNext()) {
                    OChunk ochunk = (OChunk) iterator2.next();

                    this.p().p().a(this, ochunk);
                }
            }
        }
    }

    public void g() {
        super.j_();

        for (int i = 0; i < this.bI.k_(); ++i) {
            OItemStack oitemstack = this.bI.a(i);

            if (oitemstack != null && OItem.e[oitemstack.c].f() && this.a.e() <= 5) {
                OPacket opacket = ((OItemMapBase) OItem.e[oitemstack.c]).c(oitemstack, this.p, this);

                if (opacket != null) {
                    this.a.b(opacket);
                }
            }
        }

        // CanaryMod: Update the health
        if (this.aU() != this.ck) {
            // updates your health when it is changed.
            if (!etc.getInstance().isHealthEnabled()) {
                this.j(this.aT());
                this.L = false;
            } else if ((Boolean) manager.callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), ck, this.aU())) {
                this.j(this.ck);
            }
        }

        if (this.aU() != this.ck || this.cl != this.bL.a() || this.bL.e() == 0.0F != this.cm) {
            //CanaryMod: convert health for values above 20
            int health = (int)(this.aU() / (this.aT() / 20));
            health = (this.aU() > 0 && health == 0) ? 1 : health;
            this.a.b(new OPacket8UpdateHealth(health, this.bL.a(), this.bL.e()));
            this.ck = this.aU();
            this.cl = this.bL.a();
            this.cm = this.bL.e() == 0.0F;
        }

        // CanaryMod: Update experience
        if (this.ce != this.cn) {
            // updates your experience when it is changed.
            if (!etc.getInstance().isExpEnabled()) {
                this.ce = 0;
                this.cd = 0;
            } else if ((Boolean) manager.callHook(PluginLoader.Hook.EXPERIENCE_CHANGE, getPlayer(), this.cq, ch)) {
                this.ce = this.cn;
            }
        }

        if (this.ce != this.cn) {
            this.cn = this.ce;
            this.a.b(new OPacket43Experience(this.cf, this.ce, this.cd));
        }
    }

    public void a(ODamageSource odamagesource) {
        manager.callHook(PluginLoader.Hook.DEATH, this.getEntity());
        this.b.ad().a((OPacket) (new OPacket3Chat(odamagesource.b(this))));
        if (!this.p.L().b("keepInventory")) {
            this.bI.l();
        }
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.ar()) {
            return false;
        } else {
            boolean flag = this.b.T() && this.b.X() && "fall".equals(odamagesource.q);

            if (!flag && this.co > 0) {
                return false;
            } else {
                if (!this.b.X() && odamagesource instanceof OEntityDamageSource) {
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
    }

    protected boolean h() {
        return this.b.X();
    }

    public void b(int i) {
        if (this.ap == 1 && i == 1) {
            this.a((OStatBase) OAchievementList.C);
            this.p.e((OEntity) this);
            this.j = true;
            this.a.b(new OPacket70GameEvent(4, 0));
        } else {
            if (this.ap == 1 && i == 0) {
                this.a((OStatBase) OAchievementList.B);
                OChunkCoordinates ochunkcoordinates = this.b.getWorld(this.p.name, i).l();

                if (ochunkcoordinates != null) {
                    this.a.a((double) ochunkcoordinates.a, (double) ochunkcoordinates.b, (double) ochunkcoordinates.c, 0.0F, 0.0F);
                }

                i = 1;
            } else {
                this.a((OStatBase) OAchievementList.x);
            }

            // CanaryMod onPortalUse
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PORTAL_USE, player, player.getWorld())) {
                this.b.ad().a(this, i);
                this.cn = -1;
                this.ck = -1;
                this.cl = -1;
            } //
        }
    }

    private void b(OTileEntity otileentity) {
        if (otileentity != null) {
            // CanaryMod: Let plugins know we're showing a sign
            if (otileentity instanceof OTileEntitySign) {
                Sign sign = new Sign((OTileEntitySign) otileentity);

                manager.callHook(PluginLoader.Hook.SIGN_SHOW, getPlayer(), sign);
            }
            OPacket opacket = otileentity.l();

            if (opacket != null) {
                this.a.b(opacket);
            }
        }
    }

    public void a(OEntity oentity, int i) {
        super.a(oentity, i);
        this.bK.b();
    }

    public OEnumStatus a(int i, int j, int k) {
        OEnumStatus oenumstatus = super.a(i, j, k);

        if (oenumstatus == OEnumStatus.a) {
            OPacket17Sleep opacket17sleep = new OPacket17Sleep(this, 0, i, j, k);

            this.p().p().a((OEntity) this, (OPacket) opacket17sleep);
            this.a.a(this.t, this.u, this.v, this.z, this.A);
            this.a.b(opacket17sleep);
        }

        return oenumstatus;
    }

    public void a(boolean flag, boolean flag1, boolean flag2) {
        if (this.bw()) {
            this.p().p().b(this, new OPacket18Animation(this, 3));
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

    private void ch() {
        this.cs = this.cs % 100 + 1;
    }

    public void b(int i, int j, int k) {
        // CanaryMod: Check if we can open this
        OContainerWorkbench container = new OContainerWorkbench(this.bI, this.p, i, j, k);
        Inventory inv = new Workbench(container);

        container.setInventory(inv);
        String name = "Crafting";

        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }

        if (inv != null) {
            name = inv.getName();
        }
        //CanaryMod: end
        this.ch();
        this.a.b(new OPacket100OpenWindow(this.cs, 1, name, 9)); //CanaryMod: "Crafting" to name
        this.bK = container;
        this.bK.d = this.cs;
        this.bK.a((OICrafting) this);
    }

    public void c(int i, int j, int k) {
        // CanaryMod: Check if we can open this
        OContainerEnchantment container = new OContainerEnchantment(this.bI, this.p, i, j, k);
        Inventory inv = new EnchantmentTable(container);

        container.setInventory(inv);
        String name = "Enchant";

        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }

        if (inv != null) {
            name = inv.getName();
        }

        this.ch();
        this.a.b(new OPacket100OpenWindow(this.cs, 4, name, 9)); //CanaryMod: "Enchanting" to name
        this.bK = container;
        this.bK.d = this.cs;
        this.bK.a((OICrafting) this);
    }

    public void d(int i, int j, int k) {
        // CanaryMod: Check if we can open this
        OContainerRepair container = new OContainerRepair(this.bI, this.p, i, j, k, this);
        Inventory inv = new Anvil(container);

        container.setInventory(inv);
        String name = "Repairing";

        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }

        if (inv != null) {
            name = inv.getName();
        }

        this.ch();
        this.a.b(new OPacket100OpenWindow(this.cs, 8, name, 9));
        this.bK = container;
        this.bK.d = this.cs;
        this.bK.a((OICrafting) this);
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

        if (this.bK != this.bJ) {
            this.i();
        }

        this.ch();
        this.a.b(new OPacket100OpenWindow(this.cs, 0, name, oiinventory.k_()));
        // CanaryMod: Check if openend the chest in silence mode.
        this.bK = new OContainerChest(this.bI, oiinventory, (openInventoryParameters == null) ? false : openInventoryParameters.isSilenced());
        this.bK.setInventory(inv);
        // CanaryMod end
        this.bK.d = this.cs;
        this.bK.a((OICrafting) this);
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

        this.ch();
        this.a.b(new OPacket100OpenWindow(this.cs, 2, name, otileentityfurnace.k_()));
        this.bK = new OContainerFurnace(this.bI, otileentityfurnace);
        this.bK.setInventory(inv); // CanaryMod: Set the inventory for the GUI
        this.bK.d = this.cs;
        this.bK.a((OICrafting) this);
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

        this.ch();
        this.a.b(new OPacket100OpenWindow(this.cs, 3, name, otileentitydispenser.k_()));
        this.bK = new OContainerDispenser(this.bI, otileentitydispenser);
        this.bK.setInventory(inv); // CanaryMod: set inventory for the GUI
        this.bK.d = this.cs;
        this.bK.a((OICrafting) this);
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

        this.ch();
        this.a.b(new OPacket100OpenWindow(this.cs, 5, name, otileentitybrewingstand.k_()));
        this.bK = new OContainerBrewingStand(this.bI, otileentitybrewingstand);
        this.bK.setInventory(inv); // CanaryMod: set inventory for the GUI
        this.bK.d = this.cs;
        this.bK.a((OICrafting) this);
    }

    public void a(OTileEntityBeacon otileentitybeacon) {
        // TODO add this stuff to Canary --somners
        this.ch();
        this.a.b(new OPacket100OpenWindow(this.cs, 7, otileentitybeacon.b(), otileentitybeacon.k_()));
        this.bK = new OContainerBeacon(this.bI, otileentitybeacon);
        this.bK.d = this.cs;
        this.bK.a((OICrafting) this);
    }

    public void a(OIMerchant oimerchant) {
        this.ch();
        this.bK = new OContainerMerchant(this.bI, oimerchant, this.p);
        this.bK.d = this.cs;
        this.bK.a((OICrafting) this);
        OInventoryMerchant oinventorymerchant = ((OContainerMerchant) this.bK).d();

        this.a.b(new OPacket100OpenWindow(this.cs, 6, oinventorymerchant.b(), oinventorymerchant.k_()));
        OMerchantRecipeList omerchantrecipelist = oimerchant.b(this);

        if (omerchantrecipelist != null) {
            try {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);

                dataoutputstream.writeInt(this.cs);
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
                this.a.b(new OPacket103SetSlot(ocontainer.d, i, oitemstack));
            }
        }
    }

    public void a(OContainer ocontainer) {
        this.a(ocontainer, ocontainer.a());
    }

    public void a(OContainer ocontainer, List list) {
        this.a.b(new OPacket104WindowItems(ocontainer.d, list));
        this.a.b(new OPacket103SetSlot(-1, -1, this.bI.n()));
    }

    public void a(OContainer ocontainer, int i, int j) {
        this.a.b(new OPacket105UpdateProgressbar(ocontainer.d, i, j));
    }

    public void i() {
        this.a.b(new OPacket101CloseWindow(this.bK.d));
        this.k();
    }

    public void j() {
        if (!this.h) {
            this.a.b(new OPacket103SetSlot(-1, -1, this.bI.n()));
        }
    }

    public void k() {
        this.bK.b(this);
        this.bK = this.bJ;
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

    public void l() {
        if (this.o != null) {
            this.a(this.o);
        }

        if (this.n != null) {
            this.n.a((OEntity) this);
        }

        if (this.bY) {
            this.a(true, false, false);
        }
    }

    public void m() {
        this.ck = -99999999;
    }

    public void b(String s) {
        OStringTranslate ostringtranslate = OStringTranslate.a();
        String s1 = ostringtranslate.b(s);

        this.a.b(new OPacket3Chat(s1));
    }

    protected void n() {
        this.a.b(new OPacket38EntityStatus(this.k, (byte) 9));
        super.n();
    }

    public void a(OItemStack oitemstack, int i) {
        super.a(oitemstack, i);
        if (oitemstack != null && oitemstack.b() != null && oitemstack.b().d_(oitemstack) == OEnumAction.b) {
            // CanaryMod: Call EAT Hook
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.EAT, this.getPlayer(), new Item(oitemstack))) {
                super.a(oitemstack, i);
                this.p().p().b(this, new OPacket18Animation(this, 5));
            } else {
                this.a.b((OPacket) (new OPacket38EntityStatus(this.k, (byte) 9)));
                this.getPlayer().updateLevels();
                this.getPlayer().updateInventory();
            }
        } else { // CanaryMod: Bow, or block action
            super.a(oitemstack, i);
        }
    }

    public void a(OEntityPlayer oentityplayer, boolean flag) {
        super.a(oentityplayer, flag);
        this.cn = -1;
        this.ck = -1;
        this.cl = -1;
        this.g.addAll(((OEntityPlayerMP) oentityplayer).g);
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
        this.p().p().b(this, new OPacket18Animation(oentity, 6));
    }

    public void c(OEntity oentity) {
        this.p().p().b(this, new OPacket18Animation(oentity, 7));
    }

    public void o() {
        if (this.a != null) {
            this.a.b(new OPacket202PlayerAbilities(this.cc));
        }
    }

    public OWorldServer p() {
        return (OWorldServer) this.p;
    }

    public void a(OEnumGameType oenumgametype) {
        this.c.a(oenumgametype);
        this.a.b(new OPacket70GameEvent(3, oenumgametype.a()));
    }

    public void a(String s) {
        this.a.b(new OPacket3Chat(s));
    }

    public boolean a(int i, String s) {
        return "seed".equals(s) && !this.b.T() ? true : (!"tell".equals(s) && !"help".equals(s) && !"me".equals(s) ? this.b.ad().e(this.bQ) : true);
    }

    public String q() {
        String s = this.a.b.c().toString();

        s = s.substring(s.indexOf("/") + 1);
        s = s.substring(0, s.indexOf(":"));
        return s;
    }

    public void a(OPacket204ClientInfo opacket204clientinfo) {
        if (this.cj.b().containsKey(opacket204clientinfo.d())) {
            this.cj.a(opacket204clientinfo.d());
        }

        int i = 256 >> opacket204clientinfo.f();

        if (i > 3 && i < 15) {
            this.cp = i;
        }

        this.cq = opacket204clientinfo.g();
        this.cr = opacket204clientinfo.h();
        if (this.b.I() && this.b.H().equals(this.bQ)) {
            this.b.c(opacket204clientinfo.i());
        }

        this.b(1, !opacket204clientinfo.j());
    }

    public OStringTranslate s() {
        return this.cj;
    }

    public int u() {
        return this.cq;
    }

    public void a(String s, int i) {
        String s1 = s + "\u0000" + i;

        this.a.b(new OPacket250CustomPayload("MC|TPack", s1.getBytes()));
    }

    public OChunkCoordinates b() {
        return new OChunkCoordinates(OMathHelper.c(this.t), OMathHelper.c(this.u + 0.5D), OMathHelper.c(this.v));
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public Player getEntity() {
        return player;
    }

    /**
     * Reloads the player
     */
    public void reloadPlayer() {
        player = etc.getDataSource().getPlayer(this.bQ);
        player.setUser(this);
    }
}
