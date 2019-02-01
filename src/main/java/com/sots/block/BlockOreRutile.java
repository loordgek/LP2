package com.sots.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockOreRutile extends Block {
    public BlockOreRutile() {
        super(Block.Builder.create(Material.ROCK).hardnessAndResistance(3, 15).sound(SoundType.STONE));

    }

/*    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();

        for (int i = 0; i < 3 + fortune; i++) {
            ret.add(new ItemStack(ItemRegistry.shard_rutile, 1, 0));
        }
        return ret;
    }*/

    @Override
    public void getDrops(IBlockState state, NonNullList<ItemStack> drops, World world, BlockPos pos, int fortune) {
        //drops.add(new ItemStack())
    }

}
