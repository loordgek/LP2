package com.sots.api.module;

import com.sots.api.INetwork;
import com.sots.api.LPRoutedObject;
import com.sots.api.container.IWidgetContainer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.OptionalCapabilityInstance;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public interface IModule extends ICapabilitySerializable<NBTTagCompound> {

    /**
     * Executes the Modules Logic
     *
     * @param te The Pipe executing this Module
     * @return True if the Logic could be executed. If a module returns false, Items used in its operation will be spilled!
     */
    boolean execute(World world, BlockPos pos);

    /**
     * @return True if the Module can execute its Logic, False if not
     */
    boolean canExecute();

    Class<?> type();

    /**
     * @return The Modules execution Type
     */
    ModuleType modType();

    default TextureAtlasSprite getTabIcon(){
        return null;
    }

    default boolean hasGuiComponent(){
        return false;
    }

    default IWidgetContainer getGuiComponent(EntityPlayer player){
        return null;
    }

    @Nonnull
    @Override
    default <T> OptionalCapabilityInstance<T> getCapability(@Nonnull Capability<T> cap, @Nullable EnumFacing side) {
        return OptionalCapabilityInstance.empty();
    }

    @Override
    NBTTagCompound serializeNBT();

    NBTTagCompound writeToNBT(NBTTagCompound compound);

    @Override
    void deserializeNBT(NBTTagCompound compound);

    default boolean conflictWithOther(IModule other) {
        return false;
    }

    UUID getUUID();

    void setUUID(UUID uuid);

    boolean handleLPRoutedObject(World world, BlockPos pos, LPRoutedObject routedObject);

    void onConnect(INetwork network, World world, BlockPos pos);

    void onDisconnect(INetwork network, World world, BlockPos pos);

    void onRemoved(World world, BlockPos pos);

    void onAdd(World world, BlockPos pos);

    void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos);

    enum ModuleType {
        SINK, SORT, CRAFT, EXTRACT, NONE
    }
}
