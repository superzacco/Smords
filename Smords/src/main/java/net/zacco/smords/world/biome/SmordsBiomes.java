package net.zacco.smords.world.biome;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.zacco.smords.Smords;
import net.zacco.smords.world.SmordsPlacedFeatures;

public class SmordsBiomes
{
    public static final RegistryKey<Biome> SPEARMINT_GROVE = RegistryKey.of(RegistryKeys.BIOME, new Identifier(Smords.MOD_ID, "spearmint_grove"));

    public static void Bootstrap(Registerable<Biome> context)
    {
        context.register(SPEARMINT_GROVE, SpearmintGrove(context));
    }

    private static Biome SpearmintGrove(Registerable<Biome> context)
    {
        SpawnSettings.Builder spawns = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawns);
        spawns.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3));
        DefaultBiomeFeatures.addBatsAndMonsters(spawns);

        GenerationSettings.LookupBackedBuilder generation = new GenerationSettings.LookupBackedBuilder(
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        // Same order as a vanilla forest. Minecraft requires features to be in a consistent order across biomes
        DefaultBiomeFeatures.addLandCarvers(generation);
        DefaultBiomeFeatures.addAmethystGeodes(generation);
        DefaultBiomeFeatures.addDungeons(generation);
        DefaultBiomeFeatures.addMineables(generation);
        DefaultBiomeFeatures.addSprings(generation);
        DefaultBiomeFeatures.addFrozenTopLayer(generation);
        DefaultBiomeFeatures.addDefaultOres(generation);
        DefaultBiomeFeatures.addDefaultDisks(generation);

        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, SmordsPlacedFeatures.SPEARMINT_TREES_PLACED);
        DefaultBiomeFeatures.addDefaultFlowers(generation);
        DefaultBiomeFeatures.addForestGrass(generation);
        DefaultBiomeFeatures.addDefaultMushrooms(generation);
        DefaultBiomeFeatures.addDefaultVegetation(generation);

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.6f)
                .downfall(0.8f)
                .effects(new BiomeEffects.Builder()
                        // Tints vanilla grass, ferns and vines mint green
                        .grassColor(0x7ED9A8)
                        .foliageColor(0x5FC79A)
                        .waterColor(0x4FD6C0)
                        .waterFogColor(0x0B3F38)
                        .skyColor(0x8CCFE8)
                        .fogColor(0xC8F2E4)
                        .moodSound(BiomeMoodSound.CAVE)
                        .build())
                .spawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build();
    }
}
