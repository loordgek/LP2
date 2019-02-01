package com.sots.api.container;

import com.sots.api.util.LPSide;

public interface IWidgetContainer extends IWidget {

    void init(LPSide side);

    void addWidget(IWidget widget);

    IWidget getWidget(int index);
}
