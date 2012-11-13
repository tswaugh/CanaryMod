

/**
 * Mob.java - Interface for mobs
 *
 * @author James
 */
public class Mob extends LivingEntity {

    /**
     * Creates a mob interface
     *
     * @param locallb
     *            name of mob
     */
    public Mob(OEntityLiving locallb) {
        super(locallb);
    }

    /**
     * Creates a mob interface
     *
     * @param mob
     *            name of mob
     * @deprecated Use {@link #Mob(java.lang.String, World)} instead.
     */
    public Mob(String mob) {
        this(mob, etc.getServer().getDefaultWorld());
    }

    /**
     * Creates a mob interface
     * @param mob name of the mob
     * @param world World for the mob
     */
    public Mob(String mob, World world) {
        this((OEntityLiving) OEntityList.a(mob, world.getWorld()));
        this.getEntity().bD();
    }

    /**
     * Creates a mob interface
     *
     * @param mobName
     *            name of mob
     * @param location
     *            location of mob
     */
    public Mob(String mobName, Location location) {
        this(mobName, location.getWorld());
        teleportTo(location);
    }

    /**
     * Spawns this mob
     */
    public void spawn() {
        spawn((LivingEntity) null);
    }

    /**
     * Spawns this mob with a rider
     *
     * @param rider
     */
    public void spawn(LivingEntity rider) {
        OWorld world = entity.p;

        entity.b(getX() + 0.5d, getY(), getZ() + 0.5d, getRotation(), 0f);
        world.d(entity);

        if (rider != null) {
            OEntityLiving mob2 = rider.getEntity();

            mob2.b(getX(), getY(), getZ(), getRotation(), 0f);
            world.d(mob2);
            mob2.a(entity);
        }
    }

    /**
     * Backwards compat.
     */
    public void spawn(Mob rider) {
        this.spawn((LivingEntity) rider);
    }

    /**
     * Returns this mob's name
     *
     * @return name
     */
    @Override
    public String getName() {
        return OEntityList.b(entity);
    }

    /**
     * Returns the current target of the mob
     *
     * @return OEntity
     */
    public OEntity getTarget() {
        if (getEntity() instanceof OEntityGhast) {
            OEntityGhast ghast = (OEntityGhast) getEntity();

            return ghast.getTarget();
        }
        return ((OEntityCreature) getEntity()).a_;
    }

    /**
     * Sets the mobs target
     *
     * @param target the entity to target
     */
    public void setTarget(OEntity target) {
        if (getEntity() instanceof OEntityGhast) {
            OEntityGhast ghast = (OEntityGhast) getEntity();

            ghast.setTarget(target);
            return;
        }
        ((OEntityCreature) getEntity()).a_ = target;
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        if (health <= 0) {
            dropLoot();
        }
    }

    /**
     * Returns the actual mob
     *
     * @return
     */
    public OEntityLiving getMob() {
        return getEntity();
    }

    /**
     * Checks to see if the mob is a valid mob
     *
     * @param mob
     *            the mob to check
     * @return true of mob is valid
     */
    public static boolean isValid(String mob) {
        if (mob == null) {
            return false;
        }


        // Catch InstantiationExceptions, e.g. when calling isValid("Monster")
        try {
            OEntity c = OEntityList.a(mob, etc.getServer().getDefaultWorld().getWorld());
            
            return c instanceof OIMob || c instanceof OIAnimals;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns Mob location
     * @return this mob's location
     */
    public Location getLocation() {
        Location loc = new Location();

        loc.x = getX();
        loc.y = getY();
        loc.z = getZ();
        loc.rotX = getRotation();
        loc.rotY = getPitch();
        loc.dimension = getWorld().getType().getId();
        return loc;
    }

    public boolean isInLove(){
    	if (getEntity() instanceof OEntityAnimal){
    		return ((OEntityAnimal) getEntity()).r();
    	}
    	return false;
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
    public Item getItemInHand() {
    	OItemStack stack = getEntity().bA();
    	return stack == null ? null : new Item(stack);
    }
    
    /**
     * Sets an armor slot of the entity.
     * 
     * @param slot 
     * 			The slot of the armor, 0 being boots and 3 being helmet
     * @param armor
     * 			The item of armor to add
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
     * 			The slot of the armor, 0 being boots and 3 being helmet
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
     * Whether or not this mob can pick up items.
     * 
     * @return
     */
    public boolean canPickUpLoot() {
    	return getEntity().bs;
    }
    
    /**
     * Sets whether or not this mob can pick up items.
     * 
     * @param flag
     */
    public void setCanPickUpLoot(boolean flag) {
    	getEntity().bs = flag;
    }
    
    /**
     * Returns whether or not this mob is invulnerable.
     * 
     * @return
     */
    public boolean isInvulnerable() {
    	return getEntity().bt;
    }
    
    /**
     * Sets whether or not this mob is invulnerable.
     * 
     * @param isInvulnerable
     */
    public void setInvulnerable(boolean isInvulnerable) {
    	getEntity().bt = isInvulnerable;
    }
    
    /**
     * Returns whether or not this mob will despawn.
     * 
     * @return
     */
    public boolean isPersistent() {
    	return getEntity().bV;
    }
    
    /**
     * Sets whether or not this mob will despawn.
     * 
     * @param isPersistent
     */
    public void setPersistent(boolean isPersistent) {
    	getEntity().bV = isPersistent;
    }
    
    /**
     * Returns the drop chance of an item.
     * 
     * @param slot The slot of the item, 0-4: 0 is hand, 1-4 are armor slots (1=boots and 4=helmet)
     * @return The drop chance, 0-1 (anything greater than 1 is guaranteed to drop)
     */
    public float getDropChance(int slot) {
    	if(slot >= 0 && slot <= 4) {
    		return getEntity().bp[slot];
    	}
    	return 0;
    }
    
    /**
     * Sets the drop chance of an item.
     * 
     * @param slot The slot of the item, 0-4: 0 is hand, 1-4 are armor slots (1=boots and 4=helmet)
     * @param chance The drop chance, 0-1 (anything greater than 1 is guaranteed to drop)
     */
    public void setDropChance(int slot, float chance) {
    	if(slot >= 0 && slot <= 4) {
    		getEntity().bp[slot] = chance;
    	}
    }
}
