package com.watware.thaumicthermae.common;

import com.watware.thaumicthermae.common.network.PacketRegister;
import com.watware.thaumicthermae.common.registration.GolemStuffRegister;
import com.watware.thaumicthermae.common.registration.ItemRegister;
import com.watware.thaumicthermae.common.registration.RecipeRegister;
import com.watware.thaumicthermae.common.registration.ResearchRegister;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        ThaumicThermae.LOG.info("BEGIN preInit.");

        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        PacketRegister.doRegister();
        ItemRegister.doRegister();
        GolemStuffRegister.doRegister();

        ThaumicThermae.LOG.info("END preInit.");
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {}

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        ResearchRegister.doRegister();
        RecipeRegister.doPostRegister();
        ResearchRegister.doPostRegister();
    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}
}
