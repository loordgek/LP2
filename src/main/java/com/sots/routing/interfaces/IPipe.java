package com.sots.routing.interfaces;

import com.sots.api.LPRoutedObject;
import net.minecraft.world.IBlockAccess;

public interface IPipe {
    void getAdjacentPipes(IBlockAccess world);

    boolean hasAdjacent();

    boolean catchItem(LPRoutedObject item);
}
