package com.sots.routing.router;

import com.sots.LogisticsPipes2;
import com.sots.api.util.data.Triple;
import com.sots.api.util.data.Tuple;
import com.sots.routing.NetworkNode;
import com.sots.routing.WeightedNetworkNode;
import net.minecraft.util.EnumFacing;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CachedDijkstraRouter extends DijkstraRouter {
    //private Map<Triple<Map<UUID, WeightedNetworkNode>, NetworkNode, NetworkNode>, Triple<NetworkNode, NetworkNode, ArrayDeque<Tuple<UUID, EnumFacing>>>> cache = new HashMap<Triple<Map<UUID, WeightedNetworkNode>, NetworkNode, NetworkNode>, Triple<NetworkNode, NetworkNode, ArrayDeque<Tuple<UUID, EnumFacing>>>>();
    private Map<Tuple<NetworkNode, NetworkNode>, Triple<NetworkNode, NetworkNode, ArrayDeque<Tuple<UUID, EnumFacing>>>> cache = new HashMap<>();

    public CachedDijkstraRouter(Map<UUID, WeightedNetworkNode> junctions) {
        super(junctions);
    }

    @Override
    public Triple<NetworkNode, NetworkNode, ArrayDeque<Tuple<UUID, EnumFacing>>> route(NetworkNode s, NetworkNode t) {
        //Triple<Map<UUID, WeightedNetworkNode>, NetworkNode, NetworkNode> input = new Triple<Map<UUID, WeightedNetworkNode>, NetworkNode, NetworkNode>(super.junctions, s, t);
        Tuple<NetworkNode, NetworkNode> input = new Tuple<>(s, t);
        try { //DEBUG
            if (cache.containsKey(input)) {
                LogisticsPipes2.LOGGER.info("Got a route from cache"); //DEBUG
                return cache.get(input);
            }
        } catch (Exception e) {
            LogisticsPipes2.LOGGER.info(e); //DEBUG
        }


        Triple<NetworkNode, NetworkNode, ArrayDeque<Tuple<UUID, EnumFacing>>> result = super.route(s, t);
        LogisticsPipes2.LOGGER.info("Caching a route to cache"); //DEBUG
        cache.put(input, result);
        return result;
    }

    @Override
    public void shutdown() {
        super.shutdown();
        LogisticsPipes2.LOGGER.info("Clearing cache"); //DEBUG
        cache.clear();
    }


}

