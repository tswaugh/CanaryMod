import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Interface for living entities.
 */
public class LivingEntity extends BaseEntity {

    /**
     * Interface for living entities
     */
    public LivingEntity() {}

    /**
     * Interface for living entities
     *
     * @param livingEntity
     */
    public LivingEntity(OEntityLiving livingEntity) {
        super(livingEntity);
    }

    /**
     * Returns the entity we're wrapping.
     *
     * @return
     */
    @Override
    public OEntityLiving getEntity() {
        return (OEntityLiving) entity;
    }

    /**
     * Returns the entity's health.
     *
     * @return health
     */
    public int getHealth() {
        return getEntity().aQ;
    }

    /**
     * Increase entity health.
     *
     * @param health
     *            amount of health to increase the players health with.
     */
    public void increaseHealth(int health) {
        getEntity().i(health);
    }

    /**
     * Sets the entity's health. 20 = max health 1 = 1/2 heart 2 = 1 heart
     *
     * @param health
     */
    public void setHealth(int health) {
        getEntity().j(health);
    }

    /**
     * Get the amount of ticks this entity is dead. 20 ticks per second.
     *
     * @return
     */
    public int getDeathTicks() {
        return getEntity().aX;
    }

    /**
     * Get an entities max health value
     * @return max health
     */
    public int getMaxHealth(){
        return getEntity().aT();
    }

    /**
     * Set the entities max health
     * @param toSet max health
     */
    public void setMaxHealth(int toSet){
        if(toSet > 0) {
            getEntity().maxHealth = toSet;
        }
    }

    /**
     * Set the amount of ticks this entity is dead. 20 ticks per second.
     *
     * @param ticks
     */
    public void setDeathTicks(int ticks) {
        getEntity().aX = ticks;
    }

    /**
     * Get the amount of ticks this entity will not take damage. (unless it
     * heals) 20 ticks per second.
     *
     * @return
     */
    public int getBaseNoDamageTicks() {
        return getEntity().at;
    }

    /**
     * Set the amount of ticks this entity will not take damage. (until it
     * heals) 20 ticks per second.
     *
     * @param ticks
     */
    public void setBaseNoDamageTicks(int ticks) {
        getEntity().at = ticks;
    }

    /**
     * Get the current maximum damage taken during this NoDamageTime
     *
     * @return
     */
    public int getLastDamage() {
        return getEntity().bz;
    }

    /**
     * Set the current maximum damage taken during this NoDamageTime (if any
     * damage is higher than this number the difference will be added)
     *
     * @param amount
     */
    public void setLastDamage(int amount) {
        getEntity().bz = amount;
    }

    /**
     * Drops this mob's loot. Automatically called if health is set to 0.
     */
    public void dropLoot() {
        getEntity().a(true, 0);
    }

    /**
     * Gets the entity's mob spawner.
     * @return MobSpawner of the entity, or null if it wasn't spawned with a mob spawner.
     */
    public MobSpawner getSpawner() {
        return getEntity().spawner;
    }

    /**
     * Adds a potion effect to the entity.
     *
     * @param effect the effect to add.
     */
    public void addPotionEffect(PotionEffect effect) {
        getEntity().d(effect.potionEffect);
    }

    /**
     * Removes a potion effect from entity.
     *
     * @param effect The potion effect to remove
     */

    public void removePotionEffect(PotionEffect effect) {
        OPotionEffect var3 = (OPotionEffect) getEntity().bl.get(effect.getType().getId());

        getEntity().bl.remove(Integer.valueOf(effect.getType().getId()));
        getEntity().c(var3);
    }

    /**
     * Returns a Collection of potion effects active on the entity.
     *
     * @return List of potion effects
     */
    @SuppressWarnings("unchecked")
    public List<PotionEffect> getPotionEffects() {
        Collection<OPotionEffect> potionEffects = getEntity().bz();
        ArrayList<PotionEffect> list = new ArrayList<PotionEffect>();

        for (OPotionEffect potionEffect : potionEffects) {
            list.add(potionEffect.potionEffect);
        }
        return list;
    }

    /**
     * Sets the item held by the entity.
     *
     * @param item
     */
    public void setItemInHand(Item item) {
        getEntity().b(0, item.getBaseItem());
    }

    /**
     * Gets the item held by the entity.
     *
     * @return
     */
    public Item getItemStackInHand() {
        OItemStack stack = getEntity().bD();
        return stack == null ? null : new Item(stack);
    }

    /**
     * Sets an armor slot of the entity.
     *
     * @param slot
     *             The slot of the armor, 0 being boots and 3 being helmet
     * @param armor
     *             The item of armor to add
     */
    public void setArmorSlot(int slot, Item armor) {
        if(slot >= 0 && slot <= 3) {
            getEntity().b(slot + 1, armor.getBaseItem());
        }
    }

    /**
     * Gets the item in one of the entity's armor slots.
     *
     * @param slot
     *             The slot of the armor, 0 being boots and 3 being helmet
     * @return
     */
    public Item getArmorSlot(int slot) {
        if(slot < 0 || slot > 3) {
            return null;
        }
        OItemStack stack = getEntity().q(slot);
        return stack == null ? null : new Item(stack);
    }

    /**
     * Returns whether or not this entity will despawn naturally.
     *
     * @return
     */
    public boolean isPersistent() {
        return getEntity().bT;
    }

    /**
     * Sets whether or not this entity will despawn naturally.
     *
     * @param isPersistent
     */
    public void setPersistent(boolean isPersistent) {
        getEntity().bT = isPersistent;
    }

    /**
     * Returns the drop chance of an item owned by this entity.
     *
     * @param slot The slot of the item, 0-4: 0 is hand, 1-4 are armor slots (1=boots and 4=helmet)
     * @return The drop chance, 0-1 (anything greater than 1 is guaranteed to drop)
     */
    public float getDropChance(int slot) {
        if(slot >= 0 && slot <= 4) {
            return getEntity().bo[slot];
        }
        return 0;
    }

    /**
     * Sets the drop chance of an item owned by this entity.
     *
     * @param slot The slot of the item, 0-4: 0 is hand, 1-4 are armor slots (1=boots and 4=helmet)
     * @param chance The drop chance, 0-1 (anything greater than 1 is guaranteed to drop)
     */
    public void setDropChance(int slot, float chance) {
        if(slot >= 0 && slot <= 4) {
            getEntity().bo[slot] = chance;
        }
    }

    /**
     * Whether or not this entity can pick up items.
     *
     * @return
     */
    public boolean canPickUpLoot() {
        return getEntity().br;
    }

    /**
     * Sets whether or not this entity can pick up items.
     *
     * @param flag
     */
    public void setCanPickUpLoot(boolean flag) {
        getEntity().br = flag;
    }

    /**
     * Returns whether or not this entity is currently sneaking (crouching).
     *
     * @return true if sneaking
     */
    public boolean getSneaking() {
        return getEntity().ah();
    }

    /**
     * Force this entity to be sneaking or not
     *
     * @param sneaking
     *            true if sneaking
     */
    public void setSneaking(boolean sneaking) {
        getEntity().a(sneaking);
    }

    /**
     * Damages this player, taking into account armor/enchantments/potions
     *
     * @param type The type of damage to deal (certain types byass armor or affect potions differently)
     * @param amount The amount of damage to deal (2 = 1 heart)
     */
    public void applyDamage(PluginLoader.DamageType type, int amount) {
        getEntity().d(type.getDamageSource(), amount);
    }
}
