package net.heyzeer0.openhg;

import com.sun.webkit.plugin.PluginManager;
import net.heyzeer0.openhg.api.KitManager;
import net.heyzeer0.openhg.api.eventos.OpenHGStartEvent;
import net.heyzeer0.openhg.api.example.ApiExample;
import net.heyzeer0.openhg.enums.Estagio;
import net.heyzeer0.openhg.eventos.*;
import net.heyzeer0.openhg.gui.KitSelector;
import net.heyzeer0.openhg.manager.ScoreboardManager;
import net.heyzeer0.openhg.timer.PreJogo;
import net.heyzeer0.openhg.utils.GeneralUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Score;

import java.io.File;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class Main extends JavaPlugin {

    //estagio
    public static Estagio estagio_atual = Estagio.AGUARDANDO;

    //contagens
    public static int countdown_prejogo = 60*5;
    public static int countdown_jogo = 0;
    public static int countdown_invencibilidade = 60*2 + 1;

    //valores
    public static int min_players = 5;

    //strings
    public static String prefixo = ChatColor.DARK_GRAY + "(" + ChatColor.GREEN + " ! " + ChatColor.DARK_GRAY + ") " + ChatColor.GRAY;
    public static String prefix_nome = "" + ChatColor.GREEN + ChatColor.BOLD + "Open" + ChatColor.GRAY + ChatColor.BOLD + "HG";
    public static String ip = "hg.openhg.com.br";
    public static String chatformat = "§6§l>";
    public static String site  = "www.openhg.com.br";

    //plugin
    private static Main main;

    public void onEnable() {
        main = this;
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info("OpenHG by HeyZeer0");
        Bukkit.getLogger().info("Source: https://github.com/HeyZeer0/OpenHG");
        Bukkit.getLogger().info(" ");
        PreJogo.startCountdown();
        registerEvents();

        WorldBorder b = Bukkit.getWorld("world").getWorldBorder();
        b.setSize(1000);
        b.setCenter(0, 0);

        Bukkit.getPluginManager().callEvent(new OpenHGStartEvent());
    }

    public void onDisable() {

    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InteractEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ItemEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EntityEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ApiExample(), this);
        Bukkit.getPluginManager().registerEvents(new KitSelector(), this);
    }

    public static Main getPlugin() {
        return main;
    }



}
