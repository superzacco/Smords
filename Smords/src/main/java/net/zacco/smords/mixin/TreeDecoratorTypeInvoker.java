package net.zacco.smords.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

// TreeDecoratorType's constructor is private, so custom tree decorators need this to register
@Mixin(TreeDecoratorType.class)
public interface TreeDecoratorTypeInvoker {
    @Invoker("<init>")
    static <P extends TreeDecorator> TreeDecoratorType<P> create(Codec<P> codec) {
        throw new AssertionError();
    }
}
