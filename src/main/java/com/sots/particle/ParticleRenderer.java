package com.sots.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class ParticleRenderer {
    ArrayList<Particle> particles = new ArrayList<>();

    public void updateParticles() {
        boolean doRemove;
        for (int i = 0; i < particles.size(); i++) {
            doRemove = true;
            if (particles.get(i) != null) {
                if (particles.get(i) instanceof ILP2Particle) {
                    if (((ILP2Particle) particles.get(i)).alive()) {
                        particles.get(i).tick();
                        doRemove = false;
                    }
                }
            }
            if (doRemove) {
                particles.remove(i);
            }
        }
    }

    public void renderParticles(float pTicks) {
        float f = ActiveRenderInfo.getRotationX();
        float f1 = ActiveRenderInfo.getRotationZ();
        float f2 = ActiveRenderInfo.getRotationYZ();
        float f3 = ActiveRenderInfo.getRotationXY();
        float f4 = ActiveRenderInfo.getRotationXZ();
        EntityPlayer player = Minecraft.getInstance().player;

        Particle.interpPosX = player.lastTickPosX + (player.posX - player.lastTickPosX) * pTicks;
        Particle.interpPosY = player.lastTickPosY + (player.posY - player.lastTickPosY) * pTicks;
        Particle.interpPosZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * pTicks;
        Particle.cameraViewDir = player.getLook(pTicks);
        GlStateManager.enableAlphaTest();
        GlStateManager.enableBlend();
        GlStateManager.alphaFunc(516, 0.003921569F);
        GlStateManager.disableCull();

        GlStateManager.depthMask(false);

        Minecraft.getInstance().textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.getBuffer();

        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        for (Particle particle : particles) {
            if (!((ILP2Particle) particle).isAdditive()) {
                particle.renderParticle(buffer, player, pTicks, f, f4, f1, f2, f3);
            }
        }
        tess.draw();

        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        for (Particle particle : particles) {
            if (((ILP2Particle) particle).isAdditive()) {
                particle.renderParticle(buffer, player, pTicks, f, f4, f1, f2, f3);
            }
        }
        tess.draw();

        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableBlend();
        GlStateManager.alphaFunc(516, 0.1F);

    }

    public void addParticle(Particle part) {
        particles.add(part);
    }
}
