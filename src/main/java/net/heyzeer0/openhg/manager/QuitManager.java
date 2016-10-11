package net.heyzeer0.openhg.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.api.KitManager;
import net.heyzeer0.openhg.api.eventos.PlayerLeaveTournamentEvent;
import net.heyzeer0.openhg.enums.QuitCause;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by HeyZeer0 on 11/10/2016.
 */
public class QuitManager {

    public static ArrayList<UUID> quitted = new ArrayList<UUID>();

    public static HashMap<String, ItemStack[]> items = new HashMap<String, ItemStack[]>();
    public static HashMap<String, Location> loc = new HashMap<String, Location>();
    public static HashMap<UUID, String> inFake = new HashMap<UUID, String>();

    public static boolean quitted(Player p) {
        return quitted.contains(p.getUniqueId());
    }

    public static void join(Player p) {
        if(quitted.contains(p.getUniqueId())) {
            quitted.remove(p.getUniqueId());
        }
    }

    public static void invalidate(Player p) {
        UUID uuid = p.getUniqueId();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
            public void run() {
                quitted.remove(uuid);
            }
        }, 20);
    }

    public static void quit(Player p) {

        if(PlayerManager.playerCount() <= 1) {
            return;
        }

        UUID uuid = p.getUniqueId();
        String nick = p.getName();
        items.put(p.getName(), p.getInventory().getContents());
        ItemStack[] drops = items.get(p.getName());
        loc.put(p.getName(), p.getLocation());
        Location location = loc.get(p.getName());
        quitted.add(p.getUniqueId());

        new BukkitRunnable() {
            Integer tMax = 30;
            public void run() {
                tMax = tMax -1;

                if(!quitted.contains(uuid)) {
                    this.cancel();
                }

                if(tMax <= 0) {
                    this.cancel();
                    quitted.remove(uuid);
                    Bukkit.broadcastMessage("§b" + nick + "(" + KitManager.getKit(p) + ") demorou muito para voltar e foi morto.");
                    PlayerManager.deathPlayer(uuid);
                    PlayerManager.removePlayer(uuid);
                    Bukkit.getPluginManager().callEvent(new PlayerLeaveTournamentEvent(p, QuitCause.LOGGEDOUT));
                    KitManager.removeKit(uuid);
                    Bukkit.broadcastMessage("§7" + "§c" + PlayerManager.playerCount() + " jogadores restantes.");
                    Bukkit.broadcastMessage("§7" + "§e" + nick + " saiu do torneio.");
                    if(drops != null) {
                        for(int i = 0; i < drops.length; i++) {
                            if(drops[i] == null) {
                                return;
                            }
                            Bukkit.getWorld("world").dropItemNaturally(location, drops[i]);
                        }
                    }
                    items.remove(nick);
                    loc.remove(nick);
                    inFake.remove(uuid);

                }

            }
        }.runTaskTimer(Main.getPlugin(), 0, 20);
    }

}
