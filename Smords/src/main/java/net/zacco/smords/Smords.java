package net.zacco.smords;

import net.fabricmc.api.ModInitializer;
import net.zacco.smords.block.SmordsBlocks;
import net.zacco.smords.item.SmordsItemGroups;
import net.zacco.smords.item.SmordsItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Smords implements ModInitializer {
	
	public static final String MOD_ID = "smords";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		SmordsItemGroups.RegisterSmordsItemGroups();

		SmordsItems.RegisterSmordsItems();
		SmordsBlocks.RegisterSmordsBlocks();
	}
}