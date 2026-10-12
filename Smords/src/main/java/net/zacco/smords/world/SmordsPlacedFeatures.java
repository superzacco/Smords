package net.zacco.smords.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.zacco.smords.Smords;
import net.zacco.smords.block.SmordsBlocks;

import java.util.List;

public class SmordsPlacedFeatures
{
    // "Checked" versions only generate where a sapling could survive
    public static final RegistryKey<PlacedFeature> SPEARMINT_CHECKED = RegisterKey("spearmint_checked");
    public static final RegistryKey<PlacedFeature> TALL_SPEARMINT_CHECKED = RegisterKey("tall_spearmint_checked");
    public static final RegistryKey<PlacedFeature> SPEARMINT_SHRUB_CHECKED = RegisterKey("spearmint_shrub_checked");
    public static final RegistryKey<PlacedFeature> GIANT_SPEARMINT_CHECKED = RegisterKey("giant_spearmint_checked");

    // Spearmint Grove trees
    public static final RegistryKey<PlacedFeature> SPEARMINT_TREES_PLACED = RegisterKey("spearmint_trees_placed");

    public static void Bootstrap(Registerable<PlacedFeature> context)
    {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> configured = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        Register(context, SPEARMINT_CHECKED, configured.getOrThrow(SmordsConfiguredFeatures.SPEARMINT), List.of(PlacedFeatures.wouldSurvive(SmordsBlocks.SPEARMINT_SAPLING)));
        Register(context, TALL_SPEARMINT_CHECKED, configured.getOrThrow(SmordsConfiguredFeatures.TALL_SPEARMINT), List.of(PlacedFeatures.wouldSurvive(SmordsBlocks.SPEARMINT_SAPLING)));
        Register(context, SPEARMINT_SHRUB_CHECKED, configured.getOrThrow(SmordsConfiguredFeatures.SPEARMINT_SHRUB), List.of(PlacedFeatures.wouldSurvive(SmordsBlocks.SPEARMINT_SAPLING)));
        Register(context, GIANT_SPEARMINT_CHECKED, configured.getOrThrow(SmordsConfiguredFeatures.GIANT_SPEARMINT), List.of(PlacedFeatures.wouldSurvive(SmordsBlocks.SPEARMINT_SAPLING)));

        // 6 trees per chunk, with a 10% chance of 1 extra
        Register(context, SPEARMINT_TREES_PLACED, configured.getOrThrow(SmordsConfiguredFeatures.SPEARMINT_TREES),
                VegetationPlacedFeatures.treeModifiers(PlacedFeatures.createCountExtraModifier(6, 0.1f, 1)));
    }

    public static RegistryKey<PlacedFeature> RegisterKey(String name)
    {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Smords.MOD_ID, name));
    }

    private static void Register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, net.minecraft.registry.entry.RegistryEntry<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(feature, List.copyOf(modifiers)));
    }
}
