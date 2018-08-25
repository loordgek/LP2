package com.sots.api.inventory;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import java.util.Optional;

@FunctionalInterface
public interface SpecialInventoryProvider {

    Optional<ILPInventory> getSpecialInventory(TileEntity tileEntity, EnumFacing facing);
}
