package dev.wp.phantoms_utilities;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public final class PUSounds {
    public static final ResourceLocation SPRAY_CAN_SPRAY_ID = PhantomsUtilities.makeId("spray_can.spray");
    public static final ResourceLocation SPRAY_CAN_SHAKE_ID = PhantomsUtilities.makeId("spray_can.shake");

    public static SoundEvent SPRAY_CAN_SPRAY = SoundEvent.createVariableRangeEvent(SPRAY_CAN_SPRAY_ID);
    public static SoundEvent SPRAY_CAN_SHAKE = SoundEvent.createVariableRangeEvent(SPRAY_CAN_SHAKE_ID);

    public static void register(Registry<SoundEvent> registry) {
        Registry.register(registry, "spray_can_spray", SPRAY_CAN_SPRAY);
        Registry.register(registry, "spray_can_shake", SPRAY_CAN_SHAKE);
    }
}
