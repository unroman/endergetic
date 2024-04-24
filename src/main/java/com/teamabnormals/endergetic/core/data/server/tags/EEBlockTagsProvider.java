package com.teamabnormals.endergetic.core.data.server.tags;

import com.teamabnormals.blueprint.core.data.server.tags.BlueprintBlockTagsProvider;
import com.teamabnormals.endergetic.core.EndergeticExpansion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public final class EEBlockTagsProvider extends BlueprintBlockTagsProvider {

	public EEBlockTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(EndergeticExpansion.MOD_ID, output, provider, helper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
	}
}
