import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;
import javax.crypto.SecretKey;

public class ONetLoginHandler extends ONetHandler {

    private byte[] d;
    public static Logger a = Logger.getLogger("Minecraft");
    private static Random e = new Random();
    public OTcpConnection b;
    public boolean c = false;
    private OMinecraftServer f;
    private int g = 0;
    private String h = null;
    private volatile boolean i = false;
    private String j = "";
    private SecretKey k = null;
    
    private String worldname; // CanaryMod: store worldname given by plugins

    public ONetLoginHandler(OMinecraftServer ominecraftserver, Socket socket, String s) {
        this.f = ominecraftserver;
        this.b = new OTcpConnection(socket, s, this, ominecraftserver.E().getPrivate());
        this.b.e = 0;
    }

    public void c() {
        if (this.i) {
            this.d();
        }

        if (this.g++ == 600) {
            this.a("Took too long to log in");
        } else {
            this.b.b();
        }

    }

    public void a(String s) {
        try {
            a.info("Disconnecting " + this.e() + ": " + s);
            this.b.a((OPacket) (new OPacket255KickDisconnect(s)));
            this.b.d();
            this.c = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void a(OPacket2ClientProtocol opacket2clientprotocol) {
        this.h = opacket2clientprotocol.f();
        if (!this.h.equals(OStringUtils.a(this.h))) {
            this.a("Invalid username!");
        } else {
            PublicKey publickey = this.f.E().getPublic();

            if (opacket2clientprotocol.d() != 39) {
                if (opacket2clientprotocol.d() > 39) {
                    this.a("Outdated server!");
                } else {
                    this.a("Outdated client!");
                }
            } else {
                this.j = this.f.T() ? Long.toString(e.nextLong(), 16) : "-";
                this.d = new byte[4];
                e.nextBytes(this.d);
                this.b.a((OPacket) (new OPacket253ServerAuthData(this.j, publickey, this.d)));
            }
        }
    }

    public void a(OPacket252SharedKey opacket252sharedkey) {
        PrivateKey privatekey = this.f.E().getPrivate();

        this.k = opacket252sharedkey.a(privatekey);
        if (!Arrays.equals(this.d, opacket252sharedkey.b(privatekey))) {
            this.a("Invalid client reply");
        }

        this.b.a((OPacket) (new OPacket252SharedKey()));
    }

    public void a(OPacket205ClientCommand opacket205clientcommand) {
        if (opacket205clientcommand.a == 0) {
            if (this.f.T()) {
                (new OThreadLoginVerifier(this)).start();
            } else {
                this.i = true;
            }

        }
    }

    public void a(OPacket1Login opacket1login) {}

/*      TODO: put this in ServerConfigurationManager
        if (oentityplayermp != null) { // Is not null, lets go on!
            this.e.h.b(oentityplayermp);
            // The world the player will spawn in is set here.
            // We had the LoginChecks hook in this.e.h.a(this, opacket1login.b); so we have a specific world
            // already specified and only get the right dimension here if that's needed.
            oentityplayermp.a((OWorld) this.e.getWorld(oentityplayermp.bi.name, oentityplayermp.w)); 
            oentityplayermp.c.a((OWorldServer) oentityplayermp.bi);
            a.info(this.b() + " logged in with entity id " + oentityplayermp.bd + " at (" + oentityplayermp.bm + ", " + oentityplayermp.bn + ", " + oentityplayermp.bo + " in world " + oentityplayermp.bi.name + ". Dimension: " + oentityplayermp.w + ")");
            OWorldServer oworldserver = this.e.getWorld(oentityplayermp.bi.name, oentityplayermp.w);
            OChunkCoordinates ochunkcoordinates = oworldserver.p();

            oentityplayermp.c.b(oworldserver.s().m());
            ONetServerHandler onetserverhandler = new ONetServerHandler(this.e, this.b, oentityplayermp);
            
            // CanaryMod - if seed is hidden send 0 instead.
            onetserverhandler.b((OPacket) (new OPacket1Login("", oentityplayermp.bd, oworldserver.s().p(), oentityplayermp.c.a(), oworldserver.t.g, (byte) oworldserver.q, (byte) oworldserver.y(), (byte) this.e.h.k())));
            onetserverhandler.b((OPacket) (new OPacket6SpawnPosition(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c)));
            this.e.h.a(oentityplayermp, oworldserver);
            // CanaryMod - onPlayerConnect Hook
            HookParametersConnect hookResult = new HookParametersConnect(String.format(Colors.Yellow + "%s joined the game.", oentityplayermp.v), true);

            hookResult = (HookParametersConnect) etc.getLoader().callHook(PluginLoader.Hook.PLAYER_CONNECT, oentityplayermp.getPlayer(), hookResult);
            if (!hookResult.isHidden()) { 
                this.e.h.a((OPacket) (new OPacket3Chat(hookResult.getJoinMessage())));
            }
            
            // CanaryMod - Check Creative Mode
            oentityplayermp.getPlayer().refreshCreativeMode();
            
            // CanaryMod - Check if player is listed as muted, and mute him
            if (etc.getDataSource().isPlayerOnMuteList(oentityplayermp.getPlayer().getName())) {
                oentityplayermp.getPlayer().toggleMute();
            }
            // CanaryMod END

            this.e.h.a(oentityplayermp, oworldserver);
            // this.e.h.a((OPacket) (new OPacket3Chat("\u00a7e" + oentityplayermp.v + " joined the game.")));
            this.e.h.c(oentityplayermp);
            onetserverhandler.a(oentityplayermp.bm, oentityplayermp.bn, oentityplayermp.bo, oentityplayermp.bs, oentityplayermp.bt, oentityplayermp.w, oentityplayermp.bi.name);
            this.e.c.a(onetserverhandler);
            onetserverhandler.b((OPacket) (new OPacket4UpdateTime(oworldserver.o())));

            // CanaryMod - enable/disable potion effects on login
            if (hookResult.applyPotionsEffects()) {
                Iterator iterator = oentityplayermp.aM().iterator();
*/

    public void d() {
        String s = this.f.ab().a(this.b.c(), this.h);

        if (s != null) {
            this.a(s);
        } else {
            OEntityPlayerMP oentityplayermp = this.f.ab().a(this.h);

            if (oentityplayermp != null) {
                this.f.ab().a((ONetworkManager) this.b, oentityplayermp);
            }
        }

        this.c = true;
    }

    public void a(String s, Object[] aobject) {
        a.info(this.e() + " lost connection");
        this.c = true;
    }

    public void a(OPacket254ServerPing opacket254serverping) {
        if (this.b.g() == null) {
            return;
        } // CanaryMod - Fix if we don't have a socket, don't do anything
        try {
            String s = this.f.Y() + "\u00A7" + this.f.ab().k() + "\u00A7" + this.f.ab().l();
            InetAddress inetaddress = null;

            if (this.b.g() != null) {
                inetaddress = this.b.g().getInetAddress();
            }
            this.b.a((OPacket) (new OPacket255KickDisconnect(s)));
            // CanaryMod: swapped lines below. The network connection should be terminated AFTER removing the socket from the connection list.
            if (inetaddress != null && this.f.ac() instanceof ODedicatedServerListenThread) {
                ((ODedicatedServerListenThread) this.f.ac()).a(inetaddress);
            }
            this.b.d();
            
            this.c = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void a(OPacket opacket) {
        this.a("Protocol error");
    }

    public String e() {
        return this.h != null ? this.h + " [" + this.b.c().toString() + "]" : this.b.c().toString();
    }

    public boolean a() {
        return true;
    }

    static String a(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.j;
    }

    static OMinecraftServer b(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.f;
    }

    static SecretKey c(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.k;
    }

    static String d(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.h;
    }

    static boolean a(ONetLoginHandler onetloginhandler, boolean flag) {
        return onetloginhandler.i = flag;
    }

}
