package com.sots.container.widget;

import com.sots.api.container.Widget;
import com.sots.api.crafting.IResource;

public class WidgetIResource extends Widget {
    private final IResource resource;
    public WidgetIResource(int x, int y, int width, int height, int widgetID, IResource resource) {
        super(x, y, width, height, widgetID);
        this.resource = resource;
    }

    @Override
    public boolean sendToServer() {
        return true;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        resource.render(getX(), getY(), mouseX, mouseY);
    }
}
