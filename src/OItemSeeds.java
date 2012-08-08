
public class OItemSeeds extends OItem {

    private int a;
    private int b;

    public OItemSeeds(int i, int j, int k) {
        super(i);
        this.a = j;
        this.b = k;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        if (l != 1) {
            return false;
        } else if (oentityplayer.d(i, j, k) && oentityplayer.d(i, j + 1, k)) {
            int i1 = oworld.a(i, j, k);

            if (i1 == this.b && oworld.g(i, j + 1, k)) {
                // CanaryMod: Seeds
                Block blockClicked = new Block(oworld.world, i1, i, j, k);

                blockClicked.setFaceClicked(Block.Face.fromId(l));
                Block blockPlaced = new Block(oworld.world, oworld.a(i, j + 1, k), i, j + 1, k);

                // Call the hook
                Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(oitemstack))) {
                    return false;
                }
                
                oworld.e(i, j + 1, k, this.a);
                --oitemstack.a;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
