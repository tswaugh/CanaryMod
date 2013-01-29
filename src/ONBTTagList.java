import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class ONBTTagList extends ONBTBase {

    List a = new ArrayList();
    private byte c;

    public ONBTTagList() {
        super("");
    }

    public ONBTTagList(String s) {
        super(s);
    }

    void a(DataOutput dataoutput) {
        if (!this.a.isEmpty()) {
            this.c = ((ONBTBase) this.a.get(0)).a();
        } else {
            this.c = 1;
        }

        try { // CanaryMod: surround with try-catch
            dataoutput.writeByte(this.c);
            dataoutput.writeInt(this.a.size());

            for (int i = 0; i < this.a.size(); ++i) {
                ((ONBTBase) this.a.get(i)).a(dataoutput);
            }
        } catch (IOException e) {
            ONetServerHandler.a.log(Level.SEVERE, null, e);
        }
    }

    void a(DataInput datainput) {
        try { // CanaryMod: surround with try-catch
            this.c = datainput.readByte();
            int i = datainput.readInt();

            this.a = new ArrayList();

            for (int j = 0; j < i; ++j) {
                ONBTBase onbtbase = ONBTBase.a(this.c, (String) null);

                onbtbase.a(datainput);
                this.a.add(onbtbase);
            }
        } catch (IOException e) {
            ONetServerHandler.a.log(Level.SEVERE, null, e);
        }
    }

    public byte a() {
        return (byte) 9;
    }

    public String toString() {
        return "" + this.a.size() + " entries of type " + ONBTBase.a(this.c);
    }

    public void a(ONBTBase onbtbase) {
        this.c = onbtbase.a();
        this.a.add(onbtbase);
    }

    public ONBTBase b(int i) {
        return (ONBTBase) this.a.get(i);
    }

    public int c() {
        return this.a.size();
    }

    public ONBTBase b() {
        ONBTTagList onbttaglist = new ONBTTagList(this.e());

        onbttaglist.c = this.c;
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            ONBTBase onbtbase = (ONBTBase) iterator.next();
            ONBTBase onbtbase1 = onbtbase.b();

            onbttaglist.a.add(onbtbase1);
        }

        return onbttaglist;
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            ONBTTagList onbttaglist = (ONBTTagList) object;

            if (this.c == onbttaglist.c) {
                return this.a.equals(onbttaglist.a);
            }
        }

        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ this.a.hashCode();
    }
}
