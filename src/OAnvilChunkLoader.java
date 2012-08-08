import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class OAnvilChunkLoader implements OIChunkLoader, OIThreadedFileIO {

    private List a = new ArrayList();
    private Set b = new HashSet();
    private Object c = new Object();
    private final File d;

    public OAnvilChunkLoader(File file1) {
        super();
        this.d = file1;
    }

    public OChunk a(OWorld oworld, int i, int j) {
        ONBTTagCompound onbttagcompound = null;
        OChunkCoordIntPair ochunkcoordintpair = new OChunkCoordIntPair(i, j);
        Object object = this.c;

        synchronized (this.c) {
            if (this.b.contains(ochunkcoordintpair)) {
                for (int k = 0; k < this.a.size(); ++k) {
                    if (((OAnvilChunkLoaderPending) this.a.get(k)).a.equals(ochunkcoordintpair)) {
                        onbttagcompound = ((OAnvilChunkLoaderPending) this.a.get(k)).b;
                        break;
                    }
                }
            }
        }

        if (onbttagcompound == null) {
            DataInputStream datainputstream = ORegionFileCache.b(this.d, i, j);

            if (datainputstream == null) {
                return null;
            }

            onbttagcompound = OCompressedStreamTools.a((DataInput) datainputstream);
        }

        return this.a(oworld, i, j, onbttagcompound);
    }

    protected OChunk a(OWorld oworld, int i, int j, ONBTTagCompound onbttagcompound) {
        if (!onbttagcompound.c("Level")) {
            System.out.println("Chunk file at " + i + "," + j + " is missing level data, skipping");
            return null;
        } else if (!onbttagcompound.m("Level").c("Sections")) {
            System.out.println("Chunk file at " + i + "," + j + " is missing block data, skipping");
            return null;
        } else {
            OChunk ochunk = this.a(oworld, onbttagcompound.m("Level"));

            if (!ochunk.a(i, j)) {
                System.out.println("Chunk file at " + i + "," + j + " is in the wrong location; relocating. (Expected " + i + ", " + j + ", got " + ochunk.g + ", " + ochunk.h + ")");
                onbttagcompound.a("xPos", i);
                onbttagcompound.a("zPos", j);
                ochunk = this.a(oworld, onbttagcompound.m("Level"));
            }

            ochunk.i();
            return ochunk;
        }
    }

    public void a(OWorld oworld, OChunk ochunk) {
        oworld.m();

        try {
            ONBTTagCompound onbttagcompound = new ONBTTagCompound();
            ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

            onbttagcompound.a("Level", (ONBTBase) onbttagcompound1);
            this.a(ochunk, oworld, onbttagcompound1);
            this.a(ochunk.k(), onbttagcompound);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    protected void a(OChunkCoordIntPair ochunkcoordintpair, ONBTTagCompound onbttagcompound) {
        Object object = this.c;

        synchronized (this.c) {
            if (this.b.contains(ochunkcoordintpair)) {
                for (int i = 0; i < this.a.size(); ++i) {
                    if (((OAnvilChunkLoaderPending) this.a.get(i)).a.equals(ochunkcoordintpair)) {
                        this.a.set(i, new OAnvilChunkLoaderPending(ochunkcoordintpair, onbttagcompound));
                        return;
                    }
                }
            }

            this.a.add(new OAnvilChunkLoaderPending(ochunkcoordintpair, onbttagcompound));
            this.b.add(ochunkcoordintpair);
            OThreadedFileIOBase.a.a(this);
        }
    }

    public boolean c() {
        OAnvilChunkLoaderPending oanvilchunkloaderpending = null;
        Object object = this.c;

        synchronized (this.c) {
            if (this.a.size() <= 0) {
                return false;
            }

            oanvilchunkloaderpending = (OAnvilChunkLoaderPending) this.a.remove(0);
            this.b.remove(oanvilchunkloaderpending.a);
        }

        if (oanvilchunkloaderpending != null) {
            try {
                this.a(oanvilchunkloaderpending);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return true;
    }

    private void a(OAnvilChunkLoaderPending oanvilchunkloaderpending) throws IOException {
        DataOutputStream dataoutputstream = ORegionFileCache.c(this.d, oanvilchunkloaderpending.a.a, oanvilchunkloaderpending.a.b);

        OCompressedStreamTools.a(oanvilchunkloaderpending.b, (DataOutput) dataoutputstream);
        dataoutputstream.close();
    }

    public void b(OWorld oworld, OChunk ochunk) {}

    public void a() {}

    public void b() {}

    private void a(OChunk ochunk, OWorld oworld, ONBTTagCompound onbttagcompound) {
        oworld.m();
        onbttagcompound.a("xPos", ochunk.g);
        onbttagcompound.a("zPos", ochunk.h);
        onbttagcompound.a("LastUpdate", oworld.o());
        onbttagcompound.a("HeightMap", ochunk.f);
        onbttagcompound.a("TerrainPopulated", ochunk.k);
        OExtendedBlockStorage[] aoextendedblockstorage = ochunk.h();
        ONBTTagList onbttaglist = new ONBTTagList("Sections");
        OExtendedBlockStorage[] aoextendedblockstorage1 = aoextendedblockstorage;
        int i = aoextendedblockstorage.length;

        ONBTTagCompound onbttagcompound1;

        for (int j = 0; j < i; ++j) {
            OExtendedBlockStorage oextendedblockstorage = aoextendedblockstorage1[j];

            if (oextendedblockstorage != null && oextendedblockstorage.f() != 0) {
                onbttagcompound1 = new ONBTTagCompound();
                onbttagcompound1.a("Y", (byte) (oextendedblockstorage.c() >> 4 & 255));
                onbttagcompound1.a("Blocks", oextendedblockstorage.g());
                if (oextendedblockstorage.h() != null) {
                    onbttagcompound1.a("Add", oextendedblockstorage.h().a);
                }

                onbttagcompound1.a("Data", oextendedblockstorage.i().a);
                onbttagcompound1.a("SkyLight", oextendedblockstorage.k().a);
                onbttagcompound1.a("BlockLight", oextendedblockstorage.j().a);
                onbttaglist.a((ONBTBase) onbttagcompound1);
            }
        }

        onbttagcompound.a("Sections", (ONBTBase) onbttaglist);
        onbttagcompound.a("Biomes", ochunk.l());
        ochunk.m = false;
        ONBTTagList onbttaglist1 = new ONBTTagList();

        Iterator iterator;

        for (i = 0; i < ochunk.j.length; ++i) {
            iterator = ochunk.j[i].iterator();

            while (iterator.hasNext()) {
                OEntity oentity = (OEntity) iterator.next();

                ochunk.m = true;
                onbttagcompound1 = new ONBTTagCompound();
                if (oentity.c(onbttagcompound1)) {
                    onbttaglist1.a((ONBTBase) onbttagcompound1);
                }
            }
        }

        onbttagcompound.a("Entities", (ONBTBase) onbttaglist1);
        ONBTTagList onbttaglist2 = new ONBTTagList();

        iterator = ochunk.i.values().iterator();

        while (iterator.hasNext()) {
            OTileEntity otileentity = (OTileEntity) iterator.next();

            onbttagcompound1 = new ONBTTagCompound();
            otileentity.b(onbttagcompound1);
            onbttaglist2.a((ONBTBase) onbttagcompound1);
        }

        onbttagcompound.a("TileEntities", (ONBTBase) onbttaglist2);
        List list = oworld.a(ochunk, false);

        if (list != null) {
            long k = oworld.o();
            ONBTTagList onbttaglist3 = new ONBTTagList();
            Iterator iterator1 = list.iterator();

            while (iterator1.hasNext()) {
                ONextTickListEntry onextticklistentry = (ONextTickListEntry) iterator1.next();
                ONBTTagCompound onbttagcompound2 = new ONBTTagCompound();

                onbttagcompound2.a("i", onextticklistentry.d);
                onbttagcompound2.a("x", onextticklistentry.a);
                onbttagcompound2.a("y", onextticklistentry.b);
                onbttagcompound2.a("z", onextticklistentry.c);
                onbttagcompound2.a("t", (int) (onextticklistentry.e - k));
                onbttaglist3.a((ONBTBase) onbttagcompound2);
            }

            onbttagcompound.a("TileTicks", (ONBTBase) onbttaglist3);
        }

    }

    private OChunk a(OWorld oworld, ONBTTagCompound onbttagcompound) {
        int i = onbttagcompound.f("xPos");
        int j = onbttagcompound.f("zPos");
        OChunk ochunk = new OChunk(oworld, i, j);

        ochunk.f = onbttagcompound.l("HeightMap");
        ochunk.k = onbttagcompound.o("TerrainPopulated");
        ONBTTagList onbttaglist = onbttagcompound.n("Sections");
        byte b0 = 16;
        OExtendedBlockStorage[] aoextendedblockstorage = new OExtendedBlockStorage[b0];

        for (int k = 0; k < onbttaglist.d(); ++k) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.a(k);
            byte b1 = onbttagcompound1.d("Y");
            OExtendedBlockStorage oextendedblockstorage = new OExtendedBlockStorage(b1 << 4);

            oextendedblockstorage.a(onbttagcompound1.k("Blocks"));
            if (onbttagcompound1.c("Add")) {
                oextendedblockstorage.a(new ONibbleArray(onbttagcompound1.k("Add"), 4));
            }

            oextendedblockstorage.b(new ONibbleArray(onbttagcompound1.k("Data"), 4));
            oextendedblockstorage.d(new ONibbleArray(onbttagcompound1.k("SkyLight"), 4));
            oextendedblockstorage.c(new ONibbleArray(onbttagcompound1.k("BlockLight"), 4));
            oextendedblockstorage.d();
            aoextendedblockstorage[b1] = oextendedblockstorage;
        }

        ochunk.a(aoextendedblockstorage);
        if (onbttagcompound.c("Biomes")) {
            ochunk.a(onbttagcompound.k("Biomes"));
        }

        ONBTTagList onbttaglist1 = onbttagcompound.n("Entities");

        if (onbttaglist1 != null) {
            for (int l = 0; l < onbttaglist1.d(); ++l) {
                ONBTTagCompound onbttagcompound2 = (ONBTTagCompound) onbttaglist1.a(l);
                OEntity oentity = OEntityList.a(onbttagcompound2, oworld);

                ochunk.m = true;
                if (oentity != null) {
                    ochunk.a(oentity);
                }
            }
        }

        ONBTTagList onbttaglist2 = onbttagcompound.n("TileEntities");

        if (onbttaglist2 != null) {
            for (int i1 = 0; i1 < onbttaglist2.d(); ++i1) {
                ONBTTagCompound onbttagcompound3 = (ONBTTagCompound) onbttaglist2.a(i1);
                OTileEntity otileentity = OTileEntity.c(onbttagcompound3);

                if (otileentity != null) {
                    ochunk.a(otileentity);
                }
            }
        }

        if (onbttagcompound.c("TileTicks")) {
            ONBTTagList onbttaglist3 = onbttagcompound.n("TileTicks");

            if (onbttaglist3 != null) {
                for (int j1 = 0; j1 < onbttaglist3.d(); ++j1) {
                    ONBTTagCompound onbttagcompound4 = (ONBTTagCompound) onbttaglist3.a(j1);

                    oworld.d(onbttagcompound4.f("x"), onbttagcompound4.f("y"), onbttagcompound4.f("z"), onbttagcompound4.f("i"), onbttagcompound4.f("t"));
                }
            }
        }

        return ochunk;
    }
}
