import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class OPacket20NamedEntitySpawn extends OPacket {

   public int a;
   public String b;
   public int c;
   public int d;
   public int e;
   public byte f;
   public byte g;
   public int h;
   private ODataWatcher i;
   private List j;


   public OPacket20NamedEntitySpawn() {}

   public OPacket20NamedEntitySpawn(OEntityPlayer var1) {
      this.a = var1.k;
      this.b = var1.getDisplayName(); // CanaryMod: use display name
      this.c = OMathHelper.c(var1.t * 32.0D);
      this.d = OMathHelper.c(var1.u * 32.0D);
      this.e = OMathHelper.c(var1.v * 32.0D);
      this.f = (byte)((int)(var1.z * 256.0F / 360.0F));
      this.g = (byte)((int)(var1.A * 256.0F / 360.0F));
      OItemStack var2 = var1.bJ.g();
      this.h = var2 == null?0:var2.c;
      this.i = var1.v();
   }

   public void a(DataInputStream var1) {
       try {
          this.a = var1.readInt();
          this.b = a(var1, 16);
          this.c = var1.readInt();
          this.d = var1.readInt();
          this.e = var1.readInt();
          this.f = var1.readByte();
          this.g = var1.readByte();
          this.h = var1.readShort();
       } catch (IOException e) {
           //CanaryMod: had to add try/catch
       }
      this.j = ODataWatcher.a(var1);
   }

   public void a(DataOutputStream var1) {
       try {
          var1.writeInt(this.a);
          a(this.b, var1);
          var1.writeInt(this.c);
          var1.writeInt(this.d);
          var1.writeInt(this.e);
          var1.writeByte(this.f);
          var1.writeByte(this.g);
          var1.writeShort(this.h);
       } catch (IOException e) {
           //CanaryMod: had to add try/catch
       }
      this.i.a(var1);
   }

   public void a(ONetHandler var1) {
      var1.a(this);
   }

   public int a() {
      return 28;
   }
}
