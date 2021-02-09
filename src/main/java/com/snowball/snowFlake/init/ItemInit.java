package com.snowball.snowFlake.init;

import java.util.ArrayList;
import java.util.List;

import com.snowball.snowFlake.object.items.ItemBase;
import com.snowball.snowFlake.object.items.materials.IceCrystal;
import com.snowball.snowFlake.object.items.materials.SnowFlake;
import com.snowball.snowFlake.object.items.tools.SnowFlakeWand;
import com.snowball.snowFlake.object.items.tools.SnowballWand;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Material
	public static final ToolMaterial TOOL_ICE_CRYSTAL = EnumHelper.addToolMaterial("tool_ice_crystal", 2, 250, 6.0F, 2.0F, 14);
	
	//items
	public static final Item SNOW_FLAKE = new SnowFlake();

	//blizzard items
	public static final Item BLIZZARD_INGOT = new ItemBase("blizzard_ingot");
	
	//ice crystal items
	public static final Item ICE_CRYSTAL = new IceCrystal();
	
	//harden ice crystal items
	
	
	//tools
	public static final Item SNOWBALL_WAND = new SnowballWand("snowball_wand");
	public static final Item SNOW_FLAKE_WAND = new SnowFlakeWand();
}
