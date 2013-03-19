import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OCombatTracker {

    private final List a = new ArrayList();
    private final OEntityLiving b;
    private int c = 0;
    private boolean d = false;
    private boolean e = false;
    private String f;

    public OCombatTracker(OEntityLiving var1) {
        this.b = var1;
    }

    public void a() {
        this.g();
        if (this.b.g_()) {
            int var1 = this.b.q.a(OMathHelper.c(this.b.u), OMathHelper.c(this.b.E.b), OMathHelper.c(this.b.w));

            if (var1 == OBlock.aJ.cz) {
                this.f = "ladder";
            } else if (var1 == OBlock.by.cz) {
                this.f = "vines";
            }
        } else if (this.b.G()) {
            this.f = "water";
        }
    }

    public void a(ODamageSource var1, int var2, int var3) {
        this.h();
        this.a();
        OCombatEntry var4 = new OCombatEntry(var1, this.b.ac, var2, var3, this.f, this.b.T);

        this.a.add(var4);
        this.c = this.b.ac;
        this.e = true;
        this.d |= var4.f();
    }

    public String b() {
        if (this.a.size() == 0) {
            return this.b.ax() + " died";
        } else {
            OCombatEntry var1 = this.f();
            OCombatEntry var2 = (OCombatEntry) this.a.get(this.a.size() - 1);
            String var3 = "";
            String var4 = var2.h();
            OEntity var5 = var2.a().i();

            if (var1 != null && var2.a() == ODamageSource.h) {
                String var6 = var1.h();

                if (var1.a() != ODamageSource.h && var1.a() != ODamageSource.i) {
                    if (var6 != null && (var4 == null || !var6.equals(var4))) {
                        OEntity var9 = var1.a().i();
                        OItemStack var8 = var9 instanceof OEntityLiving ? ((OEntityLiving) var9).bG() : null;

                        if (var8 != null && var8.t()) {
                            var3 = OStatCollector.a("death.fell.assist.item", new Object[] { this.b.ax(), var4, var8.s()});
                        } else {
                            var3 = OStatCollector.a("death.fell.assist", new Object[] { this.b.ax(), var6});
                        }
                    } else if (var4 != null) {
                        OItemStack var7 = var5 instanceof OEntityLiving ? ((OEntityLiving) var5).bG() : null;

                        if (var7 != null && var7.t()) {
                            var3 = OStatCollector.a("death.fell.finish.item", new Object[] { this.b.ax(), var4, var7.s()});
                        } else {
                            var3 = OStatCollector.a("death.fell.finish", new Object[] { this.b.ax(), var4});
                        }
                    } else {
                        var3 = OStatCollector.a("death.fell.killer", new Object[] { this.b.ax()});
                    }
                } else {
                    var3 = OStatCollector.a("death.fell.accident." + this.a(var1), new Object[] { this.b.ax()});
                }
            } else {
                var3 = var2.a().b(this.b);
            }

            return var3;
        }
    }

    public OEntityLiving c() {
        OEntityLiving var1 = null;
        OEntityPlayer var2 = null;
        int var3 = 0;
        int var4 = 0;
        Iterator var5 = this.a.iterator();

        while (var5.hasNext()) {
            OCombatEntry var6 = (OCombatEntry) var5.next();

            if (var6.a().i() instanceof OEntityPlayer && (var2 == null || var6.c() > var4)) {
                var4 = var6.c();
                var2 = (OEntityPlayer) var6.a().i();
            }

            if (var6.a().i() instanceof OEntityLiving && (var1 == null || var6.c() > var3)) {
                var3 = var6.c();
                var1 = (OEntityLiving) var6.a().i();
            }
        }

        if (var2 != null && var4 >= var3 / 3) {
            return var2;
        } else {
            return var1;
        }
    }

    private OCombatEntry f() {
        OCombatEntry var1 = null;
        OCombatEntry var2 = null;
        byte var3 = 0;
        float var4 = 0.0F;

        for (int var5 = 0; var5 < this.a.size(); ++var5) {
            OCombatEntry var6 = (OCombatEntry) this.a.get(var5);
            OCombatEntry var7 = var5 > 0 ? (OCombatEntry) this.a.get(var5 - 1) : null;

            if ((var6.a() == ODamageSource.h || var6.a() == ODamageSource.i) && var6.i() > 0.0F && (var1 == null || var6.i() > var4)) {
                if (var5 > 0) {
                    var1 = var7;
                } else {
                    var1 = var6;
                }

                var4 = var6.i();
            }

            if (var6.g() != null && (var2 == null || var6.c() > var3)) {
                var2 = var6;
            }
        }

        if (var4 > 5.0F && var1 != null) {
            return var1;
        } else if (var3 > 5 && var2 != null) {
            return var2;
        } else {
            return null;
        }
    }

    private String a(OCombatEntry var1) {
        return var1.g() == null ? "generic" : var1.g();
    }

    private void g() {
        this.f = null;
    }

    private void h() {
        int var1 = this.d ? 300 : 100;

        if (this.e && this.b.ac - this.c > var1) {
            this.a.clear();
            this.e = false;
            this.d = false;
        }
    }
}
