/**
 * StorageMinecart - So we can access what's in them.
 *
 * @author James
 */
public class StorageMinecart extends ItemArray<OEntityMinecart> {

    /**
     * Creates an interface for storage of powered and storage carts.
     *
     * @param cart
     */
    public StorageMinecart(OEntityMinecart cart) {
        this(null, cart);
    }
    
    public StorageMinecart(OContainer oContainer, OEntityMinecart cart) {
        super(oContainer, cart);
    }

    @Override
    public void update() {
        container.d(); // Not that it does anything, but it's the right method.
    }

    @Override
    public String getName() {
        return container.getName();
    }

    @Override
    public void setName(String name) {
        container.setName(name);
    }
    
    /**
     * Returns the amount of fuel this minecart has.
     * Only useful if this is a powered minecart.
     * 
     * @return int
     */
    public int getFuel() {
    	return container.e;
    }
    
    /**
     * Returns the amount of fuel this minecart has.
     * Only useful if this is a powered minecart.
     * 
     * @param fuel
     */
    public void setFuel(int fuel) {
    	container.e = fuel;
    }
}
