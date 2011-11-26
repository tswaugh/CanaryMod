/**
 * 
 * @author Meaglin
 */
public class Dispenser extends BaseContainerBlock implements ComplexBlock {

    public Dispenser(OTileEntityDispenser disp) {
        super(disp, "Trap");
    }
    
    public void fire() {
        OTileEntityDispenser dis = (OTileEntityDispenser) container;
        OBlock.R.a(dis.k, dis.l, dis.m, dis.n, dis.k.w);
    }
}
