public class OEntityCow extends OEntityAnimal {

    public OEntityCow(OWorld oworld) {
        super(oworld);
        this.aG = "/mob/cow.png";
        this.a(0.9F, 1.3F);
        this.az().a(true);
        this.bn.a(0, new OEntityAISwimming(this));
        this.bn.a(1, new OEntityAIPanic(this, 0.38F));
        this.bn.a(2, new OEntityAIMate(this, 0.2F));
        this.bn.a(3, new OEntityAITempt(this, 0.25F, OItem.T.cj, false));
        this.bn.a(4, new OEntityAIFollowParent(this, 0.25F));
        this.bn.a(5, new OEntityAIWander(this, 0.2F));
        this.bn.a(6, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.bn.a(7, new OEntityAILookIdle(this));
    }

    public boolean be() {
        return true;
    }

    public int aT() {
        //CanaryMod: set max health here, but check for uninitialized value.
        return this.maxHealth == 0 ? 10 : this.maxHealth;
    }

    protected String aY() {
        return "mob.cow.say";
    }

    protected String aZ() {
        return "mob.cow.hurt";
    }

    protected String ba() {
        return "mob.cow.hurt";
    }

    protected void a(int i, int j, int k, int l) {
        this.a("mob.cow.step", 0.15F, 1.0F);
    }

    protected float aX() {
        return 0.4F;
    }

    protected int bb() {
        return OItem.aF.cj;
    }

    protected void a(boolean flag, int i) {
        int j = this.aa.nextInt(3) + this.aa.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.aF.cj, 1);
        }

        j = this.aa.nextInt(3) + 1 + this.aa.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            if (this.af()) {
                this.b(OItem.bj.cj, 1);
            } else {
                this.b(OItem.bi.cj, 1);
            }
        }
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bJ.g();

        if (oitemstack != null && oitemstack.c == OItem.aw.cj && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COW_MILK, ((OEntityPlayerMP) oentityplayer).getPlayer(), new Mob(this))) {
            if (--oitemstack.a <= 0) {
                oentityplayer.bJ.a(oentityplayer.bJ.c, new OItemStack(OItem.aG));
            } else if (!oentityplayer.bJ.a(new OItemStack(OItem.aG))) {
                oentityplayer.c(new OItemStack(OItem.aG.cj, 1, 0));
            }

            return true;
        } else {
            return super.a(oentityplayer);
        }
    }

    public OEntityCow b(OEntityAgeable oentityageable) {
        return new OEntityCow(this.p);
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }
}
