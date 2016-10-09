package net.heyzeer0.openhg.eventos;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.manager.ChatManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class ChatEvent implements Listener {

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {

        if(!e.getPlayer().hasPermission("openhg.admin")) {
            e.setFormat(e.getPlayer().getName() + Main.chatformat + "$7" + e.getMessage());
        }else{
            e.setFormat(e.getPlayer().getName() + Main.chatformat + "$f" + e.getMessage());
        }


        if(!ChatManager.isActive()) {
            if(!e.getPlayer().hasPermission("openhg.admin")) {
                e.setCancelled(true);
            }else{
                e.getPlayer().sendMessage(Main.prefixo + "O chat se encontra desativado no momento.");
            }
        }
    }

}
