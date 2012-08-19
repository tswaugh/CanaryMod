
public class OEntityEnderPearl extends OEntityThrowable {

    public OEntityEnderPearl(OWorld oworld) {
        super(oworld);
    }

    public OEntityEnderPearl(OWorld oworld, OEntityLiving oentityliving) {
        super(oworld, oentityliving);
    }

    public OEntityEnderPearl(OWorld oworld, double d0, double d1, double d2) {
        super(oworld, d0, d1, d2);
    }

    protected void a(OMovingObjectPosition omovingobjectposition) {
        if (omovingobjectposition.g != null && omovingobjectposition.g.a(ODamageSource.a((OEntity) this, this.c), 0)) {
            ;
        }
        // CanaryMod start - Fix enderpwarl dupe bug
        Player p = null;

        if (this.c instanceof OEntityPlayerMP) {
            p = new Player((OEntityPlayerMP) this.c);
        }
        if ((p != null) && !(etc.getServer().getPlayerList().contains(p))) {
            this.X(); // kill this entity
            return;
        }
        // CanaryMod end
        
        for (int i = 0; i < 32; ++i) {
            this.bi.a("portal", this.bm, this.bn + this.bS.nextDouble() * 2.0D, this.bo, this.bS.nextGaussian(), 0.0D, this.bS.nextGaussian());
        }

        if (!this.bi.F) {
            if (this.c != null) {
                this.c.a_(this.bm, this.bn, this.bo);
                this.c.bK = 0.0F;
                this.c.a(ODamageSource.i, 5);
            }

            this.X();
        }

    }
}
