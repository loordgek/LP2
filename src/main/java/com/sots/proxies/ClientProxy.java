package com.sots.proxies;

import com.sots.particle.ParticleRenderer;
import com.sots.util.References;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.model.obj.OBJLoader;

public class ClientProxy extends Proxy {

    public ParticleRenderer particleRender = new ParticleRenderer();

    @Override
    public void preInit() {
        OBJLoader.INSTANCE.addDomain(References.MODID);
    }

    public ParticleRenderer getParticleRender() {
        return particleRender;
    }

    @Override
    public EntityPlayer clientPlayer() {
        return Minecraft.getMinecraft().player;
    }
}
