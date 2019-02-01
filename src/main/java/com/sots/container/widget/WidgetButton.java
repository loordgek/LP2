package com.sots.container.widget;


import com.sots.api.container.Widget;
import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import net.minecraft.entity.player.EntityPlayer;

public class WidgetButton extends Widget {
    private final IWidgetButtonListener listener;
    public WidgetButton(int x, int y, int width, int height, int widgetID, IWidgetButtonListener listener) {
        super(x, y, width, height, widgetID);
        this.listener = listener;
    }

    @Override
    public boolean onWidgetClicked(double mouseX, double mouseY, EnumMouseButton button, boolean shiftDown) {
        return listener.onWidgetClicked(this, button);
    }
}
