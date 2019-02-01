package com.sots.util;

import com.sots.tiles.TileGenericPipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.chunk.Chunk;

import javax.annotation.Nullable;

public class AccessHelper {

    @Nullable
    public static TileEntity getTileSafe(IBlockReader worldIn, BlockPos pos, EnumFacing facing) {
        return worldIn instanceof ChunkCache ?
                ((ChunkCache) worldIn).getTileEntity(pos.offset(facing), Chunk.EnumCreateEntityType.CHECK)
                :
                worldIn.getTileEntity(pos.offset(facing));
    }

    @Nullable
    public static TileEntity getTileSafe(IBlockReader worldIn, BlockPos pos) {
        return worldIn instanceof ChunkCache ?
                ((ChunkCache) worldIn).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK)
                :
                worldIn.getTileEntity(pos);
    }

    public static TileGenericPipe getPipeSafe(IBlockReader worldIn, BlockPos pos, EnumFacing facing) {
        TileEntity target = worldIn instanceof ChunkCache ?
                ((ChunkCache) worldIn).getTileEntity(pos.offset(facing), Chunk.EnumCreateEntityType.CHECK)
                :
                worldIn.getTileEntity(pos.offset(facing));
        if (target instanceof TileGenericPipe)
            return (TileGenericPipe) target;
        return null;
    }
}
