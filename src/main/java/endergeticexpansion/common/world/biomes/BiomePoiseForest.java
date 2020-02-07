package endergeticexpansion.common.world.biomes;

import endergeticexpansion.common.world.features.EEFeatures;
import endergeticexpansion.common.world.surfacebuilders.EESurfaceBuilders;
import endergeticexpansion.core.registry.EEBlocks;
import endergeticexpansion.core.registry.EEEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.dimension.EndDimension;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.DoublePlantConfig;
import net.minecraft.world.gen.feature.EndGatewayConfig;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomePoiseForest extends EndergeticBiome {

	public BiomePoiseForest() {
		super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder<>(EESurfaceBuilders.POISE_SURFACE_BUILDER.get(), SurfaceBuilder.END_STONE_CONFIG)).precipitation(RainType.NONE).category(Category.THEEND).depth(0.1F).scale(0.2F).temperature(0.5F).downfall(0.5F).waterColor(4159204).waterFogColor(329011).parent((String)null), () -> new ConfiguredSurfaceBuilder<>(EESurfaceBuilders.POISE_SURFACE_BUILDER.get(), SurfaceBuilder.END_STONE_CONFIG));
	}
	
	@Override
	public void addSpawnsAndFeatures() {
		this.addFeature(Decoration.SURFACE_STRUCTURES, createDecoratedFeature(EEFeatures.POISE_DOME.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(3, 0.02F, 1)));
		this.addFeature(Decoration.SURFACE_STRUCTURES, createDecoratedFeature(EEFeatures.POISE_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(2, 0.05F, 1)));
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, createDecoratedFeature(EEFeatures.ENDERGETIC_GATEWAY, EndGatewayConfig.func_214702_a(EndDimension.SPAWN, true), Placement.END_GATEWAY, IPlacementConfig.NO_PLACEMENT_CONFIG));
		
		this.addFeature(Decoration.VEGETAL_DECORATION, createDecoratedFeature(EEFeatures.POISE_CLUSTER.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOISE_HEIGHTMAP_32, new NoiseDependant(-0.8D, 4, 22)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createDecoratedFeature(EEFeatures.PUFFBUG_HIVE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOISE_HEIGHTMAP_32, new NoiseDependant(-0.8D, 9, 25)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createDecoratedFeature(EEFeatures.BOLLOOM_BUD.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOISE_HEIGHTMAP_32, new NoiseDependant(-0.9D, 90, 90)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createDecoratedFeature(EEFeatures.POISE_TALLGRASS.get(), new DoublePlantConfig(EEBlocks.POISE_GRASS_TALL.get().getDefaultState()), Placement.NOISE_HEIGHTMAP_32, new NoiseDependant(-0.8D, 0, 7)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createDecoratedFeature(EEFeatures.POISE_GRASS.get(), new GrassFeatureConfig(EEBlocks.POISE_GRASS.get().getDefaultState()), Placement.NOISE_HEIGHTMAP_DOUBLE, new NoiseDependant(-0.8D, 5, 10)));
		
		this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EEEntities.BOOFLO_ADOLESCENT.get(), 5, 1, 2));
		this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EEEntities.BOOFLO.get(), 15, 1, 3));
	}
	
	@Override
	public float getSpawningChance() {
		return 0.5F;
	}
	
	@Override
	public int getWeight() {
		return 6;
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public int getSkyColor() {
		return 11665571;
	}
	
	@Override
	public Type[] getBiomeTypes() {
		return new Type[] {
			Type.MAGICAL, 
			Type.FOREST, 
			Type.END
		};
	}
	
}