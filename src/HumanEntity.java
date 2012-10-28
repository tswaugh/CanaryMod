/**
 * 
 * @author
 */
public class HumanEntity extends LivingEntity {

    /**
     * Constructor
     */
    public HumanEntity() {}

    /**
     * Constructor
     * 
     * @param human
     */
    public HumanEntity(OEntityPlayer human) {
        super(human);
    }

    /**
     * Returns the entity we're wrapping.
     * 
     * @return
     */
    @Override
    public OEntityPlayer getEntity() {
        return (OEntityPlayer) entity;
    }

    /**
     * Returns the name
     * 
     * @return
     */
    @Override
    public String getName() {
        return getEntity().bT;
    }

    /**
     * Returns the world
     * 
     * @return
     */
    @Override
    public World getWorld() {
        return etc.getMCServer().getWorld(getEntity().p.name, getEntity().bK).world; //gregthegeek: getEntity().bK should be the dimension of the world, but it appears that the dimension is no longer stored there
    }
}
