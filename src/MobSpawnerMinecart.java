
public class MobSpawnerMinecart extends Minecart {

    MobSpawnerMinecart(OEntityMinecartMobSpawner o) {
        super(o);
    }

    /**
     * Get the {@link MobSpawnerLogic} for this <tt>MobSpawnerMinecart</tt>.
     * @return This <tt>MobSpawnerMinecart</tt>'s {@link MobSpawnerLogic}
     */
    public MobSpawnerLogic getLogic() {
        return this.getEntity().a.logic;
    }

    @Override
    public OEntityMinecartMobSpawner getEntity() {
        return (OEntityMinecartMobSpawner) super.getEntity();
    }
}
