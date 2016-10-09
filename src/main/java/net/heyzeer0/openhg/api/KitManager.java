package net.heyzeer0.openhg.api;

import net.heyzeer0.openhg.utils.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class KitManager {

    private static HashMap<UUID, String> kits = new HashMap<UUID, String>();
    private static HashMap<String, ItemStack[]> kititems = new HashMap<String, ItemStack[]>();

    public static String getKit(Player p) {
        if(kits.containsKey(p.getUniqueId())) {
            return kits.get(p.getUniqueId());
        }else{
            return "Nenhum";
        }
    }

    public static void setKit(Player p, String kit) {
        if(kits.containsKey(p.getUniqueId())) {
            kits.replace(p.getUniqueId(), kit);
        }else{
            kits.put(p.getUniqueId(), kit);
        }
    }

    public static void removeKit(Player p) {
        kits.remove(p.getUniqueId());
    }

    public static void giveKitItems() {
        for(UUID u : kits.keySet()) {
            Bukkit.getPlayer(u).getInventory().clear();
            Bukkit.getPlayer(u).getInventory().addItem(ItemUtil.customItem(Material.COMPASS, "$4Bússola", Arrays.asList("Clique para procurar jogadores")));

            for(ItemStack i : kititems.get(kits.get(u))) {
                Bukkit.getPlayer(u).getInventory().addItem(i);
            }

        }
    }

    public static void addKit(String kit, ItemStack[] items) {
        if(!kititems.containsKey(kit)) {
            kititems.put(kit, items);
        }
    }

}
