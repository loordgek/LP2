package com.sots.routing;

import com.sots.routing.promises.PromiseType;
import net.minecraft.util.EnumFacing;

import java.util.UUID;

public class PendingToRoute {

    private final LogisticsRoute route;
    private final UUID augmentID;
    private final Object object;
    private final PromiseType type;
    private final EnumFacing startFacing;
    private boolean readyToRoute = false;

    public PendingToRoute(LogisticsRoute route, UUID augmentID, Object object, PromiseType type, EnumFacing startFacing) {
        this.route = route;
        this.augmentID = augmentID;
        this.object = object;
        this.type = type;
        this.startFacing = startFacing;
    }


    public LogisticsRoute getRoute() {
        return route;
    }

    public UUID getAugmentID() {
        return augmentID;
    }

    public Object getObject() {
        return object;
    }

    public PromiseType getType() {
        return type;
    }

    public boolean isReadyToRoute() {
        return readyToRoute;
    }

    public synchronized void setReadyToRoute() {
        readyToRoute = true;
    }

    public EnumFacing getStartFacing() {
        return startFacing;
    }
}
