package com.watware.thaumicthermae.common.entities.golems.cores;

import com.watware.thaumicthermae.common.entities.golems.ai.AIDoBath;
import com.watware.thaumicthermae.common.entities.golems.ai.AIFetchSoap;
import com.watware.thaumicthermae.common.entities.golems.ai.AIGoHome;
import com.watware.thaumicthermae.common.entities.golems.ai.AIGotoOwner;
import com.watware.thaumicthermae.common.registration.ItemRegister;
import makeo.gadomancy.api.golems.cores.AdditionalGolemCore;
import net.minecraft.item.ItemStack;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class SanitizeGolemCore extends AdditionalGolemCore {
    public SanitizeGolemCore() {
    }

    @Override
    public void setupGolem(EntityGolemBase golem) {
        golem.tasks.addTask(0, new AIDoBath(golem));
        golem.tasks.addTask(1, new AIGotoOwner(golem));
        golem.tasks.addTask(2, new AIGoHome(golem));
        golem.tasks.addTask(3, new AIFetchSoap(golem));
//        golem.tasks.addTask(4, new AISit(golem));
    }

    @Override
    public ItemStack getToolItem(EntityGolemBase golem) {
        return golem.getDataWatcher().getWatchableObjectItemStack(16);
    }

    @Override
    public boolean hasGui() {
        return false;
    }

    public String getUnlocalizedName() {
        return "thaumicbath.golem.sanitizecore";
    }

    public ItemStack getItem() {
        return new ItemStack(ItemRegister.itemSanitizeGolemCore);
    }
}
