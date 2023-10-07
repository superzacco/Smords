package net.zacco.smords.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.zacco.smords.Smords;
import net.zacco.smords.block.SmordsBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SmordsItemGroups
{
    public static final ItemGroup SMORDS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Smords.MOD_ID, "smords"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.smords"))
                    .icon(() -> new ItemStack(SmordsBlocks.SPEARMINT_PLANKS)).entries((displayContext, entries) -> {

                        //#region ITEMS
                        //#endregion

                        //#region BLOCKS
                        entries.add(SmordsBlocks.SPEARMINT_PLANKS);
                        entries.add(SmordsBlocks.SPEARMINT_LOG);
                        entries.add(SmordsBlocks.SPEARMINT_WOOD);
                        entries.add(SmordsBlocks.STRIPPED_SPEARMINT_LOG);
                        entries.add(SmordsBlocks.STRIPPED_SPEARMINT_WOOD);
                        entries.add(SmordsBlocks.SPEARMINT_BUTTON);
                        entries.add(SmordsBlocks.SPEARMINT_PRESSURE_PLATE);
                        entries.add(SmordsBlocks.SPEARMINT_FENCE);
                        entries.add(SmordsBlocks.SPEARMINT_FENCE_GATE);

                        //#endregion

                    }).build());

    public static void RegisterSmordsItemGroups()
    {
        Smords.LOGGER.info("Registering Item Groups for " + Smords.MOD_ID);
    }
}