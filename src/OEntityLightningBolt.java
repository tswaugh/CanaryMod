import java.util.List;


public class OEntityLightningBolt extends OEntityWeatherEffect {

    private int b;
    public long a = 0L;
    private int c;

    public OEntityLightningBolt(OWorld oworld, double d0, double d1, double d2) {
        super(oworld);
        this.c(d0, d1, d2, 0.0F, 0.0F);
        this.b = 2;
        this.a = this.bS.nextLong();
        this.c = this.bS.nextInt(3) + 1;
        if (oworld.q >= 2 && oworld.a(OMathHelper.b(d0), OMathHelper.b(d1), OMathHelper.b(d2), 10)) {
            int i = OMathHelper.b(d0);
            int j = OMathHelper.b(d1);
            int k = OMathHelper.b(d2);

            if (oworld.a(i, j, k) == 0 && OBlock.ar.c(oworld, i, j, k)) {
                // CanaryMod: Ignite hook
                Block block = this.bi.world.getBlockAt(i, j, k);

                block.setStatus(5); // lightning
                if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null)) {
                    oworld.e(i, j, k, OBlock.ar.bO);
                }
            }

            for (i = 0; i < 4; ++i) {
                j = OMathHelper.b(d0) + this.bS.nextInt(3) - 1;
                k = OMathHelper.b(d1) + this.bS.nextInt(3) - 1;
                int l = OMathHelper.b(d2) + this.bS.nextInt(3) - 1;

                if (oworld.a(j, k, l) == 0 && OBlock.ar.c(oworld, j, k, l)) {
                    // CanaryMod: Ignite hook
                    Block block = this.bi.world.getBlockAt(j, k, l);

                    block.setStatus(5); // lightning
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null)) {
                        oworld.e(j, k, l, OBlock.ar.bO);
                    }
                }
            }
        }

    }

    public void F_() {
        super.F_();
        if (this.b == 2) {
            this.bi.a(this.bm, this.bn, this.bo, "ambient.weather.thunder", 10000.0F, 0.8F + this.bS.nextFloat() * 0.2F);
            this.bi.a(this.bm, this.bn, this.bo, "random.explode", 2.0F, 0.5F + this.bS.nextFloat() * 0.2F);
        }

        --this.b;
        if (this.b < 0) {
            if (this.c == 0) {
                this.X();
            } else if (this.b < -this.bS.nextInt(10)) {
                --this.c;
                this.b = 1;
                this.a = this.bS.nextLong();
                if (this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo), 10)) {
                    int i = OMathHelper.b(this.bm);
                    int j = OMathHelper.b(this.bn);
                    int k = OMathHelper.b(this.bo);

                    if (this.bi.a(i, j, k) == 0 && OBlock.ar.c(this.bi, i, j, k)) {
                        // CanaryMod: Ignite hook
                        Block block = this.bi.world.getBlockAt(i, j, k);

                        block.setStatus(5); // lightning
                        if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null)) {
                            this.bi.e(i, j, k, OBlock.ar.bO);
                        }
                    }
                }
            }
        }

        if (this.b >= 0) {
            double d0 = 3.0D;
            List list = this.bi.b((OEntity) this, OAxisAlignedBB.b(this.bm - d0, this.bn - d0, this.bo - d0, this.bm + d0, this.bn + 6.0D + d0, this.bo + d0));

            for (int l = 0; l < list.size(); ++l) {
                OEntity oentity = (OEntity) list.get(l);

                oentity.a(this);
            }

            this.bi.n = 2;
        }

    }

    protected void b() {}

    protected void a(ONBTTagCompound onbttagcompound) {}

    protected void b(ONBTTagCompound onbttagcompound) {}
}
