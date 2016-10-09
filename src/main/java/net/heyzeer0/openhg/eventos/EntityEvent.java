package net.heyzeer0.openhg.eventos;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.enums.Estagio;
import net.heyzeer0.openhg.manager.DamageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class EntityEvent implements Listener {

    @EventHandler
    public void regen(EntityRegainHealthEvent e) {
        if(e.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN || e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void lostHunger_pregame(FoodLevelChangeEvent e) {
        if(Main.estagio_atual == Estagio.AGUARDANDO || Main.estagio_atual == Estagio.PREJOGO) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void damage_pregame(EntityDamageEvent e) {
        if(Main.estagio_atual == Estagio.AGUARDANDO || Main.estagio_atual == Estagio.PREJOGO) {
            e.setCancelled(true);
            return;
        }
        if(!DamageManager.isActive()) {
            if(e.getEntity() instanceof Player) {
                e.setCancelled(true);
            }
        }
    }

}
