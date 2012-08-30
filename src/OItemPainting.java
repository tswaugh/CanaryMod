
public class OItemPainting extends OItem {

    public OItemPainting(int i) {
        super(i);
        this.a(OCreativeTabs.c);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        // CanaryMod: store clicked block data
        Block blockClicked = this.getBlockInfo(oworld, i, j, k, l);
        
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

            if (!oentityplayer.e(i, j, k)) {
                return false;
            } else {
                OEntityPainting oentitypainting = new OEntityPainting(oworld, i, j, k, b0);

                if (oentitypainting.d()) {
                    if (!oworld.K) {
                        // CanaryMod: Painting place hook
                        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, blockClicked, new Item(oitemstack))) {
                            return false;
                        }
                        oworld.d((OEntity) oentitypainting);
                    }

                    --oitemstack.a;
                }

                return true;
            }
        }
    }
}
