package net.zacco.smords.block;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.zacco.smords.block.entity.SmordsBlockEntities;
import net.zacco.smords.block.entity.SpearmintRefineryBlockEntity;
import org.jetbrains.annotations.Nullable;

/**
 * The top block of a formed Spearmint Refinery. It looks like spearmint planks, but holds the
 * refinery's inventory and timer. Right-clicking the top plank of a complete structure turns it into this.
 */
public class SpearmintRefineryBlock extends BlockWithEntity
{
    public SpearmintRefineryBlock(Settings settings)
    {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new SpearmintRefineryBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        if (!world.isClient && world.getBlockEntity(pos) instanceof SpearmintRefineryBlockEntity refinery)
        {
            player.openHandledScreen(refinery);
        }
        return ActionResult.SUCCESS;
    }

    // Drop whatever was inside when it's broken
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved)
    {
        if (!state.isOf(newState.getBlock()))
        {
            if (world.getBlockEntity(pos) instanceof SpearmintRefineryBlockEntity refinery)
            {
                ItemScatterer.spawn(world, pos, refinery);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state)
    {
        return new ItemStack(SmordsBlocks.SPEARMINT_PLANKS);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClient ? null : validateTicker(type, SmordsBlockEntities.SPEARMINT_REFINERY, SpearmintRefineryBlockEntity::Tick);
    }
}
