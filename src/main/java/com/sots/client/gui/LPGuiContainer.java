package com.sots.client.gui;

import com.sots.api.container.IWidget;
import com.sots.api.container.IWidgetContainer;
import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import com.sots.container.LPContainer;
import com.sots.network.LPPacketHandler;
import com.sots.network.message.MessageDragOverWidget;
import com.sots.network.message.MessageWidgetClicked;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LPGuiContainer extends GuiContainer {
    public final Set<IWidget> draggedWidgets = new HashSet<>();
    private final LPContainer lpContainer;

    public LPGuiContainer(LPContainer lpContainer) {
        super(lpContainer);
        this.lpContainer = lpContainer;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        lpContainer.widgets.forEach(widget -> widget.renderBackRound(mouseX, mouseY));
    }

    @Override
    public void initGui() {
        lpContainer.widgets.clear();
        lpContainer.initWidgets(LPSide.CLIENT);
        super.initGui();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        List<IWidget> widgets = lpContainer.widgets;
        widgets.stream().filter(widget -> widget.getBounds().contains(mouseX, mouseY)).forEach(widget -> {
            if (widget instanceof IWidgetContainer) {
                widget.onWidgetClicked(mouseX, mouseY, EnumMouseButton.getEnumButtonFromID(mouseButton), isShiftKeyDown(), clientPlayer(), LPSide.CLIENT);
            } else {
                if (widget.sendToServer()) {
                    LPPacketHandler.INSTANCE.sendToServer(new MessageWidgetClicked(mouseX, mouseY, mouseButton, widget.getID(), isShiftKeyDown()));
                }
                widget.onWidgetClicked(mouseX, mouseY, EnumMouseButton.getEnumButtonFromID(mouseButton), isShiftKeyDown(), clientPlayer(), LPSide.CLIENT);
            }
        });
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        lpContainer.widgets.forEach(widget -> widget.renderForeGround(mouseX, mouseY));
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        lpContainer.widgets.stream()
                .filter(widget -> widget.getBounds().contains(mouseX, mouseY))
                .forEach(widget -> widget.mouseClickMove(mouseX, mouseY, EnumMouseButton.getEnumButtonFromID(clickedMouseButton), timeSinceLastClick, clientPlayer()));
        lpContainer.widgets.stream()
                .filter(widget -> widget.getBounds().contains(mouseX, mouseY))
                .filter(widget -> !draggedWidgets.contains(widget))
                .forEach(widget -> {
                    if (widget.sendToServer())
                        LPPacketHandler.INSTANCE.sendToServer(new MessageDragOverWidget());
                    widget.onMouseDrag(mouseX, mouseY, EnumMouseButton.getEnumButtonFromID(clickedMouseButton), timeSinceLastClick, clientPlayer(), draggedWidgets, LPSide.CLIENT);
                    draggedWidgets.add(widget);

                });
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        lpContainer.widgets.forEach(widget -> widget.render(mouseX, mouseY));
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private EntityPlayer clientPlayer() {
        return Minecraft.getMinecraft().player;
    }

    @Override
    public void handleMouseInput() throws IOException {
        int wheel = Mouse.getEventDWheel();
        if (wheel != 0) {
            int mouseX = Mouse.getEventX() * width / Minecraft.getMinecraft().displayWidth;
            int mouseY = height - Mouse.getEventY() * height / Minecraft.getMinecraft().displayHeight - 1;
            lpContainer.widgets.stream().filter(widget -> widget.getBounds().contains(mouseX, mouseY)).forEach(widget -> widget.onScrollWheel(wheel, mouseX, mouseY, isShiftKeyDown()));
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        lpContainer.keyTyped(typedChar, keyCode, Keyboard.getEventKeyState(), clientPlayer());
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        lpContainer.widgets.clear();
        super.setWorldAndResolution(mc, width, height);
    }

}
