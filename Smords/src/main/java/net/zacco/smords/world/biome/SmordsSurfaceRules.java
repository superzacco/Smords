package net.zacco.smords.world.biome;

import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.zacco.smords.block.SmordsBlocks;

public class SmordsSurfaceRules
{
    // Mint grass on top of the ground in the Spearmint Grove. Anything not covered here falls back to vanilla's rules
    public static MaterialRules.MaterialRule MakeRules()
    {
        MaterialRules.MaterialRule mintGrass = MaterialRules.block(SmordsBlocks.MINT_GRASS_BLOCK.getDefaultState());

        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(SmordsBiomes.SPEARMINT_GROVE),
                        MaterialRules.condition(MaterialRules.surface(),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR,
                                        MaterialRules.condition(MaterialRules.water(-1, 0), mintGrass)))));
    }
}
