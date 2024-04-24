package com.teamabnormals.endergetic.core.registry;

import com.teamabnormals.endergetic.common.levelgen.structure.EetleNestStructure;
import com.teamabnormals.endergetic.common.levelgen.structure.structures.EetleNestPieces;
import com.teamabnormals.endergetic.core.EndergeticExpansion;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class EEStructures {
	public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, EndergeticExpansion.MOD_ID);
	public static final DeferredRegister<Structure> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE, EndergeticExpansion.MOD_ID);

	public static final RegistryObject<StructureType<EetleNestStructure>> EETLE_NEST_TYPE = STRUCTURE_TYPES.register("eetle_nest", () -> () -> EetleNestStructure.CODEC);

	// public static final RegistryObject<Structure> EETLE_NEST = STRUCTURES.register("eetle_nest", () -> new EetleNestStructure(new Structure.StructureSettings(biomes(EEBiomeTags.HAS_EETLE_NEST), Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(EEEntityTypes.CHARGER_EETLE.get(), 12, 3, 7), new MobSpawnSettings.SpawnerData(EEEntityTypes.GLIDER_EETLE.get(), 8, 3, 6)))), GenerationStep.Decoration.RAW_GENERATION, TerrainAdjustment.NONE)));

	public static final class EEStructurePieceTypes {
		public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(Registries.STRUCTURE_PIECE, EndergeticExpansion.MOD_ID);

		public static final RegistryObject<StructurePieceType.StructureTemplateType> EETLE_NEST = STRUCTURE_PIECE_TYPES.register("eetle_nest", () -> EetleNestPieces.EetleNestPiece::new);
	}

	// TODO: generate
	public static final class Sets {
		// public static final DeferredRegister<StructureSet> STRUCTURE_SETS = DeferredRegister.create(Registries.STRUCTURE_SET, EndergeticExpansion.MOD_ID);

		// public static final RegistryObject<StructureSet> EETLE_NEST = STRUCTURE_SETS.register("eetle_nest", () -> new StructureSet(EEStructures.EETLE_NEST.getHolder().get(), new RandomSpreadStructurePlacement(18, 9, RandomSpreadType.TRIANGULAR, 5193657)));
	}
}
