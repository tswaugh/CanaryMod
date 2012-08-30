import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class OItemPotion extends OItem {

    private HashMap a = new HashMap();
    private static final Map b = new LinkedHashMap();

    public OItemPotion(int i) {
        super(i);
        this.d(1);
        this.a(true);
        this.e(0);
        this.a(OCreativeTabs.k);
    }

    public List l(OItemStack oitemstack) {
        return this.f(oitemstack.j());
    }

    public List f(int i) {
        List list = (List) this.a.get(Integer.valueOf(i));

        if (list == null) {
            list = OPotionHelper.b(i, false);
            this.a.put(Integer.valueOf(i), list);
        }

        return list;
    }

    public OItemStack b(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        if (!oentityplayer.bZ.d) {
            --oitemstack.a;
        }

        if (!oworld.K) {
            List list = this.l(oitemstack);

            if (list != null) {
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

                    oentityplayer.d(new OPotionEffect(opotioneffect));
                }
            }
        }

        if (!oentityplayer.bZ.d) {
            if (oitemstack.a <= 0) {
                return new OItemStack(OItem.bt);
            }

            oentityplayer.by.a(new OItemStack(OItem.bt));
        }

        return oitemstack;
    }

    public int a(OItemStack oitemstack) {
        return 32;
    }

    public OEnumAction b(OItemStack oitemstack) {
        return OEnumAction.c;
    }

    public OItemStack a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        if (g(oitemstack.j())) {
            if (!oentityplayer.bZ.d) {
                --oitemstack.a;
            }

            oworld.a(oentityplayer, "random.bow", 0.5F, 0.4F / (d.nextFloat() * 0.4F + 0.8F));
            if (!oworld.K) {
                oworld.d((OEntity) (new OEntityPotion(oworld, oentityplayer, oitemstack.j())));
            }

            return oitemstack;
        } else {
            oentityplayer.a(oitemstack, this.a(oitemstack));
            return oitemstack;
        }
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        return (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, this.getBlockInfo(oworld, i, j, k, l), new Item(oitemstack));
    }

    public static boolean g(int i) {
        return (i & 16384) != 0;
    }
}
