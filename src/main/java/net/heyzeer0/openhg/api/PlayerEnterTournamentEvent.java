package net.heyzeer0.openhg.api;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.enums.Estagio;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class PlayerEnterTournamentEvent extends Event {


    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    Player pl;

    public PlayerEnterTournamentEvent(Player p) {
        pl = p;
    }

    public Player getPlayer() {
        return pl;
    }

    public Estagio getEstagio() {
        return Main.estagio_atual;
    }

 }
