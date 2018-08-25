package com.sots.tiles;

import com.sots.routing.interfaces.IPipe;
import com.sots.routing.interfaces.IRoutable;

public class TileChassisI extends TileGenericPipe implements IPipe, IRoutable {

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

}
