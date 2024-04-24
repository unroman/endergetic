package com.teamabnormals.endergetic.core.data.server;

import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.boatload.core.data.server.BoatloadRecipeProvider;
import com.teamabnormals.endergetic.core.EndergeticExpansion;
import com.teamabnormals.endergetic.integration.boatload.EEBoatTypes;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class EERecipeProvider extends BlueprintRecipeProvider {

	public EERecipeProvider(PackOutput output) {
		super(EndergeticExpansion.MOD_ID, output);
	}

	@Override
	public void buildRecipes(Consumer<FinishedRecipe> consumer) {
		BoatloadRecipeProvider.boatRecipes(consumer, EEBoatTypes.POISE);
	}
}