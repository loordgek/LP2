package com.sots.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import java.util.Random;

public class Misc {
    public static Random rand = new Random();
    public static Vec3i vec3i = new Vec3i(0.5, 1.5, 0.5);

    public static void spawnInventoryInWorld(World world, BlockPos pos, @Nonnull IItemHandler inventory) {
        if (!world.isRemote) {
            for (int i = 0; i < inventory.getSlots(); i++) {
                ItemStack stack = inventory.getStackInSlot(i);
                spawnItemStackInWorld(world, pos, stack);
            }
        }
    }

    public static void spawnItemStackInWorld(World world, BlockPos pos, @Nonnull ItemStack stack) {
        if (!world.isRemote) {
            if (!stack.isEmpty()) {
                world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack));
            }
        }
    }
}
