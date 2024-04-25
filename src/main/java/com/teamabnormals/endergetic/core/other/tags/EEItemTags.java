package com.teamabnormals.endergetic.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.endergetic.core.EndergeticExpansion;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class EEItemTags {
	public static final TagKey<Item> POISE_STEMS = itemTag("poise_stems");
	public static final TagKey<Item> ENDER_FIRE_BASE_BLOCKS = itemTag("ender_fire_base_blocks");

	private static TagKey<Item> itemTag(String name) {
		return TagUtil.itemTag(EndergeticExpansion.MOD_ID, name);
	}
}