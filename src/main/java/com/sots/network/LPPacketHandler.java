package com.sots.network;

import com.sots.network.message.MessagePipeContentUpdate;
import com.sots.network.message.MessageWidgetClicked;
import com.sots.network.message.MessageWidgetContainerClicked;
import com.sots.util.References;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class LPPacketHandler {
    public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(References.MODID);

    private static int id = 0;

    public static void registerMessages() {
        INSTANCE.registerMessage(MessagePipeContentUpdate.MessageHolder.class, MessagePipeContentUpdate.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(MessageWidgetClicked.handler.class, MessageWidgetClicked.class, id++, Side.SERVER);
        INSTANCE.registerMessage(MessageWidgetContainerClicked.handler.class, MessageWidgetContainerClicked.class, id++, Side.SERVER);
    }

}
