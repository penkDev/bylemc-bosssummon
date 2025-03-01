package org.penk.bylemcBosssummon.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.penk.bylemcBosssummon.BylemcBosssummon;

public class ReloadConfigCommand implements CommandExecutor {

    private final BylemcBosssummon plugin;

    public ReloadConfigCommand(BylemcBosssummon plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        plugin.reloadConfig();
        plugin.loadConfigValues();
        sender.sendMessage(ChatColor.GREEN + "Configuration reloaded.");

        return true;
    }
}
