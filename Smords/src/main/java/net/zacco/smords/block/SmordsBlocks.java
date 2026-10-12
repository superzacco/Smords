package net.zacco.smords.block;

import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.minecraft.block.*;
import net.minecraft.data.family.BlockFamily;
import net.zacco.smords.Smords;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.zacco.smords.entity.SmordsSignTypes;
import net.zacco.smords.world.tree.SpearmintSaplingGenerator;

public class SmordsBlocks
{
    //#region Spearmint
    private static final Identifier SPEARMINT_SIGN_TEXTURE = new Identifier(Smords.MOD_ID, "entity/signs/spearmint");
    private static final Identifier SPEARMINT_HANGING_SIGN_TEXTURE = new Identifier(Smords.MOD_ID, "entity/signs/hanging/spearmint");
    private static final Identifier SPEARMINT_HANGING_SIGN_GUI_TEXTURE = new Identifier(Smords.MOD_ID, "textures/gui/hanging_signs/spearmint");

    public static final Block SPEARMINT_LOG = RegisterBlock("spearmint_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block SPEARMINT_PLANKS = RegisterBlock("spearmint_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block STRIPPED_SPEARMINT_LOG = RegisterBlock("stripped_spearmint_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
    public static final Block SPEARMINT_WOOD = RegisterBlock("spearmint_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_SPEARMINT_WOOD = RegisterBlock("stripped_spearmint_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block SPEARMINT_LEAVES = RegisterBlock("spearmint_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
    public static final Block SPEARMINT_SAPLING = RegisterBlock("spearmint_sapling", new SmordsSaplingBlock(new SpearmintSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
    public static final Block SPEARMINT_BULB = RegisterBlock("spearmint_bulb", new Block(FabricBlockSettings.copyOf(Blocks.SHROOMLIGHT).mapColor(MapColor.EMERALD_GREEN).luminance(13)));
    public static final Block SPEARMINT_REFINERY = RegisterBlockNoItem("spearmint_refinery", new SpearmintRefineryBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block POTTED_SPEARMINT_SAPLING = RegisterBlockNoItem("potted_spearmint_sapling", new FlowerPotBlock(SPEARMINT_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)));
    public static final Block SPEARMINT_STAIRS = RegisterBlock("spearmint_stairs", new StairsBlock(SPEARMINT_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS)));
    public static final Block SPEARMINT_SLAB = RegisterBlock("spearmint_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));
    public static final Block SPEARMINT_BUTTON = RegisterBlock("spearmint_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON), SmordsSignTypes.SPEARMINT_SET, 30, true));
    public static final Block SPEARMINT_PRESSURE_PLATE = RegisterBlock("spearmint_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE), SmordsSignTypes.SPEARMINT_SET));
    public static final Block SPEARMINT_FENCE = RegisterBlock("spearmint_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
    public static final Block SPEARMINT_FENCE_GATE = RegisterBlock("spearmint_fence_gate", new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE), SmordsSignTypes.SPEARMINT));
    public static final Block SPEARMINT_DOOR = RegisterBlock("spearmint_door", new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR), SmordsSignTypes.SPEARMINT_SET));
    public static final Block SPEARMINT_TRAPDOOR = RegisterBlock("spearmint_trapdoor", new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR), SmordsSignTypes.SPEARMINT_SET));

    // Signs get their items in SmordsItems, since one item places both the standing and wall version
    public static final Block SPEARMINT_SIGN = RegisterBlockNoItem("spearmint_sign", new TerraformSignBlock(SPEARMINT_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)));
    public static final Block SPEARMINT_WALL_SIGN = RegisterBlockNoItem("spearmint_wall_sign", new TerraformWallSignBlock(SPEARMINT_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN).dropsLike(SPEARMINT_SIGN)));
    public static final Block SPEARMINT_HANGING_SIGN = RegisterBlockNoItem("spearmint_hanging_sign", new TerraformHangingSignBlock(SPEARMINT_HANGING_SIGN_TEXTURE, SPEARMINT_HANGING_SIGN_GUI_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN)));
    public static final Block SPEARMINT_WALL_HANGING_SIGN = RegisterBlockNoItem("spearmint_wall_hanging_sign", new TerraformWallHangingSignBlock(SPEARMINT_HANGING_SIGN_TEXTURE, SPEARMINT_HANGING_SIGN_GUI_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN).dropsLike(SPEARMINT_HANGING_SIGN)));

    // Used by datagen to generate models and recipes for the whole set at once
    public static final BlockFamily SPEARMINT_FAMILY = new BlockFamily.Builder(SPEARMINT_PLANKS)
            .stairs(SPEARMINT_STAIRS)
            .slab(SPEARMINT_SLAB)
            .fence(SPEARMINT_FENCE)
            .fenceGate(SPEARMINT_FENCE_GATE)
            .door(SPEARMINT_DOOR)
            .trapdoor(SPEARMINT_TRAPDOOR)
            .button(SPEARMINT_BUTTON)
            .pressurePlate(SPEARMINT_PRESSURE_PLATE)
            .sign(SPEARMINT_SIGN, SPEARMINT_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
    //#endregion

    private static Block RegisterBlock(String name, Block block)
    {
        RegisterBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Smords.MOD_ID, name), block);
    }

    private static Item RegisterBlockItem(String name, Block block)
    {
        return Registry.register(Registries.ITEM, new Identifier(Smords.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    private static Block RegisterBlockNoItem(String name, Block block)
    {
        return Registry.register(Registries.BLOCK, new Identifier(Smords.MOD_ID, name), block);
    }

    public static void RegisterSmordsBlocks()
    {
        Smords.LOGGER.info("Registering " + Smords.MOD_ID + " Blocks");
    }
}
