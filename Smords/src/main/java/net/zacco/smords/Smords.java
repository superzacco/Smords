package net.zacco.smords;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.zacco.smords.block.SmordsBlocks;
import net.zacco.smords.item.SmordsItemGroups;
import net.zacco.smords.item.SmordsItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Smords implements ModInitializer {
	
	public static final String MOD_ID = "smords";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		SmordsItemGroups.RegisterSmordsItemGroups();

		SmordsItems.RegisterSmordsItems();
		SmordsBlocks.RegisterSmordsBlocks();

		RegisterStrippables();
		RegisterFlammables();
	}

	// Right-clicking with an axe
	private static void RegisterStrippables()
	{
		StrippableBlockRegistry.register(SmordsBlocks.SPEARMINT_LOG, SmordsBlocks.STRIPPED_SPEARMINT_LOG);
		StrippableBlockRegistry.register(SmordsBlocks.SPEARMINT_WOOD, SmordsBlocks.STRIPPED_SPEARMINT_WOOD);
	}

	// Same burn/spread values vanilla uses for oak
	private static void RegisterFlammables()
	{
		FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();

		registry.add(SmordsBlocks.SPEARMINT_LOG, 5, 5);
		registry.add(SmordsBlocks.SPEARMINT_WOOD, 5, 5);
		registry.add(SmordsBlocks.STRIPPED_SPEARMINT_LOG, 5, 5);
		registry.add(SmordsBlocks.STRIPPED_SPEARMINT_WOOD, 5, 5);

		registry.add(SmordsBlocks.SPEARMINT_PLANKS, 5, 20);
		registry.add(SmordsBlocks.SPEARMINT_STAIRS, 5, 20);
		registry.add(SmordsBlocks.SPEARMINT_SLAB, 5, 20);
		registry.add(SmordsBlocks.SPEARMINT_FENCE, 5, 20);
		registry.add(SmordsBlocks.SPEARMINT_FENCE_GATE, 5, 20);
	}
}
