
public class OEntityItem extends OEntity {

   public OItemStack a;
   private int e;
   public int b = 0;
   public int c;
   private int f = 5;
   public float d = (float)(Math.random() * 3.141592653589793D * 2.0D);
   // CanaryMod Start
   ItemEntity item = new ItemEntity(this);

   // CanaryMod End

   public OEntityItem(OWorld var1, double var2, double var4, double var6, OItemStack var8) {
      super(var1);
      this.b(0.25F, 0.25F);
      this.au = this.aw / 2.0F;
      this.e(var2, var4, var6);
      this.a = var8;
      this.ah = (float)(Math.random() * 360.0D);
      this.ae = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
      this.af = 0.20000000298023224D;
      this.ag = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
   }

   protected boolean c() {
      return false;
   }

   public OEntityItem(OWorld var1) {
      super(var1);
      this.b(0.25F, 0.25F);
      this.au = this.aw / 2.0F;
   }

   protected void a() {}

   public void b() {
      super.b();
      if(this.c > 0) {
         --this.c;
      }

      this.Y = this.ab;
      this.Z = this.ac;
      this.aa = this.ad;
      this.af -= 0.03999999910593033D;
      if(this.X.d(OMathHelper.b(this.ab), OMathHelper.b(this.ac), OMathHelper.b(this.ad)) == OMaterial.h) {
         this.af = 0.20000000298023224D;
         this.ae = (double)((this.aH.nextFloat() - this.aH.nextFloat()) * 0.2F);
         this.ag = (double)((this.aH.nextFloat() - this.aH.nextFloat()) * 0.2F);
         this.X.a(this, "random.fizz", 0.4F, 2.0F + this.aH.nextFloat() * 0.4F);
      }

      this.i(this.ab, (this.al.b + this.al.e) / 2.0D, this.ad);
      this.a(this.ae, this.af, this.ag);
      float var1 = 0.98F;
      if(this.am) {
         var1 = 0.58800006F;
         int var2 = this.X.a(OMathHelper.b(this.ab), OMathHelper.b(this.al.b) - 1, OMathHelper.b(this.ad));
         if(var2 > 0) {
            var1 = OBlock.k[var2].ca * 0.98F;
         }
      }

      this.ae *= (double)var1;
      this.af *= 0.9800000190734863D;
      this.ag *= (double)var1;
      if(this.am) {
         this.af *= -0.5D;
      }

      ++this.e;
      ++this.b;
      if(this.b >= 6000) {
          // CanaryMod onEntityDespawn
          if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENTITY_DESPAWN, item)) {
        	  this.J();
          } else {
              this.b = 0;
          }
      }

   }

   public boolean e_() {
      return this.X.a(this.al, OMaterial.g, this);
   }

   protected void a(int var1) {
      this.a(ODamageSource.a, var1);
   }

   public boolean a(ODamageSource var1, int var2) {
      this.ad();
      this.f -= var2;
      if(this.f <= 0) {
         this.J();
      }

      return false;
   }

   public void a(ONBTTagCompound var1) {
      var1.a("Health", (short)((byte)this.f));
      var1.a("Age", (short)this.b);
      var1.a("Item", this.a.b(new ONBTTagCompound()));
   }

   public void b(ONBTTagCompound var1) {
      this.f = var1.e("Health") & 255;
      this.b = var1.e("Age");
      ONBTTagCompound var2 = var1.l("Item");
      this.a = OItemStack.a(var2);
      if(this.a == null) {
         this.J();
      }

   }

   public void a(OEntityPlayer var1) {
      if(!this.X.I) {
         int var2 = this.a.a;
         // CanaryMod: First simulate the pickup and call the hooks
         if (this.c == 0 && var1.k.canPickup(this)) {
	         if(var1.k.a(this.a)) {
	            if(this.a.c == OBlock.J.bM) {
	               var1.a((OStatBase)OAchievementList.g);
	            }
	
	            if(this.a.c == OItem.aE.bM) {
	               var1.a((OStatBase)OAchievementList.t);
	            }
	
	            if(this.a.c == OItem.m.bM) {
	               var1.a((OStatBase)OAchievementList.w);
	            }
	
	            if(this.a.c == OItem.bn.bM) {
	               var1.a((OStatBase)OAchievementList.z);
	            }
	
	            this.X.a(this, "random.pop", 0.2F, ((this.aH.nextFloat() - this.aH.nextFloat()) * 0.7F + 1.0F) * 2.0F);
	            var1.a((OEntity)this, var2);
	            if(this.a.a <= 0) {
	               this.J();
	            }
	         }
         }
      }
   }

   public String U() {
      return OStatCollector.a("item." + this.a.k());
   }
}
