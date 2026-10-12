package net.zacco.smords.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;
import net.zacco.smords.Smords;

public class SmordsSignTypes
{
    // Sounds and door/trapdoor behaviour for the spearmint set (copied from oak)
    public static final BlockSetType SPEARMINT_SET = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(new Identifier(Smords.MOD_ID, "spearmint"));
    public static final WoodType SPEARMINT = WoodTypeBuilder.copyOf(WoodType.OAK).register(new Identifier(Smords.MOD_ID, "spearmint"), SPEARMINT_SET);
}
