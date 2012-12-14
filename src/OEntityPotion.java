import java.util.Iterator;
import java.util.List;

public class OEntityPotion extends OEntityThrowable {

   private OItemStack c;


   public OEntityPotion(OWorld var1) {
      super(var1);
   }

   public OEntityPotion(OWorld var1, OEntityLiving var2, int var3) {
      this(var1, var2, new OItemStack(OItem.bs, 1, var3));
   }

   public OEntityPotion(OWorld var1, OEntityLiving var2, OItemStack var3) {
      super(var1, var2);
      this.c = var3;
   }

   public OEntityPotion(OWorld var1, double var2, double var4, double var6, OItemStack var8) {
      super(var1, var2, var4, var6);
      this.c = var8;
   }

   protected float g() {
      return 0.05F;
   }

   protected float c() {
      return 0.5F;
   }

   protected float d() {
      return -20.0F;
   }

   public void a(int var1) {
      if(this.c == null) {
         this.c = new OItemStack(OItem.bs, 1, 0);
      }

      this.c.b(var1);
   }

   public int i() {
      if(this.c == null) {
         this.c = new OItemStack(OItem.bs, 1, 0);
      }

      return this.c.j();
   }

   protected void a(OMovingObjectPosition var1) {
      if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Projectile(this), var1.g == null ? null : var1.g.getEntity()) && !this.p.J) {
         List var2 = OItem.bs.l(this.c);
         if(var2 != null && !var2.isEmpty()) {
            OAxisAlignedBB var3 = this.D.b(4.0D, 2.0D, 4.0D);
            List var4 = this.p.a(OEntityLiving.class, var3);
            if(var4 != null && !var4.isEmpty()) {
               Iterator var5 = var4.iterator();

               while(var5.hasNext()) {
                  OEntityLiving var6 = (OEntityLiving)var5.next();
                  double var7 = this.e(var6);
                  if(var7 < 16.0D) {
                     double var9 = 1.0D - Math.sqrt(var7) / 4.0D;
                     if(var6 == var1.g) {
                        var9 = 1.0D;
                     }

                     Iterator var11 = var2.iterator();

                     while(var11.hasNext()) {
                        OPotionEffect var12 = (OPotionEffect)var11.next();
                        int var13 = var12.a();
                        if(OPotion.a[var13].b()) {
                           OPotion.a[var13].a(this.h(), var6, var12.c(), var9);
                        } else {
                           int var14 = (int)(var9 * (double)var12.b() + 0.5D);
                           if(var14 > 20) {
                              var6.d(new OPotionEffect(var13, var14, var12.c()));
                           }
                        }
                     }
                  }
               }
            }
         }

         this.p.f(2002, (int)Math.round(this.t), (int)Math.round(this.u), (int)Math.round(this.v), this.i());
         this.x();
      }

   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      if(var1.b("Potion")) {
         this.c = OItemStack.a(var1.l("Potion"));
      } else {
         this.a(var1.e("potionValue"));
      }

      if(this.c == null) {
         this.x();
      }

   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      if(this.c != null) {
         var1.a("Potion", this.c.b(new ONBTTagCompound()));
      }

   }
}
