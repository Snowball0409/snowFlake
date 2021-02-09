package com.snowball.snowFlake.object.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.snowball.snowFlake.init.ItemInit;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class IceFurnaceRecipes {
	private static final IceFurnaceRecipes INSTANCE = new IceFurnaceRecipes();
	private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static IceFurnaceRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private IceFurnaceRecipes() 
	{
		addSinteringRecipe(new ItemStack(Blocks.PACKED_ICE), new ItemStack(ItemInit.ICE_CRYSTAL), 5.0F);
	}

	
	public void addSinteringRecipe(ItemStack input, ItemStack result, float experience) 
	{
		if(getSinteringResult(input) != ItemStack.EMPTY) return;
		this.smeltingList.put(input, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getSinteringResult(ItemStack input) 
	{
		for(Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet()) 
		{
			if(this.compareItemStacks(input, (ItemStack)entry.getKey())) 
			{
				return entry.getValue();
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Map<ItemStack, ItemStack> getDualSmeltingList() 
	{
		return this.smeltingList;
	}
	
	public float getSinteringExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) 
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
