package ma.shaur.bettercoppergolem.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemContainerContents.class)
public interface ContainerComponentAccessor 
{
	@Accessor
	NonNullList<ItemStack> getStacks();

	@Mutable
	@Final
	@Accessor("stacks")
	void setStacks(NonNullList<ItemStack> stacks);
}
