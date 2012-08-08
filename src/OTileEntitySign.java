
public class OTileEntitySign extends OTileEntity {

    public String[] a = new String[] { "", "", "", ""};
    public int b = -1;
    private boolean c = true;

    public OTileEntitySign() {
        super();
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Text1", this.a[0]);
        onbttagcompound.a("Text2", this.a[1]);
        onbttagcompound.a("Text3", this.a[2]);
        onbttagcompound.a("Text4", this.a[3]);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.c = false;
        super.a(onbttagcompound);

        for (int i = 0; i < 4; ++i) {
            this.a[i] = onbttagcompound.j("Text" + (i + 1));
            if (this.a[i].length() > 15) {
                this.a[i] = this.a[i].substring(0, 15);
            }
        }

    }

    public OPacket d() {
        String[] astring = new String[4];

        for (int i = 0; i < 4; ++i) {
            astring[i] = this.a[i];
        }

        return new OPacket130UpdateSign(this.l, this.m, this.n, astring);
    }

    public boolean c() {
        return this.c;
    }
}
