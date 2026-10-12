package net.zacco.smords.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class SmordsWorldGeneration
{
    public static void GenerateSmordsWorldGen()
    {
        // Rare spearmint trees in forests until the mint biome exists
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.BIRCH_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                SmordsPlacedFeatures.SPEARMINT_TREES_PLACED);
    }
}
