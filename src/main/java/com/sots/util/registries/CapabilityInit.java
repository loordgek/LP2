package com.sots.util.registries;

import com.sots.api.module.IModule;
import com.sots.api.INetwork;
import com.sots.api.LPRoutedObject;
import com.sots.api.module.Module;
import com.sots.tiles.TileRoutedPipe;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;
import java.util.UUID;

public class CapabilityInit {
    public static void init(){
        CapabilityManager.INSTANCE.register(IModule.class, new net.minecraftforge.common.capabilities.Capability.IStorage<IModule>() {
            @Nullable
            @Override
            public NBTBase writeNBT(net.minecraftforge.common.capabilities.Capability<IModule> capability, IModule instance, EnumFacing side) {
                return null;
            }

            @Override
            public void readNBT(net.minecraftforge.common.capabilities.Capability<IModule> capability, IModule instance, EnumFacing side, NBTBase nbt) {

            }
        }, FakeModule::new);
    }

    private static class FakeModule extends Module {

        public FakeModule() throws IllegalAccessException {
            throw new IllegalAccessException("dont use the default IModule cap");
        }

        @Override
        public boolean handleLPRoutedObject(TileRoutedPipe te, LPRoutedObject routedObject) {
            return false;
        }

        @Override
        public void onConnect(INetwork network, TileRoutedPipe te) {

        }

        @Override
        public void onDisconnect(INetwork network, TileRoutedPipe te) {

        }

        @Override
        public void onRemoved(TileRoutedPipe te) {

        }

        @Override
        public void onAdd(TileRoutedPipe te) {

        }
    }
}
