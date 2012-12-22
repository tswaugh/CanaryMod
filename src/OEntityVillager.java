import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class OEntityVillager extends OEntityAgeable implements OINpc, OIMerchant {

    private int e;
    private boolean f;
    private boolean g;
    OVillage d;
    private OEntityPlayer h;
    private OMerchantRecipeList i;
    private int j;
    private boolean bI;
    private int bJ;
    private String bK;
    private boolean bL;
    private float bM;
    private static final Map bN = new HashMap();
    private static final Map bO = new HashMap();

    public OEntityVillager(OWorld oworld) {
        this(oworld, 0);
    }

    public OEntityVillager(OWorld oworld, int i) {
        super(oworld);
        this.e = 0;
        this.f = false;
        this.g = false;
        this.d = null;
        this.s(i);
        this.aF = "/mob/villager/villager.png";
        this.bG = 0.5F;
        this.az().b(true);
        this.az().a(true);
        this.bm.a(0, new OEntityAISwimming(this));
        this.bm.a(1, new OEntityAIAvoidEntity(this, OEntityZombie.class, 8.0F, 0.3F, 0.35F));
        this.bm.a(1, new OEntityAITradePlayer(this));
        this.bm.a(1, new OEntityAILookAtTradePlayer(this));
        this.bm.a(2, new OEntityAIMoveIndoors(this));
        this.bm.a(3, new OEntityAIRestrictOpenDoor(this));
        this.bm.a(4, new OEntityAIOpenDoor(this, true));
        this.bm.a(5, new OEntityAIMoveTwardsRestriction(this, 0.3F));
        this.bm.a(6, new OEntityAIVillagerMate(this));
        this.bm.a(7, new OEntityAIFollowGolem(this));
        this.bm.a(8, new OEntityAIPlay(this, 0.32F));
        this.bm.a(9, new OEntityAIWatchClosest2(this, OEntityPlayer.class, 3.0F, 1.0F));
        this.bm.a(9, new OEntityAIWatchClosest2(this, OEntityVillager.class, 5.0F, 0.02F));
        this.bm.a(9, new OEntityAIWander(this, 0.3F));
        this.bm.a(10, new OEntityAIWatchClosest(this, OEntityLiving.class, 8.0F));
    }

    public boolean be() {
        return true;
    }

    protected void bm() {
        if (--this.e <= 0) {
            this.p.C.a(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
            this.e = 70 + this.aa.nextInt(50);
            this.d = this.p.C.a(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v), 32);
            if (this.d == null) {
                this.aL();
            } else {
                OChunkCoordinates ochunkcoordinates = this.d.a();

                this.b(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, (int) ((float) this.d.b() * 0.6F));
                if (this.bL) {
                    this.bL = false;
                    this.d.b(5);
                }
            }
        }

        if (!this.p() && this.j > 0) {
            --this.j;
            if (this.j <= 0) {
                if (this.bI) {
                    if (this.i.size() > 1) {
                        Iterator iterator = this.i.iterator();

                        while (iterator.hasNext()) {
                            OMerchantRecipe omerchantrecipe = (OMerchantRecipe) iterator.next();

                            if (omerchantrecipe.g()) {
                                omerchantrecipe.a(this.aa.nextInt(6) + this.aa.nextInt(6) + 2);
                            }
                        }
                    }

                    this.t(1);
                    this.bI = false;
                    if (this.d != null && this.bK != null) {
                        this.p.a(this, (byte) 14);
                        this.d.a(this.bK, 1);
                    }
                }

                this.d(new OPotionEffect(OPotion.l.H, 200, 0));
            }
        }

        super.bm();
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bI.g();
        if ((PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.ENTITY_RIGHTCLICKED,
                    ((OEntityPlayerMP) oentityplayer).getPlayer(), new Villager(this),
                    oitemstack == null ? null : new Item(oitemstack)) == PluginLoader.HookResult.PREVENT_ACTION) {
            return false;
        }
        boolean flag = oitemstack != null && oitemstack.c == OItem.bC.cg;

        if (!flag && this.S() && !this.p() && !this.h_()) {
            if (!this.p.J) {
                this.b_(oentityplayer);
                oentityplayer.a((OIMerchant) this);
            }

            return true;
        } else {
            return super.a(oentityplayer);
        }
    }

    protected void a() {
        super.a();
        this.ag.a(16, Integer.valueOf(0));
    }

    public int aT() {
        return 20;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Profession", this.m());
        onbttagcompound.a("Riches", this.bJ);
        if (this.i != null) {
            onbttagcompound.a("Offers", this.i.a());
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.s(onbttagcompound.e("Profession"));
        this.bJ = onbttagcompound.e("Riches");
        if (onbttagcompound.b("Offers")) {
            ONBTTagCompound onbttagcompound1 = onbttagcompound.l("Offers");

            this.i = new OMerchantRecipeList(onbttagcompound1);
        }
    }

    protected boolean bj() {
        return false;
    }

    protected String aY() {
        return "mob.villager.default";
    }

    protected String aZ() {
        return "mob.villager.defaulthurt";
    }

    protected String ba() {
        return "mob.villager.defaultdeath";
    }

    public void s(int i) {
        this.ag.b(16, Integer.valueOf(i));
    }

    public int m() {
        return this.ag.c(16);
    }

    public boolean n() {
        return this.f;
    }

    public void f(boolean flag) {
        this.f = flag;
    }

    public void g(boolean flag) {
        this.g = flag;
    }

    public boolean o() {
        return this.g;
    }

    public void c(OEntityLiving oentityliving) {
        super.c(oentityliving);
        if (this.d != null && oentityliving != null) {
            this.d.a(oentityliving);
            if (oentityliving instanceof OEntityPlayer) {
                byte b0 = -1;

                if (this.h_()) {
                    b0 = -3;
                }

                this.d.a(((OEntityPlayer) oentityliving).c_(), b0);
                if (this.S()) {
                    this.p.a(this, (byte) 13);
                }
            }
        }
    }

    public void a(ODamageSource odamagesource) {
        if (this.d != null) {
            OEntity oentity = odamagesource.g();

            if (oentity != null) {
                if (oentity instanceof OEntityPlayer) {
                    this.d.a(((OEntityPlayer) oentity).c_(), -2);
                } else if (oentity instanceof OIMob) {
                    this.d.h();
                }
            } else if (oentity == null) {
                OEntityPlayer oentityplayer = this.p.a(this, 16.0D);

                if (oentityplayer != null) {
                    this.d.h();
                }
            }
        }

        super.a(odamagesource);
    }

    public void b_(OEntityPlayer oentityplayer) {
        this.h = oentityplayer;
    }

    public OEntityPlayer m_() {
        return this.h;
    }

    public boolean p() {
        return this.h != null;
    }

    public void a(OMerchantRecipe omerchantrecipe) {
        omerchantrecipe.f();
        if (omerchantrecipe.a((OMerchantRecipe) this.i.get(this.i.size() - 1))) {
            this.j = 40;
            this.bI = true;
            if (this.h != null) {
                this.bK = this.h.c_();
            } else {
                this.bK = null;
            }
        }

        if (omerchantrecipe.a().c == OItem.bH.cg) {
            this.bJ += omerchantrecipe.a().a;
        }
    }

    public OMerchantRecipeList b(OEntityPlayer oentityplayer) {
        if (this.i == null) {
            this.t(1);
        }

        return this.i;
    }

    private float j(float f) {
        float f1 = f + this.bM;

        return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
    }

    private void t(int i) {
        if (this.i != null) {
            this.bM = OMathHelper.c((float) this.i.size()) * 0.2F;
        } else {
            this.bM = 0.0F;
        }

        OMerchantRecipeList omerchantrecipelist;

        omerchantrecipelist = new OMerchantRecipeList();
        label48:
        switch (this.m()) {
            case 0:
                a(omerchantrecipelist, OItem.T.cg, this.aa, this.j(0.9F));
                a(omerchantrecipelist, OBlock.ae.cm, this.aa, this.j(0.5F));
                a(omerchantrecipelist, OItem.bk.cg, this.aa, this.j(0.5F));
                a(omerchantrecipelist, OItem.aV.cg, this.aa, this.j(0.4F));
                b(omerchantrecipelist, OItem.U.cg, this.aa, this.j(0.9F));
                b(omerchantrecipelist, OItem.bf.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.j.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.bc.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.be.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.i.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.bl.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.l.cg, this.aa, this.j(0.5F));
                if (this.aa.nextFloat() < this.j(0.5F)) {
                    omerchantrecipelist.add(new OMerchantRecipe(new OItemStack(OBlock.I, 10), new OItemStack(OItem.bH), new OItemStack(OItem.ap.cg, 4 + this.aa.nextInt(2), 0)));
                }
                break;

            case 1:
                a(omerchantrecipelist, OItem.aK.cg, this.aa, this.j(0.8F));
                a(omerchantrecipelist, OItem.aL.cg, this.aa, this.j(0.8F));
                a(omerchantrecipelist, OItem.bG.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OBlock.aq.cm, this.aa, this.j(0.8F));
                b(omerchantrecipelist, OBlock.P.cm, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.aQ.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.aS.cg, this.aa, this.j(0.2F));
                break;

            case 2:
                b(omerchantrecipelist, OItem.bA.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.bD.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.aC.cg, this.aa, this.j(0.4F));
                b(omerchantrecipelist, OBlock.bg.cm, this.aa, this.j(0.3F));
                int[] aint = new int[] { OItem.q.cg, OItem.z.cg, OItem.ae.cg, OItem.ai.cg, OItem.h.cg, OItem.C.cg, OItem.g.cg, OItem.B.cg};
                int[] aint1 = aint;
                int j = aint.length;
                int k = 0;

                while (true) {
                    if (k >= j) {
                        break label48;
                    }

                    int l = aint1[k];

                    if (this.aa.nextFloat() < this.j(0.05F)) {
                        omerchantrecipelist.add(new OMerchantRecipe(new OItemStack(l, 1, 0), new OItemStack(OItem.bH, 2 + this.aa.nextInt(3), 0), OEnchantmentHelper.a(this.aa, new OItemStack(l, 1, 0), 5 + this.aa.nextInt(15))));
                    }

                    ++k;
                }

            case 3:
                a(omerchantrecipelist, OItem.m.cg, this.aa, this.j(0.7F));
                a(omerchantrecipelist, OItem.o.cg, this.aa, this.j(0.5F));
                a(omerchantrecipelist, OItem.p.cg, this.aa, this.j(0.5F));
                a(omerchantrecipelist, OItem.n.cg, this.aa, this.j(0.5F));
                b(omerchantrecipelist, OItem.q.cg, this.aa, this.j(0.5F));
                b(omerchantrecipelist, OItem.z.cg, this.aa, this.j(0.5F));
                b(omerchantrecipelist, OItem.h.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.C.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.g.cg, this.aa, this.j(0.5F));
                b(omerchantrecipelist, OItem.B.cg, this.aa, this.j(0.5F));
                b(omerchantrecipelist, OItem.f.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.A.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.P.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.Q.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ag.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ak.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ad.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ah.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ae.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ai.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.af.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.aj.cg, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ac.cg, this.aa, this.j(0.1F));
                b(omerchantrecipelist, OItem.Z.cg, this.aa, this.j(0.1F));
                b(omerchantrecipelist, OItem.aa.cg, this.aa, this.j(0.1F));
                b(omerchantrecipelist, OItem.ab.cg, this.aa, this.j(0.1F));
                break;

            case 4:
                a(omerchantrecipelist, OItem.m.cg, this.aa, this.j(0.7F));
                a(omerchantrecipelist, OItem.aq.cg, this.aa, this.j(0.5F));
                a(omerchantrecipelist, OItem.bi.cg, this.aa, this.j(0.5F));
                b(omerchantrecipelist, OItem.aA.cg, this.aa, this.j(0.1F));
                b(omerchantrecipelist, OItem.W.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.Y.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.V.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.X.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.ar.cg, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.bj.cg, this.aa, this.j(0.3F));
        }

        if (omerchantrecipelist.isEmpty()) {
            a(omerchantrecipelist, OItem.p.cg, this.aa, 1.0F);
        }

        Collections.shuffle(omerchantrecipelist);
        if (this.i == null) {
            this.i = new OMerchantRecipeList();
        }

        for (int i1 = 0; i1 < i && i1 < omerchantrecipelist.size(); ++i1) {
            this.i.a((OMerchantRecipe) omerchantrecipelist.get(i1));
        }
    }

    private static void a(OMerchantRecipeList omerchantrecipelist, int i, Random random, float f) {
        if (random.nextFloat() < f) {
            omerchantrecipelist.add(new OMerchantRecipe(a(i, random), OItem.bH));
        }
    }

    private static OItemStack a(int i, Random random) {
        return new OItemStack(i, b(i, random), 0);
    }

    private static int b(int i, Random random) {
        OTuple otuple = (OTuple) bN.get(Integer.valueOf(i));

        return otuple == null ? 1 : (((Integer) otuple.a()).intValue() >= ((Integer) otuple.b()).intValue() ? ((Integer) otuple.a()).intValue() : ((Integer) otuple.a()).intValue() + random.nextInt(((Integer) otuple.b()).intValue() - ((Integer) otuple.a()).intValue()));
    }

    private static void b(OMerchantRecipeList omerchantrecipelist, int i, Random random, float f) {
        if (random.nextFloat() < f) {
            int j = c(i, random);
            OItemStack oitemstack;
            OItemStack oitemstack1;

            if (j < 0) {
                oitemstack = new OItemStack(OItem.bH.cg, 1, 0);
                oitemstack1 = new OItemStack(i, -j, 0);
            } else {
                oitemstack = new OItemStack(OItem.bH.cg, j, 0);
                oitemstack1 = new OItemStack(i, 1, 0);
            }

            omerchantrecipelist.add(new OMerchantRecipe(oitemstack, oitemstack1));
        }
    }

    private static int c(int i, Random random) {
        OTuple otuple = (OTuple) bO.get(Integer.valueOf(i));

        return otuple == null ? 1 : (((Integer) otuple.a()).intValue() >= ((Integer) otuple.b()).intValue() ? ((Integer) otuple.a()).intValue() : ((Integer) otuple.a()).intValue() + random.nextInt(((Integer) otuple.b()).intValue() - ((Integer) otuple.a()).intValue()));
    }

    public void bG() {
        this.s(this.p.u.nextInt(5));
    }

    public void q() {
        this.bL = true;
    }

    public OEntityVillager b(OEntityAgeable oentityageable) {
        OEntityVillager oentityvillager = new OEntityVillager(this.p);

        oentityvillager.bG();
        return oentityvillager;
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }

    static {
        bN.put(Integer.valueOf(OItem.m.cg), new OTuple(Integer.valueOf(16), Integer.valueOf(24)));
        bN.put(Integer.valueOf(OItem.o.cg), new OTuple(Integer.valueOf(8), Integer.valueOf(10)));
        bN.put(Integer.valueOf(OItem.p.cg), new OTuple(Integer.valueOf(8), Integer.valueOf(10)));
        bN.put(Integer.valueOf(OItem.n.cg), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bN.put(Integer.valueOf(OItem.aK.cg), new OTuple(Integer.valueOf(24), Integer.valueOf(36)));
        bN.put(Integer.valueOf(OItem.aL.cg), new OTuple(Integer.valueOf(11), Integer.valueOf(13)));
        bN.put(Integer.valueOf(OItem.bG.cg), new OTuple(Integer.valueOf(1), Integer.valueOf(1)));
        bN.put(Integer.valueOf(OItem.bn.cg), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bN.put(Integer.valueOf(OItem.bA.cg), new OTuple(Integer.valueOf(2), Integer.valueOf(3)));
        bN.put(Integer.valueOf(OItem.aq.cg), new OTuple(Integer.valueOf(14), Integer.valueOf(18)));
        bN.put(Integer.valueOf(OItem.bi.cg), new OTuple(Integer.valueOf(14), Integer.valueOf(18)));
        bN.put(Integer.valueOf(OItem.bk.cg), new OTuple(Integer.valueOf(14), Integer.valueOf(18)));
        bN.put(Integer.valueOf(OItem.aV.cg), new OTuple(Integer.valueOf(9), Integer.valueOf(13)));
        bN.put(Integer.valueOf(OItem.S.cg), new OTuple(Integer.valueOf(34), Integer.valueOf(48)));
        bN.put(Integer.valueOf(OItem.bh.cg), new OTuple(Integer.valueOf(30), Integer.valueOf(38)));
        bN.put(Integer.valueOf(OItem.bg.cg), new OTuple(Integer.valueOf(30), Integer.valueOf(38)));
        bN.put(Integer.valueOf(OItem.T.cg), new OTuple(Integer.valueOf(18), Integer.valueOf(22)));
        bN.put(Integer.valueOf(OBlock.ae.cm), new OTuple(Integer.valueOf(14), Integer.valueOf(22)));
        bN.put(Integer.valueOf(OItem.bm.cg), new OTuple(Integer.valueOf(36), Integer.valueOf(64)));
        bO.put(Integer.valueOf(OItem.i.cg), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bO.put(Integer.valueOf(OItem.be.cg), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bO.put(Integer.valueOf(OItem.q.cg), new OTuple(Integer.valueOf(7), Integer.valueOf(11)));
        bO.put(Integer.valueOf(OItem.z.cg), new OTuple(Integer.valueOf(12), Integer.valueOf(14)));
        bO.put(Integer.valueOf(OItem.h.cg), new OTuple(Integer.valueOf(6), Integer.valueOf(8)));
        bO.put(Integer.valueOf(OItem.C.cg), new OTuple(Integer.valueOf(9), Integer.valueOf(12)));
        bO.put(Integer.valueOf(OItem.g.cg), new OTuple(Integer.valueOf(7), Integer.valueOf(9)));
        bO.put(Integer.valueOf(OItem.B.cg), new OTuple(Integer.valueOf(10), Integer.valueOf(12)));
        bO.put(Integer.valueOf(OItem.f.cg), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bO.put(Integer.valueOf(OItem.A.cg), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bO.put(Integer.valueOf(OItem.P.cg), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bO.put(Integer.valueOf(OItem.Q.cg), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bO.put(Integer.valueOf(OItem.ag.cg), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bO.put(Integer.valueOf(OItem.ak.cg), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bO.put(Integer.valueOf(OItem.ad.cg), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bO.put(Integer.valueOf(OItem.ah.cg), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bO.put(Integer.valueOf(OItem.ae.cg), new OTuple(Integer.valueOf(10), Integer.valueOf(14)));
        bO.put(Integer.valueOf(OItem.ai.cg), new OTuple(Integer.valueOf(16), Integer.valueOf(19)));
        bO.put(Integer.valueOf(OItem.af.cg), new OTuple(Integer.valueOf(8), Integer.valueOf(10)));
        bO.put(Integer.valueOf(OItem.aj.cg), new OTuple(Integer.valueOf(11), Integer.valueOf(14)));
        bO.put(Integer.valueOf(OItem.ac.cg), new OTuple(Integer.valueOf(5), Integer.valueOf(7)));
        bO.put(Integer.valueOf(OItem.Z.cg), new OTuple(Integer.valueOf(5), Integer.valueOf(7)));
        bO.put(Integer.valueOf(OItem.aa.cg), new OTuple(Integer.valueOf(11), Integer.valueOf(15)));
        bO.put(Integer.valueOf(OItem.ab.cg), new OTuple(Integer.valueOf(9), Integer.valueOf(11)));
        bO.put(Integer.valueOf(OItem.U.cg), new OTuple(Integer.valueOf(-4), Integer.valueOf(-2)));
        bO.put(Integer.valueOf(OItem.bf.cg), new OTuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bO.put(Integer.valueOf(OItem.j.cg), new OTuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bO.put(Integer.valueOf(OItem.bc.cg), new OTuple(Integer.valueOf(-10), Integer.valueOf(-7)));
        bO.put(Integer.valueOf(OBlock.P.cm), new OTuple(Integer.valueOf(-5), Integer.valueOf(-3)));
        bO.put(Integer.valueOf(OBlock.aq.cm), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bO.put(Integer.valueOf(OItem.W.cg), new OTuple(Integer.valueOf(4), Integer.valueOf(5)));
        bO.put(Integer.valueOf(OItem.Y.cg), new OTuple(Integer.valueOf(2), Integer.valueOf(4)));
        bO.put(Integer.valueOf(OItem.V.cg), new OTuple(Integer.valueOf(2), Integer.valueOf(4)));
        bO.put(Integer.valueOf(OItem.X.cg), new OTuple(Integer.valueOf(2), Integer.valueOf(4)));
        bO.put(Integer.valueOf(OItem.aA.cg), new OTuple(Integer.valueOf(6), Integer.valueOf(8)));
        bO.put(Integer.valueOf(OItem.bD.cg), new OTuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bO.put(Integer.valueOf(OItem.aC.cg), new OTuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bO.put(Integer.valueOf(OItem.aQ.cg), new OTuple(Integer.valueOf(10), Integer.valueOf(12)));
        bO.put(Integer.valueOf(OItem.aS.cg), new OTuple(Integer.valueOf(10), Integer.valueOf(12)));
        bO.put(Integer.valueOf(OBlock.bg.cm), new OTuple(Integer.valueOf(-3), Integer.valueOf(-1)));
        bO.put(Integer.valueOf(OItem.ar.cg), new OTuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bO.put(Integer.valueOf(OItem.bj.cg), new OTuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bO.put(Integer.valueOf(OItem.bl.cg), new OTuple(Integer.valueOf(-8), Integer.valueOf(-6)));
        bO.put(Integer.valueOf(OItem.bA.cg), new OTuple(Integer.valueOf(7), Integer.valueOf(11)));
        bO.put(Integer.valueOf(OItem.l.cg), new OTuple(Integer.valueOf(-12), Integer.valueOf(-8)));
    }
}
