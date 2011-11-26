
public class OEntityCow extends OEntityAnimal {

    public OEntityCow(OWorld var1) {
        super(var1);
        this.ac = "/mob/cow.png";
        this.b(0.9F, 1.3F);
    }

    public int c() {
        return 10;
    }

    public void b(ONBTTagCompound var1) {
        super.b(var1);
    }

    public void a(ONBTTagCompound var1) {
        super.a(var1);
    }

    protected String c_() {
        return "mob.cow";
    }

    protected String m() {
        return "mob.cowhurt";
    }

    protected String n() {
        return "mob.cowhurt";
    }

    protected float o() {
        return 0.4F;
    }

    protected int e() {
        return OItem.aE.bM;
    }

    protected void a(boolean var1, int var2) {
        int var3 = this.bP.nextInt(3) + this.bP.nextInt(1 + var2);

        int var4;

        for (var4 = 0; var4 < var3; ++var4) {
            this.b(OItem.aE.bM, 1);
        }

        var3 = this.bP.nextInt(3) + 1 + this.bP.nextInt(1 + var2);

        for (var4 = 0; var4 < var3; ++var4) {
            if (this.z()) {
                this.b(OItem.bi.bM, 1);
            } else {
                this.b(OItem.bh.bM, 1);
            }
        }

    }

    public boolean b(OEntityPlayer var1) {
        OItemStack var2 = var1.k.d();

        if (var2 != null && var2.c == OItem.av.bM) {
            // CanaryMod hook: onCowMilk
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COW_MILK, ((OEntityPlayerMP) var1).getPlayer(), new Mob(this))) {
                var1.k.a(var1.k.c, new OItemStack(OItem.aF));
                return true;
            } else {
                return super.b(var1);
            }
        } else {
            return super.b(var1);
        }
    }

    protected OEntityAnimal a(OEntityAnimal var1) {
        return new OEntityCow(this.bf);
    }
}
