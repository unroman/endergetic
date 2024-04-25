package com.teamabnormals.endergetic.core.data.server;

import com.teamabnormals.endergetic.common.advancement.EECriteriaTriggers;
import com.teamabnormals.endergetic.core.EndergeticExpansion;
import com.teamabnormals.endergetic.core.registry.EEBlocks;
import com.teamabnormals.endergetic.core.registry.EEEntityTypes;
import com.teamabnormals.endergetic.core.registry.EEItems;
import com.teamabnormals.endergetic.core.registry.EEStructureTypes.EEStructures;
import com.teamabnormals.endergetic.core.registry.builtin.EEBiomes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.common.data.ForgeAdvancementProvider.AdvancementGenerator;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class EEAdvancementProvider implements AdvancementGenerator {

	public static ForgeAdvancementProvider create(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		return new ForgeAdvancementProvider(output, provider, helper, List.of(new EEAdvancementProvider()));
	}

	@Override
	public void generate(Provider provider, Consumer<Advancement> consumer, ExistingFileHelper helper) {
		Advancement poiseForest = createAdvancement("find_poise_forest", "end", new ResourceLocation("end/enter_end_gateway"), EEBlocks.POISMOSS.get(), FrameType.TASK, true, true, false)
				.addCriterion("in_poise_forest", PlayerTrigger.TriggerInstance.located(LocationPredicate.inBiome(EEBiomes.POISE_FOREST)))
				.save(consumer, EndergeticExpansion.MOD_ID + ":end/find_poise_forest");

		Advancement eatBolloomFruit = createAdvancement("eat_bolloom_fruit", "end", poiseForest, EEItems.BOLLOOM_FRUIT.get(), FrameType.TASK, true, true, false)
				.addCriterion("bolloom_fruit", ConsumeItemTrigger.TriggerInstance.usedItem(EEItems.BOLLOOM_FRUIT.get()))
				.save(consumer, EndergeticExpansion.MOD_ID + ":end/eat_bolloom_fruit");

		createAdvancement("up_up_and_away", "end", eatBolloomFruit, EEItems.BOLLOOM_BALLOON.get(), FrameType.GOAL, true, true, false)
				.addCriterion("up_up_and_away", EECriteriaTriggers.UP_UP_AND_AWAY.createInstance())
				.save(consumer, EndergeticExpansion.MOD_ID + ":end/up_up_and_away");

		createAdvancement("tame_booflo", "end", poiseForest, EEItems.BOOFLO_HIDE.get(), FrameType.GOAL, true, true, false)
				.addCriterion("tamed_booflo", EECriteriaTriggers.TAME_BOOFLO.createInstance())
				.save(consumer, EndergeticExpansion.MOD_ID + ":end/tame_booflo");

		createAdvancement("obtain_booflo_vest", "end", poiseForest, EEItems.BOOFLO_VEST.get(), FrameType.GOAL, true, true, false)
				.addCriterion("booflo_vest", InventoryChangeTrigger.TriggerInstance.hasItems(EEItems.BOOFLO_VEST.get()))
				.save(consumer, EndergeticExpansion.MOD_ID + ":end/obtain_booflo_vest");

		createAdvancement("catch_puff_bug", "end", poiseForest, EEItems.PUFF_BUG_BOTTLE.get(), FrameType.GOAL, true, true, false)
				.addCriterion("puff_bug_bottle", InventoryChangeTrigger.TriggerInstance.hasItems(EEItems.PUFF_BUG_BOTTLE.get()))
				.save(consumer, EndergeticExpansion.MOD_ID + ":end/catch_puff_bug");

		// TODO: Eetle Update
		Advancement findEetleNest = createAdvancement("find_eetle_nest", "end", new ResourceLocation("end/enter_end_gateway"), EEBlocks.END_CORROCK_BLOCK.get(), FrameType.TASK, true, true, true)
				.addCriterion("in_eetle_nest", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(EEStructures.EETLE_NEST)))
				.save(consumer, EndergeticExpansion.MOD_ID + ":end/find_eetle_nest");

		 createAdvancement("kill_brood_eetle", "end", findEetleNest, EEBlocks.EETLE_EGG.get(), FrameType.TASK, true, true, true)
				.addCriterion("killed_brood_eetle", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EEEntityTypes.BROOD_EETLE.get())))
				.save(consumer, EndergeticExpansion.MOD_ID + ":end/kill_brood_eetle");
	}

	private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, ItemLike icon, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return createAdvancement(name, category, Advancement.Builder.advancement().build(parent), icon, frame, showToast, announceToChat, hidden);
	}

	private static Advancement.Builder createAdvancement(String name, String category, Advancement parent, ItemLike icon, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return Advancement.Builder.advancement().parent(parent).display(icon,
				Component.translatable("advancements." + EndergeticExpansion.MOD_ID + "." + category + "." + name + ".title"),
				Component.translatable("advancements." + EndergeticExpansion.MOD_ID + "." + category + "." + name + ".description"),
				null, frame, showToast, announceToChat, hidden);
	}
}