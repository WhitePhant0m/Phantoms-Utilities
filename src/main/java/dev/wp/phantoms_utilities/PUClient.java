package dev.wp.phantoms_utilities;

import dev.wp.phantoms_utilities.helpers.IMouseWheelItem;
import dev.wp.phantoms_utilities.network.ServerBoundPacket;
import dev.wp.phantoms_utilities.network.server.MWPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;

@Mod(value = PhantomsUtilities.ID, dist = Dist.CLIENT)
@EventBusSubscriber(value = Dist.CLIENT, modid = PhantomsUtilities.ID, bus = EventBusSubscriber.Bus.MOD)
public class PUClient {

    public PUClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    private static void clientSetup(FMLClientSetupEvent event) {
        NeoForge.EVENT_BUS.addListener(PUClient::wheelEvent);
    }

    private static void wheelEvent(final InputEvent.MouseScrollingEvent me) {
        if (me.getScrollDeltaY() == 0) return;

        final Minecraft mc = Minecraft.getInstance();
        final Player player = mc.player;

        if (player != null && player.isShiftKeyDown()) {
            var mainHand = player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof IMouseWheelItem;
            if (mainHand) {
                ServerBoundPacket msg = new MWPacket(me.getScrollDeltaY() > 0);
                PacketDistributor.sendToServer(msg);
                me.setCanceled(true);
            }
        }
    }
}
