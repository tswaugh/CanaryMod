import java.util.List;


public class OEntityLightningBolt extends OEntityWeatherEffect {

    private int b;
    public long a = 0L;
    private int c;

    public OEntityLightningBolt(OWorld var1, double var2, double var4, double var6) {
        super(var1);
        this.c(var2, var4, var6, 0.0F, 0.0F);
        this.b = 2;
        this.a = this.bP.nextLong();
        this.c = this.bP.nextInt(3) + 1;
        if (var1.v >= 2 && var1.a(OMathHelper.b(var2), OMathHelper.b(var4), OMathHelper.b(var6), 10)) {
            int var8 = OMathHelper.b(var2);
            int var9 = OMathHelper.b(var4);
            int var10 = OMathHelper.b(var6);

            if (var1.a(var8, var9, var10) == 0 && OBlock.at.c(var1, var8, var9, var10)) {
                // CanaryMod: Ignite hook
                Block block = this.bf.world.getBlockAt(var8, var9, var10);

                block.setStatus(5); // lightning
                if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null)) {
                    var1.e(var8, var9, var10, OBlock.at.bO);
                }
            }

            for (var8 = 0; var8 < 4; ++var8) {
                var9 = OMathHelper.b(var2) + this.bP.nextInt(3) - 1;
                var10 = OMathHelper.b(var4) + this.bP.nextInt(3) - 1;
                int var11 = OMathHelper.b(var6) + this.bP.nextInt(3) - 1;

                if (var1.a(var9, var10, var11) == 0 && OBlock.at.c(var1, var9, var10, var11)) {
                    // CanaryMod: Ignite hook
                    Block block = this.bf.world.getBlockAt(var9, var10, var11);

                    block.setStatus(5); // lightning
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null)) {
                        var1.e(var9, var10, var11, OBlock.at.bO);
                    }
                }
            }
        }

    }

    public void w_() {
        super.w_();
        if (this.b == 2) {
            this.bf.a(this.bj, this.bk, this.bl, "ambient.weather.thunder", 10000.0F, 0.8F + this.bP.nextFloat() * 0.2F);
            this.bf.a(this.bj, this.bk, this.bl, "random.explode", 2.0F, 0.5F + this.bP.nextFloat() * 0.2F);
        }

        --this.b;
        if (this.b < 0) {
            if (this.c == 0) {
                this.S();
            } else if (this.b < -this.bP.nextInt(10)) {
                --this.c;
                this.b = 1;
                this.a = this.bP.nextLong();
                if (this.bf.a(OMathHelper.b(this.bj), OMathHelper.b(this.bk), OMathHelper.b(this.bl), 10)) {
                    int var1 = OMathHelper.b(this.bj);
                    int var2 = OMathHelper.b(this.bk);
                    int var3 = OMathHelper.b(this.bl);

                    if (this.bf.a(var1, var2, var3) == 0 && OBlock.at.c(this.bf, var1, var2, var3)) {
                        // CanaryMod: Ignite hook
                        Block block = this.bf.world.getBlockAt(var1, var2, var3);

                        block.setStatus(5); // lightning
                        if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null)) {
                        this.bf.e(var1, var2, var3, OBlock.at.bO);
                        }
                    }
                }
            }
        }

        if (this.b >= 0) {
            double var4 = 3.0D;
            List var8 = this.bf.b((OEntity) this, OAxisAlignedBB.b(this.bj - var4, this.bk - var4, this.bl - var4, this.bj + var4, this.bk + 6.0D + var4, this.bl + var4));

            for (int var6 = 0; var6 < var8.size(); ++var6) {
                OEntity var7 = (OEntity) var8.get(var6);

                var7.a(this);
            }

            this.bf.s = 2;
        }

    }

    protected void b() {}

    protected void a(ONBTTagCompound var1) {}

    protected void b(ONBTTagCompound var1) {}
}
