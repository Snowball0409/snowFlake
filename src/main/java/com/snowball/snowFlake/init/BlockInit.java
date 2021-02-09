package com.snowball.snowFlake.init;

import java.util.ArrayList;
import java.util.List;

import com.snowball.snowFlake.object.blocks.BlockBase;
import com.snowball.snowFlake.object.blocks.containers.IceMageTable;
import com.snowball.snowFlake.object.blocks.containers.icefurnace.IceFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//blocks
	public static final Block FREEZING_PLANKS = new BlockBase("freezing_plank", Material.WOOD);
	
	//feature blocks
	public static final Block ICE_MAGE_TABLE = new IceMageTable();
	public static final Block ICE_FURNACE = new IceFurnace();
}
