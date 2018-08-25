package com.sots.proxies;

import com.sots.particle.ParticleRenderer;
import net.minecraft.entity.player.EntityPlayer;

public class Proxy {

    public void preInit() { }

    public void init() { }

    public void postInit() {}

    public ParticleRenderer getParticleRender() {
        return null;
    }

    public EntityPlayer clientPlayer(){
        return null;
    }
}
