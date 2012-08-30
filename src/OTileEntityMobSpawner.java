import java.util.Iterator;

public class OTileEntityMobSpawner extends OTileEntity {

    public int a = -1;
    protected String d = "Pig"; // CanaryMod: private -> protected
    private ONBTTagCompound e = null;
    public double b;
    public double c = 0.0D;
    private int f = 200;
    private int g = 800;
    private int h = 4;

    public OTileEntityMobSpawner() {
        super();
        this.a = 20;
    }

    public void a(String s) {
        this.d = s;
    }

    public boolean b() {
        return this.k.a((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D, 16.0D) != null;
    }

    public void g() {
        if (this.b()) {
            if (this.k.K) {
                double d0 = (double) ((float) this.l + this.k.v.nextFloat());
                double d1 = (double) ((float) this.m + this.k.v.nextFloat());
                double d2 = (double) ((float) this.n + this.k.v.nextFloat());

                this.k.a("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
                this.k.a("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
                this.c = this.b % 360.0D;
                this.b += 4.545454502105713D;
            } else {
                if (this.a == -1) {
                    this.f();
                }

                if (this.a > 0) {
                    --this.a;
                    return;
                }

                for (int i = 0; i < this.h; ++i) {
                    OEntity oentity = OEntityList.a(this.d, this.k);

                    if (oentity == null) {
                        return;
                    }

                    int j = this.k.a(oentity.getClass(), OAxisAlignedBB.a().a((double) this.l, (double) this.m, (double) this.n, (double) (this.l + 1), (double) (this.m + 1), (double) (this.n + 1)).b(8.0D, 4.0D, 8.0D)).size();

                    if (j >= 6) {
                        this.f();
                        return;
                    }

                    if (oentity != null) {
                        double d3 = (double) this.l + (this.k.v.nextDouble() - this.k.v.nextDouble()) * 4.0D;
                        double d4 = (double) (this.m + this.k.v.nextInt(3) - 1);
                        double d5 = (double) this.n + (this.k.v.nextDouble() - this.k.v.nextDouble()) * 4.0D;
                        OEntityLiving oentityliving = oentity instanceof OEntityLiving ? (OEntityLiving) oentity : null;

                        oentity.b(d3, d4, d5, this.k.v.nextFloat() * 360.0F, 0.0F);
                        if (oentityliving == null || oentityliving.bi()) {
                            // CanaryMod - set spawner block for spawned entity
                            oentityliving.spawner = (MobSpawner) this.k.world.getComplexBlock(this.l, this.m, this.n);
                            this.a(oentity);
                            this.k.d(oentity);
                            this.k.e(2004, this.l, this.m, this.n, 0);
                            if (oentityliving != null) {
                                oentityliving.aK();
                            }

                            this.f();
                        }
                    }
                }
            }

            super.g();
        }
    }

    public void a(OEntity oentity) {
        if (this.e != null) {
            ONBTTagCompound onbttagcompound = new ONBTTagCompound();

            oentity.c(onbttagcompound);
            Iterator iterator = this.e.c().iterator();

            while (iterator.hasNext()) {
                ONBTBase onbtbase = (ONBTBase) iterator.next();

                onbttagcompound.a(onbtbase.e(), onbtbase.b());
            }

            oentity.e(onbttagcompound);
        }
    }

    private void f() {
        this.a = this.f + this.k.v.nextInt(this.g - this.f);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.d = onbttagcompound.i("EntityId");
        this.a = onbttagcompound.d("Delay");
        if (onbttagcompound.b("SpawnData")) {
            this.e = onbttagcompound.l("SpawnData");
        } else {
            this.e = null;
        }

        if (onbttagcompound.b("MinSpawnDelay")) {
            this.f = onbttagcompound.d("MinSpawnDelay");
            this.g = onbttagcompound.d("MaxSpawnDelay");
            this.h = onbttagcompound.d("SpawnCount");
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("EntityId", this.d);
        onbttagcompound.a("Delay", (short) this.a);
        onbttagcompound.a("MinSpawnDelay", (short) this.f);
        onbttagcompound.a("MaxSpawnDelay", (short) this.g);
        onbttagcompound.a("SpawnCount", (short) this.h);
        if (this.e != null) {
            onbttagcompound.a("SpawnData", this.e);
        }
    }

    public OPacket e() {
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        this.b(onbttagcompound);
        return new OPacket132TileEntityData(this.l, this.m, this.n, 1, onbttagcompound);
    }
}
