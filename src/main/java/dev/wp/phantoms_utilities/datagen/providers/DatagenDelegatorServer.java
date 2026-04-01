package dev.wp.phantoms_utilities.datagen.providers;

import dev.wp.phantoms_utilities.datagen.providers.server.PUBlockTags;
import dev.wp.phantoms_utilities.datagen.providers.server.PURecipes;
import net.minecraft.data.DataProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.function.Function;

public class DatagenDelegatorServer {
    public static void configure(GatherDataEvent event) {
        add(event, PUBlockTags::new);

        add(event, PURecipes::new);
    }

    private static void add(GatherDataEvent event, Function<GatherDataEvent, DataProvider> providerCreator) {
        event.getGenerator().addProvider(event.includeServer(), providerCreator.apply(event));
    }
}
