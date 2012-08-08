
public class OItemRecord extends OItem {

    public final String a;

    protected OItemRecord(int i, String s) {
        super(i);
        this.a = s;
        this.bQ = 1;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, this.getBlockInfo(oworld, i, j, k, l), new Item(oitemstack))) {
            return true;
        }
        if (oworld.a(i, j, k) == OBlock.aY.bO && oworld.c(i, j, k) == 0) {
            if (oworld.F) {
                return true;
            } else {
                ((OBlockJukeBox) OBlock.aY).f(oworld, i, j, k, this.bP);
                oworld.a((OEntityPlayer) null, 1005, i, j, k, this.bP);
                --oitemstack.a;
                return true;
            }
        } else {
            return false;
        }
    }
}
