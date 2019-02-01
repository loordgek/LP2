package com.sots.routing.interfaces;

import com.sots.api.LPRoutedObject;
import net.minecraft.world.IBlockReader;

public interface IPipe {
    void getAdjacentPipes(IBlockReader world);

    boolean hasAdjacent();

    boolean catchObject(LPRoutedObject item);

}
