//? if fabric {
package com.li64.tide.compat.accessories;

import com.li64.tide.data.TideTags;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.ArrayList;

public class TrinketsCompat {
    public static void addSurveyItems(Player player, ArrayList<Item> items) {
        var component = TrinketsApi.getTrinketComponent(player);
        if (component.isEmpty()) return;
        component.get().getEquipped(stack -> stack.is(TideTags.Items.INFORMATIONAL))
                .forEach(tuple -> {
                    Item item = tuple.getB().getItem();
                    if (!items.contains(item)) items.add(item);
                });
    }
}
//?}