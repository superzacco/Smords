package net.zacco.smords.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.zacco.smords.util.SmordsTags;

import java.util.concurrent.CompletableFuture;

import static net.zacco.smords.block.SmordsBlocks.*;

public class SmordsBlockTagProvider extends FabricTagProvider.BlockTagProvider
{
    public SmordsBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registries)
    {
        super(output, registries);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup)
    {
        getOrCreateTagBuilder(SmordsTags.Blocks.SPEARMINT_LOGS)
                .add(SPEARMINT_LOG, SPEARMINT_WOOD, STRIPPED_SPEARMINT_LOG, STRIPPED_SPEARMINT_WOOD);

        // Lets leaves recognise the logs, and lets the logs burn
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(SmordsTags.Blocks.SPEARMINT_LOGS);

        getOrCreateTagBuilder(BlockTags.PLANKS).add(SPEARMINT_PLANKS);
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(SPEARMINT_STAIRS);
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(SPEARMINT_SLAB);
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(SPEARMINT_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(SPEARMINT_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(SPEARMINT_DOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(SPEARMINT_TRAPDOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(SPEARMINT_BUTTON);
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(SPEARMINT_PRESSURE_PLATE);
        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(SPEARMINT_SIGN);
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(SPEARMINT_WALL_SIGN);
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(SPEARMINT_HANGING_SIGN);
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(SPEARMINT_WALL_HANGING_SIGN);

        // Lets plants, saplings and animals treat mint grass like regular grass
        getOrCreateTagBuilder(BlockTags.DIRT).add(MINT_GRASS_BLOCK);
        getOrCreateTagBuilder(BlockTags.ANIMALS_SPAWNABLE_ON).add(MINT_GRASS_BLOCK);
        getOrCreateTagBuilder(BlockTags.VALID_SPAWN).add(MINT_GRASS_BLOCK);
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(MINT_GRASS_BLOCK);

        getOrCreateTagBuilder(BlockTags.LEAVES).add(SPEARMINT_LEAVES);
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(SPEARMINT_LEAVES, SPEARMINT_BULB);
        getOrCreateTagBuilder(BlockTags.SAPLINGS).add(SPEARMINT_SAPLING);
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(POTTED_SPEARMINT_SAPLING);

        // Axes break the whole set quickly
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .addTag(SmordsTags.Blocks.SPEARMINT_LOGS)
                .add(SPEARMINT_PLANKS, SPEARMINT_STAIRS, SPEARMINT_SLAB, SPEARMINT_FENCE, SPEARMINT_FENCE_GATE,
                        SPEARMINT_DOOR, SPEARMINT_TRAPDOOR, SPEARMINT_BUTTON, SPEARMINT_PRESSURE_PLATE,
                        SPEARMINT_SIGN, SPEARMINT_WALL_SIGN, SPEARMINT_HANGING_SIGN, SPEARMINT_WALL_HANGING_SIGN,
                        SPEARMINT_REFINERY);
    }
}
