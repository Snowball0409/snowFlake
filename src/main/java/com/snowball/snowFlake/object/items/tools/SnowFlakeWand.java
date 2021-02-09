package com.snowball.snowFlake.object.items.tools;

import com.snowball.snowFlake.init.BlockInit;
import com.snowball.snowFlake.init.ItemInit;
import com.snowball.snowFlake.object.items.ItemBase;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SnowFlakeWand extends ItemBase {
	
	private static float flakeProbability = 0.3F;

	public SnowFlakeWand() {
		super("snow_flake_wand");
		this.setMaxDamage(200);
		this.setMaxStackSize(1);
	}
	
	public float getProbability() {
		return flakeProbability;
	}
	//using
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		IBlockState block = worldIn.getBlockState(pos);
		ItemStack item = player.getHeldItemMainhand();
		
		if(!worldIn.isRemote) {
			//create snow flake
			if(block.getBlock().equals(Blocks.ICE)) {
				worldIn.setBlockToAir(pos);
				EntityItem dropItem;
				//drop flake probability
				if(Math.random() <= this.getProbability()) {
					dropItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemInit.SNOW_FLAKE, 1));
				}
				else {
					dropItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Item.getItemFromBlock(Blocks.ICE), 1));
				}
				worldIn.spawnEntity(dropItem);
			}
			else if(block.getBlock().equals(Blocks.CRAFTING_TABLE)) {
				worldIn.setBlockState(pos, BlockInit.ICE_MAGE_TABLE.getDefaultState());
			}
			else if(block.getBlock().equals(Blocks.FURNACE)) {
				worldIn.setBlockState(pos, BlockInit.ICE_FURNACE.getDefaultState());
			}
			item.damageItem(1, ((EntityLivingBase)player));
		}
		
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

}
