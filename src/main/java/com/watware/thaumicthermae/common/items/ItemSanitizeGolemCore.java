package com.watware.thaumicthermae.common.items;

import com.watware.thaumicthermae.common.Tags;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemSanitizeGolemCore extends Item {

    @SideOnly(Side.CLIENT)
    IIcon icon;

    public ItemSanitizeGolemCore() {
        super();
        this.setMaxStackSize(64);
        this.setUnlocalizedName("ItemGolemCore");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
//        this.icon = ir.registerIcon("thaumcraft:golem_core_bodyguard");
        this.icon = ir.registerIcon("thaumicthermae:golem_core_sanitize");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return this.icon;
    }

    @Override
    public EnumRarity getRarity(ItemStack p_77613_1_) {
        return EnumRarity.uncommon;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean showAdvanced) {
        list.add(StatCollector.translateToLocal(Tags.MODID + ".golem.sanitizecore"));
    }
}
