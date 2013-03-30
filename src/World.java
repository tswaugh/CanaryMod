
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * World.java - Interface to worlds. Most of the stuff in Server.java was moved
 * here.
 *
 * @author 14mRh4X0r
 * @author Chris
 */
public class World {

    private final OWorldServer world;

    public enum Dimension {

        /**
         * Represents the Nether.
         */
        NETHER(-1), //
        /**
         * Represents the overworld.
         */
        NORMAL(0), //
        /**
         * Represents the End.
         */
        END(1);
        private int id;
        private static Map<Integer, Dimension> map;

        private Dimension(int id) {
            this.id = id;
            add(id, this);
        }

        private static void add(int type, Dimension name) {
            if (map == null) {
                map = new HashMap<Integer, Dimension>();
            }

            map.put(type, name);
        }

        /**
         * Returns this world's ID.
         * @return this world's ID.
         */
        public int getId() {
            return id;
        }

        /**
         * Get a <tt>Dimension</tt> by ID.
         * @param type The ID for the <tt>Dimension</tt> to return
         * @return The <tt>Dimension<tt> for the given ID.
         * @see #getId()
         */
        public static Dimension fromId(final int type) {
            return map.get(type);
        }

        /**
         * Returns the array index for use with,
         * e.g., {@link Server#getWorld(java.lang.String)}
         * @return The array index for this <tt>Dimension</tt>
         */
        public int toIndex() {
            return id == 0 ? 0 : id == -1 ? 1 : 2;
        }

        @Override
        public String toString() {
            String name = this.name().toLowerCase();
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
    }

    public enum Type {

        DEFAULT(OWorldType.b),
        FLAT(OWorldType.c),
        LARGE_BIOMES(OWorldType.d),
        DEFAULT_1_1(OWorldType.e);
        private OWorldType nativeType;

        private Type(OWorldType nativeType) {
            this.nativeType = nativeType;
        }

        public OWorldType getNative() {
            return nativeType;
        }
    }

    /**
     * Instantiates this wrapper around {@code world}.
     *
     * @param world the OWorldServer to wrap
     */
    public World(OWorldServer world) {
        this.world = world;
    }

    /**
     * Returns the OWorldServer this class wraps around.
     *
     * @return the managed worldserver
     */
    public OWorldServer getWorld() {
        return world;
    }

    /**
     * Returns this dimension's type. Currently Nether, End and Normal.
     *
     * @return the dimension type
     */
    public Dimension getType() {
        return Dimension.fromId(world.t.h);
    }

    /**
     * Returns actual server time (-2^63 to 2^63-1).
     *
     * @return time server time
     */
    public long getTime() {
        return world.G();
    }

    /**
     * Returns current server time (0-24000).
     *
     * @return time server time
     */
    public long getRelativeTime() {
        return world.H();
    }

    /**
     * Sets the actual server time.
     *
     * @param time time (-2^63 to 2^63-1)
     */
    public void setTime(long time) {
        // World info for each world overwrites the other on save,
        // make sure they're the same. (Like you see it in the nether or end)
        for (World w : etc.getServer().getWorld(this.getName()))
            w.getWorld().x.b(time);
    }

    /**
     * Sets the current server time.
     *
     * @param time time (0-24000)
     */
    public void setRelativeTime(long time) {
        // World info for each world overwrites the other on save,
        // make sure they're the same.
        for (World w : etc.getServer().getWorld(this.getName()))
            w.getWorld().x.c(time);
    }

    /**
     * Returns the list of mobs in all open chunks.
     *
     * @return list of mobs
     */
    public List<Mob> getMobList() {
        List<Mob> toRet = new ArrayList<Mob>();

        for (Object o : (ArrayList) ((ArrayList) world.e).clone()) {
            if (o instanceof OEntityMob || o instanceof OEntityGhast || o instanceof OEntitySlime || o instanceof OEntityDragon) {
                toRet.add(new Mob((OEntityLiving) o));
            }
        }
        return toRet;
    }

    /**
     * Returns the list of animals in all open chunks.
     *
     * @return list of animals
     */
    public List<Mob> getAnimalList() {
        List<Mob> toRet = new ArrayList<Mob>();

        for (Object o : (ArrayList) ((ArrayList) world.e).clone()) {
            if (o instanceof OEntityAnimal || o instanceof OEntitySquid || o instanceof OEntitySnowman || o instanceof OEntityBat) {
                toRet.add(new Mob((OEntityLiving) o));
            }
        }
        return toRet;
    }

    /**
     * Returns the list of minecarts in all open chunks.
     *
     * @return list of minecarts
     */
    public List<Minecart> getMinecartList() {
        List<Minecart> toRet = new ArrayList<Minecart>();

        for (Object o : (ArrayList) ((ArrayList) world.e).clone()) {
            if (o instanceof OEntityMinecart) {
                toRet.add(((OEntityMinecart) o).cart);
            }
        }
        return toRet;
    }

    /**
     * Returns the list of boats in all open chunks.
     *
     * @return list of boats
     */
    public List<Boat> getBoatList() {
        List<Boat> toRet = new ArrayList<Boat>();

        for (Object o : (ArrayList) ((ArrayList) world.e).clone()) {
            if (o instanceof OEntityBoat) {
                toRet.add(((OEntityBoat) o).boat);
            }
        }
        return toRet;
    }

    /**
     * Returns the list of all entities in the server in open chunks.
     *
     * @return list of entities
     */
    public List<BaseEntity> getEntityList() {
        List<BaseEntity> toRet = new ArrayList<BaseEntity>();

        for (Object o : (ArrayList) ((ArrayList) world.e).clone()) {
            if (o instanceof OEntityMob || o instanceof OEntityGhast || o instanceof OEntityAnimal || o instanceof OEntitySlime || o instanceof OEntityDragon || o instanceof OEntityMagmaCube || o instanceof OEntityVillager || o instanceof OEntitySquid || o instanceof OEntitySnowman) {
                toRet.add(new Mob((OEntityLiving) o));
            } else if (o instanceof OEntityMinecart) {
                toRet.add(((OEntityMinecart) o).cart);
            } else if (o instanceof OEntityBoat) {
                toRet.add(((OEntityBoat) o).boat);
            } else if (o instanceof OEntityPlayerMP) {
                toRet.add(((OEntityPlayerMP) o).getPlayer());
            } else if (o instanceof OEntityItem) {
                toRet.add(((OEntityItem) o).item);
            } else {
                toRet.add(((OEntity) o).getEntity());
            }
        }
        return toRet;
    }

    /**
     * Returns the list of items in all open chunks.
     *
     * @return list of items
     */
    public List<ItemEntity> getItemList() {
        List<ItemEntity> toRet = new ArrayList<ItemEntity>();

        for (Object o : (ArrayList) ((ArrayList) world.e).clone()) {
            if (o instanceof OEntityItem) {
                toRet.add(((OEntityItem) o).item);
            }
        }
        return toRet;
    }

    /**
     * Returns the list of all living entities (players, animals, mobs) in open
     * chunks.
     *
     * @return list of living entities
     */
    public List<LivingEntity> getLivingEntityList() {
        List<LivingEntity> toRet = new ArrayList<LivingEntity>();

        for (Object o : (ArrayList) ((ArrayList) world.e).clone()) {
            if (o instanceof OEntityLiving) {
                toRet.add(((OEntityLiving) o).getEntity());
            }
        }
        return toRet;
    }

    /**
     * Returns the list of vehicles in open chunks.
     *
     * @return list of vehicles
     */
    public List<BaseVehicle> getVehicleEntityList() {
        List<BaseVehicle> toRet = new ArrayList<BaseVehicle>();

        for (Object o : (ArrayList) ((ArrayList) world.e).clone()) {
            if (o instanceof OEntityMinecart) {
                toRet.add(((OEntityMinecart) o).cart);
            } else if (o instanceof OEntityBoat) {
                toRet.add(((OEntityBoat) o).boat);
            }
        }
        return toRet;
    }

    /**
     * Get the global spawn location
     *
     * @return Location object for spawn
     */
    public Location getSpawnLocation() {
        // More structure ftw
        OWorldInfo info = world.x;
        Location spawn = new Location();

        spawn.x = info.c() + 0.5D;
        spawn.y = info.d();
        spawn.z = info.e() + 0.5D;
        // set y to first free position above actual spawn.
        while (!world.c((int) spawn.x, (int) spawn.y, (int) spawn.z)
                && !world.c((int) spawn.x, (int) spawn.y + 1, (int) spawn.z)) {
            spawn.y++;
        }
        spawn.rotX = 0.0F;
        spawn.rotY = 0.0F;
        spawn.dimension = 0;
        spawn.world = this.getName();
        return spawn;
    }

    /**
     * Sets the spawn location for this level, NOT only this world.
     * @param x The spawn's new x location
     * @param y The spawn's new y location
     * @param z The spawn's new z location
     */
    public void setSpawnLocation(int x, int y, int z) {
        this.getWorld().L().a(x, y, z);
    }

    /**
     * Sets the spawn location for this level, NOT only this world.
     * @param location The new spawn location.
     */
    public void setSpawnLocation(Location location) {
        this.setSpawnLocation(etc.floor(location.x), etc.floor(location.y), etc.floor(location.z));
    }

    /**
     * Sets the block
     *
     * @param block
     * @return
     */
    public boolean setBlock(Block block) {
        return setBlockAt(block.getType(), block.getX(), block.getY(), block.getZ()) && setBlockData(block.getX(), block.getY(), block.getZ(), block.getData());
    }

    /**
     * Returns the block at the specified location
     *
     * @param x
     * @param y
     * @param z
     * @return block
     */
    public Block getBlockAt(int x, int y, int z) {
        return new Block(this, getBlockIdAt(x, y, z), x, y, z, getBlockData(x, y, z));
    }

    /**
     * Returns the block data at the specified coordinates
     *
     * @param x x
     * @param y y
     * @param z z
     * @return block data
     */
    public int getBlockData(int x, int y, int z) {
        return world.h(x, y, z);
    }

    /**
     * Sets the block data at the specified coordinates
     *
     * @param x x
     * @param y y
     * @param z z
     * @param data data
     * @return true if it was successful
     */
    public boolean setBlockData(int x, int y, int z, int data) {
        return world.b(x, y, z, data, 3);
    }

    /**
     * Sets the block type at the specified location
     *
     * @param blockType
     * @param x
     * @param y
     * @param z
     * @return true if successful
     */
    public boolean setBlockAt(int blockType, int x, int y, int z) {
        return world.c(x, y, z, blockType);
    }

    /**
     * Returns the highest block Y
     *
     * @param x
     * @param z
     * @return highest block altitude
     */
    public int getHighestBlockY(int x, int z) {
        return world.f(x, z);
    }

    /**
     * Returns the block type at the specified location
     *
     * @param x
     * @param y
     * @param z
     * @return block type
     */
    public int getBlockIdAt(int x, int y, int z) {
        return world.a(x, y, z);
    }

    /**
     * Returns the complex block at the specified location. Null if there's no
     * complex block there. This will also find complex-blocks spanning multiple
     * spaces, such as double chests.
     *
     * @param block
     * @return complex block
     */
    public ComplexBlock getComplexBlock(Block block) {
        return getComplexBlock(block.getX(), block.getY(), block.getZ());
    }

    /**
     * Returns the complex block at the specified location. Null if there's no
     * complex block there. This will also find complex-blocks spanning multiple
     * spaces, such as double chest.
     *
     * @param location The location of the block. Only x/y/z is used.
     * @return ComplexBlock
     */
    public ComplexBlock getComplexBlock(Location location) {
        return getComplexBlock((int) location.x, (int) location.y, (int) location.z);
    }

    /**
     * Returns the complex block at the specified location. Null if there's no
     * complex block there. This will also find complex-blocks spanning multiple
     * spaces, such as double chests.
     *
     * @param x x
     * @param y y
     * @param z z
     * @return complex block
     */
    public ComplexBlock getComplexBlock(int x, int y, int z) {
        ComplexBlock result = getOnlyComplexBlock(x, y, z);

        if (result != null) {
            if (result instanceof Chest) {
                Chest chest = (Chest) result;

                result = chest.findAttachedChest();

                if (result != null) {
                    return result;
                } else {
                    return chest;
                }
            }
        }

        return result;
    }

    /**
     * Returns the only complex block at the specified location. Null if there's
     * no complex block there.
     *
     * @param block
     * @return complex block
     */
    public ComplexBlock getOnlyComplexBlock(Block block) {
        return getOnlyComplexBlock(block.getX(), block.getY(), block.getZ());
    }

    /**
     * Returns the complex block at the specified location. Null if there's no
     * complex block there.
     *
     * @param x x
     * @param y y
     * @param z z
     * @return complex block
     */
    public ComplexBlock getOnlyComplexBlock(int x, int y, int z) {
        OTileEntity localav = world.r(x, y, z);

        if (localav != null) {
            if (localav instanceof OTileEntitySign) {
                return new Sign((OTileEntitySign) localav);
            } else if (localav instanceof OTileEntityNote) {
                return new NoteBlock((OTileEntityNote) localav);
            } else if (localav instanceof OTileEntityRecordPlayer) {
                return new JukeBox((OTileEntityRecordPlayer) localav);
            } else if (localav instanceof OTileEntitySkull) {
                return new Skull((OTileEntitySkull) localav);
            } else {
                return localav.getComplexBlock();
            }
        }

        return null;
    }

    /**
     * Drops an item at the specified location
     *
     * @param loc
     * @param itemId
     *
     * @return returns the ItemEntity that was dropped
     */
    public ItemEntity dropItem(Location loc, int itemId) {
        return dropItem(loc.x, loc.y, loc.z, itemId, 1, 0);
    }

    /**
     * Drops an item at the specified location
     *
     * @param x
     * @param y
     * @param z
     * @param itemId
     *
     * @return returns the ItemEntity that was dropped
     */
    public ItemEntity dropItem(double x, double y, double z, int itemId) {
        return dropItem(x, y, z, itemId, 1, 0);
    }

    /**
     * Drops an item with desired quantity at the specified location
     *
     * @param x
     * @param y
     * @param z
     * @param itemId
     * @param quantity
     *
     * @return returns the ItemEntity that was dropped
     */
    public ItemEntity dropItem(double x, double y, double z, int itemId, int quantity) {
        return dropItem(x, y, z, itemId, quantity, 0);
    }

    /**
     * Drops an item with desired quantity at the specified location
     *
     * @param loc
     * @param itemId
     * @param quantity
     *
     * @return returns the ItemEntity that was dropped
     */
    public ItemEntity dropItem(Location loc, int itemId, int quantity) {
        return dropItem(loc.x, loc.y, loc.z, itemId, quantity, 0);
    }

    /**
     * Drops an item with damage data and desired quantity at the specified
     * location
     *
     * @param loc
     * @param itemId
     * @param quantity
     * @param damage
     *
     * @return returns the ItemEntity that was dropped
     */
    public ItemEntity dropItem(Location loc, int itemId, int quantity, int damage) {
        return dropItem(loc.x, loc.y, loc.z, itemId, quantity, damage);
    }

    /**
     * Drops an item with desired quantity and damage value at the specified
     * location
     *
     * @param x
     * @param y
     * @param z
     * @param itemId
     * @param quantity
     * @param damage
     * @return returns the ItemEntity that was dropped
     */
    public ItemEntity dropItem(double x, double y, double z, int itemId, int quantity, int damage) {
        return dropItem(x, y, z, new Item(itemId, quantity, -1, damage));
    }

    /**
     * Drops an item with desired quantity at the specified location
     *
     * @param loc
     * @param item
     *
     * @return returns the ItemEntity that was dropped
     */
    public ItemEntity dropItem(Location loc, Item item) {
        return dropItem(loc.x, loc.y, loc.z, item);
    }

    /**
     * Drops an item with desired quantity and damage value at the specified
     * location
     *
     * @param x
     * @param y
     * @param z
     * @param item
     * @return returns the ItemEntity that was dropped
     */
    public ItemEntity dropItem(double x, double y, double z, Item item) {
        double d1 = world.s.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d2 = world.s.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d3 = world.s.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;

        OEntityItem oei = new OEntityItem(world, x + d1, y + d2, z + d3, item.getBaseItem() != null ? item.getBaseItem() : new OItemStack(item.getItemId(), item.getAmount(), item.getDamage()));

        oei.c = 10;
        world.d(oei);
        return oei.item;
    }

    /**
     * Forces the server to update the physics for blocks around the given block
     *
     * @param block the block that changed
     */
    public void updateBlockPhysics(Block block) {
        updateBlockPhysics(block.getX(), block.getY(), block.getZ(), block.getData());
    }

    /**
     * Forces the server to update the physics for blocks around the given block
     *
     * @param x the X coordinate of the block
     * @param y the Y coordinate of the block
     * @param z the Z coordinate of the block
     * @param data the new data for the block
     */
    public void updateBlockPhysics(int x, int y, int z, int data) {
        world.b(x, y, z, data, 3);
    }

    /**
     * Checks to see whether or not the chunk containing the given block is
     * loaded into memory.
     *
     * @param block the Block to check
     * @return true if the chunk is loaded
     */
    public boolean isChunkLoaded(Block block) {
        return isChunkLoaded(block.getX(), block.getY(), block.getZ());
    }

    /**
     * Checks to see whether or not the chunk containing the given block
     * coordinates is loaded into memory.
     *
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     * @return true if the chunk is loaded
     */
    public boolean isChunkLoaded(int x, int y, int z) {
        return isChunkLoaded(x >> 4, z >> 4);
    }

    /**
     * Checks to see whether or not the chunk containing the given chunk
     * coordinates is loaded into memory.
     *
     * @param x a chunk x-coordinate
     * @param z a chunk z-coordinate
     * @return true if the chunk is loaded
     */
    public boolean isChunkLoaded(int x, int z) {
        return world.b.a(x, z);
    }

    /**
     * Loads the chunk containing the given block. If the chunk does not exist,
     * it will be generated.
     *
     * @param block the Block to check
     * @return chunk
     */
    public Chunk loadChunk(Block block) {
        return loadChunk(block.getX(), block.getY(), block.getZ());
    }

    /**
     * Loads the chunk containing the given block coordinates. If the chunk does
     * not exist, it will be generated.
     *
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     * @return chunk
     */
    public Chunk loadChunk(int x, int y, int z) {
        return loadChunk(x >> 4, z >> 4);
    }

    /**
     * Loads the chunk containing the given chunk coordinates. If the chunk does
     * not exist, it will be generated.
     *
     * @param x a chunk x-coordinate
     * @param z a chunk z-coordinate
     * @return chunk
     */
    public Chunk loadChunk(int x, int z) {
        return world.b.d(x, z).chunk;
    }

    /**
     * Gets the chunk containing the given block. If the chunk is not loaded,
     * the result will be null.
     *
     * @param block the Block to check
     * @return chunk
     */
    public Chunk getChunk(Block block) {
        return getChunk(block.getX(), block.getY(), block.getZ());
    }

    /**
     * Gets the chunk containing the given block coordinates. If the chunk is
     * not loaded, the result will be null.
     *
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     * @return chunk
     */
    public Chunk getChunk(int x, int y, int z) {
        return getChunk(x >> 4, z >> 4);
    }

    /**
     * Gets the chunk containing the given chunk coordinates. If the chunk is
     * not loaded, the result will be null.
     *
     * @param x a chunk x-coordinate
     * @param z a chunk z-coordinate
     * @return chunk
     */
    public Chunk getChunk(int x, int z) {
        if (isChunkLoaded(x, z)) {
            return world.b.d(x, z).chunk;
        } else {
            return null;
        }
    }

    /**
     * Checks if the provided block is being powered through redstone
     *
     * @param block Block to check
     * @return true if the block is being powered
     */
    public boolean isBlockPowered(Block block) {
        return isBlockPowered(block.getX(), block.getY(), block.getZ());
    }

    /**
     * Checks if the provided block is being powered through redstone
     *
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     * @return true if the block is being powered
     */
    public boolean isBlockPowered(int x, int y, int z) {
        return world.B(x, y, z) > 0;
    }

    /**
     * Checks if the provided block is being indirectly powered through redstone
     *
     * @param block Block to check
     * @return true if the block is being indirectly powered
     */
    public boolean isBlockIndirectlyPowered(Block block) {
        return isBlockIndirectlyPowered(block.getX(), block.getY(), block.getZ());
    }

    /**
     * Checks if the provided block is being indirectly powered through redstone
     *
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     * @return true if the block is being indirectly powered
     */
    public boolean isBlockIndirectlyPowered(int x, int y, int z) {
        return world.C(x, y, z);
    }

    /**
     * Set the thunder state
     *
     * @param thundering whether it should thunder
     */
    public void setThundering(boolean thundering) {
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.THUNDER_CHANGE, this, thundering)) {
            return;
        }
        world.x.a(thundering); //could be wrong, hard to differentiate between booleans

        // Thanks to Bukkit for figuring out these numbers
        if (thundering) {
            setThunderTime(world.s.nextInt(12000) + 3600);
        } else {
            setThunderTime(world.s.nextInt(168000) + 12000);
        }
    }

    /**
     * Set the thunder ticks.
     *
     * @param ticks ticks of thunder
     */
    public void setThunderTime(int ticks) {
        world.x.f(ticks);
    }

    /**
     * Sets the rain state.
     *
     * @param raining whether it should rain
     */
    public void setRaining(boolean raining) {
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.WEATHER_CHANGE, this, raining)) {
            return;
        }
        world.x.b(raining);

        // Thanks to Bukkit for figuring out these numbers
        if (raining) {
            setRainTime(world.s.nextInt(12000) + 3600);
        } else {
            setRainTime(world.s.nextInt(168000) + 12000);
        }
    }

    /**
     * Sets the rain ticks.
     *
     * @param ticks ticks of rain
     */
    public void setRainTime(int ticks) {
        world.x.g(ticks);
    }

    /**
     * Returns whether it's thundering
     *
     * @return whether it's thundering
     */
    public boolean isThundering() {
        return world.x.n();
    }

    /**
     * Returns the number of ticks to go till the end of the thunder
     *
     * @return the thunder ticks
     */
    public int getThunderTime() {
        return world.x.o();
    }

    /**
     * Returns whether it's raining
     *
     * @return whether it's raining
     */
    public boolean isRaining() {
        return world.x.p();
    }

    /**
     * Returns the number of ticks to go till the end of the rain
     *
     * @return the rain ticks
     */
    public int getRainTime() {
        return world.x.q();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj != null && obj instanceof World && ((World) obj).world == world;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 89 * hash + (this.world != null ? this.world.hashCode() : 0);
        return hash;
    }

    /**
     * Creates an explosion with the specified power at the given location.
     *
     * @param exploder The entity causing the explosion.
     * @param x
     * @param y
     * @param z
     * @param power The power of the explosion. TNT has a power of 4.
     */
    public void explode(BaseEntity exploder, double x, double y, double z, float power) {
        explode(exploder, x, y, z, power, true);
    }

    /**
     * Creates an explosion with the specified power at the given location.
     *
     * @param exploder The entity causing the explosion.
     * @param x
     * @param y
     * @param z
     * @param power The power of the explosion. TNT has a power of 4.
     * @param doesDamage Whether or not this explosion deals damage.
     */
    public void explode(BaseEntity exploder, double x, double y, double z, float power, boolean doesDamage) {
        world.a(exploder.entity, x, y, z, power, doesDamage);
    }

    /**
     * Creates an explosion with the specified power at the given location.
     *
     * @param exploder The entity causing the explosion.
     * @param x
     * @param y
     * @param z
     * @param power The power of the explosion. TNT has a power of 4.
     * @param doesCauseFires Whether or not this explosion causes fires.
     * @param doesDamage Whether or not this explosion deals damage.
     */
    public void explode(BaseEntity exploder, double x, double y, double z, float power, boolean doesCauseFires, boolean doesDamage) {
        world.a(exploder.entity, x, y, z, power, doesCauseFires, doesDamage);
    }

    /**
     * Gets the random seed of the world.
     *
     * @return seed of the world
     */
    public long getRandomSeed() {
        return world.F();
    }

    /**
     * Sets a new light level with the specified level at the given location.
     *
     * @param x
     * @param y
     * @param z
     * @param newlevel The light level.
     */
    public void setLightLevel(int x, int y, int z, int newlevel) {
        this.getWorld().b(OEnumSkyBlock.b, x, y, z, newlevel);
    }

    /**
     * Gets the light level of the given location.
     *
     * @param x
     * @param y
     * @param z
     * @return Light level of the location.
     */
    public float getLightLevel(int x, int y, int z) {
        return this.getWorld().m(x, y, z);
    }

    /**
     * Updates the light around the given location.
     *
     * @param x
     * @param y
     * @param z
     */
    public void updateLight(int x, int y, int z) {
        for (int x2 = x - 2; x2 <= x + 2; x2++) {
            for (int y2 = y - 2; y2 <= y + 2; y2++) {
                for (int z2 = z - 2; z2 <= z + 2; z2++) {
                    this.getWorld().A(x2, y2, z2);
                }
            }
        }
    }

    public EntityTracker getEntityTracker() {
       return world.getEntityTracker();
    }

    public PlayerManager getPlayerManager() {
        return world.r().getCanaryPlayerManager();
    }

    public void removePlayerFromWorld(Player player) {
        world.f((OEntity)player.getEntity());
    }

    public void addPlayerToWorld(Player player) {
        world.d((OEntity)player.getEntity());
    }

    /**
     * Gets this world's name.
     * @return This world's name.
     */
    public String getName() {
        return world.name;
    }

    /**
     * Get the default game mode for this world.
     * @return The game mode for this world.
     */
    public int getGameMode() {
        return world.x.r().e;
    }

    /**
     * Launch a projectile in this world.
     *
     * @param p The projectile to launch.
     */
    public void launchProjectile(Projectile p) {
        getWorld().d(p.getEntity());
    }

    /**
     * Have lightning strike a location.
     *
     * @param x The x coordinate to strike.
     * @param y The y coordinate to strike.
     * @param z The z coordinate to strike.
     */
    public void strikeLightning(double x, double y, double z) {
        getWorld().c(new OEntityLightningBolt(getWorld(), x, y, z));
    }

    /**
     * Have lightning strike a location.
     *
     * @param location The location to strike.
     */
    public void strikeLightning(Location location) {
        strikeLightning(location.x, location.y, location.z);
    }

    /**
     * Play a sound at the given location
     * @param location location to play the sound
     * @param sound the sound to play
     * @param volume the volume to play the sound
     * @param pitch  the pitch to play the sound at
     * @see Sound
     * @See Location
     */
    public void playSound(Location location, Sound sound, float volume, float pitch){
        playSound(location.x, location.y, location.z, sound, volume, pitch);
    }

    /**
     * Play a sound at the given location
     * @param x x value for the location
     * @param y y value for the location
     * @param z z value of the location
     * @param sound the sound to play
     * @param volume the volume to play the sound
     * @param pitch  the pitch to play the sound at
     * @see Sound
     * @See Location
     */
    public void playSound(double x, double y, double z, Sound sound, float volume, float pitch){
        world.a(x, y, z, sound.getSoundString(), volume, pitch);
    }

    /**
     * Plays a note at a given location.
     *
     * @param location The location to play the note at.
     * @param instrument The instrument to play the note with.
     * @param pitch The pitch of the note (0-24?).
     */
    public void playNote(Location location, Sound.Instrument instrument, int pitch) {
        playNote(location.x, location.y, location.z, instrument, pitch);
    }

    /**
     * Plays a note at a given location.
     *
     * @param x The x coordinate to play the note at.
     * @param y The y coordinate to play the note at.
     * @param z The z coordinate to play the note at.
     * @param instrument The instrument to play the note with.
     * @param pitch The pitch of the note (0-24?).
     */
    public void playNote(double x, double y, double z, Sound.Instrument instrument, int pitch) {
        playNote((int) x, (int) y, (int) z, instrument, pitch);
    }

    /**
     * Plays a note at a given location.
     *
     * @param x The x coordinate to play the note at.
     * @param y The y coordinate to play the note at.
     * @param z The z coordinate to play the note at.
     * @param instrument The instrument to play the note with.
     * @param pitch The pitch of the note (0-24?).
     */
    public void playNote(int x, int y, int z, Sound.Instrument instrument, int pitch) {
        ((OBlockNote) OBlock.r[25]).b(getWorld(), x, y, z, instrument.ordinal(), pitch);
    }

    /**
     * Get the chunks that are currently loaded.
     *
     * @return The loaded chunks in Canary format.
     */
    public List<Chunk> getLoadedChunks() {
        List<OChunk> nativeLoadedChunks = this.getWorld().b.g;
        List<Chunk> loadedChunks = new ArrayList<Chunk>(nativeLoadedChunks.size());
        for (OChunk ochunk : nativeLoadedChunks) {
            loadedChunks.add(ochunk.chunk);
        }
        return loadedChunks;
    }
}
