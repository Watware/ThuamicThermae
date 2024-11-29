package com.watware.thaumicthermae.common.entities.golems.ai;

import com.watware.thaumicthermae.common.Tags;
import com.watware.thaumicthermae.common.ThaumicThermae;
import com.watware.thaumicthermae.common.entities.golems.GolemHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.lib.utils.InventoryUtils;

public class AIFetchSoap extends EntityAIBase {

    EntityGolemBase golem;
    EntityLivingBase owner;
    int chestTick;
    IInventory inv;

    public AIFetchSoap(EntityGolemBase golem) {
        this.golem = golem;
        this.owner = golem.getOwner();
        super.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        if (this.golem.ticksExisted % Tags.GOLEM_DELAY == 0) {
            if (!GolemHelper.canGolemFetchSoap(golem)) return false;
            ChunkCoordinates home = golem.getHomePosition();
            ForgeDirection facing = ForgeDirection.getOrientation(this.golem.homeFacing);
            int cX = home.posX - facing.offsetX;
            int cY = home.posY - facing.offsetY;
            int cZ = home.posZ - facing.offsetZ;
            TileEntity tile = this.golem.worldObj.getTileEntity(cX, cY, cZ);
            if (!(tile instanceof IInventory)) return false;

            ItemStack needed = new ItemStack(ConfigItems.itemSanitySoap);
            needed.stackSize = 1;
            ItemStack result = InventoryUtils.extractStack((IInventory) tile, needed, facing.ordinal(),
                true, true, true, true);
            if (result != null) {
                this.golem.setCarried(result);
                this.golem.updateCarried();

                try {
                    ((IInventory) tile).openInventory();
                } catch (Exception var15) {
                    ThaumicThermae.LOG.error("Failed to open inventory while fetching soap.");
                }

                this.chestTick = 10;
                this.inv = (IInventory) tile;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean continueExecuting() {
        return this.chestTick-- > 0;
    }

    @Override
    public void resetTask() {
        chestTick = 0;
        try {
            if (this.inv != null) inv.closeInventory();
        } catch (Exception e) {
            ThaumicThermae.LOG.error("Failed to close inventory while fetching soap.");
        }
        this.golem.getNavigator().clearPathEntity();
    }
}
