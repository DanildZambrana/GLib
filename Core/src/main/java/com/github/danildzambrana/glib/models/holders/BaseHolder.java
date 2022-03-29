package com.github.danildzambrana.glib.models.holders;

import com.github.danildzambrana.glib.models.Button;
import com.github.danildzambrana.glib.models.PopulationStrategy;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Consumer;

public abstract class BaseHolder implements InventoryHolder {
    private final int lines;
    private final String title;
    private final Map<Integer, Button> buttonMap;
    private final boolean blockAllSlots;
    private PopulationStrategy populationStrategy = null;
    private Consumer<InventoryCloseEvent> onClose;

    public BaseHolder(int lines,
                      String title,
                      Map<Integer, Button> buttonMap, boolean blockAllSlots) {
        this.lines = lines;
        this.title = title;
        this.buttonMap = buttonMap;
        this.blockAllSlots = blockAllSlots;
    }

    public void open(Player player) {
        player.openInventory(getInventory());
    }

    public int getLines() {
        return lines;
    }

    public String getTitle() {
        return title;
    }

    public Map<Integer, Button> getButtonMap() {
        return buttonMap;
    }

    public boolean isBlockAllSlots() {
        return blockAllSlots;
    }

    public void addButton(int slot, Button button) {
        buttonMap.put(slot, button);
    }

    /**
     * Execute the population of menu, if the population strategy is null, then use default strategy.
     */
    public void populate() {
        if (populationStrategy != null) {
            populationStrategy.execute();
            return;
        }

        Inventory inventory = getInventory();
        int inventorySize = lines * 9;

        for (int slot = 0; slot < inventorySize; slot++) {
            Button currentButton = buttonMap.get(slot);

            if (currentButton != null) {
                inventory.setItem(slot, currentButton.getItemStack());
            }
        }
    }

    /**
     * Populate the inventory by provided strategy.
     * @param strategy the strategy to populate the inventory slots.
     */
    public void populate(@NotNull PopulationStrategy strategy) {
        strategy.execute();
    }

    /**
     * Set the population strategy to menu.
     * @param strategy the strategy used to populate the inventory.
     */
    public void setPopulationStrategy(@NotNull PopulationStrategy strategy) {
        this.populationStrategy = strategy;
    }


    //Fired events.

    public BaseHolder setOnClose(Consumer<InventoryCloseEvent> onClose) {
        this.onClose = onClose;
        return this;
    }

    public void executeOnClose(InventoryCloseEvent event) {
        if (this.onClose != null) {
            this.onClose.accept(event);
        }
    }

    public void update(Inventory inventory) {
        buttonMap.forEach((slot, button) -> inventory.setItem(slot, button.getItemStack()));
    }
}
