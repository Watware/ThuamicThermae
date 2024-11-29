package com.watware.thaumicthermae.common.entities.golems.ai;

import com.watware.thaumicthermae.common.Tags;
import com.watware.thaumicthermae.common.entities.golems.GolemHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.ChunkCoordinates;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class AIGoHome extends EntityAIBase {

    EntityGolemBase golem;
    EntityLivingBase owner;
    int count;

    public AIGoHome(EntityGolemBase golem) {
        this.golem = golem;
        this.owner = golem.getOwner();
        super.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        if (this.golem.ticksExisted % Tags.GOLEM_DELAY == 0) {
//            System.out.println("AAA");
            return GolemHelper.canGolemGoHome(golem);
        }
        return false;
    }

    @Override
    public boolean continueExecuting() {
        return this.count-- > 0 && !this.golem.getNavigator().noPath() && GolemHelper.canGolemGoHome(this.golem);
    }

    @Override
    public void startExecuting() {
        count = 200;
        ChunkCoordinates home = this.golem.getHomePosition();
        this.golem.getNavigator().tryMoveToXYZ(home.posX, home.posY, home.posZ, this.golem.getAIMoveSpeed());
    }

    @Override
    public void resetTask() {
        count = 0;
        this.golem.getNavigator().clearPathEntity();
    }
}
