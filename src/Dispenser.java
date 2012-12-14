/**
 *
 * @author Meaglin
 */
public class Dispenser extends BaseContainerBlock<OTileEntityDispenser> implements ComplexBlock {

    public Dispenser(OTileEntityDispenser disp) {
        super(disp, "Trap");
    }

    public void fire() {
        OWorld oworld = this.getWorld().getWorld();

        // OBlock.P = OBlockDispenser, n = dispense
        ((OBlockDispenser) OBlock.S).n(oworld, this.getX(), this.getY(), this.getZ());
    }
}
