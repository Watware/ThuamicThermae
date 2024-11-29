package com.watware.thaumicthermae.common.registration;

import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ResearchRegister {

    public static ResearchItem researchCoreSanitize;

    public static void doRegister() {

        researchCoreSanitize = new ResearchItem("CORESANITIZE", "GOLEMANCY",
            (new AspectList()).add(Aspect.TOOL, 3).
                add(Aspect.EXCHANGE, 3).
                add(Aspect.MECHANISM, 3).
                add(Aspect.MAN, 3).
                add(Aspect.HEAL, 5),
            -9, 1, 3,
            new ItemStack(ItemRegister.itemSanitizeGolemCore));
        researchCoreSanitize.setConcealed().setParents("COREUSE");
        researchCoreSanitize.registerResearchItem();
    }
    public static void doPostRegister() {
        researchCoreSanitize.setPages(
            new ResearchPage("tc.research_page.CORESANITIZE.1"),
            new ResearchPage("tc.research_page.CORESANITIZE.2"),
            new ResearchPage(RecipeRegister.recipeCoreSanitize));
    }
}
