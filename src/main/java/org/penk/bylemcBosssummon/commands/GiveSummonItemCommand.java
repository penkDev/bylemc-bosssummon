package org.penk.bylemcBosssummon.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.penk.bylemcBosssummon.BylemcBosssummon;

import com.iridium.iridiumcolorapi.IridiumColorAPI;

public class GiveSummonItemCommand implements CommandExecutor {

    private final BylemcBosssummon plugin;

    public GiveSummonItemCommand(BylemcBosssummon plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Tylko gracze mogą tego używać.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage(ChatColor.RED + "Nie posiadasz uprawnień do użycia tej komendy.");
            return true;
        }

        ItemStack summonItem = new ItemStack(Material.matchMaterial(plugin.getItemType()));
        ItemMeta meta = summonItem.getItemMeta();
        meta.setDisplayName(IridiumColorAPI.process(plugin.getItemName()));
        meta.setLore(IridiumColorAPI.process(plugin.getItemLore()));
        meta.setCustomModelData(plugin.getCustomModelData());
        meta.getPersistentDataContainer().set(plugin.getSummonItemKey(), PersistentDataType.STRING, "summon_boss_item");
        summonItem.setItemMeta(meta);

        player.getInventory().addItem(summonItem);
        player.sendMessage(ChatColor.GREEN + "Przedmiot został nadany.");

        return true;
    }
}
