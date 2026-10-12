package net.zacco.smords.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.zacco.smords.block.SmordsBlocks;
import net.zacco.smords.item.SmordsItems;
import net.zacco.smords.util.SmordsTags;

public class SmordsRecipeProvider extends FabricRecipeProvider
{
    public SmordsRecipeProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter)
    {
        // Stairs, slab, fence, gate, door, trapdoor, button, pressure plate, sign
        generateFamily(exporter, SmordsBlocks.SPEARMINT_FAMILY);

        offerPlanksRecipe(exporter, SmordsBlocks.SPEARMINT_PLANKS, SmordsTags.Items.SPEARMINT_LOGS, 4);
        offerBarkBlockRecipe(exporter, SmordsBlocks.SPEARMINT_WOOD, SmordsBlocks.SPEARMINT_LOG);
        offerBarkBlockRecipe(exporter, SmordsBlocks.STRIPPED_SPEARMINT_WOOD, SmordsBlocks.STRIPPED_SPEARMINT_LOG);
        offerHangingSignRecipe(exporter, SmordsItems.SPEARMINT_HANGING_SIGN, SmordsBlocks.STRIPPED_SPEARMINT_LOG);
        offerBoatRecipe(exporter, SmordsItems.SPEARMINT_BOAT, SmordsBlocks.SPEARMINT_PLANKS);
        offerChestBoatRecipe(exporter, SmordsItems.SPEARMINT_CHEST_BOAT, SmordsItems.SPEARMINT_BOAT);
    }
}
