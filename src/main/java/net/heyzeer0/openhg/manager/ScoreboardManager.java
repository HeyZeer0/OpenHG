package net.heyzeer0.openhg.manager;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.api.KitManager;
import net.heyzeer0.openhg.utils.ScoreboardUtil;
import net.heyzeer0.openhg.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class ScoreboardManager {

    public static void setScoreboard_prejogo(Player p) {

        ScoreboardUtil a = new ScoreboardUtil(Main.prefix_nome);

        a.add("   " + ChatColor.GRAY + Main.ip);
        a.blankLine();
        a.add(ChatColor.GREEN +  "Iniciando em " + ChatColor.GRAY + StringUtil.relogio(Main.countdown_prejogo));
        a.add(ChatColor.GREEN +  "Jogadores " + ChatColor.GRAY + PlayerManager.playerCount());
        a.blankLine();
        a.add(ChatColor.GREEN +  "Kit " + ChatColor.GRAY + KitManager.getKit(p));
        a.blankLine();
        a.add(ChatColor.GRAY + "   " + Main.site);

        a.build();
        a.send(p);
    }

}
