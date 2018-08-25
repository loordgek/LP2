package com.sots.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageDragOverWidget implements IMessage {
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class handler implements IMessageHandler<MessageDragOverWidget, IMessage>{

        @Override
        public IMessage onMessage(MessageDragOverWidget message, MessageContext ctx) {
            return null;
        }
    }
}
