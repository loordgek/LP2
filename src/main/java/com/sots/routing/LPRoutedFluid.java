package com.sots.routing;

import com.sots.api.LPRoutedObject;
import com.sots.api.util.data.Triple;
import com.sots.tiles.TileGenericPipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

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
        return (FluidStack.loadFluidStackFromNBT(compound.getCompoundTag("inventory")));
    }

    @Override
    public void render(TileGenericPipe te, float partialTicks) {
        ItemStack stack = UniversalBucket.getFilledBucket(new UniversalBucket(), getContent().getFluid());
        if (!stack.isEmpty()) {
            RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.pushMatrix();
            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, te.getWorld(), null);
            Triple<Double, Double, Double> newCoords = calculateTranslation(partialTicks);
            GlStateManager.translate(newCoords.getFirst(), newCoords.getSecnd(), newCoords.getThird());
            GlStateManager.rotate((((float) te.getWorld().getTotalWorldTime() + partialTicks) / 40F) * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
            GlStateManager.scale(.5f, .5f, .5f);
            itemRenderer.renderItem(stack, ibakedmodel);
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    @Override
    public void spawnInWorld(World world, double x, double y, double z) {
        world.spawnEntity(new EntityItem(world, x, y, z, UniversalBucket.getFilledBucket(new UniversalBucket(), getContent().getFluid())));
    }

    @Override
    public void putInBlock(TileEntity te) {
        if (te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, getHeading().getOpposite())) {
            IFluidHandler fluidHandler = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, getHeading().getOpposite());
            FluidStack fluidStack = getContent();
            //for (int j = 0; j < fluidHandler.getSlots(); j++) {
            //fluidStack = fluidHandler.insertFluid(j, fluidStack, false);
            //}
            int amountLeft = fluidHandler.fill(fluidStack, true);
            //if(!fluidStack.isEmpty())
            if (amountLeft < fluidStack.amount)
                if (!te.getWorld().isRemote) {
                    spawnInWorld(te.getWorld(), te.getPos().getX() + 0.5, te.getPos().getY() + 1.5, te.getPos().getZ() + 0.5);
                    //world.spawnEntity(new EntityItem(world, pos.getX()+0.5, pos.getY()+1.5, pos.getZ()+0.5, UniversalBucket.getFilledBucket(new UniversalBucket(), fluidStack.getFluid())));
                }
        } else {
            if (!te.getWorld().isRemote) {
                spawnInWorld(te.getWorld(), te.getPos().getX() + 0.5, te.getPos().getY() + 1.5, te.getPos().getZ() + 0.5);
            }
        }
    }

}
