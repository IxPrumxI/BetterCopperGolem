package ma.shaur.bettercoppergolem.mixin;

import com.google.common.collect.ImmutableList;
import ma.shaur.bettercoppergolem.custom.entity.ai.memory.CustomMemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import ma.shaur.bettercoppergolem.config.ConfigHandler;
import net.minecraft.world.entity.animal.golem.CopperGolemAi;

@Mixin(CopperGolemAi.class)
public class CopperGolemAiMixin 
{
	@ModifyConstant(method ="initIdleActivity", constant = @Constant(intValue = 8))
	private static int verticalRange(int constant)
	{
		return ConfigHandler.getConfig().verticalRange + 10;
	}
	
	@ModifyConstant(method ="initIdleActivity", constant = @Constant(intValue = 32))
	private static int horizontalRange(int constant)
	{
		return ConfigHandler.getConfig().horizontalRange + 10;
	}

	@Shadow
	@Mutable
	private static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(
			MemoryModuleType.IS_PANICKING,
			MemoryModuleType.HURT_BY,
			MemoryModuleType.HURT_BY_ENTITY,
			MemoryModuleType.NEAREST_LIVING_ENTITIES,
			MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
			MemoryModuleType.WALK_TARGET,
			MemoryModuleType.LOOK_TARGET,
			MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
			MemoryModuleType.PATH,
			MemoryModuleType.GAZE_COOLDOWN_TICKS,
			MemoryModuleType.TRANSPORT_ITEMS_COOLDOWN_TICKS,
			MemoryModuleType.VISITED_BLOCK_POSITIONS,
			MemoryModuleType.UNREACHABLE_TRANSPORT_BLOCK_POSITIONS,
			MemoryModuleType.DOORS_TO_CLOSE,
			CustomMemoryModuleType.TRANSPORT_ITEMS_LAST_HELD,
			CustomMemoryModuleType.TRANSPORT_CURRENT_CHEST_POS,
			CustomMemoryModuleType.TRANSPORT_FORCEFULLY_INSERT_INTO_TARGET_CHEST,
			CustomMemoryModuleType.TRANSPORT_LAST_EMPTY_CHEST_POS,
			CustomMemoryModuleType.TRANSPORT_LAST_FREE_SLOT_CHEST_POS
	);
}
