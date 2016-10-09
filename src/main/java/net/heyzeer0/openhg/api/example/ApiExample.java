package net.heyzeer0.openhg.api.example;

import net.heyzeer0.openhg.api.KitManager;
import net.heyzeer0.openhg.api.eventos.*;
import net.heyzeer0.openhg.utils.ItemUtil;
import net.heyzeer0.openhg.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class ApiExample {

    //how to add a kit
    @EventHandler
    public void serverStart(OpenHGStartEvent e) {
        KitManager.addKit("fisherman", new ItemStack[] {ItemUtil.customItem(Material.FISHING_ROD, ChatColor.RED + "Fisherman's rod", Arrays.asList("Fish players!"), 1)});
    }

    //how to make the kit ability.
    @EventHandler
    public void onPlayerFished(PlayerFishEvent event)
    {
        Player player = event.getPlayer();
        if (KitManager.getKit(player).equalsIgnoreCase("fisherman")) {
            if ((event.getCaught() instanceof Player))
            {
                Player caught = (Player)event.getCaught();
                if (player.getItemInHand().getType() == Material.FISHING_ROD) {
                    caught.teleport(player.getLocation());
                }
            }
            Entity caught = event.getCaught();
            if((KitManager.getKit(player).equalsIgnoreCase("fisherman")) && (player.getItemInHand().getType() == Material.FISHING_ROD) && (caught != null)) {
                caught.teleport(player.getLocation());
            }
        }
    }

    //tournament start event
    @EventHandler
    public void gameStart(StartEvent e) {
        StringUtil.broadcastMessage("Hello players!");
    }

    //invencibility finish event
    @EventHandler
    public void invencibilityfinish(InvencibilityFinishEvent e) {
        StringUtil.broadcastMessage("Omg the invencibility has finished!");
    }

    //player join the tournament
    @EventHandler
    public void PlayerEnter(PlayerEnterTournamentEvent e) {
        StringUtil.broadcastMessage("The player " + e.getPlayer().getName() + " joined the tournament!");
    }

    //player leave the tournament
    @EventHandler
    public void PlayerEnter(PlayerLeaveTournamentEvent e) {
        StringUtil.broadcastMessage("The player " + e.getPlayer().getName() + " leaved the tournament!");
    }

    //prestart = where adds the slow and jump effects
    @EventHandler
    public void PlayerEnter(PreStartEvent e) {
        StringUtil.broadcastMessage("Hmmm looks like all people are stuck ^-^");
    }

}
