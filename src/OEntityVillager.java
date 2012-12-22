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
    private boolean bJ;
    private int bK;
    private String bL;
    private boolean bM;
    private float bN;
    private static final Map bO = new HashMap();
    private static final Map bP = new HashMap();

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
        this.aG = "/mob/villager/villager.png";
        this.bH = 0.5F;
        this.az().b(true);
        this.az().a(true);
        this.bn.a(0, new OEntityAISwimming(this));
        this.bn.a(1, new OEntityAIAvoidEntity(this, OEntityZombie.class, 8.0F, 0.3F, 0.35F));
        this.bn.a(1, new OEntityAITradePlayer(this));
        this.bn.a(1, new OEntityAILookAtTradePlayer(this));
        this.bn.a(2, new OEntityAIMoveIndoors(this));
        this.bn.a(3, new OEntityAIRestrictOpenDoor(this));
        this.bn.a(4, new OEntityAIOpenDoor(this, true));
        this.bn.a(5, new OEntityAIMoveTwardsRestriction(this, 0.3F));
        this.bn.a(6, new OEntityAIVillagerMate(this));
        this.bn.a(7, new OEntityAIFollowGolem(this));
        this.bn.a(8, new OEntityAIPlay(this, 0.32F));
        this.bn.a(9, new OEntityAIWatchClosest2(this, OEntityPlayer.class, 3.0F, 1.0F));
        this.bn.a(9, new OEntityAIWatchClosest2(this, OEntityVillager.class, 5.0F, 0.02F));
        this.bn.a(9, new OEntityAIWander(this, 0.3F));
        this.bn.a(10, new OEntityAIWatchClosest(this, OEntityLiving.class, 8.0F));
    }

    public boolean be() {
        return true;
    }

    protected void bm() {
        if (--this.e <= 0) {
            this.p.B.a(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
            this.e = 70 + this.aa.nextInt(50);
            this.d = this.p.B.a(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v), 32);
            if (this.d == null) {
                this.aL();
            } else {
                OChunkCoordinates ochunkcoordinates = this.d.a();

                this.b(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, (int) ((float) this.d.b() * 0.6F));
                if (this.bM) {
                    this.bM = false;
                    this.d.b(5);
                }
            }
        }

        if (!this.p() && this.j > 0) {
            --this.j;
            if (this.j <= 0) {
                if (this.bJ) {
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
                    this.bJ = false;
                    if (this.d != null && this.bL != null) {
                        this.p.a(this, (byte) 14);
                        this.d.a(this.bL, 1);
                    }
                }

                this.d(new OPotionEffect(OPotion.l.H, 200, 0));
            }
        }

        super.bm();
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bJ.g();
        if(((PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.ENTITY_RIGHTCLICKED, new Object[] {((OEntityPlayerMP) oentityplayer).getPlayer(), new Villager(this), oitemstack == null ? null : new Item(oitemstack)})) == PluginLoader.HookResult.PREVENT_ACTION) {
          return false;
        }
        boolean flag = oitemstack != null && oitemstack.c == OItem.bC.cj;

        if (!flag && this.S() && !this.p() && !this.h_()) {
            if (!this.p.I) {
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
        onbttagcompound.a("Riches", this.bK);
        if (this.i != null) {
            onbttagcompound.a("Offers", this.i.a());
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.s(onbttagcompound.e("Profession"));
        this.bK = onbttagcompound.e("Riches");
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
            this.bJ = true;
            if (this.h != null) {
                this.bL = this.h.c_();
            } else {
                this.bL = null;
            }
        }

        if (omerchantrecipe.a().c == OItem.bH.cj) {
            this.bK += omerchantrecipe.a().a;
        }
    }

    public OMerchantRecipeList b(OEntityPlayer oentityplayer) {
        if (this.i == null) {
            this.t(1);
        }

        return this.i;
    }

    private float j(float f) {
        float f1 = f + this.bN;

        return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
    }

    private void t(int i) {
        if (this.i != null) {
            this.bN = OMathHelper.c((float) this.i.size()) * 0.2F;
        } else {
            this.bN = 0.0F;
        }

        OMerchantRecipeList omerchantrecipelist;

        omerchantrecipelist = new OMerchantRecipeList();
        int j;

        label50:
        switch (this.m()) {
            case 0:
                a(omerchantrecipelist, OItem.T.cj, this.aa, this.j(0.9F));
                a(omerchantrecipelist, OBlock.ae.cm, this.aa, this.j(0.5F));
                a(omerchantrecipelist, OItem.bk.cj, this.aa, this.j(0.5F));
                a(omerchantrecipelist, OItem.aV.cj, this.aa, this.j(0.4F));
                b(omerchantrecipelist, OItem.U.cj, this.aa, this.j(0.9F));
                b(omerchantrecipelist, OItem.bf.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.j.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.bc.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.be.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.i.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.bl.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.l.cj, this.aa, this.j(0.5F));
                if (this.aa.nextFloat() < this.j(0.5F)) {
                    omerchantrecipelist.add(new OMerchantRecipe(new OItemStack(OBlock.I, 10), new OItemStack(OItem.bH), new OItemStack(OItem.ap.cj, 4 + this.aa.nextInt(2), 0)));
                }
                break;

            case 1:
                a(omerchantrecipelist, OItem.aK.cj, this.aa, this.j(0.8F));
                a(omerchantrecipelist, OItem.aL.cj, this.aa, this.j(0.8F));
                a(omerchantrecipelist, OItem.bG.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OBlock.aq.cm, this.aa, this.j(0.8F));
                b(omerchantrecipelist, OBlock.P.cm, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.aQ.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.aS.cj, this.aa, this.j(0.2F));
                if (this.aa.nextFloat() < this.j(0.07F)) {
                    OEnchantment oenchantment = OEnchantment.c[this.aa.nextInt(OEnchantment.c.length)];
                    int k = OMathHelper.a(this.aa, oenchantment.d(), oenchantment.b());
                    OItemStack oitemstack = OItem.bW.a(new OEnchantmentData(oenchantment, k));

                    j = 2 + this.aa.nextInt(5 + k * 10) + 3 * k;
                    omerchantrecipelist.add(new OMerchantRecipe(new OItemStack(OItem.aL), new OItemStack(OItem.bH, j), oitemstack));
                }
                break;

            case 2:
                b(omerchantrecipelist, OItem.bA.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.bD.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.aC.cj, this.aa, this.j(0.4F));
                b(omerchantrecipelist, OBlock.bg.cm, this.aa, this.j(0.3F));
                int[] aint = new int[] { OItem.q.cj, OItem.z.cj, OItem.ae.cj, OItem.ai.cj, OItem.h.cj, OItem.C.cj, OItem.g.cj, OItem.B.cj};
                int[] aint1 = aint;
                int l = aint.length;

                j = 0;

                while (true) {
                    if (j >= l) {
                        break label50;
                    }

                    int i1 = aint1[j];

                    if (this.aa.nextFloat() < this.j(0.05F)) {
                        omerchantrecipelist.add(new OMerchantRecipe(new OItemStack(i1, 1, 0), new OItemStack(OItem.bH, 2 + this.aa.nextInt(3), 0), OEnchantmentHelper.a(this.aa, new OItemStack(i1, 1, 0), 5 + this.aa.nextInt(15))));
                    }

                    ++j;
                }

            case 3:
                a(omerchantrecipelist, OItem.m.cj, this.aa, this.j(0.7F));
                a(omerchantrecipelist, OItem.o.cj, this.aa, this.j(0.5F));
                a(omerchantrecipelist, OItem.p.cj, this.aa, this.j(0.5F));
                a(omerchantrecipelist, OItem.n.cj, this.aa, this.j(0.5F));
                b(omerchantrecipelist, OItem.q.cj, this.aa, this.j(0.5F));
                b(omerchantrecipelist, OItem.z.cj, this.aa, this.j(0.5F));
                b(omerchantrecipelist, OItem.h.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.C.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.g.cj, this.aa, this.j(0.5F));
                b(omerchantrecipelist, OItem.B.cj, this.aa, this.j(0.5F));
                b(omerchantrecipelist, OItem.f.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.A.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.P.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.Q.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ag.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ak.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ad.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ah.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ae.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ai.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.af.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.aj.cj, this.aa, this.j(0.2F));
                b(omerchantrecipelist, OItem.ac.cj, this.aa, this.j(0.1F));
                b(omerchantrecipelist, OItem.Z.cj, this.aa, this.j(0.1F));
                b(omerchantrecipelist, OItem.aa.cj, this.aa, this.j(0.1F));
                b(omerchantrecipelist, OItem.ab.cj, this.aa, this.j(0.1F));
                break;

            case 4:
                a(omerchantrecipelist, OItem.m.cj, this.aa, this.j(0.7F));
                a(omerchantrecipelist, OItem.aq.cj, this.aa, this.j(0.5F));
                a(omerchantrecipelist, OItem.bi.cj, this.aa, this.j(0.5F));
                b(omerchantrecipelist, OItem.aA.cj, this.aa, this.j(0.1F));
                b(omerchantrecipelist, OItem.W.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.Y.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.V.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.X.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.ar.cj, this.aa, this.j(0.3F));
                b(omerchantrecipelist, OItem.bj.cj, this.aa, this.j(0.3F));
        }

        if (omerchantrecipelist.isEmpty()) {
            a(omerchantrecipelist, OItem.p.cj, this.aa, 1.0F);
        }

        Collections.shuffle(omerchantrecipelist);
        if (this.i == null) {
            this.i = new OMerchantRecipeList();
        }

        for (int j1 = 0; j1 < i && j1 < omerchantrecipelist.size(); ++j1) {
            //CanaryMod
            OMerchantRecipe recipe = (OMerchantRecipe) omerchantrecipelist.get(j1);
            if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.VILLAGER_TRADE_UNLOCK, new Object[] {new Villager(this), new VillagerTrade(recipe)})) {
                this.i.a((OMerchantRecipe) omerchantrecipelist.get(j1));
            }
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
        OTuple otuple = (OTuple) bO.get(Integer.valueOf(i));

        return otuple == null ? 1 : (((Integer) otuple.a()).intValue() >= ((Integer) otuple.b()).intValue() ? ((Integer) otuple.a()).intValue() : ((Integer) otuple.a()).intValue() + random.nextInt(((Integer) otuple.b()).intValue() - ((Integer) otuple.a()).intValue()));
    }

    private static void b(OMerchantRecipeList omerchantrecipelist, int i, Random random, float f) {
        if (random.nextFloat() < f) {
            int j = c(i, random);
            OItemStack oitemstack;
            OItemStack oitemstack1;

            if (j < 0) {
                oitemstack = new OItemStack(OItem.bH.cj, 1, 0);
                oitemstack1 = new OItemStack(i, -j, 0);
            } else {
                oitemstack = new OItemStack(OItem.bH.cj, j, 0);
                oitemstack1 = new OItemStack(i, 1, 0);
            }

            omerchantrecipelist.add(new OMerchantRecipe(oitemstack, oitemstack1));
        }
    }

    private static int c(int i, Random random) {
        OTuple otuple = (OTuple) bP.get(Integer.valueOf(i));

        return otuple == null ? 1 : (((Integer) otuple.a()).intValue() >= ((Integer) otuple.b()).intValue() ? ((Integer) otuple.a()).intValue() : ((Integer) otuple.a()).intValue() + random.nextInt(((Integer) otuple.b()).intValue() - ((Integer) otuple.a()).intValue()));
    }

    public void bG() {
        this.s(this.p.t.nextInt(5));
    }

    public void q() {
        this.bM = true;
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
        bO.put(Integer.valueOf(OItem.m.cj), new OTuple(Integer.valueOf(16), Integer.valueOf(24)));
        bO.put(Integer.valueOf(OItem.o.cj), new OTuple(Integer.valueOf(8), Integer.valueOf(10)));
        bO.put(Integer.valueOf(OItem.p.cj), new OTuple(Integer.valueOf(8), Integer.valueOf(10)));
        bO.put(Integer.valueOf(OItem.n.cj), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bO.put(Integer.valueOf(OItem.aK.cj), new OTuple(Integer.valueOf(24), Integer.valueOf(36)));
        bO.put(Integer.valueOf(OItem.aL.cj), new OTuple(Integer.valueOf(11), Integer.valueOf(13)));
        bO.put(Integer.valueOf(OItem.bG.cj), new OTuple(Integer.valueOf(1), Integer.valueOf(1)));
        bO.put(Integer.valueOf(OItem.bn.cj), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bO.put(Integer.valueOf(OItem.bA.cj), new OTuple(Integer.valueOf(2), Integer.valueOf(3)));
        bO.put(Integer.valueOf(OItem.aq.cj), new OTuple(Integer.valueOf(14), Integer.valueOf(18)));
        bO.put(Integer.valueOf(OItem.bi.cj), new OTuple(Integer.valueOf(14), Integer.valueOf(18)));
        bO.put(Integer.valueOf(OItem.bk.cj), new OTuple(Integer.valueOf(14), Integer.valueOf(18)));
        bO.put(Integer.valueOf(OItem.aV.cj), new OTuple(Integer.valueOf(9), Integer.valueOf(13)));
        bO.put(Integer.valueOf(OItem.S.cj), new OTuple(Integer.valueOf(34), Integer.valueOf(48)));
        bO.put(Integer.valueOf(OItem.bh.cj), new OTuple(Integer.valueOf(30), Integer.valueOf(38)));
        bO.put(Integer.valueOf(OItem.bg.cj), new OTuple(Integer.valueOf(30), Integer.valueOf(38)));
        bO.put(Integer.valueOf(OItem.T.cj), new OTuple(Integer.valueOf(18), Integer.valueOf(22)));
        bO.put(Integer.valueOf(OBlock.ae.cm), new OTuple(Integer.valueOf(14), Integer.valueOf(22)));
        bO.put(Integer.valueOf(OItem.bm.cj), new OTuple(Integer.valueOf(36), Integer.valueOf(64)));
        bP.put(Integer.valueOf(OItem.i.cj), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bP.put(Integer.valueOf(OItem.be.cj), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bP.put(Integer.valueOf(OItem.q.cj), new OTuple(Integer.valueOf(7), Integer.valueOf(11)));
        bP.put(Integer.valueOf(OItem.z.cj), new OTuple(Integer.valueOf(12), Integer.valueOf(14)));
        bP.put(Integer.valueOf(OItem.h.cj), new OTuple(Integer.valueOf(6), Integer.valueOf(8)));
        bP.put(Integer.valueOf(OItem.C.cj), new OTuple(Integer.valueOf(9), Integer.valueOf(12)));
        bP.put(Integer.valueOf(OItem.g.cj), new OTuple(Integer.valueOf(7), Integer.valueOf(9)));
        bP.put(Integer.valueOf(OItem.B.cj), new OTuple(Integer.valueOf(10), Integer.valueOf(12)));
        bP.put(Integer.valueOf(OItem.f.cj), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bP.put(Integer.valueOf(OItem.A.cj), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bP.put(Integer.valueOf(OItem.P.cj), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bP.put(Integer.valueOf(OItem.Q.cj), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bP.put(Integer.valueOf(OItem.ag.cj), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bP.put(Integer.valueOf(OItem.ak.cj), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bP.put(Integer.valueOf(OItem.ad.cj), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bP.put(Integer.valueOf(OItem.ah.cj), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bP.put(Integer.valueOf(OItem.ae.cj), new OTuple(Integer.valueOf(10), Integer.valueOf(14)));
        bP.put(Integer.valueOf(OItem.ai.cj), new OTuple(Integer.valueOf(16), Integer.valueOf(19)));
        bP.put(Integer.valueOf(OItem.af.cj), new OTuple(Integer.valueOf(8), Integer.valueOf(10)));
        bP.put(Integer.valueOf(OItem.aj.cj), new OTuple(Integer.valueOf(11), Integer.valueOf(14)));
        bP.put(Integer.valueOf(OItem.ac.cj), new OTuple(Integer.valueOf(5), Integer.valueOf(7)));
        bP.put(Integer.valueOf(OItem.Z.cj), new OTuple(Integer.valueOf(5), Integer.valueOf(7)));
        bP.put(Integer.valueOf(OItem.aa.cj), new OTuple(Integer.valueOf(11), Integer.valueOf(15)));
        bP.put(Integer.valueOf(OItem.ab.cj), new OTuple(Integer.valueOf(9), Integer.valueOf(11)));
        bP.put(Integer.valueOf(OItem.U.cj), new OTuple(Integer.valueOf(-4), Integer.valueOf(-2)));
        bP.put(Integer.valueOf(OItem.bf.cj), new OTuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bP.put(Integer.valueOf(OItem.j.cj), new OTuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bP.put(Integer.valueOf(OItem.bc.cj), new OTuple(Integer.valueOf(-10), Integer.valueOf(-7)));
        bP.put(Integer.valueOf(OBlock.P.cm), new OTuple(Integer.valueOf(-5), Integer.valueOf(-3)));
        bP.put(Integer.valueOf(OBlock.aq.cm), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bP.put(Integer.valueOf(OItem.W.cj), new OTuple(Integer.valueOf(4), Integer.valueOf(5)));
        bP.put(Integer.valueOf(OItem.Y.cj), new OTuple(Integer.valueOf(2), Integer.valueOf(4)));
        bP.put(Integer.valueOf(OItem.V.cj), new OTuple(Integer.valueOf(2), Integer.valueOf(4)));
        bP.put(Integer.valueOf(OItem.X.cj), new OTuple(Integer.valueOf(2), Integer.valueOf(4)));
        bP.put(Integer.valueOf(OItem.aA.cj), new OTuple(Integer.valueOf(6), Integer.valueOf(8)));
        bP.put(Integer.valueOf(OItem.bD.cj), new OTuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bP.put(Integer.valueOf(OItem.aC.cj), new OTuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bP.put(Integer.valueOf(OItem.aQ.cj), new OTuple(Integer.valueOf(10), Integer.valueOf(12)));
        bP.put(Integer.valueOf(OItem.aS.cj), new OTuple(Integer.valueOf(10), Integer.valueOf(12)));
        bP.put(Integer.valueOf(OBlock.bg.cm), new OTuple(Integer.valueOf(-3), Integer.valueOf(-1)));
        bP.put(Integer.valueOf(OItem.ar.cj), new OTuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bP.put(Integer.valueOf(OItem.bj.cj), new OTuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bP.put(Integer.valueOf(OItem.bl.cj), new OTuple(Integer.valueOf(-8), Integer.valueOf(-6)));
        bP.put(Integer.valueOf(OItem.bA.cj), new OTuple(Integer.valueOf(7), Integer.valueOf(11)));
        bP.put(Integer.valueOf(OItem.l.cj), new OTuple(Integer.valueOf(-12), Integer.valueOf(-8)));
    }
}
