package com.sots.api.module;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class CapabilityModule {
    @CapabilityInject(IModule.class)
    public static Capability<IModule> CAPABILTY_MOODULE = null;
}
