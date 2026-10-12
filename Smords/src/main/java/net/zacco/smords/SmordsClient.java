package net.zacco.smords;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.zacco.smords.block.SmordsBlocks;
import net.zacco.smords.item.SmordsItems;

public class SmordsClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        // Doors and trapdoors have see-through holes in their textures
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), SmordsBlocks.SPEARMINT_DOOR, SmordsBlocks.SPEARMINT_TRAPDOOR);

        TerraformBoatClientHelper.registerModelLayers(SmordsItems.SPEARMINT_BOAT_ID, false);
    }
}
