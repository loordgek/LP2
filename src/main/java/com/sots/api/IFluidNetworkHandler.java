package com.sots.api;

import com.sots.api.crafting.ICraftingTemplate;
import com.sots.api.util.data.Triple;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public interface IFluidNetworkHandler {

    Triple<UUID, UUID, FluidStack> findStorageForFluid(@Nonnull FluidStack stack);

    List<FluidStack> getFluidSnapShot();

    List<ICraftingTemplate<FluidStack>> getCraftebleFluids();
}
