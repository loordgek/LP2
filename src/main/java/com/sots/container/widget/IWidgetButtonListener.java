package com.sots.container.widget;

import net.minecraft.entity.player.EntityPlayer;

public interface IWidgetButtonListener {

    void onWidgetClicked(WidgetButton button, EntityPlayer player);
}
