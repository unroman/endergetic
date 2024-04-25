package com.teamabnormals.endergetic.core.registry.builtin;

import com.teamabnormals.endergetic.common.levelgen.biome.modifiers.SmallEndIslandsAmbienceBiomeModifier;
import com.teamabnormals.endergetic.core.EndergeticExpansion;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddSpawnsBiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class EEBiomeModifiers {

	public static void bootstrap(BootstapContext<BiomeModifier> context) {
		//TODO: Biome Tag
		List<ResourceKey<Biome>> endHighlandsOrMidlands = List.of(Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS);

		// TODO: Eetle Update
//		addSpawn(context, "add_eetle_spawns", endHighlandsOrMidlands, new MobSpawnSettings.SpawnerData(EEEntityTypes.CHARGER_EETLE.get(), 12, 2, 5), new MobSpawnSettings.SpawnerData(EEEntityTypes.CHARGER_EETLE.get(), 8, 2, 4));
//		addFeature(context, "add_corrock_vegetation", endHighlandsOrMidlands, GenerationStep.Decoration.VEGETAL_DECORATION, EEPlacedFeatures.CORROCK_PATCH, EEPlacedFeatures.EETLE_EGG_PATCH);
//		addFeature(context, "add_corrock_surface_structures", endHighlandsOrMidlands, GenerationStep.Decoration.SURFACE_STRUCTURES, EEPlacedFeatures.CORROCK_BRANCH, EEPlacedFeatures.CORROCK_TOWER, EEPlacedFeatures.CORROCK_SHELF, EEPlacedFeatures.CORROCK_ARCH, EEPlacedFeatures.EUMUS_PATCH, EEPlacedFeatures.SPECKLED_CORROCK_PATCH);
//		addFeature(context, "add_sparse_corrock_branch", Biomes.END_MIDLANDS, GenerationStep.Decoration.SURFACE_STRUCTURES, EEPlacedFeatures.SPARSE_CORROCK_BRANCH);

		register(context, "small_end_islands_ambience", () -> SmallEndIslandsAmbienceBiomeModifier.INSTANCE);
	}

	@SafeVarargs
	private static void addFeature(BootstapContext<BiomeModifier> context, String name, ResourceKey<Biome> biome, Decoration step, ResourceKey<PlacedFeature>... features) {
		addFeature(context, name, List.of(biome), step, features);
	}

	@SafeVarargs
	private static void addFeature(BootstapContext<BiomeModifier> context, String name, List<ResourceKey<Biome>> biomes, Decoration step, ResourceKey<PlacedFeature>... features) {
		register(context, "add_feature/" + name, () -> new AddFeaturesBiomeModifier(HolderSet.direct(biomes.stream().map(biome -> context.lookup(Registries.BIOME).getOrThrow(biome)).collect(Collectors.toList())), featureSet(context, features), step));
	}

	private static void addSpawn(BootstapContext<BiomeModifier> context, String name, List<ResourceKey<Biome>> biomes, MobSpawnSettings.SpawnerData... spawns) {
		register(context, "add_spawn/" + name, () -> new AddSpawnsBiomeModifier(HolderSet.direct(biomes.stream().map(biome -> context.lookup(Registries.BIOME).getOrThrow(biome)).collect(Collectors.toList())), List.of(spawns)));
	}

	@SafeVarargs
	private static HolderSet<PlacedFeature> featureSet(BootstapContext<?> context, ResourceKey<PlacedFeature>... features) {
		return HolderSet.direct(Stream.of(features).map(placedFeatureKey -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeatureKey)).collect(Collectors.toList()));
	}

	private static void register(BootstapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
		context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(EndergeticExpansion.MOD_ID, name)), modifier.get());
	}
}
