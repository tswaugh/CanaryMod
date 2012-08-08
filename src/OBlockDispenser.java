import java.util.Random;


public class OBlockDispenser extends OBlockContainer {

    private Random a = new Random();

    protected OBlockDispenser(int i) {
        super(i, OMaterial.e);
        this.bN = 45;
    }

    public int d() {
        return 4;
    }

    public int a(int i, Random random, int j) {
        return OBlock.P.bO;
    }

    public void a(OWorld oworld, int i, int j, int k) {
        super.a(oworld, i, j, k);
        this.g(oworld, i, j, k);
    }

    private void g(OWorld oworld, int i, int j, int k) {
        if (!oworld.F) {
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
        return i == 1 ? this.bN + 17 : (i == 0 ? this.bN + 17 : (i == 3 ? this.bN + 1 : this.bN));
    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {
        if (oworld.F) {
            return true;
        } else {
            OTileEntityDispenser otileentitydispenser = (OTileEntityDispenser) oworld.b(i, j, k);

            if (otileentitydispenser != null) {
                oentityplayer.a(otileentitydispenser);
            }

            return true;
        }
    }

    private void b(OWorld oworld, int i, int j, int k, Random random) {
        int l = oworld.c(i, j, k);
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

        OTileEntityDispenser otileentitydispenser = (OTileEntityDispenser) oworld.b(i, j, k);

        if (otileentitydispenser != null) {
            OItemStack oitemstack = otileentitydispenser.p_();
            double d0 = (double) i + (double) b0 * 0.6D + 0.5D;
            double d1 = (double) j + 0.5D;
            double d2 = (double) k + (double) b1 * 0.6D + 0.5D;

            if (oitemstack == null) {
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), null)) {
                    oworld.f(1001, i, j, k, 0);
                }
            } else {
                if (oitemstack.c == OItem.k.bP) {
                    OEntityArrow oentityarrow = new OEntityArrow(oworld, d0, d1, d2);

                    oentityarrow.a((double) b0, 0.10000000149011612D, (double) b1, 1.1F, 6.0F);
                    oentityarrow.a = true;
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), null)) {
                        oworld.b((OEntity) oentityarrow);
                        oworld.f(1002, i, j, k, 0);
                    } else {
                        oentityarrow.W();
                    }
                } else if (oitemstack.c == OItem.aO.bP) {
                    OEntityEgg oentityegg = new OEntityEgg(oworld, d0, d1, d2);

                    oentityegg.a((double) b0, 0.10000000149011612D, (double) b1, 1.1F, 6.0F);
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new BaseEntity(oentityegg))) {
                        oworld.b((OEntity) oentityegg);
                        oworld.f(1002, i, j, k, 0);
                    } else {
                        oentityegg.W();
                    }
                } else if (oitemstack.c == OItem.aC.bP) {
                    OEntitySnowball oentitysnowball = new OEntitySnowball(oworld, d0, d1, d2);

                    oentitysnowball.a((double) b0, 0.10000000149011612D, (double) b1, 1.1F, 6.0F);
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new BaseEntity(oentitysnowball))) {
                        oworld.b((OEntity) oentitysnowball);
                        oworld.f(1002, i, j, k, 0);
                    } else {
                        oentitysnowball.W();
                    }
                } else if (oitemstack.c == OItem.br.bP && OItemPotion.c(oitemstack.h())) {
                    OEntityPotion oentitypotion = new OEntityPotion(oworld, d0, d1, d2, oitemstack.h());

                    oentitypotion.a((double) b0, 0.10000000149011612D, (double) b1, 1.375F, 3.0F);
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new BaseEntity(oentitypotion))) {
                        oworld.b((OEntity) oentitypotion);
                        oworld.f(1002, i, j, k, 0);
                    } else {
                        oentitypotion.W();
                    }
                } else if (oitemstack.c == OItem.bC.bP) {
                    OEntityExpBottle oentityexpbottle = new OEntityExpBottle(oworld, d0, d1, d2);

                    oentityexpbottle.a((double) b0, 0.10000000149011612D, (double) b1, 1.375F, 3.0F);
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new BaseEntity(oentityexpbottle))) {
                        oworld.b((OEntity) oentityexpbottle);
                        oworld.f(1002, i, j, k, 0);
                    } else {
                        oentityexpbottle.W();
                    }
                } else if (oitemstack.c == OItem.bB.bP) {
                    OItemMonsterPlacer.a(oworld, oitemstack.h(), d0 + (double) b0 * 0.3D, d1 - 0.3D, d2 + (double) b1 * 0.3D);
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), null)) {
                        oworld.f(1002, i, j, k, 0);
                    }
                } else if (oitemstack.c == OItem.bD.bP) {
                    OEntitySmallFireball oentitysmallfireball = new OEntitySmallFireball(oworld, d0 + (double) b0 * 0.3D, d1, d2 + (double) b1 * 0.3D, (double) b0 + random.nextGaussian() * 0.05D, random.nextGaussian() * 0.05D, (double) b1 + random.nextGaussian() * 0.05D);

                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new BaseEntity(oentitysmallfireball))) {
                        oworld.b((OEntity) oentitysmallfireball);
                        oworld.f(1009, i, j, k, 0);
                    } else {
                        oentitysmallfireball.W();
                    }
                } else {
                    OEntityItem oentityitem = new OEntityItem(oworld, d0, d1 - 0.3D, d2, oitemstack);
                    double d3 = random.nextDouble() * 0.1D + 0.2D;

                    oentityitem.bp = (double) b0 * d3;
                    oentityitem.bq = 0.20000000298023224D;
                    oentityitem.br = (double) b1 * d3;
                    oentityitem.bp += random.nextGaussian() * 0.007499999832361937D * 6.0D;
                    oentityitem.bq += random.nextGaussian() * 0.007499999832361937D * 6.0D;
                    oentityitem.br += random.nextGaussian() * 0.007499999832361937D * 6.0D;
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), new BaseEntity(oentityitem))) {
                        oworld.b((OEntity) oentityitem);
                        oworld.f(1000, i, j, k, 0);
                    } else {
                        oentityitem.W();
                    }
                }

                oworld.f(2000, i, j, k, b0 + 1 + (b1 + 1) * 3);
            }
        }

    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (l > 0 && OBlock.m[l].e()) {
            boolean flag = oworld.x(i, j, k) || oworld.x(i, j + 1, k);

            if (flag) {
                oworld.c(i, j, k, this.bO, this.d());
            }
        }

    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.F && (oworld.x(i, j, k) || oworld.x(i, j + 1, k))) {
            this.b(oworld, i, j, k, random);
        }

    }

    public OTileEntity a_() {
        return new OTileEntityDispenser();
    }

    public void a(OWorld oworld, int i, int j, int k, OEntityLiving oentityliving) {
        int l = OMathHelper.b((double) (oentityliving.bs * 4.0F / 360.0F) + 0.5D) & 3;

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

    public void d(OWorld oworld, int i, int j, int k) {
        OTileEntityDispenser otileentitydispenser = (OTileEntityDispenser) oworld.b(i, j, k);

        if (otileentitydispenser != null) {
            for (int l = 0; l < otileentitydispenser.c(); ++l) {
                OItemStack oitemstack = otileentitydispenser.g_(l);

                if (oitemstack != null) {
                    float f = this.a.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.a.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.a.nextFloat() * 0.8F + 0.1F;

                    while (oitemstack.a > 0) {
                        int i1 = this.a.nextInt(21) + 10;

                        if (i1 > oitemstack.a) {
                            i1 = oitemstack.a;
                        }

                        oitemstack.a -= i1;
                        OEntityItem oentityitem = new OEntityItem(oworld, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), new OItemStack(oitemstack.c, i1, oitemstack.h()));

                        if (oitemstack.n()) {
                            oentityitem.a.d((ONBTTagCompound) oitemstack.o().b());
                        }

                        float f3 = 0.05F;

                        oentityitem.bp = (double) ((float) this.a.nextGaussian() * f3);
                        oentityitem.bq = (double) ((float) this.a.nextGaussian() * f3 + 0.2F);
                        oentityitem.br = (double) ((float) this.a.nextGaussian() * f3);
                        oworld.b((OEntity) oentityitem);
                    }
                }
            }
        }

        super.d(oworld, i, j, k);
    }
}
