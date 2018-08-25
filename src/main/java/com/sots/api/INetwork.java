package com.sots.api;

import com.sots.routing.promises.PromiseType;
import net.minecraft.util.EnumFacing;

import java.util.Set;
import java.util.UUID;

public interface INetwork {

    void addPowerProvider(ILogisticPowerProvider provider);

    void removePowerProvider(ILogisticPowerProvider provider);

    void addNetworkListener(INetworkListener provider);

    void removeNetworkListener(INetworkListener provider);

    long getTotalPower();

    boolean drainPower(int amount);

    IItemNetworkHandler getItemNetworkHandler();

    IFluidNetworkHandler getFluidNetworkHandler();

    Set<LPRoutedObject<?>> getNetworkContent();

    <T> T getlogisticCapability(Class<T> clazz);

    boolean routeObject(UUID startID, UUID targetID, UUID ModuleID, Object toRoute, EnumFacing facing, PromiseType type);

}
