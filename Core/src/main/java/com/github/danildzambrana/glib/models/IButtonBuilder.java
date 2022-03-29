package com.github.danildzambrana.glib.models;

import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

public interface IButtonBuilder {
    IButtonBuilder setName(String name);

    IButtonBuilder setMaterial(Material material);

    IButtonBuilder setQuantity(int quantity);

    IButtonBuilder setDamage(int damage);

    IButtonBuilder setGlow(boolean glow);

    IButtonBuilder setColor(Color color);

    IButtonBuilder setLore(List<String> lore);

    IButtonBuilder newBuilder();

    IButtonBuilder newBuilder(Button button);

    Button build();
}
