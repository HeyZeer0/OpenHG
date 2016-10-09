package net.heyzeer0.openhg.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class PreStartEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public PreStartEvent() {
    }

}
