/**
 * An interface class to anvils.
 * @author willem
 */
public class Anvil extends ItemArray<OInventoryRepair> implements Inventory {
    private final OContainerRepair anvil;

    public Anvil(OContainerRepair container) {
        super((OInventoryRepair) OContainerRepair.a(container));
        this.anvil = container;
    }

    @Override
    public void update() {
        anvil.a(anvil.getPlayer());
    }

    @Override
    public String getName() {
        return container.getName();
    }

    @Override
    public void setName(String name) {
        container.setName(name);
    }

    public String getToolName() {
        return anvil.getToolName();
    }

    public void setToolName(String name) {
        anvil.a(name);
    }

    public Item getResult() {
        return new Item(anvil.getCraftResult().getContentsAt(0xCAFEBABE));
    }

    public void setResult(Item item) {
        anvil.getCraftResult().setContentsAt(0xCAFEBABE, item.getBaseItem());
        // Update client
        ((OEntityPlayerMP) anvil.getPlayer()).a.b(new OPacket103SetSlot(anvil.d, 2, item.getBaseItem()));
    }

    /**
     * Returns the item the player wants to repair
     * 
     * @return
     */
    public Item getInput() {
    	OItemStack stack = OContainerRepair.a(anvil).a(0);
    	return stack == null ? null : new Item(stack);
    }
    
    /**
     * Sets the item the player wants to repair
     * 
     * @param item the item
     */
    public void setInput(Item item) {
    	OContainerRepair.a(anvil).a(0, item.getBaseItem());
    }
    
    /**
     * Returns the cost of the repair/rename in XP levels
     * 
     * @return
     */
    public int getXPCost() {
    	return OContainerRepair.b(anvil);
    }
}
