package com.snowball.snowFlake.object.items.tools;

import com.snowball.snowFlake.object.items.ItemBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SnowballWand extends ItemBase{

	public SnowballWand(String name) {
		super(name);
	}
	
	//using
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		Vec3d vec = playerIn.getLookVec();
		
		EntitySnowball e = new EntitySnowball(worldIn);
		e.setPosition(playerIn.posX + vec.x*1.50, playerIn.posY + vec.y*1.50, playerIn.posZ + vec.z*1.50);
		e.setVelocity(vec.x, vec.y, vec.z);
		worldIn.spawnEntity(e);
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}
}
