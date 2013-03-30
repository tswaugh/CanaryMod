import java.util.HashMap;
import java.util.Map;


/**
 * Block.java - Provides some way of making/editing blocks
 *
 * @author James
 */
public class Block {

    /**
     * Type - Used to identify blocks
     */
    public enum Type {
        Air(0), //
        Stone(1), //
        Grass(2), //
        Dirt(3), //
        Cobblestone(4), //
        Wood(5), //
        Sapling(6), //
        Bedrock(7), //
        Water(8), //
        StationaryWater(9), //
        Lava(10), //
        StationaryLava(11), //
        Sand(12), //
        Gravel(13), //
        GoldOre(14), //
        IronOre(15), //
        CoalOre(16), //
        Log(17), //
        Leaves(18), //
        Sponge(19), //
        Glass(20), //
        LapisLazuliOre(21), //
        LapisLazuliBlock(22), //
        Dispenser(23), //
        SandStone(24), //
        NoteBlock(25), //
        Bed(26), //
        PoweredRails(27), //
        DetectorRails(28), //
        StickyPiston(29), //
        Web(30), //
        TallGrass(31), //
        DeadShrub(32), //
        Piston(33), //
        PistonExtended(34), //
        Cloth(35), //
        PistonBlockFiller(36), //
        YellowFlower(37), //
        RedRose(38), //
        BrownMushroom(39), //
        RedMushroom(40), //
        GoldBlock(41), //
        IronBlock(42), //
        DoubleStep(43), //
        Step(44), //
        Brick(45), //
        TNT(46), //
        BookShelf(47), //
        MossyCobblestone(48), //
        Obsidian(49), //
        Torch(50), //
        Fire(51), //
        MobSpawner(52), //
        WoodStairs(53), //
        Chest(54), //
        RedstoneWire(55), //
        DiamondOre(56), //
        Workbench(58), //
        Crops(59), //
        Soil(60), //
        Furnace(61), //
        BurningFurnace(62), //
        SignPost(63), //
        WoodDoor(64), //
        Ladder(65), //
        Rails(66), //
        CobblestoneStairs(67), //
        WallSign(68), //
        Lever(69), //
        StonePlate(70), //
        IronDoor(71), //
        WoodPlate(72), //
        RedstoneOre(73), //
        GlowingRedstoneOre(74), //
        RedstoneTorchOff(75), //
        RedstoneTorchOn(76), //
        StoneButton(77), //
        Snow(78), //
        Ice(79), //
        SnowBlock(80), //
        Cactus(81), //
        Clay(82), //
        Reed(83), //
        Jukebox(84), //
        Fence(85), //
        Pumpkin(86), //
        Netherstone(87), //
        SlowSand(88), //
        LightStone(89), //
        Portal(90), //
        JackOLantern(91), //
        Cake(92), //
        RedstoneRepeaterOff(93), //
        RedstoneRepeaterOn(94), //
        LockedChest(95), //
        Trapdoor(96), //
        SilverBlock(97), //
        StoneBrick(98), //
        HugeBrownMushroom(99), //
        HugeRedMushroom(100), //
        IronBars(101), //
        GlassPane(102), //
        MelonBlock(103), //
        PumpkinStem(104), //
        MelonStem(105), //
        Vine(106), //
        FenceGate(107), //
        BrickStair(108), //
        StonebrickStair(109), //
        Mycelium(110), //
        LilyPad(111), //
        NetherBrick(112), //
        NetherBrickFence(113), //
        NetherBrickStair(114), //
        NetherWart(115), //
        EnchantmentTable(116), //
        BrewingStand(117), //
        Cauldron(118), //
        EndPortal(119), //
        EndPortalFrame(120), //
        EndStone(121), //
        EnderDragonEgg(122), //
        RedstoneLampOff(123), //
        RedstoneLampOn(124), //
        WoodDoubleStep(125), //
        WoodStep(126), //
        CocoaPlant(127), //
        SandstoneStairs(128), //
        EmeraldOre(129), //
        EnderChest(130), //
        TripwireHook(131), //
        Tripwire(132), //
        Emerald(133), //
        SpruceWoodStairs(134), //
        BirchWoodStairs(135), //
        JungleWoodStairs(136), //
        CommandBlock(137), //
        Beacon(138), //
        CobblestoneWall(139), //
        FlowerPot(140), //
        Carrots(141), //
        Potatoes(142), //
        WoodenButton(143), //
        Head(144), //
        Anvil(145),//
        TrappedChest(146),//
        WeightedPressurePlateLight(147),//
        WeightedPressurePlateHeavy(148),//
        DaylightSensor(151),//
        RedstoneBlock(152),//
        NetherQuartzOre(153),//
        Hopper(154),//
        QuartzBlock(155),//
        QuartzStairs(156),//
        ActivatorRail(157),//
        Dropper(158), //
        MinecartHopper(408);

        private int                       id;
        private static Map<Integer, Type> map;

        private Type(int id) {
            this.id = id;
            add(id, this);
        }

        private static void add(int type, Type name) {
            if (map == null) {
                map = new HashMap<Integer, Type>();
            }

            map.put(type, name);
        }

        public int getType() {
            return id;
        }

        public static Type fromId(final int type) {
            return map.get(type);
        }
    }


    /**
     * Face - Used for what face of the block was clicked
     */
    public enum Face {

        /**
         * The top of the block
         */
        Top(1), //
        /**
         * The bottom of the block
         */
        Bottom(0), //
        /**
         * The left (Z-wise) of the block (Faces west)
         */
        Left(3), //
        /**
         * The right (Z-wise) of the block (Faces east)
         */
        Right(2), //
        /**
         * The front (X-wise) of the block (Faces south)
         */
        Front(5), //
        /**
         * The back (X-wise) of the block (Faces north)
         */
        Back(4);
        private final int id;

        private Face(int id) {
            this.id = id;
        }

        /**
         * Returns a Face according to the specified ID
         *
         * @param id
         *            id of face
         * @return face
         */
        public static Face fromId(final int id) {
            for (Face e : Face.values()) {
                if (e.id == id) {
                    return e;
                }
            }
            return null;
        }
    }

    private int  type, x, y, z;
    private Face faceClicked;
    public Type  blockType;
    private int  status, data;
    private World world;

    /**
     * Create a block with no type, x, y or z.
     */
    public Block() {}

    /**
     * Creates a block of specified type
     *
     * @param type
     */
    public Block(int type) {
        this(etc.getServer().getDefaultWorld(), type, 0, 0, 0, 0);
    }

    /**
     * Creates a block of specified type, x, y and z
     *
     * @param type
     *            Type of block
     * @param x
     * @param y
     * @param z
     */
    public Block(int type, int x, int y, int z) {
        this(etc.getServer().getDefaultWorld(), type, x, y, z, 0);
    }

    /**
     * Creates a block of specified world, type, x, y and z
     *
     * @param world
     *            The world the block is in.
     * @param type
     *            Type of block
     * @param x
     * @param y
     * @param z
     */
    public Block(World world, int type, int x, int y, int z) {
        this(world, type, x, y, z, 0);
    }

    /**
     * Creates a block of specified type, x, y, z and data
     *
     * @param type
     *            Type of block
     * @param x
     * @param y
     * @param z
     * @param data
     */
    public Block(int type, int x, int y, int z, int data) {
        this(etc.getServer().getDefaultWorld(), type, x, y, z, data);
    }

    /**
     * Creates a block of specified world, type, x, y, z and data
     *
     * @param type
     *            Type of block
     * @param x
     * @param y
     * @param z
     * @param data
     * @param world
     */
    public Block(World world, int type, int x, int y, int z, int data) {
        this.world = world;
        this.type = type;
        this.blockType = Type.fromId(type);
        this.x = x;
        this.y = y;
        this.z = z;
        this.data = data;
    }

    /**
     * Creates a block of specified world, type x, y, z and data
     *
     * @param world
     *            The world the block is in.
     * @param type
     *            Type of block
     * @param x
     * @param y
     * @param z
     * @param data
     */
    public Block(World world, Type type, int x, int y, int z, int data) {
        this.world = world;
        this.blockType = type;
        this.type = type.getType();
        this.x = x;
        this.y = y;
        this.z = z;
        this.data = data;
    }

    /**
     * Creates a block of specified type, x, y, z and data
     *
     * @param type
     *            Type of block
     * @param x
     * @param y
     * @param z
     * @param data
     */
    public Block(Type type, int x, int y, int z, int data) {
        this(etc.getServer().getDefaultWorld(), type, x, y, z, data);
    }

    /**
     * Creates a block of specified type and data
     *
     * @param type
     *            Type of block
     * @param data
     */
    public Block(Type type, int data) {
        this(etc.getServer().getDefaultWorld(), type, 0, 0, 0, data);
    }

    /**
     * Creates a block of specified type
     *
     * @param type
     *            Type of block
     */
    public Block(Type type) {
        this(etc.getServer().getDefaultWorld(), type, 0, 0, 0, 0);
    }

    /**
     * Creates a block of specified type and location
     *
     * @param type
     *            Type of block
     * @param location
     *            Location in the world
     */
    public Block(Type type, Location location) {
        this(location.getWorld(), type, (int) location.x, (int) location.y, (int) location.z, 0);
    }

    /**
     * Creates a block of specified type, location and data
     *
     * @param type
     *            Type of block
     * @param location
     *            Location in the world
     * @param data
     */
    public Block(Type type, Location location, int data) {
        this(location.getWorld(), type, (int) location.x, (int) location.y, (int) location.z, data);
    }

    /**
     * Creates a block of specified type, location and data
     *
     * @param type
     *            Type of block
     * @param location
     *            Location in the world
     * @param data
     */
    public Block(int type, Location location, int data) {
        this(location.getWorld(), type, (int) location.x, (int) location.y, (int) location.z, data);
    }

    /**
     * Creates a block of specified type, location and data
     *
     * @param type
     *            Type of block
     * @param location
     *            Location in the world
     */
    public Block(int type, Location location) {
        this(location.getWorld(), type, (int) location.x, (int) location.y, (int) location.z, 0);
    }

    /**
     * Creates a block at the specified location.
     *
     * @param location
     *            Location in the world
     */
    public Block(Location location) {
        this(location.getWorld(), 0, (int) location.x, (int) location.y, (int) location.z, 0);
    }

    /**
     * Type of block
     *
     * @return type
     */
    public int getType() {
        return this.type;
    }

    /**
     * Set type of block
     *
     * @param type
     */
    public void setType(int type) {
        this.blockType = Type.fromId(type);
        this.type = type;
    }

    /**
     * Gets X location
     *
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Sets X location
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets Y location
     *
     * @return y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets Y location
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets Z location
     *
     * @return z
     */
    public int getZ() {
        return this.z;
    }

    /**
     * Sets Z location
     *
     * @param z
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * Gets Location
     *
     * @return location
     */
    public Location getLocation() {
        return new Location(this.world, this.x, this.y, this.z);
    }

    /**
     * Sets Location
     *
     * @param location
     */
    public void setLocation(Location location) {
        this.world = location.getWorld();
        this.x = (int) location.x;
        this.y = (int) location.y;
        this.z = (int) location.z;
    }

    /**
     * If this block was clicked, this will return the face that was clicked.
     *
     * @return face clicked
     */
    public Face getFaceClicked() {
        return faceClicked;
    }

    /**
     * Sets the face that was clicked
     *
     * @param faceClicked
     *            face clicked
     */
    public void setFaceClicked(Face faceClicked) {
        this.faceClicked = faceClicked;
    }

    /**
     * Returns the status of this block.
     *
     * @return The current status of the block, value depends on what hook you
     * called.
     *
     * @see PluginListener#onBlockDestroy(Player, Block)
     * @see PluginListener#onExplode(Block)
     * @see PluginListener#onIgnite(Block, Player)
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Sets the current destruction status of this block.
     * For CanaryMod internal use only!
     *
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Returns this block's data
     *
     * @return
     */
    public int getData() {
        return this.data;
    }

    /**
     * Sets this block's data
     *
     * @param data
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * Returns this block's world
     * @return world
     */
    public World getWorld() {
        return this.world;
    }

    /**
     * Sets this block's world
     * @param world The new world
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Updates this block to the server.
     */
    public void update() {
        this.world.setBlock(this);
    }

    /**
     * Returns the block at the given Face
     *
     * @param face
     *            the block face of which to return
     * @return Block at the specified Face
     */
    public Block getFace(Face face) {
        if (face == null) {
            return null;
        }

        switch (face) {
            case Front:
                return getRelative(1, 0, 0);

            case Back:
                return getRelative(-1, 0, 0);

            case Top:
                return getRelative(0, 1, 0);

            case Bottom:
                return getRelative(0, -1, 0);

            case Left:
                return getRelative(0, 0, 1);

            case Right:
                return getRelative(0, 0, -1);
        }

        return null;
    }

    /**
     * Synchronises this Block with the server, abandoning all local changes and
     * refreshing the data with the current actual values
     */
    public void refresh() {
        this.type = world.getBlockIdAt(x, y, z);
        this.data = world.getBlockData(x, y, z);
        this.status = 0;
    }

    /**
     * Finds a Block relative to this Block
     *
     * @param x
     *            amount to shift the x coordinate
     * @param y
     *            amount to shift the y coordinate
     * @param z
     *            amount to shift the z coordinate
     *
     * @return Block at the requested location
     */
    public Block getRelative(int x, int y, int z) {
        return this.world.getBlockAt(getX() + x, getY() + y, getZ() + z);
    }

    /**
     * Checks if this block is being powered through redstone
     *
     * @return true if the block is being powered
     */
    public boolean isPowered() {
        return this.world.isBlockPowered(this);
    }

    /**
     * Checks if this block is being indirectly powered through redstone
     *
     * @return true if the block is being indirectly powered
     */
    public boolean isIndirectlyPowered() {
        return this.world.isBlockIndirectlyPowered(this);
    }

    /**
     * Returns a String value representing this Block
     *
     * @return String representation of this block
     */
    @Override
    public String toString() {
        return String.format("Block[type=%d, x=%d, y=%d, z=%d, world=%s, dim=%d]", this.type, this.x, this.y, this.z, this.world.getName(), this.world.getType().getId());
    }

    /**
     * Tests the given object to see if it equals this object
     *
     * @param obj
     *            the object to test
     * @return true if the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Block other = (Block) obj;

        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.z != other.z) {
            return false;
        }
        if (!this.world.equals(other.world)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a semi-unique hashcode for this block
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;

        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        hash = 97 * hash + this.z;
        return hash;
    }

    /**
     * Returns whether this block is cloth
     *
     * @return true if this block us cloth
     */
    public boolean isCloth() {
        return this.blockType == Type.Cloth;
    }

    /**
     * Get this block's {@link Cloth.Color Color} (if it is cloth)
     *
     * @return the {@link Cloth.Color Color}
     */
    public Cloth.Color getColor() {
        if (!isCloth()) {
            return null;
        } else {
            return Cloth.Color.getColor(this.data);
        }
    }

    /**
     * Returns this block as a complex block. null if this is not a complex block.
     *
     * @return
     */
    public ComplexBlock getComplex() {
        return getWorld().getComplexBlock(this);
    }

    /**
     * Simulate a right click on the block.
     * Toggles levers, pushes buttons, opens doors, etc.
     *
     * @param clicker The player that clicked the block (can be null for things like levers, buttons, etc, but should be set for things like chests.
     * @return Whether or not the block responded.
     */
    public boolean rightClick(Player clicker) {
        return OBlock.r[getType()].a(getWorld().getWorld(), getX(), getY(), getZ(), clicker != null ? clicker.getEntity() : null, 0, 0, 0, 0); // last four parameters aren't even used by lever or button
    }

    /**
     * Gets the block that this block is attached to.
     * Useful for levers, buttons, torches, etc.
     * Can have unexpected results on blocks that don't attach to other blocks (eg. dirt, stone).
     *
     * @return Block
     */
    public Block getAttachedBlock() {
        switch(data % 8) {
            case 0: // usually above
            case 7:
                return getRelative(0, 1, 0);
            case 1: // west
                return getRelative(-1, 0, 0);
            case 2: // east
                return getRelative(1, 0, 0);
            case 3: // north
                return getRelative(0, 0, -1);
            case 4: // south
                return getRelative(0, 0, 1);
            case 5: // usually below
            case 6:
                return getRelative(0, -1, 0);
            default: // should never occur
                return null;
        }
    }
}
