package com.snowball.snowFlake.util.handler;

import com.snowball.snowFlake.object.blocks.containers.icefurnace.ContainerIceFurnace;
import com.snowball.snowFlake.object.blocks.containers.icefurnace.GuiIceFurnace;
import com.snowball.snowFlake.object.tileentity.TileEntityIceFurnace;
import com.snowball.snowFlake.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_ICE_FURNACE) return new ContainerIceFurnace(player.inventory, (TileEntityIceFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_ICE_FURNACE) return new GuiIceFurnace(player.inventory, (TileEntityIceFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

}
