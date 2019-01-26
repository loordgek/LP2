package com.sots.container.widget;

import com.sots.api.container.Widget;
import com.sots.api.crafting.IResource;
import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class WidgetIResource extends Widget {
    private final IResource resource;
    public WidgetIResource(int x, int y, int width, int height, IResource resource,int widgetID) {
        super(x, y, width, height, widgetID);
        this.resource = resource;
    }

    @Override
    public boolean sendToServer() {
        return true;
    }

    @Override
    public void render(int mouseX, int mouseY) {
        resource.render(getX(), getY(), mouseX, mouseY);
    }

    @Override
    public void addTooltip(int mouseX, int mouseY, List<String> tooltips, boolean shift, EntityPlayer player) {
        resource.addTooltip(mouseX, mouseY, tooltips, shift, player);
    }

    @Override
    public void onWidgetClicked(int x, int y, EnumMouseButton button, boolean shiftDown, EntityPlayer player, LPSide side) {
        resource.onResourceClicked(player);
    }
}
