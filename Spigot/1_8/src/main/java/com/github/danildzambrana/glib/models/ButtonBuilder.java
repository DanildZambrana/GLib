package com.github.danildzambrana.glib.models;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;
import java.util.stream.Collectors;

public class ButtonBuilder implements IButtonBuilder{
    private String name = "";
    private Material material = Material.STONE;
    private int quantity = 1;
    private int damage = -1;
    private boolean isGlow = false;
    private Color color;
    private List<String> lore;

    private ButtonBuilder() {
    }

    private ButtonBuilder(Button button) {
        ItemStack stack = button.getItemStack();
        ItemMeta meta = stack.getItemMeta();

        this.name = meta.getDisplayName();
        this.material = stack.getType();
        this.quantity = stack.getAmount();
        this.lore = meta.getLore();

        if (canBeDamaged(material)) {
            this.damage = stack.getDurability();
        }

        if (meta.getEnchants().size() >= 1) {
            this.isGlow = true;
        }

        if (meta instanceof LeatherArmorMeta) {
            this.color = ((LeatherArmorMeta) meta).getColor();
        }
    }

    @Override
    public ButtonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ButtonBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    @Override
    public ButtonBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public ButtonBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public ButtonBuilder setGlow(boolean glow) {
        isGlow = glow;
        return this;
    }

    @Override
    public ButtonBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public ButtonBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    @Override
    public IButtonBuilder newBuilder() {
        return new ButtonBuilder();
    }

    @Override
    public IButtonBuilder newBuilder(Button button) {
        return new ButtonBuilder(button);
    }

    @Override
    public Button build() {
        ItemStack stack = new ItemStack(material, quantity);
        ItemMeta meta = stack.getItemMeta();

        if (!name.isEmpty()) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        }

        if (damage > -1) {
            Material type = stack.getType();

            if (canBeDamaged(type)) {
                stack.setDurability((short) damage);
            }
        }

        if (isGlow) {
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        if (color != null) {
            if (meta instanceof LeatherArmorMeta) {
                ((LeatherArmorMeta) meta).setColor(color);
            }
        }

        if (lore != null) {
            meta.setLore(lore.stream()
                    .map(line -> ChatColor.translateAlternateColorCodes('&', line))
                    .collect(Collectors.toList()));
        }

        stack.setItemMeta(meta);

        return new Button(stack);
    }

    private boolean canBeDamaged(Material type) {
        return type.name().endsWith("AXE")
                || type.name().endsWith("PICKAXE")
                || type.name().endsWith("SPADE")
                || type.name().endsWith("SWORD")
                || type.name().endsWith("HOE");
    }
}
