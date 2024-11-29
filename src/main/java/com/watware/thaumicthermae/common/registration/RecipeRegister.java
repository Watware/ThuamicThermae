package com.watware.thaumicthermae.common.registration;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;

public class RecipeRegister {
    public static InfusionRecipe recipeCoreSanitize;

    public static void doPostRegister() {
        recipeCoreSanitize = ThaumcraftApi.addInfusionCraftingRecipe(
            ResearchRegister.researchCoreSanitize.getName(),
            new ItemStack(ItemRegister.itemSanitizeGolemCore),
            10,
            new AspectList().add(Aspect.MECHANISM, 40).add(Aspect.MAGIC, 64)
                .add(Aspect.ORDER, 64).add(Aspect.HEAL, 256),
            new ItemStack(ConfigItems.itemZombieBrain),
            new ItemStack[] {
                new ItemStack(ConfigItems.itemGolemCore, 1, 1),
                new ItemStack(ConfigItems.itemBathSalts),
                new ItemStack(ConfigItems.itemSanitySoap),
                new ItemStack(ConfigItems.itemGolemCore, 1, 8),
                new ItemStack(ConfigItems.itemSanityChecker),
                new ItemStack(ConfigBlocks.blockCustomPlant, 1, 4),
            }
        );
    }
}
