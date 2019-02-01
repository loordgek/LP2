package com.sots.util;

import com.sots.util.holder.BlockHolder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class LpGruop {
    public static final ItemGroup group = new ItemGroup("label") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockHolder.BASIC_PIPE);
        }
    };
}
