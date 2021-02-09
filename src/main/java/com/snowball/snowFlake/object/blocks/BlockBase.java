package com.snowball.snowFlake.object.blocks;

import com.snowball.snowFlake.Main;
import com.snowball.snowFlake.init.BlockInit;
import com.snowball.snowFlake.init.ItemInit;
import com.snowball.snowFlake.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel{

	public BlockBase(String name, Material materialIn) {
		super(materialIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.SNOWFLAKE);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}
