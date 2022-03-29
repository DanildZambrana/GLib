package com.github.danildzambrana.glib.models;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface Action {
    void execute(InventoryClickEvent event);
}
