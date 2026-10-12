package net.zacco.smords.world.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

// A TerraBlender region is a copy of the vanilla overworld layout with some biomes swapped out
public class SmordsRegion extends Region
{
    public SmordsRegion(Identifier name, int weight)
    {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper)
    {
        addModifiedVanillaOverworldBiomes(mapper, builder ->
        {
            // In this mod's regions, forests become Spearmint Groves
            builder.replaceBiome(BiomeKeys.FOREST, SmordsBiomes.SPEARMINT_GROVE);
        });
    }
}
