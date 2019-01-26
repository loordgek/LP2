package com.sots.container.widget;

import com.sots.api.container.IWidget;
import com.sots.api.container.IWidgetContainer;
import com.sots.api.container.Widget;
import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import com.sots.network.LPPacketHandler;
import com.sots.network.message.MessageWidgetContainerClicked;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

public class WidgetContainer extends Widget implements IWidgetContainer {
    List<IWidget> widgets = new ArrayList<>();
    public WidgetContainer(int x, int y, int width, int height, int widgetID, LPSide side) {
        super(x, y, width, height, widgetID);
        init(side);
    }

    @Override
    public void init(LPSide side) { }

    @Override
    public final boolean sendToServer() {
        return false;
    }

    @Override
    public void onWidgetClosed(EntityPlayer player) {
        widgets.forEach(widget -> widget.onWidgetClosed(player));
    }

    @Override
    public void keyTyped(char typedChar, int keyCode, boolean keyPressed, EntityPlayer player, LPSide side) {
        widgets.forEach(widget -> widget.keyTyped(typedChar, keyCode, keyPressed, player, side));
    }

    @Override
    public void onWidgetClicked(int mouseX, int mouseY, EnumMouseButton button, boolean shiftDown, EntityPlayer player, LPSide side) {
        widgets.stream().filter(widget -> widget.getBounds().contains(mouseX - getX(), mouseY - getY())).forEach(widget -> {
            if (widget.sendToServer()) {
                sentClickToServer(mouseX - getX(), mouseY - getY(), button.getButtonID(), shiftDown, widget.getID());
            }
            widget.onWidgetClicked(mouseX - getX(), mouseY - getY(), button, shiftDown, player, LPSide.CLIENT);
        });
    }

    protected void sentClickToServer(int mouseX, int mouseY, int button, boolean shiftDown, int widgetID){
        LPPacketHandler.INSTANCE.sendToServer(new MessageWidgetContainerClicked(mouseX, mouseY, button, widgetID, shiftDown, getID()));
    }

    @Override
    public void renderForeGround(int mouseX, int mouseY) {
        widgets.forEach(widget -> widget.renderForeGround(mouseX - getX(), mouseY - getY()));
    }

    @Override
    public void render(int mouseX, int mouseY) {
       widgets.forEach(widget -> widget.render(mouseX - getX(), mouseY - getY()));
    }

    @Override
    public void renderBackRound(int mouseX, int mouseY) {
        widgets.forEach(widget -> widget.renderBackRound(mouseX - getX(), mouseY - getY()));
    }

    @Override
    public void onClicked(int mouseX, int mouseY, EnumMouseButton button, boolean shiftDown, EntityPlayer player, LPSide side) {
        widgets.forEach(widget -> widget.onClicked(mouseX - getX(), mouseY - getY(), button, shiftDown, player, side));
    }

    @Override
    public void onScrollWheel(int wheel, int mouseX, int mouseY, boolean shiftDown) {
        widgets.stream().filter(widget -> widget.getBounds().contains(mouseX - getX(), mouseY - getY())).forEach(widget -> widget.onScrollWheel(mouseX - getX(), mouseY - getY(), mouseY, shiftDown));
    }

    @Override
    public void addWidget(IWidget widget) {
        widgets.add(widget);
    }

    @Override
    public IWidget getWidget(int index) {
        return widgets.get(index);
    }
}
