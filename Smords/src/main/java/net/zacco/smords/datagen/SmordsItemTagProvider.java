package net.zacco.smords.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.zacco.smords.item.SmordsItems;
import net.zacco.smords.util.SmordsTags;

import java.util.concurrent.CompletableFuture;

public class SmordsItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public SmordsItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registries, BlockTagProvider blockTags)
    {
        super(output, registries, blockTags);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup)
    {
        // Item versions of the block tags (used for recipes, furnace fuel, etc.)
        copy(SmordsTags.Blocks.SPEARMINT_LOGS, SmordsTags.Items.SPEARMINT_LOGS);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockTags.DIRT, ItemTags.DIRT);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);

        getOrCreateTagBuilder(ItemTags.SIGNS).add(SmordsItems.SPEARMINT_SIGN);
        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS).add(SmordsItems.SPEARMINT_HANGING_SIGN);
        getOrCreateTagBuilder(ItemTags.BOATS).add(SmordsItems.SPEARMINT_BOAT);
        getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(SmordsItems.SPEARMINT_CHEST_BOAT);
    }
}
