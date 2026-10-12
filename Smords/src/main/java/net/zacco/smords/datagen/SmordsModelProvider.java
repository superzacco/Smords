package net.zacco.smords.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.zacco.smords.block.SmordsBlocks;
import net.zacco.smords.item.SmordsItems;

public class SmordsModelProvider extends FabricModelProvider
{
    public SmordsModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator)
    {
        generator.registerLog(SmordsBlocks.SPEARMINT_LOG).log(SmordsBlocks.SPEARMINT_LOG).wood(SmordsBlocks.SPEARMINT_WOOD);
        generator.registerLog(SmordsBlocks.STRIPPED_SPEARMINT_LOG).log(SmordsBlocks.STRIPPED_SPEARMINT_LOG).wood(SmordsBlocks.STRIPPED_SPEARMINT_WOOD);

        // Planks, stairs, slab, fence, gate, door, trapdoor, button, pressure plate, sign
        generator.registerCubeAllModelTexturePool(SmordsBlocks.SPEARMINT_PLANKS).family(SmordsBlocks.SPEARMINT_FAMILY);

        generator.registerSimpleCubeAll(SmordsBlocks.SPEARMINT_LEAVES);
        generator.registerFlowerPotPlant(SmordsBlocks.SPEARMINT_SAPLING, SmordsBlocks.POTTED_SPEARMINT_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        generator.registerHangingSign(SmordsBlocks.STRIPPED_SPEARMINT_LOG, SmordsBlocks.SPEARMINT_HANGING_SIGN, SmordsBlocks.SPEARMINT_WALL_HANGING_SIGN);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator)
    {
        generator.register(SmordsItems.SPEARMINT_BOAT, Models.GENERATED);
        generator.register(SmordsItems.SPEARMINT_CHEST_BOAT, Models.GENERATED);
    }
}
