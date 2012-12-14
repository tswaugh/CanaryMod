/**
 * Interface for the OTileEntityCommandBlock class
 *
 * @author gregthegeek
 *
 */
public class CommandBlock implements ComplexBlock {
    private final OTileEntityCommandBlock base;

    public CommandBlock(OTileEntityCommandBlock base) {
        this.base = base;
    }

    @Override
    public int getX() {
        return base.l;
    }

    @Override
    public int getY() {
        return base.m;
    }

    @Override
    public int getZ() {
        return base.n;
    }

    @Override
    public void update() {
        base.g();
    }

    @Override
    public Block getBlock() {
        return getWorld().getBlockAt(getX(), getY(), getZ());
    }

    @Override
    public World getWorld() {
        return base.k.world;
    }

    /**
     * Sets this command block's command
     *
     * @param command the command to execute when this block is activated
     */
    public void setCommand(String command) {
        base.b(command);
    }

    /**
     * Returns this command block's command
     *
     * @return
     */
    public String getCommand() {
        return base.getCommand();
    }

    /**
     * Run this command block's command
     */
    public void runCommand() {
        base.a(getWorld().getWorld());
    }

    /**
     * Sets the text that appears before a command block's command in chat
     * Default is '@'
     *
     * @param prefix
     */
    public void setPrefix(String prefix) {
        base.prefix = prefix;
    }

    /**
     * Returns the text that appears before a command block's command in chat
     * Default is '@'
     *
     * @return
     */
    public String getPrefix() {
        return base.prefix;
    }

    @Override
    public NBTTagCompound getMetaTag() {
        return base.metadata;
    }

    @Override
    public void writeToTag(NBTTagCompound tag) {
        base.b(tag.getBaseTag());
    }

    @Override
    public void readFromTag(NBTTagCompound tag) {
        base.a(tag.getBaseTag());
    }
}
