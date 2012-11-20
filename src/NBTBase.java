/**
 * Interface for the ONBTBase class
 * 
 * @author gregthegeek
 *
 */
public class NBTBase {
	private final ONBTBase baseTag;
	
	/**
	 * Creates a wrapper around an ONBTBase
	 * 
	 * @param baseTag the ONBTBase tag to wrap
	 */
	public NBTBase(ONBTBase baseTag) {
		this.baseTag = baseTag;
	}
	
	/**
	 * Returns the name of this tag
	 * 
	 * @return
	 */
	public String getName() {
		return getBaseTag().e();
	}
	
	/**
	 * Sets the name of this tag
	 * 
	 * @param name the new name for the tag
	 */
	public void setName(String name) {
		getBaseTag().p(name);
	}
	
	/**
	 * Returns the ONBTBase this wraps
	 * 
	 * @return
	 */
	public ONBTBase getBaseTag() {
		return this.baseTag;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof NBTBase) {
			return getBaseTag().equals(((NBTBase) object).getBaseTag());
		} else if(object instanceof ONBTBase) {
			return getBaseTag().equals((ONBTBase) object);
		}
		return false;
	}
	
	/**
	 * Returns the type of tag this is
	 * 
	 * @return
	 */
	public byte getType() {
		return getBaseTag().a();
	}
	
	/**
	 * Returns the name of the type of tag this is
	 * 
	 * @param type
	 * @return
	 */
	public static String getTagName(byte type) {
		return ONBTBase.a(type);
	}
}
