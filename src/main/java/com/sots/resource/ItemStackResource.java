package com.sots.resource;

import com.sots.api.INetwork;
import com.sots.api.crafting.IResource;
import com.sots.api.request.RequestNode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.UUID;

public class ItemStackResource implements IResource<ItemStack> {
    private final ItemStack stack;

    public ItemStackResource(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public ItemStack get() {
        return stack;
    }

    @Override
    public void render(int x, int y, int mouseX, int mouseY) {

    }

    @Override
    public void addTooltip(int mouseX, int mouseY, List<String> tooltips, boolean shift, EntityPlayer player) {

    }

    @Override
    public void onResourceClicked(EntityPlayer player) {

    }

    @Override
    public boolean sentToCrafter(INetwork network, UUID pipeID, UUID augmentID, RequestNode node, int amount) {
        return false;
    }
}
