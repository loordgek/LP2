package com.sots.util.holder;

import com.sots.util.References;
import net.minecraft.block.Block;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(References.MODID)
public class BlockHolder {

    @ObjectHolder("pipe_basic")
    public static Block BASIC_PIPE;
}
