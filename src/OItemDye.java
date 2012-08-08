
public class OItemDye extends OItem {

    public static final String[] a = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white"};
    public static final int[] b = new int[] { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 2651799, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320};

    public OItemDye(int i) {
        super(i);
        this.a(true);
        this.f(0);
    }

    public String a(OItemStack oitemstack) {
        int i = OMathHelper.a(oitemstack.h(), 0, 15);

        return super.b() + "." + a[i];
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        if (!oentityplayer.d(i, j, k)) {
            return false;
        } else {
            if (oitemstack.h() == 15) {
                int i1 = oworld.a(i, j, k);

                if (i1 == OBlock.y.bO) {
                    if (!oworld.F) {
                        ((OBlockSapling) OBlock.y).b(oworld, i, j, k, oworld.r);
                        --oitemstack.a;
                    }

                    return true;
                }

                if (i1 == OBlock.af.bO || i1 == OBlock.ag.bO) {
                    if (!oworld.F && ((OBlockMushroom) OBlock.m[i1]).b(oworld, i, j, k, oworld.r)) {
                        --oitemstack.a;
                    }

                    return true;
                }

                if (i1 == OBlock.bt.bO || i1 == OBlock.bs.bO) {
                    if (!oworld.F) {
                        ((OBlockStem) OBlock.m[i1]).g(oworld, i, j, k);
                        --oitemstack.a;
                    }

                    return true;
                }

                if (i1 == OBlock.az.bO) {
                    if (!oworld.F) {
                        ((OBlockCrops) OBlock.az).g(oworld, i, j, k);
                        --oitemstack.a;
                    }

                    return true;
                }

                if (i1 == OBlock.u.bO) {
                    if (!oworld.F) {
                        --oitemstack.a;

                        label73:
                        for (int j1 = 0; j1 < 128; ++j1) {
                            int k1 = i;
                            int l1 = j + 1;
                            int i2 = k;

                            for (int j2 = 0; j2 < j1 / 16; ++j2) {
                                k1 += c.nextInt(3) - 1;
                                l1 += (c.nextInt(3) - 1) * c.nextInt(3) / 2;
                                i2 += c.nextInt(3) - 1;
                                if (oworld.a(k1, l1 - 1, i2) != OBlock.u.bO || oworld.e(k1, l1, i2)) {
                                    continue label73;
                                }
                            }

                            if (oworld.a(k1, l1, i2) == 0) {
                                if (c.nextInt(10) != 0) {
                                    // CanaryMod disable bonemeal from generating stuff where it shouldnt (notch, y u no check)
                                    if (OBlock.X.f(oworld, k1, l1, i2)) {
                                        oworld.b(k1, l1, i2, OBlock.X.bO, 1);
                                    }
                                } else if (c.nextInt(3) != 0) {
                                    // CanaryMod disable bonemeal from generating stuff where it shouldnt (notch, y u no check)
                                    if (OBlock.ad.f(oworld, k1, l1, i2)) { 
                                        oworld.e(k1, l1, i2, OBlock.ad.bO);
                                    }
                                } else {
                                    // CanaryMod disable bonemeal from generating stuff where it shouldnt (notch, y u no check)
                                    if (OBlock.ae.f(oworld, k1, l1, i2)) {
                                        oworld.e(k1, l1, i2, OBlock.ae.bO);
                                    }
                                }
                            }
                        }
                    }

                    return true;
                }
            }

            return false;
        }
    }

    public void a(OItemStack oitemstack, OEntityLiving oentityliving) {
        if (oentityliving instanceof OEntitySheep) {
            OEntitySheep oentitysheep = (OEntitySheep) oentityliving;
            int i = OBlockCloth.d(oitemstack.h());

            if (!oentitysheep.A_() && oentitysheep.x() != i) {
                oentitysheep.d_(i);
                --oitemstack.a;
            }
        }

    }

}
