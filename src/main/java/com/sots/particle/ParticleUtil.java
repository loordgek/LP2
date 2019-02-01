package com.sots.particle;

import com.sots.LogisticsPipes2;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import java.util.Random;

public class ParticleUtil {
    public static Random rand = new Random();
    public static int counter = 0;

    public static void spawnGlint(World world, float x, float y, float z, float vx, float vy, float vz, float r, float g, float b, float scale, int lifetime) {
        counter += rand.nextInt(3);
        if (counter % (Minecraft.getInstance().gameSettings.particleSetting == 0 ? 1 : 2 * Minecraft.getInstance().gameSettings.particleSetting) == 0) {
            LogisticsPipes2.PROXY.getParticleRender().addParticle(new ParticleGlint(world, x, y, z, vx, vy, vz, r, g, b, scale, lifetime));
        }
    }
}
