import java.util.Random;


public class OBlockPortal extends OBlockBreakable {

    public OBlockPortal(int i, int j) {
        super(i, j, OMaterial.B, false);
    }

    public OAxisAlignedBB e(OWorld oworld, int i, int j, int k) {
        return null;
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        float f;
        float f1;

        if (oiblockaccess.a(i - 1, j, k) != this.bO && oiblockaccess.a(i + 1, j, k) != this.bO) {
            f = 0.125F;
            f1 = 0.5F;
            this.a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
        } else {
            f = 0.5F;
            f1 = 0.125F;
            this.a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
        }

    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean b_(OWorld oworld, int i, int j, int k) {
        byte b0 = 0;
        byte b1 = 0;

        if (oworld.a(i - 1, j, k) == OBlock.ap.bO || oworld.a(i + 1, j, k) == OBlock.ap.bO) {
            b0 = 1;
        }

        if (oworld.a(i, j, k - 1) == OBlock.ap.bO || oworld.a(i, j, k + 1) == OBlock.ap.bO) {
            b1 = 1;
        }

        if (b0 == b1) {
            return false;
        } else {
            if (oworld.a(i - b0, j, k - b1) == 0) {
                i -= b0;
                k -= b1;
            }

            int l;
            int i1;

            for (l = -1; l <= 2; ++l) {
                for (i1 = -1; i1 <= 3; ++i1) {
                    boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

                    if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
                        int j1 = oworld.a(i + b0 * l, j + i1, k + b1 * l);

                        if (flag) {
                            if (j1 != OBlock.ap.bO) {
                                return false;
                            }
                        } else if (j1 != 0 && j1 != OBlock.ar.bO) {
                            return false;
                        }
                    }
                }
            }

            // CanaryMod hook onPortalCreate
            Block[][] portalBlocks = new Block[3][2];

            for (i1 = 0; i1 < 3; ++i1) {
                for (l = 0; l < 2; ++l) {
                    portalBlocks[i1][l] = new Block(oworld.world, Block.Type.Portal.getType(), i + b0 * l, j + 2 - i1, k + b1 * l);
                }
            }
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PORTAL_CREATE, (Object) portalBlocks)) {
                oworld.o = true;

                for (l = 0; l < 2; ++l) {
                    for (i1 = 0; i1 < 3; ++i1) {
                        oworld.e(i + b0 * l, j + i1, k + b1 * l, OBlock.be.bO);
                    }
                }

                oworld.o = false;
                return true;
            }
            return false;
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        byte b0 = 0;
        byte b1 = 1;

        if (oworld.a(i - 1, j, k) == this.bO || oworld.a(i + 1, j, k) == this.bO) {
            b0 = 1;
            b1 = 0;
        }

        int i1;

        for (i1 = j; oworld.a(i, i1 - 1, k) == this.bO; --i1) {
            ;
        }

        if (oworld.a(i, i1 - 1, k) != OBlock.ap.bO) {
            oworld.e(i, j, k, 0);
        } else {
            int j1;

            for (j1 = 1; j1 < 4 && oworld.a(i, i1 + j1, k) == this.bO; ++j1) {
                ;
            }

            if (j1 == 3 && oworld.a(i, i1 + j1, k) == OBlock.ap.bO) {
                boolean flag = oworld.a(i - 1, j, k) == this.bO || oworld.a(i + 1, j, k) == this.bO;
                boolean flag1 = oworld.a(i, j, k - 1) == this.bO || oworld.a(i, j, k + 1) == this.bO;

                if (flag && flag1) {
                    oworld.e(i, j, k, 0);
                } else if ((oworld.a(i + b0, j, k + b1) != OBlock.ap.bO || oworld.a(i - b0, j, k - b1) != this.bO) && (oworld.a(i - b0, j, k - b1) != OBlock.ap.bO || oworld.a(i + b0, j, k + b1) != this.bO)) {
                    oworld.e(i, j, k, 0);
                }
            } else {
                oworld.e(i, j, k, 0);
            }
        }
    }

    public int a(Random random) {
        return 0;
    }

    public void a(OWorld oworld, int i, int j, int k, OEntity oentity) {
        if (oentity.bh == null && oentity.bg == null) {
            oentity.ad();
        }

    }
}
