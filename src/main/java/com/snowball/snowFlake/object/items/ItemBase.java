package com.snowball.snowFlake.object.items;

import com.snowball.snowFlake.Main;
import com.snowball.snowFlake.init.ItemInit;
import com.snowball.snowFlake.util.IHasModel;

import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.SNOWFLAKE);
	
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
