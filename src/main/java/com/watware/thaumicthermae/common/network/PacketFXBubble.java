package com.watware.thaumicthermae.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.common.Thaumcraft;

import java.util.Random;

public class PacketFXBubble implements IMessage, IMessageHandler<PacketFXBubble, IMessage> {
    private double posX;
    private double posZ;
    private double minY;
    private float height;

    public PacketFXBubble() {
    }

    public PacketFXBubble(EntityPlayer player) {
        this.posX = player.posX;
        this.posZ = player.posZ;
        this.minY = player.boundingBox.minY;
        this.height = player.height;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeDouble(this.posX);
        buffer.writeDouble(this.posZ);
        buffer.writeDouble(this.minY);
        buffer.writeFloat(this.height);
    }

    public void fromBytes(ByteBuf buffer) {
        posX = buffer.readDouble();
        posZ = buffer.readDouble();
        minY = buffer.readDouble();
        height = buffer.readFloat();
    }

    public IMessage onMessage(PacketFXBubble message, MessageContext ctx) {

        Random rand = Minecraft.getMinecraft().theWorld.rand;

        for(int a = 0; a < Thaumcraft.proxy.particleCount(5); ++a) {
            Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(),
                (float)message.posX - 0.5F + rand.nextFloat(),
                (float)message.minY + rand.nextFloat() * message.height,
                (float)message.posZ - 0.5F + rand.nextFloat(), 1.0F, 0.8F, 0.9F);
        }

        return null;
    }
}
