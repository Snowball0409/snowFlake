package com.snowball.snowFlake.world.biomes;

import java.util.Random;

import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenIcePath;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomePolar extends Biome {
	
	private final WorldGenIceSpike iceSpike = new WorldGenIceSpike();
    private final WorldGenIcePath icePatch = new WorldGenIcePath(4);
    
	public BiomePolar() {
		super(new BiomeProperties("Polar").setBaseHeight(1.0F).setHeightVariation(1.2F).setTemperature(0.6F).setSnowEnabled().setWaterColor(6333376));
	
		topBlock = Blocks.ICE.getDefaultState();
		fillerBlock = Blocks.PACKED_ICE.getDefaultState();

		this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 10, 2, 3));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPolarBear.class, 1, 1, 2));
		
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityStray.class, 80, 4, 4));
	}
	
	public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 3; ++i)
        {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            this.iceSpike.generate(worldIn, rand, worldIn.getHeight(pos.add(j, 0, k)));
        }

        for (int l = 0; l < 2; ++l)
        {
            int i1 = rand.nextInt(16) + 8;
            int j1 = rand.nextInt(16) + 8;
            this.icePatch.generate(worldIn, rand, worldIn.getHeight(pos.add(i1, 0, j1)));
        }

        super.decorate(worldIn, rand, pos);
    }
	
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return new WorldGenTaiga2(false);
    }
}
