package com.sots.util.registries;

import com.sots.api.ILPRoutedObjectSupplier;
import com.sots.api.LPRoutedObject;
import com.sots.routing.LPRoutedFluid;
import com.sots.routing.LPRoutedItem;
import com.sots.tiles.TileGenericPipe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;

import java.util.Deque;
import java.util.UUID;

public class LPRoutedTypeRegistry {

    public static void init() {
        LPRoutedObject.registerTypeOfLPRoutedObject(ItemStack.class, new ILPRoutedObjectSupplier() {

            @Override
            public LPRoutedItem getRoutedObject(Object content, EnumFacing initVector, TileGenericPipe holder, Deque<EnumFacing> routingInfo, TileGenericPipe destination, UUID moduleID) {
                return new LPRoutedItem((ItemStack) content, initVector, holder, routingInfo, destination, moduleID);
            }

            @Override
            public LPRoutedObject getRoutedObject(NBTTagCompound compound) {
                return new LPRoutedItem(compound);
            }
        });

        LPRoutedObject.registerTypeOfLPRoutedObject(FluidStack.class, new ILPRoutedObjectSupplier() {
            @Override
            public LPRoutedFluid getRoutedObject(Object content, EnumFacing initVector, TileGenericPipe holder, Deque<EnumFacing> routingInfo, TileGenericPipe destination, UUID moduleID) {
                return new LPRoutedFluid((FluidStack) content, initVector, holder, routingInfo, destination, moduleID);
            }

            @Override
            public LPRoutedObject getRoutedObject(NBTTagCompound compound) {
                return new LPRoutedFluid(compound);
            }
        });
    }

}

