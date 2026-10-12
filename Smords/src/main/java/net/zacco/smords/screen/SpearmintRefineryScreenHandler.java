package net.zacco.smords.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.zacco.smords.block.entity.SpearmintRefineryBlockEntity;
import net.zacco.smords.item.SmordsItems;

public class SpearmintRefineryScreenHandler extends ScreenHandler
{
    private final Inventory inventory;
    private final PropertyDelegate properties;

    // Client side: the real inventory and progress get synced from the server
    public SpearmintRefineryScreenHandler(int syncId, PlayerInventory playerInventory)
    {
        this(syncId, playerInventory, new SimpleInventory(2), new ArrayPropertyDelegate(3));
    }

    public SpearmintRefineryScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate properties)
    {
        super(SmordsScreenHandlers.SPEARMINT_REFINERY, syncId);
        checkSize(inventory, 2);
        this.inventory = inventory;
        this.properties = properties;
        inventory.onOpen(playerInventory.player);

        // Only Spearmint goes in the input
        addSlot(new Slot(inventory, SpearmintRefineryBlockEntity.INPUT_SLOT, 56, 35)
        {
            @Override
            public boolean canInsert(ItemStack stack)
            {
                return stack.isOf(SmordsItems.SPEARMINT);
            }
        });

        // Nothing goes in the output
        addSlot(new Slot(inventory, SpearmintRefineryBlockEntity.OUTPUT_SLOT, 116, 35)
        {
            @Override
            public boolean canInsert(ItemStack stack)
            {
                return false;
            }
        });

        for (int row = 0; row < 3; row++)
        {
            for (int column = 0; column < 9; column++)
            {
                addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }
        for (int column = 0; column < 9; column++)
        {
            addSlot(new Slot(playerInventory, column, 8 + column * 18, 142));
        }

        addProperties(properties);
    }

    public boolean IsStructureComplete()
    {
        return properties.get(2) != 0;
    }

    // How many pixels of the 24 pixel arrow to fill
    public int GetArrowProgress()
    {
        int progress = properties.get(0);
        int max = properties.get(1);
        return max == 0 ? 0 : progress * 24 / max;
    }

    // Shift-clicking
    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex)
    {
        Slot slot = slots.get(slotIndex);
        if (!slot.hasStack())
        {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getStack();
        ItemStack original = stack.copy();
        int machineSlots = inventory.size();

        if (slotIndex < machineSlots)
        {
            // Machine -> player
            if (!insertItem(stack, machineSlots, slots.size(), true))
            {
                return ItemStack.EMPTY;
            }
        }
        else if (stack.isOf(SmordsItems.SPEARMINT))
        {
            // Player -> input slot
            if (!insertItem(stack, SpearmintRefineryBlockEntity.INPUT_SLOT, SpearmintRefineryBlockEntity.INPUT_SLOT + 1, false))
            {
                return ItemStack.EMPTY;
            }
        }
        else
        {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty())
        {
            slot.setStack(ItemStack.EMPTY);
        }
        else
        {
            slot.markDirty();
        }
        return original;
    }

    @Override
    public boolean canUse(PlayerEntity player)
    {
        return inventory.canPlayerUse(player);
    }
}
