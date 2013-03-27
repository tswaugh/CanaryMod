public class ChestMinecart extends ContainerMinecart {

    public ChestMinecart(OEntityMinecartChest o) {
        super(o);
    }

    public ChestMinecart(World world, double x, double y, double z) {
        this(new OEntityMinecartChest(world.getWorld(), x, y, z));
        world.spawnEntity(this);
    }

    @Override
    public OEntityMinecartChest getEntity() {
        return (OEntityMinecartChest) super.getEntity();
    }
}
