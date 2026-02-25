package com.li64.tide.client;

import net.minecraft.client.renderer.ShaderInstance;

public class TideCoreShaders {
    public static ShaderInstance FULL_WHITE;
    public static ShaderInstance FULL_WHITE_ITEM;
    public static ShaderInstance SHINY_ITEM;
    public static ShaderInstance CUSTOM_SHINY_ITEM;
    public static ShaderInstance CUSTOM_SHINY_ENTITY;

    public static ShaderInstance fullWhite() {
        return FULL_WHITE;
    }

    public static ShaderInstance fullWhiteItem() {
        return FULL_WHITE_ITEM;
    }

    public static ShaderInstance shinyItem() {
        return SHINY_ITEM;
    }

    public static ShaderInstance customShinyItem() {
        return CUSTOM_SHINY_ITEM;
    }
}
