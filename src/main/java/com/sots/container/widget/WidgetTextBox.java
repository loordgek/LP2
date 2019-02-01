package com.sots.container.widget;

import com.sots.api.container.Widget;
import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;

public class WidgetTextBox extends Widget {
    private final GuiTextField field;
    public WidgetTextBox(int x, int y, int width, int height, int widgetID) {
        super(x, y, width, height, widgetID);
        field = new GuiTextField(widgetID, Minecraft.getInstance().fontRenderer, x, y, width, height);
    }

    @Override
    public boolean onClicked(double mouseX, double mouseY, EnumMouseButton button, boolean shiftDown) {
        return field.mouseClicked(mouseX, mouseY, button.getButtonID());
    }

    public GuiTextField getField() {
        return field;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        field.drawTextField(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {
        return field.charTyped(typedChar, keyCode);
    }
}
