import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;


public class ONetServerHandler extends ONetHandler implements OICommandListener {

    public static Logger a = Logger.getLogger("Minecraft");
    public ONetworkManager b;
    public boolean c = false;
    private OMinecraftServer d;
    private OEntityPlayerMP e;
    private int f;
    private int g;
    private boolean h;
    private int i;
    private long j;
    private static Random k = new Random();
    private long l;
    // private int m = 0; CanaryMod - disable native spam protection
    private int n = 0;
    private double o;
    private double p;
    private double q;
    private boolean r = true;
    private OIntHashMap s = new OIntHashMap();

    public ONetServerHandler(OMinecraftServer ominecraftserver, ONetworkManager onetworkmanager, OEntityPlayerMP oentityplayermp) {
        super();
        this.d = ominecraftserver;
        this.b = onetworkmanager;
        onetworkmanager.a((ONetHandler) this);
        this.e = oentityplayermp;
        oentityplayermp.a = this;
    }

    public void a() {
        this.h = false;
        ++this.f;
        this.b.b();
        if ((long) this.f - this.l > 20L) {
            this.l = (long) this.f;
            this.j = System.nanoTime() / 1000000L;
            this.i = k.nextInt();
            this.b((OPacket) (new OPacket0KeepAlive(this.i)));
        }

        /* CanaryMod - disable native spam protection
         if (this.m > 0) {
         --this.m;
         }
         */

        if (this.n > 0) {
            --this.n;
        }

    }

    public void a(String s) {
        if (!this.c) {
            this.e.I();
            this.b((OPacket) (new OPacket255KickDisconnect(s)));
            this.b.d();
            // CanaryMod handle disconnect world stuff
            this.e.bi.getEntityTracker().untrackPlayerSymmetrics(this.e);
            this.e.bi.getEntityTracker().untrackEntity(this.e);
            etc.getServer().getPlayerManager(this.e.bi.world).removePlayer(this.e);
            
            // CanaryMod - onPlayerDisconnect Hook
            HookParametersDisconnect hookResult = new HookParametersDisconnect(String.format(Colors.Yellow + "%s left the game.", this.e.v), s); // XXX

            hookResult = (HookParametersDisconnect) etc.getLoader().callHook(PluginLoader.Hook.PLAYER_DISCONNECT, this.e.getPlayer(), hookResult);
            if (!hookResult.isHidden()) { 
                this.d.h.a((OPacket) (new OPacket3Chat(hookResult.getLeaveMessage())));
            }
            
            this.d.h.e(this.e);
            this.c = true;
        }
    }

    public void a(OPacket10Flying opacket10flying) {
        OWorldServer oworldserver = this.d.getWorld(this.e.bi.name, this.e.w);

        this.h = true;
        if (!this.e.j) {
            double d0;

            if (!this.r) {
                d0 = opacket10flying.b - this.p;
                if (opacket10flying.a == this.o && d0 * d0 < 0.01D && opacket10flying.c == this.q) {
                    this.r = true;
                }
            }
            
            // CanaryMod: Notice player movement
            Player player = getPlayer();

            if (etc.floor(o) != etc.floor(player.getX()) || etc.floor(p) != etc.floor(player.getY()) || etc.floor(q) != etc.floor(player.getZ())) {
                Location from = new Location(player.getWorld(), etc.floor(o), etc.floor(p), etc.floor(q));

                Location to = player.getLocation();

                OEntity.manager.callHook(PluginLoader.Hook.PLAYER_MOVE, player, from, to);
            }

            if (this.r) {
                double d1;
                double d2;
                double d3;
                double d4;

                if (this.e.bh != null) {
                    float f = this.e.bs;
                    float f1 = this.e.bt;

                    this.e.bh.i_();
                    d1 = this.e.bm;
                    d2 = this.e.bn;
                    d3 = this.e.bo;
                    double d5 = 0.0D;

                    d4 = 0.0D;
                    if (opacket10flying.i) {
                        f = opacket10flying.e;
                        f1 = opacket10flying.f;
                    }

                    if (opacket10flying.h && opacket10flying.b == -999.0D && opacket10flying.d == -999.0D) {
                        if (opacket10flying.a > 1.0D || opacket10flying.c > 1.0D) {
                            System.err.println(this.e.v + " was caught trying to crash the server with an invalid position.");
                            this.a("Nope!");
                            return;
                        }

                        d5 = opacket10flying.a;
                        d4 = opacket10flying.c;
                    }

                    this.e.bx = opacket10flying.g;
                    this.e.a(true);
                    this.e.a(d5, 0.0D, d4);
                    this.e.b(d1, d2, d3, f, f1);
                    this.e.bp = d5;
                    this.e.br = d4;
                    if (this.e.bh != null) {
                        oworldserver.b(this.e.bh, true);
                    }

                    if (this.e.bh != null) {
                        this.e.bh.i_();
                    }

                    this.d.h.d(this.e);
                    this.o = this.e.bm;
                    this.p = this.e.bn;
                    this.q = this.e.bo;
                    oworldserver.g(this.e);
                    return;
                }

                if (this.e.Z()) {
                    this.e.a(true);
                    this.e.b(this.o, this.p, this.q, this.e.bs, this.e.bt);
                    oworldserver.g(this.e);
                    return;
                }

                d0 = this.e.bn;
                this.o = this.e.bm;
                this.p = this.e.bn;
                this.q = this.e.bo;
                d1 = this.e.bm;
                d2 = this.e.bn;
                d3 = this.e.bo;
                float f2 = this.e.bs;
                float f3 = this.e.bt;

                if (opacket10flying.h && opacket10flying.b == -999.0D && opacket10flying.d == -999.0D) {
                    opacket10flying.h = false;
                }

                if (opacket10flying.h) {
                    d1 = opacket10flying.a;
                    d2 = opacket10flying.b;
                    d3 = opacket10flying.c;
                    d4 = opacket10flying.d - opacket10flying.b;
                    if (!this.e.Z() && (d4 > 1.65D || d4 < 0.1D)) {
                        this.a("Illegal stance");
                        a.warning(this.e.v + " had an illegal stance: " + d4);
                        return;
                    }

                    if (Math.abs(opacket10flying.a) > 3.2E7D || Math.abs(opacket10flying.c) > 3.2E7D) {
                        this.a("Illegal position");
                        return;
                    }
                }

                if (opacket10flying.i) {
                    f2 = opacket10flying.e;
                    f3 = opacket10flying.f;
                }

                this.e.a(true);
                this.e.bO = 0.0F;
                this.e.b(this.o, this.p, this.q, f2, f3);
                if (!this.r) {
                    return;
                }

                d4 = d1 - this.e.bm;
                double d6 = d2 - this.e.bn;
                double d7 = d3 - this.e.bo;
                double d8 = d4 * d4 + d6 * d6 + d7 * d7;

                if (d8 > 100.0D) {
                    a.warning(this.e.v + " moved too quickly!");
                    this.a("You moved too quickly :( (Hacking?)");
                    return;
                }

                float f4 = 0.0625F;
                boolean flag = oworldserver.a(this.e, this.e.bw.b().e((double) f4, (double) f4, (double) f4)).size() == 0;

                if (this.e.bx && !opacket10flying.g && d6 > 0.0D) {
                    this.e.c(0.2F);
                }

                this.e.a(d4, d6, d7);
                this.e.bx = opacket10flying.g;
                this.e.b(d4, d6, d7);
                double d9 = d6;

                d4 = d1 - this.e.bm;
                d6 = d2 - this.e.bn;
                if (d6 > -0.5D || d6 < 0.5D) {
                    d6 = 0.0D;
                }

                d7 = d3 - this.e.bo;
                d8 = d4 * d4 + d6 * d6 + d7 * d7;
                boolean flag1 = false;

                if (d8 > 0.0625D && !this.e.Z() && !this.e.c.b()) {
                    flag1 = true;
                    a.warning(this.e.v + " moved wrongly!");
                    System.out.println("Got position " + d1 + ", " + d2 + ", " + d3);
                    System.out.println("Expected " + this.e.bm + ", " + this.e.bn + ", " + this.e.bo);
                }

                this.e.b(d1, d2, d3, f2, f3);
                boolean flag2 = oworldserver.a(this.e, this.e.bw.b().e((double) f4, (double) f4, (double) f4)).size() == 0;

                // tp back when obstructed
                if (flag && (flag1 || !flag2) && !this.e.Z()) {
                    this.a(this.o, this.p, this.q, f2, f3, this.e.w, this.e.bi.name);
                    return;
                }

                OAxisAlignedBB oaxisalignedbb = this.e.bw.b().b((double) f4, (double) f4, (double) f4).a(0.0D, -0.55D, 0.0D);

                if (!this.d.r && !this.e.c.b() && !oworldserver.b(oaxisalignedbb) && !getPlayer().canIgnoreRestrictions()) {
                    if (d9 >= -0.03125D) {
                        ++this.g;
                        if (this.g > 80) {
                            a.warning(this.e.v + " was kicked for floating too long!");
                            this.a("Flying is not enabled on this server");
                            return;
                        }
                    }
                } else {
                    this.g = 0;
                }

                this.e.bx = opacket10flying.g;
                this.d.h.d(this.e);
                this.e.b(this.e.bn - d0, opacket10flying.g);
            }

        }
    }

    // CabaryMod changed signature to include world and dimension to go
    public void a(double d0, double d1, double d2, float f, float f1, int i, String s) {
        // CanaryMod: Teleportation hook 
        Location to = new Location();

        to.x = d0;
        to.y = d1;
        to.z = d2;
        to.rotX = f;
        to.rotY = f1;
        to.dimension = i;
        to.world = s;
        Player player = getPlayer();

        if ((Boolean) OEntity.manager.callHook(PluginLoader.Hook.TELEPORT, player, player.getLocation(), to)) {
            return;
        }
        
        this.r = false;
        this.o = d0;
        this.p = d1;
        this.q = d2;
        this.e.b(d0, d1, d2, f, f1);
        this.e.a.b((OPacket) (new OPacket13PlayerLookMove(d0, d1 + 1.6200000047683716D, d1, d2, f, f1, false)));
        player.refreshCreativeMode();
    }
    
    // CanaryMod: Store x/y/z
    int x, y, z, type;

    public void a(OPacket14BlockDig opacket14blockdig) {
        OWorldServer oworldserver = this.d.getWorld(this.e.bi.name, this.e.w);

        if (opacket14blockdig.e == 4) {
            this.e.S();
        } else if (opacket14blockdig.e == 5) {
            this.e.N();
        } else {
            // CanaryMod: We allow admins and ops to dig!
            boolean flag = oworldserver.H = oworldserver.t.g != 0 || this.d.h.h(this.e.v) || getPlayer().isAdmin();
            boolean flag1 = false;

            if (opacket14blockdig.e == 0) {
                flag1 = true;
            }

            if (opacket14blockdig.e == 2) {
                flag1 = true;
            }

            int i = opacket14blockdig.a;
            int j = opacket14blockdig.b;
            int k = opacket14blockdig.c;

            if (flag1) {
                double d0 = this.e.bm - ((double) i + 0.5D);
                double d1 = this.e.bn - ((double) j + 0.5D) + 1.5D;
                double d2 = this.e.bo - ((double) k + 0.5D);
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (d3 > 36.0D) {
                    return;
                }

                if (j >= this.d.t) {
                    return;
                }
            }

            OChunkCoordinates ochunkcoordinates = oworldserver.p();
            int l = OMathHelper.a(i - ochunkcoordinates.a);
            int i1 = OMathHelper.a(k - ochunkcoordinates.c);

            if (l > i1) {
                i1 = l;
            }
            
            // CanaryMod: the player
            Player player = getPlayer();

            if (opacket14blockdig.e == 0) {
                // CanaryMod: Start digging
                // No buildrights
                if (!getPlayer().canBuild()) {
                    return;
                }
                // CanaryMod: Custom spawn prot size
                if (i1 <= etc.getInstance().getSpawnProtectionSize() && !flag) {
                    this.e.a.b((OPacket) (new OPacket53BlockChange(i, j, k, oworldserver)));
                } else {
                    // CanaryMod: Dig hooks
                    Block block = oworldserver.world.getBlockAt(i, j, k);

                    block.setStatus(0); // Started digging
                    x = block.getX();
                    y = block.getY();
                    z = block.getZ();
                    type = block.getType();
                    if (!(Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block)) {
                        this.e.c.a(i, j, k, opacket14blockdig.d);
                    } else {
                        this.e.a.b((OPacket) (new OPacket53BlockChange(i, j, k, oworldserver)));
                    }
                }
            } else if (opacket14blockdig.e == 2) {
                // CanaryMod: Break block
                Block block = oworldserver.world.getBlockAt(i, j, k);

                block.setStatus(2); // Block broken
                OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);
                
                this.e.c.a(i, j, k);
                if (oworldserver.a(i, j, k) != 0) {
                    this.e.a.b((OPacket) (new OPacket53BlockChange(i, j, k, oworldserver)));
                }
            } else if (opacket14blockdig.e == 3) {
                // CanaryMod: Send block update
                Block block = new Block(oworldserver.world, type, x, y, z);

                block.setStatus(3); // Send update for block
                OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);
                
                double d4 = this.e.bm - ((double) i + 0.5D);
                double d5 = this.e.bn - ((double) j + 0.5D);
                double d6 = this.e.bo - ((double) k + 0.5D);
                double d7 = d4 * d4 + d5 * d5 + d6 * d6;

                if (d7 < 256.0D) {
                    this.e.a.b((OPacket) (new OPacket53BlockChange(i, j, k, oworldserver)));
                }
            }

            oworldserver.H = false;
        }
    }
    
    // CanaryMod: Store the blocks between blockPlaced packets
    Block lastRightClicked;

    public void a(OPacket15Place opacket15place) {
        OWorldServer oworldserver = this.d.getWorld(this.e.bi.name, this.e.w);
        OItemStack oitemstack = this.e.k.d();
        
        // CanaryMod: Store block data to call hooks
        // CanaryMod START
        Block blockClicked;
        Block blockPlaced = null;

        // We allow admins and ops to build!
        boolean flag = oworldserver.H = oworldserver.t.g != 0 || this.d.h.h(this.e.v) || getPlayer().isAdmin();
        
        boolean flag1 = false;
        int i = opacket15place.a;
        int j = opacket15place.b;
        int k = opacket15place.c;
        int l = opacket15place.d;
        
        if (opacket15place.d == 255) {
            // ITEM_USE -- if we have a lastRightClicked then it could be a
            // usable location
            blockClicked = lastRightClicked;
            lastRightClicked = null;
        } else {
            // RIGHTCLICK or BLOCK_PLACE .. or nothing
            blockClicked = new Block(oworldserver.world, oworldserver.world.getBlockIdAt(opacket15place.a, opacket15place.b, opacket15place.c), opacket15place.a, opacket15place.b, opacket15place.c);
            blockClicked.setFaceClicked(Block.Face.fromId(opacket15place.d));
            
            lastRightClicked = blockClicked;
        }

        // If we clicked on something then we also have a location to place the
        // block
        if (blockClicked != null && oitemstack != null) {
            blockPlaced = new Block(oworldserver.world, oitemstack.c, blockClicked.getX(), blockClicked.getY(), blockClicked.getZ());
            switch (opacket15place.d) {
                case 0:
                    blockPlaced.setY(blockPlaced.getY() - 1);
                    break;

                case 1:
                    blockPlaced.setY(blockPlaced.getY() + 1);
                    break;

                case 2:
                    blockPlaced.setZ(blockPlaced.getZ() - 1);
                    break;

                case 3:
                    blockPlaced.setZ(blockPlaced.getZ() + 1);
                    break;

                case 4:
                    blockPlaced.setX(blockPlaced.getX() - 1);
                    break;

                case 5:
                    blockPlaced.setX(blockPlaced.getX() + 1);
                    break;
            }
        }

        // CanaryMod: END

        if (opacket15place.d == 255) {
            // CanaryMod: call our version with extra blockClicked/blockPlaced
            if (blockPlaced != null) {
                // Set the type of block to what it currently is
                blockPlaced.setType(oworldserver.world.getBlockIdAt(blockPlaced.getX(), blockPlaced.getY(), blockPlaced.getZ()));
            }
            
            if (oitemstack == null) {
                return;
            }

            this.e.c.itemUsed(this.e, oworldserver, oitemstack, blockPlaced, blockClicked);
        } else if (opacket15place.b >= this.d.t - 1 && (opacket15place.d == 1 || opacket15place.b >= this.d.t)) {
            this.e.a.b((OPacket) (new OPacket3Chat("\u00a77Height limit for building is " + this.d.t)));
            flag1 = true;
        } else {
            OChunkCoordinates ochunkcoordinates = oworldserver.p();
            // CanaryMod : Fix stupid buggy spawn protection. (2 times)
            int i1 = (int) OMathHelper.e((l == 4 ? i - 1 : (l == 5 ? (i + 1) : i)) - ochunkcoordinates.a);
            int j1 = (int) OMathHelper.e((l == 2 ? k - 1 : (l == 3 ? (k + 1) : k)) - ochunkcoordinates.c);

            if (i1 > j1) {
                j1 = i1;
            }
            
            // CanaryMod: call BLOCK_RIGHTCLICKED
            Item item = (oitemstack != null) ? new Item(oitemstack) : new Item(Item.Type.Air);
            Player player = getPlayer();
            boolean cancelled = (Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_RIGHTCLICKED, player, blockClicked, item);
         
            // CanaryMod: call original BLOCK_CREATED
            OEntity.manager.callHook(PluginLoader.Hook.BLOCK_CREATED, player, blockPlaced, blockClicked, item.getItemId());
            // CanaryMod: If we were building inside spawn, bail! (unless ops/admin)

            if (this.r && this.e.e((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D) < 64.0D && (j1 > etc.getInstance().getSpawnProtectionSize() || flag) && player.canBuild() && !cancelled) {
                this.e.c.a(this.e, oworldserver, oitemstack, i, j, k, l);
            } else {
                // CanaryMod: No point sending the client to update the blocks, you weren't allowed to place!
                this.e.a.b((OPacket) (new OPacket53BlockChange(i, j, k, oworldserver)));
                oworldserver.y = false;
                return;
            }

            flag1 = true;
        }

        if (flag1) {
            this.e.a.b((OPacket) (new OPacket53BlockChange(i, j, k, oworldserver)));
            if (l == 0) {
                --j;
            }

            if (l == 1) {
                ++j;
            }

            if (l == 2) {
                --k;
            }

            if (l == 3) {
                ++k;
            }

            if (l == 4) {
                --i;
            }

            if (l == 5) {
                ++i;
            }

            this.e.a.b((OPacket) (new OPacket53BlockChange(i, j, k, oworldserver)));
        }

        oitemstack = this.e.k.d();
        if (oitemstack != null && oitemstack.a == 0) {
            this.e.k.a[this.e.k.c] = null;
            oitemstack = null;
        }

        if (oitemstack == null || oitemstack.l() == 0) {
            this.e.h = true;
            this.e.k.a[this.e.k.c] = OItemStack.b(this.e.k.a[this.e.k.c]);
            OSlot oslot = this.e.m.a((OIInventory) this.e.k, this.e.k.c);

            this.e.m.a();
            this.e.h = false;
            if (!OItemStack.b(this.e.k.d(), opacket15place.e)) {
                this.b((OPacket) (new OPacket103SetSlot(this.e.m.f, oslot.c, this.e.k.d())));
            }
        }

        oworldserver.H = false;
    }

    public void a(String s, Object[] aobject) {
        // CanaryMod: disconnect!
        OEntity.manager.callHook(PluginLoader.Hook.DISCONNECT, getPlayer());
        a.info(this.e.v + " lost connection: " + s);
        
        // CanaryMod - onPlayerDisconnect Hook
        HookParametersDisconnect hookResult = new HookParametersDisconnect(String.format(Colors.Yellow + "%s left the server.", this.e.v), s);

        hookResult = (HookParametersDisconnect) etc.getLoader().callHook(PluginLoader.Hook.PLAYER_DISCONNECT, this.e.getPlayer(), hookResult); // XXX
        if (!hookResult.isHidden()) { 
            this.d.h.a((OPacket) (new OPacket3Chat(hookResult.getLeaveMessage())));
        }
        // CanaryMod handle disconnect world stuff
        this.e.bi.getEntityTracker().untrackPlayerSymmetrics(this.e);
        this.e.bi.getEntityTracker().untrackEntity(this.e);
        etc.getServer().getPlayerManager(this.e.bi.world).removePlayer(this.e);
        this.d.h.e(this.e);
        this.c = true;
    }

    public void a(OPacket opacket) {
        a.warning(this.getClass() + " wasn\'t prepared to deal with a " + opacket.getClass());
        this.a("Protocol error, unexpected packet");
    }

    public void b(OPacket opacket) {
        this.b.a(opacket);
    }

    public void a(OPacket16BlockItemSwitch opacket16blockitemswitch) {
        if (opacket16blockitemswitch.a >= 0 && opacket16blockitemswitch.a < OInventoryPlayer.h()) {
            this.e.k.c = opacket16blockitemswitch.a;
        } else {
            a.warning(this.e.v + " tried to set an invalid carried item");
        }
    }

    public void a(OPacket3Chat opacket3chat) {
        String s = opacket3chat.a;
        
        // CanaryMod - disable native spam protection
            
        // CanaryMod: redirect chathandling to player class
        getPlayer().chat(s);
    }

    // Handled by PlayerCommands class
    private void c(String s) {
    }

    public void a(OPacket18Animation opacket18animation) {
        if (opacket18animation.b == 1) {
            // CanaryMod: Swing the arm!
            if (!this.e.t) { // Only call hook once per actual swing
                OEntity.manager.callHook(PluginLoader.Hook.ARM_SWING, getPlayer());
            }
            this.e.C_();
        }

    }

    public void a(OPacket19EntityAction opacket19entityaction) {
        if (opacket19entityaction.b == 1) {
            this.e.g(true);
        } else if (opacket19entityaction.b == 2) {
            this.e.g(false);
        } else if (opacket19entityaction.b == 4) {
            this.e.h(true);
        } else if (opacket19entityaction.b == 5) {
            this.e.h(false);
        } else if (opacket19entityaction.b == 3) {
            this.e.a(false, true, true);
            this.r = false;
        }

    }

    public void a(OPacket255KickDisconnect opacket255kickdisconnect) {
        this.b.a("disconnect.quitting", new Object[0]);
    }

    public int b() {
        return this.b.e();
    }

    public void b(String s) {
        this.b((OPacket) (new OPacket3Chat("\u00a77" + s)));
    }

    public String d() {
        return this.e.v;
    }

    public void a(OPacket7UseEntity opacket7useentity) {
        OWorldServer oworldserver = this.d.getWorld(this.e.bi.name, this.e.w);
        OEntity oentity = oworldserver.a(opacket7useentity.b);

        if (oentity != null) {
            boolean flag = this.e.h(oentity);
            double d0 = 36.0D;

            if (!flag) {
                d0 = 9.0D;
            }

            if (this.e.j(oentity) < d0) {
                if (opacket7useentity.c == 0) {
                    this.e.e(oentity);
                } else if (opacket7useentity.c == 1) {
                    this.e.f(oentity);
                }
            }
        }

    }

    public void a(OPacket9Respawn opacket9respawn) {
        // CanaryMod: onPlayerRespawn
        OChunkCoordinates defaultSpawnCoords = e.ab();

        if (defaultSpawnCoords == null) {
            // defaultSpawnCoords = etc.getServer().getWorld(e.bi.name)[0].getWorld().p();
            defaultSpawnCoords = e.bi.p();
        }
        
        // Location respawnLocation = new Location(e.bi.world, defaultSpawnCoords.a, defaultSpawnCoords.b, defaultSpawnCoords.c, 0, 0);
        Location respawnLocation = e.bi.world.getSpawnLocation();

        if (this.e.j) {
            etc.getLoader().callHook(PluginLoader.Hook.PLAYER_RESPAWN, e.getPlayer(), respawnLocation);
            this.e = this.d.h.a(this.e, respawnLocation.dimension, true, respawnLocation);
        } else {
            if (this.e.aD() > 0) {
                return;
            }
            etc.getLoader().callHook(PluginLoader.Hook.PLAYER_RESPAWN, e.getPlayer(), respawnLocation);
            this.e = this.d.h.a(this.e, respawnLocation.dimension, false, respawnLocation);
        }
    }

    public void a(OPacket101CloseWindow opacket101closewindow) {
        this.e.H();
    }

    public void a(OPacket102WindowClick opacket102windowclick) {
        if (this.e.m.f == opacket102windowclick.a && this.e.m.c(this.e)) {
            OItemStack oitemstack = this.e.m.a(opacket102windowclick.b, opacket102windowclick.c, opacket102windowclick.f, this.e);

            if (OItemStack.b(opacket102windowclick.e, oitemstack)) {
                this.e.a.b((OPacket) (new OPacket106Transaction(opacket102windowclick.a, opacket102windowclick.d, true)));
                this.e.h = true;
                this.e.m.a();
                this.e.G();
                this.e.h = false;
            } else {
                this.s.a(this.e.m.f, Short.valueOf(opacket102windowclick.d));
                this.e.a.b((OPacket) (new OPacket106Transaction(opacket102windowclick.a, opacket102windowclick.d, false)));
                this.e.m.a(this.e, false);
                ArrayList arraylist = new ArrayList();

                for (int i = 0; i < this.e.m.e.size(); ++i) {
                    arraylist.add(((OSlot) this.e.m.e.get(i)).b());
                }
                // if we shiftclicked on slot 0 -> resend all inventory to player
                if (opacket102windowclick.b == 0 && opacket102windowclick.f) {
                    this.e.a(this.e.m, arraylist);
                }
            }
        }

    }

    public void a(OPacket108EnchantItem opacket108enchantitem) {
        if (this.e.m.f == opacket108enchantitem.a && this.e.m.c(this.e)) {
            this.e.m.a((OEntityPlayer) this.e, opacket108enchantitem.b);
            this.e.m.a();
        }

    }

    public void a(OPacket107CreativeSetSlot opacket107creativesetslot) {
        if (this.e.c.b()) {
            boolean flag = opacket107creativesetslot.a < 0;
            OItemStack oitemstack = opacket107creativesetslot.b;
            boolean flag1 = opacket107creativesetslot.a >= 36 && opacket107creativesetslot.a < 36 + OInventoryPlayer.h();
            boolean flag2 = oitemstack == null || oitemstack.c < OItem.d.length && oitemstack.c >= 0 && OItem.d[oitemstack.c] != null;
            boolean flag3 = oitemstack == null || oitemstack.h() >= 0 && oitemstack.h() >= 0 && oitemstack.a <= 64 && oitemstack.a > 0;

            if (flag1 && flag2 && flag3) {
                if (oitemstack == null) {
                    this.e.l.a(opacket107creativesetslot.a, (OItemStack) null);
                } else {
                    this.e.l.a(opacket107creativesetslot.a, oitemstack);
                }

                this.e.l.a(this.e, true);
            } else if (flag && flag2 && flag3 && this.n < 200) {
                this.n += 20;
                OEntityItem oentityitem = this.e.b(oitemstack);

                if (oentityitem != null) {
                    oentityitem.k();
                }
            }
        }

    }

    public void a(OPacket106Transaction opacket106transaction) {
        Short oshort = (Short) this.s.a(this.e.m.f);

        if (oshort != null && opacket106transaction.b == oshort.shortValue() && this.e.m.f == opacket106transaction.a && !this.e.m.c(this.e)) {
            this.e.m.a(this.e, true);
        }

    }

    public void a(OPacket130UpdateSign opacket130updatesign) {
        OWorldServer oworldserver = this.d.getWorld(this.e.bi.name, this.e.w);

        if (oworldserver.i(opacket130updatesign.a, opacket130updatesign.b, opacket130updatesign.c)) {
            OTileEntity otileentity = oworldserver.b(opacket130updatesign.a, opacket130updatesign.b, opacket130updatesign.c);

            if (otileentity instanceof OTileEntitySign) {
                OTileEntitySign otileentitysign = (OTileEntitySign) otileentity;

                if (!otileentitysign.c()) {
                    this.d.c("Player " + this.e.v + " just tried to change non-editable sign");
                    return;
                }
            }

            int i;
            int j;

            for (j = 0; j < 4; ++j) {
                boolean flag = true;

                // CanaryMod: Remove the char limit, for plugins.
                // if (opacket130updatesign.d[j].length() > 15) {
                // flag = false;
                // } else {
                for (i = 0; i < opacket130updatesign.d[j].length(); ++i) {
                    if (OChatAllowedCharacters.a.indexOf(opacket130updatesign.d[j].charAt(i)) < 0) {
                        flag = false;
                    }
                }
                // }

                if (!flag) {
                    opacket130updatesign.d[j] = "!?";
                }
            }

            if (otileentity instanceof OTileEntitySign) {
                j = opacket130updatesign.a;
                int k = opacket130updatesign.b;

                i = opacket130updatesign.c;
                OTileEntitySign otileentitysign1 = (OTileEntitySign) otileentity;
                
                // CanaryMod: Copy the old line text
                String[] old = Arrays.copyOf(otileentitysign1.a, otileentitysign1.a.length);

                for (int l = 0; l < 4; ++l) {
                    otileentitysign1.a[l] = opacket130updatesign.d[l];
                }
                
                // CanaryMod: Check if we can change it
                Sign sign = new Sign(otileentitysign1);

                if ((Boolean) OEntity.manager.callHook(PluginLoader.Hook.SIGN_CHANGE, getPlayer(), sign)) {
                    otileentitysign1.a = Arrays.copyOf(old, old.length);
                }

                otileentitysign1.G_();
                oworldserver.j(j, k, i);
            }
        }

    }

    public void a(OPacket0KeepAlive opacket0keepalive) {
        if (opacket0keepalive.a == this.i) {
            int i = (int) (System.nanoTime() / 1000000L - this.j);

            this.e.i = (this.e.i * 3 + i) / 4;
        }

    }

    public boolean c() {
        return true;
    }
    
    public void a(OPacket202PlayerAbilities opacket202playerabilities) {
        this.e.L.b = opacket202playerabilities.b && this.e.L.c;
    }
    
    /**
     * Returns the item in player's hand
     * 
     * @return item
     */
    public int getItemInHand() {
        if (e.k.d() != null) {
            return e.k.d().c;
        }
        return -1;
    }

    /**
     * Returns the player
     * 
     * @return player
     */
    public Player getPlayer() {
        return e.getPlayer();
    }
    
    /**
     * Override player entity
     * @param player
     */
    public void setPlayer(OEntityPlayerMP oentityplayermp) {
        this.e = oentityplayermp;
    }
    
    /**
     * Override player entity
     * @param player
     */
    public void setPlayer(Player player) {
        this.e = player.getEntity();
    }
    
    /**
     * Sends a message to the player
     * 
     * @param msg
     */
    public void msg(String msg) {
        if (msg.length() >= 119) {
            String cutMsg = msg.substring(0, 118);
            int finalCut = cutMsg.lastIndexOf(" ");
            String subCut = cutMsg.substring(0, finalCut);
            String newMsg = msg.substring(finalCut + 1);

            b(new OPacket3Chat(subCut));
            msg(newMsg);
        } else {
            b(new OPacket3Chat(msg));
        }
    }

}
