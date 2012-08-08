import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class OEnchantmentHelper {

    private static final Random a = new Random();
    private static final OEnchantmentModifierDamage b = new OEnchantmentModifierDamage((OEmpty1) null);
    private static final OEnchantmentModifierLiving c = new OEnchantmentModifierLiving((OEmpty1) null);

    public OEnchantmentHelper() {
        super();
    }

    public static int a(int i, OItemStack oitemstack) {
        if (oitemstack == null) {
            return 0;
        } else {
            ONBTTagList onbttaglist = oitemstack.p();

            if (onbttaglist == null) {
                return 0;
            } else {
                for (int j = 0; j < onbttaglist.d(); ++j) {
                    short short1 = ((ONBTTagCompound) onbttaglist.a(j)).e("id");
                    short short2 = ((ONBTTagCompound) onbttaglist.a(j)).e("lvl");

                    if (short1 == i) {
                        return short2;
                    }
                }

                return 0;
            }
        }
    }

    private static int a(int i, OItemStack[] aoitemstack) {
        int j = 0;
        OItemStack[] aoitemstack1 = aoitemstack;
        int k = aoitemstack.length;

        for (int l = 0; l < k; ++l) {
            OItemStack oitemstack = aoitemstack1[l];
            int i1 = a(i, oitemstack);

            if (i1 > j) {
                j = i1;
            }
        }

        return j;
    }

    private static void a(OIEnchantmentModifier oienchantmentmodifier, OItemStack oitemstack) {
        if (oitemstack != null) {
            ONBTTagList onbttaglist = oitemstack.p();

            if (onbttaglist != null) {
                for (int i = 0; i < onbttaglist.d(); ++i) {
                    short short1 = ((ONBTTagCompound) onbttaglist.a(i)).e("id");
                    short short2 = ((ONBTTagCompound) onbttaglist.a(i)).e("lvl");

                    if (OEnchantment.b[short1] != null) {
                        oienchantmentmodifier.a(OEnchantment.b[short1], short2);
                    }
                }

            }
        }
    }

    private static void a(OIEnchantmentModifier oienchantmentmodifier, OItemStack[] aoitemstack) {
        OItemStack[] aoitemstack1 = aoitemstack;
        int i = aoitemstack.length;

        for (int j = 0; j < i; ++j) {
            OItemStack oitemstack = aoitemstack1[j];

            a(oienchantmentmodifier, oitemstack);
        }

    }

    public static int a(OInventoryPlayer oinventoryplayer, ODamageSource odamagesource) {
        b.a = 0;
        b.b = odamagesource;
        a((OIEnchantmentModifier) b, oinventoryplayer.b);
        if (b.a > 25) {
            b.a = 25;
        }

        return (b.a + 1 >> 1) + a.nextInt((b.a >> 1) + 1);
    }

    public static int a(OInventoryPlayer oinventoryplayer, OEntityLiving oentityliving) {
        c.a = 0;
        c.b = oentityliving;
        a((OIEnchantmentModifier) c, oinventoryplayer.d());
        return c.a > 0 ? 1 + a.nextInt(c.a) : 0;
    }

    public static int b(OInventoryPlayer oinventoryplayer, OEntityLiving oentityliving) {
        return a(OEnchantment.m.x, oinventoryplayer.d());
    }

    public static int c(OInventoryPlayer oinventoryplayer, OEntityLiving oentityliving) {
        return a(OEnchantment.n.x, oinventoryplayer.d());
    }

    public static int a(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.h.x, oinventoryplayer.b);
    }

    public static int b(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.p.x, oinventoryplayer.d());
    }

    public static int c(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.r.x, oinventoryplayer.d());
    }

    public static boolean d(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.q.x, oinventoryplayer.d()) > 0;
    }

    public static int e(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.s.x, oinventoryplayer.d());
    }

    public static int f(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.o.x, oinventoryplayer.d());
    }

    public static boolean g(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.i.x, oinventoryplayer.b) > 0;
    }

    public static int a(Random random, int i, int j, OItemStack oitemstack) {
        OItem oitem = oitemstack.a();
        int k = oitem.c();

        if (k <= 0) {
            return 0;
        } else {
            if (j > 30) {
                j = 30;
            }

            j = 1 + (j >> 1) + random.nextInt(j + 1);
            int l = random.nextInt(5) + j;

            return i == 0 ? (l >> 1) + 1 : (i == 1 ? l * 2 / 3 + 1 : l);
        }
    }

    public static void a(Random random, OItemStack oitemstack, int i) {
        List list = b(random, oitemstack, i);

        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                OEnchantmentData oenchantmentdata = (OEnchantmentData) iterator.next();

                oitemstack.a(oenchantmentdata.a, oenchantmentdata.b);
            }
        }

    }

    public static List b(Random random, OItemStack oitemstack, int i) {
        OItem oitem = oitemstack.a();
        int j = oitem.c();

        if (j <= 0) {
            return null;
        } else {
            j = 1 + random.nextInt((j >> 1) + 1) + random.nextInt((j >> 1) + 1);
            int k = j + i;
            float f = (random.nextFloat() + random.nextFloat() - 1.0F) * 0.25F;
            int l = (int) ((float) k * (1.0F + f) + 0.5F);
            ArrayList arraylist = null;
            Map map = b(l, oitemstack);

            if (map != null && !map.isEmpty()) {
                OEnchantmentData oenchantmentdata = (OEnchantmentData) OWeightedRandom.a(random, map.values());

                if (oenchantmentdata != null) {
                    arraylist = new ArrayList();
                    arraylist.add(oenchantmentdata);

                    for (int i1 = l >> 1; random.nextInt(50) <= i1; i1 >>= 1) {
                        Iterator iterator = map.keySet().iterator();

                        while (iterator.hasNext()) {
                            Integer integer = (Integer) iterator.next();
                            boolean flag = true;
                            Iterator iterator1 = arraylist.iterator();

                            while (true) {
                                if (iterator1.hasNext()) {
                                    OEnchantmentData oenchantmentdata1 = (OEnchantmentData) iterator1.next();

                                    if (oenchantmentdata1.a.a(OEnchantment.b[integer.intValue()])) {
                                        continue;
                                    }

                                    flag = false;
                                }

                                if (!flag) {
                                    iterator.remove();
                                }
                                break;
                            }
                        }

                        if (!map.isEmpty()) {
                            OEnchantmentData oenchantmentdata2 = (OEnchantmentData) OWeightedRandom.a(random, map.values());

                            arraylist.add(oenchantmentdata2);
                        }
                    }
                }
            }

            return arraylist;
        }
    }

    public static Map b(int i, OItemStack oitemstack) {
        OItem oitem = oitemstack.a();
        HashMap hashmap = null;
        OEnchantment[] aoenchantment = OEnchantment.b;
        int j = aoenchantment.length;

        for (int k = 0; k < j; ++k) {
            OEnchantment oenchantment = aoenchantment[k];

            if (oenchantment != null && oenchantment.y.a(oitem)) {
                for (int l = oenchantment.c(); l <= oenchantment.a(); ++l) {
                    if (i >= oenchantment.a(l) && i <= oenchantment.b(l)) {
                        if (hashmap == null) {
                            hashmap = new HashMap();
                        }

                        hashmap.put(Integer.valueOf(oenchantment.x), new OEnchantmentData(oenchantment, l));
                    }
                }
            }
        }

        return hashmap;
    }

}
