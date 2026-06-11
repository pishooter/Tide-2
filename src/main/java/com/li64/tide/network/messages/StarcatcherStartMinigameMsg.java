//? if neoforge || forge {
/*//? if >=1.21 {
package com.li64.tide.network.messages;

import com.li64.tide.Tide;
import com.li64.tide.compat.starcatcher.TideStarcatcherMinigameScreen;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public record StarcatcherStartMinigameMsg(FishProperties properties, ItemStack rod) implements TidePacketPayload {
    public static final ResourceLocation ID = Tide.resource("start_starcatcher_minigame");
    @Override public ResourceLocation id() { return ID; }

    public StarcatcherStartMinigameMsg(RegistryFriendlyByteBuf buf) {
        this(
            ByteBufCodecs.fromCodec(FishProperties.CODEC).decode(buf),
            ItemStack.STREAM_CODEC.decode(buf)
        );
    }

    public static void encode(StarcatcherStartMinigameMsg message, RegistryFriendlyByteBuf buf) {
        ByteBufCodecs.fromCodec(FishProperties.CODEC).encode(buf, message.properties);
        ItemStack.STREAM_CODEC.encode(buf, message.rod);
    }

    public static void handle(StarcatcherStartMinigameMsg message, Player player) {
        Minecraft.getInstance().setScreen(new TideStarcatcherMinigameScreen(message.properties, message.rod));
    }
}
//?} else {
/^package com.li64.tide.network.messages;

import com.li64.tide.Tide;
import com.li64.tide.compat.starcatcher.TideStarcatcherMinigameScreen;
import com.wdiscute.starcatcher.registry.FishProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public record StarcatcherStartMinigameMsg(FishProperties properties, ItemStack rod) implements TidePacketPayload {
    public static final ResourceLocation ID = Tide.resource("start_starcatcher_minigame");
    @Override public ResourceLocation id() { return ID; }

    public StarcatcherStartMinigameMsg(FriendlyByteBuf buf) {
        this(
                buf.readJsonWithCodec(FishProperties.CODEC),
                buf.readJsonWithCodec(ItemStack.CODEC)
        );
    }

    public static void encode(StarcatcherStartMinigameMsg message, FriendlyByteBuf buf) {
        buf.writeJsonWithCodec(FishProperties.CODEC, message.properties);
        buf.writeJsonWithCodec(ItemStack.CODEC, message.rod);
    }

    public static void handle(StarcatcherStartMinigameMsg message, Player player) {
        Minecraft.getInstance().setScreen(new TideStarcatcherMinigameScreen(message.properties, message.rod));
    }
}
^///?}
*///?}