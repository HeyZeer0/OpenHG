package net.heyzeer0.openhg.eventos;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.api.KitManager;
import net.heyzeer0.openhg.enums.Estagio;
import net.heyzeer0.openhg.manager.PlayerManager;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by HeyZeer0 on 10/10/2016.
 */
public class DeathEvent implements Listener {

    public static void death(Player p) {
        PlayerManager.removePlayer(p);

        if(p.hasPermission("openhg.admin")) {
            p.setHealth(20);

            new BukkitRunnable() {
                public void run() {
                    //put in adminn
                }
            }.runTaskLater(Main.getPlugin(), 2);

            return;
        }
        if(p.hasPermission("openhg.spec")) {
            p.setHealth(20);

            new BukkitRunnable() {
                public void run() {
                    //put in spec
                }
            }.runTaskLater(Main.getPlugin(), 2);

            return;
        }

        p.kickPlayer("§cVocê morreu, tente novamente em outra partida.");
    }

    @EventHandler
    public static void kill(PlayerDeathEvent e) {
        if(Main.estagio_atual == Estagio.EMJOGO || Main.estagio_atual == Estagio.INVECIBILIDADE) {
            if(Main.countdown_jogo <= 300 && e.getEntity().hasPermission("openhg.respawnn")) {
                //renascer
                e.setDeathMessage(null);
                return;
            }

            EntityDamageEvent a = e.getEntity().getLastDamageCause();
            Entity damager = null;

            if(a instanceof EntityDamageByEntityEvent) {
                damager = ((EntityDamageByEntityEvent)a).getDamager();
            }

            if(damager != null) {
                if(damager instanceof Monster) {
                    e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") morreu para um monstro."
                            + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                            "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                    death(e.getEntity());
                    return;
                }
                if(damager instanceof Animals) {
                    e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") morreu para um animal."
                            + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                            "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                    death(e.getEntity());
                    return;
                }

                if(damager instanceof Player) {
                    if(e.getEntity().getKiller() != null && e.getEntity().getKiller() instanceof Player) {
                        e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") foi assasinado por" +
                                " " + ((Player)e.getEntity().getKiller()).getName() + "(" + KitManager.getKit(((Player)e.getEntity().getKiller())) + ")"
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                        death(e.getEntity());
                        return;
                    }
                }
            }

            //add combatlog kill

            if(e.getDeathMessage().contains("was shot by")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") levou uma flechada e morreu."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("was pricked to") || e.getDeathMessage().contains("walked into a cactus")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") resolveu abraçar um cacto."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("drowned")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") morreu afogado."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("blew up") || e.getDeathMessage().contains("blown up")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") explodiu."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("hit the grounnd")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") resolveu pular de um lugar alto."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("fell")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") resolveu pular e morreu."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("was squashed")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") caiu uma bigorna em sua cabeça."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("tried to swim in lava")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") tentou nadar na lava."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("was struck by")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") caiu um raio em sua cabeça."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("discovered floor")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") descobriu que o chão era lava."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("was killed by magic")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") foi morto por magia."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("starved to death")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") morreu de fome."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("suffocated in a wall")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") morreu sufocado."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("fell out of the world")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") caiu pra fora do mundo."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            if(e.getDeathMessage().contains("withered away")) {
                e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") morreu envenenado."
                        + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                        "\n§e" + e.getEntity().getName() + "saiu do torneio.");
                death(e.getEntity());
                return;
            }

            e.setDeathMessage("§b" + e.getEntity().getName() + "(" + KitManager.getKit(e.getEntity()) + ") morreu e não deixou o motivo."
                    + "\n§C" + (PlayerManager.playerCount() - 1) + " jogadores restantes" +
                    "\n§e" + e.getEntity().getName() + "saiu do torneio.");
            death(e.getEntity());
            return;
        }
    }

}
