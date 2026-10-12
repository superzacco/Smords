package net.zacco.smords.multiblock;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.zacco.smords.block.SmordsBlocks;
import net.zacco.smords.block.entity.SpearmintRefineryBlockEntity;

/**
 * A three-layer multiblock that refines Spearmint Chunks into Spearmint Leaves.
 * Build it out of spearmint planks, then right-click the top block to form it. The top block
 * becomes a SpearmintRefineryBlock, which has the GUI and does the actual refining.
 */
public class SpearmintRefinery
{
    // How many Spearmint Chunks make one Spearmint Leaf (each batch takes SpearmintRefineryBlockEntity.PROCESS_TIME)
    public static final int CHUNKS_PER_LEAF = 3;

    // Each layer seen from above, bottom layer first. P = structure block, . = must be empty
    private static final String[][] PATTERN = {
            { "PPP",
              "PPP",
              "PPP" },

            { ".P.",
              "PPP",
              ".P." },

            { "...",
              ".P.",
              "..." },
    };

    // Placeholder until the refinery gets its own blocks
    public static boolean IsStructureBlock(BlockState state)
    {
        return state.isOf(SmordsBlocks.SPEARMINT_PLANKS) || state.isOf(SmordsBlocks.SPEARMINT_REFINERY);
    }

    // top is the single block on the top layer
    public static boolean IsComplete(World world, BlockPos top)
    {
        BlockPos corner = top.add(-1, -(PATTERN.length - 1), -1);

        for (int y = 0; y < PATTERN.length; y++)
        {
            for (int z = 0; z < 3; z++)
            {
                for (int x = 0; x < 3; x++)
                {
                    BlockState state = world.getBlockState(corner.add(x, y, z));
                    boolean wantsBlock = PATTERN[y][z].charAt(x) == 'P';

                    if (wantsBlock ? !IsStructureBlock(state) : !state.isAir())
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void RegisterSpearmintRefinery()
    {
        UseBlockCallback.EVENT.register(SpearmintRefinery::OnUseBlock);
    }

    // Right-clicking the top plank of a finished structure forms the refinery and opens it
    private static ActionResult OnUseBlock(PlayerEntity player, World world, Hand hand, BlockHitResult hit)
    {
        BlockPos top = hit.getBlockPos();

        // Sneak to place blocks against the structure like normal
        if (player.isSpectator() || player.isSneaking() || hand != Hand.MAIN_HAND
                || !world.getBlockState(top).isOf(SmordsBlocks.SPEARMINT_PLANKS) || !IsComplete(world, top))
        {
            return ActionResult.PASS;
        }

        if (!world.isClient)
        {
            world.setBlockState(top, SmordsBlocks.SPEARMINT_REFINERY.getDefaultState());
            if (world.getBlockEntity(top) instanceof SpearmintRefineryBlockEntity refinery)
            {
                player.openHandledScreen(refinery);
            }

            Vec3d center = Vec3d.ofCenter(top);
            world.playSound(null, top, SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.BLOCKS, 0.6f, 1.5f);
            ((ServerWorld) world).spawnParticles(ParticleTypes.HAPPY_VILLAGER, center.x, center.y, center.z, 16, 0.6, 0.6, 0.6, 0.0);
        }
        return ActionResult.SUCCESS;
    }
}
