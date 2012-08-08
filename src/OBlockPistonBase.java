import java.util.ArrayList;


public class OBlockPistonBase extends OBlock {

    private boolean a;
    private static boolean b;
    // CanaryMod member.
    // Used to signal wether to retract the block attached to the stick piston.
    private boolean attemptRetractBlock;

    public OBlockPistonBase(int i, int j, boolean flag) {
        super(i, j, OMaterial.E);
        this.a = flag;
        this.a(h);
        this.c(0.5F);
    }

    public int a(int i, int j) {
        int k = d(j);

        return k > 5 ? this.bN : (i == k ? (!e(j) && this.bV <= 0.0D && this.bW <= 0.0D && this.bX <= 0.0D && this.bY >= 1.0D && this.bZ >= 1.0D && this.ca >= 1.0D ? this.bN : 110) : (i == OFacing.a[k] ? 109 : 108));
    }

    public int c() {
        return 16;
    }

    public boolean a() {
        return false;
    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {
        return false;
    }

    public void a(OWorld oworld, int i, int j, int k, OEntityLiving oentityliving) {
        int l = c(oworld, i, j, k, (OEntityPlayer) oentityliving);

        oworld.c(i, j, k, l);
        if (!oworld.F && !b) {
            this.g(oworld, i, j, k);
        }

    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (!oworld.F && !b) {
            this.g(oworld, i, j, k);
        }

    }

    public void a(OWorld oworld, int i, int j, int k) {
        if (!oworld.F && oworld.b(i, j, k) == null && !b) {
            this.g(oworld, i, j, k);
        }

    }

    private void g(OWorld oworld, int i, int j, int k) {
        int l = oworld.c(i, j, k);
        int i1 = d(l);
        boolean flag = this.f(oworld, i, j, k, i1);

        if (l != 7) {
            if (flag && !e(l)) {
                if (g(oworld, i, j, k, i1)) {
                    // CanaryMod hook onPistonExtend
                    boolean allowExtension = !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PISTON_EXTEND, new Block(oworld.world, (this.a) ? Block.Type.StickyPiston.getType() : Block.Type.Piston.getType(), i, j, k, l));

                    if (allowExtension) {
                        oworld.d(i, j, k, i1 | 8);
                        oworld.e(i, j, k, 0, i1);
                    }
                }
            } else if (!flag && e(l)) {
                // CanaryMod hook onPistonRetract
                // hook result is saved in attemptRetractBlock because later in the code the block is actually moved, and only there we should deny retraction.
                this.attemptRetractBlock = !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PISTON_RETRACT, new Block(oworld.world, (this.a) ? Block.Type.StickyPiston.getType() : Block.Type.Piston.getType(), i, j, k, l));

                oworld.d(i, j, k, i1);
                oworld.e(i, j, k, 1, i1);
            }

        }
    }

    private boolean f(OWorld oworld, int i, int j, int k, int l) {
        return l != 0 && oworld.j(i, j - 1, k, 0) ? true : (l != 1 && oworld.j(i, j + 1, k, 1) ? true : (l != 2 && oworld.j(i, j, k - 1, 2) ? true : (l != 3 && oworld.j(i, j, k + 1, 3) ? true : (l != 5 && oworld.j(i + 1, j, k, 5) ? true : (l != 4 && oworld.j(i - 1, j, k, 4) ? true : (oworld.j(i, j, k, 0) ? true : (oworld.j(i, j + 2, k, 1) ? true : (oworld.j(i, j + 1, k - 1, 2) ? true : (oworld.j(i, j + 1, k + 1, 3) ? true : (oworld.j(i - 1, j + 1, k, 4) ? true : oworld.j(i + 1, j + 1, k, 5)))))))))));
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        b = true;
        if (l == 0) {
            if (this.h(oworld, i, j, k, i1)) {
                oworld.c(i, j, k, i1 | 8);
                oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "tile.piston.out", 0.5F, oworld.r.nextFloat() * 0.25F + 0.6F);
            } else {
                oworld.d(i, j, k, i1);
            }
        } else if (l == 1) {
            OTileEntity otileentity = oworld.b(i + OFacing.b[i1], j + OFacing.c[i1], k + OFacing.d[i1]);

            if (otileentity != null && otileentity instanceof OTileEntityPiston) {
                ((OTileEntityPiston) otileentity).g();
            }

            oworld.a(i, j, k, OBlock.ac.bO, i1);
            oworld.a(i, j, k, OBlockPistonMoving.a(this.bO, i1, i1, false, true));
            if (this.a) {
                int j1 = i + OFacing.b[i1] * 2;
                int k1 = j + OFacing.c[i1] * 2;
                int l1 = k + OFacing.d[i1] * 2;
                int i2 = oworld.a(j1, k1, l1);
                int j2 = oworld.c(j1, k1, l1);
                boolean flag = false;

                if (i2 == OBlock.ac.bO) {
                    OTileEntity otileentity1 = oworld.b(j1, k1, l1);

                    if (otileentity1 != null && otileentity1 instanceof OTileEntityPiston) {
                        OTileEntityPiston otileentitypiston = (OTileEntityPiston) otileentity1;

                        if (otileentitypiston.f() == i1 && otileentitypiston.e()) {
                            otileentitypiston.g();
                            i2 = otileentitypiston.c();
                            j2 = otileentitypiston.k();
                            flag = true;
                        }
                    }
                }

                if (this.attemptRetractBlock && !flag && i2 > 0 && a(i2, oworld, j1, k1, l1, false) && (OBlock.m[i2].g() == 0 || i2 == OBlock.Z.bO || i2 == OBlock.V.bO)) {
                    i += OFacing.b[i1];
                    j += OFacing.c[i1];
                    k += OFacing.d[i1];
                    oworld.a(i, j, k, OBlock.ac.bO, j2);
                    oworld.a(i, j, k, OBlockPistonMoving.a(i2, j2, i1, false, false));
                    b = false;
                    oworld.e(j1, k1, l1, 0);
                    b = true;
                } // if retraction fails normally (i2 == 0) OR the onPistonRetract returned false earlier.
                else if (!flag || !this.attemptRetractBlock) {
                    b = false;
                    oworld.e(i + OFacing.b[i1], j + OFacing.c[i1], k + OFacing.d[i1], 0);
                    b = true;
                }
            } else {
                b = false;
                oworld.e(i + OFacing.b[i1], j + OFacing.c[i1], k + OFacing.d[i1], 0);
                b = true;
            }

            oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "tile.piston.in", 0.5F, oworld.r.nextFloat() * 0.15F + 0.6F);
        }

        b = false;
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        int l = oiblockaccess.c(i, j, k);

        if (e(l)) {
            switch (d(l)) {
                case 0:
                    this.a(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
                    break;

                case 1:
                    this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
                    break;

                case 2:
                    this.a(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
                    break;

                case 3:
                    this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
                    break;

                case 4:
                    this.a(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                    break;

                case 5:
                    this.a(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
            }
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

    }

    public void f() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void a(OWorld oworld, int i, int j, int k, OAxisAlignedBB oaxisalignedbb, ArrayList arraylist) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.a(oworld, i, j, k, oaxisalignedbb, arraylist);
    }

    public OAxisAlignedBB e(OWorld oworld, int i, int j, int k) {
        this.a((OIBlockAccess) oworld, i, j, k);
        return super.e(oworld, i, j, k);
    }

    public boolean b() {
        return false;
    }

    public static int d(int i) {
        return i & 7;
    }

    public static boolean e(int i) {
        return (i & 8) != 0;
    }

    private static int c(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {
        if (OMathHelper.e((float) oentityplayer.bm - (float) i) < 2.0F && OMathHelper.e((float) oentityplayer.bo - (float) k) < 2.0F) {
            double d0 = oentityplayer.bn + 1.82D - (double) oentityplayer.bF;

            if (d0 - (double) j > 2.0D) {
                return 1;
            }

            if ((double) j - d0 > 0.0D) {
                return 0;
            }
        }

        int l = OMathHelper.b((double) (oentityplayer.bs * 4.0F / 360.0F) + 0.5D) & 3;

        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
    }

    private static boolean a(int i, OWorld oworld, int j, int k, int l, boolean flag) {
        if (i == OBlock.ap.bO) {
            return false;
        } else {
            if (i != OBlock.Z.bO && i != OBlock.V.bO) {
                if (OBlock.m[i].m() == -1.0F) {
                    return false;
                }

                if (OBlock.m[i].g() == 2) {
                    return false;
                }

                if (!flag && OBlock.m[i].g() == 1) {
                    return false;
                }
            } else if (e(oworld.c(j, k, l))) {
                return false;
            }

            return !(OBlock.m[i] instanceof OBlockContainer);
        }
    }

    private static boolean g(OWorld oworld, int i, int j, int k, int l) {
        int i1 = i + OFacing.b[l];
        int j1 = j + OFacing.c[l];
        int k1 = k + OFacing.d[l];
        int l1 = 0;

        while (true) {
            if (l1 < 13) {
                if (j1 <= 0 || j1 >= 255) {
                    return false;
                }

                int i2 = oworld.a(i1, j1, k1);

                if (i2 != 0) {
                    if (!a(i2, oworld, i1, j1, k1, true)) {
                        return false;
                    }

                    if (OBlock.m[i2].g() != 1) {
                        if (l1 == 12) {
                            return false;
                        }

                        i1 += OFacing.b[l];
                        j1 += OFacing.c[l];
                        k1 += OFacing.d[l];
                        ++l1;
                        continue;
                    }
                }
            }

            return true;
        }
    }

    private boolean h(OWorld oworld, int i, int j, int k, int l) {
        int i1 = i + OFacing.b[l];
        int j1 = j + OFacing.c[l];
        int k1 = k + OFacing.d[l];
        int l1 = 0;

        while (true) {
            int i2;

            if (l1 < 13) {
                if (j1 <= 0 || j1 >= 255) {
                    return false;
                }

                i2 = oworld.a(i1, j1, k1);
                if (i2 != 0) {
                    if (!a(i2, oworld, i1, j1, k1, true)) {
                        return false;
                    }

                    if (OBlock.m[i2].g() != 1) {
                        if (l1 == 12) {
                            return false;
                        }

                        i1 += OFacing.b[l];
                        j1 += OFacing.c[l];
                        k1 += OFacing.d[l];
                        ++l1;
                        continue;
                    }

                    OBlock.m[i2].b(oworld, i1, j1, k1, oworld.c(i1, j1, k1), 0);
                    oworld.e(i1, j1, k1, 0);
                }
            }

            while (i1 != i || j1 != j || k1 != k) {
                l1 = i1 - OFacing.b[l];
                i2 = j1 - OFacing.c[l];
                int j2 = k1 - OFacing.d[l];
                int k2 = oworld.a(l1, i2, j2);
                int l2 = oworld.c(l1, i2, j2);

                if (k2 == this.bO && l1 == i && i2 == j && j2 == k) {
                    oworld.a(i1, j1, k1, OBlock.ac.bO, l | (this.a ? 8 : 0));
                    oworld.a(i1, j1, k1, OBlockPistonMoving.a(OBlock.aa.bO, l | (this.a ? 8 : 0), l, true, false));
                } else {
                    oworld.a(i1, j1, k1, OBlock.ac.bO, l2);
                    oworld.a(i1, j1, k1, OBlockPistonMoving.a(k2, l2, l, true, false));
                }

                i1 = l1;
                j1 = i2;
                k1 = j2;
            }

            return true;
        }
    }
}
