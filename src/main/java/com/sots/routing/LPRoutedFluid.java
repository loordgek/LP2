package com.sots.routing;

import com.sots.api.LPRoutedObject;
import com.sots.api.util.data.Triple;
import com.sots.tiles.TileGenericPipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import java.util.Deque;
import java.util.UUID;

public class LPRoutedFluid extends LPRoutedObject<FluidStack> {

    public LPRoutedFluid(FluidStack content, EnumFacing initVector, TileGenericPipe holder, Deque<EnumFacing> routingInfo, TileGenericPipe destination, UUID moduleUUid) {
        super(content, initVector, holder, routingInfo, destination, moduleUUid);
    }

    public LPRoutedFluid(NBTTagCompound compound) {
        super(compound);
    }

    @Override
    protected FluidStack copyContent(FluidStack content) {
        return content.copy();
    }

    @Override
    public Class<FluidStack> getContentType() {
        return FluidStack.class;
    }

    @Override
    public void writeContentToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", getContent().writeToNBT(new NBTTagCompound()));
    }

    @Override
    public FluidStack readContentFromNBT(NBTTagCompound compound) {
        return (FluidStack.loadFluidStackFromNBT(compound.getCompound("inventory")));
    }

    // TODO: 2/1/2019
    @Override
    public void render(TileGenericPipe te, float partialTicks) {
        ItemStack stack = ItemStack.EMPTY;//UniversalBucket.getFilledBucket(new UniversalBucket(), getContent().getFluid());
        if (!stack.isEmpty()) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.pushMatrix();
            Minecraft.getInstance().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, te.getWorld(), null);
            Triple<Double, Double, Double> newCoords = calculateTranslation(partialTicks);
            GlStateManager.translated(newCoords.getFirst(), newCoords.getSecnd(), newCoords.getThird());
            GlStateManager.rotatef((((float) te.getWorld().getGameTime() + partialTicks) / 40F) * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
            GlStateManager.scalef(.5f, .5f, .5f);
            itemRenderer.renderItem(stack, ibakedmodel);
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    @Override
    public void putInBlock(TileEntity te) {
        te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, getHeading().getOpposite())
                .ifPresent(iFluidHandler -> iFluidHandler.fill(getContent(), true));
    }
}
