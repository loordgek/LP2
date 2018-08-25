package com.sots.api;

import com.sots.api.crafting.ICraftingTemplate;
import com.sots.api.util.data.Triple;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public interface IItemNetworkHandler {

    Triple<UUID, UUID, ItemStack> findStorageForItem(@Nonnull ItemStack stack);

    List<ItemStack> getItemSnapShot();

    List<ICraftingTemplate<ItemStack>> getCraftebleItems();
}
