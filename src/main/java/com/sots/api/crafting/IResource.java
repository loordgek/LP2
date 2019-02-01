package com.sots.api.crafting;

import com.sots.api.INetwork;
import com.sots.api.request.RequestNode;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;
import java.util.UUID;

public interface IResource<T> {

    T get();

    void render(int x, int y, int mouseX, int mouseY);

    void addTooltip(int mouseX, int mouseY, List<String> tooltips, boolean shift, EntityPlayer player);

    void onResourceClicked(EntityPlayer player);

    boolean sentToCrafter(INetwork network, UUID pipeID, UUID augmentID, RequestNode node, int amount);
}
