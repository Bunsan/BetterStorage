package net.mcft.copy.betterstorage.tile;

import net.mcft.copy.betterstorage.misc.Constants;
import net.mcft.copy.betterstorage.tile.entity.TileEntityCraftingStation;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileCraftingStation extends TileContainerBetterStorage {
	
	private Icon topIcon;
	private Icon bottomIcon;
	
	public TileCraftingStation(int id) {
		super(id, Material.wood);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(Constants.modId + ":craftingStation");
		topIcon = iconRegister.registerIcon(Constants.modId + ":craftingStation_top");
		bottomIcon = iconRegister.registerIcon(Constants.modId + ":craftingStation_bottom");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return ((side == 0) ? bottomIcon : ((side == 1) ? topIcon : blockIcon));
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCraftingStation();
	}
	
}