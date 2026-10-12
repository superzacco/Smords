package net.zacco.smords;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.zacco.smords.world.SmordsConfiguredFeatures;
import net.zacco.smords.world.SmordsPlacedFeatures;
import net.zacco.smords.world.biome.SmordsBiomes;
import net.zacco.smords.datagen.*;

public class SmordsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(SmordsModelProvider::new);
		pack.addProvider(SmordsLootTableProvider::new);
		pack.addProvider(SmordsRecipeProvider::new);
		SmordsBlockTagProvider blockTags = pack.addProvider(SmordsBlockTagProvider::new);
		pack.addProvider((output, registries) -> new SmordsItemTagProvider(output, registries, blockTags));
		pack.addProvider(SmordsWorldGenerator::new);
	}

	// Trees are data-driven, so their configured and placed features are generated as JSON too
	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, SmordsConfiguredFeatures::Bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, SmordsPlacedFeatures::Bootstrap);
		registryBuilder.addRegistry(RegistryKeys.BIOME, SmordsBiomes::Bootstrap);
	}
}
