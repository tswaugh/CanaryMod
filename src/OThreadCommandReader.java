import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class OThreadCommandReader extends Thread {

    // $FF: synthetic field
    final OMinecraftServer a;

    public OThreadCommandReader(OMinecraftServer ominecraftserver) {
        super();
        this.a = ominecraftserver;
    }

    public void run() {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;

        try {
            while (!this.a.i && (s = bufferedreader.readLine()) != null) {
                // CanaryMod: run through our parser first.
                if (!etc.getInstance().parseConsoleCommand(s, a)) {
                    this.a.a(s, this.a);
                }
            }
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }

    }
}
