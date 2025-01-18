package dev.wp.phantoms_utilities.datagen;

import dev.wp.phantoms_utilities.PhantomsUtilities;
import dev.wp.phantoms_utilities.datagen.providers.DatagenDelegatorClient;
import dev.wp.phantoms_utilities.datagen.providers.DatagenDelegatorServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = PhantomsUtilities.ID, bus = EventBusSubscriber.Bus.MOD)
public class DatagenDelegator {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DatagenDelegatorClient.configure(event);
        DatagenDelegatorServer.configure(event);
    }
}
