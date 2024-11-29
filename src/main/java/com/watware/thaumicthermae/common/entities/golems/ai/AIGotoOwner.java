package com.watware.thaumicthermae.common.entities.golems.ai;

import com.watware.thaumicthermae.common.Tags;
import com.watware.thaumicthermae.common.entities.golems.GolemHelper;
import net.minecraft.entity.ai.EntityAIBase;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class AIGotoOwner extends EntityAIBase {

    EntityGolemBase golem;
    int count;

    public AIGotoOwner(EntityGolemBase golem) {
        this.golem = golem;
        super.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
//        System.out.println("BBB");
        if (this.golem.ticksExisted % Tags.GOLEM_DELAY == 0) {
            return GolemHelper.canGolemGotoOwner(golem);
        }
        return false;
    }

    @Override
    public boolean continueExecuting() {
        return this.count-- > 0 && !this.golem.getNavigator().noPath() && GolemHelper.canGolemGotoOwner(this.golem);
    }

    @Override
    public void startExecuting() {
        count = 200;
        this.golem.getNavigator().tryMoveToEntityLiving(golem.getOwner(), golem.getAIMoveSpeed());
    }

    @Override
    public void resetTask() {
        count = 0;
        this.golem.getNavigator().clearPathEntity();
    }
}
