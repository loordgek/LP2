package com.sots.routing;

import com.google.common.collect.ImmutableMap;
import com.sots.api.IFluidNetworkHandler;
import com.sots.api.IItemNetworkHandler;
import com.sots.api.ILogisticPowerProvider;
import com.sots.api.INetwork;
import com.sots.api.INetworkListener;
import com.sots.api.LPRoutedObject;
import com.sots.event.AttachLogisticCapEventIMPL;
import com.sots.routing.promises.PromiseType;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NetworkTest implements INetwork {
    private final List<ILogisticPowerProvider> powerProviders = new ArrayList<>();
    private final Map<Class, Object> logisticCapablityMap;

    public NetworkTest() {
        this.logisticCapablityMap = ImmutableMap.copyOf(new AttachLogisticCapEventIMPL().getSupplierMap());
    }

    @Override
    public void addPowerProvider(ILogisticPowerProvider provider) {
        powerProviders.add(provider);
    }

    @Override
    public void removePowerProvider(ILogisticPowerProvider provider) {
        powerProviders.remove(provider);
    }

    @Override
    public void addNetworkListener(INetworkListener provider) {

    }

    @Override
    public void removeNetworkListener(INetworkListener provider) {

    }

    @Override
    public long getTotalPower() {
        return powerProviders.stream().mapToLong(ILogisticPowerProvider::getPower).sum();
    }

    @Override
    public boolean drainPower(int amount) {
        int toDrain = amount;
        for (ILogisticPowerProvider powerProvider : powerProviders) {
            toDrain -= powerProvider.drainPower(toDrain);
            if (toDrain == 0)
                return true;
        }
        return false;
    }

    @Override
    public IItemNetworkHandler getItemNetworkHandler() {
        return null;
    }

    @Override
    public IFluidNetworkHandler getFluidNetworkHandler() {
        return null;
    }

    @Override
    public Set<LPRoutedObject<?>> getNetworkContent() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getlogisticCapability(Class<T> clazz) {
        return (T) logisticCapablityMap.get(clazz);
    }

    @Override
    public boolean routeObject(UUID startID, UUID targetID, UUID ModuleID, Object toRoute, EnumFacing facing, PromiseType type) {
        return false;
    }

}
