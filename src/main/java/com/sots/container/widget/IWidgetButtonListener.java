package com.sots.container.widget;

import com.sots.api.util.EnumMouseButton;
import net.minecraft.entity.player.EntityPlayer;

public interface IWidgetButtonListener {

    void onWidgetClicked(WidgetButton widgetButton, EnumMouseButton button, EntityPlayer player);
}
