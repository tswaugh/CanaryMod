import java.util.Iterator;
import java.util.List;

public class OEntityWolf extends OEntityAnimal {

   private boolean d = false;
   private float e;
   private float f;
   private boolean g;
   private boolean h;
   private float i;
   private float j;


   public OEntityWolf(OWorld var1) {
      super(var1);
      this.be = "/mob/wolf.png";
      this.b(0.8F, 0.8F);
      this.ca = 1.1F;
   }

   public int e() {
      return this.Q()?20:8;
   }

   protected void a() {
      super.a();
      this.aN.a(16, Byte.valueOf((byte)0));
      this.aN.a(17, "");
      this.aN.a(18, new Integer(this.ar()));
   }

   protected boolean c() {
      return false;
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      var1.a("Angry", this.N());
      var1.a("Sitting", this.M());
      if(this.H() == null) {
         var1.a("Owner", "");
      } else {
         var1.a("Owner", this.H());
      }

   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      this.f(var1.n("Angry"));
      this.b(var1.n("Sitting"));
      String var2 = var1.j("Owner");
      if(var2.length() > 0) {
         this.a(var2);
         this.g(true);
      }

   }

   protected boolean g_() {
      return this.N();
   }

   protected String h() {
      return this.N()?"mob.wolf.growl":(this.aH.nextInt(3) == 0?(this.Q() && this.aN.c(18) < 10?"mob.wolf.whine":"mob.wolf.panting"):"mob.wolf.bark");
   }

   protected String i() {
      return "mob.wolf.hurt";
   }

   protected String j() {
      return "mob.wolf.death";
   }

   protected float z() {
      return 0.4F;
   }

   protected int m() {
      return -1;
   }

   protected void D() {
      super.D();
      if(!this.b && !this.w() && this.Q() && this.W == null) {
         OEntityPlayer var3 = this.X.a(this.H());
         if(var3 != null) {
            float var2 = var3.g(this);
            if(var2 > 5.0F) {
               this.c(var3, var2);
            }
         } else if(!this.l_()) {
            this.b(true);
         }
      } else if(this.a == null && !this.w() && !this.Q() && this.X.w.nextInt(100) == 0) {
         List var1 = this.X.a(OEntitySheep.class, OAxisAlignedBB.b(this.ab, this.ac, this.ad, this.ab + 1.0D, this.ac + 1.0D, this.ad + 1.0D).b(16.0D, 4.0D, 16.0D));
         if(!var1.isEmpty()) {
            this.e((OEntity)var1.get(this.X.w.nextInt(var1.size())));
         }
      }

      if(this.l_()) {
         this.b(false);
      }

      if(!this.X.I) {
         this.aN.b(18, Integer.valueOf(this.ar()));
      }

   }

   public void f() {
      super.f();
      this.d = false;
      if(this.at() && !this.w() && !this.N()) {
         OEntity var1 = this.au();
         if(var1 instanceof OEntityPlayer) {
            OEntityPlayer var2 = (OEntityPlayer)var1;
            OItemStack var3 = var2.k.a();
            if(var3 != null) {
               if(!this.Q() && var3.c == OItem.aW.bM) {
                  this.d = true;
               } else if(this.Q() && OItem.d[var3.c] instanceof OItemFood) {
                  this.d = ((OItemFood)OItem.d[var3.c]).p();
               }
            }
         }
      }

      if(!this.bl && this.g && !this.h && !this.w() && this.am) {
         this.h = true;
         this.i = 0.0F;
         this.j = 0.0F;
         this.X.a(this, (byte)8);
      }

   }

   public void b() {
      super.b();
      this.f = this.e;
      if(this.d) {
         this.e += (1.0F - this.e) * 0.4F;
      } else {
         this.e += (0.0F - this.e) * 0.4F;
      }

      if(this.d) {
         this.cb = 10;
      }

      if(this.ab()) {
         this.g = true;
         this.h = false;
         this.i = 0.0F;
         this.j = 0.0F;
      } else if((this.g || this.h) && this.h) {
         if(this.i == 0.0F) {
            this.X.a(this, "mob.wolf.shake", this.z(), (this.aH.nextFloat() - this.aH.nextFloat()) * 0.2F + 1.0F);
         }

         this.j = this.i;
         this.i += 0.05F;
         if(this.j >= 2.0F) {
            this.g = false;
            this.h = false;
            this.j = 0.0F;
            this.i = 0.0F;
         }

         if(this.i > 0.4F) {
            float var1 = (float)this.al.b;
            int var2 = (int)(OMathHelper.a((this.i - 0.4F) * 3.1415927F) * 7.0F);

            for(int var3 = 0; var3 < var2; ++var3) {
               float var4 = (this.aH.nextFloat() * 2.0F - 1.0F) * this.av * 0.5F;
               float var5 = (this.aH.nextFloat() * 2.0F - 1.0F) * this.av * 0.5F;
               this.X.a("splash", this.ab + (double)var4, (double)(var1 + 0.8F), this.ad + (double)var5, this.ae, this.af, this.ag);
            }
         }
      }

   }

   public float n() {
      return this.aw * 0.8F;
   }

   protected int S() {
      return this.M()?20:super.S();
   }

   private void c(OEntity var1, float var2) {
      OPathEntity var3 = this.X.a(this, var1, 16.0F);
      if(var3 == null && var2 > 12.0F) {
         int var4 = OMathHelper.b(var1.ab) - 2;
         int var5 = OMathHelper.b(var1.ad) - 2;
         int var6 = OMathHelper.b(var1.al.b);

         for(int var7 = 0; var7 <= 4; ++var7) {
            for(int var8 = 0; var8 <= 4; ++var8) {
               if((var7 < 1 || var8 < 1 || var7 > 3 || var8 > 3) && this.X.o(var4 + var7, var6 - 1, var5 + var8) && !this.X.o(var4 + var7, var6, var5 + var8) && !this.X.o(var4 + var7, var6 + 1, var5 + var8)) {
                  this.c((double)((float)(var4 + var7) + 0.5F), (double)var6, (double)((float)(var5 + var8) + 0.5F), this.ah, this.ai);
                  return;
               }
            }
         }
      } else {
         this.a(var3);
      }

   }

   protected boolean u() {
      return this.M() || this.h;
   }

   public boolean a(ODamageSource var1, int var2) {
      OEntity var3 = var1.a();
      this.b(false);
      if(var3 != null && !(var3 instanceof OEntityPlayer) && !(var3 instanceof OEntityArrow)) {
         var2 = (var2 + 1) / 2;
      }

      if(!super.a(var1, var2)) {
         return false;
      } else {
         if(!this.Q() && !this.N()) {
            if(var3 instanceof OEntityPlayer) {
               this.f(true);
               this.a = var3;
            }

            if(var3 instanceof OEntityArrow && ((OEntityArrow)var3).c != null) {
               var3 = ((OEntityArrow)var3).c;
            }

            if(var3 instanceof OEntityLiving) {
               List var4 = this.X.a(OEntityWolf.class, OAxisAlignedBB.b(this.ab, this.ac, this.ad, this.ab + 1.0D, this.ac + 1.0D, this.ad + 1.0D).b(16.0D, 4.0D, 16.0D));
               Iterator var5 = var4.iterator();

               while(var5.hasNext()) {
                  OEntity var6 = (OEntity)var5.next();
                  OEntityWolf var7 = (OEntityWolf)var6;
                  if(!var7.Q() && var7.a == null) {
                     var7.a = var3;
                     if(var3 instanceof OEntityPlayer) {
                        var7.f(true);
                     }
                  }
               }
            }
         } else if(var3 != this && var3 != null) {
            if(this.Q() && var3 instanceof OEntityPlayer && ((OEntityPlayer)var3).v.equalsIgnoreCase(this.H())) {
               return true;
            }

            this.a = var3;
         }

         return true;
      }
   }

   protected OEntity k() {
      return this.N()?this.X.a(this, 16.0D):null;
   }

   protected void a(OEntity var1, float var2) {
      if(var2 > 2.0F && var2 < 6.0F && this.aH.nextInt(10) == 0) {
         if(this.am) {
            double var3 = var1.ab - this.ab;
            double var5 = var1.ad - this.ad;
            float var7 = OMathHelper.a(var3 * var3 + var5 * var5);
            this.ae = var3 / (double)var7 * 0.5D * 0.800000011920929D + this.ae * 0.20000000298023224D;
            this.ag = var5 / (double)var7 * 0.5D * 0.800000011920929D + this.ag * 0.20000000298023224D;
            this.af = 0.4000000059604645D;
         }
      } else if((double)var2 < 1.5D && var1.al.e > this.al.b && var1.al.b < this.al.e) {
         this.bx = 20;
         byte var8 = 2;
         if(this.Q()) {
            var8 = 4;
         }

         var1.a(ODamageSource.a((OEntityLiving)this), var8);
      }

   }

   public boolean b(OEntityPlayer var1) {
      OItemStack var2 = var1.k.a();
      if(!this.Q()) {
         if(var2 != null && var2.c == OItem.aW.bM && !this.N()) {
            --var2.a;
            if(var2.a <= 0) {
               var1.k.a(var1.k.c, (OItemStack)null);
            }

            if(!this.X.I) {
                // CanaryMod hook: onTame
                // randomize the tame result. if its 0 - tame success.
                int tameResult = this.aH.nextInt(3);
                // Call hook
                PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.TAME, manager.getServer().getPlayer(var1.v), new Mob(this), tameResult == 0);
                // if taming succeeded normally (tameResult == 0) or plugin hook result is allow (force taming)
                if (tameResult == 0 && res == PluginLoader.HookResult.DEFAULT_ACTION || res == PluginLoader.HookResult.ALLOW_ACTION) {
                  this.g(true);
                  this.a((OPathEntity)null);
                  this.b(true);
                  this.k(20);
                  this.a(var1.v);
                  this.a(true);
                  this.X.a(this, (byte)7);
               } else {
                  this.a(false);
                  this.X.a(this, (byte)6);
               }
            }

            return true;
         }
      } else {
         if(var2 != null && OItem.d[var2.c] instanceof OItemFood) {
            OItemFood var3 = (OItemFood)OItem.d[var2.c];
            if(var3.p() && this.aN.c(18) < 20) {
               --var2.a;
               this.c(var3.n());
               if(var2.a <= 0) {
                  var1.k.a(var1.k.c, (OItemStack)null);
               }

               return true;
            }
         }

         if(var1.v.equalsIgnoreCase(this.H())) {
            if(!this.X.I) {
               this.b(!this.M());
               this.bY = false;
               this.a((OPathEntity)null);
            }

            return true;
         }
      }

      return super.b(var1);
   }

   void a(boolean var1) {
      String var2 = "heart";
      if(!var1) {
         var2 = "smoke";
      }

      for(int var3 = 0; var3 < 7; ++var3) {
         double var4 = this.aH.nextGaussian() * 0.02D;
         double var6 = this.aH.nextGaussian() * 0.02D;
         double var8 = this.aH.nextGaussian() * 0.02D;
         this.X.a(var2, this.ab + (double)(this.aH.nextFloat() * this.av * 2.0F) - (double)this.av, this.ac + 0.5D + (double)(this.aH.nextFloat() * this.aw), this.ad + (double)(this.aH.nextFloat() * this.av * 2.0F) - (double)this.av, var4, var6, var8);
      }

   }

   public int h_() {
      return 8;
   }

   public String H() {
      return this.aN.d(17);
   }

   public void a(String var1) {
      this.aN.b(17, var1);
   }

   public boolean M() {
      return (this.aN.a(16) & 1) != 0;
   }

   public void b(boolean var1) {
      byte var2 = this.aN.a(16);
      if(var1) {
         this.aN.b(16, Byte.valueOf((byte)(var2 | 1)));
      } else {
         this.aN.b(16, Byte.valueOf((byte)(var2 & -2)));
      }

   }

   public boolean N() {
      return (this.aN.a(16) & 2) != 0;
   }

   public void f(boolean var1) {
      byte var2 = this.aN.a(16);
      if(var1) {
         this.aN.b(16, Byte.valueOf((byte)(var2 | 2)));
      } else {
         this.aN.b(16, Byte.valueOf((byte)(var2 & -3)));
      }

   }

   public boolean Q() {
      return (this.aN.a(16) & 4) != 0;
   }

   public void g(boolean var1) {
      byte var2 = this.aN.a(16);
      if(var1) {
         this.aN.b(16, Byte.valueOf((byte)(var2 | 4)));
      } else {
         this.aN.b(16, Byte.valueOf((byte)(var2 & -5)));
      }

   }

   protected OEntityAnimal a(OEntityAnimal var1) {
      return new OEntityWolf(this.X);
   }
}
