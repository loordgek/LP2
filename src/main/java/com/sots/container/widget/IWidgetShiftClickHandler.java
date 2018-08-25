package com.sots.container.widget;

import net.minecraft.entity.player.EntityPlayer;

public interface IWidgetShiftClickHandler {

    boolean handleShiftClick(WidgetSlot slot, EntityPlayer player);
}
