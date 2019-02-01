package com.sots;

import com.sots.pipe.PipeBasic;
import com.sots.proxies.ClientProxy;
import com.sots.proxies.Proxy;
import com.sots.proxies.ServerProxy;
import com.sots.tiles.TileBasicPipe;
import com.sots.tiles.TileNetworkCore;
import com.sots.tiles.TileRoutedPipe;
import com.sots.util.References;
import com.sots.util.TileRegistryUtil;
import com.sots.util.registries.CapabilityInit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(References.MODID)
public class LogisticsPipes2 {

    public static LogisticsPipes2 INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger();

    public static Proxy PROXY;

    public LogisticsPipes2(){
        PROXY = DistExecutor.runForDist(()->()->new ClientProxy(), ()->()->new ServerProxy());
        FMLModLoadingContext.get().getModEventBus().addListener(this::preInit);
        FMLModLoadingContext.get().getModEventBus().addListener(this::onBlockRegistryRegister);
        FMLModLoadingContext.get().getModEventBus().addListener(this::onItemRegistryRegister);
        FMLModLoadingContext.get().getModEventBus().addListener(this::onTileEntityRegistryRegister);
        INSTANCE = this;
    }

    public void preInit(FMLCommonSetupEvent event) {
        PROXY.setup();
        CapabilityInit.init();
    }

    public void onBlockRegistryRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new PipeBasic());
    }

    public void onItemRegistryRegister(RegistryEvent.Register<Item> event) {
    }

    public void onTileEntityRegistryRegister(RegistryEvent.Register<TileEntityType<? extends TileEntity>> event) {
        TileRegistryUtil.registerTileType(event, new ResourceLocation(References.MODID, "Tilegenericpipe"), TileRoutedPipe::new);
        TileRegistryUtil.registerTileType(event, new ResourceLocation(References.MODID, "Tilenetworkcore"), TileNetworkCore::new);
        TileRegistryUtil.registerTileType(event, new ResourceLocation(References.MODID, "Tilebasicpipee"), TileBasicPipe::new);
    }
}
