package com.sots.container.widget;

import com.sots.api.container.IWidget;
import com.sots.api.container.Widget;
import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.LPSide;
import com.sots.util.InvUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class WidgetSlot extends Widget {
    private final int index;
    private final IItemHandler handler;
    private final SlotManager slotManager;
    private ItemStack draggedStack;
    private int mouseX;

    public WidgetSlot(int x, int y, int width, int height, int widgetID, int index, @Nonnull IItemHandler handler, @Nullable SlotManager slotManager) {
        super(x, y, width, height, widgetID);
        this.index = index;
        this.handler = handler;
        this.slotManager = slotManager;
    }

    public WidgetSlot(int x, int y, int width, int height, int widgetID, int index, @Nonnull IItemHandler handler) {
        this(x, y, width, height, widgetID, index, handler, null);
    }

    public int getIndex() {
        return index;
    }

    @Nonnull
    public ItemStack getStack(){
        return handler.getStackInSlot(getIndex());
    }

    @Nonnull
    public IItemHandler getHandler() {
        return handler;
    }

    @Nullable
    public SlotManager getSlotManager() {
        return slotManager;
    }

    public boolean isPhantomSlot(){
        return false;
    }

    @Override
    public void onWidgetClicked(int mouseX, int mouseY, EnumMouseButton button, boolean shiftDown, EntityPlayer player, LPSide side) {
        if ((!getStack().isEmpty()) && shiftDown && getSlotManager() != null){
            getSlotManager().handleShiftClick(this, player);
            return;
        }
        InventoryPlayer inventoryPlayer = player.inventory;
        ItemStack mouseStack = inventoryPlayer.getItemStack();
        if (!getStack().isEmpty() && !mouseStack.isEmpty() && !ItemHandlerHelper.canItemStacksStack(getStack(), mouseStack)){
            if (getStack().getCount() <= getStack().getMaxStackSize()){
                ItemStack extract = extract(getStack().getCount());
                insert(mouseStack);
                if (!isPhantomSlot())
                    inventoryPlayer.setItemStack(extract);
            }
            return;
        }
        if (!mouseStack.isEmpty()) { // insert into the slot
            int amountInserted = 0;
            if (button.isLeftClick())
                 amountInserted = InvUtil.amountInserted(getHandler(), mouseStack, getIndex());
            else if (button.isRightClick())
                amountInserted = InvUtil.amountInserted(getHandler(), mouseStack.splitStack(mouseStack.getCount() / 2), getIndex());

            if (!isPhantomSlot()){
                mouseStack.shrink(amountInserted);
                if (mouseStack.isEmpty())
                    inventoryPlayer.setItemStack(ItemStack.EMPTY);
            }
            return;
        }
        if (!getStack().isEmpty()){
            int amountToExtract = 0;
            if (button.isLeftClick())
                amountToExtract = Math.min(getStack().getCount(), getStack().getMaxStackSize());
            else if (button.isRightClick())
                amountToExtract = Math.min(getStack().getCount() / 2, getStack().getMaxStackSize());
            ItemStack extract = extract(amountToExtract);
            if (!isPhantomSlot() && !extract.isEmpty())
                inventoryPlayer.setItemStack(extract);
            return;
        }
        if (button.isMiddleClick() && !getStack().isEmpty()){
            if (player.isCreative()){
                inventoryPlayer.setItemStack(ItemHandlerHelper.copyStackWithSize(getStack(), getStack().getMaxStackSize()));
            }
        }
    }

    @Override
    public void onMouseDrag(int mouseX, int mouseY, EnumMouseButton button, long timeSinceLastClick, EntityPlayer player, Set<IWidget> draggedWidgets, LPSide side) {
        if (getSlotManager() != null){
            getSlotManager().addDraggedSlot(this);
            getSlotManager().setDraggedButton(button);
        }
    }

    @Override
    public void render(int mouseX, int mouseY) {
        super.render(mouseX, mouseY);
    }

    @Nonnull
    public ItemStack insert(@Nonnull ItemStack stack){
        return handler.insertItem(getIndex(), stack, false);
    }

    @Nonnull
    public ItemStack extract(int amount){
       return handler.extractItem(getIndex(), amount, false);
    }

    @Override
    public void onScrollWheel(int wheel, int mouseX, int mouseY, boolean shiftDown) {
        super.onScrollWheel(wheel, mouseX, mouseY, shiftDown);
    }

    @Override
    public void addTooltip(int mouseX, int mouseY, List<String> tooltips, boolean shift, EntityPlayer player) {
        if (draggedStack.isEmpty()){

        }
    }

    public ItemStack getDraggedStack() {
        return draggedStack;
    }

    public void setDraggedStack(ItemStack draggedStack) {
        this.draggedStack = draggedStack;
    }

    @Override
    public boolean sendToServer() {
        return true;
    }
}
