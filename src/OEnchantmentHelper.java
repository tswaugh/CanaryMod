    import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class OEnchantmentHelper {

    private static final Random a = new Random();
    private static final OEnchantmentModifierDamage b = new OEnchantmentModifierDamage((OEmpty3) null);
    private static final OEnchantmentModifierLiving c = new OEnchantmentModifierLiving((OEmpty3) null);

    public static int a(int i, OItemStack oitemstack) {
        if (oitemstack == null) {
            return 0;
        } else {
            ONBTTagList onbttaglist = oitemstack.q();

            if (onbttaglist == null) {
                return 0;
            } else {
                for (int j = 0; j < onbttaglist.c(); ++j) {
                    short short1 = ((ONBTTagCompound) onbttaglist.b(j)).d("id");
                    short short2 = ((ONBTTagCompound) onbttaglist.b(j)).d("lvl");

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
            ONBTTagList onbttaglist = oitemstack.q();

            if (onbttaglist != null) {
                for (int j = 0; j < onbttaglist.c(); ++j) {
                    short short1 = ((ONBTTagCompound) onbttaglist.b(j)).d("id");
                    short short2 = ((ONBTTagCompound) onbttaglist.b(j)).d("lvl");

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
        a((OIEnchantmentModifier) c, oinventoryplayer.g());
        return c.a > 0 ? 1 + a.nextInt(c.a) : 0;
    }

    public static int b(OInventoryPlayer oinventoryplayer, OEntityLiving oentityliving) {
        return a(OEnchantment.m.x, oinventoryplayer.g());
    }

    public static int c(OInventoryPlayer oinventoryplayer, OEntityLiving oentityliving) {
        return a(OEnchantment.n.x, oinventoryplayer.g());
    }

    public static int a(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.h.x, oinventoryplayer.b);
    }

    public static int b(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.p.x, oinventoryplayer.g());
    }

    public static int c(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.r.x, oinventoryplayer.g());
    }

    public static boolean d(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.q.x, oinventoryplayer.g()) > 0;
    }

    public static int e(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.s.x, oinventoryplayer.g());
    }

    public static int f(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.o.x, oinventoryplayer.g());
    }

    public static boolean g(OInventoryPlayer oinventoryplayer) {
        return a(OEnchantment.i.x, oinventoryplayer.b) > 0;
    }

    public static int a(Random random, int i, int j, OItemStack oitemstack) {
        OItem oitem = oitemstack.b();
        int k = oitem.b();

        if (k <= 0) {
            return 0;
        } else {
            if (j > 15) {
                j = 15;
            }

            int l = random.nextInt(8) + 1 + (j >> 1) + random.nextInt(j + 1);

            return i == 0 ? Math.max(l / 3, 1) : (i == 1 ? l * 2 / 3 + 1 : Math.max(l, j * 2));
        }
    }

    public static OItemStack a(Random random, OItemStack oitemstack, int i) {
        List list = b(random, oitemstack, i);

        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                OEnchantmentData oenchantmentdata = (OEnchantmentData) iterator.next();

                oitemstack.a(oenchantmentdata.b, oenchantmentdata.c);
            }
        }
        
        return oitemstack;
    }

    public static List b(Random random, OItemStack oitemstack, int i) {
        OItem oitem = oitemstack.b();
        int j = oitem.b();

        if (j <= 0) {
            return null;
        } else {
            j /= 2;
            j = 1 + random.nextInt((j >> 1) + 1) + random.nextInt((j >> 1) + 1);
            int k = j + i;
            float f = (random.nextFloat() + random.nextFloat() - 1.0F) * 0.15F;
            int l = (int) ((float) k * (1.0F + f) + 0.5F);
            
            if (l < 1) {
                l = 1;
            }
            
            ArrayList arraylist = null;
            Map map = b(l, oitemstack);

            if (map != null && !map.isEmpty()) {
                OEnchantmentData oenchantmentdata = (OEnchantmentData) OWeightedRandom.a(random, map.values());

                if (oenchantmentdata != null) {
                    arraylist = new ArrayList();
                    arraylist.add(oenchantmentdata);

                    for (int i1 = l; random.nextInt(50) <= i1; i1 >>= 1) {
                        Iterator iterator = map.keySet().iterator();

                        while (iterator.hasNext()) {
                            Integer integer = (Integer) iterator.next();
                            boolean flag = true;
                            Iterator iterator1 = arraylist.iterator();

                            while (true) {
                                if (iterator1.hasNext()) {
                                    OEnchantmentData oenchantmentdata1 = (OEnchantmentData) iterator1.next();

                                    if (oenchantmentdata1.b.a(OEnchantment.b[integer.intValue()])) {
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
        OItem oitem = oitemstack.b();
        HashMap hashmap = null;
        OEnchantment[] aoenchantment = OEnchantment.b;
        int j = aoenchantment.length;

        for (int k = 0; k < j; ++k) {
            OEnchantment oenchantment = aoenchantment[k];

            if (oenchantment != null && oenchantment.y.a(oitem)) {
                for (int l = oenchantment.d(); l <= oenchantment.b(); ++l) {
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
