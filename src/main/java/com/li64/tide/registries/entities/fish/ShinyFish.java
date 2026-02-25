package com.li64.tide.registries.entities.fish;

public interface ShinyFish {
    String tide$SHINY_KEY = "IsShiny";

    default boolean tide$isShiny() { return false; }

    default void tide$setIsShiny(boolean isShiny) {}
}
