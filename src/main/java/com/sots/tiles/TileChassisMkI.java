package com.sots.tiles;

import com.sots.util.Connections;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.ArrayList;

public class TileChassisMkI extends TileGenericPipe {

    public TileChassisMkI(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ArrayList<String> checkConnections(IBlockReader world, BlockPos pos) {
        ArrayList<String> hidden = new ArrayList<>();
        if (down != ConnectionTypes.BLOCK) {
            hidden.add(Connections.C_DOWN.toString());
            if (down != ConnectionTypes.PIPE)
                hidden.add(Connections.DOWN.toString());
        }
        if (up != ConnectionTypes.BLOCK) {
            hidden.add(Connections.C_UP.toString());
            if (up != ConnectionTypes.PIPE)
                hidden.add(Connections.UP.toString());
        }
        if (north != ConnectionTypes.BLOCK) {
            hidden.add(Connections.C_NORTH.toString());
            if (north != ConnectionTypes.PIPE)
                hidden.add(Connections.NORTH.toString());
        }
        if (south != ConnectionTypes.BLOCK) {
            hidden.add(Connections.C_SOUTH.toString());
            if (south != ConnectionTypes.PIPE)
                hidden.add(Connections.SOUTH.toString());
        }
        if (west != ConnectionTypes.BLOCK) {
            hidden.add(Connections.C_WEST.toString());
            if (west != ConnectionTypes.PIPE)
                hidden.add(Connections.WEST.toString());
        }
        if (east != ConnectionTypes.BLOCK) {
            hidden.add(Connections.C_EAST.toString());
            if (east != ConnectionTypes.PIPE)
                hidden.add(Connections.EAST.toString());
        }

        return hidden;
    }

    @Override
    public boolean isRouted() {
        return true;
    }

    @Override
    public boolean isRoutable() {
        return true;
    }

    @Override
    public boolean hasPower() {
        return false;
    }

    @Override
    public boolean consumesPower() {
        return true;
    }

    @Override
    public int powerConsumed() {
        return 1;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
    }
}
