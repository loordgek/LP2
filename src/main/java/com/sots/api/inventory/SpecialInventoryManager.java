package com.sots.api.inventory;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class SpecialInventoryManager {
    private final List<SpecialInventoryProvider> inventories = new ArrayList<>();

    public Optional<ILPInventory> getInventoryFromTileEnity(TileEntity entity, EnumFacing facing){
        for (SpecialInventoryProvider sip : inventories){
            if (sip.getSpecialInventory(entity, facing).isPresent())
                return sip.getSpecialInventory(entity, facing);
        }

        if (entity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing)){

        }

        return Optional.empty();
    }
}
