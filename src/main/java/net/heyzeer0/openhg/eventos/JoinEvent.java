package net.heyzeer0.openhg.eventos;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.enums.Estagio;
import net.heyzeer0.openhg.manager.GameManager;
import net.heyzeer0.openhg.manager.PlayerManager;
import net.heyzeer0.openhg.utils.GeneralUtil;
import net.heyzeer0.openhg.utils.ItemUtil;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class JoinEvent implements Listener {

    @EventHandler
    public void joinEvent(PlayerJoinEvent e) {
        if(Main.estagio_atual == Estagio.PREJOGO || Main.estagio_atual == Estagio.AGUARDANDO) {
            PlayerManager.addPlayer(e.getPlayer());
            ItemUtil.giveJoinItems(e.getPlayer());

            e.getPlayer().teleport(GeneralUtil.toLocation(e.getPlayer().getWorld(), 0, e.getPlayer().getWorld().getHighestBlockYAt(0, 0), 0));

            if(Main.countdown_prejogo <= 10) {
                GameManager.prestart(e.getPlayer());
            }

            return;
        }
        if(Main.estagio_atual == Estagio.EMJOGO) {
            if(Main.countdown_jogo <= 300) {
                if(e.getPlayer().hasPermission("openhg.afterjoin")) {
                    PlayerManager.addPlayer(e.getPlayer());
                }
                return;
            }

        }
        if(Main.estagio_atual == Estagio.VITORIA) {

        }
    }

    @EventHandler
    public void quitEvent(PlayerQuitEvent e) {
        if(Main.estagio_atual == Estagio.PREJOGO || Main.estagio_atual == Estagio.AGUARDANDO) {
            PlayerManager.removePlayer(e.getPlayer());
            return;
        }
        if(Main.estagio_atual == Estagio.EMJOGO) {
            //countdown para ele voltar
        }
        if(Main.estagio_atual == Estagio.VITORIA) {
            //desligar servidor por ser o ultimo jogador xd
        }
    }

}
