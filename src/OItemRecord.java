
public class OItemRecord extends OItem {

    public final String a;

    protected OItemRecord(int i, String s) {
        super(i);
        this.a = s;
        this.bU = 1;
        this.a(OCreativeTabs.f);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, this.getBlockInfo(oworld, i, j, k, l), new Item(oitemstack))) {
            return true;
        }
        if (oworld.a(i, j, k) == OBlock.aY.ca && oworld.g(i, j, k) == 0) {
            if (oworld.K) {
                return true;
            } else {
                ((OBlockJukeBox) OBlock.aY).e(oworld, i, j, k, this.bT);
                oworld.a((OEntityPlayer) null, 1005, i, j, k, this.bT);
                --oitemstack.a;
                return true;
            }
        } else {
            return false;
        }
    }
}
