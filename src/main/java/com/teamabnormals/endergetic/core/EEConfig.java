package com.teamabnormals.endergetic.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author SmellyModder(Luke Tonon)
 */
public final class EEConfig {

	public static class Common {
		public final ConfigValue<Boolean> debugDragonFightManager;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("debug");
			debugDragonFightManager = builder.comment("If the Dragon Fight Manager should debug its portal values").define("Debug Dragon Fight Manager", false);
			builder.pop();
		}
	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}