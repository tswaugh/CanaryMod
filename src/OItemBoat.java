import java.util.List;


public class OItemBoat extends OItem {

    public OItemBoat(int i) {
        super(i);
        this.bQ = 1;
    }

    public OItemStack a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        float f = 1.0F;
        float f1 = oentityplayer.bv + (oentityplayer.bt - oentityplayer.bv) * f;
        float f2 = oentityplayer.bu + (oentityplayer.bs - oentityplayer.bu) * f;
        double d0 = oentityplayer.bj + (oentityplayer.bm - oentityplayer.bj) * (double) f;
        double d1 = oentityplayer.bk + (oentityplayer.bn - oentityplayer.bk) * (double) f + 1.62D - (double) oentityplayer.bF;
        double d2 = oentityplayer.bl + (oentityplayer.bo - oentityplayer.bl) * (double) f;
        OVec3D ovec3d = OVec3D.b(d0, d1, d2);
        float f3 = OMathHelper.b(-f2 * 0.017453292F - 3.1415927F);
        float f4 = OMathHelper.a(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -OMathHelper.b(-f1 * 0.017453292F);
        float f6 = OMathHelper.a(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        OVec3D ovec3d1 = ovec3d.c((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
        OMovingObjectPosition omovingobjectposition = oworld.a(ovec3d, ovec3d1, true);

        if (omovingobjectposition == null) {
            return oitemstack;
        } else {
            OVec3D ovec3d2 = oentityplayer.f(f);
            boolean flag = false;
            float f9 = 1.0F;
            List list = oworld.b((OEntity) oentityplayer, oentityplayer.bw.a(ovec3d2.a * d3, ovec3d2.b * d3, ovec3d2.c * d3).b((double) f9, (double) f9, (double) f9));

            for (int i = 0; i < list.size(); ++i) {
                OEntity oentity = (OEntity) list.get(i);

                if (oentity.o_()) {
                    float f10 = oentity.j_();
                    OAxisAlignedBB oaxisalignedbb = oentity.bw.b((double) f10, (double) f10, (double) f10);

                    if (oaxisalignedbb.a(ovec3d)) {
                        flag = true;
                    }
                }
            }

            if (flag) {
                return oitemstack;
            } else {
                if (omovingobjectposition.a == OEnumMovingObjectType.a) {
                    int j = omovingobjectposition.b;
                    int k = omovingobjectposition.c;
                    int l = omovingobjectposition.d;

                    if (!oworld.F) {
                        if (oworld.a(j, k, l) == OBlock.aS.bO) {
                            --k;
                        }
                        
                        // CanaryMod: placing of a boat
                        Block blockClicked = new Block(oworld.world, oworld.a(j, k, l), j, k, l);

                        blockClicked.setFaceClicked(Block.Face.fromId(omovingobjectposition.e));
                        Block blockPlaced = new Block(oworld.world, 0, j, k, l);

                        // CanaryMod: Call hook
                        if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack))) {
                            return oitemstack;
                        }

                        oworld.b((OEntity) (new OEntityBoat(oworld, (double) ((float) j + 0.5F), (double) ((float) k + 1.0F), (double) ((float) l + 0.5F))));
                    }

                    if (!oentityplayer.L.d) {
                        --oitemstack.a;
                    }
                }

                return oitemstack;
            }
        }
    }
}
