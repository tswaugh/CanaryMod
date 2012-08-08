
public class OInventoryLargeChest implements OIInventory, Container<OItemStack> {

    private String a;
    private OIInventory b;
    private OIInventory c;

    public OInventoryLargeChest(String s, OIInventory oiinventory, OIInventory oiinventory1) {
        super();
        this.a = s;
        if (oiinventory == null) {
            oiinventory = oiinventory1;
        }

        if (oiinventory1 == null) {
            oiinventory1 = oiinventory;
        }

        this.b = oiinventory;
        this.c = oiinventory1;
    }

    public int c() {
        return this.b.c() + this.c.c();
    }

    public String e() {
        return this.a;
    }

    public OItemStack g_(int i) {
        return i >= this.b.c() ? this.c.g_(i - this.b.c()) : this.b.g_(i);
    }

    public OItemStack a(int i, int j) {
        return i >= this.b.c() ? this.c.a(i - this.b.c(), j) : this.b.a(i, j);
    }

    public OItemStack b(int i) {
        return i >= this.b.c() ? this.c.b(i - this.b.c()) : this.b.b(i);
    }

    public void a(int i, OItemStack oitemstack) {
        if (i >= this.b.c()) {
            this.c.a(i - this.b.c(), oitemstack);
        } else {
            this.b.a(i, oitemstack);
        }

    }

    public int a() {
        return this.b.a();
    }

    public void G_() {
        this.b.G_();
        this.c.G_();
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.b.a(oentityplayer) && this.c.a(oentityplayer);
    }

    public void f() {
        this.b.f();
        this.c.f();
    }

    public void g() {
        this.b.g();
        this.c.g();
    }
    
    public OItemStack[] getContents() {
        int size = getContentsSize();
        OItemStack[] result = new OItemStack[size];

        for (int i = 0; i < size; i++) {
            result[i] = getContentsAt(i);
        }
        return result;
    }

    public void setContents(OItemStack[] aoitemstack) {
        int size = getContentsSize();

        for (int i = 0; i < size; i++) {
            setContentsAt(i, aoitemstack[i]);
        }
    }

    public OItemStack getContentsAt(int i) {
        return g_(i);
    }

    public void setContentsAt(int i, OItemStack oitemstack) {
        a(i, oitemstack);
    }

    public int getContentsSize() {
        return c();
    }

    public String getName() {
        return a;
    }

    public void setName(String s) {
        a = s;
    }

    public Block getChestBlock() {
        if (b instanceof OTileEntityChest) {
            OTileEntityChest block = (OTileEntityChest) b;

            return block.k.world.getBlockAt(block.l, block.m, block.n);
        }
        if (c instanceof OTileEntityChest) {
            OTileEntityChest block = (OTileEntityChest) c;

            return block.k.world.getBlockAt(block.l, block.m, block.n);
        }
        return null;
    }
}
