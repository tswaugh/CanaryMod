public class OItemFirework extends OItem {

    public OItemFirework(int i) {
        super(i);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        if (!oworld.I) {
        	OEntityFireworkRocket oentityfireworkrocket = new OEntityFireworkRocket(oworld, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), oitemstack);
        	
        	if(oitemstack.d == null)
        		return true;
            
        	oworld.d((OEntity) oentityfireworkrocket);
            if (!oentityplayer.cd.d) {
                --oitemstack.a;
            }

            return true;
        } else {
            return false;
        }
    }
}
