
public class OItemHoe extends OItem {

    public OItemHoe(int i, OEnumToolMaterial oenumtoolmaterial) {
        super(i);
        this.bQ = 1;
        this.f(oenumtoolmaterial.a());
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        if (!oentityplayer.d(i, j, k)) {
            return false;
        } else {
            int i1 = oworld.a(i, j, k);
            int j1 = oworld.a(i, j + 1, k);

            if ((l == 0 || j1 != 0 || i1 != OBlock.u.bO) && i1 != OBlock.v.bO) {
                return false;
            } else {
                // CanaryMod: Hoes
                Block blockClicked = new Block(oworld.world, i1, i, j, k);

                blockClicked.setFaceClicked(Block.Face.fromId(l));
                Block blockPlaced = new Block(oworld.world, oworld.a(i, j + 1, k), i, j + 1, k);

                // Call the hook
                if (oentityplayer instanceof OEntityPlayerMP) {
                    Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                    if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(oitemstack))) {
                        return false;
                    }
                }
                
                OBlock oblock = OBlock.aA;

                oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), oblock.cb.c(), (oblock.cb.a() + 1.0F) / 2.0F, oblock.cb.b() * 0.8F);
                if (oworld.F) {
                    return true;
                } else {
                    oworld.e(i, j, k, oblock.bO);
                    oitemstack.a(1, oentityplayer);
                    return true;
                }
            }
        }
    }
}
