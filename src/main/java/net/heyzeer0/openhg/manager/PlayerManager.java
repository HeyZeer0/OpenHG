package net.heyzeer0.openhg.manager;

import net.heyzeer0.openhg.api.eventos.PlayerEnterTournamentEvent;
import net.heyzeer0.openhg.api.eventos.PlayerLeaveTournamentEvent;
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

    public static boolean isPlayer(Player p) {
        return jogadores.contains(p.getUniqueId());
    }

    public static void addPlayer(Player p) {
        if(jogadores.contains(p.getUniqueId())) {
            return;
        }
        jogadores.add(p.getUniqueId());
        Bukkit.getPluginManager().callEvent(new PlayerEnterTournamentEvent(p));
        jogadores_mortos.remove(p.getUniqueId());
    }

    public static void removePlayer(Player p) {
        jogadores.remove(p.getUniqueId());
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


    private static ArrayList<UUID> jogadores_mortos = new ArrayList<UUID>();

    public static void deathPlayer(Player p) {
        if(jogadores_mortos.contains(p.getUniqueId())) {
            return;
        }
        jogadores_mortos.add(p.getUniqueId());
    }

    public static void deathPlayer(UUID u) {
        if(jogadores_mortos.contains(u)) {
            return;
        }
        jogadores_mortos.add(u);
    }

    public static boolean isDeath(Player p) {
        return jogadores_mortos.contains(p.getUniqueId());
    }




}
