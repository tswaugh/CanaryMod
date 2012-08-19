
public abstract class OEntityAITarget extends OEntityAIBase {

    protected OEntityLiving c;
    protected float d;
    protected boolean e;
    private boolean a;
    private int b;
    private int f;
    private int g;

    public OEntityAITarget(OEntityLiving oentityliving, float f, boolean flag) {
        this(oentityliving, f, flag, false);
    }

    public OEntityAITarget(OEntityLiving oentityliving, float f, boolean flag, boolean flag1) {
        super();
        this.b = 0;
        this.f = 0;
        this.g = 0;
        this.c = oentityliving;
        this.d = f;
        this.e = flag;
        this.a = flag1;
    }

    public boolean b() {
        OEntityLiving oentityliving = this.c.at();

        if (oentityliving == null) {
            return false;
        } else if (!oentityliving.aE()) {
            return false;
        } else if (this.c.j(oentityliving) > (double) (this.d * this.d)) {
            return false;
        } else {
            if (this.e) {
                if (!this.c.am().a(oentityliving)) {
                    if (++this.g > 60) {
                        return false;
                    }
                } else {
                    this.g = 0;
                }
            }

            return true;
        }
    }

    public void c() {
        this.b = 0;
        this.f = 0;
        this.g = 0;
    }

    public void d() {
        this.c.b((OEntityLiving) null);
    }

    protected boolean a(OEntityLiving oentityliving, boolean flag) {
        if (oentityliving == null) {
            return false;
        } else if (oentityliving == this.c) {
            return false;
        } else if (!oentityliving.aE()) {
            return false;
        } else if (oentityliving.bw.e > this.c.bw.b && oentityliving.bw.b < this.c.bw.e) {
            if (!this.c.a(oentityliving.getClass())) {
                return false;
            } else {
                if (this.c instanceof OEntityTameable && ((OEntityTameable) this.c).u_()) {
                    if (oentityliving instanceof OEntityTameable && ((OEntityTameable) oentityliving).u_()) {
                        return false;
                    }

                    if (oentityliving == ((OEntityTameable) this.c).w_()) {
                        return false;
                    }
                } else if (oentityliving instanceof OEntityPlayer && !flag && ((OEntityPlayer) oentityliving).L.a) {
                    return false;
                }

                if (!this.c.e(OMathHelper.b(oentityliving.bm), OMathHelper.b(oentityliving.bn), OMathHelper.b(oentityliving.bo))) {
                    return false;
                } else if (this.e && !this.c.am().a(oentityliving)) {
                    return false;
                } else {
                    if (this.a) {
                        if (--this.f <= 0) {
                            this.b = 0;
                        }

                        if (this.b == 0) {
                            this.b = this.a(oentityliving) ? 1 : 2;
                        }

                        if (this.b == 2) {
                            return false;
                        }
                    }

                    // CanaryMod - MOB_TARGET
                    if (oentityliving.entity.isPlayer()) {
                        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentityliving.entity.getPlayer(), this.c.entity)) {
                            return false;
                        }
                    }
                    // CanaryMod end

                    return true;
                }
            }
        } else {
            return false;
        }
    }

    private boolean a(OEntityLiving oentityliving) {
        this.f = 10 + this.c.an().nextInt(5);
        OPathEntity opathentity = this.c.al().a(oentityliving);

        if (opathentity == null) {
            return false;
        } else {
            OPathPoint opathpoint = opathentity.c();

            if (opathpoint == null) {
                return false;
            } else {
                int i = opathpoint.a - OMathHelper.b(oentityliving.bm);
                int j = opathpoint.c - OMathHelper.b(oentityliving.bo);

                return (double) (i * i + j * j) <= 2.25D;
            }
        }
    }
}
