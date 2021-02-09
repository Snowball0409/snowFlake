package com.snowball.snowFlake.util.handler;

import com.snowball.snowFlake.Main;
import com.snowball.snowFlake.init.BiomeInit;
import com.snowball.snowFlake.init.BlockInit;
import com.snowball.snowFlake.init.ItemInit;
import com.snowball.snowFlake.util.IHasModel;
import com.snowball.snowFlake.world.types.WorldTypeTest;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.WorldType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@EventBusSubscriber
public class RegistryHandler 
{

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(RegistryEvent.Register<Item> event)
	{
		for(Item item : ItemInit.ITEMS) 
		{
			if(item instanceof IHasModel) 
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : BlockInit.BLOCKS) 
		{
			if(block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	

	public static void preInitRegistries(FMLPreInitializationEvent event) {
		BiomeInit.registerBiomes();
		
	}

	@SuppressWarnings("unused")
	public static void postInitRegistries(FMLPostInitializationEvent event) {
		WorldType TEST = new WorldTypeTest();
	}

	public static void initRegistries(FMLInitializationEvent event) {
		SoundsHandler.registerSounds();
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
}
