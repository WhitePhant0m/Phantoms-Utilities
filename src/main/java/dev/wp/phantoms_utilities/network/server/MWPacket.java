package dev.wp.phantoms_utilities.network.server;

import dev.wp.phantoms_utilities.helpers.IMouseWheelItem;
import dev.wp.phantoms_utilities.network.CustomPUPayload;
import dev.wp.phantoms_utilities.network.ServerBoundPacket;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import org.jetbrains.annotations.NotNull;

public record MWPacket(boolean wheelUp) implements ServerBoundPacket {
    public static final StreamCodec<RegistryFriendlyByteBuf, MWPacket> STREAM_CODEC = StreamCodec.ofMember(
            MWPacket::write,
            MWPacket::decode);

    public static final Type<MWPacket> TYPE = CustomPUPayload.createType("mouse_wheel");

    public static MWPacket decode(RegistryFriendlyByteBuf byteBuf) {
        var wheelUp = byteBuf.readBoolean();
        return new MWPacket(wheelUp);
    }

    @Override
    public @NotNull Type<MWPacket> type() {
        return TYPE;
    }

    public void write(RegistryFriendlyByteBuf data) {
        data.writeBoolean(wheelUp);
    }

    @Override
    public void handleOnServer(ServerPlayer player) {
        var mainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        var offHand = player.getItemInHand(InteractionHand.OFF_HAND);

        if (mainHand.getItem() instanceof IMouseWheelItem mouseWheelItem) {
            mouseWheelItem.onScroll(mainHand, wheelUp);
        } else if (offHand.getItem() instanceof IMouseWheelItem mouseWheelItem) {
            mouseWheelItem.onScroll(offHand, wheelUp);
        }
    }
}
