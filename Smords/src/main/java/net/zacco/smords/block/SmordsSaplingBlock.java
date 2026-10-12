package net.zacco.smords.block;

import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;

// SaplingBlock's constructor is protected
public class SmordsSaplingBlock extends SaplingBlock
{
    public SmordsSaplingBlock(SaplingGenerator generator, Settings settings)
    {
        super(generator, settings);
    }
}
