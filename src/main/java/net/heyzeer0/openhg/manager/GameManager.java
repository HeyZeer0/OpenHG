package net.heyzeer0.openhg.manager;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.api.KitManager;
import net.heyzeer0.openhg.api.eventos.InvencibilityFinishEvent;
import net.heyzeer0.openhg.api.eventos.PreStartEvent;
import net.heyzeer0.openhg.api.eventos.StartEvent;
import net.heyzeer0.openhg.enums.Estagio;
import net.heyzeer0.openhg.timer.EmJogo;
import net.heyzeer0.openhg.timer.Invencibilidade;
import net.heyzeer0.openhg.utils.GeneralUtil;
import net.heyzeer0.openhg.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class GameManager {

    public static void prestart() {
        for(Player p : Bukkit.getWorld("world").getPlayers()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, Integer.MAX_VALUE));
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, -Integer.MAX_VALUE, Integer.MAX_VALUE));
            p.teleport(GeneralUtil.toLocation(p.getWorld(), 0, p.getWorld().getHighestBlockYAt(0, 0), 0));
        }
        Bukkit.getPluginManager().callEvent(new PreStartEvent());
        ChatManager.setChat(false);
    }

    public static void prestart(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, Integer.MAX_VALUE));
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, -Integer.MAX_VALUE, Integer.MAX_VALUE));
        p.teleport(GeneralUtil.toLocation(p.getWorld(), 0, p.getWorld().getHighestBlockYAt(0, 0), 0));
        KitManager.giveKitItems(p);
    }

    public static void startgame() {
        for(Player p : Bukkit.getWorld("world").getPlayers()) {
            p.removePotionEffect(PotionEffectType.SLOW);
            p.removePotionEffect(PotionEffectType.JUMP);
            p.teleport(GeneralUtil.toLocation(p.getWorld(), 0, p.getWorld().getHighestBlockYAt(0, 0), 0));
        }

        Bukkit.broadcastMessage(" ");
        StringUtil.broadcastMessage("O torneio teve inicio, boa sorte a todos.");
        Bukkit.broadcastMessage(" ");

        Bukkit.getPluginManager().callEvent(new StartEvent());
        Main.estagio_atual = Estagio.INVECIBILIDADE;
        Invencibilidade.startCountdown();
        DamageManager.setDamage(false);
    }

    public static void finishInvencibilidade() {
        DamageManager.setDamage(true);
        ChatManager.setChat(true);
        EmJogo.startCountdown();
        Bukkit.getPluginManager().callEvent(new InvencibilityFinishEvent());
    }

}
