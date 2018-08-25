package com.sots.network.message;

import com.sots.api.container.IWidgetContainer;
import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import com.sots.container.LPContainer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageWidgetContainerClicked extends MessageWidgetClicked {
    private int containerWidgetID;

    public MessageWidgetContainerClicked(int x, int y, int mouseButton, int widgetID, boolean shiftDown, int containerWidgetID) {
        super(x, y, mouseButton, widgetID, shiftDown);
        this.containerWidgetID = containerWidgetID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        containerWidgetID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(containerWidgetID);
    }

    public static class handler implements IMessageHandler<MessageWidgetContainerClicked, IMessage>{

        @Override
        public IMessage onMessage(MessageWidgetContainerClicked message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().player;
            ((IThreadListener)player.world).addScheduledTask(() -> {
                IWidgetContainer widgetContainer = (IWidgetContainer) ((LPContainer) player.openContainer).widgets.get(message.containerWidgetID);
                widgetContainer.getWidget(message.widgetID).onWidgetClicked(message.x, message.y, EnumMouseButton.getEnumButtonFromID(message.mouseButton), message.shiftDown, player, LPSide.SERVER);
            });
            return null;
        }
    }
}
