package com.sots.client.gui;

import com.sots.api.container.IWidget;
import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import com.sots.container.LPContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class LPGuiContainer extends GuiContainer {
    private final LPContainer lpContainer;

    public LPGuiContainer(LPContainer lpContainer) {
        super(lpContainer);
        this.lpContainer = lpContainer;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        lpContainer.widgets.forEach(widget -> widget.renderBackRound(mouseX, mouseY, partialTicks));
    }

    @Override
    public void initGui() {
        lpContainer.widgets.clear();
        lpContainer.initWidgets(LPSide.CLIENT);
        super.initGui();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        List<IWidget> widgets = lpContainer.widgets;
        return  widgets.stream().filter(widget -> widget.getBounds().containsD(mouseX, mouseY))
                .anyMatch(widget -> widget.onWidgetClicked(mouseX, mouseY, EnumMouseButton.getButtonFromID(mouseButton), isShiftKeyDown()));
    }

    @Override
    public boolean mouseScrolled(double wheel) {
        Minecraft minecraft = Minecraft.getInstance();
        MouseHelper mouseHelper = minecraft.mouseHelper;

        double mouseX = mouseHelper.getMouseX() * width / minecraft.mainWindow.getWidth();
        double mouseY = height - mouseHelper.getMouseY() * height / minecraft.mainWindow.getHeight() - 1;
        return lpContainer.widgets.stream()
                .filter(widget -> widget.getBounds().containsD(mouseX, mouseY))
                .anyMatch(widget -> widget.onScrollWheel(wheel, mouseX, mouseY, isShiftKeyDown()));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        lpContainer.widgets.forEach(widget -> widget.renderForeGround(mouseX, mouseY, Minecraft.getInstance().getTickLength()));
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        lpContainer.widgets.forEach(widget -> widget.render(mouseX, mouseY, partialTicks));
        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean handleComponentClick(ITextComponent component) {
        return super.handleComponentClick(component);
    }

    @Override
    public boolean charTyped(char p_charTyped_1_, int p_charTyped_2_) {
        return lpContainer.widgets.stream().anyMatch(iWidget -> iWidget.keyTyped(p_charTyped_1_, p_charTyped_2_));
    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        lpContainer.widgets.clear();
        super.setWorldAndResolution(mc, width, height);
    }

}
