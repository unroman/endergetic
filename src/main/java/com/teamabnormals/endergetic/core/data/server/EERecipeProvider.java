package com.teamabnormals.endergetic.core.data.server;

import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.boatload.core.data.server.BoatloadRecipeProvider;
import com.teamabnormals.endergetic.core.EndergeticExpansion;
import com.teamabnormals.endergetic.core.registry.EEBlockFamilies;
import com.teamabnormals.endergetic.core.registry.other.tags.EEItemTags;
import com.teamabnormals.endergetic.integration.boatload.EEBoatTypes;
import com.teamabnormals.woodworks.core.data.server.WoodworksRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.teamabnormals.endergetic.core.registry.EEBlocks.*;

public class EERecipeProvider extends BlueprintRecipeProvider {

	public EERecipeProvider(PackOutput output) {
		super(EndergeticExpansion.MOD_ID, output);
	}

	@Override
	public void buildRecipes(Consumer<FinishedRecipe> consumer) {
		generateRecipes(consumer, EEBlockFamilies.POISE_PLANKS_FAMILY);
		planksFromLogs(consumer, POISE_PLANKS.get(), EEItemTags.POISE_STEMS, 4);
		woodFromLogs(consumer, POISE_WOOD.get(), POISE_STEM.get());
		woodFromLogs(consumer, STRIPPED_POISE_WOOD.get(), STRIPPED_POISE_STEM.get());
		woodFromLogs(consumer, GLOWING_POISE_WOOD.get(), GLOWING_POISE_STEM.get());
		// hangingSign(consumer, POISE_HANGING_SIGNS.getFirst().get(), STRIPPED_POISE_STEM.get());

		BoatloadRecipeProvider.boatRecipes(consumer, EEBoatTypes.POISE);
		WoodworksRecipeProvider.baseRecipes(consumer, POISE_PLANKS.get(), POISE_SLAB.get(), POISE_BOARDS.get(), POISE_BOOKSHELF.get(), Blocks.CHISELED_BOOKSHELF, POISE_LADDER.get(), POISE_BEEHIVE.get(), POISE_CHEST.get(), TRAPPED_POISE_CHEST.get(), EndergeticExpansion.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(consumer, EEBlockFamilies.POISE_PLANKS_FAMILY, EEItemTags.POISE_STEMS, POISE_BOARDS.get(), POISE_LADDER.get(), EndergeticExpansion.MOD_ID);

	}
}