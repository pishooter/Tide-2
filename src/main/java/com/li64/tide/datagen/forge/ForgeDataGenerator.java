//? if forge {
/*package com.li64.tide.datagen.forge;

import com.li64.tide.Tide;
import com.li64.tide.data.loot.modifiers.SingleItemLootModifier;
import com.li64.tide.registries.TideItems;
import com.li64.tide.registries.entities.TideSpawnConfigs;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Tide.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeDataGenerator extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BIOME_MODS = new RegistrySetBuilder()
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ForgeDataGenerator::bootstrap);

    public ForgeDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BIOME_MODS, Set.of(Tide.MOD_ID));
    }

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var biomes = context.lookup(Registries.BIOME);

        TideSpawnConfigs.onRegisterSpawnConfigs(config -> {
            String entityKey = BuiltInRegistries.ENTITY_TYPE.getKey(config.entityType()).getPath();
            ResourceKey<BiomeModifier> key = ResourceKey.create(
                    ForgeRegistries.Keys.BIOME_MODIFIERS,
                    Tide.resource("add_" + entityKey + "_spawns")
            );
            context.register(key, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                    biomes.getOrThrow(config.biomes()),
                    List.of(new MobSpawnSettings.SpawnerData(
                            config.entityType(), config.weight(),
                            config.minGroup(), config.maxGroup()
                    ))
            ));
        });
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();

        generator.addProvider(true, new ForgeDataGenerator(output, registries));
        generator.addProvider(true, new GlobalLootModifierProvider(output, Tide.MOD_ID) {
            @Override
            protected void start() {
                this.add(
                        "add_echo_fishing_rod",
                        new SingleItemLootModifier(
                                singleLootTableCondition(BuiltInLootTables.ANCIENT_CITY),
                                TideItems.ECHO_FISHING_ROD, 0.1f
                        )
                );

                this.add(
                        "add_blazing_fishing_rod",
                        new SingleItemLootModifier(
                                singleLootTableCondition(BuiltInLootTables.NETHER_BRIDGE),
                                TideItems.BLAZING_FISHING_ROD, 0.3f
                        )
                );

                this.add(
                        "add_prismarine_fishing_rod",
                        new SingleItemLootModifier(
                                singleLootTableCondition(EntityType.ELDER_GUARDIAN.getDefaultLootTable()),
                                TideItems.PRISMARINE_FISHING_ROD, 0.5f
                        )
                );
            }
        });
    }

    private static LootItemCondition[] singleLootTableCondition(ResourceLocation table) {
        return new LootItemCondition[] { new LootTableIdCondition.Builder(table).build() };
    }
}
*///?}