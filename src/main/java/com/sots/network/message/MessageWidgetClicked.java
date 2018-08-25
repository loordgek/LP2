package com.sots.network.message;

import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import com.sots.container.LPContainer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageWidgetClicked implements IMessage {
    protected int x,y, mouseButton, widgetID;
    protected boolean shiftDown;

    public MessageWidgetClicked(int x, int y, int mouseButton, int widgetID, boolean shiftDown) {
        this.x = x;
        this.y = y;
        this.mouseButton = mouseButton;
        this.widgetID = widgetID;
        this.shiftDown = shiftDown;
    }

    public MessageWidgetClicked() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        mouseButton = buf.readInt();
        widgetID = buf.readInt();
        shiftDown = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(mouseButton);
        buf.writeInt(widgetID);
        buf.writeBoolean(shiftDown);
    }

    public static class handler implements IMessageHandler<MessageWidgetClicked, IMessage>{

        @Override
        public IMessage onMessage(MessageWidgetClicked message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().player;
            ((IThreadListener)player.world).addScheduledTask(() -> ((LPContainer) player.openContainer).widgets.get(message.widgetID).onWidgetClicked(message.x, message.y, EnumMouseButton.getEnumButtonFromID(message.mouseButton), message.shiftDown, player, LPSide.SERVER));
            return null;
        }
    }

}
