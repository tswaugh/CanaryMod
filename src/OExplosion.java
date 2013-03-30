import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OExplosion {

    public boolean a = false;
    public boolean b = true;
    private int i = 16;
    private Random j = new Random();
    private OWorld k;
    public double c;
    public double d;
    public double e;
    public OEntity f;
    public float g;
    public List h = new ArrayList();
    private Map l = new HashMap();

    public OExplosion(OWorld oworld, OEntity oentity, double d0, double d1, double d2, float f) {
        this.k = oworld;
        this.f = oentity;
        this.g = f;
        this.c = d0;
        this.d = d1;
        this.e = d2;
    }

    public void a() {
        float f = this.g;
        HashSet hashset = new HashSet();
        // CanaryMod: allow explosion
        Block block = new Block(this.k.world, this.k.world.getBlockIdAt((int) this.c, (int) this.d, (int) this.e), (int) this.c, (int) this.d, (int) this.e);

        if (this.f == null) {
            block.setStatus(1);
        } else if (this.f instanceof OEntityCreeper) {
            block.setStatus(2);
        } else if (this.f instanceof OEntityFireball) {
            block.setStatus(3);
        } else if (this.f instanceof OEntityWitherSkull) {
            block.setStatus(4);
        }

        int i;
        int j;
        int k;
        double d0;
        double d1;
        double d2;

        for (i = 0; i < this.i; ++i) {
            for (j = 0; j < this.i; ++j) {
                for (k = 0; k < this.i; ++k) {
                    if (i == 0 || i == this.i - 1 || j == 0 || j == this.i - 1 || k == 0 || k == this.i - 1) {
                        double d3 = (double) ((float) i / ((float) this.i - 1.0F) * 2.0F - 1.0F);
                        double d4 = (double) ((float) j / ((float) this.i - 1.0F) * 2.0F - 1.0F);
                        double d5 = (double) ((float) k / ((float) this.i - 1.0F) * 2.0F - 1.0F);
                        double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);

                        d3 /= d6;
                        d4 /= d6;
                        d5 /= d6;
                        float f1 = this.g * (0.7F + this.k.s.nextFloat() * 0.6F);

                        d0 = this.c;
                        d1 = this.d;
                        d2 = this.e;

                        for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
                            int l = OMathHelper.c(d0);
                            int i1 = OMathHelper.c(d1);
                            int j1 = OMathHelper.c(d2);
                            int k1 = this.k.a(l, i1, j1);

                            if (k1 > 0) {
                                OBlock oblock = OBlock.r[k1];
                                float f3 = this.f != null ? this.f.a(this, this.k, l, i1, j1, oblock) : oblock.a(this.f);

                                f1 -= (f3 + 0.3F) * f2;
                            }

                            if (f1 > 0.0F && (this.f == null || this.f.a(this, this.k, l, i1, j1, k1, f1))) {
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
        boolean cancel = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLODE, block, this.f, hashset); // Call deprecated hook first; it may remove blocks from hashset.

        // Add affected blocks into a List of Blocks.
        List<Block> blocksAffected = new ArrayList<Block>(hashset.size());
        for (OChunkPosition ocp : (HashSet<OChunkPosition>) hashset) {
            blocksAffected.add(new Block(this.k.world, this.k.world.getBlockIdAt(ocp.a, ocp.b, ocp.c), ocp.a, ocp.b, ocp.c));
        }

        cancel = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLOSION, block, (this.f != null ? this.f.entity : null), blocksAffected) || cancel;

        // Repopulate hashset according to blocksAffected.
        hashset.clear();
        for (Block affected : blocksAffected) {
            hashset.add(new OChunkPosition(affected.getX(), affected.getY(), affected.getZ()));
        }
        // CanaryMod end

        // CanaryMod: if cancelled, don't populate this.h at all.
        if (!cancel) {
            this.h.addAll(hashset);
        }

        this.g *= 2.0F;
        i = OMathHelper.c(this.c - (double) this.g - 1.0D);
        j = OMathHelper.c(this.c + (double) this.g + 1.0D);
        k = OMathHelper.c(this.d - (double) this.g - 1.0D);
        int l1 = OMathHelper.c(this.d + (double) this.g + 1.0D);
        int i2 = OMathHelper.c(this.e - (double) this.g - 1.0D);
        int j2 = OMathHelper.c(this.e + (double) this.g + 1.0D);
        List list = this.k.b(this.f, OAxisAlignedBB.a().a((double) i, (double) k, (double) i2, (double) j, (double) l1, (double) j2));
        OVec3 ovec3 = this.k.T().a(this.c, this.d, this.e);

        for (int k2 = 0; k2 < list.size(); ++k2) {
            OEntity oentity = (OEntity) list.get(k2);
            double d7 = oentity.f(this.c, this.d, this.e) / (double) this.g;

            if (d7 <= 1.0D) {
                d0 = oentity.u - this.c;
                d1 = oentity.v + (double) oentity.e() - this.d;
                d2 = oentity.w - this.e;
                double d8 = (double) OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

                if (d8 != 0.0D) {
                    d0 /= d8;
                    d1 /= d8;
                    d2 /= d8;
                    double d9 = (double) this.k.a(ovec3, oentity.E);
                    double d10 = (1.0D - d7) * d9;


                    // CanaryMod Damage hook: Explosions
                    int damage = (int) ((d10 * d10 + d10) / 2.0D * 8.0D * (double) this.g + 1.0D);
                    DamageSource damageSource = ODamageSource.a(this).damageSource;

                    HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(damageSource.getDamageSourceEntity(), oentity.getEntity(), damageSource, damage));
                    if (!cancel && !ev.isCanceled()) {
                        //Cannot add a random damage source here, only damage applies
                        oentity.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmount());
                    }
                    double d11 = OEnchantmentProtection.a(oentity, d10);

                    oentity.x += d0 * d11;
                    oentity.y += d1 * d11;
                    oentity.z += d2 * d11;
                    if (oentity instanceof OEntityPlayer) {
                        this.l.put((OEntityPlayer) oentity, this.k.T().a(d0 * d10, d1 * d10, d2 * d10));
                    }
                }
            }
        }

        this.g = f;
    }

    public void a(boolean flag) {
        this.k.a(this.c, this.d, this.e, "random.explode", 4.0F, (1.0F + (this.k.s.nextFloat() - this.k.s.nextFloat()) * 0.2F) * 0.7F);
        if (this.g >= 2.0F && this.b) {
            this.k.a("hugeexplosion", this.c, this.d, this.e, 1.0D, 0.0D, 0.0D);
        } else {
            this.k.a("largeexplode", this.c, this.d, this.e, 1.0D, 0.0D, 0.0D);
        }

        Iterator iterator;
        OChunkPosition ochunkposition;
        int i;
        int j;
        int k;
        int l;

        if (this.b) {
            iterator = this.h.iterator();

            while (iterator.hasNext()) {
                ochunkposition = (OChunkPosition) iterator.next();
                i = ochunkposition.a;
                j = ochunkposition.b;
                k = ochunkposition.c;
                l = this.k.a(i, j, k);
                if (flag) {
                    double d0 = (double) ((float) i + this.k.s.nextFloat());
                    double d1 = (double) ((float) j + this.k.s.nextFloat());
                    double d2 = (double) ((float) k + this.k.s.nextFloat());
                    double d3 = d0 - this.c;
                    double d4 = d1 - this.d;
                    double d5 = d2 - this.e;
                    double d6 = (double) OMathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);

                    d3 /= d6;
                    d4 /= d6;
                    d5 /= d6;
                    double d7 = 0.5D / (d6 / (double) this.g + 0.1D);

                    d7 *= (double) (this.k.s.nextFloat() * this.k.s.nextFloat() + 0.3F);
                    d3 *= d7;
                    d4 *= d7;
                    d5 *= d7;
                    this.k.a("explode", (d0 + this.c * 1.0D) / 2.0D, (d1 + this.d * 1.0D) / 2.0D, (d2 + this.e * 1.0D) / 2.0D, d3, d4, d5);
                    this.k.a("smoke", d0, d1, d2, d3, d4, d5);
                }

                if (l > 0) {
                    OBlock oblock = OBlock.r[l];

                    if (oblock.a(this)) {
                        oblock.a(this.k, i, j, k, this.k.h(i, j, k), 1.0F / this.g, 0);
                    }

                    this.k.f(i, j, k, 0, 0, 3);
                    oblock.a(this.k, i, j, k, this);
                }
            }
        }

        if (this.a) {
            iterator = this.h.iterator();

            while (iterator.hasNext()) {
                ochunkposition = (OChunkPosition) iterator.next();
                i = ochunkposition.a;
                j = ochunkposition.b;
                k = ochunkposition.c;
                l = this.k.a(i, j, k);
                int i1 = this.k.a(i, j - 1, k);

                if (l == 0 && OBlock.s[i1] && this.j.nextInt(3) == 0) {
                    this.k.c(i, j, k, OBlock.av.cz);
                }
            }
        }
    }

    public Map b() {
        return this.l;
    }

    public OEntityLiving c() {
        return this.f == null ? null : (this.f instanceof OEntityTNTPrimed ? ((OEntityTNTPrimed) this.f).c() : (this.f instanceof OEntityLiving ? (OEntityLiving) this.f : null));
    }
}
