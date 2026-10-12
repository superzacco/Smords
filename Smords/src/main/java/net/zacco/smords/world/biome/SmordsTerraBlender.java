package net.zacco.smords.world.biome;

import net.minecraft.util.Identifier;
import net.zacco.smords.Smords;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

// TerraBlender calls this on startup (registered as the "terrablender" entrypoint in fabric.mod.json)
public class SmordsTerraBlender implements TerraBlenderApi
{
    // Higher weight = this mod's regions show up more often. Vanilla's is 10
    private static final int REGION_WEIGHT = 3;

    @Override
    public void onTerraBlenderInitialized()
    {
        Regions.register(new SmordsRegion(new Identifier(Smords.MOD_ID, "overworld"), REGION_WEIGHT));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Smords.MOD_ID, SmordsSurfaceRules.MakeRules());
    }
}
