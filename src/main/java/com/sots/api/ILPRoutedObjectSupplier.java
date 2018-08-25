package com.sots.api;

import com.sots.tiles.TileGenericPipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

import java.util.Deque;
import java.util.UUID;

public interface ILPRoutedObjectSupplier {

    LPRoutedObject getRoutedObject(Object content, EnumFacing initVector, TileGenericPipe holder, Deque<EnumFacing> routingInfo, TileGenericPipe destination, UUID moduleID);

    LPRoutedObject getRoutedObject(NBTTagCompound compound);
}
