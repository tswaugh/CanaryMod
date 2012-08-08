import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;


public class ONetLoginHandler extends ONetHandler {

    public static Logger a = Logger.getLogger("Minecraft");
    private static Random d = new Random();
    public ONetworkManager b;
    public boolean c = false;
    private OMinecraftServer e;
    private int f = 0;
    private String g = null;
    private OPacket1Login h = null;
    private String i = "";
    
    private String worldname; // CanaryMod: store worldname given by plugins

    public ONetLoginHandler(OMinecraftServer ominecraftserver, Socket socket, String s) throws IOException {
        super();
        this.e = ominecraftserver;
        this.b = new ONetworkManager(socket, s, this);
        this.b.f = 0;
    }

    public void a() {
        if (this.h != null) {
            this.b(this.h);
            this.h = null;
        }

        if (this.f++ == 600) {
            this.a("Took too long to log in");
        } else {
            this.b.b();
        }

    }

    public void a(String s) {
        try {
            a.info("Disconnecting " + this.b() + ": " + s);
            this.b.a((OPacket) (new OPacket255KickDisconnect(s)));
            this.b.d();
            this.c = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void a(OPacket2Handshake opacket2handshake) {
        if (this.e.n) {
            this.i = Long.toString(d.nextLong(), 16);
            this.b.a((OPacket) (new OPacket2Handshake(this.i)));
        } else {
            this.b.a((OPacket) (new OPacket2Handshake("-")));
        }

    }

    public void a(OPacket1Login opacket1login) {
        // CanaryMod: Filter bad player names and remove them from the login process
        if (!opacket1login.b.toLowerCase().matches("[a-z0-9-_]+")) {
            c = true; // finished processing
            b.a("This name has been assimilated and you have been kicked.");
            return;
        }
        // CanaryMod End
        this.g = opacket1login.b;
        if (opacket1login.a != 29) {
            if (opacket1login.a > 29) {
                this.a("Outdated server!");
            } else {
                this.a("Outdated client!");
            }

        } else {
            if (!this.e.n) {
                this.b(opacket1login);
            } else {
                (new OThreadLoginVerifier(this, opacket1login)).start();
            }

        }
    }

    public void b(OPacket1Login opacket1login) {
        OEntityPlayerMP oentityplayermp = this.e.h.a(this, opacket1login.b); // create new player instance - this has called a loginchecks hook

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

                while (iterator.hasNext()) {
                    OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

                    onetserverhandler.b((OPacket) (new OPacket41EntityEffect(oentityplayermp.bd, opotioneffect)));
                }
            }

            oentityplayermp.x();
        }

        this.c = true;
    }

    public void a(String s, Object[] aobject) {
        a.info(this.b() + " lost connection");
        this.c = true;
    }

    public void a(OPacket254ServerPing opacket254serverping) {
        if (this.b.f() == null) {
            return;
        } // CanaryMod - Fix if we don't have a socket, don't do anything
        try {
            String s = this.e.s + "\u00a7" + this.e.h.j() + "\u00a7" + this.e.h.k();

            this.b.a((OPacket) (new OPacket255KickDisconnect(s)));
            // CanaryMod swapped lines below. The network connection should be terminated AFTER removing the socket from the connection list.
            this.e.c.a(this.b.f());
            this.b.d();
            this.c = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void a(OPacket opacket) {
        this.a("Protocol error");
    }

    public String b() {
        return this.g != null ? this.g + " [" + this.b.c().toString() + "]" : this.b.c().toString();
    }

    public boolean c() {
        return true;
    }

    // $FF: synthetic method
    static String a(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.i;
    }

    // $FF: synthetic method
    static OPacket1Login a(ONetLoginHandler onetloginhandler, OPacket1Login opacket1login) {
        return onetloginhandler.h = opacket1login;
    }

}
