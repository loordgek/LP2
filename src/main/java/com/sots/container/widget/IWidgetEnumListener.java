package com.sots.container.widget;

import net.minecraft.entity.player.EntityPlayer;

public interface IWidgetEnumListener {

    void onWidgetClicked(WidgetEnum button, EntityPlayer player);
}
