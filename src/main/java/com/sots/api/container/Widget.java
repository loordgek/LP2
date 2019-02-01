package com.sots.api.container;

import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.Rectangle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class Widget implements IWidget {
    private final Rectangle rectangle;
    private final int widgetID;

    @Nullable
    private IWidget parent;

    public Widget(int x, int y, int width, int height, int widgetID) {

        this.widgetID = widgetID;
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public Widget(Rectangle rectangle, int widgetID){
        this.rectangle = rectangle;
        this.widgetID = widgetID;
    }

    @Override
    public boolean sendToServer(){
        return false;
    }

    @Override
    public int getID() {
        return widgetID;
    }

    @Override
    public void renderForeGround(int mouseX, int mouseY, float partialTicks) {}

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {}

    @Override
    public void renderBackRound(int mouseX, int mouseY, float partialTicks) {}

    @Override
    public boolean onWidgetClicked(double mouseX, double mouseY, EnumMouseButton button, boolean shiftDown) {
        return false;
    }

    @Override
    public boolean onClicked(double mouseX, double mouseY, EnumMouseButton button, boolean shiftDown) {
        return false;
    }

    @Override
    public boolean onScrollWheel(double wheel, double mouseX, double mouseY, boolean shiftDown) {
        return false;
    }

    @Override
    public void onWidgetClosed(EntityPlayer player) {}

    @Override
    @Nonnull
    public Rectangle getBounds() {
        return rectangle;
    }

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {return false; }

    @Nullable
    public IWidget getParent() {
        return parent;
    }

    @Override
    public void setParent(@Nonnull IWidget parent) {
        this.parent = parent;
    }

    @Override
    public void addTooltip(int mouseX, int mouseY, List<ITextComponent> tooltips, boolean shift, EntityPlayer player) {

    }
}
