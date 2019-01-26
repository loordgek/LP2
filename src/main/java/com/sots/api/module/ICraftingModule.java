package com.sots.api.module;

import com.sots.api.crafting.ICraftingTemplate;

public interface ICraftingModule extends IModule {

    ICraftingTemplate<?> getTemplate();

    @Override
    default ModuleType modType() {
        return ModuleType.CRAFT;
    }
}
