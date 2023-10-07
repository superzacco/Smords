package net.zacco.smords.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.render.block.entity.PistonBlockEntityRenderer;
import net.zacco.smords.Smords;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import javax.swing.plaf.synth.Region;

public class SmordsBlocks
{


    //#region Spearmint
    public static final Block SPEARMINT_LOG = RegisterBlock("spearmint_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block SPEARMINT_PLANKS = RegisterBlock("spearmint_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block STRIPPED_SPEARMINT_LOG = RegisterBlock("stripped_spearmint_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
    public static final Block SPEARMINT_WOOD = RegisterBlock("spearmint_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_SPEARMINT_WOOD = RegisterBlock("stripped_spearmint_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block SPEARMINT_BUTTON = RegisterBlock("spearmint_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON), BlockSetType.OAK, 30, true));
    public static final Block STRIPPED_PRESSURE_PLATE = RegisterBlock("spearmint_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    //#endregion

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
