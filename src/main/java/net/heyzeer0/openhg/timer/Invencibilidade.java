package net.heyzeer0.openhg.timer;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.enums.Estagio;
import net.heyzeer0.openhg.manager.GameManager;
import net.heyzeer0.openhg.utils.StringUtil;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class Invencibilidade {

    public static void startCountdown() {
        new BukkitRunnable() {
            public void run() {
                Main.countdown_invencibilidade-= 1;

                if(Main.countdown_invencibilidade == 120) {
                    StringUtil.broadcastMessage("A invencibilidade ira acabar em 2 minutos.");
                }
                if(Main.countdown_invencibilidade == 60) {
                    StringUtil.broadcastMessage("A invencibilidade ira acabar em 1 minuto.");
                }
                if(Main.countdown_invencibilidade == 30) {
                    StringUtil.broadcastMessage("A invencibilidade ira acabar em 30 segundos.");
                }
                if(Main.countdown_invencibilidade == 15) {
                    StringUtil.broadcastMessage("A invencibilidade ira acabar em 15 segundos.");
                }
                if(Main.countdown_invencibilidade == 10) {
                    StringUtil.broadcastMessage("A invencibilidade ira acabar em 10 segundos.");
                }
                if(Main.countdown_invencibilidade == 5) {
                    StringUtil.broadcastMessage("A invencibilidade ira acabar em 5 segundos.");
                }
                if(Main.countdown_invencibilidade == 4) {
                    StringUtil.broadcastMessage("A invencibilidade ira acabar em 4 segundos.");
                }
                if(Main.countdown_invencibilidade == 3) {
                    StringUtil.broadcastMessage("A invencibilidade ira acabar em 3 segundos.");
                }
                if(Main.countdown_invencibilidade == 2) {
                    StringUtil.broadcastMessage("A invencibilidade ira acabar em 2 segundos.");
                }
                if(Main.countdown_invencibilidade == 1) {
                    StringUtil.broadcastMessage("A invencibilidade ira acabar em 1 segundo.");
                }
                if(Main.countdown_invencibilidade == 0) {
                    StringUtil.broadcastMessage("A invencibilidade acabou.");
                    GameManager.finishInvencibilidade();
                    this.cancel();
                }

            }
        }.runTaskTimer(Main.getPlugin(), 0, 20);
    }

}
