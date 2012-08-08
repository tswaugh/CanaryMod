
public class OItemRedstone extends OItem {

    public OItemRedstone(int i) {
        super(i);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        // CanaryMod: Store block data clicked
        Block blockClicked = new Block(oworld.world, oworld.a(i, j, k), i, j, k);

        blockClicked.setFaceClicked(Block.Face.fromId(l));
        
        if (oworld.a(i, j, k) != OBlock.aS.bO) {
            if (l == 0) {
                --j;
            }

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

            if (!oworld.g(i, j, k)) {
                return false;
            }
        }

        if (!oentityplayer.d(i, j, k)) {
            return false;
        } else {
            if (OBlock.av.c(oworld, i, j, k)) {
                // CanaryMod: Redstone dust hook!
                Block blockPlaced = new Block(oworld.world, Block.Type.RedstoneWire.getType(), i, j, k);
                Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(oitemstack))) {
                    return false;
                }

                --oitemstack.a;
                oworld.e(i, j, k, OBlock.av.bO);
            }

            return true;
        }
    }
}
