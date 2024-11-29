package com.watware.thaumicthermae.common.registration;

import com.watware.thaumicthermae.common.entities.golems.cores.SanitizeGolemCore;
import makeo.gadomancy.api.GadomancyApi;
import makeo.gadomancy.api.golems.cores.AdditionalGolemCore;

public class GolemStuffRegister {
    public static SanitizeGolemCore testGolemCore;

    public static void doRegister() {
        testGolemCore = register(new SanitizeGolemCore());
    }

    private static <T extends AdditionalGolemCore> T register(T core) {
        GadomancyApi.registerAdditionalGolemCore(core.getClass().getName().toLowerCase(), core);
        return core;
    }
}
