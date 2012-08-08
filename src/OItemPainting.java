
public class OItemPainting extends OItem {

    public OItemPainting(int i) {
        super(i);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        // CanaryMod: store clicked block data
        Block blockClicked = new Block(oworld.world, oworld.world.getBlockIdAt(i, j, k), i, j, k);

        blockClicked.setFaceClicked(Block.Face.fromId(l));
        
        if (l == 0) {
            return false;
        } else if (l == 1) {
            return false;
        } else {
            byte b0 = 0;

            if (l == 4) {
                b0 = 1;
            }

            if (l == 3) {
                b0 = 2;
            }

            if (l == 5) {
                b0 = 3;
            }

            if (!oentityplayer.d(i, j, k)) {
                return false;
            } else {
                OEntityPainting oentitypainting = new OEntityPainting(oworld, i, j, k, b0);

                if (oentitypainting.k()) {
                    if (!oworld.F) {
                        // CanaryMod: Painting place hook
                        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, blockClicked, new Item(oitemstack))) {
                            return false;
                        }
                        oworld.b((OEntity) oentitypainting);
                    }

                    --oitemstack.a;
                }

                return true;
            }
        }
    }
}
