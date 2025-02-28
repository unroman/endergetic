package com.teamabnormals.endergetic.client.particle.data;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.endergetic.core.registry.EEParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class CorrockCrownParticleData implements ParticleOptions {
	public static final ParticleOptions.Deserializer<CorrockCrownParticleData> DESERIALIZER = new ParticleOptions.Deserializer<>() {
		@Override
		public CorrockCrownParticleData fromCommand(ParticleType<CorrockCrownParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
			reader.expect(' ');
			int lifetime = reader.readInt();
			reader.expect(' ');
			int lifetimeBoost = reader.readInt();
			reader.expect(' ');
			float gravity = reader.readFloat();
			reader.expect(' ');
			float gravityBoost = reader.readFloat();
			reader.expect(' ');
			return new CorrockCrownParticleData(() -> particleTypeIn, lifetime, lifetimeBoost, gravity, gravityBoost, Optional.of(reader.readFloat()));
		}

		@Override
		public CorrockCrownParticleData fromNetwork(ParticleType<CorrockCrownParticleData> particleTypeIn, FriendlyByteBuf buffer) {
			return new CorrockCrownParticleData(() -> particleTypeIn, buffer.readInt(), buffer.readInt(), buffer.readFloat(), buffer.readFloat(), buffer.readBoolean() ? Optional.of(buffer.readFloat()) : Optional.empty());
		}
	};
	@SuppressWarnings("FunctionalExpressionCanBeFolded")
	public static final CorrockCrownParticleData EETLE = new CorrockCrownParticleData(() -> EEParticleTypes.END_CROWN.get(), 20, 21, 0.8F, 0.025F, Optional.empty());
	private final Supplier<ParticleType<CorrockCrownParticleData>> particleType;
	private final int lifetime;
	private final int lifetimeBoost;
	private final float gravity;
	private final float gravityBoost;
	private final Optional<Float> scale;

	public CorrockCrownParticleData(Supplier<ParticleType<CorrockCrownParticleData>> particleType, int lifetime, int lifetimeBoost, float gravity, float gravityBoost, Optional<Float> scale) {
		this.particleType = particleType;
		this.lifetime = lifetime;
		this.lifetimeBoost = lifetimeBoost;
		this.gravity = gravity;
		this.gravityBoost = gravityBoost;
		this.scale = scale;
	}

	public static CorrockCrownParticleData of(Supplier<ParticleType<CorrockCrownParticleData>> type) {
		return new CorrockCrownParticleData(type, 40, 21, 0.0F, 0.08F, Optional.empty());
	}

	public static CorrockCrownParticleData scaled(Supplier<ParticleType<CorrockCrownParticleData>> type, float scale) {
		return new CorrockCrownParticleData(type, 40, 21, 0.0F, 0.08F, Optional.of(scale));
	}

	public static Codec<CorrockCrownParticleData> codec(ParticleType<CorrockCrownParticleData> type) {
		return RecordCodecBuilder.create((instance) -> {
			return instance.group(
					Codec.INT.optionalFieldOf("lifetime", 40).forGetter(data -> data.lifetime),
					Codec.INT.optionalFieldOf("lifetime_boost", 21).forGetter(data -> data.lifetimeBoost),
					Codec.FLOAT.optionalFieldOf("gravity", 0.0F).forGetter(data -> data.gravity),
					Codec.FLOAT.optionalFieldOf("gravity_boost", 0.08F).forGetter(data -> data.gravityBoost),
					Codec.FLOAT.optionalFieldOf("scale").forGetter(data -> data.scale)
			).apply(instance, ((lifetime, lifetimeBoost, gravity, gravityBoost, scale) -> {
				return new CorrockCrownParticleData(() -> type, lifetime, lifetimeBoost, gravity, gravityBoost, scale);
			}));
		});
	}

	@Override
	public ParticleType<?> getType() {
		return this.particleType.get();
	}

	@Override
	public void writeToNetwork(FriendlyByteBuf buffer) {
		buffer.writeInt(this.lifetime);
		buffer.writeInt(this.lifetimeBoost);
		buffer.writeFloat(this.gravity);
		buffer.writeFloat(this.gravityBoost);
		Optional<Float> scale = this.scale;
		boolean present = scale.isPresent();
		buffer.writeBoolean(present);
		if (present) {
			buffer.writeDouble(scale.get());
		}
	}

	@Override
	public String writeToString() {
		return BuiltInRegistries.PARTICLE_TYPE.getKey(this.getType()) +
				", type: " + this.particleType +
				", lifetime: " + this.lifetime +
				", lifetimeBoost: " + this.lifetimeBoost +
				", gravity: " + this.gravity +
				", gravityBoost: " + this.gravityBoost +
				", scale: " + this.scale;
	}

	public int getLifetime() {
		return this.lifetime;
	}

	public int getLifetimeBoost() {
		return this.lifetimeBoost;
	}

	public float getGravity() {
		return this.gravity;
	}

	public float getGravityBoost() {
		return this.gravityBoost;
	}

	public Optional<Float> getScale() {
		return this.scale;
	}
}
