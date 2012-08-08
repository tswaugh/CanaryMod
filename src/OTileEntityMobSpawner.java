
public class OTileEntityMobSpawner extends OTileEntity {

    public int a = -1;
    protected String d = "Pig"; // CanaryMod: private -> protected
    public double b;
    public double c = 0.0D;

    public OTileEntityMobSpawner() {
        super();
        this.a = 20;
    }

    public void a(String s) {
        this.d = s;
    }

    public boolean c() {
        return this.k.a((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D, 16.0D) != null;
    }

    public void q_() {
        this.c = this.b;
        if (this.c()) {
            double d0 = (double) ((float) this.l + this.k.r.nextFloat());
            double d1 = (double) ((float) this.m + this.k.r.nextFloat());
            double d2 = (double) ((float) this.n + this.k.r.nextFloat());

            this.k.a("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
            this.k.a("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);

            for (this.b += (double) (1000.0F / ((float) this.a + 200.0F)); this.b > 360.0D; this.c -= 360.0D) {
                this.b -= 360.0D;
            }

            if (!this.k.F) {
                if (this.a == -1) {
                    this.e();
                }

                if (this.a > 0) {
                    --this.a;
                    return;
                }

                byte b0 = 4;

                for (int i = 0; i < b0; ++i) {
                    OEntityLiving oentityliving = (OEntityLiving) ((OEntityLiving) OEntityList.a(this.d, this.k));

                    if (oentityliving == null) {
                        return;
                    }

                    int j = this.k.a(oentityliving.getClass(), OAxisAlignedBB.b((double) this.l, (double) this.m, (double) this.n, (double) (this.l + 1), (double) (this.m + 1), (double) (this.n + 1)).b(8.0D, 4.0D, 8.0D)).size();

                    if (j >= 6) {
                        this.e();
                        return;
                    }

                    if (oentityliving != null) {
                        double d3 = (double) this.l + (this.k.r.nextDouble() - this.k.r.nextDouble()) * 4.0D;
                        double d4 = (double) (this.m + this.k.r.nextInt(3) - 1);
                        double d5 = (double) this.n + (this.k.r.nextDouble() - this.k.r.nextDouble()) * 4.0D;

                        oentityliving.c(d3, d4, d5, this.k.r.nextFloat() * 360.0F, 0.0F);
                        if (oentityliving.l()) {
                            // CanaryMod - set spawner block for spawned entity
                            oentityliving.spawner = (MobSpawner) k.world.getComplexBlock(l, m, n);
                            this.k.b((OEntity) oentityliving);
                            this.k.f(2004, this.l, this.m, this.n, 0);
                            oentityliving.aB();
                            this.e();
                        }
                    }
                }
            }

            super.q_();
        }
    }

    private void e() {
        this.a = 200 + this.k.r.nextInt(600);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        // CanaryMod - There is no more Monster class
        String entityId = onbttagcompound.j("EntityId");

        if (entityId.equalsIgnoreCase("Monster")) {
            this.d = "Pig";
        } else {
            this.d = entityId;
        }
        this.a = onbttagcompound.e("Delay");
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("EntityId", this.d);
        onbttagcompound.a("Delay", (short) this.a);
    }

    public OPacket d() {
        int i = OEntityList.a(this.d);

        return new OPacket132UpdateTileEntity(this.l, this.m, this.n, 1, i);
    }
}
