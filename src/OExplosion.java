import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OExplosion {

    public boolean a = false;
    private int h = 16;
    private Random i = new Random();
    private OWorld j;
    public double b;
    public double c;
    public double d;
    public OEntity e;
    public float f;
    public List g = new ArrayList();
    private Map k = new HashMap();
    
    public OExplosion(OWorld oworld, OEntity oentity, double d0, double d1, double d2, float f) {
        this.j = oworld;
        this.e = oentity;
        this.f = f;
        this.b = d0;
        this.c = d1;
        this.d = d2;
    }

    public void a() {
        // CanaryMod: allow explosion
        Block block = new Block(this.j.world, this.j.world.getBlockIdAt((int) this.b, (int) this.c, (int) this.d), (int) this.b, (int) this.c, (int) this.d);

        if (this.e == null) {
            block.setStatus(1);
        } else if (this.e instanceof OEntityCreeper) {
            block.setStatus(2);
        } else if (this.e instanceof OEntityFireball) {
            block.setStatus(3);
        }

        float f = this.f;
        HashSet hashset = new HashSet();

        int i;
        int j;
        int k;
        double d0;
        double d1;
        double d2;

        for (i = 0; i < this.h; ++i) {
            for (j = 0; j < this.h; ++j) {
                for (k = 0; k < this.h; ++k) {
                    if (i == 0 || i == this.h - 1 || j == 0 || j == this.h - 1 || k == 0 || k == this.h - 1) {
                        double d3 = (double) ((float) i / ((float) this.h - 1.0F) * 2.0F - 1.0F);
                        double d4 = (double) ((float) j / ((float) this.h - 1.0F) * 2.0F - 1.0F);
                        double d5 = (double) ((float) k / ((float) this.h - 1.0F) * 2.0F - 1.0F);
                        double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);

                        d3 /= d6;
                        d4 /= d6;
                        d5 /= d6;
                        float f1 = this.f * (0.7F + this.j.v.nextFloat() * 0.6F);

                        d0 = this.b;
                        d1 = this.c;
                        d2 = this.d;

                        for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
                            int l = OMathHelper.c(d0);
                            int i1 = OMathHelper.c(d1);
                            int j1 = OMathHelper.c(d2);
                            int k1 = this.j.a(l, i1, j1);

                            if (k1 > 0) {
                                f1 -= (OBlock.m[k1].a(this.e) + 0.3F) * f2;
                            }

                            if (f1 > 0.0F) {
                                hashset.add(new OChunkPosition(l, i1, j1));
                            }

                            d0 += d3 * (double) f2;
                            d1 += d4 * (double) f2;
                            d2 += d5 * (double) f2;
                        }
                    }
                }
            }
        }
        
        // CanaryMod start
        boolean cancel = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLODE, block, this.e, hashset); // Call deprecated hook first; it may remove blocks from hashset.
        
        // Add affected blocks into a List of Blocks.
        List<Block> blocksAffected = new ArrayList<Block>(hashset.size());
        for (OChunkPosition ocp : (HashSet<OChunkPosition>) hashset) {
            blocksAffected.add(new Block(this.j.world, this.j.world.getBlockIdAt(ocp.a, ocp.b, ocp.c), ocp.a, ocp.b, ocp.c));
        }

        cancel = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLOSION, block, (this.e != null ? this.e.entity : null), blocksAffected) || cancel;        
        
        // Repopulate hashset according to blocksAffected.
        hashset.clear();
        for (Block affected : blocksAffected) {
            hashset.add(new OChunkPosition(affected.getX(), affected.getY(), affected.getZ()));
        }
        // CanaryMod end
        
        // CanaryMod: if cancelled, don't populate this.g at all.
        if (!cancel) {
            this.g.addAll(hashset);
        }
        this.f *= 2.0F;
        i = OMathHelper.c(this.b - (double) this.f - 1.0D);
        j = OMathHelper.c(this.b + (double) this.f + 1.0D);
        k = OMathHelper.c(this.c - (double) this.f - 1.0D);
        int l1 = OMathHelper.c(this.c + (double) this.f + 1.0D);
        int i2 = OMathHelper.c(this.d - (double) this.f - 1.0D);
        int j2 = OMathHelper.c(this.d + (double) this.f + 1.0D);
        List list = this.j.b(this.e, OAxisAlignedBB.a().a((double) i, (double) k, (double) i2, (double) j, (double) l1, (double) j2));
        OVec3 ovec3 = OVec3.a().a(this.b, this.c, this.d);

        for (int k2 = 0; k2 < list.size(); ++k2) {
            OEntity oentity = (OEntity) list.get(k2);
            double d7 = oentity.f(this.b, this.c, this.d) / (double) this.f;

            if (d7 <= 1.0D) {
                d0 = oentity.t - this.b;
                d1 = oentity.u + (double) oentity.e() - this.c;
                d2 = oentity.v - this.d;
                double d8 = (double) OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

                if (d8 != 0.0D) {
                    d0 /= d8;
                    d1 /= d8;
                    d2 /= d8;
                    double d9 = (double) this.j.a(ovec3, oentity.D);
                    double d10 = (1.0D - d7) * d9;

                    // CanaryMod Damage hook: Explosions
                    int damage = (int) ((d10 * d10 + d10) / 2.0D * 8.0D * (double) this.f + 1.0D);
                    PluginLoader.DamageType dmgType = (e instanceof OEntityCreeper) ? PluginLoader.DamageType.CREEPER_EXPLOSION : PluginLoader.DamageType.EXPLOSION;

                    if (!cancel && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, dmgType, (this.e != null ? this.e.entity : null), oentity.entity, damage)) {
                        oentity.a(ODamageSource.k, (int) ((d10 * d10 + d10) / 2.0D * 8.0D * (double) this.f + 1.0D));
                    }
                    oentity.w += d0 * d10;
                    oentity.x += d1 * d10;
                    oentity.y += d2 * d10;
                    if (oentity instanceof OEntityPlayer) {
                        this.k.put((OEntityPlayer) oentity, OVec3.a().a(d0 * d10, d1 * d10, d2 * d10));
                    }
                }
            }
        }

        this.f = f;
    }

    public void a(boolean flag) {
        this.j.a(this.b, this.c, this.d, "random.explode", 4.0F, (1.0F + (this.j.v.nextFloat() - this.j.v.nextFloat()) * 0.2F) * 0.7F);
        this.j.a("hugeexplosion", this.b, this.c, this.d, 0.0D, 0.0D, 0.0D);
        Iterator iterator = this.g.iterator();

        OChunkPosition ochunkposition;
        int i;
        int j;
        int k;
        int l;

        while (iterator.hasNext()) {
            ochunkposition = (OChunkPosition) iterator.next();
            i = ochunkposition.a;
            j = ochunkposition.b;
            k = ochunkposition.c;
            l = this.j.a(i, j, k);
            if (flag) {
                double d0 = (double) ((float) i + this.j.v.nextFloat());
                double d1 = (double) ((float) j + this.j.v.nextFloat());
                double d2 = (double) ((float) k + this.j.v.nextFloat());
                double d3 = d0 - this.b;
                double d4 = d1 - this.c;
                double d5 = d2 - this.d;
                double d6 = (double) OMathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);

                d3 /= d6;
                d4 /= d6;
                d5 /= d6;
                double d7 = 0.5D / (d6 / (double) this.f + 0.1D);

                d7 *= (double) (this.j.v.nextFloat() * this.j.v.nextFloat() + 0.3F);
                d3 *= d7;
                d4 *= d7;
                d5 *= d7;
                this.j.a("explode", (d0 + this.b * 1.0D) / 2.0D, (d1 + this.c * 1.0D) / 2.0D, (d2 + this.d * 1.0D) / 2.0D, d3, d4, d5);
                this.j.a("smoke", d0, d1, d2, d3, d4, d5);
            }

            if (l > 0) {
                OBlock.m[l].a(this.j, i, j, k, this.j.g(i, j, k), 0.3F, 0);
                if (this.j.a(i, j, k, 0, 0, this.j.K)) {
                    this.j.h(i, j, k, 0);
                }

                OBlock.m[l].k(this.j, i, j, k);
            }
        }

        if (this.a) {
            iterator = this.g.iterator();

            while (iterator.hasNext()) {
                ochunkposition = (OChunkPosition) iterator.next();
                i = ochunkposition.a;
                j = ochunkposition.b;
                k = ochunkposition.c;
                l = this.j.a(i, j, k);
                int i1 = this.j.a(i, j - 1, k);

                if (l == 0 && OBlock.n[i1] && this.i.nextInt(3) == 0) {
                    this.j.e(i, j, k, OBlock.ar.ca);
                }
            }
        }

    }

    public Map b() {
        return this.k;
    }
}
