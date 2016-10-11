package net.heyzeer0.openhg.eventos;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.api.KitManager;
import net.heyzeer0.openhg.api.eventos.PlayerLeaveTournamentEvent;
import net.heyzeer0.openhg.enums.Estagio;
import net.heyzeer0.openhg.enums.QuitCause;
import net.heyzeer0.openhg.manager.CombatLogManager;
import net.heyzeer0.openhg.manager.GameManager;
import net.heyzeer0.openhg.manager.PlayerManager;
import net.heyzeer0.openhg.manager.QuitManager;
import net.heyzeer0.openhg.utils.GeneralUtil;
import net.heyzeer0.openhg.utils.ItemUtil;
import org.bukkit.Bukkit;
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
        e.setJoinMessage(null);

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
            if(PlayerManager.isDeath(e.getPlayer())) {
                if(e.getPlayer().hasPermission("openhg.admin")) {
                    //put in admin
                    return;
                }
                if(e.getPlayer().hasPermission("openhg.spec")) {
                    //put in spec
                    return;
                }
                e.getPlayer().kickPlayer("§cVocê morreu, tente novamente em outra partida.");
                return;
            }
            if(QuitManager.quitted(e.getPlayer())) {
                QuitManager.invalidate(e.getPlayer());
            }
            if(Main.countdown_jogo <= 300) {
                if(e.getPlayer().hasPermission("openhg.afterjoin")) {
                    PlayerManager.addPlayer(e.getPlayer());
                }
                return;
            }
            e.getPlayer().kickPlayer("§cTorneio em andamento.");
        }
        if(Main.estagio_atual == Estagio.VITORIA) {
            if(e.getPlayer().hasPermission("openhg.admin")) {
                //put in admin
                return;
            }
            if(e.getPlayer().hasPermission("openhg.spec")) {
                //put in spec
                return;
            }
            e.getPlayer().kickPlayer("§cTorneio em andamento.");
        }
    }

    @EventHandler
    public void quitEvent(PlayerQuitEvent e) {
        e.setQuitMessage(null);

        if(Main.estagio_atual == Estagio.PREJOGO || Main.estagio_atual == Estagio.AGUARDANDO) {
            PlayerManager.removePlayer(e.getPlayer());
            return;
        }
        if(Main.estagio_atual == Estagio.INVECIBILIDADE) {
            if(PlayerManager.isPlayer(e.getPlayer())) {
                PlayerManager.removePlayer(e.getPlayer());
                Bukkit.getPluginManager().callEvent(new PlayerLeaveTournamentEvent(e.getPlayer(), QuitCause.LOGGEDOUT));
                KitManager.removeKit(e.getPlayer());
                PlayerManager.deathPlayer(e.getPlayer());
            }
            return;
        }
        if(Main.estagio_atual == Estagio.EMJOGO) {
            if(CombatLogManager.combat.containsKey(e.getPlayer().getUniqueId())) {
                CombatLogManager.quitCombat(e.getPlayer());
                return;
            }
            QuitManager.quit(e.getPlayer());
        }
        if(Main.estagio_atual == Estagio.VITORIA) {
            //desligar servidor por ser o ultimo jogador xd
        }
    }

}
