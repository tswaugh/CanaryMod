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
        return getEntity().bR;
    }
    
    /**
     * Returns the name displayed above this player's head.
     * 
     * @return String
     */
    public String getDisplayName() {
    	return getEntity().getDisplayName();
    }
    
    /**
     * Sets the name displayed above this player's head.
     * 
     * @param name The name displayed. Any non-color modification will affect skin.
     */
    public void setDisplayName(String name) {
    	getEntity().setDisplayName(name);
    }
}
