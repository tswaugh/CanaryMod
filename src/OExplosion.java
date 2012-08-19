
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class OExplosion {

    public boolean a = false;
    private Random h = new Random();
    private OWorld i;
    public double b;
    public double c;
    public double d;
    public OEntity e;
    public float f;
    public Set g;

    // CanaryMod Start
    protected boolean toRet;
    protected List blocksaffected;
    // CanaryMod End

    public OExplosion(OWorld oworld, OEntity oentity, double d0, double d1, double d2, float f) {
        super();
        this.i = oworld;
        this.e = oentity;
        this.f = f;
        this.b = d0;
        this.c = d1;
        this.d = d2;
        this.g = new HashSet();
        this.toRet = false;
        this.blocksaffected = new ArrayList();
    }

    public void a() {
        // CanaryMod: allow explosion
        Block block = new Block(i.world, i.a((int) Math.floor(b), (int) Math.floor(c), (int) Math.floor(d)), (int) Math.floor(b), (int) Math.floor(c), (int) Math.floor(d));

        if (this.e == null) {
            block.setStatus(1);
        } else if (this.e instanceof OEntityCreeper) {
            block.setStatus(2);
        } else if (this.e instanceof OEntityFireball) {
            block.setStatus(3);
        }

        float f = this.f;
        byte b0 = 16;

        int i;
        int j;
        int k;
        double d0;
        double d1;
        double d2;

        for (i = 0; i < b0; ++i) {
            for (j = 0; j < b0; ++j) {
                for (k = 0; k < b0; ++k) {
                    if (i == 0 || i == b0 - 1 || j == 0 || j == b0 - 1 || k == 0 || k == b0 - 1) {
                        double d3 = (double) ((float) i / ((float) b0 - 1.0F) * 2.0F - 1.0F);
                        double d4 = (double) ((float) j / ((float) b0 - 1.0F) * 2.0F - 1.0F);
                        double d5 = (double) ((float) k / ((float) b0 - 1.0F) * 2.0F - 1.0F);
                        double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);

                        d3 /= d6;
                        d4 /= d6;
                        d5 /= d6;
                        float f1 = this.f * (0.7F + this.i.r.nextFloat() * 0.6F);

                        d0 = this.b;
                        d1 = this.c;
                        d2 = this.d;

                        for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
                            int l = OMathHelper.b(d0);
                            int i1 = OMathHelper.b(d1);
                            int j1 = OMathHelper.b(d2);
                            int k1 = this.i.a(l, i1, j1);

                            if (k1 > 0) {
                                f1 -= (OBlock.m[k1].a(this.e) + 0.3F) * f2;
                            }

                            if (f1 > 0.0F) {
                                this.g.add(new OChunkPosition(l, i1, j1));

                                // CanaryMod - set up a set of blocks rather than giving the OChunkPosition
                                Block blockaffect = new Block(this.i.world, this.i.a(l, i1, j1), l, i1, j1);

                                if (blockaffect.getType() != 0 && !blocksaffected.contains(blockaffect)) { // Don't add air to the list
                                    this.blocksaffected.add(blockaffect);
                                }
                            }

                            d0 += d3 * (double) f2;
                            d1 += d4 * (double) f2;
                            d2 += d5 * (double) f2;
                            
                        }
                    }
                }
            }
        }

        this.toRet = ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLODE, block, e, g) || (Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLOSION, block, (e != null ? new BaseEntity(e) : null), blocksaffected));
        this.f *= 2.0F;
        i = OMathHelper.b(this.b - (double) this.f - 1.0D);
        j = OMathHelper.b(this.b + (double) this.f + 1.0D);
        k = OMathHelper.b(this.c - (double) this.f - 1.0D);
        int l1 = OMathHelper.b(this.c + (double) this.f + 1.0D);
        int i2 = OMathHelper.b(this.d - (double) this.f - 1.0D);
        int j2 = OMathHelper.b(this.d + (double) this.f + 1.0D);
        List list = this.i.b(this.e, OAxisAlignedBB.b((double) i, (double) k, (double) i2, (double) j, (double) l1, (double) j2));
        OVec3D ovec3d = OVec3D.b(this.b, this.c, this.d);

        for (int k2 = 0; k2 < list.size(); ++k2) {
            OEntity oentity = (OEntity) list.get(k2);
            double d7 = oentity.f(this.b, this.c, this.d) / (double) this.f;

            if (d7 <= 1.0D) {
                d0 = oentity.bm - this.b;
                d1 = oentity.bn - this.c;
                d2 = oentity.bo - this.d;
                double d8 = (double) OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

                d0 /= d8;
                d1 /= d8;
                d2 /= d8;
                double d9 = (double) this.i.a(ovec3d, oentity.bw);
                double d10 = (1.0D - d7) * d9;

                // CanaryMod Damage hook: Explosions
                int damage = (int) ((d10 * d10 + d10) / 2.0D * 8.0D * f + 1.0D);
                PluginLoader.DamageType dmgType = (e instanceof OEntityCreeper) ? PluginLoader.DamageType.CREEPER_EXPLOSION : PluginLoader.DamageType.EXPLOSION;

                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, dmgType, null, oentity.entity, damage) && !toRet) {
                    oentity.a(ODamageSource.l, (int) ((d10 * d10 + d10) / 2.0D * 8.0D * (double) this.f + 1.0D));
                }
                oentity.bp += d0 * d10;
                oentity.bq += d1 * d10;
                oentity.br += d2 * d10;
            }
        }

        this.f = f;
        ArrayList arraylist = new ArrayList();

        arraylist.addAll(this.g);
    }

    public void a(boolean flag) {
        this.i.a(this.b, this.c, this.d, "random.explode", 4.0F, (1.0F + (this.i.r.nextFloat() - this.i.r.nextFloat()) * 0.2F) * 0.7F);
        this.i.a("hugeexplosion", this.b, this.c, this.d, 0.0D, 0.0D, 0.0D);
        ArrayList arraylist = new ArrayList();

        arraylist.addAll(this.g);
        if (this.toRet) {
            this.g = new HashSet();
            return;
        }

        int i;
        OChunkPosition ochunkposition;
        int j;
        int k;
        int l;
        int i1;

        for (i = arraylist.size() - 1; i >= 0; --i) {
            ochunkposition = (OChunkPosition) arraylist.get(i);
            j = ochunkposition.a;
            k = ochunkposition.b;
            l = ochunkposition.c;
            i1 = this.i.a(j, k, l);
            if (flag) {
                double d0 = (double) ((float) j + this.i.r.nextFloat());
                double d1 = (double) ((float) k + this.i.r.nextFloat());
                double d2 = (double) ((float) l + this.i.r.nextFloat());
                double d3 = d0 - this.b;
                double d4 = d1 - this.c;
                double d5 = d2 - this.d;
                double d6 = (double) OMathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);

                d3 /= d6;
                d4 /= d6;
                d5 /= d6;
                double d7 = 0.5D / (d6 / (double) this.f + 0.1D);

                d7 *= (double) (this.i.r.nextFloat() * this.i.r.nextFloat() + 0.3F);
                d3 *= d7;
                d4 *= d7;
                d5 *= d7;
                this.i.a("explode", (d0 + this.b * 1.0D) / 2.0D, (d1 + this.c * 1.0D) / 2.0D, (d2 + this.d * 1.0D) / 2.0D, d3, d4, d5);
                this.i.a("smoke", d0, d1, d2, d3, d4, d5);
            }

            if (i1 > 0) {
                OBlock.m[i1].a(this.i, j, k, l, this.i.c(j, k, l), 0.3F, 0);
                this.i.e(j, k, l, 0);
                OBlock.m[i1].a_(this.i, j, k, l);
            }
        }

        if (this.a) {
            for (i = arraylist.size() - 1; i >= 0; --i) {
                ochunkposition = (OChunkPosition) arraylist.get(i);
                j = ochunkposition.a;
                k = ochunkposition.b;
                l = ochunkposition.c;
                i1 = this.i.a(j, k, l);
                int j1 = this.i.a(j, k - 1, l);

                if (i1 == 0 && OBlock.n[j1] && this.h.nextInt(3) == 0) {
                    this.i.e(j, k, l, OBlock.ar.bO);
                }
            }
        }

    }

    public boolean getRet() {
        return this.toRet;
    }
}
