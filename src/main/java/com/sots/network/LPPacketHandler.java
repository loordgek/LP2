package com.sots.network;

import com.sots.util.References;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Objects;

public class LPPacketHandler {
    public static final ResourceLocation NETID = new ResourceLocation(References.MODID, "net");
    private static int netID;
    public static SimpleChannel channel;
    static {
        channel = NetworkRegistry.ChannelBuilder.named(NETID).
                clientAcceptedVersions(s -> Objects.equals(s, "1")).
                serverAcceptedVersions(s -> Objects.equals(s, "1")).
                networkProtocolVersion(() -> "1").
                simpleChannel();
    }


}
