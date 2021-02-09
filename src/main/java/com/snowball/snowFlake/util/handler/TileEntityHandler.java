package com.snowball.snowFlake.util.handler;

import com.snowball.snowFlake.object.tileentity.TileEntityIceFurnace;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	@SuppressWarnings("deprecation")
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityIceFurnace.class, "ice_furnace");
	}
}
