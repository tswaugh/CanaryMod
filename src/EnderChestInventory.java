/**
 * Interface for accessing the inventories of ender chests
 * 
 * @author gregthegeek
 *
 */
public class EnderChestInventory extends ItemArray<OInventoryEnderChest> {

	public EnderChestInventory(OInventoryEnderChest container) {
		super(container);
	}
	
	/**
	 * Returns an NBTTagList with data about the contents of this inventory
	 * 
	 * @return
	 */
	public NBTTagList writeToTag() {
		return new NBTTagList(container.g());
	}
	
	/**
	 * Sets this inventory's data to equal the contents of an NBTTagList
	 * 
	 * @param tag the tag to read data from
	 */
	public void readFromTag(NBTTagList tag) {
		container.a(tag.getBaseTag());
	}
}
