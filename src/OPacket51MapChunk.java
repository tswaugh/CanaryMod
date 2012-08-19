import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


public class OPacket51MapChunk extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public byte[] e;
    public boolean f;
    private int g;
    private int h;
    private static byte[] i = new byte[0];

    public OPacket51MapChunk() {
        super();
        this.p = true;
    }

    public OPacket51MapChunk(OChunk ochunk, boolean flag, int i) {
        super();
        this.p = true;
        this.a = ochunk.g;
        this.b = ochunk.h;
        this.f = flag;
        if (flag) {
            i = '\uffff';
        }

        OExtendedBlockStorage[] aoextendedblockstorage = ochunk.h();
        int j = 0;
        int k = 0;

        int l;

        for (l = 0; l < aoextendedblockstorage.length; ++l) {
            if (aoextendedblockstorage[l] != null && (!flag || !aoextendedblockstorage[l].a()) && (i & 1 << l) != 0) {
                this.c |= 1 << l;
                ++j;
                if (aoextendedblockstorage[l].h() != null) {
                    this.d |= 1 << l;
                    ++k;
                }
            }
        }

        l = 2048 * (5 * j + k);
        if (flag) {
            l += 256;
        }

        if (OPacket51MapChunk.i.length < l) {
            OPacket51MapChunk.i = new byte[l];
        }

        byte[] abyte = OPacket51MapChunk.i;
        int i1 = 0;

        int j1;

        for (j1 = 0; j1 < aoextendedblockstorage.length; ++j1) {
            if (aoextendedblockstorage[j1] != null && (!flag || !aoextendedblockstorage[j1].a()) && (i & 1 << j1) != 0) {
                byte[] abyte1 = aoextendedblockstorage[j1].g();

                System.arraycopy(abyte1, 0, abyte, i1, abyte1.length);
                i1 += abyte1.length;
            }
        }

        ONibbleArray onibblearray;

        for (j1 = 0; j1 < aoextendedblockstorage.length; ++j1) {
            if (aoextendedblockstorage[j1] != null && (!flag || !aoextendedblockstorage[j1].a()) && (i & 1 << j1) != 0) {
                onibblearray = aoextendedblockstorage[j1].i();
                System.arraycopy(onibblearray.a, 0, abyte, i1, onibblearray.a.length);
                i1 += onibblearray.a.length;
            }
        }

        for (j1 = 0; j1 < aoextendedblockstorage.length; ++j1) {
            if (aoextendedblockstorage[j1] != null && (!flag || !aoextendedblockstorage[j1].a()) && (i & 1 << j1) != 0) {
                onibblearray = aoextendedblockstorage[j1].j();
                System.arraycopy(onibblearray.a, 0, abyte, i1, onibblearray.a.length);
                i1 += onibblearray.a.length;
            }
        }

        for (j1 = 0; j1 < aoextendedblockstorage.length; ++j1) {
            if (aoextendedblockstorage[j1] != null && (!flag || !aoextendedblockstorage[j1].a()) && (i & 1 << j1) != 0) {
                onibblearray = aoextendedblockstorage[j1].k();
                System.arraycopy(onibblearray.a, 0, abyte, i1, onibblearray.a.length);
                i1 += onibblearray.a.length;
            }
        }

        if (k > 0) {
            for (j1 = 0; j1 < aoextendedblockstorage.length; ++j1) {
                if (aoextendedblockstorage[j1] != null && (!flag || !aoextendedblockstorage[j1].a()) && aoextendedblockstorage[j1].h() != null && (i & 1 << j1) != 0) {
                    onibblearray = aoextendedblockstorage[j1].h();
                    System.arraycopy(onibblearray.a, 0, abyte, i1, onibblearray.a.length);
                    i1 += onibblearray.a.length;
                }
            }
        }

        if (flag) {
            byte[] abyte2 = ochunk.l();

            System.arraycopy(abyte2, 0, abyte, i1, abyte2.length);
            i1 += abyte2.length;
        }

        Deflater deflater = new Deflater(-1);
        boolean flag1 = false;

        try {
            flag1 = true;
            deflater.setInput(abyte, 0, i1);
            deflater.finish();
            this.e = new byte[i1];
            this.g = deflater.deflate(this.e);
            flag1 = false;
        } finally {
            if (flag1) {
                deflater.end();
            }
        }

        deflater.end();
    }

    public void a(DataInputStream datainputstream) {
        try {
            this.a = datainputstream.readInt();
            this.b = datainputstream.readInt();
            this.f = datainputstream.readBoolean();
            this.c = datainputstream.readShort();
            this.d = datainputstream.readShort();
            this.g = datainputstream.readInt();
            this.h = datainputstream.readInt();
            if (i.length < this.g) {
                i = new byte[this.g];
            }
            datainputstream.readFully(i, 0, this.g);
    
            int i = 0;
    
            int j;
    
            for (j = 0; j < 16; ++j) {
                i += this.c >> j & 1;
            }
    
            j = 12288 * i;
            if (this.f) {
                j += 256;
            }
    
            this.e = new byte[j];
            Inflater inflater = new Inflater();
    
            inflater.setInput(OPacket51MapChunk.i, 0, this.g);
            boolean flag = false;
    
            try {
                flag = true;
                inflater.inflate(this.e);
                flag = false;
            } catch (DataFormatException dataformatexception) {
                throw new IOException("Bad compressed data format");
            } finally {
                if (flag) {
                    inflater.end();
                }
            }
    
            inflater.end();
        } catch (IOException ioexception) {}
    }

    public void a(DataOutputStream dataoutputstream) {
        try {
            dataoutputstream.writeInt(this.a);
            dataoutputstream.writeInt(this.b);
            dataoutputstream.writeBoolean(this.f);
            dataoutputstream.writeShort((short) (this.c & '\uffff'));
            dataoutputstream.writeShort((short) (this.d & '\uffff'));
            dataoutputstream.writeInt(this.g);
            dataoutputstream.writeInt(this.h);
            dataoutputstream.write(this.e, 0, this.g);
        } catch (IOException ioexception) {}
    }

    public void a(ONetHandler onethandler) {
        onethandler.a(this);
    }

    public int a() {
        return 17 + this.g;
    }

}
