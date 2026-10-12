package net.zacco.smords.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.zacco.smords.Smords;

public class SmordsTags
{
    public static class Blocks
    {
        public static final TagKey<Block> SPEARMINT_LOGS = TagKey.of(RegistryKeys.BLOCK, new Identifier(Smords.MOD_ID, "spearmint_logs"));
    }

    public static class Items
    {
        public static final TagKey<Item> SPEARMINT_LOGS = TagKey.of(RegistryKeys.ITEM, new Identifier(Smords.MOD_ID, "spearmint_logs"));
    }
}
