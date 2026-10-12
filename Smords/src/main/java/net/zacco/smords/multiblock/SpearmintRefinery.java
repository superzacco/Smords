package net.zacco.smords.multiblock;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.zacco.smords.block.SmordsBlocks;
import net.zacco.smords.item.SmordsItems;

/**
 * A three-layer multiblock that refines Spearmint into Spearmint Extract.
 * Right-click the top block with Spearmint to refine one batch, or sneak to refine the whole stack.
 */
public class SpearmintRefinery
{
    // How much Spearmint makes one Spearmint Extract
    public static final int SPEARMINT_PER_EXTRACT = 3;

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
        return state.isOf(SmordsBlocks.SPEARMINT_PLANKS);
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

    private static ActionResult OnUseBlock(PlayerEntity player, World world, Hand hand, BlockHitResult hit)
    {
        ItemStack stack = player.getStackInHand(hand);
        BlockPos top = hit.getBlockPos();

        if (player.isSpectator() || !stack.isOf(SmordsItems.SPEARMINT) || !IsComplete(world, top))
        {
            return ActionResult.PASS;
        }

        if (stack.getCount() < SPEARMINT_PER_EXTRACT && !player.isCreative())
        {
            // A failed click never reaches the server, so the message is shown client-side
            if (world.isClient)
            {
                player.sendMessage(Text.translatable("message.smords.refinery.not_enough", SPEARMINT_PER_EXTRACT), true);
            }
            return ActionResult.FAIL;
        }

        if (world.isClient)
        {
            return ActionResult.SUCCESS;
        }

        // Sneak to refine as much of the stack as possible
        int batches = player.isSneaking() ? Math.max(1, stack.getCount() / SPEARMINT_PER_EXTRACT) : 1;
        if (!player.isCreative())
        {
            stack.decrement(batches * SPEARMINT_PER_EXTRACT);
        }

        Refine((ServerWorld) world, top, batches);
        return ActionResult.SUCCESS;
    }

    private static void Refine(ServerWorld world, BlockPos top, int amount)
    {
        Vec3d output = Vec3d.ofCenter(top.up());

        // Split into stacks in case it's more than one stack's worth
        int maxStack = SmordsItems.SPEARMINT_EXTRACT.getMaxCount();
        while (amount > 0)
        {
            int count = Math.min(amount, maxStack);
            ItemEntity item = new ItemEntity(world, output.x, output.y, output.z, new ItemStack(SmordsItems.SPEARMINT_EXTRACT, count));
            item.setVelocity(0, 0.2, 0);
            world.spawnEntity(item);
            amount -= count;
        }

        world.playSound(null, top, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1.0f, 1.2f);
        world.spawnParticles(ParticleTypes.HAPPY_VILLAGER, output.x, output.y, output.z, 12, 0.4, 0.3, 0.4, 0.0);
    }
}
