//? if neoforge || forge {
/*package com.li64.tide.compat.accessories;

import com.li64.tide.data.TideTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;

public class CuriosCompat {
    public static void addSurveyItems(Player player, ArrayList<Item> items) {
        var itemHandler = CuriosApi.getCuriosInventory(player);
        if (itemHandler.isEmpty()) return;
        itemHandler.get().findCurios(stack -> stack.is(TideTags.Items.INFORMATIONAL))
                .forEach(result -> {
                    Item item = result.stack().getItem();
                    if (!items.contains(item)) items.add(item);
                });
    }
}
*///?}