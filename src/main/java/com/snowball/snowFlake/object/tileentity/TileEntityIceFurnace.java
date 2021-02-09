package com.snowball.snowFlake.object.tileentity;

import com.snowball.snowFlake.init.ItemInit;
import com.snowball.snowFlake.object.blocks.containers.icefurnace.IceFurnace;
import com.snowball.snowFlake.object.recipes.IceFurnaceRecipes;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityIceFurnace extends TileEntity implements ITickable{
	private ItemStackHandler handler = new ItemStackHandler(3);
	private String customName;
	private ItemStack smelting = ItemStack.EMPTY;
	
	private int burnTime;
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime;

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		else return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.ice_furnace");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentBurnTime = getItemBurnTime((ItemStack)this.handler.getStackInSlot(1));
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		compound.setTag("Inventory", this.handler.serializeNBT());
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	public boolean isBurning() 
	{
		return this.burnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntityIceFurnace te) 
	{
		return te.getField(0) > 0;
	}
	
	public void update() 
	{	
		if(this.isBurning())
		{
			--this.burnTime;
			IceFurnace.setState(true, world, pos);
		}
		
		ItemStack input = this.handler.getStackInSlot(0);
		ItemStack fuel = this.handler.getStackInSlot(1);
		
		if(this.isBurning() || !fuel.isEmpty() && !this.handler.getStackInSlot(0).isEmpty())
		{
			if(!this.isBurning() && this.canSmelt())
			{
				this.burnTime = getItemBurnTime(fuel);
				this.currentBurnTime = burnTime;
				
				if(this.isBurning() && !fuel.isEmpty())
				{
					Item item = fuel.getItem();
					fuel.shrink(1);
					
					if(fuel.isEmpty())
					{
						ItemStack item1 = item.getContainerItem(fuel);
						this.handler.setStackInSlot(1, item1);
					}
				}
			}
		}
		
		if(this.isBurning() && this.canSmelt())
		{
			cookTime++;
			System.out.println("Smelting..., cookTime="+cookTime);
			if(cookTime == 200)//dirty
			{
				ItemStack output = IceFurnaceRecipes.getInstance().getSinteringResult(input);
				if(!output.isEmpty())
				{
					smelting = output;
					input.shrink(1);
					handler.setStackInSlot(0, input);
				}
				
				if(handler.getStackInSlot(2).isEmpty()) 
				{
					handler.setStackInSlot(2, output);
				}
				else if(handler.getStackInSlot(2).getCount() > 0)
				{
					handler.getStackInSlot(2).grow(1);
				}
				else
				{
					handler.insertItem(2, smelting, false);
				}
				
				smelting = ItemStack.EMPTY;
				cookTime = 0;
				return;
			}
		}
	}
	
	private boolean canSmelt() 
	{
		if(((ItemStack)this.handler.getStackInSlot(0)).isEmpty()) return false;
		else 
		{
			ItemStack result = IceFurnaceRecipes.getInstance().getSinteringResult((ItemStack)this.handler.getStackInSlot(0));	
			if(result.isEmpty()) return false;
			else
			{
				ItemStack output = (ItemStack)this.handler.getStackInSlot(2);
				if(output.isEmpty()) return true;
				if(!output.isItemEqual(result)) return false;
				int res = output.getCount() + result.getCount();
				return res <= 64 && res <= output.getMaxStackSize();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static int getItemBurnTime(ItemStack fuel) 
	{
		if(fuel.isEmpty()) return 0;
		else 
		{
			Item item = fuel.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) 
			{
				//Block fuel
				//Not use yet
				//Block block = Block.getBlockFromItem(item);
			}

//			if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName())) return 200;
//			if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName())) return 200;
//			if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())) return 200;
//			if (item == Items.STICK) return 100;
//			if (item == Items.COAL) return 1600;
//			if (item == Items.LAVA_BUCKET) return 20000;
//			if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
//			if (item == Items.BLAZE_ROD) return 2400;
			if (item == ItemInit.SNOW_FLAKE) return 200;
			
			return GameRegistry.getFuelValue(fuel);
		}
	}
		
	public static boolean isItemFuel(ItemStack fuel)
	{
		return getItemBurnTime(fuel) > 0;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public int getField(int id) 
	{
		switch(id) 
		{
		case 0:
			return this.burnTime;
		case 1:
			return this.currentBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return 200;//total cook time, dirty
		default:
			return 0;
		}
	}

	public void setField(int id, int value) 
	{
		switch(id) 
		{
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = 200;
		}
	}
}
