
public class OEntityCow extends OEntityAnimal {

    public OEntityCow(OWorld oworld) {
        super(oworld);
        this.az = "/mob/cow.png";
        this.a(0.9F, 1.3F);
        this.as().a(true);
        this.bg.a(0, new OEntityAISwimming(this));
        this.bg.a(1, new OEntityAIPanic(this, 0.38F));
        this.bg.a(2, new OEntityAIMate(this, 0.2F));
        this.bg.a(3, new OEntityAITempt(this, 0.25F, OItem.T.bT, false));
        this.bg.a(4, new OEntityAIFollowParent(this, 0.25F));
        this.bg.a(5, new OEntityAIWander(this, 0.2F));
        this.bg.a(6, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.bg.a(7, new OEntityAILookIdle(this));
    }

    public boolean aV() {
        return true;
    }

    public int aM() {
        return 10;
    }

    protected String aQ() {
        return "mob.cow";
    }

    protected String aR() {
        return "mob.cowhurt";
    }

    protected String aS() {
        return "mob.cowhurt";
    }

    protected float aP() {
        return 0.4F;
    }

    protected int aT() {
        return OItem.aF.bT;
    }

    protected void a(boolean flag, int i) {
        int j = this.Z.nextInt(3) + this.Z.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.aF.bT, 1);
        }

        j = this.Z.nextInt(3) + 1 + this.Z.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            if (this.ad()) {
                this.b(OItem.bj.bT, 1);
            } else {
                this.b(OItem.bi.bT, 1);
            }
        }

    }

    public boolean c(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.by.g();

        if (oitemstack != null && oitemstack.c == OItem.aw.bT && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COW_MILK, ((OEntityPlayerMP) oentityplayer).getPlayer(), new Mob(this))) {
            if (--oitemstack.a <= 0) {
                oentityplayer.by.a(oentityplayer.by.c, new OItemStack(OItem.aG));
            } else if (!oentityplayer.by.a(new OItemStack(OItem.aG))) {
                oentityplayer.b(new OItemStack(OItem.aG.bT, 1, 0));
            }
            
            return true;
        } else {
            return super.c(oentityplayer);
        }
    }

    public OEntityAnimal a(OEntityAnimal oentityanimal) {
        return new OEntityCow(this.p);
    }
}
