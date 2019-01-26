package com.sots.container;


import com.sots.api.container.IWidget;
import com.sots.api.util.LPSide;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class LPContainer extends Container {
    public final List<IWidget> widgets = new ArrayList<>();
    public final Set<IWidget> draggedWidgets = new HashSet<>();

    public LPContainer(LPSide side) {
        if (side.isServer())
            initWidgets(side);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    public void initWidgets(LPSide side){

    }

    public void keyTyped(char typedChar, int keyCode, boolean keyPressed, EntityPlayer player, LPSide side){
        widgets.forEach(widget -> widget.keyTyped(typedChar, keyCode, keyPressed, player));
    }

    public void addWidget(IWidget widget){
        widgets.add(widget);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        widgets.forEach(widget -> widget.onWidgetClosed(playerIn));
        super.onContainerClosed(playerIn);
    }
}
