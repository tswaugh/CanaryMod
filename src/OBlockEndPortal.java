import java.util.ArrayList;
import java.util.Random;


public class OBlockEndPortal extends OBlockContainer {

    public static boolean a = false;

    protected OBlockEndPortal(int i, OMaterial omaterial) {
        super(i, 0, omaterial);
        this.a(1.0F);
    }

    public OTileEntity a_() {
        return new OTileEntityEndPortal();
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        float f = 0.0625F;

        this.a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

    public void a(OWorld oworld, int i, int j, int k, OAxisAlignedBB oaxisalignedbb, ArrayList arraylist) {}

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int a(Random random) {
        return 0;
    }

    public void a(OWorld oworld, int i, int j, int k, OEntity oentity) {
        if (oentity.bh == null && oentity.bg == null && oentity instanceof OEntityPlayer && !oworld.F) {
            // CanaryMod: Check if end is enabled
            if (etc.getInstance().isEndEnabled()) {
                ((OEntityPlayer) oentity).e(1);
            }
        }
    }

    public int c() {
        return -1;
    }

    public void a(OWorld oworld, int i, int j, int k) {
        if (!a) {
            if (oworld.t.g != 0) {
                oworld.e(i, j, k, 0);
            }
        }
    }
}
