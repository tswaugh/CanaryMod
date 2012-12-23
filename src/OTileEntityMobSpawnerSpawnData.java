class OTileEntityMobSpawnerSpawnData extends OWeightedRandomItem {

    public ONBTTagCompound b; // CanaryMod: make this not final.
    public final String c;

    final OTileEntityMobSpawner d;

    public OTileEntityMobSpawnerSpawnData(OTileEntityMobSpawner otileentitymobspawner, ONBTTagCompound onbttagcompound) {
        super(onbttagcompound.e("Weight"));
        this.d = otileentitymobspawner;
        this.b = onbttagcompound.l("Properties");
        this.c = onbttagcompound.i("Type");
    }

    public OTileEntityMobSpawnerSpawnData(OTileEntityMobSpawner otileentitymobspawner, ONBTTagCompound onbttagcompound, String s) {
        super(1);
        this.d = otileentitymobspawner;
        this.b = onbttagcompound;
        this.c = s;
    }

    public ONBTTagCompound a() {
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        onbttagcompound.a("Properties", this.b);
        onbttagcompound.a("Type", this.c);
        onbttagcompound.a("Weight", this.a);
        return onbttagcompound;
    }
}
