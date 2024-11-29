package com.watware.thaumicthermae.common.entities.golems.ai;

import com.watware.thaumicthermae.common.Tags;
import com.watware.thaumicthermae.common.entities.golems.GolemHelper;
import com.watware.thaumicthermae.common.network.PacketFXBubble;
import com.watware.thaumicthermae.common.network.PacketRegister;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class AIDoBath extends EntityAIBase {

    EntityGolemBase golem;
    int count;
    float chance;

    public AIDoBath(EntityGolemBase golem) {
        this.golem = golem;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        if (this.golem.ticksExisted % Tags.GOLEM_DELAY == 0) {
            return GolemHelper.canGolemDoBath(golem);
        }
        return false;
    }

    @Override
    public boolean continueExecuting() {
        return this.count-- > 0 && GolemHelper.canGolemDoBath(this.golem);
    }

    @Override
    public void updateTask() {
        golem.startRightArmTimer();
        EntityLivingBase owner = golem.getOwner();
        PacketRegister.INSTANCE.sendToAllAround(new PacketFXBubble((EntityPlayer) owner), new NetworkRegistry.TargetPoint(owner.dimension, owner.posX, owner.posY, owner.posZ, 8));
        golem.getLookHelper().setLookPosition(owner.posX, owner.posY + (double) owner.getEyeHeight(), owner.posZ, 10.0F, (float) golem.getVerticalFaceSpeed());
        owner.worldObj.playSoundAtEntity(owner, "thaumcraft:roots", 0.1F, 1.5F + owner.worldObj.rand.nextFloat() * 0.2F);
    }

    @Override
    public void startExecuting() {
        count = 195;
        chance = 0.83F;
        for (int i = 0; i < 3; i++) {
            byte upgrade = golem.getUpgrade(i);
            if (upgrade == 5) {
                chance -= 0.05F;
                count -= 20;
            }
            if (upgrade == 4) {
                chance += 0.05F;
                count += 40;
            }
        }
        if (golem.getGolemType().name().equals("SILVERWOOD")) {
            chance += 0.2F;
        }
    }

    @Override
    public void resetTask() {
        if (count <= 0) {
            this.golem.setCarried(null);
            EntityLivingBase owner = golem.getOwner();
            if (golem.worldObj.rand.nextFloat() < chance && Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(owner.getCommandSenderName()) > 0) {
                golem.worldObj.playSoundAtEntity(owner, "thaumcraft:craftstart", 0.25F, 1.0F);
                Thaumcraft.addStickyWarpToPlayer((EntityPlayer) owner, -1);
            }
            if (Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(owner.getCommandSenderName()) > 0) {
                Thaumcraft.addWarpToPlayer((EntityPlayer) owner, -Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(owner.getCommandSenderName()), true);
            }
        }
        count = 0;
    }
}
