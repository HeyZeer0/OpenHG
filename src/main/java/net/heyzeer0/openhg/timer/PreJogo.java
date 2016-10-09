package net.heyzeer0.openhg.timer;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.enums.Estagio;
import net.heyzeer0.openhg.manager.PlayerManager;
import net.heyzeer0.openhg.manager.ScoreboardManager;
import net.heyzeer0.openhg.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class PreJogo {

    public static void startCountdown() {
        new BukkitRunnable() {
            public void run() {

                for(Player p : Bukkit.getWorld("world").getPlayers()) {
                    ScoreboardManager.setScoreboard_prejogo(p);
                }

                if(PlayerManager.playerCount() < Main.min_players) {
                    Main.estagio_atual = Estagio.AGUARDANDO;
                }else{
                    Main.estagio_atual = Estagio.PREJOGO;
                    Main.countdown_prejogo -= 1;

                    //bem q eu poderia ter usado case ._. era mais prático

                    if(Main.countdown_prejogo == 120) {
                        StringUtil.broadcastMessage("O torneio ira iniciar em 2 minutos.");
                    }
                    if(Main.countdown_prejogo == 60) {
                        StringUtil.broadcastMessage("O torneio ira iniciar em 1 minuto.");
                    }
                    if(Main.countdown_prejogo == 30) {
                        StringUtil.broadcastMessage("O torneio ira iniciar em 30 segundos.");
                    }
                    if(Main.countdown_prejogo == 15) {
                        StringUtil.broadcastMessage("O torneio ira iniciar em 15 segundos.");
                    }
                    if(Main.countdown_prejogo == 10) {
                        StringUtil.broadcastMessage("O torneio ira iniciar em 10 segundos.");
                    }
                    if(Main.countdown_prejogo == 5) {
                        StringUtil.broadcastMessage("O torneio ira iniciar em 5 segundos.");
                    }
                    if(Main.countdown_prejogo == 4) {
                        StringUtil.broadcastMessage("O torneio ira iniciar em 4 segundos.");
                    }
                    if(Main.countdown_prejogo == 3) {
                        StringUtil.broadcastMessage("O torneio ira iniciar em 3 segundos.");
                    }
                    if(Main.countdown_prejogo == 2) {
                        StringUtil.broadcastMessage("O torneio ira iniciar em 2 segundos.");
                    }
                    if(Main.countdown_prejogo == 1) {
                        StringUtil.broadcastMessage("O torneio ira iniciar em 1 segundos.");
                        //iniciar jogo
                    }

                }
            }
        }.runTaskTimer(Main.getPlugin(), 0, 20);
    }

}
