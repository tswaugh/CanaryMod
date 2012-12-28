import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ONBTTagList extends ONBTBase {

   protected List a = new ArrayList(); //CanaryMod: private -> protected
   private byte c;


   public ONBTTagList() {
      super("");
   }

   public ONBTTagList(String var1) {
      super(var1);
   }

   void a(DataOutput var1) {
      if(!this.a.isEmpty()) {
         this.c = ((ONBTBase)this.a.get(0)).a();
      } else {
         this.c = 1;
      }

      //CanaryMod: I had to add this try/catch block in
    try {
      var1.writeByte(this.c);
		var1.writeInt(this.a.size());
	} catch (IOException e) {
		e.printStackTrace();
	}
    //CanaryMod end

      for(int var2 = 0; var2 < this.a.size(); ++var2) {
         ((ONBTBase)this.a.get(var2)).a(var1);
      }

   }

   void a(DataInput var1) {
	   //CanaryMod: I had to add this try/catch block in
	   int var2 = 0;
	 try {
      this.c = var1.readByte();
		var2 = var1.readInt();
	} catch (IOException e) {
		e.printStackTrace();
	}
	 //CanaryMod end
      this.a = new ArrayList();

      for(int var3 = 0; var3 < var2; ++var3) {
         ONBTBase var4 = ONBTBase.a(this.c, (String)null);
         var4.a(var1);
         this.a.add(var4);
      }

   }

   public byte a() {
      return (byte)9;
   }

   public String toString() {
      return "" + this.a.size() + " entries of type " + ONBTBase.a(this.c);
   }

   public void a(ONBTBase var1) {
      this.c = var1.a();
      this.a.add(var1);
   }

   public ONBTBase b(int var1) {
      return (ONBTBase)this.a.get(var1);
   }

   public int c() {
      return this.a.size();
   }

   public ONBTBase b() {
      ONBTTagList var1 = new ONBTTagList(this.e());
      var1.c = this.c;
      Iterator var2 = this.a.iterator();

      while(var2.hasNext()) {
         ONBTBase var3 = (ONBTBase)var2.next();
         ONBTBase var4 = var3.b();
         var1.a.add(var4);
      }

      return var1;
   }

   public boolean equals(Object var1) {
      if(super.equals(var1)) {
         ONBTTagList var2 = (ONBTTagList)var1;
         if(this.c == var2.c) {
            return this.a.equals(var2.a);
         }
      }

      return false;
   }

   public int hashCode() {
      return super.hashCode() ^ this.a.hashCode();
   }
}
