package net.zacco.smords.item;

import net.zacco.smords.Smords;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SmordsItems
{
    public static final Item TEST_INGOT = RegisterItem("test_ingot", new Item(new FabricItemSettings()));
    public static final Item SLUDGE = RegisterItem("sludge", new Item(new FabricItemSettings()));

    private static Item RegisterItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, new Identifier(Smords.MOD_ID, name), item);
    }

    public static void RegisterSmordsItems()
    {
        Smords.LOGGER.info("Registering " + Smords.MOD_ID + " Items"); 
    }
}
