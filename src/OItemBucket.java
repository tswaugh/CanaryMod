
public class OItemBucket extends OItem {

    private int a;

    public OItemBucket(int i, int j) {
        super(i);
        this.bQ = 1;
        this.a = j;
    }

    public OItemStack a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        float f = 1.0F;
        double d0 = oentityplayer.bj + (oentityplayer.bm - oentityplayer.bj) * (double) f;
        double d1 = oentityplayer.bk + (oentityplayer.bn - oentityplayer.bk) * (double) f + 1.62D - (double) oentityplayer.bF;
        double d2 = oentityplayer.bl + (oentityplayer.bo - oentityplayer.bl) * (double) f;
        boolean flag = this.a == 0;
        OMovingObjectPosition omovingobjectposition = this.a(oworld, oentityplayer, flag);

        if (omovingobjectposition == null) {
            return oitemstack;
        } else {
            if (omovingobjectposition.a == OEnumMovingObjectType.a) {
                int i = omovingobjectposition.b;
                int j = omovingobjectposition.c;
                int k = omovingobjectposition.d;

                if (!oworld.a(oentityplayer, i, j, k)) {
                    return oitemstack;
                }
                
                // CanaryMod: Click == placed when handling an empty bucket!
                Block blockClicked = new Block(oworld.world, oworld.a(i, j, k), i, j, k);

                blockClicked.setFaceClicked(Block.Face.fromId(omovingobjectposition.e));
                Block blockPlaced = new Block(oworld.world, 0, i, j, k);

                if (this.a == 0) {
                    if (!oentityplayer.d(i, j, k)) {
                        return oitemstack;
                    }
                    
                    if (oworld.d(i, j, k) == OMaterial.g && oworld.c(i, j, k) == 0) {
                        // Filling a bucket with water!
                        if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack)) && oentityplayer.L.d) {
                            return oitemstack;
                        }
                        
                        oworld.e(i, j, k, 0);

                        return new OItemStack(OItem.aw);
                    } else if (oworld.d(i, j, k) == OMaterial.h && oworld.c(i, j, k) == 0) {
                        // Filling a bucket with lava!
                        if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack)) && oentityplayer.L.d) {
                            return oitemstack;
                        }
                        oworld.e(i, j, k, 0);
                        return new OItemStack(OItem.ax);
                    }
                } else {
                    if (this.a < 0) {
                        return new OItemStack(OItem.av);
                    }

                    if (omovingobjectposition.e == 0) {
                        --j;
                    }

                    if (omovingobjectposition.e == 1) {
                        ++j;
                    }

                    if (omovingobjectposition.e == 2) {
                        --k;
                    }

                    if (omovingobjectposition.e == 3) {
                        ++k;
                    }

                    if (omovingobjectposition.e == 4) {
                        --i;
                    }

                    if (omovingobjectposition.e == 5) {
                        ++i;
                    }

                    if (!oentityplayer.d(i, j, k)) {
                        return oitemstack;
                    }

                    if (oworld.g(i, j, k) || !oworld.d(i, j, k).a()) {
                        if (oworld.t.d && this.a == OBlock.A.bO) {
                            oworld.a(d0 + 0.5D, d1 + 0.5D, d2 + 0.5D, "random.fizz", 0.5F, 2.6F + (oworld.r.nextFloat() - oworld.r.nextFloat()) * 0.8F);

                            for (int l = 0; l < 8; ++l) {
                                oworld.a("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
                            }
                        } else {
                            // CanaryMod: bucket empty
                            blockPlaced = new Block(oworld.world, a, i, j, k);
                            if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack))) {
                                return oitemstack;
                            }
                            oworld.b(i, j, k, this.a, 0);
                        }

                        if (oentityplayer.L.d) {
                            return oitemstack;
                        }

                        return new OItemStack(OItem.av);
                    }
                }
            } else if (this.a == 0 && omovingobjectposition.g instanceof OEntityCow) {
                // CanaryMod hook: onCowMilk
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COW_MILK, etc.getServer().getPlayer(oentityplayer.v), new Mob((OEntityCow) omovingobjectposition.g))) {
                    return new OItemStack(OItem.aF);
                }
            }

            return oitemstack;
        }
    }
}
