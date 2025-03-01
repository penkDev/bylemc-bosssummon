package org.penk.bylemcBosssummon;

import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.penk.bylemcBosssummon.commands.GiveSummonItemCommand;
import org.penk.bylemcBosssummon.commands.ReloadConfigCommand;

public final class BylemcBosssummon extends JavaPlugin {

    private String bossName;
    private String itemType;
    private String itemName;
    private List<String> itemLore;
    private int customModelData;
    private NamespacedKey summonItemKey;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        loadConfigValues();
        summonItemKey = new NamespacedKey(this, "summon_boss_item");
        getServer().getPluginManager().registerEvents(new BossSummonListener(this), this);
        getServer().getPluginManager().registerEvents(new PreventCraftingListener(this), this);
        this.getCommand("givesummonitem").setExecutor(new GiveSummonItemCommand(this));
        this.getCommand("summonbossreload").setExecutor(new ReloadConfigCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public String getBossName() {
        return bossName;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public List<String> getItemLore() {
        return itemLore;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public NamespacedKey getSummonItemKey() {
        return summonItemKey;
    }

    public void loadConfigValues() {
        FileConfiguration config = getConfig();
        bossName = config.getString("boss-name");
        itemType = config.getString("item-type");
        itemName = config.getString("item-name");
        itemLore = config.getStringList("item-lore");
        customModelData = config.getInt("custom-model-data");
    }
}
