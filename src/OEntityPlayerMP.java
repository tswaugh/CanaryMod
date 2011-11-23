import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class OEntityPlayerMP extends OEntityPlayer implements OICrafting {

   public ONetServerHandler a;
   public MinecraftServer b;
   public OItemInWorldManager c;
   public double d;
   public double e;
   public List f = new LinkedList();
   public Set g = new HashSet();
   private int cc = -99999999;
   private int cd = -99999999;
   private boolean ce = true;
   private int cf = -99999999;
   private int cg = 60;
   private OItemStack[] ch = new OItemStack[]{null, null, null, null, null};
   private int ci = 0;
   public boolean h;
   public int i;
   public boolean j = false;
   // CanaryMod: Player storage
   private Player player;

   public OEntityPlayerMP(MinecraftServer var1, OWorld var2, String var3, OItemInWorldManager var4) {
      super(var2);
      var4.b = this;
      this.c = var4;
      OChunkCoordinates var5 = var2.o();
      int var6 = var5.a;
      int var7 = var5.c;
      int var8 = var5.b;
      if(!var2.y.e) {
         var6 += this.aH.nextInt(20) - 10;
         var8 = var2.f(var6, var7);
         var7 += this.aH.nextInt(20) - 10;
      }

      this.c((double)var6 + 0.5D, (double)var8, (double)var7 + 0.5D, 0.0F, 0.0F);
      this.b = var1;
      this.aE = 0.0F;
      this.v = var3;
      this.au = 0.0F;
      
      // CanaryMod: So we don't conflict with runecraft
      c = new Digging((OWorldServer) var2, this);

      // CanaryMod: Store player
      player = etc.getDataSource().getPlayer(var3);
      player.setUser(this);
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      if(var1.c("playerGameType")) {
         this.c.a(var1.f("playerGameType"));
      }

   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      var1.a("playerGameType", this.c.a());
   }

   public void a(OWorld var1) {
      super.a(var1);
      // CanaryMod: New world, new manager.
      this.c = new Digging((OWorldServer) var1, this);
   }

   public void c_(int var1) {
      super.c_(var1);
      this.cf = -1;
   }

   public void k() {
      this.m.a((OICrafting)this);
   }

   public OItemStack[] l() {
      return this.ch;
   }

   protected void n_() {
      this.au = 0.0F;
   }

   public float n() {
      return 1.62F;
   }

   public void b() {
      this.c.c();
      --this.cg;
      this.m.a();

      for(int var1 = 0; var1 < 5; ++var1) {
         OItemStack var2 = this.b(var1);
         if(var2 != this.ch[var1]) {
            this.b.b(this.w).a(this, new OPacket5PlayerInventory(this.S, var1, var2));
            this.ch[var1] = var2;
         }
      }

   }

   public OItemStack b(int var1) {
      return var1 == 0?this.k.a():this.k.b[var1 - 1];
   }

   public void a(ODamageSource var1) {
	   if (etc.getInstance().deathMessages) {
		   this.b.h.a((OPacket)(new OPacket3Chat(var1.a((OEntityPlayer)this))));
	   }
	   this.k.h();
   }

   public boolean a(ODamageSource var1, int var2) {
      if(this.cg > 0) {
         return false;
      } else {
         if(!this.b.p && var1 instanceof OEntityDamageSource) {
            OEntity var3 = var1.a();
            if(var3 instanceof OEntityPlayer) {
               return false;
            }

            if(var3 instanceof OEntityArrow) {
               OEntityArrow var4 = (OEntityArrow)var3;
               if(var4.c instanceof OEntityPlayer) {
                  return false;
               }
            }
         }

         return super.a(var1, var2);
      }
   }

   protected boolean o() {
      return this.b.p;
   }

   public void c(int var1) {
      super.c(var1);
   }

   public void a(boolean var1) {
      super.b();

      for(int var2 = 0; var2 < this.k.d(); ++var2) {
         OItemStack var3 = this.k.c(var2);
         if(var3 != null && OItem.d[var3.c].a_() && this.a.b() <= 2) {
            OPacket var4 = ((OItemMapBase)OItem.d[var3.c]).d(var3, this.X, this);
            if(var4 != null) {
               this.a.b(var4);
            }
         }
      }

      if(var1 && !this.f.isEmpty()) {
         OChunkCoordIntPair var7 = (OChunkCoordIntPair)this.f.get(0);
         if(var7 != null) {
            boolean var9 = false;
            if(this.a.b() < 4) {
               var9 = true;
            }

            if(var9) {
               OWorldServer var11 = this.b.a(this.w);
               this.f.remove(var7);
               this.a.b((OPacket)(new OPacket51MapChunk(var7.a * 16, 0, var7.b * 16, 16, var11.c, 16, var11)));
               List var5 = var11.d(var7.a * 16, 0, var7.b * 16, var7.a * 16 + 16, var11.c, var7.b * 16 + 16);

               for(int var6 = 0; var6 < var5.size(); ++var6) {
                  this.a((OTileEntity)var5.get(var6));
               }
            }
         }
      }

      if(this.J) {
         if(this.b.d.a("allow-nether", true)) {
            if(this.m != this.l) {
               this.r();
            }

            if(this.W != null) {
               this.a(this.W);
            } else {
               this.K += 0.0125F;
               if(this.K >= 1.0F) {
                  this.K = 1.0F;
                  this.I = 10;
                  boolean var8 = false;
                  byte var10;
                  if(this.w == -1) {
                     var10 = 0;
                  } else {
                     var10 = -1;
                  }

                  // CanaryMod onPortalUse
                  if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PORTAL_USE, player, player.getWorld())) {
                      this.b.h.a(this, var10);
                      this.cf = -1;
                      this.cc = -1;
                      this.cd = -1;
                      this.a((OStatBase)OAchievementList.x);
                      player.refreshCreativeMode();
                  }
               }
            }

            this.J = false;
         }
      } else {
         if(this.K > 0.0F) {
            this.K -= 0.05F;
         }

         if(this.K < 0.0F) {
            this.K = 0.0F;
         }
      }

      if(this.I > 0) {
         --this.I;
      }
      
      // CanaryMod: Update the health
      if (this.bq != this.cc) {
          // updates your health when it is changed.
          if (!etc.getInstance().isHealthEnabled()) {
              bq = 20;
              bA = false;
          } else if ((Boolean) manager.callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), cc, bq))
              bq = cc;
      }
      
      // CanaryMod: Update the health or Hunger bar
      if(this.ar() != this.cc || this.cd != this.n.a() || this.n.c() == 0.0F != this.ce) {
          this.a.b((OPacket) (new OPacket8UpdateHealth(this.ar(), this.n.a(), this.n.c())));
          this.cc = this.ar();
          this.cd = this.n.a();
          this.ce = this.n.c() == 0.0F;
      }
      
      // CanaryMod: Update experience
      if(this.N != this.cf) {
         // updates your experience when it is changed.
         if (!etc.getInstance().isExpEnabled()) {
      	   N = 0;
      	   M = 0;
         } else if ((Boolean) manager.callHook(PluginLoader.Hook.EXPERIENCE_CHANGE, getPlayer(), cf, N))
      	   N = cf;
      }

      // CanaryMod: Update the experience bar
      if(this.N != this.cf) {
          this.cf = this.N;
          this.a.b((OPacket)(new OPacket43Experience(this.O, this.N, this.M)));
       }
   }

   public void d(int var1) {
      if(this.w == 1 && var1 == 1) {
         this.a((OStatBase)OAchievementList.C);
         this.X.e(this);
         this.j = true;
         this.a.b((OPacket)(new OPacket70Bed(4, 0)));
      } else {
         this.a((OStatBase)OAchievementList.B);
         OChunkCoordinates var2 = this.b.a(var1).d();
         if(var2 != null) {
            this.a.a((double)var2.a, (double)var2.b, (double)var2.c, 0.0F, 0.0F);
         }

         this.b.h.a(this, 1);
         this.cf = -1;
         this.cc = -1;
         this.cd = -1;
      }

   }

   private void a(OTileEntity var1) {
      if(var1 != null) {
          // CanaryMod: Let plugins know we're showing a sign
          if (var1 instanceof OTileEntitySign) {
              Sign sign = new Sign((OTileEntitySign) var1);
              manager.callHook(PluginLoader.Hook.SIGN_SHOW, getPlayer(), sign);
          }

         OPacket var2 = var1.b();
         if(var2 != null) {
            this.a.b(var2);
         }
      }

   }

   public void a(OEntity var1, int var2) {
      if(!var1.at) {
         OEntityTracker var3 = this.b.b(this.w);
         if(var1 instanceof OEntityItem) {
            var3.a(var1, new OPacket22Collect(var1.S, this.S));
         }

         if(var1 instanceof OEntityArrow) {
            var3.a(var1, new OPacket22Collect(var1.S, this.S));
         }

         if(var1 instanceof OEntityXPOrb) {
            var3.a(var1, new OPacket22Collect(var1.S, this.S));
         }
      }

      super.a(var1, var2);
      this.m.a();
   }

   public void o_() {
      if(!this.t) {
         this.u = -1;
         this.t = true;
         OEntityTracker var1 = this.b.b(this.w);
         var1.a(this, new OPacket18Animation(this, 1));
      }

   }

   public void q() {}

   public OEnumStatus a(int var1, int var2, int var3) {
      OEnumStatus var4 = super.a(var1, var2, var3);
      if(var4 == OEnumStatus.a) {
         OEntityTracker var5 = this.b.b(this.w);
         OPacket17Sleep var6 = new OPacket17Sleep(this, 0, var1, var2, var3);
         var5.a(this, var6);
         this.a.a(this.ab, this.ac, this.ad, this.ah, this.ai);
         this.a.b((OPacket)var6);
      }

      return var4;
   }

   public void a(boolean var1, boolean var2, boolean var3) {
      if(this.L()) {
         OEntityTracker var4 = this.b.b(this.w);
         var4.b(this, new OPacket18Animation(this, 3));
      }

      super.a(var1, var2, var3);
      if(this.a != null) {
         this.a.a(this.ab, this.ac, this.ad, this.ah, this.ai);
      }

   }

   public void a(OEntity var1) {
      super.a(var1);
      this.a.b((OPacket)(new OPacket39AttachEntity(this, this.W)));
      this.a.a(this.ab, this.ac, this.ad, this.ah, this.ai);
   }

   protected void a(double var1, boolean var3) {}

   public void b(double var1, boolean var3) {
      super.a(var1, var3);
   }

   private void an() {
      this.ci = this.ci % 100 + 1;
   }

   public void b(int var1, int var2, int var3) {
      this.an();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.ci, 1, "Crafting", 9)));
      this.m = new OContainerWorkbench(this.k, this.X, var1, var2, var3);
      this.m.f = this.ci;
      this.m.a((OICrafting)this);
   }

   public void c(int var1, int var2, int var3) {
      this.an();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.ci, 4, "Enchanting", 9)));
      this.m = new OContainerEnchantment(this.k, this.X, var1, var2, var3);
      this.m.f = this.ci;
      this.m.a((OICrafting)this);
   }

   public void a(OIInventory var1) {
	   // CanaryMod: Check if we can open this
       Inventory inv = null;
       String name = var1.e();
       if (var1 instanceof OTileEntityChest) {
           inv = new Chest((OTileEntityChest) var1);
           if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
               return;
       } else if (var1 instanceof OInventoryLargeChest) {
           inv = new DoubleChest((OInventoryLargeChest) var1);
           if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
               return;
       }

       if (inv != null)
           name = inv.getName();
       
      this.an();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.ci, 0, var1.e(), var1.d())));
      this.m = new OContainerChest(this.k, var1);
      this.m.f = this.ci;
      this.m.a((OICrafting)this);
   }

   public void a(OTileEntityFurnace var1) {
      this.an();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.ci, 2, var1.e(), var1.d())));
      this.m = new OContainerFurnace(this.k, var1);
      this.m.f = this.ci;
      this.m.a((OICrafting)this);
   }

   public void a(OTileEntityDispenser var1) {
      this.an();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.ci, 3, var1.e(), var1.d())));
      this.m = new OContainerDispenser(this.k, var1);
      this.m.f = this.ci;
      this.m.a((OICrafting)this);
   }

   public void a(OTileEntityBrewingStand var1) {
      this.an();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.ci, 5, var1.e(), var1.d())));
      this.m = new OContainerBrewingStand(this.k, var1);
      this.m.f = this.ci;
      this.m.a((OICrafting)this);
   }

   public void a(OContainer var1, int var2, OItemStack var3) {
      if(!(var1.b(var2) instanceof OSlotCrafting)) {
         if(!this.h) {
            this.a.b((OPacket)(new OPacket103SetSlot(var1.f, var2, var3)));
         }
      }
   }

   public void a(OContainer var1) {
      this.a(var1, var1.b());
   }

   public void a(OContainer var1, List var2) {
      this.a.b((OPacket)(new OPacket104WindowItems(var1.f, var2)));
      this.a.b((OPacket)(new OPacket103SetSlot(-1, -1, this.k.j())));
   }

   public void a(OContainer var1, int var2, int var3) {
      this.a.b((OPacket)(new OPacket105UpdateProgressbar(var1.f, var2, var3)));
   }

   public void a(OItemStack var1) {}

   public void r() {
      this.a.b((OPacket)(new OPacket101CloseWindow(this.m.f)));
      this.t();
   }

   public void s() {
      if(!this.h) {
         this.a.b((OPacket)(new OPacket103SetSlot(-1, -1, this.k.j())));
      }
   }

   public void t() {
      this.m.b(this);
      this.m = this.l;
   }

   public void a(float var1, float var2, boolean var3, boolean var4, float var5, float var6) {
      this.bV = var1;
      this.bW = var2;
      this.bY = var3;
      this.c(var4);
      this.ai = var5;
      this.ah = var6;
   }

   public void a(OStatBase var1, int var2) {
      if(var1 != null) {
         if(!var1.g) {
            while(var2 > 100) {
               this.a.b((OPacket)(new OPacket200Statistic(var1.e, 100)));
               var2 -= 100;
            }

            this.a.b((OPacket)(new OPacket200Statistic(var1.e, var2)));
         }

      }
   }

   public void u() {
      if(this.W != null) {
         this.a(this.W);
      }

      if(this.V != null) {
         this.V.a((OEntity)this);
      }

      if(this.E) {
         this.a(true, false, false);
      }

   }

   public void v() {
      this.cc = -99999999;
   }

   public void a(String var1) {
      OStringTranslate var2 = OStringTranslate.a();
      String var3 = var2.a(var1);
      this.a.b((OPacket)(new OPacket3Chat(var3)));
   }

   protected void w() {
      this.a.b((OPacket)(new OPacket38EntityStatus(this.S, (byte)9)));
      super.w();
   }

   public void a(OItemStack var1, int var2) {
	   super.a(var1, var2);
       // CanaryMod: Call EAT Hook
       if (var1 != null && var1.a() != null && var1.a().b(var1) == OEnumAction.b) {
           if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.EAT, ((OEntityPlayerMP) this).getPlayer(), new Item(var1))){
                   super.a(var1, var2);
                   OEntityTracker var3 = this.b.b(this.w);
                   var3.b(this, new OPacket18Animation(this, 5));
           } else {
               this.a.b((OPacket) (new OPacket38EntityStatus(this.S, (byte) 9)));
               this.getPlayer().updateLevels();
               this.getPlayer().updateInventory();
           } 
       } else { // CanaryMod: Bow, or block action
           super.a(var1, var2);
       }
   }

   protected void a(OPotionEffect var1) {
      super.a(var1);
      this.a.b((OPacket)(new OPacket41EntityEffect(this.S, var1)));
   }

   protected void b(OPotionEffect var1) {
      super.b(var1);
      this.a.b((OPacket)(new OPacket41EntityEffect(this.S, var1)));
   }

   protected void c(OPotionEffect var1) {
      super.c(var1);
      this.a.b((OPacket)(new OPacket42RemoveEntityEffect(this.S, var1)));
   }

   public void c(double var1, double var3, double var5) {
      this.a.a(var1, var3, var5, this.ah, this.ai);
   }

   public void b(OEntity var1) {
      OEntityTracker var2 = this.b.b(this.w);
      var2.b(this, new OPacket18Animation(var1, 6));
   }

   public void a_(OEntity var1) {
      OEntityTracker var2 = this.b.b(this.w);
      var2.b(this, new OPacket18Animation(var1, 7));
   }
   
   public Player getPlayer() {
       return player;
   }

   /**
    * Reloads the player
    */
   public void reloadPlayer() {
       player = etc.getDataSource().getPlayer(v);
       player.setUser(this);
   }
}
