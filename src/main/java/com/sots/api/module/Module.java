package com.sots.api.module;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

public abstract class Module implements IModule {
    private UUID uuid;

    @Override
    public boolean canExecute() {
        return false;
    }

    @Override
    public boolean canInsert() {
        return false;
    }

    @Override
    public final NBTTagCompound serializeNBT() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setUniqueId("UUID", uuid);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        uuid = compound.getUniqueId("UUID");
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {

    }
}
