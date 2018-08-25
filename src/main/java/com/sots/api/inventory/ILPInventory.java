package com.sots.api.inventory;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Iterator;

public interface ILPInventory extends Iterable<ItemStack> {

    boolean isValid();

    ItemStack getStackInSLot(int slot);

    @Override
    default Iterator<ItemStack> iterator() {
        return new Iterator<ItemStack>() {
            int currentSlot = 0;
            @Override
            public boolean hasNext() {
                return currentSlot < size();
            }

            @Override
            public ItemStack next() {
                if (!hasNext())
                    throw new IndexOutOfBoundsException();
                return getStackInSLot(currentSlot++);
            }
        };
    }

    int size();

    int getPriority();

    @Nonnull
    ItemStack insert(@Nonnull ItemStack stack, boolean simulate);

    ItemStack insert(@Nonnull ItemStack stack, int slot, boolean simulate);

    ItemStack extact(@Nonnull IItemFilter filter, int amount, boolean simulate);

    ItemStack extact(@Nonnull IItemFilter filter, int slot, int amount, boolean simulate);

}
