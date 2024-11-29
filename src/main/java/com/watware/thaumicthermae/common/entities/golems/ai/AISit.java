package com.watware.thaumicthermae.common.entities.golems.ai;

import com.watware.thaumicthermae.common.Tags;
import net.minecraft.entity.ai.EntityAIBase;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class AISit extends EntityAIBase {

    EntityGolemBase golem;

    public AISit(EntityGolemBase golem) {
        this.golem = golem;
    }

    @Override
    public boolean shouldExecute() {
        return golem.ticksExisted % Tags.GOLEM_DELAY == 0;
    }

    @Override
    public boolean continueExecuting() {
        return true;
    }

    @Override
    public void updateTask() {
        this.golem.getLookHelper().setLookPosition(0.0, 0.0, 0.0, 0.0F, -45.0F);
    }

}
