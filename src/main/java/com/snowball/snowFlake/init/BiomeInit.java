package com.snowball.snowFlake.init;

import com.snowball.snowFlake.world.biomes.BiomePolar;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit {
	
	public static final Biome POLAR = new BiomePolar();
	
	public static void registerBiomes() {
		initBiome(POLAR, "Polar", BiomeType.ICY, Type.COLD);
	}
	
	//create biome
	public static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type type) {
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, type);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		System.out.println("Register Biome:" + name);
		return biome;	
	}
	
}
