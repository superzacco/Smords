package net.zacco.smords;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
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
	}
}
