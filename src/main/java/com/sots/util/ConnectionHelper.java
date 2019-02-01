package com.sots.util;

import com.sots.routing.interfaces.IPipe;
import com.sots.tiles.TileGenericPipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.ArrayList;

public class ConnectionHelper {
    public static boolean canConnectTile(IBlockReader world, BlockPos pos) {
        return world.getTileEntity(pos) != null;
    }

    public static boolean isPipe(IBlockReader worldIn, BlockPos pos, EnumFacing facing) {
        TileEntity target = AccessHelper.getTileSafe(worldIn, pos, facing);
        return (target instanceof IPipe);
    }

    public static ArrayList<String> checkForPipes(IBlockReader world, BlockPos pos) {
        ArrayList<String> hidden = new ArrayList<String>();
        //The Center Block of the Pipe allways has to be shown, thus its never added here

        //North Connection
        if (!isPipe(world, pos, EnumFacing.NORTH)) {
            hidden.add(Connections.NORTH.toString());
            hidden.add(Connections.G_NORTH.toString());
        }

        //South Connection
        if (!isPipe(world, pos, EnumFacing.SOUTH)) {
            hidden.add(Connections.SOUTH.toString());
            hidden.add(Connections.G_SOUTH.toString());
        }

        //East Connection
        if (!isPipe(world, pos, EnumFacing.EAST)) {
            hidden.add(Connections.EAST.toString());
            hidden.add(Connections.G_EAST.toString());
        }

        //West Connection
        if (!isPipe(world, pos, EnumFacing.WEST)) {
            hidden.add(Connections.WEST.toString());
            hidden.add(Connections.G_WEST.toString());
        }

        //Up Connection
        if (!isPipe(world, pos, EnumFacing.UP)) {
            hidden.add(Connections.UP.toString());
            hidden.add(Connections.G_UP.toString());
        }

        //Down Connection
        if (!isPipe(world, pos, EnumFacing.DOWN)) {
            hidden.add(Connections.DOWN.toString());
            hidden.add(Connections.G_DOWN.toString());
        }

        return hidden;
    }

    public static TileGenericPipe getAdjacentPipe(IBlockReader worldIn, BlockPos pos, EnumFacing facing) {
        if (isPipe(worldIn, pos, facing)) {
            TileGenericPipe target = (TileGenericPipe) AccessHelper.getTileSafe(worldIn, pos, facing);
            if (target != null) {
                return target;
            }
        }
        return null;
    }
}
