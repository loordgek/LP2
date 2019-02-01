package com.sots.api;

import com.sots.api.module.ICraftingModule;

import java.util.List;
import java.util.Optional;

public class PipeCraftingManager {
    private ICraftingModule blockingCraft;
    private List<ICraftingModule> craftingModules;
    /**
     * @return the module that is using the crafter, Optional.empty if it was not blocked
     */
    public Optional<ICraftingModule> getBlockingCraft(){
        return Optional.ofNullable(blockingCraft);
    }

    public void setBlockingCraft(ICraftingModule module){
        blockingCraft = module;
    }
}
