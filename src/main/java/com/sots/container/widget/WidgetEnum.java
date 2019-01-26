package com.sots.container.widget;

import com.sots.api.container.Widget;

public class WidgetEnum<E extends Enum<E>> extends Widget {
    private E Enum;
    private final IWidgetEnumListener listener;
    public WidgetEnum(int x, int y, int width, int height, int widgetID, IWidgetEnumListener listener) {
        super(x, y, width, height, widgetID);
        this.listener = listener;
    }

    @Override
    public void onScrollWheel(int wheel, int mouseX, int mouseY, boolean shiftDown) {
        super.onScrollWheel(wheel, mouseX, mouseY, shiftDown);
    }
}
