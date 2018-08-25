package com.sots.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class InvUtil {

    public static int amountInserted(IItemHandler handler, ItemStack toInsert, int slot){
        int originalAmount = toInsert.getCount();
        ItemStack inserted = handler.insertItem(slot, toInsert, false);
        return !inserted.isEmpty() ? inserted.getCount() - originalAmount : 0;
    }
}
