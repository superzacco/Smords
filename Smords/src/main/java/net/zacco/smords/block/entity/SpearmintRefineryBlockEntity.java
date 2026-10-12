package net.zacco.smords.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.zacco.smords.item.SmordsItems;
import net.zacco.smords.multiblock.SpearmintRefinery;
import net.zacco.smords.screen.SpearmintRefineryScreenHandler;
import org.jetbrains.annotations.Nullable;

public class SpearmintRefineryBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, SidedInventory
{
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    // Ticks per batch (20 ticks = 1 second)
    public static final int PROCESS_TIME = 100;

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
    private int progress = 0;
    private boolean structureComplete = false;

    // Synced to the screen so it can draw the arrow and the "incomplete" warning
    private final PropertyDelegate properties = new PropertyDelegate()
    {
        @Override
        public int get(int index)
        {
            return switch (index)
            {
                case 0 -> progress;
                case 1 -> PROCESS_TIME;
                case 2 -> structureComplete ? 1 : 0;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value)
        {
            if (index == 0) progress = value;
            if (index == 2) structureComplete = value != 0;
        }

        @Override
        public int size()
        {
            return 3;
        }
    };

    public SpearmintRefineryBlockEntity(BlockPos pos, BlockState state)
    {
        super(SmordsBlockEntities.SPEARMINT_REFINERY, pos, state);
    }

    public static void Tick(World world, BlockPos pos, BlockState state, SpearmintRefineryBlockEntity refinery)
    {
        refinery.structureComplete = SpearmintRefinery.IsComplete(world, pos);

        if (!refinery.structureComplete || !refinery.CanRefine())
        {
            if (refinery.progress != 0)
            {
                refinery.progress = 0;
                refinery.markDirty();
            }
            return;
        }

        refinery.progress++;
        if (refinery.progress >= PROCESS_TIME)
        {
            refinery.progress = 0;
            refinery.inventory.get(INPUT_SLOT).decrement(SpearmintRefinery.SPEARMINT_PER_EXTRACT);

            ItemStack output = refinery.inventory.get(OUTPUT_SLOT);
            if (output.isEmpty())
            {
                refinery.inventory.set(OUTPUT_SLOT, new ItemStack(SmordsItems.SPEARMINT_EXTRACT));
            }
            else
            {
                output.increment(1);
            }

            world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 0.6f, 1.2f);
        }
        refinery.markDirty();
    }

    private boolean CanRefine()
    {
        ItemStack input = inventory.get(INPUT_SLOT);
        ItemStack output = inventory.get(OUTPUT_SLOT);

        boolean enoughInput = input.isOf(SmordsItems.SPEARMINT) && input.getCount() >= SpearmintRefinery.SPEARMINT_PER_EXTRACT;
        boolean roomForOutput = output.isEmpty() || (output.isOf(SmordsItems.SPEARMINT_EXTRACT) && output.getCount() < output.getMaxCount());
        return enoughInput && roomForOutput;
    }

    //#region Screen
    @Override
    public Text getDisplayName()
    {
        return Text.translatable("container.smords.spearmint_refinery");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
    {
        return new SpearmintRefineryScreenHandler(syncId, playerInventory, this, properties);
    }
    //#endregion

    //#region Saving
    @Override
    protected void writeNbt(NbtCompound nbt)
    {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt)
    {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("progress");
    }
    //#endregion

    //#region Inventory (hoppers put Spearmint in from the top or sides, and take Extract out from the bottom)
    @Override
    public int[] getAvailableSlots(Direction side)
    {
        return side == Direction.DOWN ? new int[] { OUTPUT_SLOT } : new int[] { INPUT_SLOT };
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir)
    {
        return isValid(slot, stack);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir)
    {
        return slot == OUTPUT_SLOT;
    }

    @Override
    public boolean isValid(int slot, ItemStack stack)
    {
        return slot == INPUT_SLOT && stack.isOf(SmordsItems.SPEARMINT);
    }

    @Override
    public int size()
    {
        return inventory.size();
    }

    @Override
    public boolean isEmpty()
    {
        return inventory.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStack(int slot)
    {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount)
    {
        ItemStack result = Inventories.splitStack(inventory, slot, amount);
        if (!result.isEmpty())
        {
            markDirty();
        }
        return result;
    }

    @Override
    public ItemStack removeStack(int slot)
    {
        return Inventories.removeStack(inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack)
    {
        inventory.set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack())
        {
            stack.setCount(getMaxCountPerStack());
        }
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player)
    {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    public void clear()
    {
        inventory.clear();
    }
    //#endregion
}
