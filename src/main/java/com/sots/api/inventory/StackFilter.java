package com.sots.api.inventory;

import com.google.common.collect.Range;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.function.Predicate;

public class StackFilter implements IItemFilter {
    /**
     * Filter that matches any stack.
     */
    public static final StackFilter ANY = builder().setInverted().build();

    private final Item item;
    private final Range<Integer> metadata;
    private final NBTTagCompound nbtTag;
    private final boolean matchNBT;
    private final Map<Pair<Capability<?>, EnumFacing>, Predicate<?>> capabilityFilters;
    private final boolean inverted;

    private StackFilter(Item item, Range<Integer> metadata, NBTTagCompound nbtTag, boolean matchNBT,
                        Map<Pair<Capability<?>, EnumFacing>, Predicate<?>> capabilityFilters, boolean inverted) {
        this.item = item;
        this.metadata = metadata;
        this.nbtTag = nbtTag;
        this.matchNBT = matchNBT;
        this.capabilityFilters = capabilityFilters;
        this.inverted = inverted;
    }

    /**
     * Tests the stack's {@link Capability Capabilities} against this filter. The result respects inversion.
     */
    @SuppressWarnings("unchecked")
    public <T> boolean testCapabilities(ItemStack stack) {
        for (Map.Entry<Pair<Capability<?>, EnumFacing>, Predicate<?>> entry : capabilityFilters.entrySet()) {
            Capability<T> cap = (Capability<T>) entry.getKey().getKey();
            EnumFacing face = entry.getKey().getValue();

            boolean has = stack.hasCapability(cap, face);
            if (has == inverted) {
                return false;
            }
            if (has && ((Predicate<T>) entry.getValue()).test(stack.getCapability(cap, face)) == inverted) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean test(ItemStack stack) {
        if (stack.getItem() != item) {
            return inverted;
        }
        if (!metadata.contains(stack.getMetadata())) {
            return inverted;
        }
        if (matchNBT) {
            NBTTagCompound tag = stack.getTagCompound();
            if ((nbtTag == null) != (tag == null)) {
                return inverted;
            }
            return (tag == null || nbtTag.equals(tag)) != inverted;
        }
        // This can be re-used because it's the last test
        return testCapabilities(stack);
    }

    public static Builder builder(){
        return new Builder();
    }

    private static class Builder{
        private Item item;
        private Range<Integer> damage;
        private NBTTagCompound nbtTag;
        private boolean matchNBT;
        private Map<Pair<Capability<?>, EnumFacing>, Predicate<?>> capabilityFilters;
        private boolean inverted;

        public Builder setInverted() {
            this.inverted = true;
            return this;
        }

        public StackFilter build(){
            return new StackFilter(item, damage, nbtTag, matchNBT, capabilityFilters, inverted);
        }
    }
}
