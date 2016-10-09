package net.heyzeer0.openhg.eventos;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.enums.Estagio;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class InteractEvent implements Listener {

    @EventHandler
    public void onClick_pregame(PlayerInteractEvent e) {
        if(Main.estagio_atual == Estagio.AGUARDANDO || Main.estagio_atual == Estagio.PREJOGO) {
            e.setCancelled(true);
            return;
        }
    }



}
