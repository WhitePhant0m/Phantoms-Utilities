package dev.wp.phantoms_utilities;

import appeng.api.implementations.blockentities.IColorableBlockEntity;
import dev.wp.phantoms_utilities.Util.PUColor;
import dev.wp.phantoms_utilities.Util.Utils;
import dev.wp.phantoms_utilities.client.gui.SprayCanColorScreen;
import dev.wp.phantoms_utilities.helpers.IMouseWheelItem;
import dev.wp.phantoms_utilities.items.SprayCan;
import dev.wp.phantoms_utilities.network.ServerBoundPacket;
import dev.wp.phantoms_utilities.network.server.MWPacket;
import dev.wp.phantoms_utilities.network.server.SprayCanColorSelectPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
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
@EventBusSubscriber(value = Dist.CLIENT, modid = PhantomsUtilities.ID)
public class PUClient {

    public PUClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    private static void clientSetup(FMLClientSetupEvent event) {
        NeoForge.EVENT_BUS.addListener(PUClient::wheelEvent);
        NeoForge.EVENT_BUS.addListener(PUClient::onKeyInput);
    }

    private static void onKeyInput(final InputEvent.InteractionKeyMappingTriggered event) {
        if (!event.isPickBlock()) return;

        final Minecraft mc = Minecraft.getInstance();
        final Player player = mc.player;
        if (player == null) return;

        if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof SprayCan) {
            if (player.isShiftKeyDown()) {
                mc.setScreen(new SprayCanColorScreen());
                event.setCanceled(true);
                return;
            }

            HitResult target = mc.hitResult;
            if (target != null && target.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHit = (BlockHitResult) target;
                BlockPos pos = blockHit.getBlockPos();
                BlockState state = player.level().getBlockState(pos);

                if (SprayCan.isBlacklisted(state)) return;

                if (Utils.isAE2Loaded) {
                    BlockEntity be = player.level().getBlockEntity(pos);
                    if (be instanceof IColorableBlockEntity colorable) {
                        for (PUColor color : PUColor.VALID_COLORS) {
                            if (color.name().equals(colorable.getColor().name())) {
                                PacketDistributor.sendToServer(new SprayCanColorSelectPacket(color));
                                event.setCanceled(true);
                                return;
                            }
                        }
                    }
                }

                String path = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
                for (PUColor color : PUColor.VALID_COLORS) {
                    if (path.contains(color.registryPrefix)) {
                        PacketDistributor.sendToServer(new SprayCanColorSelectPacket(color));
                        event.setCanceled(true);
                        return;
                    }
                }
            }
        }
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
