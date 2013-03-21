
/**
 * Wrapper class for hoppers.
 * @author Somners
 */
public class Hopper extends BaseContainerBlock<OTileEntityHopper> implements ComplexBlock {

    private final OTileEntityHopper hopper;

    Hopper(OTileEntityHopper hopper) {
        this(null, hopper);
    }

    public Hopper(OContainer oContainer, OTileEntityHopper hopper) {
        super(oContainer, hopper, "Hopper");
        this.hopper = hopper;
    }

    /**
     * Gets the BaseContainerBlock inputting to the hopper.
     * @return the Block or null if none.
     */
    public BaseContainerBlock getInputContainer() {
        return this.getBaseContainerBlock(hopper.getInputInventory());
    }

    /**
     * Gets the BaseContainerBlock the hopper outputs to.
     * @return the Block or null if none.
     */
    public BaseContainerBlock getOutputContainer() {
        return this.getBaseContainerBlock(hopper.getOutputInventory());
    }

    /**
     * Check if this hopper is connected to any Container either input or output.
     * @return true - it is connected<br>false - it is not connected
     */
    public boolean isConnected() {
        return (this.isInputConnected() && this.isOutputConnected());
    }

    /**
     * Check if the block this hopper inputs from is a Container.
     * @return true - it is connected<br>false - it is not connected
     */
    public boolean isInputConnected() {
        return this.getInputContainer() != null;
    }

    /**
     * Check if the block this hopper outputs to is a Container.
     * @return true - it is connected<br>false - it is not connected
     */
    public boolean isOutputConnected() {
        return this.getOutputContainer() != null;
    }
    
    /**
     * Gets the BaseContainerBlock from the inventory instance.
     * @param inventory
     * @return 
     */
    private BaseContainerBlock getBaseContainerBlock(OIInventory inventory){
        if (inventory instanceof OTileEntityBeacon) {
            return new Beacon((OTileEntityBeacon)inventory);
        }
        else if (inventory instanceof OTileEntityBrewingStand) {
            return new BrewingStand((OTileEntityBrewingStand)inventory);
        }
        else if (inventory instanceof OTileEntityChest) {
            return new Chest((OTileEntityChest)inventory);
        }
        else if (inventory instanceof OTileEntityDispenser) {
            return new Dispenser((OTileEntityDispenser)inventory);
        }
        else if (inventory instanceof OTileEntityFurnace) {
            return new Furnace((OTileEntityFurnace)inventory);
        }
        else if (inventory instanceof OTileEntityHopper) {
            return new Hopper((OTileEntityHopper)inventory);
        }
        return null;
    }
}
