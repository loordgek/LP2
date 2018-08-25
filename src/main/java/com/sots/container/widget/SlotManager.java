package com.sots.container.widget;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.sots.api.container.Widget;
import com.sots.api.util.EnumMouseButton;
import com.sots.api.util.Rectangle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class SlotManager extends Widget {
    private final Multimap<IItemHandler, IWidgetShiftClickHandler> shiftHandler = MultimapBuilder.hashKeys().linkedHashSetValues().build();
    private final Set<WidgetSlot> widgetSlots = new HashSet<>();
    private EnumMouseButton draggedButton;
    @Nonnull
    private ItemStack draggedStack = ItemStack.EMPTY;

    public SlotManager(int widgetID) {
        super(Rectangle.NULL, widgetID);

    }

    public boolean handleShiftClick(WidgetSlot from, EntityPlayer player){
        if (from.getStack().isEmpty() && shiftHandler.containsKey(from.getHandler())){
            return shiftHandler.get(from.getHandler()).stream().anyMatch(shift -> shift.handleShiftClick(from, player));
        }
        return false;
    }

    public void addShiftClickingHandler(IItemHandler from, IWidgetShiftClickHandler to){
        shiftHandler.put(from, to);
    }

    public void addDraggedSlot(WidgetSlot slot){
        widgetSlots.add(slot);
        slot.setDraggedStack(draggedStack);
    }

    public void setDraggedButton(EnumMouseButton draggedButton) {
        this.draggedButton = draggedButton;
    }

    public EnumMouseButton getDraggedButton() {
        return draggedButton;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }
}
