package com.sots;

import com.sots.network.LPPacketHandler;
import com.sots.proxies.Proxy;
import com.sots.util.References;
import com.sots.util.registries.CapabilityInit;
import com.sots.world.LPWorldGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = References.MODID, name = References.MODNAME, version = References.VERSION, dependencies = "", useMetadata = true)
public class LogisticsPipes2 {

    @SidedProxy(clientSide = "com.sots.proxies.ClientProxy", serverSide = "com.sots.proxies.ServerProxy")
    public static Proxy proxy;

    @Mod.Instance
    public static LogisticsPipes2 instance = new LogisticsPipes2();

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit();
        CapabilityInit.init();
        LPPacketHandler.registerMessages();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        LPWorldGen worldGen = new LPWorldGen();
        GameRegistry.registerWorldGenerator(worldGen, 0);
        MinecraftForge.EVENT_BUS.register(worldGen);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}
