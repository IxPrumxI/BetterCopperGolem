package ma.shaur.bettercoppergolem.mixin;

import ma.shaur.bettercoppergolem.custom.entity.ai.memory.CustomMemoryModuleType;
import net.minecraft.world.entity.ai.Brain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import ma.shaur.bettercoppergolem.custom.entity.LastItemDataHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.golem.CopperGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

@Mixin(CopperGolem.class)
public abstract class CopperGolemMixin implements LastItemDataHolder
{
	@Shadow
	public abstract Brain<CopperGolem> getBrain();

	@Inject(method = "mobInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/golem/CopperGolem;setItemInHand(Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;)V", shift = At.Shift.AFTER))
	public void interactMob(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> info)
	{
		//No need to optimize for the same item since a player took it out of golem's hands
		this.getBrain().setMemory(CustomMemoryModuleType.TRANSPORT_ITEMS_LAST_HELD, ItemStack.EMPTY);
	}
	
	@Override
	public ItemStack betterCopperGolem$getLastItemStack()
	{
		return this.getBrain().getMemory(CustomMemoryModuleType.TRANSPORT_ITEMS_LAST_HELD).orElse(ItemStack.EMPTY);
	}

	@Override
	public void betterCopperGolem$setLastItemStack(ItemStack lastItemStack)
	{
		this.getBrain().setMemory(CustomMemoryModuleType.TRANSPORT_ITEMS_LAST_HELD, lastItemStack);
	}
}
