package com.teamabnormals.endergetic.core.registry;

import com.google.common.base.Function;
import com.mojang.serialization.Codec;
import com.teamabnormals.endergetic.client.particle.CorrockCrownParticle;
import com.teamabnormals.endergetic.client.particle.FastBlockParticle.Factory;
import com.teamabnormals.endergetic.client.particle.ParticleTypeWithData;
import com.teamabnormals.endergetic.client.particle.PoiseBubbleParticle;
import com.teamabnormals.endergetic.client.particle.data.CorrockCrownParticleData;
import com.teamabnormals.endergetic.core.EndergeticExpansion;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EEParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EndergeticExpansion.MOD_ID);

	public static final RegistryObject<SimpleParticleType> POISE_BUBBLE = createBasicParticleType("poise_bubble", true);
	public static final RegistryObject<SimpleParticleType> SHORT_POISE_BUBBLE = createBasicParticleType("short_poise_bubble", true);
	public static final RegistryObject<SimpleParticleType> ENDER_FIRE_FLAME = createBasicParticleType("ender_fire_flame", true);
	public static final RegistryObject<SimpleParticleType> SMALL_ENDER_FIRE_FLAME = createBasicParticleType("small_ender_fire_flame", false);
	public static final RegistryObject<ParticleType<BlockParticleOption>> FAST_BLOCK = createParticleType("fast_block", BlockParticleOption.DESERIALIZER, BlockParticleOption::codec);
	public static final RegistryObject<ParticleType<CorrockCrownParticleData>> OVERWORLD_CROWN = createParticleType("overworld_crown", CorrockCrownParticleData.DESERIALIZER, CorrockCrownParticleData::codec);
	public static final RegistryObject<ParticleType<CorrockCrownParticleData>> NETHER_CROWN = createParticleType("nether_crown", CorrockCrownParticleData.DESERIALIZER, CorrockCrownParticleData::codec);
	public static final RegistryObject<ParticleType<CorrockCrownParticleData>> END_CROWN = createParticleType("end_crown", CorrockCrownParticleData.DESERIALIZER, CorrockCrownParticleData::codec);

	private static RegistryObject<SimpleParticleType> createBasicParticleType(String name, boolean alwaysShow) {
		return PARTICLES.register(name, () -> new SimpleParticleType(alwaysShow));
	}

	@SuppressWarnings("deprecation")
	private static <T extends ParticleOptions> RegistryObject<ParticleType<T>> createParticleType(String name, ParticleOptions.Deserializer<T> deserializer, Function<ParticleType<T>, Codec<T>> function) {
		return PARTICLES.register(name, () -> new ParticleTypeWithData<>(deserializer, function));
	}

	@OnlyIn(Dist.CLIENT)
	@EventBusSubscriber(modid = EndergeticExpansion.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class RegisterParticleFactories {

		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
			event.registerSpriteSet(ENDER_FIRE_FLAME.get(), FlameParticle.Provider::new);
			event.registerSpriteSet(SMALL_ENDER_FIRE_FLAME.get(), FlameParticle.SmallFlameProvider::new);
			event.registerSpriteSet(POISE_BUBBLE.get(), PoiseBubbleParticle.Factory::new);
			event.registerSpriteSet(SHORT_POISE_BUBBLE.get(), PoiseBubbleParticle.ShortFactory::new);
			event.registerSpecial(FAST_BLOCK.get(), new Factory());
			event.registerSpriteSet(OVERWORLD_CROWN.get(), CorrockCrownParticle.Factory::new);
			event.registerSpriteSet(NETHER_CROWN.get(), CorrockCrownParticle.Factory::new);
			event.registerSpriteSet(END_CROWN.get(), CorrockCrownParticle.Factory::new);
		}
	}
}