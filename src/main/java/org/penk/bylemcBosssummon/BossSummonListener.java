package org.penk.bylemcBosssummon;

import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import io.lumine.mythic.api.MythicProvider;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;

public class BossSummonListener implements Listener {

    private final BylemcBosssummon plugin;

    public BossSummonListener(BylemcBosssummon plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerUseItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Block clickedBlock = event.getClickedBlock();

        if (item != null && clickedBlock != null) {
            if (item.getType() == Material.matchMaterial(plugin.getItemType())) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null && meta.getPersistentDataContainer().has(plugin.getSummonItemKey(), PersistentDataType.STRING)) {
                    String nbtValue = meta.getPersistentDataContainer().get(plugin.getSummonItemKey(), PersistentDataType.STRING);
                    if ("summon_boss_item".equals(nbtValue)) {
                        Optional<MythicMob> optionalMythicMob = MythicProvider.get().getMobManager().getMythicMob(plugin.getBossName());
                        if (optionalMythicMob.isPresent()) {
                            MythicMob mythicMob = optionalMythicMob.get();
                            AbstractLocation location = BukkitAdapter.adapt(clickedBlock.getLocation().add(0.5, 1, 0.5));
                            mythicMob.spawn(location, 1);
                            item.setAmount(item.getAmount() - 1);
                        }
                    }
                }
            }
        }
    }
}
