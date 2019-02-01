package com.sots.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

import java.util.function.Supplier;

public class TileRegistryUtil {

    public static void registerTileType(RegistryEvent.Register<TileEntityType<? extends TileEntity>> event, ResourceLocation location, Supplier<TileEntity> supplier){
        event.getRegistry().register(TileEntityType.register(location.getPath(), TileEntityType.Builder.create(supplier)).setRegistryName(location));
    }
}
