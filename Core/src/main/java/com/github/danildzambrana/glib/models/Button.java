package com.github.danildzambrana.glib.models;

import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Button extends ItemStack implements ConfigurationSerializable, Cloneable{
    private final Map<ClickType, List<Action>> actionMap;
    private final ItemStack itemStack;

    Button(@NotNull ItemStack itemStack, Map<ClickType, List<Action>> actionMap) {
        this.itemStack = itemStack;
        this.actionMap = actionMap;
    }

    Button(ItemStack itemStack) {
        this(itemStack, new HashMap<>());
    }

    Button(@NotNull ItemStack stack, String name) throws IllegalArgumentException {
        this.actionMap = new HashMap<>();

        if (!name.isEmpty()) {
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            stack.setItemMeta(meta);
        }

        this.itemStack = stack;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("item", itemStack);
        return map;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public static IButtonBuilder getBuilder() {
        return null;
    }

    public static IButtonBuilder getBuilder(Button button) {
        return null;
    }

    public Button addAction(@Nullable ClickType clicked, Action action) {
        List<Action> actions = actionMap.get(clicked);

        if (actions == null) {
            actions = new ArrayList<>();
        }

        actions.add(action);

        actionMap.put(clicked, actions);
        return this;
    }


    public Map<ClickType, List<Action>> getActionMap() {
        return actionMap;
    }

    @Override
    public @NotNull Button clone() {
        return new Button(itemStack, new HashMap<>(actionMap));
    }
}
