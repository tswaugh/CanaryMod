import java.util.Arrays;


public class OTileEntityChest extends OTileEntity implements OIInventory, Container<OItemStack> {

   public OItemStack[] i = new OItemStack[36];
   public boolean a = false;
   public OTileEntityChest b;
   public OTileEntityChest c;
   public OTileEntityChest d;
   public OTileEntityChest e;
   public float f;
   public float g;
   public int h;
   public int j;
   private String name = "Chest";

   public OTileEntityChest() {
      super();
   }

   public int d() {
      return 27;
   }

   public OItemStack c(int var1) {
      return this.i[var1];
   }

   public OItemStack a(int var1, int var2) {
      if(this.i[var1] != null) {
         OItemStack var3;
         if(this.i[var1].a <= var2) {
            var3 = this.i[var1];
            this.i[var1] = null;
            this.i();
            return var3;
         } else {
            var3 = this.i[var1].a(var2);
            if(this.i[var1].a == 0) {
               this.i[var1] = null;
            }

            this.i();
            return var3;
         }
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      this.i[var1] = var2;
      if(var2 != null && var2.a > this.f()) {
         var2.a = this.f();
      }

      this.i();
   }

   public String e() {
      return "Chest";
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      ONBTTagList var2 = var1.m("Items");
      this.i = new OItemStack[this.d()];

      for(int var3 = 0; var3 < var2.d(); ++var3) {
         ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
         int var5 = var4.d("Slot") & 255;
         if(var5 >= 0 && var5 < this.i.length) {
            this.i[var5] = OItemStack.a(var4);
         }
      }

   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      ONBTTagList var2 = new ONBTTagList();

      for(int var3 = 0; var3 < this.i.length; ++var3) {
         if(this.i[var3] != null) {
            ONBTTagCompound var4 = new ONBTTagCompound();
            var4.a("Slot", (byte)var3);
            this.i[var3].b(var4);
            var2.a((ONBTBase)var4);
         }
      }

      var1.a("Items", (ONBTBase)var2);
   }

   public int f() {
      return 64;
   }

   public boolean a_(OEntityPlayer var1) {
      return this.k.l(this.l, this.m, this.n) != this?false:var1.g((double)this.l + 0.5D, (double)this.m + 0.5D, (double)this.n + 0.5D) <= 64.0D;
   }

   public void c() {
      super.c();
      this.a = false;
   }

   public void g() {
      if(!this.a) {
         this.a = true;
         this.b = null;
         this.c = null;
         this.d = null;
         this.e = null;
         if(this.k.a(this.l - 1, this.m, this.n) == OBlock.au.bM) {
            this.d = (OTileEntityChest)this.k.l(this.l - 1, this.m, this.n);
         }

         if(this.k.a(this.l + 1, this.m, this.n) == OBlock.au.bM) {
            this.c = (OTileEntityChest)this.k.l(this.l + 1, this.m, this.n);
         }

         if(this.k.a(this.l, this.m, this.n - 1) == OBlock.au.bM) {
            this.b = (OTileEntityChest)this.k.l(this.l, this.m, this.n - 1);
         }

         if(this.k.a(this.l, this.m, this.n + 1) == OBlock.au.bM) {
            this.e = (OTileEntityChest)this.k.l(this.l, this.m, this.n + 1);
         }

         if(this.b != null) {
            this.b.c();
         }

         if(this.e != null) {
            this.e.c();
         }

         if(this.c != null) {
            this.c.c();
         }

         if(this.d != null) {
            this.d.c();
         }

      }
   }

   public void a() {
      super.a();
      this.g();
      if(++this.j % 20 * 4 == 0) {
         this.k.e(this.l, this.m, this.n, 1, this.h);
      }

      this.g = this.f;
      float var1 = 0.1F;
      double var4;
      if(this.h > 0 && this.f == 0.0F && this.b == null && this.d == null) {
         double var2 = (double)this.l + 0.5D;
         var4 = (double)this.n + 0.5D;
         if(this.e != null) {
            var4 += 0.5D;
         }

         if(this.c != null) {
            var2 += 0.5D;
         }

         this.k.a(var2, (double)this.m + 0.5D, var4, "random.chestopen", 0.5F, this.k.w.nextFloat() * 0.1F + 0.9F);
      }

      if(this.h == 0 && this.f > 0.0F || this.h > 0 && this.f < 1.0F) {
         float var6 = this.f;
         if(this.h > 0) {
            this.f += var1;
         } else {
            this.f -= var1;
         }

         if(this.f > 1.0F) {
            this.f = 1.0F;
         }

         float var7 = 0.5F;
         if(this.f < var7 && var6 >= var7 && this.b == null && this.d == null) {
            var4 = (double)this.l + 0.5D;
            double var8 = (double)this.n + 0.5D;
            if(this.e != null) {
               var8 += 0.5D;
            }

            if(this.c != null) {
               var4 += 0.5D;
            }

            this.k.a(var4, (double)this.m + 0.5D, var8, "random.chestclosed", 0.5F, this.k.w.nextFloat() * 0.1F + 0.9F);
         }

         if(this.f < 0.0F) {
            this.f = 0.0F;
         }
      }

   }

   public void b(int var1, int var2) {
      if(var1 == 1) {
         this.h = var2;
      }

   }

   public void k() {
      ++this.h;
      this.k.e(this.l, this.m, this.n, 1, this.h);
   }

   public void z_() {
      --this.h;
      this.k.e(this.l, this.m, this.n, 1, this.h);
   }

   public void h() {
      this.c();
      this.g();
      super.h();
   }
   
   public OItemStack[] getContents() {
       return Arrays.copyOf(i, getContentsSize());
   }

   public void setContents(OItemStack[] values) {
       int size = getContentsSize();

       for (int i = 0; i < size; i++)
           setContentsAt(i, values[i]);
   }

   public OItemStack getContentsAt(int index) {
       return c(index);
   }

   public void setContentsAt(int index, OItemStack value) {
       a(index, value);
   }

   public int getContentsSize() {
       return d();
   }

   public String getName() {
       return name;
   }

   public void setName(String value) {
       name = value;
   }
}
