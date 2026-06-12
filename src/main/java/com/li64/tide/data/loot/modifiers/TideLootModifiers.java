//? if neoforge {
/*package com.li64.tide.data.loot.modifiers;

import com.li64.tide.Tide;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class TideLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Tide.MOD_ID);

    public static final Supplier<MapCodec<SingleItemLootModifier>> SINGLE_ITEM =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("single_item", () -> SingleItemLootModifier.CODEC);
}
*///?}