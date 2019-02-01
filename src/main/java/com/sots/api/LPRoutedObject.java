package com.sots.api;

import com.sots.LogisticsPipes2;
import com.sots.api.util.data.Triple;
import com.sots.routing.PendingToRoute;
import com.sots.tiles.TileGenericPipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class LPRoutedObject<T> {

    private static Map<Class<?>, ILPRoutedObjectSupplier> types = new HashMap<>();
    public final int TICK_MAX = 10;
    private final Deque<EnumFacing> route;
    private final T contents;
    private final UUID ID;
    private final UUID moduleUUid;
    public int ticks;
    private EnumFacing heading;
    private TileGenericPipe holding;
    private TileGenericPipe destination;
    private boolean canceled;

    public LPRoutedObject(T content, EnumFacing initVector, TileGenericPipe holder, Deque<EnumFacing> routingInfo, TileGenericPipe destination, UUID moduleUUid) {
        this.heading = initVector;
        this.holding = holder;
        this.route = routingInfo;
        this.moduleUUid = moduleUUid;
        this.ticks = 0;
        this.contents = copyContent(content);
        this.ID = UUID.randomUUID();
        this.destination = destination;
    }

    protected LPRoutedObject(NBTTagCompound compound) {
        this.ticks = compound.getInt("ticks");
        this.ID = compound.getUniqueId("UID");
        this.moduleUUid = compound.getUniqueId("moduleID");
        this.contents = readContentFromNBT(compound);
        int[] intArray = compound.getIntArray("route");
        Deque<EnumFacing> routingInfo = new ArrayDeque<>();
        for (int i1 : intArray) {
            routingInfo.add(EnumFacing.BY_INDEX[i1]);
        }
        this.route = routingInfo;
        this.heading = (EnumFacing.BY_INDEX[compound.getInt("heading")]);
    }

    public static LPRoutedObject readFromNBT(NBTTagCompound compound, TileGenericPipe holder) {
        //TODO: CONTINUE FROM HERE
        try {
            //LPRoutedObject item = LPRoutedObject.makeLPRoutedObjectFromContent(ticks, id, Class.forName(compound.getString("type")));
            LPRoutedObject item = types.get(Class.forName(compound.getString("type"))).getRoutedObject(compound);
            item.setHolding(holder);
            return item;
        } catch (Exception e) {
            LogisticsPipes2.LOGGER.info("Something went wrong", e);
            return null;
        }

    }

    public static void registerTypeOfLPRoutedObject(Class<?> content, ILPRoutedObjectSupplier supplier) {
        types.put(content, supplier);
    }

    public static LPRoutedObject makeLPRoutedObjectFromContent(Object content, EnumFacing initVector, TileGenericPipe holder, Deque<EnumFacing> routingInfo, TileGenericPipe destination, UUID moduleID) {
        return types.get(content.getClass()).getRoutedObject(content, initVector, holder, routingInfo, destination, moduleID);
    }

    public static LPRoutedObject makeLPRoutedObjectFromContent(TileGenericPipe holder, PendingToRoute pendingToRoute) {
        return types.get(pendingToRoute.getObject().getClass()).getRoutedObject(pendingToRoute.getObject(), pendingToRoute.getStartFacing(), holder,
                new ArrayDeque<>(pendingToRoute.getRoute().getdirectionStack()), (TileGenericPipe) pendingToRoute.getRoute().getTarget().getMember(), pendingToRoute.getAugmentID());
    }

    protected abstract T copyContent(T content);

    public abstract Class<T> getContentType();

    public EnumFacing getHeading() {
        return heading;
    }

    public void setHeading(EnumFacing heading) {
        this.heading = heading;
    }

    public TileGenericPipe getHolding() {
        return holding;
    }

    public void setHolding(TileGenericPipe holding) {
        this.holding = holding;
    }

    public EnumFacing getHeadingForNode() {
        if (route.peek() == null) {
            return EnumFacing.UP;
        }
        return route.pop();
    }

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public T getContent() {
        return contents;
    }

    public TileGenericPipe getDestination() {
        return destination;
    }

    public UUID getID() {
        return ID;
    }

    public UUID getModuleID() {
        return moduleUUid;
    }

    public Triple<Double, Double, Double> calculateTranslation(float partialTicks) {
        float tmpTicks = ticks + partialTicks;

        double newX = (getHeading().getDirectionVec().getX() * (tmpTicks / TICK_MAX - 0.5));
        double newY = (getHeading().getDirectionVec().getY() * (tmpTicks / TICK_MAX - 0.5));
        double newZ = (getHeading().getDirectionVec().getZ() * (tmpTicks / TICK_MAX - 0.5));
        return new Triple<>(newX, newY, newZ);
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInt("heading", heading.ordinal());
        tag.setUniqueId("UID", this.ID);
        tag.setUniqueId("moduleID", this.moduleUUid);
        writeContentToNBT(tag);
        tag.setInt("ticks", this.ticks);
        tag.setString("type", getContentType().getName());
        List<Integer> list = new ArrayList<>();
        for (EnumFacing node : route) {
            list.add(node.getIndex());
        }
        NBTTagIntArray routeList = new NBTTagIntArray(list);
        tag.setTag("route", routeList);
        return tag;
    }

    public abstract void writeContentToNBT(NBTTagCompound compound);

    public abstract T readContentFromNBT(NBTTagCompound compound);

    public abstract void render(TileGenericPipe te, float partialTicks);

    public void spawnInWorld(World world, double x, double y, double z) { }

    public void spawnInWorld() {
        BlockPos pos = getHolding().getPos();
        spawnInWorld(getHolding().getWorld(), pos.getX(), pos.getY(), pos.getZ());
    }

    public abstract void putInBlock(TileEntity te);
}
