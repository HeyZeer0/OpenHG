package net.heyzeer0.openhg.gui;

import net.heyzeer0.openhg.Main;
import net.heyzeer0.openhg.api.KitManager;
import net.heyzeer0.openhg.utils.ItemUtil;
import net.heyzeer0.openhg.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class KitSelector implements Listener {

    public static HashMap<Integer, HashMap<String, ItemStack[]>> valores = new HashMap<>();

    public static void registerKits() {
        Integer kits = 0;

        for(String a : KitManager.kititems.keySet()) {
            HashMap<String, ItemStack[]> pc = new HashMap<>();
            pc.put(a, KitManager.kititems.get(a));
            valores.put(kits, pc);
            kits++;
        }
    }

    public static void openKitSelector(Player p, Integer pagina) {
        Inventory i = Bukkit.createInventory(null, 54, "Kit Selector " + pagina);

        if(pagina > 1) {
            i.setItem(0,ItemUtil.customItem(Material.CARPET, "§7Página anterior", null, (short)5));
        }else{
            i.setItem(0,ItemUtil.customItem(Material.CARPET, "§7Página anterior", null, (short)7));
        }
        i.setItem(1,ItemUtil.customItem(Material.STAINED_GLASS_PANE, "", null, (short)7));
        i.setItem(2,ItemUtil.customItem(Material.STAINED_GLASS_PANE, "", null, (short)7));
        i.setItem(3,ItemUtil.customItem(Material.STAINED_GLASS_PANE, "", null, (short)7));
        i.setItem(4,ItemUtil.customItem(Material.EMERALD, Main.prefix_nome, null));
        i.setItem(5,ItemUtil.customItem(Material.STAINED_GLASS_PANE, "", null, (short)7));
        i.setItem(6,ItemUtil.customItem(Material.STAINED_GLASS_PANE, "", null, (short)7));
        i.setItem(7,ItemUtil.customItem(Material.STAINED_GLASS_PANE, "", null, (short)7));

        if(KitManager.kititems.size() > 45 * pagina) {
            i.addItem(ItemUtil.customItem(Material.CARPET, "§7Proxima página", null, (short)5));
        }else{
            i.addItem(ItemUtil.customItem(Material.CARPET, "§7Proxima página", null, (short)7));
        }

        int y = 9;

        int valor_atual = 0;

        if(pagina > 1) {
            valor_atual = 45 * pagina;
        }

        for(int i2 = valor_atual; i2 < valores.size(); i2++) {
            if(y > 54) {
                return;
            }
            if(valores.get(i2) == null) {
                return;
            }
            for(String valr : valores.get(i2).keySet()) {
                if(p.hasPermission("openhg." + valr)) {
                    ItemStack item = valores.get(i2).get(valr)[0];
                    ItemMeta m = item.getItemMeta();
                    m.setDisplayName("§a" + StringUtil.upperCase(valr));
                    m.setLore(null);
                    item.setItemMeta(m);

                    i.setItem(y,item);
                    y++;
                }
            }
        }

        for (int x = 0; x < i.getSize(); x++){
            while(i.getItem(x) == null){
                i.setItem(x, new ItemStack(Material.getMaterial(102)));
            }
        }

        p.openInventory(i);
    }

    @EventHandler
    public void click(InventoryClickEvent e) {
        if(e.getInventory().getTitle().contains("Kit Selector ")) {
            e.setCancelled(true);
            if(e.getCurrentItem() == ItemUtil.customItem(Material.CARPET, "§7Página anterior", null, (short)5)) {
                e.getWhoClicked().closeInventory();
                openKitSelector((Player)e.getWhoClicked(), StringUtil.stringToInt(e.getInventory().getTitle().replace("Kit Selector ", "")) - 1);
                return;
            }
            if(e.getCurrentItem() == ItemUtil.customItem(Material.CARPET, "§7Proxima página", null, (short)5)) {
                e.getWhoClicked().closeInventory();
                openKitSelector((Player)e.getWhoClicked(), StringUtil.stringToInt(e.getInventory().getTitle().replace("Kit Selector ", "")) + 1);
                return;
            }
            if(!(e.getSlot() >= 0 && e.getSlot() < 9)) {
                if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                    Bukkit.dispatchCommand((Player)e.getWhoClicked(), "kit " + e.getCurrentItem().getItemMeta().getDisplayName().replace("§a", ""));
                }
                return;
            }
        }
    }

}
