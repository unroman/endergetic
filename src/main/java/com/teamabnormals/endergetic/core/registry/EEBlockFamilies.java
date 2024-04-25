package com.teamabnormals.endergetic.core.registry;

import net.minecraft.data.BlockFamily;

import static com.teamabnormals.endergetic.core.registry.EEBlocks.*;

public class EEBlockFamilies {
	public static final BlockFamily POISE_PLANKS_FAMILY = new BlockFamily.Builder(POISE_PLANKS.get()).button(POISE_BUTTON.get()).fence(POISE_FENCE.get()).fenceGate(POISE_FENCE_GATE.get()).pressurePlate(POISE_PRESSURE_PLATE.get()).sign(POISE_SIGNS.getFirst().get(), POISE_SIGNS.getSecond().get()).slab(POISE_SLAB.get()).stairs(POISE_STAIRS.get()).door(POISE_DOOR.get()).trapdoor(POISE_TRAPDOOR.get()).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
}
