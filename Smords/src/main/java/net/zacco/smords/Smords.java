package net.zacco.smords;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.HoeItem;
import net.zacco.smords.block.SmordsBlocks;
import net.zacco.smords.block.entity.SmordsBlockEntities;
import net.zacco.smords.screen.SmordsScreenHandlers;
import net.zacco.smords.item.SmordsItemGroups;
import net.zacco.smords.item.SmordsItems;
import net.zacco.smords.multiblock.SpearmintRefinery;
import net.zacco.smords.world.tree.SmordsFoliagePlacerTypes;
import net.zacco.smords.world.tree.SmordsTreeDecoratorTypes;

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
		SmordsBlockEntities.RegisterSmordsBlockEntities();
		SmordsScreenHandlers.RegisterSmordsScreenHandlers();
		SmordsFoliagePlacerTypes.RegisterSmordsFoliagePlacerTypes();
		SmordsTreeDecoratorTypes.RegisterSmordsTreeDecoratorTypes();

		RegisterStrippables();
		RegisterFlammables();
		RegisterCompostables();

		SpearmintRefinery.RegisterSpearmintRefinery();
	}

	// Right-clicking with an axe, hoe or shovel
	private static void RegisterStrippables()
	{
		TillableBlockRegistry.register(SmordsBlocks.MINT_GRASS_BLOCK, HoeItem::canTillFarmland, Blocks.FARMLAND.getDefaultState());
		FlattenableBlockRegistry.register(SmordsBlocks.MINT_GRASS_BLOCK, Blocks.DIRT_PATH.getDefaultState());

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

		registry.add(SmordsBlocks.SPEARMINT_LEAVES, 30, 60);
	}

	private static void RegisterCompostables()
	{
		CompostingChanceRegistry.INSTANCE.add(SmordsBlocks.SPEARMINT_LEAVES, 0.3f);
		CompostingChanceRegistry.INSTANCE.add(SmordsBlocks.SPEARMINT_SAPLING, 0.3f);
	}
}
