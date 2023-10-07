package net.zacco.smords.block;

import net.minecraft.block.*;
import net.zacco.smords.Smords;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class SmordsBlocks
{


    //#region Spearmint
    public static final Block SPEARMINT_LOG = RegisterBlock("spearmint_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block SPEARMINT_PLANKS = RegisterBlock("spearmint_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block STRIPPED_SPEARMINT_LOG = RegisterBlock("stripped_spearmint_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
    public static final Block SPEARMINT_WOOD = RegisterBlock("spearmint_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_SPEARMINT_WOOD = RegisterBlock("stripped_spearmint_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block SPEARMINT_BUTTON = RegisterBlock("spearmint_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON), BlockSetType.OAK, 30, true));
    public static final Block SPEARMINT_PRESSURE_PLATE = RegisterBlock("spearmint_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final Block SPEARMINT_FENCE = RegisterBlock("spearmint_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
    public static final Block SPEARMINT_FENCE_GATE = RegisterBlock("spearmint_fence_gate", new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE), WoodType.OAK));
    public static final Block SPEARMINT_HANGING_SIGN = RegisterBlock("spearmint_hanging_sign", new HangingSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN), WoodType.OAK));
    public static final Block SPEARMINT_WALL_HANGING_SIGN = RegisterBlock("spearmint_wall_hanging_sign", new WallHangingSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN), WoodType.OAK));
    //#endregion

    private static Block RegisterBlock(String name, Block block)
    {
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
