package com.sots.container.widget;

import com.sots.api.container.IWidget;
import com.sots.api.container.IWidgetContainer;
import com.sots.api.container.Widget;
import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

public class WidgetContainer extends Widget implements IWidgetContainer {
    List<IWidget> widgets = new ArrayList<>();

    public WidgetContainer(int x, int y, int width, int height, int widgetID, LPSide side) {
        super(x, y, width, height, widgetID);
    }

    @Override
    public void init(LPSide side) {
    }

    @Override
    public void onWidgetClosed(EntityPlayer player) {
        widgets.forEach(widget -> widget.onWidgetClosed(player));
    }

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {
        return widgets.stream().anyMatch(iWidget -> iWidget.keyTyped(typedChar, keyCode));
    }

    @Override
    public boolean onWidgetClicked(double mouseX, double mouseY, EnumMouseButton button, boolean shiftDown) {
        return widgets.stream()
                .filter(widget -> widget.getBounds().containsD(mouseX - getX(), mouseY - getY()))
                .anyMatch(widget -> widget.onWidgetClicked(mouseX, mouseY, button, shiftDown));
    }

    @Override
    public void renderForeGround(int mouseX, int mouseY, float partialTicks) {
        widgets.forEach(widget -> widget.renderForeGround(mouseX - getX(), mouseY - getY(), partialTicks));
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        widgets.forEach(widget -> widget.render(mouseX - getX(), mouseY - getY(), partialTicks));
    }

    @Override
    public void renderBackRound(int mouseX, int mouseY, float partialTicks) {
        widgets.forEach(widget -> widget.renderBackRound(mouseX - getX(), mouseY - getY(), partialTicks));
    }

    @Override
    public boolean onClicked(double mouseX, double mouseY, EnumMouseButton button, boolean shiftDown) {
        return widgets.stream().anyMatch(widget -> widget.onClicked(mouseX - getX(), mouseY - getY(), button, shiftDown));
    }

    @Override
    public boolean onScrollWheel(double wheel, double mouseX, double mouseY, boolean shiftDown) {
        return widgets.stream().filter(widget -> widget.getBounds().containsD(mouseX - getX(), mouseY - getY()))
                .anyMatch(widget -> widget.onScrollWheel(mouseX - getX(), mouseY - getY(), mouseY, shiftDown));
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
