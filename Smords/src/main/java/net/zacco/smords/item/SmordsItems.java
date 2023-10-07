package net.zacco.smords.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Items;
import net.zacco.smords.Smords;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.zacco.smords.block.SmordsBlocks;

public class SmordsItems
{
    public static final Item SPEARMINT_HANGING_SIGN = RegisterItem("spearmint_hanging_sign", new HangingSignItem(SmordsBlocks.SPEARMINT_HANGING_SIGN, SmordsBlocks.SPEARMINT_WALL_HANGING_SIGN, new FabricItemSettings().maxCount(16)));

    private static Item RegisterItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, new Identifier(Smords.MOD_ID, name), item);
    }

    public static void RegisterSmordsItems()
    {
        Smords.LOGGER.info("Registering " + Smords.MOD_ID + " Items"); 
    }
}
