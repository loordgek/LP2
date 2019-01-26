package com.sots.container.widget;

import com.sots.api.container.Widget;
import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WidgetTextBox extends Widget {
    private final GuiTextField field;
    public WidgetTextBox(int x, int y, int width, int height, int widgetID) {
        super(x, y, width, height, widgetID);
        field = new GuiTextField(widgetID, Minecraft.getMinecraft().fontRenderer, x, y, width, height);
    }

    @Override
    public void onClicked(int mouseX, int mouseY, EnumMouseButton button, boolean shiftDown, EntityPlayer player, LPSide side) {
        field.mouseClicked(mouseX, mouseY, button.getButtonID());
    }

    public GuiTextField getField() {
        return field;
    }

    @Override
    public void render(int mouseX, int mouseY) {
        field.drawTextBox();
    }

    @Override
    public void keyTyped(char typedChar, int keyCode, boolean keyPressed, EntityPlayer player, LPSide side) {
        field.textboxKeyTyped(typedChar, keyCode);
    }
}
