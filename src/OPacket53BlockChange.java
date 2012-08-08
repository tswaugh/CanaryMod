import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class OPacket53BlockChange extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public OPacket53BlockChange() {
        super();
        this.p = true;
    }

    public OPacket53BlockChange(int i, int j, int k, OWorld oworld) {
        super();
        this.p = true;
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = oworld.a(i, j, k);
        this.e = oworld.c(i, j, k);
    }

    public void a(DataInputStream datainputstream) {
        try {
            this.a = datainputstream.readInt();
            this.b = datainputstream.read();
            this.c = datainputstream.readInt();
            this.d = datainputstream.read();
            this.e = datainputstream.read();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        try {
            dataoutputstream.writeInt(this.a);
            dataoutputstream.write(this.b);
            dataoutputstream.writeInt(this.c);
            dataoutputstream.write(this.d);
            dataoutputstream.write(this.e);
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }

    public void a(ONetHandler onethandler) {
        onethandler.a(this);
    }

    public int a() {
        return 11;
    }
}
