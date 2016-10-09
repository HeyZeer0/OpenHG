package net.heyzeer0.openhg.eventos;

import com.sun.javafx.embed.EmbeddedStageInterface;
import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.enums.Estagio;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class ItemEvent implements Listener {

    @EventHandler
    public void dropItem_pregame(PlayerDropItemEvent e) {
        if(Main.estagio_atual == Estagio.PREJOGO || Main.estagio_atual == Estagio.AGUARDANDO) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void dropItem_pregame(PlayerPickupItemEvent e) {
        if(Main.estagio_atual == Estagio.PREJOGO || Main.estagio_atual == Estagio.AGUARDANDO) {
            e.setCancelled(true);
        }
    }

}
