package dev.wp.phantoms_utilities;

import dev.wp.phantoms_utilities.Util.PUColor;
//import dev.wp.phantoms_utilities.items.CurioEffects;
//import dev.wp.phantoms_utilities.items.ItemCurio;
//import dev.wp.phantoms_utilities.items.ItemCurioTicking;
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

@EventBusSubscriber(modid = PhantomsUtilities.ID, bus = EventBusSubscriber.Bus.MOD)
public class PUItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PhantomsUtilities.ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PhantomsUtilities.ID);
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, PhantomsUtilities.ID);

    // Spray Can
    public static final DeferredItem<SprayCan> SPRAY_CAN = ITEMS.register("spray_can", () -> new SprayCan(new Item.Properties()));

    // Curios
//    public static final DeferredItem<ItemCurioTicking> SLEEP_CHARM = ITEMS.register("sleep_charm", () -> new ItemCurioTicking(CurioEffects::sleepCharmTick));

    // Components
    public static final DataComponentType<PUColor> SELECTED_COLOR = DataComponentType.<PUColor>builder().persistent(PUColor.CODEC).networkSynchronized(PUColor.STREAM_CODEC).build();

    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(SPRAY_CAN);
//            event.accept(SLEEP_CHARM);
        }
    }

}
