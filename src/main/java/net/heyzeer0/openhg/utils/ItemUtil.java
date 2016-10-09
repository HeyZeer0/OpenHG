package net.heyzeer0.openhg.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class ItemUtil {

    public static ItemStack customItem(Material mat, String displayname, List<String> lore, short durability) {
        ItemStack is = new ItemStack(mat);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(displayname);
        im.setLore(lore);
        is.setDurability(durability);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack customItem(Material mat, String displayname, List<String> lore) {
        ItemStack is = new ItemStack(mat);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(displayname);
        im.setLore(lore);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack customItem(Material mat, String displayname, List<String> lore, Integer amount) {
        ItemStack is = new ItemStack(mat);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(displayname);
        im.setLore(lore);
        is.setItemMeta(im);
        is.setAmount(amount);
        return is;
    }

    public static void giveJoinItems(Player p) {
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.getInventory().addItem(customItem(Material.CHEST, ChatColor.GOLD + "Kit Selector", Arrays.asList(ChatColor.GRAY + "Clique para abrir a seleção de kits")));
    }


}
