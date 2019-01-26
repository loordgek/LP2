package com.sots.container.widget;

import com.sots.api.util.EnumMouseButton;
import net.minecraft.entity.player.EntityPlayer;

public interface IWidgetEnumListener {

    void onWidgetClicked(WidgetEnum widgetEnum, EnumMouseButton button, EntityPlayer player);
}
