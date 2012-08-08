import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class OItemPotion extends OItem {

    private HashMap a = new HashMap();

    public OItemPotion(int i) {
        super(i);
        this.e(1);
        this.a(true);
        this.f(0);
    }

    public List b(OItemStack oitemstack) {
        return this.b(oitemstack.h());
    }

    public List b(int i) {
        List list = (List) this.a.get(Integer.valueOf(i));

        if (list == null) {
            list = OPotionHelper.a(i, false);
            this.a.put(Integer.valueOf(i), list);
        }

        return list;
    }

    public OItemStack b(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        --oitemstack.a;
        if (!oworld.F) {
            List list = this.b(oitemstack);

            if (list != null) {
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

                    oentityplayer.e(new OPotionEffect(opotioneffect));
                }
            }
        }

        if (oitemstack.a <= 0) {
            return new OItemStack(OItem.bs);
        } else {
            oentityplayer.k.a(new OItemStack(OItem.bs));
            return oitemstack;
        }
    }

    public int c(OItemStack oitemstack) {
        return 32;
    }

    public OEnumAction d(OItemStack oitemstack) {
        return OEnumAction.c;
    }

    public OItemStack a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        if (c(oitemstack.h())) {
            --oitemstack.a;
            oworld.a(oentityplayer, "random.bow", 0.5F, 0.4F / (c.nextFloat() * 0.4F + 0.8F));
            if (!oworld.F) {
                oworld.b((OEntity) (new OEntityPotion(oworld, oentityplayer, oitemstack.h())));
            }

            return oitemstack;
        } else {
            oentityplayer.a(oitemstack, this.c(oitemstack));
            return oitemstack;
        }
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
        return (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, this.getBlockInfo(oworld, i, j, k, l), new Item(oitemstack));
    }

    public static boolean c(int i) {
        return (i & 16384) != 0;
    }
}
