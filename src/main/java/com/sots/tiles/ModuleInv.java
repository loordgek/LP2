package com.sots.tiles;

import com.sots.api.module.IModule;
import com.sots.api.INetwork;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ModuleInv {
    private final NonNullList<ItemStack> stacks;
    private final Map<UUID, IModule> moduleMap = new HashMap<>();

    public ModuleInv(int size) {
        this.stacks = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    public boolean isStackValid(ItemStack stack){
        return false;
    }

    public void putStack(){

    }

    public void spawnInvIntoworld(World world, BlockPos pos){

    }

    public void onConnect(INetwork network, TileRoutedPipe te) {
        moduleMap.forEach((uuid, iModule) -> iModule.onConnect(network, te.getWorld(), te.getPos()));
    }

    public void onDisconnect(INetwork network, TileRoutedPipe te) {
        moduleMap.forEach((uuid, iModule) -> iModule.onDisconnect(network, te.getWorld(), te.getPos()));
    }

    public void onRemoved(TileRoutedPipe te) {
        moduleMap.forEach((uuid, iModule) -> iModule.onRemoved(te.getWorld(), te.getPos()));
    }

    public void onAdd(TileRoutedPipe te) {
        moduleMap.forEach((uuid, iModule) -> iModule.onAdd(te.getWorld(), te.getPos()));
    }
}
