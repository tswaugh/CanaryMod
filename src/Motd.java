import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Motd - Class for handling of the MOTD
 * extracted from etc
 * 
 * @author Talmor
 */
public class Motd {
    private static final Logger           log = Logger.getLogger("Minecraft");
    
    public static String getMotd(MessageReceiver caller) {
        try {
              FileInputStream fis = new FileInputStream(etc.getConfigFolder()+"motd.txt");
              Scanner scanner = new Scanner(fis, "UTF-8");
              scanner.useDelimiter("\r\n");
              while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.startsWith("#")) {
                  continue;
                }
                String msg = line;
                if(line.contains("&"))
                    msg = line.replace("&", Colors.Marker);
                if(line.contains("&&"))
                    msg = line.replaceAll("&&", "&");
                ((Player)caller).getEntity().a.b(new OPacket3Chat(msg));
              }
              scanner.close();
              fis.close();
            } catch (FileNotFoundException e) {
              log.log(Level.WARNING, "motd.txt does not exist.");
              caller.notify("Cannot load motd");
            } catch (IOException e) {
              log.log(Level.WARNING, "Couldn't load motd.txt");
              caller.notify("Cannot load motd.txt");
            }
        return null;
    }
}
