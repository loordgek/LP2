package com.sots.api.container;

import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import com.sots.api.util.Rectangle;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class Widget implements IWidget {
    private final Rectangle rectangle;
    private final int widgetID;
    private boolean doubleClick;

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
    public void renderForeGround(int mouseX, int mouseY) {}

    @Override
    public void render(int mouseX, int mouseY) {}

    @Override
    public void renderBackRound(int mouseX, int mouseY) {}

    @Override
    public void onWidgetClicked(int x, int y, EnumMouseButton button, boolean shiftDown, EntityPlayer player, LPSide side) {}

    @Override
    public void onClicked(int mouseX, int mouseY, EnumMouseButton button, boolean shiftDown, EntityPlayer player, LPSide side) { }

    @Override
    public void onScrollWheel(int wheel, int mouseX, int mouseY, boolean shiftDown) {}

    @Override
    public void mouseClickMove(int mouseX, int mouseY, EnumMouseButton button, long timeSinceLastClick, EntityPlayer player) { }

    @Override
    public void onMouseDrag(int mouseX, int mouseY, EnumMouseButton button, long timeSinceLastClick, EntityPlayer player, Set<IWidget> draggedWidgets, LPSide side) { }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void onWidgetClosed(EntityPlayer player) {}

    @Override
    @Nonnull
    public Rectangle getBounds() {
        return rectangle;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) { }

    @Nullable
    public IWidget getParent() {
        return parent;
    }

    @Override
    public void setParent(@Nonnull IWidget parent) {
        this.parent = parent;
    }

    @Override
    public void addTooltip(int mouseX, int mouseY, List<String> tooltips, boolean shift, EntityPlayer player) {}
}
