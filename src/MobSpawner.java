

/**
 * MobSpawner.java - Wrapper for mob spawners.
 *
 * @author James
 */
public class MobSpawner implements ComplexBlock {

    OTileEntityMobSpawner spawner;

    /**
     * Creates an interface for the spawner.
     *
     * @param spawner
     */
    public MobSpawner(OTileEntityMobSpawner spawner) {
        this.spawner = spawner;
    }

    @Override
    public int getX() {
        return spawner.l;
    }

    @Override
    public int getY() {
        return spawner.m;
    }

    @Override
    public int getZ() {
        return spawner.n;
    }

    @Override
    public Block getBlock() {
        return getWorld().getBlockAt(getX(), getY(), getZ());
    }

    @Override
    public World getWorld() {
        return spawner.k.world;
    }

    @Override
    public void update() {
        etc.getMCServer().ad().a(spawner.l());
    }

    /**
     * Allows what to spawn to change on-the-fly
     *
     * @param spawn
     */
    public void setSpawn(String spawn) {
        spawner.d = spawn;
        update();
    }

    /**
     * Returns the spawn used.
     *
     * @return
     */
    public String getSpawn() {
        return spawner.d;
    }

    /**
     * Allows delay of what to spawn to change on-the-fly.
     * Modification of this is near-useless as delays get randomized after
     * spawn.
     *
     * @param delay
     */
    public void setDelay(int delay) {
        spawner.a = delay;
    }
    
    /**
     * Returns the minimum delay of the spawner. 
     * The delay between spawns is picked randomly between this and the max delay.
     * 
     * @return
     */
    public int getMinDelay() {
        return spawner.f;
    }
    
    /**
     * Sets the minimum delay of the spawner. 
     * The delay between spawns is picked randomly between this and the max delay. 
     * Default is 200.
     * 
     * @param delay
     */
    public void setMinDelay(int delay) {
        spawner.f = delay;
    }
    
    /**
     * Returns the maximum delay of the spawner. 
     * The delay between spawns is picked randomly between this and the min delay.
     * 
     * @return
     */
    public int getMaxDelay() {
        return spawner.g;
    }
    
    /**
     * Sets the maximum delay of the spawner. 
     * The delay between spawns is picked randomly between this and the min delay. 
     * Default is 800.
     * 
     * @param delay
     */
    public void setMaxDelay(int delay) {
        spawner.g = delay;
    }
    
    /**
     * Returns the amount of mobs this spawner attempts to spawn.
     * 
     * @return
     */
    public int getSpawnCount() {
        return spawner.h;
    }
    
    /**
     * Sets the amount of mobs this spawner attempts to spawn.
     * Default is 4.
     * 
     * @param count
     */
    public void setSpawnCount(int count) {
        spawner.h = count;
    }
    
    /**
     * Returns the maximum number of entities this spawner allows nearby in order to continue spawning.
     * Any more entities and this spawner won't spawn mobs.
     * 
     * @return
     */
    public int getMaxNearbyEntities() {
        return spawner.j;
    }
    
    /**
     * Sets the maximum number of entities this spawner allows nearby in order to continue spawning.
     * Any more entities and this spawner won't spawn mobs.
     * Default is 6.
     * 
     * @param entities
     */
    public void setMaxNearbyEntities(int entities) {
        spawner.j = entities;
    }
    
    /**
     * If there are no players within this distance of the spawner, it won't spawn.
     * 
     * @return
     */
    public int getRequiredPlayerRange() {
        return spawner.r;
    }
    
    /**
     * If there are no players within this distance of the spawner, it won't spawn.
     * Default is 16.
     * 
     * @param range
     */
    public void setRequiredPlayerRange(int range) {
        spawner.r = range;
    }
    
    /**
     * Returns the maximum distance that this spawner will spawn mobs at.
     * 
     * @return
     */
    public int getSpawnRange() {
        return spawner.s;
    }
    
    /**
     * Sets the maximum distance that this spawner will spawn mobs at.
     * Default is 4.
     * 
     * @param range
     */
    public void setSpawnRange(int range) {
        spawner.s = range;
    }
    
    /**
     * Sets the entity spawned by this spawner.
     * 
     * @param entity The entity this spawner should spawn
     */
    public void setSpawnedEntity(BaseEntity entity) {
        setSpawnedEntity(entity.getEntity());
    }
    
    /**
     * Sets the entity spawned by this spawner to an item.
     * 
     * @param itemEntity The item this spawner should spawn
     */
    public void setSpawnedEntity(Item itemEntity) {
        setSpawnedEntity(new OEntityItem(null, 0, 0, 0, itemEntity.getBaseItem()));
    }
    
    /**
     * Sets the entity spawned by this spawner.
     * 
     * @param entity The entity this spawner should spawn
     */
    public void setSpawnedEntity(OEntity entity) {
        NBTTagCompound tag = new NBTTagCompound();
        entity.b(tag.getBaseTag());
        tag.removeTag("Health");
        tag.removeTag("HurtTime");
        tag.removeTag("DeathTime");
        tag.removeTag("AttackTime");
        tag.removeTag("Age");
        spawner.e = tag.getBaseTag();
        setSpawn(entity.Q());
    }
    
    /**
     * Reads the data for this spawner from an NBTTagCompound
     * 
     * @param tag the tag to read from
     */
    public void readFromTag(NBTTagCompound tag) {
        spawner.a(tag.getBaseTag());
    }
    
    /**
     * Writes the data for this spawner to an NBTTagCompound
     * 
     * @param tag the tag to write to
     */
    public void writeToTag(NBTTagCompound tag) {
        spawner.b(tag.getBaseTag());
    }
    
    @Override
    public NBTTagCompound getMetaTag() {
        return spawner.metadata;
    }
}
