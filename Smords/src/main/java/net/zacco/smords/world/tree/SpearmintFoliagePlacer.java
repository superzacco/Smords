package net.zacco.smords.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

/**
 * Spearmint foliage grows in tiers down the trunk, like the leaf pairs on a mint stem.
 * Each tier is a long, narrow pair of leaves turned 90 degrees from the tier above it,
 * with drooping tips. Tiers get wider towards the bottom, and the gaps between them leave
 * the trunk showing. The top of the tree ends in a small tuft.
 */
public class SpearmintFoliagePlacer extends FoliagePlacer
{
    public static final Codec<SpearmintFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            fillFoliagePlacerFields(instance)
                    .and(IntProvider.createValidatingCodec(1, 32).fieldOf("height").forGetter(placer -> placer.height))
                    .and(Codec.intRange(1, 6).fieldOf("tier_spacing").forGetter(placer -> placer.tierSpacing))
                    .apply(instance, SpearmintFoliagePlacer::new));

    // How far down the trunk the tiers reach
    private final IntProvider height;
    // Blocks between each tier
    private final int tierSpacing;

    public SpearmintFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height, int tierSpacing)
    {
        super(radius, offset);
        this.height = height;
        this.tierSpacing = tierSpacing;
    }

    @Override
    protected FoliagePlacerType<?> getType()
    {
        return SmordsFoliagePlacerTypes.SPEARMINT_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset)
    {
        // The node sits just above the top log
        BlockPos top = treeNode.getCenter().up(offset);
        boolean giant = treeNode.isGiantTrunk();

        // Tuft on top
        GenerateLeafPair(world, placer, random, config, top, 1, 1, true, giant, false);
        GenerateLeafPair(world, placer, random, config, top.up(), 0, 0, true, giant, false);

        boolean alongX = random.nextBoolean();
        int tier = 0;
        for (int y = -tierSpacing; y >= -foliageHeight; y -= tierSpacing)
        {
            // Giant trunks are twice as thick, so their tiers start out bigger
            int length = Math.min(radius, (giant ? 3 : 2) + tier);
            int width = Math.max(1, (length + 1) / 2);

            GenerateLeafPair(world, placer, random, config, top.up(y), length, width, alongX, giant, true);

            // Big tiers with room above them get a mound in the middle so they aren't paper thin
            if (tierSpacing >= 3 && length >= 4)
            {
                GenerateLeafPair(world, placer, random, config, top.up(y + 1), length - 2, Math.max(1, width - 1), alongX, giant, false);
            }

            alongX = !alongX;
            tier++;
        }
    }

    // An oval of leaves around the trunk, stretched along one axis
    private void GenerateLeafPair(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos center, int length, int width, boolean alongX, boolean giant, boolean droop)
    {
        // Giant trunks are 2x2, so the shape is stretched by one block on the positive side
        int g = giant ? 1 : 0;
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int dx = -length; dx <= length + g; dx++)
        {
            for (int dz = -length; dz <= length + g; dz++)
            {
                int ax = giant && dx > 0 ? dx - 1 : Math.abs(dx);
                int az = giant && dz > 0 ? dz - 1 : Math.abs(dz);
                int along = alongX ? ax : az;
                int across = alongX ? az : ax;

                double a = along / (length + 0.5);
                double b = across / (width + 0.5);
                double edge = a * a + b * b;
                if (edge > 1.0)
                {
                    continue;
                }

                // Ragged edges, but always keep the tips
                if (edge > 0.65 && along < length && random.nextFloat() < 0.3f)
                {
                    continue;
                }

                pos.set(center, dx, 0, dz);
                placeFoliageBlock(world, placer, random, config, pos);

                // Leaf tips droop down a block
                if (droop && length >= 3 && along == length && across == 0)
                {
                    pos.move(0, -1, 0);
                    placeFoliageBlock(world, placer, random, config, pos);
                }
            }
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config)
    {
        // Keep the lowest tier at least one block off the ground
        return Math.min(height.get(random), trunkHeight - 1);
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk)
    {
        // Unused, the shape is handled in GenerateLeafPair
        return false;
    }
}
