/**
 * Interface to brewing stands.
 * TODO: add hooks
 * @author willem
 */
public class BrewingStand extends BaseContainerBlock<OTileEntityBrewingStand> implements Inventory {

    public BrewingStand(OTileEntityBrewingStand brewingStand) {
        super(brewingStand, "Brewing Stand");
    }
    
}
