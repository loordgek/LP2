package com.sots.routing.interfaces;

import com.sots.routing.Network;
import com.sots.routing.promises.PromiseType;
import net.minecraft.util.EnumFacing;

import java.util.UUID;

public interface IRoutable {
    /**
     * Wether or not the Pipe itself is a Routed Node
     *
     * @return true=Routed Node, False=passive Pipe
     */
    boolean isRouted();

    /**
     * Wether or not the Pipe is Routeable, aka. if the Network can send stuff through it.
     *
     * @return true=Can be routed through, false=Can't be routed through
     */
    boolean isRoutable();

    /**
     * Wether or not this Pipe is Part of a Network
     */
    boolean hasNetwork();

    /**
     * Override this to specify behavior of a Pipe when it gets added to a Network
     */
    void subscribe(Network parent);

    /**
     * Override this to specify behavior of a Pipe when it gets removed from a Network
     */
    void disconnect();

    /**
     * Wether or not this Pipe is powered by a Network
     */
    boolean hasPower();

    /**
     * Wether or not this Pipe actually actively consumes Power
     */
    boolean consumesPower();

    /**
     * The amount of power that this pipe consumes by default
     *
     * @return Idle Power Cost
     */
    int powerConsumed();

    int posX();

    int posY();

    int posZ();

    void spawnParticle(float r, float g, float b);

    boolean routeObject(UUID targetPipeID, UUID targetModuleID, Object toRoute, EnumFacing facing, PromiseType type);
}
