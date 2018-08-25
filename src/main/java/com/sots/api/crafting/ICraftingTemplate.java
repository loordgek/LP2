package com.sots.api.crafting;

import javax.annotation.Nonnull;
import java.util.List;

public interface ICraftingTemplate<T> {

    @Nonnull
    T getResult();

    List<IResource> getRemainingItems();

    List<IResource> getIngredients();

    List<IResource> getToolsUsed();

    int getPriority();

}
