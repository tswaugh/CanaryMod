import java.util.Iterator;
import java.util.List;

public abstract class OEntityPlayer extends OEntityLiving {

   public OInventoryPlayer k = new OInventoryPlayer(this);
   public OContainer l;
   public OContainer m;
   protected OFoodStats n = new OFoodStats();
   protected int o = 0;
   public byte p = 0;
   public int q = 0;
   public float r;
   public float s;
   public boolean t = false;
   public int u = 0;
   public String v;
   public int w;
   public int x = 0;
   public double y;
   public double z;
   public double A;
   public double B;
   public double C;
   public double D;
   protected boolean E;
   public OChunkCoordinates F;
   private int a;
   public float G;
   public float H;
   private OChunkCoordinates b;
   private OChunkCoordinates c;
   public int I = 20;
   protected boolean J = false;
   public float K;
   public OPlayerCapabilities L = new OPlayerCapabilities();
   public int M;
   public int N;
   public float O;
   private OItemStack d;
   private int e;
   protected float P = 0.1F;
   protected float Q = 0.02F;
   public OEntityFish R = null;
   // CanaryMod start
   @SuppressWarnings("FieldNameHidesFieldInSuperclass")
   HumanEntity entity = new HumanEntity(this);

   // CanaryMod end

   public OEntityPlayer(OWorld var1) {
      super(var1);
      this.l = new OContainerPlayer(this.k, !var1.I);
      this.m = this.l;
      this.au = 1.62F;
      OChunkCoordinates var2 = var1.o();
      this.c((double)var2.a + 0.5D, (double)(var2.b + 1), (double)var2.c + 0.5D, 0.0F, 0.0F);
      this.bh = "humanoid";
      this.bg = 180.0F;
      this.aJ = 20;
      this.be = "/mob/char.png";
   }

   public int e() {
      return 20;
   }

   protected void a() {
      super.a();
      this.aN.a(16, Byte.valueOf((byte)0));
      this.aN.a(17, Byte.valueOf((byte)0));
   }

   public boolean x() {
      return this.d != null;
   }

   public void p_() {
      if(this.d != null) {
         this.d.a(this.X, this, this.e);
      }

      this.q_();
   }

   public void q_() {
      this.d = null;
      this.e = 0;
      if(!this.X.I) {
         this.e(false);
      }

   }

   public boolean A() {
      return this.x() && OItem.d[this.d.c].b(this.d) == OEnumAction.d;
   }

   public void b() {
      if(this.d != null) {
         OItemStack var1 = this.k.a();
         if(var1 != this.d) {
            this.q_();
         } else {
            if(this.e <= 25 && this.e % 4 == 0) {
               this.b(var1, 5);
            }

            if(--this.e == 0 && !this.X.I) {
               this.w();
            }
         }
      }

      if(this.x > 0) {
         --this.x;
      }

      if(this.L()) {
         ++this.a;
         if(this.a > 100) {
            this.a = 100;
         }

         if(!this.X.I) {
            if(!this.v()) {
               this.a(true, true, false);
            } else if(this.X.e()) {
               this.a(false, true, true);
            }
         }
      } else if(this.a > 0) {
         ++this.a;
         if(this.a >= 110) {
            this.a = 0;
         }
      }

      super.b();
      if(!this.X.I && this.m != null && !this.m.a(this)) {
         this.r();
         this.m = this.l;
      }

      if(this.L.b) {
         for(int var10 = 0; var10 < 8; ++var10) {
            ;
         }
      }

      if(this.ai() && this.L.a) {
         this.Y();
      }

      this.y = this.B;
      this.z = this.C;
      this.A = this.D;
      double var2 = this.ab - this.B;
      double var4 = this.ac - this.C;
      double var6 = this.ad - this.D;
      double var8 = 10.0D;
      if(var2 > var8) {
         this.y = this.B = this.ab;
      }

      if(var6 > var8) {
         this.A = this.D = this.ad;
      }

      if(var4 > var8) {
         this.z = this.C = this.ac;
      }

      if(var2 < -var8) {
         this.y = this.B = this.ab;
      }

      if(var6 < -var8) {
         this.A = this.D = this.ad;
      }

      if(var4 < -var8) {
         this.z = this.C = this.ac;
      }

      this.B += var2 * 0.25D;
      this.D += var6 * 0.25D;
      this.C += var4 * 0.25D;
      this.a(OStatList.k, 1);
      if(this.W == null) {
         this.c = null;
      }

      if(!this.X.I) {
         this.n.a(this);
      }

   }

   protected void b(OItemStack var1, int var2) {
      if(var1.m() == OEnumAction.c) {
         this.X.a(this, "random.drink", 0.5F, this.X.w.nextFloat() * 0.1F + 0.9F);
      }

      if(var1.m() == OEnumAction.b) {
         for(int var3 = 0; var3 < var2; ++var3) {
            OVec3D var4 = OVec3D.b(((double)this.aH.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
            var4.a(-this.ai * 3.1415927F / 180.0F);
            var4.b(-this.ah * 3.1415927F / 180.0F);
            OVec3D var5 = OVec3D.b(((double)this.aH.nextFloat() - 0.5D) * 0.3D, (double)(-this.aH.nextFloat()) * 0.6D - 0.3D, 0.6D);
            var5.a(-this.ai * 3.1415927F / 180.0F);
            var5.b(-this.ah * 3.1415927F / 180.0F);
            var5 = var5.c(this.ab, this.ac + (double)this.n(), this.ad);
            this.X.a("iconcrack_" + var1.a().bM, var5.a, var5.b, var5.c, var4.a, var4.b + 0.05D, var4.c);
         }

         this.X.a(this, "random.eat", 0.5F + 0.5F * (float)this.aH.nextInt(2), (this.aH.nextFloat() - this.aH.nextFloat()) * 0.2F + 1.0F);
      }

   }

   protected void w() {
      if(this.d != null) {
         this.b(this.d, 16);
         int var1 = this.d.a;
         OItemStack var2 = this.d.b(this.X, this);
         if(var2 != this.d || var2 != null && var2.a != var1) {
            this.k.a[this.k.c] = var2;
            if(var2.a == 0) {
               this.k.a[this.k.c] = null;
            }
         }

         this.q_();
      }

   }

   protected boolean B() {
      return this.ar() <= 0 || this.L();
   }

   protected void r() {
      this.m = this.l;
   }

   public void C() {
      double var1 = this.ab;
      double var3 = this.ac;
      double var5 = this.ad;
      super.C();
      this.r = this.s;
      this.s = 0.0F;
      this.j(this.ab - var1, this.ac - var3, this.ad - var5);
   }

   private int u() {
      return this.a(OPotion.e)?6 - (1 + this.b(OPotion.e).c()) * 1:(this.a(OPotion.f)?6 + (1 + this.b(OPotion.f).c()) * 2:6);
   }

   protected void D() {
      int var1 = this.u();
      if(this.t) {
         ++this.u;
         if(this.u >= var1) {
            this.u = 0;
            this.t = false;
         }
      } else {
         this.u = 0;
      }

      this.bp = (float)this.u / (float)var1;
   }

   public void f() {
       // CanaryMod: adjust 'healing over time' independent of monster-spawn=true/false (nice notchup!)
       PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();
       if (this.X.q == 0 && autoHeal == PluginLoader.HookResult.DEFAULT_ACTION || autoHeal == PluginLoader.HookResult.ALLOW_ACTION) {
           if (this.bE < 20 && this.bM % 20 * 12 == 0)
               this.b(1);
       }

      if(this.o > 0) {
         --this.o;
      }

      if(this.X.v == 0 && this.ar() < this.e() && this.aI % 20 * 12 == 0) {
         this.c(1);
      }

      this.k.c();
      this.r = this.s;
      super.f();
      this.bm = this.P;
      this.bn = this.Q;
      if(this.ak()) {
         this.bm = (float)((double)this.bm + (double)this.P * 0.3D);
         this.bn = (float)((double)this.bn + (double)this.Q * 0.3D);
      }

      float var1 = OMathHelper.a(this.ae * this.ae + this.ag * this.ag);
      float var2 = (float)Math.atan(-this.af * 0.20000000298023224D) * 15.0F;
      if(var1 > 0.1F) {
         var1 = 0.1F;
      }

      if(!this.am || this.ar() <= 0) {
         var1 = 0.0F;
      }

      if(this.am || this.ar() <= 0) {
         var2 = 0.0F;
      }

      this.s += (var1 - this.s) * 0.4F;
      this.bz += (var2 - this.bz) * 0.8F;
      if(this.ar() > 0) {
         List var3 = this.X.b((OEntity)this, this.al.b(1.0D, 0.0D, 1.0D));
         if(var3 != null) {
            for(int var4 = 0; var4 < var3.size(); ++var4) {
               OEntity var5 = (OEntity)var3.get(var4);
               if(!var5.at) {
                  this.f(var5);
               }
            }
         }
      }

   }

   private void f(OEntity var1) {
      var1.a(this);
   }

   public void a(ODamageSource var1) {
      super.a(var1);
      this.b(0.2F, 0.2F);
      this.e(this.ab, this.ac, this.ad);
      this.af = 0.10000000149011612D;
      if(this.v.equals("Notch")) {
         this.a(new OItemStack(OItem.i, 1), true);
      }

      this.k.h();
      if(var1 != null) {
         this.ae = (double)(-OMathHelper.b((this.bv + this.ah) * 3.1415927F / 180.0F) * 0.1F);
         this.ag = (double)(-OMathHelper.a((this.bv + this.ah) * 3.1415927F / 180.0F) * 0.1F);
      } else {
         this.ae = this.ag = 0.0D;
      }

      this.au = 0.1F;
      this.a(OStatList.y, 1);
   }

   public void b(OEntity var1, int var2) {
      this.q += var2;
      if(var1 instanceof OEntityPlayer) {
         this.a(OStatList.A, 1);
      } else {
         this.a(OStatList.z, 1);
      }

   }

   protected int e(int var1) {
      int var2 = OEnchantmentHelper.a(this.k);
      return var2 > 0 && this.aH.nextInt(var2 + 1) > 0?var1:super.e(var1);
   }

   public void E() {
      this.a(this.k.a(this.k.c, 1), false);
   }

   public void b(OItemStack var1) {
      this.a(var1, false);
   }

   public void a(OItemStack var1, boolean var2) {
      if(var1 != null) {
         OEntityItem var3 = new OEntityItem(this.X, this.ab, this.ac - 0.30000001192092896D + (double)this.n(), this.ad, var1);
         var3.c = 40;
         float var4 = 0.1F;
         float var5;
         if(var2) {
            var5 = this.aH.nextFloat() * 0.5F;
            float var6 = this.aH.nextFloat() * 3.1415927F * 2.0F;
            var3.ae = (double)(-OMathHelper.a(var6) * var5);
            var3.ag = (double)(OMathHelper.b(var6) * var5);
            var3.af = 0.20000000298023224D;
         } else {
            var4 = 0.3F;
            var3.ae = (double)(-OMathHelper.a(this.ah / 180.0F * 3.1415927F) * OMathHelper.b(this.ai / 180.0F * 3.1415927F) * var4);
            var3.ag = (double)(OMathHelper.b(this.ah / 180.0F * 3.1415927F) * OMathHelper.b(this.ai / 180.0F * 3.1415927F) * var4);
            var3.af = (double)(-OMathHelper.a(this.ai / 180.0F * 3.1415927F) * var4 + 0.1F);
            var4 = 0.02F;
            var5 = this.aH.nextFloat() * 3.1415927F * 2.0F;
            var4 *= this.aH.nextFloat();
            var3.ae += Math.cos((double)var5) * (double)var4;
            var3.af += (double)((this.aH.nextFloat() - this.aH.nextFloat()) * 0.1F);
            var3.ag += Math.sin((double)var5) * (double)var4;
         }
         if (!(Boolean) manager.callHook(PluginLoader.Hook.ITEM_DROP, ((OEntityPlayerMP) this).getPlayer(), var3.item)) {
             Item droppedItem = var3.item.getItem();
             if (droppedItem.getAmount() < 0) {
                 droppedItem.setAmount(1);
                 droppedItem.update();
             }
             this.a(var3);
             this.a(OStatList.v, 1);
             // return the item to the inventory.
         } else
             k.a(var1);
      }
   }

   protected void a(OEntityItem var1) {
      this.X.b((OEntity)var1);
   }

   public float a(OBlock var1) {
      float var2 = this.k.a(var1);
      float var3 = var2;
      int var4 = OEnchantmentHelper.b(this.k);
      if(var4 > 0 && this.k.b(var1)) {
         var3 = var2 + (float)(var4 * var4 + 1);
      }

      if(this.a(OPotion.e)) {
         var3 *= 1.0F + (float)(this.b(OPotion.e).c() + 1) * 0.2F;
      }

      if(this.a(OPotion.f)) {
         var3 *= 1.0F - (float)(this.b(OPotion.f).c() + 1) * 0.2F;
      }

      if(this.a(OMaterial.g) && !OEnchantmentHelper.g(this.k)) {
         var3 /= 5.0F;
      }

      if(!this.am) {
         var3 /= 5.0F;
      }

      return var3;
   }

   public boolean b(OBlock var1) {
      return this.k.b(var1);
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      ONBTTagList var2 = var1.m("Inventory");
      this.k.b(var2);
      this.w = var1.f("Dimension");
      this.E = var1.n("Sleeping");
      this.a = var1.e("SleepTimer");
      this.O = var1.h("XpP");
      this.M = var1.f("XpLevel");
      this.N = var1.f("XpTotal");
      if(this.E) {
         this.F = new OChunkCoordinates(OMathHelper.b(this.ab), OMathHelper.b(this.ac), OMathHelper.b(this.ad));
         this.a(true, true, false);
      }

      if(var1.c("SpawnX") && var1.c("SpawnY") && var1.c("SpawnZ")) {
         this.b = new OChunkCoordinates(var1.f("SpawnX"), var1.f("SpawnY"), var1.f("SpawnZ"));
      }

      this.n.a(var1);
      this.L.b(var1);
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      var1.a("Inventory", (ONBTBase)this.k.a(new ONBTTagList()));
      var1.a("Dimension", this.w);
      var1.a("Sleeping", this.E);
      var1.a("SleepTimer", (short)this.a);
      var1.a("XpP", this.O);
      var1.a("XpLevel", this.M);
      var1.a("XpTotal", this.N);
      if(this.b != null) {
         var1.a("SpawnX", this.b.a);
         var1.a("SpawnY", this.b.b);
         var1.a("SpawnZ", this.b.c);
      }

      this.n.b(var1);
      this.L.a(var1);
   }

   public void a(OIInventory var1) {}

   public void c(int var1, int var2, int var3) {}

   public void b(int var1, int var2, int var3) {}

   public void a(OEntity var1, int var2) {}

   public float n() {
      return 0.12F;
   }

   protected void n_() {
      this.au = 1.62F;
   }

   public boolean a(ODamageSource var1, int var2) {
      if(this.L.a && !var1.f()) {
         return false;
      } else {
         this.bU = 0;
         if(this.ar() <= 0) {
            return false;
         } else {
            if(this.L() && !this.X.I) {
               this.a(true, true, false);
            }

            OEntity var3 = var1.a();
            if(var3 instanceof OEntityMob || var3 instanceof OEntityArrow) {
               if(this.X.v == 0) {
                  var2 = 0;
               }

               if(this.X.v == 1) {
                  var2 = var2 / 2 + 1;
               }

               if(this.X.v == 3) {
                  var2 = var2 * 3 / 2;
               }
            }

            if(var2 == 0) {
               return false;
            } else {
               OEntity var4 = var3;
               if(var3 instanceof OEntityArrow && ((OEntityArrow)var3).c != null) {
                  var4 = ((OEntityArrow)var3).c;
               }

               if(var4 instanceof OEntityLiving) {
                  this.a((OEntityLiving)var4, false);
               }

               this.a(OStatList.x, var2);
               return super.a(var1, var2);
            }
         }
      }
   }

   protected int b(ODamageSource var1, int var2) {
      int var3 = super.b(var1, var2);
      if(var3 <= 0) {
         return 0;
      } else {
         int var4 = OEnchantmentHelper.a(this.k, var1);
         if(var4 > 20) {
            var4 = 20;
         }

         if(var4 > 0 && var4 <= 20) {
            int var5 = 25 - var4;
            int var6 = var3 * var5 + this.bs;
            var3 = var6 / 25;
            this.bs = var6 % 25;
         }

         return var3;
      }
   }

   protected boolean o() {
      return false;
   }

   protected void a(OEntityLiving var1, boolean var2) {
      if(!(var1 instanceof OEntityCreeper) && !(var1 instanceof OEntityGhast)) {
         if(var1 instanceof OEntityWolf) {
            OEntityWolf var3 = (OEntityWolf)var1;
            if(var3.Q() && this.v.equals(var3.H())) {
               return;
            }
         }

         if(!(var1 instanceof OEntityPlayer) || this.o()) {
            List var7 = this.X.a(OEntityWolf.class, OAxisAlignedBB.b(this.ab, this.ac, this.ad, this.ab + 1.0D, this.ac + 1.0D, this.ad + 1.0D).b(16.0D, 4.0D, 16.0D));
            Iterator var4 = var7.iterator();

            while(var4.hasNext()) {
               OEntity var5 = (OEntity)var4.next();
               OEntityWolf var6 = (OEntityWolf)var5;
               if(var6.Q() && var6.x() == null && this.v.equals(var6.H()) && (!var2 || !var6.M())) {
                  var6.b(false);
                  var6.e(var1);
               }
            }

         }
      }
   }

   protected void f(int var1) {
      this.k.d(var1);
   }

   protected int F() {
      return this.k.g();
   }

   protected void c(ODamageSource var1, int var2) {
      if(!var1.d() && this.A()) {
         var2 = 1 + var2 >> 1;
      }

      var2 = this.e(var1, var2);
      var2 = this.b(var1, var2);
      this.c(var1.e());
      super.c(var1, var2);
   }

   public void a(OTileEntityFurnace var1) {}

   public void a(OTileEntityDispenser var1) {}

   public void a(OTileEntitySign var1) {}

   public void a(OTileEntityBrewingStand var1) {}

   public void d(OEntity var1) {
       // CanaryMod hook: ENTITY_RIGHCLICKED
       OItemStack itemInHand = this.G();
       PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.ENTITY_RIGHTCLICKED, ((OEntityPlayerMP) this).getPlayer(), var1.entity, (itemInHand == null) ? null : new Item(itemInHand));
       if (res != PluginLoader.HookResult.PREVENT_ACTION) {
           // Normally when interact action is not defined on the interacted entity, false is returned, and the item stack is not used.
           // For example sheep can interact by shearing and cows by milking, and the item stack changes from this interaction if it returns true.
           // Players on the other hand won't interact normally, but if we want to update the item stack anyways, we will ALLOW the action.
           if (!var1.b(this) || res == PluginLoader.HookResult.ALLOW_ACTION) {
               OItemStack var2 = itemInHand;
               if (var2 != null && var1 instanceof OEntityLiving) {
                   var2.a((OEntityLiving) var1);
                   if (var2.a <= 0) {
                       var2.a(this);
                       this.H();
                   }
               }
           }
       }
   }

   public OItemStack G() {
      return this.k.a();
   }

   public void H() {
      this.k.a(this.k.c, (OItemStack)null);
   }

   public double I() {
      return (double)(this.au - 0.5F);
   }

   public void o_() {
      if(!this.t || this.u >= this.u() / 2 || this.u < 0) {
         this.u = -1;
         this.t = true;
      }

   }

   public void e(OEntity var1) {
      int var2 = this.k.a(var1);
      if(this.a(OPotion.g)) {
         var2 += 3 << this.b(OPotion.g).c();
      }

      if(this.a(OPotion.t)) {
         var2 -= 2 << this.b(OPotion.t).c();
      }

      int var3 = 0;
      int var4 = 0;
      if(var1 instanceof OEntityLiving) {
         var4 = OEnchantmentHelper.a(this.k, (OEntityLiving)var1);
         var3 += OEnchantmentHelper.b(this.k, (OEntityLiving)var1);
      }

      if(this.ak()) {
         ++var3;
      }

      if(var2 > 0 || var4 > 0) {
         boolean var5 = this.az > 0.0F && !this.am && !this.t_() && !this.l_() && !this.a(OPotion.q) && this.W == null && var1 instanceof OEntityLiving;
         if(var5) {
            var2 += this.aH.nextInt(var2 / 2 + 2);
         }

         var2 += var4;
         boolean var6 = var1.a(ODamageSource.b(this), var2);
         if(var6) {
            if(var3 > 0) {
               var1.b((double)(-OMathHelper.a(this.ah * 3.1415927F / 180.0F) * (float)var3 * 0.5F), 0.1D, (double)(OMathHelper.b(this.ah * 3.1415927F / 180.0F) * (float)var3 * 0.5F));
               this.ae *= 0.6D;
               this.ag *= 0.6D;
               this.d(false);
            }

            if(var5) {
               this.b(var1);
            }

            if(var4 > 0) {
               this.a_(var1);
            }

            if(var2 >= 18) {
               this.a((OStatBase)OAchievementList.E);
            }
         }

         OItemStack var7 = this.G();
         if(var7 != null && var1 instanceof OEntityLiving) {
            var7.a((OEntityLiving)var1, this);
            if(var7.a <= 0) {
               var7.a(this);
               this.H();
            }
         }

         if(var1 instanceof OEntityLiving) {
            if(var1.af()) {
               this.a((OEntityLiving)var1, true);
            }

            this.a(OStatList.w, var2);
            int var8 = OEnchantmentHelper.c(this.k, (OEntityLiving)var1);
            if(var8 > 0) {
               var1.h(var8 * 4);
            }
         }

         this.c(0.3F);
      }

   }

   public void b(OEntity var1) {}

   public void a_(OEntity var1) {}

   public void a(OItemStack var1) {}

   public void J() {
      super.J();
      this.l.b(this);
      if(this.m != null) {
         this.m.b(this);
      }

   }

   public boolean K() {
      return !this.E && super.K();
   }

   public OEnumStatus a(int var1, int var2, int var3) {
      if(!this.X.I) {
         if(this.L() || !this.af()) {
            return OEnumStatus.e;
         }

         if(this.X.y.c) {
            return OEnumStatus.b;
         }

         if(this.X.e()) {
            return OEnumStatus.c;
         }

         if(Math.abs(this.ab - (double)var1) > 3.0D || Math.abs(this.ac - (double)var2) > 2.0D || Math.abs(this.ad - (double)var3) > 3.0D) {
            return OEnumStatus.d;
         }

         double var4 = 8.0D;
         double var6 = 5.0D;
         List var8 = this.X.a(OEntityMob.class, OAxisAlignedBB.b((double)var1 - var4, (double)var2 - var6, (double)var3 - var4, (double)var1 + var4, (double)var2 + var6, (double)var3 + var4));
         if(!var8.isEmpty()) {
            return OEnumStatus.f;
         }
      }

      this.b(0.2F, 0.2F);
      this.au = 0.2F;
      if(this.X.c(var1, var2, var3)) {
         int var9 = this.X.e(var1, var2, var3);
         int var10 = OBlockBed.c(var9);
         float var11 = 0.5F;
         float var12 = 0.5F;
         switch(var10) {
         case 0:
            var12 = 0.9F;
            break;
         case 1:
            var11 = 0.1F;
            break;
         case 2:
            var12 = 0.1F;
            break;
         case 3:
            var11 = 0.9F;
         }

         this.b(var10);
         this.e((double)((float)var1 + var11), (double)((float)var2 + 0.9375F), (double)((float)var3 + var12));
      } else {
         this.e((double)((float)var1 + 0.5F), (double)((float)var2 + 0.9375F), (double)((float)var3 + 0.5F));
      }

      this.E = true;
      this.a = 0;
      this.F = new OChunkCoordinates(var1, var2, var3);
      this.ae = this.ag = this.af = 0.0D;
      if(!this.X.I) {
         this.X.s();
      }

      return OEnumStatus.a;
   }

   private void b(int var1) {
      this.G = 0.0F;
      this.H = 0.0F;
      switch(var1) {
      case 0:
         this.H = -1.8F;
         break;
      case 1:
         this.G = 1.8F;
         break;
      case 2:
         this.H = 1.8F;
         break;
      case 3:
         this.G = -1.8F;
      }

   }

   public void a(boolean var1, boolean var2, boolean var3) {
      this.b(0.6F, 1.8F);
      this.n_();
      OChunkCoordinates var4 = this.F;
      OChunkCoordinates var5 = this.F;
      if(var4 != null && this.X.a(var4.a, var4.b, var4.c) == OBlock.S.bM) {
         OBlockBed.a(this.X, var4.a, var4.b, var4.c, false);
         var5 = OBlockBed.f(this.X, var4.a, var4.b, var4.c, 0);
         if(var5 == null) {
            var5 = new OChunkCoordinates(var4.a, var4.b + 1, var4.c);
         }

         this.e((double)((float)var5.a + 0.5F), (double)((float)var5.b + this.au + 0.1F), (double)((float)var5.c + 0.5F));
      }

      this.E = false;
      if(!this.X.I && var2) {
         this.X.s();
      }

      if(var1) {
         this.a = 0;
      } else {
         this.a = 100;
      }

      if(var3) {
         this.a(this.F);
      }

   }

   private boolean v() {
      return this.X.a(this.F.a, this.F.b, this.F.c) == OBlock.S.bM;
   }

   public static OChunkCoordinates a(OWorld var0, OChunkCoordinates var1) {
      OIChunkProvider var2 = var0.p();
      var2.a(var1.a - 3 >> 4, var1.c - 3 >> 4);
      var2.a(var1.a + 3 >> 4, var1.c - 3 >> 4);
      var2.a(var1.a - 3 >> 4, var1.c + 3 >> 4);
      var2.a(var1.a + 3 >> 4, var1.c + 3 >> 4);
      if(var0.a(var1.a, var1.b, var1.c) != OBlock.S.bM) {
         return null;
      } else {
         OChunkCoordinates var3 = OBlockBed.f(var0, var1.a, var1.b, var1.c, 0);
         return var3;
      }
   }

   public boolean L() {
      return this.E;
   }

   public boolean M() {
      return this.E && this.a >= 100;
   }

   public void a(String var1) {}

   public OChunkCoordinates N() {
      return this.b;
   }

   public void a(OChunkCoordinates var1) {
      if(var1 != null) {
         this.b = new OChunkCoordinates(var1);
      } else {
         this.b = null;
      }

   }

   public void a(OStatBase var1) {
      this.a(var1, 1);
   }

   public void a(OStatBase var1, int var2) {}

   protected void O() {
      super.O();
      this.a(OStatList.u, 1);
      if(this.ak()) {
         this.c(0.8F);
      } else {
         this.c(0.2F);
      }

   }

   public void a(float var1, float var2) {
      double var3 = this.ab;
      double var5 = this.ac;
      double var7 = this.ad;
      if(this.L.b) {
         double var9 = this.af;
         float var11 = this.bn;
         this.bn = 0.05F;
         super.a(var1, var2);
         this.af = var9 * 0.6D;
         this.bn = var11;
      } else {
         super.a(var1, var2);
      }

      this.d(this.ab - var3, this.ac - var5, this.ad - var7);
   }

   public void d(double var1, double var3, double var5) {
      if(this.W == null) {
         int var7;
         if(this.a(OMaterial.g)) {
            var7 = Math.round(OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
            if(var7 > 0) {
               this.a(OStatList.q, var7);
               this.c(0.015F * (float)var7 * 0.01F);
            }
         } else if(this.l_()) {
            var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
            if(var7 > 0) {
               this.a(OStatList.m, var7);
               this.c(0.015F * (float)var7 * 0.01F);
            }
         } else if(this.t_()) {
            if(var3 > 0.0D) {
               this.a(OStatList.o, (int)Math.round(var3 * 100.0D));
            }
         } else if(this.am) {
            var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
            if(var7 > 0) {
               this.a(OStatList.l, var7);
               if(this.ak()) {
                  this.c(0.099999994F * (float)var7 * 0.01F);
               } else {
                  this.c(0.01F * (float)var7 * 0.01F);
               }
            }
         } else {
            var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
            if(var7 > 25) {
               this.a(OStatList.p, var7);
            }
         }

      }
   }

   private void j(double var1, double var3, double var5) {
      if(this.W != null) {
         int var7 = Math.round(OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
         if(var7 > 0) {
            if(this.W instanceof OEntityMinecart) {
               this.a(OStatList.r, var7);
               if(this.c == null) {
                  this.c = new OChunkCoordinates(OMathHelper.b(this.ab), OMathHelper.b(this.ac), OMathHelper.b(this.ad));
               } else if(this.c.a(OMathHelper.b(this.ab), OMathHelper.b(this.ac), OMathHelper.b(this.ad)) >= 1000.0D) {
                  this.a((OStatBase)OAchievementList.q, 1);
               }
            } else if(this.W instanceof OEntityBoat) {
               this.a(OStatList.s, var7);
            } else if(this.W instanceof OEntityPig) {
               this.a(OStatList.t, var7);
            }
         }
      }

   }

   protected void b(float var1) {
      if(!this.L.c) {
         if(var1 >= 2.0F) {
            this.a(OStatList.n, (int)Math.round((double)var1 * 100.0D));
         }

         super.b(var1);
      }
   }

   public void a(OEntityLiving var1) {
      if(var1 instanceof OEntityMob) {
         this.a((OStatBase)OAchievementList.s);
      }

   }

   public void P() {
      if(this.I > 0) {
         this.I = 10;
      } else {
         this.J = true;
      }
   }

	public void g(int var1) {
    	addXP(var1);
    }
    
    public void addXP(int var1) {
        this.q += var1;
        this.O += (float)var1 / (float)this.Q();
		this.N += var1;
       	levelUp();
    }

    public void removeXP(int var1) {
        this.q -= var1;
        this.O -= (float)var1 / (float)this.Q();
		this.N -= var1;
       	levelUp();
    }

    public void setXP(int var1) {
        this.q = var1;
        this.O = (float)var1 / (float)this.Q();
		this.N = var1;
       	levelUp();
    }
    
    public void levelUp() {
   		while (this.O >= 1.0F) {
   			--this.O;
   			this.an();
   			manager.callHook(PluginLoader.Hook.LEVEL_UP, ((OEntityPlayerMP) this).getPlayer());
   		}
    }
	
	public void c_(int var1) {
		this.M -= var1;
		if(this.M < 0) {
			this.M = 0;
		}
	}
	
	public int Q() {
    	return 7 + (this.M * 7 >> 1);
    }

    private void an() {
        ++this.M;
    }

   public void c(float var1) {
      if(!this.L.a) {
         if(!this.X.I) {
            this.n.a(var1);
         }

      }
   }

   public OFoodStats R() {
      return this.n;
   }

   public boolean b(boolean var1) {
      return (var1 || this.n.b()) && !this.L.a;
   }

   public boolean r_() {
      return this.ar() > 0 && this.ar() < this.e();
   }

   public void a(OItemStack var1, int var2) {
      if(var1 != this.d) {
         this.d = var1;
         this.e = var2;
         if(!this.X.I) {
            this.e(true);
         }

      }
   }

   public boolean d(int var1, int var2, int var3) {
      return true;
   }

   protected int c(OEntityPlayer var1) {
      int var2 = this.M * 7;
      return var2 > 100?100:var2;
   }

   protected boolean T() {
      return true;
   }

   public String U() {
      return this.v;
   }

   public void d(int var1) {}

   public void d(OEntityPlayer var1) {
      this.k.a(var1.k);
      this.bq = var1.bq;
      this.n = var1.n;
      this.M = var1.M;
      this.N = var1.N;
      this.O = var1.O;
      this.q = var1.q;
   }
}
