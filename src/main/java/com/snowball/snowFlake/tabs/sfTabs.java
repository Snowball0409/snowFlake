package com.snowball.snowFlake.tabs;

import com.snowball.snowFlake.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class sfTabs extends CreativeTabs{

	public sfTabs(String label) {
		super("sf_tab");
		this.setBackgroundImageName("bg.png");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.BLIZZARD_INGOT);
	}

}
