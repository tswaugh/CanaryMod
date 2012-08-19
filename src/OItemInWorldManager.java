import java.util.ArrayList;


public class OItemInWorldManager {

    public OWorld a;
    public OEntityPlayer b;
    private int c = -1;
    private float d = 0.0F;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;

    public OItemInWorldManager(OWorld oworld) {
        super();
        this.a = oworld;
    }

    public void a(int i) {
        this.c = i;
        if (i == 0) {
            this.b.L.c = false;
            this.b.L.b = false;
            this.b.L.d = false;
            this.b.L.a = false;
        } else {
            this.b.L.c = true;
            this.b.L.d = true;
            this.b.L.a = true;
        }

    }

    public int a() {
        return this.c;
    }

    public boolean b() {
        return this.c == 1;
    }

    public void b(int i) {
        if (this.c == -1) {
            this.c = i;
        }

        this.a(this.c);
    }

    public void c() {
        this.i = (int) (System.currentTimeMillis() / 50); // CanaryMod - block lag fix
        if (this.j) {
            int i = this.i - this.n;
            int j = this.a.a(this.k, this.l, this.m);

            if (j != 0) {
                OBlock oblock = OBlock.m[j];
                float f = oblock.a(this.b) * (float) (i + 1);

                if (f >= 1.0F) {
                    this.j = false;
                    this.c(this.k, this.l, this.m);
                }
            } else {
                this.j = false;
            }
        }

    }

    public void a(int i, int j, int k, int l) {
        if (this.b()) {
            if (!this.a.a((OEntityPlayer) null, i, j, k, l)) {
                this.c(i, j, k);
            }

        } else {
            this.a.a((OEntityPlayer) null, i, j, k, l);
            this.e = this.i;
            int i1 = this.a.a(i, j, k);

            if (i1 > 0) {
                OBlock.m[i1].b(this.a, i, j, k, this.b);
            }

            if (i1 > 0 && OBlock.m[i1].a(this.b) >= 1.0F) {
                this.c(i, j, k);
            } else {
                this.f = i;
                this.g = j;
                this.h = k;
            }

        }
    }

    public void a(int i, int j, int k) {
        if (i == this.f && j == this.g && k == this.h) {
            int l = this.i - this.e;
            int i1 = this.a.a(i, j, k);

            if (i1 != 0) {
                OBlock oblock = OBlock.m[i1];
                float f = oblock.a(this.b) * (float) (l + 1);

                if (f >= 0.7F) {
                    this.c(i, j, k);
                } else if (!this.j) {
                    this.j = true;
                    this.k = i;
                    this.l = j;
                    this.m = k;
                    this.n = this.e;
                }
            }
        }

        this.d = 0.0F;
    }

    public boolean b(int i, int j, int k) {
        OBlock oblock = OBlock.m[this.a.a(i, j, k)];
        int l = this.a.c(i, j, k);
        boolean flag = this.a.e(i, j, k, 0);

        if (oblock != null && flag) {
            oblock.c(this.a, i, j, k, l);
        }

        return flag;
    }

    public boolean c(int i, int j, int k) {
        // CanaryMod start - portal destroy
        Block block = ((OEntityPlayerMP) b).getPlayer().getWorld().getBlockAt(i, j, k); //

        if (block.getType() == Block.Type.Obsidian.getType()) {
            boolean removeAll = true;
            ArrayList<Player> updatedPlayers = new ArrayList<Player>();

            for (Player player : etc.getServer().getPlayerList()) {
                if (player.getWorld().equals(block.getWorld())) {
                    updatedPlayers.add(player);
                }
            }
            int[][] blockOffsets = new int[][] { new int[] { 0, 1, 0}, new int[] { 1, 0, 0}, new int[] { -1, 0, 0}, new int[] { 0, 0, 1}, new int[] { 0, 0, -1}, new int[] { 0, -1, 0}};

            for (int i1 = 0; i1 < blockOffsets.length; i1 += 1) {
                Block[][] blocks = getPortalBlocks(block.getWorld(), i + blockOffsets[i1][0], j + blockOffsets[i1][1], k + blockOffsets[i1][2]);

                if (blocks != null) {
                    // CanaryMod hook onPortalDestroy
                    if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.PORTAL_DESTROY, (Object) blocks)) {
                        removeAll = false;
                    } else {
                        for (int j1 = 0; j1 < 3; j1 += 1) {
                            for (int k1 = 0; k1 < 2; k1 += 1) {
                                block.getWorld().getWorld().c(blocks[j1][k1].getX() >> 4, blocks[j1][k1].getZ() >> 4).a(blocks[j1][k1].getX() & 15, blocks[j1][k1].getY(), blocks[j1][k1].getZ() & 15, 0, 0, false);
                                for (Player player : updatedPlayers) {
                                    player.getUser().a.b(new OPacket53BlockChange(blocks[j1][k1].getX(), blocks[j1][k1].getY(), blocks[j1][k1].getZ(), block.getWorld().getWorld()));
                                }
                            }
                        }
                    }
                }
            }
            if (removeAll == false) {
                return true;
            }
        }
        // CanaryMod hook - onBlockBreak
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_BROKEN, ((OEntityPlayerMP) b).getPlayer(), block)) {
            return true;
        }
        // CanaryMod end
        
        int l = this.a.a(i, j, k);
        int i1 = this.a.c(i, j, k);

        this.a.a(this.b, 2001, i, j, k, l + (this.a.c(i, j, k) << 12));
        boolean flag = this.b(i, j, k);

        if (this.b()) {
            ((OEntityPlayerMP) this.b).a.b((OPacket) (new OPacket53BlockChange(i, j, k, this.a)));
        } else {
            OItemStack oitemstack = this.b.U();
            boolean flag1 = this.b.b(OBlock.m[l]);

            if (oitemstack != null) {
                oitemstack.a(l, i, j, k, this.b);
                if (oitemstack.a == 0) {
                    oitemstack.a(this.b);
                    this.b.V();
                }
            }

            if (flag && flag1) {
                OBlock.m[l].a(this.a, this.b, i, j, k, i1);
            }
        }

        return flag;
    }

    public boolean a(OEntityPlayer var1, OWorld var2, OItemStack var3) {        
        int var4 = var3.a;
        int var5 = var3.h();
        OItemStack var6 = var3.a(var2, var1);

        if (var6 == var3 && (var6 == null || var6.a == var4) && (var6 == null || var6.l() <= 0)) {
            return false;
        } else {
            var1.k.a[var1.k.c] = var6;
            if (this.b()) {
                var6.a = var4;
                var6.b(var5);
            }

            if (var6.a == 0) {
                var1.k.a[var1.k.c] = null;
            }

            return true;
        }
    }
    
    /**
     * Called when a player right-click air with an item in hand. We intercept
     * it.
     * 
     * @param player
     * @param world
     * @param item
     * @param blockToPlace
     * @param blockClicked
     * @return
     */
    public boolean itemUsed(OEntityPlayer oentityplayer, OWorld oworld, OItemStack oitemstack, Block block, Block block1) {
        // CanaryMod: only call this hook if we're not using buckets/signs
        if (oitemstack != null) {
            if (oitemstack.a > 0 && oitemstack.c != Item.Type.Sign.getId() && oitemstack.c != Item.Type.Bucket.getId() && oitemstack.c != Item.Type.WaterBucket.getId() && oitemstack.c != Item.Type.LavaBucket.getId() && oitemstack.c != Item.Type.MilkBucket.getId()) {
                if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), block, block1, new Item(oitemstack))) {
                    return false;
                }
            }
        }
        return this.a(oentityplayer, oworld, oitemstack);
    }

    public boolean a(OEntityPlayer oentityplayer, OWorld oworld, OItemStack oitemstack, int i, int j, int k, int l) {
        int i1 = oworld.a(i, j, k);

        if (i1 > 0 && OBlock.m[i1].a(oworld, i, j, k, oentityplayer)) {
            return true;
        } else if (oitemstack == null) {
            return false;
        } else if (this.b()) {
            int j1 = oitemstack.h();
            int k1 = oitemstack.a;
            boolean flag = oitemstack.a(oentityplayer, oworld, i, j, k, l);

            oitemstack.b(j1);
            oitemstack.a = k1;
            return flag;
        } else {
            return oitemstack.a(oentityplayer, oworld, i, j, k, l);
        }
    }

    public void a(OWorldServer oworldserver) {
        this.a = oworldserver;
    }
    
    // CanaryMod start - getPortalBlocks
    private Block[][] getPortalBlocks(World world, int i, int j, int k) {
        int portalId = Block.Type.Portal.getType();

        if (world.getBlockIdAt(i, j, k) == portalId) {
            // These will be equal 1 if the portal is defined on their axis
            // and 0 if not.
            int portalXOffset = (world.getBlockIdAt(i - 1, j, k) == portalId || world.getBlockIdAt(i + 1, j, k) == portalId) ? 1 : 0;
            int portalZOffset = (world.getBlockIdAt(i, j, k - 1) == portalId || world.getBlockIdAt(i, j, k + 1) == portalId) ? 1 : 0;

            // If the portal is either x aligned or z aligned but not both
            // (has neighbor portal in x or z plane but not both)
            if (portalXOffset != portalZOffset) {
                // Get the edge of the portal.
                int portalX = i - ((world.getBlockIdAt(i - 1, j, k) == portalId) ? 1 : 0);
                int portalZ = k - ((world.getBlockIdAt(i, j, k - 1) == portalId) ? 1 : 0);
                int portalY = j;

                while (world.getBlockIdAt(portalX, ++portalY, portalZ) == portalId) {
                    ;
                }
                portalY -= 1;
                // Scan the portal and see if its still all there (2x3
                // formation)
                boolean completePortal = true;
                Block[][] portalBlocks = new Block[3][2];

                for (int i1 = 0; i1 < 3 && completePortal == true; i1 += 1) {
                    for (int j1 = 0; j1 < 2 && completePortal == true; j1 += 1) {
                        portalBlocks[i1][j1] = world.getBlockAt(portalX + j1 * portalXOffset, portalY - i1, portalZ + j1 * portalZOffset);
                        if (portalBlocks[i1][j1].getType() != portalId) {
                            completePortal = false;
                        }
                    }
                }
                if (completePortal == true) {
                    return portalBlocks;
                }
            }
        }
        return null;
    }
    // CanaryMod end
}
