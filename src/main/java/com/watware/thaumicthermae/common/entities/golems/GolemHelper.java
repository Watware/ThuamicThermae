package com.watware.thaumicthermae.common.entities.golems;

import com.watware.thaumicthermae.common.Tags;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class GolemHelper {

    private static final double eps = 1.5;

    public static boolean canGolemGotoOwner(EntityGolemBase golem) {
        if (!canGolemSanitize(golem)) return false;
        EntityLivingBase owner = golem.getOwner();
        return (golem.getDistanceSqToEntity(owner) > eps);
    }

    public static boolean canGolemDoBath(EntityGolemBase golem) {
        if (!canGolemSanitize(golem)) return false;
        EntityLivingBase owner = golem.getOwner();
        return (golem.getDistanceSqToEntity(owner) <= eps);
    }

    public static boolean canGolemSanitize(EntityGolemBase golem) {
        if (golem.itemCarried == null) return false;
        EntityPlayer owner = (EntityPlayer) golem.getOwner();
        ChunkCoordinates home = golem.getHomePosition();
        if (owner == null) return false;
        if (owner.getDistanceSq(home.posX, home.posY, home.posZ) > Tags.GOLEM_SANITIZE_RANGE_SQ) return false;
        if (!owner.isPotionActive(Config.potionWarpWardID)) return false;
        for (int i = 0; i < 4; i++)
            if (owner.getCurrentArmor(i) != null) return false;
        int i = MathHelper.floor_double(owner.posX);
        int j = MathHelper.floor_double(owner.posY);
        int k = MathHelper.floor_double(owner.posZ);
        return owner.worldObj.getBlock(i, j, k) == ConfigBlocks.blockFluidPure;
    }

    public static boolean canGolemGoHome(EntityGolemBase golem) {
        if (canGolemSanitize(golem)) return false;
        return !(getHomeDistance(golem) <= eps);
    }

    public static boolean canGolemFetchSoap(EntityGolemBase golem) {
        if (golem.itemCarried != null) return false;
        return !(getHomeDistance(golem) > eps);
    }

    private static double getHomeDistance(EntityGolemBase golem) {
        ChunkCoordinates home = golem.getHomePosition();
        return golem.getDistanceSq(home.posX + 0.5, home.posY + 0.5, home.posZ + 0.5);
    }
}
