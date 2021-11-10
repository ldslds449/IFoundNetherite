package net.fabricmc.ifoundnetherite.mixin;

import net.fabricmc.ifoundnetherite.Main;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.entity.EntityLike;

import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin extends World implements StructureWorldAccess{

	protected ServerWorldMixin(MutableWorldProperties properties, RegistryKey<World> registryRef,
			DimensionType dimensionType, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long seed) {
		super(properties, registryRef, dimensionType, profiler, isClient, debugWorld, seed);
		// Do nothing
	}

	@Shadow
	public abstract boolean addEntity(Entity entity);

	public boolean spawnEntity(Entity entity) {
		if(entity instanceof ItemEntity){
			ItemEntity item = (ItemEntity)entity;
			// TODO: identify the target item and play sound
			Main.LOGGER.info("[Detect Spawn Entity] --- {} x {}", 
				item.getStack().getItem().getTranslationKey(), 
				item.getStack().getCount());
			this.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 1f, 1f);
		}
		return this.addEntity(entity);
	}

}