package com.github.danildzambrana.glib.models;

@FunctionalInterface
public interface PopulationStrategy {

    /**
     * Strategy to populate the inventory.
     */
    void execute();
}
