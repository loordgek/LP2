package com.sots;

import com.sots.pipe.BlockGenericPipe;
import com.sots.util.References;
import com.sots.util.registries.PipeRegistry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;


@Mod.EventBusSubscriber(modid = References.MODID, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent event) {
        ResourceLocation particleGlint = new ResourceLocation("lptwo:entity/particle_glint");
        event.getMap().registerSprite(particleGlint);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            LogisticsPipes2.PROXY.getParticleRender().updateParticles();
        }
    }

    @SubscribeEvent
    public static void onRenderAfterWorld(RenderWorldLastEvent event) {
        GlStateManager.pushMatrix();
        LogisticsPipes2.PROXY.getParticleRender().renderParticles(event.getPartialTicks());
        GlStateManager.popMatrix();
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
     //   PipeRegistry.registry.forEach(BlockGenericPipe::initModel);
    }
}
