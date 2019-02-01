package com.sots.block;

import com.sots.tiles.ITileEntityBase;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class BlockTileBase extends Block {

    public BlockTileBase(Builder properties) {
        super(properties);
    }

    @Nullable
    @Override
    public abstract TileEntity createTileEntity(IBlockState state, IBlockReader world);

    @Override
    public abstract boolean hasTileEntity(IBlockState state);

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        return ((ITileEntityBase) worldIn.getTileEntity(pos)).activate(worldIn, pos, state, player, hand, side, hitX, hitY, hitZ);
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        super.onBlockHarvested(world, pos, state, player);
        ((ITileEntityBase) world.getTileEntity(pos)).breakBlock(world, pos, state, player);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }
}
