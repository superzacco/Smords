package net.zacco.smords.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.FeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.zacco.smords.Smords;
import net.zacco.smords.block.SmordsBlocks;
import net.zacco.smords.world.tree.SpearmintFoliagePlacer;

import java.util.List;

public class SmordsConfiguredFeatures
{
    //#region Spearmint Trees
    public static final RegistryKey<ConfiguredFeature<?, ?>> SPEARMINT = RegisterKey("spearmint");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TALL_SPEARMINT = RegisterKey("tall_spearmint");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SPEARMINT_SHRUB = RegisterKey("spearmint_shrub");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_SPEARMINT = RegisterKey("giant_spearmint");

    // Picks one of the variants above, used for world generation
    public static final RegistryKey<ConfiguredFeature<?, ?>> SPEARMINT_TREES = RegisterKey("spearmint_trees");
    //#endregion

    public static void Bootstrap(Registerable<ConfiguredFeature<?, ?>> context)
    {
        // Regular: 5-7 tall, three or so tiers
        Register(context, SPEARMINT, Feature.TREE, SpearmintTree(
                new StraightTrunkPlacer(5, 2, 0), 3, UniformIntProvider.create(4, 6), 2,
                new TwoLayersFeatureSize(1, 0, 1)));

        // Tall: a thin spire with tiers spread further apart
        Register(context, TALL_SPEARMINT, Feature.TREE, SpearmintTree(
                new StraightTrunkPlacer(9, 3, 1), 3, UniformIntProvider.create(7, 10), 3,
                new TwoLayersFeatureSize(1, 0, 1)));

        // Shrub: short trunk with one wide skirt of leaves near the ground
        Register(context, SPEARMINT_SHRUB, Feature.TREE, SpearmintTree(
                new StraightTrunkPlacer(3, 1, 0), 3, ConstantIntProvider.create(2), 2,
                new TwoLayersFeatureSize(1, 0, 1)));

        // Giant: 2x2 trunk grown from four saplings, wide tiers
        Register(context, GIANT_SPEARMINT, Feature.TREE, SpearmintTree(
                new GiantTrunkPlacer(13, 2, 4), 5, UniformIntProvider.create(13, 17), 3,
                new TwoLayersFeatureSize(1, 1, 2)));

        RegistryEntryLookup<PlacedFeature> placed = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        Register(context, SPEARMINT_TREES, Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(
                new RandomFeatureEntry(placed.getOrThrow(SmordsPlacedFeatures.TALL_SPEARMINT_CHECKED), 0.25f),
                new RandomFeatureEntry(placed.getOrThrow(SmordsPlacedFeatures.SPEARMINT_SHRUB_CHECKED), 0.2f),
                new RandomFeatureEntry(placed.getOrThrow(SmordsPlacedFeatures.GIANT_SPEARMINT_CHECKED), 0.05f)),
                placed.getOrThrow(SmordsPlacedFeatures.SPEARMINT_CHECKED)));
    }

    private static TreeFeatureConfig SpearmintTree(TrunkPlacer trunk, int radius, IntProvider foliageHeight, int tierSpacing, FeatureSize size)
    {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(SmordsBlocks.SPEARMINT_LOG),
                trunk,
                BlockStateProvider.of(SmordsBlocks.SPEARMINT_LEAVES),
                new SpearmintFoliagePlacer(ConstantIntProvider.create(radius), ConstantIntProvider.create(0), foliageHeight, tierSpacing),
                size)
                .ignoreVines()
                .build();
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> RegisterKey(String name)
    {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Smords.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void Register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config)
    {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
