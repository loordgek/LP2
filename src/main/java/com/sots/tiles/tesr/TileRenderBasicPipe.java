package com.sots.tiles.tesr;

import com.sots.api.LPRoutedObject;
import com.sots.tiles.TileGenericPipe;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;

import java.util.Set;

public class TileRenderBasicPipe extends TileEntityRenderer<TileGenericPipe> {

    @Override
    public void render(TileGenericPipe te, double x, double y, double z, float partialTicks,
                                   int destroyStage) {
        if (!te.getWorld().isBlockLoaded(te.getPos(), false))
            return;
        Set<LPRoutedObject> displays = te.getContents();
        GlStateManager.pushMatrix();
        GlStateManager.translated(x + .5, y + .5, z + .5);
        if (!displays.isEmpty()) {
            for (LPRoutedObject item : displays) {
                item.render(te, partialTicks);
            }
        }
        GlStateManager.popMatrix();
    }
}
