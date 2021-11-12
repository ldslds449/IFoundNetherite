package net.fabricmc.ifoundnetherite.mixin;

import net.fabricmc.ifoundnetherite.Config;
import net.fabricmc.ifoundnetherite.CustomSound;
import net.fabricmc.ifoundnetherite.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin extends World implements StructureWorldAccess {
	protected ServerWorldMixin(MutableWorldProperties properties, RegistryKey<World> registryRef,
			DimensionType dimensionType, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long seed) {
		super(properties, registryRef, dimensionType, profiler, isClient, debugWorld, seed);
		// Do nothing
	}

	@Shadow
	public abstract boolean addEntity(Entity entity);

	public boolean spawnEntity(Entity entity) {
		if (entity instanceof ItemEntity) {
			ItemEntity item = (ItemEntity) entity;
			Main.LOGGER.info("[Detect Spawn Entity] --- {} x {}", item.getStack().getItem().getTranslationKey(),
					item.getStack().getCount());
			if (Config.in_config(item.getStack().getItem().getTranslationKey())) {
				// this.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_BELL_USE,
				// SoundCategory.BLOCKS, 1f, 1f);
				this.playSound(null, entity.getBlockPos(), CustomSound.POE_SOUND_EVENT, SoundCategory.BLOCKS, 1f, 1f);
			}
		}
		return this.addEntity(entity);
	}
}