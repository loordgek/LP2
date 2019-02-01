package com.sots.util.holder;

import com.sots.util.References;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(References.MODID)
public class TileHolder {

    @ObjectHolder("Tilegenericpipe")
    public static TileEntityType<? extends TileEntity> TILE_ROUTED_PIPE;

    @ObjectHolder("Tilenetworkcore")
    public static TileEntityType<? extends TileEntity> TILE_NETWORK_CORE;

    @ObjectHolder("Tilebasicpipee")
    public static TileEntityType<? extends TileEntity> TILE_BASIC_PIPE;
}
