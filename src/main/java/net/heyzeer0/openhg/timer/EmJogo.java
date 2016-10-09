package net.heyzeer0.openhg.timer;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.enums.Estagio;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class EmJogo {

    public static void startCountdown() {
        new BukkitRunnable() {
            public void run() {
                Main.countdown_jogo+= 1;

                if(Main.estagio_atual == Estagio.VITORIA) {
                    return;
                }

            }
        }.runTaskTimer(Main.getPlugin(), 0, 20);
    }

}
