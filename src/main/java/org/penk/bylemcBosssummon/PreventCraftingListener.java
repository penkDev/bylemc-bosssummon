package org.penk.bylemcBosssummon;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PreventCraftingListener implements Listener {

    private final BylemcBosssummon plugin;

    public PreventCraftingListener(BylemcBosssummon plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        for (ItemStack item : event.getInventory().getMatrix()) {
            if (item != null && item.getType() == Material.matchMaterial(plugin.getItemType())) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null && meta.getPersistentDataContainer().has(plugin.getSummonItemKey(), PersistentDataType.STRING)) {
                    String nbtValue = meta.getPersistentDataContainer().get(plugin.getSummonItemKey(), PersistentDataType.STRING);
                    if ("summon_boss_item".equals(nbtValue)) {
                        event.getInventory().setResult(new ItemStack(Material.AIR));
                        break;
                    }
                }
            }
        }
    }
}
