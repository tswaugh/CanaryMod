/**
 * Interface for the ONBTTagList class
 * 
 * @author gregthegeek
 *
 */
public class NBTTagList extends NBTBase {
	/**
	 * Wraps an ONBTTagList
	 * 
	 * @param baseTag the tag to wrap
	 */
	public NBTTagList(ONBTTagList baseTag) {
		super(baseTag);
	}
	
	/**
	 * Creates a new NBTTagList
	 */
	public NBTTagList() {
		this(new ONBTTagList());
	}
	
	/**
	 * Creates a new NBTTagList
	 * 
	 * @param name the name of the tag
	 */
	public NBTTagList(String name) {
		this(new ONBTTagList(name));
	}
	
	@Override
	public ONBTTagList getBaseTag() {
		return (ONBTTagList) super.getBaseTag();
	}
	
	/**
	 * Returns the size of this list
	 * 
	 * @return
	 */
	public int size() {
		return this.getBaseTag().c();
	}
	
	/**
	 * Adds a tag to this list
	 * 
	 * @param tag
	 */
	public void add(NBTBase tag) {
		this.getBaseTag().a(tag.getBaseTag());
	}
	
	/**
	 * Gets the tag at the specified index
	 * 
	 * @param index
	 * @return
	 */
	public NBTBase get(int index) {
		ONBTBase tag = this.getBaseTag().b(index);
		switch(tag.a()) {
		case 1:
			return new NBTTagByte((ONBTTagByte) tag);
		case 2:
			return new NBTTagShort((ONBTTagShort) tag);
		case 3:
			return new NBTTagInt((ONBTTagInt) tag);
		case 4:
			return new NBTTagLong((ONBTTagLong) tag);
		case 5:
			return new NBTTagFloat((ONBTTagFloat) tag);
		case 6:
			return new NBTTagDouble((ONBTTagDouble) tag);
		case 7:
			return new NBTTagByteArray((ONBTTagByteArray) tag);
		case 8:
			return new NBTTagString((ONBTTagString) tag);
		case 9:
			return new NBTTagList((ONBTTagList) tag);
		case 10:
			return new NBTTagCompound((ONBTTagCompound) tag);
		case 11:
			return new NBTTagIntArray((ONBTTagIntArray) tag);
		default:
			return new NBTBase(tag);
		}
	}
}
