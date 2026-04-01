package dev.wp.phantoms_utilities;

import dev.wp.phantoms_utilities.Util.PUColor;
import dev.wp.phantoms_utilities.items.SprayCan;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = PhantomsUtilities.ID)
public class PUItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PhantomsUtilities.ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PhantomsUtilities.ID);

    // Items
    public static final DeferredItem<SprayCan> SPRAY_CAN = ITEMS.register("spray_can", () -> new SprayCan(new Item.Properties()));

    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            ITEMS.getEntries().forEach(entry -> event.accept(entry.get()));
        }
    }

}
