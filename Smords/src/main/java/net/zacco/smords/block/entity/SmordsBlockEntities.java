package net.zacco.smords.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.zacco.smords.Smords;
import net.zacco.smords.block.SmordsBlocks;

public class SmordsBlockEntities
{
    public static final BlockEntityType<SpearmintRefineryBlockEntity> SPEARMINT_REFINERY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(Smords.MOD_ID, "spearmint_refinery"),
            FabricBlockEntityTypeBuilder.create(SpearmintRefineryBlockEntity::new, SmordsBlocks.SPEARMINT_REFINERY).build());

    public static void RegisterSmordsBlockEntities()
    {
        Smords.LOGGER.info("Registering Block Entities for " + Smords.MOD_ID);
    }
}
