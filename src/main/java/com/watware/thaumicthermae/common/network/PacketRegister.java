package com.watware.thaumicthermae.common.network;

import com.watware.thaumicthermae.common.Tags;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketRegister {
    public static final SimpleNetworkWrapper INSTANCE;

    public static void doRegister() {
        int idx = 0;
        INSTANCE.registerMessage(PacketFXBubble.class, PacketFXBubble.class, idx++, Side.CLIENT);
    }

    static {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Tags.MODID);
    }
}
