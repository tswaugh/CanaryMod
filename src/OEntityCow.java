
public class OEntityCow extends OEntityAnimal {

    public OEntityCow(OWorld oworld) {
        super(oworld);
        this.ae = "/mob/cow.png";
        this.b(0.9F, 1.3F);
        this.al().a(true);
        this.aL.a(0, new OEntityAISwimming(this));
        this.aL.a(1, new OEntityAIPanic(this, 0.38F));
        this.aL.a(2, new OEntityAIMate(this, 0.2F));
        this.aL.a(3, new OEntityAITempt(this, 0.25F, OItem.S.bP, false));
        this.aL.a(4, new OEntityAIFollowParent(this, 0.25F));
        this.aL.a(5, new OEntityAIWander(this, 0.2F));
        this.aL.a(6, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.aL.a(7, new OEntityAILookIdle(this));
    }

    public boolean c_() {
        return true;
    }

    public int d() {
        return 10;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
    }

    protected String i() {
        return "mob.cow";
    }

    protected String j() {
        return "mob.cowhurt";
    }

    protected String k() {
        return "mob.cowhurt";
    }

    protected float p() {
        return 0.4F;
    }

    protected int f() {
        return OItem.aE.bP;
    }

    protected void a(boolean flag, int i) {
        int j = this.bS.nextInt(3) + this.bS.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.aE.bP, 1);
        }

        j = this.bS.nextInt(3) + 1 + this.bS.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            if (this.B_()) {
                this.b(OItem.bi.bP, 1);
            } else {
                this.b(OItem.bh.bP, 1);
            }
        }

    }

    public boolean b(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.k.d();

        if (oitemstack != null && oitemstack.c == OItem.av.bP) {
            // CanaryMod hook: onCowMilk
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COW_MILK, ((OEntityPlayerMP) oentityplayer).getPlayer(), new Mob(this))) {
                oentityplayer.k.a(oentityplayer.k.c, new OItemStack(OItem.aF));
                return true;
            } else {
                return super.b(oentityplayer);
            }
        } else {
            return super.b(oentityplayer);
        }
    }

    public OEntityAnimal a(OEntityAnimal oentityanimal) {
        return new OEntityCow(this.bi);
    }
}
