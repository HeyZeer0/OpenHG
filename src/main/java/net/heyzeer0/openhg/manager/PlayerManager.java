package net.heyzeer0.openhg.manager;

import net.heyzeer0.openhg.api.PlayerEnterTournamentEvent;
import net.heyzeer0.openhg.api.PlayerLeaveTournamentEvent;
import net.heyzeer0.openhg.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class PlayerManager {

    private static ArrayList<UUID> jogadores = new ArrayList<UUID>();

    public static void addPlayer(Player p) {
        if(jogadores.contains(p.getUniqueId())) {
            return;
        }
        jogadores.add(p.getUniqueId());
        Bukkit.getPluginManager().callEvent(new PlayerEnterTournamentEvent(p));
    }

    public static void removePlayer(Player p) {
        jogadores.remove(p.getUniqueId());
        Bukkit.getPluginManager().callEvent(new PlayerLeaveTournamentEvent(p));
    }

    public static void removePlayer(UUID uuid) {
        jogadores.remove(uuid);
    }

    public static void removePlayer(String uuid) {
        jogadores.remove(StringUtil.convertToUUID(uuid));
    }

    public static Integer playerCount() {
        return jogadores.size();
    }


}
