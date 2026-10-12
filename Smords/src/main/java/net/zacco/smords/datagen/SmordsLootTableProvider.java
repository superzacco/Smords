package net.zacco.smords.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.zacco.smords.block.SmordsBlocks;
import net.zacco.smords.item.SmordsItems;

public class SmordsLootTableProvider extends FabricBlockLootTableProvider
{
    public SmordsLootTableProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generate()
    {
        // Dirt, or itself with Silk Touch, like vanilla grass
        addDrop(SmordsBlocks.MINT_GRASS_BLOCK, drops(SmordsBlocks.MINT_GRASS_BLOCK, Blocks.DIRT));

        addDrop(SmordsBlocks.SPEARMINT_LOG);
        addDrop(SmordsBlocks.SPEARMINT_WOOD);
        addDrop(SmordsBlocks.STRIPPED_SPEARMINT_LOG);
        addDrop(SmordsBlocks.STRIPPED_SPEARMINT_WOOD);
        addDrop(SmordsBlocks.SPEARMINT_LEAVES, leavesDrops(SmordsBlocks.SPEARMINT_LEAVES, SmordsBlocks.SPEARMINT_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(SmordsBlocks.SPEARMINT_SAPLING);

        // 2-3 Spearmint Chunks, or the bulb itself with Silk Touch
        addDrop(SmordsBlocks.SPEARMINT_BULB, dropsWithSilkTouch(SmordsBlocks.SPEARMINT_BULB,
                applyExplosionDecay(SmordsBlocks.SPEARMINT_BULB, ItemEntry.builder(SmordsItems.SPEARMINT_CHUNK)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 3.0f))))));
        addPottedPlantDrops(SmordsBlocks.POTTED_SPEARMINT_SAPLING);
        addDrop(SmordsBlocks.SPEARMINT_PLANKS);
        addDrop(SmordsBlocks.SPEARMINT_STAIRS);
        addDrop(SmordsBlocks.SPEARMINT_SLAB, slabDrops(SmordsBlocks.SPEARMINT_SLAB));
        addDrop(SmordsBlocks.SPEARMINT_FENCE);
        addDrop(SmordsBlocks.SPEARMINT_FENCE_GATE);
        addDrop(SmordsBlocks.SPEARMINT_DOOR, doorDrops(SmordsBlocks.SPEARMINT_DOOR));
        addDrop(SmordsBlocks.SPEARMINT_TRAPDOOR);
        addDrop(SmordsBlocks.SPEARMINT_BUTTON);
        addDrop(SmordsBlocks.SPEARMINT_PRESSURE_PLATE);

        // Breaking the formed refinery gives the plank back
        addDrop(SmordsBlocks.SPEARMINT_REFINERY, SmordsBlocks.SPEARMINT_PLANKS);

        // Wall variants use dropsLike(), so they share these tables
        addDrop(SmordsBlocks.SPEARMINT_SIGN);
        addDrop(SmordsBlocks.SPEARMINT_HANGING_SIGN);
    }
}
