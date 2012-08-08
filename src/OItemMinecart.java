
public class OItemMinecart extends OItem {

    public int a;

    public OItemMinecart(int i, int j) {
        super(i);
        this.bQ = 1;
        this.a = j;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        int i1 = oworld.a(i, j, k);

        if (OBlockRail.d(i1)) {
            if (!oworld.F) {
                // CanaryMod: placing of a mine cart
                Block block = new Block(oworld.world, i1, i, j, k);
                Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, block, block, new Item(oitemstack))) {
                    return false;
                }
                oworld.b((OEntity) (new OEntityMinecart(oworld, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this.a)));
            }

            --oitemstack.a;
            return true;
        } else {
            return false;
        }
    }
}
