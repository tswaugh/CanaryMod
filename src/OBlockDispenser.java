import java.util.Random;

public class OBlockDispenser extends OBlockContainer {

    private Random a = new Random();

    protected OBlockDispenser(int i) {
        super(i, OMaterial.e);
        this.bZ = 45;
        this.a(OCreativeTabs.d);
    }

    public int p_() {
        return 4;
    }

    public int a(int i, Random random, int j) {
        return OBlock.P.ca;
    }

    public void g(OWorld oworld, int i, int j, int k) {
        super.g(oworld, i, j, k);
        this.l(oworld, i, j, k);
    }

    private void l(OWorld oworld, int i, int j, int k) {
        if (!oworld.K) {
            int l = oworld.a(i, j, k - 1);
            int i1 = oworld.a(i, j, k + 1);
            int j1 = oworld.a(i - 1, j, k);
            int k1 = oworld.a(i + 1, j, k);
            byte b0 = 3;

            if (OBlock.n[l] && !OBlock.n[i1]) {
                b0 = 3;
            }

            if (OBlock.n[i1] && !OBlock.n[l]) {
                b0 = 2;
            }

            if (OBlock.n[j1] && !OBlock.n[k1]) {
                b0 = 5;
            }

            if (OBlock.n[k1] && !OBlock.n[j1]) {
                b0 = 4;
            }

            oworld.c(i, j, k, b0);
        }
    }

    public int a(int i) {
        return i == 1 ? this.bZ + 17 : (i == 0 ? this.bZ + 17 : (i == 3 ? this.bZ + 1 : this.bZ));
    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer, int l, float f, float f1, float f2) {
        if (oworld.K) {
            return true;
        } else {
            OTileEntityDispenser otileentitydispenser = (OTileEntityDispenser) oworld.p(i, j, k);

            if (otileentitydispenser != null) {
                oentityplayer.a(otileentitydispenser);
            }

            return true;
        }
    }

    protected void c(OWorld oworld, int i, int j, int k, Random random) { // CanaryMod: private -> protected
        int l = oworld.g(i, j, k);
        byte b0 = 0;
        byte b1 = 0;

        if (l == 3) {
            b1 = 1;
        } else if (l == 2) {
            b1 = -1;
        } else if (l == 5) {
            b0 = 1;
        } else {
            b0 = -1;
        }

        OTileEntityDispenser otileentitydispenser = (OTileEntityDispenser) oworld.p(i, j, k);

        if (otileentitydispenser != null) {
            int i1 = otileentitydispenser.i();

            if (i1 < 0) {
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), null)) {
                    oworld.e(1001, i, j, k, 0);
                }
            } else {
                double d0 = (double) i + (double) b0 * 0.6D + 0.5D;
                double d1 = (double) j + 0.5D;
                double d2 = (double) k + (double) b1 * 0.6D + 0.5D;
                OItemStack oitemstack = otileentitydispenser.a(i1);
                int j1 = a(otileentitydispenser, oworld, oitemstack, random, i, j, k, b0, b1, d0, d1, d2);

                if (j1 == 1) {
                    otileentitydispenser.a(i1, 1);
                } else if (j1 == 0) {
                    oitemstack = otileentitydispenser.a(i1, 1);
                    OEntityItem oentityitem = new OEntityItem(oworld, d0, d1 - 0.3D, d2, oitemstack);
                    
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new ItemEntity(oentityitem))) {
                        a(oworld, oitemstack, random, 6, b0, b1, d0, d1, d2, oentityitem);
                        oworld.e(1000, i, j, k, 0);
                    } else {
                        oentityitem.y();
                        OItemStack oitemstack1 = otileentitydispenser.a(i1); // Get remaining stack
                        if (oitemstack1 != null) {
                            oitemstack.a += oitemstack1.a; // Add size to removed item, creating original stack
                        }//
                        otileentitydispenser.a(i1, oitemstack); // put original stack back
                    }//
                }

                oworld.e(2000, i, j, k, b0 + 1 + (b1 + 1) * 3);
            }
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (l > 0 && OBlock.m[l].i()) {
            boolean flag = oworld.z(i, j, k) || oworld.z(i, j + 1, k);

            if (flag) {
                oworld.a(i, j, k, this.ca, this.p_());
            }
        }
    }

    public void b(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.K && (oworld.z(i, j, k) || oworld.z(i, j + 1, k))) {
            this.c(oworld, i, j, k, random);
        }
    }

    public OTileEntity a(OWorld oworld) {
        return new OTileEntityDispenser();
    }

    public void a(OWorld oworld, int i, int j, int k, OEntityLiving oentityliving) {
        int l = OMathHelper.c((double) (oentityliving.z * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            oworld.c(i, j, k, 2);
        }

        if (l == 1) {
            oworld.c(i, j, k, 5);
        }

        if (l == 2) {
            oworld.c(i, j, k, 3);
        }

        if (l == 3) {
            oworld.c(i, j, k, 4);
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        OTileEntityDispenser otileentitydispenser = (OTileEntityDispenser) oworld.p(i, j, k);

        if (otileentitydispenser != null) {
            for (int j1 = 0; j1 < otileentitydispenser.i_(); ++j1) {
                OItemStack oitemstack = otileentitydispenser.a(j1);

                if (oitemstack != null) {
                    float f = this.a.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.a.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.a.nextFloat() * 0.8F + 0.1F;

                    while (oitemstack.a > 0) {
                        int k1 = this.a.nextInt(21) + 10;

                        if (k1 > oitemstack.a) {
                            k1 = oitemstack.a;
                        }

                        oitemstack.a -= k1;
                        OEntityItem oentityitem = new OEntityItem(oworld, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), new OItemStack(oitemstack.c, k1, oitemstack.j()));

                        if (oitemstack.o()) {
                            oentityitem.a.d((ONBTTagCompound) oitemstack.p().b());
                        }

                        float f3 = 0.05F;

                        oentityitem.w = (double) ((float) this.a.nextGaussian() * f3);
                        oentityitem.x = (double) ((float) this.a.nextGaussian() * f3 + 0.2F);
                        oentityitem.y = (double) ((float) this.a.nextGaussian() * f3);
                        oworld.d((OEntity) oentityitem);
                    }
                }
            }
        }

        super.a(oworld, i, j, k, l, i1);
    }
    
    private static void a(OWorld oworld, OItemStack oitemstack, Random random, int i, int j, int k, double d0, double d1, double d2) {
        a(oworld, oitemstack, random, i, j, k, d0, d1, d2, null);
    }

    // CanaryMod: OItemStack -> OEntityItem
    private static void a(OWorld oworld, OItemStack oitemstack, Random random, int i, int j, int k, double d0, double d1, double d2, OEntityItem oentityitem) {
        if (oentityitem == null) {
            oentityitem = new OEntityItem(oworld, d0, d1 - 0.3D, d2, oitemstack);
        }
        double d3 = random.nextDouble() * 0.1D + 0.2D;

        oentityitem.w = (double) j * d3;
        oentityitem.x = 0.20000000298023224D;
        oentityitem.y = (double) k * d3;
        oentityitem.w += random.nextGaussian() * 0.007499999832361937D * (double) i;
        oentityitem.x += random.nextGaussian() * 0.007499999832361937D * (double) i;
        oentityitem.y += random.nextGaussian() * 0.007499999832361937D * (double) i;
        oworld.d((OEntity) oentityitem);
    }

    private static int a(OTileEntityDispenser otileentitydispenser, OWorld oworld, OItemStack oitemstack, Random random, int i, int j, int k, int l, int i1, double d0, double d1, double d2) {
        float f = 1.1F;
        byte b0 = 6;

        if (oitemstack.c == OItem.l.bT) {
            OEntityArrow oentityarrow = new OEntityArrow(oworld, d0, d1, d2);

            oentityarrow.c((double) l, 0.10000000149011612D, (double) i1, f, (float) b0);
            oentityarrow.a = 1;
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new BaseEntity(oentityarrow))) {
                oworld.d((OEntity) oentityarrow);
                oworld.e(1002, i, j, k, 0);
            } else {
                oentityarrow.y();
            }
            return 1;
        } else if (oitemstack.c == OItem.aP.bT) {
            OEntityEgg oentityegg = new OEntityEgg(oworld, d0, d1, d2);

            oentityegg.c((double) l, 0.10000000149011612D, (double) i1, f, (float) b0);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new BaseEntity(oentityegg))) {
                oworld.d((OEntity) oentityegg);
                oworld.e(1002, i, j, k, 0);
            } else {
                oentityegg.y();
            }
            return 1;
        } else if (oitemstack.c == OItem.aD.bT) {
            OEntitySnowball oentitysnowball = new OEntitySnowball(oworld, d0, d1, d2);

            oentitysnowball.c((double) l, 0.10000000149011612D, (double) i1, f, (float) b0);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new BaseEntity(oentitysnowball))) {
                oworld.d((OEntity) oentitysnowball);
                oworld.e(1002, i, j, k, 0);
            } else {
                oentitysnowball.y();
            }
            return 1;
        } else if (oitemstack.c == OItem.bs.bT && OItemPotion.g(oitemstack.j())) {
            OEntityPotion oentitypotion = new OEntityPotion(oworld, d0, d1, d2, oitemstack.j());

            oentitypotion.c((double) l, 0.10000000149011612D, (double) i1, f * 1.25F, (float) b0 * 0.5F);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new BaseEntity(oentitypotion))) {
                oworld.d((OEntity) oentitypotion);
                oworld.e(1002, i, j, k, 0);
            } else {
                oentitypotion.y();
            }
            return 1;
        } else if (oitemstack.c == OItem.bD.bT) {
            OEntityExpBottle oentityexpbottle = new OEntityExpBottle(oworld, d0, d1, d2);

            oentityexpbottle.c((double) l, 0.10000000149011612D, (double) i1, f * 1.25F, (float) b0 * 0.5F);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new BaseEntity(oentityexpbottle))) {
                oworld.d((OEntity) oentityexpbottle);
                oworld.e(1002, i, j, k, 0);
            } else {
                oentityexpbottle.y();
            }
            return 1;
        } else if (oitemstack.c == OItem.bC.bT) {
            OEntityLiving oentityliving = (OEntityLiving) OEntityList.a(oitemstack.j(), oworld); // Get the egg's mob
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new LivingEntity(oentityliving))) {
                OItemMonsterPlacer.a(oworld, oitemstack.j(), d0 + (double) l * 0.3D, d1 - 0.3D, d2 + (double) i1 * 0.3D);
                oworld.e(1002, i, j, k, 0);
            } else {
                oentityliving.y();
            }
            return 1;
        } else if (oitemstack.c == OItem.bE.bT) {
            OEntitySmallFireball oentitysmallfireball = new OEntitySmallFireball(oworld, d0 + (double) l * 0.3D, d1, d2 + (double) i1 * 0.3D, (double) l + random.nextGaussian() * 0.05D, random.nextGaussian() * 0.05D, (double) i1 + random.nextGaussian() * 0.05D);

            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new BaseEntity(oentitysmallfireball))) {
                oworld.d((OEntity) oentitysmallfireball);
                oworld.e(1009, i, j, k, 0);
            } else {
                oentitysmallfireball.y();
            }
            return 1;
        } else if (oitemstack.c != OItem.ay.bT && oitemstack.c != OItem.ax.bT) {
            if (oitemstack.c == OItem.aw.bT) {
                int j1 = i + l;
                int k1 = k + i1;
                OMaterial omaterial = oworld.f(j1, j, k1);
                int l1 = oworld.g(j1, j, k1);

                if (omaterial == OMaterial.g && l1 == 0) {
                    oworld.e(j1, j, k1, 0);
                    if (--oitemstack.a == 0) {
                        oitemstack.c = OItem.ax.bT;
                        oitemstack.a = 1;
                    } else if (otileentitydispenser.a(new OItemStack(OItem.ax)) < 0) {
                        a(oworld, new OItemStack(OItem.ax), random, 6, l, i1, d0, d1, d2);
                    }

                    return 2;
                } else if (omaterial == OMaterial.h && l1 == 0) {
                    oworld.e(j1, j, k1, 0);
                    if (--oitemstack.a == 0) {
                        oitemstack.c = OItem.ay.bT;
                        oitemstack.a = 1;
                    } else if (otileentitydispenser.a(new OItemStack(OItem.ay)) < 0) {
                        a(oworld, new OItemStack(OItem.ay), random, 6, l, i1, d0, d1, d2);
                    }

                    return 2;
                } else {
                    return 0;
                }
            } else if (oitemstack.b() instanceof OItemMinecart) {
                d0 = (double) i + (l < 0 ? (double) l * 0.8D : (double) ((float) l * 1.8F)) + (double) ((float) Math.abs(i1) * 0.5F);
                d2 = (double) k + (i1 < 0 ? (double) i1 * 0.8D : (double) ((float) i1 * 1.8F)) + (double) ((float) Math.abs(l) * 0.5F);
                if (OBlockRail.d_(oworld, i + l, j, k + i1)) {
                    d1 = (double) ((float) j + 0.5F);
                } else {
                    if (!oworld.c(i + l, j, k + i1) || !OBlockRail.d_(oworld, i + l, j - 1, k + i1)) {
                        return 0;
                    }

                    d1 = (double) ((float) j - 0.5F);
                }

                OEntityMinecart oentityminecart = new OEntityMinecart(oworld, d0, d1, d2, ((OItemMinecart) oitemstack.b()).a);

                oworld.d((OEntity) oentityminecart);
                oworld.e(1000, i, j, k, 0);
                return 1;
            } else if (oitemstack.c == OItem.aE.bT) {
                d0 = (double) i + (l < 0 ? (double) l * 0.8D : (double) ((float) l * 1.8F)) + (double) ((float) Math.abs(i1) * 0.5F);
                d2 = (double) k + (i1 < 0 ? (double) i1 * 0.8D : (double) ((float) i1 * 1.8F)) + (double) ((float) Math.abs(l) * 0.5F);
                if (oworld.f(i + l, j, k + i1) == OMaterial.g) {
                    d1 = (double) ((float) j + 1.0F);
                } else {
                    if (!oworld.c(i + l, j, k + i1) || oworld.f(i + l, j - 1, k + i1) != OMaterial.g) {
                        return 0;
                    }

                    d1 = (double) j;
                }

                OEntityBoat oentityboat = new OEntityBoat(oworld, d0, d1, d2);

                oworld.d((OEntity) oentityboat);
                oworld.e(1000, i, j, k, 0);
                return 1;
            } else {
                return 0;
            }
        } else {
            OItemBucket oitembucket = (OItemBucket) oitemstack.b();

            if (oitembucket.a(oworld, (double) i, (double) j, (double) k, i + l, j, k + i1)) {
                oitemstack.c = OItem.aw.bT;
                oitemstack.a = 1;
                return 2;
            } else {
                return 0;
            }
        }
    }
}
