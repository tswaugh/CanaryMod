import java.util.List;

public abstract class OEntityAnimal extends OEntityAgeable implements OIAnimals {

    private int d;
    private int e = 0;
    private Mob mob = new Mob(this);

    public OEntityAnimal(OWorld oworld) {
        super(oworld);
    }

    protected void bm() {
        if (this.b() != 0) {
            this.d = 0;
        }

        super.bm();
    }

    public void c() {
        super.c();
        if (this.b() != 0) {
            this.d = 0;
        }

        if (this.d > 0) {
            --this.d;
            String s = "heart";

            if (this.d % 10 == 0) {
                double d0 = this.aa.nextGaussian() * 0.02D;
                double d1 = this.aa.nextGaussian() * 0.02D;
                double d2 = this.aa.nextGaussian() * 0.02D;

                this.p.a(s, this.t + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N, this.u + 0.5D + (double) (this.aa.nextFloat() * this.O), this.v + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N, d0, d1, d2);
            }
        } else {
            this.e = 0;
        }
    }

    protected void a(OEntity oentity, float f) {
        if (oentity instanceof OEntityPlayer) {
            if (f < 3.0F) {
                double d0 = oentity.t - this.t;
                double d1 = oentity.v - this.v;

                this.z = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
                this.b = true;
            }

            OEntityPlayer oentityplayer = (OEntityPlayer) oentity;

            if (oentityplayer.bS() == null || !this.c(oentityplayer.bS())) {
                this.a_ = null;
            }
        } else if (oentity instanceof OEntityAnimal) {
            OEntityAnimal oentityanimal = (OEntityAnimal) oentity;

            if (this.b() > 0 && oentityanimal.b() < 0) {
                if ((double) f < 2.5D) {
                    this.b = true;
                }
            } else if (this.d > 0 && oentityanimal.d > 0) {
                if (oentityanimal.a_ == null && !(Boolean) manager.callHook(PluginLoader.Hook.MOB_TARGET, this.getEntity(), oentityanimal.getEntity())) {
                    oentityanimal.a_ = this;
                }

                if (oentityanimal.a_ == this && (double) f < 3.5D) {
                    ++oentityanimal.d;
                    ++this.d;
                    ++this.e;
                    if (this.e % 4 == 0) {
                        this.p.a("heart", this.t + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N, this.u + 0.5D + (double) (this.aa.nextFloat() * this.O), this.v + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N, 0.0D, 0.0D, 0.0D);
                    }

                    if (this.e == 60) {
                        this.b((OEntityAnimal) oentity);
                    }
                } else {
                    this.e = 0;
                }
            } else {
                this.e = 0;
                this.a_ = null;
            }
        }
    }

    private void b(OEntityAnimal oentityanimal) {
        OEntityAgeable oentityageable = this.a((OEntityAgeable) oentityanimal);

        if (oentityageable != null) {
            this.a(6000);
            oentityanimal.a(6000);
            this.d = 0;
            this.e = 0;
            this.a_ = null;
            oentityanimal.a_ = null;
            oentityanimal.e = 0;
            oentityanimal.d = 0;
            oentityageable.a(-24000);
            oentityageable.b(this.t, this.u, this.v, this.z, this.A);

            for (int i = 0; i < 7; ++i) {
                double d0 = this.aa.nextGaussian() * 0.02D;
                double d1 = this.aa.nextGaussian() * 0.02D;
                double d2 = this.aa.nextGaussian() * 0.02D;

                this.p.a("heart", this.t + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N, this.u + 0.5D + (double) (this.aa.nextFloat() * this.O), this.v + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N, d0, d1, d2);
            }

            this.p.d((OEntity) oentityageable);
        }
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.ar()) {
            return false;
        } else {
            this.c = 60;
            this.a_ = null;
            this.d = 0;
            return super.a(odamagesource, i);
        }
    }

    public float a(int i, int j, int k) {
        return this.p.a(i, j - 1, k) == OBlock.x.cm ? 10.0F : this.p.p(i, j, k) - 0.5F;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("InLove", this.d);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.d = onbttagcompound.e("InLove");
    }

    protected OEntity j() {
        if (this.c > 0) {
            return null;
        } else {
            float f = 8.0F;
            List list;
            int i;
            OEntityAnimal oentityanimal;

            if (this.d > 0) {
                list = this.p.a(this.getClass(), this.D.b((double) f, (double) f, (double) f));

                for (i = 0; i < list.size(); ++i) {
                    oentityanimal = (OEntityAnimal) list.get(i);
                    if (oentityanimal != this && oentityanimal.d > 0) {
                        return oentityanimal;
                    }
                }
            } else if (this.b() == 0) {
                list = this.p.a(OEntityPlayer.class, this.D.b((double) f, (double) f, (double) f));

                for (i = 0; i < list.size(); ++i) {
                    OEntityPlayer oentityplayer = (OEntityPlayer) list.get(i);

                    if (oentityplayer.bS() != null && this.c(oentityplayer.bS())) {
                        return oentityplayer;
                    }
                }
            } else if (this.b() > 0) {
                list = this.p.a(this.getClass(), this.D.b((double) f, (double) f, (double) f));

                for (i = 0; i < list.size(); ++i) {
                    oentityanimal = (OEntityAnimal) list.get(i);
                    if (oentityanimal != this && oentityanimal.b() < 0) {
                        return oentityanimal;
                    }
                }
            }

            return null;
        }
    }

    public boolean bs() {
        int i = OMathHelper.c(this.t);
        int j = OMathHelper.c(this.D.b);
        int k = OMathHelper.c(this.v);

        return this.p.a(i, j - 1, k) == OBlock.x.cm && this.p.l(i, j, k) > 8 && super.bs();
    }

    public int aN() {
        return 120;
    }

    protected boolean bj() {
        return false;
    }

    protected int c(OEntityPlayer oentityplayer) {
        return 1 + this.p.t.nextInt(3);
    }

    public boolean c(OItemStack oitemstack) {
        return oitemstack.c == OItem.T.cj;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bJ.g();

        if (oitemstack != null && this.c(oitemstack) && this.b() == 0) {
            if (!oentityplayer.cd.d) {
                --oitemstack.a;
                if (oitemstack.a <= 0) {
                    oentityplayer.bJ.a(oentityplayer.bJ.c, (OItemStack) null);
                }
            }

            this.d = 600;
            this.a_ = null;

            for (int i = 0; i < 7; ++i) {
                double d0 = this.aa.nextGaussian() * 0.02D;
                double d1 = this.aa.nextGaussian() * 0.02D;
                double d2 = this.aa.nextGaussian() * 0.02D;

                this.p.a("heart", this.t + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N, this.u + 0.5D + (double) (this.aa.nextFloat() * this.O), this.v + (double) (this.aa.nextFloat() * this.N * 2.0F) - (double) this.N, d0, d1, d2);
            }

            return true;
        } else {
            return super.a(oentityplayer);
        }
    }

    public boolean r() {
        return this.d > 0;
    }

    public void s() {
        this.d = 0;
    }

    public boolean a(OEntityAnimal oentityanimal) {
        return oentityanimal == this ? false : (oentityanimal.getClass() != this.getClass() ? false : this.r() && oentityanimal.r());
    }

    @Override
    public LivingEntity getEntity() {
        return mob;
    } //
}
