package com.watware.thaumicthermae.common.registration;

import com.watware.thaumicthermae.common.items.ItemSanitizeGolemCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemRegister {
    public static ItemSanitizeGolemCore itemSanitizeGolemCore;

    public static void doRegister() {
        itemSanitizeGolemCore = register(new ItemSanitizeGolemCore());
    }

    public static void doPostRegister() {}

    private static <T extends Item> T register(T item) {
        GameRegistry.registerItem(item, item.getClass().getName().toLowerCase());
        return item;
    }
}
