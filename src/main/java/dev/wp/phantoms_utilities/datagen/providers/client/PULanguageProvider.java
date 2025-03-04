package dev.wp.phantoms_utilities.datagen.providers.client;

import dev.wp.phantoms_utilities.PUItems;
import dev.wp.phantoms_utilities.PhantomsUtilities;
import dev.wp.phantoms_utilities.Util.PUColor;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public final class PULanguageProvider extends LanguageProvider {
    public PULanguageProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), PhantomsUtilities.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (PUColor color : PUColor.values()) {
            add(color.toString(), color.getEnglishName());
        }

//        add(PUItems.SLEEP_CHARM.get(), "Sleep Charm");
        add(PUItems.SPRAY_CAN.get(), "Spray Can");
        add("phantoms_utilities.configuration.items", "Item configs");
        add("phantoms_utilities.configuration.spray_can", "Spray Can");
        add("phantoms_utilities.configuration.spray_can.max_cable_dye_count", "Max Cable Dye Count");
        add("phantoms_utilities.configuration.spray_can.max_block_dye_count", "Max Block Dye Count");
        add("phantoms_utilities.configuration.spray_can.max_total_checks", "Max Total Checks");
    }
}
