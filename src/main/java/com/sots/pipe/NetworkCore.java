package com.sots.pipe;

import com.sots.LogisticsPipes2;
import com.sots.util.AccessHelper;
import com.sots.util.References;
import com.sots.util.holder.TileHolder;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;

public class NetworkCore extends BlockGenericPipe {

    public NetworkCore() {
        super(Material.IRON);
        setRegistryName(References.RN_NETWORK_CORE);
        setCreativeTab(CreativeTabs.TRANSPORTATION);
        setHardness(3.0f);
        setResistance(15.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 3);
    }

    @Override
    public String getTranslationKey() {
        return References.NAME_NETWORK_CORE;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
        return TileHolder.TILE_NETWORK_CORE.create();
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        super.onBlockActivated(state, worldIn, pos, player, hand, side, hitX, hitY, hitZ);
        if (!worldIn.isRemote) {
            TileEntity self = AccessHelper.getTileSafe(worldIn, pos);
            LogisticsPipes2.LOGGER.log(Level.DEBUG, (self != null ? "Tile is present!" : "Tile is absent!"));
        }
        return true;
    }

}
