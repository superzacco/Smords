package net.zacco.smords.screen;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.zacco.smords.Smords;

public class SmordsScreenHandlers
{
    public static final ScreenHandlerType<SpearmintRefineryScreenHandler> SPEARMINT_REFINERY = Registry.register(Registries.SCREEN_HANDLER,
            new Identifier(Smords.MOD_ID, "spearmint_refinery"),
            new ScreenHandlerType<>(SpearmintRefineryScreenHandler::new, FeatureFlags.VANILLA_FEATURES));

    public static void RegisterSmordsScreenHandlers()
    {
        Smords.LOGGER.info("Registering Screen Handlers for " + Smords.MOD_ID);
    }
}
