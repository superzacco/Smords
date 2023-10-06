package net.zacco.smords.block;

import net.zacco.smords.Smords;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class SmordsBlocks
{
    public static final Block SPEARMINT_PLANKS = RegisterBlock("spearmint_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));

    private static Block RegisterBlock(String name, Block block)
    {
        RegisterBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Smords.MOD_ID, name), block);
    
    }

    private static Item RegisterBlockItem(String name, Block block)
    {
        return Registry.register(Registries.ITEM, new Identifier(Smords.MOD_ID, name),
        new BlockItem(block, new FabricItemSettings()));
    }

    public static void RegisterSmordsBlocks()
    {
        Smords.LOGGER.info("Registering " + Smords.MOD_ID + " Blocks");
    }
}
