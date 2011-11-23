import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class OEntityLiving extends OEntity {

   public int aU = 20;
   public float aV;
   public float aW;
   public float aX = 0.0F;
   public float aY = 0.0F;
   protected float aZ;
   protected float ba;
   protected float bb;
   protected float bc;
   protected boolean bd = true;
   protected String be = "/mob/char.png";
   protected boolean bf = true;
   protected float bg = 0.0F;
   protected String bh = null;
   protected float bi = 1.0F;
   protected int bj = 0;
   protected float bk = 0.0F;
   public boolean bl = false;
   public float bm = 0.1F;
   public float bn = 0.02F;
   public float bo;
   public float bp;
   protected int bq = this.e();
   public int br;
   protected int bs;
   private int a;
   public int bt;
   public int bu;
   public float bv = 0.0F;
   public int bw = 0;
   public int bx = 0;
   public float by;
   public float bz;
   protected boolean bA = false;
   protected int bB;
   public int bC = -1;
   public float bD = (float)(Math.random() * 0.8999999761581421D + 0.10000000149011612D);
   public float bE;
   public float bF;
   public float bG;
   protected OEntityPlayer bH = null;
   protected int bI = 0;
   public int bJ = 0;
   public int bK = 0;
   protected HashMap bL = new HashMap();
   private boolean b = true;
   private int c;
   protected int bM;
   protected double bN;
   protected double bO;
   protected double bP;
   protected double bQ;
   protected double bR;
   float bS = 0.0F;
   protected int bT = 0;
   protected int bU = 0;
   protected float bV;
   protected float bW;
   protected float bX;
   protected boolean bY = false;
   protected float bZ = 0.0F;
   protected float ca = 0.7F;
   private int d = 0;
   private OEntity e;
   protected int cb = 0;
   // CanaryMod Start
   LivingEntity entity = new LivingEntity(this);

   // CanaryMod end

   public OEntityLiving(OWorld var1) {
      super(var1);
      this.U = true;
      this.aW = (float)(Math.random() + 1.0D) * 0.01F;
      this.e(this.ab, this.ac, this.ad);
      this.aV = (float)Math.random() * 12398.0F;
      this.ah = (float)(Math.random() * 3.1415927410125732D * 2.0D);
      this.aE = 0.5F;
   }

   protected void a() {
      this.aN.a(8, Integer.valueOf(this.c));
   }

   public boolean i(OEntity var1) {
      return this.X.a(OVec3D.b(this.ab, this.ac + (double)this.n(), this.ad), OVec3D.b(var1.ab, var1.ac + (double)var1.n(), var1.ad)) == null;
   }

   public boolean y_() {
      return !this.at;
   }

   public boolean f_() {
      return !this.at;
   }

   public float n() {
      return this.aw * 0.85F;
   }

   public int s_() {
      return 80;
   }

   public void ao() {
      String var1 = this.h();
      if(var1 != null) {
         this.X.a(this, var1, this.z(), this.u());
      }

   }

   public void W() {
      this.bo = this.bp;
      super.W();
      OProfiler.a("mobBaseTick");
      if(this.aH.nextInt(1000) < this.a++) {
         this.a = -this.s_();
         this.ao();
      }

      if(this.af() && this.K()) {
    	  // CanaryMod Damage hook: Suffocation
    	  if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.SUFFOCATION, null, entity, 1))
    		  this.a(ODamageSource.d, 1);

      }

      if(this.aa() || this.X.I) {
         this.Y();
      }

      if(this.af() && this.a(OMaterial.g) && !this.u_() && !this.bL.containsKey(Integer.valueOf(OPotion.o.H))) {
         this.j(this.e(this.al()));
         if(this.al() == -20) {
            this.j(0);

            for(int var1 = 0; var1 < 8; ++var1) {
               float var2 = this.aH.nextFloat() - this.aH.nextFloat();
               float var3 = this.aH.nextFloat() - this.aH.nextFloat();
               float var4 = this.aH.nextFloat() - this.aH.nextFloat();
               this.X.a("bubble", this.ab + (double)var2, this.ac + (double)var3, this.ad + (double)var4, this.ae, this.af, this.ag);
            }
            // CanaryMod Damage hook: Drowning
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.WATER, null, entity, 2))
                this.a(ODamageSource.e, 2);

         }

         this.Y();
      } else {
         this.j(300);
      }

      this.by = this.bz;
      if(this.bx > 0) {
         --this.bx;
      }

      if(this.bt > 0) {
         --this.bt;
      }

      if(this.aL > 0) {
         --this.aL;
      }

      if(this.bq <= 0) {
         this.ap();
      }

      if(this.bI > 0) {
         --this.bI;
      } else {
         this.bH = null;
      }

      this.aw();
      this.bc = this.bb;
      this.aY = this.aX;
      this.aj = this.ah;
      this.ak = this.ai;
      OProfiler.a();
   }

   protected void ap() {
      ++this.bw;
      if(this.bw == 20) {
         int var1;
         if(!this.X.I && (this.bI > 0 || this.T()) && !this.w_()) {
            var1 = this.c(this.bH);

            while(var1 > 0) {
               int var2 = OEntityXPOrb.b(var1);
               var1 -= var2;
               this.X.b((OEntity)(new OEntityXPOrb(this.X, this.ab, this.ac, this.ad, var2)));
            }
         }

         this.av();
         this.J();

         for(var1 = 0; var1 < 20; ++var1) {
            double var3 = this.aH.nextGaussian() * 0.02D;
            double var5 = this.aH.nextGaussian() * 0.02D;
            double var7 = this.aH.nextGaussian() * 0.02D;
            this.X.a("explode", this.ab + (double)(this.aH.nextFloat() * this.av * 2.0F) - (double)this.av, this.ac + (double)(this.aH.nextFloat() * this.aw), this.ad + (double)(this.aH.nextFloat() * this.av * 2.0F) - (double)this.av, var3, var5, var7);
         }
      }

   }

   protected int e(int var1) {
      return var1 - 1;
   }

   protected int c(OEntityPlayer var1) {
      return this.bB;
   }

   protected boolean T() {
      return false;
   }

   public void aq() {
      for(int var1 = 0; var1 < 20; ++var1) {
         double var2 = this.aH.nextGaussian() * 0.02D;
         double var4 = this.aH.nextGaussian() * 0.02D;
         double var6 = this.aH.nextGaussian() * 0.02D;
         double var8 = 10.0D;
         this.X.a("explode", this.ab + (double)(this.aH.nextFloat() * this.av * 2.0F) - (double)this.av - var2 * var8, this.ac + (double)(this.aH.nextFloat() * this.aw) - var4 * var8, this.ad + (double)(this.aH.nextFloat() * this.av * 2.0F) - (double)this.av - var6 * var8, var2, var4, var6);
      }

   }

   public void C() {
      super.C();
      this.aZ = this.ba;
      this.ba = 0.0F;
      this.az = 0.0F;
   }

   public void b() {
      super.b();
      if(this.bJ > 0) {
         if(this.bK <= 0) {
            this.bK = 60;
         }

         --this.bK;
         if(this.bK <= 0) {
            --this.bJ;
         }
      }

      this.f();
      double var1 = this.ab - this.Y;
      double var3 = this.ad - this.aa;
      float var5 = OMathHelper.a(var1 * var1 + var3 * var3);
      float var6 = this.aX;
      float var7 = 0.0F;
      this.aZ = this.ba;
      float var8 = 0.0F;
      if(var5 > 0.05F) {
         var8 = 1.0F;
         var7 = var5 * 3.0F;
         var6 = (float)Math.atan2(var3, var1) * 180.0F / 3.1415927F - 90.0F;
      }

      if(this.bp > 0.0F) {
         var6 = this.ah;
      }

      if(!this.am) {
         var8 = 0.0F;
      }

      this.ba += (var8 - this.ba) * 0.3F;

      float var9;
      for(var9 = var6 - this.aX; var9 < -180.0F; var9 += 360.0F) {
         ;
      }

      while(var9 >= 180.0F) {
         var9 -= 360.0F;
      }

      this.aX += var9 * 0.3F;

      float var10;
      for(var10 = this.ah - this.aX; var10 < -180.0F; var10 += 360.0F) {
         ;
      }

      while(var10 >= 180.0F) {
         var10 -= 360.0F;
      }

      boolean var11 = var10 < -90.0F || var10 >= 90.0F;
      if(var10 < -75.0F) {
         var10 = -75.0F;
      }

      if(var10 >= 75.0F) {
         var10 = 75.0F;
      }

      this.aX = this.ah - var10;
      if(var10 * var10 > 2500.0F) {
         this.aX += var10 * 0.2F;
      }

      if(var11) {
         var7 *= -1.0F;
      }

      while(this.ah - this.aj < -180.0F) {
         this.aj -= 360.0F;
      }

      while(this.ah - this.aj >= 180.0F) {
         this.aj += 360.0F;
      }

      while(this.aX - this.aY < -180.0F) {
         this.aY -= 360.0F;
      }

      while(this.aX - this.aY >= 180.0F) {
         this.aY += 360.0F;
      }

      while(this.ai - this.ak < -180.0F) {
         this.ak -= 360.0F;
      }

      while(this.ai - this.ak >= 180.0F) {
         this.ak += 360.0F;
      }

      this.bb += var7;
   }

   protected void b(float var1, float var2) {
      super.b(var1, var2);
   }

   public void c(int var1) {
      if(this.bq > 0) {
         this.bq += var1;
         if(this.bq > this.e()) {
            this.bq = this.e();
         }

         this.aL = this.aU / 2;
      }
   }

   public abstract int e();

   public int ar() {
      return this.bq;
   }

   public void k(int var1) {
      this.bq = var1;
      if(var1 > this.e()) {
         var1 = this.e();
      }

   }

   public boolean a(ODamageSource var1, int var2) {
      if(this.X.I) {
         return false;
      } else {
         this.bU = 0;
         if(this.bq <= 0) {
            return false;
         } else if(var1.k() && this.a(OPotion.n)) {
            return false;
         } else {
            this.bF = 1.5F;
            
            // CanaryMod damage entities.
            LivingEntity attacker = null;
            if (var1 != null && var1 instanceof OEntityDamageSource && ((OEntityDamageSource) var1).a() instanceof OEntityLiving) {
                OEntity o = ((OEntityDamageSource) var1).a();
                attacker = new LivingEntity((OEntityLiving) o);
            }

            // CanaryMod attack by entity, but it might not do damage!
            if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.ATTACK, attacker, entity, var2)) {
                if (this instanceof OEntityCreature)
                    ((OEntityCreature) this).c = 0;
                return false;
            }

            boolean var3 = true;
            if((float)this.aL > (float)this.aU / 2.0F) {
               if(var2 <= this.bT) {
                  return false;
               }
               
               // CanaryMod: partial damage
               if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, var2 - bT))
                   return false;

               this.c(var1, var2 - this.bT);
               this.bT = var2;
               var3 = false;
            } else {
                // CanaryMod: full damage
                if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, var2))
                    return false;

               this.bT = var2;
               this.br = this.bq;
               this.aL = this.aU;
               this.c(var1, var2);
               this.bt = this.bu = 10;
            }

            this.bv = 0.0F;
            OEntity var4 = var1.a();
            if(var4 != null) {
               if(var4 instanceof OEntityPlayer) {
                  this.bI = 60;
                  this.bH = (OEntityPlayer)var4;
               } else if(var4 instanceof OEntityWolf) {
                  OEntityWolf var5 = (OEntityWolf)var4;
                  if(var5.Q()) {
                     this.bI = 60;
                     this.bH = null;
                  }
               }
            }

            if(var3) {
               this.X.a(this, (byte)2);
               this.ad();
               if(var4 != null) {
                  double var6 = var4.ab - this.ab;

                  double var8;
                  for(var8 = var4.ad - this.ad; var6 * var6 + var8 * var8 < 1.0E-4D; var8 = (Math.random() - Math.random()) * 0.01D) {
                     var6 = (Math.random() - Math.random()) * 0.01D;
                  }

                  this.bv = (float)(Math.atan2(var8, var6) * 180.0D / 3.1415927410125732D) - this.ah;
                  this.a(var4, var2, var6, var8);
               } else {
                  this.bv = (float)((int)(Math.random() * 2.0D) * 180);
               }
            }

            if(this.bq <= 0) {
               if(var3) {
                  this.X.a(this, this.j(), this.z(), this.u());
               }

               this.a(var1);
            } else if(var3) {
               this.X.a(this, this.i(), this.z(), this.u());
            }

            return true;
         }
      }
   }

   private float u() {
      return this.w_()?(this.aH.nextFloat() - this.aH.nextFloat()) * 0.2F + 1.5F:(this.aH.nextFloat() - this.aH.nextFloat()) * 0.2F + 1.0F;
   }

   protected int F() {
      return 0;
   }

   protected void f(int var1) {}

   protected int e(ODamageSource var1, int var2) {
      if(!var1.d()) {
         int var3 = 25 - this.F();
         int var4 = var2 * var3 + this.bs;
         this.f(var2);
         var2 = var4 / 25;
         this.bs = var4 % 25;
      }

      return var2;
   }

   protected int b(ODamageSource var1, int var2) {
      if(this.a(OPotion.m)) {
         int var3 = (this.b(OPotion.m).c() + 1) * 5;
         int var4 = 25 - var3;
         int var5 = var2 * var4 + this.bs;
         var2 = var5 / 25;
         this.bs = var5 % 25;
      }

      return var2;
   }

   protected void c(ODamageSource var1, int var2) {
      var2 = this.e(var1, var2);
      var2 = this.b(var1, var2);
      this.bq -= var2;
   }

   protected float z() {
      return 1.0F;
   }

   protected String h() {
      return null;
   }

   protected String i() {
      return "damage.hurtflesh";
   }

   protected String j() {
      return "damage.hurtflesh";
   }

   public void a(OEntity var1, int var2, double var3, double var5) {
      this.aT = true;
      float var7 = OMathHelper.a(var3 * var3 + var5 * var5);
      float var8 = 0.4F;
      this.ae /= 2.0D;
      this.af /= 2.0D;
      this.ag /= 2.0D;
      this.ae -= var3 / (double)var7 * (double)var8;
      this.af += 0.4000000059604645D;
      this.ag -= var5 / (double)var7 * (double)var8;
      if(this.af > 0.4000000059604645D) {
         this.af = 0.4000000059604645D;
      }

   }

   public void a(ODamageSource var1) {
      OEntity var2 = var1.a();
      if(this.bj >= 0 && var2 != null) {
         var2.b(this, this.bj);
      }

      if(var2 != null) {
         var2.a(this);
      }

      this.bA = true;
      if(!this.X.I) {
         int var3 = 0;
         if(var2 instanceof OEntityPlayer) {
            var3 = OEnchantmentHelper.f(((OEntityPlayer)var2).k);
         }

         if(!this.w_()) {
            this.a(this.bI > 0, var3);
         }
      }

      this.X.a(this, (byte)3);
   }

   protected void a(boolean var1, int var2) {
      int var3 = this.m();
      if(var3 > 0) {
         int var4 = this.aH.nextInt(3);
         if(var2 > 0) {
            var4 += this.aH.nextInt(var2 + 1);
         }

         for(int var5 = 0; var5 < var4; ++var5) {
            this.b(var3, 1);
         }
      }

   }

   protected int m() {
      return 0;
   }

   protected void b(float var1) {
      super.b(var1);
      int var2 = (int)Math.ceil((double)(var1 - 3.0F));
      if(var2 > 0) {
          // CanaryMod Damage hook: Falling
    	  if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, entity, var2)){
    		  if(var2 > 4) {
    			  this.X.a(this, "damage.fallbig", 1.0F, 1.0F);
    		  } else {
    			  this.X.a(this, "damage.fallsmall", 1.0F, 1.0F);
    		  }
    		  this.a(ODamageSource.h, var2);
    	  }
		int var3 = this.X.a(OMathHelper.b(this.ab), OMathHelper.b(this.ac - 0.20000000298023224D - (double)this.au), OMathHelper.b(this.ad));
		if(var3 > 0) {
		   OStepSound var4 = OBlock.k[var3].bX;
		   this.X.a(this, var4.c(), var4.a() * 0.5F, var4.b() * 0.75F);
		}
      }

   }

   public void a(float var1, float var2) {
      double var3;
      if(this.l_()) {
         var3 = this.ac;
         this.a(var1, var2, 0.02F);
         this.a(this.ae, this.af, this.ag);
         this.ae *= 0.800000011920929D;
         this.af *= 0.800000011920929D;
         this.ag *= 0.800000011920929D;
         this.af -= 0.02D;
         if(this.an && this.f(this.ae, this.af + 0.6000000238418579D - this.ac + var3, this.ag)) {
            this.af = 0.30000001192092896D;
         }
      } else if(this.ac()) {
         var3 = this.ac;
         this.a(var1, var2, 0.02F);
         this.a(this.ae, this.af, this.ag);
         this.ae *= 0.5D;
         this.af *= 0.5D;
         this.ag *= 0.5D;
         this.af -= 0.02D;
         if(this.an && this.f(this.ae, this.af + 0.6000000238418579D - this.ac + var3, this.ag)) {
            this.af = 0.30000001192092896D;
         }
      } else {
         float var5 = 0.91F;
         if(this.am) {
            var5 = 0.54600006F;
            int var6 = this.X.a(OMathHelper.b(this.ab), OMathHelper.b(this.al.b) - 1, OMathHelper.b(this.ad));
            if(var6 > 0) {
               var5 = OBlock.k[var6].ca * 0.91F;
            }
         }

         float var12 = 0.16277136F / (var5 * var5 * var5);
         float var7 = this.am?this.bm * var12:this.bn;
         this.a(var1, var2, var7);
         var5 = 0.91F;
         if(this.am) {
            var5 = 0.54600006F;
            int var8 = this.X.a(OMathHelper.b(this.ab), OMathHelper.b(this.al.b) - 1, OMathHelper.b(this.ad));
            if(var8 > 0) {
               var5 = OBlock.k[var8].ca * 0.91F;
            }
         }

         if(this.t_()) {
            float var13 = 0.15F;
            if(this.ae < (double)(-var13)) {
               this.ae = (double)(-var13);
            }

            if(this.ae > (double)var13) {
               this.ae = (double)var13;
            }

            if(this.ag < (double)(-var13)) {
               this.ag = (double)(-var13);
            }

            if(this.ag > (double)var13) {
               this.ag = (double)var13;
            }

            this.az = 0.0F;
            if(this.af < -0.15D) {
               this.af = -0.15D;
            }

            if(this.aj() && this.af < 0.0D) {
               this.af = 0.0D;
            }
         }

         this.a(this.ae, this.af, this.ag);
         if(this.an && this.t_()) {
            this.af = 0.2D;
         }

         this.af -= 0.08D;
         this.af *= 0.9800000190734863D;
         this.ae *= (double)var5;
         this.ag *= (double)var5;
      }

      this.bE = this.bF;
      var3 = this.ab - this.Y;
      double var9 = this.ad - this.aa;
      float var11 = OMathHelper.a(var3 * var3 + var9 * var9) * 4.0F;
      if(var11 > 1.0F) {
         var11 = 1.0F;
      }

      this.bF += (var11 - this.bF) * 0.4F;
      this.bG += this.bF;
   }

   public boolean t_() {
      int var1 = OMathHelper.b(this.ab);
      int var2 = OMathHelper.b(this.al.b);
      int var3 = OMathHelper.b(this.ad);
      return this.X.a(var1, var2, var3) == OBlock.aF.bM;
   }

   public void a(ONBTTagCompound var1) {
      var1.a("Health", (short)this.bq);
      var1.a("HurtTime", (short)this.bt);
      var1.a("DeathTime", (short)this.bw);
      var1.a("AttackTime", (short)this.bx);
      if(!this.bL.isEmpty()) {
         ONBTTagList var2 = new ONBTTagList();
         Iterator var3 = this.bL.values().iterator();

         while(var3.hasNext()) {
            OPotionEffect var4 = (OPotionEffect)var3.next();
            ONBTTagCompound var5 = new ONBTTagCompound();
            var5.a("Id", (byte)var4.a());
            var5.a("Amplifier", (byte)var4.c());
            var5.a("Duration", var4.b());
            var2.a((ONBTBase)var5);
         }

         var1.a("ActiveEffects", (ONBTBase)var2);
      }

   }

   public void b(ONBTTagCompound var1) {
      this.bq = var1.e("Health");
      if(!var1.c("Health")) {
         this.bq = this.e();
      }

      this.bt = var1.e("HurtTime");
      this.bw = var1.e("DeathTime");
      this.bx = var1.e("AttackTime");
      if(var1.c("ActiveEffects")) {
         ONBTTagList var2 = var1.m("ActiveEffects");

         for(int var3 = 0; var3 < var2.d(); ++var3) {
            ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
            byte var5 = var4.d("Id");
            byte var6 = var4.d("Amplifier");
            int var7 = var4.f("Duration");
            this.bL.put(Integer.valueOf(var5), new OPotionEffect(var5, var7, var6));
         }
      }

   }

   public boolean af() {
      return !this.at && this.bq > 0;
   }

   public boolean u_() {
      return false;
   }

   public void f() {
      if(this.d > 0) {
         --this.d;
      }

      if(this.bM > 0) {
         double var1 = this.ab + (this.bN - this.ab) / (double)this.bM;
         double var3 = this.ac + (this.bO - this.ac) / (double)this.bM;
         double var5 = this.ad + (this.bP - this.ad) / (double)this.bM;

         double var7;
         for(var7 = this.bQ - (double)this.ah; var7 < -180.0D; var7 += 360.0D) {
            ;
         }

         while(var7 >= 180.0D) {
            var7 -= 360.0D;
         }

         this.ah = (float)((double)this.ah + var7 / (double)this.bM);
         this.ai = (float)((double)this.ai + (this.bR - (double)this.ai) / (double)this.bM);
         --this.bM;
         this.e(var1, var3, var5);
         this.c(this.ah, this.ai);
         List var9 = this.X.a((OEntity)this, this.al.e(0.03125D, 0.0D, 0.03125D));
         if(var9.size() > 0) {
            double var10 = 0.0D;

            for(int var12 = 0; var12 < var9.size(); ++var12) {
               OAxisAlignedBB var13 = (OAxisAlignedBB)var9.get(var12);
               if(var13.e > var10) {
                  var10 = var13.e;
               }
            }

            var3 += var10 - this.al.b;
            this.e(var1, var3, var5);
         }
      }

      OProfiler.a("OItemSapling");
      if(this.B()) {
         this.bY = false;
         this.bV = 0.0F;
         this.bW = 0.0F;
         this.bX = 0.0F;
      } else if(!this.bl) {
         this.D();
      }

      OProfiler.a();
      boolean var14 = this.l_();
      boolean var15 = this.ac();
      if(this.bY) {
         if(var14) {
            this.af += 0.03999999910593033D;
         } else if(var15) {
            this.af += 0.03999999910593033D;
         } else if(this.am && this.d == 0) {
            this.O();
            this.d = 10;
         }
      } else {
         this.d = 0;
      }

      this.bV *= 0.98F;
      this.bW *= 0.98F;
      this.bX *= 0.9F;
      float var16 = this.bm;
      this.bm *= this.y();
      this.a(this.bV, this.bW);
      this.bm = var16;
      OProfiler.a("push");
      List var17 = this.X.b((OEntity)this, this.al.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
      if(var17 != null && var17.size() > 0) {
         for(int var18 = 0; var18 < var17.size(); ++var18) {
            OEntity var19 = (OEntity)var17.get(var18);
            if(var19.f_()) {
               var19.b_(this);
            }
         }
      }

      OProfiler.a();
   }

   protected boolean B() {
      return this.bq <= 0;
   }

   public boolean A() {
      return false;
   }

   protected void O() {
      this.af = 0.41999998688697815D;
      if(this.a(OPotion.j)) {
         this.af += (double)((float)(this.b(OPotion.j).c() + 1) * 0.1F);
      }

      if(this.ak()) {
         float var1 = this.ah * 0.017453292F;
         this.ae -= (double)(OMathHelper.a(var1) * 0.2F);
         this.ag += (double)(OMathHelper.b(var1) * 0.2F);
      }

      this.aT = true;
   }

   protected boolean g_() {
      return true;
   }

   protected void as() {
      OEntityPlayer var1 = this.X.a(this, -1.0D);
      if(var1 != null) {
         double var2 = var1.ab - this.ab;
         double var4 = var1.ac - this.ac;
         double var6 = var1.ad - this.ad;
         double var8 = var2 * var2 + var4 * var4 + var6 * var6;
         if(this.g_() && var8 > 16384.0D) {
             // CanaryMod hook onEntityDespawn
             if (!(Boolean) OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
            	 this.J();
             }
         }

         if(this.bU > 600 && this.aH.nextInt(800) == 0 && this.g_()) {
        	 if(var8 < 1024.0D) {
	            this.bU = 0;
	         } else {
	             // CanaryMod hook onEntityDespawn
	             if (!(Boolean) OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
	                 this.J();
	             } else {
	                 this.bU = 0;
	             }
	         }
         }
      }

   }

   protected void D() {
      ++this.bU;
      OEntityPlayer var1 = this.X.a(this, -1.0D);
      this.as();
      this.bV = 0.0F;
      this.bW = 0.0F;
      float var2 = 8.0F;
      if(this.aH.nextFloat() < 0.02F) {
         var1 = this.X.a(this, (double)var2);
         if(var1 != null) {
            this.e = var1;
            this.cb = 10 + this.aH.nextInt(20);
         } else {
            this.bX = (this.aH.nextFloat() - 0.5F) * 20.0F;
         }
      }

      if(this.e != null) {
         this.a(this.e, 10.0F, (float)this.S());
         if(this.cb-- <= 0 || this.e.at || this.e.h(this) > (double)(var2 * var2)) {
            this.e = null;
         }
      } else {
         if(this.aH.nextFloat() < 0.05F) {
            this.bX = (this.aH.nextFloat() - 0.5F) * 20.0F;
         }

         this.ah += this.bX;
         this.ai = this.bZ;
      }

      boolean var3 = this.l_();
      boolean var4 = this.ac();
      if(var3 || var4) {
         this.bY = this.aH.nextFloat() < 0.8F;
      }

   }

   protected int S() {
      return 40;
   }

   public void a(OEntity var1, float var2, float var3) {
      double var4 = var1.ab - this.ab;
      double var6 = var1.ad - this.ad;
      double var9;
      if(var1 instanceof OEntityLiving) {
         OEntityLiving var8 = (OEntityLiving)var1;
         var9 = this.ac + (double)this.n() - (var8.ac + (double)var8.n());
      } else {
         var9 = (var1.al.b + var1.al.e) / 2.0D - (this.ac + (double)this.n());
      }

      double var11 = (double)OMathHelper.a(var4 * var4 + var6 * var6);
      float var13 = (float)(Math.atan2(var6, var4) * 180.0D / 3.1415927410125732D) - 90.0F;
      float var14 = (float)(-(Math.atan2(var9, var11) * 180.0D / 3.1415927410125732D));
      this.ai = -this.b(this.ai, var14, var3);
      this.ah = this.b(this.ah, var13, var2);
   }

   public boolean at() {
      return this.e != null;
   }

   public OEntity au() {
      return this.e;
   }

   private float b(float var1, float var2, float var3) {
      float var4;
      for(var4 = var2 - var1; var4 < -180.0F; var4 += 360.0F) {
         ;
      }

      while(var4 >= 180.0F) {
         var4 -= 360.0F;
      }

      if(var4 > var3) {
         var4 = var3;
      }

      if(var4 < -var3) {
         var4 = -var3;
      }

      return var1 + var4;
   }

   public void av() {}

   public boolean v_() {
      return this.X.a(this.al) && this.X.a((OEntity)this, this.al).size() == 0 && !this.X.c(this.al);
   }

   protected void Z() {
      this.a(ODamageSource.i, 4);
   }

   public OVec3D ah() {
      return this.d(1.0F);
   }

   public OVec3D d(float var1) {
      float var2;
      float var3;
      float var4;
      float var5;
      if(var1 == 1.0F) {
         var2 = OMathHelper.b(-this.ah * 0.017453292F - 3.1415927F);
         var3 = OMathHelper.a(-this.ah * 0.017453292F - 3.1415927F);
         var4 = -OMathHelper.b(-this.ai * 0.017453292F);
         var5 = OMathHelper.a(-this.ai * 0.017453292F);
         return OVec3D.b((double)(var3 * var4), (double)var5, (double)(var2 * var4));
      } else {
         var2 = this.ak + (this.ai - this.ak) * var1;
         var3 = this.aj + (this.ah - this.aj) * var1;
         var4 = OMathHelper.b(-var3 * 0.017453292F - 3.1415927F);
         var5 = OMathHelper.a(-var3 * 0.017453292F - 3.1415927F);
         float var6 = -OMathHelper.b(-var2 * 0.017453292F);
         float var7 = OMathHelper.a(-var2 * 0.017453292F);
         return OVec3D.b((double)(var5 * var6), (double)var7, (double)(var4 * var6));
      }
   }

   public int h_() {
      return 4;
   }

   public boolean L() {
      return false;
   }

   protected void aw() {
      Iterator var1 = this.bL.keySet().iterator();

      while(var1.hasNext()) {
         Integer var2 = (Integer)var1.next();
         OPotionEffect var3 = (OPotionEffect)this.bL.get(var2);
         if(!var3.a(this) && !this.X.I) {
            var1.remove();
            this.c(var3);
         }
      }

      int var10;
      if(this.b) {
         if(!this.X.I) {
            if(!this.bL.isEmpty()) {
               var10 = OPotionHelper.a(this.bL.values());
               this.aN.b(8, Integer.valueOf(var10));
            } else {
               this.aN.b(8, Integer.valueOf(0));
            }
         }

         this.b = false;
      }

      if(this.aH.nextBoolean()) {
         var10 = this.aN.c(8);
         if(var10 > 0) {
            double var4 = (double)(var10 >> 16 & 255) / 255.0D;
            double var6 = (double)(var10 >> 8 & 255) / 255.0D;
            double var8 = (double)(var10 >> 0 & 255) / 255.0D;
            this.X.a("mobSpell", this.ab + (this.aH.nextDouble() - 0.5D) * (double)this.av, this.ac + this.aH.nextDouble() * (double)this.aw - (double)this.au, this.ad + (this.aH.nextDouble() - 0.5D) * (double)this.av, var4, var6, var8);
         }
      }

   }

   public void ax() {
      Iterator var1 = this.bL.keySet().iterator();

      while(var1.hasNext()) {
         Integer var2 = (Integer)var1.next();
         OPotionEffect var3 = (OPotionEffect)this.bL.get(var2);
         if(!this.X.I) {
            var1.remove();
            this.c(var3);
         }
      }

   }

   public Collection ay() {
      return this.bL.values();
   }

   public boolean a(OPotion var1) {
      return this.bL.containsKey(Integer.valueOf(var1.H));
   }

   public OPotionEffect b(OPotion var1) {
      return (OPotionEffect)this.bL.get(Integer.valueOf(var1.H));
   }

   public void e(OPotionEffect var1) {
       // CanaryMod - POTION_EFFECT HOOK
       PotionEffect pe = (PotionEffect) etc.getLoader().callHook(PluginLoader.Hook.POTION_EFFECT, this.entity, var1.potionEffect);
       if (pe == null) return;
       var1 = pe.potionEffect;
       if(this.d(var1)) {
    	   if(this.bL.containsKey(Integer.valueOf(var1.a()))) {
    		   ((OPotionEffect)this.bL.get(Integer.valueOf(var1.a()))).a(var1);
    		   this.b((OPotionEffect)this.bL.get(Integer.valueOf(var1.a())));
    	   } else {
    		   this.bL.put(Integer.valueOf(var1.a()), var1);
    		   this.a(var1);
    	   }
       }
   }

   public boolean d(OPotionEffect var1) {
      if(this.x_() == OEnumCreatureAttribute.b) {
         int var2 = var1.a();
         if(var2 == OPotion.l.H || var2 == OPotion.u.H) {
            return false;
         }
      }

      return true;
   }

   public boolean az() {
      return this.x_() == OEnumCreatureAttribute.b;
   }

   protected void a(OPotionEffect var1) {
      this.b = true;
   }

   protected void b(OPotionEffect var1) {
      this.b = true;
   }

   protected void c(OPotionEffect var1) {
      this.b = true;
   }

   protected float y() {
      float var1 = 1.0F;
      if(this.a(OPotion.c)) {
         var1 *= 1.0F + 0.2F * (float)(this.b(OPotion.c).c() + 1);
      }

      if(this.a(OPotion.d)) {
         var1 *= 1.0F - 0.15F * (float)(this.b(OPotion.d).c() + 1);
      }

      return var1;
   }

   public void c(double var1, double var3, double var5) {
      this.c(var1, var3, var5, this.ah, this.ai);
   }

   public boolean w_() {
      return false;
   }

   public OEnumCreatureAttribute x_() {
      return OEnumCreatureAttribute.a;
   }

   public void c(OItemStack var1) {
      this.X.a(this, "random.break", 0.8F, 0.8F + this.X.w.nextFloat() * 0.4F);

      for(int var2 = 0; var2 < 5; ++var2) {
         OVec3D var3 = OVec3D.b(((double)this.aH.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
         var3.a(-this.ai * 3.1415927F / 180.0F);
         var3.b(-this.ah * 3.1415927F / 180.0F);
         OVec3D var4 = OVec3D.b(((double)this.aH.nextFloat() - 0.5D) * 0.3D, (double)(-this.aH.nextFloat()) * 0.6D - 0.3D, 0.6D);
         var4.a(-this.ai * 3.1415927F / 180.0F);
         var4.b(-this.ah * 3.1415927F / 180.0F);
         var4 = var4.c(this.ab, this.ac + (double)this.n(), this.ad);
         this.X.a("iconcrack_" + var1.a().bM, var4.a, var4.b, var4.c, var3.a, var3.b + 0.05D, var3.c);
      }

   }
}
