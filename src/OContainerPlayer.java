
public class OContainerPlayer extends OContainer {

    public OInventoryCrafting a;
    public OIInventory b;
    public boolean c;

    public OContainerPlayer(OInventoryPlayer oinventoryplayer) {
        this(oinventoryplayer, true);
    }

    public OContainerPlayer(OInventoryPlayer oinventoryplayer, boolean flag) {
        super();
        this.a = new OInventoryCrafting(this, 2, 2);
        this.b = new OInventoryCraftResult();
        this.c = false;
        this.c = flag;
        this.a((OSlot) (new OSlotCrafting(oinventoryplayer.d, this.a, this.b, 0, 144, 36)));

        int i;
        int j;

        for (i = 0; i < 2; ++i) {
            for (j = 0; j < 2; ++j) {
                this.a(new OSlot(this.a, j + i * 2, 88 + j * 18, 26 + i * 18));
            }
        }

        for (i = 0; i < 4; ++i) {
            this.a((OSlot) (new OSlotArmor(this, oinventoryplayer, oinventoryplayer.c() - 1 - i, 8, 8 + i * 18, i)));
        }

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.a(new OSlot(oinventoryplayer, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.a(new OSlot(oinventoryplayer, i, 8 + i * 18, 142));
        }

        this.a((OIInventory) this.a);
    }

    // Canarymod - send custom recipes result to client
    public void a(OIInventory oiinventory) {
        OItemStack craftresult = OCraftingManager.a().a(this.a);

        this.b.a(0, craftresult);
        if (super.g.size() < 1) {
            return;
        }      
        OEntityPlayerMP player = (OEntityPlayerMP) super.g.get(0); 

        player.a.b(new OPacket103SetSlot(player.l.f, 0, craftresult));
    }

    public void a(OEntityPlayer oentityplayer) {
        super.a(oentityplayer);

        for (int i = 0; i < 4; ++i) {
            OItemStack oitemstack = this.a.b(i);

            if (oitemstack != null) {
                oentityplayer.b(oitemstack);
            }
        }

        this.b.a(0, (OItemStack) null);
    }

    public boolean b(OEntityPlayer oentityplayer) {
        return true;
    }

    public OItemStack a(int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.e.get(i);

        if (oslot != null && oslot.c()) {
            OItemStack oitemstack1 = oslot.b();

            oitemstack = oitemstack1.j();
            if (i == 0) {
                if (!this.a(oitemstack1, 9, 45, true)) {
                    return null;
                }

                oslot.a(oitemstack1, oitemstack);
            } else if (i >= 9 && i < 36) {
                if (!this.a(oitemstack1, 36, 45, false)) {
                    return null;
                }
            } else if (i >= 36 && i < 45) {
                if (!this.a(oitemstack1, 9, 36, false)) {
                    return null;
                }
            } else if (!this.a(oitemstack1, 9, 45, false)) {
                return null;
            }

            if (oitemstack1.a == 0) {
                oslot.d((OItemStack) null);
            } else {
                oslot.d();
            }

            if (oitemstack1.a == oitemstack.a) {
                return null;
            }

            oslot.c(oitemstack1);
        }

        return oitemstack;
    }
}
