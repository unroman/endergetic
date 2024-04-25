package com.teamabnormals.endergetic.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.endergetic.core.EndergeticExpansion;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class EEBiomeTags {
	public static final TagKey<Biome> HAS_EETLE = biomeTag("has_monster/eetle");
	public static final TagKey<Biome> HAS_EETLE_NEST = biomeTag("has_structure/eetle_nest");
	public static final TagKey<Biome> HAS_CORROCK = biomeTag("has_feature/corrock");
	public static final TagKey<Biome> HAS_SPARSE_CORROCK = biomeTag("has_feature/sparse_corrock");

	private static TagKey<Biome> biomeTag(String name) {
		return TagUtil.biomeTag(EndergeticExpansion.MOD_ID, name);
	}
}
