package net.heyzeer0.openhg.manager;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.api.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by HeyZeer0 on 11/10/2016.
 */
public class CombatLogManager {

    public static HashMap<UUID, UUID> combat = new HashMap<UUID, UUID>();
    public static HashMap<String, Integer> combatTime = new HashMap<String, Integer>();
    public static HashMap<String, Integer> combatTask = new HashMap<String, Integer>();

    public static void putInCombat(Player p, Player p2) {
        combat.put(p.getUniqueId(), p2.getUniqueId());
        combatTime.put(p.getName(), 15);

        int task;
        String name = p.getName();

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            public void run() {
                combatTime.put(name, combatTime.get(name) - 1);

                if(combatTime.get(name) <= 0) {
                    Bukkit.getScheduler().cancelTask(combatTask.get(name));
                    removeCombat(p);
                }
            }
        }, 0L, 20L);

        combatTask.put(p.getName(), task);

    }

    public static void updateCombat(Player p, Player p2) {
        if(combat.containsKey(p.getUniqueId()) && combat.get(p.getUniqueId()) == p2.getUniqueId()) {
            combatTime.put(p.getName(), 15);

        }else{
            if(combatTask.containsKey(p.getName())) {
                Bukkit.getScheduler().cancelTask(combatTask.get(p.getName()));
            }

            removeCombat(p);
            putInCombat(p, p2);
        }
    }

    public static void removeCombat(Player p) {
        combatTime.remove(p.getName());
        combat.remove(p.getUniqueId());
        combatTask.remove(p.getName());
    }

    public static void removeCombat(UUID p) {
        combatTime.remove(p);
        combat.remove(p);
        combatTask.remove(p);
    }

    public static void quitCombat(Player p) {
        if(combat.containsKey(p.getUniqueId())) {
            if(combatTask.containsKey(p.getName())) {
                Bukkit.getScheduler().cancelTask(combatTask.get(p.getName()));
            }

            if(invalidate.contains(p.getName())) {
                removeCombat(p);
                return;
            }

            //Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
            //    public void run() {
            //        QuitManager.invalidate(p);
            //    }
            //}, 5);

            Bukkit.broadcastMessage("§b" + p.getName() + "(" + KitManager.getKit(p) + ") desconectou em combate.");

            KitManager.removeKit(p);
            PlayerManager.removePlayer(p.getUniqueId());

            Bukkit.broadcastMessage("§7" + "§c" + PlayerManager.playerCount() + " jogadores restantes.");
            Bukkit.broadcastMessage("§7" + "§e" + p.getName() + " saiu do torneio.");

            //Add kill
            KillManager.addKill(p, 1);
            removeCombat(p);
        }
    }

    public static ArrayList<String> invalidate = new ArrayList<String>();

    public static void invalidate(Player p) {
        if(!invalidate.contains(p.getName())) {
            invalidate.add(p.getName());
        }
    }


}
