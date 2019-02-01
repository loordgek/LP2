package com.sots.util.registries;

import com.sots.api.INetwork;
import com.sots.api.LPRoutedObject;
import com.sots.api.module.IModule;
import com.sots.api.module.Module;
import com.sots.tiles.TileRoutedPipe;
import net.minecraft.nbt.INBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityInit {
    public static void init(){
        CapabilityManager.INSTANCE.register(IModule.class, new net.minecraftforge.common.capabilities.Capability.IStorage<IModule>() {
            @Nullable
            @Override
            public INBTBase writeNBT(net.minecraftforge.common.capabilities.Capability<IModule> capability, IModule instance, EnumFacing side) {
                return null;
            }

            @Override
            public void readNBT(Capability<IModule> capability, IModule instance, EnumFacing side, INBTBase nbt) {

            }
        }, FakeModule::new);
    }

    private static class FakeModule extends Module {

        public FakeModule(){

        }

        @Override
        public boolean execute(World world, BlockPos pos) {
            return false;
        }

        @Override
        public Class<?> type() {
            return null;
        }

        @Override
        public ModuleType modType() {
            return null;
        }

        @Override
        public boolean handleLPRoutedObject(World world, BlockPos pos, LPRoutedObject routedObject) {
            return false;
        }

        @Override
        public void onConnect(INetwork network, World world, BlockPos pos) {

        }

        @Override
        public void onDisconnect(INetwork network, World world, BlockPos pos) {

        }

        @Override
        public void onRemoved(World world, BlockPos pos) {

        }

        @Override
        public void onAdd(World world, BlockPos pos) {

        }
    }
}
