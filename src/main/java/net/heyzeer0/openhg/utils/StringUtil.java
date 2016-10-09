package net.heyzeer0.openhg.utils;

import net.heyzeer0.openhg.Main;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class StringUtil {

    public static UUID convertToUUID(String uuid) {
        return UUID.fromString(uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32));
    }

    public static void broadcastMessage(String msg) {
        Bukkit.broadcastMessage(Main.prefixo + msg);
    }

    public static String relogio(Integer tempo) {
        int aa = tempo/60;
        int bb = tempo%60;
        String a = (aa < 10 ? "0" : "") + aa;
        String b = (bb < 10 ? "0" : "") + bb;
        return a + ":" + b;
    }

    public static String upperCase(String i) {
        return Character.toString(i.charAt(0)).toUpperCase()+i.substring(1);
    }

    public static Integer stringToInt(String i) {
        return Integer.valueOf(i);
    }


}
