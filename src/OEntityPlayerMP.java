import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class OEntityPlayerMP extends OEntityPlayer implements OICrafting {

    public ONetServerHandler a;
    public OMinecraftServer b;
    public OItemInWorldManager c;
    public double d;
    public double e;
    public List f = new LinkedList();
    public Set g = new HashSet();
    private int cf = -99999999;
    private int cg = -99999999;
    private boolean ch = true;
    private int ci = -99999999;
    private int cj = 60;
    private OItemStack[] ck = new OItemStack[] { null, null, null, null, null};
    private int cl = 0;
    public boolean h;
    public int i;
    public boolean j = false;
    // CanaryMod: Player storage
    private Player player;

    public OEntityPlayerMP(OMinecraftServer ominecraftserver, OWorld oworld, String s, OItemInWorldManager oiteminworldmanager) {
        super(oworld);
        oiteminworldmanager.b = this;
        this.c = oiteminworldmanager;
        OChunkCoordinates ochunkcoordinates = oworld.p();
        int i = ochunkcoordinates.a;
        int j = ochunkcoordinates.c;
        int k = ochunkcoordinates.b;

        if (!oworld.t.e) {
            i += this.bS.nextInt(20) - 10;
            k = oworld.g(i, j);
            j += this.bS.nextInt(20) - 10;
        }

        this.c((double) i + 0.5D, (double) k, (double) j + 0.5D, 0.0F, 0.0F);
        this.b = ominecraftserver;
        this.bP = 0.0F;
        this.v = s;
        this.bF = 0.0F;
        
        // CanaryMod: Store player
        player = etc.getDataSource().getPlayer(s);
        player.setUser(this);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        if (onbttagcompound.c("playerGameType")) {
            this.c.a(onbttagcompound.f("playerGameType"));
        }

    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("playerGameType", this.c.a());
    }

    public void a(OWorld oworld) {
        super.a(oworld);
    }

    public void e_(int i) {
        super.e_(i);
        this.ci = -1;
    }

    public void x() {
        this.m.a((OICrafting) this);
    }

    public OItemStack[] y() {
        return this.ck;
    }

    protected void A() {
        this.bF = 0.0F;
    }

    public float B() {
        return 1.62F;
    }

    public void F_() {
        this.c.c();
        --this.cj;
        this.m.a();

        for (int i = 0; i < 5; ++i) {
            OItemStack oitemstack = this.c(i);

            if (oitemstack != this.ck[i]) {
                this.bi.getEntityTracker().sendPacketToPlayersAndEntity(this, new OPacket5PlayerInventory(this.bd, i, oitemstack));
                this.ck[i] = oitemstack;
            }
        }

    }

    public OItemStack c(int i) {
        return i == 0 ? this.k.d() : this.k.b[i - 1];
    }

    public void a(ODamageSource odamagesource) {
        if (etc.getInstance().deathMessages) {
            this.b.h.a((OPacket) (new OPacket3Chat(odamagesource.a((OEntityPlayer) this))));
        }
        this.k.k();
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.cj > 0) {
            return false;
        } else {
            if (!this.b.q && odamagesource instanceof OEntityDamageSource) {
                OEntity oentity = odamagesource.a();

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

    protected boolean C() {
        return this.b.q;
    }

    public void d(int i) {
        super.d(i);
    }

    public void a(boolean flag) {
        super.F_();

        for (int i = 0; i < this.k.c(); ++i) {
            OItemStack oitemstack = this.k.g_(i);

            if (oitemstack != null && OItem.d[oitemstack.c].t_() && this.a.b() <= 2) {
                OPacket opacket = ((OItemMapBase) OItem.d[oitemstack.c]).c(oitemstack, this.bi, this);

                if (opacket != null) {
                    this.a.b(opacket);
                }
            }
        }

        if (flag && !this.f.isEmpty()) {
            OChunkCoordIntPair ochunkcoordintpair = (OChunkCoordIntPair) this.f.get(0);
            double d0 = ochunkcoordintpair.a(this);

            for (int j = 0; j < this.f.size(); ++j) {
                OChunkCoordIntPair ochunkcoordintpair1 = (OChunkCoordIntPair) this.f.get(j);
                double d1 = ochunkcoordintpair1.a(this);

                if (d1 < d0) {
                    ochunkcoordintpair = ochunkcoordintpair1;
                    d0 = d1;
                }
            }

            if (ochunkcoordintpair != null) {
                boolean flag1 = false;

                if (this.a.b() < 4) {
                    flag1 = true;
                }

                if (flag1) {
                    OWorldServer oworldserver = this.b.getWorld(this.bi.name, this.w);

                    if (oworldserver.i(ochunkcoordintpair.a << 4, 0, ochunkcoordintpair.b << 4)) {
                        OChunk ochunk = oworldserver.d(ochunkcoordintpair.a, ochunkcoordintpair.b);

                        if (ochunk.k) {
                            this.f.remove(ochunkcoordintpair);
                            this.a.b((OPacket) (new OPacket51MapChunk(oworldserver.d(ochunkcoordintpair.a, ochunkcoordintpair.b), true, 0)));
                            List list = oworldserver.c(ochunkcoordintpair.a * 16, 0, ochunkcoordintpair.b * 16, ochunkcoordintpair.a * 16 + 16, 256, ochunkcoordintpair.b * 16 + 16);

                            for (int k = 0; k < list.size(); ++k) {
                                this.a((OTileEntity) list.get(k));
                            }
                        }
                    }
                }
            }
        }

        if (this.J) {
            if (this.b.d.a("allow-nether", true)) {
                if (this.m != this.l) {
                    this.F();
                }

                if (this.bh != null) {
                    this.b(this.bh);
                } else {
                    this.K += 0.0125F;
                    if (this.K >= 1.0F) {
                        this.K = 1.0F;
                        this.I = 10;
                        boolean flag2 = false;
                        byte b0;

                        if (this.w == -1) {
                            b0 = 0;
                        } else {
                            b0 = -1;
                        }
                        
                        // CanaryMod onPortalUse
                        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PORTAL_USE, player, player.getWorld())) {
                            this.b.h.a(this, b0);
                            this.ci = -1;
                            this.cf = -1;
                            this.cg = -1;
                            this.a((OStatBase) OAchievementList.x);
                        }
                    }
                }

                this.J = false;
            }
        } else {
            if (this.K > 0.0F) {
                this.K -= 0.05F;
            }

            if (this.K < 0.0F) {
                this.K = 0.0F;
            }
        }

        if (this.I > 0) {
            --this.I;
        }

        // CanaryMod: Update the health
        if (this.ap != this.cf) {
            // updates your health when it is changed.
            if (!etc.getInstance().isHealthEnabled()) {
                ap = 20;
                az = false;
            } else if ((Boolean) manager.callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), cf, ap)) {
                ap = cf;
            }
        }
        
        if (this.aD() != this.cf || this.cg != this.n.a() || this.n.c() == 0.0F != this.ch) {
            this.a.b((OPacket) (new OPacket8UpdateHealth(this.aD(), this.n.a(), this.n.c())));
            this.cf = this.aD();
            this.cg = this.n.a();
            this.ch = this.n.c() == 0.0F;
        }
            
        // CanaryMod: Update experience
        if (this.N != this.ci) {
            // updates your experience when it is changed.
            if (!etc.getInstance().isExpEnabled()) {
                N = 0;
                M = 0;
            } else if ((Boolean) manager.callHook(PluginLoader.Hook.EXPERIENCE_CHANGE, getPlayer(), ci, N)) {
                N = ci;
            }
        }
    
        if (this.N != this.ci) {
            this.ci = this.N;
            this.a.b((OPacket) (new OPacket43Experience(this.O, this.N, this.M)));
        }

    }

    public void e(int i) {
        if (this.w == 1 && i == 1) {
            this.a((OStatBase) OAchievementList.C);
            this.bi.e(this);
            this.j = true;
            this.a.b((OPacket) (new OPacket70Bed(4, 0)));
        } else {
            this.a((OStatBase) OAchievementList.B);
            OChunkCoordinates ochunkcoordinates = this.b.getWorld(this.bi.name, i).d();

            if (ochunkcoordinates != null) {
                this.a.a((double) ochunkcoordinates.a, (double) ochunkcoordinates.b, (double) ochunkcoordinates.c, 0.0F, 0.0F, this.w, this.bi.name);
            }

            this.b.h.a(this, 1);
            this.ci = -1;
            this.cf = -1;
            this.cg = -1;
        }

    }

    private void a(OTileEntity otileentity) {
        if (otileentity != null) {
            // CanaryMod: Let plugins know we're showing a sign
            if (otileentity instanceof OTileEntitySign) {
                Sign sign = new Sign((OTileEntitySign) otileentity);

                manager.callHook(PluginLoader.Hook.SIGN_SHOW, getPlayer(), sign);
            }
            OPacket opacket = otileentity.d();

            if (opacket != null) {
                this.a.b(opacket);
            }
        }

    }

    public void a(OEntity oentity, int i) {
        if (!oentity.bE) {
            OEntityTracker oentitytracker = this.bi.getEntityTracker().getTracker();

            if (oentity instanceof OEntityItem) {
                oentitytracker.a(oentity, new OPacket22Collect(oentity.bd, this.bd));
            }

            if (oentity instanceof OEntityArrow) {
                oentitytracker.a(oentity, new OPacket22Collect(oentity.bd, this.bd));
            }

            if (oentity instanceof OEntityXPOrb) {
                oentitytracker.a(oentity, new OPacket22Collect(oentity.bd, this.bd));
            }
        }

        super.a(oentity, i);
        this.m.a();
    }

    public void C_() {
        if (!this.t) {
            this.u = -1;
            this.t = true;
            OEntityTracker oentitytracker = this.bi.getEntityTracker().getTracker();

            oentitytracker.a(this, new OPacket18Animation(this, 1));
        }

    }

    public void E() {}

    public OEnumStatus a(int i, int j, int k) {
        OEnumStatus oenumstatus = super.a(i, j, k);

        if (oenumstatus == OEnumStatus.a) {
            OEntityTracker oentitytracker = this.bi.getEntityTracker().getTracker();
            OPacket17Sleep opacket17sleep = new OPacket17Sleep(this, 0, i, j, k);

            oentitytracker.a(this, opacket17sleep);
            this.a.a(this.bm, this.bn, this.bo, this.bs, this.bt, this.w, this.bi.name);
            this.a.b((OPacket) opacket17sleep);
        }

        return oenumstatus;
    }

    public void a(boolean flag, boolean flag1, boolean flag2) {
        if (this.Z()) {
            OEntityTracker oentitytracker = this.bi.getEntityTracker().getTracker();

            oentitytracker.b(this, new OPacket18Animation(this, 3));
        }

        super.a(flag, flag1, flag2);
        if (this.a != null) {
            this.a.a(this.bm, this.bn, this.bo, this.bs, this.bt, this.w, this.bi.name);
        }

    }

    public void b(OEntity oentity) {
        super.b(oentity);
        this.a.b((OPacket) (new OPacket39AttachEntity(this, this.bh)));
        this.a.a(this.bm, this.bn, this.bo, this.bs, this.bt, this.w, this.bi.name);
    }

    protected void a(double d0, boolean flag) {}

    public void b(double d0, boolean flag) {
        super.a(d0, flag);
    }

    private void bc() {
        this.cl = this.cl % 100 + 1;
    }

    public void b(int i, int j, int k) {
        // CanaryMod: Check if we can open this
        OContainerWorkbench container = new OContainerWorkbench(this.k, this.bi, i, j, k);
        Inventory inv = new Workbench(container);

        container.setInventory(inv);
        String name = "Crafting";
        
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }
        
        if (inv != null) {
            name = inv.getName();
        }
    
        this.bc();
        this.a.b((OPacket) (new OPacket100OpenWindow(this.cl, 1, name, 9)));
        this.m = new OContainerWorkbench(this.k, this.bi, i, j, k);
        this.m.f = this.cl;
        this.m.a((OICrafting) this);
    }

    public void c(int i, int j, int k) {
        // CanaryMod: Check if we can open this
        OContainerEnchantment container = new OContainerEnchantment(this.k, this.bi, i, j, k);
        Inventory inv = new EnchantmentTable(container);

        container.setInventory(inv);
        String name = "Enchanting";
        
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }
        
        if (inv != null) {
            name = inv.getName();
        }
        
        this.bc();
        this.a.b((OPacket) (new OPacket100OpenWindow(this.cl, 4, name, 9)));
        this.m = new OContainerEnchantment(this.k, this.bi, i, j, k);
        this.m.f = this.cl;
        this.m.a((OICrafting) this);
    }

    public void a(OIInventory oiinventory) {
        // CanaryMod: Check if we can open this
        Inventory inv = null;
        String name = oiinventory.e();

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
        
        this.bc();
        this.a.b((OPacket) (new OPacket100OpenWindow(this.cl, 0, oiinventory.e(), oiinventory.c())));
        // CanaryMod: Check if openend the chest in silence mode.
        this.m = new OContainerChest(this.k, oiinventory, (openInventoryParameters == null) ? false : openInventoryParameters.isSilenced());
        this.m.setInventory(inv);
        // CanaryMod end
        this.m.f = this.cl;
        this.m.a((OICrafting) this);
    }

    public void a(OTileEntityFurnace otileentityfurnace) {
        // CanaryMod: Check if we can open this
        Inventory inv = new Furnace(otileentityfurnace);
        String name = otileentityfurnace.e();
        
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }
        
        if (inv != null) {
            name = inv.getName();
        }
        
        this.bc();
        this.a.b((OPacket) (new OPacket100OpenWindow(this.cl, 2, name, otileentityfurnace.c())));
        this.m = new OContainerFurnace(this.k, otileentityfurnace);
        // CanaryMod: Set the inventory for the GUI
        this.m.setInventory(inv);
        this.m.f = this.cl;
        this.m.a((OICrafting) this);
    }

    public void a(OTileEntityDispenser otileentitydispenser) {
        // CanaryMod: Check if we can open this
        Inventory inv = new Dispenser(otileentitydispenser);
        String name = otileentitydispenser.e();
        
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }
        
        if (inv != null) {
            name = inv.getName();
        }
        
        this.bc();
        this.a.b((OPacket) (new OPacket100OpenWindow(this.cl, 3, name, otileentitydispenser.c())));
        this.m = new OContainerDispenser(this.k, otileentitydispenser);
        // CanaryMod: Set the inventory for the GUI
        this.m.setInventory(inv);
        this.m.f = this.cl;
        this.m.a((OICrafting) this);
    }

    public void a(OTileEntityBrewingStand otileentitybrewingstand) {
        // CanaryMod: Check if we can open this
        Inventory inv = new BrewingStand(otileentitybrewingstand);
        String name = otileentitybrewingstand.e();
        
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }
        
        if (inv != null) {
            name = inv.getName();
        }
        
        this.bc();
        this.a.b((OPacket) (new OPacket100OpenWindow(this.cl, 5, name, otileentitybrewingstand.c())));
        this.m = new OContainerBrewingStand(this.k, otileentitybrewingstand);
        // CanaryMod: Set the inventory for the GUI
        this.m.setInventory(inv);
        this.m.f = this.cl;
        this.m.a((OICrafting) this);
    }

    public void a(OContainer ocontainer, int i, OItemStack oitemstack) {
        if (!(ocontainer.b(i) instanceof OSlotCrafting)) {
            if (!this.h) {
                this.a.b((OPacket) (new OPacket103SetSlot(ocontainer.f, i, oitemstack)));
            }
        }
    }

    public void a(OContainer ocontainer) {
        this.a(ocontainer, ocontainer.b());
    }

    public void a(OContainer ocontainer, List list) {
        this.a.b((OPacket) (new OPacket104WindowItems(ocontainer.f, list)));
        this.a.b((OPacket) (new OPacket103SetSlot(-1, -1, this.k.l())));
    }

    public void a(OContainer ocontainer, int i, int j) {
        this.a.b((OPacket) (new OPacket105UpdateProgressbar(ocontainer.f, i, j)));
    }

    public void a(OItemStack oitemstack) {}

    public void F() {
        this.a.b((OPacket) (new OPacket101CloseWindow(this.m.f)));
        this.H();
    }

    public void G() {
        if (!this.h) {
            this.a.b((OPacket) (new OPacket103SetSlot(-1, -1, this.k.l())));
        }
    }

    public void H() {
        this.m.a((OEntityPlayer) this);
        this.m = this.l;
    }

    public void a(OStatBase ostatbase, int i) {
        if (ostatbase != null) {
            if (!ostatbase.f) {
                while (i > 100) {
                    this.a.b((OPacket) (new OPacket200Statistic(ostatbase.e, 100)));
                    i -= 100;
                }

                this.a.b((OPacket) (new OPacket200Statistic(ostatbase.e, i)));
            }

        }
    }

    public void I() {
        if (this.bh != null) {
            this.b(this.bh);
        }

        if (this.bg != null) {
            this.bg.b((OEntity) this);
        }

        if (this.E) {
            this.a(true, false, false);
        }

    }

    public void D_() {
        this.cf = -99999999;
    }

    public void a(String s) {
        OStringTranslate ostringtranslate = OStringTranslate.a();
        String s1 = ostringtranslate.b(s);

        this.a.b((OPacket) (new OPacket3Chat(s1)));
    }

    protected void K() {
        this.a.b((OPacket) (new OPacket38EntityStatus(this.bd, (byte) 9)));
        super.K();
    }

    public void a(OItemStack oitemstack, int i) {
        super.a(oitemstack, i);
        // CanaryMod: Call EAT Hook
        if (oitemstack != null && oitemstack.a() != null && oitemstack.a().d(oitemstack) == OEnumAction.b) {
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.EAT, ((OEntityPlayerMP) this).getPlayer(), new Item(oitemstack))) {
                super.a(oitemstack, i);
                OEntityTracker oentitytracker = this.bi.getEntityTracker().getTracker();

                oentitytracker.b(this, new OPacket18Animation(this, 5));
            } else {
                this.a.b((OPacket) (new OPacket38EntityStatus(this.S, (byte) 9)));
                this.getPlayer().updateLevels();
                this.getPlayer().updateInventory();
            }
        } else { // CanaryMod: Bow, or block action
            super.a(oitemstack, i);
        }

    }

    protected void b(OPotionEffect opotioneffect) {
        super.b(opotioneffect);
        this.a.b((OPacket) (new OPacket41EntityEffect(this.bd, opotioneffect)));
    }

    protected void c(OPotionEffect opotioneffect) {
        super.c(opotioneffect);
        this.a.b((OPacket) (new OPacket41EntityEffect(this.bd, opotioneffect)));
    }

    protected void d(OPotionEffect opotioneffect) {
        super.d(opotioneffect);
        this.a.b((OPacket) (new OPacket42RemoveEntityEffect(this.bd, opotioneffect)));
    }

    public void a_(double d0, double d1, double d2) {
        this.a.a(d0, d1, d2, this.bs, this.bt, this.w, this.bi.name);
    }

    public void c(OEntity oentity) {
        OEntityTracker oentitytracker = this.bi.getEntityTracker().getTracker();

        oentitytracker.b(this, new OPacket18Animation(oentity, 6));
    }

    public void d(OEntity oentity) {
        OEntityTracker oentitytracker = this.bi.getEntityTracker().getTracker();

        oentitytracker.b(this, new OPacket18Animation(oentity, 7));
    }
    
    public void L() {
        if (this.a != null) {
            this.a.b((OPacket) (new OPacket202PlayerAbilities(this.L)));
        }
    }
    
    public Player getPlayer() {
        return player;
    }

    /**
     * Reloads the player
     */
    public void reloadPlayer() {
        player = etc.getDataSource().getPlayer(v);
        player.setUser(this);
    }
}
