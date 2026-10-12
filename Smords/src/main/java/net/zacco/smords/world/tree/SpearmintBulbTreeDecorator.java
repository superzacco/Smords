package net.zacco.smords.world.tree;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.zacco.smords.block.SmordsBlocks;

import java.util.ArrayList;
import java.util.List;

// Hangs a few glowing spearmint bulbs from the underside of the leaves
public class SpearmintBulbTreeDecorator extends TreeDecorator
{
    public static final Codec<SpearmintBulbTreeDecorator> CODEC = IntProvider.createValidatingCodec(0, 16)
            .fieldOf("count")
            .xmap(SpearmintBulbTreeDecorator::new, decorator -> decorator.count)
            .codec();

    private final IntProvider count;

    public SpearmintBulbTreeDecorator(IntProvider count)
    {
        this.count = count;
    }

    @Override
    protected TreeDecoratorType<?> getType()
    {
        return SmordsTreeDecoratorTypes.SPEARMINT_BULB;
    }

    @Override
    public void generate(Generator generator)
    {
        Random random = generator.getRandom();
        int toPlace = count.get(random);
        if (toPlace <= 0)
        {
            return;
        }

        // Any open spot directly under a leaf
        List<BlockPos> spots = new ArrayList<>();
        for (BlockPos leaf : generator.getLeavesPositions())
        {
            BlockPos below = leaf.down();
            if (generator.isAir(below))
            {
                spots.add(below);
            }
        }

        for (int i = 0; i < toPlace && !spots.isEmpty(); i++)
        {
            BlockPos spot = spots.remove(random.nextInt(spots.size()));
            generator.replace(spot, SmordsBlocks.SPEARMINT_BULB.getDefaultState());
        }
    }
}
