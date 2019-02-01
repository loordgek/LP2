package com.sots.tiles;

import com.sots.api.LPRoutedObject;
import com.sots.api.util.data.Triple;
import com.sots.routing.interfaces.IPipe;
import com.sots.routing.interfaces.IRoutable;
import com.sots.routing.promises.LogisticsPromise;
import com.sots.routing.promises.PromiseType;
import com.sots.util.Connections;
import com.sots.util.holder.TileHolder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TileRoutedPipe extends TileGenericPipe implements IRoutable, IPipe {

    protected boolean hasInv = false;
    protected int ticksTillSparkle = 0;
    protected List<LogisticsPromise> promises = new ArrayList<>();

    public TileRoutedPipe() {
        super(TileHolder.TILE_ROUTED_PIPE);
    }


    public ArrayList<String> checkConnections(IBlockReader world, BlockPos pos) {
        ArrayList<String> hidden = new ArrayList<String>();
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


    public void onItemCatch(LPRoutedObject item) {
        if (!promises.isEmpty()) {
            LogisticsPromise toRemove = promises.stream()
                    .filter(promise -> item.getID().equals(promise.getPromiseID()))
                    .findFirst().get();
            promises.remove(toRemove);
        }
    }

    @Override
    public void tick() {
        super.tick();
        ticksTillSparkle += 1;
        if (ticksTillSparkle == 10) {
            if (!promises.isEmpty()) {
                promises.forEach(promise -> {
                    Triple<Float, Float, Float> rgb = PromiseType.getRGBFromType(promise.getType());
                    this.spawnParticle(rgb.getFirst(), rgb.getSecnd(), rgb.getThird());
                });
            }
            ticksTillSparkle = 0;
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        super.breakBlock(world, pos, state, player);
    }

}
