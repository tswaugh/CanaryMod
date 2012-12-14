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

   public OEntityVillager(OWorld var1) {
      this(var1, 0);
   }

   public OEntityVillager(OWorld var1, int var2) {
      super(var1);
      this.e = 0;
      this.f = false;
      this.g = false;
      this.d = null;
      this.s(var2);
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
      if(--this.e <= 0) {
         this.p.C.a(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
         this.e = 70 + this.aa.nextInt(50);
         this.d = this.p.C.a(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v), 32);
         if(this.d == null) {
            this.aL();
         } else {
            OChunkCoordinates var1 = this.d.a();
            this.b(var1.a, var1.b, var1.c, (int)((float)this.d.b() * 0.6F));
            if(this.bL) {
               this.bL = false;
               this.d.b(5);
            }
         }
      }

      if(!this.p() && this.j > 0) {
         --this.j;
         if(this.j <= 0) {
            if(this.bI) {
               if(this.i.size() > 1) {
                  Iterator var3 = this.i.iterator();

                  while(var3.hasNext()) {
                     OMerchantRecipe var2 = (OMerchantRecipe)var3.next();
                     if(var2.g()) {
                        var2.a(this.aa.nextInt(6) + this.aa.nextInt(6) + 2);
                     }
                  }
               }

               this.t(1);
               this.bI = false;
               if(this.d != null && this.bK != null) {
                  this.p.a(this, (byte)14);
                  this.d.a(this.bK, 1);
               }
            }
            this.d(new OPotionEffect(OPotion.l.H, 200, 0));
         }
      }

      super.bm();
   }

   public boolean a(OEntityPlayer var1) {
      OItemStack var2 = var1.bI.g();
      if(((PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.ENTITY_RIGHTCLICKED, new Object[] {((OEntityPlayerMP) var1).getPlayer(), new Villager(this), var2 == null ? null : new Item(var2)})) == PluginLoader.HookResult.PREVENT_ACTION) {
          return false;
      }
      boolean var3 = var2 != null && var2.c == OItem.bC.cg;
      if(!var3 && this.S() && !this.p() && !this.h_()) {
         if(!this.p.J) {
            this.b_(var1);
            var1.a((OIMerchant)this);
         }

         return true;
      } else {
         return super.a(var1);
      }
   }

   protected void a() {
      super.a();
      this.ag.a(16, Integer.valueOf(0));
   }

   public int aT() {
      return 20;
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("Profession", this.m());
      var1.a("Riches", this.bJ);
      if(this.i != null) {
         var1.a("Offers", this.i.a());
      }
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      this.s(var1.e("Profession"));
      this.bJ = var1.e("Riches");
      if(var1.b("Offers")) {
         ONBTTagCompound var2 = var1.l("Offers");
         this.i = new OMerchantRecipeList(var2);
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

   public void s(int var1) {
      this.ag.b(16, Integer.valueOf(var1));
   }

   public int m() {
      return this.ag.c(16);
   }

   public boolean n() {
      return this.f;
   }

   public void f(boolean var1) {
      this.f = var1;
   }

   public void g(boolean var1) {
      this.g = var1;
   }

   public boolean o() {
      return this.g;
   }

   public void c(OEntityLiving var1) {
      super.c(var1);
      if(this.d != null && var1 != null) {
         this.d.a(var1);
         if(var1 instanceof OEntityPlayer) {
            byte var2 = -1;
            if(this.h_()) {
               var2 = -3;
            }

            this.d.a(((OEntityPlayer)var1).c_(), var2);
            if(this.S()) {
               this.p.a(this, (byte)13);
            }
         }
      }
   }

   public void a(ODamageSource var1) {
      if(this.d != null) {
         OEntity var2 = var1.g();
         if(var2 != null) {
            if(var2 instanceof OEntityPlayer) {
               this.d.a(((OEntityPlayer)var2).c_(), -2);
            } else if(var2 instanceof OIMob) {
               this.d.h();
            }
         } else if(var2 == null) {
            OEntityPlayer var3 = this.p.a(this, 16.0D);
            if(var3 != null) {
               this.d.h();
            }
         }
      }

      super.a(var1);
   }

   public void b_(OEntityPlayer var1) {
      this.h = var1;
   }

   public OEntityPlayer m_() {
      return this.h;
   }

   public boolean p() {
      return this.h != null;
   }

   public void a(OMerchantRecipe var1) {
      var1.f();
      if(var1.a((OMerchantRecipe)this.i.get(this.i.size() - 1))) {
         this.j = 40;
         this.bI = true;
         if(this.h != null) {
            this.bK = this.h.c_();
         } else {
            this.bK = null;
         }
      }

      if(var1.a().c == OItem.bH.cg) {
         this.bJ += var1.a().a;
      }
   }

   public OMerchantRecipeList b(OEntityPlayer var1) {
      if(this.i == null) {
         this.t(1);
      }

      return this.i;
   }

   private float j(float var1) {
      float var2 = var1 + this.bM;
      return var2 > 0.9F?0.9F - (var2 - 0.9F):var2;
   }

   private void t(int var1) {
      if(this.i != null) {
         this.bM = OMathHelper.c((float)this.i.size()) * 0.2F;
      } else {
         this.bM = 0.0F;
      }

      OMerchantRecipeList var2;
      var2 = new OMerchantRecipeList();
      label48:
      switch(this.m()) {
      case 0:
         a(var2, OItem.T.cg, this.aa, this.j(0.9F));
         a(var2, OBlock.ae.cm, this.aa, this.j(0.5F));
         a(var2, OItem.bk.cg, this.aa, this.j(0.5F));
         a(var2, OItem.aV.cg, this.aa, this.j(0.4F));
         b(var2, OItem.U.cg, this.aa, this.j(0.9F));
         b(var2, OItem.bf.cg, this.aa, this.j(0.3F));
         b(var2, OItem.j.cg, this.aa, this.j(0.3F));
         b(var2, OItem.bc.cg, this.aa, this.j(0.3F));
         b(var2, OItem.be.cg, this.aa, this.j(0.3F));
         b(var2, OItem.i.cg, this.aa, this.j(0.3F));
         b(var2, OItem.bl.cg, this.aa, this.j(0.3F));
         b(var2, OItem.l.cg, this.aa, this.j(0.5F));
         if(this.aa.nextFloat() < this.j(0.5F)) {
            var2.add(new OMerchantRecipe(new OItemStack(OBlock.I, 10), new OItemStack(OItem.bH), new OItemStack(OItem.ap.cg, 4 + this.aa.nextInt(2), 0)));
         }
         break;
      case 1:
         a(var2, OItem.aK.cg, this.aa, this.j(0.8F));
         a(var2, OItem.aL.cg, this.aa, this.j(0.8F));
         a(var2, OItem.bG.cg, this.aa, this.j(0.3F));
         b(var2, OBlock.aq.cm, this.aa, this.j(0.8F));
         b(var2, OBlock.P.cm, this.aa, this.j(0.2F));
         b(var2, OItem.aQ.cg, this.aa, this.j(0.2F));
         b(var2, OItem.aS.cg, this.aa, this.j(0.2F));
         break;
      case 2:
         b(var2, OItem.bA.cg, this.aa, this.j(0.3F));
         b(var2, OItem.bD.cg, this.aa, this.j(0.2F));
         b(var2, OItem.aC.cg, this.aa, this.j(0.4F));
         b(var2, OBlock.bg.cm, this.aa, this.j(0.3F));
         int[] var3 = new int[]{OItem.q.cg, OItem.z.cg, OItem.ae.cg, OItem.ai.cg, OItem.h.cg, OItem.C.cg, OItem.g.cg, OItem.B.cg};
         int[] var4 = var3;
         int var5 = var3.length;
         int var6 = 0;

         while(true) {
            if(var6 >= var5) {
               break label48;
            }

            int var7 = var4[var6];
            if(this.aa.nextFloat() < this.j(0.05F)) {
               var2.add(new OMerchantRecipe(new OItemStack(var7, 1, 0), new OItemStack(OItem.bH, 2 + this.aa.nextInt(3), 0), OEnchantmentHelper.a(this.aa, new OItemStack(var7, 1, 0), 5 + this.aa.nextInt(15))));
            }

            ++var6;
         }
      case 3:
         a(var2, OItem.m.cg, this.aa, this.j(0.7F));
         a(var2, OItem.o.cg, this.aa, this.j(0.5F));
         a(var2, OItem.p.cg, this.aa, this.j(0.5F));
         a(var2, OItem.n.cg, this.aa, this.j(0.5F));
         b(var2, OItem.q.cg, this.aa, this.j(0.5F));
         b(var2, OItem.z.cg, this.aa, this.j(0.5F));
         b(var2, OItem.h.cg, this.aa, this.j(0.3F));
         b(var2, OItem.C.cg, this.aa, this.j(0.3F));
         b(var2, OItem.g.cg, this.aa, this.j(0.5F));
         b(var2, OItem.B.cg, this.aa, this.j(0.5F));
         b(var2, OItem.f.cg, this.aa, this.j(0.2F));
         b(var2, OItem.A.cg, this.aa, this.j(0.2F));
         b(var2, OItem.P.cg, this.aa, this.j(0.2F));
         b(var2, OItem.Q.cg, this.aa, this.j(0.2F));
         b(var2, OItem.ag.cg, this.aa, this.j(0.2F));
         b(var2, OItem.ak.cg, this.aa, this.j(0.2F));
         b(var2, OItem.ad.cg, this.aa, this.j(0.2F));
         b(var2, OItem.ah.cg, this.aa, this.j(0.2F));
         b(var2, OItem.ae.cg, this.aa, this.j(0.2F));
         b(var2, OItem.ai.cg, this.aa, this.j(0.2F));
         b(var2, OItem.af.cg, this.aa, this.j(0.2F));
         b(var2, OItem.aj.cg, this.aa, this.j(0.2F));
         b(var2, OItem.ac.cg, this.aa, this.j(0.1F));
         b(var2, OItem.Z.cg, this.aa, this.j(0.1F));
         b(var2, OItem.aa.cg, this.aa, this.j(0.1F));
         b(var2, OItem.ab.cg, this.aa, this.j(0.1F));
         break;
      case 4:
         a(var2, OItem.m.cg, this.aa, this.j(0.7F));
         a(var2, OItem.aq.cg, this.aa, this.j(0.5F));
         a(var2, OItem.bi.cg, this.aa, this.j(0.5F));
         b(var2, OItem.aA.cg, this.aa, this.j(0.1F));
         b(var2, OItem.W.cg, this.aa, this.j(0.3F));
         b(var2, OItem.Y.cg, this.aa, this.j(0.3F));
         b(var2, OItem.V.cg, this.aa, this.j(0.3F));
         b(var2, OItem.X.cg, this.aa, this.j(0.3F));
         b(var2, OItem.ar.cg, this.aa, this.j(0.3F));
         b(var2, OItem.bj.cg, this.aa, this.j(0.3F));
      }

      if(var2.isEmpty()) {
         a(var2, OItem.p.cg, this.aa, 1.0F);
      }

      Collections.shuffle(var2);
      if(this.i == null) {
         this.i = new OMerchantRecipeList();
      }

      for(int var8 = 0; var8 < var1 && var8 < var2.size(); ++var8) {
          OMerchantRecipe recipe = (OMerchantRecipe) var2.get(var8);
          if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.VILLAGER_TRADE_UNLOCK, new Object[] {new Villager(this), new VillagerTrade(recipe)})) {
              this.i.a(recipe);
          }
      }
   }

   private static void a(OMerchantRecipeList var0, int var1, Random var2, float var3) {
      if(var2.nextFloat() < var3) {
         var0.add(new OMerchantRecipe(a(var1, var2), OItem.bH));
      }
   }

   private static OItemStack a(int var0, Random var1) {
      return new OItemStack(var0, b(var0, var1), 0);
   }

   private static int b(int var0, Random var1) {
      OTuple var2 = (OTuple)bN.get(Integer.valueOf(var0));
      return var2 == null?1:(((Integer)var2.a()).intValue() >= ((Integer)var2.b()).intValue()?((Integer)var2.a()).intValue():((Integer)var2.a()).intValue() + var1.nextInt(((Integer)var2.b()).intValue() - ((Integer)var2.a()).intValue()));
   }

   private static void b(OMerchantRecipeList var0, int var1, Random var2, float var3) {
      if(var2.nextFloat() < var3) {
         int var4 = c(var1, var2);
         OItemStack var5;
         OItemStack var6;
         if(var4 < 0) {
            var5 = new OItemStack(OItem.bH.cg, 1, 0);
            var6 = new OItemStack(var1, -var4, 0);
         } else {
            var5 = new OItemStack(OItem.bH.cg, var4, 0);
            var6 = new OItemStack(var1, 1, 0);
         }
         var0.add(new OMerchantRecipe(var5, var6));
      }
   }

   private static int c(int var0, Random var1) {
      OTuple var2 = (OTuple)bO.get(Integer.valueOf(var0));
      return var2 == null?1:(((Integer)var2.a()).intValue() >= ((Integer)var2.b()).intValue()?((Integer)var2.a()).intValue():((Integer)var2.a()).intValue() + var1.nextInt(((Integer)var2.b()).intValue() - ((Integer)var2.a()).intValue()));
   }

   public void bG() {
      this.s(this.p.u.nextInt(5));
   }

   public void q() {
      this.bL = true;
   }

   public OEntityVillager b(OEntityAgeable var1) {
      OEntityVillager var2 = new OEntityVillager(this.p);
      var2.bG();
      return var2;
   }

   public OEntityAgeable a(OEntityAgeable var1) {
      return this.b(var1);
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
