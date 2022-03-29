package com.github.danildzambrana.glib.holders;

import com.github.danildzambrana.glib.models.Button;
import com.github.danildzambrana.glib.models.holders.BaseHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class SinglePageHolder extends BaseHolder {
    public SinglePageHolder(int lines,
                            String title,
                            Map<Integer, Button> buttonMap,
                            boolean blockAllSlots) {
        super(lines, title, buttonMap, blockAllSlots);
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this,
                getLines() * 9, ChatColor.translateAlternateColorCodes('&', getTitle()));

        getButtonMap().forEach((slot, button) -> inventory.setItem(slot, button.getItemStack()));

        return inventory;
    }
}
