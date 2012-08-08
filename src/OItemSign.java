
public class OItemSign extends OItem {

    public OItemSign(int i) {
        super(i);
        this.bQ = 1;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        if (l == 0) {
            return false;
        } else if (!oworld.d(i, j, k).a()) {
            return false;
        } else {
            // CanaryMod: Store block data clicked
            Block blockClicked = new Block(oworld.world, oworld.a(i, j, k), i, j, k);

            blockClicked.setFaceClicked(Block.Face.fromId(l));
            
            if (l == 1) {
                ++j;
            }

            if (l == 2) {
                --k;
            }

            if (l == 3) {
                ++k;
            }

            if (l == 4) {
                --i;
            }

            if (l == 5) {
                ++i;
            }

            if (!oentityplayer.d(i, j, k)) {
                return false;
            } else if (!OBlock.aD.c(oworld, i, j, k)) {
                return false;
            } else {
                // CanaryMod: Now we can call itemUse :)
                Block blockPlaced = new Block(oworld.world, (l == 1 ? 63 : 68), i, j, k);

                if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack))) {
                    return false;
                }
                
                if (l == 1) {
                    int i1 = OMathHelper.b((double) ((oentityplayer.bs + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;

                    oworld.b(i, j, k, OBlock.aD.bO, i1);
                } else {
                    oworld.b(i, j, k, OBlock.aI.bO, l);
                }

                --oitemstack.a;
                OTileEntitySign otileentitysign = (OTileEntitySign) oworld.b(i, j, k);

                if (otileentitysign != null) {
                    oentityplayer.a(otileentitysign);
                }

                return true;
            }
        }
    }
}
