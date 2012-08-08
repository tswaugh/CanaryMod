import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import java.util.logging.Level;


public class OEntityTracker {
    // CanaryMod: New fields to store the runnables in.
    private static final DelayQueue<DelayedTask> delayQueue = new DelayQueue<DelayedTask>();

    private Set a = new HashSet();
    private OIntHashMap b = new OIntHashMap();
    private OMinecraftServer c;
    private int d;
    private int e;
    
    private String worldName; // CanaryMod: store worldname for multiworld
    private EntityTracker canaryEntityTracker; // CanaryMod entity Tracker

    public OEntityTracker(OMinecraftServer ominecraftserver, int i, String s) {
        super();
        this.c = ominecraftserver;
        this.e = i;
        this.d = ominecraftserver.h.getMaxTrackingDistance(s);
        this.s = s; // CanaryMod
        canaryEntityTracker = new EntityTracker(this);
    }
    
    /**
     * CanaryMod Get the EntityTracker
     * @return
     */
    public EntityTracker getCanaryEntityTracker() {
        return canaryEntityTracker;
    }

    public void a(OEntity oentity) {
        if (oentity instanceof OEntityPlayerMP) {
            this.a(oentity, 512, 2);
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) oentity;
            Iterator iterator = this.a.iterator();

            while (iterator.hasNext()) {
                OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) iterator.next();

                if (oentitytrackerentry.a != oentityplayermp) {
                    oentitytrackerentry.b(oentityplayermp);
                }
            }
        } else if (oentity instanceof OEntityFishHook) {
            this.a(oentity, 64, 5, true);
        } else if (oentity instanceof OEntityArrow) {
            this.a(oentity, 64, 20, false);
        } else if (oentity instanceof OEntitySmallFireball) {
            this.a(oentity, 64, 10, false);
        } else if (oentity instanceof OEntityFireball) {
            this.a(oentity, 64, 10, false);
        } else if (oentity instanceof OEntitySnowball) {
            this.a(oentity, 64, 10, true);
        } else if (oentity instanceof OEntityThrownEnderpearl) {
            this.a(oentity, 64, 10, true);
        } else if (oentity instanceof OEntityEnderEye) {
            this.a(oentity, 64, 10, true);
        } else if (oentity instanceof OEntityEgg) {
            this.a(oentity, 64, 10, true);
        } else if (oentity instanceof OEntityPotion) {
            this.a(oentity, 64, 10, true);
        } else if (oentity instanceof OEntityExpBottle) {
            this.a(oentity, 64, 10, true);
        } else if (oentity instanceof OEntityItem) {
            this.a(oentity, 64, 20, true);
        } else if (oentity instanceof OEntityMinecart) {
            this.a(oentity, 80, 3, true);
        } else if (oentity instanceof OEntityBoat) {
            this.a(oentity, 80, 3, true);
        } else if (oentity instanceof OEntitySquid) {
            this.a(oentity, 64, 3, true);
        } else if (oentity instanceof OIAnimals) {
            this.a(oentity, 80, 3, true);
        } else if (oentity instanceof OEntityDragon) {
            this.a(oentity, 160, 3, true);
        } else if (oentity instanceof OEntityTNTPrimed) {
            this.a(oentity, 160, 10, true);
        } else if (oentity instanceof OEntityFallingSand) {
            this.a(oentity, 160, 20, true);
        } else if (oentity instanceof OEntityPainting) {
            this.a(oentity, 160, Integer.MAX_VALUE, false);
        } else if (oentity instanceof OEntityXPOrb) {
            this.a(oentity, 160, 20, true);
        } else if (oentity instanceof OEntityEnderCrystal) {
            this.a(oentity, 256, Integer.MAX_VALUE, false);
        }

    }

    public void a(OEntity oentity, int i, int j) {
        this.a(oentity, i, j, false);
    }

    public void a(OEntity oentity, int i, int j, boolean flag) {
        if (i > this.d) {
            i = this.d;
        }

        if (this.b.b(oentity.bd)) {
            throw new IllegalStateException("Entity is already tracked!");
        } else {
            OEntityTrackerEntry oentitytrackerentry = new OEntityTrackerEntry(oentity, i, j, flag);

            this.a.add(oentitytrackerentry);
            this.b.a(oentity.bd, oentitytrackerentry);
            oentitytrackerentry.b(this.c.getWorld(this.worldName, this.e).d);
        }
    }

    public void b(OEntity oentity) {
        if (oentity instanceof OEntityPlayerMP) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) oentity;
            Iterator iterator = this.a.iterator();

            while (iterator.hasNext()) {
                OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) iterator.next();

                oentitytrackerentry.a(oentityplayermp);
            }
        }

        OEntityTrackerEntry oentitytrackerentry1 = (OEntityTrackerEntry) this.b.d(oentity.bd);

        if (oentitytrackerentry1 != null) {
            this.a.remove(oentitytrackerentry1);
            oentitytrackerentry1.a();
        }

    }

    public void a() {
        try {
            ArrayList arraylist = new ArrayList();
            Iterator iterator = this.a.iterator();

            while (iterator.hasNconcurrentmodificationexceptiont()) {
                OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) iterator.nconcurrentmodificationexceptiont();

                oentitytrackerentry.a(this.c.getWorld(this.worldName, this.e).d);
                if (oentitytrackerentry.n && oentitytrackerentry.a instanceof OEntityPlayerMP) {
                    arraylist.add((OEntityPlayerMP) oentitytrackerentry.a);
                }
            }

            for (int i = 0; i < arraylist.size(); ++i) {
                OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) arraylist.get(i);
                Iterator iterator1 = this.a.iterator();

                while (iterator1.hasNconcurrentmodificationexceptiont()) {
                    OEntityTrackerEntry oentitytrackerentry1 = (OEntityTrackerEntry) iterator1.nconcurrentmodificationexceptiont();

                    if (oentitytrackerentry1.a != oentityplayermp) {
                        oentitytrackerentry1.b(oentityplayermp);
                    }
                }
            }
        } catch (ConcurrentModificationException concurrentmodificationexception) {
            // people seem to get this concurrentmodificationexceptionception often, lets just catch so it doesn't crash the server.
            OMinecraftServer.a.log(Level.WARNING, "CanaryMod WARNING: ConcurrentModificationException in OEntityTracker:", concurrentmodificationexception);   
        }
        // CanaryMod: Execute runnables contained in eventQueue.
        for (DelayedTask task = delayQueue.poll(); task != null; task = delayQueue.poll()) {
            // should we catch concurrentmodificationexceptionceptions here?
            task.run();
        }
    }
    
    // CanaryMod: Allow adding of tasks to the queue

    public static void add(Runnable runnable, long i) {
        // j.u.concurent.* classes are thread safe
        delayQueue.add(new DelayedTask(runnable, i));
    }

    // CanaryMod: deprecated. Use server.addToServerQueue().
    @Deprecated
    public synchrunnableonized static void add(Runnable runnable) {
        add(runnable, 0L);
    }

    public void a(OEntity oentity, OPacket opacket) {
        OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) this.b.a(oentity.bd);

        if (oentitytrackerentry != null) {
            oentitytrackerentry.a(opacket);
        }

    }

    public void b(OEntity oentity, OPacket opacket) {
        OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) this.b.a(oentity.bd);

        if (oentitytrackerentry != null) {
            oentitytrackerentry.b(opacket);
        }

    }

    public void a(OEntityPlayerMP oentityplayermp) {
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) iterator.next();

            oentitytrackerentry.c(oentityplayermp);
        }

    }
}
