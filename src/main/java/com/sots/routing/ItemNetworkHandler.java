package com.sots.routing;

import com.sots.api.IItemNetworkHandler;
import com.sots.api.crafting.ICraftingTemplate;
import com.sots.api.util.data.Triple;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemNetworkHandler implements IItemNetworkHandler {
    private final List<ICraftingTemplate<ItemStack>> craftingList = new ArrayList<>();

    @Override
    public Triple<UUID, UUID, ItemStack> findStorageForItem(@Nonnull ItemStack stack) {
        return null;
    }

    @Override
    public List<ItemStack> getItemSnapShot() {
        return null;
    }

    @Override
    public List<ICraftingTemplate<ItemStack>> getCraftebleItems() {
        return craftingList;
    }
}
