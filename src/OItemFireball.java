
public class OItemFireball extends OItem {

    public OItemFireball(int i) {
        super(i);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        // CanaryMod: Store block data clicked
        Block blockClicked = new Block(oworld.world, oworld.a(i, j, k), i, j, k);

        blockClicked.setFaceClicked(Block.Face.fromId(l));

        if (oworld.F) {
            return true;
        } else {
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

            if (!oentityplayer.d(i, j, k)) {
                return false;
            } else {
                int i1 = oworld.a(i, j, k);

                if (i1 == 0) {

                    // CanaryMod: Hook to control ignites AND fireball clicks
                    Block blockPlaced = new Block(oworld.world, Block.Type.Fire.getType(), i, j, k);
                    Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                    Boolean preventLighter = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(oitemstack));

                    blockPlaced.setStatus(6); // Specifically to mark this ignite as from a fireball
                    Boolean preventIgnite = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, blockPlaced, player);

                    if (preventIgnite || preventLighter) {
                        return false;
                    }

                    oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "fire.ignite", 1.0F, c.nextFloat() * 0.4F + 0.8F);
                    oworld.e(i, j, k, OBlock.ar.bO);
                }

                if (!oentityplayer.L.d) {
                    --oitemstack.a;
                }

                return true;
            }
        }
    }
}
