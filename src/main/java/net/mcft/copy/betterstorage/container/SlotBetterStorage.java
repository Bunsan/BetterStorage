package net.mcft.copy.betterstorage.container;

import com.bioxx.tfc.Items.ItemBlocks.ItemAnvil;
import com.bioxx.tfc.Items.ItemBlocks.ItemAnvil1;
import com.bioxx.tfc.Items.ItemBlocks.ItemAnvil2;
import com.bioxx.tfc.Items.ItemBlocks.ItemBarrels;
import com.bioxx.tfc.Items.ItemBlocks.ItemLargeVessel;
import com.bioxx.tfc.api.TFCItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotBetterStorage extends Slot {

	private final ContainerBetterStorage container;
	private boolean isProtected = false;
	
	public SlotBetterStorage(ContainerBetterStorage container,
	                         IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
		this.container = container;
	}
	
	/** Prevents the slot from being interacted with. */
	public SlotBetterStorage setProtected() {
		isProtected = true;
		return this;
	}
	/** Returns if the slot is protected. */
	public boolean isProtected() { return isProtected; }
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (!isProtected && inventory.isItemValidForSlot(slotNumber, stack))
		{
			if (container.inInventory(slotNumber))
			{
				Item item = stack.getItem();
				if (item instanceof ItemBarrels || item instanceof ItemLargeVessel || item instanceof ItemAnvil || item instanceof ItemAnvil1 || item instanceof ItemAnvil2) 
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			return true;
		}
		else
		{
			return false;	
		}
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer player) {
		// Not sure if isItemValidForSlot can / should be used to check
		// if items can be taken out, but I'll just use it anyway.
		return (!isProtected && inventory.isItemValidForSlot(slotNumber, null));
	}
	
	@Override
	public void onSlotChanged() {
		container.onSlotChanged(slotNumber);
		super.onSlotChanged();
	}
	
}
