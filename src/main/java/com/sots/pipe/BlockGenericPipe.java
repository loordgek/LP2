package com.sots.pipe;

import com.google.common.collect.UnmodifiableIterator;
import com.sots.block.BlockTileBase;
import com.sots.tiles.TileGenericPipe;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.model.IModelPart;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.Models;
import net.minecraftforge.common.model.TRSRTransformation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlockGenericPipe extends BlockTileBase {

    // TODO: 2/1/2019 you can not have fields ina block
    protected final List<String> hidden = new ArrayList<>();
    public final IModelState state = new IModelState() {
        private final Optional<TRSRTransformation> value = Optional.of(TRSRTransformation.identity());

        @Override
        public Optional<TRSRTransformation> apply(Optional<? extends IModelPart> part) {
            if (part.isPresent()) {
                // This whole thing is subject to change, but should do for now.
                UnmodifiableIterator<String> parts = Models.getParts(part.get());
                if (parts.hasNext()) {
                    String name = parts.next();
                    // only interested in the root level
                    if (!parts.hasNext() && hidden.contains(name)) {
                        return value;
                    }
                }
            }
            return Optional.empty();
        }


    };

    public BlockGenericPipe(Material materialIn) {
        super(Builder.create(materialIn).hardnessAndResistance(3f, 10f).sound(SoundType.METAL));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    // TODO: 2/1/2019
    /*@Override
    public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        //return super.getCollisionShape(state, worldIn, pos);
        double x1 = 0.275;
        double y1 = 0.275;
        double z1 = 0.275;
        double x2 = 0.725;
        double y2 = 0.725;
        double z2 = 0.725;

        if (worldIn.getTileEntity(pos) instanceof TileGenericPipe) {
            TileGenericPipe pipe = (TileGenericPipe) worldIn.getTileEntity(pos);
            if (pipe.down != ConnectionTypes.NONE) {
                y1 = 0;
            }
            if (pipe.up != ConnectionTypes.NONE) {
                y2 = 1;
            }
            if (pipe.north != ConnectionTypes.NONE) {
                z1 = 0;
            }
            if (pipe.south != ConnectionTypes.NONE) {
                z2 = 1;
            }
            if (pipe.west != ConnectionTypes.NONE) {
                x1 = 0;
            }
            if (pipe.east != ConnectionTypes.NONE) {
                x2 = 1;
            }
        }
        return new VoxelShape()
    }*/

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        ((TileGenericPipe) worldIn.getTileEntity(pos)).neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
        return null;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return false;
    }
}
