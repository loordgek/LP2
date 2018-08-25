package com.sots.api;

import com.sots.LogisticsPipes2;
import com.sots.api.util.data.Triple;
import com.sots.routing.PendingToRoute;
import com.sots.routing.promises.PromiseType;
import com.sots.tiles.TileGenericPipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class LPRoutedObject<T> {

    private static Map<Class<?>, ILPRoutedObjectSupplier> types = new HashMap<>();
    public final int TICK_MAX = 10;
    private final Deque<EnumFacing> route;
    private final T contents;
    //private Triple<Double, Double, Double> position;
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
        //this.position = new Triple<Double, Double, Double>(x, y, z);
        this.ID = UUID.randomUUID();
        this.destination = destination;
    }

    protected LPRoutedObject(NBTTagCompound compound) {
        this.ticks = compound.getInteger("ticks");
        //this.position=new Triple<Double, Double, Double>(x, y, z);
        this.ID = compound.getUniqueId("UID");
        this.moduleUUid = compound.getUniqueId("moduleID");
        this.contents = readContentFromNBT(compound);
        NBTTagList tagList = compound.getTagList("route", Constants.NBT.TAG_COMPOUND);
        Deque<EnumFacing> routingInfo = new ArrayDeque<>();
        for (int i = 0; i < tagList.tagCount(); i++) {
            routingInfo.add(EnumFacing.VALUES[tagList.getIntAt(i)]);
        }
        this.route = routingInfo;
        this.heading = (EnumFacing.VALUES[compound.getInteger("heading")]);
    }

    public static LPRoutedObject readFromNBT(NBTTagCompound compound, TileGenericPipe holder) {
        //TODO: CONTINUE FROM HERE
        try {
            //LPRoutedObject item = LPRoutedObject.makeLPRoutedObjectFromContent(ticks, id, Class.forName(compound.getString("type")));
            LPRoutedObject item = types.get(Class.forName(compound.getString("type"))).getRoutedObject(compound);
            item.setHeading(EnumFacing.VALUES[compound.getInteger("heading")]);
            item.setHolding(holder);
            return item;
        } catch (Exception e) {
            LogisticsPipes2.logger.info("Something went wrong", e);
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
        tag.setInteger("heading", heading.ordinal());
        tag.setUniqueId("UID", this.ID);
        tag.setUniqueId("moduleID", this.moduleUUid);
        writeContentToNBT(tag);
        tag.setInteger("ticks", this.ticks);
        tag.setString("type", getContentType().getName());
        NBTTagList routeList = new NBTTagList();
        for (EnumFacing node : route) {
            NBTTagCompound nodeTag = new NBTTagCompound();
            //nodeTag.setUniqueId("UID", node.getKey());
            nodeTag.setInteger("heading", node.ordinal());
            routeList.appendTag(nodeTag);
        }
        tag.setTag("route", routeList);
        return tag;
    }

    public abstract void writeContentToNBT(NBTTagCompound compound);

    public abstract T readContentFromNBT(NBTTagCompound compound);

    @SideOnly(Side.CLIENT)
    public abstract void render(TileGenericPipe te, float partialTicks);

    public abstract void spawnInWorld(World world, double x, double y, double z);

    public abstract void putInBlock(TileEntity te);
}
