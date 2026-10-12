package net.zacco.smords.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.zacco.smords.block.SmordsBlocks;

public class SmordsLootTableProvider extends FabricBlockLootTableProvider
{
    public SmordsLootTableProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generate()
    {
        addDrop(SmordsBlocks.SPEARMINT_LOG);
        addDrop(SmordsBlocks.SPEARMINT_WOOD);
        addDrop(SmordsBlocks.STRIPPED_SPEARMINT_LOG);
        addDrop(SmordsBlocks.STRIPPED_SPEARMINT_WOOD);
        addDrop(SmordsBlocks.SPEARMINT_PLANKS);
        addDrop(SmordsBlocks.SPEARMINT_STAIRS);
        addDrop(SmordsBlocks.SPEARMINT_SLAB, slabDrops(SmordsBlocks.SPEARMINT_SLAB));
        addDrop(SmordsBlocks.SPEARMINT_FENCE);
        addDrop(SmordsBlocks.SPEARMINT_FENCE_GATE);
        addDrop(SmordsBlocks.SPEARMINT_DOOR, doorDrops(SmordsBlocks.SPEARMINT_DOOR));
        addDrop(SmordsBlocks.SPEARMINT_TRAPDOOR);
        addDrop(SmordsBlocks.SPEARMINT_BUTTON);
        addDrop(SmordsBlocks.SPEARMINT_PRESSURE_PLATE);

        // Wall variants use dropsLike(), so they share these tables
        addDrop(SmordsBlocks.SPEARMINT_SIGN);
        addDrop(SmordsBlocks.SPEARMINT_HANGING_SIGN);
    }
}
