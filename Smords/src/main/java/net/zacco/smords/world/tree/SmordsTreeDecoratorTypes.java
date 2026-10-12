package net.zacco.smords.world.tree;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.zacco.smords.Smords;
import net.zacco.smords.mixin.TreeDecoratorTypeInvoker;

public class SmordsTreeDecoratorTypes
{
    public static final TreeDecoratorType<SpearmintBulbTreeDecorator> SPEARMINT_BULB = Registry.register(Registries.TREE_DECORATOR_TYPE,
            new Identifier(Smords.MOD_ID, "spearmint_bulb"), TreeDecoratorTypeInvoker.create(SpearmintBulbTreeDecorator.CODEC));

    public static void RegisterSmordsTreeDecoratorTypes()
    {
        Smords.LOGGER.info("Registering Tree Decorators for " + Smords.MOD_ID);
    }
}
