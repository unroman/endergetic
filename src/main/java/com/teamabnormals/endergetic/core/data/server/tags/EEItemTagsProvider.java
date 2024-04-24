package com.teamabnormals.endergetic.core.data.server.tags;

import com.teamabnormals.blueprint.core.data.server.tags.BlueprintItemTagsProvider;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.endergetic.core.EndergeticExpansion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.endergetic.core.registry.EEItems.*;

public final class EEItemTagsProvider extends BlueprintItemTagsProvider {

	public EEItemTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> tagLookup, ExistingFileHelper helper) {
		super(EndergeticExpansion.MOD_ID, output, lookupProvider, tagLookup, helper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(ItemTags.BOATS).add(POISE_BOAT.getFirst().get());
		this.tag(ItemTags.CHEST_BOATS).add(POISE_BOAT.getSecond().get());
		this.tag(BlueprintItemTags.FURNACE_BOATS).add(POISE_FURNACE_BOAT.get());
		this.tag(BlueprintItemTags.LARGE_BOATS).add(LARGE_POISE_BOAT.get());
	}
}
