package net.zacco.smords.item;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.SignItem;
import net.minecraft.registry.RegistryKey;
import net.zacco.smords.Smords;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.zacco.smords.block.SmordsBlocks;

public class SmordsItems
{
    //#region Spearmint
    public static final Item SPEARMINT = RegisterItem("spearmint", new Item(new FabricItemSettings()));
    public static final Item SPEARMINT_EXTRACT = RegisterItem("spearmint_extract", new Item(new FabricItemSettings().maxCount(16)));

    public static final Identifier SPEARMINT_BOAT_ID = new Identifier(Smords.MOD_ID, "spearmint");
    public static final RegistryKey<TerraformBoatType> SPEARMINT_BOAT_KEY = TerraformBoatTypeRegistry.createKey(SPEARMINT_BOAT_ID);

    public static final Item SPEARMINT_SIGN = RegisterItem("spearmint_sign", new SignItem(new FabricItemSettings().maxCount(16), SmordsBlocks.SPEARMINT_SIGN, SmordsBlocks.SPEARMINT_WALL_SIGN));
    public static final Item SPEARMINT_HANGING_SIGN = RegisterItem("spearmint_hanging_sign", new HangingSignItem(SmordsBlocks.SPEARMINT_HANGING_SIGN, SmordsBlocks.SPEARMINT_WALL_HANGING_SIGN, new FabricItemSettings().maxCount(16)));
    public static final Item SPEARMINT_BOAT = TerraformBoatItemHelper.registerBoatItem(new Identifier(Smords.MOD_ID, "spearmint_boat"), SPEARMINT_BOAT_KEY, false);
    public static final Item SPEARMINT_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(new Identifier(Smords.MOD_ID, "spearmint_chest_boat"), SPEARMINT_BOAT_KEY, true);
    //#endregion

    private static Item RegisterItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, new Identifier(Smords.MOD_ID, name), item);
    }

    public static void RegisterSmordsItems()
    {
        Smords.LOGGER.info("Registering " + Smords.MOD_ID + " Items");

        TerraformBoatType spearmintBoat = new TerraformBoatType.Builder()
                .item(SPEARMINT_BOAT)
                .chestItem(SPEARMINT_CHEST_BOAT)
                .planks(SmordsBlocks.SPEARMINT_PLANKS.asItem())
                .build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, SPEARMINT_BOAT_KEY, spearmintBoat);
    }
}
