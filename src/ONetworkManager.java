import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ONetworkManager {

    public static final Object a = new Object();
    public static int b;
    public static int c;
    private Object g = new Object();
    private Socket h;
    private final SocketAddress i;
    private DataInputStream j;
    private DataOutputStream k;
    private boolean l = true;
    private List m = Collections.synchronizedList(new ArrayList());
    private List n = Collections.synchronizedList(new ArrayList());
    private List o = Collections.synchronizedList(new ArrayList());
    private ONetHandler p;
    private boolean q = false;
    private Thread r;
    private Thread s;
    private boolean t = false;
    private String u = "";
    private Object[] v;
    private int w = 0;
    private int x = 0;
    public static int[] d = new int[256];
    public static int[] e = new int[256];
    public int f = 0;
    private int y = 50;

    public ONetworkManager(Socket socket, String s, ONetHandler onethandler) throws IOException {
        super();
        this.h = socket;
        this.i = socket.getRemoteSocketAddress();
        this.p = onethandler;

        try {
            socket.setSoTimeout(30000);
            socket.setTrafficClass(24);
        } catch (SocketException socketexception) {
            System.err.println(socketexception.getMessage());
        }

        this.j = new DataInputStream(socket.getInputStream());
        this.k = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream(), 5120));
        this.s = new ONetworkReaderThread(this, s + " read thread");
        this.r = new ONetworkWriterThread(this, s + " write thread");
        this.s.start();
        this.r.start();
    }

    public void a(ONetHandler onethandler) {
        this.p = onethandler;
    }

    public void a(OPacket opacket) {
        if (!this.q) {
            Object object = this.g;

            synchronized (this.g) {
                this.x += opacket.a() + 1;
                if (opacket.p) {
                    this.o.add(opacket);
                } else {
                    this.n.add(opacket);
                }

            }
        }
    }

    private boolean g() {
        boolean flag = false;

        try {
            Object object;
            OPacket opacket;
            int i;
            int[] aint;

            if (!this.n.isEmpty() && (this.f == 0 || System.currentTimeMillis() - ((OPacket) this.n.get(0)).k >= (long) this.f)) {
                object = this.g;
                synchronized (this.g) {
                    opacket = (OPacket) this.n.remove(0);
                    this.x -= opacket.a() + 1;
                }

                OPacket.a(opacket, this.k);
                aint = e;
                i = opacket.b();
                aint[i] += opacket.a() + 1;
                flag = true;
            }

            if (this.y-- <= 0 && !this.o.isEmpty() && (this.f == 0 || System.currentTimeMillis() - ((OPacket) this.o.get(0)).k >= (long) this.f)) {
                object = this.g;
                synchronized (this.g) {
                    opacket = (OPacket) this.o.remove(0);
                    this.x -= opacket.a() + 1;
                }

                OPacket.a(opacket, this.k);
                aint = e;
                i = opacket.b();
                aint[i] += opacket.a() + 1;
                this.y = 0;
                flag = true;
            }

            return flag;
        } catch (Exception exception) {
            if (!this.t) {
                this.a(exception);
            }

            return false;
        }
    }

    public void a() {
        this.s.interrupt();
        this.r.interrupt();
    }

    private boolean h() {
        boolean flag = false;

        try {
            OPacket opacket = OPacket.a(this.j, this.p.c());

            if (opacket != null) {
                int[] aint = d;
                int i = opacket.b();

                aint[i] += opacket.a() + 1;
                if (!this.q) {
                    this.m.add(opacket);
                }

                flag = true;
            } else {
                this.a("disconnect.endOfStream", new Object[0]);
            }

            return flag;
        } catch (Exception exception) {
            if (!this.t) {
                this.a(exception);
            }

            return false;
        }
    }

    private void a(Exception exception) {
        exception.printStackTrace();
        this.a("disconnect.genericReason", new Object[] { "Internal exception: " + exception.toString()});
    }

    public void a(String s, Object... aobject) {
        if (this.l) {
            this.t = true;
            this.u = s;
            this.v = aobject;
            (new ONetworkMasterThread(this)).start();
            this.l = false;

            try {
                this.j.close();
                this.j = null;
            } catch (Throwable throwable) {
                ;
            }

            try {
                this.k.close();
                this.k = null;
            } catch (Throwable throwable1) {
                ;
            }

            try {
                this.h.close();
                this.h = null;
            } catch (Throwable throwable2) {
                ;
            }

        }
    }

    public void b() {
        if (this.x > 1048576) {
            this.a("disconnect.overflow", new Object[0]);
        }

        if (this.m.isEmpty()) {
            if (this.w++ == 1200) {
                this.a("disconnect.timeout", new Object[0]);
            }
        } else {
            this.w = 0;
        }

        int i = 1000;

        while (!this.m.isEmpty() && i-- >= 0) {
            OPacket opacket = (OPacket) this.m.remove(0);

            opacket.a(this.p);
        }

        this.a();
        if (this.t && this.m.isEmpty()) {
            this.p.a(this.u, this.v);
        }

    }

    public SocketAddress c() {
        return this.i;
    }

    public void d() {
        if (!this.q) {
            this.a();
            this.q = true;
            this.s.interrupt();
            (new OThreadMonitorConnection(this)).start();
        }
    }

    public int e() {
        return this.o.size();
    }

    public Socket f() {
        return this.h;
    }

    // $FF: synthetic method
    static boolean a(ONetworkManager onetworkmanager) {
        return onetworkmanager.l;
    }

    // $FF: synthetic method
    static boolean b(ONetworkManager onetworkmanager) {
        return onetworkmanager.q;
    }

    // $FF: synthetic method
    static boolean c(ONetworkManager onetworkmanager) {
        return onetworkmanager.h();
    }

    // $FF: synthetic method
    static boolean d(ONetworkManager onetworkmanager) {
        return onetworkmanager.g();
    }

    // $FF: synthetic method
    static DataOutputStream e(ONetworkManager onetworkmanager) {
        return onetworkmanager.k;
    }

    // $FF: synthetic method
    static boolean f(ONetworkManager onetworkmanager) {
        return onetworkmanager.t;
    }

    // $FF: synthetic method
    static void a(ONetworkManager onetworkmanager, Exception exception) {
        onetworkmanager.a(exception);
    }

    // $FF: synthetic method
    static Thread g(ONetworkManager onetworkmanager) {
        return onetworkmanager.s;
    }

    // $FF: synthetic method
    static Thread h(ONetworkManager onetworkmanager) {
        return onetworkmanager.r;
    }

}
