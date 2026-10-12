package net.zacco.smords.world.tree;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.zacco.smords.world.SmordsConfiguredFeatures;

public class SpearmintSaplingGenerator extends LargeTreeSaplingGenerator
{
    // A single sapling grows one of the three smaller variants
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees)
    {
        int roll = random.nextInt(10);
        if (roll < 2)
        {
            return SmordsConfiguredFeatures.SPEARMINT_SHRUB;
        }
        if (roll < 5)
        {
            return SmordsConfiguredFeatures.TALL_SPEARMINT;
        }
        return SmordsConfiguredFeatures.SPEARMINT;
    }

    // Four saplings in a square grow a giant one
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getLargeTreeFeature(Random random)
    {
        return SmordsConfiguredFeatures.GIANT_SPEARMINT;
    }
}
