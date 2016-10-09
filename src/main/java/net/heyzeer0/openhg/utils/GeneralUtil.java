package net.heyzeer0.openhg.utils;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.enums.Estagio;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class GeneralUtil {

    public static Location toLocation(World w, float x, float y, float z) {
        return new Location(w, x, y, z);
    }

    public static Estagio getEstagio() {
        return Main.estagio_atual;
    }

}
