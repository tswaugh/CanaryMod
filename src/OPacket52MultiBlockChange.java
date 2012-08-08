import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class OPacket52MultiBlockChange extends OPacket {

    public int a;
    public int b;
    public byte[] c;
    public int d;
    private static byte[] e = new byte[0];

    public OPacket52MultiBlockChange() {
        super();
        this.p = true;
    }

    public OPacket52MultiBlockChange(int i, int j, short[] ashort, int k, OWorld oworld) {
        super();
        this.p = true;
        this.a = i;
        this.b = j;
        this.d = k;
        int l = 4 * k;
        OChunk ochunk = oworld.d(i, j);

        try {
            if (k >= 64) {
                System.out.println("ChunkTilesUpdatePacket compress " + k);
                if (e.length < l) {
                    e = new byte[l];
                }
            } else {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(l);
                DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);

                for (int i1 = 0; i1 < k; ++i1) {
                    int j1 = ashort[i1] >> 12 & 15;
                    int k1 = ashort[i1] >> 8 & 15;
                    int l1 = ashort[i1] & 255;

                    dataoutputstream.writeShort(ashort[i1]);
                    dataoutputstream.writeShort((short) ((ochunk.a(j1, l1, k1) & 4095) << 4 | ochunk.c(j1, l1, k1) & 15));
                }

                this.c = bytearrayoutputstream.toByteArray();
                if (this.c.length != l) {
                    throw new RuntimeException("Expected length " + l + " doesn\'t match received length " + this.c.length);
                }
            }
        } catch (IOException ioexception) {
            System.err.println(ioexception.getMessage());
            this.c = null;
        }

    }

    public void a(DataInputStream datainputstream) {
        try {
            this.a = datainputstream.readInt();
            this.b = datainputstream.readInt();
            this.d = datainputstream.readShort() & '\uffff';
            int i = datainputstream.readInt();

            if (i > 0) {
                this.c = new byte[i];
                datainputstream.readFully(this.c);
            }
        } catch (ioexceptionxception ioexception) {}

    }

    public void a(DataOutputStream dataoutputstream) {
        try {
            dataoutputstream.writeInt(this.a);
            dataoutputstream.writeInt(this.b);
            dataoutputstream.writeShort((short) this.d);
            if (this.c != null) {
                dataoutputstream.writeInt(this.c.length);
                dataoutputstream.write(this.c);
            } else {
                dataoutputstream.writeInt(0);
            }
        } catch (ioexceptionxception ioexception) {}

    }

    public void a(ONetHandler onethandler) {
        onethandler.a(this);
    }

    public int a() {
        return 10 + this.d * 4;
    }

}
