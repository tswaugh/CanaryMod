import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OFlatGeneratorInfo {

    private final List a = new ArrayList();
    private final Map b = new HashMap();
    private int c = 0;

    public OFlatGeneratorInfo() {}

    public int a() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public Map b() {
        return this.b;
    }

    public List c() {
        return this.a;
    }

    public void d() {
        int i = 0;

        OFlatLayerInfo oflatlayerinfo;

        for (Iterator iterator = this.a.iterator(); iterator.hasNext(); i += oflatlayerinfo.a()) {
            oflatlayerinfo = (OFlatLayerInfo) iterator.next();
            oflatlayerinfo.d(i);
        }
    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();

        stringbuilder.append(2);
        stringbuilder.append(";");

        int i;

        for (i = 0; i < this.a.size(); ++i) {
            if (i > 0) {
                stringbuilder.append(",");
            }

            stringbuilder.append(((OFlatLayerInfo) this.a.get(i)).toString());
        }

        stringbuilder.append(";");
        stringbuilder.append(this.c);
        if (!this.b.isEmpty()) {
            stringbuilder.append(";");
            i = 0;
            Iterator iterator = this.b.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry entry = (Entry) iterator.next();

                if (i++ > 0) {
                    stringbuilder.append(",");
                }

                stringbuilder.append(((String) entry.getKey()).toLowerCase());
                Map map = (Map) entry.getValue();

                if (!map.isEmpty()) {
                    stringbuilder.append("(");
                    int j = 0;
                    Iterator iterator1 = map.entrySet().iterator();

                    while (iterator1.hasNext()) {
                        Entry entry1 = (Entry) iterator1.next();

                        if (j++ > 0) {
                            stringbuilder.append(" ");
                        }

                        stringbuilder.append((String) entry1.getKey());
                        stringbuilder.append("=");
                        stringbuilder.append((String) entry1.getValue());
                    }

                    stringbuilder.append(")");
                }
            }
        }

        return stringbuilder.toString();
    }

    private static OFlatLayerInfo a(String s, int i) {
        String[] astring = s.split("x", 2);
        int j = 1;
        int k = 0;

        if (astring.length == 2) {
            try {
                j = Integer.parseInt(astring[0]);
                if (i + j >= 256) {
                    j = 256 - i;
                }

                if (j < 0) {
                    j = 0;
                }
            } catch (Throwable throwable) {
                return null;
            }
        }

        int l;

        try {
            String s1 = astring[astring.length - 1];

            astring = s1.split(":", 2);
            l = Integer.parseInt(astring[0]);
            if (astring.length > 1) {
                k = Integer.parseInt(astring[1]);
            }

            if (OBlock.p[l] == null) {
                l = 0;
                k = 0;
            }

            if (k < 0 || k > 15) {
                k = 0;
            }
        } catch (Throwable throwable1) {
            return null;
        }

        OFlatLayerInfo oflatlayerinfo = new OFlatLayerInfo(j, l, k);

        oflatlayerinfo.d(i);
        return oflatlayerinfo;
    }

    private static List b(String s) {
        if (s != null && s.length() >= 1) {
            ArrayList arraylist = new ArrayList();
            String[] astring = s.split(",");
            int i = 0;
            String[] astring1 = astring;
            int j = astring.length;

            for (int k = 0; k < j; ++k) {
                String s1 = astring1[k];
                OFlatLayerInfo oflatlayerinfo = a(s1, i);

                if (oflatlayerinfo == null) {
                    return null;
                }

                arraylist.add(oflatlayerinfo);
                i += oflatlayerinfo.a();
            }

            return arraylist;
        } else {
            return null;
        }
    }

    public static OFlatGeneratorInfo a(String s) {
        if (s == null) {
            return e();
        } else {
            String[] astring = s.split(";", -1);
            int i = astring.length == 1 ? 0 : OMathHelper.a(astring[0], 0);

            if (i >= 0 && i <= 2) {
                OFlatGeneratorInfo oflatgeneratorinfo = new OFlatGeneratorInfo();
                int j = astring.length == 1 ? 0 : 1;
                List list = b(astring[j++]);

                if (list != null && !list.isEmpty()) {
                    oflatgeneratorinfo.c().addAll(list);
                    oflatgeneratorinfo.d();
                    int k = OBiomeGenBase.c.N;

                    if (i > 0 && astring.length > j) {
                        k = OMathHelper.a(astring[j++], k);
                    }

                    oflatgeneratorinfo.a(k);
                    if (i > 0 && astring.length > j) {
                        String[] astring1 = astring[j++].toLowerCase().split(",");
                        String[] astring2 = astring1;
                        int l = astring1.length;

                        for (int i1 = 0; i1 < l; ++i1) {
                            String s1 = astring2[i1];
                            String[] astring3 = s1.split("\\(", 2);
                            HashMap hashmap = new HashMap();

                            if (astring3[0].length() > 0) {
                                oflatgeneratorinfo.b().put(astring3[0], hashmap);
                                if (astring3.length > 1 && astring3[1].endsWith(")") && astring3[1].length() > 1) {
                                    String[] astring4 = astring3[1].substring(0, astring3[1].length() - 1).split(" ");

                                    for (int j1 = 0; j1 < astring4.length; ++j1) {
                                        String[] astring5 = astring4[j1].split("=", 2);

                                        if (astring5.length == 2) {
                                            hashmap.put(astring5[0], astring5[1]);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        oflatgeneratorinfo.b().put("village", new HashMap());
                    }

                    return oflatgeneratorinfo;
                } else {
                    return e();
                }
            } else {
                return e();
            }
        }
    }

    public static OFlatGeneratorInfo e() {
        OFlatGeneratorInfo oflatgeneratorinfo = new OFlatGeneratorInfo();

        oflatgeneratorinfo.a(OBiomeGenBase.c.N);
        oflatgeneratorinfo.c().add(new OFlatLayerInfo(1, OBlock.C.cm));
        oflatgeneratorinfo.c().add(new OFlatLayerInfo(2, OBlock.y.cm));
        oflatgeneratorinfo.c().add(new OFlatLayerInfo(1, OBlock.x.cm));
        oflatgeneratorinfo.d();
        oflatgeneratorinfo.b().put("village", new HashMap());
        return oflatgeneratorinfo;
    }
}
