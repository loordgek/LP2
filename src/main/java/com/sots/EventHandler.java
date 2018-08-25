package com.sots;

import com.sots.util.References;
import com.sots.util.registries.PipeRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = References.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void onBlockRegistryRegister(RegistryEvent.Register<Block> event) {
        PipeRegistry.init();
        PipeRegistry.registry.forEach(pipe -> event.getRegistry().register(pipe));
    }

    @SubscribeEvent
    public static void onItemRegistryRegister(RegistryEvent.Register<Item> event) {
        PipeRegistry.registry.forEach(pipe -> event.getRegistry().register(new ItemBlock(pipe)));
    }
}
