package dev.wp.phantoms_utilities.network;

import dev.wp.phantoms_utilities.PhantomsUtilities;
import dev.wp.phantoms_utilities.network.server.MWPacket;
import dev.wp.phantoms_utilities.network.server.SprayCanColorSelectPacket;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class InitNetwork {
    public static void init(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar(PhantomsUtilities.ID);

        // Server-bound
        serverbound(registrar, SprayCanColorSelectPacket.TYPE, SprayCanColorSelectPacket.STREAM_CODEC);
        serverbound(registrar, MWPacket.TYPE, MWPacket.STREAM_CODEC);
    }

    private static <T extends ServerBoundPacket> void serverbound(PayloadRegistrar registrar,
                                                                  CustomPacketPayload.Type<T> type,
                                                                  StreamCodec<RegistryFriendlyByteBuf, T> codec) {
        registrar.playToServer(type, codec, ServerBoundPacket::handleOnServer);
    }
}
