
/**
 * Wrapper class for hoppers.
 * @author Somners
 */
public class Hopper extends BaseContainerBlock<OTileEntityHopper> implements ComplexBlock {
    
    private final OTileEntityHopper hopper;

    Hopper(OTileEntityHopper hopper) {
        this(null, hopper);
    }
    
    public Hopper(OContainer oContainer, OTileEntityHopper hopper){
        super(oContainer, hopper, "Hopper");
        this.hopper = hopper;
    }
    
    /**
     * Gets the BaseContainerBlock above the hopper.
     * @return the Block or null if none.
     */
    public BaseContainerBlock getAboveContainer(){
        ComplexBlock cb = hopper.az().world.getOnlyComplexBlock((int)container.aA(), (int)container.aB()+1, (int)container.aC());
        if(cb != null){
            if(cb instanceof BaseContainerBlock){
                return (BaseContainerBlock)cb;
            }
        }
        return null;
    }
    
    /**
     * Gets the BaseContainerBlock below the hopper.
     * @return the Block or null if none.
     */
    public BaseContainerBlock getBelowContainer(){
        ComplexBlock cb = hopper.az().world.getOnlyComplexBlock((int)container.aA(), (int)container.aB()-1, (int)container.aC());
        if(cb != null){
            if(cb instanceof BaseContainerBlock){
                return (BaseContainerBlock)cb;
            }
        }
        return null;
    }
    
    /**
     * Check if this hopper is connected to any Container either above or below.
     * @return true - it is connected<br>false - it is not connected
     */
    public boolean isConnected(){
        return (this.isAboveConnected() && this.isBelowConnected());
    }
    
    /**
     * Check if the block above this hopper is a Container.
     * @return true - it is connected<br>false - it is not connected
     */
    public boolean isAboveConnected(){
        return this.getAboveContainer() != null;
    }
    
    /**
     * Check if the block below this hopper is a Container.
     * @return true - it is connected<br>false - it is not connected
     */
    public boolean isBelowConnected(){
        return this.getBelowContainer() != null;
    }
}
