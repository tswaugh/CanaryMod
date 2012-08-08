
public class OItemEnderEye extends OItem {

    public OItemEnderEye(int i) {
        super(i);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        int i1 = oworld.a(i, j, k);
        int j1 = oworld.c(i, j, k);
        
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), this.getBlockInfo(oworld, i, j, k, l), null, new Item(oitemstack))) {
            return true;
        }

        if (oentityplayer.d(i, j, k) && i1 == OBlock.bI.bO && !OBlockEndPortalFrame.d(j1)) {
            if (oworld.F) {
                return true;
            } else {
                oworld.c(i, j, k, j1 + 4);
                --oitemstack.a;

                int k1;

                for (k1 = 0; k1 < 16; ++k1) {
                    double d0 = (double) ((float) i + (5.0F + c.nextFloat() * 6.0F) / 16.0F);
                    double d1 = (double) ((float) j + 0.8125F);
                    double d2 = (double) ((float) k + (5.0F + c.nextFloat() * 6.0F) / 16.0F);
                    double d3 = 0.0D;
                    double d4 = 0.0D;
                    double d5 = 0.0D;

                    oworld.a("smoke", d0, d1, d2, d3, d4, d5);
                }

                k1 = j1 & 3;
                int l1 = 0;
                int i2 = 0;
                boolean flag = false;
                boolean flag1 = true;
                int j2 = ODirection.f[k1];

                int k2;
                int l2;
                int i3;
                int j3;
                int k3;

                for (l2 = -2; l2 <= 2; ++l2) {
                    k2 = i + ODirection.a[j2] * l2;
                    j3 = k + ODirection.b[j2] * l2;
                    i3 = oworld.a(k2, j, j3);
                    if (i3 == OBlock.bI.bO) {
                        k3 = oworld.c(k2, j, j3);
                        if (!OBlockEndPortalFrame.d(k3)) {
                            flag1 = false;
                            break;
                        }

                        if (!flag) {
                            l1 = l2;
                            i2 = l2;
                            flag = true;
                        } else {
                            i2 = l2;
                        }
                    }
                }

                if (flag1 && i2 == l1 + 2) {
                    for (l2 = l1; l2 <= i2; ++l2) {
                        k2 = i + ODirection.a[j2] * l2;
                        j3 = k + ODirection.b[j2] * l2;
                        k2 += ODirection.a[k1] * 4;
                        j3 += ODirection.b[k1] * 4;
                        i3 = oworld.a(k2, j, j3);
                        k3 = oworld.c(k2, j, j3);
                        if (i3 != OBlock.bI.bO || !OBlockEndPortalFrame.d(k3)) {
                            flag1 = false;
                            break;
                        }
                    }

                    for (l2 = l1 - 1; l2 <= i2 + 1; l2 += 4) {
                        for (k2 = 1; k2 <= 3; ++k2) {
                            j3 = i + ODirection.a[j2] * l2;
                            i3 = k + ODirection.b[j2] * l2;
                            j3 += ODirection.a[k1] * k2;
                            i3 += ODirection.b[k1] * k2;
                            k3 = oworld.a(j3, j, i3);
                            int l3 = oworld.c(j3, j, i3);

                            if (k3 != OBlock.bI.bO || !OBlockEndPortalFrame.d(l3)) {
                                flag1 = false;
                                break;
                            }
                        }
                    }

                    if (flag1) {
                        for (l2 = l1; l2 <= i2; ++l2) {
                            for (k2 = 1; k2 <= 3; ++k2) {
                                j3 = i + ODirection.a[j2] * l2;
                                i3 = k + ODirection.b[j2] * l2;
                                j3 += ODirection.a[k1] * k2;
                                i3 += ODirection.b[k1] * k2;
                                oworld.e(j3, j, i3, OBlock.bH.bO);
                            }
                        }
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    public OItemStack a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        OMovingObjectPosition omovingobjectposition = this.a(oworld, oentityplayer, false);

        if (omovingobjectposition != null && omovingobjectposition.a == OEnumMovingObjectType.a) {
            int i = oworld.a(omovingobjectposition.b, omovingobjectposition.c, omovingobjectposition.d);

            if (i == OBlock.bI.bO) {
                return oitemstack;
            }
        }

        if (!oworld.F) {
            OChunkPosition ochunkposition = oworld.b("Stronghold", (int) oentityplayer.bm, (int) oentityplayer.bn, (int) oentityplayer.bo);

            if (ochunkposition != null) {
                OEntityEnderEye oentityendereye = new OEntityEnderEye(oworld, oentityplayer.bm, oentityplayer.bn + 1.62D - (double) oentityplayer.bF, oentityplayer.bo);

                oentityendereye.a((double) ochunkposition.a, ochunkposition.b, (double) ochunkposition.c);
                oworld.b((OEntity) oentityendereye);
                oworld.a(oentityplayer, "random.bow", 0.5F, 0.4F / (c.nextFloat() * 0.4F + 0.8F));
                oworld.a((OEntityPlayer) null, 1002, (int) oentityplayer.bm, (int) oentityplayer.bn, (int) oentityplayer.bo, 0);
                if (!oentityplayer.L.d) {
                    --oitemstack.a;
                }
            }
        }

        return oitemstack;
    }
}
