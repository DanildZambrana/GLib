package com.github.danildzambrana.glib.menu;

import com.github.danildzambrana.glib.models.holders.BaseHolder;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface Menu {
    /**
     * Get the menu with the holder.
     * @param dataMap data provided to menu.
     * @return a holder with menu.
     */
    BaseHolder getInventory(@Nullable Map<String, Object> dataMap);

}
