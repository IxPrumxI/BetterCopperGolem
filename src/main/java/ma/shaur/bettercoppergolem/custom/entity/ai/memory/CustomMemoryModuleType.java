package ma.shaur.bettercoppergolem.custom.entity.ai.memory;

import com.mojang.serialization.Codec;
import ma.shaur.bettercoppergolem.BetterCopperGolem;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.behavior.PositionTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class CustomMemoryModuleType<U> extends MemoryModuleType<U> {

    public static CustomMemoryModuleType<ItemStack> TRANSPORT_ITEMS_LAST_HELD;
    public static CustomMemoryModuleType<GlobalPos> TRANSPORT_CURRENT_CHEST_POS;
    public static CustomMemoryModuleType<Boolean> TRANSPORT_FORCEFULLY_INSERT_INTO_TARGET_CHEST;
    public static CustomMemoryModuleType<PositionTracker> TRANSPORT_LAST_FREE_SLOT_CHEST_POS;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public CustomMemoryModuleType(Optional<Codec<U>> optional) {
        super(optional);
    }

    private static <U> CustomMemoryModuleType<U> register(String string, Codec<U> codec) {
        return Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE, Identifier.fromNamespaceAndPath(BetterCopperGolem.MOD_ID, string), new CustomMemoryModuleType<>(Optional.of(codec)));
    }

    private static <U> CustomMemoryModuleType<U> register(String string) {
        return Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE, Identifier.fromNamespaceAndPath(BetterCopperGolem.MOD_ID, string), new CustomMemoryModuleType<>(Optional.empty()));
    }

    public static void init() {
        TRANSPORT_ITEMS_LAST_HELD = register("transport_items_last_held", ItemStack.CODEC);
        TRANSPORT_CURRENT_CHEST_POS = register("transport_current_chest_pos", GlobalPos.CODEC);
        TRANSPORT_FORCEFULLY_INSERT_INTO_TARGET_CHEST = register("transport_forcefully_insert", Codec.BOOL);
        TRANSPORT_LAST_FREE_SLOT_CHEST_POS = register("transport_last_free_slot_chest_pos");
    }
}
