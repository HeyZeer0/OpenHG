package net.heyzeer0.openhg.timer;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.enums.Estagio;
import net.heyzeer0.openhg.manager.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class EmJogo {

    public static void startCountdown() {
        new BukkitRunnable() {
            public void run() {
                Main.countdown_jogo+= 1;

                for(Player p : Bukkit.getWorld("world").getPlayers()) {
                    ScoreboardManager.setScoreboard_emjogo(p);
                }

                if(Main.estagio_atual == Estagio.VITORIA) {
                    return;
                }

            }
        }.runTaskTimer(Main.getPlugin(), 0, 20);
    }

}
