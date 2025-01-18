package dev.wp.phantoms_utilities.network;

import dev.wp.phantoms_utilities.PhantomsUtilities;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public interface CustomPUPayload extends CustomPacketPayload {
    static <T extends CustomPacketPayload> CustomPacketPayload.Type<T> createType(String name) {
        return new CustomPacketPayload.Type<>(PhantomsUtilities.makeId(name));
    }
}
