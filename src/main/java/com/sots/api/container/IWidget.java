package com.sots.api.container;

import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import com.sots.api.util.Rectangle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public interface IWidget {

    boolean sendToServer();

    int getID();

    void renderForeGround(int mouseX, int mouseY, float partialTicks);

    void render(int mouseX, int mouseY, float partialTicks);

    void renderBackRound(int mouseX, int mouseY, float partialTicks);

    boolean onWidgetClicked(double mouseX, double mouseY, EnumMouseButton button, boolean shiftDown);

    boolean onClicked(double mouseX, double mouseY, EnumMouseButton button, boolean shiftDown);

    boolean onScrollWheel(double wheel, double mouseX, double mouseY, boolean shiftDown);

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

    boolean keyTyped(char typedChar, int keyCode);

    @Nullable
    IWidget getParent();

    void setParent(@Nonnull IWidget parent);

    void addTooltip(int mouseX, int mouseY, List<ITextComponent> tooltips, boolean shift, EntityPlayer player);
}
