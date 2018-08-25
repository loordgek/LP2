package com.sots.api.container;

import com.sots.api.KeyInfo;
import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import com.sots.api.util.Rectangle;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public interface IWidget {

    boolean sendToServer();

    int getID();

    void renderForeGround(int mouseX, int mouseY);

    void render(int mouseX, int mouseY);

    void renderBackRound(int mouseX, int mouseY);


    void onWidgetClicked(int mouseX, int mouseY, EnumMouseButton button, boolean shiftDown, EntityPlayer player, LPSide side);

    void onClicked(int mouseX, int mouseY, EnumMouseButton button, boolean shiftDown, EntityPlayer player, LPSide side);

    void onScrollWheel(int wheel, int mouseX, int mouseY, boolean shiftDown);

    void mouseClickMove(int mouseX, int mouseY, EnumMouseButton button, long timeSinceLastClick, EntityPlayer player);

    void onMouseDrag(int mouseX, int mouseY, EnumMouseButton button, long timeSinceLastClick, EntityPlayer player, Set<IWidget> draggedWidgets, LPSide side);

    void mouseReleased(int mouseX, int mouseY, int state);

    void onWidgetClosed(EntityPlayer player);

    Rectangle getBounds();

    default int getX() {
        return getBounds().getX();
    }

    default int getY() {
        return getBounds().getY();
    }

    default int getWidth() {
        return getBounds().getWidth();
    }

    default int getHeight() {
        return getBounds().getHeight();
    }

    void keyTyped(char typedChar, int keyCode);

    @Nullable
    IWidget getParent();

    void setParent(@Nonnull IWidget parent);

    void addTooltip(int mouseX, int mouseY, List<String> tooltips, boolean shift, EntityPlayer player);
}
