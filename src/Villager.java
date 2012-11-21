/**
 * Interface for villagers
 * 
 * @author gregthegeek
 *
 */
public class Villager extends Mob {
	
	/**
	 * Represents the profession of the villager
	 * 
	 * @author gregthegeek
	 *
	 */
	public enum Profession {
		FARMER,
		LIBRARIAN,
		PRIEST,
		BLACKSMITH,
		BUTCHER,
		GENERIC;
	}
	
	/**
	 * Creates a villager wrapper
	 * 
	 * @param oentity The entity to wrap
	 */
	public Villager(OEntityVillager oentity) {
		super(oentity);
	}
	
	/**
	 * Creates a new villager
	 * 
	 * @param world The world in which to create it
	 */
	public Villager(World world) {
		super("Villager", world);
	}
	
	/**
	 * Creates a new villager
	 * 
	 * @param location The location at which to create it
	 */
	public Villager(Location location) {
		super("Villager", location);
	}
	
	/**
	 * Returns the profession of the villager
	 * 
	 * @return
	 */
	public Profession getProfession() {
		return Profession.values()[getEntity().m()];
	}
	
	/**
	 * Sets the profession of the villager
	 * 
	 * @param profession
	 */
	public void setProfession(Profession profession) {
		getEntity().s(profession.ordinal());
	}
	
	@Override
	public OEntityVillager getEntity() {
		return (OEntityVillager) entity;
	}
}
