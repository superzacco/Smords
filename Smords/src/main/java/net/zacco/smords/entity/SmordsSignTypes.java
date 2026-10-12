package net.zacco.smords.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;
import net.zacco.smords.Smords;

public class SmordsSignTypes
{
    public static final WoodType SPEARMINT = WoodTypeBuilder.copyOf(WoodType.OAK).register(new Identifier(Smords.MOD_ID, "spearmint"), BlockSetType.OAK);
}
