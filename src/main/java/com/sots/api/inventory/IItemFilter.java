package com.sots.api.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.function.Predicate;

@FunctionalInterface
public interface IItemFilter extends Predicate<ItemStack> {
    NonNullList<ItemStack> EMPTY_LIST = NonNullList.create();

    default NonNullList<ItemStack> stacks(){
        return EMPTY_LIST;
    }
}
