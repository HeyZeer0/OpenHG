package net.heyzeer0.openhg.eventos;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.enums.Estagio;
import net.heyzeer0.openhg.manager.PlayerManager;
import net.heyzeer0.openhg.utils.ItemUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class JoinEvent implements Listener {

    @EventHandler
    public void joinEvent(PlayerJoinEvent e) {
        if(Main.estagio_atual == Estagio.PREJOGO || Main.estagio_atual == Estagio.AGUARDANDO) {
            PlayerManager.addPlayer(e.getPlayer());
            ItemUtil.giveJoinItems(e.getPlayer());
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

}
