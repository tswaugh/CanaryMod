import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ONetworkListenThread {

    public static Logger a = Logger.getLogger("Minecraft");
    private ServerSocket d;
    private Thread e;
    public volatile boolean b = false;
    private int f = 0;
    private ArrayList g = new ArrayList();
    private ArrayList h = new ArrayList();
    public OMinecraftServer c;
    private HashMap i = new HashMap();

    public ONetworkListenThread(OMinecraftServer ominecraftserver, InetAddress inetaddress, int i) throws IOException {
        super();
        this.c = ominecraftserver;
        this.d = new ServerSocket(i, 0, inetaddress);
        this.d.setPerformancePreferences(0, 2, 1);
        this.b = true;
        this.e = new ONetworkAcceptThread(this, "Listen thread", ominecraftserver);
        this.e.start();
    }

    public void a(Socket socket) {
        InetAddress inetaddress = socket.getInetAddress();
        HashMap hashmap = this.i;

        synchronized (this.i) {
            this.i.remove(inetaddress);
        }
    }

    public void a(ONetServerHandler onetserverhandler) {
        this.h.add(onetserverhandler);
    }

    private void a(ONetLoginHandler onetloginhandler) {
        if (onetloginhandler == null) {
            throw new IllegalArgumentException("Got null pendingconnection!");
        } else {
            this.g.add(onetloginhandler);
        }
    }

    public void a() {
        int i;

        for (i = 0; i < this.g.size(); ++i) {
            ONetLoginHandler onetloginhandler = (ONetLoginHandler) this.g.get(i);

            try {
                onetloginhandler.a();
            } catch (Exception exception) {
                onetloginhandler.a("Internal server error");
                a.log(Level.WARNING, "Failed to handle packet: " + exception, exception);
            }

            if (onetloginhandler.c) {
                this.g.remove(i--);
            }

            onetloginhandler.b.a();
        }

        for (i = 0; i < this.h.size(); ++i) {
            ONetServerHandler onetserverhandler = (ONetServerHandler) this.h.get(i);

            try {
                onetserverhandler.a();
            } catch (Exception exception1) {
                a.log(Level.WARNING, "Failed to handle packet: " + exception1, exception1);
                onetserverhandler.a("Internal server error");
            }

            if (onetserverhandler.c) {
                this.h.remove(i--);
            }

            onetserverhandler.b.a();
        }

    }

    // $FF: synthetic method
    static ServerSocket a(ONetworkListenThread onetworklistenthread) {
        return onetworklistenthread.d;
    }

    // $FF: synthetic method
    static HashMap b(ONetworkListenThread onetworklistenthread) {
        return onetworklistenthread.i;
    }

    // $FF: synthetic method
    static int c(ONetworkListenThread onetworklistenthread) {
        return onetworklistenthread.f++;
    }

    // $FF: synthetic method
    static void a(ONetworkListenThread onetworklistenthread, ONetLoginHandler onetloginhandler) {
        onetworklistenthread.a(onetloginhandler);
    }

}
