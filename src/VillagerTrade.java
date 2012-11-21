/**
 * Interface for the OMerchantRecipe class
 * 
 * @author gregthegeek
 *
 */
public class VillagerTrade {
	private final OMerchantRecipe recipe;
	
	/**
	 * Wraps an OMerchantRecipe
	 * 
	 * @param recipeBase the recipe to wrap
	 */
	public VillagerTrade(OMerchantRecipe recipe) {
		this.recipe = recipe;
	}
	
	/**
	 * Creates a new villager trade
	 * 
	 * @param buying the item the player will give to the villager
	 * @param selling the item the villager will give to the player
	 */
	public VillagerTrade(Item buying, Item selling) {
		this(new OMerchantRecipe(buying.getBaseItem(), selling.getBaseItem()));
	}
	
	/**
	 * Creates a new villager trade
	 * 
	 * @param buyingOne the first item the player will give to the villager
	 * @param buyingTwo the second item the player will give to the villager
	 * @param selling the item the villager will give to the player
	 */
	public VillagerTrade(Item buyingOne, Item buyingTwo, Item selling) {
		this(new OMerchantRecipe(buyingOne.getBaseItem(), buyingTwo.getBaseItem(), selling.getBaseItem()));
	}
	
	/**
	 * Creates a new villager trade
	 * 
	 * @param tag the tag to read the trade data from
	 */
	public VillagerTrade(NBTTagCompound tag) {
		this(new OMerchantRecipe(tag.getBaseTag()));
	}
	
	/**
	 * Returns the base recipe for this trade
	 * 
	 * @return
	 */
	public OMerchantRecipe getRecipe() {
		return this.recipe;
	}
	
	/**
	 * Returns the first item the player must give to the villager
	 * 
	 * @return
	 */
	public Item getBuyingOne() {
		return new Item(getRecipe().a());
	}
	
	/**
	 * Returns the second item the player must give to the villager
	 * 
	 * @return
	 */
	public Item getBuyingTwo() {
		return new Item(getRecipe().b());
	}
	
	/**
	 * Returns whether or not this trade requires the player to give the villager two items
	 * 
	 * @return
	 */
	public boolean requiresTwoItems() {
		return getRecipe().c();
	}
	
	/**
	 * Returns the item the player receives from the trade
	 * 
	 * @return
	 */
	public Item getSelling() {
		return new Item(getRecipe().d());
	}
	
	/**
	 * Increase the number of times this was used by one
	 */
	public void use() {
		getRecipe().f();
	}
	
	/**
	 * Increases the maximum amount of times this trade can be used
	 * The default max is 7
	 * 
	 * @param increase the amount to increase it buy
	 */
	public void increaseMaxUses(int increase) {
		getRecipe().a(increase);
	}
	
	/**
	 * Returns whether or not this recipe has exceeded its max usages and can no longer be used
	 * 
	 * @return
	 */
	public boolean isUsedUp() {
		return getRecipe().g();
	}
	
	/**
	 * Returns the data for this trade in an NBTCompoundTag
	 * 
	 * @return
	 */
	public NBTTagCompound getDataAsTag() {
		return new NBTTagCompound(getRecipe().i());
	}
	
	/**
	 * Reads the data from an NBTTagCompound into this trade
	 * 
	 * @param tag the tag to read the data from
	 */
	public void readFromTag(NBTTagCompound tag) {
		getRecipe().a(tag.getBaseTag());
	}
}
