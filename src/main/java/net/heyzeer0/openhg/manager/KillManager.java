package net.heyzeer0.openhg.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by HeyZeer0 on 11/10/2016.
 */
public class KillManager {

    private static HashMap<UUID, Integer> kills = new HashMap<>();

    public static void addKill(Player p, Integer amount) {
        if(!kills.containsKey(p.getUniqueId())) {
            kills.put(p.getUniqueId(), 1);
        }else{
            kills.replace(p.getUniqueId(), kills.get(p.getUniqueId()) + 1);
        }
    }

    public static void addKill(UUID p, Integer amount) {
        if(!kills.containsKey(p)) {
            kills.put(p, 1);
        }else{
            kills.replace(p, kills.get(p) + 1);
        }
    }

    public static Integer getKills(Player p) {
        if(!kills.containsKey(p.getUniqueId())) {
            return 0;
        }else{
            return kills.get(p.getUniqueId());
        }
    }

}
