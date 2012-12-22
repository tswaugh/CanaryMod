import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class OTileEntityMobSpawner extends OTileEntity {

    public int a = -1;
    protected String d = "Pig"; // CanaryMod: private -> protected
    private List e = null;
    protected OTileEntityMobSpawnerSpawnData f = null; // CanaryMod: private -> protected
    public double b;
    public double c = 0.0D;
    protected int g = 200; // CanaryMod: private -> protected
    protected int h = 800; //CanaryMod: private -> protected
    protected int i = 4; //CanaryMod: private -> protected
    private OEntity j;
    protected int r = 6; //CanaryMod: private -> protected
    protected int s = 16; //CanaryMod: private -> protected
    protected int t = 4; //CanaryMod: private -> protected

    public OTileEntityMobSpawner() {
        this.a = 20;
    }

    public String a() {
        return this.f == null ? this.d : this.f.c;
    }

    public void a(String s) {
        this.d = s;
    }

    public boolean b() {
        return this.k.a((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D, (double) this.s) != null;
    }

    public void g() {
        if (this.b()) {
            double d0;

            if (this.k.I) {
                double d1 = (double) ((float) this.l + this.k.t.nextFloat());
                double d2 = (double) ((float) this.m + this.k.t.nextFloat());

                d0 = (double) ((float) this.n + this.k.t.nextFloat());
                this.k.a("smoke", d1, d2, d0, 0.0D, 0.0D, 0.0D);
                this.k.a("flame", d1, d2, d0, 0.0D, 0.0D, 0.0D);
                if (this.a > 0) {
                    --this.a;
                }

                this.c = this.b;
                this.b = (this.b + (double) (1000.0F / ((float) this.a + 200.0F))) % 360.0D;
            } else {
                if (this.a == -1) {
                    this.e();
                }

                if (this.a > 0) {
                    --this.a;
                    return;
                }

                boolean flag = false;

                for (int i = 0; i < this.i; ++i) {
                    OEntity oentity = OEntityList.a(this.a(), this.k);

                    if (oentity == null) {
                        return;
                    }

                    int j = this.k.a(oentity.getClass(), OAxisAlignedBB.a().a((double) this.l, (double) this.m, (double) this.n, (double) (this.l + 1), (double) (this.m + 1), (double) (this.n + 1)).b((double) (this.t * 2), 4.0D, (double) (this.t * 2))).size();

                    if (j >= this.r) {
                        this.e();
                        return;
                    }

                    if (oentity != null) {
                        d0 = (double) this.l + (this.k.t.nextDouble() - this.k.t.nextDouble()) * (double) this.t;
                        double d3 = (double) (this.m + this.k.t.nextInt(3) - 1);
                        double d4 = (double) this.n + (this.k.t.nextDouble() - this.k.t.nextDouble()) * (double) this.t;
                        OEntityLiving oentityliving = oentity instanceof OEntityLiving ? (OEntityLiving) oentity : null;

                        oentity.b(d0, d3, d4, this.k.t.nextFloat() * 360.0F, 0.0F);
                        if (oentityliving == null || oentityliving.bs()) {
                            this.a(oentity);
                            this.k.d(oentity);
                            this.k.f(2004, this.l, this.m, this.n, 0);
                            if (oentityliving != null) {
                                // CanaryMod - set spawner block for spawned entity
                                oentityliving.spawner = (MobSpawner) this.k.world.getComplexBlock(this.l, this.m, this.n);
                                oentityliving.aR();
                            }

                            flag = true;
                        }
                    }
                }

                if (flag) {
                    this.e();
                }
            }

            super.g();
        }
    }

    public void a(OEntity oentity) {
        if (this.f != null) {
            ONBTTagCompound onbttagcompound = new ONBTTagCompound();

            oentity.c(onbttagcompound);
            Iterator iterator = this.f.b.c().iterator();

            while (iterator.hasNext()) {
                ONBTBase onbtbase = (ONBTBase) iterator.next();

                onbttagcompound.a(onbtbase.e(), onbtbase.b());
            }

            oentity.e(onbttagcompound);
        } else if (oentity instanceof OEntityLiving && oentity.p != null) {
            ((OEntityLiving) oentity).bG();
        }
    }

    private void e() {
        if (this.h <= this.g) {
            this.a = this.g;
        } else {
            this.a = this.g + this.k.t.nextInt(this.h - this.g);
        }

        if (this.e != null && this.e.size() > 0) {
            this.f = (OTileEntityMobSpawnerSpawnData) OWeightedRandom.a(this.k.t, (Collection) this.e);
            this.k.i(this.l, this.m, this.n);
        }

        this.k.c(this.l, this.m, this.n, this.q().cm, 1, 0);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.d = onbttagcompound.i("EntityId");
        this.a = onbttagcompound.d("Delay");
        if (onbttagcompound.b("SpawnPotentials")) {
            this.e = new ArrayList();
            ONBTTagList onbttaglist = onbttagcompound.m("SpawnPotentials");

            for (int i = 0; i < onbttaglist.c(); ++i) {
                this.e.add(new OTileEntityMobSpawnerSpawnData(this, (ONBTTagCompound) onbttaglist.b(i)));
            }
        } else {
            this.e = null;
        }

        if (onbttagcompound.b("SpawnData")) {
            this.f = new OTileEntityMobSpawnerSpawnData(this, onbttagcompound.l("SpawnData"), this.d);
        } else {
            this.f = null;
        }

        if (onbttagcompound.b("MinSpawnDelay")) {
            this.g = onbttagcompound.d("MinSpawnDelay");
            this.h = onbttagcompound.d("MaxSpawnDelay");
            this.i = onbttagcompound.d("SpawnCount");
        }

        if (onbttagcompound.b("MaxNearbyEntities")) {
            this.r = onbttagcompound.d("MaxNearbyEntities");
            this.s = onbttagcompound.d("RequiredPlayerRange");
        }

        if (onbttagcompound.b("SpawnRange")) {
            this.t = onbttagcompound.d("SpawnRange");
        }

        if (this.k != null && this.k.I) {
            this.j = null;
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("EntityId", this.a());
        onbttagcompound.a("Delay", (short) this.a);
        onbttagcompound.a("MinSpawnDelay", (short) this.g);
        onbttagcompound.a("MaxSpawnDelay", (short) this.h);
        onbttagcompound.a("SpawnCount", (short) this.i);
        onbttagcompound.a("MaxNearbyEntities", (short) this.r);
        onbttagcompound.a("RequiredPlayerRange", (short) this.s);
        onbttagcompound.a("SpawnRange", (short) this.t);
        if (this.f != null) {
            onbttagcompound.a("SpawnData", (ONBTTagCompound) this.f.b.b());
        }

        if (this.f != null || this.e != null && this.e.size() > 0) {
            ONBTTagList onbttaglist = new ONBTTagList();

            if (this.e != null && this.e.size() > 0) {
                Iterator iterator = this.e.iterator();

                while (iterator.hasNext()) {
                    OTileEntityMobSpawnerSpawnData otileentitymobspawnerspawndata = (OTileEntityMobSpawnerSpawnData) iterator.next();

                    onbttaglist.a((ONBTBase) otileentitymobspawnerspawndata.a());
                }
            } else {
                onbttaglist.a((ONBTBase) this.f.a());
            }

            onbttagcompound.a("SpawnPotentials", (ONBTBase) onbttaglist);
        }
    }

    public OPacket l() {
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        this.b(onbttagcompound);
        onbttagcompound.o("SpawnPotentials");
        return new OPacket132TileEntityData(this.l, this.m, this.n, 1, onbttagcompound);
    }

    public void b(int i, int j) {
        if (i == 1 && this.k.I) {
            this.a = this.g;
        }
    }
}
