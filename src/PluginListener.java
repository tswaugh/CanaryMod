import java.util.HashSet;
import java.util.List;

/**
 * PluginListener.java - Extend this and register it to listen to specific hooks.
 *
 * @author Maine
 */
public class PluginListener {

    /**
     * Priority - Used for priority for plugin listeners
     */
    public enum Priority {

        /**
         * Highly critical for hooks that absolutely must occur before any others. Use carefully.
         */
        CRITICAL, //
        /**
         * May block/interrupt/undo the action, but prefer MEDIUM
         */
        HIGH, //
        /**
         * Preferred priority for blocking/interrupting/undoing the action
         */
        MEDIUM, //
        /**
         * Must not block/interrupt/undo the action
         */
        LOW;
    }

    /**
     * Called when a player moves from one block to another
     *
     * @param player
     *            player moving
     * @param from
     *            previous block location
     * @param to
     *            current block location
     */
    public void onPlayerMove(Player player, Location from, Location to) {
    }

    /**
     * Called when a player teleports from one location to another
     *
     * @param player
     *            player moving
     * @param from
     *            previous block location
     * @param to
     *            current block location
     * @return false if you want the player to teleport.
     */
    public boolean onTeleport(Player player, Location from, Location to) {
        return false;
    }

    /**
     * Called during the early login process to check whether or not to kick the player
     *
     * @param user
     * @return kick reason. null if you don't want to kick the player.
     *
     * @deprecated use {@link #onLoginChecks(java.lang.String, java.lang.String) } instead.
     */
    @Deprecated
    public String onLoginChecks(String user) {
        return null;
    }

    /**
     * Called during the early login process to check whether or not to kick the player
     *
     * @param user
     * @param IP
     * @return kick reason. null if you don't want to kick the player.
     * @deprecated Use onLoginChecks(HookParametersLogincheck) instead!
     *
     */
    @Deprecated
    public String onLoginChecks(String user, String IP){
        return onLoginChecks(user);
    }

    /**
     * Called during the early login process to check whether or not to kick the player
     * and optionally set his world to spawn in (for multiworld plugins!)
     * @param hook Use setKickReason(String) to specify a kick reason or leave/set it null to not kick the player
     * @return
     */
    public HookParametersLogincheck onLoginChecks(HookParametersLogincheck hook) {
        String kickReason = onLoginChecks(hook.getPlayerName(), hook.getIp());
        if(kickReason != null) {
            hook.setKickReason(kickReason);
        }
        return hook;
    }

    /**
     * Called during the later login process
     *
     * @param player
     */
    public void onLogin(Player player) {
    }

    /**
     * Called on player disconnect
     *
     * @param player
     */
    public void onDisconnect(Player player) {
    }

    /**
     * Called when a player talks. If you return true the message won't be sent out.
     *
     * @param player
     * @param message
     * @return false if you want the message to be sent.
     *
     * @deprecated use onChat(Player, StringBuilder) to get the information
     */
    @Deprecated
    public boolean onChat(Player player, String message) {
        return false;
    }

    /**
     * Called when a player talks. If you return true the message won't be sent out.
     *
     * @param player
     * @param sbMessage
     * @return false if you want the message to be sent.
     *
     * @deprecated use HookParameters onChat(HookParametersChat) to get the information
     */
    @Deprecated
    public boolean onChat(Player player, StringBuilder sbMessage) {
        return onChat(player, sbMessage.toString());
    }

    /**
     * Called when a player talks.
     *
     * @param parametersChat
     *
     * @return parametersChat   use parametersChat.setCanceled(true) to stop the message
     */
    public HookParametersChat onChat(HookParametersChat parametersChat){
        if(onChat(parametersChat.getPlayer(), parametersChat.getMessage())){
            parametersChat.setCanceled(true);
        }
        return parametersChat;
    }

    /**
     * Called before the command is parsed. Return true if you don't want the command to be parsed.
     *
     * @param player
     * @param split
     * @return false if you want the command to be parsed.
     */
    public boolean onCommand(Player player, String[] split) {
        return false;
    }

    /**
     * Called before the console command is parsed. Return true if you don't want the server command to be parsed by the server.
     *
     * @param split
     * @return false if you want the command to be parsed.
     */
    public boolean onConsoleCommand(String[] split) {
        return false;
    }

    /**
     * Called when a player is banned
     *
     * @param mod
     *            moderator that's banning
     * @param player
     *            player being banned
     * @param reason
     *            reason for ban
     */
    public void onBan(Player mod, Player player, String reason) {
    }

    /**
     * Called when a player is IP banned
     *
     * @param mod
     *            moderator that's banning
     * @param player
     *            player being banning
     * @param reason
     *            for IP ban
     */
    public void onIpBan(Player mod, Player player, String reason) {
    }

    /**
     * Called when a player is kicked
     *
     * @param mod
     *            moderator that's kicking
     * @param player
     *            player being kicked
     * @param reason
     *            reason for kick
     */
    public void onKick(Player mod, Player player, String reason) {
    }

    /**
     * Called when someone presses right click aimed at a block. You can intercept this to add your own right click actions to different item types (see itemInHand)
     *
     * @param player
     * @param blockPlaced
     * @param blockClicked
     * @param itemInHand
     * @return false if you want the action to go through
     *
     * @deprecated use {@link #onBlockRightClick(Player, Block, Item)} to get the information
     * @see #onBlockRightClick(Player, Block, Item)
     * @see #onBlockPlace(Player, Block, Block, Item)
     * @see #onItemUse(Player, Block, Block, Item)
     */
    @Deprecated
    public boolean onBlockCreate(Player player, Block blockPlaced, Block blockClicked, int itemInHand) {
        return false;
    }

    /**
     * Called when a person left clicks a block. Block status: 0 = Started Digging, 2 = Stopped digging.
     *
     * @param player
     * @param block
     * @return
     */
    public boolean onBlockDestroy(Player player, Block block) {
        return false;
    }

    /**
     * Called when a person actually breaks the block.
     *
     * @param player
     * @param block
     * @return
     */
    public boolean onBlockBreak(Player player, Block block) {
        return false;
    }

    /**
     * Called when a player swings their arm, aka left clicks (even if no block is in front of them)
     *
     * @param player
     *            player swinging
     */
    public void onArmSwing(Player player) {
    }

    /**
     * Called when a player drops an item.
     *
     * @param player
     *            player who dropped the item
     * @param item
     *            item that was dropped
     * @deprecated Use onItemDrop(Player, ItemEntity) instead. You can still get the Item via ItemEntity.getItem()
     * @return true if you don't want the dropped item to be spawned in the world
     */
    @Deprecated
    public boolean onItemDrop(Player player, Item item) {
        return false;
    }

    /**
     * Called when a player drops an item or when a player is given an item via
     * native Minecraft methods, such as the "give" command.
     *
     * @param player
     *            player who dropped the item
     * @param item
     *            item that was dropped
     * @return true if you don't want the dropped item to be spawned in the world
     */
    public boolean onItemDrop(Player player, ItemEntity item) {
        return onItemDrop(player, item.getItem());
    }

    /**
     * Called when a player picks up an item.
     *
     * @param player
     *            player who picked up the item
     * @param item
     *            item that was picked up
     * @deprecated Use onItemPickUp(Player, ItemEntity) instead. You can still get the Item via ItemEntity.getItem()
     * @return true if you want to leave the item where it was
     */
    @Deprecated
    public boolean onItemPickUp(Player player, Item item) {
        return false;
    }

    /**
     * Called when a player picks up an item.
     *
     * @param player
     *            player who picked up the item
     * @param item
     *            item that was picked up
     * @return true if you want to leave the item where it was
     */
    public boolean onItemPickUp(Player player, ItemEntity item) {
        return onItemPickUp(player, item.getItem());
    }

    /**
     * Called when an item begins touching the ground
     *
     * @param item
     *            item that began touching the ground
     * @return true to destroy the item
     */
    public boolean onItemTouchGround(ItemEntity item) {
        return false;
    }

    /**
     * Called when either a lava block or a lighter tries to light something on
     * fire. block status depends on the light source: 1 = lava. 2 = lighter
     * (flint + steel). 3 = spread (dynamic spreading of fire). 4 = fire
     * destroying a block. 5 = lightning
     *
     * @param block
     *            block that the fire wants to spawn in.
     * @param player
     *            player
     * @return true if you dont want the fire to ignite.
     */
    public boolean onIgnite(Block block, Player player) {
        return false;
    }

    /**
     * Called when a dynamite block or a creeper is triggered. block status
     * depends on explosive compound: 1 = dynamite. 2 = creeper. 3 = ghast
     * fireball. 4 = wither skull
     *
     * @param block
     *            Dynamite block/creeper/ghast fireball/wither skull location block.
     *
     * @deprecated Use onExplosion(Block,BaseEntity,List) instead. You can still
     *             use block.getStatus to get what exploded, as well as using
     *             OEntity.
     * @return true if you dont the block to explode.
     */
    @Deprecated
    public boolean onExplode(Block block) {
        return false;
    }

    /**
     * Called when a tnt block, creeper, ghast fireball, or wither skull explodes. Block
     * status depends on the entity exploding: 1 = tnt. 2 = creeper. 3 = ghast
     * fireball. 4 = wither skull
     *
     * You can also get the explosion type from OEntity using instanceof.
     *
     * @param block
     *            The block location where the explosion occurred.
     *
     * @param entity
     *            The entity that caused the explosion(tnt, creeper, fireball,
     *            skull)
     *
     * @param blocksaffected
     *            The blocks affected by the explosion in a hashset.
     *
     * @deprecated Use onExplosion(Block,BaseEntity,List) instead. You can still
     *             use block.getStatus to get what exploded, as well as using
     *             Entity.
     * @return true if you don't want the explosion to occur.
     */
    @Deprecated
    public boolean onExplode(Block block, OEntity entity, HashSet blocksaffected) {
        return onExplode(block);
    }

    /**
     * Called when a tnt block, creeper, or ghast fireball explodes. Block
     * status depends on the entity exploding: 1 = tnt. 2 = creeper. 3 = ghast
     * fireball. 4 = wither skull
     *
     * You can also get the explosion type from BaseEntity using
     * BaseEntity.getName().
     *
     * @param block
     *            The block location where the explosion occurred.
     *
     * @param entity
     *            The entity that caused the explosion(tnt, creeper, fireball,
     *            skull)
     *
     * @param blocksaffected
     *            The blocks affected by the explosion in a list.
     *            Removing the blocks from the list marks them as unaffected,
     *            thus not being destroyed.
     *
     * @return true if you don't want the explosion to occur.
     */
    public boolean onExplosion(Block block, BaseEntity entity, List blocksaffected) {
        return false;
    }

    /**
     * Called when fluid wants to flow to a certain block. (10 & 11 for lava and 8 & 9 for water)
     *
     * @param blockFrom
     *            the block where the fluid came from. (blocktype = fluid type)
     * @param blockTo
     *            the block where fluid wants to flow to.
     *
     *
     * @return true if you dont want the substance to flow.
     */
    public boolean onFlow(Block blockFrom, Block blockTo) {
        return false;
    }

    /**
     * @param mob
     *            Mob attempting to spawn.
     * @return true if you dont want mob to spawn.
     */
    public boolean onMobSpawn(Mob mob) {
        return false;
    }

    /**
     * Called when a living object is attacked. tip: Use isMob() and isPlayer() and getPlayer().
     *
     * @param type
     *            type of damage dealt.
     * @param attacker
     *            object that is attacking.
     * @param defender
     *            object that is defending.
     * @param amount
     *            amount of damage dealt.
     *
     * @return true to prevent damage
     * @deprecated use {@link #onDamage(HookParametersDamage)} instead
     */
    @Deprecated
    public boolean onDamage(PluginLoader.DamageType type, BaseEntity attacker, BaseEntity defender, int amount) {
        return false;
    }

    /**
     * Called when an entity is damaged.
     * @param hpDamage The {@link HookParametersDamage}-instance containing the damage data.
     * @return The (un)changed <tt>hpDamage</tt>
     */
    public HookParametersDamage onDamage(HookParametersDamage hpDamage) {
        if (onDamage(PluginLoader.DamageType.fromDamageSource(hpDamage.getDamageSource().getDamageSource()), hpDamage.getAttacker(), hpDamage.getDefender(), hpDamage.getDamageAmount())) {
            hpDamage.setCanceled();
        }
        return hpDamage;
    }

    /**
     * Called when a players health changes.
     *
     * @param player
     *            the player which health is changed.
     * @param oldValue
     *            old lives value
     * @param newValue
     *            new lives value
     * @return return true to stop the change.
     */
    public boolean onHealthChange(Player player, int oldValue, int newValue) {
        return false;
    }

    /**
     * Called whenever a redstone source (wire, switch, torch) changes its current.
     *
     * Standard values for wires are 0 for no current, and 15 for a strong current.
     * Default behaviour for redstone wire is to lower the current by one every block.
     *
     * For other blocks which provide a source of redstone current, the current
     * value will be 1 or 0 for on and off respectively, except for weighted
     * pressure plates. Those still range from 0 to 15 for no to strong current.
     *
     * @param block
     * @param oldLevel
     *            the old current
     * @param newLevel
     *            the new current
     * @return the new current to use (newLevel to leave as-is)
     */
    public int onRedstoneChange(Block block, int oldLevel, int newLevel) {
        return newLevel;
    }

    /**
     * Called whenever a piston is extended
     *
     * @param block
     *            the piston's block
     * @param isSticky
     *            true if the piston is sticky
     * @return false if you want the piston to attempt expanding
     */
    public boolean onPistonExtend(Block block, boolean isSticky) {
        return false;
    }

    /**
     * Called whenever a piston is retracted
     *
     * @param block
     *            the piston's block
     * @param isSticky
     *            Whether the piston is sticky
     * @return false if you want the piston to attempt retracting the attached block.
     */
    public boolean onPistonRetract(Block block, boolean isSticky) {
        return false;
    }

    /**
     * Called when the game is checking the physics for a certain block. This method is called frequently whenever a nearby block is changed, or if the block has just been placed. Currently the only supported blocks are sand, gravel and portals.
     *
     * @param block
     *            Block which requires special physics
     * @param placed
     *            True if block was just placed
     * @return true if you do want to stop the default physics for this block
     */
    public boolean onBlockPhysics(Block block, boolean placed) {
        return false;
    }

    /**
     * Called when you place a vehicle.
     *
     * @param vehicle
     *            the vehicle placed
     */
    public void onVehicleCreate(BaseVehicle vehicle) {
    }

    /**
     * Called when vehicle receives damage
     *
     * @param vehicle
     * @param attacker
     *            entity that dealt the damage
     * @param damage
     * @return false to set damage
     */
    public boolean onVehicleDamage(BaseVehicle vehicle, BaseEntity attacker, int damage) {
        return false;
    }

    /**
     * Called when a vehicle changes speed
     *
     * @param vehicle
     *            the vehicle
     */
    public void onVehicleUpdate(BaseVehicle vehicle) {
    }

    /**
     * Called when a collision occurs with a vehicle and an entity.
     *
     * @param vehicle
     *            the vehicle
     * @param collisioner
     * @return false to ignore damage
     */
    public Boolean onVehicleCollision(BaseVehicle vehicle, BaseEntity collisioner) {
        return false;
    }

    /**
     * Called when a vehicle is destroyed
     *
     * @param vehicle
     *            the vehicle
     */
    public void onVehicleDestroyed(BaseVehicle vehicle) {
    }

    /**
     * Called when a player enter or leaves a vehicle
     *
     * @param vehicle
     *            the vehicle
     * @param player
     *            the player
     */
    public void onVehicleEnter(BaseVehicle vehicle, HumanEntity player) {
    }

    /**
     * Called when a vehicle changes block
     *
     * @param vehicle
     *            the vehicle
     * @param x
     *            coordinate x
     * @param y
     *            coordinate y
     * @param z
     *            coordinate z
     */
    public void onVehiclePositionChange(BaseVehicle vehicle, int x, int y, int z) {
    }

    /**
     * Called when a player uses an item (rightclick with item in hand)
     *
     * @param player
     *            the player
     * @param blockPlaced
     *            where a block would end up when the item was a bucket
     * @param blockClicked
     * @param itemInHand
     *            the item being used (in hand)
     * @return true to prevent using the item.
     */
    public boolean onItemUse(Player player, Block blockPlaced, Block blockClicked, Item itemInHand) {
        return false;
    }

    /**
     * Called when someone places a block. Return true to prevent the placement.
     *
     * @param player
     * @param blockPlaced
     * @param blockClicked
     * @param itemInHand
     * @return true if you want to undo the block placement
     */
    public boolean onBlockPlace(Player player, Block blockPlaced, Block blockClicked, Item itemInHand) {
        return false;
    }

    /**
     * Called when someone presses right click aimed at a block. You can intercept this to add your own right click actions to different item types (see itemInHand)
     *
     * @deprecated Use {@link #onBlockRightClick(Player, Block, Item) } instead.
     * @param player
     * @param blockClicked
     * @param itemInHand
     */
    @Deprecated
    public void onBlockRightClicked(Player player, Block blockClicked, Item itemInHand) {
    }

    /**
     * Called when someone presses right click aimed at a block. You can intercept this to add your own right click actions to different item types (see itemInHand)
     *
     * @param player
     * @param blockClicked
     * @param itemInHand
     * @return true if you wish to cancel the click
     */
    public boolean onBlockRightClick(Player player, Block blockClicked, Item itemInHand) {
        onBlockRightClicked(player, blockClicked, itemInHand);
        return false;
    }

    /**
     * Called when water or lava tries to populate a block, you can prevent crushing of torches, railways, flowers etc. You can alternatively allow to let normally solid blocks be crushed.
     *
     * @param currentState
     *            the current tristate, once it's set to a non DEFAULT_ACTION it is final.
     * @param liquidBlockId
     *            the type of the attacking block
     * @param targetBlock
     *            the block to be destroyed
     * @return final after a non DEFAULT_ACTION
     */
    public PluginLoader.HookResult onLiquidDestroy(PluginLoader.HookResult currentState, int liquidBlockId, Block targetBlock) {
        return PluginLoader.HookResult.DEFAULT_ACTION;
    }

    /**
     * Called when an entity (attacker) tries to hurt a player (defender). Returning 'true' prevents all damage, returning 'false' lets the game handle it. Remember that the damage will be lessened by the amount of {@link LivingEntity#getLastDamage()} the defender has.
     *
     * @param attacker
     *            the giver
     * @param defender
     *            the taker
     * @param amount
     *            of damage the entity tries to do
     * @return
     */
    public boolean onAttack(LivingEntity attacker, LivingEntity defender, Integer amount) {
        return false;
    }

    /**
     * Called when a player attempts to open an inventory; whether it's a workbench, a chest or their own player inventory
     *
     * @param player
     *            user who attempted to open the inventory
     * @param inventory
     *            the inventory that they are attempting to open
     * @return
     * @deprecated Use onOpenInventory(HookParametersOpenInventory) instead.
     */
    public boolean onOpenInventory(Player player, Inventory inventory) {
        return false;
    }

    /**
     * Called when a player attempts to open an inventory; whether it's a workbench, a chest or their own player inventory
     *
     * @param openInventory The parameter object for this hook
     * @return true if to disable open inventory
     */
    public boolean onOpenInventory(HookParametersOpenInventory openInventory) {
        if (onOpenInventory(openInventory.getPlayer(), openInventory.getInventory()))
        {
            return true;
        }
        return false;
    }

    /**
     * Called when a player closes an inventory; whether it's a workbench, a chest or their own player inventory
     *
     * @param closeInventory The parameter object for this hook
     */
    public void onCloseInventory(HookParametersCloseInventory closeInventory) {
    }

    /**
     * Called when a sign is shown to a player, most often when they come into range of a sign.
     *
     * @param player
     *            Player who this sign is being shown to
     * @param sign
     *            Sign which is being shown to the player
     */
    public void onSignShow(Player player, Sign sign) {
    }

    /**
     * Called when a sign is changed by a player (Usually, when they first place it)
     *
     * @param player
     *            Player who changed the sign
     * @param sign
     *            Sign which had changed
     * @return true if you wish to cancel this change
     */
    public boolean onSignChange(Player player, Sign sign) {
        return false;
    }

    /**
     * Called when a leaf block is about to decay.
     *
     * @param block
     *            The leaf block about to decay
     * @return true if you wish to stop the block from decaying
     */
    public boolean onLeafDecay(Block block) {
        return false;
    }

    /**
     * Called when a player attempt to tame a wolf
     *
     * @param player
     *            Player who is tries to tame the wolf
     * @param wolf
     *            Wolf being tamed
     * @param shouldSucceed
     *            True if the taming should have succeeded normally
     * @return Whether the taming should succeed (ALLOW_ACTION), fail (PREVENT_ACTION), or do random as always (DEFAULT_ACTION)
     */
    public PluginLoader.HookResult onTame(Player player, Mob wolf, boolean shouldSucceed) {
        return PluginLoader.HookResult.DEFAULT_ACTION;
    }

    /**
     * Called when lightning strikes an entity
     *
     * @param entity
     *            The entity that's being struck
     * @return true if you want to cancel the lightning striking this entity
     */
    public boolean onLightningStrike(BaseEntity entity) {
        return false;
    }

    /**
     * Called when the weather changes (rain/snow)
     *
     * @deprecated Use {@link #onWeatherChange(World, boolean) } instead.
     * @param newValue
     *            The new weather value
     * @return true to prevent the weather from changing
     */
    @Deprecated
    public boolean onWeatherChange(boolean newValue) {
        return false;
    }

    /**
     * Called when the weather changes (rain/snow)
     *
     * @param world
     *            The {@link World} the weather changes in
     * @param newValue
     *            The new weather value
     * @return true to prevent the weather from changing
     */
    public boolean onWeatherChange(World world, boolean newValue) {
        if (world.equals(etc.getServer().getDefaultWorld())) {
            onWeatherChange(newValue);
        }
        return false;
    }

    /**
     * Called when the thunder changes (NOT when lightning strikes)
     *
     * @deprecated Use {@link #onThunderChange(World, boolean) } instead.
     * @param newValue
     *            The new thunder value
     * @return true to prevent the thunder from changing
     */
    @Deprecated
    public boolean onThunderChange(boolean newValue) {
        return false;
    }

    /**
     * Called when the thunder changes (NOT when lightning strikes)
     *
     * @param world
     *            The {@link World} the thunder changes in
     * @param newValue
     *            The new thunder value
     * @return true to prevent the thunder from changing
     */
    public boolean onThunderChange(World world, boolean newValue) {
        if (world.equals(etc.getServer().getDefaultWorld())) {
            return onThunderChange(newValue);
        }
        return false;
    }

    /**
     * Called when a player uses a portal
     *
     * @param player
     * @param from
     *            The world the player wants to leave
     * @return true to prevent the player from using the portal
     * @deprecated use onPortalUse(Player, Location) instead
     */
    @Deprecated
    public boolean onPortalUse(Player player, World from) {
        return false;
    }

    /**
     * Called when a player uses a portal
     *
     * @param player
     *            the player using the portal
     * @param to
     *            The location to where the player is going
     * @return true to prevent the player from using the portal
     */
    public boolean onPortalUse(Player player, Location to) {
        return onPortalUse(player, player.getWorld());
    }

    /**
     * Called when the time changes
     *
     * @param world
     *            The {@link World} the time changes in
     * @param newValue
     *            The new time value
     * @return true to prevent the time from changing
     */
    public boolean onTimeChange(World world, long newValue) {
        return false;
    }

    /**
     * Called when a player tries to use a command.
     *
     * @param player
     *            Player who wants to use the command.
     * @param command
     *            The command itself.
     * @return Whether the player is allowed (ALLOW_ACTION), prohibited (PREVENT_ACTION), or when another plugin should decide (DEFAULT_ACTION)
     */
    public PluginLoader.HookResult canPlayerUseCommand(Player player, String command) {
        return PluginLoader.HookResult.DEFAULT_ACTION;
    }

    /**
     * Called before chunk is unloaded
     *
     * @param chunk
     */
    public void onChunkUnload(Chunk chunk) {
    }

    /**
     * Called after a chunk was loaded
     *
     * @param chunk
     */
    public void onChunkLoaded(Chunk chunk) {
    }

    /**
     * Called when a chunk is generated. Tips: Return a byte[32768] if you want to generate a new chunk. The index of the block is: (x * 16 + z) * 128 + y where 0<=x<16, 0<=z<16 and 0<=y<128 Use world.getRandomSeed() or (x * 0x4f9939f508L + z * 0x1ef1565bd5L) as seed for your Random.
     *
     * @param x
     * @param z
     * @param world
     * @return null if you want the default world generator to create the chunk.
     */
    public byte[] onChunkCreate(int x, int z, World world) {
        return null;
    }

    /**
     * Called when a spawnpoint is generated. If you don't implement this when overriding onChunkCreate, this could cause an OutOfMemoryError.
     *
     * @param world
     * @return The location of the spawnpoint. The values are rounded to integers, elevation and pitch are ignored.
     */
    public Location onSpawnpointCreate(World world) {
        return null;
    }

    /**
     * Called after a chunk was generated
     *
     * @param chunk
     */
    public void onChunkCreated(Chunk chunk) {
    }

    /**
     * Called when a portal is created.
     *
     * @param blocks
     *            Array of portal blocks that were created.
     * @return true if to prevent the creation of the portal
     */
    public boolean onPortalCreate(Block[][] blocks) {
        return false;
    }

    /**
     * Called when a portal is destroyed.
     *
     * @param blocks
     *            Array of portal blocks that were created.
     * @return true if to prevent destruction of the portal
     */
    public boolean onPortalDestroy(Block[][] blocks) {
        return false;
    }

    /**
     * Called when a player respawns
     * @param player
     *          Player that respawns
     * @deprecated Use {@link #onPlayerRespawn(Player, Location) } instead.
     */
    @Deprecated
    public void onPlayerRespawn(Player player) {}

    /**
     * Called when a player respawns
     * @param player
     *          Player that respawns
     * @param spawnLocation
     *          Location the player will spawn at
     */
    public void onPlayerRespawn(Player player, Location spawnLocation)
    {
        onPlayerRespawn(player);
    }

    /**
     * Called when an entity despawns
     *
     * @param entity
     *            The entity that despawns
     * @return true if to prevent despawning
     */
    public boolean onEntityDespawn(BaseEntity entity) {
        return false;
    }

    /**
     * Called when an enderman picks up a block
     *
     * @param entity
     *            The enderman that picks up the block
     * @param block
     *            The block the enderman picks up
     * @return true if to prevent the enderman from pickup up the block
     */
    public boolean onEndermanPickup(Enderman entity, Block block) {
        return false;
    }

    /**
     * Called when an enderman drops a block
     *
     * @param entity
     *            The enderman that drops the block
     * @param block
     *            The block the enderman drops
     * @return true if to prevent the enderman from dropping the block
     */
    public boolean onEndermanDrop(Enderman entity, Block block) {
        return false;
    }

    /**
     * Called when a player milks a cow
     *
     * @param player
     *            The milking player
     * @param cow
     *            The milked cow
     * @deprecated Use {@link #onEntityRightClick(Player, BaseEntity, Item) } instead.
     * @return true if to prevent the player from milking the cow
     */
    @Deprecated
    public boolean onCowMilk(Player player, Mob cow) {
        return false;
    }

    /**
     * Called when a player tries to eat food
     *
     * @param player
     *            The player
     * @param item
     *            The eaten item
     * @return true to prevent the player eat the item
     */
    public boolean onEat(Player player, Item item) {
        return false;
    }

    /**
     * Called when a player food level changes
     *
     * @param player
     *            the player
     * @param oldFoodLevel
     *            the current food level
     * @param newFoodLevel
     *            the new food level
     *
     * @return the new food level
     */
    public int onFoodLevelChange(Player player, int oldFoodLevel, int newFoodLevel) {
        return newFoodLevel;
    }

    /**
     * Called when a player food exhaustion level changes
     *
     * @param player
     * @param oldLevel
     * @param newLevel
     *
     * @return new Level of FoodExhaustion
     */
    public Float onFoodExhaustionChange(Player player, Float oldLevel, Float newLevel) {
        return newLevel;
    }

    /**
     * Called when a player food saturation level changes
     *
     * @param player
     * @param oldLevel
     * @param newLevel
     *
     * @return new Level of FoodExhaustion
     */
    public Float onFoodSaturationChange(Player player, Float oldLevel, Float newLevel) {
        return newLevel;
    }

    /**
     * Called when a potion effect is applied to the player
     *
     * @param entity
     *            the affected entity
     * @param potionEffect
     *            the potion being effect applied
     *
     * @return modified potionEffect or null for no effect
     */
    public PotionEffect onPotionEffect(LivingEntity entity, PotionEffect potionEffect) {
        return potionEffect;
    }

    /**
     * Called when a potion effect is finished on an entity
     * @param entity entity the effect applies to
     * @param potionEffect effect the potion is
     */
    public void onPotionEffectFinished(LivingEntity entity, PotionEffect potionEffect){

    }

    /**
     * Called when a players experience changes.
     *
     * @param player
     *            the player which health is changed.
     * @param oldValue
     *            old experience value
     * @param newValue
     *            new experience value
     * @return return true to stop the change.
     */
    public boolean onExpChange(Player player, int oldValue, int newValue) {
        return false;
    }

    /**
     * Called when a player levels up
     *
     * @param player
     *            Player that levels up
     * @return true to prevent the default action.
     */
    public boolean onLevelUp(Player player) {
        return false;
    }

    /**
     * Called when sending the playername to the new list
     *
     * @param player
     * @param entry
     *            (the playerlist entry)
     * @return the playerlistentry to show.
     */
    public PlayerlistEntry onGetPlayerlistEntry(Player player, PlayerlistEntry entry) {
        return entry;
    }

    /**
     * Called when player connects to the server
     *
     * @param player
     * @param hookParametersConnect
     * @return modified hookParametersConnect
     */
    public Object onPlayerConnect(Player player, HookParametersConnect hookParametersConnect) {
        return hookParametersConnect;
    }

    /**
     * Called when player connects to the server
     *
     * @param player Player that is disconnecting.
     * @param hookParametersDisconnect Object holding parameters for this hook
     * @return modified hookParametersConnect
     */
    public Object onPlayerDisconnect(Player player, HookParametersDisconnect hookParametersDisconnect) {
        return hookParametersDisconnect;
    }

    /**
     * Called when someone presses right click aimed at an entity. You can intercept this to add your own right click actions to different item types (see itemInHand) Some interactions update the player's item in hand (for example shearing a sheep), but some do not (like using an item on a player). If you want to update the item stack anyways, you should return ALLOW_ACTION.
     *
     * @param player
     * @param entityClicked
     * @param itemInHand
     * @return ALLOW_ACTION to allow interaction and always update the item stack the player is holding, DEFAULT_ACTION to allow interaction and update the item stack if needed by default. PREVENT_ACTION to prevent the interaction when right clicking completely.
     */

    public PluginLoader.HookResult onEntityRightClick(Player player, BaseEntity entityClicked, Item itemInHand) {
        if (entityClicked.entity instanceof OEntityCow) {
            return onCowMilk(player, new Mob((OEntityCow) entityClicked.entity)) ? PluginLoader.HookResult.PREVENT_ACTION : PluginLoader.HookResult.DEFAULT_ACTION;
        }
        return PluginLoader.HookResult.DEFAULT_ACTION;
    }

    /**
     * Called when a Mob Entity targets a player for following and/or attacking.
     * You can interrupt this by returning true.
     *
     * @param player
     * @param mob
     * @return false to allow target to occur, true to cancel the target.
     */
    public boolean onMobTarget(Player player, LivingEntity mob) {
        return false;
    }

    /**
     * Called when one <tt>LivingEntity</tt> targets another for following
     * and/or attacking.
     * You can interrupt this by returning true.
     *
     * @param target The entity being targeted
     * @param targeter The entity that wants to target <tt>target</tt>
     * @return false to allow target to occur, true to cancel the target
     */
    public boolean onMobTarget(LivingEntity target, LivingEntity targeter) {
        return target.isPlayer()
                ? this.onMobTarget(target.getPlayer(), targeter)
                : false;
    }

    /**
     * Called when a Block updates
     *
     * @param block The block that's being updated
     * @return false to allow the update, true to cancel it.
     * NOTE: Only farmland right now
     * @deprecated Use {@link #onBlockUpdate(Block, int) } instead.
     */
    @Deprecated
    public boolean onBlockUpdate(Block block) {
        return false;
    }

    /**
     * Called when a Block updates
     *
     * @param oldBlock The Block that's being modified
     * @param newBlockId  The block ID of the new block
     * @return false to allow the update, true to cancel it.
     */
    public boolean onBlockUpdate(Block oldBlock, int newBlockId){
        return onBlockUpdate(oldBlock);
    }

    /**
     * Called when player enchants an item
     *
     * @param enchantParameters The HookParametersEnchant object used to modify enchantment result
     * @return modified enchantParameters
     */

    public Object onEnchant(HookParametersEnchant enchantParameters) {
        return enchantParameters;
    }

    /**
     * Called when a dispenser is activated with an item in it.
     *
     * @param dispenser - Block the dispenser is located at.
     * @param tobedispensed - BaseEntity that is to be dispensed. If no entity
     *                        is to be dispensed, this parameter will be null.
     * @return false to allow the dispense, true to cancel it.
     */
    public boolean onDispense(Dispenser dispenser, BaseEntity tobedispensed){
        return false;
    }

    /**
     * Called when light changes at a specific location.
     *
     * @param xcord
     *            - X coordinate of the position the light changed at.
     * @param ycord
     *            - Y coordinate of the position the light changed at.
     * @param zcord
     *            - Z coordinate of the position the light changed at.
     * @param level
     *            - Level the light is being changed to.
     */
    public void onLightChange(int xcord, int ycord, int zcord, int level) {

    }
        /**
     * Called when a LivingEntity dies.
     *
     * @param entity
     *            - Entity that has died.
     */
    public void onDeath(LivingEntity entity) {

    }

    /**
     * Called when a projectile hits something.
     *
     * @param projectile
     *             - The projectile that impacted.
     * @param hit
     *             - The entity that was hit (null if none).
     * @return false to allow the impact, true to cancel it (if true is always returned, the projectile will fall through the world)
     */
    public boolean onProjectileHit(Projectile projectile, BaseEntity hit) {
        return false;
    }

    /**
     * Called when a player trades with a villager.
     *
     * @param player The player involved in the trade.
     * @param villager The villager involved in the trade.
     * @param trade The trade.
     * @return false to allow the trade, true to cancel it.
     */
    public boolean onVillagerTrade(Player player, Villager villager, VillagerTrade trade) {
        return false;
    }

    /**
     * Called when a trade option is added to a villager.
     *
     * @param villager The villager receiving the new trade option.
     * @param trade The trade being added.
     * @return false to allow the unlock, true to cancel it.
     */
    public boolean onVillagerTradeUnlock(Villager villager, VillagerTrade trade) {
        return false;
    }

    /**
     * Called when the name section of an anvil changes.
     *
     * @param hookParametersAnvilUse Object containing information about this hook.
     * @return modified HookParametersAnvilUse
     */
    public HookParametersAnvilUse onAnvilUse(HookParametersAnvilUse hookParametersAnvilUse) {
        return hookParametersAnvilUse;
    }

    /**
     * Called when a firework explodes.
     *
     * @param firework The firework exploding.
     * @return true to cancel the explosion. It is also recommended to extend the life of the firework to prevent the calling of this hook repeatedly.
     */
    public boolean onFireworkExplode(Firework firework) {
        return false;
    }

    /**
     * Called when an inventory slot is clicked by a player.
     *
     * @param hookParametersSlotClick Object containing information about this hook
     * @return modified HookParametersSlotClick
     */
    public HookParametersSlotClick onSlotClick(HookParametersSlotClick hookParametersSlotClick) {
        return hookParametersSlotClick;
    }

    /**
     * Called when a CommandBlock executes a command.
     *
     * @param block The block executing the command.
     * @param split Parts of the command split by spaces.
     * @return true to cancel execution, false to allow it.
     */
    public boolean onCommandBlockCommand(CommandBlock block, String[] split) {
        return false;
    }

    /**
     *
     * @param player Player that gained the stat.
     * @param stat The stat gained.
     */
    public boolean onStatGained(Player player, Stat stat) {
        return false;
    }

    /**
     * Called when any entity is destroyed.
     * @param entity The entity destroyed.
     */
    public void onEntityDestroyed(BaseEntity entity){

    }

    /**
     * Called when any hanging entity is destroyed.
     * @param entity The entity destroyed.
     * @return true - entity is not destroyed<br>false - entity is destroyed
     */
    public boolean onHangingEntityDestroyed(HangingEntity baseEntity) {
        return false;
    }

    /**
     * Called when a hopper tries to Transfer an item
     * @param hopper hopper involved with this trasnfer
     * @param itemTransfered item being transferred
     * @param transferIn <tt>true</tt> if the item is being transferred into
     * the hopper, <tt>false</tt> if the item is being transferred out of it
     * @return <tt>true</tt> if the item should not be transferred,
     * <tt>false</tt> otherwise.
     */
    public boolean onHopperTransfer(Hopper hopper, Item itemTransfered, boolean transferIn) {
        return false;
    }

    /**
     * Called when a {@link Minecart} passes over an activation rail.
     * @param cart The cart passing over the rail
     * @param powered Whether the activation rail is powered
     * @return <tt>true</tt> if the minecart should not be activated,
     * <tt>false</tt> otherwise.
     */
    public boolean onMinecartActivate(Minecart cart, boolean powered) {
        return false;
    }
}
