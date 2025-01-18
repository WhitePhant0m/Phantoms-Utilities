package dev.wp.phantoms_utilities;

import dev.wp.phantoms_utilities.network.InitNetwork;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(PhantomsUtilities.ID)
public class PhantomsUtilities {
    public static final String ID = "phantoms_utilities";
    public static final String NAME = "Phantoms Utilities";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public PhantomsUtilities(IEventBus bus, ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, PUConfig.SPEC);

        bus.addListener(this::commonSetup);
        bus.addListener(InitNetwork::init);
        bus.addListener((RegisterEvent event) -> {
            if (event.getRegistryKey() == Registries.SOUND_EVENT) registerSounds();
        });

        registerDataComponents();

        PUItems.ITEMS.register(bus);
        PUItems.CREATIVE_MODE_TABS.register(bus);
        PUItems.DATA_COMPONENTS.register(bus);
    }

    public static ResourceLocation makeId(String id) {
        return ResourceLocation.fromNamespaceAndPath(ID, id);
    }

    private void registerSounds() {
        PUSounds.register(BuiltInRegistries.SOUND_EVENT);
    }

    private void registerDataComponents() {
        PUItems.DATA_COMPONENTS.register("selected_color", () -> PUItems.SELECTED_COLOR);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

}
