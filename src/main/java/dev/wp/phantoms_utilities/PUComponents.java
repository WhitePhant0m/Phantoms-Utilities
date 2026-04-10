package dev.wp.phantoms_utilities;

import com.mojang.serialization.Codec;
import dev.wp.phantoms_utilities.util.PUColor;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class PUComponents {
    private static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, PhantomsUtilities.ID);

    public static final Supplier<DataComponentType<PUColor>> SELECTED_COLOR = create("selected_color", PUColor.CODEC, PUColor.STREAM_CODEC);

    public static void init(IEventBus bus) {
        COMPONENTS.register(bus);
    }

    private static <D> DeferredHolder<DataComponentType<?>, DataComponentType<D>> create(String name, Codec<D> codec, StreamCodec<? super RegistryFriendlyByteBuf, D> streamCodec) {
        return COMPONENTS.registerComponentType(name, (b) -> b.persistent(codec).networkSynchronized(streamCodec));
    }
}
