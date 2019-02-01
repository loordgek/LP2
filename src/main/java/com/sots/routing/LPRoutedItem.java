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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Deque;
import java.util.UUID;

public class LPRoutedItem extends LPRoutedObject<ItemStack> {

    public LPRoutedItem(ItemStack content, EnumFacing initVector, TileGenericPipe holder, Deque<EnumFacing> routingInfo, TileGenericPipe destination, UUID moduleID) {
        super(content, initVector, holder, routingInfo, destination, moduleID);
    }

    public LPRoutedItem(NBTTagCompound compound) {
        super(compound);
    }

    @Override
    protected ItemStack copyContent(ItemStack content) {
        return content.copy();
    }

    @Override
    public Class<ItemStack> getContentType() {
        return ItemStack.class;
    }

    @Override
    public void writeContentToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", getContent().serializeNBT());
    }

    @Override
    public ItemStack readContentFromNBT(NBTTagCompound compound) {
        return ItemStack.read(compound.getCompound("inventory"));
    }

    @Override
    public void render(TileGenericPipe te, float partialTicks) {
        if (!getContent().isEmpty()) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.pushMatrix();
            Minecraft.getInstance().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(getContent(), te.getWorld(), null);
            Triple<Double, Double, Double> newCoords = calculateTranslation(partialTicks);
            GlStateManager.translated(newCoords.getFirst(), newCoords.getSecnd(), newCoords.getThird());
            if (getContent().getItem() instanceof ItemBlock) {
                GlStateManager.scalef(.3f, .3f, .3f);
            } else {
                GlStateManager.rotatef((((float) te.getWorld().getGameTime() + partialTicks) / 40F) * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
                GlStateManager.scalef(.5f, .5f, .5f);
            }
            itemRenderer.renderItem(getContent(), ibakedmodel);
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    @Override
    public void spawnInWorld(World world, double x, double y, double z) {
        world.spawnEntity(new EntityItem(world, x, y, z, getContent()));
    }

    @Override
    public void putInBlock(TileEntity te) {
        ItemStack remainder = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, getHeading().getOpposite())
                .map(iItemHandler -> ItemHandlerHelper.insertItem(iItemHandler, getContent(), false)).orElse(getContent());
        if (!remainder.isEmpty())
            spawnInWorld();
    }
}
