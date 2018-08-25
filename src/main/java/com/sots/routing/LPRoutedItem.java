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
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

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
        return (new ItemStack(compound.getCompoundTag("inventory")));
    }

    @Override
    public void render(TileGenericPipe te, float partialTicks) {
        if (!getContent().isEmpty()) {
            RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.pushMatrix();
            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(getContent(), te.getWorld(), null);
            Triple<Double, Double, Double> newCoords = calculateTranslation(partialTicks);
            GlStateManager.translate(newCoords.getFirst(), newCoords.getSecnd(), newCoords.getThird());
            if (getContent().getItem() instanceof ItemBlock) {
                GlStateManager.scale(.3f, .3f, .3f);
            } else {
                GlStateManager.rotate((((float) te.getWorld().getTotalWorldTime() + partialTicks) / 40F) * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
                GlStateManager.scale(.5f, .5f, .5f);
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
        if (te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, getHeading().getOpposite())) {
            IItemHandler itemHandler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, getHeading().getOpposite());
            ItemStack itemStack = getContent();
            for (int j = 0; j < itemHandler.getSlots(); j++) {
                itemStack = itemHandler.insertItem(j, itemStack, false);
            }
            if (!itemStack.isEmpty())
                if (!te.getWorld().isRemote) {
                    spawnInWorld(te.getWorld(), te.getPos().getX() + 0.5, te.getPos().getY() + 1.5, te.getPos().getZ() + 0.5);
                    //world.spawnEntity(new EntityItem(world, pos.getX()+0.5, pos.getY()+1.5, pos.getZ()+0.5, itemStack));
                }
        } else {
            if (!te.getWorld().isRemote) {
                spawnInWorld(te.getWorld(), te.getPos().getX() + 0.5, te.getPos().getY() + 1.5, te.getPos().getZ() + 0.5);
                //world.spawnEntity(new EntityItem(world, pos.getX()+0.5, pos.getY()+1.5, pos.getZ()+0.5, getContent()));
            }
        }
    }
}
