
public class OContainerChest extends OContainer {

    private OIInventory a;
    private int b;
    // CanaryMod: silenced - Used to determine wether the chest should be opened or closed stealthily
    private boolean silenced;

    public OContainerChest(OIInventory oiinventory, OIInventory oiinventory1, boolean flag) {
        super();
        this.a = oiinventory1;
        this.b = oiinventory1.c() / 9;
        this.silenced = flag;
        if (!this.silenced) {
            oiinventory1.f();
        }
        int i = (this.b - 4) * 18;

        int j;
        int k;

        for (j = 0; j < this.b; ++j) {
            for (k = 0; k < 9; ++k) {
                this.a(new OSlot(oiinventory1, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                this.a(new OSlot(oiinventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }

        for (j = 0; j < 9; ++j) {
            this.a(new OSlot(oiinventory, j, 8 + j * 18, 161 + i));
        }

    }

    public boolean b(OEntityPlayer oentityplayer) {
        return this.a.a(oentityplayer);
    }

    public OItemStack a(int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.e.get(i);

        if (oslot != null && oslot.c()) {
            OItemStack oitemstack1 = oslot.b();

            oitemstack = oitemstack1.j();
            if (i < this.b * 9) {
                if (!this.a(oitemstack1, this.b * 9, this.e.size(), true)) {
                    return null;
                }
            } else if (!this.a(oitemstack1, 0, this.b * 9, false)) {
                return null;
            }

            if (oitemstack1.a == 0) {
                oslot.d((OItemStack) null);
            } else {
                oslot.d();
            }
        }

        return oitemstack;
    }

    public void a(OEntityPlayer oentityplayer) {
        super.a(oentityplayer);
        if (!this.silenced) {
            this.a.g();
        }
    }
}
