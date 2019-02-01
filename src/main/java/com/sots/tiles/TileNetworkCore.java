package com.sots.tiles;

import com.sots.routing.Network;
import com.sots.util.holder.TileHolder;

import java.util.UUID;

public class TileNetworkCore extends TileGenericPipe {

    private boolean ownsNetwork = false;

    public TileNetworkCore() {
        super(TileHolder.TILE_NETWORK_CORE);
    }

    private void makeNetwork() {
        if (!ownsNetwork) {
            network = new Network(UUID.randomUUID());
            nodeID = network.setRoot(this);
            hasNetwork = true;
            ownsNetwork = true;
        }
    }

    public void updateNetwork() {
        network.purgeNetwork();
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            if (!ownsNetwork) {
                makeNetwork();
            }
        }
    }
}
