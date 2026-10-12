package net.zacco.smords.world.tree;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.zacco.smords.Smords;
import net.zacco.smords.mixin.FoliagePlacerTypeInvoker;

public class SmordsFoliagePlacerTypes
{
    public static final FoliagePlacerType<SpearmintFoliagePlacer> SPEARMINT_FOLIAGE_PLACER = Registry.register(Registries.FOLIAGE_PLACER_TYPE,
            new Identifier(Smords.MOD_ID, "spearmint_foliage_placer"), FoliagePlacerTypeInvoker.create(SpearmintFoliagePlacer.CODEC));

    public static void RegisterSmordsFoliagePlacerTypes()
    {
        Smords.LOGGER.info("Registering Foliage Placers for " + Smords.MOD_ID);
    }
}
