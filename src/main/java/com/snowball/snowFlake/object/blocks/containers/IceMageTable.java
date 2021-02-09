package com.snowball.snowFlake.object.blocks.containers;

import com.snowball.snowFlake.object.blocks.BlockBase;

import net.minecraft.block.material.Material;

public class IceMageTable extends BlockBase {

	public IceMageTable() {
		super("ice_mage_table", Material.WOOD);
		setHarvestLevel("axe", 1);
	}

}
