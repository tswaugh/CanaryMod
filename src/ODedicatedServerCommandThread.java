import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ODedicatedServerCommandThread extends Thread {

    final ODedicatedServer a;

    ODedicatedServerCommandThread(ODedicatedServer odedicatedserver) {
        this.a = odedicatedserver;
    }

    public void run() {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));

        String s;

        try {
<<<<<<<
            while (!this.a.aa() && this.a.l() && (s = bufferedreader.readLine()) != null) {
                // CanaryMod: run through our parser first.
                if (!etc.getInstance().parseConsoleCommand(s, a)) {
|||||||
            while (!this.a.aa() && this.a.l() && (s = bufferedreader.readLine()) != null) {
=======
            while (!this.a.ac() && this.a.m() && (s = bufferedreader.readLine()) != null) {
>>>>>>>
                    this.a.a(s, (OICommandSender) this.a);
                }
            }
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }
}
